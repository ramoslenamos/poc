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

    @Override
    public Dictionnary addInfo(Dictionnary info) {
        System.out.println(info);
        return dictionnaryRepository.saveAndFlush(info);
    }

    @Override
    public Dictionnary labelToDictionnary(String label) {
        return dictionnaryRepository.findByTableName(label);
    }

    @Override
    public List<Dictionnary> getAllTables() {
        return dictionnaryRepository.findAll();
    }

    @Override
    public void DeleteAll() {
        dictionnaryRepository.deleteAll();
    }
}
