package com.itgrids.partyanalyst.excel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;


public class CsvReader {
	ConstituencyBlock constituencyBlock=null;
	List<CsvColumnMapper> constituencyCandidates = new ArrayList<CsvColumnMapper>();
	CandidateElectionResult candidateElectionResult=null;
	List<ConstituencyBlock> constituencyBlocks= new ArrayList<ConstituencyBlock>();
	List<CandidateElectionResult> candidateElectionResults=null;
	CsvColumnMapper columnMapper= null;
	String constituencyName="";
	public void readCSV(String filePath) throws CsvException{
		File exceFile= new File(filePath);
		try{
			Workbook workbook=Workbook.getWorkbook(exceFile);	
			Sheet sheet = workbook.getSheet(0);
			// Loop over first 10 column and lines
			//sheet.getColumns()
			for (int row = 0; row < sheet.getRows(); row++) {
				columnMapper = new CsvColumnMapper(sheet,row,sheet.getColumns());
				identifyRowAndBindObject(columnMapper);
				//candidateElectionResults=null;
				//if(row==16) break;
			}

			if(constituencyBlocks!=null && constituencyBlocks.size()>0){
				setConstituencyBlocks(constituencyBlocks);
			}
			//		System.out.println("Completed in readCSVFileAndStoreIntoDB");
		}catch(IOException ioe){
			throw new CsvException(ioe.getMessage());
		}catch(BiffException ioe){
			throw new CsvException(ioe.getMessage());
		}
	}
	//public void bindConstituencyElection

	public static void main(String[] args) {
		try {			
			CsvReader csvReader = new CsvReader();
			//D:/documents/elections data converted to excel from pdf/excel - assembly/forTest/Assembly-ElectionResults-AP-1983.xls
			//csvReader.readCSV("D:/documents/Parlament-AP-Results-2009.xls");
			csvReader.readCSV("D:/documents/Assembley-AP-Results-2009.xls");
			List<ConstituencyBlock> constituencies= csvReader.getConstituencyBlocks();
			System.out.println("\nConstitution Blocak details =="+csvReader.getConstituencyBlocks().size());
			ConstituencyBlock constituencyBlock=constituencies.get(178);
			System.out.println("Constituency Name == "+constituencyBlock.getConstituencyName());
			System.out.println("Constituency Margin =="+constituencyBlock.getMargin());
			System.out.println("Constituency Remarks =="+constituencyBlock.getRemarks());
			System.out.println("Total Votes Polled == "+constituencyBlock.getTotalVotesPolled());
			System.out.println("Missing Votes == "+constituencyBlock.getMissingVotes());
			System.out.println("Rejected Votes == "+constituencyBlock.getRejectedVotes());
			System.out.println("Tendered Votes == "+constituencyBlock.getTenderedVotes());
			System.out.println("Total Electors == "+constituencyBlock.getTotalElectors());
			System.out.println("Valid Votes == "+constituencyBlock.getValidVotes());
			System.out.println("total Number of candidated == "+constituencyBlock.getCandidateElectionlst().size());
			
			
			CandidateElectionResult candidateElectionResult = constituencyBlock.getCandidateElectionlst().get(9);
			System.out.println("Candidate Name == "+candidateElectionResult.getCandidateName());

		} catch (Exception e) {
			System.out.println("Exception == "+e.getMessage());
		}

	}

	/**
	 * This method will return the type of row, whether 'constituency election Result' details row or
	 *  CandidateElectionResults row
	 */
	/**
	 * @param csvColumnMapperObj
	 * @param state
	 * @param electionType
	 * @param electionYear
	 */
	private void identifyRowAndBindObject(CsvColumnMapper csvColumnMapperObj) throws CsvException{
		candidateElectionResult = new CandidateElectionResult();
		Double localDouble=validateNumericColumn(csvColumnMapperObj.getCsvColumn4());
		try{
			java.lang.reflect.Method []methods=CsvColumnMapper.class.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if(methods[i].getName().startsWith("get")){
					String str=(String)methods[i].invoke(csvColumnMapperObj, null);
					if(methods[i].getName().equals("getCsvColumn2") && str.trim().indexOf("-")>-1 && csvColumnMapperObj.getCsvColumn3().length()==0 && csvColumnMapperObj.getCsvColumn4().length()==0){
						candidateElectionResults=new ArrayList<CandidateElectionResult>();
						constituencyBlock= new ConstituencyBlock();
						constituencyBlock.setConstituencyName((str.split("-"))[1].trim());
						constituencyBlock.setMargin(csvColumnMapperObj.getCsvColumn5());
						constituencyBlock.setRemarks(csvColumnMapperObj.getCsvColumn6());
						break;
					}else if(str.equalsIgnoreCase(ConstituencyElectionResultCSVColumnNames.MISSIMG_VOTES))
					{
						constituencyBlock.setMissingVotes(localDouble);
						break;

					}else if(str.equalsIgnoreCase(ConstituencyElectionResultCSVColumnNames.REJECTED_VOTES))
					{
						constituencyBlock.setRejectedVotes(localDouble);
						break;

					} else if(str.equalsIgnoreCase(ConstituencyElectionResultCSVColumnNames.TENDERED_VOTES))
					{
						constituencyBlock.setTenderedVotes(localDouble);
						constituencyBlock.setCandidateElectionlst(candidateElectionResults);
						constituencyBlocks.add(constituencyBlock);
						constituencyName="";
						break;
					} else if(str.equalsIgnoreCase(ConstituencyElectionResultCSVColumnNames.TOTAL_ELECTORS))
					{
						
						constituencyBlock.setTotalElectors(localDouble);
						break;
					}else if(str.equalsIgnoreCase(ConstituencyElectionResultCSVColumnNames.VALID_VOTES))
					{
						constituencyBlock.setValidVotes(localDouble);
						break;

					}else if(str.equalsIgnoreCase(ConstituencyElectionResultCSVColumnNames.TOTAL_VOTES_POLLED))
					{
						constituencyBlock.setTotalVotesPolled(localDouble);
						break;
					} else if(candidateElectionResults!=null && csvColumnMapperObj.isCandidateDetails){
							if(methods[i].getName().equals("getCsvColumn2")){						
								candidateElectionResult.setCandidateName(str);
							}else if(methods[i].getName().equals("getCsvColumn3")){
								candidateElectionResult.setCandidatePrty(str);
							}else if(methods[i].getName().equals("getCsvColumn4")){
								candidateElectionResult.setVotesEarned(localDouble);
								candidateElectionResults.add(candidateElectionResult); //asif.shaik@live.in //faisal.usmani@hp.com
								//asif.sb@techmahindra.com
							}
						}
					}

				}
		}catch (IllegalArgumentException argumentExeception) {
			throw new CsvException(argumentExeception.getMessage());
		}catch(InvocationTargetException invocationEx){
			throw new CsvException(invocationEx.getMessage());
		}catch(IllegalAccessException iace){
			throw new CsvException(iace.getMessage());
		}
	}
	public List<ConstituencyBlock> getConstituencyBlocks() {
		return constituencyBlocks;
	}
	public void setConstituencyBlocks(List<ConstituencyBlock> constituencyBlocks) {
		this.constituencyBlocks = constituencyBlocks;
	}

	public ConstituencyBlock getConstituencyBlock() {
		return constituencyBlock;
	}

	public void setConstituencyBlock(ConstituencyBlock constituencyBlock) {
		this.constituencyBlock = constituencyBlock;
	}

	public Double validateNumericColumn(String columnValue){
		Double tempDouble= new Double(0);
		if(StringUtils.isNotEmpty(columnValue.trim())){
			String tempValue=StringUtils.replace(columnValue, ",", "");
			if(StringUtils.isNumeric(tempValue)){
				tempDouble= new Double(tempValue);
				return tempDouble;
			}

		}

		return tempDouble;
	}
}
