package com.nexoscredibanco.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private boolean isCredit; // true for Credit, false for Debit

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;
}
