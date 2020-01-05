package com.example.twiiter.requestDTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
public class TweetsByUserIdRequestDTO {
    private Long userId;

    @Max(200)
    @Min(1)
    private Integer count = 10;

    @Min(0)
    private Integer page = 0;
}
