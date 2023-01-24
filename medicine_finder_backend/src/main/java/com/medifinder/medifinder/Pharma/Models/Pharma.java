package com.medifinder.medifinder.Pharma.Models;

import com.medifinder.medifinder.Auth.Model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "Pharmacy")
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
}
