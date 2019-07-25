package com.dbview.app;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dbview.app.crudrepository.DatabaseInfoRepository;
import com.dbview.app.entity.DatabaseInfo;
import com.dbview.app.service.DatabaseInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBViewApplicationTests {

	@Autowired
	private DatabaseInfoRepository databaseInfoRepository;

	@Autowired
	DatabaseInfoService databaseInfoService;

	@Test
	public void retieveDbInfoTest() {
		DatabaseInfo databaseInfo = databaseInfoRepository.findByName("db2");
		Assert.assertNotNull(databaseInfo);
	}

	@Test
	public void listTablesTest() {
		DatabaseInfo databaseInfo = databaseInfoRepository.findByName("db2");
		Assert.assertNotNull(databaseInfo);
		try {
			System.out.println(databaseInfoService.listTables(databaseInfo));
		} catch (Exception ex) {
			Assert.fail();
		}
	}

	@Test
	public void listColumnsTest() {
		DatabaseInfo databaseInfo = databaseInfoRepository.findByName("db2");
		Assert.assertNotNull(databaseInfo);

		String tableName = "databaseinfo";
		try {
			databaseInfoService.listColumns(databaseInfo, tableName);
		} catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void previewTableTest() {
		DatabaseInfo databaseInfo = databaseInfoRepository.findByName("db2");
		Assert.assertNotNull(databaseInfo);

		String tableName = "databaseinfo";
		try {
			databaseInfoService.previewTable(databaseInfo, tableName, 3);
		} catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void getColumnStatsTest() {
		DatabaseInfo databaseInfo = databaseInfoRepository.findByName("db2");
		Assert.assertNotNull(databaseInfo);

		String tableName = "databaseinfo";
		String columnName = "id";
		String aggOperator = "min";
		try {
			databaseInfoService.getColumnStats(databaseInfo, tableName, columnName, aggOperator);
		} catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void getTableStatsTest() {
		DatabaseInfo databaseInfo = databaseInfoRepository.findByName("db2");
		Assert.assertNotNull(databaseInfo);

		String tableName = "databaseinfo";
		String statType = "statistics";
		try {
			databaseInfoService.getTableStats(databaseInfo, tableName, statType);
		} catch (Exception ex) {
			Assert.fail();
		}
	}

}
