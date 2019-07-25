package com.dbview.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbview.app.crudrepository.DatabaseInfoRepository;
import com.dbview.app.entity.DBColumn;
import com.dbview.app.entity.DatabaseInfo;
import com.dbview.app.service.DatabaseInfoService;

@RestController
@RequestMapping(path = "/dbinfo")
public class DatabaseInfoController {

	@Autowired
	private DatabaseInfoRepository databaseInfoRepository;

	@Autowired
	private DatabaseInfoService databaseInfoService;

	@PostMapping(path = "/addDbInfo")
	public DatabaseInfo addDbInfo(@RequestBody DatabaseInfo dbInfo) {
		return databaseInfoRepository.save(dbInfo);
	}

	@GetMapping(path = "/getAllDbinfo")
	public @ResponseBody Iterable<DatabaseInfo> getAllDatabases() {
		return databaseInfoRepository.findAll();
	}

	@GetMapping("/listTables")
	public @ResponseBody Iterable<String> listTables(@RequestParam("name") String name) {
		DatabaseInfo dbInfo = databaseInfoRepository.findByName(name);
		List<String> tables = databaseInfoService.listTables(dbInfo);
		return tables;
	}

	@GetMapping("/listColumns")
	public @ResponseBody Iterable<DBColumn> listColumns(@RequestParam("name") String name,
			@RequestParam("tableName") String tableName) {
		DatabaseInfo dbInfo = databaseInfoRepository.findByName(name);
		List<DBColumn> columns = databaseInfoService.listColumns(dbInfo, tableName);
		return columns;
	}

	@GetMapping("/previewTable")
	public @ResponseBody List<Map<String, Object>> previewTable(@RequestParam("name") String name,
			@RequestParam("tableName") String tableName, @RequestParam("browseSize") int browseSize) {
		DatabaseInfo dbInfo = databaseInfoRepository.findByName(name);
		List<Map<String, Object>> tablePreview = databaseInfoService.previewTable(dbInfo, tableName, browseSize);
		return tablePreview;
	}

	@GetMapping("/getColumnStats")
	public @ResponseBody List<Map<String, Object>> getColumnStats(@RequestParam("name") String name,
			@RequestParam("tableName") String tableName, @RequestParam("columnName") String columnName,
			@RequestParam("aggOperator") String aggOperator) {
		DatabaseInfo dbInfo = databaseInfoRepository.findByName(name);
		List<Map<String, Object>> columnStats = databaseInfoService.getColumnStats(dbInfo, tableName, columnName,
				aggOperator);
		return columnStats;
	}

	@GetMapping("/getTableStats")
	public @ResponseBody List<Map<String, Object>> getTableStats(@RequestParam("name") String name,
			@RequestParam("tableName") String tableName, @RequestParam("statType") String statType) {
		DatabaseInfo dbInfo = databaseInfoRepository.findByName(name);
		List<Map<String, Object>> tableStats = databaseInfoService.getTableStats(dbInfo, tableName, statType);
		return tableStats;
	}

}
