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
   * @param colLabels les colonnes à montrer
   * @return la liste d'un sous-type de personnes
   * @throws UnirestException
   */
  public JSONObject getAll(String typePersonne, List<String> colLabels) throws UnirestException;

  /**
   * Obtient la liste des anciens stagiaire d'une organisation.
   *
   * @param organisation le nom de l'organisation
   * @param colLabels les colonnes à montrer
   * @return la liste des anciens stagiaire de l'organisation
   * @throws UnirestException
   */
  public JSONObject getOldTrainees(String organisation, List<String> colLabels) throws UnirestException;

  /**
   * Recherche avancée en utilisant les critères de recherche Eudonet.
   *
   * @param customSearch les critères de recherche
   * @return la liste des personnes correspondantes aux critères
   * @throws UnirestException
   */
  public JsonNode search(CustomSearch customSearch) throws UnirestException;
}
