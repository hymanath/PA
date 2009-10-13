package com.itgrids.partyanalyst.excel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;


public class CsvReader2004 {
	ConstituencyBlock constituencyBlock=null;
	List<CsvColumnMapper> constituencyCandidates = new ArrayList<CsvColumnMapper>();
	CandidateElectionResult candidateElectionResult=null;
	List<ConstituencyBlock> constituencyBlocks= new ArrayList<ConstituencyBlock>();
	List<CandidateElectionResult> candidateElectionResults=null;
	ExcelRowExtracter columnMapper= null;
	String constituencyName="";
	public void readCSV(String filePath) throws CsvException{
		File exceFile= new File(filePath);
		try{
			Workbook workbook=Workbook.getWorkbook(exceFile);	
			Sheet sheet = workbook.getSheet(0);
			// Loop over first no column and rows in the excel sheet
			//sheet.getColumns()
			for (int row = 0; row < sheet.getRows(); row++) {
				columnMapper = new ExcelRowExtracter(sheet,row,sheet.getColumns());
				identifyRowAndBindObject(columnMapper);
				/*if(row==22)
					break;*/

			}
			if(constituencyBlocks!=null && constituencyBlocks.size()>0){
				setConstituencyBlocks(constituencyBlocks);
			}else
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!No constituency Blocks are created....");
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
			CsvReader2004 csvReader = new CsvReader2004();
			//csvReader.readCSV("D:/documents/Parlament-AP-Results-2009.xls");//StatisticalReport_AP1989_Extracted 
			//Assembly-ElectionResults-AP-1983
			//Assembly-ElectionResults-AP-1994 Assembly-ElectionResults-AP-2004
			//Assembly-ElectionResults-AP-1999
			csvReader.readCSV("D:/documents/elections data converted to excel from pdf/excel - assembly/forTest/Assembly-ElectionResults-AP-1983.xls");
			System.out.println("\nConstitution Blocak details =="+csvReader.getConstituencyBlocks().size());
			List<ConstituencyBlock> constituencies= csvReader.getConstituencyBlocks();
			ConstituencyBlock constituencyBlock=constituencies.get(8);
			
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
			CandidateElectionResult candidateObj=constituencyBlock.getCandidateElectionlst().get(1);
			System.out.println("Candidate name"+candidateObj.getCandidateName());
			System.out.println("Candidate party"+candidateObj.getCandidatePrty());
			System.out.println("Candidate sex"+candidateObj.getSex());
			System.out.println("Candidate votes earned"+candidateObj.getVotesEarned());
			//CandidateElectionResult candidateElectionResult = constituencyBlock.getCandidateElectionlst().get(6);
			//System.out.println("Candidate Name == "+candidateElectionResult.getCandidateName());

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
	private void identifyRowAndBindObject(ExcelRowExtracter csvColumnMapperObj) throws CsvException{
		candidateElectionResult = new CandidateElectionResult();
		try{
			java.lang.reflect.Method []methods=ExcelRowExtracter.class.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if(methods[i].getName().startsWith("get")){
					String str=(String)methods[i].invoke(csvColumnMapperObj, null);
					if(methods[i].getName().equals("getExcelColumn2")&& str.indexOf(".")>1 && csvColumnMapperObj.getExcelColumn3().length()==0 && csvColumnMapperObj.getExcelColumn4().length()==0){
						candidateElectionResults=new ArrayList<CandidateElectionResult>();
						constituencyBlock= new ConstituencyBlock();
						constituencyBlock.setConstituencyName(StringUtils.split(str,".")[1].trim());
						break;
					}else if(str.startsWith(ConstituencyElectionResultCSVColumnNames.ELECTORS))
					{
						constituencyBlock.setTotalElectors(validateNumericColumn(csvColumnMapperObj.getExcelColumn2()));
						constituencyBlock.setValidVotes(validateNumericColumn(csvColumnMapperObj.getExcelColumn9()));
						constituencyBlock.setTotalVotesPolled(validateNumericColumn(csvColumnMapperObj.getExcelColumn4()));
						constituencyBlock.setCandidateElectionlst(candidateElectionResults);
						constituencyBlocks.add(constituencyBlock);
						constituencyName="";
						break;
					} else if(candidateElectionResults!=null && csvColumnMapperObj.isCandidateDetails){
						if(methods[i].getName().equals("getExcelColumn2")){
							candidateElectionResult.setCandidateName(StringUtils.replace(str, ".", ""));
						}else if(methods[i].getName().equals("getExcelColumn6")){
							candidateElectionResult.setSex(str);
						}else if(methods[i].getName().equals("getExcelColumn7")){
							candidateElectionResult.setCandidatePrty(str);
						}else if(methods[i].getName().equals("getExcelColumn8")){
							candidateElectionResult.setVotesEarned(validateNumericColumn(str));
							candidateElectionResults.add(candidateElectionResult);
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
		if(StringUtils.isNotEmpty(columnValue)){
			String tempValue=StringUtils.replace(columnValue, ",", "");
			if(StringUtils.isNumeric(tempValue)){
				tempDouble= new Double(tempValue);
				return tempDouble;
			}
		}
		return tempDouble;
	}
}
