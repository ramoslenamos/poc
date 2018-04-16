
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
   * @param field    Descid du champ du critère
   * @param operator Opérateur logique de comparaison entre le 'Field' et la 'Value'
   * @param valeur    Valuer de comparaison
   */
  public Criteria(String field, Integer operator, String valeur) {
    super();
    this.champ = field;
    this.operateur = operator;
    this.valeur = valeur;
  }

  public String getChamp() {
    return champ;
  }

  public void setChamp(String champ) {
    this.champ = champ;
  }
}
