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

public class CsvReader2004 implements IExcelReader{
	private ConstituencyBlock constituencyBlock;
	private CandidateElectionResult candidateElectionResult;
	private List<ConstituencyBlock> constituencyBlocks;
	private List<CandidateElectionResult> candidateElectionResults;
	private ExcelRowExtracter columnMapper;
	private String constituencyName;
	private Long locationId;
	public void readCSV(File filePath) throws CsvException{
		
		try{
			File exceFile= filePath;
			constituencyBlocks= new ArrayList<ConstituencyBlock>();
			Workbook workbook=Workbook.getWorkbook(exceFile);	
			Sheet sheet = workbook.getSheet(0);
			// Loop over first no column and rows in the excel sheet
			//sheet.getColumns()
			for (int row = 0; row < sheet.getRows(); row++) {
				columnMapper = new ExcelRowExtracter(sheet,row,sheet.getColumns());
				identifyRowAndBindObject(columnMapper);
				/*if(row==15)
					break;*/
	
			}
			if(constituencyBlocks!=null && constituencyBlocks.size()>0){
				setConstituencyBlocks(constituencyBlocks);
			}else
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!No constituency Blocks are created....");
					//System.out.println("Completed in readCSVFileAndStoreIntoDB");
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
			//Assembly-ElectionResults-AP-1994
			//Assembly-ElectionResults-AP-1999
			//D:/documents/elections data converted to excel from pdf/excel - assembly/forTst/Assembly-ElectionResults-AP-1989_Pattern2.xls
			//csvReader.readCSV("D:/documents/elections data converted to excel from pdf/excel - assembly/forTst/Assembly-ElectionResults-AP-1989_Pattern2.xls");
			System.out.println("\nConstitution Blocak details =="+csvReader.getConstituencyBlocks().size());
			List<ConstituencyBlock> constituencies= csvReader.getConstituencyBlocks();
			ConstituencyBlock constituencyBlock=constituencies.get(7);
			
			System.out.println("Constituency Name == "+constituencyBlock.getConstituencyName());
			System.out.println("Constituency Margin =="+constituencyBlock.getMargin());
			System.out.println("Constituency Remarks =="+constituencyBlock.getRemarks());
			System.out.println("Constituency Reservation Info =="+constituencyBlock.getReservationInfo());
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
					if(methods[i].getName().equals("getExcelColumn2")&& str.indexOf(".")>0 && csvColumnMapperObj.getExcelColumn3().length()==0 && csvColumnMapperObj.getExcelColumn4().length()==0 && csvColumnMapperObj.getExcelColumn5().length()==0 && csvColumnMapperObj.getExcelColumn6().length()==0 && csvColumnMapperObj.getExcelColumn7().length()==0 && csvColumnMapperObj.getExcelColumn8().length()==0){
						candidateElectionResults=new ArrayList<CandidateElectionResult>();
						/*new ConstituencyBlock();
						constituencyBlock.setConstituencyName(StringUtils.split(str,".")[1].trim());*/
						
						//modified by sai
						boolean flag=false;
						String constiName[] = str.split("-");
						if(constiName.length > 1){
							flag = true;
							locationId = new Long(constiName[1].trim());
							
						}
						//end
						constituencyBlock= checkConstituencyForReservation(str,flag);
						
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
							//String cName=StringUtils.replace(str, ".", "");
							//candidateElectionResult.setCandidateName(StringUtils.trim(cName));
							candidateElectionResult.setCandidateName(StringUtils.trim(str));
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

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public ExcelRowExtracter getColumnMapper() {
		return columnMapper;
	}

	public void setColumnMapper(ExcelRowExtracter columnMapper) {
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
			constituencyBlock.setDistrictId(locationId);
		
		//end
		if(constituencyName!=null && constituencyName.length()>0){
			String str[]=StringUtils.split(constituencyName+"(PA)", ".()");
			String constiName[] = str[1].split("-");
			if(constiName.length > 1)
				constituencyBlock.setConstituencyName(constiName[0].trim());
			else
			    constituencyBlock.setConstituencyName(str[1].trim());
			if(str.length>2 && !str[2].equalsIgnoreCase("PA")){
				constituencyBlock.setReservationInfo(str[2].trim());
			}			
		}

		
		return constituencyBlock;
	}
	
	
	
	
}
