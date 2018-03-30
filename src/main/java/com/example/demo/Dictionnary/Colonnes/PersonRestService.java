package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/person")
public class PersonRestService {
  @Autowired
  PersonMetier personMetier;

  @GetMapping("/all/{type}")
  public JSONObject getAll(@PathVariable("type") String type){
    try {
      return personMetier.getAll(type).getObject();
    } catch (UnirestException e) {
      return new JSONObject(e.getMessage());
    }
  }

  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public JSONObject search(@RequestBody CustomSearch customSearch){
    try {
      return personMetier.search(customSearch).getObject();
    } catch (UnirestException e) {
      return new JSONObject(e.getMessage());
    }
  }
}
