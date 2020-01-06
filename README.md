# TwitterTool
Spring-boot which uses Twitter4j to live stream tweets from twitter created by specific user or has an specifc hashtag.

Streamed tweets will be made available to end-user through api's

1. /api/v1/user_timeline -> This api will be used by frontend to show the list of tweets belonging to user
@Param -> page
          count
          user_id
          
Response 
"[
    {
        "id": 1213911868764377088,
        "text": "Adding one more tweet",
        "tweetId": 1213911868764377088,
        "created_at": "2020-01-05T19:55:23.000+0000"
    }
]"

2. /api/v1/tweet/{tweetId} -> This api will be used by frontend to show the details of an specific tweet. This can be used to plot the graph.

    {
        "id": 1213911868764377088,
        "text": "Adding one more tweet",
        "tweetId": 1213911868764377088,
        "created_at": "2020-01-05T19:55:23.000+0000"
    }    
        
