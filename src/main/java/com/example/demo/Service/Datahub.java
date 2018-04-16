package com.example.demo.Service;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public interface Datahub {
  /**
   * Recherche approfondie.
   *
   * @param customSearch      la recherche.
   * @param dictionnarySearch le domaine dans lequel chercher.
   * @param dictionnaryCrit   le domaine des propriétés utilisées en filtres.
   * @param dictionnaryRes    le domaine des propriétés renvoyées dans les résultat.
   * @return le résultat de la recherche
   * @throws UnirestException
   */
  public JSONObject search(CustomSearch customSearch, String dictionnarySearch, String dictionnaryCrit, String dictionnaryRes) throws UnirestException;

  /**
   * Obtenir la liste des domaines.
   *
   * @return
   */
  public JSONObject getDomains();

  /**
   * Obtenir la liste des propriétés d'un domaine.
   *
   * @param domain le domaine
   * @return la liste des propriétés d'un domaine.
   */
  public JSONObject getProperties(String domain);
}
