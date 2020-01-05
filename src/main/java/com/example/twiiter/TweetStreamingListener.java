package com.example.twiiter;


import com.example.twiiter.dao.TweetRepository;
import com.example.twiiter.model.Tweet;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.Optional;

@Component
public class TweetStreamingListener {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TweetStreamingListener.class);
    @Autowired
    TweetRepository tweetRepository;

    @Value("${consumerKey}")
    private String consumerKey;

    @Value("${consumerSecret}")
    private String consumerSecret;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${accessTokenSecret}")
    private String accessTokenSecret;

    @Value("#{'${userIds}'.split(',')}")
    private List<String> userIds;

    @Value("#{'${hashTags}'.split(',')}")
    private List<String> hashtags;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }


            /**
             * Deleting the tweet
             */
            @Override
            public void onDeletionNotice(StatusDeletionNotice arg) {
                logger.info("onDeletionNotice {}", arg);
                Optional<Tweet> deletedTweet = tweetRepository.getTweetsByTweetId(arg.getStatusId());
                if (deletedTweet.isPresent()) {
                    Tweet deleted = deletedTweet.get();
                    tweetRepository.delete(deleted);
                } else {
                    logger.info("Tweet not found in system");
                }
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                logger.info("userId {} upToStatusId {}", userId, upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                logger.info("StallWarning {}", warning);
            }

            /**
             * Creating a new tweet
             */
            @Override
            public void onStatus(Status status) {
                logger.info("Status {}", status);
                Tweet tweet = new Tweet();
                tweet.setCreatedAt(status.getCreatedAt());
                tweet.setTweetId(status.getId());
                tweet.setText(status.getText());
                tweet.setUserId(status.getUser().getId());
                tweetRepository.save(tweet);
                logger.info("Tweet has been created successfully");
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                logger.info("numberOfLimitedStatuses {}", numberOfLimitedStatuses);
            }
        };

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(listener);
        FilterQuery filterQuery = new FilterQuery();
        userIds.forEach(userId -> filterQuery.follow(Long.valueOf(userId)));
        hashtags.forEach(hashTag -> filterQuery.track(hashTag));

        twitterStream.filter(filterQuery);
    }
}
