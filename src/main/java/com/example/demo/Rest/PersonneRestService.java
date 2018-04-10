package com.example.demo.Rest;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.Service.PersonneService;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/personne")
@Api(value = "personnes", description = "Opérations liées aux personnes")
public class PersonneRestService {
  @Autowired
  private PersonneService personneService;

  /**
   * Obtenir une liste d'un sous-type de personnes.
   *
   * @param type le type de recherché
   * @return la liste d'un sous-type de personnes
   */
  @ApiOperation(value = "Voir la liste des personnes d'un certain type")
  @GetMapping(value = "/{type}", produces = "application/json")
  public String getAll(@PathVariable("type") String type) {
    List<String> listCols = new ArrayList<>();
    listCols.add("Nom");
    listCols.add("Prenom");
    try {
      return personneService.getAll(type, listCols).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  @ApiOperation(value = "Voir la liste des étudiants")
  @GetMapping(value = "/etudiants", produces = "application/json")
  public String getStudents() {
    List<String> listCols = new ArrayList<>();
    listCols.add("Nom");
    listCols.add("Prenom");
    listCols.add("N° étudiant");
    listCols.add("Email pro.");
    listCols.add("Email perso");
    listCols.add("Tel. perso");
    listCols.add("Tél pro.");
    listCols.add("Date de naissance");
    listCols.add("Age");
    try {
      return personneService.getAll("étudiant", listCols).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  @ApiOperation(value = "Voir la liste des anciens stagiaires d'une entreprise")
  @GetMapping(value = "/stagiaire/{organisation}", produces = "application/json")
  public String getOldTrainees(@PathVariable("organisation") String organisation){
    List<String> listCols = new ArrayList<>();
    listCols.add("Nom");
    listCols.add("Prenom");
    listCols.add("Email pro.");
    listCols.add("Email perso");
    listCols.add("Tel. perso");
    listCols.add("Tél pro.");
    listCols.add("Date de naissance");
    listCols.add("Age");
    try {
      return personneService.getOldTrainees(organisation, listCols).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  /**
   * Recherche avancée en utilisant les critères de recherche Eudonet
   *
   * @param customSearch les critères de recherche
   * @return la liste des personnes correspondantes aux critères
   */
  @ApiOperation(value = "Recherche avancée d'une personne")
  @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
  public String search(@RequestBody CustomSearch customSearch) {
    try {
      return personneService.search(customSearch).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }
}
