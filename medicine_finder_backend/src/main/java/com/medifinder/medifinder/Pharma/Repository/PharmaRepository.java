package com.medifinder.medifinder.Pharma.Repository;

import com.medifinder.medifinder.Pharma.Models.Pharma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmaRepository extends JpaRepository<Pharma, String> {
}
