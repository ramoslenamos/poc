package com.example.demo;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by othmane on 19/03/2018.
 */
@RestController
public class dictionnaryRestService {
    @Autowired
    private dictionnaryMetier dictionnaryMetier;

    @RequestMapping(value="/addDictionnary",method= RequestMethod.POST)
    public Dictionnary addInfo(@RequestBody Dictionnary info)
    {System.out.println(info+"chat$$$$$$$$$$$$$$$");

        return dictionnaryMetier.addInfo(info);}

}
