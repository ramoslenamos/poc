package com.example.demo.Dictionnary.Colonnes;

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
}
