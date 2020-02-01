package com.le2t.prod.bacheca.service;

import com.le2t.prod.bacheca.model.Comment;
import com.le2t.prod.bacheca.model.Post;
import com.le2t.prod.bacheca.repository.CommentRepository;
import com.le2t.prod.bacheca.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BachecaService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  @Autowired
  public BachecaService(PostRepository postRepository, CommentRepository commentRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
  }

  public List<Post> getActivePosts() {
    return postRepository.findByActiveOrderByPublicationTime(true);
  }

  public Post findPostById(Long postId) {
    return postRepository.findById(postId).orElse(new Post());
  }

  public void savePost(Post post) {

    postRepository.save(post);
  }

  public void saveComment(Comment comment) {

    commentRepository.save(comment);
  }
}
