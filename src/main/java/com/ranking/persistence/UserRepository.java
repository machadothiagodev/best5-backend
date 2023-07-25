package com.ranking.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranking.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
