package com.example.post.service;

import com.example.post.entity.PostDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PostService {

    @Autowired
    private RestTemplate restTemplate;

    List<PostDetails> postDetailsList = new ArrayList<>();

    public void addPost(PostDetails postDetails) {
        postDetailsList.add(postDetails);
    }

    @Async("asyncExecutor")
    public CompletableFuture<String> sendPostNotification(String notifyTo) throws InterruptedException {
        log.info("Step 2 : sendPostNotification() method started and calling notification service");
        String response = restTemplate.getForObject("http://localhost:8081/notification/"+notifyTo, String.class);
        Thread.sleep(10000);
        log.info(response);
        log.info("Step 3 : sendPostNotification() method completed");
        return CompletableFuture.completedFuture(response);
    }
}
