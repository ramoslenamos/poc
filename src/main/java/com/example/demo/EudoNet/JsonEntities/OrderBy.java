
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
     * 
     */
    public OrderBy() {
    }

    /**
     * 
     * @param order
     * @param descId
     */
    public OrderBy(Integer descId, Integer order) {
        super();
        this.descId = descId;
        this.order = order;
    }

}
