package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
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

	public List<CensusVO> getConstituencyCensusDetails(Integer selectIndex,Long stateId,Long districtId,Long year,String level)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the ElectionService.getConstituencyCensusDetails().. Call");
			}
		String censusStr = null;
		List<CensusVO> censusVOlist = new ArrayList<CensusVO>();
		List<Object[]>list = new ArrayList<Object[]>();
		
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
		else if(selectIndex == 6)
			censusStr = "model.nonWorkingPopPercentage";
		
		if(level.equalsIgnoreCase(IConstants.STATE_STR))
		{
			list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentages(censusStr,stateId);
		}
		
		if(level.equalsIgnoreCase(IConstants.DISTRICT_STR))
		{
			List<Long>constituencyIds = delimitationConstituencyDAO.getLatestConstituenciesForDistrictBasedOnYear(districtId,year);
			
			list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentagesOfADistrict(censusStr,constituencyIds);
		}
		
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
		for(Map.Entry<String,List<Long>> entry : resultMap.entrySet())
		{
			censusVO = new CensusVO();
			censusVO.setRange(entry.getKey());
			censusVO.setLocationIds(entry.getValue());
			censusVO.setCount(entry.getValue().size());
			
			censusVOlist.add(censusVO);
		}
			return censusVOlist;
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the ElectionService.getConstituencyCensusDetails().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
	  }
	
	/**
	 * Returns Party Wise Constituencies Grouped Results For An Election 
	 */
	public ElectionDataVO findAssemblyConstituenciesResultsByConstituencyIds(
			String electionYear, List<Long> constituencyIds, List<Long> partyIds, 
			List<Long> districtIds, Boolean isAll){
		
		ElectionDataVO electionDataVO = new ElectionDataVO();
		List<PartyResultsVO> partyResultsList = new ArrayList<PartyResultsVO>();
		List<ConstituencyElectionResultsVO> constituenciesResults = null;
		List<String> allPartiesList = null;
		Set<SelectOptionVO> districts = new HashSet<SelectOptionVO>();
		Set<SelectOptionVO> parties = new HashSet<SelectOptionVO>();
		List resultsList = null;
		StringBuilder query = new StringBuilder();
		try {
			
			getDistrictsAndParties(districts, parties, constituencyIds, electionYear);
			
			if(constituencyIds != null && constituencyIds.size() > 0 && isAll)
				resultsList = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies(electionYear,constituencyIds);
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
				
			Map<PartyResultsVO, List<Object[]>> partyWithResults = new LinkedHashMap<PartyResultsVO, List<Object[]>>();
			
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
			Double percentageTotals = 0.0;
			for(Map.Entry<PartyResultsVO, List<Object[]>> entry:partyWithResults.entrySet()){
				seatsWon = 0;
				partyResultsVO = entry.getKey();
				partyResults = entry.getValue();
				percentageTotals = 0.0;
				for(Object[] results:partyResults){
					percentageTotals += Double.parseDouble(results[0].toString());
					if("1".equalsIgnoreCase(results[7].toString()))
						seatsWon++;	
				}
				partyResultsVO.setSeatsParticipated(partyResults.size());
				partyResultsVO.setTotalSeatsWon(seatsWon);
				if(partyResults.size() > 0)
					partyResultsVO.setAvgPercentage(new BigDecimal(percentageTotals/partyResults.size()).
							setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				partyResultsList.add(partyResultsVO);
			}
			
			constituenciesResults = staticDataService.findAssemblyConstituenciesResultsByConstituencyIds(
					electionYear, constituencyIds, resultsList);
			Set<String> allParties = new HashSet<String>(0);
			Set<String> partiesInConstituency = null;
			for(ConstituencyElectionResultsVO constiInfo:constituenciesResults){
				partiesInConstituency = new HashSet<String>();
				for(PartyResultsVO partyInfo:constiInfo.getPartyResultsVO()){
					partiesInConstituency.add(partyInfo.getPartyName());
					allParties.add(partyInfo.getPartyName());
				}
				constiInfo.setParticipatedParties(partiesInConstituency);
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
			
		} catch (Exception e) {
			
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
		List resultsList = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies(electionYear,constituencyIds);
		for(Object[] values:(List<Object[]>)resultsList){
			districts.add(new SelectOptionVO((Long)values[8], values[9].toString()));
			parties.add(new SelectOptionVO((Long)values[3], values[2].toString()));
		}
	}

}
