package com.example.demo.Rest.Dictionnary;

import com.example.demo.Business.DictionnaryMetier;
import com.example.demo.Domain.Dictionnary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by othmane on 19/03/2018.
 */
@RestController
public class DictionnaryRestService {
    @Autowired
    private DictionnaryMetier dictionnaryMetier;

    @RequestMapping(value="/addDictionnary",method= RequestMethod.POST)
    public Dictionnary addInfo(@RequestBody Dictionnary info)
    {System.out.println(info+"chat$$$$$$$$$$$$$$$");

        return dictionnaryMetier.addInfo(info);}

}
