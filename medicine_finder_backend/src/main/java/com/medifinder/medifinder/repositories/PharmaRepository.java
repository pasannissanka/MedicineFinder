package com.medifinder.medifinder.repositories;

import com.medifinder.medifinder.entities.Pharma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmaRepository extends JpaRepository<Pharma, String> {
    @Query("select p from Pharma p where p.user.email = ?1")
    Optional<Pharma> findByUser_Email(String email);

    Optional<Pharma> findByUser_Id(String id);

    @Override
    Optional<Pharma> findById(String s);

    @Query(value = "SELECT * FROM pharmacy p " +
            "WHERE ST_DWithin(p.location, ST_SetSRID(cast(ST_MakePoint(:userLongitude, :userLatitude) AS geography) , 4326), :radius )", nativeQuery = true)
    List<Pharma> findAllByDistance(@Param("userLongitude") Double userLongitude, @Param("userLatitude") Double userLatitude, @Param("radius") Double radius);
}
