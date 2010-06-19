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
import com.itgrids.partyanalyst.dao.INominationDAO;
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
import com.itgrids.partyanalyst.dto.PartyResultsInVotesMarginVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyVotesMarginInConstituency;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsInMandal;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotesMarginResultsMainVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.model.Booth;
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
	private INominationDAO nominationDAO;
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

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
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
		
		Long validVotes = partyResult.getValidVotes();
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
			Long tehsilId,Long partyId,String electionYear,String electnType,String partyType,Long rank) 
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
				mandalBoothResults = candidateBoothResultDAO.getBoothWisePartyResultsInAMandalByPartyRank(tehsilId, electionYear,electnType, rank);
			
			
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
			
			boothResult.setPartNo(booth.getPartNo());
			boothResult.setLocation(booth.getLocation());
			boothResult.setMandal(booth.getTehsil().getTehsilName());
			boothResult.setVillagesCovered(booth.getvillagesCovered());
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
		    	
			Map<String, Map<Long, List<BoothResultVO>>> mainPartyResults = getPartyMarginResultsInAMandalForAllElections(mandalId,partyId,electionYear,null,IConstants.MAIN_PARTY,new Long(0));
			//Map<String, Map<Long, List<BoothResultVO>>> oppositnPartyResults = new HashMap<String, Map<Long, List<BoothResultVO>>>();			
			
			//getting results for opposition parties
			if(mainPartyResults != null && !mainPartyResults.isEmpty()){
			 Set<String> elecTypes = mainPartyResults.keySet();
			 for(String electionTyp:elecTypes){
				 
			   Map<Long, List<BoothResultVO>> mainPartyRes = mainPartyResults.get(electionTyp);
			   List rankOfPartyCandidate = nominationDAO.getCandidateRankInAConstituencyElection(constituencyId, electionYear, electionTyp, partyId);
			   if(rankOfPartyCandidate != null && rankOfPartyCandidate.size() > 0){
				 Object rankParam = (Object)rankOfPartyCandidate.get(0);
				 Long rank = (Long)rankParam;
				 Long oppCandRank = new Long(0);
				 if(rank.equals(new Long(1)))
					 oppCandRank = new Long(2);
				 else if(rank > new Long(1))
					 oppCandRank = new Long(1);
				 
				 Map<String, Map<Long, List<BoothResultVO>>> oppPartyResults = getPartyMarginResultsInAMandalForAllElections(mandalId,partyId,electionYear,electionTyp,IConstants.OPP_PARTY,oppCandRank);
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
		}
	 return boothWiseResultsMainMap;
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
					
					PartyVotesMarginResultsInMandal partyResultsInMandal = getBoothWisePartyResultsForAMandal(constituencyId,mandalId,partyId);
					if(partyResultsInMandal != null){
						partyResultsInMandal.setMandalName(mandalName);
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
	
}
