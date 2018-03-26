package com.example.demo;

import com.example.demo.EudoNet.EudoNetAPI;
import com.example.demo.EudoNet.JsonEntities.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.List;

public class testEudoNetAPI {
  public static void main(String[] args) {
    try {
      // !IMPORTANT : userInfos à renseigner
      UserInfos userInfos = new UserInfos("", "", "", "", "", "", "");

      EudoNetAPI api = new EudoNetAPI(userInfos);
      // Recherche des étudiants
      System.out.println(api.search(200, buildStudentsSearch()).toString());
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }

  /**
   * Création d'une rechrche d'étudiants.
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
}
