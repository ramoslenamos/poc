package com.example.demo.Rest;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.example.demo.Service.Datahub;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Api(value = "datahub", description = "Opérations liées à l'ensemble des données.")
public class DataHubRestService {
  @Autowired
  private Datahub datahub;
  /**
   * Datahub avancée en utilisant les critères de datahub Eudonet
   *
   * @param customSearch les critères de datahub
   * @return la liste des personnes correspondantes aux critères
   */
  @ApiOperation(value = "Datahub approfondie dans un domaine.")
  @RequestMapping(value = "/search/{domaine}", method = RequestMethod.POST, produces = "application/json")
  public String search(@RequestBody CustomSearch customSearch, @PathVariable("domaine") String domaine) {
    try {
      return datahub.search(customSearch, domaine, domaine, domaine).toString();
    } catch (UnirestException e) {
      return new JsonNode(e.getMessage()).toString();
    }
  }

  /**
   * Obtenir la liste des domaines.
   *
   * @return la liste des domaines
   */
  @ApiOperation(value = "Voir la liste des domaines.")
  @GetMapping(value = "/domaines", produces = "application/json")
  public String getDomaines() {
    return datahub.getDomains().toString();
  }

  /**
   * Obtenir la liste des propriétés d'un domaine.
   *
   * @return la liste des propriétés d'un domaine
   */
  @ApiOperation(value = "Voir la liste des propriétés d'un domaine")
  @GetMapping(value = "/proprietes/{domaine}", produces = "application/json")
  public String getProperties(@PathVariable("domaine") String domaine) {
    return datahub.getProperties(domaine).toString();
  }
}
