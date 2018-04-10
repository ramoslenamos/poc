
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WhereCustom {

  @SerializedName("WhereCustoms")
  @Expose
  public List<WhereCustom> whereCustoms = null;
  @SerializedName("Criteria")
  @Expose
  public Criteria criteria;
  @SerializedName("InterOperator")
  @Expose
  public Integer interOperator;

  /**
   * No args constructor for use in serialization
   */
  public WhereCustom() {
  }

  /**
   * @param interOperator Opérateur logique entre ce critère et le précédent
   * @param criteria      Critère
   * @param whereCustoms  Liste de sous-critères
   */
  public WhereCustom(List<WhereCustom> whereCustoms, Criteria criteria, Integer interOperator) {
    super();
    this.whereCustoms = whereCustoms;
    this.criteria = criteria;
    this.interOperator = interOperator;
  }

}
