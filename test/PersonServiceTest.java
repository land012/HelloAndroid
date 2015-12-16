

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;







import com.example.helloworld.domain.Person;
import com.example.helloworld.xmlparser.DOMPersonService;
import com.example.helloworld.xmlparser.PULLPersonService;
import com.example.helloworld.xmlparser.SAXPersonService;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase {
	
	private static final String TAG = "PersonServiceTest";
	
	public void testGetPersons() throws ParserConfigurationException, SAXException, IOException {
		SAXPersonService service = new SAXPersonService();
		List<Person> list = service.getPersons(this.getClass().getClassLoader().getResourceAsStream("persons.xml"));
		if(list == null) {
			Log.i(TAG, "list is null");
		} else {
			Log.i(TAG, "list.size()=" + list.size());
		}
		for(Person p : list) {
			Log.i(TAG, p.toString());
		}
	}
	
	public void testGetPersonsByDOM() throws ParserConfigurationException, SAXException, IOException {
		DOMPersonService service = new DOMPersonService();
		List<Person> list = service.getPersons(this.getClass().getClassLoader().getResourceAsStream("persons.xml"));
		if(list == null) {
			Log.i(TAG, "list is null");
		} else {
			Log.i(TAG, "list.size()=" + list.size());
		}
		for(Person p : list) {
			Log.i(TAG, p.toString());
		}
	}
	
	public void testGetPersonsByPULL() throws XmlPullParserException, IOException {
		PULLPersonService service = new PULLPersonService();
		List<Person> list = service.getPersons(this.getClass().getClassLoader().getResourceAsStream("persons.xml"));
		if(list == null) {
			Log.i(TAG, "list is null");
		} else {
			Log.i(TAG, "list.size()=" + list.size());
		}
		for(Person p : list) {
			Log.i(TAG, p.toString());
		}
	}
	
	/**
	 * 使用 OutputStream 写到文件
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void testPULLSave() throws IllegalArgumentException, IllegalStateException, FileNotFoundException, IOException {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("1", "tom", "18"));
		persons.add(new Person("2", "jim", "19"));
		
		PULLPersonService service = new PULLPersonService();
		service.writeToXml(persons, this.getContext().openFileOutput("persons.xml", Context.MODE_PRIVATE));
	}
	
	/**
	 * 使用 Writer 写到文件
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void testPULLSave2() throws IllegalArgumentException, IllegalStateException, FileNotFoundException, IOException {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("1", "tom", "18"));
		persons.add(new Person("2", "jim", "19"));
		
		PULLPersonService service = new PULLPersonService();
		OutputStreamWriter writer = new OutputStreamWriter(this.getContext().openFileOutput("persons.xml", Context.MODE_PRIVATE), "utf-8");
		BufferedWriter bWriter = new BufferedWriter(writer);
		service.writeToXml(persons, bWriter);
	}
	
	/**
	 * 使用 Writer 写到内存中
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void testPULLSave3() throws IllegalArgumentException, IllegalStateException, FileNotFoundException, IOException {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("1", "tom", "18"));
		persons.add(new Person("2", "jim", "19"));
		
		PULLPersonService service = new PULLPersonService();
		StringWriter writer = new StringWriter();
		service.writeToXml(persons, writer);
		Log.i(TAG, writer.toString());
	}
}
