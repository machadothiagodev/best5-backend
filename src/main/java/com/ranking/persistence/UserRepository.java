package com.ranking.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranking.persistence.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
