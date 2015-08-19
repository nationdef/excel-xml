package com.jfgw.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jfgw.model.DSModel;

public class GetDataFromXml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<DSModel> resList = new ArrayList<DSModel>();
		SAXReader reader = new SAXReader();             
	       try {
			Document   document = reader.read(new File("D:\\lt0813\\lt0813.xml"));
			
			Element root = document.getRootElement(); 
			List nodes = root.elements("ROW");
			 for (Iterator it = nodes.iterator(); it.hasNext();) {  
			      Element elm = (Element) it.next();
			      DSModel model = new DSModel();
			      for(Iterator it1=elm.elementIterator();it1.hasNext();){
			          Element element = (Element) it1.next();     
			         System.out.println(element.getName()+"---"+ element.getStringValue());
			         if(element.getName().endsWith("PHONE_NUMBER")){
			        	 model.setPhoneNum(element.getStringValue());
			         }
			         if(element.getName().endsWith("BEGIN_TIME")){
			        	 model.setBegTime(element.getStringValue());
			         }
			         if(element.getName().endsWith("END_TIME")){
			        	 model.setEndTime(element.getStringValue());
			         }
			         if(element.getName().endsWith("RESULT")){
			        	 model.setResult(element.getStringValue());
			         }
			   }  
			 }  
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	public List<DSModel> getDate(String filePath){
		List<DSModel> resList = new ArrayList<DSModel>();
		
		
		SAXReader reader = new SAXReader();             
	       try {
			Document   document = reader.read(new File(filePath));
			
			Element root = document.getRootElement(); 
			List nodes = root.elements("ROW");
			 for (Iterator it = nodes.iterator(); it.hasNext();) {  
			      Element elm = (Element) it.next();
			      DSModel model = new DSModel();
			      for(Iterator it1=elm.elementIterator();it1.hasNext();){
			          Element element = (Element) it1.next();     
			         //System.out.println(element.getName()+"---"+ element.getStringValue());
			         if(element.getName().endsWith("PHONE_NUMBER")){
			        	 model.setPhoneNum(element.getStringValue());
			         }
			         if(element.getName().endsWith("BEGIN_TIME")){
			        	 model.setBegTime(element.getStringValue());
			         }
			         if(element.getName().endsWith("END_TIME")){
			        	 model.setEndTime(element.getStringValue());
			         }
			         if(element.getName().endsWith("RESULT")){
			        	 model.setResult(element.getStringValue());
			         }
			   }  
			      
			      //System.out.println(model.getPhoneNum() + model.getResult());
			   
			      resList.add(model);
			 }  
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return resList;
	}

}
