package com.example.demo.Rest;

import com.example.demo.EudoNet.EudoNetAPI;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Api(value="authentification", description="Opérations liées à l'authentification")
public class Authentification {
  @ApiOperation(value = "Permet de se connecter")
  @GetMapping("/connect")
  public JSONObject connect(){
    try {
      EudoNetAPI.connect();
      return new JSONObject("connection réussie");
    } catch (UnirestException e) {
      e.printStackTrace();
      return new JSONObject(e.getMessage());
    }
  }
  @ApiOperation(value = "Permet de se déconnecter")
  @GetMapping("/disconnect")
  public JSONObject disconnect(){
    EudoNetAPI.disconnect();
    return new JSONObject("déconnection réussie");
  }
}
