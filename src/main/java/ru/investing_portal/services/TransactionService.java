package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionGroupDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.mappers.TransactionMapper;
import ru.investing_portal.models.domain.Transaction;
import ru.investing_portal.models.domain.TransactionGroup;
import ru.investing_portal.repos.TransactionGroupRepository;
import ru.investing_portal.repos.TransactionRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionGroupRepository transactionGroupRepository;

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    // Transaction
    private Transaction getTransactionById(int id) {
        return transactionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Транзакция с id " + id + " не была найдена!"));
    }

    public void createTransaction(TransactionCreateDto transactionCreateDto) {

        int coinId = transactionCreateDto.getCoinId();
        int portfolioId = transactionCreateDto.getPortfolioId();
        TransactionGroup transactionGroup = transactionGroupRepository.findByCoinIdAndPortfolioId(coinId, portfolioId);
        Transaction transaction = transactionMapper.toTransaction(transactionCreateDto);

        if (transactionGroup == null) {
            TransactionGroup newTransactionGroup = transactionMapper.toInitialTransactionGroup(transactionCreateDto);
            switch (transaction.getTransactionType()) {
                case BUY -> newTransactionGroup.setTotalSpend(transaction.getSum());
                case SELL -> newTransactionGroup.setTotalSpend(transaction.getSum().multiply(BigDecimal.valueOf(-1)));
                default -> newTransactionGroup.setTotalSpend(BigDecimal.ZERO);
            }
            transaction.setTransactionGroup(newTransactionGroup);
            newTransactionGroup.getTransactions().add(transaction);
            transactionGroupRepository.save(newTransactionGroup);
        }
        else {
            transaction.setTransactionGroup(transactionGroup);
            transactionGroup.getTransactions().add(transaction);
            fullUpdateTransactionGroupInfo(transactionGroup);
            transactionGroupRepository.save(transactionGroup);
        }
    }

    public TransactionReadDto findTransactionById(int id) {
        return transactionMapper.toReadDto(getTransactionById(id));
    }

    public void updateTransaction(int id, TransactionCreateDto transactionCreateDto) {
        Transaction dbTransaction = getTransactionById(id);
        transactionCreateDto.setId(id);
        transactionMapper.updateTransactionFromDto(transactionCreateDto, dbTransaction);
        transactionRepository.save(dbTransaction);
        fullUpdateTransactionGroupInfo(dbTransaction.getTransactionGroup());
        transactionGroupRepository.save(dbTransaction.getTransactionGroup());
    }

    public void deleteTransactionById(int id) {
        TransactionGroup transactionGroup = getTransactionById(id).getTransactionGroup();
        if (transactionGroup.getTransactions().size() - 1 == 0)
            deleteTransactionGroupById(transactionGroup.getId());
        else {
            transactionGroup.getTransactions().removeIf(el -> el.getId() == id);
            fullUpdateTransactionGroupInfo(transactionGroup); // full update and save group
            transactionGroupRepository.save(transactionGroup);
        }
    }

    public List<TransactionReadDto> findTransactionsByGroupId(int groupId) {
        return transactionMapper.mapToTransactionReadDto(transactionRepository.findTransactionsByTransactionGroupId(groupId));
    }


    // Transaction Groups
    private TransactionGroup getTransactionGroupById(int id) {
        return transactionGroupRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Группа транзакций с id " + id + " не была найдена!"));
    }

    public TransactionGroupDto findTransactionGroupById(int groupId) {
        TransactionGroup transactionGroup = getTransactionGroupById(groupId);
        partialUpdateTransactionGroupInfo(transactionGroup);
        return transactionMapper.toGroupDto(transactionGroup);
    }

    public void deleteTransactionGroupById(int groupId) {
        transactionGroupRepository.deleteById(groupId);
    }

    public List<TransactionGroupDto> findTransactionGroupsByPortfolioId(int portfolioId) {
        List<TransactionGroup> transactionGroups = transactionGroupRepository.findTransactionGroupsByPortfolioId(portfolioId);
        transactionGroups.forEach(this::partialUpdateTransactionGroupInfo);
        return transactionMapper.mapToGroupDto(transactionGroups);
    }

    public void partialUpdateTransactionGroupInfo(TransactionGroup transactionGroup) {
        BigDecimal currentPrice = transactionGroup.getCoin().getCurrentPrice();

        // holdings value
        transactionGroup.setHoldingsValue(currentPrice.multiply(transactionGroup.getHoldings(), MathContext.DECIMAL32));

        // priceChange
        transactionGroup.setPriceChange(transactionGroup.getHoldingsValue().subtract(transactionGroup.getTotalSpend()));

        // priceChangePercentage
        if (transactionGroup.getTotalSpend().equals(BigDecimal.ZERO)) {
            transactionGroup.setPriceChangePercentage(BigDecimal.ZERO);
        }
        else {
            transactionGroup.setPriceChangePercentage(transactionGroup.getPriceChange()
                    .divide(transactionGroup.getTotalSpend(), MathContext.DECIMAL32).multiply(ONE_HUNDRED));
        }
    }

    private void fullUpdateTransactionGroupInfo(TransactionGroup transactionGroup) {
        Set<Transaction> transactions = transactionGroup.getTransactions();
        if (transactions.size() == 0) return;

        BigDecimal currentPrice = transactionGroup.getCoin().getCurrentPrice();

        BigDecimal holdings = BigDecimal.ZERO;
        int buyTransactionsCounter = 0;
        BigDecimal avgPriceSum = BigDecimal.ZERO;
        BigDecimal totalSpend = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            switch (transaction.getTransactionType()) {
                case BUY -> {
                    holdings = holdings.add(transaction.getAmount());
                    avgPriceSum = avgPriceSum.add(transaction.getPricePerCoin());
                    buyTransactionsCounter++;
                    totalSpend = totalSpend.add(transaction.getSum());
                }
                case SELL -> {
                    holdings = holdings.subtract(transaction.getAmount());
                    totalSpend = totalSpend.subtract(transaction.getSum());
                }
                case TRANSFER_IN -> holdings = holdings.add(transaction.getAmount());
                case TRANSFER_OUT -> holdings = holdings.subtract(transaction.getAmount());
            }
        }

        // holdings
        transactionGroup.setHoldings(holdings);

        // holdingsValue
        transactionGroup.setHoldingsValue(currentPrice.multiply(holdings, MathContext.DECIMAL32));

        // avgPrice
        transactionGroup.setAvgPrice(avgPriceSum.divide(BigDecimal.valueOf(buyTransactionsCounter), MathContext.DECIMAL32));

        // totalSpend
        transactionGroup.setTotalSpend(totalSpend);

        // priceChange
        transactionGroup.setPriceChange(transactionGroup.getHoldingsValue().subtract(totalSpend));

        // priceChangePercentage
        if (transactionGroup.getTotalSpend().compareTo(BigDecimal.ZERO) == 0) {
            transactionGroup.setPriceChangePercentage(BigDecimal.ZERO);
        }
        else {
            transactionGroup.setPriceChangePercentage(transactionGroup.getPriceChange().divide(totalSpend, MathContext.DECIMAL32)
                    .multiply(ONE_HUNDRED));
        }
    }

}
