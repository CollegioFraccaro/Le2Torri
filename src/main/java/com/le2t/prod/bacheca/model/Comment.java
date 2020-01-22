package com.le2t.prod.bacheca.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long uniqueId;
  boolean active;
  String username;
  String description;
  Instant publicationTime;
  @ManyToOne
  @JoinColumn
  Post post;

}
