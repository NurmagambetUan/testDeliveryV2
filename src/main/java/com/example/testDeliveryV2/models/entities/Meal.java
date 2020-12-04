package com.example.testDeliveryV2.models.entities;


import com.example.testDeliveryV2.models.enums.Tag;
import com.example.testDeliveryV2.models.audit.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "meals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_meals",
        initialValue = 1,
        allocationSize = 1
)
public class Meal  extends AuditModel {
    @Column(name = "title")
    @NotNull(message = "title is required")
    private String title;

    @Column(name = "price")
    @NotNull(message = "price is required")
    private Double price;

    @Column(name = "ingredients")
    @NotNull(message = "ingredients is required")
    private String ingredients;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private Tag tag;

    @Column(name = "url")
    private String url;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "category is required")
    private Category category;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_meal",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orderList;

}
