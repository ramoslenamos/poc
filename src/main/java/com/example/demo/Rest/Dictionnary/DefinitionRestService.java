package com.example.demo.Rest.Dictionnary;

import com.example.demo.Business.DefinitionMetier;
import com.example.demo.Domain.Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by othmane on 23/03/2018.
 */
public class DefinitionRestService {
    @Autowired
    private DefinitionMetier definitionMetier;

    @RequestMapping(value="/addDefinition",method= RequestMethod.POST)
    public Definition addInfo(@RequestBody Definition info)
    {System.out.println(info+"chat$$$$$$$$$$$$$$$");

        return definitionMetier.addInfo(info);}
}
