package com.example.demo.Business;

import com.example.demo.Domain.Catalogue;
import com.example.demo.Domain.Definition;
import com.example.demo.Repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by othmane on 09/04/2018.
 * Mapping des catalogues d'Eudonet.
 */
@Service
public class CatalogueMetierImpl implements CatalogueMetier {
  @Autowired
  private CatalogueRepository catalogueRepository;

  /**
   * Enregistrer un catalogue.
   *
   * @param catalogue le catalogue
   * @return le catalogue
   */
  @Override
  public Catalogue saveCatalogue(Catalogue catalogue) {
    return catalogueRepository.saveAndFlush(catalogue);
  }

  /**
   * Récupérer le DescId Eudonet du catalogue à partir du label.
   *
   * @param label le label du catalogue
   * @return le descId du catalogue
   */
  @Override
  public String labelToDescId(String label) {
    Catalogue catalogue = catalogueRepository.findByLabel(label);

    return catalogue.getDBValue();
  }

  /**
   * Supprimer tous les catalogues.
   */
  @Override
  public void DeleteAll() {
    catalogueRepository.deleteAll();
  }

  /**
   * Retourner la liste des catalogues d'une définition.
   *
   * @param definition la définition
   * @return
   */
  @Override
  public List<Catalogue> getByDefinition(Definition definition) {
    return catalogueRepository.findByDefinition(definition);
  }
}
