package com.pasan.medifinder.cloud.oauth.service.repositories;

import com.pasan.medifinder.cloud.oauth.service.entities.AuthGrantedAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGrantedAuthorityRepository extends JpaRepository<AuthGrantedAuthority, Long> {
}
