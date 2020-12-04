package com.example.testDeliveryV2.repositories;

import com.example.testDeliveryV2.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByDeletedAtIsNull();
}
