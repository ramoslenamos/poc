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
public class DefinitionMetierImpl implements  DefinitionMetier{
    @Autowired
    private DefinitionRepository definitionRepository;

    @Override
    public Definition addInfo(Definition info) {

        return definitionRepository.saveAndFlush(info);
    }

    @Override
    public Definition getByLabelIdTable(String label, Dictionnary dictionnary) {
        return definitionRepository.findByLabelIdTable(label, dictionnary.getId());
    }

    @Override
    public String labelToDescId(String label, Dictionnary dictionnary) {
        Definition definition = definitionRepository.findByLabelIdTable(label, dictionnary.getId());
      return definition.getIdColoumn();
    }

    @Override
    public Definition getByColoumnId(String coloumnId) {
        return definitionRepository.findByIdColoumn(coloumnId);
    }

    @Override
    public void DeleteAll() {
        definitionRepository.deleteAll();
    }

    @Override
    public List<Definition> getByDictionnary(Dictionnary dictionnary) {
        return definitionRepository.findByTableName(dictionnary);
    }
}
