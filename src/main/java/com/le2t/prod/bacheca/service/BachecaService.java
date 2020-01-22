package com.le2t.prod.bacheca.service;

import com.le2t.prod.bacheca.model.Post;
import com.le2t.prod.bacheca.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BachecaService {

  private final PostRepository postRepository;

  @Autowired
  public BachecaService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getActivePosts() {
    return postRepository.findByActiveOrderByPublicationTime(true);
  }

  public void savePost(Post post) {

    postRepository.save(post);
  }
}
