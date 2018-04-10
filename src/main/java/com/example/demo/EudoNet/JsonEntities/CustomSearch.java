
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomSearch {

  @SerializedName("ShowMetadata")
  @Expose
  public Boolean showMetadata;
  @SerializedName("RowsPerPage")
  @Expose
  public Integer rowsPerPage;
  @SerializedName("NumPage")
  @Expose
  public Integer numPage;
  @SerializedName("ListCols")
  @Expose
  public List<String> listCols = null;
  @SerializedName("WhereCustom")
  @Expose
  public WhereCustom whereCustom;
  @SerializedName("OrderBy")
  @Expose
  public List<OrderBy> orderBy = null;

  /**
   * No args constructor for use in serialization
   */
  public CustomSearch() {
  }

  /**
   * Constructeur d'une recherche avancée.
   *
   * @param rowsPerPage  Permet de définir le nombre de lignes par page
   * @param numPage      Numéro de page à afficher
   * @param orderBy      Ordre de tri la liste retourné par la recherche
   * @param whereCustom  Critères de recherches
   * @param listCols     Liste des descId des champs souhaités
   * @param showMetadata Fournir 'true' pour obtenir les méta-datas des champs et tables demandés
   */
  public CustomSearch(Boolean showMetadata, Integer rowsPerPage, Integer numPage, List<String> listCols, WhereCustom whereCustom, List<OrderBy> orderBy) {
    super();
    this.showMetadata = showMetadata;
    this.rowsPerPage = rowsPerPage;
    this.numPage = numPage;
    this.listCols = listCols;
    this.whereCustom = whereCustom;
    this.orderBy = orderBy;
  }

}
