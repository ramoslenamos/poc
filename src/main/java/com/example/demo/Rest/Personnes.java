package com.example.demo.Rest;

import com.example.demo.Dictionnary.Colonnes.PersonneMetier;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/personne")
@Api(value="personnes", description="Opérations liées aux personnes")
public class Personnes {
  @Autowired
  private PersonneMetier personneMetier;

  @ApiOperation(value = "Voir la liste des personnes d'un certain type")
  @GetMapping(value ="/{type}", produces = "application/json")
  public String getAll(@PathVariable("type") String type){
    try {
      return personneMetier.getAll(type).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  @ApiOperation(value = "Voir la liste des anciens stagiaires d'une entreprise")
  @GetMapping(value = "/stagiaire/{organisation}", produces = "application/json")
  public String getOldTrainees(@PathVariable("organisation") String organisation){
    try {
      return personneMetier.getOldTrainees(organisation).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  @ApiOperation(value = "Recherche avancée d'une personne")
  @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
  public String search(@RequestBody CustomSearch customSearch){
    try {
      return personneMetier.search(customSearch).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }
}
