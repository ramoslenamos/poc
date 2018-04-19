package com.example.demo.Service;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public interface Datahub {
  /**
   * Recherche approfondie.
   * Info : On peut avoir trois domaines différents dans une recherche (voir service getOldTraineesByOrg).
   *
   * @param customSearch         la recherche.
   * @param dictionnarySearchLab le domaine dans lequel chercher.
   * @param dictionnaryCritLab   le domaine des propriétés utilisées en filtres.
   * @param dictionnaryResLab    le domaine des propriétés renvoyées dans les résultat.
   * @return le résultat de la recherche
   * @throws UnirestException
   */
  public JSONObject search(CustomSearch customSearch, String dictionnarySearchLab, String dictionnaryCritLab, String dictionnaryResLab) throws UnirestException;

  /**
   * Obtenir la liste des domaines.
   *
   * @return la liste des domaines
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
