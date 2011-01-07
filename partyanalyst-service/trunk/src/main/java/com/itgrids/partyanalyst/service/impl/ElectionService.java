package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.service.IElectionService;
import com.itgrids.partyanalyst.utils.ElectionYearsComparator;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionService implements IElectionService{
	
	private IElectionDAO electionDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IElectionTypeDAO electionTypeDAO;
	private INominationDAO nominationDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
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

	public List<CensusVO> getConstituencyCensusDetails(int selectIndex)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the ElectionService.getConstituencyCensusDetails().. Call");
			}
		String censusStr = null;
		List<CensusVO> censusVOlist = new ArrayList<CensusVO>();
		
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
		
		List<Object[]>list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentages(censusStr);
		
		Map<String,List<Long>> resultMap = new HashMap<String,List<Long>>();
		
		List<Long> idsList = null;
		
		for(Object[] obj:list)
		{
			Long constituencyId = (Long)obj[0];
			Double percentage = (Double)obj[1];
			
			if(percentage > 0 && percentage <= 10)
			{
				idsList = resultMap.get("0-10");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("0-10", idsList);
			}else if(percentage > 10 && percentage <= 20)
			{
				idsList = resultMap.get("10-20");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("10-20", idsList);
			}else if(percentage > 20 && percentage <= 30)
			{
				idsList = resultMap.get("20-30");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("20-30", idsList);
			}else if(percentage > 30 && percentage <= 40)
			{
				idsList = resultMap.get("30-10");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("30-10", idsList);
			}else if(percentage > 40 && percentage <= 50)
			{
				idsList = resultMap.get("40-10");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("40-50", idsList);
			}else if(percentage > 50 && percentage <= 60)
			{
				idsList = resultMap.get("50-60");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("50-60", idsList);
			}else if(percentage > 60 && percentage <= 70)
			{
				idsList = resultMap.get("60-70");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("60-70", idsList);
			}else if(percentage > 70 && percentage <= 80)
			{
				idsList = resultMap.get("70-80");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("70-80", idsList);
			}else if(percentage > 80 && percentage <= 90)
			{
				idsList = resultMap.get("80-90");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("80-90", idsList);
			}else if(percentage > 90 && percentage <= 100)
			{
				idsList = resultMap.get("90-100");
				if(idsList == null)
					idsList = new ArrayList<Long>();
				idsList.add(constituencyId);
				resultMap.put("90-100", idsList);
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

}
