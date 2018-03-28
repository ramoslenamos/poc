
package com.example.demo.EudoNet.JsonEntities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    public List<Integer> listCols = null;
    @SerializedName("WhereCustom")
    @Expose
    public WhereCustom whereCustom;
    @SerializedName("OrderBy")
    @Expose
    public List<OrderBy> orderBy = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CustomSearch() {
    }

    /**
     * 
     * @param rowsPerPage
     * @param numPage
     * @param orderBy
     * @param whereCustom
     * @param listCols
     * @param showMetadata
     */
    public CustomSearch(Boolean showMetadata, Integer rowsPerPage, Integer numPage, List<Integer> listCols, WhereCustom whereCustom, List<OrderBy> orderBy) {
        super();
        this.showMetadata = showMetadata;
        this.rowsPerPage = rowsPerPage;
        this.numPage = numPage;
        this.listCols = listCols;
        this.whereCustom = whereCustom;
        this.orderBy = orderBy;
    }

}
