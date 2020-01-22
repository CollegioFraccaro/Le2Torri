package com.le2t.prod.bacheca.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long uniqueId;
  boolean active;
  String description;
  Instant publicationTime;
  String username;
  String name;
  String surname;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "comments")
  Set<Comment> comments;

}
