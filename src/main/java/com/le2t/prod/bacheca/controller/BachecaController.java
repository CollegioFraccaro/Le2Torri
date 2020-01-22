package com.le2t.prod.bacheca.controller;

import com.le2t.prod.authentication.model.User;
import com.le2t.prod.bacheca.model.Post;
import com.le2t.prod.bacheca.model.WritePost;
import com.le2t.prod.bacheca.service.BachecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Controller
public class BachecaController {

  private final BachecaService bachecaService;

  @Autowired
  public BachecaController(BachecaService bachecaService) {
    this.bachecaService = bachecaService;
  }

  @GetMapping("/bacheca")
  public String getBacheca(Model model) {

    List<Post> posts = bachecaService.getActivePosts();
    model.addAttribute("posts", posts);
    return "bacheca/bacheca";
  }

  @GetMapping("/comment/post")
  public String getCommentPost() {

    List<Post> posts = bachecaService.getActivePosts();
    System.out.println(posts);
    return "bacheca/comments";
  }

  @GetMapping("/write/post")
  public String writePost(Model model) {

    model.addAttribute("writePost", new WritePost());
    return "bacheca/write_post";
  }

  @PostMapping("/write/post")
  public String writePost(@AuthenticationPrincipal User user,
                          WritePost writePost,
                          Model model) {

    Post post = Post.builder()
            .username(user.getUsername())
            .name(user.getName())
            .surname(user.getSurname())
            .description(writePost.getDescription())
            .publicationTime(Instant.now())
            .comments(Collections.emptySet())
            .active(true)
            .build();

    bachecaService.savePost(post);

    return getBacheca(model);
  }
}
