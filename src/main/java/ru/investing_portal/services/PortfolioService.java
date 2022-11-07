package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.PortfolioFullDto;
import ru.investing_portal.dto.PortfolioShortDto;
import ru.investing_portal.mappers.PortfolioMapper;
import ru.investing_portal.models.domain.Portfolio;
import ru.investing_portal.models.domain.TransactionGroup;
import ru.investing_portal.repos.PortfolioRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    private final TransactionService transactionService;

    private final PortfolioMapper portfolioMapper;

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private Portfolio getPortfolioById(int id) {
        return portfolioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Портфолио с id " + id + " не был найден!"));
    }

    public void createPortfolio(PortfolioShortDto portfolioShortDto) {
        portfolioRepository.save(portfolioMapper.toPortfolio(portfolioShortDto));
    }

    public PortfolioFullDto findPortfolioById(int id) {
        return calculateAndConvertPortfolioData(getPortfolioById(id));
    }

    public void updatePortfolio(int id, PortfolioShortDto portfolioShortDto) {
        Portfolio dbPortfolio = getPortfolioById(id);
        portfolioShortDto.setId(id);
        portfolioMapper.updatePortfolioFromDto(portfolioShortDto, dbPortfolio);
        portfolioRepository.save(dbPortfolio);
    }

    public void deletePortfolioById(int id) {
        portfolioRepository.deleteById(id);
    }

    public List<PortfolioShortDto> findAllPortfolios(Integer pageNum, Integer perPage) {
        List<Portfolio> portfolios = portfolioRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        portfolios.forEach(el -> el.setTotalBalance(calculatePortfolioTotalBalance(el)));
        return portfolioMapper.map(portfolios);
    }

    private BigDecimal calculatePortfolioTotalBalance(Portfolio portfolio) {
        Set<TransactionGroup> transactionGroups = portfolio.getTransactionGroups();
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (TransactionGroup transactionGroup : transactionGroups) {
            transactionService.partialUpdateTransactionGroupInfo(transactionGroup);
            totalBalance = totalBalance.add(transactionGroup.getHoldingsValue());
        }
        return totalBalance;
    }

    private PortfolioFullDto calculateAndConvertPortfolioData(Portfolio portfolio) {
        Set<TransactionGroup> transactionGroups = portfolio.getTransactionGroups();
        PortfolioFullDto portfolioFullDto = portfolioMapper.toFullDto(portfolio);

        BigDecimal totalBalance = BigDecimal.ZERO;
        BigDecimal totalProfitLoss = BigDecimal.ZERO;
        HashMap<String, BigDecimal> portfolioAllocation = new HashMap<>();

        double bestPerformer = Double.MIN_VALUE; // biggest price change percentage
        double worstPerformer = Double.MAX_VALUE; // lowest price change percentage
        int bestPerformerId = 0;
        int worstPerformerId = 0;

        for (TransactionGroup transactionGroup : transactionGroups) {
            transactionService.partialUpdateTransactionGroupInfo(transactionGroup);
            totalBalance = totalBalance.add(transactionGroup.getHoldingsValue());
            totalProfitLoss = totalProfitLoss.add(transactionGroup.getPriceChange());
            portfolioAllocation.put(transactionGroup.getCoin().getName(), transactionGroup.getHoldingsValue());

            if (bestPerformer < transactionGroup.getPriceChangePercentage()) {
                bestPerformer = transactionGroup.getPriceChangePercentage();
                bestPerformerId = transactionGroup.getId();
            }
            if (worstPerformer > transactionGroup.getPriceChangePercentage()) {
                worstPerformer = transactionGroup.getPriceChangePercentage();
                worstPerformerId = transactionGroup.getId();
            }
        }

        for (Map.Entry<String, BigDecimal> set : portfolioAllocation.entrySet()) {
            set.setValue(set.getValue().divide(totalBalance, 4, RoundingMode.HALF_UP).multiply(ONE_HUNDRED));
        }

        portfolioFullDto.setTotalBalance(totalBalance);
        portfolioFullDto.setTotalProfitLoss(totalProfitLoss);
        portfolioFullDto.setAllocation(portfolioAllocation);
        portfolioFullDto.setBestPerformerId(bestPerformerId);
        portfolioFullDto.setWorstPerformerId(worstPerformerId);
        portfolioFullDto.setBalanceChange24h(totalBalance.subtract(portfolio.getBalance24h()));
        portfolioFullDto.setBalanceChangePercentage24h(ONE_HUNDRED);

//            portfolioFullDto.setBalanceChangePercentage24h(portfolioFullDto.getBalanceChange24h()
//                    .divide(portfolio.getBalance24h(), MathContext.DECIMAL32).multiply(ONE_HUNDRED));


        return portfolioFullDto;
    }

    // Сохраняет каждые 24 часа состояние портфеля
    private void updatePortfolio24hBalance() {

    }

}
