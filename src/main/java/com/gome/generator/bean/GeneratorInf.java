/**   
 * @Title: GeneratorInf.java 
 * @Package com.ulpay.generator.bean 
 * @Description: 
 * @author panhaicheng@gomeholdings.com
 * @date 2016年10月21日 下午8:14:03 
 * @version V1.0   
 */
package com.gome.generator.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GeneratorInf
 * @Description: 代码生成基本信息
 * @author panhaicheng@gomeholdings.com
 * @date 2016年10月21日 下午8:14:03
 * 
 */
public class GeneratorInf {
	public static final String AUTOGENINF = "autoGenInf";
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 日期
	 */
	private String date;
	/**
	 * 实体名称
	 */
	private String entityName;

	/**
	 * 实体名称第一个小写
	 */
	private String entityNameFirstLower;
	/**
	 * 数据库类型
	 */
	private String dbType;

	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * 包名List
	 */
	private List<String> packages = new ArrayList<String>();

	/**
	 * 
	 * @return 获取{@link #entityNameFirstLower}
	 */
	public String getEntityNameFirstLower() {
		return entityNameFirstLower;
	}

	/**
	 *
	 * @param entityNameFirstLower
	 *            设置{@link #entityNameFirstLower}
	 */
	public void setEntityNameFirstLower(String entityNameFirstLower) {
		this.entityNameFirstLower = entityNameFirstLower;
	}

	/**
	 * 
	 * @return 获取{@link #author}
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 *
	 * @param author
	 *            设置{@link #author}
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 
	 * @return 获取{@link #date}
	 */
	public String getDate() {
		return date;
	}

	/**
	 *
	 * @param date
	 *            设置{@link #date}
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return 获取{@link #entityName}
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 *
	 * @param entityName
	 *            设置{@link #entityName}
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * 
	 * @return 获取{@link #dbType}
	 */
	public String getDbType() {
		return dbType;
	}

	/**
	 *
	 * @param dbType
	 *            设置{@link #dbType}
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * 
	 * @return 获取{@link #packageName}
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 *
	 * @param packageName
	 *            设置{@link #packageName}
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * 
	 * @return 获取{@link #packages}
	 */
	public List<String> getPackages() {
		return packages;
	}

	/**
	 *
	 * @param packages
	 *            设置{@link #packages}
	 */
	public void setPackages(List<String> packages) {
		this.packages = packages;
	}

	/**
	 * 导入包
	 * 
	 * @param className
	 *            包名
	 * */
	public void implortPage(String className) {
		if (className.indexOf("java.lang") == -1
				&& className.indexOf(".") != -1
				&& packages.indexOf(className) == -1) {
			packages.add(className);
		}
	}

	@Override
	public String toString() {
		return "GeneratorInf [author=" + author + ", date=" + date
				+ ", entityName=" + entityName + ", entityNameFirstLower="
				+ entityNameFirstLower + ", dbType=" + dbType
				+ ", packageName=" + packageName + ", packages=" + packages
				+ "]";
	}
}
