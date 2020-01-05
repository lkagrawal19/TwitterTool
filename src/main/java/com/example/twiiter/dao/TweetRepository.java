package com.example.twiiter.dao;

import com.example.twiiter.model.Tweet;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {

    @Query("select t from Tweet t where t.userId = ?1 order by t.createdAt desc")
    List<Tweet> getTweetsByUserId(Long userId, PageRequest pageRequest);

    @Query("select t from Tweet t where t.tweetId = ?1")
    Optional<Tweet> getTweetsByTweetId(Long tweetId);
}
