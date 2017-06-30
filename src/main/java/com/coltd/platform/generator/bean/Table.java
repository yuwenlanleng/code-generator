package com.coltd.platform.generator.bean;

import java.util.Map;

public class Table {
	public static final String TABLEINFENTITY="tableInfEntity";
	private String id;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 类型
	 */
	private String tableType="table";
	/**
	 * 属主
	 */
	private String schema;
	/**
	 * 表描述信息
	 */
	private String tableDescription;
	
	/**
	 * 表列的数量
	 */
	private int columnNum;
	
	/**
	 * 列信息
	 */
	private Map<String,Column> columnMap;
	
	/**
	 * 主键
	 */
	private Map<String,String> primaryKeyMap;
	
	/**
	 * 唯一索引
	 */
	private Map<String,String> uniqueKeyMap;
	public Table(){
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Table(String tableName, String tableDescription) {
		super();
		this.tableName = tableName;
		this.tableDescription = tableDescription;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTableDescription() {
		return tableDescription;
	}

	public void setTableDescription(String tableDescription) {
		this.tableDescription = tableDescription;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public Map<String, Column> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, Column> columnMap) {
		this.columnMap = columnMap;
	}

	public Map<String, String> getPrimaryKeyMap() {
		return primaryKeyMap;
	}

	public void setPrimaryKeyMap(Map<String, String> primaryKeyMap) {
		this.primaryKeyMap = primaryKeyMap;
	}

	public Map<String, String> getUniqueKeyMap() {
		return uniqueKeyMap;
	}

	public void setUniqueKeyMap(Map<String, String> uniqueKeyMap) {
		this.uniqueKeyMap = uniqueKeyMap;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableInfEntity [tableName=");
		builder.append(tableName);
		builder.append(", tableType=");
		builder.append(tableType);
		builder.append(", schema=");
		builder.append(schema);
		builder.append(", tableDescription=");
		builder.append(tableDescription);
		builder.append(", columnNum=");
		builder.append(columnNum);
		builder.append(", columnMap=");
		builder.append(columnMap);
		builder.append(", primaryKeyMap=");
		builder.append(primaryKeyMap);
		builder.append(", uniqueKeyMap=");
		builder.append(uniqueKeyMap);
		builder.append("]");
		return builder.toString();
	}
	
}
