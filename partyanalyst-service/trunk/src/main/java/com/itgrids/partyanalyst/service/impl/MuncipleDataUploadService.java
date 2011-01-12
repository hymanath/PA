package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
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
import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.dao.IElectionGoverningBodyPositionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.MPTCElectionResultVO;
import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;
import com.itgrids.partyanalyst.model.ElectionGoverningBodyPosition;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IMuncipleDataUploadService;
import com.itgrids.partyanalyst.utils.CandidateDetailsVOComparator;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author ITGRIDS1
 *
 */
public class MuncipleDataUploadService implements IMuncipleDataUploadService{
	
	private IElectionTypeDAO electionTypeDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IElectionDAO electionDAO;
	private IPartyDAO partyDAO;
	private IStateDAO stateDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private INominationDAO nominationDAO;
	private ICandidateDAO candidateDAO;
	private ICandidateResultDAO candidateResultDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IElectionGoverningBodyDAO electionGoverningBodyDAO;
	private IElectionGoverningBodyPositionDAO electionGoverningBodyPositionDAO;
	private TransactionTemplate transactionTemplate;
	private Map<String, Integer> excelHeaderData = new HashMap<String, Integer>();
	private File filePath;
	private Long stateId;
	private Long electionTypeId;
	private String electionYear;
	private String elecSubtype;
	private static Logger log = Logger.getLogger(MptcElectionService.class);
		
	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}
	
	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}
	
	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}
	
	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
	
	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}
	
	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}
	
	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}

	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}

	public IElectionGoverningBodyPositionDAO getElectionGoverningBodyPositionDAO() {
		return electionGoverningBodyPositionDAO;
	}

	public void setElectionGoverningBodyPositionDAO(
			IElectionGoverningBodyPositionDAO electionGoverningBodyPositionDAO) {
		this.electionGoverningBodyPositionDAO = electionGoverningBodyPositionDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public MPTCElectionResultVO readExcelDataForMuncipalities(File file, Long electionTypeId, 
			Long stateId, String electionYear, String elecSubtype){
		this.filePath = file;
		this.stateId = stateId;
		this.electionYear = electionYear;
		this.electionTypeId = electionTypeId;
		this.elecSubtype = elecSubtype;
		MPTCElectionResultVO resultVO = (MPTCElectionResultVO) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus txStatus) {
				MPTCElectionResultVO resultVO = new MPTCElectionResultVO();
				try{
					readAndInsertData(resultVO);
					
				}catch(Exception ex){
					System.out.println("Exeption at ::"+resultVO.getCurrentRow());
					txStatus.setRollbackOnly();
					resultVO.setExceptionEncountered(ex);
				}
				return resultVO;
			}
		});
		return resultVO;			
	}
	
	private void readAndInsertData(MPTCElectionResultVO resultVO) throws Exception {
		
		Workbook workbook = Workbook.getWorkbook(filePath);
		Sheet[] sheets = workbook.getSheets();
		int row = 0;
		String districtName = "";
		String tehsilName = "";
		String localElecBodyName = "";
		String wardNo = "";
		Long constituencyTotalVoters = 0l;
		Long validVotes = 0l;
		String votingPercenatge = "";
		String reservedCast = "";
		ElectionScope electionScope = null;
		List<LocalElectionBody> localElectionBodies = null;
		Election election = null;
		ElectionType electionType = null;
		State state = null;
		LocalElectionBody localElectionBody = null;
		List constituencyElections = null;
		ConstituencyElection constituencyElection = null;
		ConstituencyElectionResult constituencyElectionResult = null;
		Constituency constituency = null;
		Map<String, Constituency> wardsInMuncipalityMap = null;
		List<Party> parties = partyDAO.getAll();
		String governingPosition = "";
		String subGoverningPosition = "";

		//Verifying the election scope is already exist or not
		List<ElectionScope> electionScopes = electionScopeDAO.findByPropertyElectionTypeIdandStateId(electionTypeId, stateId);
		electionType = electionTypeDAO.get(electionTypeId);
		state = stateDAO.get(stateId);
		
		//If election scope doesn't exist create new election code else throw exception
		if(electionScopes.size() == 0){
			electionScope = new ElectionScope();
			electionScope.setElectionType(electionType);
			electionScope.setState(stateDAO.get(stateId));
			electionScope.setCountry(state.getCountry());
			electionScope = electionScopeDAO.save(electionScope);	
		}else{
			if(electionScopes.size() > 1)
				throw new Exception("There are \'"+electionScopes.size()+"\' no.of ElectionScopes Exists With Election Type Id:"+electionTypeId+" and StateId:"+stateId);
			
			electionScope = electionScopes.get(0);
		}
		
		if(electionType.getElectionType().equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE)){
			governingPosition = IConstants.MAYOR;
			subGoverningPosition = IConstants.DYPUTY_MAYOR;
		}else if(electionType.getElectionType().equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE)){
			governingPosition = IConstants.MUNCIPALE_CHAIRMAN;
			subGoverningPosition = IConstants.MUNCIPALE_VICE_CHAIRMAN;
		}
		
		//Verfifying the election is already exist or not
		election = electionDAO.findByElectionScopeIdElectionYear(electionScope.getElectionScopeId(), electionYear, elecSubtype);
		
		if(election == null){
			election = new Election();
			election.setElectionScope(electionScope);
			election.setElectionYear(electionYear);
			election.setElecSubtype(elecSubtype);
			election = electionDAO.save(election);
		}	
		
		//Reading each sheet in the excel
		for(Sheet sheet:sheets){
			createColumnsMap(sheet.getRow(row++));
			resultVO.setCurrentRow(1);
			
			//Reading each row in the excel 
			for(;row < sheet.getRows();){
				resultVO.setCurrentRow(row+1);
				
				validVotes = 0l;
				constituencyTotalVoters = 0l;
				
				districtName = checkCellData(sheet,IConstants.DISTRICT_NAME,row);
				tehsilName = checkCellData(sheet,IConstants.MANDAL_NAME,row);
				localElecBodyName = checkCellData(sheet,IConstants.LOCAL_ELECTION_BODY,row);
				wardNo = checkCellData(sheet,IConstants.MUNCIPLE_WARD_NO,row);
				
				//District Name, Tehsil Name, Local Election Body Name (Municipality Name) exist for 
				//each muncipality when it starts
				if(districtName.length() > 0 && tehsilName.length() > 0 && localElecBodyName.length() > 0){
						
					localElectionBodies = localElectionBodyDAO.findByElectionTypeDistrictTehsilAndLEBName(electionTypeId,
							districtName, tehsilName, localElecBodyName);
					if(localElectionBodies.size() != 1)
						throw new Exception("There are "+localElectionBodies.size()+" no.of Local Bodies Exists With Name:"+localElecBodyName +
								" at Row:"+row);
					localElectionBody = localElectionBodies.get(0);
					
					if(excelHeaderData.get(IConstants.GOVERNING_BODY) != null)
						createElectionGoverningBody(election, localElectionBody, sheet, row, IConstants.GOVERNING_BODY, 
								IConstants.GOVERNING_BODY_PARTY, electionScope.getElectionType().getElectionType(), governingPosition, parties);
					
					if(excelHeaderData.get(IConstants.SUB_GOVERNING_BODY) != null)
						createElectionGoverningBody(election, localElectionBody, sheet, row, IConstants.SUB_GOVERNING_BODY, 
								IConstants.SUB_GOVERNING_BODY_PARTY, electionScope.getElectionType().getElectionType(), subGoverningPosition, parties);
					
					wardsInMuncipalityMap = createSelectOptionVO(constituencyDAO.findWardsAndIdsInMuncipality(localElectionBody.getLocalElectionBodyId()));
					
				}
				
				if(wardNo.length() == 0)
					throw new Exception("Ward No Missing At Row ::"+row);
					
				System.out.println("Constituency Row No:"+(row+1));
				
				constituency = wardsInMuncipalityMap.get(IConstants.MUNCIPLE_WARD+"-"+wardNo);
			
				if(constituency == null){
					constituencyDAO.flushAndclearSession();
					constituency = new Constituency();
					constituency.setName(IConstants.MUNCIPLE_WARD+"-"+wardNo);
					constituency.setLocalElectionBody(localElectionBody);
					constituency = constituencyDAO.save(constituency);
					wardsInMuncipalityMap.put(constituency.getName(), constituency);
				}
				
				constituencyElections = constituencyElectionDAO.findByElectionAndConstituency(election.getElectionId(), constituency.getConstituencyId());
				reservedCast = checkCellData(sheet, IConstants.CONSTITUENCY_RESERVATION_ZONE, row);
				
				if(constituencyElections.size() == 0){
					constituencyElection = new ConstituencyElection();
					constituencyElection.setElection(election);
					constituencyElection.setConstituency(constituency);
					constituencyElection.setReservationZone(reservedCast);
					constituencyElection = constituencyElectionDAO.save(constituencyElection);
					
					if(checkCellData(sheet, IConstants.CONSTITUENCY_TOTAL_VOTES, row).length() > 0)
						constituencyTotalVoters = checkForNumber(checkCellData(sheet, IConstants.CONSTITUENCY_TOTAL_VOTES, row), IConstants.CONSTITUENCY_TOTAL_VOTES, row);
					
					if(checkCellData(sheet, IConstants.CONSTITUENCY_VALID_VOTES, row).length() == 0)
						throw new Exception("CONSTITUENCY_VALID_VOTES Missing At Row ::"+row);
					
					validVotes = checkForNumber(checkCellData(sheet, IConstants.CONSTITUENCY_VALID_VOTES, row), IConstants.CONSTITUENCY_VALID_VOTES, row);
					
					if(constituencyTotalVoters > 0 && validVotes > 0)
						votingPercenatge = new BigDecimal(validVotes*100.0/constituencyTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					
					constituencyElectionResult = new ConstituencyElectionResult();
					constituencyElectionResult.setTotalVotes(new Double(constituencyTotalVoters));
					constituencyElectionResult.setValidVotes(new Double(validVotes));
					constituencyElectionResult.setVotingPercentage(votingPercenatge);
					constituencyElectionResult.setConstituencyElection(constituencyElection);
					constituencyElectionResultDAO.save(constituencyElectionResult);
					
				}else{
					throw new Exception("Data All Ready Exists For Ward No::"+wardNo+"At Row::"+row);
				}
				
				row = storeConstituencyAndCandidatesInfo(constituencyElection, sheet, row, wardNo, validVotes, parties, resultVO);
				
			}
		}
	}
	
	private Map<String, Constituency> createSelectOptionVO(List<Constituency> wardsAndIdsInMuncipality) {
		Map<String, Constituency> wards = new HashMap<String, Constituency>();
		for(Constituency constituency:wardsAndIdsInMuncipality)
			wards.put(constituency.getName(), constituency);
		return wards;
	}

	private void createElectionGoverningBody(Election election,
			LocalElectionBody localElectionBody, Sheet sheet, int row, String candidateColumn,
			String partyColumn, String electionType, String position, List<Party> parties) throws Exception{
		List<ElectionGoverningBodyPosition> electionGoverningBodyPositions = null;
		String candidateName = checkCellData(sheet, candidateColumn, row);
		String partyName = checkCellData(sheet, partyColumn, row);
		
		if(candidateName.length() == 0 && partyName.length() == 0)
			return;
		if((candidateName.length() == 0 && partyName.length() > 0)||(candidateName.length() > 0 && partyName.length() == 0))
			throw new Exception("Inconsistant Data:: Either "+candidateColumn+" Or "+partyColumn+" Missing at Row::"+row);
		
		electionGoverningBodyPositions = electionGoverningBodyPositionDAO.findByPosition(position);
		if(electionGoverningBodyPositions.size() != 1)
			throw new Exception("There are "+electionGoverningBodyPositions.size()+" no.of Positions Exists With Name:"+IConstants.MUNCIPALE_CHAIRMAN +
					" at Row:"+row);
		
		ElectionGoverningBodyPosition electionGoverningBodyPosition = electionGoverningBodyPositions.get(0);
		
		CandidateDetailsVO candidateInfo = new CandidateDetailsVO();
		candidateInfo.setCandidateName(candidateName);
		candidateInfo.setGender("");
		
		electionGoverningBodyDAO.save(new ElectionGoverningBody(checkAndInsertCandidate(candidateInfo), 0l, localElectionBody, election,
				electionGoverningBodyPosition, checkAndInsertParty(parties, partyName)));
		
	}

	private int storeConstituencyAndCandidatesInfo(ConstituencyElection constituencyElection, Sheet sheet,
			int row, String wardNo, Long validVotes, List<Party> parties, MPTCElectionResultVO resultVO) throws Exception{
		String currentWard = "";
		String candidateName = "";
		Long age = 0l;
		String gender = "";
		String party = "";
		Long candidateVotes = 0l;
		String percentage = "";
		
		List<CandidateDetailsVO> candidatesInfo = new ArrayList<CandidateDetailsVO>();
		CandidateDetailsVO candidateInfo = null;
		
		while(row < sheet.getRows()){
			age = 0l;
			currentWard = checkCellData(sheet, IConstants.MUNCIPLE_WARD_NO, row);
			candidateInfo = new CandidateDetailsVO();
			candidateName = checkCellData(sheet, IConstants.CANDIDATE_NAME, row);
			if(checkCellData(sheet, IConstants.CANDIDATE_AGE, row).length() > 0)
				age = checkForNumber(checkCellData(sheet, IConstants.CANDIDATE_AGE, row), IConstants.CANDIDATE_AGE, row);	
			gender = checkCellData(sheet, IConstants.CANDIDATE_GENDER, row);
			candidateVotes = checkForNumber(checkCellData(sheet, IConstants.CANDIDATE_VOTES_EARNED, row), IConstants.CANDIDATE_VOTES_EARNED, row);
			percentage = checkCellData(sheet, IConstants.CANDIDATE_VOTES_PERCENTAGE, row);
			party = checkCellData(sheet, IConstants.PARTY_NAME, row);
			
			if(percentage.length() == 0 && validVotes > 0)
				percentage = new BigDecimal(candidateVotes*100.0/validVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			
			candidateInfo.setCandidateName(candidateName);
			candidateInfo.setAge(age);
			candidateInfo.setGender(gender);
			candidateInfo.setCandidateVotesEarned(candidateVotes);
			candidateInfo.setVotesPercentage(percentage);
			candidateInfo.setPartyName(party);
			candidateInfo.setCurrentRowNo(new Long(row));
			
			if(!wardNo.equalsIgnoreCase(currentWard) && currentWard.length() > 0)
				break;
			candidatesInfo.add(candidateInfo);
			row++;
		}
		
		Collections.sort(candidatesInfo, new CandidateDetailsVOComparator());
		for(int i=0; i<candidatesInfo.size(); i++)
			candidatesInfo.get(i).setRank(new Long(i+1));
			
		storeCandidateInfoAndNomination(candidatesInfo, constituencyElection, parties);
		
		return row;
	}

	private void storeCandidateInfoAndNomination(
			List<CandidateDetailsVO> candidatesInfo,
			ConstituencyElection constituencyElection, List<Party> parties) throws Exception{
		Nomination nomination = null;
		CandidateResult candidateResult = null;
		Party party = null;
		Candidate candidate = null;
		for(CandidateDetailsVO candidateDetailsVO:candidatesInfo){
			
			candidate = checkAndInsertCandidate(candidateDetailsVO);	
			
			party = checkAndInsertParty(parties, candidateDetailsVO.getPartyName());
				
			nomination = new Nomination();
			nomination.setConstituencyElection(constituencyElection);
			nomination.setCandidate(candidate);
			nomination.setParty(party);
			nomination = nominationDAO.save(nomination);
			
			candidateResult = new CandidateResult();
			candidateResult.setVotesEarned(new Double(candidateDetailsVO.getCandidateVotesEarned()));
			candidateResult.setVotesPercengate(candidateDetailsVO.getVotesPercentage());
			candidateResult.setRank(candidateDetailsVO.getRank());
			candidateResult.setNomination(nomination);
			candidateResultDAO.save(candidateResult);
			
		}
		
	}

	private Long checkForNumber(String value, String columnName, int row) throws Exception{
		if(!StringUtils.isBlank(value) && StringUtils.isNumeric(value))
			return new Long(value);	
		else
			throw new Exception(columnName+"::"+value+" is Not A Number at Row::"+row);
	}
	
	private void createColumnsMap(Cell[] row) throws Exception{

		// if required column headers are not available in excel sheet then throwing the exception 
		// thats why working with 2 ArrayList objects
		List<String> mandatoryHeaders = new ArrayList<String>();
		List<String> headers = new ArrayList<String>();
		mandatoryHeaders.add(IConstants.CANDIDATE_NAME);//
		mandatoryHeaders.add(IConstants.PARTY_NAME);//
		mandatoryHeaders.add(IConstants.CANDIDATE_VOTES_EARNED);//
		mandatoryHeaders.add(IConstants.MANDAL_NAME);
		mandatoryHeaders.add(IConstants.MUNCIPLE_WARD_NO);
		mandatoryHeaders.add(IConstants.DISTRICT_NAME);
		mandatoryHeaders.add(IConstants.LOCAL_ELECTION_BODY);
		mandatoryHeaders.add(IConstants.LOCAL_ELECTION_BODY);
		mandatoryHeaders.add(IConstants.CONSTITUENCY_VALID_VOTES);//
		
		headers.add(IConstants.CANDIDATE_VOTES_PERCENTAGE);
		headers.add(IConstants.CANDIDATE_GENDER);
		headers.add(IConstants.CANDIDATE_EDUCATION);
		headers.add(IConstants.CANDIDATE_ADDRESS);
		headers.add(IConstants.CANDIDATE_MOBILE);
		headers.add(IConstants.CANDIDATE_PHONE);
		headers.add(IConstants.CANDIDATE_EMAIL);
		headers.add(IConstants.CANDIDATE_AGE);
		headers.add(IConstants.CANDIDATE_DATE_OF_BIRTH);
		headers.add(IConstants.CONSTITUENCY_VOTING_PERCENTAGE);//
		headers.add(IConstants.CONSTITUENCY_TENDERED_VOTES);
		headers.add(IConstants.CONSTITUENCY_MISSING_VOTES);
		headers.add(IConstants.CONSTITUENCY_REJECTED_VOTES);//
		headers.add(IConstants.CONSTITUENCY_RESERVATION_ZONE);
		headers.add(IConstants.CONSTITUENCY_TOTAL_VOTES);//
		headers.add(IConstants.CONSTITUENCY_TOTAL_VOTES_POLLED);//
		headers.add(IConstants.ELECTION_UPLOAD_DISTRICT_COLUMN);
		headers.add(IConstants.GOVERNING_BODY);
		headers.add(IConstants.GOVERNING_BODY_PARTY);
		headers.add(IConstants.SUB_GOVERNING_BODY);
		headers.add(IConstants.SUB_GOVERNING_BODY_PARTY);
		
		for(String header : mandatoryHeaders){
			boolean notAvailable = true;
			for(Cell cell : row){
				if(header.equals(cell.getContents())){
					excelHeaderData.put(header,cell.getColumn());
					notAvailable = false;
					break;
				}
			}
			if(notAvailable){
				throw new CsvException(header+" Column Name is Not available in the excel sheet file name :");
			}
		}
		
		for(String header : headers){
			log.debug("Column Names::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			for(Cell cell : row){
				log.debug(cell.getContents());
				if(header.equals(cell.getContents())){
					excelHeaderData.put(header,cell.getColumn());
					log.debug("Selected Column Name::"+cell.getContents());
				}
			}
		}
	
	}
	
	private String checkCellData(Sheet sheet, String columnName, int row){
		String cellData = "";
		String cellContent="";
		if(excelHeaderData.get(columnName) != null)
			cellData = sheet.getCell(excelHeaderData.get(columnName), row).getContents();
		if(cellData.length()>0)
			cellContent = cellData.trim();
		return cellContent;
	}
	
	private Candidate checkAndInsertCandidate(CandidateDetailsVO candidateDetailsVO) throws Exception{
		Candidate candidate = candidateDAO.findCandidateByLastName(candidateDetailsVO.getCandidateName());
		
		if(candidate == null){
			candidate = new Candidate();
			candidate.setLastname(candidateDetailsVO.getCandidateName());
			candidate.setGender(candidateDetailsVO.getGender());
			candidate = candidateDAO.save(candidate);
		}
		
		return candidate;
	}

	/**
	 * adding the party in DB if it is not existing in DB
	 * @param parties - list of parties in the database
	 * @param partyName
	 * @param resultVO
	 * @return
	 */
	private Party checkAndInsertParty(List<Party> parties,String partyName){
		boolean partyFlag = true;
		Party lpartyObj = null;
		if(parties!=null && parties.size()>0){
			for(Party par: parties){
				if(partyName.equalsIgnoreCase(par.getLongName()) || partyName.equalsIgnoreCase(par.getShortName())){
					lpartyObj = par;
					partyFlag = false;
					break;
				}
			}
		}
		if(partyFlag){
			lpartyObj= new Party();
			lpartyObj.setLongName(partyName);
			String shortName=(partyName.length()>25)?"":partyName;
			lpartyObj.setShortName(shortName);
			lpartyObj=partyDAO.save(lpartyObj);
			parties.add(lpartyObj);
		}

		return lpartyObj;
	}

}
