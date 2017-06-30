package com.coltd.platform.generator;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.coltd.platform.generator.bean.AutoGenInf;
import com.coltd.platform.generator.utils.MyExcelHelper;
import com.coltd.platform.generator.utils.VelocityInfoUtil;


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
	private static final String DAO_PACKAGE="dao_package";
	private static final String DAO_IMPL_PACKAGE="dao_impl_package";
	private static final String ENTITY_NAME="Entity";
	private static final String DAO_NAME="EntityDao";
	private static final String DAO_IMPL_NAME="EntityDaoImpl";
	private static final String ENTITY_PACKAGE_NAME="entity";
	private static final String DAO_PACKAGE_NAME="dao";
	private static final String SERVICE_PACKAGE_NAME="service";
	private static final String DAO_IMPL_PACKAGE_NAME="impl";
	private static final String SQLMAP_PACKAGE_NAME="mapper";
	private static final String ACTION_PACKAGE_NAME="action";
	private static final String JSP_PACKAGE_NAME="jsp";
	private static final String SLASH="/";
	private static final String DOT=".";
	private static final String SOURCE=SLASH+"src"+SLASH;
	private static final String MAVENSOURCE=SLASH+SOURCE+SLASH+"main"+SLASH+"java"+SLASH;
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
		
		AutoGenInf inf=(AutoGenInf) model.get(AutoGenInf.AUTOGENINF);
		String entityId=inf.getEntityId();
		String entityIdL=MyExcelHelper.firstToLowerCase(inf.getEntityId());
		String packagePath=(String) inf.getAuthGenPath();
		if(packagePath.endsWith(SLASH)){
			packagePath=packagePath.substring(0,packagePath.lastIndexOf(SLASH)).replaceAll("\\"+DOT, SLASH);
		}else{
			packagePath=packagePath.replaceAll("\\"+DOT, SLASH);
		}
		Long start = System.currentTimeMillis();
		String entityPath= packagePath+SLASH+ENTITY_PACKAGE_NAME;
		String daoPath= packagePath+SLASH+DAO_PACKAGE_NAME;
		String servicePath= packagePath+SLASH+SERVICE_PACKAGE_NAME;
		String daoSqlMapPath= packagePath+SLASH+DAO_PACKAGE_NAME+SLASH+SQLMAP_PACKAGE_NAME;
		String daoImplPath=packagePath+SLASH+DAO_PACKAGE_NAME+SLASH+DAO_IMPL_PACKAGE_NAME;
		String serviceImplPath=packagePath+SLASH+SERVICE_PACKAGE_NAME+SLASH+DAO_IMPL_PACKAGE_NAME;
		String actionPath= packagePath+SLASH+ACTION_PACKAGE_NAME;
		String jspPath= packagePath+SLASH+JSP_PACKAGE_NAME;
		String entityName=entityId+FILE_JAVA_SUFFIX;
		String daoName=entityId+"Dao"+FILE_JAVA_SUFFIX;
		String daoSqlMapName=entityId+"Mapper"+FILE_XML_SUFFIX;
		String daoImplName=entityId+DAO_IMPL_NAME+FILE_JAVA_SUFFIX;
		
		model.put(ENTITY_PACKAGE, entityPath.replaceAll(SLASH, DOT));
		model.put(DAO_PACKAGE, daoPath.replaceAll(SLASH,DOT));
		model.put(DAO_IMPL_PACKAGE, daoImplPath.replaceAll(SLASH,DOT));
		model.put(ENTITY_NAME, entityId);
		model.put(ENTITY_NAME+"Low", entityIdL);
		model.put(DAO_NAME, entityId+"Dao");
		model.put(DAO_IMPL_NAME,entityId+DAO_IMPL_NAME);
		
		try {
			logger.info("---------------start---------------");
			VelocityInfoUtil.generatorCode(version+"/Entity.vm", model,sourcePath+entityPath,entityName);
			VelocityInfoUtil.generatorCode(version+"/Dao.vm", model,sourcePath+daoPath,daoName);
			if(version.equals("v1")||version.equals("v3")){
				VelocityInfoUtil.generatorCode(version+"/DaoImpl.vm", model,sourcePath+daoImplPath,daoImplName);
				logger.info("["+entityId+DAO_IMPL_NAME + ".java]生成代码成功");
			}
			if(version.equals("v3")){
				VelocityInfoUtil.generatorCode(version+"/ServiceImpl.vm", model,sourcePath+serviceImplPath,entityId+"ServiceImpl.java");
				logger.info("["+entityId+"ServiceImpl" + ".java]生成代码成功");
				VelocityInfoUtil.generatorCode(version+"/Service.vm", model,sourcePath+servicePath,entityId+"Service.java");
				logger.info("["+entityId+"Service" + ".java]生成代码成功");
				VelocityInfoUtil.generatorCode(version+"/Action.vm", model,sourcePath+actionPath,entityId+"Action.java");
				logger.info("["+entityId+"Action" + ".java]生成代码成功");
				VelocityInfoUtil.generatorCode(version+"/jsp.vm", model,sourcePath+jspPath,entityIdL+"_manage.jsp");
				logger.info("["+entityId + ".jsp]生成代码成功");
			}
			VelocityInfoUtil.generatorCode(version+"/Mapper.vm", model,sourcePath+daoSqlMapPath,daoSqlMapName);
			logger.info("["+entityId+ENTITY_NAME + ".java]生成代码成功");
			logger.info("["+entityId+DAO_NAME + ".java]生成代码成功");
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
