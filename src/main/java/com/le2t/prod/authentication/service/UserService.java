package com.le2t.prod.authentication.service;

import com.le2t.prod.authentication.model.User;
import com.le2t.prod.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean enableAccount(String username) {
    boolean successOperation = false;
    if ( username != null && !(username.isEmpty()) ) {
      User user = userRepository.findByUsername(username);
      if (user != null) {
        user.setEnable(true);
        userRepository.save(user);
        successOperation = true;
      }
    }

    return successOperation;
  }

  public List<String> enableAccount(List<String> usernameList) {
    return  usernameList.stream()
            .filter(username -> !(enableAccount(username)))
            .collect(Collectors.toList());
  }


  public List<User> getAccountNotEnable() {
/*    User user1 = new User("admin", "plinio", "plinioFraccaro",
            "plinio", "fraccaro", "plinio@prova.it", true, "ROLE_ADMIN");
    User user2 = new User("tushir", "plinio", "plinioFraccaro",
            "plinio", "fraccaro", "plinio@prova.it", true, "ROLE_ADMIN");
    User user3 = new User("pasculi", "plinio", "plinioFraccaro",
            "plinio", "fraccaro", "plinio@prova.it", true, "ROLE_ADMIN");
    List<User> users = Arrays.asList(user1, user2, user3);*/
    return userRepository.findByEnableIsFalse();
  }
}
