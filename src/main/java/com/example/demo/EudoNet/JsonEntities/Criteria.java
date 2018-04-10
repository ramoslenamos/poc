
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Criteria {

  @SerializedName("Field")
  @Expose
  public String field;
  @SerializedName("Operator")
  @Expose
  public Integer operator;
  @SerializedName("Value")
  @Expose
  public String value;

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
   * @param value    Valuer de comparaison
   */
  public Criteria(String field, Integer operator, String value) {
    super();
    this.field = field;
    this.operator = operator;
    this.value = value;
  }

}
