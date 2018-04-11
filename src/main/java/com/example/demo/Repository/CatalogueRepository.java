package com.example.demo.Repository;

import com.example.demo.Domain.Catalogue;
import com.example.demo.Domain.Definition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by othmane on 09/04/2018.
 */
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
  Catalogue findByLabel(String label);
  List<Catalogue> findByDefinition(Definition definition);
}
