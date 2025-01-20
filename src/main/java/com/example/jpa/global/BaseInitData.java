package com.example.jpa.global;

import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            // 샘플 데이터 3개 생성.

            if( postService.count() > 0 ) {
                return ;
            }

            postService.write("title1", "body1");

            postService.write("title2", "body3");

            postService.write("title3", "body3");

        };
    }

}