package com.example.demo.Business;

import com.example.demo.Domain.Catalogue;

/**
 * Created by othmane on 09/04/2018.
 */
public interface CatalogueMetier {
  public Catalogue addInfo(Catalogue info);

  public String labelToDescId(String label);

  public void DeleteAll();

}
