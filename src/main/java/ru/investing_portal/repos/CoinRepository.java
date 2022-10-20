package ru.investing_portal.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.investing_portal.models.domain.Coin;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {

    List<Coin> findCoinsByCategoriesId(int id);

}
