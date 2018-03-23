package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.Dictionnary.Tables.Dictionnary;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by othmane on 23/03/2018.
 */
@Entity
public class Definition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50)
    private String idColoumn;
    @ManyToOne
    @JoinColumn(name = "idTable")
    private Dictionnary tableName;
    @Column(length = 50)
    private String label;

    public Definition(String idColoumn, Dictionnary tableName, String label) {
        this.idColoumn = idColoumn;
        this.tableName = tableName;
        this.label = label;
    }

    public Definition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdColoumn() {
        return idColoumn;
    }

    public void setIdColoumn(String idColoumn) {
        this.idColoumn = idColoumn;
    }

    public Dictionnary getTableName() {
        return tableName;
    }

    public void setTableName(Dictionnary tableName) {
        this.tableName = tableName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}


