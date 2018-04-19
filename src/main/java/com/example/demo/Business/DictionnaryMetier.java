package com.example.demo.Business;

import com.example.demo.Domain.Dictionnary;

import java.util.List;

/**
 * Created by othmane on 19/03/2018.
 * Mapping des tables Eudonet.
 */
public interface DictionnaryMetier {
  /**
   * Enregistrer le dictionnaire.
   *
   * @param dictionnary le dictionnaire
   * @return le dictionnaire
   */
  public Dictionnary saveDictionnary(Dictionnary dictionnary);

  /**
   * Obtenir le dictionnaire en fonction de son label.
   *
   * @param label le label
   * @return le dictionnaire
   */
  public Dictionnary labelToDictionnary(String label);

  /**
   * Retourne la liste de tous les dictionnaires.
   *
   * @return la liste de tous les dictionnaires
   */
  public List<Dictionnary> getAllTables();

  /**
   * Supprimer tous les dictionnaires.
   */
  public void DeleteAll();
}
