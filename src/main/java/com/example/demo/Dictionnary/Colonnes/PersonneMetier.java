package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

public interface PersonneMetier {
  public JsonNode getAll(String type) throws UnirestException;
  public JsonNode getOldTrainees(String organisation) throws UnirestException;
  public JsonNode search(CustomSearch customSearch) throws UnirestException;
}
