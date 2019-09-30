package com.le2t.prod.authentication.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class UserInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String phoneNumber;
  private String workPosition;
  private String linkedinProfile;
  private String sotry;
}
