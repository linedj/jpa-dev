package com.example.jpa.global;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            // 샘플 데이터 3개 생성.
            // 데이터가 3개가 이미 있으면 패스
            if( postService.count() > 0 ) {
                return ;
            }

            postService.write("title1", "body1");
            postService.write("title2", "body2");
            postService.write("title3", "body3");

        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return new ApplicationRunner() {
            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                Post post = postService.findById(1L).get();
                Thread.sleep(1000);
                postService.modify(post, "new title1212", "new body1212");
            }
        };
    }

    @Bean
    @Order(3)
    public ApplicationRunner applicationRunner3() {
        return new ApplicationRunner() {
            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                Post p1 = postService.findById(1L).get();
                Post p2 = postService.findById(1L).get();

//                postService.delete(p1);
//                postService.delete(p2);

                System.out.println("=======p1 삭제========");
                postService.deleteById(1L);
                System.out.println("=======p1 삭제 완료========");
                System.out.println("=======p2 삭제========");
                postService.deleteById(2L);
                System.out.println("=======p2 삭제 완료========");
            }
        };
    }

    @Bean
    @Order(4)
    public ApplicationRunner applicationRunner4() {
        return new ApplicationRunner() {
            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                Post post = postService.findById(3L).get();

                System.out.println(post.getId() + "번 포스트를 가져왔습니다.");

                Post post2 = postService.findById(3L).get();
                System.out.println(post2.getId() + "번 포스트를 가져왔습니다.");
            }
        };
    }

}