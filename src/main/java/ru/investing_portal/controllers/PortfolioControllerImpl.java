package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.PortfolioDto;
import ru.investing_portal.services.PortfolioService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PortfolioControllerImpl implements PortfolioController {

    private final PortfolioService portfolioService;

    @Override
    public void create(PortfolioDto portfolioDto) {
        portfolioService.createPortfolio(portfolioDto);
    }

    @Override
    public PortfolioDto read(int id) {
        return portfolioService.findPortfolioById(id);
    }

    @Override
    public void update(int id, PortfolioDto portfolioDto) {
        portfolioService.updatePortfolio(id, portfolioDto);
    }

    @Override
    public void delete(int id) {
        portfolioService.deletePortfolioById(id);
    }

    @Override
    public List<PortfolioDto> readAll(Integer pageNum, Integer perPage) {
        return portfolioService.findAllPortfolios(pageNum, perPage);
    }

}
