package com.example.twiiter.responseDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class TweetsResponseDTO {

    private Long id;
    private String text;
    @JsonProperty("created_at")
    private Date createdAt;
    private Long tweetId;
}
