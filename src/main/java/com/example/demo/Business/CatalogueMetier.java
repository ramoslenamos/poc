package com.example.demo.Business;

import com.example.demo.Domain.Catalogue;
import com.example.demo.Domain.Definition;

import java.util.List;

/**
 * Created by othmane on 09/04/2018.
 */
public interface CatalogueMetier {
  /**
   * Enregistrer un catalogue.
   *
   * @param catalogue le catalogue
   * @return le catalogue
   */
  public Catalogue saveCatalogue(Catalogue catalogue);

  /**
   * Récupérer le DescId Eudonet du catalogue à partir du label.
   *
   * @param label le label du catalogue
   * @return le descId du catalogue
   */
  public String labelToDescId(String label);

  /**
   * Supprimer tous les catalogues.
   */
  public void DeleteAll();

  /**
   * Retourner la liste des catalogues d'une définition.
   *
   * @param definition la définition
   * @return
   */
  public List<Catalogue> getByDefinition(Definition definition);

}
