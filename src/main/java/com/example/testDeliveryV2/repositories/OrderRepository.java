package com.example.testDeliveryV2.repositories;

import com.example.testDeliveryV2.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDeletedAtIsNull();

    List<Order> findAllByDeletedAtIsNullAndUserId(Long id);

    List<Order> findAllByCreatedAtBetweenAndDeletedAtIsNull(Date from, Date till);
}
