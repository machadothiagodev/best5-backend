package com.ranking.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.business.RankingManager;
import com.ranking.business.mapper.RankingMapper;
import com.ranking.presentation.dto.RankingDto;

@RestController()
@RequestMapping("api/rankings")
public class RankingController {

	@Autowired
	private RankingManager rankingManager;

	@Autowired
	private RankingMapper rankingMapper;

	@GetMapping
	public List<RankingDto> getRankings(@RequestParam(name = "search", required = false) String search) {
		return this.rankingMapper.convertoToDtoList(this.rankingManager.getRankings(search));
	}

	@GetMapping("/{rankingId}")
	public RankingDto getRanking(@PathVariable Long rankingId) {
		return this.rankingMapper.convertToDto(this.rankingManager.getRanking(rankingId));
	}

	@PostMapping
	public RankingDto createRanking(@Validated @RequestBody RankingDto rankingDto) {
		return this.rankingMapper.convertToDto(this.rankingManager.createRanking(rankingDto));
	}

//	@DeleteMapping("/{rankingId}")
//	public void deleteRanking(@PathVariable Long rankingId) {
//		this.rankingManager.deleteRanking(rankingId);
//	}

}
