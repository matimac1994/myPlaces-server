package com.maciejak.myplaces_server.repositories;

import com.maciejak.myplaces_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u from User u where username = :username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT u from User u where email = :email")
    User findByEmail(@Param("email") String email);
}
