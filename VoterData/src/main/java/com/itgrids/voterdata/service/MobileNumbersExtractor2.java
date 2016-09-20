package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MobileNumbersExtractor2 {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.3.56:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public void getconstituencyWiseCadreMobileNumbers()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		StringBuilder sb1 = new StringBuilder();
    		StringBuilder sb2 = new StringBuilder();
    		StringBuilder sb3 = new StringBuilder();
    		StringBuilder sb4 = new StringBuilder();
    		StringBuilder sb5 = new StringBuilder();
    		StringBuilder sb6 = new StringBuilder();
    		
    		BufferedWriter outwriter1 = new BufferedWriter(new FileWriter("E:\\MN\\1.txt"));
    		BufferedWriter outwriter2 = new BufferedWriter(new FileWriter("E:\\MN\\2.txt"));
    		BufferedWriter outwriter3 = new BufferedWriter(new FileWriter("E:\\MN\\3.txt"));
    		BufferedWriter outwriter4 = new BufferedWriter(new FileWriter("E:\\MN\\4.txt"));
    		BufferedWriter outwriter5 = new BufferedWriter(new FileWriter("E:\\MN\\5.txt"));
    		BufferedWriter outwriter6 = new BufferedWriter(new FileWriter("E:\\MN\\6.txt"));
    		
    		ResultSet rs = stmt.executeQuery("select constituency_id from constituency where election_scope_id = 2 and deform_date is null and district_id between 11 and 23");
    		
			List<Integer> constituencyIdsList = new ArrayList<Integer>(0); 
			while(rs.next())
				constituencyIdsList.add(rs.getInt(1));
			
			System.out.println(constituencyIdsList.size());
			int index = 0;
			
			for(Integer constituencyId : constituencyIdsList)
			{
				try{
				List<String> femaleMobilesList = new ArrayList<String>(0);
				List<String> maleMobilesList = new ArrayList<String>(0);
				
				System.out.println(++index+") constituency -- "+constituencyId);
				rs = stmt.executeQuery("SELECT DISTINCT TC.mobile_no,D.district_name,C.name,T.tehsil_name,L.name FROM constituency C,district D,tdp_cadre TC,user_address UA " +
						" LEFT OUTER JOIN tehsil T ON UA.tehsil_id = T.tehsil_id LEFT OUTER JOIN local_election_body L ON UA.local_election_body = L.local_election_body_id " +
						" WHERE TC.address_id = UA.user_address_id AND UA.constituency_id = C.constituency_id AND C.district_id = D.district_id AND TC.is_deleted = 'N' AND " +
						" TC.enrollment_year = 2014 AND TC.mobile_no IS NOT NULL AND LENGTH(LTRIM(RTRIM(TC.mobile_no))) = 10 AND TC.gender = 'F' AND UA.constituency_id = "+constituencyId+" AND " +
						" TC.is_invalid_mobile_no = 'N' AND TC.mobile_no NOT IN ( SELECT DISTINCT TC2.mobile_no FROM tdp_cadre TC2,user_address UA2 WHERE TC2.address_id = UA2.user_address_id AND " +
						" TC2.is_deleted = 'N' AND TC2.enrollment_year = 2014 AND TC2.gender = 'M' AND TC2.mobile_no IS NOT NULL AND LENGTH(LTRIM(RTRIM(TC2.mobile_no))) = 10 AND " +
						" UA2.constituency_id = "+constituencyId+")ORDER BY RAND() LIMIT 3000");
				
				while(rs.next())
				{
					String muncipality = rs.getString(5);
					String mandal = rs.getString(4);
					String str = "";
					if(muncipality != null)
						mandal = muncipality + " Muncipality";
						
					str = rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+mandal+"\t"+"Female";
					femaleMobilesList.add(str);
				}
				
				int maleReqCount = 6006-femaleMobilesList.size();
				
				rs = stmt.executeQuery("SELECT DISTINCT TC.mobile_no,D.district_name,C.name,T.tehsil_name,L.name FROM constituency C,district D,tdp_cadre TC,user_address UA " +
						" LEFT OUTER JOIN tehsil T ON UA.tehsil_id = T.tehsil_id LEFT OUTER JOIN local_election_body L ON UA.local_election_body = L.local_election_body_id WHERE " +
						" TC.address_id = UA.user_address_id AND UA.constituency_id = C.constituency_id AND C.district_id = D.district_id AND TC.is_deleted = 'N' AND TC.enrollment_year = 2014 AND " +
						" TC.mobile_no IS NOT NULL AND LENGTH(LTRIM(RTRIM(TC.mobile_no))) = 10 AND TC.gender = 'M' AND UA.constituency_id = "+constituencyId+" AND TC.is_invalid_mobile_no = 'N' " +
						" ORDER BY RAND() LIMIT "+maleReqCount);
				
				while(rs.next())
				{
					String muncipality = rs.getString(5);
					String mandal = rs.getString(4);
					String str = "";
					if(muncipality != null)
						mandal = muncipality + " Muncipality";
						
					str = rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+mandal+"\t"+"Male";
					maleMobilesList.add(str);
				}
				
				System.out.println("Female Count - "+femaleMobilesList.size()+" -- Male Count - "+maleMobilesList.size());
				
				int fs = femaleMobilesList.size()/6;
				int ms = maleMobilesList.size()/6;
				
				for(int i=0;i<fs;i++)
					sb1.append(femaleMobilesList.get(i)+"\n");
				for(int i=0;i<ms;i++)
					sb1.append(maleMobilesList.get(i)+"\n");
				
				for(int i=fs;i<2*fs;i++)
					sb2.append(femaleMobilesList.get(i)+"\n");
				for(int i=ms;i<2*ms;i++)
					sb2.append(maleMobilesList.get(i)+"\n");
				
				for(int i=2*fs;i<3*fs;i++)
					sb3.append(femaleMobilesList.get(i)+"\n");
				for(int i=2*ms;i<3*ms;i++)
					sb3.append(maleMobilesList.get(i)+"\n");
				
				for(int i=3*fs;i<4*fs;i++)
					sb4.append(femaleMobilesList.get(i)+"\n");
				for(int i=3*ms;i<4*ms;i++)
					sb4.append(maleMobilesList.get(i)+"\n");
				
				for(int i=4*fs;i<5*fs;i++)
					sb5.append(femaleMobilesList.get(i)+"\n");
				for(int i=4*ms;i<5*ms;i++)
					sb5.append(maleMobilesList.get(i)+"\n");
				
				for(int i=5*fs;i<6*fs;i++)
					sb6.append(femaleMobilesList.get(i)+"\n");
				for(int i=5*ms;i<6*ms;i++)
					sb6.append(maleMobilesList.get(i)+"\n");
				
			}catch(Exception e){
				e.printStackTrace();
			}
			}
			 outwriter1.write(sb1.toString());
			 outwriter2.write(sb1.toString());
			 outwriter3.write(sb1.toString());
			 outwriter4.write(sb1.toString());
			 outwriter5.write(sb1.toString());
			 outwriter6.write(sb1.toString());
			 outwriter1.close();
			 outwriter2.close();
			 outwriter3.close();
			 outwriter4.close();
			 outwriter5.close();
			 outwriter6.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)
	{
		MobileNumbersExtractor2 extractor = new MobileNumbersExtractor2();
		extractor.getconstituencyWiseCadreMobileNumbers();
	}
}
