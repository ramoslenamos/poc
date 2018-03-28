package com.example.demo.Dictionnary.Tables;

import com.example.demo.EudoNet.JsonEntities.UserInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMetierImp implements  UserMetier {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Optional<UserInfos> getUser(Long id){
    return userRepository.findById(id);
  }
}
