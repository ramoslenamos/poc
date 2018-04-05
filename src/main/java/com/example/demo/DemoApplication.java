package com.example.demo;

import com.example.demo.Dictionnary.Colonnes.DefinitionMetier;
import com.example.demo.Dictionnary.Tables.UserRepository;
import com.example.demo.Dictionnary.Tables.DictionnaryMetier;
import com.example.demo.EudoNet.EudoNetAPI;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;
  @Autowired
  private DictionnaryMetier DictionnaryMetier;
  @Autowired
  private DefinitionMetier definitionMetier;


  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      EudoNetAPI api = new EudoNetAPI();
      api.getListTables(DictionnaryMetier);
      api.getListTablesDetails(DictionnaryMetier,definitionMetier);

    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }

}
