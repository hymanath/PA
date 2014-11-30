package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MobileNumbersExtractor {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.1.219:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	@SuppressWarnings("resource")
	public void getMobileNumbers()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		List<Integer> cIdsList = new ArrayList<Integer>(0);
    		BufferedWriter outwriter1 = new BufferedWriter(new FileWriter("H:\\KOTI\\MAN2000_1.txt"));
    		BufferedWriter outwriter2 = new BufferedWriter(new FileWriter("H:\\KOTI\\MAN2000_2.txt"));
    		BufferedWriter outwriter3 = new BufferedWriter(new FileWriter("H:\\KOTI\\MAN2000_3.txt"));
    		BufferedWriter outwriter4 = new BufferedWriter(new FileWriter("H:\\KOTI\\MAN2000_4.txt"));
    		
    		StringBuilder sb1 = new StringBuilder();
    		StringBuilder sb2 = new StringBuilder();
    		StringBuilder sb3 = new StringBuilder();
    		StringBuilder sb4 = new StringBuilder();
    		
    		ResultSet rs = stmt.executeQuery("select constituency_id from constituency where district_id BETWEEN 21 and 23 and " +
    				" election_scope_id = 2 and deform_date is null");
    		
    		 while(rs.next())
    			 cIdsList.add(rs.getInt("constituency_id"));
    		 
    		 for(Integer cid : cIdsList)
    		 {
    			 System.out.println("constituency -->"+cid);
    			 
    			 rs = stmt.executeQuery("select area_type from constituency where constituency_id = "+cid);

    			 String areaType = "";
    			 
    			 while(rs.next())
    				  areaType = rs.getString("area_type");
    			 
    			 if(areaType.equalsIgnoreCase("URBAN"))
    			 {
    				 rs = stmt.executeQuery("select DISTINCT(MN.mobile_no) from ivr_mobile MN,constituency C,district D where MN.constituency_id = C.constituency_id and C.district_id = D.district_id and " +
 	    			 		" MN.Constituency_ID = "+cid+" and" +
 	    			 		" MN.mobile_no IS NOT NULL AND LENGTH(MN.mobile_no) = 10 AND MN.mobile_no <> '9999999999' " +
 	    			 		" AND MN.mobile_no not in (select mob from mob_tmp) order by rand() limit 8000");
    				 
    				 int index = 0; 
    				 while(rs.next())
    	   			 {
    					 if(index < 2000)
    						 sb1.append(rs.getString(1)+"\t0\t"+cid+"\n"); 
    					 else if(index < 4000)
    						 sb2.append(rs.getString(1)+"\t0\t"+cid+"\n"); 
    					 else if(index < 6000)
    						 sb3.append(rs.getString(1)+"\t0\t"+cid+"\n"); 
    					 else if(index < 8000)
    						 sb4.append(rs.getString(1)+"\t0\t"+cid+"\n");
    					 index++;
    	   			 }
    			 }
    			 else
    			 {
    				 rs = stmt.executeQuery("select tehsil_id from constituency_tehsil where constituency_id = "+cid);
    				 int tIndex = 0;
    				 
    				 int t1c = 0;
    				 int t2c = 0;
    				 int t3c = 0;
    				 int t4c = 0;

    				 List<Integer> tehsilList = new ArrayList<Integer>(0);
    				 while(rs.next())
    				 {
    					 tehsilList.add(rs.getInt("tehsil_id"));
    				 }
    				 
    				 for(Integer tehsilId : tehsilList)
    				 {
    					 tIndex++;
    					 System.out.println("Tehsil_id -->"+tehsilId);
    					 if(tIndex < 5)
    					 {
	    					 rs = stmt.executeQuery("select DISTINCT(MN.mobile_no) from ivr_mobile MN,constituency C,district D where MN.constituency_id = C.constituency_id and C.district_id = D.district_id and " +
	    	 	    			 		" MN.Constituency_ID = "+cid+" and MN.tehsil_id = "+tehsilId+" and "+
	    	 	    			 		" MN.mobile_no IS NOT NULL AND LENGTH(MN.mobile_no) = 10 AND MN.mobile_no <> '9999999999' " +
	    	 	    			 		" AND MN.mobile_no not in (select mob from mob_tmp) order by rand() limit 2000");
	    					 while(rs.next())
	    					 {
		    					 if(tIndex == 1)
		    					 {
		    						 sb1.append(rs.getString(1)+"\t"+tehsilId+"\t"+cid+"\n");
		    						 t1c++;
		    					 }
		    					 else if(tIndex == 2)
		    					 {
		    						 sb2.append(rs.getString(1)+"\t"+tehsilId+"\t"+cid+"\n");
		    						 t2c++;
		    					 }
		    					 else if(tIndex == 3)
		    					 {
		    						 sb3.append(rs.getString(1)+"\t"+tehsilId+"\t"+cid+"\n");
		    						 t3c++;
		    					 }
		    					 else if(tIndex == 4)
		    					 {
		    						 sb4.append(rs.getString(1)+"\t"+tehsilId+"\t"+cid+"\n");
		    						 t4c++;
		    					 }
	    					 }
    					 }
    				 }
    				 int ttc = t1c+t2c+t3c+t4c;
    				 if(ttc < 8000)
    				 {
    					 Integer limit = 8000 - ttc;
    					 rs = stmt.executeQuery("select DISTINCT(MN.mobile_no) from ivr_mobile MN,constituency C,district D where MN.constituency_id = C.constituency_id and C.district_id = D.district_id and " +
 	 	    			 		" MN.Constituency_ID = "+cid+" and MN.tehsil_id is null and "+
 	 	    			 		" MN.mobile_no IS NOT NULL AND LENGTH(MN.mobile_no) = 10 AND MN.mobile_no <> '9999999999' " +
 	 	    			 		" AND MN.mobile_no not in (select mob from mob_tmp) order by rand() limit "+limit.toString());
    					 
    					 while(rs.next())
        				 {
    						 if(t1c < 2000)
    						 {
    							 sb1.append(rs.getString(1)+"\t0\t"+cid+"\n");
        						 t1c++;
    						 }
    						 else if(t2c < 2000)
    						 {
    							 sb2.append(rs.getString(1)+"\t0\t"+cid+"\n");
        						 t2c++;
    						 }
    						 else if(t3c < 2000)
    						 {
    							 sb3.append(rs.getString(1)+"\t0\t"+cid+"\n");
        						 t3c++;
    						 }
    						 else if(t4c < 2000)
    						 {
    							 sb4.append(rs.getString(1)+"\t0\t"+cid+"\n");
        						 t4c++;
    						 }
        				 }
    				 }
    				 
    			 }
    		 }
    			 
    		 outwriter1.write(sb1.toString());
			 outwriter1.close();
			 outwriter2.write(sb2.toString());
			 outwriter2.close();
			 outwriter3.write(sb3.toString());
			 outwriter3.close();
			 outwriter4.write(sb4.toString());
			 outwriter4.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getTehsilWiseMobileNumbers(String fileName,int count)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		List<Integer> cIdsList = new ArrayList<Integer>(0);
    		
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\MANDAL\\"+fileName+".txt"));
    		
    		StringBuilder sb = new StringBuilder();
    		
    		ResultSet rs = stmt.executeQuery("select constituency_id from constituency where district_id BETWEEN 11 and 23 and " +
    				" election_scope_id = 2 and deform_date is null and constituency_id not in(108,195,196,260,291,304,308,311,312,331,340,354,355,356,357,358)");
    		
    		 while(rs.next())
    			 cIdsList.add(rs.getInt("constituency_id"));
    		 
    		 for(Integer cid : cIdsList)
    		 {
    			 try{
    			 System.out.println("constituency -->"+cid);
    			 
    			 int total = 0;
    			 int maxCount = 0;
    			 StringBuilder sbTemp = new StringBuilder();
    			 
    			/* rs = stmt.executeQuery("SELECT COUNT(DISTINCT MN.mobile_number) from mobile_numbers MN where MN.is_deleted = 'N' and MN.is_used = 'N' " +
	    			 		" AND MN.constituency_id = "+cid+" and MN.mobile_number <> '9999999999' and MN.tehsil_id IS NULL");
    			 
    			 while(rs.next())
    				 total = rs.getInt(1); 
    			 
    			 maxCount = total/count;*/
    			 maxCount = 230;
    			
    			 rs = stmt.executeQuery("SELECT DISTINCT MN.mobile_number from mobile_numbers MN where MN.is_deleted = 'N' and MN.is_used = 'N' " +
	    			 		" AND MN.constituency_id = "+cid+" and MN.mobile_number <> '9999999999' and MN.tehsil_id IS NULL" +
	    			 				" order by rand() limit "+maxCount);
    			 
    			 while(rs.next())
    			 {
    				 try{
    				 sb.append(rs.getString(1)+"\n");
    				 sbTemp.append("'"+rs.getString(1)+"',");
    				 }catch(Exception e)
    				 {
    					 e.printStackTrace();
    				 }
    			 }
    			 System.out.println("Going to update -->");
    			 /*
    			 int updated = stmt.executeUpdate("UPDATE mobile_numbers SET is_used = 'Y' where is_deleted = 'N' and constituency_id = "+cid+" and " +
    			 		" mobile_number in ("+sbTemp.toString().substring(0,sbTemp.toString().length()-1)+")");
    			 
    			 System.out.println("Updated Records -->"+updated); */
    			 }
    			 catch(Exception e)
    			 {
    				 e.printStackTrace();
    			 }
    		 }
    		outwriter.write(sb.toString());
 			outwriter.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getMandalWiseMobileNumbers(String fileName,int count)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		List<Integer> cIdsList = new ArrayList<Integer>(0);
    		
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\IVR_MANDAL\\"+fileName+".txt"));
    		
    		StringBuilder sb = new StringBuilder();
    		
    		ResultSet rs = stmt.executeQuery("select DISTINCT tehsil_id from mobile_numbers MN WHERE MN.is_deleted = 'N' AND MN.tehsil_id is NOT null AND MN.district_id BETWEEN 11 AND 13 order by MN.tehsil_id ");
    		
    		 while(rs.next())
    			 cIdsList.add(rs.getInt("tehsil_id"));
    		 
    		 int index = 0;
    		 for(Integer cid : cIdsList)
    		 {
    			 try{
    			 System.out.println(++index+" tehsil -->"+cid);
    			 
    			 rs = stmt.executeQuery("SELECT M.mobile_number,D.district_name,C.name,T.tehsil_name FROM mobile_numbers M,constituency C, tehsil T,district D WHERE "+
    					 				" M.constituency_id = C.constituency_id AND T.tehsil_id = M.tehsil_id AND M.district_id = D.district_id AND "+
    					 				" M.tehsil_id = "+cid+" ORDER BY M.mobile_numbers_id LIMIT 20300,130000");
    			 while(rs.next())
    			 {
    				 try{
    				 sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n");
    				 }catch(Exception e)
    				 {
    					 e.printStackTrace();
    				 }
    			 }
    			 }
    			 catch(Exception e)
    			 {
    				 e.printStackTrace();
    			 }
    		 }
    		outwriter.write(sb.toString());
 			outwriter.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getDistrictWiseMobileNumbers()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		List<Integer> cIdsList = new ArrayList<Integer>(0);
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\TG7.txt"));
    		StringBuilder sb = new StringBuilder();
    		
    		ResultSet rs = stmt.executeQuery("select district_id from district where district_id in(5,10)");
    		
    		 while(rs.next())
    			 cIdsList.add(rs.getInt("district_id"));
    		 
    		 for(Integer cid : cIdsList)
    		 {
    			 System.out.println("District -->"+cid);
    			 try{
    			 rs = stmt.executeQuery("select DISTINCT(MN.Mobile_Numbers) from mobile_numbers MN where " +
    			 		" MN.district_id = "+cid+" and" +
    			 		" MN.Mobile_Numbers IS NOT NULL AND LENGTH(MN.Mobile_Numbers) = 10 AND MN.Mobile_Numbers <> '9999999999' limit 50000,10000");
    			 while(rs.next())
    			 {
    				 try{
    				 sb.append(rs.getString(1)+"\n");
    				 }catch(Exception e)
    				 {
    					 e.printStackTrace();
    				 }
    			 }
    			 
    			 }catch(Exception e)
    			 {
    				 e.printStackTrace();
    			 }
    		 }
    		 outwriter.write(sb.toString());
			 outwriter.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getPanchayatWiseMobileNumbers()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		File file = new File("G:\\Downloads\\panchayat_ids.txt"); 
    		BufferedReader br = new BufferedReader(new FileReader(file));
    		StringBuilder sb = new StringBuilder();
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\11_15P.txt"));
    		
			String pId = null;
			List<String> pidList = new ArrayList<String>(0); 
			while((pId = br.readLine()) != null)
			{
				pidList.add(pId);
			}
			
			System.out.println(pidList.size());
			for(String panchayatId : pidList)
			{
				ResultSet rs = stmt.executeQuery("select DISTINCT mobile_number,C.name,D.district_name,T.tehsil_name,P.panchayat_name from mobile_numbers MN,constituency C,district D,tehsil T,panchayat P " +
						" where P.panchayat_id = MN.panchayat_id and MN.district_id = D.district_id and MN.tehsil_id = T.tehsil_id and MN.constituency_id = C.constituency_id and" +
						" MN.district_id  between 11 and 15 and P.panchayat_id = "+panchayatId+" order by rand() limit 10");
				while(rs.next()){
					sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\n");
				}
				
			}
			 outwriter.write(sb.toString());
			 outwriter.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void getconstituencyWiseMobileNumbers(String path,int ind)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		StringBuilder sb = new StringBuilder();
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\"+path));
    		
    		ResultSet rs = stmt.executeQuery("select constituency_id from constituency where election_scope_id = 2 and deform_date is null and district_id  between 11 and 23");
    		
			List<Integer> constituencyIdsList = new ArrayList<Integer>(0); 
			while(rs.next())
				constituencyIdsList.add(rs.getInt(1));
			
			System.out.println(constituencyIdsList.size());
			int index = 0;
			for(Integer constituencyId : constituencyIdsList)
			{
				System.out.println(++index+") constituency -- "+constituencyId);
				rs = stmt.executeQuery("select DISTINCT mobile_number,C.name,D.district_name from mobile_numbers MN,constituency C,district D " +
						" where C.district_id = D.district_id and MN.constituency_id = C.constituency_id and " +
						" MN.constituency_id = "+constituencyId+" order by rand() limit "+ind+",1000");
				while(rs.next()){
					sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\n");
				}
				
			}
			 outwriter.write(sb.toString());
			 outwriter.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void getconstituencyWiseCadreMobileNumbers(String path,int ind)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		StringBuilder sb = new StringBuilder();
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\"+path));
    		
    		ResultSet rs = stmt.executeQuery("select constituency_id from constituency where election_scope_id = 2 and deform_date is null and district_id  between 11 and 23");
    		
			List<Integer> constituencyIdsList = new ArrayList<Integer>(0); 
			while(rs.next())
				constituencyIdsList.add(rs.getInt(1));
			
			System.out.println(constituencyIdsList.size());
			int index = 0;
			for(Integer constituencyId : constituencyIdsList)
			{
				System.out.println(++index+") constituency -- "+constituencyId);
				rs = stmt.executeQuery("SELECT DISTINCT TC.mobile_no,C.name,D.district_name from tdp_cadre TC,user_address UA,constituency C,district D "+
						" where TC.address_id = UA.user_address_id AND UA.constituency_id = C.constituency_id AND C.district_id = D.district_id AND " +
						" C.constituency_id = "+constituencyId+" AND TC.mobile_no IS NOT NULL AND TC.mobile_no <> '9999999999' AND LENGTH(TC.mobile_no) = 10 ORDER BY RAND() LIMIT "+ind+",100 ");
				while(rs.next()){
					sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\n");
				}
				
			}
			 outwriter.write(sb.toString());
			 outwriter.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)
	{
		MobileNumbersExtractor extractor = new MobileNumbersExtractor();
		extractor.getconstituencyWiseMobileNumbers("Constituency_1000.txt",0);
	}
}
