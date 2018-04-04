package com.example.demo.EudoNet;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface EudoApi {
  /**
   * Lance une recherche avancée à partir de critères de recherche complexe.
   *
   * @param descId       l'Id de la table
   * @param customSearch critères de recherche
   * @return le résultat de la recherche si token valide, ou appel récursif après renouvellement du token.
   * @throws UnirestException
   */
  public JsonNode search(int descId, CustomSearch customSearch) throws UnirestException;

  /**
   * Se déconnecter de l'API (désactivation du token).
   */
  public void disconnect();
}
