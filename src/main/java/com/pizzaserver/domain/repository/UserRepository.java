package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * User Repository interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value= "INSERT INTO pizza.users (login, password, token)" +
            " values (:login, :password, :token)", nativeQuery = true)
    void createUser(@Param("login") String login, @Param("password") String password, @Param("token") String token);

    User findOneByLogin(String login);

    User findOneByToken(String token);

    @Modifying
    @Transactional
    void deleteByLogin(String login);


    @Query(value= "SELECT * FROM pizza.users WHERE password=crypt(:password, password)", nativeQuery = true)
    User findByPassword(@Param("password") String password);
}
