package com.example.twiiter.controller;

import com.example.twiiter.requestDTOs.TweetsByUserIdRequestDTO;
import com.example.twiiter.responseDTOs.TweetsResponseDTO;
import com.example.twiiter.services.TwiiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TwitterController {

    private static final Logger logger = LoggerFactory.getLogger(TwitterController.class);

    @Autowired
    TwiiterService twiiterService;

    @GetMapping(value =  "/v1/user_timeline")
    public List<TweetsResponseDTO> getUserTimeline(@Valid TweetsByUserIdRequestDTO tweetsByUserIdRequestDTO, BindingResult bindingResult) throws  Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("validation failed");
        }
        return twiiterService.fetchTweetsByUserId(tweetsByUserIdRequestDTO);
    }

    @GetMapping(value =  "/v1/tweet/{tweetId}")
    public TweetsResponseDTO getTweet(@PathVariable("tweetId") Long tweetId) throws  Exception {
        return twiiterService.fetchTweetByTweetId(tweetId);
    }


}
