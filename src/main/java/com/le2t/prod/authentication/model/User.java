package com.le2t.prod.authentication.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long uniqueId;

  private String username;
  private String password;
  private String avatar;
  private String name;
  private String surname;
  private String email;
  private boolean enable;
  private String role;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_information")
  private UserInformation userInformation;

  public User(String username, String password, String avatar,
              String name, String surname, String email, boolean enable,
              String role, UserInformation userInformation) {
    this.username = username;
    this.password = password;
    this.avatar = avatar;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.enable = enable;
    this.role = role;
    this.userInformation = userInformation;
  }

  public User() {
  }

  public Long getUniqueId() {
    return uniqueId;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getEmail() {
    return email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserInformation getUserInformation() {
    return userInformation;
  }

  public void setUserInformation(UserInformation userInformation) {
    this.userInformation = userInformation;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(role));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enable;
  }
}
