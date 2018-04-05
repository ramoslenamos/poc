package com.example.demo.EudoNet;

import com.example.demo.Dictionnary.Tables.UserRepository;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.EudoNet.JsonEntities.UserInfos;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

@Service
public class EudoNetAPI {
  @Autowired
  private UserRepository userRepository;

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
    headers.put("x-auth", getToken());
    String body = new GsonBuilder().create().toJson(customSearch, CustomSearch.class);

    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Search/{descId}").routeParam("descId", "200").headers(headers).body(body).asJson();
    bodyResponse = httpRep.getBody();
    if (!renewToken(bodyResponse)) {
      return bodyResponse;
    }
    return search(descId, customSearch);
  }

  /**
   * Demander un token d'authentification.
   *
   * @throws UnirestException
   */
  public void connect() throws UnirestException {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    UserInfos userInfos = userRepository.findOneById(new Long(1));
    System.out.println("USER_LOGIN : " + userInfos.getUserLogin());
    String body = new GsonBuilder().create().toJson(userInfos, UserInfos.class);
    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Authenticate/Token").headers(headers).body(body).asJson();
    // Récupération du token
    String token = httpRep.getBody().getObject().getJSONObject("ResultData").getString("Token");
    System.out.println("TOKEN : " + token);
    this.setToken(token);
  }

  /**
   * Se déconnecter de l'API (désactivation du token).
   */
  public void disconnect() {
    Unirest.delete("http://xrm3.eudonet.com/EudoAPI/Authenticate/Disconnect").header("x-auth", getToken());
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
    if (nb == 103 || nb == 103) { // error 103 : token invalide ou introuvable
      connect();
      System.out.println("Token renouvelé");
      return true;
    }
    System.out.println("Token valide");
    return false;
  }

  private String getToken() {
    Properties prop = new Properties();
    InputStream input = null;
    String token = null;
    try {

      input = new FileInputStream("src/main/resources/config.properties");

      // load a properties file
      prop.load(input);

      // get the property value and print it out
      token = prop.getProperty("token");

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return token;
  }

  private void setToken(String token) {
    Properties prop = new Properties();
    OutputStream output = null;

    try {

      output = new FileOutputStream("src/main/resources/config.properties");

      // set the properties value
      prop.setProperty("token", token);
      // save properties to project root folder
      prop.store(output, null);

    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
