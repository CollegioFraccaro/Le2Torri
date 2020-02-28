package com.le2t.prod.user.controller;

import com.le2t.prod.authentication.model.User;
import com.le2t.prod.authentication.repository.UserRepository;
import com.le2t.prod.user.controller.model.SingleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.le2t.prod.user.utils.Utils.checkSingleInfo;
import static com.le2t.prod.user.utils.Utils.valueNotEmpty;

@Controller
public class UserInfoController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/user/info")
  public String getUserInfo(Model model,
                            @AuthenticationPrincipal User user) {
    model.addAttribute("user", user);
    return "user_info";
  }

  @GetMapping("/user/info/{username}")
  public String getUserInfoReadOnly(Model model,
                            @PathVariable("username") String username) {

    User user = userRepository.findByUsername(username);
    model.addAttribute("user", user);

    return "user_info_view";
  }

  @GetMapping("/user/modify/{fieldName}")
  public String getModifyUserInfo(Model model,
                                  @PathVariable("fieldName") String fieldName) {
    if ("email".equals(fieldName)) {
      model.addAttribute("fieldType", fieldName);
    } else if ("phone".equals(fieldName)) {
      model.addAttribute("fieldType", fieldName);
    } else if ("linkedin".equals(fieldName)) {
      model.addAttribute("fieldType", fieldName);
    }
    model.addAttribute("singleInfo", new SingleInfo());

    return "change_info";
  }

  @PostMapping("/user/modify/{fieldType}")
  public String modifyUser(Model model,
                           @PathVariable("fieldType") String fieldType,
                           SingleInfo singleInfo,
                           @AuthenticationPrincipal User user) {
    model.addAttribute("user", user);
    boolean valueIsOk = false;

    if (valueNotEmpty(singleInfo.getFieldValue())) {
      if ("email".equals(fieldType)) {
        valueIsOk = checkSingleInfo(singleInfo.getFieldValue(), fieldType);
      } else {
        valueIsOk = checkSingleInfo(singleInfo.getFieldValue(), fieldType);
      }
    }
    if (!valueIsOk) {
      model.addAttribute("valueError","Valore inserito non valido");
      return "change_info";
    }
    if ("email".equals(fieldType)) {
      user.setEmail(singleInfo.getFieldValue());
      user.setEmail(singleInfo.getFieldValue());
      userRepository.save(user);
    } else if ("phone".equals(fieldType)) {
      user.getUserInformation().setPhoneNumber(singleInfo.getFieldValue());
      userRepository.save(user);
    } else if ("linkedin".equals(fieldType)) {
      user.getUserInformation().setLinkedinProfile(singleInfo.getFieldValue());
      userRepository.save(user);
    }
    return "user_info";
  }
}
