/**   
 * @Title: Column.java 
 * @Package com.ulpay.generator.bean 
 * @Description: 
 * @author panhaicheng@gomeholdings.com
 * @date 2016年10月24日 上午11:57:28 
 * @version V1.0   
 */
package com.gome.generator.bean;

/**
 * @ClassName: Column
 * @Description:
 * @author panhaicheng@gomeholdings.com
 * @date 2016年10月24日 上午11:57:28
 * 
 */
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
	private boolean primaryKey;
	/**
	 * 是否可以为空
	 */
	private boolean canNull;
	/**
	 * 是否是标识
	 */
	private boolean identity;
	/**
	 * 控件类型 TEXTBOX,COMBOBOX,DATEBOX
	 */
	private String boxType;
	/**
	 * 下拉值
	 */
	private String selectValue;
	/**
	 * 是否列表显示
	 */
	private boolean showList;
	/**
	 * 是否详情显示
	 */
	private boolean allowDetail;
	/**
	 * 是否允许增加
	 */
	private boolean allowAdd;
	/**
	 * 是否允许编辑
	 */
	private boolean allowUpdate;
	/**
	 * 是否搜索
	 */
	private boolean search;
	/**
	 * 是否允许为空
	 */
	private boolean allowNull;
	/**
	 * 搜索类型(1:值,2:范围，3：模糊查询)
	 */
	private String searchType;
	/**
	 * 详情是否展示
	 */
	private boolean detailHidden;
	/**
	 * 是否汇总
	 */
	private boolean sum;

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
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isCanNull() {
		return canNull;
	}

	public void setCanNull(boolean canNull) {
		this.canNull = canNull;
	}

	public boolean isIdentity() {
		return identity;
	}

	public void setIdentity(boolean identity) {
		this.identity = identity;
	}

	public String getBoxType() {
		return boxType;
	}

	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public boolean isShowList() {
		return showList;
	}

	public void setShowList(boolean showList) {
		this.showList = showList;
	}

	public boolean isAllowDetail() {
		return allowDetail;
	}

	public void setAllowDetail(boolean allowDetail) {
		this.allowDetail = allowDetail;
	}

	public boolean isAllowAdd() {
		return allowAdd;
	}

	public void setAllowAdd(boolean allowAdd) {
		this.allowAdd = allowAdd;
	}

	public boolean isAllowUpdate() {
		return allowUpdate;
	}

	public void setAllowUpdate(boolean allowUpdate) {
		this.allowUpdate = allowUpdate;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public boolean isAllowNull() {
		return allowNull;
	}

	public void setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public boolean isDetailHidden() {
		return detailHidden;
	}

	public void setDetailHidden(boolean detailHidden) {
		this.detailHidden = detailHidden;
	}

	public boolean isSum() {
		return sum;
	}

	public void setSum(boolean sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "Column [columnName=" + columnName + ", dataType=" + dataType
				+ ", description=" + description + ", remark=" + remark
				+ ", jfunctionName=" + jfunctionName + ", jcolumnName="
				+ jcolumnName + ", jdataType=" + jdataType + ", filedLength="
				+ filedLength + ", precision=" + precision + ", deVlaue="
				+ deVlaue + ", hasLength=" + hasLength + ", hasPrecision="
				+ hasPrecision + ", primaryKey=" + primaryKey + ", canNull="
				+ canNull + ", identity=" + identity + ", boxType=" + boxType
				+ ", selectValue=" + selectValue + ", showList=" + showList
				+ ", allowDetail=" + allowDetail + ", allowAdd=" + allowAdd
				+ ", allowUpdate=" + allowUpdate + ", search=" + search
				+ ", allowNull=" + allowNull + ", searchType=" + searchType
				+ ", detailHidden=" + detailHidden + ", sum=" + sum + "]";
	}
}
