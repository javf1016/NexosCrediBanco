package com.nexoscredibanco.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "accountHolders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "accountHolder")
    private List<Product> products;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
