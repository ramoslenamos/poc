
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomSearch {
  @SerializedName("ListCols")
  @Expose
  public List<String> listeProprietes = null;
  @SerializedName("WhereCustom")
  @Expose
  public WhereCustom filtre;

  /**
   * No args constructor for use in serialization
   */
  public CustomSearch() {
  }

  /**
   * Constructeur d'une recherche avancée.
   *
   * @param filtre  Critères de recherches
   * @param listeProprietes     Liste des descId des champs souhaités
   */
  public CustomSearch(List<String> listeProprietes, WhereCustom filtre) {
    super();
    this.listeProprietes = listeProprietes;
    this.filtre = filtre;
  }

  public List<String> getListeProprietes() {
    return listeProprietes;
  }

  public void setListeProprietes(List<String> listeProprietes) {
    this.listeProprietes = listeProprietes;
  }

  public WhereCustom getFiltre() {
    return filtre;
  }

  public void setFiltre(WhereCustom filtre) {
    this.filtre = filtre;
  }
}
