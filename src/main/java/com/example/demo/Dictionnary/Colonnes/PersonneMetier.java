package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface PersonneMetier {
  public JSONObject getAll(String type) throws UnirestException;
  public JSONObject getOldTrainees(String organisation) throws UnirestException;
  public JSONObject search(CustomSearch customSearch) throws UnirestException;
}
