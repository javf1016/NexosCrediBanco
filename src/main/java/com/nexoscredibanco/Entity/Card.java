package com.nexoscredibanco.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 6)
    private Long productId;

    @Column(unique = true, nullable = false, length = 16)
    private Long cardNumber;

    @ManyToOne
    @JoinColumn(name = "cardholderId", nullable = false)
    private AccountHolder cardholder;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private double balance;
}
