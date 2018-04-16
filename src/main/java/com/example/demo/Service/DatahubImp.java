package com.example.demo.Service;

import com.example.demo.Business.DefinitionMetier;
import com.example.demo.Business.DictionnaryMetier;
import com.example.demo.Domain.Definition;
import com.example.demo.Domain.Dictionnary;
import com.example.demo.EudoNet.EudoNetAPI;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.EudoNet.JsonEntities.WhereCustom;
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
public class DatahubImp implements Datahub {
  @Autowired
  private DefinitionMetier defintitionMetier;
  @Autowired
  private EudoNetAPI eudoNetAPI;
  @Autowired
  private DictionnaryMetier dictionnaryMetier;

  @Override
  public JSONObject search(CustomSearch customSearch, String dictionnarySearchLab, String dictionnaryCritLab, String dictionnaryResLab) throws UnirestException {
    Dictionnary dictionnarySearch = dictionnaryMetier.labelToDictionnary(dictionnarySearchLab);
    Dictionnary dictionnaryCrit = dictionnaryMetier.labelToDictionnary(dictionnaryCritLab);
    Dictionnary dictionnaryRes = dictionnaryMetier.labelToDictionnary(dictionnaryResLab);

    Map<String, String> map = new HashMap<>();
    List<String> definitionsDescIds = new ArrayList<>();
    for (String propriete: customSearch.getListeProprietes()) {
      definitionsDescIds.add(defintitionMetier.labelToDescId(propriete, dictionnaryRes));
      String id = defintitionMetier.labelToDescId(propriete, dictionnaryRes);
      map.put(id, propriete);
    }
    customSearch.setListeProprietes(definitionsDescIds);
    String champLabel = customSearch.getFiltre().getCritere().getChamp();
    customSearch.getFiltre().getCritere().setChamp(defintitionMetier.labelToDescId(champLabel, dictionnaryCrit));

    if(customSearch.getFiltre().getFiltres() != null){
      if(customSearch.getFiltre().getFiltres().get(0) != null) {
        customSearch.getFiltre().setFiltres(this.translateFiltres(customSearch.getFiltre().getFiltres(), dictionnarySearch));
      }else{
        customSearch.getFiltre().getFiltres().remove(0);
        WhereCustom wc = new WhereCustom();
        wc.setFiltres(new ArrayList<>());
      }
    }

    JSONArray resultsEudo = eudoNetAPI.search(dictionnarySearch.getIdTable(), customSearch).getObject().getJSONObject("ResultData").getJSONArray("Rows");;
    return prettify(resultsEudo, map);
  }

  @Override
  public JSONObject getDomains() {
      List<Dictionnary> dictionnaries = dictionnaryMetier.getAllTables();
      JSONObject response = new JSONObject();
      JSONArray domains = new JSONArray();
      for (Dictionnary dictionnary : dictionnaries) {
        domains.put(dictionnary.getTableName());
      }
      response.put("Domaines", domains);
      return response;
  }

  @Override
  public JSONObject getProperties(String domain) {
    Dictionnary dictionnary = dictionnaryMetier.labelToDictionnary(domain);
    List<Definition> definitions = defintitionMetier.getByDictionnary(dictionnary);
    JSONObject response = new JSONObject();
    JSONArray properties = new JSONArray();
    for (Definition definition : definitions) {
      properties.put(definition.getLabel());
    }
    response.put("Proprietes", properties);
    return response;
  }

  private List<WhereCustom> translateFiltres(List<WhereCustom> wc, Dictionnary dictionnary){
    for (WhereCustom f: wc) {
      if (f != null && f.getCritere()!=null) {
        String champLabel = f.getCritere().getChamp();
        f.getCritere().setChamp(defintitionMetier.labelToDescId(champLabel, dictionnary));
        if (f.getFiltres() != null) {
          f.setFiltres(this.translateFiltres(f.getFiltres(),dictionnary));
        }
      }else{
        f= new WhereCustom();
        f.setFiltres(new ArrayList<>());
      }
    }
    return wc;
  }

  /**
   * Créer un Json simplifié en gardant que les champs définis dans le hashmap.
   *
   * @param resultsEudo le Json provenant de l'API EudoNet
   * @param map           Contient le mapping entre les descIds Eudonet et leur label
   * @return
   */
  private JSONObject prettify(JSONArray resultsEudo, Map<String, String> map) {
    JSONObject response = new JSONObject();
    JSONArray results = new JSONArray();
    for (int i = 0; i < resultsEudo.length(); i++) {
      JSONObject champsArray = resultsEudo.getJSONObject(i);
      JSONArray champs = champsArray.getJSONArray("Fields");
      JSONObject res = new JSONObject();
      for (int j = 0; j < champs.length(); j++) {
        JSONObject champ = champs.getJSONObject(j);
        res.put(map.get(Integer.toString(champ.getInt("DescId"))), champ.get("Value"));
      }
      results.put(res);
    }
    response.put("Results", results);

    return response;
  }
}
