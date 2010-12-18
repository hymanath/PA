package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.excel.CandidateElectionResult;
import com.itgrids.partyanalyst.excel.ConstituencyBlock;
import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.ExcelReaderFactory;
import com.itgrids.partyanalyst.excel.IExcelReader;
import com.itgrids.partyanalyst.excel.upload.vo.UploadFormVo;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IExcelToDBService;
import com.itgrids.partyanalyst.utils.CandidateVotesComparator;
import com.itgrids.partyanalyst.utils.IConstants;

public class ExcelToDBService implements IExcelToDBService {

	private static Logger logger = Logger.getLogger(ExcelToDBService.class);
	private IElectionTypeDAO electionTypeDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private ICandidateDAO candidateDAO;
	private IPartyDAO partyDAO;
	private INominationDAO nominationDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO; 
	private TransactionTemplate transactionTemplate;
	
	public void readCSVFileAndStoreIntoDB(UploadFormVo uploadFormVo,String fileName,File fileToUpload) throws Exception{
 
		try{
			if(logger.isDebugEnabled()){
			 logger.debug("Congrats logger has been initialized");			
			 logger.debug("Congrats Successes fully entered in the service layer.. ");
			 logger.debug("File path == "+fileToUpload);
			 logger.debug("Country Name == "+uploadFormVo.getCountry());
			 logger.debug("State =="+uploadFormVo.getElectionScope());
			 logger.debug("District =="+uploadFormVo.getDistrict());
			 logger.debug("Type of Election == "+uploadFormVo.getElectionType());
			 logger.debug("Election Year == "+uploadFormVo.getElectionYear());
			}
			insertIntoDatabase(selectReaderAndFetchConstituencyBlocks(uploadFormVo,fileName,fileToUpload));
		}catch(Exception excep){
			excep.printStackTrace();
			throw new Exception(excep.getMessage());
		}
}


//private void insertIntoDatabase(String countryNam,String stateName,String distName,String typeOfElection, String electionYear,List<ConstituencyBlock> constituenciesBlocks) throws CsvException{
private void insertIntoDatabase(UploadFormVo uploadFormVo) throws CsvException{
	List<ConstituencyBlock>  constituencyBlocks=uploadFormVo.getConstituencyBlocks();
	if(logger.isDebugEnabled())
	 logger.debug("Total no of constituency blocks =="+constituencyBlocks.size());
	Country countryObj=null;
	State stateObj=null;
	District districtObj=null;
	//Check whether electlion type has been defined or not
	List<ElectionType> electionTypes=electionTypeDAO.findByElectionType(uploadFormVo.getElectionType());
	//Election Type has been defined in the system so we can proceed ahead
	if(electionTypes.size()>0){
		if(logger.isDebugEnabled())
		logger.debug("1");
		ElectionType electionTypeObj= electionTypes.get(0);
		//Checking for the existance of state if insert state and get the stateID
		List<Country> countries= countryDAO.findByCountryName(uploadFormVo.getCountry());
		if(logger.isDebugEnabled())
		logger.debug("2");
		List<State> states=stateDAO.findByStateName(uploadFormVo.getElectionScope());
		List<District> districts=districtDAO.findByDistrictName(uploadFormVo.getDistrict());
		if(logger.isDebugEnabled())
		logger.debug("3");
		if(states!=null && countries!=null && districts!=null){
			countryObj=countries.get(0);
			stateObj= states.get(0);
			districtObj=districts.get(0);
			if(logger.isDebugEnabled())
			logger.debug("4");
			ElectionScope electionScopeObj = checkAndInsertElectionScope(electionTypeObj, countryObj, stateObj);
			Election electionObj=checkAndInsertElection(electionScopeObj,uploadFormVo);
			try {
				int constituencyNo=0;
				if(logger.isDebugEnabled())
				logger.debug("4.1");
				List<Party> parties=partyDAO.getAll();
				//List<Constituency> constituencies= constituencyDAO.getAll();
				//ProcessBatchCallback pbc= new ProcessBatchCallback(parties, candidates, constituencies, constituenciesBlocks, stateObj, districtObj, electionObj, constituencyNo, countryObj, electionScopeObj);
				Long countryId = countryObj.getCountryId();
				String electionType = electionScopeObj.getElectionType().getElectionType();
				
				for (ConstituencyBlock constituencyBlock: constituencyBlocks)
					processBatch(parties, constituencyBlock, stateObj, districtObj, electionObj, constituencyNo, countryObj, 
							electionScopeObj, countryId, electionType);
				
				//this.transactionTemplate.execute(pbc);

			} catch (Exception e) {
				//throw new CsvException(e.getMessage());
				//System.out.println("Exception e="+e.getMessage());
			}
		}


	}else{
		throw new CsvException("Election type has not been defined in the system.");
	}
}

private Candidate checkAndInsertCandidate(String candidateName) throws Exception{
	Candidate candidate = candidateDAO.findCandidateByLastName(candidateName);
	
	if(candidate == null){
		candidate = new Candidate();
		candidate.setLastname(candidateName);
		candidate = candidateDAO.save(candidate);
	}
	
	return candidate;
}


/*	private class ProcessBatchCallback implements TransactionCallback{
		List<Party> parties;
		List<Candidate> candidates;
		List<Constituency> constituencies;
		List<ConstituencyBlock> constituenciesBlocks;
		State stateObj;
		District districtObj;
		Election electionObj;
		int constituencyNo;
		Country countryObj;
		ElectionScope electionScopeObj;

		public ProcessBatchCallback(List<Party> parties,List<Candidate> candidates,List<Constituency> constituencies,List<ConstituencyBlock> constituenciesBlocks, State stateObj,District districtObj,Election electionObj,int constituencyNo,Country countryObj,ElectionScope electionScopeObj){
			 this.parties= parties;
			 this.candidates=candidates;
			 this.constituencies=constituencies;
			 this.constituenciesBlocks=constituenciesBlocks;
			 this.stateObj=stateObj;
			 this.districtObj=districtObj;
			 this.electionObj=electionObj;
			 this.constituencyNo=constituencyNo;
			 this.countryObj =countryObj;
			 this.electionScopeObj=electionScopeObj;
		}

		public Object doInTransaction(TransactionStatus arg0) {
			// TODO Auto-generated method stub
			processBatch(parties, candidates, constituencies, constituenciesBlocks, stateObj, districtObj, electionObj, constituencyNo, countryObj, electionScopeObj);
			return null;
		}

	}*/


public int processBatch(List<Party> parties, ConstituencyBlock constituecBlock, 
		State stateObj,District districtObj,Election electionObj, int constituencyNo,
		Country countryObj,ElectionScope electionScopeObj, Long countryId, String electionType){
	
		try {
			long currentTime=System.currentTimeMillis();
			if(logger.isDebugEnabled()){
				logger.debug("4.2");
				logger.debug("Constituency No =="+(++constituencyNo));
			}
			Long constiCount = 0l;
			Constituency constituencyObj;
			//modified by sai
			if(constituecBlock.getDistrictId() != null && constituecBlock.getDistrictId().longValue() != 0){
				if(electionObj.getElectionScope().getElectionType().getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
				    constituencyObj = checkAndInsertConstituency(constituecBlock.getConstituencyName(), countryId, stateObj, districtObj, 
				    		electionScopeObj, electionType, null, constituecBlock.getDistrictId(), constiCount);
				else
					constituencyObj = checkAndInsertConstituency(constituecBlock.getConstituencyName(), countryId, stateObj, districtObj, 
				    		electionScopeObj, electionType, constituecBlock.getDistrictId(), null, constiCount);
			}
			//end
			else
				constituencyObj=checkAndInsertConstituency(constituecBlock.getConstituencyName(), countryId, stateObj, districtObj, 
			    		electionScopeObj, electionType, null, null, constiCount);
			
			ConstituencyElection constituencyElectionObj = new ConstituencyElection();
			constituencyElectionObj.setConstituency(constituencyObj);
			constituencyElectionObj.setElection(electionObj);
			constituencyElectionObj.setReservationZone(constituecBlock.getReservationInfo());
			constituencyElectionObj = constituencyElectionDAO.save(constituencyElectionObj);

			List<CandidateElectionResult> candidateElectionResults= constituecBlock.getCandidateElectionlst();
			Collections.sort(candidateElectionResults,new CandidateVotesComparator());
			int rankByVotes=1;
			if(logger.isDebugEnabled())
			logger.debug("4.3");
			for (CandidateElectionResult candidateElectionResult : candidateElectionResults) {
				Candidate candidateObj=checkAndInsertCandidate(candidateElectionResult.getCandidateName());
				Party partyObj= checkAndInsertParty(parties,candidateElectionResult.getCandidatePrty());
				if(logger.isDebugEnabled())
				logger.debug("4.6");
				Nomination nominationObj = new Nomination();
				nominationObj.setCandidate(candidateObj);
				nominationObj.setParty(partyObj);
				nominationObj.setConstituencyElection(constituencyElectionObj);
				nominationObj =nominationDAO.save(nominationObj);


				CandidateResult candidateResultObj = new CandidateResult();
				candidateResultObj.setRank(new Long(rankByVotes++));
				candidateResultObj.setVotesEarned(candidateElectionResult.getVotesEarned());
				candidateResultObj.setVotesPercengate(calculateVotesPercengate(constituecBlock.getValidVotes(),candidateElectionResult.getVotesEarned()));
				candidateResultObj.setNomination(nominationObj);
				candidateResultDAO.save(candidateResultObj);
				if(logger.isDebugEnabled())
				logger.debug("4.7");
			}
			ConstituencyElectionResult constituencyElectionResultObj = new ConstituencyElectionResult();
			constituencyElectionResultObj.setMissingVotes(constituecBlock.getMissingVotes());
			constituencyElectionResultObj.setRejectedVotes(constituecBlock.getRejectedVotes());
			constituencyElectionResultObj.setTenderedVotes(constituecBlock.getTenderedVotes());
			constituencyElectionResultObj.setTotalVotes(constituecBlock.getTotalElectors());
			constituencyElectionResultObj.setTotalVotesPolled(constituecBlock.getTotalVotesPolled());
			constituencyElectionResultObj.setValidVotes(constituecBlock.getValidVotes());
			constituencyElectionResultObj.setVotingPercentage(calculateVotesPercengate(constituecBlock.getTotalElectors(),constituecBlock.getTotalVotesPolled()));
			constituencyElectionResultObj.setConstituencyElection(constituencyElectionObj);
			constituencyElectionResultDAO.save(constituencyElectionResultObj);
			
			if(logger.isDebugEnabled())
			logger.debug("4.5");
			long lastTime=System.currentTimeMillis();
			if(logger.isDebugEnabled())
			logger.debug("Time difference == "+(lastTime-currentTime)/1000);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			logger.debug("Exception == "+e.getMessage());
		}
	return constituencyNo;
}

public Constituency checkAndInsertConstituency(String constituencyName, 
		Long countryId, State state, District district, ElectionScope electionScope, 
		String electionType, Long districtId, Long stateId, Long constiCount){
	Constituency constituency = null;
	List<Constituency> constituencies = null;

	if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){
		if(districtId != null)
			constituencies = constituencyDAO.findByConstituencyNameElectionScopeAndDistrictId(constituencyName, 
					districtId, electionScope.getElectionScopeId());
		else
			constituencies = constituencyDAO.findConstituenciesByNameScopeAndStateId(constituencyName, 
					electionScope.getElectionScopeId(), state.getStateId());
	}else{
		if(stateId != null)
			constituencies = constituencyDAO.findConstituenciesByNameScopeAndStateId(constituencyName, electionScope.getElectionScopeId(), stateId);
		else
			constituencies = constituencyDAO.findConstituenciesByNameScopeAndCountryId(constituencyName, electionScope.getElectionScopeId(), countryId);
	}
	
	if(constituencies.size() == 0){
		constituency = new Constituency();
		constituency.setName(constituencyName);
		constituency.setCountryId(countryId);
		constituency.setState(state);
		constituency.setDistrict(district);
		constituency.setElectionScope(electionScope);
		constituency = constituencyDAO.save(constituency);
		logger.debug(">> "+(++constiCount)+" New Constituency Created With Name: "+constituencyName+" Of Type: "+electionType);
		return constituency;
	}
	
	if(constituencies.size() > 1)
		logger.debug(">> Error "+constituencies.size()+" No.of Constituencies Exisits with Name: "+constituencyName+" and Type: "+electionType);
	
	return constituencies.get(0);
	
}

private Party checkAndInsertParty(List<Party> partis,String partyName){
	boolean partyFlag = true;
	Party lpartyObj = null;
	if(partis!=null && partis.size()>0){
		for(Party par: partis){
			if(partyName.equalsIgnoreCase(par.getLongName().trim()) || partyName.equalsIgnoreCase(par.getShortName().trim())){
				lpartyObj = par;
				partyFlag = false;
				break;
			}
		}
	}
	if(partyFlag){
		if(logger.isDebugEnabled())
		logger.debug("New party has been identified.");
		lpartyObj= new Party();
		lpartyObj.setLongName(partyName);
		String shortName=(partyName.length()>25)?"":partyName;
		lpartyObj.setShortName(shortName);
		lpartyObj=partyDAO.save(lpartyObj);
		partis.add(lpartyObj);
	}

	return lpartyObj;
}

private ElectionScope checkAndInsertElectionScope(ElectionType electionType,Country country,State state) throws CsvException{
	ElectionScope electionScope=null;
	try{
		if(electionType.getElectionTypeId().longValue()==1){
			electionScope= electionScopeDAO.findByElectionTypeCountry(electionType, country);
			if(electionScope==null){
				electionScope= new ElectionScope();
				electionScope.setElectionType(electionType);
				electionScope.setCountry(country);
				electionScope.setState(state);
				electionScope=electionScopeDAO.save(electionScope);
			}
		}else{
			electionScope= electionScopeDAO.findByElectionTypeCountryState(electionType, country, state);
			if(electionScope==null){
				electionScope= new ElectionScope();
				electionScope.setElectionType(electionType);
				electionScope.setCountry(country);
				electionScope.setState(state);
				electionScope=electionScopeDAO.save(electionScope);
			}
		}

	}catch(Exception excep){
		if(logger.isDebugEnabled())
		logger.debug("Exception == "+excep.getMessage());
	}
	return electionScope;
}

private Election checkAndInsertElection(ElectionScope electionScope,UploadFormVo uploadFormVo) throws CsvException{
	Election lelectionObj = electionDAO.findByElectionScopeIdElectionYear(electionScope.getElectionScopeId(), 
			uploadFormVo.getElectionYear(), uploadFormVo.getElecSubtype());
	if(lelectionObj!=null){
		throw new CsvException("These Election Results have already been uploaded.");
	}else{
		lelectionObj = new Election();
		lelectionObj.setElectionScope(electionScope);
		lelectionObj.setElecSubtype(uploadFormVo.getElecSubtype());
		lelectionObj.setElectionYear(uploadFormVo.getElectionYear());
		lelectionObj = electionDAO.save(lelectionObj);
	}
	return lelectionObj;
}



private String calculateVotesPercengate(Double validVotes, Double votesEarned){
	BigDecimal percengate= new BigDecimal(0.0);
	if((validVotes!=null && validVotes.doubleValue()>0) && (votesEarned!=null && votesEarned.doubleValue()>0)){
		percengate= new BigDecimal((votesEarned/validVotes)*100).setScale (2,BigDecimal.ROUND_HALF_UP);
	}
	return percengate.toString();
}
//public 
public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
	this.electionTypeDAO = electionTypeDAO;
}

public void setStateDAO(IStateDAO stateDAO) {
	this.stateDAO = stateDAO;
}

public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
	this.electionScopeDAO = electionScopeDAO;
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

public void setCandidateDAO(ICandidateDAO candidateDAO) {
	this.candidateDAO = candidateDAO;
}

public void setPartyDAO(IPartyDAO partyDAO) {
	this.partyDAO = partyDAO;
}

public void setNominationDAO(INominationDAO nominationDAO) {
	this.nominationDAO = nominationDAO;
}

public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
	this.candidateResultDAO = candidateResultDAO;
}

public void setConstituencyElectionResultDAO(
		IConstituencyElectionResultDAO constituencyElectionResultDAO) {
	this.constituencyElectionResultDAO = constituencyElectionResultDAO;
}


public IElectionTypeDAO getElectionTypeDAO() {
	return electionTypeDAO;
}

public IStateDAO getStateDAO() {
	return stateDAO;
}

public IElectionScopeDAO getElectionScopeDAO() {
	return electionScopeDAO;
}

public IElectionDAO getElectionDAO() {
	return electionDAO;
}

public IConstituencyDAO getConstituencyDAO() {
	return constituencyDAO;
}

public IConstituencyElectionDAO getConstituencyElectionDAO() {
	return constituencyElectionDAO;
}

public ICandidateDAO getCandidateDAO() {
	return candidateDAO;
}

public IPartyDAO getPartyDAO() {
	return partyDAO;
}

public INominationDAO getNominationDAO() {
	return nominationDAO;
}

public ICandidateResultDAO getCandidateResultDAO() {
	return candidateResultDAO;
}

public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
	return constituencyElectionResultDAO;
}

public ICountryDAO getCountryDAO() {
	return countryDAO;
}

public void setCountryDAO(ICountryDAO countryDAO) {
	this.countryDAO = countryDAO;
}
public IDistrictDAO getDistrictDAO() {
	return districtDAO;
}
public void setDistrictDAO(IDistrictDAO districtDAO) {
	this.districtDAO = districtDAO;
}


public TransactionTemplate getTransactionTemplate() {
	return transactionTemplate;
}


public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	this.transactionTemplate = transactionTemplate;
}

private UploadFormVo selectReaderAndFetchConstituencyBlocks(UploadFormVo uploadFormVo,String fileName,File fileToUpload) throws CsvException{
	if(logger.isDebugEnabled())
	logger.debug("In the selectReaderAndFetchConstituencyBlock metbod");
	List<ConstituencyBlock> constituencyBlocks=null;
	if(fileToUpload.exists()){
		if(logger.isDebugEnabled())
		logger.debug("File name length is>0");
		IExcelReader excelReader=ExcelReaderFactory.selectReader(fetchPattern(fileName));
		excelReader.readCSV(fileToUpload);
		constituencyBlocks=excelReader.getConstituencyBlocks();
		if(logger.isDebugEnabled())
		logger.debug("Constituencies blocks =="+constituencyBlocks.size());
		if(constituencyBlocks!=null && constituencyBlocks.size()>0){
			if(logger.isDebugEnabled())
			logger.debug("Constituencies blocks greated than zero.");
			uploadFormVo.setConstituencyBlocks(constituencyBlocks);			
		}else{
			throw new CsvException("Sorry no constituency blocks were returned from the selected reader "+excelReader.getClass().getName());
		}
	}
	return uploadFormVo;
}

private String fetchPattern(String excelFileName){
	StringTokenizer stringTokenizer= new StringTokenizer(excelFileName,"_.");
	String pattern="";
	while(stringTokenizer.hasMoreElements()){
		pattern=stringTokenizer.nextToken();
		if(pattern.startsWith("Pattern")){
			break;
		}
	}
	return pattern;
}


}
