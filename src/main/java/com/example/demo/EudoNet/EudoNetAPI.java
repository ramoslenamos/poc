package com.example.demo.EudoNet;

import com.example.demo.Domain.Catalogue;
import com.example.demo.Business.CatalogueMetier;
import com.example.demo.Domain.Definition;
import com.example.demo.Business.DefinitionMetier;
import com.example.demo.Domain.Dictionnary;
import com.example.demo.Business.DictionnaryMetier;
import com.example.demo.Repository.UserRepository;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.EudoNet.JsonEntities.UserInfos;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@Service
public class EudoNetAPI {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DictionnaryMetier dictionnaryMetier;
  @Autowired
  private DefinitionMetier definitionMetier;
  @Autowired
  private CatalogueMetier catalogueMetier;

  /**
   * Lance une recherche avancée à partir de critères de recherche complexe.
   *
   * @param descId       l'Id de la table
   * @param customSearch critères de recherche
   * @return le résultat de la recherche si token valide, ou appel récursif après renouvellement du token.
   * @throws UnirestException
   */
  public JsonNode search(String descId, CustomSearch customSearch) throws UnirestException {
    JsonNode bodyResponse = new JsonNode("");
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    headers.put("x-auth", getToken());
    String body = new GsonBuilder().create().toJson(customSearch, CustomSearch.class);
    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Search/{descId}").routeParam("descId", descId).headers(headers).body(body).asJson();
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
    String body = new GsonBuilder().create().toJson(userInfos, UserInfos.class);
    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Authenticate/Token").headers(headers).body(body).asJson();
    // Récupération du token
    String token = httpRep.getBody().getObject().getJSONObject("ResultData").getString("Token");
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
    System.out.println("Error EudoNetAPI:\n"+ resultInfos.toString(3));
    if (nb == 101 || nb == 103) { // error 101-103 : token invalide ou introuvable
      connect();
      System.out.println("Token renouvelé");
      return true;
    }
    System.out.println("Token valide");
    return false;
  }

  /**
   * Obtenir le token stocké dans le fichier config.properties
   *
   * @return le token
   */
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

  /**
   * Ecrire le token dans le fichier config.properties
   *
   * @param token le token
   */
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

  public void getListTables() throws UnirestException {

    JsonNode bodyResponse = new JsonNode("");
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    headers.put("x-auth", getToken());
    HttpResponse<JsonNode> httpRep = Unirest.get("http://xrm3.eudonet.com/EudoAPI/MetaInfos/ListTabs/").headers(headers).asJson();


    JSONArray tables = httpRep.getBody().getObject().getJSONObject("ResultMetaData").getJSONArray("Tables");
    for (int i = 0; i < tables.length(); i++) {
      JSONObject obj = tables.getJSONObject(i);
      int A = obj.getInt("Descid");
      String idTable = String.valueOf(A);
      String tableName = obj.getString("Label");
      Dictionnary dictionnary = new Dictionnary();
      dictionnary.setIdTable(idTable);
      dictionnary.setTableName(tableName);
      dictionnaryMetier.addInfo(dictionnary);
    }

  }

  public void getListTablesDetails() throws UnirestException {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    headers.put("x-auth", getToken());
    List<Dictionnary> dictionnaries = dictionnaryMetier.getAllTables();
    for (Dictionnary dictionnary : dictionnaries) {
      int[] fields = {0};
      JSONObject[] jsonObjects = new JSONObject[1];
      jsonObjects[0] =
              new JSONObject().put("DescId", dictionnary.getIdTable()).put("AllFields", true).put("Fields", fields);


      String jsonString = new JSONObject()
              .put("Tables", jsonObjects).toString();

      HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/MetaInfos/").
              headers(headers).body(jsonString).asJson();


      JSONArray tables = httpRep.getBody().getObject().getJSONObject("ResultMetaData").getJSONArray("Tables");

      JSONArray jsonArray = tables.getJSONObject(0).getJSONArray("Fields");

      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject obj = jsonArray.getJSONObject(i);
        int A = obj.getInt("DescId");
        String idColumn = String.valueOf(A);
        String labelName = obj.getString("Label");

        Definition definition = new Definition();
        definition.setIdColoumn(idColumn);
        definition.setLabel(labelName);
        definition.setTableName(dictionnary);
        definitionMetier.addInfo(definition);
      }
    }
  }

  public void getListCatalogs(String labelDictionnary, String labelDefinition) throws UnirestException {
    Dictionnary personDic = dictionnaryMetier.labelToDictionnary(labelDictionnary);
    String catalogDescId = definitionMetier.getByLabelIdTable(labelDefinition, personDic).getIdColoumn();
    JsonNode bodyResponse = new JsonNode("");
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    headers.put("x-auth", getToken());
    HttpResponse<JsonNode> httpRep = Unirest.get("http://xrm3.eudonet.com/EudoAPI/Catalog/{DescId}").
            routeParam("DescId", catalogDescId).headers(headers).asJson();
    System.out.println(httpRep.getBody().getObject().toString(3));
    JSONArray CatalogValue = httpRep.getBody().getObject().getJSONObject("ResultData").getJSONArray("CatalogValues");
    for (int i = 0; i < CatalogValue.length(); i++) {
      JSONObject obj = CatalogValue.getJSONObject(i);

      String DBValue = obj.getString("DBValue");
      String label = obj.getString("Label");
      Catalogue c = new Catalogue();
      c.setDBValue(DBValue);
      c.setLabel(label);

      Definition def = definitionMetier.getByColoumnId(catalogDescId);
      c.setDefinition(def);

      catalogueMetier.addInfo(c);
    }
  }

}
