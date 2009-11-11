package com.itgrids.partyanalyst.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.enums.DelimitationConstituencyType;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyMandalService implements IDelimitationConstituencyMandalService{

	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IConstituencyDAO constituencyDAO;
	private ICensusDAO censusDAO;
	
	private static final Logger log = Logger.getLogger(DelimitationConstituencyMandalService.class);

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO){
		this.censusDAO = censusDAO;
	}

	@SuppressWarnings("unchecked")
	public DelimitationConstituencyMandalResultVO getMandalsForDelConstituency(Long constituencyID){
		if(log.isDebugEnabled())
			log.debug("DelimitationConstituencyMandalService.java--getMandalsForDelConstituency() ID::"+constituencyID);
		DelimitationConstituencyMandalResultVO resultVO = new DelimitationConstituencyMandalResultVO();
		resultVO.setConstituencyType(DelimitationConstituencyType.EXISTING_CONSTITUENCY);
		Constituency constituency = constituencyDAO.get(constituencyID);
		
		List<DelimitationConstituency> delimitationConstituencies = delimitationConstituencyDAO
				.findDelimitationConstituencyByConstituencyID(constituencyID);
		
		DelimitationConstituency delimitationConstituency1 = delimitationConstituencies.get(0);
		Long dc1 = delimitationConstituency1.getDelimitationConstituencyID();
		resultVO.setDelimitationYear(delimitationConstituency1.getYear());
		
		List<DelimitationConstituencyMandal>  results = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(dc1);
		
		List<MandalInfoVO> currentResult = getMandalsForDelimitationConstituency(results);
		resultVO.setPresentMandals(currentResult);
		if(delimitationConstituencies.size()>1){
			resultVO.setConstituencyType(DelimitationConstituencyType.CHANGE_CONSTITUENCY);
			DelimitationConstituency delimitationConstituency2 = delimitationConstituencies.get(1);
			Long dc2 = delimitationConstituency2.getDelimitationConstituencyID();
			resultVO.setBeforeDelimitationYear(delimitationConstituency2.getYear());
			
			results = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(dc2);
			
			currentResult = getMandalsForDelimitationConstituency(results);
			if(constituency.getDeformDate()!=null){
				resultVO.setConstituencyType(DelimitationConstituencyType.NON_EXISTING_CONSTITUENCY);
			}
			resultVO.setMandalsBeforeDelimitationConstituency(currentResult);
		}
		
		return resultVO;
	}
	
	public List<MandalInfoVO> getMandalsForDelimitationConstituency(List<DelimitationConstituencyMandal> delimitationConstituencyMandalList){
		Map<Long, String> mandalNames = new HashMap<Long, String>();
		StringBuilder mandalIDs = new StringBuilder();
		for(DelimitationConstituencyMandal delimitationConstituencyMandal : delimitationConstituencyMandalList){
			Tehsil mandal = delimitationConstituencyMandal.getTehsil();
			mandalNames.put(mandal.getTehsilId(), mandal.getTehsilName());
			mandalIDs.append(",").append(mandal.getTehsilId());
		}
		String mandalsStr = mandalIDs.toString().substring(1);
		List<Census> mandalsCensus = censusDAO.findByYearAndTehsilIDs(IConstants.CENSUS_YEAR, mandalsStr);
		List<MandalInfoVO> mandalInfoList = new ArrayList<MandalInfoVO>();
		for(Census mandalCensus : mandalsCensus){
			MandalInfoVO obj = new MandalInfoVO();
			Long tehsilID = mandalCensus.getTehsilId();
			String name = mandalNames.get(tehsilID);
			
			obj.setMandalID(tehsilID);
			obj.setMandalName(name);
			
			obj.setTotalPersons(mandalCensus.getTotalPopulation());
			obj.setTotalMalePersons(mandalCensus.getTotalMalePopulation());
			obj.setTotalFemalePersons(mandalCensus.getTotalFemalePopulation());
			
			obj.setTotalSCPersons(mandalCensus.getPopulationSC());
			obj.setTotalSCFemalePersons(mandalCensus.getFemaleSC());
			obj.setTotalSCMalePersons(mandalCensus.getMaleSC());

			obj.setTotalSTPersons(mandalCensus.getPopulationST());
			obj.setTotalSTFemalePersons(mandalCensus.getFemaleST());
			obj.setTotalSTMalePersons(mandalCensus.getMaleST());

			obj.setTotalLiteratePersons(mandalCensus.getPopulationLiterates());
			obj.setTotalLiterateFemalePersons(mandalCensus.getFemaleLiterates());
			obj.setTotalLiterateMalePersons(mandalCensus.getMaleLiterates());

			obj.setTotalIlliteratePersons(mandalCensus.getPopulationIlliterates());
			obj.setTotalIlliterateFemalePersons(mandalCensus.getFemaleIlliterates());
			obj.setTotalIlliterateMalePersons(mandalCensus.getMaleIlliterates());


			obj.setTotalWorkingPersons(mandalCensus.getWorkingPopulation());
			obj.setTotalWorkingFemalePersons(mandalCensus.getWorkingFemale());
			obj.setTotalWorkingMalePersons(mandalCensus.getWorkingMale());
			mandalInfoList.add(obj);
			
		}
		return mandalInfoList;
	}
	
	public List<MandalInfoVO> getCensusInfoForMandals(String mandalIDs){
		if(log.isDebugEnabled())
			log.debug("service.getCensusInfoForMandals() started..........................");
		List<Census> mandalsCensus = censusDAO.findByYearAndTehsilIDs(IConstants.CENSUS_YEAR, mandalIDs);
		List<MandalInfoVO> mandalInfoList = new ArrayList<MandalInfoVO>();
		for(Census mandalCensus : mandalsCensus){
			MandalInfoVO obj = new MandalInfoVO();
			Long tehsilID = mandalCensus.getTehsilId();
			
			obj.setMandalID(tehsilID);
			
			obj.setTotalPersons(mandalCensus.getTotalPopulation());
			obj.setTotalMalePersons(mandalCensus.getTotalMalePopulation());
			obj.setTotalFemalePersons(mandalCensus.getTotalFemalePopulation());
			
			obj.setTotalSCPersons(mandalCensus.getPopulationSC());
			obj.setTotalSCFemalePersons(mandalCensus.getFemaleSC());
			obj.setTotalSCMalePersons(mandalCensus.getMaleSC());

			obj.setTotalSTPersons(mandalCensus.getPopulationST());
			obj.setTotalSTFemalePersons(mandalCensus.getFemaleST());
			obj.setTotalSTMalePersons(mandalCensus.getMaleST());

			obj.setTotalLiteratePersons(mandalCensus.getPopulationLiterates());
			obj.setTotalLiterateFemalePersons(mandalCensus.getFemaleLiterates());
			obj.setTotalLiterateMalePersons(mandalCensus.getMaleLiterates());

			obj.setTotalIlliteratePersons(mandalCensus.getPopulationIlliterates());
			obj.setTotalIlliterateFemalePersons(mandalCensus.getFemaleIlliterates());
			obj.setTotalIlliterateMalePersons(mandalCensus.getMaleIlliterates());


			obj.setTotalWorkingPersons(mandalCensus.getWorkingPopulation());
			obj.setTotalWorkingFemalePersons(mandalCensus.getWorkingFemale());
			obj.setTotalWorkingMalePersons(mandalCensus.getWorkingMale());
			mandalInfoList.add(obj);
			
		}
		if(log.isDebugEnabled())
			log.debug("service.getCensusInfoForMandals() end.........................."+mandalInfoList.size());
		return mandalInfoList;
	}
}
