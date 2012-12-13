package com.itgrids.voterdata;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ConvertVoterDataFromPdfToText {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/itgrids";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static String readFromFile(File file,
                                      Charset charset) throws IOException {
        InputStream in = new FileInputStream(file);
        Closeable stream = in;
        try {
            Reader reader = new InputStreamReader(in, charset);
            stream = reader;
            StringBuilder inputBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            while (true) {
                int readCount = reader.read(buffer);
                if (readCount < 0) {
                    break;
                }
                inputBuilder.append(buffer, 0, readCount);
            }
            return inputBuilder.toString();
        } finally {
            stream.close();
        }
    }
    
    public static Integer saveVotersData(List<VoterInfo> votersInfoList)
    {
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting to database...");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		System.out.println("Inserting records into the table...");
    		stmt = conn.createStatement();
    		
    		for(VoterInfo info : votersInfoList)
    		{
    			String insertQuery = "INSERT INTO voter(voter_id, name, sex, age, house_no, guardian_name, relation, constituency_id, " +
    				" constituency_name, booth_id, booth_name,sno) VALUES ('"+info.getVoterId()+"','"+info.getVoterName()+"','"+info.getSex()+
    				"','"+info.getAge()+"','"+info.getHouseNumber()+"','"+info.getGuardianName()+"','"+info.getGuardianRelation()+
    				"','"+info.getConstituencyId()+"','"+info.getConstituency()+"','"+info.getBoothNo()+"','"+info.getBoothName().replaceAll(".pdf","")+"','"+info.getsNo()+"')";
    			stmt.executeUpdate(insertQuery);
    		}
    		
    		return null;
    	}catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    	finally{
    		try {
				conn.close();
			} catch (SQLException e) {
				
			}
    	}
    }

    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        List<VoterInfo> voterList = new ArrayList<VoterInfo>();
        int i = 0;
        // Regex. For those who do not know. The Pattern refers to the format you are looking for.
        // In our example, we are looking for voter data
        //Pattern p = Pattern.compile("Age:\\sSex:\\s([a-zA-Z]*)\\n(AJP\\d*|AP\\d*)\\nElector's Name:\\n(Husband's Name:|Father's Name:)\\nHouse No:\\n([A-Z\\s\\n]*)\\n([A-Z\\s]*)\\n([0-9\\-_/]*)\\n([\\s0-9]*)\\n[\\s0-9]*\\n");
        //  Pattern p = Pattern.compile("Age:\\sSex:\\s([a-zA-Z]*)\\n(KLQ\\d*|AJP\\d*|AP\\d*)\\nElector's Name:\\n(Husband's Name:|Father's Name:|Mother's Name:)\\nHouse No:\\n([A-Z\\s\\n]*)\\n([A-Z\\s]*)\\n([0-9\\-_/A-Za-z]*)\\n([\\s0-9]*)\\n[\\s0-9]*\\n");
        //Pattern p = Pattern.compile("Age:\\sSex:\\s([a-zA-Z]*)\\n([A-Z\\d]*)\\nElector's Name:\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\nHouse No:\\n([A-Za-z\\.\\s\\n]*)\\n([A-Za-z\\.\\s]*)\\n([0-9\\-_/A-Za-z]*)\\n([\\s0-9]*)\\n([\\s0-9a-zA-Z]*)\\n");
       /* This Pattern will use full for 2013 */
        Pattern p = Pattern.compile("Age:\\sSex:\\s([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s]*)\\r\\n([0-9\\-_/A-Za-z]*)\\r\\n([\\s0-9]*)\\r\\n([\\s0-9a-zA-Z]*)\\r\\n");
        Pattern newp = Pattern.compile("([\\s0-9a-zA-Z\\s]*)\\r\\nAge:\\sSex:\\s([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\?\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\?\\.\\s\\r\\n]*)\\r\\n([0-9\\-_/A-Za-z\\s]*)\\r\\n([\\s0-9]*)\\r\\n");
        //Pattern p = Pattern.compile("Elector's Name:\\r\\nSex:Age:\\r\\nHouse No:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s]*)Father's Name:\\r\\n([\\s0-9]*)\\r\\n([\\s0-9a-zA-Z]*)\\s([a-zA-Z]*)\\r\\n");
        
        //Pattern p = Pattern.compile("Elector's Name:\\r\\nSex:Age:\\r\\nHouse No:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s]*)Father's Name:\\r\\n()\\r\\n");
        
        try {

                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                //  PDF file from where the voter data is extracted
                File inputDir = new File(args[0]);
                String voterInfo = "";
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {

                    // StringBuilder to store the extracted text
                    StringBuilder sb = new StringBuilder();
                    //System.out.println(readFromFile(input,Charset.forName("UTF-8")));
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();
                    // Add text to the StringBuilder from the PDF
                    sb.append(stripper.getText(pd));
                    /*String ssb = sb.toString();
                    ssb.replaceAll("\n"," ");
                    sb = new StringBuilder(ssb);*/
                    System.out.println("File text:"+stripper.getText(pd));
                                       
                    String [] fileName = input.getName().split("-");
                    // Matcher refers to the actual text where the pattern will be found
                    Matcher m = p.matcher(sb);
                    VoterInfo voter = null;
                    
                    StringBuilder sb2 = new StringBuilder();
                    File reultFile  = new File(args[0]+"/"+args[1]);
                    BufferedWriter outwriter = new BufferedWriter(new FileWriter(reultFile));
                    outwriter.write(sb.toString());
                    
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        i++;
                        voter = new VoterInfo();
                        // group() method refers to the next number that follows the pattern we have specified.
                        voter.setAge(m.group(7).replaceAll("\\r\\n","").trim());
                        voter.setSex(m.group(1).replaceAll("\\r\\n","").trim());
                        voter.setVoterId(m.group(2).replaceAll("\\r\\n","").trim());
                        voter.setVoterName(m.group(4).replaceAll("\\r\\n","").trim());
                        voter.setGuardianName(m.group(5).replaceAll("\\r\\n","").trim());
                        voter.setGuardianRelation(m.group(3).substring(0, m.group(3).indexOf("'s Name")).replaceAll("\\r\\n","").trim());
                        voter.setHouseNumber(m.group(6).replaceAll("\\r\\n","").trim());
                        String sNo = m.group(8).replaceAll("\\r\\n","").trim(); 
                        try{
                        if(sNo != null && sNo.length() > 0)
                        	voter.setsNo(new Long(sNo).longValue());
                            System.out.println(voter.getsNo());
                        }catch(Exception e){
                        	
                        }
                        voter.setConstituency(fileName[1]);
                        voter.setBoothNo(fileName[2]);
                        voter.setBoothName(fileName[3]);
                        voter.setConstituencyId(fileName[0]);
                        voterList.add(voter);
                        voterInfoList.add(voter);
                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
                        System.out.println(voterInfo);
                        sb2.append(voterInfo);
                        writer.write(voterInfo); 
                    }
                    if (pd != null) {
                        pd.close();
                    }
                    System.out.println("Kamalakar - "+i);
                    saveVotersData(voterInfoList);  
            }
            System.out.println("Total No of Voters:" + voterList.size());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
