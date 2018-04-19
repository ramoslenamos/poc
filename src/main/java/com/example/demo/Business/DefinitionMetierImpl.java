package com.example.demo.Business;

import com.example.demo.Domain.Definition;
import com.example.demo.Domain.Dictionnary;
import com.example.demo.Repository.DefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by othmane on 23/03/2018.
 */
@Service
public class DefinitionMetierImpl implements DefinitionMetier {
  @Autowired
  private DefinitionRepository definitionRepository;

  /**
   * Enregistrer la définition.
   *
   * @param definition la définition
   * @return la définition
   */
  @Override
  public Definition saveDefinition(Definition definition) {

    return definitionRepository.saveAndFlush(definition);
  }

  /**
   * Obtenir la définition à partir de son label et du dictionnaire auquel elle appartient.
   *
   * @param label       le label
   * @param dictionnary le dictionnaire
   * @return le définition
   */
  @Override
  public Definition getByLabelIdTable(String label, Dictionnary dictionnary) {
    return definitionRepository.findByLabelIdTable(label, dictionnary.getId());
  }

  /**
   * Obtenir le descId Eudonet de la définition à partir de son label et du dictionnaire auquel elle appartient.
   *
   * @param label       le label
   * @param dictionnary le dictionnaire
   * @return la définition
   */
  @Override
  public String labelToDescId(String label, Dictionnary dictionnary) {
    Definition definition = definitionRepository.findByLabelIdTable(label, dictionnary.getId());
    return definition.getIdColumn();
  }

  /**
   * Obtenir la définition à partir de son descId Eudonet.
   *
   * @param columnId le descId de la définition
   * @return la définition
   */
  @Override
  public Definition getByColumnId(String columnId) {
    return definitionRepository.findByIdColumn(columnId);
  }

  /**
   * Supprimer toutes les définitions.
   */
  @Override
  public void DeleteAll() {
    definitionRepository.deleteAll();
  }

  /**
   * Obtenir la liste des définitions d'un dictionnaire.
   *
   * @param dictionnary le dictionnaire
   * @return la liste des définitions
   */
  @Override
  public List<Definition> getByDictionnary(Dictionnary dictionnary) {
    return definitionRepository.findByTableName(dictionnary);
  }
}
