package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public interface PersonMetier {
  public JsonNode getAll(String type) throws UnirestException;
  public JsonNode search(CustomSearch customSearch) throws UnirestException;
  public JsonNode getContactsDirection();
  public JsonNode getContactsOperationnel();
  public JsonNode getContactsRH();
  public JsonNode getStudents();
  public JsonNode getDiplomes();
  public JsonNode getInscrits();
  public JsonNode getProfs();
}
