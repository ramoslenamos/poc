package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.Dictionnary.Tables.Dictionnary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by othmane on 23/03/2018.
 */
public interface DefinitionRepository extends JpaRepository<Definition, Long> {
  Definition findByLabelAndTableName(String label, Dictionnary dictionnary);
}
