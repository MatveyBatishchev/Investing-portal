package ru.investing_portal.repos;

import org.mapstruct.Named;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.investing_portal.models.domain.Coin;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {

    // FIXME: NotNullAPI annotation want to be added
    @Override
    @Named("getCoinReferenceById")
    Coin getReferenceById(Integer id);

    List<Coin> findCoinsByCategoriesId(int id, Pageable pageable);

    List<Coin> findCoinsByWatchlistsId(int id);

    @Modifying
    @Transactional
    @Query(value="UPDATE main.coin SET current_price=:currentPrice WHERE symbol=LOWER(:symbol)", nativeQuery = true)
    void updateCoinRates(@Param(value="symbol") String symbol,
                         @Param(value="currentPrice") BigDecimal currentPrice);

    @Modifying
    @Transactional
    @Query(value = """
             UPDATE main.coin
            SET
            market_cap=:marketCap,
            high_24h=:high24h,
            low_24h=:low24h,
            price_change_24h=:priceChange24h,
            market_cap_change_24h=:marketCapChange24h,
            market_cap_change_percentage_24h=:marketCapChangePercentage24h,
            change_percentage_1h=:changePercentage1h,
            change_percentage_24h=:changePercentage24h,
            change_percentage_7d=:changePercentage7d,
            last_updated=now()
            WHERE api_id=:apiId""", nativeQuery = true)
    void updateCoinPartial(@Param(value="apiId") String apiId,
                           @Param(value="marketCap") BigDecimal marketCap,
                           @Param(value = "high24h") double high24h,
                           @Param(value = "low24h") double low24h,
                           @Param(value = "priceChange24h") BigDecimal priceChange24h,
                           @Param(value = "marketCapChange24h") BigDecimal marketCapChange24h,
                           @Param(value = "marketCapChangePercentage24h") double marketCapChangePercentage24h,
                           @Param(value = "changePercentage1h") double changePercentage1h,
                           @Param(value = "changePercentage24h") double changePercentage24h,
                           @Param(value = "changePercentage7d") double changePercentage7d);


}
