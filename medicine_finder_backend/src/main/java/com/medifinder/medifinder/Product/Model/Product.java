package com.medifinder.medifinder.Product.Model;


import com.medifinder.medifinder.Pharma.Models.Pharma;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.findByPharma_Id", query = "select p from Product p where p.pharma.id = :id"),
        @NamedQuery(name = "findAll", query = "select p from Product p where p.pharma.id = :id")
})
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String brandName;
    private String genericName;
    private double price;
    private boolean available;
    private String description;
    @ManyToOne
    @JoinColumn(name = "pharma_id", nullable = true)
    private Pharma pharma;
}
