package com.pasan.medifinder.cloud.oauth.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_user_gen")
    @SequenceGenerator(name = "auth_user_gen", sequenceName = "auth_user_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

}
