package ru.investing_portal.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.investing_portal.models.domain.Watchlist;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {

    // CHECKME: flushAutomatically and clearAutomatically
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO main.watchlist_coin(watchlist_id, coin_id) " +
            "VALUES (:watchlistId, :coinId);", nativeQuery = true)
    void addCoinToWatchlist(@Param(value = "watchlistId") Integer watchlistId,
                            @Param(value = "coinId") Integer coinId);

}
