package com.itgrids.partyanalyst.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.VillageCensusInfoVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.dto.enums.DelimitationConstituencyType;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyMandalService implements IDelimitationConstituencyMandalService{

	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IConstituencyDAO constituencyDAO;
	private ICensusDAO censusDAO;
	private ITownshipDAO townshipDAO;
	
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
 
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	@SuppressWarnings("unchecked")
	public DelimitationConstituencyMandalResultVO getMandalsForDelConstituency(Long constituencyID){
		if(log.isDebugEnabled())
			log.debug("DelimitationConstituencyMandalService.java--getMandalsForDelConstituency() ID::"+constituencyID);
		DelimitationConstituencyMandalResultVO resultVO = new DelimitationConstituencyMandalResultVO();
		try{
			resultVO.setConstituencyType(DelimitationConstituencyType.EXISTING_CONSTITUENCY);
			Constituency constituency = constituencyDAO.get(constituencyID);
			
			List<DelimitationConstituency> delimitationConstituencies = delimitationConstituencyDAO
					.findDelimitationConstituencyByConstituencyID(constituencyID);
			if(delimitationConstituencies.size()==0){
				if(log.isEnabledFor(Level.WARN))
					log.warn(constituency.getName() +" Constituency is not available in the delimitation_constituency table");
					resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					resultVO.setResultPartial(true);
					return resultVO;
			}
			DelimitationConstituency delimitationConstituency1 = delimitationConstituencies.get(0);
			Long dc1 = delimitationConstituency1.getDelimitationConstituencyID();
			resultVO.setDelimitationYear(delimitationConstituency1.getYear());
			
			List<DelimitationConstituencyMandal>  results = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(dc1);
			if(results.size()==0){
				if(log.isEnabledFor(Level.WARN))
					log.warn(dc1 +" delimitationConstituencyID is not available in the delimitation_constituency_mandal_details table");
					resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					resultVO.setResultPartial(true);
					return resultVO;
			}
			List<MandalInfoVO> currentResult = getMandalsForDelimitationConstituency(results);
			resultVO.setPresentMandals(currentResult);
			if(delimitationConstituencies.size()>1){
				resultVO.setConstituencyType(DelimitationConstituencyType.CHANGE_CONSTITUENCY);
				DelimitationConstituency delimitationConstituency2 = delimitationConstituencies.get(1);
				Long dc2 = delimitationConstituency2.getDelimitationConstituencyID();
				resultVO.setBeforeDelimitationYear(delimitationConstituency2.getYear());
				
				results = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(dc2);
				if(results.size()==0){
					if(log.isEnabledFor(Level.WARN))
						log.warn(dc2 +" delimitationConstituencyID is not available in the delimitation_constituency_mandal_details table");
						resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
						resultVO.setResultPartial(true);
						return resultVO;
				}
				currentResult = getMandalsForDelimitationConstituency(results);
				if(constituency.getDeformDate()!=null){
					resultVO.setConstituencyType(DelimitationConstituencyType.NON_EXISTING_CONSTITUENCY);
				}
				resultVO.setMandalsBeforeDelimitationConstituency(currentResult);
			}
		}catch(Exception ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);
		}
		return resultVO;
	}
	
	public List<MandalInfoVO> getMandalsForDelimitationConstituency(
			List<DelimitationConstituencyMandal> delimitationConstituencyMandalList){
		Map<Long, String> mandalNames = new HashMap<Long, String>();
		StringBuilder mandalIDs = new StringBuilder();
		for(DelimitationConstituencyMandal delimitationConstituencyMandal :
												delimitationConstituencyMandalList){
			Tehsil mandal = delimitationConstituencyMandal.getTehsil();
			mandalNames.put(mandal.getTehsilId(), mandal.getTehsilName());
			mandalIDs.append(",").append(mandal.getTehsilId());
		}
		String mandalsStr = mandalIDs.toString().substring(1);
		List<MandalInfoVO> mandalInfoList = getCensusInfoForMandals(mandalsStr);
		for(MandalInfoVO voObject : mandalInfoList){
			String name = mandalNames.get(voObject.getMandalID());
			voObject.setMandalName(name);
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
			convertCensusTOMandalInfo(mandalCensus, obj);
			mandalInfoList.add(obj);
		}
		if(log.isDebugEnabled())
			log.debug("service.getCensusInfoForMandals() end.........................."+mandalInfoList.size());
		return mandalInfoList;
	}
	
	public MandalInfoVO convertCensusTOMandalInfo(Census mandalCensus, MandalInfoVO obj){
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
		return obj;
	}
	
	public VillageDetailsVO getVillagesFormMandal(Long mandalID){
		VillageDetailsVO villageDetailsVO = new VillageDetailsVO();
		List<VillageCensusInfoVO> villageCensusList = new ArrayList<VillageCensusInfoVO>();
		try{
			List<Township> villages = townshipDAO.findByTehsilID(mandalID);
			if(log.isDebugEnabled()){
				log.debug("total villages available for the mandalID:"+mandalID+" is ::"+villages.size());
			}
			StringBuilder villageIDs = new StringBuilder();
			Map<Long,String> villageMap = new HashMap<Long, String>();
			for(Township township : villages){
				villageMap.put(township.getTownshipId(), township.getTownshipName());
				villageIDs.append(",").append(township.getTownshipId());
			}
			if(log.isDebugEnabled()){
				log.debug("total villages villageIDs ::"+villageIDs.toString());
			}
			
			List<Census> censusList = censusDAO.findByYearAndTownshipIDs(IConstants.CENSUS_YEAR,villageIDs.substring(1));
			if(log.isDebugEnabled()){
				log.debug("censusList villages available for the censusList ::"+censusList.size());
			}
			for(Census villageCensus : censusList){
				VillageCensusInfoVO villageInfo = new VillageCensusInfoVO();
				villageInfo.setTownshipName(villageMap.get(villageCensus.getTownshipId()));
				convertCencesToVillageInfo(villageCensus, villageInfo);
				villageCensusList.add(villageInfo);
				if(log.isDebugEnabled()){
					log.debug("List Village Name ::"+villageMap.get(villageCensus.getTownshipId()));
				}
			}
			villageDetailsVO.setVillageCensusList(villageCensusList);
		}catch(Exception ex){
			villageDetailsVO.setExceptionEncountered(ex);
			villageDetailsVO.setResultCode(ResultCodeMapper.FAILURE);
			villageDetailsVO.setResultPartial(true);
		}
		
		return villageDetailsVO;
	}
	public void convertCencesToVillageInfo(Census villageCensus, VillageCensusInfoVO obj){
		Long villageID = villageCensus.getTownshipId();
		
		obj.setTownshipID(villageID);
		
		obj.setTotalPersons(villageCensus.getTotalPopulation());
		obj.setTotalMalePersons(villageCensus.getTotalMalePopulation());
		obj.setTotalFemalePersons(villageCensus.getTotalFemalePopulation());
		
		obj.setTotalSCPersons(villageCensus.getPopulationSC());
		obj.setTotalSCFemalePersons(villageCensus.getFemaleSC());
		obj.setTotalSCMalePersons(villageCensus.getMaleSC());

		obj.setTotalSTPersons(villageCensus.getPopulationST());
		obj.setTotalSTFemalePersons(villageCensus.getFemaleST());
		obj.setTotalSTMalePersons(villageCensus.getMaleST());

		obj.setTotalLiteratePersons(villageCensus.getPopulationLiterates());
		obj.setTotalLiterateFemalePersons(villageCensus.getFemaleLiterates());
		obj.setTotalLiterateMalePersons(villageCensus.getMaleLiterates());

		obj.setTotalIlliteratePersons(villageCensus.getPopulationIlliterates());
		obj.setTotalIlliterateFemalePersons(villageCensus.getFemaleIlliterates());
		obj.setTotalIlliterateMalePersons(villageCensus.getMaleIlliterates());


		obj.setTotalWorkingPersons(villageCensus.getWorkingPopulation());
		obj.setTotalWorkingFemalePersons(villageCensus.getWorkingFemale());
		obj.setTotalWorkingMalePersons(villageCensus.getWorkingMale());
	}
}
