package com.ranking.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.business.FeedbackManager;
import com.ranking.business.mapper.FeedbackMapper;
import com.ranking.presentation.dto.FeedbackDto;
import com.ranking.presentation.dto.NewFeedbackDto;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Autowired
	private FeedbackManager feedbackManager;

	@PostMapping
	private FeedbackDto createFeedback(@RequestBody NewFeedbackDto newFeedbackDto) {
		return this.feedbackMapper.convertToDto(this.feedbackManager.createFeedback(newFeedbackDto));
	}
}
