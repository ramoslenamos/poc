package com.example.demo.Business;

import com.example.demo.Domain.Definition;
import com.example.demo.Domain.Dictionnary;

import java.util.List;

/**
 * Created by othmane on 23/03/2018.
 */
public interface DefinitionMetier {
    public Definition addInfo(Definition info);
    public Definition getByLabelIdTable(String label, Dictionnary dictionnary);
    public String labelToDescId(String label, Dictionnary dictionnary);
    public Definition getByColoumnId(String coloumnId);
    public void DeleteAll();
    public List<Definition> getByDictionnary(Dictionnary dictionnary);
}
