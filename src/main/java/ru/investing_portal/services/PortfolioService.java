package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.PortfolioDto;
import ru.investing_portal.mappers.PortfolioMapper;
import ru.investing_portal.models.domain.Portfolio;
import ru.investing_portal.repos.PortfolioRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    private final PortfolioMapper portfolioMapper;

    private Portfolio getPortfolioById(int id) {
        return portfolioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Портфолио с id " + id + " не был найден!"));
    }

    public void createPortfolio(PortfolioDto portfolioDto) {
        portfolioRepository.save(portfolioMapper.toPortfolio(portfolioDto));
    }

    public PortfolioDto findPortfolioById(int id) {
        return portfolioMapper.toDto(getPortfolioById(id));
    }

    public void updatePortfolio(int id, PortfolioDto portfolioDto) {
        Portfolio dbPortfolio = getPortfolioById(id);
        portfolioDto.setId(id);
        portfolioMapper.updatePortfolioFromDto(portfolioDto, dbPortfolio);
        portfolioRepository.save(dbPortfolio);
    }

    public void deletePortfolioById(int id) {
        portfolioRepository.deleteById(id);
    }

    public List<PortfolioDto> findAllPortfolios(Integer pageNum, Integer perPage) {
        List<Portfolio> portfolios = portfolioRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return portfolios.stream().map(portfolioMapper::toDto).collect(Collectors.toList());
    }




}
