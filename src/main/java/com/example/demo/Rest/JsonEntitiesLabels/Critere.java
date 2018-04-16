
package com.example.demo.Rest.JsonEntitiesLabels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Critere {

  @SerializedName("Champ")
  @Expose
  public String champ;
  @SerializedName("Opérateur")
  @Expose
  public Integer operateur;
  @SerializedName("Valeur")
  @Expose
  public String valeur;

  /**
   * No args constructor for use in serialization
   */
  public Critere() {
  }

  /**
   * Constructeur Critere.
   *
   * @param champ    Descid du champ du critère
   * @param operateur Opérateur logique de comparaison entre le 'Field' et la 'Value'
   * @param valeur    Valuer de comparaison
   */
  public Critere(String champ, Integer operateur, String valeur) {
    super();
    this.champ = champ;
    this.operateur = operateur;
    this.valeur = valeur;
  }

  public String getChamp() {
    return champ;
  }

  public void setChamp(String champ) {
    this.champ = champ;
  }

  public Integer getOperateur() {
    return operateur;
  }

  public void setOperateur(Integer operateur) {
    this.operateur = operateur;
  }

  public String getValeur() {
    return valeur;
  }

  public void setValeur(String valeur) {
    this.valeur = valeur;
  }

  @Override
  public String toString() {
    return "champ : " + this.champ +", operateur : " + this.operateur  + ", valeur : " + this.valeur;
  }
}
