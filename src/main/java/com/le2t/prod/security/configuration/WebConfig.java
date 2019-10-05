package com.le2t.prod.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/info").setViewName("user_info");
    registry.addViewController("/storia").setViewName("storia");
    registry.addViewController("/").setViewName("index");
//    registry.addViewController("/people").setViewName("people");
    registry.addViewController("/calendar").setViewName("calendar");
  }
}
