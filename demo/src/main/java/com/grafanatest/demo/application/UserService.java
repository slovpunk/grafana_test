package com.grafanatest.demo.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grafanatest.demo.domain.User;
import com.grafanatest.demo.domain.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final FakerService fakerService;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void fakeUsers() {
    fakerService.fakeData();
  }

  public String getJsonUser() throws JsonProcessingException {
    var user = fakerService.getJsonUser();
    log.info("Get json user {}", user);
    return user;
  }

  public void saveUser(User user) {
    userRepository.save(user);
    log.info("User was saved: {} {}", user.getName(), user.getId());
  }

}
