package com.example.demo.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

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

    @OneToMany(mappedBy = "definition", fetch = FetchType.LAZY)
    private Collection<Catalogue> catalogues;

    public Definition() {

    }

    public Definition(String idColoumn, Dictionnary tableName, String label) {
        this.idColoumn = idColoumn;
        this.tableName = tableName;
        this.label = label;
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

    public Collection<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(Collection<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }
}


