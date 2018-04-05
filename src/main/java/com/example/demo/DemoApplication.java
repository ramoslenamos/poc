package com.example.demo;

import com.example.demo.Dictionnary.Tables.UserRepository;
import com.example.demo.EudoNet.EudoNetAPI;
import com.example.demo.EudoNet.JsonEntities.UserInfos;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    UserInfos userInfos = userRepository.findOneById(new Long(1));
    System.out.println("USER : " + userInfos.getUserLogin());
  }
}
