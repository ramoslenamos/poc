package com.example.demo;

import com.example.demo.Business.CatalogueMetier;
import com.example.demo.Business.DefinitionMetier;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Business.DictionnaryMetier;
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
  private DictionnaryMetier dictionnaryMetier;
  @Autowired
  private DefinitionMetier definitionMetier;
  @Autowired
  private CatalogueMetier catalogueMetier;
  @Autowired
  private EudoNetAPI eudoNetAPI;


  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // Création du dictionnaire de mapping desciIds - labels
    try {
      catalogueMetier.DeleteAll();
      definitionMetier.DeleteAll();
      dictionnaryMetier.DeleteAll();
      eudoNetAPI.connect();
      eudoNetAPI.getListTables();
      eudoNetAPI.getListTablesDetails();
      eudoNetAPI.getListCatalogs("Personnes", "Type");
      eudoNetAPI.disconnect();
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }
}
