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
    listCols.add("Civilité");
    listCols.add("Type");
    try {
      return personneService.getPersonsByType(type, listCols).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  /**
   * Obtenir la liste des types de personnes.
   *
   * @return la liste des types de personnes
   */
  @ApiOperation(value = "Voir la liste des types de personne.")
  @GetMapping(value = "/types", produces = "application/json")
  public String getTypes() {
    return personneService.getPersonTypes().toString();
  }

  /**
   * Obtenir la liste des étudiants.
   *
   * @return la liste des étudiants.
   */
  @ApiOperation(value = "Voir la liste des étudiants.")
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
    try {
      return personneService.getPersonsByType("étudiant", listCols).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  /**
   * Obtenir les infos d'un étudiant.
   *
   * @param numEtudiant le numéro étudiant
   * @return les infos d'un étudiant
   */
  @ApiOperation(value = "Datahub d'un étudiant.")
  @GetMapping(value = "/etudiant/{numEtudiant}", produces = "application/json")
  public String getStudent(@PathVariable("numEtudiant") String numEtudiant) {
    List<String> listCols = new ArrayList<>();
    listCols.add("Nom");
    listCols.add("Prénom");
    listCols.add("Type");
    listCols.add("N° étudiant");
    listCols.add("Particule");
    listCols.add("Civilité");
    listCols.add("Date de naissance");
    listCols.add("Email pro.");
    listCols.add("Email perso");
    listCols.add("Tel. perso");
    listCols.add("Tél pro.");
    listCols.add("Fonction détaillée");
    listCols.add("Contact archivé");
    listCols.add("Obtention diplôme");
    listCols.add("Diplômes soutenus");
    listCols.add("Origine");
    listCols.add("Organisme princ°");
    listCols.add("Date désinscription");
    listCols.add("Diplôme");
    listCols.add("Réseaux sociaux");
    listCols.add("LinkedIn");
    listCols.add("Facebook");
    listCols.add("Twitter");
    listCols.add("ID Contact");

    try {
      return personneService.getStudent(numEtudiant, listCols).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  /**
   * Obtenirla liste des anciens stagiaires d'une organisation.
   *
   * @param organisation le nom de l'organisation
   * @return la liste des anciens stagiaires
   */
  @ApiOperation(value = "Voir la liste des anciens stagiaires d'une organisation.")
  @GetMapping(value = "/stagiaire/{organisation}", produces = "application/json")
  public String getOldTrainees(@PathVariable("organisation") String organisation) {
    List<String> listCols = new ArrayList<>();
    listCols.add("Nom");
    listCols.add("Prenom");
    listCols.add("Email pro.");
    listCols.add("Email perso");
    listCols.add("Tel. perso");
    listCols.add("Tél pro.");
    try {
      return personneService.getOldTraineesByOrg(organisation, listCols).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  /**
   * Datahub avancée en utilisant les critères de recherche Eudonet
   *
   * @param customSearch les critères de recherche
   * @return la liste des personnes correspondantes aux critères
   */
  @ApiOperation(value = "Datahub Eudonet avancée d'une personne.")
  @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
  public String search(@RequestBody CustomSearch customSearch) {
    try {
      return personneService.search(customSearch).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }
}
