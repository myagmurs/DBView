package com.dbview.app.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.dbview.app.entity.DBColumn;
import com.dbview.app.entity.DatabaseInfo;

@Service
public class DatabaseInfoService {
	
	private final String ROWSTAT = "rows";
	private final String MSSQLDRIVERNAME = "com.mysql.jdbc.Driver";
	private final String MYSQLURLINITIAL = "jdbc:mysql://";
	
	private JdbcTemplate getDbDriverProperties(DatabaseInfo dbInfo) {
		String dbUrl = MYSQLURLINITIAL + dbInfo.printHostAndDB();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(MSSQLDRIVERNAME);
	    dataSource.setUrl(dbUrl);
	    dataSource.setUsername(dbInfo.getUsername());
	    dataSource.setPassword(dbInfo.getPassword());
	    JdbcTemplate jdbcConn = new JdbcTemplate(dataSource);
	    return jdbcConn;
	}
	
	public List<String> listTables(DatabaseInfo dbInfo) {
		String query = "show tables";
		JdbcTemplate jt = this.getDbDriverProperties(dbInfo);
		List<String> rs = jt.queryForList(query, String.class);
		return rs;
	}
	
	public List<DBColumn> listColumns(DatabaseInfo dbInfo, String tableName) {
		// the queries must be written better and safer; this is just a test
		String query = "show columns from " + tableName;
		JdbcTemplate jt = this.getDbDriverProperties(dbInfo);
		List<DBColumn> columns = jt.query(query, new RowMapper<DBColumn>() {
			public DBColumn mapRow(ResultSet rs, int rowNum) throws SQLException {
				DBColumn c = new DBColumn();
				c.setFieldname(rs.getString("Field"));
				c.setType(rs.getString("Type"));
				c.setNullable(rs.getString("Null"));
				c.setPrimaryKey(rs.getString("Key"));
				c.setDefaultValue(rs.getString("Default"));
				c.setExtraInfo(rs.getString("Extra"));
				return c;
			}
		});
		return columns;
	}
	
	public List<Map<String, Object>> previewTable(DatabaseInfo dbInfo, String tableName, int browseSize) {
		// the queries must be written better and safer; this is just a test
		String query = "select * from " + tableName + " limit " + browseSize;
		JdbcTemplate jt = this.getDbDriverProperties(dbInfo);
		List<Map<String, Object>> tablePreview = jt.queryForList(query);
		return tablePreview;
	}
	
	public List<Map<String, Object>> getColumnStats(DatabaseInfo dbInfo, String tableName, String columnName, String aggOperator) {
		// enumeration could be implemented for the aggOperator
		// the queries must be written better and safer; this is just a test
		String query = "select " + aggOperator + "(" + columnName + ") from " + tableName;
		JdbcTemplate jt = this.getDbDriverProperties(dbInfo);
		List<Map<String, Object>> columnStats = jt.queryForList(query);
		return columnStats;
	}
	
	public List<Map<String, Object>> getTableStats(DatabaseInfo dbInfo, String tableName, String statType) {
		// enumeration could be implemented for the statType
		// the queries must be written better and safer; this is just a test
		String query = "select count(*) from ";
		if(this.ROWSTAT.equals(statType)) {
			query += tableName;
		} else {
			query += "information_schema." + statType + " WHERE table_name = '" + tableName + "'";
		}
		JdbcTemplate jt = this.getDbDriverProperties(dbInfo);
		List<Map<String, Object>> tableStats = jt.queryForList(query);
		return tableStats;
	}
	
	
	

}
