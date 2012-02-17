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


public class CsvReader implements IExcelReader{
	
	private ConstituencyBlock constituencyBlock;
	private CandidateElectionResult candidateElectionResult;
	private List<ConstituencyBlock> constituencyBlocks;
	private List<CandidateElectionResult> candidateElectionResults;
	private CsvColumnMapper columnMapper;
	private String constituencyName;
	private Long districtId;
	
	public CsvReader(){
		
	}

	public void readCSV(File filePath) throws CsvException{
		
		try{
			File exceFile= filePath;
			constituencyBlocks= new ArrayList<ConstituencyBlock>();
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


	public static void main(String[] args) {
		try {			
			CsvReader csvReader = new CsvReader();
			//D:/documents/elections data converted to excel from pdf/excel - assembly/forTest/Assembly-ElectionResults-AP-1983.xls
			//csvReader.readCSV("D:/documents/Parlament-AP-Results-2009.xls");
			//D:/documents/elections data converted to excel from pdf/excel - assembly/forTest/Assembly-ElectionResults-AP-2004
			//D:\documents\elections data converted to excel from pdf\excel - assembly\forTst\Assembly1-ElectionResults-AP-2004_Pattern1
			//D:/documents/elections data converted to excel from pdf/excel  -parliament/forTst/Parlament-AP-Results-2009_Pattern1
			//csvReader.readCSV("D:/documents/elections data converted to excel from pdf/excel  -parliament/forTst/Parlament-AP-Results-2009_Pattern1.xls");
			List<ConstituencyBlock> constituencies= csvReader.getConstituencyBlocks();
			System.out.println("\nConstitution Blocak details =="+csvReader.getConstituencyBlocks().size());
			ConstituencyBlock constituencyBlock=constituencies.get(0);
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
			System.out.println("This is the end of the line");


			CandidateElectionResult candidateElectionResult = constituencyBlock.getCandidateElectionlst().get(3);
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
		Double assets = validateNumericColumn(csvColumnMapperObj.getCsvColumn5());
		Double liabilities = validateNumericColumn(csvColumnMapperObj.getCsvColumn6());
		
		try{
			java.lang.reflect.Method []methods=CsvColumnMapper.class.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if(methods[i].getName().startsWith("get")){
					String str=(String)methods[i].invoke(csvColumnMapperObj, null);
					if(methods[i].getName().equals("getCsvColumn2") && str.trim().indexOf("-")>-1 
							&& csvColumnMapperObj.getCsvColumn3().length()==0 && 
							csvColumnMapperObj.getCsvColumn4().length()==0){
						//modified by sai
						boolean flag=false;
						String constiName[] = str.split("-");
						if(constiName.length > 2){
							flag = true;
							districtId = new Long(constiName[2].trim());
						}
						//end
						constituencyName=(str.split("-"))[1].trim();
						constituencyName=StringUtils.trim(constituencyName);
						candidateElectionResults=new ArrayList<CandidateElectionResult>();
						constituencyBlock= checkConstituencyForReservation(str,flag);
/*							new ConstituencyBlock();
						constituencyBlock.setConstituencyName(constituencyName);*/
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
					} else if(candidateElectionResults!= null && csvColumnMapperObj.isCandidateDetails){
						if(methods[i].getName().equals("getCsvColumn2")){						
							candidateElectionResult.setCandidateName(str);
						}else if(methods[i].getName().equals("getCsvColumn3")){
							candidateElectionResult.setCandidatePrty(str);
						}else if(methods[i].getName().equals("getCsvColumn4")){
							candidateElectionResult.setVotesEarned(localDouble);
							//candidateElectionResults.add(candidateElectionResult); //asif.shaik@live.in //faisal.usmani@hp.com
							//asif.sb@techmahindra.com
						}else if(methods[i].getName().equals("getCsvColumn5")){
							
							candidateElectionResult.setAssets(assets);
						}else if(methods[i].getName().equals("getCsvColumn6")){
							
							candidateElectionResult.setLiabilities(liabilities);
						}else if(methods[i].getName().equals("getCsvColumn7")){
							candidateElectionResult.setEducation(csvColumnMapperObj.getCsvColumn7());
						}else if(methods[i].getName().equals("getCsvColumn8")){
							candidateElectionResult.setCriminalCharges(csvColumnMapperObj.getCsvColumn8());
						}else if(methods[i].getName().equals("getCsvColumn9")){
							candidateElectionResult.setSex(csvColumnMapperObj.getCsvColumn9());
							candidateElectionResults.add(candidateElectionResult);
						}
					}
				}

			}
		}catch (IllegalArgumentException argumentExeception) {
			argumentExeception.printStackTrace();
			throw new CsvException(argumentExeception.getMessage());
		}catch(InvocationTargetException invocationEx){
			invocationEx.printStackTrace();
			throw new CsvException(invocationEx.getMessage());
		}catch(IllegalAccessException iace){
			iace.printStackTrace();
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
		
		if(columnValue != null){
			if(StringUtils.isNotEmpty(columnValue.trim())){
				String tempValue=StringUtils.replace(columnValue, ",", "");
				if(StringUtils.isNumeric(tempValue)){
					tempDouble= new Double(tempValue);
					return tempDouble;
				}
	
			}
		}

		return tempDouble;
	}
	
	public CandidateElectionResult getCandidateElectionResult() {
		return candidateElectionResult;
	}
	public void setCandidateElectionResult(
			CandidateElectionResult candidateElectionResult) {
		this.candidateElectionResult = candidateElectionResult;
	}
	public List<CandidateElectionResult> getCandidateElectionResults() {
		return candidateElectionResults;
	}
	public void setCandidateElectionResults(
			List<CandidateElectionResult> candidateElectionResults) {
		this.candidateElectionResults = candidateElectionResults;
	}
	public CsvColumnMapper getColumnMapper() {
		return columnMapper;
	}
	public void setColumnMapper(CsvColumnMapper columnMapper) {
		this.columnMapper = columnMapper;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	private ConstituencyBlock checkConstituencyForReservation(String constituencyName,Boolean flag){
		ConstituencyBlock constituencyBlock = new ConstituencyBlock();
		//modified by sai
		if(flag == true)
			constituencyBlock.setDistrictId(districtId);
		//end
		if(constituencyName!=null && constituencyName.length()>0){
			String str[]=StringUtils.split(constituencyName+"(PA)", "-()");
			constituencyBlock.setConstituencyName(str[1].trim());
			if(str.length>2 && !str[2].equalsIgnoreCase("PA")){
				constituencyBlock.setReservationInfo(str[2].trim());
			}			
		}

		
		return constituencyBlock;
	}
}
