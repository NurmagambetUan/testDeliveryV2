package com.example.testDeliveryV2.models.entities;

import com.example.testDeliveryV2.models.audit.AuditModel;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_roles",
        initialValue = 1,
        allocationSize = 1
)
public class Role extends AuditModel {

    public final static Long ROLE_ADMIN_ID = 1L;
    public final static Long ROLE_USER = 2L;

    public final static String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public final static String ROLE_TEACHER_NAME = "ROLE_USER";

    @Column(unique = true)
    private String name;
}
