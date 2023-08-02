package com.ranking.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranking.persistence.entity.OneTimePassword;

@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {

	public Optional<OneTimePassword> findFirstByEmailOrderByIdDesc(String email);

}
