package com.grafanatest.demo.application;

import com.grafanatest.demo.domain.User;
import com.grafanatest.demo.domain.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

}
