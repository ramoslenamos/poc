package com.example.demo.Dictionnary.Catalogue;

import com.example.demo.Dictionnary.Colonnes.Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by othmane on 09/04/2018.
 */
@Service
public class CatalogueMetierImpl implements CatalogueMetier {
  @Autowired
  private CatalogueRepository catalogueRepository;

  @Override
  public Catalogue addInfo(Catalogue info) {
    return catalogueRepository.saveAndFlush(info);
  }

  @Override
  public String labelToDescId(String label) {
    Catalogue catalogue = catalogueRepository.findByLabel(label);
    return catalogue.getDBValue();
  }

  @Override
  public void DeleteAll() {
    catalogueRepository.deleteAll();
  }
}
