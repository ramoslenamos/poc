
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WhereCustom {

  @SerializedName("WhereCustoms")
  @Expose
  public List<WhereCustom> filtres = null;
  @SerializedName("Criteria")
  @Expose
  public Criteria critere;
  @SerializedName("InterOperator")
  @Expose
  public Integer operateurInterFiltres;

  /**
   * No args constructor for use in serialization
   */
  public WhereCustom() {
  }

  /**
   * @param operateurInterFiltres Opérateur logique entre ce critère et le précédent
   * @param critere               Critère
   * @param filtres               Liste de sous-critères
   */
  public WhereCustom(List<WhereCustom> filtres, Criteria critere, Integer operateurInterFiltres) {
    super();
    this.filtres = filtres;
    this.critere = critere;
    this.operateurInterFiltres = operateurInterFiltres;
  }

  public List<WhereCustom> getFiltres() {
    return filtres;
  }

  public void setFiltres(List<WhereCustom> filtres) {
    this.filtres = filtres;
  }

  public Criteria getCritere() {
    return critere;
  }

  public void setCritere(Criteria critere) {
    this.critere = critere;
  }
}
