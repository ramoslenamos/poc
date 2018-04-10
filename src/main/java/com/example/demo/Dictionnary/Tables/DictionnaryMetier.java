package com.example.demo.Dictionnary.Tables;

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
