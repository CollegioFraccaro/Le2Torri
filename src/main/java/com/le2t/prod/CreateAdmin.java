package com.le2t.prod;

import com.le2t.prod.authentication.model.User;
import com.le2t.prod.authentication.model.UserInformation;
import com.le2t.prod.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateAdmin implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public CreateAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) throws Exception {
/*    UserInformation userInformation = new UserInformation();
    userInformation.setLinkedinProfile("nic-admin");
    userInformation.setPhoneNumber("33344564");
    User user = new User("admin", passwordEncoder.encode("plinio"), "plinioFraccaro",
            "plinio", "fraccaro", "plinio@prova.it", true, "ROLE_ADMIN", userInformation);

    userRepository.save(user);*/
  }
}
