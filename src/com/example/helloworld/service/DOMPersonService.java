package com.example.helloworld.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.helloworld.domain.Person;

import android.util.Log;


public class DOMPersonService {
	public List<Person> getPersons(InputStream is) throws ParserConfigurationException, SAXException, IOException {
		
		List<Person> res = null;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(is);
		Element root = doc.getDocumentElement();
		NodeList persons = root.getElementsByTagName("person");
		
		res = new ArrayList<Person>();
		
		for(int i=0; i<persons.getLength(); i++) {
			Element e_p = (Element)persons.item(i);
			Person p = new Person();
			p.setId(e_p.getAttribute("id"));
			NodeList children = e_p.getChildNodes();
			for(int j=0; j<children.getLength(); j++) {
				Node n_c = children.item(j);
				if(n_c.getNodeType() == Node.ELEMENT_NODE) {
					Element e_c = (Element)n_c;
					Log.i("tag", "getNodeName()=" + e_c.getNodeName());
					Log.i("tag", "getNodeValue()=" + e_c.getNodeValue());
					Log.i("tag", "getTagName()=" + e_c.getTagName());
					Log.i("tag", "getTextContent()=" + e_c.getTextContent());
					Log.i("tag", "getFirstChild().getNodeValue()=" + e_c.getFirstChild().getNodeValue());
					if("name".equals(e_c.getNodeName())) {
						p.setName(e_c.getFirstChild().getNodeValue());
					} else if ("age".equals(e_c.getNodeName())) {
						p.setAge(e_c.getFirstChild().getNodeValue());
					}
				}
				
			}
			res.add(p);
		}
		
		return res;
	}
}
