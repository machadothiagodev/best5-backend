package com.ranking.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranking.persistence.entity.RankingItem;

public interface RankingItemRepository extends JpaRepository<RankingItem, Long> {

//	@Modifying
//	@Query("UPDATE RankingItem i SET i.votes = i.votes + 1 WHERE i.id = :id")
//	public void vote(@Param("id") Long id);

}
