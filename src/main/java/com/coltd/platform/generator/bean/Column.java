package com.coltd.platform.generator.bean;

public class Column {
	/**
	 * 字段名
	 */
	private String columnName;
	/**
	 * 字段类型
	 */
	private String dataType;
	/**
	 * 字段描述
	 */
	private String description;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * get set方法名
	 */
	private String jfunctionName;
	/**
	 * 对于的java字段名称
	 */
	private String jcolumnName;
	
	/**
	 * java类型
	 */
	private String jdataType;
	/**
	 * 长度
	 */
	private int filedLength;
	/**
	 * 精度
	 */
	private int precision;
	/**
	 * 默认值
	 */
	private String deVlaue;
	/**
	 * 是否有长度
	 */
	private boolean hasLength;
	/**
	 * 是否有精度
	 */
	private boolean hasPrecision;
	/**
	 * 是否是主键
	 */
	private boolean isPrimaryKey;
	/**
	 * 是否可以为空
	 */
	private boolean isCanNull;
	/**
	 * 是否是标识
	 */
	private boolean IsIdentity;
	
	public String getJdataType() {
		return jdataType;
	}
	public void setJdataType(String jdataType) {
		this.jdataType = jdataType;
	}
	public int getFiledLength() {
		return filedLength;
	}
	public void setFiledLength(int filedLength) {
		this.filedLength = filedLength;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public String getDeVlaue() {
		return deVlaue;
	}
	public void setDeVlaue(String deVlaue) {
		this.deVlaue = deVlaue;
	}
	public boolean isHasLength() {
		return hasLength;
	}
	public void setHasLength(boolean hasLength) {
		this.hasLength = hasLength;
	}
	public boolean isHasPrecision() {
		return hasPrecision;
	}
	public void setHasPrecision(boolean hasPrecision) {
		this.hasPrecision = hasPrecision;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public boolean isCanNull() {
		return isCanNull;
	}
	public void setCanNull(boolean isCanNull) {
		this.isCanNull = isCanNull;
	}
	public boolean isIsIdentity() {
		return IsIdentity;
	}
	public void setIsIdentity(boolean isIdentity) {
		IsIdentity = isIdentity;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJfunctionName() {
		return jfunctionName;
	}
	public void setJfunctionName(String jfunctionName) {
		this.jfunctionName = jfunctionName;
	}
	public String getJcolumnName() {
		return jcolumnName;
	}
	public void setJcolumnName(String jcolumnName) {
		this.jcolumnName = jcolumnName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Column [columnName=");
		builder.append(columnName);
		builder.append(", dataType=");
		builder.append(dataType);
		builder.append(", description=");
		builder.append(description);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", jfunctionName=");
		builder.append(jfunctionName);
		builder.append(", jcolumnName=");
		builder.append(jcolumnName);
		builder.append(", filedLength=");
		builder.append(filedLength);
		builder.append(", precision=");
		builder.append(precision);
		builder.append(", deVlaue=");
		builder.append(deVlaue);
		builder.append(", hasLength=");
		builder.append(hasLength);
		builder.append(", hasPrecision=");
		builder.append(hasPrecision);
		builder.append(", isPrimaryKey=");
		builder.append(isPrimaryKey);
		builder.append(", isCanNull=");
		builder.append(isCanNull);
		builder.append(", IsIdentity=");
		builder.append(IsIdentity);
		builder.append("]");
		return builder.toString();
	}

}
