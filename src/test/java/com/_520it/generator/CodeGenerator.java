package com._520it.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.MessageFormat;



import com._520it.pss.domain.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

@SuppressWarnings("all")
// 代码生成器
public class CodeGenerator {
	private static  boolean isBuild=false;
	public static void main(String[] args) throws Exception {
		 ClassInfo classInfo = new ClassInfo(Employee.class);
//		 createFile(classInfo);
	}

	private static void createFile(ClassInfo classInfo) throws Exception{
		// 生成DAO组件
		createFile(classInfo, "IDAO.ftl", "src/main/java/{0}/dao/I{1}DAO.java");
		createFile(classInfo, "DAOImpl.ftl",
				"src/main/java/{0}/dao/impl/{1}DAOImpl.java");
		// 生成Service组件
		createFile(classInfo, "IService.ftl",
				"src/main/java/{0}/service/I{1}Service.java");
		createFile(classInfo, "ServiceImpl.ftl",
				"src/main/java/{0}/service/impl/{1}ServiceImpl.java");
		// 生成query和action组件
		createFile(classInfo, "QueryObject.ftl",
				"src/main/java/{0}/query/{1}QueryObject.java");
		createFile(classInfo, "Action.ftl",
				"src/main/java/{0}/web/action/{1}Action.java");
		// 生成映射文件
		createFile(classInfo, "hbm.ftl",
				"src/main/resources/{0}/domain/{1}.hbm.xml");
		// 生成JSP文件(list.jsp/input.jsp)
		createFile(classInfo, "list.ftl",
				"src/main/webapp/WEB-INF/views/{2}/list.jsp");
		createFile(classInfo, "input.ftl",
				"src/main/webapp/WEB-INF/views/{2}/input.jsp");
		// 追加<bean/>元素配置
		appendXml(classInfo, "dao.ftl",
				"src/main/resources/applicationContext-dao.xml");
		appendXml(classInfo, "service.ftl",
				"src/main/resources/applicationContext-service.xml");
		appendXml(classInfo, "action2.ftl",
				"src/main/resources/applicationContext-action.xml");
	}
	@Test
	public void test54() {

		try {
			createFile(new ClassInfo(SaleAccount.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void test66(){

		System.out.println(new ClassInfo(OrderBill.class));

	}

	// 追加XML片段
	private static void appendXml(ClassInfo classInfo, String templateFileName,
			String outputFilePath) throws Exception {
		if(isBuild){
			return;
		}
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("templates"));
		Template template = cfg.getTemplate(templateFileName);

		StringWriter sWriter = new StringWriter();
		template.process(classInfo, sWriter);
		XmlUtil.mergeXML(new File(outputFilePath), sWriter.toString());
	}

	// 生成Java和JSP文件
	private static void createFile(ClassInfo classInfo,
			String templateFileName, String outputFilePath) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("templates"));
		Template template = cfg.getTemplate(templateFileName);
		outputFilePath = MessageFormat.format(outputFilePath, classInfo
				.getBasePackage().replace(".", "/"), classInfo.getClassName(),
				classInfo.getObjectName());
		File f = new File(outputFilePath);
		if(f.exists()){
			//存在该文件就不操作
			isBuild=true;
			return;
		}
		if (!f.getParentFile().exists()) {// 如果该文件的上一级目录不存在,则新建上级目录
			f.getParentFile().mkdirs();
		}
		template.process(classInfo, new FileWriter(f));
	}

}
