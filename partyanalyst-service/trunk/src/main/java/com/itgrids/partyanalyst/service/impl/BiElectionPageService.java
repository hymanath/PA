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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.AllPartyElectionResultsForElectionTypeVO;
import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultAllPartyVO;
import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsForMandalVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsInConstituencyVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyResultsComparator;
import com.itgrids.partyanalyst.utils.PartyResultsVOComparator;

public class BiElectionPageService implements IBiElectionPageService {
	
	private ITehsilDAO tehsilDAO;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO; 
	
	private static final Logger log = Logger.getLogger(BiElectionPageService.class);

	
	
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

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
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
				selectOption.setName((String)params[3]);
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
		MandalElectionResultVO mandalElectionResultVO = new MandalElectionResultVO();
		List<PartyElectionResultsInConstituencyVO> partyElecResultsInConstituency = null;
		Map<Long,PartyElectionResultsInConstituencyVO> partyResultsMap = null;
		
		if(mandalId != null && !mandalId.equals(new Long(0))){
			
			partyElecResultsInConstituency = new ArrayList<PartyElectionResultsInConstituencyVO>();
			partyResultsMap = new HashMap<Long,PartyElectionResultsInConstituencyVO>();
			
			try{
			List electionResultsList = candidateBoothResultDAO.getElectionResultsInAMandalForAllParties(mandalId, electionType, electionYear);
			if(electionResultsList != null && electionResultsList.size() > 0){
				
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
			//set to final vo
			Tehsil tehsil = tehsilDAO.get(mandalId);
			mandalElectionResultVO.setMandalId(mandalId);
			mandalElectionResultVO.setMandalName(tehsil.getTehsilName());
			mandalElectionResultVO.setPartyElecResultsInConstituency(partyElecResultsInConstituency);
			
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
				mandalElectionResultVO.setResultStatus(resultStatus);
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
		
		ElectionResultsForMandalVO electionResultsForMandalVO = new ElectionResultsForMandalVO();
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
			  
			  //set to VO
			  if(electionResultsForMandal != null && electionResultsForMandal.size() > 0){
				  electionResultsForMandalVO.setElectionType(electionType);
				  electionResultsForMandalVO.setElectionYear(electionYear);
				  electionResultsForMandalVO.setElectionResultsForMandal(electionResultsForMandal);
				  
				  List election = electionDAO.findElectionIdByElectionTypeAndYear(electionType,electionYear,new Long(1));
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
				  
				  /*
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
					  
				  }*/
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
								  partyRes.setPercentage("N/A");
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
				electionResultsForMandalVO.setResultStatus(resultStatus);
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
					
					biElectionResultsVOList.add(biElectionResultOne);
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
				partyRslt.setPercentage("N/A");
				
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
		resultVO.setPercentage("N/A");
		
		partyResultsVOList.add(resultVO);
				
		}
	 return partyResultsVOList;
	}
}
