package com.example.demo;

import com.example.demo.EudoNet.EudoNetAPI;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.ProtocolException;

public class testEudoNetAPI {
  public static void main(String[] args) {
    try {
      EudoNetAPI api = new EudoNetAPI();
      System.out.println(api.getAllPersons().toString());
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }
}
