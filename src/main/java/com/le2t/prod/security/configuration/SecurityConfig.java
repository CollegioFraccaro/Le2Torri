package com.le2t.prod.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  final String[] PATH_PERMITTED = new String[] {
          "/", "/index.html", "/index",
          "/resources/**", "/images/**", "/static/**",
          "/register", "/info", "/user/**"
  };

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
          throws Exception {
    auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .formLogin()
            .loginPage("/login")
            .failureUrl("/loginError")
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()

            .authorizeRequests()
            .antMatchers(PATH_PERMITTED).permitAll()
            .antMatchers(HttpMethod.GET,"/login", "/register", "/index", "/loginError").permitAll()
//            .antMatchers("/validate/**").permitAll()//.hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
            .csrf().disable();

  }



}
