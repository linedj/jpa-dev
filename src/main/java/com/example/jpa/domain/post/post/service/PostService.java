package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(String title, String body) {

        Post post = new Post(
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                title,
                body);

        return postRepository.save(post);
    }

    public long count(){
        return postRepository.count();
    }
}