package com.dbview.app.crudrepository;

import org.springframework.data.repository.CrudRepository;

import com.dbview.app.entity.DatabaseInfo;

public interface DatabaseInfoRepository extends CrudRepository<DatabaseInfo, Integer> {

	DatabaseInfo findByName(String name);

}
