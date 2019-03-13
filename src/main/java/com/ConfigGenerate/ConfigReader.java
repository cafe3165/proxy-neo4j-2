package com.ConfigGenerate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigReader {

	private File inputXml;

	public ConfigReader(File inputXml) {
		this.inputXml = inputXml;
	}

//	public static void main(String[] argv) {
//		LocationReader dom4jParser = new LocationReader(new File("Location.xml"));
//		dom4jParser.traversalDocumentByIterator();
//	}

	public Element getRootElement() {
		return getDocument().getRootElement();
	}

	public Document getDocument() {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(inputXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public List<HashMap<String, String>> traversalDocumentByIterator() {
		Element root = getRootElement();
		// 枚举根节点下所有子节点
		List<HashMap<String, String>> lMaps=new ArrayList<HashMap<String,String>>();
		for (Iterator ie = root.elementIterator(); ie.hasNext();) {
//			System.out.println("======");
			Element element = (Element) ie.next();
//			System.out.println(element.getName());
			// 枚举属性
			String keyString="",valueString="";
//			for (Iterator ia = element.attributeIterator(); ia.hasNext();) {
//				Attribute attribute = (Attribute) ia.next();
//				System.out.println(attribute.getName() + ":" + attribute.getData());
////				LName = attribute.getData().toString();
////				LName = (String) attribute.getData();
//			}
			
			HashMap<String, String> map=new HashMap<String, String>();
			int count=0;
			for (Iterator ieson = element.elementIterator(); ieson.hasNext();) {
				Element elementSon = (Element) ieson.next();
//				System.out.println(elementSon.getName() + ":" + elementSon.getText());
				keyString=elementSon.getName().toString();
				valueString=elementSon.getText().toString();
				map.put(keyString, valueString);
			}
			lMaps.add(map);
		}		
//		for(int i=0;i<lMaps.size();i++) {
//			HashMap<String, String> tMap=lMaps.get(i);
//			for(String k:tMap.keySet()) {
//				System.out.println(k+" "+tMap.get(k));
//			}
//		}
			
		
		return lMaps;

	}

}
