package com.le2t.prod.bacheca.repository;

import com.le2t.prod.bacheca.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
