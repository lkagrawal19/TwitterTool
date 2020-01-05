package com.example.twiiter.util;

import com.example.twiiter.model.Tweet;
import com.example.twiiter.responseDTOs.TweetsResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class TwiiterFormatter {

    public static List<TweetsResponseDTO> formatTweetResponse(List<Tweet> tweets) {
        List<TweetsResponseDTO> tweetsResponseDTOS = new ArrayList<>();
        tweets.forEach(value -> {
            TweetsResponseDTO tweetsResponseDTO = new TweetsResponseDTO();
            tweetsResponseDTO.setCreatedAt(value.getCreatedAt());
            tweetsResponseDTO.setId(value.getTweetId());
            tweetsResponseDTO.setText(value.getText());
            tweetsResponseDTOS.add(tweetsResponseDTO);
        });
        return tweetsResponseDTOS;
    }
}
