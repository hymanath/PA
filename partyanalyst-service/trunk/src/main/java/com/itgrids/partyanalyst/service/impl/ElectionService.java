package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.ICensusParameterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.CensusWisePartyResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CensusParameter;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyCensusDetails;
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
	private IConstituencyDAO constituencyDAO;
	private ICensusDAO censusDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	
	private final static Logger log = Logger.getLogger(ElectionService.class);

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	public ICensusDAO getCensusDAO() {
		return censusDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}


	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

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
			List<Long> constituencyIds = delimitationConstituencyDAO.getLatestConstituenciesForDistrictBasedOnYear(districtId,year);
			if(constituencyIds.size()>0 )
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
		
		if(censusByPercent.size()>0)
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
			if(totalVotes != null && totalVotes > 0)
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

	  
	  public String roundTo2DigitsFloatValue(Float number){
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			return f.format(number);
			
		}
	  

		
	public CensusVO getCensusDetailsForAConstituency(Long constituencyId)
	{
		log.debug("Entered into the getCensusDetailsForAConstituency service method");
		CensusVO resultVO = new CensusVO();
		
		try
		{
			List<Long> years = new ArrayList<Long>();
			
			years.add(2001L);
			years.add(2011L);
		
			String stateName = null;
			String districtName = null;
			List<Object[]> stateInfo=stateDAO.getStateDetailsByconstituencyId(constituencyId);			
			List<Object[]> districtInfo=districtDAO.getDistrictIdAndNameByConstituency(constituencyId);
			
			for (Object[] parms : stateInfo) {
				stateName = parms[1].toString();
			}
			
			for (Object[] parms : districtInfo) {
				districtName = parms[1].toString();
			}
			
			List<ConstituencyCensusDetails> censusDetailsList = constituencyCensusDetailsDAO
					.getCensusConstituencyByConstituencyIdAndYears(constituencyId, years);
					
			resultVO.setCount(Integer.valueOf(censusDetailsList.size()));			
			resultVO.setStateName(stateInfo.size() != 0 ?stateName:"");
			resultVO.setDistrictName(districtInfo.size()>0 ? districtName:"");
								
			
			List<CensusVO> censusList = new ArrayList<CensusVO>();
			
			for(ConstituencyCensusDetails details:censusDetailsList)
			{

					CensusVO censusDetailsVO = new CensusVO();				
					
					censusDetailsVO.setYear(Integer.parseInt(details.getYear().toString()!= null ? details.getYear().toString():"0"));
					censusDetailsVO.setTotalPopulation(details.getTotalPopulation());
					censusDetailsVO.setTotalPopulationPercentage("".toString());
					
					
					censusDetailsVO.setMalePopulation(details.getTotalMalePopulation());
					censusDetailsVO.setFemalePopulation(details.getTotalFemalePopulation());				
					censusDetailsVO.setMalePopulationPercentage(roundTo2DigitsFloatValue((float)details.getTotalMalePopulation()*100f/details.getTotalPopulation()));
					censusDetailsVO.setFemalePopulationPercentage(roundTo2DigitsFloatValue((float)details.getTotalFemalePopulation()*100f/details.getTotalPopulation()));
					
					censusDetailsVO.setHouseHolds(details.getHouseHolds());
					censusDetailsVO.setHouseHoldsPercentage(details.getPopHHPercentage().toString()!=null?details.getPopHHPercentage().toString():"0.0");
					
					censusDetailsVO.setPopulationSC(details.getPopulationSC());
					censusDetailsVO.setPopulationSCPercent(BigDecimal.valueOf(details.getPercentageSC()));
					
					censusDetailsVO.setPopulationST(details.getPopulationST());
					censusDetailsVO.setPopulationSTPercent(BigDecimal.valueOf(details.getPercentageST()));
					
					censusDetailsVO.setWorkingPeople(details.getWorkingPopulation());
					censusDetailsVO.setWorkingPeoplePercentage(details.getTotalWorkingPopPercentage().toString()!= null ? details.getTotalWorkingPopPercentage().toString():"0.0");
					
					censusDetailsVO.setWorkingMale(details.getWorkingMale());
					censusDetailsVO.setTotalWorkingMalePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getWorkingMale()*100f/details.getWorkingPopulation())));
					
					censusDetailsVO.setWorkingFemale(details.getWorkingFemale());
					censusDetailsVO.setTotalWorkingFemalePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getWorkingFemale()*100f/details.getWorkingPopulation())));
					
					censusDetailsVO.setNonWorkingPeople(details.getNonWorkingPopulation());
					censusDetailsVO.setNonWorkingPeoplePercent(BigDecimal.valueOf(100-details.getTotalWorkingPopPercentage()));
					
					censusDetailsVO.setPopulationUnderSix(details.getPopulationUnderSix());
					censusDetailsVO.setPopulationUnderSixPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getPopulationUnderSix()*100f/censusDetailsVO.getTotalPopulation()).toString());
					
					censusDetailsVO.setLiterates(details.getPopulationLiterates());
					censusDetailsVO.setLiteratesPercentage(roundTo2DigitsFloatValue((float)censusDetailsVO.getLiterates()*100f/censusDetailsVO.getTotalPopulation()).toString());
					
					censusDetailsVO.setMaleLiterates(details.getMaleLiterates());
					censusDetailsVO.setMaleLiteraturePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getMaleLiterates()*100f/details.getPopulationLiterates())));
					
					censusDetailsVO.setFemaleLiterates(details.getFemaleLiterates());
					censusDetailsVO.setFemaleLiteraturePercentage(Double.parseDouble(roundTo2DigitsFloatValue((float)details.getFemaleLiterates()*100f/details.getPopulationLiterates())));
					
					censusList.add(censusDetailsVO);
					

			}
			
			resultVO.setCensusDetailsList(censusList);
			
			CensusVO previousDetails = censusList.get(0);
			CensusVO currentDetails = censusList.get(1);
			
			resultVO.setDifferencePopulation(currentDetails.getTotalPopulation() - previousDetails.getTotalPopulation());
			resultVO.setDifferencePopulationPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferencePopulation()*100f/previousDetails.getTotalPopulation()));
			
			resultVO.setDifferenceMalePopulation(currentDetails.getMalePopulation() - previousDetails.getMalePopulation());
			resultVO.setDifferenceMalePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceMalePopulation()*100f/previousDetails.getMalePopulation()));
			resultVO.setDifferenceFemalePopulation(currentDetails.getFemalePopulation() - previousDetails.getFemalePopulation());
			resultVO.setDifferenceFemalePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceFemalePopulation()*100f/previousDetails.getFemalePopulation()));
			
			resultVO.setDifferenceHouseHolds(currentDetails.getHouseHolds() - previousDetails.getHouseHolds());
			resultVO.setDifferenceHouseHoldsPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceHouseHolds()*100f/previousDetails.getHouseHolds()));
			
			resultVO.setDifferenceSC(currentDetails.getPopulationSC() - previousDetails.getPopulationSC());
			resultVO.setDifferenceSCPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceSC()*100f / previousDetails.getPopulationSC()));
			
			resultVO.setDifferenceST(currentDetails.getPopulationST() - previousDetails.getPopulationST());
			resultVO.setDifferenceSTPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceST()*100f/previousDetails.getPopulationST()));
			
			resultVO.setDifferenceWorkingPeople(currentDetails.getWorkingPeople() - previousDetails.getWorkingPeople());
			resultVO.setDifferenceWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceWorkingPeople()*100f / previousDetails.getWorkingPeople()));
			
			resultVO.setDifferenceMaleWorkingPeople(currentDetails.getWorkingMale() - previousDetails.getWorkingMale());
			resultVO.setDifferenceMaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceMaleWorkingPeople()*100f / previousDetails.getWorkingMale()));
			
			resultVO.setDifferenceFemaleWorkingPeople(currentDetails.getWorkingFemale() - previousDetails.getWorkingFemale());
			resultVO.setDifferenceFemaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceFemaleWorkingPeople()*100f / previousDetails.getWorkingFemale()));
			
			resultVO.setDifferenceNonWorkingPeople(currentDetails.getNonWorkingPeople() - previousDetails.getNonWorkingPeople());
			resultVO.setDifferenceNonWorkingPeoplePercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceNonWorkingPeople()*100f/previousDetails.getNonWorkingPeople()));
			
			resultVO.setDifferenceLessthan6Population(currentDetails.getPopulationUnderSix() - previousDetails.getPopulationUnderSix());
			resultVO.setDifferenceLessthan6Percent(roundTo2DigitsFloatValue((float) resultVO.getDifferenceLessthan6Population()*100f/ previousDetails.getPopulationUnderSix()));
			
			resultVO.setDifferenceLiterates(currentDetails.getLiterates() - previousDetails.getLiterates());
			resultVO.setDifferenceLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceLiterates() *100f/previousDetails.getLiterates()));
			
			resultVO.setDifferenceMaleLiterates(currentDetails.getMaleLiterates() - previousDetails.getMaleLiterates());
			resultVO.setDifferenceMaleLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceMaleLiterates()*100f/previousDetails.getMaleLiterates()));
			resultVO.setDifferenceFemaleLiterates(currentDetails.getFemaleLiterates() - previousDetails.getFemaleLiterates());
			resultVO.setDifferenceFemaleLiteratesPercent(roundTo2DigitsFloatValue((float)resultVO.getDifferenceFemaleLiterates() *100f /previousDetails.getFemaleLiterates()));
			
			Constituency constituency = constituencyDAO.get(constituencyId);
			
			Long stateId = constituency.getState().getStateId();
			Long districtId = constituency.getDistrict().getDistrictId();
			
			
			List<Object[]> districtCensusDetails = censusDAO.getDistrictPopulationForDifferentYears(districtId,years);
			
			List<Object[]> stateCensusDetails = censusDAO.getStatePopulationForDifferentYears(stateId,years);
			
			CensusVO districtVO = new CensusVO();
			CensusVO stateVO = new CensusVO();
			
			
			setValuesToCensusVO(districtCensusDetails.get(0),districtCensusDetails.get(1), districtVO);
			setValuesToCensusVO(stateCensusDetails.get(0),stateCensusDetails.get(1), stateVO);
			
			
			resultVO.setDistrictDetails(districtVO);
			resultVO.setStateDetails(stateVO);
			
			//List<ConstituencyCensusDetails> censusDetailsList = censusDAO.getCensusConstituencyForStateAndDistrict(constituencyId, years);
			
			
			
		}catch(Exception e)
		{
			log.error("Exception occured in the getCensusDetailsForAConstituency service method");
		}
		
		return resultVO;
	}
	
	
	public void setValuesToCensusVO(Object[] currentDetails , Object[] previousDetails,CensusVO censusDetailsVO)
	{
		log.error("Entered into the setValuesToCensusVO method electionservice class");
		//CensusVO censusDetailsVO = new CensusVO();
		try
		{

			censusDetailsVO.setDifferencePopulation((Long)currentDetails[0] - (Long)previousDetails[0]);
			censusDetailsVO.setDifferencePopulationPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferencePopulation()*100f/(Long)previousDetails[0]));
			
			censusDetailsVO.setDifferenceMalePopulation((Long)currentDetails[1] -(Long) previousDetails[1]);
			censusDetailsVO.setDifferenceMalePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceMalePopulation()*100f/(Long)previousDetails[1]));
			
			censusDetailsVO.setDifferenceFemalePopulation((Long)currentDetails[2] - (Long)previousDetails[2]);
			censusDetailsVO.setDifferenceFemalePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceFemalePopulation()*100f/(Long)previousDetails[2]));
			
			censusDetailsVO.setDifferenceHouseHolds((Long)currentDetails[3] - (Long)previousDetails[3]);
			censusDetailsVO.setDifferenceHouseHoldsPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceHouseHolds()*100f/(Long)previousDetails[3]));
			
			censusDetailsVO.setDifferenceSC((Long)currentDetails[4] - (Long)previousDetails[4]);
			censusDetailsVO.setDifferenceSCPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceSC()*100f / (Long)previousDetails[4]));
			
			censusDetailsVO.setDifferenceST((Long)currentDetails[5] - (Long)previousDetails[5]);
			censusDetailsVO.setDifferenceSTPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceST()*100f/(Long)previousDetails[5]));
			
			censusDetailsVO.setDifferenceWorkingPeople((Long)currentDetails[6] - (Long)previousDetails[6]);
			censusDetailsVO.setDifferenceWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceWorkingPeople()*100f / (Long)previousDetails[6]));
			
			censusDetailsVO.setDifferenceMaleWorkingPeople((Long)currentDetails[7] - (Long)previousDetails[7]);
			censusDetailsVO.setDifferenceMaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceMaleWorkingPeople()*100f / (Long)previousDetails[7]));
			
			censusDetailsVO.setDifferenceFemaleWorkingPeople((Long)currentDetails[8] - (Long)previousDetails[8]);
			censusDetailsVO.setDifferenceFemaleWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceFemaleWorkingPeople()*100f / (Long)previousDetails[8]));
			
			censusDetailsVO.setDifferenceNonWorkingPeople((Long)currentDetails[9] - (Long)previousDetails[9]);
			censusDetailsVO.setDifferenceNonWorkingPeoplePercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceNonWorkingPeople()*100f/ (Long)previousDetails[9]));
			
			censusDetailsVO.setDifferenceLessthan6Population((Long)currentDetails[10] - (Long)previousDetails[10]);
			censusDetailsVO.setDifferenceLessthan6Percent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceLessthan6Population()*100f/ (Long)previousDetails[10]));
			
			censusDetailsVO.setDifferenceLiterates((Long)currentDetails[11] - (Long)previousDetails[11]);
			censusDetailsVO.setDifferenceLiteratesPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceLiterates() *100f/(Long)previousDetails[11]));
			
			censusDetailsVO.setDifferenceMaleLiterates((Long)currentDetails[12] - (Long)previousDetails[12]);
			censusDetailsVO.setDifferenceMaleLiteratesPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceMaleLiterates()*100f/(Long)previousDetails[12]));
			
			censusDetailsVO.setDifferenceFemaleLiterates((Long)currentDetails[13] - (Long) previousDetails[13]);
			censusDetailsVO.setDifferenceFemaleLiteratesPercent(roundTo2DigitsFloatValue((float)censusDetailsVO.getDifferenceFemaleLiterates() *100f /(Long)previousDetails[13]));
			

			
		}catch(Exception e)
		{
			log.error("Exception Occured in the setValuesToCensusVO method electionservice class",e);
			
		}
	}
	
}
