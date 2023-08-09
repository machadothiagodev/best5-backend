package com.ranking.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranking.persistence.entity.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

//	@Modifying
//	@Query("UPDATE Banner i SET i.clicks = i.clicks + 1 WHERE i.id = :id")
//	public void click(@Param("id") Long id);

}
