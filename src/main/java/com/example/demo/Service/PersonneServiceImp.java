package com.example.demo.Service;

import com.example.demo.Business.CatalogueMetier;
import com.example.demo.Business.DefinitionMetier;
import com.example.demo.Business.DictionnaryMetier;
import com.example.demo.Domain.Catalogue;
import com.example.demo.Domain.Definition;
import com.example.demo.Domain.Dictionnary;
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
  public JSONObject getPersonsByType(String typePersonne, List<String> colLabels) throws UnirestException {
    JSONObject response = new JSONObject();

    Dictionnary personDic = dictionnaryMetier.labelToDictionnary("Personnes");
    Map<Integer, String> map = this.buildTranslationMap(colLabels, personDic);

    Criteria criteria = new Criteria(defintitionMetier.labelToDescId("Type", personDic), 9, catalogueMetier.labelToDescId(typePersonne));
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);
    List<OrderBy> orderBy = new ArrayList<>();

    JSONArray personnesEudo = eudoNetAPI.search(personDic.getIdTable(), new CustomSearch(true, 0, 0, new ArrayList<>(map.keySet()), whereCustom, orderBy)).getObject().getJSONObject("ResultData").getJSONArray("Rows");

    return prettify(personnesEudo, map);
  }

  @Override
  public JSONObject getPersonTypes() {
    Dictionnary personDic = dictionnaryMetier.labelToDictionnary("Personnes");
    Definition def = defintitionMetier.getByLabelIdTable("Type", personDic);
    List<Catalogue> catalogues = catalogueMetier.getByDefinition(def);
    JSONObject response = new JSONObject();
    JSONArray types = new JSONArray();
    for (Catalogue catalogue: catalogues) {
      types.put(catalogue.getLabel());
    }
    response.put("Types", types);
    return response;
  }

  @Override
  public JSONObject getStudent(String numEtudiant, List<String> colLabels) throws UnirestException {
    Dictionnary personDic = dictionnaryMetier.labelToDictionnary("Personnes");

    Map<Integer, String> map = this.buildTranslationMap(colLabels, personDic);

    Criteria criteria = new Criteria(defintitionMetier.labelToDescId("N° étudiant", personDic), 9, numEtudiant);
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);
    List<OrderBy> orderBy = new ArrayList<>();

    JSONArray personnesEudo = eudoNetAPI.search(personDic.getIdTable(), new CustomSearch(true, 0, 0, new ArrayList<>(map.keySet()), whereCustom, orderBy)).getObject().getJSONObject("ResultData").getJSONArray("Rows");

    return  prettify(personnesEudo, map);
  }


  /**
   * Obtient la liste des anciens stagiaire d'une organisation.
   *
   * @param organisation le nom de l'organisation
   * @return la liste des anciens stagiaire de l'organisation
   * @throws UnirestException
   */
  @Override
  public JSONObject getOldTraineesByOrg(String organisation, List<String> colLabels) throws UnirestException {
    Dictionnary orgaDic = dictionnaryMetier.labelToDictionnary("Organisme");
    Dictionnary stageDic = dictionnaryMetier.labelToDictionnary("Stages");
    Dictionnary personDic = dictionnaryMetier.labelToDictionnary("Personnes");

    Map<Integer, String> map = this.buildTranslationMap(colLabels, personDic);
    Criteria criteria = new Criteria(defintitionMetier.labelToDescId("Sigle", orgaDic), 9, organisation);
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);
    List<OrderBy> orderBy = new ArrayList<>();

    JSONArray personnesEudo = eudoNetAPI.search(stageDic.getIdTable(), new CustomSearch(true, 0, 0, new ArrayList<>(map.keySet()), whereCustom, orderBy)).getObject().getJSONObject("ResultData").getJSONArray("Rows");

    return prettify(personnesEudo, map);
  }


  /**
   * Recherche avancée en utilisant les critères de recherche Eudonet.
   *
   * @param customSearch les critères de recherche
   * @return la liste des personnes correspondantes aux critères
   * @throws UnirestException
   */
  @Override
  public JsonNode search(CustomSearch customSearch) throws UnirestException {
    return eudoNetAPI.search(dictionnaryMetier.labelToDictionnary("Personnes").getIdTable(), customSearch);
  }

  /**
   * Créer un Json simplifié en gardant que les champs définis dans le hashmap.
   *
   * @param personnesEudo le Json provenant de l'API EudoNet
   * @param map           Contient le mapping entre les descIds Eudonet et leur label
   * @return
   */
  private JSONObject prettify(JSONArray personnesEudo, Map<Integer, String> map) {
    JSONObject response = new JSONObject();
    JSONArray personnes = new JSONArray();
    for (int i = 0; i < personnesEudo.length(); i++) {
      JSONObject champsArray = personnesEudo.getJSONObject(i);
      JSONArray champs = champsArray.getJSONArray("Fields");
      JSONObject personne = new JSONObject();
      for (int j = 0; j < champs.length(); j++) {
        JSONObject champ = champs.getJSONObject(j);
        personne.put(map.get(champ.getInt("DescId")), champ.get("Value"));
      }
      personnes.put(personne);
    }
    response.put("Results", personnes);

    return response;
  }

  /**
   * Construit un traducteur de descId en label.
   *
   * @param colLabels la liste des labels
   * @param dico      le dictionnaire
   * @return
   */
  private Map<Integer, String> buildTranslationMap(List<String> colLabels, Dictionnary dico) {
    Map<Integer, String> map = new HashMap<>();
    for (String col : colLabels) {
      String id = defintitionMetier.labelToDescId(col, dico);
      map.put(Integer.parseInt(id), col);
    }
    return map;
  }
}
