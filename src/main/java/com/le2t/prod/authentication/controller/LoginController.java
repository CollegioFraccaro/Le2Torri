package com.le2t.prod.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String loginPage(Model model) {
    return "login";
  }

  @GetMapping("/loginError")
  public String loginErrorPage(Model model) {
    model.addAttribute("loginError", true);

    return "login";
  }
}
