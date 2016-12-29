package com.itgrids.voterdata.service.sqlite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CadreRegistrationDataCount {

	public static void main(String[] args)
	{
		getCounts("D:/SHARE","D:/Workspace/cadre_registration/result3.txt");
	}
	
	public static void getCounts(String folderPath,String resultFile)
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			Statement statement = null;
			
			File mainDir = new File(folderPath);
			
			if(mainDir.isDirectory())
			{
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
				StringBuilder sb = new StringBuilder();
				
				for(File districtDir : mainDir.listFiles())
				{
					try{
					String districtName = districtDir.getName();
					
					if(districtDir.isDirectory())
					{
						for(File constituencyDir : districtDir.listFiles())
						{
							if(constituencyDir.isDirectory())
							{
								try{
									String constituencyName = constituencyDir.getName();
									
									for(File sqlFolder : constituencyDir.listFiles())
									{
										if(sqlFolder.isDirectory())
										{
											for(File sqlite : sqlFolder.listFiles())
											{
												
												try{
													if(!sqlite.isDirectory() && sqlite.getName().contains(".sqlite"))
													{
														String username = sqlite.getName().replace(".sqlite","");
														
														connection = DriverManager.getConnection("jdbc:sqlite:"+sqlite.getAbsolutePath());
														statement = connection.createStatement();
														
														ResultSet rs = statement.executeQuery(" SELECT user_id,tab_user_info_id,is_synched,count(*) as count FROM cadre "
																+ " GROUP BY user_id,tab_user_info_id,is_synched ORDER BY user_id,tab_user_info_id,is_synched ");
														
														while(rs.next())
														{
															int user_id = rs.getInt("user_id");
															int tab_user_info_id = rs.getInt("tab_user_info_id");
															String is_synched = rs.getString("is_synched");
															int count = rs.getInt("count");
															
															System.out.println(districtName+"\t"+constituencyName+"\t"+username+"\t"+user_id+"\t"+tab_user_info_id+"\t"+is_synched+"\t"+count);
															sb.append(districtName+"\t"+constituencyName+"\t"+username+"\t"+user_id+"\t"+tab_user_info_id+"\t"+is_synched+"\t"+count+"\n");
														}
														
														connection.close();
													}
												}catch (Exception e) {
													e.printStackTrace();
												}
											}//----------------
										}
									}
								}catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}//
				outwriter.write(sb.toString());
				outwriter.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
