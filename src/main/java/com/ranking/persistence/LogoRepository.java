package com.ranking.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranking.persistence.entity.Logo;

@Repository
public interface LogoRepository extends JpaRepository<Logo, Long> {

}
