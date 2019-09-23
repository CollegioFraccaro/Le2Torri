package com.le2t.prod.authentication.controller;

import com.le2t.prod.authentication.model.User;
import com.le2t.prod.authentication.model.UsernameForm;
import com.le2t.prod.authentication.repository.UserRepository;
import com.le2t.prod.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

@Controller
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/validate/account")
  public String getValidateAccount(Model model) {
    model.addAttribute("users", userService.getAccountNotEnable());
    model.addAttribute("usernameList", new UsernameForm());
    return "validate_account";
  }

  @PostMapping("/validate/account")
  public RedirectView validateAccount(@ModelAttribute("usernameList") UsernameForm usernameForm) {
    if (usernameForm.getMultiUsernameSelected() != null) {
      List<String> usernameList = Arrays.asList(usernameForm.getMultiUsernameSelected());
      List<String> usernameListNotEnable = userService.enableAccount(usernameList);
    }
    return new RedirectView("/validate/account");
  }

}
