package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

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
import com.itgrids.partyanalyst.excel.CsvReader;
import com.itgrids.partyanalyst.excel.CsvReader2004;
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
import com.itgrids.partyanalyst.strategy.PercentageIntegrationTemplate;
import com.itgrids.partyanalyst.utils.CandidateVotesComparator;

public class ExcelToDBService implements IExcelToDBService {

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
	public void readCSVFileAndStoreIntoDB(String excelFilePath,String countryName,String stateName,String districtName, String typeOfElection, String electionYear) throws Exception{

		try{
			System.out.println("Congrats Successes fully entered in the service layer.. ");
			System.out.println("Fila path == "+excelFilePath);
			System.out.println("Country Name == "+countryName);
			System.out.println("State =="+stateName);
			System.out.println("District =="+districtName);
			System.out.println("Type of Election == "+typeOfElection);
			System.out.println("Election Year == "+electionYear);
			if(excelFilePath.trim().length()>0){
				List<ConstituencyBlock> constituencyBlocks;
				if(Integer.parseInt(electionYear)>=2004){
					CsvReader csvReader= new CsvReader();
					csvReader.readCSV(excelFilePath);
					constituencyBlocks= csvReader.getConstituencyBlocks();
				}else{
					CsvReader2004 csvReader2004= new CsvReader2004();
					csvReader2004.readCSV(excelFilePath);
					constituencyBlocks= csvReader2004.getConstituencyBlocks();
				}
				System.out.println(" No of constituencies == "+constituencyBlocks.size());
				insertIntoDatabase(countryName,stateName,districtName,typeOfElection,electionYear,constituencyBlocks);
			}

		}catch(Exception excep){
			throw new Exception(excep.getMessage());
		}
	}


	private void insertIntoDatabase(String countryNam,String stateName,String distName,String typeOfElection, String electionYear,List<ConstituencyBlock> constituenciesBlocks) throws CsvException{
		Country countryObj=null;
		State stateObj=null;
		District districtObj=null;
		//Check whether electlion type has been defined or not
		List<ElectionType> electionTypes=electionTypeDAO.findByElectionType(typeOfElection);
		//Election Type has been defined in the system so we can proceed ahead
		if(electionTypes.size()>0){
			System.out.println("1");
			ElectionType electionTypeObj= electionTypes.get(0);
			Long electionTypeId=electionTypeObj.getElectionTypeId();
			//Checking for the existance of state if insert state and get the stateID
			List<Country> countries= countryDAO.findByCountryName(countryNam);
			System.out.println("2");
			List<State> states=stateDAO.findByStateName(stateName);
			List<District> districts=districtDAO.findByDistrictName(distName);
			System.out.println("3");
			if(states!=null && countries!=null && districts!=null){
				countryObj=countries.get(0);
				stateObj= states.get(0);
				districtObj=districts.get(0);
				System.out.println("4");
				ElectionScope electionScopeObj=checkAndInsertElectionScope(electionTypeObj, countryObj, stateObj,electionYear);
				/*Election electionObj= new Election();
				electionObj.setElectionScope(electionScopeObj);
				electionObj.setElectionYear(electionYear);
				electionObj = electionDAO.save(electionObj);*/
				Election electionObj=checkAndInsertElection(electionScopeObj,electionYear);
				try {
					int constituencyNo=0;
					System.out.println("4.1");
					List<Party> parties=partyDAO.getAll();
					List<Candidate> candidates= candidateDAO.getAll();
					List<Constituency> constituencies= constituencyDAO.getAll();
					for (ConstituencyBlock constituencyBlock: constituenciesBlocks) {
						try {
							long currentTime=System.currentTimeMillis();
							System.out.println("4.2");
							System.out.println("Constituency No =="+(++constituencyNo));
							Constituency constituencyObj=checkAndInsertConstituency(constituencies,constituencyBlock.getConstituencyName(), countryObj.getCountryId(), stateObj, districtObj, electionScopeObj);
							
							ConstituencyElection constituencyElectionObj = new ConstituencyElection();
							constituencyElectionObj.setConstituency(constituencyObj);
							constituencyElectionObj.setElection(electionObj);
							constituencyElectionObj = constituencyElectionDAO.save(constituencyElectionObj);
							
							System.out.println("4.3");
							ConstituencyElectionResult constituencyElectionResultObj = new ConstituencyElectionResult();
							constituencyElectionResultObj.setMissingVotes(constituencyBlock.getMissingVotes());
							constituencyElectionResultObj.setRejectedVotes(constituencyBlock.getRejectedVotes());
							constituencyElectionResultObj.setTenderedVotes(constituencyBlock.getTenderedVotes());
							constituencyElectionResultObj.setTotalVotes(constituencyBlock.getTotalElectors());
							constituencyElectionResultObj.setTotalVotesPolled(constituencyBlock.getTotalVotesPolled());
							constituencyElectionResultObj.setValidVotes(constituencyBlock.getValidVotes());
							constituencyElectionResultObj.setConstituencyElection(constituencyElectionObj);
							constituencyElectionResultObj=constituencyElectionResultDAO.save(constituencyElectionResultObj);

							List<CandidateElectionResult> candidateElectionResults= constituencyBlock.getCandidateElectionlst();
							Collections.sort(candidateElectionResults,new CandidateVotesComparator());
							int rankByVotes=1;
							System.out.println("4.5.3");
							
							for (CandidateElectionResult candidateElectionResult : candidateElectionResults) {
								Candidate candidateObj=checkAndInsertCandidate(candidates,candidateElectionResult.getCandidateName());
								Party partyObj= checkAndInsertParty(parties,candidateElectionResult.getCandidatePrty());
								System.out.println("4.6");
								Nomination nominationObj = new Nomination();
								nominationObj.setCandidate(candidateObj);
								nominationObj.setParty(partyObj);
								nominationObj.setConstituencyElection(constituencyElectionObj);
								nominationObj=nominationDAO.save(nominationObj);
								System.out.println("4.6.1");
								CandidateResult candidateResultObj = new CandidateResult();
								candidateResultObj.setRank(new Long(rankByVotes++));
								candidateResultObj.setVotesEarned(candidateElectionResult.getVotesEarned());
								candidateResultObj.setVotesPercengate(calculateVotesPercengate(constituencyBlock.getValidVotes(),candidateElectionResult.getVotesEarned()));
								candidateResultObj.setNomination(nominationObj);
								
								candidateResultObj = candidateResultDAO.save(candidateResultObj);
								System.out.println("4.7");
								
							}
							System.out.println("4.5");
							long lastTime=System.currentTimeMillis();
							System.out.println("Time difference == "+(lastTime-currentTime)/1000);
						} catch (Exception e) {
							//throw new Exception(e.getMessage());
						}
					}
					//transaction.commit();					
				} catch (Exception e) {
					//throw new CsvException(e.getMessage());
					//System.out.println("Exception e="+e.getMessage());
				}
			}


		}else{
			throw new CsvException("Election type has not been defined in the system.");
		}



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
			System.out.println("New party has been created");
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
			lpartyObj= new Party();
			lpartyObj.setLongName(partyName);
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

}
