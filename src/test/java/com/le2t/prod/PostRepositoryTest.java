package com.le2t.prod;

import com.le2t.prod.bacheca.model.Post;
import com.le2t.prod.bacheca.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private PostRepository postRepository;

  @Test
  public void findByActiveOrderByPublicationTime_goodCase_test() {

    Post post1 = Post.builder()
            .active(true)
            .comments(Collections.emptyList())
            .description("post_1")
            .publicationTime(Instant.now())
            .build();

    Post post2 = Post.builder()
            .active(false)
            .comments(Collections.emptyList())
            .description("post_2")
            .publicationTime(Instant.now())
            .build();

    testEntityManager.persist(post1);
    testEntityManager.persist(post2);
    testEntityManager.flush();

    List<Post> posts = postRepository.findByActiveOrderByPublicationTime(true);

    assertNotNull(posts);
    assertFalse(posts.isEmpty());
    assertEquals(1, posts.size());
    assertEquals(post1, posts.get(0));
  }
}
