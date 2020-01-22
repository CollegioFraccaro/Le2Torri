package com.le2t.prod;

import com.le2t.prod.bacheca.model.Comment;
import com.le2t.prod.bacheca.model.Post;
import com.le2t.prod.bacheca.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Component
public class CreatPosts implements CommandLineRunner {

  private final PostRepository postRepository;

  @Autowired
  public CreatPosts(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public void run(String... args) {

    Comment comment1 = Comment.builder()
            .active(true)
            .description("ciao anche a te!")
            .publicationTime(Instant.now())
            .username("Josh")
            .build();

    Comment comment2 = Comment.builder()
            .active(true)
            .description("Ri-ciao")
            .publicationTime(Instant.now())
            .username("Paolo")
            .build();

    Set<Comment> comments = new HashSet<>();
    comments.add(comment1);
    comments.add(comment2);

    Post post = Post.builder()
            .active(true)
            .description("ciao questo è un post di prova")
            .publicationTime(Instant.now())
            .username("lucas")
            .name("luca")
            .surname("rossi")
            .comments(comments)
            .build();

    postRepository.save(post);
  }
}
