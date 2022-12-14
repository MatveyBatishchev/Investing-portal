package ru.investing_portal.repos;

import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.investing_portal.models.domain.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

    @Override
    @Named("getPortfolioReferenceById")
    Portfolio getReferenceById(Integer integer);

}
