package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

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
import com.itgrids.partyanalyst.utils.CandidateVotesComparator;

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
	//public void readCSVFileAndStoreIntoDB(String excelFilePath,String countryName,String stateName,String districtName, String typeOfElection, String electionYear) throws Exception{
	public void readCSVFileAndStoreIntoDB(UploadFormVo uploadFormVo) throws Exception{

		try{
			logger.debug("Congrats logger has been initialized");			
			System.out.println("Congrats Successes fully entered in the service layer.. ");
			System.out.println("Fila path == "+uploadFormVo.getInputFile());
			System.out.println("Country Name == "+uploadFormVo.getCountry());
			System.out.println("State =="+uploadFormVo.getElectionScope());
			System.out.println("District =="+uploadFormVo.getDistrict());
			System.out.println("Type of Election == "+uploadFormVo.getElectionType());
			System.out.println("Election Year == "+uploadFormVo.getElectionYear());
			insertIntoDatabase(selectReaderAndFetchConstituencyBlocks(uploadFormVo));
		}catch(Exception excep){
			throw new Exception(excep.getMessage());
		}
}


//private void insertIntoDatabase(String countryNam,String stateName,String distName,String typeOfElection, String electionYear,List<ConstituencyBlock> constituenciesBlocks) throws CsvException{
private void insertIntoDatabase(UploadFormVo uploadFormVo) throws CsvException{
	List<ConstituencyBlock>  constituencyBlocks=uploadFormVo.getConstituencyBlocks();
	System.out.println("Total no of constituency blocks =="+constituencyBlocks.size());
	Country countryObj=null;
	State stateObj=null;
	District districtObj=null;
	//Check whether electlion type has been defined or not
	List<ElectionType> electionTypes=electionTypeDAO.findByElectionType(uploadFormVo.getElectionType());
	//Election Type has been defined in the system so we can proceed ahead
	if(electionTypes.size()>0){
		System.out.println("1");
		ElectionType electionTypeObj= electionTypes.get(0);
		Long electionTypeId=electionTypeObj.getElectionTypeId();
		//Checking for the existance of state if insert state and get the stateID
		List<Country> countries= countryDAO.findByCountryName(uploadFormVo.getCountry());
		System.out.println("2");
		List<State> states=stateDAO.findByStateName(uploadFormVo.getElectionScope());
		List<District> districts=districtDAO.findByDistrictName(uploadFormVo.getDistrict());
		System.out.println("3");
		if(states!=null && countries!=null && districts!=null){
			countryObj=countries.get(0);
			stateObj= states.get(0);
			districtObj=districts.get(0);
			System.out.println("4");
			ElectionScope electionScopeObj=checkAndInsertElectionScope(electionTypeObj, countryObj, stateObj,uploadFormVo.getElectionYear());
			Election electionObj=checkAndInsertElection(electionScopeObj,uploadFormVo.getElectionYear());
			try {
				int constituencyNo=0;
				System.out.println("4.1");
				List<Party> parties=partyDAO.getAll();
				List<Candidate> candidates= candidateDAO.getAll();
				List<Constituency> constituencies= constituencyDAO.getAll();
				//ProcessBatchCallback pbc= new ProcessBatchCallback(parties, candidates, constituencies, constituenciesBlocks, stateObj, districtObj, electionObj, constituencyNo, countryObj, electionScopeObj);
				
				for (ConstituencyBlock constituencyBlock: constituencyBlocks) {
				processBatch(parties, candidates, constituencies, constituencyBlock, stateObj, districtObj, electionObj, constituencyNo, countryObj, electionScopeObj);
				}
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


public int processBatch(List<Party> parties,List<Candidate> candidates,List<Constituency> constituencies,ConstituencyBlock constituecBlock, State stateObj,District districtObj,Election electionObj,int constituencyNo,Country countryObj,ElectionScope electionScopeObj){
	
		try {
			long currentTime=System.currentTimeMillis();
			System.out.println("4.2");
			System.out.println("Constituency No =="+(++constituencyNo));
			Constituency constituencyObj=checkAndInsertConstituency(constituencies,constituecBlock.getConstituencyName(), countryObj.getCountryId(), stateObj, districtObj, electionScopeObj);

			ConstituencyElection constituencyElectionObj = new ConstituencyElection();
			constituencyElectionObj.setConstituency(constituencyObj);
			constituencyElectionObj.setElection(electionObj);
			constituencyElectionObj = constituencyElectionDAO.save(constituencyElectionObj);

			List<CandidateElectionResult> candidateElectionResults= constituecBlock.getCandidateElectionlst();
			Collections.sort(candidateElectionResults,new CandidateVotesComparator());
			int rankByVotes=1;
			System.out.println("4.3");
			for (CandidateElectionResult candidateElectionResult : candidateElectionResults) {
				Candidate candidateObj=checkAndInsertCandidate(candidates,candidateElectionResult.getCandidateName());
				Party partyObj= checkAndInsertParty(parties,candidateElectionResult.getCandidatePrty());
				System.out.println("4.6");
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
				System.out.println("4.7");
			}
			ConstituencyElectionResult constituencyElectionResultObj = new ConstituencyElectionResult();
			constituencyElectionResultObj.setMissingVotes(constituecBlock.getMissingVotes());
			constituencyElectionResultObj.setRejectedVotes(constituecBlock.getRejectedVotes());
			constituencyElectionResultObj.setTenderedVotes(constituecBlock.getTenderedVotes());
			constituencyElectionResultObj.setTotalVotes(constituecBlock.getTotalElectors());
			constituencyElectionResultObj.setTotalVotesPolled(constituecBlock.getTotalVotesPolled());
			constituencyElectionResultObj.setValidVotes(constituecBlock.getValidVotes());
			constituencyElectionResultObj.setConstituencyElection(constituencyElectionObj);
			constituencyElectionResultDAO.save(constituencyElectionResultObj);

			System.out.println("4.5");
			long lastTime=System.currentTimeMillis();
			System.out.println("Time difference == "+(lastTime-currentTime)/1000);
		} catch (Exception e) {
			System.out.println("Exception == "+e.getMessage());
		}
	return constituencyNo;
}
private Constituency checkAndInsertConstituency(List<Constituency> constituencis,String constituencyName, Long countryId, State state,District district,ElectionScope electionScope){
	boolean constituencyFlag = true;
	Constituency lconstituencyObj= null;
	if(constituencis!=null && constituencis.size()>0){
		for(Constituency con: constituencis){
			if(constituencyName.equalsIgnoreCase(con.getName().trim())){
				lconstituencyObj = con;
				constituencyFlag = false;
				break;
			}
		}
	}
	if(constituencyFlag){
		lconstituencyObj= new Constituency();
		lconstituencyObj.setName(constituencyName);
		lconstituencyObj.setCountryId(countryId);
		lconstituencyObj.setState(state);
		lconstituencyObj.setDistrict(district);
		lconstituencyObj.setElectionScope(electionScope);
		lconstituencyObj=constituencyDAO.save(lconstituencyObj);
		constituencis.add(lconstituencyObj);
		System.out.println("New party has been created");
	}
	return lconstituencyObj;
}
private Candidate checkAndInsertCandidate(List<Candidate> candidats,String candidateName){
	boolean candidateFlag = true;
	Candidate lcandidateObj = null;
	if(candidats!=null && candidats.size()>0){
		for(Candidate can: candidats){
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
		lcandidateObj=candidateDAO.save(lcandidateObj);
		candidats.add(lcandidateObj);
	}
	return lcandidateObj;
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
		System.out.println("New party has been identified.");
		lpartyObj= new Party();
		lpartyObj.setLongName(partyName);
		String shortName=(partyName.length()>25)?"":partyName;
		lpartyObj.setShortName(shortName);
		lpartyObj=partyDAO.save(lpartyObj);
		partis.add(lpartyObj);
	}

	return lpartyObj;
}

private ElectionScope checkAndInsertElectionScope(ElectionType electionType,Country country,State state,String eleYear) throws CsvException{
	ElectionScope electionScope=null;
	if(electionType.getElectionTypeId().longValue()==1){
		electionScope= electionScopeDAO.findByElectionTypeCountry(electionType, country);
		if(electionScope==null){
			electionScope= new ElectionScope();
			electionScope.setElectionType(electionType);
			electionScope.setCountry(country);
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
	return electionScope;
}

private Election checkAndInsertElection(ElectionScope electionScope,String eleYear) throws CsvException{
	Election lelectionObj;
	lelectionObj= electionDAO.findByESIdEleYear(electionScope,eleYear);
	if(lelectionObj!=null){
		throw new CsvException("These Election Results have already been uploaded.");
	}else{
		lelectionObj= new Election();
		lelectionObj.setElectionScope(electionScope);
		lelectionObj.setElectionYear(eleYear);
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

private UploadFormVo selectReaderAndFetchConstituencyBlocks(UploadFormVo uploadFormVo) throws CsvException{
	IExcelReader excelReader=null;
	List<ConstituencyBlock> constituencyBlocks=null;
	if(uploadFormVo.getInputFile().length()>0){
		excelReader=ExcelReaderFactory.selectReader(fetchPattern(uploadFormVo.getInputFile()));
		excelReader.readCSV(uploadFormVo.getInputFile());
		constituencyBlocks=excelReader.getConstituencyBlocks();
		
		if(constituencyBlocks!=null && constituencyBlocks.size()>0){
			uploadFormVo.setConstituencyBlocks(excelReader.getConstituencyBlocks());
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
