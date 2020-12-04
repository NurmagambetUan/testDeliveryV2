package com.example.testDeliveryV2.models.entities;

import com.example.testDeliveryV2.models.audit.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_orders",
        initialValue = 1,
        allocationSize = 1
)

public class Order extends AuditModel {
    @Column(name = "overall_quantity")
    @NotNull(message = "overallQuantity is required")
    private Integer overallQuantity;

    @Column(name = "overall_price")
    @NotNull(message = "overallPrice is required")
    private Double overallPrice;

    @Column(name = "payment_type")
    @NotNull(message = "paymentType is required")
    private String paymentType;

    @Column(name = "status")
    private Integer status;

    @ManyToMany
    @JoinTable(name = "order_meal",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Meal> mealList;


    @ManyToOne
    @NotNull(message = "User is required")
    private User user;

    private Boolean paid;
    private Boolean confirmed;
    private Boolean completed;
}
