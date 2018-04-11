package com.example.demo.Business;

import com.example.demo.Domain.Catalogue;
import com.example.demo.Domain.Definition;
import com.example.demo.Repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  @Override
  public List<Catalogue> getByDefinition(Definition definition) {
    return catalogueRepository.findByDefinition(definition);
  }
}
