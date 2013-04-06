/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 10, 2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.AllBoothsResultsForAPartyInAMandal;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsMainVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.ByeElecGroupVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateWonVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultAllPartyVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyMandalVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionResultsForMandalVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.MandalElectionResultVO;
import com.itgrids.partyanalyst.dto.MandalLevelResultsForParty;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsInConstituencyVO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsInVotesMarginVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyTownshipResultsVO;
import com.itgrids.partyanalyst.dto.PartyVillageLevelAnalysisVO;
import com.itgrids.partyanalyst.dto.PartyVotesMarginInConstituency;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsInMandal;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.RevenueVillageElectionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotesMarginResultsMainVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ByeElecGroupVOComparator;
import com.itgrids.partyanalyst.utils.ElectionResultTypeComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyInfoVOComparator;
import com.itgrids.partyanalyst.utils.PartyResultVOPercentComparator;
import com.itgrids.partyanalyst.utils.PartyResultsComparator;
import com.itgrids.partyanalyst.utils.PartyResultsVOComparator;
import com.itgrids.partyanalyst.utils.PartyTownshipResultsComparator;
import com.itgrids.partyanalyst.utils.SelectOptionVOComparator;


public class BiElectionPageService implements IBiElectionPageService {
	
	private ITehsilDAO tehsilDAO;
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO; 
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private IStaticDataService staticDataService;
	private IConstituencyPageService constituencyPageService;
	private IAllianceGroupDAO  allianceGroupDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
		
	private static final Logger log = Logger.getLogger(BiElectionPageService.class);

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
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

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}
	
	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getBiElectionConstituenciesDistrictWise()
	 */
	@SuppressWarnings("unchecked")
	public List<BiElectionDistrictVO> getBiElectionConstituenciesDistrictWise() {
		
		if(log.isDebugEnabled())
			log.debug(" Inside getBiElectionConstituenciesDistrictWise Method ... ");
		List<BiElectionDistrictVO> biElectionDistrictVO = new ArrayList<BiElectionDistrictVO>();
		
		//For Karimnagar
		StringBuilder sb = new StringBuilder();
		sb.append("select model.district.districtId,model.district.districtName,");
		sb.append("model.constituencyId,model.name from Constituency model where ");
		sb.append("model.name in('Dharmapuri','Huzurabad','Koratla','Sircilla','Vemulawada') ");
		sb.append("and model.electionScope.electionType.electionType = 'Assembly' ");
		sb.append("and model.district.districtName = 'Karimnagar' and model.state.stateName = 'Andhra Pradesh'");
		
		List karimnagarConsts = constituencyDAO.findConstituenciesForBiElectionInADistrict(sb.toString());
		
		if(karimnagarConsts != null && karimnagarConsts.size() > 0){
			BiElectionDistrictVO results = getBiElectionConstituencies(karimnagarConsts);
			biElectionDistrictVO.add(results);
		}
		
		//For Adilabad
		StringBuilder sb1 = new StringBuilder();
		sb1.append("select model.district.districtId,model.district.districtName,");
		sb1.append("model.constituencyId,model.name from Constituency model where ");
		sb1.append("model.name in('Sirpur','Chennur','Mancherial') ");
		sb1.append("and model.electionScope.electionType.electionType = 'Assembly' ");
		sb1.append("and model.district.districtName = 'Adilabad' and model.state.stateName = 'Andhra Pradesh'");
		
		List adilabadConsts = constituencyDAO.findConstituenciesForBiElectionInADistrict(sb1.toString());
		if(adilabadConsts != null && adilabadConsts.size() > 0){
			BiElectionDistrictVO results = getBiElectionConstituencies(adilabadConsts);
			biElectionDistrictVO.add(results);
		}
		
		//For Nizamabad
		StringBuilder sb2 = new StringBuilder();
		sb2.append("select model.district.districtId,model.district.districtName,");
		sb2.append("model.constituencyId,model.name from Constituency model where ");
		sb2.append("model.name in('Yellareddy','Nizamabad Urban') ");
		sb2.append("and model.electionScope.electionType.electionType = 'Assembly' ");
		sb2.append("and model.district.districtName = 'Nizamabad' and model.state.stateName = 'Andhra Pradesh'");
		
		List nizamabadConsts = constituencyDAO.findConstituenciesForBiElectionInADistrict(sb2.toString());
		if(nizamabadConsts != null && nizamabadConsts.size() > 0){
			BiElectionDistrictVO results = getBiElectionConstituencies(nizamabadConsts);
			biElectionDistrictVO.add(results);
		}
		
		
		//For Medak
		StringBuilder sb3 = new StringBuilder();
		sb3.append("select model.district.districtId,model.district.districtName,");
		sb3.append("model.constituencyId,model.name from Constituency model where ");
		sb3.append("model.name in('Siddipet') ");
		sb3.append("and model.electionScope.electionType.electionType = 'Assembly' ");
		sb3.append("and model.district.districtName = 'Medak' and model.state.stateName = 'Andhra Pradesh'");
		
		List medakConsts = constituencyDAO.findConstituenciesForBiElectionInADistrict(sb3.toString());
		if(medakConsts != null && medakConsts.size() > 0){
			BiElectionDistrictVO results = getBiElectionConstituencies(medakConsts);
			biElectionDistrictVO.add(results);
		}
		
		//For Warangal
		StringBuilder sb4 = new StringBuilder();
		sb4.append("select model.district.districtId,model.district.districtName,");
		sb4.append("model.constituencyId,model.name from Constituency model where ");
		sb4.append("model.name in('Warangal West') ");
		sb4.append("and model.electionScope.electionType.electionType = 'Assembly' ");
		sb4.append("and model.district.districtName = 'Warangal' and model.state.stateName = 'Andhra Pradesh'");
		
		List warangalConsts = constituencyDAO.findConstituenciesForBiElectionInADistrict(sb4.toString());
		if(warangalConsts != null && warangalConsts.size() > 0){
			BiElectionDistrictVO results = getBiElectionConstituencies(warangalConsts);
			biElectionDistrictVO.add(results);
		}
		
		return biElectionDistrictVO;
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public BiElectionDistrictVO getBiElectionConstituencies(List constiDetails){
		
		BiElectionDistrictVO biElectionDistrictVO = null;
		if(constiDetails != null && constiDetails.size() > 0){
			biElectionDistrictVO = new BiElectionDistrictVO();
			List<SelectOptionVO> constituenciesList = new ArrayList<SelectOptionVO>();
			for(int i=0;i<constiDetails.size();i++){
				
				Object[] params = (Object[])constiDetails.get(i);
				
				if(i == 0){
					biElectionDistrictVO.setDistrictId((Long)params[0]);
					biElectionDistrictVO.setDistrictName((String)params[1]);
				}
				
				SelectOptionVO selectOption = new SelectOptionVO();
				selectOption.setId((Long)params[2]);
				selectOption.setName(WordUtils.capitalize((String)params[3]));
				constituenciesList.add(selectOption);
			}
			biElectionDistrictVO.setConstituenciesList(constituenciesList);
		}
		
		return biElectionDistrictVO;
	}
	
	/*
	 * Method to process the party results and set them to VO
	 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResultAllPartyVO> getProcesedPartyResults(List electionResults,String electionYear){
		
		if(log.isDebugEnabled())
			log.debug(" Inside getProcesedPartyResults Method ...");
		List<ConstituencyElectionResultAllPartyVO> constituencyElectionResultAllPartyVOList = null;
		ConstituencyElectionResultAllPartyVO constituencyElectionResultAllPartyVO = null;
		
		if(electionResults != null && electionResults.size() > 0){
			
			constituencyElectionResultAllPartyVOList = new ArrayList<ConstituencyElectionResultAllPartyVO>();
			
			
			Map<Long,List<PartyResultsVO>> partyResultsMap = new HashMap<Long,List<PartyResultsVO>>();
			
			for(int i=0;i<electionResults.size();i++){
				Object[] params = (Object[])electionResults.get(i);
				
				Long constituencyId = (Long)params[5];
				
				if(partyResultsMap.isEmpty() || !partyResultsMap.containsKey(constituencyId)){
					List<PartyResultsVO> partyResultsVO = new ArrayList<PartyResultsVO>();
					partyResultsMap.put(constituencyId, partyResultsVO);
				}
				else{
					PartyResultsVO partyResult = new PartyResultsVO();
					partyResult.setPartyId((Long)params[0]);
					partyResult.setPartyName((String)params[1]);
					//partyResult.setCandidateName((String)params[7]);
					partyResult.setVotesEarned((Long)params[2]);
					partyResult.setValidVotes((Long)params[3]);
					partyResult.setRank((Long)params[4]);
					
					if(partyResultsMap.containsKey(constituencyId)){
						List<PartyResultsVO> partyResultsVO = partyResultsMap.get(constituencyId);
						partyResultsVO.add(partyResult);
						partyResultsMap.put(constituencyId, partyResultsVO);
					}
				}
			}
			
			//map processing and set to VO
			if(!partyResultsMap.isEmpty()){
				Set entries = partyResultsMap.entrySet();
				Iterator iterator = entries.iterator();
				while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				List<PartyResultsVO> partyResults = (List<PartyResultsVO>)entry.getValue();
				Long constituencyId = (Long)entry.getKey();
				Constituency constituency = constituencyDAO.get(constituencyId);
				
				constituencyElectionResultAllPartyVO = new ConstituencyElectionResultAllPartyVO();
				constituencyElectionResultAllPartyVO.setElectionYear(electionYear);
				constituencyElectionResultAllPartyVO.setConstituencyId(constituencyId);
				constituencyElectionResultAllPartyVO.setConstituencyName(constituency.getName());
				constituencyElectionResultAllPartyVO.setPartyResultsVO(partyResults);
				
				constituencyElectionResultAllPartyVOList.add(constituencyElectionResultAllPartyVO);
				}
			}
		}
		
	 return constituencyElectionResultAllPartyVOList;	
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getElectionResultForPartyInMandal(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public MandalElectionResultVO getElectionResultForPartyInMandal(Long mandalId,
			String electionType,String electionYear) {
		
		if(log.isDebugEnabled())
			log.debug(" Inside getElectionResultForPartyInMandal Method ...");
		
		ResultStatus resultStatus = new ResultStatus();
		MandalElectionResultVO mandalElectionResultVO = null;
		List<PartyElectionResultsInConstituencyVO> partyElecResultsInConstituency = null;
		Map<Long,PartyElectionResultsInConstituencyVO> partyResultsMap = null;
		
		if(mandalId != null && !mandalId.equals(new Long(0))){
			
			partyElecResultsInConstituency = new ArrayList<PartyElectionResultsInConstituencyVO>();
			partyResultsMap = new HashMap<Long,PartyElectionResultsInConstituencyVO>();
			
			try{
			List electionResultsList = candidateBoothResultDAO.getElectionResultsInAMandalForAllParties(mandalId, electionType, electionYear);
			if(electionResultsList != null && electionResultsList.size() > 0){
				
				mandalElectionResultVO = new MandalElectionResultVO();
				for(int i=0;i<electionResultsList.size();i++){
					Object[] params = (Object[])electionResultsList.get(i);
					Long constituencyId = (Long)params[5];
					String constiName = (String)params[6];
					
					if(log.isDebugEnabled())
						log.debug(" Mandal In Constituency :"+constiName);
					
					if(partyResultsMap.isEmpty() || !partyResultsMap.containsKey(constituencyId)){
						PartyElectionResultsInConstituencyVO partyResultsInMap = new PartyElectionResultsInConstituencyVO();
						List<PartyResultsVO> partyElecResults = new ArrayList<PartyResultsVO>();
						partyResultsInMap.setConstituencyId(constituencyId);
						partyResultsInMap.setConstituencyName(constiName);
						partyResultsInMap.setPartyElecResults(partyElecResults);
						partyResultsMap.put(constituencyId, partyResultsInMap);
					}
					
					//assign party results to vo
					Long votesEarned = (Long)(Long)params[2];
					if(log.isDebugEnabled())
						log.debug(" Party VE :" + votesEarned);
					if(votesEarned != null && votesEarned > new Long(0)){
					PartyResultsVO partyResults = new PartyResultsVO();
					partyResults.setPartyId((Long)params[0]);
					partyResults.setPartyName((String)params[1]);
					partyResults.setVotesEarned((Long)params[2]);
					partyResults.setValidVotes((Long)params[3]);
					partyResults.setRank((Long)params[4]);
					
					Double votesPercent = new BigDecimal(((Long)params[2]).doubleValue()/((Long)params[3]).doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					partyResults.setPercentage(votesPercent.toString());
					
					if(partyResultsMap.containsKey(constituencyId)){
						PartyElectionResultsInConstituencyVO partyResultsInMap = partyResultsMap.get(constituencyId);
						if(partyResultsInMap != null){
							List<PartyResultsVO> partyElecResults = partyResultsInMap.getPartyElecResults();
							
							if(partyResults.getPartyName().equalsIgnoreCase("IND")){
								partyElecResults = getIndependentCandidatesGrouped(partyElecResults,partyResults);
							}
							else{
							partyElecResults.add(partyResults);
							}
														
							partyResultsInMap.setPartyElecResults(partyElecResults);
							partyResultsMap.put(constituencyId, partyResultsInMap);
						}
					}
					}
				}
				
				//processing the map
				if(!partyResultsMap.isEmpty()){
					Set entries = partyResultsMap.entrySet();
					Iterator iterator = entries.iterator();
					while(iterator.hasNext()){
					Map.Entry entry = (Map.Entry)iterator.next();
					PartyElectionResultsInConstituencyVO partyResultsInMap = (PartyElectionResultsInConstituencyVO)entry.getValue();
					
					//partyResultsInMap = getPartiesSorted(partyResultsInMap,parties);
					Collections.sort(partyResultsInMap.getPartyElecResults(), new PartyResultsVOComparator());
					
					partyElecResultsInConstituency.add(partyResultsInMap);
					}
				}
			}
			if(mandalElectionResultVO != null && partyElecResultsInConstituency.size() > 0){
				//set to final vo
				Tehsil tehsil = tehsilDAO.get(mandalId);
				mandalElectionResultVO.setMandalId(mandalId);
				mandalElectionResultVO.setMandalName(tehsil.getTehsilName());
				mandalElectionResultVO.setPartyElecResultsInConstituency(partyElecResultsInConstituency);
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
				if(log.isDebugEnabled())
					log.debug("Exception Raised :" + ex);
				resultStatus.setExceptionEncountered(ex);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setResultPartial(true);
			}
			finally{
				//mandalElectionResultVO.setResultStatus(resultStatus);
			}
			
		}
		
	 return mandalElectionResultVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getElectionResultsForMandalsInAConstituencyForAElection(java.util.List, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public ElectionResultsForMandalVO getElectionResultsForMandalsInAConstituencyForAElection(
			List<Long> mandalIds, String electionType, String electionYear){
		
		if(log.isDebugEnabled())
			log.debug(" Inside getElectionResultsForMandalsInAConstituencyForAElection method ..");
		
		ElectionResultsForMandalVO electionResultsForMandalVO = null;
		List<MandalElectionResultVO> electionResultsForMandal = null;
		ResultStatus resultStatus = new ResultStatus();
		
		if(mandalIds != null && mandalIds.size() > 0 && electionType != null && electionYear != null){
			
			electionResultsForMandal = new ArrayList<MandalElectionResultVO>();
			
			try{
				
			  //election results for mandals in a constituency	
			  for(Long mandal:mandalIds){
				MandalElectionResultVO mandalResult = getElectionResultForPartyInMandal(mandal,electionType,electionYear);
				if(mandalResult != null)
					electionResultsForMandal.add(mandalResult);
			  }
			  
			  log.debug(" ......................... Mandal Result size :.........." + electionResultsForMandal.size());
			  //set to VO
			  if(electionResultsForMandal != null && electionResultsForMandal.size() > 0){
				  electionResultsForMandalVO = new ElectionResultsForMandalVO();
				  electionResultsForMandalVO.setElectionType(electionType);
				  electionResultsForMandalVO.setElectionYear(electionYear);
				  electionResultsForMandalVO.setElectionResultsForMandal(electionResultsForMandal);
				  
				  List election = null;
				  if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		        	  election = electionDAO.findElectionIdByParliamentElectionTypeAndYear(electionType, electionYear);
		          else
				      election = electionDAO.findElectionIdByElectionTypeAndYear(electionType,electionYear,new Long(1));
				  if(election != null && election.size() > 0){
					  Object params = (Object)election.get(0);
					  electionResultsForMandalVO.setElectionId((Long)params);
				  }
			  }
			  
			  //For parties participated info
			  Map<Long,String> partyIdAndName = new HashMap<Long,String>();
			  List<SelectOptionVO> partysIdsAndName = new ArrayList<SelectOptionVO>();
			  if(electionResultsForMandal != null && electionResultsForMandal.size() > 0){
				  
				  partyIdAndName = getPartiesParticipatedDetails(electionResultsForMandal);
				 
				  if(!partyIdAndName.isEmpty()){
					Set entries = partyIdAndName.entrySet();
					Iterator iterator = entries.iterator();
					while(iterator.hasNext()){
					Map.Entry entry = (Map.Entry)iterator.next();
					
					SelectOptionVO party = new SelectOptionVO();
					party.setId((Long)entry.getKey());
					party.setName((String)entry.getValue());
					
					partysIdsAndName.add(party);
					log.debug(" Party Ids and Names :" + party.getName());
					}
				  }
				  Collections.sort(partysIdsAndName, new PartyResultsComparator());
				  electionResultsForMandalVO.setPartysList(partysIdsAndName);
				  
				  //assign party's not participated mandal results empty in VO
                  for(MandalElectionResultVO manElecRes:electionResultsForMandal){
					  
					  List<PartyElectionResultsInConstituencyVO> partyConstiRes = manElecRes.getPartyElecResultsInConstituency();
					  
					  for(PartyElectionResultsInConstituencyVO partyResInConsti:partyConstiRes){
						  
						  List<PartyResultsVO> partyElecResults = partyResInConsti.getPartyElecResults();
						  
						  for(SelectOptionVO partiPartys:partysIdsAndName){
							  boolean flag = checkForParty(partyElecResults,partiPartys);
							  if(flag == false){
								  PartyResultsVO partyRes = new PartyResultsVO();
								  partyRes.setPartyId(partiPartys.getId());
								  partyRes.setPartyName(partiPartys.getName());
								  partyRes.setVotesEarned(new Long(0));
								  partyRes.setTotalSeatsWon(0);
								  partyRes.setPercentage("--");
								  partyResInConsti.getPartyElecResults().add(partyRes);
							  }
						  }
						  Collections.sort(partyResInConsti.getPartyElecResults(), new PartyResultsVOComparator());
					  }
				
				  }
				  
			  }
			
			}
			catch(Exception ex){
				ex.printStackTrace();
				if(log.isDebugEnabled())
					log.debug("Exception Raised :" + ex);
				resultStatus.setExceptionEncountered(ex);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setResultPartial(true);
			}
			finally{
				//electionResultsForMandalVO.setResultStatus(resultStatus);
			}
		}
	 return electionResultsForMandalVO;
	}
	
	public boolean checkForParty(List<PartyResultsVO> partyElecRes,SelectOptionVO partiParty){
		
		if(partyElecRes != null && partiParty != null){
		for(PartyResultsVO results:partyElecRes){
			if(partiParty.getId().equals(results.getPartyId()))
				return true;
		}
		}
		return false;
	}

	/*
	 * Method returns parties that has been participated in a particular election in set of mandals in a constituency
	 */
	public Map<Long,String> getPartiesParticipatedDetails(List<MandalElectionResultVO> electionResultsForMandal){
		
		log.debug(" Inside getPartiesParticipatedDetails Method ...");
		 Map<Long,String> partyIdAndName = null;
		 if(electionResultsForMandal != null){
			 
			 partyIdAndName = new HashMap<Long,String>();
			 for(MandalElectionResultVO manElecRes:electionResultsForMandal){
				 List<PartyElectionResultsInConstituencyVO> partyConstiRes = manElecRes.getPartyElecResultsInConstituency();
				  
				  for(PartyElectionResultsInConstituencyVO partyResInConsti:partyConstiRes){
					  
					  List<PartyResultsVO> partyElecResults = partyResInConsti.getPartyElecResults();
					  
					  for(PartyResultsVO partyRes:partyElecResults){
						  
						  if(partyIdAndName.isEmpty() || !partyIdAndName.containsKey(partyRes.getPartyId())){
							  partyIdAndName.put(partyRes.getPartyId(), partyRes.getPartyName()); 
						  }
					  }
				  }
			 }
		 }
		 
	 return partyIdAndName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getMandalWiseResultsForAConstituency(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<BiElectionResultsVO> getMandalWiseResultsForAConstituency(
			Long constituencyId) {
		
		if(log.isDebugEnabled())
			log.debug(" Inside getMandalWiseResultsForAConstituency method ..");
		
		List<BiElectionResultsVO> biElectionResultsVOList = null;
		
		if(constituencyId != null){
			biElectionResultsVOList = new ArrayList<BiElectionResultsVO>();
			ResultStatus result = new ResultStatus();
			
			try{
				
			//List<SelectOptionVO> partiesList = getParticipatedPartiesInfo(constituencyId);
			
			 List latestMandals = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(constituencyId);
			 List<Long> mandalIds = new ArrayList<Long>();
			 if(latestMandals != null && latestMandals.size() > 0){
				for(int i=0;i<latestMandals.size();i++){
					Object[] params = (Object[])latestMandals.get(i);
					Long mandalId = (Long)params[0];
					String mandalName = (String)params[1];
					
					mandalIds.add(mandalId);
				}
				
				if(mandalIds != null && mandalIds.size() > 0){
					
					//for 2010 bye election
					ElectionResultsForMandalVO  elecResultParli2010 = getElectionResultsForMandalsInAConstituencyForAElection(mandalIds,IConstants.ASSEMBLY_ELECTION_TYPE,"2010");
					BiElectionResultsVO biElectionResultTen = new BiElectionResultsVO();
					List<ElectionResultsForMandalVO> electionResultsTen = new ArrayList<ElectionResultsForMandalVO>();
					
					if(elecResultParli2010 != null){
						electionResultsTen.add(elecResultParli2010);
						biElectionResultTen.setConstituencyId(constituencyId);
						biElectionResultTen.setBiElectionResultsVO(electionResultsTen);
					biElectionResultsVOList.add(biElectionResultTen);
					}
					
					//for asembly 2009 & parliament 2009
					ElectionResultsForMandalVO  elecResultAssembly2009 = getElectionResultsForMandalsInAConstituencyForAElection(mandalIds,IConstants.ASSEMBLY_ELECTION_TYPE,"2009");
					ElectionResultsForMandalVO  elecResultParli2009 = getElectionResultsForMandalsInAConstituencyForAElection(mandalIds,IConstants.PARLIAMENT_ELECTION_TYPE,"2009");
					
					BiElectionResultsVO biElectionResultOne = new BiElectionResultsVO();
					List<ElectionResultsForMandalVO> electionResultsOne = new ArrayList<ElectionResultsForMandalVO>();
					
					if(elecResultAssembly2009 != null)
						electionResultsOne.add(elecResultAssembly2009);
					if(elecResultParli2009 != null)
						electionResultsOne.add(elecResultParli2009);
					biElectionResultOne.setConstituencyId(constituencyId);
					biElectionResultOne.setBiElectionResultsVO(electionResultsOne);
					//biElectionResultOne.setParticipatedParties(partiesList);
					biElectionResultsVOList.add(biElectionResultOne);
					
					
					//for parliament 2008
					ElectionResultsForMandalVO  elecResultParli2008 = getElectionResultsForMandalsInAConstituencyForAElection(mandalIds,IConstants.PARLIAMENT_ELECTION_TYPE,"2008");
					BiElectionResultsVO biElectionResultThree = new BiElectionResultsVO();
					List<ElectionResultsForMandalVO> electionResultsThree = new ArrayList<ElectionResultsForMandalVO>();
					
					if(elecResultParli2008 != null){
						electionResultsThree.add(elecResultParli2008);
					biElectionResultThree.setConstituencyId(constituencyId);
					biElectionResultThree.setBiElectionResultsVO(electionResultsThree);
					biElectionResultsVOList.add(biElectionResultThree);
					}
					
					
					//for parliament 2004
					ElectionResultsForMandalVO  elecResultParli2006 = getElectionResultsForMandalsInAConstituencyForAElection(mandalIds,IConstants.PARLIAMENT_ELECTION_TYPE,"2006");
					BiElectionResultsVO biElectionResultFour = new BiElectionResultsVO();
					List<ElectionResultsForMandalVO> electionResultsFour = new ArrayList<ElectionResultsForMandalVO>();
					
					if(elecResultParli2006 != null){
						electionResultsFour.add(elecResultParli2006);
						biElectionResultFour.setConstituencyId(constituencyId);
						biElectionResultFour.setBiElectionResultsVO(electionResultsFour);
					biElectionResultsVOList.add(biElectionResultFour);
					}
					
					
					//for assembly 2004 and parliament 2004
					ElectionResultsForMandalVO  elecResultAssembly2004 = getElectionResultsForMandalsInAConstituencyForAElection(mandalIds,IConstants.ASSEMBLY_ELECTION_TYPE,"2004");
					ElectionResultsForMandalVO  elecResultParli2004 = getElectionResultsForMandalsInAConstituencyForAElection(mandalIds,IConstants.PARLIAMENT_ELECTION_TYPE,"2004");

					BiElectionResultsVO biElectionResultTwo = new BiElectionResultsVO();
					List<ElectionResultsForMandalVO> electionResultsTwo = new ArrayList<ElectionResultsForMandalVO>();
					
					if(elecResultAssembly2004 != null)
						electionResultsTwo.add(elecResultAssembly2004);
					if(elecResultParli2004 != null)
						electionResultsTwo.add(elecResultParli2004);
					biElectionResultTwo.setConstituencyId(constituencyId);
					biElectionResultTwo.setBiElectionResultsVO(electionResultsTwo);
					//biElectionResultTwo.setParticipatedParties(partiesList);
					
					
					biElectionResultsVOList.add(biElectionResultTwo);
					
				}
			 }
			
			}
			catch(Exception ex){
				ex.printStackTrace();
				if(log.isDebugEnabled())
					log.debug("Exception Raised :" + ex);
				result.setExceptionEncountered(ex);
				result.setResultCode(ResultCodeMapper.FAILURE);
				result.setResultPartial(true);
			}
			
		}
	  return biElectionResultsVOList;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getParticipatedPartiesInfo(Long constituencyId){
		log.debug(" Inside getParticipatedPartiesInfo method ...");
		
		List<SelectOptionVO> parties = null;
		
		List partiesList = candidateBoothResultDAO.getAllPartiesPariticipatedInAConstituencyElection(constituencyId);
		
		if(partiesList != null && partiesList.size() > 0){
			
			parties = new ArrayList<SelectOptionVO>();
			for(int i=0;i<partiesList.size();i++){
			Object params = (Object)partiesList.get(i);
			
			Party party = (Party)params;
			SelectOptionVO partyOption = new SelectOptionVO();
			partyOption.setId(party.getPartyId());
			partyOption.setName(party.getShortName());
			
			parties.add(partyOption);
			}
			
			Collections.sort(parties, new PartyResultsComparator());
		}
		
	 return parties;
	}

	public PartyElectionResultsInConstituencyVO getPartiesSorted(PartyElectionResultsInConstituencyVO partyResults,List<SelectOptionVO> parties){
		
		log.debug(" Inside getPartiesSorted Method ..");
		
		Map<Long,String> partiesList = new HashMap<Long,String>();
		for(PartyResultsVO partyResult:partyResults.getPartyElecResults()){
			partiesList.put(partyResult.getPartyId(), partyResult.getPartyName());
		}
		
		for(SelectOptionVO result:parties){
			if(!partiesList.isEmpty() && partiesList.containsKey(result.getId())){
				log.debug(" Party Result Availble ...");
			}
			else{
				PartyResultsVO partyRslt = new PartyResultsVO();
				partyRslt.setPartyId(result.getId());
				partyRslt.setPartyName(result.getName());
				partyRslt.setVotesEarned(new Long(0));
				partyRslt.setPercentage("--");
				
				partyResults.getPartyElecResults().add(partyRslt);
				
			}
		}
		
	 return partyResults;		
	}
	
	/*
	 * Processing independent candidate results
	 */
	public List<PartyResultsVO> getIndependentCandidatesGrouped(List<PartyResultsVO> totalResults,PartyResultsVO partyResult){
		
		log.debug(" Inside getIndependentCandidatesGrouped method ...");
		List<PartyResultsVO> partyResultsVOList = null;
		
		if(totalResults != null && partyResult != null){
			
		partyResultsVOList = new ArrayList<PartyResultsVO>();
		Long votesEarned = new Long(0);
		
		for(PartyResultsVO partyReslt:totalResults){
			if(!partyReslt.getPartyName().equalsIgnoreCase("IND"))
				partyResultsVOList.add(partyReslt);
			
			if(partyReslt.getPartyName().equalsIgnoreCase("IND")){
				votesEarned+=partyReslt.getVotesEarned();
			}
		}
		votesEarned+=partyResult.getVotesEarned();
		PartyResultsVO resultVO = new PartyResultsVO();
		resultVO.setPartyId(partyResult.getPartyId());
		resultVO.setPartyName(partyResult.getPartyName());
		resultVO.setVotesEarned(votesEarned);
		
		Long validVotes = partyResult.getValidVotes();
		resultVO.setValidVotes(validVotes);
		Double votesPercent = new BigDecimal(votesEarned.doubleValue()/validVotes.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		resultVO.setPercentage(votesPercent.toString());
		
		partyResultsVOList.add(resultVO);
				
		}
	 return partyResultsVOList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getPartyMarginResultsInAMandalForAllElections(java.lang.Long)
	 * Method to get booth wise party results in a mandal for a particular election
	 * @Input Params : tehsilId,partyId and electionYear
	 * @Output : VO that contains the booth wise results in that mandal 
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Map<Long,List<BoothResultVO>>> getPartyMarginResultsInAMandalForAllElections(
			Long tehsilId,Long constiId,Long partyId,String electionYear,String electnType,String partyType,Long rank) 
			throws Exception{
	   
		if(log.isDebugEnabled())
			log.debug(" Inside getPartyMarginResultsInAMandalForAllElections Method .. ");
		Map<String,Map<Long,List<BoothResultVO>>> boothwiseResultsInMandalMap = null;
		
		if(tehsilId != null){
			
			boothwiseResultsInMandalMap = new HashMap<String,Map<Long,List<BoothResultVO>>>();
			List mandalBoothResults = null;
						
			//DAO call to get all booth wise party results in a mandal for a particular year
			if(partyType.equals(IConstants.MAIN_PARTY))
			    mandalBoothResults = candidateBoothResultDAO.getBoothWisePartyResultsInAMandal(tehsilId, partyId, electionYear);
			else if(partyType.equals(IConstants.OPP_PARTY))
				mandalBoothResults = candidateBoothResultDAO.getBoothWisePartyResultsInAMandalByPartyRank(tehsilId,constiId, electionYear,electnType, rank);
			
			
			if(mandalBoothResults != null && mandalBoothResults.size() > 0){
				
				//Iterator to iterate the results list
				Iterator listIterator = mandalBoothResults.listIterator();
				while(listIterator.hasNext()){
					Object[] listParams = (Object[])listIterator.next();
					String electionType = (String)listParams[7];
					Map<Long,List<BoothResultVO>> partyBoothResultsMap = null;
					List<BoothResultVO> boothResults = null;
					
					//if Map Does'nt contain the election type
					if(boothwiseResultsInMandalMap.isEmpty() || !boothwiseResultsInMandalMap.containsKey(electionType)){
						partyBoothResultsMap = new HashMap<Long,List<BoothResultVO>>();
						boothwiseResultsInMandalMap.put(electionType, partyBoothResultsMap);
					}
					else{
						partyBoothResultsMap = boothwiseResultsInMandalMap.get(electionType);
					}
					
					Long constituencyId = (Long)listParams[3];
					if(partyBoothResultsMap != null){
						
						//if Map Does'nt contain the constituency
						if(partyBoothResultsMap.isEmpty() || !partyBoothResultsMap.containsKey(constituencyId)){
							boothResults = new ArrayList<BoothResultVO>();
							partyBoothResultsMap.put(constituencyId, boothResults);
						}
						else{
							boothResults = partyBoothResultsMap.get(constituencyId);
						}
						
						BoothResultVO partyBoothResult = null;
						if(partyType.equals(IConstants.MAIN_PARTY))
						    partyBoothResult = getBoothResultForAParty(listParams);
						else if(partyType.equals(IConstants.OPP_PARTY))
							partyBoothResult = getBoothResultForOppParty(listParams);
							
						if(partyBoothResult != null){
							boothResults.add(partyBoothResult);
							partyBoothResultsMap.put(constituencyId, boothResults);
						}
						
						//set to main Map
						boothwiseResultsInMandalMap.put(electionType, partyBoothResultsMap);
					}
					
				}
			}
			
		}
		
				
     return boothwiseResultsInMandalMap;
	}
	
	/*
	 * Sets booth result data to VO and returns it to called function
	 */
	public BoothResultVO getBoothResultForAParty(Object[] resultObj){
		
		BoothResultVO boothResult = null;
		
		if(resultObj != null){
			
			Booth booth = (Booth)resultObj[0];
			boothResult = new BoothResultVO();
			Long votesEarned = (Long)resultObj[1];
			Long validVotes = (Long)resultObj[2];
			
			boothResult.setBoothId(booth.getBoothId());
			boothResult.setPartNo(booth.getPartNo());
			boothResult.setLocation(booth.getLocation());
			boothResult.setMandal(booth.getTehsil().getTehsilName());
			boothResult.setVillagesCovered(booth.getVillagesCovered());
			boothResult.setTotalVoters(validVotes.intValue());
			boothResult.setVotesEarned(votesEarned.intValue());
			boothResult.setPercentage((String)resultObj[8]);
		}
		
		return boothResult;
	}
	
	/*
	 * Sets booth result for opp party data to VO and returns it to called function
	 */
	public BoothResultVO getBoothResultForOppParty(Object[] resultObj){
         BoothResultVO boothResult = null;
		
		if(resultObj != null){
			
			Booth booth = (Booth)resultObj[0];
			boothResult = new BoothResultVO();
			Long votesEarned = (Long)resultObj[1];
			Long validVotes = (Long)resultObj[2];
			
			boothResult.setPartNo(booth.getPartNo());
			boothResult.setOppPartyId((Long)resultObj[5]);
			boothResult.setOppParty((String)resultObj[6]);
			boothResult.setOppPartyVotesEarned(votesEarned.intValue());
			boothResult.setOppPartyPercentage((String)resultObj[8]);
		}
		
	 return boothResult;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getPartyVotesMarginResults(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public Map<String, Map<String, Map<Long, List<BoothResultVO>>>> getPartyVotesMarginResults(
			Long constituencyId, Long mandalId, Long partyId) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug(" Inside getPartyVotesMarginResults Method ...");
		Map<String, Map<String, Map<Long, List<BoothResultVO>>>> partyResultsInAMandalMap = null;
		
		if(constituencyId != null && mandalId != null && partyId != null){
			
			partyResultsInAMandalMap = new HashMap<String, Map<String, Map<Long, List<BoothResultVO>>>>();
			
			//for 2009
			Map<String, Map<Long, List<BoothResultVO>>> resultsOne = getProcessedResultsForMainPartyAndOppParty(constituencyId,
					partyId,mandalId,IConstants.PRESENT_ELECTION_YEAR);
			if(resultsOne != null && !resultsOne.isEmpty()){
				partyResultsInAMandalMap.put(IConstants.PRESENT_ELECTION_YEAR, resultsOne);
			}
			
			//for 2004
			Map<String, Map<Long, List<BoothResultVO>>> resultsTwo = getProcessedResultsForMainPartyAndOppParty(constituencyId,
					partyId,mandalId,IConstants.PREVIOUS_ELECTION_YEAR);
			if(resultsTwo != null && !resultsTwo.isEmpty()){
				partyResultsInAMandalMap.put(IConstants.PREVIOUS_ELECTION_YEAR, resultsTwo);
			}
		}
		
	 return partyResultsInAMandalMap;
	}
	
	/*
	 * Process the results for main party and opposition party
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Map<Long, List<BoothResultVO>>> getProcessedResultsForMainPartyAndOppParty(Long constituencyId,Long partyId,Long mandalId,String electionYear) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Inside getProcessedResultsForMainPartyAndOppParty Method ...");
		
		Map<String, Map<Long, List<BoothResultVO>>> boothWiseResultsMainMap = new HashMap<String, Map<Long, List<BoothResultVO>>>();
		
		if(mandalId != null && constituencyId != null && electionYear != null){
		    	
			Map<String, Map<Long, List<BoothResultVO>>> mainPartyResults = getPartyMarginResultsInAMandalForAllElections(mandalId,null,partyId,electionYear,null,IConstants.MAIN_PARTY,new Long(0));
			//Map<String, Map<Long, List<BoothResultVO>>> oppositnPartyResults = new HashMap<String, Map<Long, List<BoothResultVO>>>();			
			
			//getting results for opposition parties
			if(mainPartyResults != null && !mainPartyResults.isEmpty()){
			 Set<String> elecTypes = mainPartyResults.keySet();
			 for(String electionTyp:elecTypes){
				 
			   Map<Long, List<BoothResultVO>> mainPartyRes = mainPartyResults.get(electionTyp);
			 
			   Map<String, Map<Long, List<BoothResultVO>>> oppPartyResults = new HashMap<String, Map<Long, List<BoothResultVO>>>();
			   Map<Long, List<BoothResultVO>> oppPartysMap = new HashMap<Long, List<BoothResultVO>>();
			   Set<Long> mainPartyConstiIds = mainPartyRes.keySet();
			   for(Long consti:mainPartyConstiIds){
			   //List rankOfPartyCandidate = nominationDAO.getCandidateRankInAConstituencyElection(consti, electionYear, electionTyp, partyId);
			   Long rank = getCandidateRankInAParticipatedElection(consti,electionYear,electionTyp,partyId);
				 if(rank != null && !rank.equals(new Long(0))){
				 //Object rankParam = (Object)rankOfPartyCandidate.get(0);
				
				 Long oppCandRank = new Long(0);
				 if(rank.equals(new Long(1)))
					 oppCandRank = new Long(2);
				 else if(rank > new Long(1))
					 oppCandRank = new Long(1);
				 
				 Map<String, Map<Long, List<BoothResultVO>>> oppPartyResults1 = getPartyMarginResultsInAMandalForAllElections(mandalId,consti,partyId,electionYear,electionTyp,IConstants.OPP_PARTY,oppCandRank);
				 if(oppPartyResults1 != null && !oppPartyResults1.isEmpty()){
					 Map<Long, List<BoothResultVO>> oppParty = oppPartyResults1.get(electionTyp);
					 if(!oppParty.isEmpty()){
						 List<BoothResultVO> oppPartyBooth = oppParty.get(consti);
						 oppPartysMap.put(consti, oppPartyBooth);
					 }
					
				  }
			     }
			   }
			     
			     oppPartyResults.put(electionTyp, oppPartysMap);
				 Boolean flag = false;
				 
				 if(oppPartyResults != null && !oppPartyResults.isEmpty()){
					 Map<Long, List<BoothResultVO>> oppPartyRes = oppPartyResults.get(electionTyp);
					// oppositnPartyResults.put(electionTyp, oppPartyRes);
					 
					 Map<String, Map<Long, List<BoothResultVO>>> partyAndOppPartyRes = getMainPartyAndOppPartyGroupedResults(mainPartyRes,oppPartyRes,electionTyp);
					 
					 if(partyAndOppPartyRes != null && !partyAndOppPartyRes.isEmpty()){
					 	 boothWiseResultsMainMap.put(electionTyp, partyAndOppPartyRes.get(electionTyp));
					 	 flag = true;
					 }
				 }
				 else if(oppPartyResults == null || oppPartyResults.isEmpty() || flag == false){
					 boothWiseResultsMainMap.put(electionTyp, mainPartyRes);
				 }
			   
			 
			 }
			}
		}
	 return boothWiseResultsMainMap;
	}
	
	/*
	 * Method To Get Candidate Rank In An Election
	 * @Input constituencyId,electionYear,electionType,partyId
	 * @Output rank of the candidate
	 */
	@SuppressWarnings("unchecked")
	public Long getCandidateRankInAParticipatedElection(Long constiId,String electionYear,String electionType,Long partyId){
		if(log.isDebugEnabled())
			log.debug(" Inside getCandidateRankInAParticipatedElection Method ...");
		Long rank = new Long(0);
		
		List rankOfPartyCandidate = nominationDAO.getCandidateRankInAConstituencyElection(constiId, electionYear, electionType, partyId);
		 Object rankParam = (Object)rankOfPartyCandidate.get(0);
		 rank = (Long)rankParam;
	 return rank;
	}
	
	/*
	 * Adds the main party and opp party results into single Map
	 */
	public Map<String, Map<Long, List<BoothResultVO>>> getMainPartyAndOppPartyGroupedResults(Map<Long, List<BoothResultVO>> mainParty,
			Map<Long, List<BoothResultVO>> oppParty,String elecType) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Inside getMainPartyAndOppPartyGroupedResults Method ..");
		
		Map<String, Map<Long, List<BoothResultVO>>> finalPartyResultsMap = null;
		if(mainParty != null && !mainParty.isEmpty() && oppParty != null && !oppParty.isEmpty()){
			finalPartyResultsMap = new HashMap<String, Map<Long, List<BoothResultVO>>>();
			
			Set<Long> constiSet = mainParty.keySet();
			if(constiSet != null){
				
				Map<Long, List<BoothResultVO>> allConstiResultsMap = new HashMap<Long, List<BoothResultVO>>();
				for(Long constiIds:constiSet){
					
					List<BoothResultVO> mainAndOppPartyRes = new ArrayList<BoothResultVO>();
					
					List<BoothResultVO> boothResultsForMainParty = mainParty.get(constiIds);
					List<BoothResultVO> boothResultsForOppParty = oppParty.get(constiIds);
					
					if(boothResultsForOppParty != null && boothResultsForOppParty.size() > 0){
					Map<String,BoothResultVO> oppPartyBoothResMap = getBoothResultsDataMapByPartNo(boothResultsForOppParty);
					
					for(BoothResultVO mainPartyConsti:boothResultsForMainParty){
						if(oppPartyBoothResMap.containsKey(mainPartyConsti.getPartNo())){
							BoothResultVO oppRes = oppPartyBoothResMap.get(mainPartyConsti.getPartNo());
							mainPartyConsti.setOppPartyId(oppRes.getOppPartyId());
							mainPartyConsti.setOppParty(oppRes.getOppParty());
							mainPartyConsti.setOppPartyVotesEarned(oppRes.getOppPartyVotesEarned());
							mainPartyConsti.setOppPartyPercentage(oppRes.getOppPartyPercentage());
							
							mainAndOppPartyRes.add(mainPartyConsti);
						}
					}
					
					}
					
					if(mainAndOppPartyRes.size() > 0){
						allConstiResultsMap.put(constiIds, mainAndOppPartyRes);
					}
					else{
						allConstiResultsMap.put(constiIds, boothResultsForMainParty);
					}
				}
				finalPartyResultsMap.put(elecType, allConstiResultsMap);
			}
			
		}
	 return finalPartyResultsMap;	
	}
	
	/*
	 * Method To Create BoothResults Map By PartNo As Key
	 */
	public Map<String,BoothResultVO> getBoothResultsDataMapByPartNo(List<BoothResultVO> resultsList){
		
		Map<String,BoothResultVO> resultsMap = new HashMap<String,BoothResultVO>();
		if(resultsList != null && resultsList.size() > 0){
			for(BoothResultVO boothResults:resultsList){
				resultsMap.put(boothResults.getPartNo(), boothResults);
			}
		}
	 return resultsMap;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getBoothWisePartyResultsForAMandal(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public PartyVotesMarginResultsInMandal getBoothWisePartyResultsForAMandal(Long constituencyId,Long mandalId,Long partyId){
		
		if(log.isDebugEnabled())
			log.debug(" Inside getBoothWisePartyResultsForAMandal Method ..");
		
		PartyVotesMarginResultsInMandal partyResultsInMandal = null;
		ResultStatus resultStatus = new ResultStatus();
		
		try{
			
			partyResultsInMandal = new PartyVotesMarginResultsInMandal();
					
		    if(constituencyId != null && mandalId != null && partyId != null){
			  Map<String,Map<String,Map<Long,List<BoothResultVO>>>> partyResultsInMap = getPartyVotesMarginResults(constituencyId,mandalId,partyId);
			  
			  if(!partyResultsInMap.isEmpty()){
				  
				  List<PartyVotesMarginResultsVO> partyVotesMarginResultsVO = new ArrayList<PartyVotesMarginResultsVO>();
				  
				  Set<String> electionYears = partyResultsInMap.keySet();
				  
				  for(String elecYear:electionYears){
					  
					  Map<String,Map<Long,List<BoothResultVO>>> partyResultsForElecTypes = partyResultsInMap.get(elecYear);
					  if(!partyResultsForElecTypes.isEmpty()){
						  Set<String> elecTypes = partyResultsForElecTypes.keySet();
						  
						  for(String elecTyp:elecTypes){
							 
							  PartyVotesMarginResultsVO resultsInElection = new PartyVotesMarginResultsVO();
							  List<PartyVotesMarginInConstituency> partyVotesMarginInConsti = new ArrayList<PartyVotesMarginInConstituency>();
							  Map<Long,List<BoothResultVO>> resultsForConstituency = partyResultsForElecTypes.get(elecTyp);
							  
							  if(!resultsForConstituency.isEmpty()){
								  Set<Long> constituencyIds = resultsForConstituency.keySet();
								  for(Long consti:constituencyIds){
									  PartyVotesMarginInConstituency partyVotesInConsti = new PartyVotesMarginInConstituency();
									  List<BoothResultVO> boothResults = resultsForConstituency.get(consti);
									  if(boothResults != null){
										  partyVotesInConsti.setConstituencyId(consti);
										  Constituency constitncy = constituencyDAO.get(consti);
										  partyVotesInConsti.setConstituencyName(constitncy.getName());
										  partyVotesInConsti.setBoothResults(boothResults);
										  partyVotesInConsti.setPartyResultsInVotesMarginVO(getMarginResultsInAMandal(boothResults,IConstants.VOTES_PERCENT));
										  
										  //party results high level overview
										  List mandalLevelResult = candidateBoothResultDAO.getPartyResultsInAMandalForAnElection(mandalId, consti, partyId, elecYear);
										  if(mandalLevelResult != null && mandalLevelResult.size() > 0){
											  Long candRank = getCandidateRankInAParticipatedElection(consti,elecYear,elecTyp,partyId);
											  Long oppCandRank = new Long(0);
											  if(candRank.equals(new Long(1)))
												  oppCandRank = new Long(2);
											  else if(candRank > new Long(1))
												  oppCandRank = new Long(1);
											  List oppPartyMDResults = candidateBoothResultDAO.getPartyResultsInAMandalForAnElection(mandalId, consti, elecYear, oppCandRank);
											  partyVotesInConsti.setPartyResultsOverview(getPartyMandalLevelResults(mandalLevelResult,oppPartyMDResults));
										  }
										  partyVotesMarginInConsti.add(partyVotesInConsti);
									  }
									  
								  }
								  if(partyVotesMarginInConsti.size() > 0){
									  resultsInElection.setElecionType(elecTyp);
									  resultsInElection.setElectionYear(elecYear);
									  resultsInElection.setPartyVotesMarginInConstituency(partyVotesMarginInConsti);
									  
									  partyVotesMarginResultsVO.add(resultsInElection);
								  }
							  }
						  }
					  }
					  
				  }
				  if(partyVotesMarginResultsVO.size() > 0){
					  partyResultsInMandal.setMandalId(mandalId);
					  partyResultsInMandal.setPartyVotesMarginResultsVO(partyVotesMarginResultsVO);
				  }
			  }
		    }
		
		}
		catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		finally{
			partyResultsInMandal.setResultStatus(resultStatus);
		}
	 return partyResultsInMandal;
	}
	
	@SuppressWarnings("unchecked")
	public MandalLevelResultsForParty getPartyMandalLevelResults(List resultsList,List oppCandResults){
		
		if(log.isDebugEnabled())
			log.debug(" Inside getPartyMandalLevelResults Method ...");
		MandalLevelResultsForParty mandalLevelResults = null;
		if(resultsList != null){
			
			mandalLevelResults = new MandalLevelResultsForParty();
			Iterator listIterator = resultsList.listIterator();
			while(listIterator.hasNext()){
				Object[] params = (Object[])listIterator.next();
				
				Long votesEarn = (Long)params[0];
				Long validVotes = (Long)params[1];
				
				mandalLevelResults.setTotalVoters((Long)params[2]);
				mandalLevelResults.setPolledVotes(validVotes);
				mandalLevelResults.setVotesEarned(votesEarn);
				mandalLevelResults.setTotalBooths((Long)params[3]);
				
				
				Double votesPercent = new BigDecimal(votesEarn.doubleValue()/validVotes.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				mandalLevelResults.setVotesPercent(votesPercent.toString());
			}
			
			if(oppCandResults != null && oppCandResults.size() > 0){
				Iterator oppCandResultsIter = oppCandResults.listIterator();
				while(oppCandResultsIter.hasNext()){
					Object[] params = (Object[])oppCandResultsIter.next();
					
					Long oppVotesEarn = (Long)params[0];
					Long oppValidVotes = (Long)params[1];
					
					Double oppVotesPercent = new BigDecimal(oppVotesEarn.doubleValue()/oppValidVotes.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					
					mandalLevelResults.setOppParty((String)params[2]);
					mandalLevelResults.setOppVotesEarned(oppVotesEarn);
					mandalLevelResults.setOppValidVotes(oppValidVotes);
					mandalLevelResults.setOppVotesPercent(oppVotesPercent.toString());
				}
			}
		}
		
	 return mandalLevelResults;	
	}
	
	/*
	 * 
	 */
	public List<PartyResultsInVotesMarginVO> getMarginResultsInAMandal(List<BoothResultVO> boothResults,String resultType){
		
		if(log.isDebugEnabled())
			log.debug("Inside getMarginResultsInAMandal Method ..");
		
		List<PartyResultsInVotesMarginVO> partyWiseMarginResults = new ArrayList<PartyResultsInVotesMarginVO>();
		Map<Long,List<BoothResultVO>> marginResults = new HashMap<Long,List<BoothResultVO>>();
		
		//set map with default values
		for(int i=1;i<=7;i++){
			List<BoothResultVO> bootResult = new ArrayList<BoothResultVO>();
			marginResults.put(new Long(i), bootResult);
		}
		
		if(boothResults!= null && boothResults.size() > 0){
			for(BoothResultVO results:boothResults){
				Double votesPercent = new Double(0);
				Double oppCandVotesPercent = new Double(0);
				
				//Double votesPercent = Double.valueOf(results.getPercentage());
				if(resultType.equals(IConstants.VOTES_PERCENT)){
				 votesPercent = new BigDecimal((new Double(results.getVotesEarned())/new Double(results.getTotalVoters()))*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				 oppCandVotesPercent = new BigDecimal((new Double(results.getOppPartyVotesEarned())/new Double(results.getTotalVoters()))*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
				 log.debug(" Votes Percent :" + votesPercent);
				}
				else if(resultType.equals(IConstants.VOTES_MARGIN)){
					log.debug(" Votes Percent :");
				}
				
				
				Long key = new Long(0);
				if(votesPercent > new Double(0) && votesPercent <= new Double(15))
					key = new Long(1);
				else if(votesPercent > new Double(15) && votesPercent <= new Double(30))
					key = new Long(2);
				else if(votesPercent > new Double(30) && votesPercent <= new Double(35))
					key = new Long(3);
				else if(votesPercent > new Double(35) && votesPercent <= new Double(40))
					key = new Long(4);
				else if(votesPercent > new Double(40) && votesPercent <= new Double(45))
					key = new Long(5);
				else if(votesPercent > new Double(45) && votesPercent <= new Double(50))
					key = new Long(6);
				else if(votesPercent > new Double(50) && votesPercent <= new Double(100))
					key = new Long(7);
				
				
				if(key != new Long(0)){
					List<BoothResultVO> bootResults = marginResults.get(key);
					results.setPercentage(votesPercent.toString());
					results.setOppPartyPercentage(oppCandVotesPercent.toString());
					bootResults.add(results);
					marginResults.put(key, bootResults);
				}
			}
			
			if(!marginResults.isEmpty()){
				Set<Long> mapKeys = marginResults.keySet();
				for(Long keyValue:mapKeys){
					
					Long marginValue1 = new Long(0);
					Long marginValue2 = new Long(0);
					int resultsCount = 0;
					List<BoothResultVO> boothResultsVO = new ArrayList<BoothResultVO>();
					
					
					switch(keyValue.intValue()){
					case 1:
						   marginValue1 = new Long(0);
						   marginValue2 = new Long(15);
						   boothResultsVO = marginResults.get(keyValue);
						   resultsCount = boothResultsVO.size();
						   break;
					case 2:	
						   marginValue1 = new Long(15);
						   marginValue2 = new Long(30);
						   boothResultsVO = marginResults.get(keyValue);
						   resultsCount = boothResultsVO.size();
						   break;
					case 3:
						   marginValue1 = new Long(30);
						   marginValue2 = new Long(35);
						   boothResultsVO = marginResults.get(keyValue);
						   resultsCount = boothResultsVO.size();
						   break;
					case 4:
						   marginValue1 = new Long(35);
						   marginValue2 = new Long(40);
						   boothResultsVO = marginResults.get(keyValue);
						   resultsCount = boothResultsVO.size();
						   break;
					case 5:
						   marginValue1 = new Long(40);
						   marginValue2 = new Long(45);
						   boothResultsVO = marginResults.get(keyValue);
						   resultsCount = boothResultsVO.size();
						   break;
					case 6:
						   marginValue1 = new Long(45);
						   marginValue2 = new Long(50);
						   boothResultsVO = marginResults.get(keyValue);
						   resultsCount = boothResultsVO.size();
						   break;
					case 7:
						   marginValue1 = new Long(50);
						   marginValue2 = new Long(100);
						   boothResultsVO = marginResults.get(keyValue);
						   resultsCount = boothResultsVO.size();
						   break;
					default:
						    log.debug("Invalid Case ...");
					        break;
					}
					PartyResultsInVotesMarginVO resultVO = new PartyResultsInVotesMarginVO();
			        resultVO.setMarginValue1(marginValue1.intValue());
			        resultVO.setMarginValue2(marginValue2.intValue());
			        resultVO.setResultsCount(resultsCount);
			        resultVO.setBoothResultsVO(marginResults.get(keyValue));
			        partyWiseMarginResults.add(resultVO);
				}
			}
		}
		
	 return partyWiseMarginResults;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getVotesMarginResultsCompleteDetails(java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public VotesMarginResultsMainVO getVotesMarginResultsCompleteDetails(
			Long constituencyId, Long partyId) {
		
		VotesMarginResultsMainVO votesMarginResultsMainVO = null;
		List<PartyVotesMarginResultsInMandal> partyVotesMarginResultsInMandal = null;
		ResultStatus resultStatus = new ResultStatus();
		try{
		  if(constituencyId != null && partyId != null){
			votesMarginResultsMainVO = new VotesMarginResultsMainVO();
			partyVotesMarginResultsInMandal = new ArrayList<PartyVotesMarginResultsInMandal>();
			
			List latestMandals = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(constituencyId);
			 
			 if(latestMandals != null && latestMandals.size() > 0){
				for(int i=0;i<latestMandals.size();i++){
					Object[] params = (Object[])latestMandals.get(i);
					Long mandalId = (Long)params[0];
					String mandalName = (String)params[1];
					String chartName = "MandalWiseElectionResultFor_"+mandalName+"_"+mandalId+"_For"+constituencyId+"_"+partyId+".png";
					PartyVotesMarginResultsInMandal partyResultsInMandal = getBoothWisePartyResultsForAMandal(constituencyId,mandalId,partyId);
					if(partyResultsInMandal != null){
						partyResultsInMandal.setMandalName(mandalName);
						partyResultsInMandal.setChartName(chartName);
					    partyVotesMarginResultsInMandal.add(partyResultsInMandal);
					}
				}
				
				if(partyVotesMarginResultsInMandal.size() > 0){
					votesMarginResultsMainVO.setConstituencyId(constituencyId);
					votesMarginResultsMainVO.setPartyVotesMarginResultsInMandal(partyVotesMarginResultsInMandal);
				}
			 }
		  }
		}
		catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		finally{
			votesMarginResultsMainVO.setResultStatus(resultStatus);
		}
	 return votesMarginResultsMainVO;
	}

	@SuppressWarnings("unchecked")
	public AllBoothsResultsForAPartyInAMandal getAllBoothsResultsInAConstituency(
			Long tehsilId, Long partyId, Long constituencyId,
			String electionYear, String electionType) {
		log.debug("Entered in to getAllBoothsResultsInAConstituency method in BiElection Service");
		AllBoothsResultsForAPartyInAMandal allBoothsResultsForAPartyInAMandal = new AllBoothsResultsForAPartyInAMandal();
		List<BoothResultVO> allBoothsResults= new ArrayList<BoothResultVO>();
		try{
			List boothsResultsList = candidateBoothResultDAO.getBoothWisePartyResultsInAMandalByConstituencyId(tehsilId, partyId, constituencyId, electionYear, electionType);
			log.debug("allBoothsResults size in  getAllBoothsResultsInAConstituency method in BiElection Service" + boothsResultsList.size());
			Long candRank = getCandidateRankInAParticipatedElection(constituencyId,electionYear,electionType,partyId);
			Long oppCandRank = new Long(0);
			
			if(candRank.equals(new Long(1)))
				  oppCandRank = new Long(2);
			  else if(candRank > new Long(1))
				  oppCandRank = new Long(1);
			List oppPartyResults = candidateBoothResultDAO.getBoothWisePartyResultsInAMandalByPartyRank(tehsilId, constituencyId, electionYear, electionType, oppCandRank);
			log.debug("oppPartyResults size in  getAllBoothsResultsInAConstituency method in BiElection Service" + oppPartyResults.size());
			if(boothsResultsList.size() != oppPartyResults.size())
			{
				throw new Exception("Partial booth results");
			} else{ 
				
					for(int i =0; i< boothsResultsList.size();i++)
						{
							BoothResultVO boothResultVO = new  BoothResultVO();
							
							Object[] boothResultObjArr = (Object[])boothsResultsList.get(i);
							Object[] oppPartyResultObjArr = (Object[])oppPartyResults.get(i);
							
							Long votesEarned = new Long(boothResultObjArr[1].toString());
							Long validVotes = new Long(boothResultObjArr[2].toString());
							
							Long oppPartyVotesEarned = new Long(oppPartyResultObjArr[1].toString());
							Long oppPartyValidVotes = new Long(oppPartyResultObjArr[2].toString());
							
							Booth booth = (Booth)boothResultObjArr[0];
							Booth booth1 = (Booth)oppPartyResultObjArr[0];
							String villagesCovered = booth.getVillagesCovered();
							if(booth.getBoothId() == booth1.getBoothId())
							{
								boothResultVO.setBoothId(booth.getBoothId());
								boothResultVO.setPartNo(booth.getPartNo());
								boothResultVO.setLocation(booth.getLocation());
								boothResultVO.setMandal(booth.getTehsil().getTehsilName());
																
								if(villagesCovered.contains("\n")){
									villagesCovered = villagesCovered.replace("\n"," ");
									boothResultVO.setVillagesCovered(villagesCovered);
								}else
									boothResultVO.setVillagesCovered(villagesCovered);
								
								boothResultVO.setVotesEarned(votesEarned.intValue());
								boothResultVO.setPercentage(new BigDecimal((votesEarned.doubleValue()*100)/validVotes.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								
								boothResultVO.setOppParty(oppPartyResultObjArr[6].toString());
								boothResultVO.setOppPartyId(new Long(oppPartyResultObjArr[5].toString()));
								boothResultVO.setOppPartyPercentage(new BigDecimal((oppPartyVotesEarned.doubleValue()*100)/oppPartyValidVotes.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								boothResultVO.setOppPartyVotesEarned(oppPartyVotesEarned.intValue());
								allBoothsResults.add(boothResultVO);
							}							
						}
					log.debug("allBoothsResults size in  getAllBoothsResultsInAConstituency method in BiElection Service" + allBoothsResults.size());
					allBoothsResultsForAPartyInAMandal.setBoothResults(allBoothsResults);
			}
		
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
			allBoothsResultsForAPartyInAMandal.setExceptionEncountered(ex);
			allBoothsResultsForAPartyInAMandal.setResultCode(ResultCodeMapper.FAILURE);
			
		}
		return allBoothsResultsForAPartyInAMandal;
	}
	
	
	/*
	 * Method To Obtain Bye-Election 2010 Non-Participating Parties Details In 2009 General Election
	 * @INPUT election results list
	 * @OUTPUT non participating parties results list as PartyInfoVO
	 */
	public List<PartyResultsVO> getNonParticipatingPartiesResults(List<ElectionDataVO> urbanRuralConstiResults,String conType){
		
		log.debug(" Entered Into getNonParticipatingPartiesResults Method ...");
		
		List<PartyResultsVO> partyResList = null;
		ResultStatus resultStatus = new ResultStatus();
		
		try{
			//check for election results list
			if(urbanRuralConstiResults != null && urbanRuralConstiResults.size() > 0){
				partyResList = new ArrayList<PartyResultsVO>();
				
				List<PartyResultsVO> partyResInfo = getCrossVotingResults(urbanRuralConstiResults);
				if(partyResInfo != null && partyResInfo.size() > 0){
				for(PartyResultsVO res:partyResInfo){
					
					if("URBAN".equalsIgnoreCase(conType)){
						if(res.getPartyName().equals(IConstants.TRS) ||
								res.getPartyName().equals(IConstants.PRP)){
							partyResList.add(res);
						}
					}
					else{ 
						if(res.getPartyName().equals(IConstants.BJP) ||
							res.getPartyName().equals(IConstants.PRP)){
						partyResList.add(res);
						}
					}
				}
				getTotalResults(partyResList,0L);
				}
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug(" Exception Raised :" + ex);
						
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
	 return partyResList;
	}
	
	

	/*
	 * Method To Obtain Cross Voting Details for Parties In 2009 General Election
	 * @INPUT election results list
	 * @OUTPUT cross voting details for all parties as PartyResultsVO
	 */
	public List<PartyResultsVO> getCrossVotingResults(List<ElectionDataVO> urbanRuralConstiResults){
		
		log.debug(" Entered Into getNonParticipatingPartiesResults Method ...");
		List<PartyResultsVO> crossVotingDetails = null;
		try{
			
			if(urbanRuralConstiResults != null && urbanRuralConstiResults.size() > 0){
				
				//for assembly and parliament results
				Map<Long,PartyInfoVO> assemblyMap = new HashMap<Long,PartyInfoVO>();
				Map<Long,PartyInfoVO> parlimntMap = new HashMap<Long,PartyInfoVO>();
				
				crossVotingDetails = new ArrayList<PartyResultsVO>();
				
				for(ElectionDataVO resultLst:urbanRuralConstiResults){
					
					Boolean flag = false;
					if(resultLst.getElectionType().equals(IConstants.ASSEMBLY_ELECTION_TYPE) && 
							resultLst.getElectionYear().equals(IConstants.PRESENT_ELECTION_YEAR))
						flag = true;
					else if(resultLst.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE) && 
							resultLst.getElectionYear().equals(IConstants.PRESENT_ELECTION_YEAR))
						flag = true;  
				
					if(flag){
						for(ConstituencyMandalVO totRes:resultLst.getConstituencyMandalInfo()){
							if(totRes.getIsTotal()){
							  if(resultLst.getElectionType().equals(IConstants.ASSEMBLY_ELECTION_TYPE))
								  assemblyMap = getElectionResultsMap(totRes.getPartiesReslts());
							  else if(resultLst.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
								  parlimntMap = getElectionResultsMap(totRes.getPartiesReslts());
							}
						}
					}
					
				}
				
				//process the maps and set to VO
				if(!assemblyMap.isEmpty() && !parlimntMap.isEmpty()){
				Set<Long> keys = assemblyMap.keySet();
				for(Long key:keys){
					if(assemblyMap.containsKey(key) && parlimntMap.containsKey(key)){
					PartyResultsVO resultVO = getCrossVotingData(assemblyMap.get(key),parlimntMap.get(key));
					crossVotingDetails.add(resultVO);
					}
				}
				getTotalResults(crossVotingDetails,1L);
				}
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			log.debug(" Exception Raised :" + ex);
		}
		
	 return crossVotingDetails;
	}
	
	public void getTotalResults(List<PartyResultsVO> resultDetails,Long type){
		
		if(resultDetails != null && resultDetails.size() > 0){
			
			Long totAssemblyVE = new Long(0);
			Double totAssembltVP = new Double(0);
			Long totParliamentVE = new Long(0);
			Double totParliamentVP = new Double(0);
			
			for(PartyResultsVO res:resultDetails){
				totAssemblyVE+=res.getVotesEarned();
				totParliamentVE+=res.getBallotVotes();
				totAssembltVP+=new Double(res.getPercentage());
				totParliamentVP+=new Double(res.getBallotVotesPercentage());
			}
			
			PartyResultsVO totResult = new PartyResultsVO();
			totResult.setPartyName("Total");
			totResult.setVotesEarned(totAssemblyVE);
			totResult.setBallotVotes(totParliamentVE);
			totResult.setPercentage(new BigDecimal(totAssembltVP).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			totResult.setBallotVotesPercentage(new BigDecimal(totParliamentVP).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			if(type.equals(1L))
				totResult.setDiffPercent(new BigDecimal(totAssembltVP - totParliamentVP).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			resultDetails.add(totResult);
		}
	}
	
	public Map<Long,PartyInfoVO> getElectionResultsMap(List<PartyInfoVO> partiesResList){
		
		log.debug(" Inside getElectionResultsMap Method ..");
		Map<Long,PartyInfoVO> resultsMap = new HashMap<Long,PartyInfoVO>();
		
		if(partiesResList != null){
			for(PartyInfoVO res:partiesResList){
				if(res.getPartyShortName().equals(IConstants.TRS) ||
						res.getPartyShortName().equals(IConstants.INC) ||
						res.getPartyShortName().equals(IConstants.TDP) ||
						res.getPartyShortName().equals(IConstants.BJP) ||
						res.getPartyShortName().equals(IConstants.PRP)){
					resultsMap.put(res.getPartyId(), res);
				}
			}
		}
		
	 return resultsMap;
	}
	
	public PartyResultsVO getCrossVotingData(PartyInfoVO assemblyRes,PartyInfoVO parliamRes){
		
		log.debug(" Entered getCrossVotingData Method ..");
		PartyResultsVO partyResult = null;
		
		if(assemblyRes != null && parliamRes != null){
			partyResult = new PartyResultsVO();
			
			partyResult.setPartyId(assemblyRes.getPartyId());
			partyResult.setPartyName(assemblyRes.getPartyShortName());
			partyResult.setPercentage(assemblyRes.getPercentageOfVotes().toString());
			partyResult.setVotesEarned(assemblyRes.getPartyTotalVotes());
			partyResult.setBallotVotes(parliamRes.getPartyTotalVotes());
			partyResult.setBallotVotesPercentage(parliamRes.getPercentageOfVotes().toString());
			partyResult.setDiffPercent(new BigDecimal(assemblyRes.getPercentageOfVotes().doubleValue()-parliamRes.getPercentageOfVotes().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		
	 return partyResult;	
	}
	
	public BiElectionResultsMainVO getMandalWiseResultsForSelectedPartiesInConstituency(
			Long constituencyId) {
		BiElectionResultsMainVO biElectionResultsMainVO = new BiElectionResultsMainVO(); 
		List<BiElectionResultsVO> biElectionResultsVO = new ArrayList<BiElectionResultsVO>();
		
		Constituency constituency = constituencyDAO.get(constituencyId);
		
		//if("RURAL".equalsIgnoreCase(constituency.getAreaType()) || "URBAN".equalsIgnoreCase(constituency.getAreaType())){
		if(constituency.getStartDate() != null){
			
			BiElectionResultsVO resultForCroVotAndNonParty = new BiElectionResultsVO();
			biElectionResultsMainVO.setConstituencyType(constituency.getAreaType());
			List<ElectionDataVO> urbanRuralConstiResults = getAllPartiesPerformanceInConstituency(constituencyId);
			
			if(urbanRuralConstiResults != null && urbanRuralConstiResults.size() > 0){
			
			//for non-participating parties
			List<PartyResultsVO> nonParticipRes = getNonParticipatingPartiesResults(urbanRuralConstiResults,constituency.getAreaType());
			if(nonParticipRes != null && nonParticipRes.size() > 0)
				resultForCroVotAndNonParty.setNonPartiParties(nonParticipRes);
									
			//for cross voting details of all parties
			List<PartyResultsVO> crossVotingRes = getCrossVotingResults(urbanRuralConstiResults);
            if(crossVotingRes != null && crossVotingRes.size() > 0)
            	resultForCroVotAndNonParty.setCrossVotingResults(crossVotingRes);
               
            //set cross voting and non participating parties results VO in List
            biElectionResultsVO.add(resultForCroVotAndNonParty);
            //set List to main VO
            biElectionResultsMainVO.setBiElectionResultsMainVO(biElectionResultsVO);
			}
			
			biElectionResultsMainVO.setUrbanRuralConstiResults(urbanRuralConstiResults);
			ElectionWiseMandalPartyResultListVO allElectionsInfo = getAllElecReultsForConstituencyIncludingLocal(urbanRuralConstiResults, 
					constituency.getAreaType(), constituencyId);
			biElectionResultsMainVO.setAllPartiesElecInfo(allElectionsInfo);
			return biElectionResultsMainVO;
		}
		
		List<BiElectionResultsVO>  allPartiesResultsList = getMandalWiseResultsForAConstituency(constituencyId);
		
		for(BiElectionResultsVO allPartiesResultsObj :allPartiesResultsList)
		{
			List<ElectionResultsForMandalVO> results = allPartiesResultsObj.getBiElectionResultsVO();
			List<PartyElectionResultVO> postalBalletsList = new ArrayList<PartyElectionResultVO>();
			Map<String,ElectionResultsForMandalVO> crossVotingResults = new HashMap<String,ElectionResultsForMandalVO>();
			PartyResultsVO resultForLSP = new PartyResultsVO();
			
			for(ElectionResultsForMandalVO electionResultsForMandalVO:results)
			{
					List<MandalElectionResultVO> electionResultsInMandalList = electionResultsForMandalVO.getElectionResultsForMandal();
					Map<Long,PartyResultsVO> resultsSumMap = new HashMap<Long,PartyResultsVO>();
					Map<Long, PartyElectionResultVO> postalBalletsMap = new HashMap<Long, PartyElectionResultVO>();
					// to get postal ballet votes in a constituency for assembly 2009
					if(electionResultsForMandalVO.getElectionType().equals(IConstants.ASSEMBLY_ELECTION_TYPE) && electionResultsForMandalVO.getElectionYear().equals(IConstants.PRESENT_ELECTION_YEAR))
					{
						postalBalletsList = constituencyPageService.OtherVotesDataForAConstituency(constituencyId, electionResultsForMandalVO.getElectionYear(), electionResultsForMandalVO.getElectionType());
						log.debug("postalBalletsList size"+postalBalletsList.size());
						
						// processing ballot votes result processing
						for(PartyElectionResultVO partyElectionResultVO: postalBalletsList)
						{
							if(postalBalletsMap.isEmpty() || !postalBalletsMap.containsKey(partyElectionResultVO.getPartyId()))
							{
								postalBalletsMap.put(partyElectionResultVO.getPartyId(), partyElectionResultVO);
							}
								
						}
					}
					
										
					for(MandalElectionResultVO mandalElectionResultVOObj:electionResultsInMandalList)
					{
						List<PartyElectionResultsInConstituencyVO> partyElectionResultsInConstituencyList = mandalElectionResultVOObj.getPartyElecResultsInConstituency();					
												
						for(PartyElectionResultsInConstituencyVO partyElectionResultsInConstituencyObj:partyElectionResultsInConstituencyList)
							{
							   	List<PartyResultsVO> requiredPartiesResults = new ArrayList<PartyResultsVO>();
								Long validVotes = new Long(0);
								Long votesEarned = new Long(0) ; 
								List<PartyResultsVO> partyResults = partyElectionResultsInConstituencyObj.getPartyElecResults();
								PartyResultsVO partyResultsVOObj = new PartyResultsVO();
																
								for(PartyResultsVO partyResultsVO: partyResults)
								{
									if(partyResultsVO.getPartyName().equals(IConstants.TDP) || partyResultsVO.getPartyName().equals(IConstants.BJP) || partyResultsVO.getPartyName().equals(IConstants.INC) || partyResultsVO.getPartyName().equals(IConstants.TRS) 
											|| partyResultsVO.getPartyName().equals(IConstants.PRP))
									{
										requiredPartiesResults.add(partyResultsVO);
										
										//to calculate sum of results in an election
										if(resultsSumMap.isEmpty() || !resultsSumMap.containsKey(partyResultsVO.getPartyId())){
											
											if(partyResultsVO != null){
											PartyResultsVO resultVO = getCopiedVO(partyResultsVO);
											resultsSumMap.put(partyResultsVO.getPartyId(), resultVO);
											}
										}
										else if(resultsSumMap.containsKey(partyResultsVO.getPartyId())){
											PartyResultsVO partyRes = resultsSumMap.get(partyResultsVO.getPartyId());
											
											if(partyRes != null && partyResultsVO != null){
											Boolean resultInMap = checkForPartyResultValidation(partyRes);
											Boolean prtyRes = checkForPartyResultValidation(partyResultsVO);
											Long ve = new Long(0);
											Double votesPrcn = new Double(0);
											if(resultInMap && prtyRes){				
											   ve = partyRes.getVotesEarned() + partyResultsVO.getVotesEarned();
											   votesPrcn = new Double(partyRes.getPercentage()) + new Double(partyResultsVO.getPercentage());
											}
											else if(resultInMap){
											   ve = partyRes.getVotesEarned();
											   votesPrcn = new Double(partyRes.getPercentage());
											}
											else if(prtyRes){
											   ve = partyResultsVO.getVotesEarned();
											   votesPrcn = new Double(partyResultsVO.getPercentage());
											}
											
											if(ve != null && !ve.equals(new Long(0)) && votesPrcn != null && !votesPrcn.equals(new Double(0)))
											{	
											Double percnt = new BigDecimal(votesPrcn).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
											partyRes.setVotesEarned(ve);
											partyRes.setPercentage(percnt.toString());		
											
											resultsSumMap.put(partyResultsVO.getPartyId(), partyRes);
											}
											}
										}
									} else 
									{
										if(partyResultsVO.getValidVotes() != null && partyResultsVO.getVotesEarned() != null){
										validVotes = partyResultsVO.getValidVotes();
										votesEarned += partyResultsVO.getVotesEarned();		
										}
										
										//for LSP result
										if(partyResultsVO.getPartyName().equalsIgnoreCase("LSP"))
											resultForLSP = partyResultsVO;
									}
									
									
								}
								partyResultsVOObj.setPartyId(new Long(0));
								partyResultsVOObj.setPartyName(IConstants.OTHERS);
								partyResultsVOObj.setValidVotes(validVotes);
								partyResultsVOObj.setVotesEarned(votesEarned);
								
								if(votesEarned != null && votesEarned > new Long(0) && validVotes > new Long(0))
								    partyResultsVOObj.setPercentage(new BigDecimal(votesEarned.doubleValue()/validVotes.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								else
									partyResultsVOObj.setPercentage("--");
									
								if(!resultsSumMap.containsKey(new Long(0))){
									PartyResultsVO resultVO = getCopiedVO(partyResultsVOObj);
									resultsSumMap.put(new Long(0), resultVO);
									
								}
								else if(resultsSumMap.containsKey(new Long(0))){
									PartyResultsVO partyRes = resultsSumMap.get(new Long(0));
									if(partyRes != null && partyResultsVOObj != null){
										Boolean resultInMap = checkForPartyResultValidation(partyRes);
										Boolean prtyRes = checkForPartyResultValidation(partyResultsVOObj);
										Long ve = new Long(0);
										Double votesPrcn = new Double(0);	
									
										if(resultInMap && prtyRes){				
											   ve = partyRes.getVotesEarned() + partyResultsVOObj.getVotesEarned();
											   votesPrcn = new Double(partyRes.getPercentage()) + new Double(partyResultsVOObj.getPercentage());
										}
										else if(resultInMap){
										   ve = partyRes.getVotesEarned();
										   votesPrcn = new Double(partyRes.getPercentage());
										}
										else if(prtyRes){
										   ve = partyResultsVOObj.getVotesEarned();
										   votesPrcn = new Double(partyResultsVOObj.getPercentage());
										}
																											
									if(ve != null && !ve.equals(new Long(0)) && votesPrcn != null && !votesPrcn.equals(new Double(0))){
									Double percnt = new BigDecimal(votesPrcn).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									partyRes.setVotesEarned(ve);
									partyRes.setPercentage(percnt.toString());
									
									resultsSumMap.put(new Long(0), partyRes);
									}
									}
								}
								requiredPartiesResults.add(partyResultsVOObj);
								partyElectionResultsInConstituencyObj.setPartyElecResults(requiredPartiesResults);
							}
						
					}
					
					//processing votes sum map
					if(!resultsSumMap.isEmpty()){
						List<PartyResultsVO> partyResultsSum = new ArrayList<PartyResultsVO>();
						PartyResultsVO otherPartyRes = new PartyResultsVO();
						Set<Long> keys = resultsSumMap.keySet();
						Long totValidVotes = new Long(0);
						Set<Long> ballotMapKeys = new HashSet<Long>(0);
						if(!postalBalletsMap.isEmpty())
						   ballotMapKeys = postalBalletsMap.keySet();
						Long totBallotVotes = new Long(0);
						String ballotVotesPercent = "";
						for(Long partId:keys){
							
							if(partId.equals(new Long(0))){
								otherPartyRes = resultsSumMap.get(partId);
								totValidVotes+=otherPartyRes.getVotesEarned();
								continue;
							}
							PartyResultsVO resltVO = resultsSumMap.get(partId);
														
							//for ballot votes
							if(electionResultsForMandalVO.getElectionType().equals(IConstants.ASSEMBLY_ELECTION_TYPE) 
									&& electionResultsForMandalVO.getElectionYear().equals(IConstants.PRESENT_ELECTION_YEAR)){
							if(!postalBalletsMap.isEmpty() && postalBalletsMap.containsKey(partId)){
								PartyElectionResultVO balotVotes = postalBalletsMap.get(partId);
								if(balotVotes.getVotesEarned() != null)
								resltVO.setBallotVotes(balotVotes.getVotesEarned());
								if(balotVotes.getVotesPercentage() != null)
								resltVO.setBallotVotesPercentage(balotVotes.getVotesPercentage());
								
								resltVO.setVotesEarned(resltVO.getVotesEarned() + balotVotes.getVotesEarned());
								ballotMapKeys.remove(partId);
							}
							}
							totValidVotes+=resltVO.getVotesEarned();
							partyResultsSum.add(resltVO);
						}
						
						for(Long others:ballotMapKeys){
							PartyElectionResultVO balotVotes = postalBalletsMap.get(others);
							totBallotVotes+=balotVotes.getVotesEarned();
						}
						
						//for votes percent
						for(PartyResultsVO resultSumPer:partyResultsSum){
							Double votePrcnt = new BigDecimal(resultSumPer.getVotesEarned().doubleValue()/totValidVotes.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							resultSumPer.setPercentage(votePrcnt.toString());
							
							if(resultSumPer.getPartyId().equals(new Long(0))){
								resultSumPer.setBallotVotes(totBallotVotes);
								resultSumPer.setBallotVotesPercentage("--");
							}
						}
						
						Collections.sort(partyResultsSum, new PartyResultsVOComparator());
						
						Double votePrcnt = new BigDecimal(otherPartyRes.getVotesEarned().doubleValue()/totValidVotes*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						otherPartyRes.setPercentage(votePrcnt.toString());
						partyResultsSum.add(partyResultsSum.size(), otherPartyRes);
						
						electionResultsForMandalVO.setPartyResultsSum(partyResultsSum);
						
						//for cross voting and non participated party results map
						if(electionResultsForMandalVO.getElectionYear().equalsIgnoreCase(IConstants.PRESENT_ELECTION_YEAR) && electionResultsForMandalVO.getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || 
								electionResultsForMandalVO.getElectionYear().equalsIgnoreCase(IConstants.PRESENT_ELECTION_YEAR) && electionResultsForMandalVO.getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
							
							if(crossVotingResults.isEmpty() || !crossVotingResults.containsKey(electionResultsForMandalVO.getElectionType())){
								crossVotingResults.put(electionResultsForMandalVO.getElectionType(), electionResultsForMandalVO);
							}
							
						}
					}
					electionResultsForMandalVO.setPartysList(setStaticParties(electionResultsForMandalVO.getPartysList()));
				}
			//process the map set to VO ,for cross voting data
			if(!crossVotingResults.isEmpty()){
			List<PartyResultsVO> crossVotingResult = getCrossVotingResults(crossVotingResults,"CROSS_VOTING");
			if(crossVotingResult != null && crossVotingResult.size() > 0)
				allPartiesResultsObj.setCrossVotingResults(crossVotingResult);
			}
			
			//for non participated parties
			List<PartyResultsVO> nonPartiResult = getCrossVotingResults(crossVotingResults,"NON_PARTICIPATED");
			if(nonPartiResult != null && nonPartiResult.size() > 0){
				allPartiesResultsObj.setNonPartiParties(nonPartiResult);
			}
		}
		biElectionResultsMainVO.setBiElectionResultsMainVO(allPartiesResultsList);
		return biElectionResultsMainVO;
	}
	
  	private ElectionWiseMandalPartyResultListVO getAllElecReultsForConstituencyIncludingLocal(
			List<ElectionDataVO> urbanRuralConstiResults, String areaType, Long constituencyId) {
  		ElectionWiseMandalPartyResultListVO elections = new ElectionWiseMandalPartyResultListVO();
  		Map<PartyResultVO, List<ElectionResultVO>> partiesByElectionsMap = new HashMap<PartyResultVO, List<ElectionResultVO>>();
  		List<ElectionResultVO> partyElecs = null;
  		ElectionResultVO partyElecVO = null;
  		List<PartyResultVO> parties = new ArrayList<PartyResultVO>();
  		PartyResultVO party = null;
  		Set<String> electionsHeading = new HashSet<String>();
  		List<SelectOptionVO> electionsSelect = new ArrayList<SelectOptionVO>();
  		for(ElectionDataVO elecInfo:urbanRuralConstiResults)
  			for(ConstituencyMandalVO constiInfo:elecInfo.getConstituencyMandalInfo())
  				if(constiInfo.getIsTotal() == true)
  					for(PartyInfoVO partyInfo:constiInfo.getPartiesReslts()){
						party = new PartyResultVO();
  						party.setPartyId(partyInfo.getPartyId());
  						party.setPartyName(partyInfo.getPartyShortName());
  						partyElecs = partiesByElectionsMap.get(party);
  						if(partyElecs == null)
  							partyElecs = new ArrayList<ElectionResultVO>();
  						partyElecVO = new ElectionResultVO();
  						partyElecVO.setElectionType(elecInfo.getElectionType());
  						partyElecVO.setElectionYear(elecInfo.getElectionYear());
  						partyElecVO.setElectionYearAndType(elecInfo.getElectionYear()+" "+elecInfo.getElectionType());
  						partyElecVO.setPercentage(partyInfo.getPercentageOfVotes().toString());
  						partyElecs.add(partyElecVO);
  						electionsHeading.add(elecInfo.getElectionYear()+" "+elecInfo.getElectionType());
  						partiesByElectionsMap.put(party, partyElecs);
  					}
  		
  		String tehsils = staticDataService.getAllLatestMandalsForAConstituency(constituencyId).toString().substring(1);
  		List validVotesRawData = null;
  		List partyResultsRawData = null;
  		if("RURAL".equalsIgnoreCase(areaType)){
  			validVotesRawData = constituencyElectionDAO.getValidVotesForMptcZptcElectionsInMandals(tehsils);
  			partyResultsRawData = nominationDAO.getMptcZptcPartiesResultsInMandals(tehsils);
  		}
  		else{
  			validVotesRawData = constituencyElectionDAO.getValidVotesForMunicipalitiesAndCorporationsInMandals(tehsils);
  			partyResultsRawData = nominationDAO.getMunicipalitiesAndCorporationsResultsInMandals(tehsils);
  		}
  		
  		getLocalElectionResultsMapForConstituency(validVotesRawData, partyResultsRawData, partiesByElectionsMap, electionsHeading);
 
  		Set<String> electionsForParty = null;
  		List<Float> partyPecentages = null;
  		List result = null;
  		List alliance = null;
  		for(Map.Entry<PartyResultVO, List<ElectionResultVO>> entry:partiesByElectionsMap.entrySet()){
  			party = entry.getKey();
  			party.setElectionWiseResults(entry.getValue());
  			electionsForParty = new HashSet<String>();
  			partyPecentages = new ArrayList<Float>();
  			for(ElectionResultVO ele:entry.getValue()){
  				electionsForParty.add(ele.getElectionYearAndType());
  				
  				if(ele.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
  					result = electionDAO.findElectionIdByParliamentElectionTypeAndYear(ele.getElectionType(), ele.getElectionYear());
		        else
  				  result = electionDAO.findElectionIdByElectionTypeAndYear(ele.getElectionType(),ele.getElectionYear(),1l);					
				if(result.size() > 0)
					alliance = allianceGroupDAO.findAlliancePartiesByElectionAndParty(Long.parseLong(result.get(0).toString()),party.getPartyId());
				ele.setHasAlliance((alliance.size()>0 && result.size() > 0)?"true":"false");
				partyPecentages.add(new Float(ele.getPercentage()));
  			}
  			Collections.sort(partyPecentages);
			party.setRange(partyPecentages.get(0)+"-"+partyPecentages.get(partyPecentages.size()-1));
  			party.setElections(electionsForParty);
  			parties.add(party);
  		}
  		
  		//Heading Data Collection
		for(String elecType:electionsHeading)
			electionsSelect.add(new SelectOptionVO(0l, elecType));
		
		Collections.sort(electionsSelect, new SelectOptionVOComparator());//Heading Data Sorting
  		
		for(PartyResultVO party1:parties){
			partyElecs = new ArrayList<ElectionResultVO>();
			for(SelectOptionVO elec1:electionsSelect){
				if(!party1.getElections().contains(elec1.getName())){
					partyElecVO = new ElectionResultVO();
					partyElecVO.setElectionYearAndType(elec1.getName());
					partyElecVO.setPercentage("-1");
					partyElecs.add(partyElecVO);
				}
			}
			if(partyElecs.size() > 0)
				party1.getElectionWiseResults().addAll(party1.getElectionWiseResults().size()-1, partyElecs);
			partyElecs = party1.getElectionWiseResults();
			Collections.sort(partyElecs, new ElectionResultTypeComparator());//Data Sorting Same As Heading Order
			party1.setElectionWiseResults(partyElecs);
		}
  				
		elections.setElections(electionsSelect);
		elections.setAllPartiesAllElectionResults(parties);
		return elections;
	}

  	//Muncipalities And MPTC ZPTC
	private void getLocalElectionResultsMapForConstituency(List validVotesRawData,
			List partyResultsRawData,
			Map<PartyResultVO, List<ElectionResultVO>> partiesByElectionsMap, Set<String> electionsHeading) {
		Map<Long, Long> electionAndValidVotesMap = new HashMap<Long, Long>();
		Set<String> staticPartiesSet = new HashSet<String>();
		staticPartiesSet.add("INC");staticPartiesSet.add("PRP");staticPartiesSet.add("TDP");
		staticPartiesSet.add("TRS");staticPartiesSet.add("BJP");
		for(Object[] values:(List<Object[]>)validVotesRawData)
				electionAndValidVotesMap.put(((Long)values[0]), ((Double)values[3]).longValue());
				
		List<PartyResultVO> partiesList = null;
		PartyResultVO partyResultVO = null;
		List<ElectionResultVO> elections = null;
		ElectionResultVO resultVO = null;
		Long validVotes = 0l;
		Long earnedVotes = 0l;
		Float othersPercent = 0.0f;
		Map<ElectionResultVO, List<PartyResultVO>> electionWithPartiesMap = new HashMap<ElectionResultVO, List<PartyResultVO>>(); 
		
		for(Object[] values:(List<Object[]>)partyResultsRawData){
			resultVO = new ElectionResultVO();
			resultVO.setElectionId((Long)values[0]);
			resultVO.setElectionType(values[1].toString());
			resultVO.setElectionYear(values[2].toString());
			resultVO.setElectionYearAndType(values[2].toString()+" "+values[1].toString());
			electionsHeading.add(values[2].toString()+" "+values[1].toString());
			partiesList = electionWithPartiesMap.get(resultVO);
			if(partiesList == null)
				partiesList = new ArrayList<PartyResultVO>();
			partyResultVO = new PartyResultVO();
			partyResultVO.setPartyId((Long)values[4]);
			partyResultVO.setPartyName(values[3].toString());
			earnedVotes = ((Double)values[5]).longValue();
			validVotes = electionAndValidVotesMap.get((Long)values[0]);
			partyResultVO.setVotesPercent(new BigDecimal(earnedVotes*100.0/validVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			partiesList.add(partyResultVO);
			electionWithPartiesMap.put(resultVO, partiesList);
		}
		ElectionResultVO resultVONew = null;
		for(Map.Entry<ElectionResultVO, List<PartyResultVO>> entry:electionWithPartiesMap.entrySet()){
			resultVO = entry.getKey();
			partiesList = entry.getValue();
			othersPercent = 0.0f;
			for(PartyResultVO party:partiesList){
				if(!staticPartiesSet.contains(party.getPartyName())){
					othersPercent += Float.parseFloat(party.getVotesPercent());
					continue;
				}
				resultVONew = new ElectionResultVO();
				resultVONew.setPercentage(party.getVotesPercent());
				resultVONew.setElectionType(resultVO.getElectionType());
				resultVONew.setElectionYear(resultVO.getElectionYear());
				resultVONew.setElectionYearAndType(resultVO.getElectionYearAndType());
				elections = partiesByElectionsMap.get(party);
				if(elections == null)
					elections = new ArrayList<ElectionResultVO>();
				elections.add(resultVONew);
				partiesByElectionsMap.put(party, elections);
			}
			partyResultVO = new PartyResultVO();
			partyResultVO.setPartyName(IConstants.OTHERS);
			partyResultVO.setPartyId(0l);
			resultVO.setPercentage(othersPercent.toString());
			elections = partiesByElectionsMap.get(partyResultVO);
			if(elections == null)
				elections = new ArrayList<ElectionResultVO>();
			elections.add(resultVO);
			partiesByElectionsMap.put(partyResultVO, elections);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<ElectionDataVO> getAllPartiesPerformanceInConstituency(
			Long constituencyId) {
		Set<String> staticPartiesSet = new HashSet<String>();
		staticPartiesSet.add("INC");staticPartiesSet.add("PRP");staticPartiesSet.add("TDP");staticPartiesSet.add("TRS");
		staticPartiesSet.add("BJP");
		List<ElectionDataVO> elections = null;
		Map<String, PartyInfoVO> partyNameResultMap2009 = null;
		Map<String, PartyInfoVO> partyNameResultMap2010 = null;
		Boolean isResultsExists = true;
		Long totalVotes = 0l;
		List acResults2010 = nominationDAO.getCandidatesInfoForTheGivenConstituency(""+constituencyId, "2010", IConstants.ASSEMBLY_ELECTION_TYPE);
		List boothACResults2010 = candidateBoothResultDAO.findElectionResultsForAConstituencyByElectionYear(constituencyId, "2010");
  		List acResults2009 = nominationDAO.getCandidatesInfoForTheGivenConstituency(""+constituencyId, IConstants.PRESENT_ELECTION_YEAR, IConstants.ASSEMBLY_ELECTION_TYPE);
		List boothACResults2009 = candidateBoothResultDAO.findElectionResultsForAConstituencyByElectionYear(constituencyId, IConstants.PRESENT_ELECTION_YEAR);
		List pcResults2009 = candidateBoothResultDAO.findAssemblyWiseParliamentResultsForPartiesInAssembly(constituencyId, IConstants.PRESENT_ELECTION_YEAR);
		List acpcResults2004 = candidateBoothResultDAO.findElectionResultsForAMappedConstituencyByElectionType(constituencyId, IConstants.PREVIOUS_ELECTION_YEAR);
		
		partyNameResultMap2010 = getPartiesResultsInAMap(acResults2010, staticPartiesSet);
		partyNameResultMap2009 = getPartiesResultsInAMap(acResults2009, staticPartiesSet);
		elections = getElectionDataVOFromBoothData(boothACResults2010, staticPartiesSet);
		
		for(Map.Entry<String, PartyInfoVO> entry:partyNameResultMap2010.entrySet())
			totalVotes += entry.getValue().getPartyTotalVotes();
			
		if(totalVotes.intValue() == 0)
			isResultsExists = false;
		
		if(elections.size() == 0 && isResultsExists){
			ElectionDataVO electionDataVO = new ElectionDataVO();
			electionDataVO.setElectionType(IConstants.ASSEMBLY_ELECTION_TYPE);
			electionDataVO.setElectionYear("2010");
			elections.add(electionDataVO);
		}
		
		elections.addAll(elections.size(), getElectionDataVOFromBoothData(boothACResults2009, staticPartiesSet));
		elections.addAll(elections.size(), getElectionDataVOFromBoothData(pcResults2009, staticPartiesSet));
		elections.addAll(elections.size(), getElectionDataVOFromBoothData(acpcResults2004, staticPartiesSet));
		findTotalsInEachElection(elections, partyNameResultMap2009, partyNameResultMap2010);
		
		return elections;
	}
  	
	private void findTotalsInEachElection(List<ElectionDataVO> elections, 
			Map<String, PartyInfoVO> partyNameResultMap2009, Map<String, PartyInfoVO> partyNameResultMap2010) {
		Long votesEarned = 0l;
		Long polledVotes = 0l;
		ConstituencyMandalVO totalsRecord = null;
		List<PartyInfoVO> parties = null;
		PartyInfoVO infoVO = null;
		Long partyId = null;
		for(ElectionDataVO election:elections){
			parties = new ArrayList<PartyInfoVO>();
			
			if(election.getPartiesHeading() != null && election.getPartiesHeading().size() > 0)
				for(int i=0; i<election.getPartiesHeading().size(); i++){
					votesEarned = 0l;
					polledVotes = 0l;
					for(int j=0; j<election.getConstituencyMandalInfo().size(); j++){
						votesEarned += election.getConstituencyMandalInfo().get(j).getPartiesReslts().get(i).getPartyTotalVotes();
						polledVotes += election.getConstituencyMandalInfo().get(j).getPartiesReslts().get(i).getValidVotes();
						if(election.getConstituencyMandalInfo().get(j).getPartiesReslts().get(i).getPartyId() != null)
							partyId = election.getConstituencyMandalInfo().get(j).getPartiesReslts().get(i).getPartyId();
					}
					infoVO = new PartyInfoVO();
					infoVO.setPartyId(partyId);
					infoVO.setPartyShortName(election.getPartiesHeading().get(i).getName());
					infoVO.setPartyTotalVotes(votesEarned);
					infoVO.setPercentageOfVotes(new BigDecimal(votesEarned*100.0/polledVotes).setScale(2, BigDecimal.ROUND_HALF_UP));
					parties.add(infoVO);
				}
			
			totalsRecord = new ConstituencyMandalVO();
			
			if(election.getElectionYear().equalsIgnoreCase(IConstants.PRESENT_ELECTION_YEAR) && 
					election.getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				List<PartyInfoVO> postalVotes = new ArrayList<PartyInfoVO>();
				Long allPostalVotes = 0l;
				for(PartyInfoVO party:parties){
					infoVO = new PartyInfoVO();
					votesEarned = partyNameResultMap2009.get(party.getPartyShortName()).getPartyTotalVotes() - party.getPartyTotalVotes();
					allPostalVotes += votesEarned;
					infoVO.setPartyShortName(party.getPartyShortName());
					infoVO.setPartyTotalVotes(votesEarned);
					postalVotes.add(infoVO);
				}
				
				for(PartyInfoVO party:postalVotes)
					party.setPercentageOfVotes(new BigDecimal(party.getPartyTotalVotes()*100.0/allPostalVotes).setScale(2, BigDecimal.ROUND_HALF_UP));
				
				totalsRecord.setIsPostalVotes(true);
				totalsRecord.setPartiesReslts(postalVotes);
				election.getConstituencyMandalInfo().add(totalsRecord);
				
				postalVotes = new ArrayList<PartyInfoVO>();
				for(Map.Entry<String, PartyInfoVO> entry:partyNameResultMap2009.entrySet())
					postalVotes.add(entry.getValue());
				
				Collections.sort(postalVotes, new PartyInfoVOComparator());
				
				//Moving Others to last
				int i=0;
				for(PartyInfoVO party:postalVotes){
					if(party.getPartyShortName().equalsIgnoreCase(IConstants.OTHERS)){
						infoVO = postalVotes.remove(i);
						postalVotes.add(infoVO);
						break;
					}
					i++;
				}
				
				totalsRecord = new ConstituencyMandalVO();
				totalsRecord.setIsTotal(true);
				totalsRecord.setPartiesReslts(postalVotes);
				election.getConstituencyMandalInfo().add(totalsRecord);
				continue;
			}else if(election.getElectionYear().equalsIgnoreCase("2010") && 
					election.getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				List<PartyInfoVO> postalVotes = new ArrayList<PartyInfoVO>();
				Long allPostalVotes = 0l;
				
				for(PartyInfoVO party:parties){
					infoVO = new PartyInfoVO();
					votesEarned = partyNameResultMap2010.get(party.getPartyShortName()).getPartyTotalVotes() - party.getPartyTotalVotes();
					allPostalVotes += votesEarned;
					infoVO.setPartyShortName(party.getPartyShortName());
					infoVO.setPartyTotalVotes(votesEarned);
					postalVotes.add(infoVO);
				}
				
				for(PartyInfoVO party:postalVotes)
					party.setPercentageOfVotes(new BigDecimal(party.getPartyTotalVotes()*100.0/allPostalVotes).setScale(2, BigDecimal.ROUND_HALF_UP));
				
				if(postalVotes.size() > 0){
					totalsRecord.setIsPostalVotes(true);
					totalsRecord.setPartiesReslts(postalVotes);
					election.getConstituencyMandalInfo().add(totalsRecord);	
				}
				
				postalVotes = new ArrayList<PartyInfoVO>();
				for(Map.Entry<String, PartyInfoVO> entry:partyNameResultMap2010.entrySet())
					postalVotes.add(entry.getValue());
				
				Collections.sort(postalVotes, new PartyInfoVOComparator());
				
				//Moving Others to last
				int i=0;
				for(PartyInfoVO party:postalVotes){
					if(party.getPartyShortName().equalsIgnoreCase(IConstants.OTHERS)){
						infoVO = postalVotes.remove(i);
						postalVotes.add(infoVO);
						break;
					}
					i++;
				}
				
				totalsRecord = new ConstituencyMandalVO();
				totalsRecord.setIsTotal(true);
				totalsRecord.setPartiesReslts(postalVotes);
				if(election.getConstituencyMandalInfo() != null)
					election.getConstituencyMandalInfo().add(totalsRecord);
				else{
					List<ConstituencyMandalVO> constituencyMandalInfo = new ArrayList<ConstituencyMandalVO>();
					constituencyMandalInfo.add(totalsRecord);
					election.setConstituencyMandalInfo(constituencyMandalInfo);
				}
					
				continue;
			}
			
			totalsRecord.setIsTotal(true);
			totalsRecord.setPartiesReslts(parties);
			election.getConstituencyMandalInfo().add(totalsRecord);
			
		}
		
	}

	private List<ElectionDataVO> getElectionDataVOFromBoothData(
			List boothwiseResults, Set<String> staticPartiesSet) {
		Map<ElectionDataVO, List<Object[]>> electionDataMap = getAllElectionsRawDataAsMap(boothwiseResults);
		List<ElectionDataVO> finalResult = new ArrayList<ElectionDataVO>();
		
		for(Map.Entry<ElectionDataVO, List<Object[]>> entry:electionDataMap.entrySet())
			finalResult.add(getConstiMandalResults(entry.getValue(), staticPartiesSet, entry.getKey()));

		return finalResult;
	}

	private ElectionDataVO getConstiMandalResults(List<Object[]> value, Set<String> staticPartiesSet, ElectionDataVO electionDataVO) {
		Map<ConstituencyMandalVO, List<PartyInfoVO>> constiMandalResultsMap = new HashMap<ConstituencyMandalVO, List<PartyInfoVO>>();
		List<ConstituencyMandalVO> constiMandals = new ArrayList<ConstituencyMandalVO>();
		ConstituencyMandalVO constituencyMandalVO = null;
		List<PartyInfoVO> allParties = null;
		List<PartyInfoVO> fixedParties = null;
		PartyInfoVO partyInfoVO = null;
		Set<String> participatedParties = new HashSet<String>();
		Long votesEarned = 0l;
		Long validVotes = 0l;
		for(Object[] rawData:value){
			constituencyMandalVO = new ConstituencyMandalVO();
			constituencyMandalVO.setValidVotesInElec((Long)rawData[9]);// Valid Votes In Election
			constituencyMandalVO.setConstituencyId((Long)rawData[3]);
			constituencyMandalVO.setTehsilId((Long)rawData[5]);
			constituencyMandalVO.setConstituencyName(rawData[4].toString());
			constituencyMandalVO.setTehsilName(rawData[6].toString());
			allParties = constiMandalResultsMap.get(constituencyMandalVO);
			if(allParties == null)
				allParties = new ArrayList<PartyInfoVO>();
			partyInfoVO = new PartyInfoVO();
			partyInfoVO.setPartyId((Long)rawData[7]);
			partyInfoVO.setPartyShortName(rawData[8].toString());
			votesEarned = (Long)rawData[10];
			validVotes = (Long)rawData[9];
			partyInfoVO.setPartyTotalVotes(votesEarned);
			partyInfoVO.setValidVotes(validVotes);
			if(votesEarned > 0 && validVotes > 0 && votesEarned < validVotes)
				partyInfoVO.setPercentageOfVotes(new BigDecimal(votesEarned*100.0/validVotes).setScale(2, BigDecimal.ROUND_HALF_UP));
			else
				partyInfoVO.setPercentageOfVotes(new BigDecimal(0));
			allParties.add(partyInfoVO);
			constiMandalResultsMap.put(constituencyMandalVO, allParties);
		}
		
		Long othersVotes = 0l;
		String othersPercenatge = "0.0";
		Float percentage = 0f;
		Set<String> partiesInConstiMandal = null;
		for(Map.Entry<ConstituencyMandalVO, List<PartyInfoVO>> entry:constiMandalResultsMap.entrySet()){
			constituencyMandalVO = entry.getKey();
			allParties = entry.getValue();
			fixedParties = new ArrayList<PartyInfoVO>();
			othersVotes = 0l;
			validVotes = 0l;
			othersPercenatge = "0.0";
			percentage = 0f;
			partiesInConstiMandal = new HashSet<String>();
			for(PartyInfoVO infoVO:allParties){
				if(!staticPartiesSet.contains(infoVO.getPartyShortName())){
					othersVotes += infoVO.getPartyTotalVotes();
					validVotes = infoVO.getValidVotes();
					percentage = Float.parseFloat(othersPercenatge) + Float.parseFloat(infoVO.getPercentageOfVotes().toString());
					othersPercenatge = percentage.toString();
					continue;
				}
				
				fixedParties.add(infoVO);
				participatedParties.add(infoVO.getPartyShortName());
				partiesInConstiMandal.add(infoVO.getPartyShortName());
			}
			
			partyInfoVO = new PartyInfoVO();
			partyInfoVO.setPartyId(0l);
			partyInfoVO.setPartyShortName(IConstants.OTHERS);
			partyInfoVO.setPartyTotalVotes(othersVotes);
			partyInfoVO.setValidVotes(validVotes);
			partyInfoVO.setPercentageOfVotes(new BigDecimal(othersPercenatge).setScale(2, BigDecimal.ROUND_HALF_UP));
			fixedParties.add(partyInfoVO);
			constituencyMandalVO.setPartiesParticipated(partiesInConstiMandal);
			constituencyMandalVO.setPartiesReslts(fixedParties);
			constiMandals.add(constituencyMandalVO);
		}
		
		//Not Participated Parties
		for(ConstituencyMandalVO constiMandal:constiMandals)
			for(String party:participatedParties){
				if(!constiMandal.getPartiesParticipated().contains(party)){
					partyInfoVO = new PartyInfoVO();
					partyInfoVO.setPartyShortName(party);
					partyInfoVO.setValidVotes(constiMandal.getValidVotesInElec());
					partyInfoVO.setPercentageOfVotes(new BigDecimal("-1"));
					constiMandal.getPartiesReslts().add(partyInfoVO);
				}
				Collections.sort(constiMandal.getPartiesReslts(), new PartyInfoVOComparator());
			}
		
		for(ConstituencyMandalVO constiMandal:constiMandals){
			int i = 0;
			for(PartyInfoVO party:constiMandal.getPartiesReslts()){
				if(party.getPartyShortName().equalsIgnoreCase(IConstants.OTHERS)){
					partyInfoVO = constiMandal.getPartiesReslts().remove(i);
					constiMandal.getPartiesReslts().add(partyInfoVO);
					break;
				}
				i++;
			}
				
		}
				
		//Heading Generation
		List<SelectOptionVO> partiesInHeading = new ArrayList<SelectOptionVO>(); 
		for(String party:participatedParties)
			partiesInHeading.add(new SelectOptionVO(0l, party));
		Collections.sort(partiesInHeading, new SelectOptionVOComparator());
		
		partiesInHeading.add(new SelectOptionVO(0l, IConstants.OTHERS));
		
		electionDataVO.setConstituencyMandalInfo(constiMandals);
		electionDataVO.setPartiesHeading(partiesInHeading);
		
		return electionDataVO;
	}

	private Map<ElectionDataVO, List<Object[]>> getAllElectionsRawDataAsMap(
			List boothwiseResults) {
		Map<ElectionDataVO, List<Object[]>> electionDataMap = new HashMap<ElectionDataVO, List<Object[]>>();
		ElectionDataVO electionDataVO = null;
		List<Object[]> rawData = null;

		for(Object[] values:(List<Object[]>)boothwiseResults){
			electionDataVO = new ElectionDataVO();
			electionDataVO.setElectionId((Long)values[0]);
			electionDataVO.setElectionType(values[1].toString());
			electionDataVO.setElectionYear(values[2].toString());
			rawData = electionDataMap.get(electionDataVO);
			if(rawData == null)
				rawData = new ArrayList<Object[]>();				
				
			rawData.add(values);
			electionDataMap.put(electionDataVO, rawData);
		}
		
		return electionDataMap;
	}

	private Map<String, PartyInfoVO> getPartiesResultsInAMap(List acResults2009, Set<String> staticPartiesSet) {
		
		Map<String, PartyInfoVO> partyNameResultMap = new HashMap<String, PartyInfoVO>();
		PartyInfoVO partyInfoVO = null;
		String partyName = "";
		Long othersVotes = 0l;
		String othersPercenatge = "0.0";
		Float percentage = 0f;
		for(Object[] values:(List<Object[]>)acResults2009){
			partyName = new String(values[8].toString());
			if(!staticPartiesSet.contains(partyName)){
				othersVotes += ((Double)values[2]).longValue();
				percentage = Float.parseFloat(othersPercenatge) + Float.parseFloat(values[3].toString());
				othersPercenatge = percentage.toString();
				continue;
			}

			partyInfoVO = new PartyInfoVO();
			partyInfoVO.setPartyId((Long)values[5]);
			partyInfoVO.setPartyShortName(partyName);
			partyInfoVO.setPartyTotalVotes(((Double)values[2]).longValue());
			partyInfoVO.setPercentageOfVotes(new BigDecimal(values[3].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
			partyNameResultMap.put(partyName, partyInfoVO);
			
		}
		
		partyInfoVO = new PartyInfoVO();
		partyInfoVO.setPartyId(0l);
		partyInfoVO.setPartyShortName(IConstants.OTHERS);
		partyInfoVO.setPartyTotalVotes(othersVotes);
		partyInfoVO.setPercentageOfVotes(new BigDecimal(othersPercenatge).setScale(2, BigDecimal.ROUND_HALF_UP));
		partyNameResultMap.put(IConstants.OTHERS, partyInfoVO);
		return partyNameResultMap;
	}
	
	//To Get The Results In User Required Format
	public ElectionWiseMandalPartyResultListVO getResultsOfRuralUrbanAreaBeasedOnSelection(Long constituencyId, 
			Set<String> parties, Set<String> elecTypeOrYear, Boolean isElecTypeOnly, Boolean isAlliance){
		ElectionWiseMandalPartyResultListVO allElecAllParties = getMandalWiseResultsForSelectedPartiesInConstituency(constituencyId).
																								getAllPartiesElecInfo();
		List<PartyResultVO> allPartiesInfo = allElecAllParties.getAllPartiesAllElectionResults();
		Set<String> electionsHeading = new LinkedHashSet<String>();
		List<SelectOptionVO> electionsHeadingList = new ArrayList<SelectOptionVO>();
		
		List<PartyResultVO> selectedPartiesInfo = new ArrayList<PartyResultVO>();
		if(parties != null && parties.size() > 0){
			for(PartyResultVO partyInfo:allPartiesInfo)
				if(parties.contains(partyInfo.getPartyName()))
					selectedPartiesInfo.add(partyInfo);
		}
		
		if(selectedPartiesInfo.size() > 0)
			allPartiesInfo = selectedPartiesInfo;
		
		if(elecTypeOrYear != null && elecTypeOrYear.size() > 0)
			getResultsForSelectedElections(allPartiesInfo, elecTypeOrYear, electionsHeading, isElecTypeOnly);
		
		if(isAlliance && (constituencyId.intValue() != 341))
			getAlliancePartiesGroupedResult(allPartiesInfo);
				
		//Calculating Range
		List<Float> partyPecentages = null;
		for(PartyResultVO party:allPartiesInfo){
			partyPecentages = new ArrayList<Float>();
			for(ElectionResultVO elec:party.getElectionWiseResults()){
				if(elec.getPercentage().equals("-1"))
					continue;
				partyPecentages.add(new Float(elec.getPercentage()));	
			}
			
			Collections.sort(partyPecentages);
			if(partyPecentages.size() > 0)
				party.setRange(partyPecentages.get(0)+"-"+partyPecentages.get(partyPecentages.size()-1));
			else
				party.setRange("--");
		}

		allElecAllParties.setAllPartiesAllElectionResults(allPartiesInfo);

		if(electionsHeading.size() == 0)
			for(SelectOptionVO obj:allElecAllParties.getElections())
				electionsHeading.add(obj.getName());		
		
		for(String elec:electionsHeading){
			String [] yearAndType = StringUtils.split(elec, " ");
			if(yearAndType[1].equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
				electionsHeadingList.add(new SelectOptionVO(0l, yearAndType[0]+" "+"AC"));
			else
			if(yearAndType[1].equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
				electionsHeadingList.add(new SelectOptionVO(0l, yearAndType[0]+" "+"PC"));
			else
			if(yearAndType[1].equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))
				electionsHeadingList.add(new SelectOptionVO(0l, yearAndType[0]+" "+"CORP"));
			else
				electionsHeadingList.add(new SelectOptionVO(0l, elec));
		}
			
		//Moving Assemblies Before Parliaments
		int index=0;
		for(PartyResultVO party:allPartiesInfo)
			for(int i=0; i<party.getElectionWiseResults().size(); i++)
				if((i+1) <= party.getElectionWiseResults().size()-1 && IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(party.getElectionWiseResults().get(i).getElectionType()) &&
						"2009".equalsIgnoreCase(party.getElectionWiseResults().get(i).getElectionYear()) && 
						IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(party.getElectionWiseResults().get(i+1).getElectionType()) &&
						"2009".equalsIgnoreCase(party.getElectionWiseResults().get(i+1).getElectionYear())){
					index = i;
					break;
				}
					
		
		if(index != 0){
			SelectOptionVO obj = electionsHeadingList.get(index);
			electionsHeadingList.set(index, electionsHeadingList.get(index+1));
			electionsHeadingList.set(index+1, obj);
			
			ElectionResultVO elecVO1 = null;
			for(PartyResultVO party:allPartiesInfo){
				elecVO1 = party.getElectionWiseResults().get(index);
				party.getElectionWiseResults().set(index, party.getElectionWiseResults().get(index+1));
				party.getElectionWiseResults().set(index+1, elecVO1);
			}
		}
		
		allElecAllParties.setElections(electionsHeadingList);
		
		return allElecAllParties;
	}

	private void getAlliancePartiesGroupedResult(List<PartyResultVO> allPartiesInfo){
		List<ByeElecGroupVO> allianceGroup1 = new ArrayList<ByeElecGroupVO>();
		List<ByeElecGroupVO> allianceGroup2 = new ArrayList<ByeElecGroupVO>();
		ByeElecGroupVO byeElecGroupVO = null;
		Float grp1Percent = 0.0f;
		Float grp2Percent = 0.0f;
		for(int j=0; j<allPartiesInfo.size(); j++){
			if(allPartiesInfo.get(j).getPartyName().equalsIgnoreCase(IConstants.INC) || 
					allPartiesInfo.get(j).getPartyName().equalsIgnoreCase(IConstants.TRS)){
				for(int i=0; i<allPartiesInfo.get(j).getElectionWiseResults().size(); i++){
					if(allPartiesInfo.get(j).getElectionWiseResults().get(i).getElectionYearAndType().
							equalsIgnoreCase(IConstants.PREVIOUS_ELECTION_YEAR+" "+IConstants.ASSEMBLY_ELECTION_TYPE)){
						byeElecGroupVO = new ByeElecGroupVO();
						byeElecGroupVO.setPartyName(allPartiesInfo.get(j).getPartyName());
						byeElecGroupVO.setPercenatge(allPartiesInfo.get(j).getElectionWiseResults().get(i).getPercentage());
						byeElecGroupVO.setIndexOfElection(i);
						byeElecGroupVO.setIndexOfParty(j);
						grp1Percent += new Float(allPartiesInfo.get(j).getElectionWiseResults().get(i).getPercentage());
						allianceGroup1.add(byeElecGroupVO);
					}
				}
			}else if(allPartiesInfo.get(j).getPartyName().equalsIgnoreCase(IConstants.TDP) || 
					allPartiesInfo.get(j).getPartyName().equalsIgnoreCase(IConstants.BJP)){
				for(int i=0; i<allPartiesInfo.get(j).getElectionWiseResults().size(); i++){
					if(allPartiesInfo.get(j).getElectionWiseResults().get(i).getElectionYearAndType().
							equalsIgnoreCase(IConstants.PREVIOUS_ELECTION_YEAR+" "+IConstants.ASSEMBLY_ELECTION_TYPE)){
						byeElecGroupVO = new ByeElecGroupVO();
						byeElecGroupVO.setPartyName(allPartiesInfo.get(j).getPartyName());
						byeElecGroupVO.setPercenatge(allPartiesInfo.get(j).getElectionWiseResults().get(i).getPercentage());
						byeElecGroupVO.setIndexOfElection(i);
						byeElecGroupVO.setIndexOfParty(j);
						grp2Percent += new Float(allPartiesInfo.get(j).getElectionWiseResults().get(i).getPercentage());
						allianceGroup2.add(byeElecGroupVO);
					}
				}
			}
		}
		
		Collections.sort(allianceGroup1, new ByeElecGroupVOComparator());
		Collections.sort(allianceGroup2, new ByeElecGroupVOComparator());
		
		int i=0;
		for(ByeElecGroupVO groupEle:allianceGroup1){
			if(i == 0){
				allPartiesInfo.get(groupEle.getIndexOfParty()).getElectionWiseResults().
				get(groupEle.getIndexOfElection()).setPercentage(grp1Percent.toString());
				allPartiesInfo.get(groupEle.getIndexOfParty()).getElectionWiseResults().
				get(groupEle.getIndexOfElection()).setAlliancRes(true);
			}
			else
				allPartiesInfo.get(groupEle.getIndexOfParty()).getElectionWiseResults().
				get(groupEle.getIndexOfElection()).setPercentage("-1");
			i++;
		}
		
		i=0;
		for(ByeElecGroupVO groupEle:allianceGroup2){
			if(i == 0){
				allPartiesInfo.get(groupEle.getIndexOfParty()).getElectionWiseResults().
				get(groupEle.getIndexOfElection()).setPercentage(grp2Percent.toString());
				allPartiesInfo.get(groupEle.getIndexOfParty()).getElectionWiseResults().
				get(groupEle.getIndexOfElection()).setAlliancRes(true);
			}
			else
				allPartiesInfo.get(groupEle.getIndexOfParty()).getElectionWiseResults().
				get(groupEle.getIndexOfElection()).setPercentage("-1");
			i++;
		}
			
		
	}
	
	private void getResultsForSelectedElections(
			List<PartyResultVO> selectedPartiesInfo,Set<String> elecTypeOrYear, 
			Set<String> electionsHeading, Boolean isElecTypeOnly) {
		List<ElectionResultVO> selectedElections = null;
		for(PartyResultVO partyInfo:selectedPartiesInfo){
			selectedElections = new ArrayList<ElectionResultVO>();
			for(ElectionResultVO election:partyInfo.getElectionWiseResults()){
				if(isElecTypeOnly){
					if(elecTypeOrYear.contains(election.getElectionYearAndType().substring(5))){
						selectedElections.add(election);
						electionsHeading.add(election.getElectionYearAndType());
					}
				}else{
					if(elecTypeOrYear.contains(election.getElectionYearAndType())){
						selectedElections.add(election);
						electionsHeading.add(election.getElectionYearAndType());
					}
				}
			}
			partyInfo.setElectionWiseResults(selectedElections);
		}
	}

	public List<PartyResultsVO> getCrossVotingResults(Map<String,ElectionResultsForMandalVO> crossVotingResults,String status){
		
		List<PartyResultsVO> partyResults = null;
		if(!crossVotingResults.isEmpty()){
			partyResults = new ArrayList<PartyResultsVO>();
			
			Long totAVE = new Long(0);
			Long totPVE = new Long(0);
			Double votAP = new Double(0);
			Double votPP = new Double(0);
			Double totDif = new Double(0);
			
			if(crossVotingResults.containsKey(IConstants.ASSEMBLY_ELECTION_TYPE) && crossVotingResults.containsKey(IConstants.PARLIAMENT_ELECTION_TYPE)){
				ElectionResultsForMandalVO assembly = crossVotingResults.get(IConstants.ASSEMBLY_ELECTION_TYPE);
				ElectionResultsForMandalVO parliamnt = crossVotingResults.get(IConstants.PARLIAMENT_ELECTION_TYPE);
				
				for(PartyResultsVO assmblRes:assembly.getPartyResultsSum()){
					
					if(status.equalsIgnoreCase("NON_PARTICIPATED")){
						if(assmblRes.getPartyName().equalsIgnoreCase(IConstants.BJP) || assmblRes.getPartyName().equalsIgnoreCase(IConstants.PRP))
							log.debug(" Non Participated Party :" + assmblRes.getPartyName());
						else
							continue;
					}
					
					for(PartyResultsVO parliamtRes:parliamnt.getPartyResultsSum()){
						
						//include alliance
						Map<Long,String> alliancePartiesMap = getAlliancPartiesList(IConstants.ASSEMBLY_ELECTION_TYPE,IConstants.PRESENT_ELECTION_YEAR,assmblRes.getPartyId());
									
						if(!assmblRes.getPartyId().equals(new Long(0))){
							if(assmblRes.getPartyId().equals(parliamtRes.getPartyId()) 
									|| alliancePartiesMap.containsKey(parliamtRes.getPartyId())){
							
							PartyResultsVO res = new PartyResultsVO();
							res.setPartyId(assmblRes.getPartyId());
							if(alliancePartiesMap.containsKey(parliamtRes.getPartyId()))
								res.setPartyName(assmblRes.getPartyName() + "*");
							else
							    res.setPartyName(assmblRes.getPartyName());
							res.setVotesEarned(assmblRes.getVotesEarned());
							res.setPercentage(assmblRes.getPercentage());
							res.setBallotVotes(parliamtRes.getVotesEarned());
							res.setBallotVotesPercentage(parliamtRes.getPercentage());
							
							//for total sum
							totAVE+=assmblRes.getVotesEarned();
							totPVE+=parliamtRes.getVotesEarned();
							votAP+=new Double(assmblRes.getPercentage());
							votPP+=new Double(parliamtRes.getPercentage());
							
							if(status.equalsIgnoreCase("CROSS_VOTING")){
							try{
							Double diff = new BigDecimal(new Double(assmblRes.getPercentage()) - new Double(parliamtRes.getPercentage())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							res.setDiffPercent(diff.toString());
							totDif+=diff;
							}
							catch(Exception ex){
								ex.printStackTrace();
							}
							}
							partyResults.add(res);
							break;
							}
						}
					}
				}
				
				//set total results sum
				if(totAVE > new Long(0) && votAP > new Double(0)){
					PartyResultsVO res = new PartyResultsVO();
					res.setPartyId(0l);
					res.setPartyName("Total");
					res.setVotesEarned(totAVE);
					Double AsVP = new BigDecimal(votAP).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
					res.setPercentage(AsVP.toString());
					res.setBallotVotes(totPVE);
					Double PsVP = new BigDecimal(votPP).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					res.setBallotVotesPercentage(PsVP.toString());
					if(status.equalsIgnoreCase("CROSS_VOTING")){
						Double totalDif = new BigDecimal(totDif).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						res.setDiffPercent(totalDif.toString());
					}
					partyResults.add(res);
				}
			}
		}
	 return partyResults;
	}
	
	/*
	 * To Get Alliance Parties List In An Election
	 */
	public Map<Long,String> getAlliancPartiesList(String electionType,String electionYear,Long partyId){
		
		log.debug(" Alliance Party ........ " + partyId);
		Map<Long,String> allianceMap = new HashMap<Long,String>();
		
		if(electionType != null && electionYear != null && partyId != null){
			List<SelectOptionVO> alliance = staticDataService.getAlliancePartiesAsVO(electionYear,IConstants.ASSEMBLY_ELECTION_TYPE_ID,partyId,0L);
			if(alliance != null && alliance.size() > 0){
				for(SelectOptionVO res:alliance){
					log.debug(" Parties In Alliance :" + res.getName());
					allianceMap.put(res.getId(), res.getName());
				}
				
				if(allianceMap.containsKey(partyId))
					allianceMap.remove(partyId);
			}
		}
	 return allianceMap;
	}
	/*
	 * Check For Party Result Validation 
	 */
	public Boolean checkForPartyResultValidation(PartyResultsVO results){
		
		if(log.isDebugEnabled())
			log.debug(" Inside checkForPartyResultValidation Method ");
		
		if(results != null){
			if(results.getPartyName() == null)
				return false;
			else if(results.getVotesEarned() != null && results.getVotesEarned() > new Long(0) 
			&& results.getPercentage() != null && !results.getPercentage().equalsIgnoreCase("--"))
				return true;
		}
	 return false;
	}
	
	/*
	 * 
	 */
	public PartyResultsVO getCopiedVO(PartyResultsVO resultVO){
		PartyResultsVO result = null;
		
		if(resultVO.getPartyId() != null && resultVO.getPartyName() != null && resultVO.getVotesEarned() != null && resultVO.getPercentage() !=null){
		result = new PartyResultsVO();
			
		result.setPartyId(resultVO.getPartyId());
		result.setPartyName(resultVO.getPartyName());
		result.setVotesEarned(resultVO.getVotesEarned());
		result.setValidVotes(resultVO.getValidVotes());
		result.setPercentage(resultVO.getPercentage());
		}
		
		return result;
	}
	
	public List<SelectOptionVO> setStaticParties(List<SelectOptionVO> parties)
	{ 
		List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
		
		
		for(SelectOptionVO party:parties)
		{
			if(party.getName().equals(IConstants.TDP) || party.getName().equals(IConstants.INC) || party.getName().equals(IConstants.BJP) || party.getName().equals(IConstants.TRS) || party.getName().equals(IConstants.PRP))
				partiesList.add(party);
		}
		int listSize = partiesList.size();
		partiesList.add(listSize,new SelectOptionVO(0L,"Others"));
		return partiesList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyResultVO> findRevenueVillageswiseResultsInElectionsOfMandal(Long tehsilId, String parties, 
			String elections, Boolean includeAlliance ){
		List validVotesInElecOfMandal = villageBoothElectionDAO.findPolledVotesInAllElectionsOfMandalByRevenueVillages(tehsilId);
		Map<String, Long> elecVillagePVMap = new LinkedHashMap<String, Long>();
		for(Object[] values:(List<Object[]>)validVotesInElecOfMandal)
			elecVillagePVMap.put(values[0]+"_"+values[3], (Long)values[5]);
		String[] partyIds = StringUtils.split(parties, ",");
		boolean isOthersSelected = false;
		for(String partyId:partyIds)
			if("0".equals(partyId.trim()))
				isOthersSelected = true;
		
		if(includeAlliance){
			return alliancePartiesResultsByRevenueVillages(parties, elections, elecVillagePVMap, tehsilId); 
		}
		
		StringBuilder hqlQuery1 =new StringBuilder();
		hqlQuery1.append("and model.nomination.party.partyId in("+parties+")" )
		.append("and model.boothConstituencyElection.constituencyElection.election.electionId in ("+elections+")");
		hqlQuery1.append(" group by model.boothConstituencyElection.villageBoothElection.township.townshipId, ")
		.append("model.boothConstituencyElection.constituencyElection.election.electionId, model.nomination.party.partyId ");
		
		List<PartyResultVO> partiesResultsVO = new ArrayList<PartyResultVO>();
		List villagesWiseResults = null;
		
		villagesWiseResults = candidateBoothResultDAO.
			getAllPartiesResultsInAllElectionsByRevenueVillgesInMandal(hqlQuery1.toString(), tehsilId);
		getPartyResultsListByRevenueVillages(villagesWiseResults, partiesResultsVO, elecVillagePVMap, false, null);
		
		if(isOthersSelected)
			otherPartiesResultsByRevenueVillages(elections, elecVillagePVMap,partiesResultsVO, tehsilId);
		
		return partiesResultsVO;
	}
	
	private void otherPartiesResultsByRevenueVillages(String elections, Map<String, Long> elecVillagePVMap,List<PartyResultVO> partiesResultsVO, Long tehsilId) {
		StringBuilder exculededPartyIds = new StringBuilder();
		StringBuilder hqlQuery2 = new StringBuilder();
		MandalVO mandalVO = staticDataService.findListOfElectionsAndPartiesInMandal(tehsilId);
		List<SelectOptionVO> partiesExcluded = mandalVO.getPartiesInMandal();

		for(SelectOptionVO party:partiesExcluded)
			exculededPartyIds.append(","+party.getId());
		
		hqlQuery2.append("and model.nomination.party.partyId not in("+exculededPartyIds.substring(1)+")" )
		.append("and model.boothConstituencyElection.constituencyElection.election.electionId in ("+elections+")");
		hqlQuery2.append(" group by model.boothConstituencyElection.villageBoothElection.township.townshipId, ")
		.append("model.boothConstituencyElection.constituencyElection.election.electionId ");
		
		List villagesWiseResults = candidateBoothResultDAO.
		getAllPartiesResultsInAllElectionsByRevenueVillgesInMandal(hqlQuery2.toString(), tehsilId);			
		getPartyResultsListByRevenueVillages(villagesWiseResults, partiesResultsVO, elecVillagePVMap, true, null);
	}
	
	@SuppressWarnings("unchecked")
	private List<PartyResultVO> alliancePartiesResultsByRevenueVillages(
			String parties, String elections, Map<String, Long> elecVillagePVMap, Long tehsilId) {
		List<PartyResultVO> partiesResultsVO = new ArrayList<PartyResultVO>();
		
		String[] electionsArr = StringUtils.split(elections,",");
		String[] partiesArr = StringUtils.split(parties,",");
		
		StringBuilder hqlQuery = null;
		AlliancePartyResultsVO allianceGroup = null;
		List villagesWiseResults = null;
		
		for(String elecId:electionsArr){
			Set<Long> completedPartyIds = new HashSet<Long>();
			for(String partyId:partiesArr){
				if(completedPartyIds.contains(Long.valueOf(partyId.trim())))
					continue;
				String commaSeperatedPartyIds;
				allianceGroup = staticDataService.getAlliancePartiesByElectionAndParty(new Long(elecId.trim()), new Long(partyId.trim()));
				if(allianceGroup == null){
					completedPartyIds.add(Long.valueOf(partyId.trim()));
					commaSeperatedPartyIds = partyId;
				}else{
					boolean exist = false;
					StringBuilder partyIds = new StringBuilder();
					for(SelectOptionVO party:allianceGroup.getAllianceParties()){
						partyIds.append(","+party.getId());
						if(completedPartyIds.contains(party.getId()))
							exist = true;
						completedPartyIds.add(party.getId());
					}
					commaSeperatedPartyIds = partyIds.substring(1).toString();
					if(exist)
						continue;
				}
					
				if("0".equals(commaSeperatedPartyIds.trim())){
					otherPartiesResultsByRevenueVillages(elections, elecVillagePVMap, partiesResultsVO, tehsilId);
					continue;
				}
				
				hqlQuery = new StringBuilder();
				hqlQuery.append("and model.nomination.party.partyId in("+commaSeperatedPartyIds+")" )
				.append("and model.boothConstituencyElection.constituencyElection.election.electionId ="+elecId);
				hqlQuery.append(" group by model.boothConstituencyElection.villageBoothElection.township.townshipId, ")
				.append("model.boothConstituencyElection.constituencyElection.election.electionId ");
				
				villagesWiseResults = candidateBoothResultDAO.
				getAllPartiesResultsInAllElectionsByRevenueVillgesInMandal(hqlQuery.toString(), tehsilId);
				getPartyResultsListByRevenueVillages(villagesWiseResults, partiesResultsVO, elecVillagePVMap, false, allianceGroup);
			}
		}
		return partiesResultsVO;
	}

	private void getPartyResultsListByRevenueVillages(List villagesWiseResults, List<PartyResultVO> partiesResultsVO, 
			Map<String, Long> elecVillagePVMap, Boolean includeOthers, AlliancePartyResultsVO allianceGroup){
		PartyResultVO partyResultVO = null;
		for(Object[] values:(List<Object[]>)villagesWiseResults){
			partyResultVO = new PartyResultVO();
			if(allianceGroup != null)
				partyResultVO.setPartyName(allianceGroup.getAllianceGroupName()+" IN "+values[2]+" "+values[1]);
			else if(includeOthers)
				partyResultVO.setPartyName("Others IN "+values[2]+" "+values[1]);
			else
				partyResultVO.setPartyName(values[6]+" IN "+values[2]+" "+values[1]);
			partyResultVO.setConstituencyName(values[4].toString());
			Long data = elecVillagePVMap.get(values[0]+"_"+values[3]);
			if(data!=0){
				partyResultVO.setVotesPercent(new BigDecimal((Long)values[7]*100.0/data).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				partyResultVO.setVotesPercent("0.0");
			}
			
			partiesResultsVO.add(partyResultVO);
		}
	}	
	
	
	
	

	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#getTownshipWiseAllPartyResults(java.lang.Long, java.lang.String, java.lang.String)
	 * @Input tehsilId , electionType and electionYear
	 * @Output township wise all party results map
	 * Returns all party results for each township in given mandal
	 */
	@SuppressWarnings("unchecked")
	public Map<Long, List<PartyTownshipResultsVO>> getTownshipWiseAllPartyResults(
			Long tehsilId, String electionType, String electionYear,Boolean isPanchayatWise) {
		
		if(log.isDebugEnabled())
			log.debug(" Inside getTownshipWiseAllPartyResults method ...");
		
		//Map<Long,List<PartyResultVO>> Long refers to townshipId, List<PartyResultVO> is all party results
		Map<Long, List<PartyTownshipResultsVO>> allPartyRes = new HashMap<Long, List<PartyTownshipResultsVO>>();
		
		if(tehsilId != null && electionType != null && electionYear != null){
			List resultsList = null;
			
			if(isPanchayatWise != null && isPanchayatWise)
			{	
				resultsList = getPanchayatWisePartiesResultInAMandal(tehsilId, electionType, electionYear);
				
				if(resultsList == null || resultsList.size() == 0)
					resultsList = candidateBoothResultDAO.findPanchayatWiseAllPartyResultsInAMandal(electionType,electionYear,tehsilId);
			}
			else
				resultsList = candidateBoothResultDAO.findTownshipWiseAllPartyResultsInAMandal(electionType,electionYear,tehsilId);
			
			Boolean checkRes = checkResultsList(resultsList);
			
			if(checkRes){
				
				ListIterator resultIT = resultsList.listIterator();
				
				//iterate the results
				while(resultIT.hasNext()){
					Object[] resParam = (Object[])resultIT.next();
					
					Long townId = (Long)resParam[2];
					if(allPartyRes.isEmpty() || !allPartyRes.containsKey(townId)){
					List<PartyTownshipResultsVO> townshipResList = new ArrayList<PartyTownshipResultsVO>();
					PartyTownshipResultsVO townshipRes = getTownshipResults(resParam);
					 
					if(townshipRes != null)
						 townshipResList.add(townshipRes);
					allPartyRes.put(townId, townshipResList);
					}
					else if(allPartyRes.containsKey(townId)){
					List<PartyTownshipResultsVO> townshipResList = allPartyRes.get(townId);
					PartyTownshipResultsVO townshipRes = getTownshipResults(resParam);
						 
					if(townshipRes != null)
						 townshipResList.add(townshipRes);
					allPartyRes.put(townId, townshipResList);
					}
				}
				
			}
			
		}
		
	 return allPartyRes;
	}
	
	public List<Object[]> getPanchayatWisePartiesResultInAMandal(Long tehsilId, String electionType, String electionYear)
	{
		try{
			List<Object[]> resultList = null;
			Long electionId = (Long)boothConstituencyElectionDAO.getElectionIdInMandal(tehsilId, electionType, electionYear);
			MandalAndRevenueVillagesInfoVO mandalAndRevenueVillagesInfoVO = constituencyPageService.getPanchayatWiseBoothDetailsForTehsil(tehsilId, electionId);
			
			if(mandalAndRevenueVillagesInfoVO != null)
			{
				resultList = new ArrayList<Object[]>(0); 
				List<LocationWiseBoothDetailsVO> panchayats = mandalAndRevenueVillagesInfoVO.getPartiesResultsInVillages().get(0).getRevenueVillagesInfo();
				
				for(LocationWiseBoothDetailsVO boothDetailsVO : panchayats)
				{
					List<Long> boothIdList = new ArrayList<Long>(0);
					
					for(SelectOptionVO optionVO : boothDetailsVO.getBooths())
						boothIdList.add(optionVO.getId());
					List<Object[]> list = candidateBoothResultDAO.findBoothResultsForBoothsAndElection(boothIdList,electionId);
					
					for(Object[] params : list)
					{
						Object[] result = new Object[7];
						result[0] = params[2];
						result[1] = boothDetailsVO.getVotesPolled();
						result[2] = boothDetailsVO.getLocationId();
						result[3] = boothDetailsVO.getLocationName();
						result[4] = params[0];
						result[5] = params[1];
						result[6] = boothDetailsVO.getPopulation();
						resultList.add(result);
					}
				}
			}
			return resultList;
		}catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * Checks wheather DAO return results is empty or not
	 */
	@SuppressWarnings("unchecked")
	public Boolean checkResultsList(List resultsList){
		if(resultsList != null && resultsList.size() > 0)
			return true;
		else return false;
	}
	
	/*
	 * Township results to VO
	 */
	public PartyTownshipResultsVO getTownshipResults(Object[] results){
		if(results != null){
			PartyTownshipResultsVO partyResult = new PartyTownshipResultsVO();
			partyResult.setPartyId((Long)results[4]);
			partyResult.setPartyName((String)results[5]);
			partyResult.setTownshipId((Long)results[2]);
			partyResult.setTownshipName((String)results[3]);
			
			Long votesEarned = (Long)results[0];
			Long validVotes = (Long)results[1];
			partyResult.setValidVotes(validVotes);
			partyResult.setVotesEarned(votesEarned);
			Long totVoters = (Long)results[6];
			partyResult.setTotVoters(totVoters);
			
			Double votePercent = new BigDecimal(new Double(votesEarned)/new Double(validVotes)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			partyResult.setVotesPercent(votePercent.toString());
			
			return partyResult;
		}
	 return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBiElectionPageService#villageLevelPArtyAnalysis(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List<PartyVillageLevelAnalysisVO> villageLevelPArtyAnalysis(
			Long tehsilId, String electionType, String electionYear,int rank,Boolean isPanchayatWise) {
		
		if(log.isDebugEnabled())
			log.debug(" Inside villageLevelPArtyAnalysis Method ");
		List<PartyVillageLevelAnalysisVO> villageLevelResults = null;
		
		if(tehsilId != null && electionType != null && electionYear != null){
			
			villageLevelResults = new ArrayList<PartyVillageLevelAnalysisVO>();
			Map<Long, List<PartyTownshipResultsVO>> townshipwiseRes = getTownshipWiseAllPartyResults(tehsilId,electionType,electionYear,isPanchayatWise);
			Map<Long,List<PartyTownshipResultsVO>> partyResultsSorted = new HashMap<Long,List<PartyTownshipResultsVO>>();
			if(!townshipwiseRes.isEmpty()){
				
				try{
					
				 Long mandalValidVotes = getMandalValidVotes(tehsilId,electionType,electionYear);
				 Long constiValidVotes = new Long(0);
				 
				 //get constituency id for the selected mandal
				 Long constitId = getLatestConstituencyForAMandal(tehsilId); 
				 if(constitId != null){
					 if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
						 constitId = getLatestParliamentConstituencyForAssembly(constitId);
					 
					 if(constitId != null)
						 constiValidVotes =  getConstituencyValidVotes(constitId,electionYear);
				 }
				 
				 //sort the results for all townships
				 Set<Long> townKeys = townshipwiseRes.keySet();
				 for(Long township:townKeys){
					List<PartyTownshipResultsVO> townshipRes = townshipwiseRes.get(township);
					
					//sort the results votes earned desc
					Collections.sort(townshipRes, new PartyTownshipResultsComparator());
					PartyTownshipResultsVO res = townshipRes.get(rank-1);
					Long partyId = res.getPartyId();
					if(partyResultsSorted.isEmpty() || !partyResultsSorted.containsKey(partyId)){
						List<PartyTownshipResultsVO> partyResInMap = new ArrayList<PartyTownshipResultsVO>();
						partyResInMap.add(res);
						partyResultsSorted.put(partyId, partyResInMap);
					}
					else if(partyResultsSorted.containsKey(partyId)){
						List<PartyTownshipResultsVO> partyResInMap = partyResultsSorted.get(partyId);
						partyResInMap.add(res);
						partyResultsSorted.put(partyId, partyResInMap);
					}
					
					
				 }
				 
				 //map process and set results to VO
				 if(!partyResultsSorted.isEmpty()){
					 Set<Long> partyKeys = partyResultsSorted.keySet();
					 for(Long partyKey:partyKeys){
						 List<PartyTownshipResultsVO> townshipResList = partyResultsSorted.get(partyKey);
						 PartyVillageLevelAnalysisVO villageLevelRes = getPartyVillageResultsInAMandal(partyKey,townshipResList,mandalValidVotes,constiValidVotes,electionType,electionYear);
						 if(villageLevelRes != null)
						 villageLevelResults.add(villageLevelRes);
					 }
				 }
				 
				}
				catch(Exception ex){
				 ex.printStackTrace();
				 log.debug("Exception Raised :" + ex);
				}
			}
		}
		
	 return villageLevelResults;
	}
	
	@SuppressWarnings("unchecked")
	public Long getLatestConstituencyForAMandal(Long mandalId){
		if(mandalId != null){
		List resList = delimitationConstituencyMandalDAO.getLatestAssemblyConstitueciesOfTehsil(mandalId);
		 if(resList != null && resList.size() > 0){
			 Object[] params = (Object[])resList.get(0);
			 Long constituencyId = (Long)params[4];
			 
			 log.debug(" Latest Constituency For Mandal Is : " + constituencyId);
			 return constituencyId;
		 }
		
		}
	 return null;
	}
	
	@SuppressWarnings("unchecked")
	public Long getLatestParliamentConstituencyForAssembly(Long assemConstiId){
		if(assemConstiId != null){
		 List assmConst = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(assemConstiId);
		 if(assmConst != null && assmConst.size() > 0){
			 Object[] params = (Object[])assmConst.get(0);
			 Long parConsti = (Long)params[0];
			 
			 log.debug(" Parliament ConstituencyId :" + parConsti);
			 return parConsti;
		 }
		}
	 return null;
	}
	
	/*
	 * Process the final village level data for a party and set to VO
	 */
	public PartyVillageLevelAnalysisVO getPartyVillageResultsInAMandal(Long partyId,List<PartyTownshipResultsVO> townshipResList,Long mandValidVotes,Long constiValidVotes,
			String electionType,String electionYear){
		
		if(log.isDebugEnabled())
			log.debug(" Inside getPartyVillageResultsInAMandal Method ...");
		
		PartyVillageLevelAnalysisVO villageLevelRes = null;
		if(partyId != null && townshipResList != null && mandValidVotes != null && constiValidVotes != null){
			
			villageLevelRes = new PartyVillageLevelAnalysisVO();
			villageLevelRes.setElectionType(electionType);
			villageLevelRes.setElectionYear(electionYear);
			villageLevelRes.setPartyId(partyId);
			
			//process the results List and set to VO
			Long validVotes = new Long(0);
			Long votesEarned = new Long(0);
			Long totVoters = new Long(0);
			String partyName = "";
			StringBuilder townships = new StringBuilder(" ");
			int i=1;
			Boolean flag = false;
			for(PartyTownshipResultsVO townshipRes:townshipResList){
				
				votesEarned+=townshipRes.getVotesEarned();
				validVotes+=townshipRes.getValidVotes();
				totVoters+=townshipRes.getTotVoters();
				
				partyName = townshipRes.getPartyName();
				if(i == townshipResList.size())
					townships.append(townshipRes.getTownshipName());
				else{
					if(i%4 == 0){
				     townships.append("\n");
				     flag = true;
					}
				    townships.append(townshipRes.getTownshipName() + " , ");
				}
				i++;
			}
						
			if(flag == true){
			townships.delete(townships.length()-1, townships.length());
			townships.append(".");
			}
			
			if(!validVotes.equals(new Long(0)) && !votesEarned.equals(new Long(0))){
				villageLevelRes.setPartyName(partyName);
				villageLevelRes.setWonVillagesCount(townshipResList.size());
				villageLevelRes.setTotVotesEarned(votesEarned);
				villageLevelRes.setTotValidVotes(validVotes);
				villageLevelRes.setTotVoters(totVoters);
				villageLevelRes.setVotesShareInVill(getVotesPercent(votesEarned,validVotes));
				villageLevelRes.setVotesShareInMandal(getVotesPercent(votesEarned,mandValidVotes));
				villageLevelRes.setVotesShareInConsti(getVotesPercent(votesEarned,constiValidVotes));
				log.debug(" ................. Townships :" + townships);
				villageLevelRes.setTownships(townships.toString());
			}
			
		}
	 return villageLevelRes;
	}
	
	public String getVotesPercent(Long votesEarned,Long validVotes){
		String votesPercent = null;
		try{
		 Double votePrcnt = new BigDecimal(votesEarned.doubleValue()/validVotes.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 votesPercent = votePrcnt.toString();
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.debug(" Exception Raised :" + ex);
			return null;
		}
	 return votesPercent; 
	}
	
	@SuppressWarnings("unchecked")
	public Long getMandalValidVotes(Long tehsilId,String elecType,String elecYear){
		
		try{
		//List mandalValidVotes = candidateBoothResultDAO.getValidVotesInAMandal(tehsilId, elecType, elecYear);
			List mandalValidVotes = boothConstituencyElectionDAO.getValidVotesInAnElectionInMandal(tehsilId, elecType, elecYear);
		if(mandalValidVotes != null){
			Object params = (Object)mandalValidVotes.get(0);
			Long validVotes = (Long)params;
		 return validVotes;
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
	 return null;
	}
	
	@SuppressWarnings("unchecked")
	public Long getConstituencyValidVotes(Long constiId,String electionYear){
		
		try{
		 List constValidVotes = constituencyElectionDAO.getValidVotesForAConstituency(constiId,electionYear);
		 if(constValidVotes != null){
			Object params = (Object)constValidVotes.get(0);
			Double validVotes = (Double)params;
		  return validVotes.longValue();
		 }
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
	 return null;
	}
	
	//Need To Be Refactored - Gives Predicted Results On Bye Elections 
	public List<ConstituencyVO> getAllTelanganaConstituencieswisePartiesResultsBasedOnExpectedPercentage(String expePercent,Boolean includeLocalElec){
		List<BiElectionDistrictVO> byeElecConsties = getBiElectionConstituenciesDistrictWise();
		List<PartyResultVO> constiPartyResults = null;
		Map<String, Float> partyAndPercent = null;
		Set<String> staticParties = new HashSet<String>();
		staticParties.add("INC");staticParties.add("TDP");
		String consideringParty = "";
		Float totalPercent = 0.0f;
		Float remainingPercent = 0.0f;
		Float favourPercent = 0.0f;
		Float sharablePercent = 0.0f;
		String partyName = "";
		Float percent = 0.0f;
		Float considPartyPercent = 0.0f;
		List<ConstituencyVO> constituenies = new ArrayList<ConstituencyVO>();
		List<PartyResultVO> partiesResults = null;
		ConstituencyVO constituencyVO = null;
		PartyResultVO partyResultVO = null;
		ElectionTrendzReportVO constituencyOverView = null;
		String[] choices = {"AC","PC"};
		Set<String> choice = new HashSet<String>();
		choice.add(IConstants.ASSEMBLY_ELECTION_TYPE);
		choice.add(IConstants.PARLIAMENT_ELECTION_TYPE);
		for(BiElectionDistrictVO biElectionDistrictVO:byeElecConsties)
			for(SelectOptionVO consti:biElectionDistrictVO.getConstituenciesList()){
				partyAndPercent = new HashMap<String, Float>();
				constituencyVO = new ConstituencyVO();
				constituencyVO.setName(consti.getName().toUpperCase());
				constituencyVO.setId(consti.getId());
				constituencyOverView = staticDataService.getConstituencyOverview(consti.getId(), consti.getName());
				constituencyVO.setTotalVoters2009(constituencyOverView.getPresentYearTotalVoters());
				constituencyVO.setTotalVoters2010(constituencyOverView.getLatestElectionYearsTotalVoters());
				constituencyVO.setTotalPolledVotes(constituencyOverView.getLatestElectionYearsTotalPolledVotes());
				constituencyVO.setVotesPercent(constituencyOverView.getLatestElectionYearsTotalVotesPercentage());
				if("Nizamabad Urban".equalsIgnoreCase(consti.getName()))
					consideringParty = "BJP";
				else
					consideringParty = "TRS";
				if("Nizamabad Urban".equalsIgnoreCase(consti.getName()) || "Warangal West".equalsIgnoreCase(consti.getName()))
					if(includeLocalElec)
					    constiPartyResults = getResultsOfRuralUrbanAreaBeasedOnSelection(consti.getId(), null, null, 
							false, false).getAllPartiesAllElectionResults();
					else
						constiPartyResults = getResultsOfRuralUrbanAreaBeasedOnSelection(consti.getId(), null, choice, 
									true, false).getAllPartiesAllElectionResults();
				else{
					if(includeLocalElec)
					    constiPartyResults = staticDataService.getPartyVotesPercentageInAConstituency(consti.getId(),"All", null);
					else
						constiPartyResults = staticDataService.getPartyVotesPercentageInAConstituency(consti.getId(),"-", choices);
				}
				//constituencyVO.setAllPartiesElecResults(constiPartyResults);
				totalPercent = 0f;
				remainingPercent = 0f;
				favourPercent = 0f;
				sharablePercent = 0f;
				considPartyPercent = 0f;
				for(PartyResultVO party:constiPartyResults){
					String[] lowAndHighPercents = StringUtils.split(party.getRange(),"- ");
					if(lowAndHighPercents.length == 0)
						continue;
					if(staticParties.contains(party.getPartyName()) || consideringParty.equalsIgnoreCase(party.getPartyName())){
						if(consideringParty.equalsIgnoreCase(party.getPartyName()))
							considPartyPercent = new Float(lowAndHighPercents[0]);
						totalPercent += new Float(lowAndHighPercents[0]);
						partyAndPercent.put(party.getPartyName(), new Float(lowAndHighPercents[0]));
					}
				}
				remainingPercent = 100.0f - totalPercent;
				favourPercent = remainingPercent*new Float(expePercent)/100.0f;
				sharablePercent = remainingPercent - favourPercent;
				partiesResults = new ArrayList<PartyResultVO>();
				for(Map.Entry<String, Float> entry:partyAndPercent.entrySet()){
					partyResultVO = new PartyResultVO();
					partyName = entry.getKey();
					partyResultVO.setPartyName(entry.getKey());
					if(partyName.equalsIgnoreCase(consideringParty)){
						partyResultVO.setIsConsiderParty(true);
						percent = entry.getValue() + favourPercent;
					}
					else
						percent = entry.getValue() + (sharablePercent * (entry.getValue()/(totalPercent-considPartyPercent)));
					partyResultVO.setVotesPercent(new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					partiesResults.add(partyResultVO);
				}
				Collections.sort(partiesResults, new PartyResultVOPercentComparator());
				partiesResults.get(0).setIsPartyWon(true);
				constituencyVO.setPredictedPartiesResults(partiesResults);
				constituenies.add(constituencyVO);
				
			}
		
		return constituenies;
		
	}


	public List<ConstituencyElectionResultsVO> getMainPartiesResultsInConstituency(
			Long constituencyId, String constituencyName) {
		log.debug("Entered in to getMainPartiesResultsInConstituency method in service:");
		List<ConstituencyElectionResultsVO> electionResultsList = new ArrayList<ConstituencyElectionResultsVO>();
		ConstituencyElectionResultsVO constituencyElectionResults = new ConstituencyElectionResultsVO();
		ConstituencyElectionResultsVO presentElectionResults = null;
		ConstituencyElectionResultsVO byeElectionResults = null;
		ConstituencyElectionResultsVO processedPresentElectionResults = null;
		ConstituencyElectionResultsVO processedByeElectionResults = null;
		Set<String> presentYearParties = new HashSet<String>();
		Set<String> byeElecYearParties = new HashSet<String>();
		Boolean includeINDs = false;
		presentYearParties.add("BJP");
		presentYearParties.add("INC");
		presentYearParties.add("PRP");
		presentYearParties.add("TDP");
		presentYearParties.add("TRS");
				
		if("SIRCILLA".equalsIgnoreCase(constituencyName))
			includeINDs = true;
			
				
		byeElecYearParties.add("INC");
		byeElecYearParties.add("TDP");
		
		
		if("Nizamabad Urban".equalsIgnoreCase(constituencyName))
			byeElecYearParties.add("BJP");
		else byeElecYearParties.add("TRS");
		try{
			byeElectionResults = staticDataService.getAllCandidatesDetailsForConstituency(constituencyId, IConstants.PRESENT_YEAR, IConstants.ASSEMBLY_ELECTION_TYPE);
			presentElectionResults = staticDataService.getAllCandidatesDetailsForConstituency(constituencyId, IConstants.PRESENT_ELECTION_YEAR, IConstants.ASSEMBLY_ELECTION_TYPE);
			 
				
			if(byeElectionResults != null)
			{	
				processedByeElectionResults = processElectionResults(byeElectionResults, byeElecYearParties,false, IConstants.PRESENT_YEAR);
				electionResultsList.add(processedByeElectionResults);
			}
			if(presentElectionResults != null)
			{	
				processedPresentElectionResults = processElectionResults(presentElectionResults, presentYearParties, includeINDs, IConstants.PRESENT_ELECTION_YEAR);
				electionResultsList.add(processedPresentElectionResults);
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}		
		
		return electionResultsList;
	}
	
	private ConstituencyElectionResultsVO processElectionResults(ConstituencyElectionResultsVO resultsToProcess, Set<String> partiesList, Boolean includeINDs, String electionYear)
	{
		log.debug("includeINDs:::::::::::::::::::" + includeINDs);
		ConstituencyElectionResultsVO constituencyElectionResults = new ConstituencyElectionResultsVO();
		List<CandidateOppositionVO> requiredPartiesList = new ArrayList<CandidateOppositionVO>();
		CandidateOppositionVO othersResults = new CandidateOppositionVO(); 
		Long votesEarned = new Long(0l);
		Float percentage = new Float(0f);
		int i = 0;
		Integer othersCount = 0;
		Boolean resultsFlag = false;
		CandidateWonVO wonCandidate = resultsToProcess.getCandidateResultsVO();
		CandidateOppositionVO wonCandidateObj = new CandidateOppositionVO();
		
		//If Data Not Exits For that Election
		if(wonCandidate == null){
			constituencyElectionResults.setResultsFlag(resultsFlag);
			return constituencyElectionResults;
		}
			
		wonCandidateObj.setCandidateId(wonCandidate.getCandidateId());
		wonCandidateObj.setCandidateName(wonCandidate.getCandidateName());
		wonCandidateObj.setPartyId(wonCandidate.getPartyId());
		wonCandidateObj.setPartyName(wonCandidate.getPartyName());
		wonCandidateObj.setRank(wonCandidate.getRank());
		wonCandidateObj.setVotesEarned(wonCandidate.getVotesEarned());
		wonCandidateObj.setVotesPercentage(new BigDecimal(wonCandidate.getVotesPercentage()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		List<CandidateOppositionVO> oppositionCandList = resultsToProcess.getCandidateOppositionList();
		oppositionCandList.add(0,wonCandidateObj);
		for(CandidateOppositionVO candidateOppositionVO: oppositionCandList)
		{
			if(new Float(candidateOppositionVO.getVotesEarned()) > 0)
				resultsFlag = true;
			if(includeINDs && "IND".equals(candidateOppositionVO.getPartyName()) && i == 0)
			{				
				requiredPartiesList.add(candidateOppositionVO);
				i++;				
			}
			if(partiesList.contains(candidateOppositionVO.getPartyName()))
			{
				requiredPartiesList.add(candidateOppositionVO);
			} else
			{
				votesEarned += new Long(candidateOppositionVO.getVotesEarned());
				percentage += new Float(candidateOppositionVO.getVotesPercentage());
				othersCount++;
			}						
		}
		othersResults.setPartyName("Others");
		othersResults.setCandidateName(othersCount.toString()+" Members Consolidated Results");
		othersResults.setVotesEarned(votesEarned.toString());
		othersResults.setVotesPercentage(new BigDecimal(percentage).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		requiredPartiesList.add(othersResults);
		constituencyElectionResults.setCandidateOppositionList(requiredPartiesList);
		constituencyElectionResults.setCandidateResultsVO(resultsToProcess.getCandidateResultsVO());
		constituencyElectionResults.setElectionYear(electionYear);
		constituencyElectionResults.setResultsFlag(resultsFlag);
		return constituencyElectionResults;
	}
	
}
