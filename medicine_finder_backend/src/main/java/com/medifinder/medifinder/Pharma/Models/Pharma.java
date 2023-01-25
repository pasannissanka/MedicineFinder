package com.medifinder.medifinder.Pharma.Models;

import com.medifinder.medifinder.Auth.Model.User;
import com.medifinder.medifinder.Product.Model.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Entity
@Table(name = "pharmacy")
@NoArgsConstructor
@Accessors(chain = true)
public class Pharma {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String details;

    private String address;

    @ManyToMany()
    @JoinTable(
            name = "product_pharma",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_id")
    )
    private Set<Product> products;
}
