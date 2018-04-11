package com.example.demo.Business;

import com.example.demo.Domain.Dictionnary;

import java.util.List;

/**
 * Created by othmane on 19/03/2018.
 */
public interface DictionnaryMetier {
    public Dictionnary addInfo(Dictionnary info);
    public Dictionnary labelToDictionnary(String label);
    public List<Dictionnary> getAllTables();
    public void DeleteAll();
}
