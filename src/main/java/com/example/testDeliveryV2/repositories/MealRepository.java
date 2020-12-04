package com.example.testDeliveryV2.repositories;

import com.example.testDeliveryV2.models.entities.Meal;
import com.example.testDeliveryV2.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>{
    List<Meal> findAllByDeletedAtIsNull();

    Meal findByIdAndDeletedAtIsNull(Long id);

    List<Meal> findAllByCategoryIdAndDeletedAtIsNull(Long id);

    @Query(value = "SELECT  *\n" +
            "from meals\n" +
            "where id in (\n" +
            "    select meal_id\n" +
            "    from order_meal\n" +
            "    where order_id = ?\n" +
            "    )\n", nativeQuery = true)
    List<Meal> findAllByOrderId(Long id);
}
