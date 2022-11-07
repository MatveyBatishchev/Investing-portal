package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionGroupDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.mappers.TransactionMapper;
import ru.investing_portal.models.domain.Coin;
import ru.investing_portal.models.domain.Transaction;
import ru.investing_portal.models.domain.TransactionGroup;
import ru.investing_portal.models.domain.TransactionType;
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
            TransactionGroup newTransactionGroup = transactionMapper.toTransactionGroup(transactionCreateDto);
            // FIXME
            if (transaction.getTransactionType() == TransactionType.BUY) {
                newTransactionGroup.setTotalSpend(transaction.getSum());
            }
            else {
                newTransactionGroup.setTotalSpend(BigDecimal.ONE);
            }
            transaction.setTransactionGroup(newTransactionGroup);
            newTransactionGroup.getTransactions().add(transaction);
            transactionGroupRepository.save(newTransactionGroup);
        }
        else {
            transaction.setTransactionGroup(transactionGroup);
            transactionRepository.save(transaction);
            transactionGroup.getTransactions().add(transaction);
            fullUpdateTransactionGroupInfo(transactionGroup); // full update and save
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
        fullUpdateTransactionGroupInfo(dbTransaction.getTransactionGroup()); // full update and save group
        transactionGroupRepository.save(dbTransaction.getTransactionGroup());
    }

    public void deleteTransactionById(int id) {
        TransactionGroup transactionGroup = getTransactionById(id).getTransactionGroup();
        transactionRepository.deleteById(id);
        fullUpdateTransactionGroupInfo(transactionGroup); // full update and save group
        transactionGroupRepository.save(transactionGroup);

    }

    public List<TransactionReadDto> findTransactionsByGroupId(int groupId) {
        return transactionMapper.mapTransactions(transactionRepository.findTransactionsByTransactionGroupId(groupId));
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
        return transactionMapper.mapGroups(transactionGroups);
    }

    public void partialUpdateTransactionGroupInfo(TransactionGroup transactionGroup) {
        Coin coin = transactionGroup.getCoin();
        // holdings value
        System.out.println("-----------------------------------------------");
        System.out.println(coin.getCurrentPrice());
        System.out.println(transactionGroup.getHoldings());
        transactionGroup.setHoldingsValue(coin.getCurrentPrice()
                .multiply(BigDecimal.valueOf(transactionGroup.getHoldings()), MathContext.DECIMAL32));


        // priceChange
        transactionGroup.setPriceChange(transactionGroup.getHoldingsValue()
                .subtract(transactionGroup.getTotalSpend()));

        // priceChangePercentage
        transactionGroup.setPriceChangePercentage(transactionGroup.getPriceChange()
                .divide(transactionGroup.getTotalSpend(), MathContext.DECIMAL32).multiply(ONE_HUNDRED).doubleValue());

    }

    private void fullUpdateTransactionGroupInfo(TransactionGroup transactionGroup) {
        Set<Transaction> transactions = transactionGroup.getTransactions();
        Coin coin = transactionGroup.getCoin();

        double holdings = 0.0;
        int buyTransactionsCounter = 0;
        BigDecimal avgPriceSum = BigDecimal.ZERO;
        BigDecimal totalSpend = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            switch (transaction.getTransactionType()) {
                case BUY -> {
                    holdings += transaction.getAmount();
                    avgPriceSum = avgPriceSum.add(transaction.getPricePerCoin());
                    buyTransactionsCounter++;
                    totalSpend = totalSpend.add(transaction.getSum());
                }
                case SELL -> {
                    holdings -= transaction.getAmount();
                    totalSpend = totalSpend.subtract(transaction.getSum());
                }
                case TRANSFER_IN -> holdings += transaction.getAmount();
                case TRANSFER_OUT -> holdings -= transaction.getAmount();
            }
        }

        // holdings
        transactionGroup.setHoldings(holdings);

        // holdingsValue
        transactionGroup.setHoldingsValue(coin.getCurrentPrice().multiply(BigDecimal.valueOf(holdings)));

        // avgPrice
        System.out.println(avgPriceSum);
        System.out.println(buyTransactionsCounter);
        transactionGroup.setAvgPrice(avgPriceSum.divide(BigDecimal.valueOf(buyTransactionsCounter), MathContext.DECIMAL32));

        // totalSpend
        transactionGroup.setTotalSpend(totalSpend);

        // priceChange
        transactionGroup.setPriceChange(transactionGroup.getHoldingsValue().subtract(totalSpend));

        // priceChangePercentage
        transactionGroup.setPriceChangePercentage(transactionGroup.getPriceChange().divide(totalSpend, MathContext.DECIMAL32)
                .multiply(ONE_HUNDRED).doubleValue());
    }

}
