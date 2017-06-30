package com.coltd.platform.generator.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coltd.platform.generator.utils.TimeUtil;

/**
 * 
 * @ClassName: AutoGenInf
 * @Description: 自动生成信息
 * @author panhaicheng@gomeholdings.com
 * @date 2016年7月12日 下午2:02:46
 *
 */
public class AutoGenInf {
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
	 * 生成路径
	 */
	private String authGenPath;
	/**
	 * 实体名称
	 */
	private String entityId;

	/**
	 * 数据库类型
	 */
	private String dbType;
	/**
	 * 包名List
	 */
	private List<String> packages = new ArrayList<String>();

	public AutoGenInf() {

	}

	public AutoGenInf(String authGenPath, String entityId, String author,
			String dbType) {
		super();
		this.author = author;
		this.authGenPath = authGenPath;
		this.entityId = entityId;
		this.dbType = dbType;
		this.date = TimeUtil.foramtDate2String(new Date(),
				"yyyy年MM月dd日 HH时mm分ss秒");
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthGenPath() {
		return authGenPath;
	}

	public void setAuthGenPath(String authGenPath) {
		this.authGenPath = authGenPath;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AutoGenInf [author=");
		builder.append(author);
		builder.append(", date=");
		builder.append(date);
		builder.append(", authGenPath=");
		builder.append(authGenPath);
		builder.append(", entityId=");
		builder.append(entityId);
		builder.append("]");
		return builder.toString();
	}

	public List<String> getPackages() {
		return packages;
	}

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

}
