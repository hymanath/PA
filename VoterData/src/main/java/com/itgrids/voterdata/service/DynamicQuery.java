package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DynamicQuery {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.1.219:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public void getconstituencyWiseMobileNumbers()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		StringBuilder sb = new StringBuilder();
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\counts.txt"));
    		
    		ResultSet rs = stmt.executeQuery("select constituency_id from constituency where election_scope_id = 2 and deform_date is null");
    		
			List<Integer> constituencyIdsList = new ArrayList<Integer>(0); 
			while(rs.next())
				constituencyIdsList.add(rs.getInt(1));
			
			System.out.println(constituencyIdsList.size());
			int index = 0;
			for(Integer constituencyId : constituencyIdsList)
			{
				System.out.println(++index+") constituency -- "+constituencyId);
				rs = stmt.executeQuery("SELECT P.panchayat_id,D.district_name AS District,D.name_local AS District_t,C.name AS Constituency,C.name_local AS Constituency_t,T.tehsil_name AS Mandal,T.name_local as "+ 
						" Mandal_t,P.panchayat_name AS Panchayat,P.name_local AS Panchayat_t,DC.constituency_no AS AC_NO,COUNT(TC.tdp_cadre_id) AS CADRE_COUNT FROM voter V,booth_publication_voter BPV,booth B,constituency C,panchayat P,tehsil T,"+
						" delimitation_constituency DC,district D,tdp_cadre TC LEFT OUTER JOIN caste_state CS ON TC.caste_state_id = CS.caste_state_id LEFT OUTER JOIN caste CT ON CS.caste_id = CT.caste_id LEFT OUTER JOIN caste_category_group CCG ON CS.caste_category_group_id = CCG.caste_category_group_id "+ 
						" LEFT OUTER JOIN voter_names VN ON TC.voter_id = VN.voter_id WHERE TC.voter_id = V.voter_id AND V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND B.constituency_id = C.constituency_id AND B.publication_date_id = 11 AND B.panchayat_id = P.panchayat_id AND "+
						" P.tehsil_id = T.tehsil_id AND C.constituency_id = DC.constituency_id AND DC.`year` = 2009 AND C.district_id = D.district_id AND TC.enrollment_year = 2014 AND TC.is_deleted = 'N' AND TC.family_voterId IS NULL AND "+
						" C.constituency_id = "+constituencyId+" GROUP BY D.district_id,C.constituency_id,T.tehsil_id,P.panchayat_id ORDER BY D.district_id,C.`name`,T.tehsil_name,P.panchayat_name ");
				
				while(rs.next())
					sb.append(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(4)+"\t"+rs.getString(6)+"\t"+rs.getString(8)+"\t"+rs.getString(10)+"\t"+rs.getInt(11)+"\n");
					
					rs = stmt.executeQuery("SELECT P.panchayat_id,D.district_name AS District,D.name_local AS District_t,C.name AS Constituency,C.name_local AS Constituency_t,T.tehsil_name AS Mandal,T.name_local as " +
							" Mandal_t,P.panchayat_name AS Panchayat,P.name_local AS Panchayat_t,DC.constituency_no AS AC_NO,COUNT(TC.tdp_cadre_id) AS CADRE_COUNT " +
							" FROM constituency C,panchayat P,tehsil T,user_address UA,delimitation_constituency DC,district D,tdp_cadre TC LEFT OUTER JOIN caste_state CS ON TC.caste_state_id = CS.caste_state_id " +
							" LEFT OUTER JOIN caste CT ON CS.caste_id = CT.caste_id LEFT OUTER JOIN caste_category_group CCG ON CS.caste_category_group_id = CCG.caste_category_group_id LEFT OUTER JOIN tdp_cadre_telugu_names TN ON TC.tdp_cadre_id = TN.tdp_cadre_id " +
							" WHERE TC.address_id = UA.user_address_id AND UA.constituency_id = C.constituency_id AND UA.panchayat_id = P.panchayat_id AND P.tehsil_id = T.tehsil_id AND C.constituency_id = DC.constituency_id AND " +
							" DC.`year` = 2009 AND C.district_id = D.district_id AND TC.enrollment_year = 2014 AND TC.is_deleted = 'N' AND TC.family_voterId IS NOT NULL AND TC.voter_id IS NULL AND " +
							" C.constituency_id = "+constituencyId+" GROUP BY D.district_id,C.constituency_id,T.tehsil_id,P.panchayat_id ORDER BY  D.district_id,C.`name`,T.tehsil_name,P.panchayat_name ");
				
				while(rs.next())
					sb.append(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(4)+"\t"+rs.getString(6)+"\t"+rs.getString(8)+"\t"+rs.getString(10)+"\t"+rs.getInt(11)+"\n");
				
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
		DynamicQuery extractor = new DynamicQuery();
		extractor.getconstituencyWiseMobileNumbers();
	}
}
