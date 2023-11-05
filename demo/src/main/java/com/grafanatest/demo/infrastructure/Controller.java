package com.grafanatest.demo.infrastructure;

import com.grafanatest.demo.application.UserService;
import com.grafanatest.demo.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

  private final UserService userService;

  public ResponseEntity<List<User>> getAllUsers() {

    return ResponseEntity.ok(userService.getAllUsers());
  }

}
