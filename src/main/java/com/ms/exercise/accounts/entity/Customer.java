package com.ms.exercise.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int customerId;
    private String name;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "create_dt")
    private LocalDate createDt;

}