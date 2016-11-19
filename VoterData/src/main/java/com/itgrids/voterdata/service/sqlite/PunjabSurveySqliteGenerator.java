package com.itgrids.voterdata.service.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class PunjabSurveySqliteGenerator {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://103.241.136.117:3306/public_pulse";
	static final String USER = "itgrids";
	static final String PASS = "publ!cPu1se";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public static void main(String[] args) throws Exception{
		
		PunjabSurveySqliteGenerator generator = new PunjabSurveySqliteGenerator();
		
		List<Integer> conList = new ArrayList<Integer>(0);
		conList.add(38744);
		conList.add(38745);
		conList.add(38746);
		conList.add(38749);
		conList.add(38750);
		conList.add(38752);
		conList.add(38753);
		conList.add(38755);
		conList.add(38757);
		conList.add(38758);
		conList.add(38759);
		conList.add(38760);
		conList.add(38761);
		conList.add(38762);
		conList.add(38763);
		conList.add(38764);
		conList.add(38765);
		conList.add(38766);
		conList.add(38768);
		conList.add(38770);
		conList.add(38771);
		conList.add(38772);
		conList.add(38773);
		conList.add(38775);
		conList.add(38777);
		conList.add(38779);
		conList.add(38780);
		conList.add(38781);
		conList.add(38782);
		conList.add(38783);
		conList.add(38785);
		conList.add(38786);
		conList.add(38787);
		conList.add(38789);
		conList.add(38790);
		conList.add(38793);
		conList.add(38794);
		conList.add(38795);
		conList.add(38796);
		conList.add(38797);
		conList.add(38799);
		conList.add(38800);
		conList.add(38801);
		conList.add(38803);
		conList.add(38805);
		conList.add(38806);
		conList.add(38808);
		conList.add(38809);
		conList.add(38811);
		conList.add(38813);
		conList.add(38814);
		conList.add(38816);
		conList.add(38817);
		conList.add(38819);
		conList.add(38820);
		conList.add(38822);
		conList.add(38823);
		conList.add(38825);
		conList.add(38826);
		conList.add(38828);
		conList.add(38829);
		conList.add(38830);
		conList.add(38831);
		conList.add(38832);
		conList.add(38833);
		conList.add(38834);
		conList.add(38835);
		conList.add(38836);
		conList.add(38839);
		conList.add(38840);
		conList.add(38841);
		conList.add(38842);
		conList.add(38843);
		conList.add(38845);
		conList.add(38846);
		conList.add(38847);
		conList.add(38848);
		conList.add(38849);
		conList.add(38850);
		conList.add(38851);
		conList.add(38855);
		conList.add(38857);
		conList.add(38858);
		conList.add(38859);
		conList.add(39566);
		conList.add(39567);
		conList.add(39568);
		conList.add(39569);
		conList.add(39570);
		conList.add(39571);
		conList.add(39572);
		conList.add(39573);
		conList.add(39574);
		conList.add(39575);
		conList.add(39576);
		conList.add(39577);
		conList.add(39578);
		conList.add(39579);
		conList.add(39580);
		conList.add(39581);
		conList.add(39582);
		conList.add(39583);
		conList.add(39584);
		conList.add(39585);
		conList.add(39586);
		conList.add(39587);
		conList.add(39588);
		conList.add(39589);
		conList.add(39590);
		conList.add(39591);
		conList.add(39592);
		conList.add(39593);
		conList.add(39594);
		conList.add(39595);
		conList.add(39596);
		conList.add(39597);
		conList.add(39598);
		
		for(Integer constituencyId : conList)
			generator.genearateSqlite("D:/PublicPulse/SQLITES/Base/Base.sqlite","D:/PublicPulse/SQLITES", constituencyId);

	}
	
	public boolean genearateSqlite(String basePath,String targetFolder,int constituencyId)
	{
		try{
			Date startTime = new Date();
			ResultSet rs = null;
			
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		Class.forName("org.sqlite.JDBC");
    		Connection connection = null;
			Statement statement = null;
			
			File targetFile = new File(targetFolder+"/"+constituencyId+".sqlite");
			FileUtils.copyFile(new File(basePath), targetFile);
			
			rs = stmt.executeQuery("SELECT V.voter_id,V.house_no,V.name,V.relationship_type,V.relative_name,V.gender,V.age,V.voter_id_card_no FROM voter V,booth_publication_voter BPV,booth B "
					+ " WHERE V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND B.constituency_id = "+constituencyId+" AND B.publication_date_id = 23 GROUP BY V.voter_id");
			
			connection = DriverManager.getConnection("jdbc:sqlite:"+targetFile.getAbsolutePath());
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			int count = 0;
			while(rs.next())
			{
				try{
					statement.executeUpdate("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no) VALUES (" +
							"'"+rs.getInt(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+rs.getString(6)+"'," +
									"'"+rs.getInt(7)+"','"+rs.getString(8)+"')");
					count++;
					
						
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			System.out.println(count+" --> Voters Insetred in Constituency -- "+constituencyId);
			
			rs = stmt.executeQuery("SELECT BPV.booth_publication_voter_id,BPV.booth_id,BPV.voter_id,BPV.serial_no FROM booth_publication_voter BPV,booth B "
					+ " WHERE BPV.booth_id = B.booth_id AND B.constituency_id = "+constituencyId+" AND B.publication_date_id = 23 GROUP BY BPV.voter_id ORDER BY B.booth_id,BPV.serial_no");
			
			while(rs.next())
			{
				try{
					statement.executeUpdate("INSERT INTO booth_publication_voter(booth_publication_voter_id,booth_id,voter_id,serial_no) VALUES (" +
							"'"+rs.getInt(1)+"','"+rs.getInt(2)+"','"+rs.getInt(3)+"','"+rs.getInt(4)+"')");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			connection.commit();
			statement.close();
			connection.close();
			
			connection = DriverManager.getConnection("jdbc:sqlite:"+targetFile.getAbsolutePath());
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			
			statement.executeUpdate("CREATE INDEX idx_voter_voter_id ON voter(voter_id)");
			statement.executeUpdate("CREATE INDEX idx_voter_house_no ON voter(house_no)");
			statement.executeUpdate("CREATE INDEX idx_voter_name ON voter(name)");
			statement.executeUpdate("CREATE INDEX idx_voter_voter_id_card_no ON voter(voter_id_card_no)");
			statement.executeUpdate("CREATE INDEX idx_voter_age ON voter(age)");
			statement.executeUpdate("CREATE INDEX idx_voter_gender ON voter(gender)");
			
			statement.executeUpdate("CREATE INDEX idx_booth_publication_voter_booth_id ON booth_publication_voter(booth_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_publication_voter_voter_id ON booth_publication_voter(voter_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_publication_voter_serial_no ON booth_publication_voter(serial_no)");
			
			connection.commit();
			statement.close();
			connection.close();
			
			connection = DriverManager.getConnection("jdbc:sqlite:"+targetFile.getAbsolutePath());
			statement = connection.createStatement();
			statement.executeUpdate("VACUUM");
			statement.close();
			connection.close();
			
			Date endTime = new Date();
			System.out.println("Time Taken - "+(endTime.getTime() - startTime.getTime())/1000+" Seconds");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

}
