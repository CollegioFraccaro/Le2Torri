package com.le2t.prod.bacheca.controller;

import com.le2t.prod.authentication.model.User;
import com.le2t.prod.bacheca.model.Comment;
import com.le2t.prod.bacheca.model.Post;
import com.le2t.prod.bacheca.model.WriteComment;
import com.le2t.prod.bacheca.model.WritePost;
import com.le2t.prod.bacheca.service.BachecaService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//TODO: aggiungere tutti i controlli per i parametri che arrivano da front-end

@Controller
public class BachecaController {

  private final BachecaService bachecaService;

  @Autowired
  public BachecaController(BachecaService bachecaService) {
    this.bachecaService = bachecaService;
  }

  @GetMapping(path = "/bacheca")
  public String getBacheca(Model model) {

    List<Post> posts = bachecaService.getActivePosts();
    posts.forEach(Post::removeDisableComment);

    model.addAttribute("posts", posts);
    return "bacheca/bacheca";
  }

  @GetMapping(path = "/comment/post/{post_id}")
  public String getCommentPost(@PathVariable("post_id") Long postId, Model model) {

    Post post = bachecaService.findPostById(postId);
    post.removeDisableComment();

    model.addAttribute("post", post);
    return "bacheca/comments";
  }

  @GetMapping(path = "/write/post")
  public String writePost(Model model) {

    model.addAttribute("writePost", new WritePost());
    return "bacheca/write_post";
  }

  @PostMapping(path = "/write/post")
  public String writePost(@AuthenticationPrincipal User user,
                          WritePost writePost,
                          Model model) {

    Post post = Post.builder()
            .username(user.getUsername())
            .name(user.getName())
            .surname(user.getSurname())
            .description(writePost.getDescription())
            .publicationTime(Instant.now())
            .comments(Collections.emptyList())
            .active(true)
            .build();

    bachecaService.savePost(post);

    return getBacheca(model);
  }

  @GetMapping(path = "/write/comment/{post_id}")
  public String writeComment(@PathVariable("post_id") Long postId,
                             @AuthenticationPrincipal User user,
                             Model model) {

    WriteComment writeComment = new WriteComment();

    model.addAttribute("write_comment", writeComment);
    model.addAttribute("post_id", postId);
    return "bacheca/add_comment";
  }

  @PostMapping("/write/comment/{post_id}")
  public String writeComment(@PathVariable("post_id") Long postId,
                             @AuthenticationPrincipal User user,
                             WriteComment writeComment,
                             Model model) {

    Post post = bachecaService.findPostById(postId);

    Comment comment = Comment.builder()
            .username(user.getUsername())
            .surname(user.getSurname())
            .name(user.getName())
            .post(post)
            .active(true)
            .publicationTime(Instant.now())
            .description(writeComment.getDescription())
            .build();
    List<Comment> comments = post.getComments();
    comments.add(comment);
    post.setComments(comments);
    bachecaService.savePost(post);

    return getCommentPost(postId, model);
  }

  @GetMapping("/{post_id}/{comment_id}/delete")
  public String deleteComment(@PathVariable("comment_id") Long commentId,
                              @PathVariable("post_id") Long postId,
                              @AuthenticationPrincipal User user,
                              Model model) {
    //TODO: creare messaggio errore
    if ( "ROLE_ADMIN".equals(user.getRole()) ) {
      Post post = bachecaService.findPostById(postId);

      post.getComments().forEach(comment -> {
        if (comment.isActive() && comment.getUniqueId().equals(commentId)) {
          comment.setActive(false);
        }
      });

      bachecaService.savePost(post);
    }
    return getCommentPost(postId, model);
  }
}
