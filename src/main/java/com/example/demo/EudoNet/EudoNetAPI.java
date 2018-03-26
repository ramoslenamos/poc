package com.example.demo.EudoNet;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.EudoNet.JsonEntities.UserInfos;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;

public class EudoNetAPI {
  String token;
  UserInfos userInfos;

  public EudoNetAPI(UserInfos userInfos) throws UnirestException {
    this.userInfos = userInfos;
    //this.connect();
    this.token = "fCyatI/C+7GJGmLrZ1IrGi4J6fA6z21LjIEmpuS4lJN0r6O+bs8TUTSCH6kbsxKjqAFE7EmhrU9eg2jRuy086sQYyJMlJusrUwgXZjNy6GKkEyzVOHR4i381jM6Ah7qNtWCO7NB2qYR9GduuzmH3t4wt2d2m6JSoTpv0E4Rmp9RAQMhm2kKwePTTNX70RK+YOoslw+3DLJlXAyNzz/Cd+Yomtm2Fgoo2EMF+dD1NlJqYzTLgFm8MEvGsOdV9f1wri4RFQwfaL/h1lX++82h/OWcV9wA1FUpxd4bYUKHHy12f8TeY3gXUw7ecKy2rArXWp2jMCiAp88gx5V4sE4k3Vu6EGcTuqjfwnYrZyQ==";
  }

  /**
   * Lance une recherche avancée à partir de critères de recherche complexe.
   *
   * @param descId       l'Id de la table
   * @param customSearch critères de recherche
   * @return le résultat de la recherche si token valide, ou appel récursif après renouvellement du token.
   * @throws UnirestException
   */
  public JsonNode search(int descId, CustomSearch customSearch) throws UnirestException {
    JsonNode bodyResponse = new JsonNode("");
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    headers.put("x-auth", token);
    String body = new GsonBuilder().create().toJson(customSearch, CustomSearch.class);

    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Search/{descId}").routeParam("descId", "200").headers(headers).body(body).asJson();
    bodyResponse = httpRep.getBody();
    if (!this.renewToken(bodyResponse)) {
      return bodyResponse;
    }
    return search(descId, customSearch);
  }

  /**
   * Demander un token d'authentification.
   *
   * @throws UnirestException
   */
  private void connect() throws UnirestException {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    String body = new GsonBuilder().create().toJson(userInfos, UserInfos.class);
    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Authenticate/Token").headers(headers).body(body).asJson();
    // Récupération du token
    this.token = httpRep.getBody().getObject().getJSONObject("ResultData").getString("Token");
  }

  /**
   * Se déconnecter de l'API (désactivation du token).
   */
  public void disconnect() {
    Unirest.delete("http://xrm3.eudonet.com/EudoAPI/Authenticate/Disconnect").header("x-auth", token);
  }

  /**
   * Renouvellement du token si celui-ci n'est plus valide.
   *
   * @param response la réponse d'une requête
   * @return vrai si le token a été renouvelé, false si le token est valide
   * @throws UnirestException
   */
  private boolean renewToken(JsonNode response) throws UnirestException {
    JSONObject resultInfos = response.getObject().getJSONObject("ResultInfos");
    int nb = (int) resultInfos.getInt("ErrorNumber");
    System.out.println("ErrorNumber : " + nb);
    if (nb == 103) { // error 103 : token invalide
      this.connect();
      System.out.println("Token renouvelé");
      return true;
    }
    System.out.println("Token valide");
    return false;
  }
}
