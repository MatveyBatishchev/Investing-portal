package ru.investing_portal.repos;

import org.mapstruct.Named;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.investing_portal.models.domain.Coin;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {

    // FIXME: NotNullAPI annotation want to be added
    @Override
    @Named("getCoinReferenceById")
    Coin getReferenceById(Integer integer);

    List<Coin> findCoinsByCategoriesId(int id, Pageable pageable);

}
