package com.example.demo.Rest;

import com.example.demo.Dictionnary.Colonnes.PersonneMetier;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/personne")
@Api(value="personnes", description="Opérations liées aux personnes")
public class Personnes {
  @Autowired
  private PersonneMetier personneMetier;

  @ApiOperation(value = "Voir la liste des personnes d'un certain type")
  @GetMapping("/{type}")
  public JSONArray getAll(@PathVariable("type") String type){
    try {
      return personneMetier.getAll(type);
    } catch (UnirestException e) {
      return new JSONArray(e.getMessage());
    }
  }

  @ApiOperation(value = "Voir la liste des anciens stagiaires d'une entreprise")
  @GetMapping("/stagiaire/{organisation}")
  public JSONArray getOldTrainees(@PathVariable("organisation") String organisation){
    try {
      return personneMetier.getOldTrainees(organisation);
    } catch (UnirestException e) {
      return new JSONArray(e.getMessage());
    }
  }

  @ApiOperation(value = "Recherche avancée d'une personne")
  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public JSONArray search(@RequestBody CustomSearch customSearch){
    try {
      return personneMetier.search(customSearch);
    } catch (UnirestException e) {
      return new JSONArray(e.getMessage());
    }
  }
}
