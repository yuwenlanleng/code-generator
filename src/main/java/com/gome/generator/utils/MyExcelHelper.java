package com.gome.generator.utils;

import com.coltd.platform.generator.bean.Table;
import com.coltd.platform.generator.utils.TimeUtil;
import com.gome.generator.bean.Column;
import com.gome.generator.bean.GeneratorInf;
import com.gome.generator.bean.TableInf;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Excel工具类<br>包括得到所有表及字段<br>
 * */
public class MyExcelHelper {
	
	/**
	 * 1:值,2:范围，3：模糊查询
	 */
	public static final String COLUMN_SEARCH_TYPE_1 = "1";
	public static final String COLUMN_SEARCH_TYPE_2 = "2";
	public static final String COLUMN_SEARCH_TYPE_3 = "3";
	
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
			//代码生成基本信息
			GeneratorInf inf=new GeneratorInf();
			inf.setAuthor($(firSheet.getRow(i), 5));
			inf.setDate(TimeUtil.foramtDate2String(new Date(),"yyyy年MM月dd日 HH时mm分ss秒"));
			inf.setDbType($(firSheet.getRow(i), 6));
			inf.setEntityName($(firSheet.getRow(i), 2));
			inf.setEntityNameFirstLower(StringUtil.firstToLowerCase($(firSheet.getRow(i), 2)));
			inf.setPackageName($(firSheet.getRow(i), 1));
			
			//构建一张表
			TableInf table=new TableInf();
			table.setTableName($(firSheet.getRow(i), 3));
			table.setDescription($(firSheet.getRow(i), 4));
			
			//需要读限列信息
			if (readColumn) {
				for (int j = 1; j < sheet.length; j++) {//找出对应的表
					String tn = $(sheet[j].getRow(0), 1);//得到表名
					if (table.getTableName().equals(tn)) {//找到对应的表后，读取列信息
						Sheet s=sheet[j];
						table.setId(String.valueOf(j));
						table.setSchema($(s.getRow(1), 1));
						//取主键信息
						String primaryKey=$(s.getRow(3), 1);
						Map<String,String> priMap=new LinkedHashMap<String, String>();
						String [] arr=primaryKey.split(",");
						for (int k = 0; k < arr.length; k++) {
							priMap.put(arr[k], StringUtil.replace_(StringUtil.toLowerCase(arr[k])));
						}
						table.setPrimaryKeyMap(priMap);
						//唯一索引信息
						String uniqueKey=$(s.getRow(3), 1);
						Map<String,String> uniMap=new LinkedHashMap<String, String>();
						String [] arrU=uniqueKey.split(",");
						for (int k = 0; k < arrU.length; k++) {
							priMap.put(arrU[k], StringUtil.replace_(StringUtil.toLowerCase(arrU[k])));
						}
						table.setUniqueKeyMap(uniMap);
						
						//获取操作信息
						try{
							List<String> operList=new ArrayList<String>();
							operList.add($(s.getRow(4), 1));
							operList.add($(s.getRow(4), 2));
							operList.add($(s.getRow(4), 3));
							operList.add($(s.getRow(4), 4));
						}catch(Exception ex){
							
						}
						
						table.setColumnMap(getColumnsByTable(inf,s));
						break;
					}
				}
			}
			model.put(GeneratorInf.AUTOGENINF, inf);
			model.put(TableInf.TABLEINFENTITY, table);
			listModel.add(model);
		}
		wb.close();//最后关闭资源，释放内存
		return listModel;
	}

	/**
	 * 得到某表的所有字段
	 * @param tableName 表名
	 * */
	public static Map<String, Column> getColumnsByTable(GeneratorInf inf, File file, String tableName) throws BiffException, IOException {
		Map<String, Column> columns = null;
		Workbook wb = Workbook.getWorkbook(file);//构造Workbook对象

		Sheet[] sheet = wb.getSheets();

		//对每个工作表进行循环,找出对应表
		for (int i = 1; i < sheet.length; i++) {
			String tn = $(sheet[i].getRow(0), 1);//表名
			if (tableName.equals(tn)) {//找到对应的表
				columns = getColumnsByTable(inf,sheet[i]);
			}
		}
		return columns;
	}
	
	/**
	 * 将excel中sheet的每一行封装为Column
	 * */
	private static Map<String, Column> getColumnsByTable(GeneratorInf inf,
			Sheet sheet) {
		Map<String, Column> columns = new LinkedHashMap<String, Column>();
		// 从第8行开始取列信息
		for (int j = 8; j < sheet.getRows(); j++) {
			Cell[] cells = sheet.getRow(j);
			Column column = new Column();
			if (StringUtil.isEmpty($(cells, 0).toUpperCase().trim())) {
				break;
			}
			column.setColumnName($(cells, 0).toUpperCase().trim());// 字段名
			column.setDescription($(cells, 1));// 中文字段名
			column.setDataType($(cells, 2));// 类型
			column.setRemark($(cells, 4));// 外键
			column.setJcolumnName(StringUtil.replace_(StringUtil.toLowerCase($(
					cells, 0).trim())));// java字段名称
			column.setJfunctionName(StringUtil.firstToUpperCase(StringUtil
					.replace_(StringUtil.toLowerCase($(cells, 0).trim()))));// 方法名称
			column.setBoxType(StringUtil.trim($(cells, 5)));
			column.setSelectValue(StringUtil.trim($(cells, 6)));
			column.setShowList(StringUtil.trim($(cells, 7)).equals("1") ? true
					: false);
			column.setAllowDetail(StringUtil.trim($(cells, 8)).equals("1") ? true
					: false);
			column.setAllowAdd(StringUtil.trim($(cells, 9)).equals("1") ? true
					: false);
			column.setAllowUpdate(StringUtil.trim($(cells, 10)).equals("1") ? true
					: false);
			column.setSearch(StringUtil.trim($(cells, 11)).equals("1") ? true
					: false);
			column.setAllowNull(StringUtil.trim($(cells, 12)).equals("1") ? true
					: false);
			column.setSearchType(StringUtil.trim($(cells, 13)));
			column.setDetailHidden(StringUtil.trim($(cells, 14)).equals("1") ? true
					: false);
			column.setSum(StringUtil.trim($(cells, 15)).equals("1") ? true
					: false);

			// 处理数据类型
			String type = $(cells, 2);// 类型和长度// varchat(10,3)
			if (type.indexOf("(") == -1) {// 没有长度的类型
				column.setHasLength(false);
				column.setDataType(type);// 类型
			} else {// 有长度的类型
				column.setHasLength(true);
				int beginIndex = type.indexOf("(");
				int endIndex = type.indexOf(")");
				String typeTemp = type.substring(0, beginIndex);// 类型
				String typeLengthTemp = type
						.substring(beginIndex + 1, endIndex);// 长度
				int dianIndex = typeLengthTemp.indexOf(",");
				if (dianIndex == -1) {// 一位长度
					column.setHasPrecision(false);// 无精度
					column.setFiledLength(Integer.parseInt(typeLengthTemp
							.trim()));
				} else {// 多位长度
					column.setHasPrecision(true);// 有精度
					column.setFiledLength(Integer.parseInt(typeLengthTemp
							.substring(0, dianIndex).trim()));
					column.setPrecision(Integer.parseInt(typeLengthTemp
							.substring(dianIndex + 1).trim()));
				}
				column.setDataType(typeTemp);
			}
			String fullType = TypeMapping.sqlToJava(inf.getDbType(),
					column.getDataType());
			inf.implortPage(fullType);
			column.setJdataType(StringUtil.classNameSubName(fullType));
			columns.put(column.getColumnName(), column);// 键为小写
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
		
		TableInf table = null;
		GeneratorInf inf=null;
		Workbook wb = Workbook.getWorkbook(file);//构造Workbook对象

		//获得了Workbook对象之后，就可以通过它得到Sheet对象了
		Sheet[] sheet = wb.getSheets();

		//读取第一张表
		Sheet firSheet = sheet[0];
		for (int i = 1; i < firSheet.getRows(); i++) {
			String tn = $(firSheet.getRow(i), 3);//表名

			//找到对应的表
			if (tableName.equals(tn)) {
				//代码生成基本信息
				inf=new GeneratorInf();
				inf.setAuthor($(firSheet.getRow(i), 5));
				inf.setDate(TimeUtil.foramtDate2String(new Date(),"yyyy年MM月dd日 HH时mm分ss秒"));
				inf.setDbType($(firSheet.getRow(i), 6));
				inf.setEntityName($(firSheet.getRow(i), 2));
				inf.setEntityNameFirstLower(StringUtil.firstToLowerCase($(firSheet.getRow(i), 2)));
				inf.setPackageName($(firSheet.getRow(i), 1));
			
				//构建一张表
				table=new TableInf();
				table.setTableName($(firSheet.getRow(i), 3));
				table.setDescription($(firSheet.getRow(i), 4));
				if (readColumn) {
					table.setColumnMap(getColumnsByTable(inf,file, tableName));
				}
				break;
			}
			model.put(GeneratorInf.AUTOGENINF, inf);
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
}
