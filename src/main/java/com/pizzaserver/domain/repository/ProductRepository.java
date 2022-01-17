package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * ProductCSV Repository interface
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query(value = "SELECT * FROM pizza.products_combined WHERE id=:id", nativeQuery = true)
    Product findById(Integer id);


    @Modifying
    @Transactional
    void deleteById(Integer id);

}
