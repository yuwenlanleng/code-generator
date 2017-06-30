/**   
 * @Title: TableInf.java 
 * @Package com.ulpay.generator.bean 
 * @Description: 
 * @author panhaicheng@gomeholdings.com
 * @date 2016年10月21日 下午8:19:13 
 * @version V1.0   
 */
package com.gome.generator.bean;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TableInf
 * @Description:
 * @author panhaicheng@gomeholdings.com
 * @date 2016年10月21日 下午8:19:13
 * 
 */
public class TableInf {
	public static final String TABLEINFENTITY = "tableInfEntity";
	private String id;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 属主
	 */
	private String schema;
	/**
	 * 表描述信息
	 */
	private String description;

	/**
	 * 表列的数量
	 */
	private int columnNum;

	/**
	 * 列信息
	 */
	private Map<String, Column> columnMap = new LinkedHashMap<String, Column>();

	/**
	 * 主键
	 */
	private Map<String, String> primaryKeyMap = new LinkedHashMap<String, String>();

	/**
	 * 唯一索引
	 */
	private Map<String, String> uniqueKeyMap = new LinkedHashMap<String, String>();
	/**
	 * 表操作 select,insert,update,operator
	 */
	private List<String> operators = new LinkedList<String>();

	/**
	 *
	 * @return 获取{@link #id}
	 */
	public String getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 *            设置{@link #id}
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return 获取{@link #tableName}
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 *
	 * @param tableName
	 *            设置{@link #tableName}
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 *
	 * @return 获取{@link #schema}
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 *
	 * @param schema
	 *            设置{@link #schema}
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 *
	 * @return 获取{@link #description}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 *            设置{@link #description}
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 * @return 获取{@link #columnNum}
	 */
	public int getColumnNum() {
		return columnNum;
	}

	/**
	 *
	 * @param columnNum
	 *            设置{@link #columnNum}
	 */
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	/**
	 *
	 * @return 获取{@link #columnMap}
	 */
	public Map<String, Column> getColumnMap() {
		return columnMap;
	}

	/**
	 *
	 * @param columnMap
	 *            设置{@link #columnMap}
	 */
	public void setColumnMap(Map<String, Column> columnMap) {
		this.columnMap = columnMap;
	}

	/**
	 * 
	 * @return 获取{@link #primaryKeyMap}
	 */
	public Map<String, String> getPrimaryKeyMap() {
		return primaryKeyMap;
	}

	/**
	 *
	 * @param primaryKeyMap
	 *            设置{@link #primaryKeyMap}
	 */
	public void setPrimaryKeyMap(Map<String, String> primaryKeyMap) {
		this.primaryKeyMap = primaryKeyMap;
	}

	/**
	 * 
	 * @return 获取{@link #uniqueKeyMap}
	 */
	public Map<String, String> getUniqueKeyMap() {
		return uniqueKeyMap;
	}

	/**
	 *
	 * @param uniqueKeyMap
	 *            设置{@link #uniqueKeyMap}
	 */
	public void setUniqueKeyMap(Map<String, String> uniqueKeyMap) {
		this.uniqueKeyMap = uniqueKeyMap;
	}

	/**
	 * 
	 * @return 获取{@link #operators}
	 */
	public List<String> getOperators() {
		return operators;
	}

	/**
	 *
	 * @param operators
	 *            设置{@link #operators}
	 */
	public void setOperators(List<String> operators) {
		this.operators = operators;
	}

	@Override
	public String toString() {
		return "TableInf [id=" + id + ", tableName=" + tableName + ", schema="
				+ schema + ", description=" + description + ", columnNum="
				+ columnNum + ", columnMap=" + columnMap + ", primaryKeyMap="
				+ primaryKeyMap + ", uniqueKeyMap=" + uniqueKeyMap
				+ ", operators=" + operators + "]";
	}
}
