package com.medifinder.medifinder.Product.Model;


import com.medifinder.medifinder.Pharma.Models.Pharma;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String brandName;
    private String genericName;
    private double price;
    private boolean isAvailable;
    private String description;


    @ManyToMany(mappedBy = "products")
    private Set<Pharma> pharmas;
}
