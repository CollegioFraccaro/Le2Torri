package com.le2t.prod.bacheca.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@EqualsAndHashCode(exclude = {"comments"})
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "unique_id")
  Long uniqueId;

  @Column(name = "active")
  boolean active;

  @Column(name = "description")
  String description;

  @Column(name = "publication_time")
  Instant publicationTime;

  @Column(name = "username")
  String username;

  @Column(name = "name")
  String name;

  @Column(name = "surname")
  String surname;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "comments")
  List<Comment> comments;

  public void addComment(Comment comment) {

    if (comments != null) {
      comments.add(comment);
    } else {
      comments = new ArrayList<>();
      comments.add(comment);
    }
  }
}
