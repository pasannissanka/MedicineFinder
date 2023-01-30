package com.medifinder.medifinder.Auth;

import com.medifinder.medifinder.Auth.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Modifying
    @Query("update User u set u.verified = ?1, u.emailVerificationToken = null where u.id = ?2")
    int updateVerifiedAndEmailVerificationTokenById(Boolean verified, String id);

    @Query("select u from User u where u.emailVerificationToken = ?1")
    Optional<User> findByEmailVerificationToken(String emailVerificationToken);

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);
}
