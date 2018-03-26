package com.example.demo.EudoNet;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;

public class EudoNetAPI {
  String token;

  public EudoNetAPI() throws UnirestException {
    this.connect();
  }

  public JsonNode getAllPersons() throws UnirestException {
    JsonNode bodyResponse = new JsonNode("");
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    headers.put("x-auth", token);
    String body = "{\n" +
            " \"ShowMetadata\": true,\n" +
            " \"RowsPerPage\": 50,\n" +
            " \"NumPage\": 10,\n" +
            " \"ListCols\": [\n" +
            "   201, 202, 209\n" +
            " ],\n" +
            " \"WhereCustom\": {\n" +
            "   \"WhereCustoms\": [\n" +
            "     {}\n" +
            "   ],\n" +
            "   \"Criteria\": {\n" +
            "     \"Operator\": 9,\n" +
            "     \"Field\": \"209\",\n" +
            "     \"Value\": \"1982\"\n" +
            "   },\n" +
            "   \"InterOperator\": 0\n" +
            " },\n" +
            "}";

    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Search/{descId}").routeParam("descId", "200").headers(headers).body(body).asJson();

    bodyResponse = httpRep.getBody();
    if(!this.renewToken(bodyResponse)){
      return bodyResponse;
    }
    return getAllPersons();
  }

  private void connect() throws UnirestException {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("accept", "application/json");
    headers.put("Content-Type", "application/json");
    String body = "{\n" +
            " \"SubscriberLogin\": \"\",\n" +
            " \"SubscriberPassword\": \"\",\n" +
            " \"BaseName\": \"\",\n" +
            " \"UserLogin\": \"\",\n" +
            " \"UserPassword\": \"\",\n" +
            " \"UserLang\": \"\",\n" +
            " \"ProductName\": \"\"\n" +
            "}";
    HttpResponse<JsonNode> httpRep = Unirest.post("http://xrm3.eudonet.com/EudoAPI/Authenticate/Token").headers(headers).body(body).asJson();
    this.token = httpRep.getBody().getObject().getString("token");
  }

  private void disconnect(){
    Unirest.delete("http://xrm3.eudonet.com/EudoAPI/Authenticate/Disconnect").header("x-auth", token);
  }

  private boolean renewToken(JsonNode response) throws UnirestException {
    JSONObject resultInfos = response.getObject().getJSONObject("ResultInfos");
    int nb = (int)resultInfos.getInt("ErrorNumber");
    System.out.println("ErrorNumber : " + nb);
    if(nb == 103){
      this.connect();
      System.out.println("Token renouvelé");
      return true;
    }
    System.out.println("Token valide");
    return false;
  }
}
