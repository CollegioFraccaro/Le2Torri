package com.le2t.prod.user.controller;

import com.le2t.prod.authentication.model.User;
import com.le2t.prod.user.controller.model.SingleInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserInfoController {

  @GetMapping("/user/info")
  public String getUserInfo(Model model,
                            @AuthenticationPrincipal User user) {
    model.addAttribute("user", user);
    return "user_info";
  }

  @GetMapping("/user/modify/{fieldName}")
  public String getModifyUserInfo(Model model,
                                  @PathVariable("fieldName") String fieldName) {
    if ("email".equals(fieldName)) {
      model.addAttribute("fieldType", fieldName);
    } else if ("phone".equals(fieldName)) {
      model.addAttribute("fieldType", fieldName);
    } else if ("linkedin".equals(fieldName)) {
      model.addAttribute("linkedin", fieldName);
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
    System.out.println("tipo campo: " + fieldType);
    System.out.println("single info print: " + singleInfo);

    return "user_info";
  }
}
