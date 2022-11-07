package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.PortfolioFullDto;
import ru.investing_portal.dto.PortfolioShortDto;
import ru.investing_portal.services.PortfolioService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PortfolioControllerImpl implements PortfolioController {

    private final PortfolioService portfolioService;

    @Override
    public void create(PortfolioShortDto portfolioShortDto) {
        portfolioService.createPortfolio(portfolioShortDto);
    }

    @Override
    public PortfolioFullDto read(int id) {
        return portfolioService.findPortfolioById(id);
    }

    @Override
    public void update(int id, PortfolioShortDto portfolioShortDto) {
        portfolioService.updatePortfolio(id, portfolioShortDto);
    }

    @Override
    public void delete(int id) {
        portfolioService.deletePortfolioById(id);
    }

    @Override
    public List<PortfolioShortDto> readAll(Integer pageNum, Integer perPage) {
        return portfolioService.findAllPortfolios(pageNum, perPage);
    }

}
