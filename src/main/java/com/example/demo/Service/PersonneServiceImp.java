package com.example.demo.Service;

import com.example.demo.Dictionnary.Catalogue.CatalogueMetier;
import com.example.demo.Dictionnary.Colonnes.DefinitionMetier;
import com.example.demo.Dictionnary.Tables.Dictionnary;
import com.example.demo.Dictionnary.Tables.DictionnaryMetier;
import com.example.demo.EudoNet.EudoNetAPI;
import com.example.demo.EudoNet.JsonEntities.Criteria;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.EudoNet.JsonEntities.OrderBy;
import com.example.demo.EudoNet.JsonEntities.WhereCustom;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonneServiceImp implements PersonneService {
  @Autowired
  private DictionnaryMetier dictionnaryMetier;
  @Autowired
  private DefinitionMetier defintitionMetier;
  @Autowired
  private CatalogueMetier catalogueMetier;
  @Autowired
  private EudoNetAPI eudoNetAPI;

  /**
   * Obtenir une liste d'un sous-type de personnes.
   *
   * @param typePersonne le type de recherché
   * @return la liste d'un sous-type de personnes
   * @throws UnirestException
   */
  @Override
  public JSONObject getAll(String typePersonne, List<String> colLabels) throws UnirestException {
    JSONObject response = new JSONObject();

    Dictionnary personDic = dictionnaryMetier.labelToDictionnary("Personne");

    Map<String, String> map = new HashMap<>();
    for (String col : colLabels) {
      String id = defintitionMetier.labelToDescId(col, personDic);
      map.put(id, col);
    }
    Criteria criteria = new Criteria(defintitionMetier.labelToDescId("Type", personDic), 9, catalogueMetier.labelToDescId(typePersonne));
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);
    List<OrderBy> orderBy = new ArrayList<>();
    //orderBy.add(new OrderBy(201, 0));

    JSONArray personsEudo = eudoNetAPI.search(personDic.getIdTable(), new CustomSearch(true, 0, 0, new ArrayList<>(map.values()), whereCustom, orderBy)).getObject().getJSONArray("rows");
    JSONArray personnnes = new JSONArray();
    for (int i = 0; i < personsEudo.length(); i++) {
      JSONArray champs = personsEudo.getJSONArray(i);
      JSONObject personne = new JSONObject();
      for (int j = 0; j < champs.length(); i++) {
        JSONObject champ = champs.getJSONObject(i);
        personne.put(map.get(champ.getString("DescId")), champ.getString("Value"));
      }
      personnnes.put(personne);
    }
    response.put("personnes", personnnes);

    return response;
  }

  /**
   * Obtient la liste des anciens stagiaire d'une organisation.
   *
   * @param organisation le nom de l'organisation
   * @return la liste des anciens stagiaire de l'organisation
   * @throws UnirestException
   */
  /*
  @Override
  public JsonNode getOldTrainees(String organisation) throws UnirestException {
    List<Integer> listCols = new ArrayList<>();
    Criteria criteria = new Criteria("301", 9, organisation);
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);
    List<OrderBy> orderBy = new ArrayList<>();
    //orderBy.add(new OrderBy(201, 0));

    return eudoNetAPI.search("2400", new CustomSearch(true, 0, 0, listCols, whereCustom, orderBy));
  }*/


  /**
   * Recherche avancée en utilisant les critères de recherche Eudonet.
   *
   * @param customSearch les critères de recherche
   * @return la liste des personnes correspondantes aux critères
   * @throws UnirestException
   */
  @Override
  public JsonNode search(CustomSearch customSearch) throws UnirestException {
    return eudoNetAPI.search(dictionnaryMetier.labelToDictionnary("Personne").getIdTable(), customSearch);
  }
}
