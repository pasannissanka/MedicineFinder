package com.medifinder.medifinder.Pharma;

import com.medifinder.medifinder.Pharma.Models.Pharma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PharmaRepository extends JpaRepository<Pharma, String> {
    @Query("select p from Pharma p where p.user.email = ?1")
    Optional<Pharma> findByUser_Email(String email);

    Optional<Pharma> findByUser_Id(String id);

    @Override
    Optional<Pharma> findById(String s);
}
