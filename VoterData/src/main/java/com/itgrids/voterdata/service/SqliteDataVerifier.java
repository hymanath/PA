package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteDataVerifier {

	public static void main(String[] args)
	{
		SqliteDataVerifier sqliteDataVerifier = new SqliteDataVerifier();
		sqliteDataVerifier.verifyData("D:\\SQLITE_FILES\\");
	}
	
	public void verifyData(String dirPath)
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;
			
			File dir = new File(dirPath);
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File(dirPath+"\\Result_All.txt")));
			StringBuilder sb = new StringBuilder();
			int index = 0;
			
			for(File sqliteFile : dir.listFiles())
			{
				if(!sqliteFile.getName().contains(".sqlite"))
					continue;
				try{
					System.out.println("Reading "+sqliteFile.getName());
					index++;
					String districtName = "";
					String constituencyName = "";
					String memberStr = "";
					int constituencyId = 0;
					int totalVoters = 0;
					int votersAvailableBoothsCount = 0;
					int boothsCount = 0;
					int namesCount = 0;
					
					connection = DriverManager.getConnection("jdbc:sqlite:"+sqliteFile.getAbsolutePath());
					statement = connection.createStatement();
					
					/*statement.execute("CREATE UNIQUE INDEX voter_voter_id_unq_idx on voter(voter_id)");
					statement.execute("CREATE UNIQUE INDEX voter_voter_id_card_no_unq_idx on voter(voter_id_card_no)");
					statement.execute("CREATE INDEX bpv_voter_id_idx on booth_publication_voter(voter_id)");
					statement.execute("CREATE INDEX bpv_booth_id_idx on booth_publication_voter(booth_id)");
					statement.execute("CREATE UNIQUE INDEX booth_booth_id_idx on booth(booth_id)");
					statement.execute("CREATE INDEX booth_constituency_id_idx on booth(constituency_id)");
					statement.execute("CREATE INDEX booth_tehsil_id_idx on booth(tehsil_id)");
					statement.execute("CREATE INDEX booth_panchayat_id_idx on booth(panchayat_id)");
					statement.execute("CREATE UNIQUE INDEX voter_telugu_names_voter_id_idx on voter_telugu_names(voter_id)");
					statement.execute("CREATE UNIQUE INDEX constituency_constituency_id_idx on constituency(constituency_id)");
					statement.execute("CREATE INDEX delimitation_constituency_constituency_id_idx on delimitation_constituency(constituency_id)");*/

					System.out.println("Indexes Created... ");
					rs = statement.executeQuery("SELECT D.district_name,C.constituency_id,C.constituency_name,count(BPV.voter_id) FROM " +
							" voter V,booth_publication_voter BPV,booth B,constituency C,district D WHERE C.district_id = D.district_id AND" +
							" V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND B.constituency_id = C.constituency_id AND B.publication_date_id = 11" +
							" GROUP BY B.constituency_id");
					
					while(rs.next())
					{
						districtName = rs.getString(1);
						constituencyId = rs.getInt(2);
						constituencyName = rs.getString(3);
						totalVoters = rs.getInt(4);
					}
					
					rs = statement.executeQuery("SELECT count(DISTINCT BPV.booth_id) FROM delimitation_constituency DC,constituency C,booth B,booth_publication_voter BPV WHERE " +
							" DC.constituency_id = C.constituency_id AND DC.year = 2009 AND C.constituency_id = B.constituency_id AND B.booth_id = BPV.booth_id AND B.publication_date_id = 11 AND DC.constituency_no = "+sqliteFile.getName().replaceAll(".sqlite",""));
					while(rs.next())
					{
						votersAvailableBoothsCount = rs.getInt(1);
					}
					
					rs = statement.executeQuery("SELECT count(DISTINCT B.booth_id) FROM delimitation_constituency DC,constituency C,booth B WHERE DC.constituency_id = C.constituency_id AND C.constituency_id = B.constituency_id AND " +
							" B.publication_date_id = 11 AND DC.year = 2009 AND DC.constituency_no = "+sqliteFile.getName().replaceAll(".sqlite",""));
					
					while(rs.next())
					{
						boothsCount = rs.getInt(1);
					}
					
					rs = statement.executeQuery("SELECT year,count(M.membership_no) FROM member M GROUP BY year");
					
					while(rs.next())
					{
						memberStr = memberStr+rs.getString(1)+" - "+ rs.getInt(2)+ " & "; 
					}
					memberStr = memberStr.substring(0,memberStr.length()-3);
					
					rs = statement.executeQuery("SELECT count(voter_id) FROM voter_telugu_names");
					
					while(rs.next())
					{
						namesCount = rs.getInt(1);
					}
					
					System.out.println(sqliteFile.getName().replaceAll(".sqlite","")+") "+districtName+" ->  constituency : "+constituencyName+" ("+constituencyId+") : Total Voters -> "+totalVoters+", Total Booths : "
							+boothsCount+", Voters Contains : "+votersAvailableBoothsCount+" & diff - "+(boothsCount-votersAvailableBoothsCount)+", members : "+memberStr+" & Telugu Names - "+namesCount);
					
					sb.append(sqliteFile.getName().replaceAll(".sqlite","")+") "+districtName+" ->  constituency : "+constituencyName+" ("+constituencyId+") : Total Voters -> "+totalVoters+", Total Booths : "
							+boothsCount+", Voters Contains : "+votersAvailableBoothsCount+" & diff - "+(boothsCount-votersAvailableBoothsCount)+", members : "+memberStr+" & Telugu Names - "+namesCount+"\n");
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
}

