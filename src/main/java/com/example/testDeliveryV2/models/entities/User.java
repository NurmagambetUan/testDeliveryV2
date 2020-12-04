package com.example.testDeliveryV2.models.entities;


import com.example.testDeliveryV2.models.audit.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_users",
        initialValue = 1,
        allocationSize = 1
)

public class User extends AuditModel {

    @Column(name = "first_name")
    @NotNull(message = "first_name is required")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "last_name is required")
    private String lastName;

    @Column(name = "phone_number")
    @NotNull(message = "phone_number is required")
    private String phoneNumber;

    @Column(name = "login")
    @NotNull(message = "login is required")
    private String login;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "is_Active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "role is required")
    private Role role;


}
