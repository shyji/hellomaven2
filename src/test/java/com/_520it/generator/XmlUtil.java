package com._520it.generator;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {

	/**
	 * 	把XML片段追加到指定的XML文件中去
	 * 
	 * @param targetXml		XML文件
	 * @param appendingXml	XML片段
	 * 				<bean id="roleDAO" class="com._520it.pss.dao.impl.RoleDAOImpl" parent="baseDAO"/>
	 */
	public static void mergeXML(File targetXml, String appendingXml)
			throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(targetXml);
		doc.setXMLEncoding("UTF-8");
		
		Document flagment = DocumentHelper.parseText(appendingXml);
		Element flagEle = flagment.getRootElement();
		flagEle.setQName(new QName(flagEle.getName(), doc.getRootElement()
				.getNamespace()));
		if (flagEle.elements().size() > 0) {
			for (Object c : flagEle.elements()) {
				Element cel = (Element) c;
				cel.setQName(new QName(cel.getName(), flagEle.getNamespace()));
			}
		}
		doc.getRootElement().add(flagEle);

		XMLWriter writer = new XMLWriter(new FileWriter(targetXml),OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
}
