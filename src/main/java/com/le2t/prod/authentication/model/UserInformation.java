package com.le2t.prod.authentication.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "users_information")
public class UserInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "work_position")
  private String workPosition;

  @Column(name = "linkedin_profile")
  private String linkedinProfile;

  @Column(name = "story")
  private String story;
}
