package ru.investing_portal.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.investing_portal.models.domain.TransactionGroup;

import java.util.List;

@Repository
public interface TransactionGroupRepository extends JpaRepository<TransactionGroup, Integer> {

    List<TransactionGroup> findTransactionGroupsByPortfolioId(int portfolioId);

    TransactionGroup findByCoinIdAndPortfolioId(int coinId, int portfolioId);

}
