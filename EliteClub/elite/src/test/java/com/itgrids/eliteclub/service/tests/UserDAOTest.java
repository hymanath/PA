package com.itgrids.eliteclub.service.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.model.User;
import com.mysql.jdbc.Buffer;


@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/app-config.xml"
@ContextConfiguration("/applicationContext.xml")
@ActiveProfiles("dev")
@Transactional
public class UserDAOTest {
	Logger log=LogManager.getLogger();	
    @Autowired
	private UserDAO userDAOImpl;
    
  /*  @Rollback(true)
    @Test*/
   
    public void testSave()
    {
    	log.debug("hello");
    	System.out.println(userDAOImpl);
    	
    	User user =new User();
    	//User  us=userDAOImpl.get(1);
    	User u=new User("anil", "a@gmail.com", "95054854444", "56482", null, null);
    	//userDAOImpl.saveOrUpdate(u);
    	User  us=userDAOImpl.save(u);
       // userDAOImpl.saveOrUpdate(new User("anil", "a@gmail.com", "9505485444", "56482", null, null));
    	System.out.println(us.getUserId());
    	
    	
    }
    @Test
    public void testGetUserByIMEINumber()
    {
    	log.debug(" here test case for  getting user by imei");
    User u=	userDAOImpl.getUserByIMEINumber("860070024338231");
    System.out.println(u.getUserId());
    	
    }
    public static void main(String[] args) throws IOException {
		List<Integer> al =new ArrayList<Integer>();
    	for(int i=1;i<=150;i++)
    	{
    		try {
    		main(i);
    		}catch (Exception e) {
    			e.printStackTrace();
    			al.add(i);
			}
    	}
    	for (Integer integer : al) {
			System.out.println(integer);
		}
	}
    
    public static void main(int j) throws IOException {
		
    	
    	//String url="http://affidavitarchive.nic.in/DynamicAffidavitDisplay/CANDIDATEAFFIDAVIT.aspx?YEARID=May-2014 ( GEN )&AC_No=84&st_code=S01&constType=AC";
   	 /* String k="0";
    	if(j>=100)
   		   k=0+""+(j-100);
    	else*/
    		String k=j+"";
    		if(k.length()>2)
    			k=k.substring(1, k.length());
    		
    	String url="http://eciresults.ap.nic.in/ConstituencywiseS121"+k+".htm?ac="+j;
   	    System.out.println(url);
   	    InputStream in = UserDAOTest.class.getClassLoader().getResourceAsStream("electionresults2014.properties");
    	Properties prop = new  Properties();
    	prop.load(in);
    	
    	String url1=(String) prop.get("url");    	
    	System.out.println(url1);
    	System.out.println( prop.get("stateNo"));
    	System.out.println( prop.get("electionId"));
    	
   	  
   	 
   	  
   	  HttpClient client = null;
    
         GetMethod get=null;
    		client = new HttpClient(new MultiThreadedHttpConnectionManager());

    		client.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt("30000"));
    		get=new GetMethod(url);
    		
    		try {
    		int status=	client.executeMethod(get);
    		System.out.println(status);
    		}catch (Exception e) {
    			e.printStackTrace();
			}
    		
    		//System.out.println(get.getResponseBodyAsString());
    		
    		//String sb=get.getResponseBodyAsString();
    		
    	InputStream fis=	get.getResponseBodyAsStream();
    	BufferedReader br= new BufferedReader(new InputStreamReader(fis));
    	StringBuffer sb =new StringBuffer();
    	
    	String line = br.readLine();

        while (line != null) {
            sb.append(line);
         
            line = br.readLine();
        }
    	
    		
    		get=null;
    		
    		 String patern="<div id=\"div1\"[\\s\\S]*?<table.*?>(<tr.*?>(<td.*?>.*</td>)*</tr>)</table>"; 
    		 String pattern2="(.*)<td.*?>([\\s\\S]+?)</td>(.*)";
    		 
    		 Pattern pat =    Pattern.compile(patern, Pattern.CASE_INSENSITIVE);
 			Matcher matcher=pat.matcher(sb);
 			String str=null;
 			 while (matcher.find()) {
 			   System.out.println("inside matcher==================");
 			
 			 //  System.out.println(matcher.group(0).toString());
 		//	System.out.println("===="+  matcher.group(1).toString());
 			//System.out.println("======="+matcher.group(2).toString());
 		//	System.out.println("======="+matcher.group(3).toString());
 				// System.out.println(matcher.group(3));
 			str=matcher.group(2).toString();
 			    }
 			 if(str==null)
 				 return;
 			  Pattern pat1 =    Pattern.compile(pattern2, Pattern.CASE_INSENSITIVE);
 				Matcher matcher1=pat1.matcher(str);
 				
 				String[] strArray=str.split("<td.*?>");
 				
 				
 				System.out.println(strArray);
 				
 				 int flagCount=0;
 				for(int i=3;i<strArray.length;i++)
 				{
 					if(flagCount<=2) {
 						System.out.print("======="+strArray[i].toString());
 						
 						}
 						else {
 							System.out.println();
 							flagCount=-1;
 						}
 						flagCount++;
 				}
 				//System.exit(0);
 				
 				
 				/*int count=1;
 				
 				 int flagCount=0;
 				System.out.println(str);
 				 while (matcher1.find()) {
 			
 					  System.out.println("inside matcher1==================");
 					if(count>2)
 					{
 						if(flagCount<=2) {
 						System.out.print("======="+matcher1.group(1).toString());
 						
 						}
 						else {
 							System.out.println();
 							flagCount=0;
 						}
 						flagCount++;
 					}
 					count++;
 					    }*/
 				 System.out.println();
    
    }
    
    
}
