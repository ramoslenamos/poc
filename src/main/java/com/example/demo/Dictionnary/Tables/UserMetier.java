package com.example.demo.Dictionnary.Tables;

import com.example.demo.EudoNet.JsonEntities.UserInfos;

import java.util.Optional;

public interface UserMetier {
  public Optional<UserInfos> getUser(Long id);
}
