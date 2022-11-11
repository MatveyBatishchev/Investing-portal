package ru.investing_portal.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.investing_portal.models.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // CHECKME: flushAutomatically and clearAutomatically
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO main.coin_category(coin_id, category_id) VALUES (:coinId, :categoryId)", nativeQuery = true)
    void addCoinToCategory(@Param(value = "categoryId") int categoryId, @Param(value = "coinId") int coinId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM main.coin_category WHERE coin_id=:coinId AND category_id=:categoryId", nativeQuery = true)
    void deleteCoinFromCategory(@Param(value = "categoryId") int categoryId, @Param(value = "coinId") int coinId);

}
