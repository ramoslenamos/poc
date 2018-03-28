package com.example.demo.Dictionnary.Tables;

import com.example.demo.EudoNet.JsonEntities.UserInfos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfos, Long> {
  UserInfos findByBaseName(String base);
}
