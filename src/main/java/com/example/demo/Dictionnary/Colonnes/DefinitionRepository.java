package com.example.demo.Dictionnary.Colonnes;

import com.example.demo.Dictionnary.Tables.Dictionnary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by othmane on 23/03/2018.
 */
public interface DefinitionRepository extends JpaRepository<Definition, Long> {
  @Query(value = "SELECT * from Definition d   where d.label=?1 and d.id_table=?2",nativeQuery = true)
  Definition findByLabelIdTable(String label, Long idTable);
}
