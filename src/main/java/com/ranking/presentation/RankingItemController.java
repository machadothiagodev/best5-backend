package com.ranking.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.business.RankingItemManager;
import com.ranking.business.mapper.RankingItemMapper;
import com.ranking.presentation.dto.RankingItemDto;

@RestController
@RequestMapping("api/rankings/{rankingId}/items")
public class RankingItemController {

	@Autowired
	private RankingItemManager rankingItemManager;

	@Autowired
	private RankingItemMapper rankingItemMapper;

	@PostMapping
	public RankingItemDto createItem(@PathVariable("rankingId") Long rankingId, @Validated @RequestBody RankingItemDto rankingItemDto) {
		return this.rankingItemMapper.convertToDto(this.rankingItemManager.createItem(rankingId, rankingItemDto));
	}

	@PutMapping("/{rankingItemId}")
	public void vote(@PathVariable("rankingId") Long rankingId, @PathVariable("rankingItemId") Long rankingItemId) {
		this.rankingItemManager.vote(rankingItemId);
	}

//	@DeleteMapping("/{rankingItemId}")
//	public void deleteItem(@PathVariable Long rankingItemId) {
//		this.rankingItemManager.deleteItem(rankingItemId);
//	}

}
