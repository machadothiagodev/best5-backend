package com.ranking.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranking.business.mapper.FeedbackMapper;
import com.ranking.persistence.FeedbackRepository;
import com.ranking.persistence.entity.Feedback;
import com.ranking.presentation.dto.NewFeedbackDto;

@Service
public class FeedbackManager {
	
	private FeedbackMapper feedbackMapper;

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public Feedback createFeedback(NewFeedbackDto newFeedbackDto) {
		return this.feedbackRepository.save(this.feedbackMapper.convertToEntity(newFeedbackDto));
	}
	
}
