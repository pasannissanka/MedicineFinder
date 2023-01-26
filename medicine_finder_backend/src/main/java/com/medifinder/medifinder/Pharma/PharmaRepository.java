package com.medifinder.medifinder.Pharma;

import com.medifinder.medifinder.Pharma.Models.Pharma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmaRepository extends JpaRepository<Pharma, String> {
    Optional<Pharma> findByUser_Id(String id);
}
