package com.example.demo.Dictionnary.Colonnes;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
