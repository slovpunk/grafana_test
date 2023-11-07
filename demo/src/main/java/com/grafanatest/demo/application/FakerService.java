package com.grafanatest.demo.application;

import com.github.javafaker.Faker;
import com.grafanatest.demo.domain.User;
import com.grafanatest.demo.domain.UserRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FakerService {

  private final UserRepository userRepository;

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
