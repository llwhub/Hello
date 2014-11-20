package com.llw.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class XmlActivity extends Activity implements OnClickListener {
   
	private static final String BOOKS_PATH = "/sdcard/books.xml";
	private Button mButton1,mButton2,mButton3;
	private TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        setupViews();
    }
    //��ʼ������
    private void setupViews(){
    	mTextView = (TextView)findViewById(R.id.result);
    	mButton1 = (Button)findViewById(R.id.btn1);
    	mButton2 = (Button)findViewById(R.id.btn2);
    	mButton3 = (Button)findViewById(R.id.btn3);
    	mButton1.setOnClickListener(this);
    	mButton2.setOnClickListener(this);
    	mButton3.setOnClickListener(this);
    }
    //����xml�ļ�
    private void createXmlFile(){
    	File linceseFile = new File(BOOKS_PATH);
    	try{
    		linceseFile.createNewFile();
    	}catch (IOException e) {
    		Log.e("IOException", "exception in createNewFile() method");
		}
    	FileOutputStream fileos = null;
    	try{
    		fileos = new FileOutputStream(linceseFile);
    	}catch (FileNotFoundException  e) {
    		Log.e("FileNotFoundException", "can't create FileOutputStream");
		}
    	XmlSerializer serializer = Xml.newSerializer();
    	try {
            serializer.setOutput(fileos,"UTF-8");
            serializer.startDocument(null, true);
            serializer.startTag(null, "books");
            for(int i = 0; i < 3; i ++){
                serializer.startTag(null, "book");
                serializer.startTag(null, "bookname");
                serializer.text("Android�̳�" + i);
                serializer.endTag(null, "bookname");
                serializer.startTag(null, "bookauthor");
                serializer.text("Frankie" + i);
                serializer.endTag(null, "bookauthor");
                serializer.endTag(null, "book");
            }
        
            serializer.endTag(null, "books");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
        } catch (Exception e) {
        	Log.e("Exception","error occurred while creating xml file");
        }
        Toast.makeText(getApplicationContext(), "����xml�ļ��ɹ�!", Toast.LENGTH_SHORT).show();
    }
    //dom����xml�ļ�
    private void domParseXML(){
    	File file = new File(BOOKS_PATH);
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   	
    	DocumentBuilder db = null;	
    	try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = db.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element root = doc.getDocumentElement();
		NodeList books = root.getElementsByTagName("book");
		String res = "�������ͨ��dom����:" + "/n";
		for(int i = 0; i < books.getLength();i++){
			Element book = (Element)books.item(i);
			Element bookname = (Element)book.getElementsByTagName("bookname").item(0);
			Element bookauthor = (Element)book.getElementsByTagName("bookauthor").item(0);
			res += "����: " + bookname.getFirstChild().getNodeValue() + " " +
			       "����: " + bookauthor.getFirstChild().getNodeValue() + "/n";
		}
		
		mTextView.setText(res);
    }
  
    //xmlPullParser����xml�ļ�
    private void xmlPullParseXML(){
    	String res = "�������ͨ��XmlPullParse����:" + "/n";
    	try {    		
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
			
			xmlPullParser.setInput(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(BOOKS_PATH), "UTF-8");
			
			int eventType = xmlPullParser.getEventType();
			
			try{
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String nodeName = xmlPullParser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if("bookname".equals(nodeName)){	
							res += "����: " + xmlPullParser.nextText() + " ";					
						}else if("bookauthor".equals(nodeName)){
							res += "����: " + xmlPullParser.nextText() + "/n";
						}
						break;
					default:
						break;
					}
					eventType = xmlPullParser.next();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		
		mTextView.setText(res);
    }
    //��ť�¼���Ӧ
	public void onClick(View v) {
		if(v == mButton1){
			createXmlFile();
		}else if(v == mButton2){
			domParseXML();			
		}else if(v == mButton3){
			xmlPullParseXML();
		}
	}		
}