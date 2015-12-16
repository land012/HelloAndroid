package com.example.helloworld.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.helloworld.domain.Person;

import android.util.Log;


public class SAXPersonService {
	public List<Person> getPersons(InputStream is) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		PersonParserHandler handler = new PersonParserHandler();
		parser.parse(is, handler);
		return handler.getPersons();
	}
	
	private final class PersonParserHandler extends DefaultHandler {
		private List<Person> persons = null;
		private Person person = null;
		private String tag = null;
		
		public List<Person> getPersons() {
			return persons;
		}
		
		@Override
		public void startDocument() throws SAXException {
			
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			
			tag = localName;
			
			Log.i("tag", "s-" + tag);
			
			if("persons".equals(localName)) {
				persons = new ArrayList<Person>();
			} else if ("person".equals(localName)) {
				person = new Person();
				person.setId(attributes.getValue("id"));
			}
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if("name".equals(tag)) {
				String name = new String(ch, start, length);
				person.setName(name);
			} else if("age".equals(tag)) {
				String age = new String(ch, start, length);
				person.setAge(age);
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			
			if("person".equals(localName)) {
				persons.add(person);
				person = null;
			}
			tag = null;
		}
		
	}
}
