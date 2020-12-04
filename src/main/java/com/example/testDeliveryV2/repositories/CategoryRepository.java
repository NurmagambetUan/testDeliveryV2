package com.example.testDeliveryV2.repositories;


import com.example.testDeliveryV2.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    List<Category> findAllByDeletedAtIsNull();
    Category findByIdAndDeletedAtIsNull(Long id);


}
