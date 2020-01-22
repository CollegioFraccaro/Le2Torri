package com.le2t.prod.bacheca.repository;

import com.le2t.prod.bacheca.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

  List<Post> findByActiveOrderByPublicationTime(boolean active);
}
