package com.example.demo.Domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by othmane on 23/03/2018.
 */
@Entity
public class Catalogue implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(length = 50)
  private String DBValue;

  @Column(length = 50)
  private String label;

  public Catalogue(String DBValue, String label) {
    this.DBValue = DBValue;
    this.label = label;
  }

  public Catalogue() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDBValue() {
    return DBValue;
  }

  public void setDBValue(String DBValue) {
    this.DBValue = DBValue;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}


