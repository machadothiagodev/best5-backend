package com.ranking.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ranking_item_vote")
public class RankingItemVote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ranking_item_id")
	private RankingItem item;

	@Column(name = "vote_date")
	private Date voteDate;

	public RankingItemVote() {
		super();
	}

	public RankingItemVote(RankingItem item, Date voteDate) {
		this.item = item;
		this.voteDate = voteDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RankingItem getItem() {
		return item;
	}

	public void setItem(RankingItem item) {
		this.item = item;
	}

	public Date getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}

}
