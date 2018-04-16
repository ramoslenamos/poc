
package com.example.demo.Rest.JsonEntitiesLabels;

import com.example.demo.EudoNet.JsonEntities.Criteria;
import com.example.demo.EudoNet.JsonEntities.CustomSearch;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Filtre {

  @SerializedName("Filtres")
  @Expose
  public List<Filtre> filtres = new ArrayList<>();
  @SerializedName("Critère")
  @Expose
  public Critere critere;
  @SerializedName("Opérateur Filtres")
  @Expose
  public Integer operateurFiltres;

  /**
   * No args constructor for use in serialization
   */
  public Filtre() {

  }

  /**
   * @param operateurFiltres Opérateur logique entre ce critère et le précédent
   * @param critere      Critère
   * @param filtres  Liste de sous-critères
   */
  public Filtre(List<Filtre> filtres, Critere critere, Integer operateurFiltres) {
    super();
    this.filtres = filtres;
    this.critere = critere;
    this.operateurFiltres = operateurFiltres;
  }

  public List<Filtre> getFiltres() {
    return filtres;
  }

  public void setFiltres(List<Filtre> filtres) {
    this.filtres = filtres;
  }

  public Critere getCritere() {
    return critere;
  }

  public void setCritere(Critere critere) {
    this.critere = critere;
  }

  public Integer getOperateurFiltres() {
    return operateurFiltres;
  }

  public void setOperateurFiltres(Integer operateurFiltres) {
    this.operateurFiltres = operateurFiltres;
  }

  @Override
  public String toString() {
    return "FILTRE : critere : " + this.critere.toString()+", filtres : " + filtres;
  }
}
