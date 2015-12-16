package com.example.helloworld.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.example.helloworld.domain.Person;

import android.util.Log;
import android.util.Xml;


/**
 *  π”√ Pull Ω‚Œˆ xml
 */

public class PULLPersonService {
	
	private static final String TAG = "PULLPersonService";
	
	public List<Person> getPersons(InputStream is) throws XmlPullParserException, IOException {
		List<Person> res = null;
		Person person = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "utf-8");
		
		int eventType = parser.getEventType();
		
		while(eventType != XmlPullParser.END_DOCUMENT) {
			
			Log.i(TAG, eventType + "");
			
			switch(eventType) {
			case XmlPullParser.START_DOCUMENT:
				res = new ArrayList<Person>();
				break;
			case XmlPullParser.START_TAG:
				String sname = parser.getName();
				if("person".equals(sname)) {
					person = new Person();
					person.setId(parser.getAttributeValue("", "id"));
				}
				if(person != null) {
					if("name".equals(sname)) {
						person.setName(parser.nextText());
					}
					if("age".equals(sname)) {
						person.setAge(parser.nextText());
					}
				}
				
				break;
			case XmlPullParser.END_TAG:
				String ename = parser.getName();
				if("person".equals(ename)) {
					res.add(person);
					person = null;
				}
				break;
			default:
				break;
			}
			eventType = parser.next();
		}
		
		return res;
	}
	
	public boolean writeToXml(List<Person> persons, OutputStream os) throws IllegalArgumentException, IllegalStateException, IOException {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(os, "utf-8");
		serializer.startDocument("utf-8", true);
		
		serializer.startTag(null, "persons");
		for(Person p : persons) {
			serializer.startTag(null, "person");
			serializer.attribute(null, "id", p.getId());
			
			serializer.startTag(null, "name");
			serializer.text(p.getName());
			serializer.endTag(null, "name");
			
			serializer.startTag(null, "age");
			serializer.text(p.getAge());
			serializer.endTag(null, "age");
			
			serializer.endTag(null, "person");
		}
		serializer.endTag(null, "persons");
		
		serializer.endDocument();
		return true;
	}
	
	public boolean writeToXml(List<Person> persons, Writer writer) throws IllegalArgumentException, IllegalStateException, IOException {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startDocument("utf-8", true);
		
		serializer.startTag(null, "persons");
		for(Person p : persons) {
			serializer.startTag(null, "person");
			serializer.attribute(null, "id", p.getId());
			
			serializer.startTag(null, "name");
			serializer.text(p.getName());
			serializer.endTag(null, "name");
			
			serializer.startTag(null, "age");
			serializer.text(p.getAge());
			serializer.endTag(null, "age");
			
			serializer.endTag(null, "person");
		}
		serializer.endTag(null, "persons");
		
		serializer.endDocument();
		return true;
	}
}
