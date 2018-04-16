package com.example.demo.Service;

import com.example.demo.Business.CatalogueMetier;
import com.example.demo.Business.DefinitionMetier;
import com.example.demo.Business.DictionnaryMetier;
import com.example.demo.Domain.Catalogue;
import com.example.demo.Domain.Definition;
import com.example.demo.Domain.Dictionnary;
import com.example.demo.EudoNet.JsonEntities.Criteria;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.EudoNet.JsonEntities.WhereCustom;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonneServiceImp implements PersonneService {
  @Autowired
  private DictionnaryMetier dictionnaryMetier;
  @Autowired
  private DefinitionMetier defintitionMetier;
  @Autowired
  private CatalogueMetier catalogueMetier;
  @Autowired
  private Datahub datahub;

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

    Criteria criteria = new Criteria("Type", 9, catalogueMetier.labelToDescId(typePersonne));
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);

    return datahub.search(new CustomSearch(colLabels, whereCustom), "Personnes", "Personnes", "Personnes");
  }

  @Override
  public JSONObject getPersonTypes() {
    Dictionnary personDic = dictionnaryMetier.labelToDictionnary("Personnes");
    Definition def = defintitionMetier.getByLabelIdTable("Type", personDic);
    List<Catalogue> catalogues = catalogueMetier.getByDefinition(def);
    JSONObject response = new JSONObject();
    JSONArray types = new JSONArray();
    for (Catalogue catalogue : catalogues) {
      types.put(catalogue.getLabel());
    }
    response.put("Types", types);
    return response;
  }

  /**
   * Obtenir les informations d'un étudiant.
   *
   * @param numEtudiant le numéro de l'étudiant
   * @param colLabels   les colonnes à montrer
   * @return les informations d'un étudiant
   * @throws UnirestException
   */
  @Override
  public JSONObject getStudent(String numEtudiant, List<String> colLabels) throws UnirestException {

    Criteria criteria = new Criteria("N° étudiant", 9, numEtudiant);
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);

    return datahub.search(new CustomSearch(colLabels, whereCustom), "Personnes", "Personnes", "Personnes");
  }


  /**
   * Obtenir la liste des anciens stagiaire d'une organisation.
   *
   * @param organisation le nom de l'organisation
   * @return la liste des anciens stagiaire de l'organisation
   * @throws UnirestException
   */
  @Override
  public JSONObject getOldTraineesByOrg(String organisation, List<String> colLabels) throws UnirestException {
    Criteria criteria = new Criteria("Sigle", 9, organisation);
    List<WhereCustom> whereCustoms = new ArrayList<>();
    whereCustoms.add(new WhereCustom());
    WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);

    return datahub.search(new CustomSearch(colLabels, whereCustom), "Stages", "Organisme", "Personnes");
  }


  /**
   * Datahub avancée en utilisant les critères de datahub Eudonet.
   *
   * @param customSearch les critères de datahub
   * @return la liste des personnes correspondantes aux critères
   * @throws UnirestException
   */
  @Override
  public JSONObject search(CustomSearch customSearch) throws UnirestException {
    Dictionnary personDic = dictionnaryMetier.labelToDictionnary("Personnes");
    return datahub.search(customSearch, "Personnes", "Personnes", "Personnes");
  }
}
