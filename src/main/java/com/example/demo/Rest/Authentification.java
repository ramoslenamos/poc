package com.example.demo.Rest;

import com.example.demo.EudoNet.EudoNetAPI;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Api(value="authentification", description="Opérations liées à l'authentification")
public class Authentification {
  @Autowired
  EudoNetAPI eudoNetAPI;

  @ApiOperation(value = "Permet de se connecter")
  @GetMapping("/connect")
  public JSONObject connect(){
    JSONObject jsonObject = new JSONObject();
    try {
      eudoNetAPI.connect();
      jsonObject.put("info", "connection réussie");
      return jsonObject;
    } catch (UnirestException e) {
      e.printStackTrace();
      jsonObject.put("erreur", e.getMessage());
      return jsonObject;
    }
  }
  @ApiOperation(value = "Permet de se déconnecter")
  @GetMapping("/disconnect")
  public JSONObject disconnect(){
    eudoNetAPI.disconnect();
    return new JSONObject("déconnection réussie");
  }
}
