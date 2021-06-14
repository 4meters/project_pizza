package com.pizzaserver.domain.repository;

import com.pizzaserver.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
    
    User findOneByLogin(String login);

    User findOneByToken(String token);

}
