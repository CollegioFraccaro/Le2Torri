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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  final String[] PATH_PERMITTED = new String[] {
          "/", "/index.html", "/index", "/storia",
          "/resources/**", "/images/**", "/static/**",
          "/register", "/info", "/css/**", "/bacheca", "/comment/post", "/write/post"
  };

  final String[] PATH_AUTHENTICATED = new String[] {
          "/login", "/register", "/index", "/loginError",
          "/calendar", "/validate/**", "/user/**"
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
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")

            .and()
            .authorizeRequests()
            .antMatchers(PATH_PERMITTED).permitAll()
            .antMatchers(HttpMethod.GET, PATH_AUTHENTICATED).permitAll()
            .antMatchers("/validate/**").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated();

  }



}
