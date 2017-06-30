package com.coltd.platform.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.coltd.platform.generator.bean.AutoGenInf;
import com.coltd.platform.generator.bean.Column;
import com.coltd.platform.generator.bean.Table;

/**
 * Excel工具类<br>包括得到所有表及字段<br>
 * */
public class MyExcelHelper {
	
	/**
	 * 从Excel中得到表
	 * @param file 文件
	 * @param readColumn 是否读取列信息
	 */
	public static List<Map<String, Object>> getAllTables(File file, boolean readColumn) throws BiffException, IOException {
		Workbook wb = Workbook.getWorkbook(file);//构造Workbook（工作薄）对象
		List<Map<String, Object>> listModel=new ArrayList<Map<String,Object>>();

		//获得了Workbook对象之后，就可以通过它得到Sheet对象了
		Sheet[] sheet = wb.getSheets();

		//读取第一张表
		Sheet firSheet = sheet[0];
		for (int i = 1; i < firSheet.getRows(); i++) {//从第2张开始
			Map<String, Object> model = new LinkedHashMap<String, Object>();
			
			AutoGenInf autoGenInf = new AutoGenInf($(firSheet.getRow(i), 1), $(firSheet.getRow(i), 2), $(firSheet.getRow(i), 5),$(firSheet.getRow(i), 6));
			//构建一张表
			Table table = new Table($(firSheet.getRow(i), 3), $(firSheet.getRow(i), 4));

			//需要读限列信息
			if (readColumn) {
				for (int j = 1; j < sheet.length; j++) {//找出对应的表
					String tn = $(sheet[j].getRow(0), 1);//得到表名
					if (table.getTableName().equals(tn)) {//找到对应的表后，读取列信息
						Sheet s=sheet[j];
						table.setId(String.valueOf(j));
						table.setSchema($(s.getRow(1), 1));
						table.setTableDescription($(s.getRow(2), 1));
						String primaryKey=$(s.getRow(3), 1);
						Map<String,String> priMap=new LinkedHashMap<String, String>();
						String [] arr=primaryKey.split(",");
						String primaryKeyJ="";
						try{
							primaryKeyJ=$(s.getRow(2), 2);
						}catch(Exception ex){
						}
						if(primaryKeyJ!=null&&!"".equals(primaryKeyJ)){
							String [] arr2=primaryKeyJ.split(",");
							for (int k = 0; k < arr.length; k++) {
								priMap.put(arr[k],arr2[k]);
							}
						}else{
							for (int k = 0; k < arr.length; k++) {
								priMap.put(arr[k],replace_(toLowerCase(arr[k])));
							}
						}
						
						table.setPrimaryKeyMap(priMap);
						String uniqueKey=$(s.getRow(3), 1);
						Map<String,String> uniMap=new LinkedHashMap<String, String>();
						String [] arrU=uniqueKey.split(",");
						String uniqueKeyJ="";
						try{
							uniqueKeyJ=$(s.getRow(3), 2);
						}catch(Exception ex){
						}
						if(uniqueKeyJ!=null&&!"".equals(uniqueKeyJ)){
							String [] arrU2=uniqueKeyJ.split(",");
							for (int k = 0; k < arrU.length; k++) {
								priMap.put(arrU[k],arrU2[k]);
							}
						}else{
							for (int k = 0; k < arrU.length; k++) {
								priMap.put(arrU[k],replace_(toLowerCase(arrU[k])));
							}
						}
						
						table.setUniqueKeyMap(uniMap);
						table.setColumnMap(getColumnsByTable(autoGenInf,s));
						break;
					}
				}
			}
			model.put(AutoGenInf.AUTOGENINF, autoGenInf);
			model.put(Table.TABLEINFENTITY, table);
			listModel.add(model);
		}
		wb.close();//最后关闭资源，释放内存
		return listModel;
	}

	/**
	 * 得到某表的所有字段
	 * @param tableName 表名
	 * */
	public static Map<String, Column> getColumnsByTable(AutoGenInf autoGenInf,File file, String tableName) throws BiffException, IOException {
		Map<String, Column> columns = null;
		Workbook wb = Workbook.getWorkbook(file);//构造Workbook对象

		Sheet[] sheet = wb.getSheets();

		//对每个工作表进行循环,找出对应表
		for (int i = 1; i < sheet.length; i++) {
			String tn = $(sheet[i].getRow(0), 1);//表名
			if (tableName.equals(tn)) {//找到对应的表
				columns = getColumnsByTable(autoGenInf,sheet[i]);
			}
		}
		return columns;
	}
	/**
	 * 字符串首个字母大写
	 */
	public static String firstToUpperCase(String str) {
		char trimChars[] = str.toCharArray();
		trimChars[0] = Character.toUpperCase(trimChars[0]);
		return new String(trimChars);
	}
	/**
	 * 字符串首个字母大写
	 */
	public static String firstToLowCase(String str) {
		char trimChars[] = str.toCharArray();
		trimChars[0] = Character.toLowerCase(trimChars[0]);
		return new String(trimChars);
	}
	
	/**
	 * 替换- 并
	* @Title: replace_ 
	* @Description: 
	* @param str
	* @return    设定文件
	 */
	public static String replace_(String str){
		String a[]=str.split("_");
		StringBuilder sb=new StringBuilder();
		sb.append(a[0]);
		for (int i = 1; i < a.length; i++) {
			char trimChars[] = a[i].toCharArray();
			trimChars[0] = Character.toUpperCase(trimChars[0]);
			sb.append(new String(trimChars));
		}
		return sb.toString();
	}
	/**
	 * 字符串首个字母小写
	 */
	public static String firstToLowerCase(String str) {
		char trimChars[] = str.toCharArray();
		trimChars[0] = Character.toLowerCase(trimChars[0]);
		return new String(trimChars);
	}
	/**
	 * 字符串首个字母小写
	 */
	public static String toLowerCase(String str) {
		return str.toLowerCase();
	}
	/**
	 * 将excel中sheet的每一行封装为Column
	 * */
	private static Map<String, Column> getColumnsByTable(AutoGenInf autoGenInf,Sheet sheet) {
		Map<String, Column> columns = new LinkedHashMap<String, Column>();
			
		for (int j = 7; j < sheet.getRows(); j++) {
			Cell[] cells = sheet.getRow(j);
			Column column = new Column();
			column.setColumnName($(cells, 0).toUpperCase().trim());// 字段名
			column.setDescription($(cells, 1));// 中文字段名
			column.setDataType($(cells, 2));//类型
			column.setRemark($(cells, 4));// 外键
			column.setJcolumnName(replace_(toLowerCase($(cells, 0).trim())));
			column.setJfunctionName(firstToUpperCase(replace_(toLowerCase($(cells, 0).trim()))));
			try{
				if($(cells, 5)!=null&&!"".equals($(cells, 5))){
					column.setJcolumnName($(cells, 5).trim());
					column.setJfunctionName(firstToUpperCase($(cells, 5).trim()));
				}
			}catch(Exception ex){
			}
			//处理数据类型
			String type = $(cells, 2);// 类型和长度// varchat(10,3)
			if (type.indexOf("(") == -1) {// 没有长度的类型
				column.setHasLength(false);
				column.setDataType(type);// 类型
			} else {// 有长度的类型
				column.setHasLength(true);
				int beginIndex = type.indexOf("(");
				int endIndex = type.indexOf(")");
				String typeTemp = type.substring(0, beginIndex);// 类型
				String typeLengthTemp = type.substring(beginIndex + 1, endIndex);// 长度
				int dianIndex = typeLengthTemp.indexOf(",");
				if (dianIndex == -1) {// 一位长度
					column.setHasPrecision(false);// 无精度
					column.setFiledLength(Integer.parseInt(typeLengthTemp.trim()));
				} else {// 多位长度
					column.setHasPrecision(true);// 有精度
					column.setFiledLength(Integer.parseInt(typeLengthTemp.substring(0, dianIndex).trim()));
					column.setPrecision(Integer.parseInt(typeLengthTemp.substring(dianIndex + 1).trim()));
				}
				column.setDataType(typeTemp);
			}
			String fullType=TypeMapping.sqlToJava(autoGenInf.getDbType(),column.getDataType());
			autoGenInf.implortPage(fullType);
			column.setJdataType(classNameSubName(fullType));
			columns.put(column.getColumnName(), column);//键为小写
		}
		return columns;
	}

	/**
	 * 根据表名得到某一张表
	 * @param tableName 表名
	 * @param readColumn 是否需要读取列信息
	 */
	public static Map<String, Object> getTable(File file, String tableName, boolean readColumn) throws BiffException, IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		
		Table table = null;
		AutoGenInf autoGenInf=null;
		Workbook wb = Workbook.getWorkbook(file);//构造Workbook对象

		//获得了Workbook对象之后，就可以通过它得到Sheet对象了
		Sheet[] sheet = wb.getSheets();

		//读取第一张表
		Sheet firSheet = sheet[0];
		for (int i = 1; i < firSheet.getRows(); i++) {
			String tn = $(firSheet.getRow(i), 3);//表名

			//找到对应的表
			if (tableName.equals(tn)) {
				autoGenInf = new AutoGenInf($(firSheet.getRow(i), 1), $(firSheet.getRow(i), 2), $(firSheet.getRow(i), 5),$(firSheet.getRow(i), 6));
				//构建一张表
				 table = new Table($(firSheet.getRow(i), 3), $(firSheet.getRow(i), 4));
				if (readColumn) {
					table.setColumnMap(getColumnsByTable(autoGenInf,file, tableName));
				}
				break;
			}
			model.put(AutoGenInf.AUTOGENINF, autoGenInf);
			model.put(Table.TABLEINFENTITY, table);
		}
		wb.close();//最后关闭资源，释放内存
		return model;
	}

	/**
	 * 从Cell[]得到第index个cell的内容<br>
	 * 如果index>=Cell.length()，或cell[index]为空,则返回""
	 * */
	private static String $(Cell[] cs, int index) {
		if (index >= cs.length) {
			return "";
		}
		if (cs[index] == null) {
			return "";
		}
		return cs[index].getContents();
	}
	/**
	 * 将类的包名过滤，只返回类名 的数组<br>如果只有类名，则直接返回类名
	 * */
	public static String classNameSubName(String className) {
		int index = className.lastIndexOf(".");
		if (index != -1) {
			return className.substring(index + 1);
		}
		return className;
	}
}
