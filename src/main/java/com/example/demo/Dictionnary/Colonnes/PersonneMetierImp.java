package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.Dictionnary.Tables.DictionnaryMetier;
import com.example.demo.EudoNet.EudoNetAPI;
import com.example.demo.EudoNet.JsonEntities.*;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonneMetierImp implements PersonneMetier{
  @Autowired
  private DictionnaryMetier dictionnaryMetier;
  @Autowired
  private EudoNetAPI eudoNetAPI;

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

    return eudoNetAPI.search(200, new CustomSearch(true, 0, 0, listCols, whereCustom, orderBy));
  }

  @Override
  public JsonNode getOldTrainees(String organisation) throws UnirestException {
    List<Integer> listCols = new ArrayList<>();
    listCols.add(201);// nom
    listCols.add(202);// prénom
    listCols.add(204);// civilité
    Criteria criteria = new Criteria("301", 9, organisation);
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);
    List<OrderBy> orderBy = new ArrayList<>();
    orderBy.add(new OrderBy(201, 0));

    return eudoNetAPI.search(2400, new CustomSearch(true, 0, 0, listCols, whereCustom, orderBy));
  }

  @Override
  public JsonNode search(CustomSearch customSearch) throws UnirestException {
    return eudoNetAPI.search(200, customSearch);
  }

  private String labelToDescId(String label){
    return dictionnaryMetier.labelToDescId(label);
  }
}
