package com.grafanatest.demo.infrastructure;

import com.grafanatest.demo.application.UserService;
import com.grafanatest.demo.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

  private final UserService userService;

  @GetMapping()
  public ResponseEntity<List<User>> getAllUsers() {

    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/faker")
  public void faker() {
    userService.fakeUsers();
  }

  @PostMapping("/user")
  public void saveUser(@RequestBody User user) {
    userService.saveUser(user);
    log.info("controller with user: {}", user);
  }
}
