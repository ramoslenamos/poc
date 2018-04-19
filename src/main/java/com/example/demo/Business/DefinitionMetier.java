package com.example.demo.Business;

import com.example.demo.Domain.Definition;
import com.example.demo.Domain.Dictionnary;

import java.util.List;

/**
 * Created by othmane on 23/03/2018.
 * Mapping des champs Eudonet.
 */
public interface DefinitionMetier {
  /**
   * Enregistrer la définition.
   *
   * @param definition la définition
   * @return la définition
   */
  public Definition saveDefinition(Definition definition);

  /**
   * Obtenir la définition à partir de son label et du dictionnaire auquel elle appartient.
   *
   * @param label       le label
   * @param dictionnary le dictionnaire
   * @return le définition
   */
  public Definition getByLabelIdTable(String label, Dictionnary dictionnary);

  /**
   * Obtenir le descId Eudonet de la définition à partir de son label et du dictionnaire auquel elle appartient.
   *
   * @param label       le label
   * @param dictionnary le dictionnaire
   * @return la définition
   */
  public String labelToDescId(String label, Dictionnary dictionnary);

  /**
   * Obtenir la définition à partir de son descId Eudonet.
   *
   * @param columnId le descId de la définition
   * @return la définition
   */
  public Definition getByColumnId(String columnId);

  /**
   * Supprimer toutes les définitions.
   */
  public void DeleteAll();

  /**
   * Obtenir la liste des définitions d'un dictionnaire.
   *
   * @param dictionnary le dictionnaire
   * @return la liste des définitions
   */
  public List<Definition> getByDictionnary(Dictionnary dictionnary);
}
