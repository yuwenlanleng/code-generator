package com.gome.generator;

import com.gome.generator.bean.GeneratorInf;
import com.gome.generator.utils.VelocityInfoUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;


/**
 * ***********************************************************************
 * <br>description : 代码生成类
 * @author      panhaicheng@gomeholdings.com
 * @version     1.0  
 * @date        2014年12月3日 下午6:06:53 
 ************************************************************************
 */
public class CodeAutoGenerator {

	private static final Logger logger = Logger.getLogger(CodeAutoGenerator.class);
	
	private static final String ENTITY_PACKAGE="entity_package";
	private static final String DAO_PACKAGE="mapper_package";
	private static final String ENTITY_NAME="Entity";
	private static final String DAO_NAME="EntityMapper";
	private static final String ENTITY_PACKAGE_NAME="data.model";
	private static final String DAO_PACKAGE_NAME="data.mapper";
	private static final String DAO_PACKAGE_CHILD_NAME="base";
	private static final String SERVICE_PACKAGE_NAME="service";
	private static final String SERVICE_IMPL_PACKAGE_NAME="impl";
	private static final String SQLMAP_PARENT_PACKAGE_NAME="mybatis";
	private static final String SQLMAP_PACKAGE_NAME="mappers";
	private static final String SQLMAP_PACKAGE_CHILD_NAME="base";
	private static final String ACTION_PACKAGE_NAME="controller";
	private static final String JSP_PACKAGE_NAME="templates";
	private static final String JSP_PACKAGE_CHILD_NAME="module";
	private static final String JS_PACKAGE_NAME="static";
	private static final String JS_PACKAGE_CHILD_NAME="js";
	private static final String SLASH="/";
	private static final String DOT=".";
	private static final String SOURCE=SLASH+"src"+SLASH;
	private static final String MAVENSOURCE=SLASH+SOURCE+SLASH+"main"+SLASH+"java"+SLASH;
	private static final String MAVENRESOURCE=SLASH+SOURCE+SLASH+"main"+SLASH+"resources"+SLASH;
	private static final String FILE_JAVA_SUFFIX=".java";
	private static final String FILE_XML_SUFFIX=".xml";
	private static final String classPath=System.getProperty("user.dir");
	
	public static boolean generator(Map<String,Object> model,boolean isMaven,String path,String version) {
		String sourcePath =classPath+SOURCE;
		if(isMaven){
			sourcePath =classPath+MAVENSOURCE;
		}
		if(StringUtils.isNotEmpty(path)){
			sourcePath=path;
		}
		
		GeneratorInf inf=(GeneratorInf) model.get(GeneratorInf.AUTOGENINF);
		String entityId=inf.getEntityName();
		String entityIdLower=inf.getEntityNameFirstLower();
		String packagePath=(String) inf.getPackageName();
		if (packagePath.endsWith(SLASH)) {
			packagePath=packagePath.substring(0,packagePath.lastIndexOf(SLASH)).replaceAll("\\"+DOT, SLASH);
		} else {
			packagePath = packagePath.replaceAll("\\" + DOT, SLASH);
		}
		
		Long start = System.currentTimeMillis();
		String entityPath = packagePath + SLASH + ENTITY_PACKAGE_NAME;
		String daoPath = packagePath + SLASH + DAO_PACKAGE_NAME + SLASH +DAO_PACKAGE_CHILD_NAME;
		String servicePath = packagePath + SLASH + SERVICE_PACKAGE_NAME;
		String serviceImplPath = packagePath + SLASH + SERVICE_PACKAGE_NAME	+ SLASH + SERVICE_IMPL_PACKAGE_NAME;
		String daoSqlMapPath = SLASH +SQLMAP_PARENT_PACKAGE_NAME + SLASH + SQLMAP_PACKAGE_NAME + SLASH + SQLMAP_PACKAGE_CHILD_NAME;
		String actionPath = packagePath + SLASH + ACTION_PACKAGE_NAME;
		String jspPath = packagePath + SLASH + JSP_PACKAGE_NAME+SLASH + JSP_PACKAGE_CHILD_NAME;
		String jsPath = packagePath + SLASH + JS_PACKAGE_NAME+SLASH + JS_PACKAGE_CHILD_NAME;

		String entityName = entityId + FILE_JAVA_SUFFIX;
		String daoName = entityId + "Mapper" + FILE_JAVA_SUFFIX;
		String daoSqlMapName = entityId + "Mapper" + FILE_XML_SUFFIX;

		packagePath = packagePath.replaceAll(SLASH, DOT);
		String packageName = packagePath.substring(
				packagePath.lastIndexOf(".") + 1, packagePath.length());
		
		model.put(ENTITY_PACKAGE, entityPath.replaceAll(SLASH, DOT));
		model.put("action_package", actionPath.replaceAll(SLASH, DOT));
		model.put(DAO_PACKAGE, daoPath.replaceAll(SLASH,DOT));
		model.put("service_package", servicePath.replaceAll(SLASH,DOT));
		model.put("service_impl_package", serviceImplPath.replaceAll(SLASH,DOT));
		model.put(ENTITY_NAME, entityId);
		model.put(ENTITY_NAME+"Low", entityIdLower);
		model.put("packageName", packageName);
		
		try {
			logger.info("---------------start---------------");
			VelocityInfoUtil.generatorCode(version+"/Entity.vm", model,sourcePath+entityPath,entityName);
			logger.info("["+entityId+ENTITY_NAME + ".java]生成代码成功");
			VelocityInfoUtil.generatorCode(version+"/Mapper.vm", model,sourcePath+daoPath,daoName);
			logger.info("["+entityId+DAO_NAME + ".java]生成代码成功");

			VelocityInfoUtil.generatorCode(version+"/ServiceImpl.vm", model,sourcePath+serviceImplPath,entityId+"ServiceImpl.java");
			logger.info("["+entityId+"ServiceImpl" + ".java]生成代码成功");
			VelocityInfoUtil.generatorCode(version+"/Service.vm", model,sourcePath+servicePath,entityId+"Service.java");
			logger.info("["+entityId+"Service" + ".java]生成代码成功");

			VelocityInfoUtil.generatorCode(version+"/Controller.vm", model,sourcePath+actionPath,entityId+"Controller.java");
			logger.info("["+entityId+"Controller" + ".java]生成代码成功");

			VelocityInfoUtil.generatorCode(version+"/ftl.vm", model,sourcePath+jspPath,"main.ftl");
			logger.info("["+entityId + ".ftl]生成代码成功");
			VelocityInfoUtil.generatorCode(version+"/js.vm", model,sourcePath+jsPath,entityId+".js");
			logger.info("["+entityId + ".js]生成代码成功");

			VelocityInfoUtil.generatorCode(version + "/SqlMapper.vm", model, classPath + MAVENRESOURCE + daoSqlMapPath, daoSqlMapName);
			logger.info("["+entityId+ENTITY_NAME+".xml" + "]生成代码成功");
			logger.info("---------------end time：" + (System.currentTimeMillis() - start) + "ms-----");
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		String a[]="aa".split("_");
		StringBuilder sb=new StringBuilder();
		sb.append(a[0]);
		for (int i = 1; i < a.length; i++) {
			char trimChars[] = a[i].toCharArray();
			trimChars[0] = Character.toUpperCase(trimChars[0]);
			sb.append(new String(trimChars));
		}
		System.out.println(sb);
	}
}
