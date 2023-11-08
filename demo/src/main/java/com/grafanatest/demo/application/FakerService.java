package com.grafanatest.demo.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.grafanatest.demo.domain.User;
import com.grafanatest.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FakerService {

  private final UserRepository userRepository;

  // TODO: билдер для юзеров

  public String getJsonUser() throws JsonProcessingException {

    Faker faker = new Faker();
    var name = faker.artist().name();
    var age = faker.random().nextInt(20, 50);
    var user = new User(name, age);

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(user);
  }

  public void fakeData() {

    int count = 0;
    Faker faker = new Faker();

    while (count != 100) {

      var name = faker.artist().name();
      var age = faker.random().nextInt(20, 50);
      userRepository.save(new User(name, age));
      count++;

    }
  }

}
