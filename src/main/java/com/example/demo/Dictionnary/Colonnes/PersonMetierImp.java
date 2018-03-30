package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.Dictionnary.Tables.dictionnaryMetier;
import com.example.demo.EudoNet.EudoNetAPI;
import com.example.demo.EudoNet.JsonEntities.Criteria;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.EudoNet.JsonEntities.OrderBy;
import com.example.demo.EudoNet.JsonEntities.WhereCustom;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PersonMetierImp implements PersonMetier {
  @Autowired
  private EudoNetAPI eudoApi;
  @Autowired
  private dictionnaryMetier dictionnaryMetier;

  @Override
  public JsonNode getAll(String type) throws UnirestException {
    String descId = this.labelToDescId(type);
    List<Integer> listCols = new ArrayList<>();
    listCols.add(201);// nom
    listCols.add(202);// prénom
    listCols.add(204);// civilité
    Criteria criteria = new Criteria("209", 9, descId);
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);
    List<OrderBy> orderBy = new ArrayList<>();
    orderBy.add(new OrderBy(201, 0));

    return eudoApi.search(200, new CustomSearch(true, 0, 0, listCols, whereCustom, orderBy));
  }

  @Override
  public JsonNode search(CustomSearch customSearch) throws UnirestException {
    return eudoApi.search(200, customSearch);
  }

  @Override
  public JsonNode getContactsDirection() {
    return null;
  }

  @Override
  public JsonNode getContactsOperationnel() {
    return null;
  }

  @Override
  public JsonNode getContactsRH() {
    return null;
  }

  @Override
  public JsonNode getStudents() {
    return null;
  }

  @Override
  public JsonNode getDiplomes() {
    return null;
  }

  @Override
  public JsonNode getInscrits() {
    return null;
  }

  @Override
  public JsonNode getProfs() {
    return null;
  }

  private String labelToDescId(String label){
    return dictionnaryMetier.labelToDescId(label);
  }
}
