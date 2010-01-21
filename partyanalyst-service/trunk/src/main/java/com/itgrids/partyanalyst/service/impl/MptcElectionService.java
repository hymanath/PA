package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.MPTCElectionResultVO;
import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.IMptcElectionService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MptcElectionService implements IMptcElectionService {

	private Long electionTypeID; 
	private String year;
	private Long countryID;
	private Long stateID;
	private Long districtID;
	private File file;
	private TransactionTemplate transactionTemplate;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private ICandidateDAO candidateDAO;
	private INominationDAO nominationDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IPartyDAO partyDAO;
	private IElectionScopeDAO electionScopeDAO;
	private static Logger log = Logger.getLogger(MptcElectionService.class);
	private Map<String, Integer> excelHeaderData = new HashMap<String, Integer>();
		
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	
	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	/**
	 * reading the header column names and maintaining the column numbers for reading the data
	 * @param cells
	 * @throws CsvException
	 */
	private void addExcelHeaderInfo(Cell[] cells) throws CsvException{
		// if required column headers are not available in excel sheet then throwing the exception 
		// thats why working with 2 ArrayList objects
		List<String> mandatoryHeaders = new ArrayList<String>();
		List<String> headers = new ArrayList<String>();
		mandatoryHeaders.add(IConstants.MPTC_NAME);//
		mandatoryHeaders.add(IConstants.CANDIDATE_NAME);//
		mandatoryHeaders.add(IConstants.PARTY_NAME);//
		mandatoryHeaders.add(IConstants.CANDIDATE_VOTES_EARNED);//
		mandatoryHeaders.add(IConstants.MANDAL_NAME);

		headers.add(IConstants.CANDIDATE_VOTES_PERCENTAGE);
		headers.add(IConstants.CANDIDATE_GENDER);
		headers.add(IConstants.CANDIDATE_EDUCATION);
		headers.add(IConstants.CANDIDATE_ADDRESS);
		headers.add(IConstants.CANDIDATE_MOBILE);
		headers.add(IConstants.CANDIDATE_PHONE);
		headers.add(IConstants.CANDIDATE_EMAIL);
		headers.add(IConstants.CANDIDATE_DATE_OF_BIRTH);
		headers.add(IConstants.CONSTITUENCY_VOTING_PERCENTAGE);//
		headers.add(IConstants.CONSTITUENCY_TENDERED_VOTES);
		headers.add(IConstants.CONSTITUENCY_MISSING_VOTES);
		headers.add(IConstants.CONSTITUENCY_REJECTED_VOTES);//
		headers.add(IConstants.CONSTITUENCY_RESERVATION_ZONE);
		headers.add(IConstants.CONSTITUENCY_TOTAL_VOTES);//
		headers.add(IConstants.CONSTITUENCY_TOTAL_VOTES_POLLED);//
		headers.add(IConstants.CONSTITUENCY_VALID_VOTES);//
		
		for(String header : mandatoryHeaders){
			boolean notAvailable = true;
			for(Cell cell : cells){
				if(header.equals(cell.getContents())){
					excelHeaderData.put(header,cell.getColumn());
					notAvailable = false;
					break;
				}
			}
			if(notAvailable){
				throw new CsvException(header+" Column Name is Not available in the excel sheet file name :"+file.getName());
			}
		}
		
		for(String header : headers){
			for(Cell cell : cells){
				if(header.equals(cell.getContents())){
					excelHeaderData.put(header,cell.getColumn());
				}
			}
		}
	}

	/**
	 *  reading the excel sheet for uplading the MPTC election data into the DB
	 * @param electionTypeID
	 * @param countryID
	 * @param stateID
	 * @param districtID
	 * @param year
	 * @param file
	 * @return
	 */
	public MPTCElectionResultVO uploadMPTCElectionData(Long electionTypeID,
			Long countryID, Long stateID, Long districtID, String year,
			File file) {
		this.electionTypeID = electionTypeID;
		this.year = year;
		this.stateID = stateID;
		this.districtID = districtID;
		this.file = file;
		this.countryID = countryID;
		MPTCElectionResultVO resultVO = (MPTCElectionResultVO) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus txStatus) {
				MPTCElectionResultVO resultVO = new MPTCElectionResultVO();
				try{
					MptcElectionService.this.readAndInsertData(resultVO);
					
				}catch(Exception ex){
					txStatus.setRollbackOnly();
					resultVO.setExceptionEncountered(ex);
				}
				return resultVO;
			}
		});
		this.electionTypeID = null;
		this.year = null;
		this.stateID = null;
		this.districtID = null;
		this.file = null;
		this.countryID=null;
		return resultVO;
	}
	/**
	 * adding the election into data base if it is not existing in the data base.
	 * @param electionScope
	 * @param eleYear
	 * @return
	 * @throws CsvException
	 */
	private Election checkAndInsertElection(ElectionScope electionScope,String eleYear) throws CsvException{
		Election election = electionDAO.findByESIdEleYear(electionScope,eleYear);
		if(election==null){
			election= new Election();
			election.setElectionScope(electionScope);
			election.setElectionYear(eleYear);
			election = electionDAO.save(election);
		}
		if(election==null || election.getElectionId()==null)
			throw new CsvException("Election object is null after checking and inserting");
		return election;
	}
	
	/**
	 * reading the constituency election, constituency election result from the excel sheet and adding into the DB
	 * @param resultVO
	 * @throws Exception
	 */
	private void readAndInsertData(MPTCElectionResultVO resultVO) throws Exception{
		//Election election = electionDAO.getElectionByCountryStateTypeIDElectionYear(electionTypeID, countryID, stateID, year);
		List<ElectionScope> electionScopes = electionScopeDAO.findByTypeIdCountryIdStateId(electionTypeID, countryID, stateID);
		if(electionScopes==null || electionScopes.size()!=1)
			throw new CsvException("ElectionScope is null or in election_scope table more than one set of values exist for type_id-"+
					electionTypeID +	"countryID-" + countryID + " stateID-" + stateID);
		ElectionScope electionScope = electionScopes.get(0);
		Election election = checkAndInsertElection(electionScope,year);
		
		List<Candidate> candidates = candidateDAO.getAll();
		List<Party> parties = partyDAO.getAll();
		Workbook workbook=Workbook.getWorkbook(file);
		int rowNo = 0;
		Sheet sheet = workbook.getSheet(0);
		addExcelHeaderInfo(sheet.getRow(rowNo++));
		boolean sheetFlag = true;
		while(sheetFlag){
			if(log.isDebugEnabled())
				log.debug("MptcElectionService.readAndInsertData() rowNo:"+rowNo);
			String constituencyName=checkCellData((sheet.getCell(excelHeaderData.get(IConstants.MPTC_NAME),rowNo)).getContents());
			if(constituencyName==null || constituencyName.length()==0)
				throw new CsvException("Constituency Name is empty in Excel File " +
						" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.MPTC_NAME));
			

			String tehsilName=checkCellData((sheet.getCell(excelHeaderData.get(IConstants.MANDAL_NAME),rowNo)).getContents());
			if(tehsilName==null || tehsilName.length()==0)
				throw new CsvException("Tehsil Name is empty in Excel File " +
						" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.MANDAL_NAME));
			
			List<Constituency> constituencies = constituencyDAO.findByConstituencyNameDistrictIdTehsilName(
					constituencyName, districtID, tehsilName);
			if(constituencies==null || constituencies.size()==0)
				throw new CsvException("Constituency " + constituencyName + " not exist in DB and Excel File " +
						" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.MPTC_NAME));
			
			int candidateSize = getConstituencyCandidateSize(sheet, rowNo);
			
			Constituency constituency = constituencies.get(0);
			ConstituencyElection constituencyElection = new ConstituencyElection();
			constituencyElection.setElection(election);
			constituencyElection.setConstituency(constituency);
			if (excelHeaderData.get(IConstants.CONSTITUENCY_RESERVATION_ZONE) != null){
				constituencyElection.setReservationZone(checkCellData((sheet
						.getCell(excelHeaderData
								.get(IConstants.CONSTITUENCY_RESERVATION_ZONE),
								rowNo)).getContents()));
			}
			constituencyElection = constituencyElectionDAO.save(constituencyElection);
			resultVO.addConstituencyElections();
			
			ConstituencyElectionResult constituencyElectionResult = new ConstituencyElectionResult();
			constituencyElectionResult.setConstituencyElection(constituencyElection);
			constituencyElectionResult = collectConstituencyElectionResultData(sheet, constituencyElectionResult, rowNo);
			
			resultVO.addConstituencyElectionResults();
			addCandidateResultToDB(sheet, rowNo, candidateSize, candidates, parties, constituencyElection,resultVO,constituencyElectionResult.getValidVotes());
			log.debug("MptcElectionService.readAndInsertData() candidateSize:"+candidateSize);			
			rowNo = rowNo + candidateSize;
			constituencyName = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.MPTC_NAME), rowNo)).getContents());
			String candidateName=checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_NAME), rowNo)).getContents());
			if((constituencyName==null || constituencyName.length()==0) && (candidateName==null || candidateName.length()==0)){
				sheetFlag = false;
				log.info("End of the File since No Candidate and Constituency Name exist in excel sheet at Row No. :"+rowNo);
			}
		}
	}
	/**
	 *  reading constituency election result data from excel sheet and save to data base
	 * @param sheet
	 * @param constituencyElectionResult
	 * @param rowNo
	 * @return
	 * @throws Exception
	 */
	private ConstituencyElectionResult collectConstituencyElectionResultData(
			Sheet sheet, ConstituencyElectionResult constituencyElectionResult,
			int rowNo) throws Exception {
		String constituencyName = constituencyElectionResult.getConstituencyElection().getConstituency().getName();

		String totalVotes = null;
		if(excelHeaderData.get(IConstants.CONSTITUENCY_TOTAL_VOTES)!=null)
			totalVotes = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CONSTITUENCY_TOTAL_VOTES),rowNo)).getContents());
		if(totalVotes==null || !NumberUtils.isNumber(totalVotes.trim())){
			//throw new CsvException
			log.info("Constituency " + constituencyName + " not valid totalVotes in Excel File " +
					" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.CONSTITUENCY_TOTAL_VOTES));
		} else {
			Double totalVotesDouble = new Double(totalVotes.trim());
			constituencyElectionResult.setTotalVotes(totalVotesDouble);
		}
		
		String validVotes = null;
		if(excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES)!=null)
		 validVotes = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES),rowNo)).getContents());
		if(validVotes==null || !NumberUtils.isNumber(validVotes.trim())){
			//throw new CsvException
			log.info("Constituency " + constituencyName + " not valid valid-votes in Excel File " +
					" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES));
		} else {
			Double validVotesDouble = new Double(validVotes.trim());
			constituencyElectionResult.setValidVotes(validVotesDouble);
		}
		

		String polledVotes = null;
		if(excelHeaderData.get(IConstants.CONSTITUENCY_TOTAL_VOTES_POLLED)!=null)
			polledVotes = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CONSTITUENCY_TOTAL_VOTES_POLLED),rowNo)).getContents());
		if(polledVotes==null || !NumberUtils.isNumber(polledVotes.trim())){
			//throw new CsvException
			log.info("Constituency " + constituencyName + " not valid polledVotes in Excel File " +
					" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.CONSTITUENCY_TOTAL_VOTES_POLLED));
		} else {
			Double polledVotesDouble = new Double(polledVotes.trim());
			constituencyElectionResult.setTotalVotesPolled(polledVotesDouble);
		}
		if(excelHeaderData.get(IConstants.CONSTITUENCY_REJECTED_VOTES)!=null){
			try{
				constituencyElectionResult.setRejectedVotes(new Double(
						checkCellData((sheet.getCell(excelHeaderData
								.get(IConstants.CONSTITUENCY_REJECTED_VOTES),
								rowNo)).getContents())));
			}catch(Exception ex){
				log.info("Constituency " + constituencyName + " not valid invalid Votes in Excel File " +
						" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.CONSTITUENCY_REJECTED_VOTES));
			}
			
		}
		
		/*if(excelHeaderData.get(IConstants.CONSTITUENCY_VOTING_PERCENTAGE)==null){
			constituencyElectionResult.setVotingPercentage(String.valueOf((polledVotesDouble*100)/totalVotesDouble));
		}else{
			String polledVotesPercentage = checkCellData((sheet.getCell(excelHeaderData.get(
					IConstants.CONSTITUENCY_VOTING_PERCENTAGE), rowNo)).getContents());
			if(polledVotesPercentage==null || polledVotesPercentage.length()==0 || !NumberUtils.isNumber(polledVotesPercentage.trim())){
				constituencyElectionResult.setVotingPercentage(String.valueOf((polledVotesDouble*100)/totalVotesDouble));
			}
			constituencyElectionResult.setVotingPercentage(polledVotesPercentage);
		}*/
		if(excelHeaderData.get(IConstants.CONSTITUENCY_VOTING_PERCENTAGE)!=null){
			String polledVotesPercentage = checkCellData((sheet.getCell(excelHeaderData.get(
					IConstants.CONSTITUENCY_VOTING_PERCENTAGE), rowNo)).getContents());
			constituencyElectionResult.setVotingPercentage(polledVotesPercentage);
		}
		
		if(excelHeaderData.get(IConstants.CONSTITUENCY_TENDERED_VOTES)!=null){
			String data = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CONSTITUENCY_TENDERED_VOTES), rowNo)).getContents());
			if(data!=null && NumberUtils.isNumber(data))
				constituencyElectionResult.setTenderedVotes(new Double(data));
		}
		if(excelHeaderData.get(IConstants.CONSTITUENCY_MISSING_VOTES)!=null){
			String data = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CONSTITUENCY_MISSING_VOTES), rowNo)).getContents());
			if(data!=null && NumberUtils.isNumber(data.trim())){
				try{
					constituencyElectionResult.setMissingVotes(new Double(data));
				}catch(Exception ex){
					log.info("Constituency " + constituencyName + " not valid missing Votes in Excel File " +
							" Row No:" + rowNo + " Column No:" + excelHeaderData.get(IConstants.CONSTITUENCY_MISSING_VOTES));
				}
			}
		}	
		constituencyElectionResult = constituencyElectionResultDAO.save(constituencyElectionResult);
		
		return constituencyElectionResult;
	}
	
	/**
	 * reading the candidates, parties, candidate results from the excel sheet and adding into the DB
	 * @param sheet
	 * @param rowNo
	 * @param candidateSize
	 * @param candidates
	 * @param parties
	 * @param constituencyElection
	 * @param resultVO
	 * @throws Exception
	 */
	private void addCandidateResultToDB(Sheet sheet,int rowNo, int candidateSize, 
									 List<Candidate> candidates, List<Party> parties,
									ConstituencyElection constituencyElection,
									MPTCElectionResultVO resultVO, Double constituencyTotalValidVotes) throws Exception{
		double[] sortedCandidateVotes = new double[candidateSize];
		double totalSumOfCandidateVotes = 0;
		for(int i=0; i<candidateSize; i++){
			String candidateVotesEarned = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_VOTES_EARNED), rowNo+i)).getContents());
			if(candidateVotesEarned==null || candidateVotesEarned.length()==0)
				throw new CsvException("Candidate earned Votes is not valid in Excel File " +
						" Row No:" + (rowNo+i) + " Column No:" + excelHeaderData.get(IConstants.CANDIDATE_VOTES_EARNED));
			try{
				double votesEarned =Double.parseDouble(candidateVotesEarned) ;
				sortedCandidateVotes[i] = votesEarned;
				totalSumOfCandidateVotes += votesEarned;
			}catch(NumberFormatException ex){
				throw new CsvException("Candidate earned Votes is not valid number in Excel File " +
						" Row No:" + (rowNo+i) + " Column No:" + excelHeaderData.get(IConstants.CANDIDATE_VOTES_EARNED));
			
			}
		}
		Arrays.sort(sortedCandidateVotes);
		
		for(int i=0; i<candidateSize; i++){
			String candidateName = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_NAME), (rowNo+i))).getContents());
			if(candidateName==null || candidateName.length()==0)
				throw new CsvException("Candidate Name is not valid in Excel File " +
						" Row No:" + (rowNo+i) + " Column No:" + excelHeaderData.get(IConstants.CANDIDATE_NAME));
			
			String partyName = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.PARTY_NAME), rowNo+i)).getContents());
			if(partyName==null || partyName.length()==0)
				throw new CsvException("Party Name is not valid in Excel File " +
						" Row No:" + (rowNo+i) + " Column No:" + excelHeaderData.get(IConstants.PARTY_NAME));
			
			Candidate candidate = checkAndInsertCandidate(candidates, candidateName, sheet, rowNo+i, resultVO);
			Party party = checkAndInsertParty(parties, partyName, resultVO);
			if(party==null)
				throw new CsvException("Party not available in DB of Excel File at" +
						" Row No:" + (rowNo+i) + " Column No:" + excelHeaderData.get(IConstants.PARTY_NAME));
			Nomination nomination = new Nomination();
			nomination.setCandidate(candidate);
			nomination.setParty(party);
			nomination.setConstituencyElection(constituencyElection);
			nomination = nominationDAO.save(nomination);
			resultVO.addNominations();
			CandidateResult candidateResult = new CandidateResult();
			candidateResult.setNomination(nomination);

			String votesEarned = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_VOTES_EARNED), rowNo+i)).getContents());
			if(votesEarned==null || !NumberUtils.isNumber(votesEarned.trim()))
				throw new CsvException("Candidate votes Earned is not valid in Excel File " +
						" Row No:" + (rowNo+i) + " Column No:" + excelHeaderData.get(IConstants.CANDIDATE_VOTES_EARNED));

			Double votes = new Double(votesEarned.trim());
			candidateResult.setVotesEarned(votes);
			
			for(int index = 0; index<candidateSize; index++){
				if(votes==sortedCandidateVotes[index]){
					candidateResult.setRank(new Long(index+1));
					sortedCandidateVotes[index] = -1;
					break;
				}
			}
			
			/*Double constituencyValidVotes = totalSumOfCandidateVotes;
			if(excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES)!=null){
				try{
					constituencyValidVotes = new Double(
							checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES), 
							rowNo)).getContents()));
				}catch(Exception ex){
					log.info(ex.getMessage());
					constituencyValidVotes = totalSumOfCandidateVotes;
				}
			}
			String candidateVotesEarnedPercentage = String.valueOf((votes*100)/constituencyValidVotes);
			*/
			/*if(excelHeaderData.get(IConstants.CANDIDATE_VOTES_PERCENTAGE)!=null){
				candidateVotesEarnedPercentage = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_VOTES_PERCENTAGE), rowNo+i)).getContents());
				if(candidateVotesEarnedPercentage==null || candidateVotesEarnedPercentage.length()==0){
					
					if(excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES)!=null){
						try{
							constituencyValidVotes = new Double(
									checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES), 
									rowNo)).getContents()).trim());
						}catch(Exception ex){
							log.info(ex.getMessage());
							constituencyValidVotes = totalSumOfCandidateVotes;
						}
					}
					if(constituencyValidVotes==0){
						constituencyValidVotes = totalSumOfCandidateVotes;*/
						/*throw new CsvException("constituency valid votes is zero which is not valid in Excel File " +
								" Row No:" + rowNo+i + " Column No:" + excelHeaderData.get(IConstants.CONSTITUENCY_VALID_VOTES));*/
					/*}
					candidateVotesEarnedPercentage = String.valueOf((votes*100)/totalSumOfCandidateVotes);
				}
			}
				*/
			if (excelHeaderData.get(IConstants.CANDIDATE_VOTES_PERCENTAGE) != null) {
				candidateResult.setVotesPercengate(
						checkCellData((sheet.getCell(excelHeaderData.get(
								IConstants.CANDIDATE_VOTES_PERCENTAGE),rowNo + i)
						).getContents()));
			} else {
				if(constituencyTotalValidVotes==null || constituencyTotalValidVotes==0L){
					constituencyTotalValidVotes = totalSumOfCandidateVotes;
				}
				Double percentage = 0D;
				if(constituencyTotalValidVotes!=0D)
					percentage = (votes*100)/constituencyTotalValidVotes;
				String candidateVotesEarnedPercentage = new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				candidateResult.setVotesPercengate(candidateVotesEarnedPercentage);
			}
			//candidateResult.setVotesPercengate(candidateVotesEarnedPercentage);
			candidateResult = candidateResultDAO.save(candidateResult);
			resultVO.addCandidateResults();
		}
	}
	
	private static String checkCellData(String cellData){
		String cellContect="";
		if(cellData!=null){
			if(cellData.length()>0){
				cellContect=cellData.trim();
			}
		}
		return cellContect;
	}
	/**
	 * retrieving the no. of candidates participating in a constituency
	 * @param sheet
	 * @param row
	 * @return
	 */
	private int getConstituencyCandidateSize(Sheet sheet, int row){
		int candidateSize = 0;
		boolean candidateFlag = true;
		String prevConstituencyName=checkCellData((sheet.getCell(excelHeaderData.get(IConstants.MPTC_NAME),row)).getContents());
		//String candidateName=checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_NAME),row)).getContents());
		candidateSize++;
		while(candidateFlag){
			String presentConstituencyName=checkCellData((sheet.getCell(excelHeaderData.get(IConstants.MPTC_NAME),++row)).getContents());
			boolean isNewConstituency = (presentConstituencyName!=null) && (presentConstituencyName.length()>0) && !prevConstituencyName.equals(presentConstituencyName);
			String candidateName=checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_NAME),row)).getContents());
			if(candidateName.length()==0 || isNewConstituency){
				candidateFlag = false;
			}else{
				candidateSize++;
			}
		}
		return candidateSize;
	}
	public static void main(String args[]) throws Exception{
		File file = new File("C:/Documents and Settings/USER/Desktop/MPTC/test.xls");
		Workbook workbook=Workbook.getWorkbook(file);
		int rowNo = 0;
		Sheet sheet = workbook.getSheet(0);
		MptcElectionService service = new MptcElectionService();
		service.addExcelHeaderInfo(sheet.getRow(rowNo++));
		System.out.println(service.getConstituencyCandidateSize( sheet, rowNo));
		service.addCandidateResultToDB( sheet, rowNo, 5, 
				 new ArrayList(), new ArrayList(),
				new ConstituencyElection(),
				new MPTCElectionResultVO(),null);
	}
	/**
	 * adding the candidate into DB if the candidate is not existing in DB
	 * @param candidates
	 * @param candidateName
	 * @param sheet
	 * @param row
	 * @param resultVO
	 * @return
	 * @throws Exception
	 */
	private Candidate checkAndInsertCandidate(List<Candidate> candidates,
			String candidateName, Sheet sheet, int row, 
			MPTCElectionResultVO resultVO) throws Exception{
		boolean candidateFlag = true;
		Candidate lcandidateObj = null;
		if(candidates!=null && candidates.size()>0){
			for(Candidate can: candidates){
				if(candidateName.equalsIgnoreCase(can.getLastname().trim())){
					lcandidateObj = can;
					candidateFlag = false;
					break;
				}
			}
		}
		if(candidateFlag){
			lcandidateObj = new Candidate();
			lcandidateObj.setLastname(candidateName);
			if(excelHeaderData.get(IConstants.CANDIDATE_GENDER)!=null)
				lcandidateObj.setGender(checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_GENDER),row)).getContents()));
			if(excelHeaderData.get(IConstants.CANDIDATE_EDUCATION)!=null)
				lcandidateObj.setEducation(checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_EDUCATION),row)).getContents()));
			if(excelHeaderData.get(IConstants.CANDIDATE_ADDRESS)!=null)
				lcandidateObj.setAddress(checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_ADDRESS),row)).getContents()));
			if(excelHeaderData.get(IConstants.CANDIDATE_MOBILE)!=null)
				lcandidateObj.setMobile(checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_MOBILE),row)).getContents()));
			if(excelHeaderData.get(IConstants.CANDIDATE_PHONE)!=null)
				lcandidateObj.setPhone(checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_PHONE),row)).getContents()));
			if(excelHeaderData.get(IConstants.CANDIDATE_EMAIL)!=null)
				lcandidateObj.setEmailAddress(checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_EMAIL),row)).getContents()));
			if(excelHeaderData.get(IConstants.CANDIDATE_DATE_OF_BIRTH)!=null){
				String age = checkCellData((sheet.getCell(excelHeaderData.get(IConstants.CANDIDATE_DATE_OF_BIRTH),row)).getContents());
				int candidateAge = 0;
				try{
					candidateAge = Integer.parseInt(age);
					candidateAge = new Integer(this.year)-candidateAge-1900;
				}catch(NumberFormatException ex){
					throw new CsvException("Number Format Exception age is not a number which is not valid in Excel File " +
							" Row No:" + row + " Column No:" + excelHeaderData.get(IConstants.CANDIDATE_DATE_OF_BIRTH));
				}
				lcandidateObj.setDateofbirth(new Date(candidateAge,0,1));
			}
			
			lcandidateObj=candidateDAO.save(lcandidateObj);
			candidates.add(lcandidateObj);
			resultVO.addCandidates();
		}
		return lcandidateObj;
	}

	/**
	 * adding the party in DB if it is not existing in DB
	 * @param parties - list of parties in the database
	 * @param partyName
	 * @param resultVO
	 * @return
	 */
	private Party checkAndInsertParty(List<Party> parties,String partyName, MPTCElectionResultVO resultVO){
		boolean partyFlag = true;
		Party lpartyObj = null;
		if(parties!=null && parties.size()>0){
			for(Party par: parties){
				if(partyName.equalsIgnoreCase(par.getLongName().trim()) || partyName.equalsIgnoreCase(par.getShortName().trim())){
					lpartyObj = par;
					partyFlag = false;
					break;
				}
			}
		}
		if(partyFlag){
			/*if(log.isDebugEnabled())
				log.debug("New party has been identified.");
			lpartyObj= new Party();
			lpartyObj.setLongName(partyName);
			String shortName=(partyName.length()>25)?"":partyName;
			lpartyObj.setShortName(shortName);
			lpartyObj=partyDAO.save(lpartyObj);
			parties.add(lpartyObj);
			resultVO.addParties();*/
			log.debug("Party Name:::::::::::::::::::::::::::::"+partyName);
			lpartyObj=null;
		}

		return lpartyObj;
	}
	public static void main1(String[] arg)throws Exception{
		File file = new File("c:/testing.xsl");
		System.out.println(new Date(110,0,1));
	}

}
