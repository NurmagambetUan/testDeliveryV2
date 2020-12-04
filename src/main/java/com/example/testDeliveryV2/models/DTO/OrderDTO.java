package com.example.testDeliveryV2.models.DTO;

import com.example.testDeliveryV2.models.entities.Meal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

    private Long id;

    private Integer overallQuantity;

    private Double overallPrice;

    private String paymentType;

    private Integer status;

    private List<Meal> mealList;
}
