package com.le2t.prod.authentication.controller;

import com.le2t.prod.authentication.model.RegistrationForm;
import com.le2t.prod.authentication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping
  public String registerForm(Model model) {
    if ( model.asMap().get("registrationForm") == null ) {
      System.out.println("è entrato");
      model.addAttribute("registrationForm", new RegistrationForm());
    }
    model.asMap().forEach((key, value) -> {System.out.println("key:" + key + " value: " + value);});
    return "register";
  }

  @PostMapping
  public String processRegistration(@Valid RegistrationForm registrationForm,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationForm", result);
      redirectAttributes.addFlashAttribute("registrationForm", registrationForm);
      return "redirect:/register";
    }

    userRepository.save(registrationForm.toUser(passwordEncoder));
    System.out.println("utente: " + registrationForm.toString());
    redirectAttributes.addFlashAttribute("registerSuccess", true);
    return "redirect:/login";
  }
}
