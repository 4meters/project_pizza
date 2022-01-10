package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductCSV Repository interface
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Product findOneById(String id);

    /*@Modifying Replaced with delete+save !
    @Query(value = "UPDATE pizza.products_combined SET type = :type, description= :description, costS= :costS," +
            " costM =:costM, costL= :costL, costU=:costU WHERE id = :id", nativeQuery = true)
    void updateProduct(@Param("id") String id, @Param("type") String type, @Param("description") String description,
                    @Param("name") String name,
                    @Param("costS") String costS, @Param("costM") String costM, @Param("costL") String costL,
                    @Param("costU") String costU);*/

    void deleteById(String id);


    //@Query("INSERT INTO ProductCSV p VALUES(:id,:type) m.movieId = :movieId")
    /*@Modifying replaced with Repository.save() !
    @Query(value= "INSERT INTO pizza.products_combined (id, type, description, costS, costM, costL, costU)" +
            " values (:id, :type, :description, :costS, :costM, :costL, :costU)", nativeQuery = true)
    void insertProduct(@Param("id") String id, @Param("type") String type, @Param("description") String description,
                       @Param("name") String name,
                       @Param("costS") String costS, @Param("costM") String costM, @Param("costL") String costL,
                       @Param("costU") String costU);*/

    //TODO
    //@Modifying
    //@Query(value = ":query", nativeQuery = true)
    //void insertCustom(@Param query);

    //ProductCSV findOneByToken(String token);

}
