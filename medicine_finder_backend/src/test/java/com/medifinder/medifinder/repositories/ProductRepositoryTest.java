package com.medifinder.medifinder.repositories;

import com.medifinder.medifinder.entities.Pharma;
import com.medifinder.medifinder.entities.Product;
import com.medifinder.medifinder.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    private Pharma pharma;
    private User user;
    @Autowired
    private PharmaRepository pharmaRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = userRepository.save(new User());
        pharma = pharmaRepository.save(Pharma.builder().user(user).build());
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        pharmaRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void itShouldFindAllByPharma_Id() {
        // given
        underTest.save(Product.builder()
                .pharma(pharma)
                .price(200)
                .brandName("Brand Name 1")
                .genericName("Generic Name 1")
                .build());

        underTest.save(Product.builder()
                .pharma(pharma)
                .price(200)
                .brandName("Brand Name 2")
                .genericName("Generic Name 2")
                .build());
        // when
        List<Product> products = underTest.findAllByPharma_Id(pharma.getId());

        // then
        assertNotNull(products);
        assertEquals(products.size(), 2);
    }

    @Test
    @Disabled
    void itShouldSearchForProductsByBrandName() {
        // given
        underTest.save(Product.builder()
                .pharma(pharma)
                .price(200)
                .brandName("Brand Name 1")
                .genericName("Generic Name 1")
                .build());
        underTest.save(Product.builder()
                .pharma(pharma)
                .price(200)
                .brandName("Brand Name 2")
                .genericName("Generic Name 2")
                .build());

        // when

        // then

    }
}