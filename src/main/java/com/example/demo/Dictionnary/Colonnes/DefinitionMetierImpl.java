package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.Dictionnary.Tables.dictionnaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by othmane on 23/03/2018.
 */
public class DefinitionMetierImpl implements  DefinitionMetier{
    @Autowired
    private DefinitionRepository definitionRepository;

    @Override
    public Definition addInfo(Definition info) {
      ;
        return definitionRepository.saveAndFlush(info);
    }
}
