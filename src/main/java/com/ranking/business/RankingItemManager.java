package com.ranking.business;

import java.util.Calendar;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ranking.business.mapper.RankingItemMapper;
import com.ranking.persistence.RankingItemRepository;
import com.ranking.persistence.entity.RankingItem;
import com.ranking.persistence.entity.RankingItemVote;
import com.ranking.persistence.entity.User;
import com.ranking.presentation.dto.RankingItemDto;

@Service
public class RankingItemManager {

	@Autowired
	private RankingManager rankingManager;

	@Autowired
	private RankingItemMapper rankingItemMapper;

	@Autowired
	private RankingItemRepository rankingItemRepository;

	public RankingItem createItem(Long rankingId, RankingItemDto rankingItemDto) {
		RankingItem rankingItem = this.rankingItemMapper.convertToEntity(rankingItemDto);

		rankingItem.setRanking(this.rankingManager.getRanking(rankingId));
		rankingItem.setCreatedBy((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		rankingItem.setCreatedDate(Calendar.getInstance().getTime());
		rankingItem.getVotes().add(new RankingItemVote(rankingItem, rankingItem.getCreatedDate()));

		return this.rankingItemRepository.save(rankingItem);
	}

	@Transactional
	public void vote(Long rankingItemId) {
		RankingItem item = this.getRankingItem(rankingItemId);
		item.getVotes().add(new RankingItemVote(item, Calendar.getInstance().getTime()));
	}

	private RankingItem getRankingItem(Long rankingItemId) {
		Optional<RankingItem> optional = this.rankingItemRepository.findById(rankingItemId);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new EntityNotFoundException(String.format("Ranking Item #%s does not exist", rankingItemId));
	}

	public void deleteItem(Long rankingItemId) {
		this.rankingItemRepository.deleteById(rankingItemId);
	}

}
