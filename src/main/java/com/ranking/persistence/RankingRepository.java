package com.ranking.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ranking.persistence.entity.Ranking;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

	@Query("SELECT DISTINCT(i.ranking) FROM RankingItem AS i WHERE UPPER(i.name) LIKE %:name% OR UPPER(i.ranking.name) LIKE %:name%")
	List<Ranking> findByName(@Param("name") String name);

	@Query("SELECT r FROM Ranking AS r WHERE r.createdBy.email = :email ORDER BY id DESC")
	List<Ranking> findByEmail(@Param("email") String email);

	@Query("SELECT r.id, r.name FROM Ranking AS r WHERE r.banners IS NULL ORDER BY name")
	List<Map<Long, String>> find();

}
