 package com.example.projeto.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.projeto.projeto.domain.user.User;



public interface UserRepository extends JpaRepository<User,String> {

UserDetails findByLogin(String login);

}
