package com.medifinder.medifinder.repositories.impl;

import com.medifinder.medifinder.entities.Product;
import com.medifinder.medifinder.repositories.custom.ProductRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final EntityManager em;

    @Override
    public List<Product> searchProducts(String brandName, String genericName, Boolean available, Double priceLow, Double priceHigh) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (brandName != null && genericName == null) {
            predicates.add(cb.like(product.get("brandName"), "%" + brandName + "%"));
        }
        if (genericName != null && brandName == null) {
            predicates.add(cb.like(product.get("genericName"), "%" + genericName + "%"));
        }
        if (genericName != null && brandName != null) {
            predicates.add(
                    cb.or(
                            cb.like(product.get("brandName"), "%" + brandName + "%"),
                            cb.like(product.get("genericName"), "%" + genericName + "%")
                    )
            );
        }
        if (available == null) {
            predicates.add((cb.equal(product.get("available"), true)));
        }
        if (available != null) {
            predicates.add((cb.equal(product.get("available"), available)));
        }
        if (priceLow != null && priceHigh == null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), priceLow));
        }
        if (priceHigh != null && priceLow == null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), priceHigh));
        }
        if (priceLow != null && priceHigh != null) {
            predicates.add(
                    cb.and(
                            cb.greaterThanOrEqualTo(product.get("price"), priceLow),
                            cb.lessThanOrEqualTo(product.get("price"), priceHigh)
                    )
            );
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
