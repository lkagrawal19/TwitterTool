package com.example.twiiter.util;

import com.example.twiiter.controller.TwitterController;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class HttpClientUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TwitterController.class);

    public HttpEntity getCall(String url, Map<String, String> params) throws IOException {

        String consumer_key = "0Dmvz5iLKHgaH695Nnt8BlaYu";
        String consumer_secret = "AytfkRwxvlVNZbxG9M735hCETMoUengMF8ezRrpnb106MdyKUE";
        String access_token = "920211137051762693-DfHb3XO0DgIRpMpmpWuei30YkKSipFX";
        String access_secret= "SHJHHPHrj4SjWzFuzOKTjwnLhHuzHNdo2gfrmAblY0MZ3";

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumer_key,
                consumer_secret);
        consumer.setTokenWithSecret(access_token, access_secret);
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = null;

        try {
            URIBuilder builder = new URIBuilder(url);
            params.forEach((k, v) -> builder.setParameter(k,v));
            httpget = new HttpGet(builder.build());
        } catch (Exception ex) {
            logger.error("Error in building params {}", ex.getLocalizedMessage());
        }

        try {
            consumer.sign(httpget);
        } catch (Exception ex) {
            logger.error("Error in signing {}", ex.getLocalizedMessage());
        }

        HttpResponse response = httpclient.execute(httpget);
        System.out.println(response.getStatusLine().toString());
        HttpEntity entity = response.getEntity();
        return entity;
    }
}
