package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.Dictionnary.Tables.Dictionnary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String labelToDescId(String label, Dictionnary dictionnary) {
        System.out.println("REQ : " + label +", DICO_ID :"+ dictionnary.getId());
        Definition definition = definitionRepository.findByLabelIdTable(label, dictionnary.getId());
      return definition.getIdColoumn();
    }

    @Override
    public void DeleteAll() {
        definitionRepository.deleteAll();
    }
}
