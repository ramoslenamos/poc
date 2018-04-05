package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

public interface PersonneMetier {
  public JSONArray getAll(String type) throws UnirestException;
  public JSONArray getOldTrainees(String organisation) throws UnirestException;
  public JSONArray search(CustomSearch customSearch) throws UnirestException;
}
