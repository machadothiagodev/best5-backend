package com.ranking.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ranking.business.mapper.RankingMapper;
import com.ranking.persistence.RankingRepository;
import com.ranking.persistence.entity.Ranking;
import com.ranking.persistence.entity.RankingItem;
import com.ranking.persistence.entity.RankingItemVote;
import com.ranking.persistence.entity.User;
import com.ranking.presentation.dto.RankingDto;

@Service
public class RankingManager {

	@Autowired
	private RankingMapper rankingMapper;

	@Autowired
	private RankingRepository rankingRepository;

	public List<Ranking> getRankings(String name) {
		List<Ranking> rankings = null;

		if (name == null) {
			rankings = this.rankingRepository.findByOrderByIdDesc();
		} else {
			rankings = this.rankingRepository.findByName(name.toUpperCase());
		}

		Collections.sort(rankings);
		return rankings;
	}

	public Ranking getRanking(Long rankingId) {
		Optional<Ranking> optional = this.rankingRepository.findById(rankingId);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new EntityNotFoundException(String.format("Ranking #%s does not exist", rankingId));
	}

	public Ranking createRanking(RankingDto rankingDto) {
		Ranking ranking = this.rankingMapper.convertToEntity(rankingDto);
		ranking.setCreatedBy((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		this.initItemsVotes(ranking.getItems(), ranking);

		return this.rankingRepository.save(ranking);
	}

	private void initItemsVotes(List<RankingItem> items, Ranking ranking) {
		int length = items.size();

		for (RankingItem rankingItem : items) {
			rankingItem.setCreatedBy(ranking.getCreatedBy());
			rankingItem.setCreatedDate(ranking.getCreatedDate());

			List<RankingItemVote> votes = new ArrayList<>(length);
			for (int i = 0; i < length; i++) {
				votes.add(new RankingItemVote(rankingItem, ranking.getCreatedDate()));
			}

			rankingItem.getVotes().addAll(votes);
			length -= 1;
		}
	}

	public void deleteRanking(Long rankingId) {
		this.rankingRepository.deleteById(rankingId);
	}

}
