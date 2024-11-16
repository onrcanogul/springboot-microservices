package com.bankapp.accounts.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {
    @Id
    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "account-type")
    private String accountType;

    @Column(name = "branch-address")
    private String branchAddress;

    @Column(name="customer_id")
    private long customerId;
}
