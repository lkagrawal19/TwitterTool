package com.example.twiiter.util;

import com.example.twiiter.model.Tweet;
import com.example.twiiter.responseDTOs.TweetsResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class TwiiterFormatter {

    public static List<TweetsResponseDTO> formatTweetListResponse(List<Tweet> tweets) {
        List<TweetsResponseDTO> tweetsResponseDTOS = new ArrayList<>();
        tweets.forEach(value -> {
            TweetsResponseDTO tweetsResponseDTO = new TweetsResponseDTO();
            tweetsResponseDTO.setCreatedAt(value.getCreatedAt());
            tweetsResponseDTO.setId(value.getTweetId());
            tweetsResponseDTO.setText(value.getText());
            tweetsResponseDTO.setTweetId(value.getTweetId());
            tweetsResponseDTOS.add(tweetsResponseDTO);
        });
        return tweetsResponseDTOS;
    }

    public static TweetsResponseDTO formatTweet(Tweet tweet) {

            TweetsResponseDTO tweetsResponseDTO = new TweetsResponseDTO();
            tweetsResponseDTO.setCreatedAt(tweet.getCreatedAt());
            tweetsResponseDTO.setId(tweet.getTweetId());
            tweetsResponseDTO.setText(tweet.getText());
            tweetsResponseDTO.setTweetId(tweet.getTweetId());
        return tweetsResponseDTO;
    }
}
