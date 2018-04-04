package com.example.demo;

import com.example.demo.Dictionnary.Tables.UserRepository;
import com.example.demo.EudoNet.EudoApiImp;
import com.example.demo.EudoNet.JsonEntities.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  /**
   * Création d'une recherche d'étudiants.
   *
   * @return
   */
  private static CustomSearch buildStudentsSearch() {
    List<Integer> listCols = new ArrayList<>();
    listCols.add(201);
    listCols.add(202);

    Criteria criteria = new Criteria("209", 9, "1982");

    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());

    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);

    List<OrderBy> orderBy = new ArrayList<>();
    orderBy.add(new OrderBy(0, 0));

    return new CustomSearch(true, 10, 10, listCols, whereCustom, orderBy);
  }

  @Override
  public void run(String... args) throws Exception {
    UserInfos userInfos = userRepository.findOneById(new Long(1));
    try {
      EudoApiImp api = new EudoApiImp(userInfos);
      // Recherche des étudiants
      System.out.println(api.search(200, buildStudentsSearch()).toString());
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }
}
