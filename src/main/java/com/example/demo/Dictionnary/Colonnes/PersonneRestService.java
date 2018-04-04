package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/personne")
public class PersonneRestService {
  @Autowired
  PersonneMetier personneMetier;

  @GetMapping("/{type}")
  public JSONObject getAll(@PathVariable("type") String type){
    try {
      return personneMetier.getAll(type);
    } catch (UnirestException e) {
      return new JSONObject(e.getMessage());
    }
  }

  @GetMapping("/stagiaire/{organisation}")
  public JSONObject getOldTrainees(@PathVariable("organisation") String organisation){
    try {
      return personneMetier.getOldTrainees(organisation);
    } catch (UnirestException e) {
      return new JSONObject(e.getMessage());
    }
  }

  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public JSONObject search(@RequestBody CustomSearch customSearch){
    try {
      return personneMetier.search(customSearch);
    } catch (UnirestException e) {
      return new JSONObject(e.getMessage());
    }
  }

}
