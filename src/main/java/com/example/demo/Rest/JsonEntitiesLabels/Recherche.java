
package com.example.demo.Rest.JsonEntitiesLabels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recherche {
  @SerializedName("Liste Champs")
  @Expose
  public List<String> listeChamps = null;
  @SerializedName("Filtre")
  @Expose
  public Filtre filtre;

  /**
   * No args constructor for use in serialization
   */
  public Recherche() {
  }

  /**
   * Constructeur d'une recherche avancée.
   *
   * @param filtre      Critères de recherches
   * @param listeChamps Liste des descId des champs souhaités
   */
  public Recherche(List<String> listeChamps, Filtre filtre) {
    super();
    this.listeChamps = listeChamps;
    this.filtre = filtre;
  }

  public List<String> getListeChamps() {
    return listeChamps;
  }

  public void setListeChamps(List<String> listeChamps) {
    this.listeChamps = listeChamps;
  }

  public Filtre getFiltre() {
    return filtre;
  }

  public void setFiltre(Filtre filtre) {
    this.filtre = filtre;
  }
}
