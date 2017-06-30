package com.coltd.platform.generator.utils;

/**
 * 
 * @ClassName: TypeMapping 
 * @Description: 类型映射关系
 * @author panhaicheng@gomeholdings.com
 * @date 2016年7月12日 下午1:59:35 
 *
 */
public class TypeMapping {
	public static final String DB_TYPE_DB2 = "DB2";
	public static final String DB_TYPE_ORACLE = "ORACLE";
	public static final String DB_TYPE_MYSQL = "MYSQL";

	public static String sqlToJava(String dbType, String type) {
		if (DB_TYPE_DB2.equalsIgnoreCase(dbType)) {
			return sqlToJavaDB2(type);
		} else if (DB_TYPE_ORACLE.equalsIgnoreCase(dbType)) {
			return sqlToJavaOracle(type);
		} else if (DB_TYPE_MYSQL.equalsIgnoreCase(dbType)) {
			return sqlToJavaMySql(type);
		}
		return "";
	}

	public static String sqlToJavaDB2(String type) {
		if (type == null) {
			return null;
		}
		type = type.toLowerCase();// 变成小写
		if (type.equalsIgnoreCase("int")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("varchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("char")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("nchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("nvarchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("text")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("ntext")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("tinyint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("int")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("tinyint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("smallint")) {
			return "java.lang.Short";
		} else if (type.equalsIgnoreCase("bit")) {
			return "java.lang.Boolean";
		} else if (type.equalsIgnoreCase("bigint")) {
			return "java.lang.Long";
		} else if (type.equalsIgnoreCase("float")) {
			return "java.lang.Double";
		} else if (type.equalsIgnoreCase("decimal")) {
			return "java.lang.Long";
		} else if (type.equalsIgnoreCase("money")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("smallmoney")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("numeric")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("real")) {
			return "java.lang.Float";
		} else if (type.equalsIgnoreCase("uniqueidentifier")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("smalldatetime")) {
			return "java.util.Date";
		} else if (type.equalsIgnoreCase("datetime")) {
			return "java.util.Date";
		} else if (type.equalsIgnoreCase("sql_variant")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("TIMESTAMP")) {
			return "java.sql.Timestamp";
		} else if (type.equalsIgnoreCase("current timestamp")) {
			return "java.sql.Timestamp";
		}
		return null;
	}

	public static String sqlToJavaMySql(String type) {
		if (type == null) {
			return null;
		}
		type = type.toLowerCase();// 变成小写
		if (type.equalsIgnoreCase("int")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("varchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("char")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("nchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("nvarchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("text")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("ntext")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("tinyint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("int")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("tinyint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("smallint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("bit")) {
			return "java.lang.Boolean";
		} else if (type.equalsIgnoreCase("bigint")) {
			return "java.lang.Long";
		} else if (type.equalsIgnoreCase("float")) {
			return "java.lang.Double";
		} else if (type.equalsIgnoreCase("decimal")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("money")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("smallmoney")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("numeric")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("real")) {
			return "java.lang.Float";
		} else if (type.equalsIgnoreCase("uniqueidentifier")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("smalldatetime")) {
			return "java.util.Date";
		} else if (type.equalsIgnoreCase("datetime")) {
			return "java.util.Date";
		} else if (type.equalsIgnoreCase("sql_variant")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("TIMESTAMP")) {
			return "java.sql.Timestamp";
		} else if (type.equalsIgnoreCase("current timestamp")) {
			return "java.sql.Timestamp";
		}
		return null;
	}

	public static String sqlToJavaOracle(String type) {
		System.out.println(type);
		if (type == null) {
			return null;
		}
		System.out.println(type);
		type = type.toLowerCase().trim();// 变成小写
		if (type.equalsIgnoreCase("int")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("varchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("varchar2")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("char")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("nchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("nvarchar")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("text")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("ntext")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("tinyint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("int")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("tinyint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("smallint")) {
			return "java.lang.Integer";
		} else if (type.equalsIgnoreCase("bit")) {
			return "java.lang.Boolean";
		} else if (type.equalsIgnoreCase("bigint")) {
			return "java.lang.Long";
		} else if (type.equalsIgnoreCase("float")) {
			return "java.lang.Double";
		} else if (type.equalsIgnoreCase("decimal")) {
			return "java.math.BigDecimal";
		}else if (type.equalsIgnoreCase("decimal")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("money")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("smallmoney")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("integer")) {
			return "java.lang.Integer";
		}  else if (type.equalsIgnoreCase("numeric")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("NUMBER(18,2)")) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("NUMBER(18,2)")||(type.contains("NUMBER")&&type.contains(","))) {
			return "java.math.BigDecimal";
		} else if (type.equalsIgnoreCase("NUMBER(13)")) {
			return "java.lang.BigDecimal";
		}else if (type.equalsIgnoreCase("NUMBER")) {
			return "java.lang.BigDecimal";
		}else if (type.equalsIgnoreCase("real")) {
			return "java.lang.Float";
		} else if (type.equalsIgnoreCase("uniqueidentifier")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("smalldatetime")) {
			return "java.util.Date";
		} else if (type.equalsIgnoreCase("datetime")) {
			return "java.util.Date";
		} else if (type.equalsIgnoreCase("sql_variant")) {
			return "java.lang.String";
		} else if (type.equalsIgnoreCase("TIMESTAMP")) {
			return "java.sql.Timestamp";
		} else if (type.equalsIgnoreCase("current timestamp")) {
			return "java.sql.Timestamp";
		}
		return null;
	}
}
