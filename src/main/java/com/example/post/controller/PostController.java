package com.example.post.controller;

import com.example.post.entity.PostDetails;
import com.example.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add-post")
    public void addPost(@RequestBody PostDetails postDetails) throws InterruptedException {
        log.info("Step 1 : Calling sendPostNostification() method");
        CompletableFuture<String> notificationSvcResponse = postService.sendPostNotification(postDetails.getNotifyTo());
        log.info("Step 4: Notification sent");

        log.info("Step 5 : Calling add post method");
        postService.addPost(postDetails);
        log.info("Step 6 : Post save successfully");

    }
}
