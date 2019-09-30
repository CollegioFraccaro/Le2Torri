package com.le2t.prod.authentication.model;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class RegistrationForm {

  private String username;
  @NotBlank(message = "password non deve essere vuota")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",
    message = "La password deve contenere almeno una lettere maiuscola, una lettera maiuscola, un numero e lunga almeno 8 caratteri")
  private String password;
  @NotBlank(message = "digitare nuovamente la password nel campo ripeti password")
  private String repeatPassword;
  @NotBlank(message = "avatar non deve essere vuoto")
  @Size(max = 30, message = "Il numero massimo di caratteri consentito per l'avatar 30")
  private String avatar;
  @Size(max = 30, message = "Il numero massimo di caratteri consentito per il nome 30")
  private String name;
  @Size(max = 30, message = "Il numero massimo di caratteri consentito per il cognome 30")
  private String surname;
  @NotBlank(message = "email non deve essere vuota")
  @Size(max = 100, message = "Il numero massimo di caratteri consentito per la mail 100")
  @Email(message = "Email should be valid")
  private String email;

  public RegistrationForm(String username, String password, String avatar, String name, String surname, String email, String repeatPassword) {
    this.username = email;
    this.password = password;
    this.avatar = avatar;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.repeatPassword = repeatPassword;
  }

  public RegistrationForm() {
  }

  public String getUsername() {
    return email;
  }

  public String getPassword() {
    return password;
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

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRepeatPassword() {
    return repeatPassword;
  }

  public void setRepeatPassword(String repeatPassword) {
    this.repeatPassword = repeatPassword;
  }

  public User toUser(PasswordEncoder passwordEncoder) {
    return new User(email, passwordEncoder.encode(password), avatar, name, surname, email, false, "USER", new UserInformation());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RegistrationForm)) return false;
    RegistrationForm that = (RegistrationForm) o;
    return Objects.equals(username, that.username) &&
            Objects.equals(password, that.password) &&
            Objects.equals(avatar, that.avatar) &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, avatar, name, surname, email);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("RegistrationForm{");
    sb.append("username='").append(username).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", avatar='").append(avatar).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", surname='").append(surname).append('\'');
    sb.append(", mail='").append(email).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
