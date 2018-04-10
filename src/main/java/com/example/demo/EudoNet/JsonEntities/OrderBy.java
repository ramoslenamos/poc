
package com.example.demo.EudoNet.JsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderBy {

  @SerializedName("DescId")
  @Expose
  public Integer descId;
  @SerializedName("Order")
  @Expose
  public Integer order;

  /**
   * No args constructor for use in serialization
   */
  public OrderBy() {
  }

  /**
   * Constructeur OrderBy.
   *
   * @param order  Ordre du tri (0 pour ascendant, 1 pour descendant)
   * @param descId Descid du champ a trier
   */
  public OrderBy(Integer descId, Integer order) {
    super();
    this.descId = descId;
    this.order = order;
  }

}
