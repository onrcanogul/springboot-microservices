package com.bankapp.accounts.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;

}
