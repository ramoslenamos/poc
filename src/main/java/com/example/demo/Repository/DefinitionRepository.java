package com.example.demo.Repository;

import com.example.demo.Domain.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by othmane on 23/03/2018.
 */
public interface DefinitionRepository extends JpaRepository<Definition, Long> {
  @Query(value = "SELECT * from Definition d   where d.label=?1 and d.id_table=?2",nativeQuery = true)
  Definition findByLabelIdTable(String label, Long idTable);
  Definition findByIdColoumn(String idColoumn);
}
