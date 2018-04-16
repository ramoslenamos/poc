package com.example.demo.Service;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.List;

public interface PersonneService {
  /**
   * Obtenir une liste d'un sous-type de personnes.
   *
   * @param typePersonne le type de recherché
   * @param colLabels    les colonnes à montrer
   * @return la liste d'un sous-type de personnes
   * @throws UnirestException
   */
  public JSONObject getPersonsByType(String typePersonne, List<String> colLabels) throws UnirestException;

  /**
   * Obtenir les types de personnes.
   *
   * @return les types de personnes.
   */
  public JSONObject getPersonTypes();

  /**
   * Obtenir les informations d'un étudiant
   *
   * @param numEtudiant le numéro de l'étudiant
   * @param colLabels   les colonnes à montrer
   * @return les information détaillées de l'étudiant
   * @throws UnirestException
   */
  public JSONObject getStudent(String numEtudiant, List<String> colLabels) throws UnirestException;

  /**
   * Obtenir la liste des anciens stagiaire d'une organisation.
   *
   * @param organisation le nom de l'organisation
   * @param colLabels    les colonnes à montrer
   * @return la liste des anciens stagiaire de l'organisation
   * @throws UnirestException
   */
  public JSONObject getOldTraineesByOrg(String organisation, List<String> colLabels) throws UnirestException;

  /**
   * Datahub avancée en utilisant les critères de recherche Eudonet.
   *
   * @param customSearch les critères de recherche
   * @return la liste des personnes correspondantes aux critères
   * @throws UnirestException
   */
  public JSONObject search(CustomSearch customSearch) throws UnirestException;
}
