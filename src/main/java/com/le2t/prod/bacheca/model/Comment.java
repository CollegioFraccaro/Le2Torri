package com.le2t.prod.bacheca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "unique_id")
  Long uniqueId;

  @Column(name = "active")
  boolean active;

  @Column(name = "username")
  String username;

  @Column(name = "name")
  String name;

  @Column(name = "surname")
  String surname;

  @Column(name = "description")
  String description;

  @Column(name = "publication_time")
  Instant publicationTime;

  @ManyToOne
  @JoinColumn
  Post post;

}
