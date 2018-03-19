package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by othmane on 19/03/2018.
 */
@Service
public class dictionnaryMetierImpl implements dictionnaryMetier {
    @Autowired
    private dictionnaryRepository dictionnaryRepository;

    @Override
    public Dictionnary addInfo(Dictionnary info) {

        System.out.println(info);
        System.out.println("sALUUT");
        return dictionnaryRepository.saveAndFlush(info);
    }
}
