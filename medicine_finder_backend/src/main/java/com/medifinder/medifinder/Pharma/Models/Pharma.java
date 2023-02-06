package com.medifinder.medifinder.Pharma.Models;

import com.medifinder.medifinder.Auth.Model.User;
import com.medifinder.medifinder.Product.Model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.util.Set;

@Data
@Entity
@Table(name = "pharmacy")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Pharma {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String details;
    private String name;
    private String address;
    @OneToMany(mappedBy = "pharma")
    private Set<Product> products;
    private Point location;
}
