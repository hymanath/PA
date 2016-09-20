package com.itgrids.voterdata.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMigration {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL1 = "jdbc:mysql://192.168.11.4:3306/cnp4?autoReconnect=true";
	static final String USER1 = "root";
	static final String PASS1 = "itg$36593^";
	
	static final String DB_URL2 = "jdbc:mysql://192.168.11.4:3306/cnp4?autoReconnect=true";
	static final String USER2 = "root";
	static final String PASS2 = "itg$36593^";
	
	static Connection conn1 = null;
	static Statement stmt1 = null;
	static Connection conn2 = null;
	static Statement stmt2 = null;
	
	public static void main(String... args)
	{
		startMigration(21408,1);
	}
	public static void startMigration(int startIndex,int maxCount)
	{
		try{
			conn1 = DriverManager.getConnection(DB_URL1,USER1,PASS1);
    		stmt1 = conn1.createStatement();
    		
    		conn2 = DriverManager.getConnection(DB_URL2,USER2,PASS2);
    		stmt2 = conn2.createStatement();
    		
    		Integer articleGroupsId = 0;
    		Integer articleGroupsRelationId = 0;
    		Integer candidateArticleId = 0;
    		Integer articleGroupCandidateId = 0;
    		
			ResultSet rs = stmt1.executeQuery("SELECT DISTINCT(article_id) FROM candidate_party_article limit "+startIndex+","+maxCount);
			List<Integer> articlesList = new ArrayList<Integer>(0);
			
			while(rs.next())
			{
				//articlesList.add(rs.getInt(1));
			}
			articlesList.add(17281);
			for(Integer articleId : articlesList)
			{
				try{
					List<Integer> groupsList = new ArrayList<Integer>(0);
					Map<String,Integer> groupsMap = new HashMap<String, Integer>(0);
					Map<String,Integer> caIdMap = new HashMap<String, Integer>(0);
					
					rs = stmt1.executeQuery("SELECT G.comment_group FROM candidate_party_article C,candidate_party_article_comment_group G WHERE C.candidate_party_article_id = G.candidate_party_article_id AND " +
							" C.article_id = "+articleId+" GROUP BY G.comment_group");
					
					while(rs.next())
					{
						groupsList.add(rs.getInt(1));
					}
					
					for(Integer groupId : groupsList)
					{
						articleGroupsId++;
						stmt1.executeUpdate("INSERT INTO article_groups(article_groups_id,article_id,group_type,order_no) VALUES ("+articleGroupsId+","+articleId+",'F',"+articleGroupsId+")");
						groupsMap.put("F-"+groupId, articleGroupsId);
						
						articleGroupsId++;
						stmt1.executeUpdate("INSERT INTO article_groups(article_groups_id,article_id,group_type,order_no) VALUES ("+articleGroupsId+","+articleId+",'T',"+articleGroupsId+")");
						groupsMap.put("T-"+groupId, articleGroupsId);
						
						articleGroupsRelationId++;
						stmt1.executeUpdate("INSERT INTO article_groups_relation(article_groups_relation_id,source_group_id,target_group_id,order_no) VALUES ("+articleGroupsRelationId+","+groupsMap.get("F-"+groupId)+","+groupsMap.get("T-"+groupId)+","+articleGroupsRelationId+")");
					}
					
					rs = stmt1.executeQuery("SELECT G.comment_group,C.source_party_id,C.source_candidate_id,C.source_benefit_id FROM candidate_party_article C,candidate_party_article_comment_group G WHERE " +
							" C.candidate_party_article_id = G.candidate_party_article_id AND C.article_id =  "+articleId+" " +
							" GROUP BY G.comment_group,C.source_party_id,C.source_candidate_id");
					
					while(rs.next())
					{
						candidateArticleId++;
						Integer comment_group = rs.getInt("comment_group");
						Integer source_party_id = rs.getInt("source_party_id");
						Integer source_candidate_id = rs.getInt("source_candidate_id");
						Integer source_benefit_id = rs.getInt("source_benefit_id");
						
						if(source_party_id.intValue() == 0)
							source_party_id = null;
						if(source_candidate_id.intValue() == 0)
							source_candidate_id = null;
						if(source_benefit_id.intValue() == 0)
							source_benefit_id = 3;
							
						stmt2.executeUpdate("INSERT INTO candidate_article(candidate_article_id,article_id,candidate_id,organization_id,benefit_id,state_id,impact_level_id) VALUES (" +
								" "+candidateArticleId+","+articleId+","+(source_candidate_id != null ? source_candidate_id : "NULL")+","+(source_party_id != null ? source_party_id : "NULL")+","+(source_benefit_id != null ? source_benefit_id : 3)+",1,2)");
						
						stmt2.executeUpdate("INSERT INTO article_groups_candidate(article_groups_candidate_id,article_groups_id,candidate_article_id,order_no) VALUES ("+ ++articleGroupCandidateId+","+groupsMap.get("F-"+comment_group)+","+candidateArticleId+","+articleGroupCandidateId+")");
						caIdMap.put(articleId+"-"+comment_group+"-"+(source_party_id != null ? source_party_id:"NULL")+"-"+(source_candidate_id != null ? source_candidate_id : "NULL"),candidateArticleId);
					}
					
					rs = stmt1.executeQuery("SELECT G.comment_group,C.destination_party_id,C.destination_candidate_id,C.destination_benefit_id FROM candidate_party_article C,candidate_party_article_comment_group G WHERE " +
							" C.candidate_party_article_id = G.candidate_party_article_id AND C.article_id = " +articleId+" "+
							" GROUP BY G.comment_group,C.destination_party_id,C.destination_candidate_id");
					
					while(rs.next())
					{
						candidateArticleId++;
						Integer comment_group = rs.getInt("comment_group");
						Integer destination_party_id = rs.getInt("destination_party_id");
						Integer destination_candidate_id = rs.getInt("destination_candidate_id");
						Integer destination_benefit_id = rs.getInt("destination_benefit_id");
						
						if(destination_party_id.intValue() == 0)
							destination_party_id = null;
						if(destination_candidate_id.intValue() == 0)
							destination_candidate_id = null;
						if(destination_benefit_id.intValue() == 0)
							destination_benefit_id = 3;
						
						stmt2.executeUpdate("INSERT INTO candidate_article(candidate_article_id,article_id,candidate_id,organization_id,benefit_id,state_id,impact_level_id) VALUES (" +
								" "+candidateArticleId+","+articleId+","+(destination_candidate_id != null ? destination_candidate_id : "NULL")+","+(destination_party_id != null ? destination_party_id : "NULL")+","+(destination_benefit_id != null ? destination_benefit_id : 3)+",1,2)");
						
						stmt2.executeUpdate("INSERT INTO article_groups_candidate(article_groups_candidate_id,article_groups_id,candidate_article_id,order_no) VALUES ("+ ++articleGroupCandidateId+","+groupsMap.get("T-"+comment_group)+","+candidateArticleId+","+articleGroupCandidateId+")");
						caIdMap.put(articleId+"-"+comment_group+"-"+(destination_party_id != null ? destination_party_id:"NULL")+"-"+(destination_candidate_id != null ? destination_candidate_id : "NULL"),candidateArticleId);
					}
					
					rs = stmt1.executeQuery("SELECT CPA.for_source_category,CG.comment_group,CPA.candidate_party_article_id,CPA.source_party_id,CPA.source_candidate_id," +
							" CPA.destination_party_id,CPA.destination_candidate_id,CPC.gallary_id FROM candidate_party_article CPA,candidate_party_category CPC,candidate_party_article_comment_group CG " +
							" WHERE CPA.candidate_party_article_id = CPC.candidate_party_article_id AND CPC.candidate_party_article_id = CG.candidate_party_article_id AND CPA.article_id = "+articleId);
					
					while(rs.next())
					{
						String for_source_category = rs.getString("for_source_category");
						Integer comment_group = rs.getInt("comment_group");
						Integer source_party_id = rs.getInt("source_party_id");
						Integer source_candidate_id = rs.getInt("source_candidate_id");
						Integer destination_party_id = rs.getInt("destination_party_id");
						Integer destination_candidate_id = rs.getInt("destination_candidate_id");
						Integer gallary_id = rs.getInt("gallary_id");
						Integer candArtId = null;
						
						if(source_party_id.intValue() == 0)
							source_party_id = null;
						
						if(source_candidate_id.intValue() == 0)
							source_candidate_id = null;
						
						if(destination_party_id.intValue() == 0)
							destination_party_id = null;
						
						if(destination_candidate_id.intValue() == 0)
							destination_candidate_id = null;
						
						if(for_source_category == null || for_source_category.equalsIgnoreCase("N"))
						{
							candArtId = caIdMap.get(articleId+"-"+comment_group+"-"+(destination_party_id != null ? destination_party_id:"NULL")+"-"+(destination_candidate_id != null ? destination_candidate_id : "NULL"));
						}
						else if(for_source_category.equalsIgnoreCase("O") || for_source_category.equalsIgnoreCase("Y"))
						{
							candArtId = caIdMap.get(articleId+"-"+comment_group+"-"+(source_party_id != null ? source_party_id:"NULL")+"-"+(source_candidate_id != null ? source_candidate_id : "NULL"));
						}
						
						if(candArtId != null)
						{
							stmt2.executeUpdate("INSERT INTO candidate_article_category(category_id,candidate_article_id) VALUES ("+gallary_id+","+candArtId+")");
						}
						else
						{
							System.out.println("Record Not Found");
						}
					}
				}catch(Exception e)
				{
					System.out.println("Exception Occured at : "+articleId);
					e.printStackTrace();
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
