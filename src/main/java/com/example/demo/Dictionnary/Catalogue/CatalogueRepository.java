package com.example.demo.Dictionnary.Catalogue;

import com.example.demo.Dictionnary.Colonnes.Definition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by othmane on 09/04/2018.
 */
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
  Catalogue findByLabel(String label);
}
