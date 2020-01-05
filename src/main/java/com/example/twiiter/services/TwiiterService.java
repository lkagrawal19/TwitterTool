package com.example.twiiter.services;

import com.example.twiiter.controller.TwitterController;
import com.example.twiiter.dao.TweetRepository;
import com.example.twiiter.model.Tweet;
import com.example.twiiter.requestDTOs.TweetsByUserIdRequestDTO;
import com.example.twiiter.responseDTOs.TweetsResponseDTO;
import com.example.twiiter.util.HttpClientUtil;
import com.example.twiiter.util.TwiiterFormatter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwiiterService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TwitterController.class);

    @Autowired
    HttpClientUtil httpClientUtil;

    @Autowired
    TweetRepository tweetRepository;

    public List<TweetsResponseDTO> fetchTweetsByUserId(TweetsByUserIdRequestDTO tweetsByUserIdRequestDTO) throws Exception {
        List<Tweet> userTweets = tweetRepository.getTweetsByUserId(tweetsByUserIdRequestDTO.getUserId(), PageRequest.of(tweetsByUserIdRequestDTO.getPage(), tweetsByUserIdRequestDTO.getCount()));
        List<TweetsResponseDTO> tweetsResponseDTO = TwiiterFormatter.formatTweetResponse(userTweets);
        return tweetsResponseDTO;
    }


}
