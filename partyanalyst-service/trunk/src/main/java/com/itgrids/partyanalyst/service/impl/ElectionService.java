package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICensusParameterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.CensusWisePartyResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CensusParameter;
import com.itgrids.partyanalyst.service.IElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyResultsVOComparator;

public class ElectionService implements IElectionService{
	
	private IElectionDAO electionDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IElectionTypeDAO electionTypeDAO;
	private INominationDAO nominationDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
	private IStaticDataService staticDataService;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private ICensusParameterDAO censusParameterDAO;
	private IPartyDAO partyDAO;
	
	private final static Logger log = Logger.getLogger(ElectionService.class);

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	
	public IConstituencyCensusDetailsDAO getConstituencyCensusDetailsDAO() {
		return constituencyCensusDetailsDAO;
	}

	public void setConstituencyCensusDetailsDAO(
			IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO) {
		this.constituencyCensusDetailsDAO = constituencyCensusDetailsDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}

	public ICensusParameterDAO getCensusParameterDAO() {
		return censusParameterDAO;
	}

	public void setCensusParameterDAO(ICensusParameterDAO censusParameterDAO) {
		this.censusParameterDAO = censusParameterDAO;
	}

	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

    public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	/**
	 * This method give Constituency Ids in the range with 10 width based on census parameter.
	 * @author kamalakar Dandu
	 * @param Integer selectIndex
	 * @param Long stateId
	 * @param Long districtId
 	 * @param Long year
	 * @param String level
	 * @return List<CensusVO>
	 * 
	 */
	public List<CensusVO> getConstituencyCensusDetails(Integer selectIndex,Long stateId,Long districtId,Long year,String level)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the ElectionService.getConstituencyCensusDetails().. Call");
			}
		String censusStr = getCensusStructureBySelectedIndex(selectIndex);
		List<CensusVO> censusVOlist = new ArrayList<CensusVO>();
		List<Object[]>list = new ArrayList<Object[]>();
		
		//Here we are getting Constituency Ids in the State/District.
		if(level.equalsIgnoreCase(IConstants.STATE_STR))
		{
			list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentages(censusStr,stateId);
		}
		
		if(level.equalsIgnoreCase(IConstants.DISTRICT_STR))
		{
			List<Long>constituencyIds = delimitationConstituencyDAO.getLatestConstituenciesForDistrictBasedOnYear(districtId,year);
			
			list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentagesOfADistrict(censusStr,constituencyIds);
		}
		
		//Here we are putting Constituency Ids in HashMap with selected range.
		Map<String,List<Long>> resultMap = new LinkedHashMap<String,List<Long>>();
		
		resultMap.put("0-10", new ArrayList<Long>(0));
		resultMap.put("10-20", new ArrayList<Long>(0));
		resultMap.put("20-30", new ArrayList<Long>(0));
		resultMap.put("30-40", new ArrayList<Long>(0));
		resultMap.put("40-50", new ArrayList<Long>(0));
		resultMap.put("50-60", new ArrayList<Long>(0));
		resultMap.put("60-70", new ArrayList<Long>(0));
		resultMap.put("70-80", new ArrayList<Long>(0));
		resultMap.put("80-90", new ArrayList<Long>(0));
		resultMap.put("90-100", new ArrayList<Long>(0));
		
		for(Object[] obj:list)
		{
			Long constituencyId = (Long)obj[0];
			Double percentage = (Double)obj[1];
			
			if(percentage > 0 && percentage <= 10)
			{
				List<Long> idsListNew = resultMap.get("0-10");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("0-10", idsListNew);
			}else if(percentage > 10 && percentage <= 20)
			{
				List<Long> idsListNew = resultMap.get("10-20");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("10-20", idsListNew);
			}else if(percentage > 20 && percentage <= 30)
			{
				List<Long> idsListNew = resultMap.get("20-30");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("20-30", idsListNew);
			}else if(percentage > 30 && percentage <= 40)
			{
				List<Long> idsListNew = resultMap.get("30-40");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("30-40", idsListNew);
			}else if(percentage > 40 && percentage <= 50)
			{
				List<Long> idsListNew = resultMap.get("40-50");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("40-50", idsListNew);
			}else if(percentage > 50 && percentage <= 60)
			{
				List<Long> idsListNew = resultMap.get("50-60");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("50-60", idsListNew);
			}else if(percentage > 60 && percentage <= 70)
			{
				List<Long> idsListNew = resultMap.get("60-70");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("60-70", idsListNew);
			}else if(percentage > 70 && percentage <= 80)
			{
				List<Long> idsListNew = resultMap.get("70-80");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("70-80", idsListNew);
			}else if(percentage > 80 && percentage <= 90)
			{
				List<Long> idsListNew = resultMap.get("80-90");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("80-90", idsListNew);
			}else if(percentage > 90 && percentage <= 100)
			{
				List<Long> idsListNew = resultMap.get("90-100");
				if(idsListNew == null)
					idsListNew = new ArrayList<Long>();
				idsListNew.add(constituencyId);
				resultMap.put("90-100", idsListNew);
			}
		}
		 
		CensusVO censusVO = null;
		Long constituenciesCount = 0l;
		
		//Here we are setting data to List<CensusVO> from HashMap.
		for(Map.Entry<String,List<Long>> entry : resultMap.entrySet())
		{
			censusVO = new CensusVO();
			censusVO.setRange(entry.getKey());
			censusVO.setLocationIds(entry.getValue());
			censusVO.setCount(entry.getValue().size());
			constituenciesCount += entry.getValue().size();
			censusVOlist.add(censusVO);
		}
		
		censusVOlist.get(0).setTotalConstituencies(constituenciesCount);
		
		//Here we are setting Voting Percentage.
		for(CensusVO census:censusVOlist)
			census.setVotingPercent(findAverageVotingPercentageInConstituenciesInAYear(census.getLocationIds(), year.toString()));
		
		return censusVOlist;
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the ElectionService.getConstituencyCensusDetails().... ");
			log.error("Exception raised please check the log for details "+ex);
			return null;
		}
	}
	
	/**
	 * Here we are building Query dynamically.
	 * @param selectIndex
	 * @return String
	 */
	public String getCensusStructureBySelectedIndex(Integer selectIndex){
		String censusStr = "";
		if(selectIndex == 1)
			censusStr = "model.percentageSC";
		else if(selectIndex == 2)
			censusStr = "model.percentageST";
		else if(selectIndex == 3)
			censusStr = "model.popLiteraturePercentage";
		else if(selectIndex == 4)
			censusStr = "(100-model.popLiteraturePercentage)";
		else if(selectIndex == 5)
			censusStr = "model.totalWorkingPopPercentage";
		else
			censusStr = "model.nonWorkingPopPercentage";
		return censusStr;
	}
	
	/**
	 * Returns Party Wise Constituencies Grouped Results For An Election 
	 */
	public ElectionDataVO findAssemblyConstituenciesResultsByConstituencyIds(
			String electionYear, List<Long> constituencyIds, List<Long> partyIds, 
			List<Long> districtIds, Integer selected, Boolean isAll, Boolean includeConstiInfo){
		
		ElectionDataVO electionDataVO = new ElectionDataVO();
		List<PartyResultsVO> partyResultsList = new ArrayList<PartyResultsVO>();
		List<PartyResultsVO> partyResultsVOList = null;
		List<ConstituencyElectionResultsVO> constituenciesResults = null;
		List<String> allPartiesList = null;
		Set<SelectOptionVO> districts = new TreeSet<SelectOptionVO>();
		Set<SelectOptionVO> parties = new TreeSet<SelectOptionVO>();
		Map<String,Double> pariisMap = new HashMap<String, Double>();
		List resultsList = null;
		List constiResults = null;
		Double totalVotesEarned = 0d;
		Double totalValidVotes = 0d;
		StringBuilder query = new StringBuilder();
		Map<Long, Double> constituencyWithPercentMap = new HashMap<Long, Double>();
		Map<PartyResultsVO, List<Object[]>> partyWithResults = new LinkedHashMap<PartyResultsVO, List<Object[]>>();
		
		try {
			
			if(constituencyIds != null && constituencyIds.size() > 0 && isAll)
				resultsList = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies(electionYear,constituencyIds,IConstants.VOTES_PERCENTAGE_CONCERNED);
			else{
				if(districtIds != null && districtIds.size() > 0)
					query.append(" and model.constituencyElection.constituency.district.districtId in (:districtIds)");
				if(partyIds != null && partyIds.size() > 0)
					query.append(" and model.party.partyId in (:partyIds)");
				if(districtIds != null && partyIds != null && (districtIds.size() > 0 || partyIds.size() > 0))
					resultsList = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituenciesByCriteria(electionYear, 
							constituencyIds, partyIds, districtIds, query.toString());
				else
					return null;
			}
				
			
			PartyResultsVO partyResultsVO = null;
			List<Object[]> partyResults = null;
			
			for(Object[] values:(List<Object[]>)resultsList){
				partyResultsVO = new PartyResultsVO();
				partyResultsVO.setPartyId((Long)values[3]);
				partyResultsVO.setPartyName(values[2].toString());
				partyResults = partyWithResults.get(partyResultsVO);
				if(partyResults == null)
					partyResults = new ArrayList<Object[]>();
				partyResults.add(values);
				partyWithResults.put(partyResultsVO, partyResults);
			}
			
			int seatsWon = 0;
			
			if(constituencyIds.size() > 0)
				constiResults = constituencyElectionResultDAO.findTotalVotersAndValidVotesByYearAndConstituencyIds(constituencyIds, electionYear);
			
			for(Map.Entry<PartyResultsVO, List<Object[]>> entry:partyWithResults.entrySet()){
				seatsWon = 0;
				partyResultsVO = entry.getKey();
				partyResults = entry.getValue();
				totalVotesEarned = 0d;
				totalValidVotes = 0d;
				for(Object[] results:partyResults){
					totalVotesEarned += Double.parseDouble(results[1].toString());
					totalValidVotes += Double.parseDouble(results[6].toString());
					if("1".equalsIgnoreCase(results[7].toString()))
						seatsWon++;	
				}
				
				partyResultsVO.setSeatsParticipated(partyResults.size());
				partyResultsVO.setTotalSeatsWon(seatsWon);
				
				if(constiResults != null && constiResults.size() > 0 && partyResults.size() > 0 &&
						((Double)((Object[])constiResults.get(0))[1]).intValue() > 0)
					partyResultsVO.setAvgPercentage(new BigDecimal(totalVotesEarned*(100.0)/(Double)((Object[])constiResults.get(0))[1]).
							setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(totalValidVotes > 0)
					partyResultsVO.setPConstavgPercentage(new BigDecimal(totalVotesEarned*(100.0)/totalValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				
				if(Double.parseDouble(partyResultsVO.getAvgPercentage()) > Double.parseDouble(IConstants.VOTES_PERCENTAGE_CONCERNED))
				{
					partyResultsList.add(partyResultsVO);
					pariisMap.put(partyResultsVO.getPartyName(), Double.parseDouble(partyResultsVO.getAvgPercentage()));
				}
			}
			
			if(includeConstiInfo){
				
				getDistrictsAndParties(districts, parties, constituencyIds, electionYear);
				
				constituenciesResults = staticDataService.findAssemblyConstituenciesResultsByConstituencyIds(
						electionYear, constituencyIds, resultsList);
				Set<String> allParties = new HashSet<String>(0);
				Set<String> partiesInConstituency = null;
				for(ConstituencyElectionResultsVO constiInfo:constituenciesResults){
					partiesInConstituency = new HashSet<String>();
					partyResultsVOList = new ArrayList<PartyResultsVO>();
					List<Long> conList = new ArrayList<Long>();
					conList.add(constiInfo.getConstituencyId());
					List<Object[]> voting = constituencyElectionResultDAO.findTotalVotersAndValidVotesByYearAndConstituencyIds(conList, electionYear);
					constiInfo.setVotingPercentage(new BigDecimal((Double)voting.get(0)[1]*(100.0)/(Double)voting.get(0)[0]).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					for(PartyResultsVO partyInfo:constiInfo.getPartyResultsVO()){
						if(pariisMap.get(partyInfo.getPartyName()) == null || partyInfo.getPartyName().equalsIgnoreCase("IND"))
							continue;
						else
							partyResultsVOList.add(partyInfo);
							
						partiesInConstituency.add(partyInfo.getPartyName());
						allParties.add(partyInfo.getPartyName());
					}
					constiInfo.setParticipatedParties(partiesInConstituency);
					constiInfo.setPartyResultsVO(partyResultsVOList);
				}
				
				PartyResultsVO partyInfo = null;
				for(ConstituencyElectionResultsVO constiInfo:constituenciesResults)
					for(String partyName:allParties)
						if(!constiInfo.getParticipatedParties().contains(partyName)){
							partyInfo = new PartyResultsVO();
							partyInfo.setPartyName(partyName);
							constiInfo.getPartyResultsVO().add(partyInfo);
						}
				
				for(ConstituencyElectionResultsVO constiInfo:constituenciesResults)
					Collections.sort(constiInfo.getPartyResultsVO(), new PartyResultsVOComparator());
				
				allPartiesList = new ArrayList<String>(allParties);
				Collections.sort(allPartiesList);
				
				//Setting Census Structure Percentages For Each Constituency
				List<Object[]> list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentagesOfADistrict(getCensusStructureBySelectedIndex(selected),constituencyIds);
				
				for(Object[] obj:list)
					constituencyWithPercentMap.put((Long)obj[0], (Double)obj[1]);
				
				for(ConstituencyElectionResultsVO constiInfo:constituenciesResults)
					constiInfo.setCensusReportPercent(constituencyWithPercentMap.get(constiInfo.getConstituencyId()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		electionDataVO.setDistricts(districts);
		electionDataVO.setParties(parties);
		electionDataVO.setConstituenciesResults(constituenciesResults);
		electionDataVO.setAllPartiesList(allPartiesList);
		electionDataVO.setPartyResultsList(partyResultsList);
		
		return electionDataVO;
	}

	private void getDistrictsAndParties(Set<SelectOptionVO> districts,
			Set<SelectOptionVO> parties, List<Long> constituencyIds,
			String electionYear) {
		List resultsList = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies(electionYear,constituencyIds,IConstants.VOTES_PERCENTAGE_CONCERNED);
		for(Object[] values:(List<Object[]>)resultsList){
			districts.add(new SelectOptionVO((Long)values[8], values[9].toString()));
			parties.add(new SelectOptionVO((Long)values[3], values[2].toString()));
		}
		
	}
	
	public ResultWithExceptionVO getPartywiseConstituenciesResultsForCensusInfo(Integer selectIndex,Long stateId,Long districtId,
			Long year,String level, Long partyId){
		ResultWithExceptionVO resultWithExceptionVO = new ResultWithExceptionVO();
		List<CensusVO> censusByPercent = getConstituencyCensusDetails(selectIndex, stateId, districtId, year, level);
		List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(partyId);
		ElectionDataVO electionDataVO = null;
		
		for(CensusVO census:censusByPercent){
			census.setPartyName(partyDAO.get(partyId).getShortName());
			if(census.getLocationIds().size() == 0)
				continue;
			electionDataVO = findAssemblyConstituenciesResultsByConstituencyIds(year.toString(), census.getLocationIds(), partyIds, 
					new ArrayList<Long>(), selectIndex, false, false);
			if(electionDataVO.getPartyResultsList().size() == 1){
				census.setSeatsParticipated(new Long(electionDataVO.getPartyResultsList().get(0).getSeatsParticipated()));
				census.setSeatsWon(new Long(electionDataVO.getPartyResultsList().get(0).getTotalSeatsWon()));
				census.setAvgPercent(new BigDecimal(electionDataVO.getPartyResultsList().get(0).getAvgPercentage()));
				census.setPConstavgPercent(new BigDecimal(electionDataVO.getPartyResultsList().get(0).getPConstavgPercentage()));
			}
				
		}
		
		resultWithExceptionVO.setFinalResult(censusByPercent);
		return resultWithExceptionVO;
	}

	/**
	 * Finds Average Voting Percentage For List Of Constituencies 
	 * @param constituenciesIds
	 * @param year
	 * @return
	 */
	public String findAverageVotingPercentageInConstituenciesInAYear(List<Long> constituenciesIds, String year){
		try{
			if(constituenciesIds.size() == 0)
				return null;
			List<Object[]> validVotesAndVoters = constituencyElectionResultDAO.findTotalVotersAndValidVotesByYearAndConstituencyIds(constituenciesIds, year);
			Double totalVotes = (Double)validVotesAndVoters.get(0)[0];
			Double validVotes = (Double)validVotesAndVoters.get(0)[1];
			if(totalVotes > 0)
				return new BigDecimal(validVotes*100/totalVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "0.0";
	}
	
	public List<SelectOptionVO> getAllCensusParameters(){
		
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		List<CensusParameter> list = censusParameterDAO.getAll();
		
		for(CensusParameter censusParameter:list)
		{
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(censusParameter.getCensusParameterId());
			selectOption.setName(censusParameter.getParameterName());
			selectOptionVOList.add(selectOption);
		}
			return selectOptionVOList;
	}
	
	public List<CensusWisePartyResultsVO> findAllPartiesInfoByCensusRanges(Integer selectIndex,Long stateId,Long districtId,
			Long year,String level){
		List<CensusWisePartyResultsVO> censusInfo = new ArrayList<CensusWisePartyResultsVO>();
		List<SelectOptionVO> parties = staticDataService.getStaticPartiesListForAState(1l);//Parties In State with National Parties
		List<CensusVO> censusForParty = null;
		Map<String, List<PartyResultsVO>> censusInfoMap = new LinkedHashMap<String, List<PartyResultsVO>>();
		List<PartyResultsVO> partyResult = null;
		PartyResultsVO partyResultsVO = null;
		List<CensusVO> censusVOList = null;
		CensusWisePartyResultsVO censusWisePartyResultsVO = null;
		Map<String,Long> resultMap = null;
		
		for(SelectOptionVO party:parties){
			censusForParty = (List<CensusVO>)getPartywiseConstituenciesResultsForCensusInfo(selectIndex, stateId, districtId, year, level, party.getId()).getFinalResult();
			for(CensusVO censusVO:censusForParty){
				partyResultsVO = new PartyResultsVO();
				partyResultsVO.setPartyName(censusVO.getPartyName());
				partyResultsVO.setTotalSeatsWon(censusVO.getSeatsWon() != null?censusVO.getSeatsWon().intValue():null);
				partyResultsVO.setVotesPercent(censusVO.getAvgPercent());
				partyResultsVO.setPConstavgPercentage(censusVO.getPConstavgPercent()== null?null:censusVO.getPConstavgPercent().toString());
				partyResult = censusInfoMap.get(censusVO.getRange());
				if(partyResult == null)
					partyResult = new ArrayList<PartyResultsVO>();
				partyResult.add(partyResultsVO);
				censusInfoMap.put(censusVO.getRange(), partyResult);
			}
		}
		censusVOList = getConstituencyCensusDetails(selectIndex,stateId,districtId,year,level);
		resultMap = new LinkedHashMap<String,Long>(0);
		
		for(CensusVO censusVO:censusVOList)
		{
			resultMap.put(censusVO.getRange(),censusVO.getCount().longValue());
		}
		
		for(Map.Entry<String, List<PartyResultsVO>> entrySet:censusInfoMap.entrySet()){
			censusWisePartyResultsVO = new CensusWisePartyResultsVO();
			censusWisePartyResultsVO.setRange(entrySet.getKey());
			censusWisePartyResultsVO.setCount(resultMap.get(entrySet.getKey()));
			censusWisePartyResultsVO.setPartiesResults(entrySet.getValue());
			censusInfo.add(censusWisePartyResultsVO);
		}
		
		return censusInfo;
	}
	
	  public List<SelectOptionVO> getLatestElectionYearForAStateBasedOnElectionType(Long stateId, String electionType, String subType)
	  {
		try{
			  
		 List list = electionDAO.getLatestElectionYearForAStateBasedOnElectionType(stateId, electionType, subType);
		
		 if(list != null && list.size() > 0)
		 {
			 List<SelectOptionVO> selectList = new ArrayList<SelectOptionVO>(0);
			 SelectOptionVO selectOptionVO = null;
			 Long id = 0L;
			 for(int i=0;i<list.size();i++)
			 {
				 Object param = list.get(i);
				 selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setId(++id);
				 selectOptionVO.setName(param.toString());
				 selectList.add(selectOptionVO);
			 }
			 return selectList;
		 }
		 else 
			 return null;
		}catch(Exception e){
			return null;
		}
		  
	  }

	
}
