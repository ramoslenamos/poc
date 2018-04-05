package com.example.demo.Dictionnary.Tables;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by othmane on 19/03/2018.
 */
public interface DictionnaryRepository extends JpaRepository<Dictionnary, Long> {
  Dictionnary findByTableName(String tableName);

}
