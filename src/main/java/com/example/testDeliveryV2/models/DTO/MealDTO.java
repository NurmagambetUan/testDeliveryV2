package com.example.testDeliveryV2.models.DTO;

import com.example.testDeliveryV2.models.entities.Category;
import com.example.testDeliveryV2.models.entities.Order;
import com.example.testDeliveryV2.models.enums.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO implements Serializable {

    private Long id;

    private String title;

    private Double price;

    private String ingredients;

    private String description;

    private Tag tag;

    private String url;

    private Integer quantity;

    private Category category;

    private List<Order> orderList;
}
