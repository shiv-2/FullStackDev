package com.gentech.catering.repository;

import com.gentech.catering.entity.CateringEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CateringRepository extends JpaRepository<CateringEntity,Integer> {
    List<CateringEntity> findByCateringName(String name);
    List<CateringEntity> findByCateringCategory(String category);
    List<CateringEntity> findByCateringNameContaining(String partialName);
    List<CateringEntity> findByCateringIsVeg(boolean value);
    List<CateringEntity> findByCateringIsActive(boolean active);

    @Query("FROM CateringEntity WHERE cateringPrice BETWEEN :firstPrice AND :secPrice")
    List<CateringEntity> getAllCateringBetweenPrices(double firstPrice,double secPrice);

    @Query("FROM CateringEntity WHERE cateringQuantity>:quantity")
    List<CateringEntity> getAllCateringGreaterThanQuantity(int quantity);

    @Modifying
    @Transactional
    @Query("DELETE FROM CateringEntity WHERE cateringIsActive=false")
    void deleteAllInactiveCatering();
}
