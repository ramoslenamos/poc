package com.example.demo.Business;

import com.example.demo.Domain.Dictionnary;
import com.example.demo.Repository.DictionnaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by othmane on 19/03/2018.
 */
@Service
public class DictionnaryMetierImpl implements DictionnaryMetier {
  @Autowired
  private DictionnaryRepository dictionnaryRepository;

  /**
   * Enregistrer le dictionnaire.
   *
   * @param dictionnary le dictionnaire
   * @return le dictionnaire
   */
  @Override
  public Dictionnary saveDictionnary(Dictionnary dictionnary) {
    return dictionnaryRepository.saveAndFlush(dictionnary);
  }

  /**
   * Obtenir le dictionnaire en fonction de son label.
   *
   * @param label le label
   * @return le dictionnaire
   */
  @Override
  public Dictionnary labelToDictionnary(String label) {
    return dictionnaryRepository.findByTableName(label);
  }

  /**
   * Retourne la liste de tous les dictionnaires.
   *
   * @return la liste de tous les dictionnaires
   */
  @Override
  public List<Dictionnary> getAllTables() {
    return dictionnaryRepository.findAll();
  }

  /**
   * Supprimer tous les dictionnaires.
   */
  @Override
  public void DeleteAll() {
    dictionnaryRepository.deleteAll();
  }
}
