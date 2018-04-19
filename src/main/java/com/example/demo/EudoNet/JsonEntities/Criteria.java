
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Criteria {

  @SerializedName("Field")
  @Expose
  public String champ;
  @SerializedName("Operator")
  @Expose
  public Integer operateur;
  @SerializedName("Value")
  @Expose
  public String valeur;

  /**
   * No args constructor for use in serialization
   */
  public Criteria() {
  }

  /**
   * Constructeur Criteria.
   *
   * @param champ    champ du critère
   * @param operateur opérateur logique de comparaison entre le champ et la valeur
   * @param valeur   valeur de comparaison
   */
  public Criteria(String champ, Integer operateur, String valeur) {
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
}
