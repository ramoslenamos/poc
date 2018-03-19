package com.example.demo;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;

/**
 * Created by othmane on 18/03/2018.
 */
@Entity
public class Dictionnary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50)
    private String idTable;
    @Column(length = 50)
    private String tableName;

    public Dictionnary(String idTable, String tableName) {
        this.idTable = idTable;
        this.tableName = tableName;
    }

    public Dictionnary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTable() {
        return idTable;
    }
@JsonSetter
    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public String getTableName() {
        return tableName;
    }
@JsonSetter
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
