package com.ranking.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

	public List<Ranking> getRankings(Boolean loggedin) {
		List<Ranking> rankings = new ArrayList<>();

		if (BooleanUtils.isTrue(loggedin)) {
			if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
				rankings = this.rankingRepository.findByEmail(user.getEmail());
			}
		} else {
			rankings = this.rankingRepository.findAll();
			Collections.sort(rankings);
		}

		rankings.forEach(ranking -> Collections.sort(ranking.getItems()));

		return rankings;
	}	

	public List<Ranking> searchRankings(String name) {
		List<Ranking> rankings = this.rankingRepository.findByName(name.toUpperCase());

		Collections.sort(rankings);
		rankings.forEach(ranking -> Collections.sort(ranking.getItems()));

		return rankings;
	}

	public Ranking getRanking(Long rankingId) {
		Optional<Ranking> optional = this.rankingRepository.findById(rankingId);

		if (optional.isPresent()) {
			Ranking ranking = optional.get();
			Collections.sort(ranking.getItems());

			return ranking;
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
