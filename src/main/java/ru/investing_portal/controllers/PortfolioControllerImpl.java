package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.PortfolioDto;
import ru.investing_portal.mappers.PortfolioMapper;
import ru.investing_portal.models.domain.Portfolio;
import ru.investing_portal.repos.PortfolioRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PortfolioControllerImpl implements PortfolioController {

    private final PortfolioRepository portfolioRepository;

    private final PortfolioMapper portfolioMapper;


    @Override
    public void create(PortfolioDto portfolioDto) {
        portfolioRepository.save(portfolioMapper.toPortfolio(portfolioDto));
    }

    @Override
    public PortfolioDto read(int id) {
        return portfolioMapper.toDto(portfolioRepository.findById(id).get());
    }

    @Override
    public void update(int id, PortfolioDto portfolioDto) {
        Portfolio dbPortfolio = portfolioRepository.findById(id).get();
        portfolioDto.setId(id);
        portfolioMapper.updatePortfolioFromDto(portfolioDto, dbPortfolio);
        portfolioRepository.save(dbPortfolio);
    }

    @Override
    public void delete(int id) {
        portfolioRepository.deleteById(id);
    }

    @Override
    public List<PortfolioDto> readAll(Integer pageNum, Integer perPage) {
        List<Portfolio> portfolios = portfolioRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return portfolios.stream().map(portfolioMapper::toDto).collect(Collectors.toList());
    }
}
