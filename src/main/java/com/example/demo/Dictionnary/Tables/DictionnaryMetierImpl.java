package com.example.demo.Dictionnary.Tables;

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

    @Override
    public Dictionnary addInfo(Dictionnary info) {

        System.out.println(info);
        System.out.println("sALUUT");
        return dictionnaryRepository.saveAndFlush(info);
    }

    @Override
    public String labelToDescId(String label) {
        Dictionnary dictionnary = dictionnaryRepository.findByTableName(label);
        return dictionnary.getIdTable();
    }

    @Override
    public List<Dictionnary> getAllTables() {
        return dictionnaryRepository.findAll();
    }
}
