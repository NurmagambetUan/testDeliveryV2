package com.example.testDeliveryV2.models.entities;

import com.example.testDeliveryV2.models.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_categories",
        initialValue = 1,
        allocationSize = 1
)
public class Category extends AuditModel{
    @Column(name = "name")
    @NotNull(message = "name is required")
    private String name;

    @Column(name = "url")
    private String url;
}

