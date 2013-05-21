package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dto.DataVerificationInfoVO;
import com.itgrids.partyanalyst.dto.DataVerificationVO;
import com.itgrids.partyanalyst.dto.ElectionResultsVerificationInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultsVerificationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.DataValidationVO;
import com.itgrids.partyanalyst.service.IDataValidationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DataValidationService implements IDataValidationService{
	private static final Logger LOG = Logger.getLogger(DataValidationService.class);
	
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IPanchayatHamletDAO panchayatHamletDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothDAO boothDAO;
	private IVotersAnalysisService votersAnalysisService;
	private ITehsilDAO tehsilDAO;
	private IPanchayatDAO panchayatDAO;
	private IVoterFamilyInfoDAO voterFamilyInfoDAO;
	private IVoterFamilyRangeDAO voterFamilyRangeDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private IVoterAgeRangeDAO voterAgeRangeDAO;
	private IVoterModificationInfoDAO voterModificationInfoDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	
	public IVoterFamilyInfoDAO getVoterFamilyInfoDAO() {
		return voterFamilyInfoDAO;
	}

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	public IVoterFamilyRangeDAO getVoterFamilyRangeDAO() {
		return voterFamilyRangeDAO;
	}

	public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
		this.voterFamilyRangeDAO = voterFamilyRangeDAO;
	}

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}

	public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
		return voterModificationInfoDAO;
	}

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}
	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}

	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	public IVoterModificationAgeInfoDAO getVoterModificationAgeInfoDAO() {
		return voterModificationAgeInfoDAO;
	}

	public void setVoterModificationAgeInfoDAO(
			IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
		this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public static Comparator<DataValidationVO> sortData = new Comparator<DataValidationVO>()
    {
   
        public int compare(DataValidationVO resultList1, DataValidationVO resultList2)
        {
            return (resultList1.getTehsilName()).compareTo(resultList2.getTehsilName());
        	
        }
    };
    
    public List<DataValidationVO> getHamletsAssignedValidation(Long constituencyId,Long publicationDateId,Long userId)
	{
		List<DataValidationVO> result = new ArrayList<DataValidationVO>();
		List<Long> panchayatsList = new ArrayList<Long>(0);
		try{
			List<Object[]> list = voterInfoDAO.getPanchayatWiseVotersCount(constituencyId, publicationDateId);
			if(list != null && list.size() > 0)
			{
				DataValidationVO dataValidationVO = null;
				for(Object[] params : list)
				{
					dataValidationVO = new DataValidationVO();
					dataValidationVO.setTehsilId((Long)params[0]);
					dataValidationVO.setTehsilName(params[1].toString());
					dataValidationVO.setPanchayatId((Long)params[2]);
					dataValidationVO.setPanchayatName(params[3].toString());
					dataValidationVO.setTotalVoters((Long)params[4]);
					panchayatsList.add(dataValidationVO.getPanchayatId());
					result.add(dataValidationVO);
				}
				
				List<Object[]> panchayatsHamletsList = panchayatHamletDAO.getHamletsByPanchayatsList(panchayatsList);
				
				for(Object[] params : panchayatsHamletsList)
				{
					DataValidationVO dataValidationVO2 = getDataValidationVOFromList(result,(Long)params[0]);
					List<SelectOptionVO> hamletsList = dataValidationVO2.getHamletsList();
					hamletsList.add(new SelectOptionVO((Long)params[2],params[3].toString()));
					dataValidationVO2.setHamletsList(hamletsList);
				}
				
				List<Object[]> villageResult = userVoterDetailsDAO.getPanchayatWiseHamletsAssignedDetails(constituencyId,publicationDateId,userId);
				SelectOptionVO selectOptionVO = null;
				
				for(Object[] params : villageResult)
				{
					DataValidationVO validationVO = getDataValidationVOFromList(result,(Long)params[0]);
					if(validationVO == null)
						continue;
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[2]);
					selectOptionVO.setName(params[3].toString());
					selectOptionVO.setPopulateId((Long)params[4]);
					
					List<SelectOptionVO> assignedHamletsList = validationVO.getAssignedHamletsList();
					assignedHamletsList.add(selectOptionVO);
					validationVO.setAssignedHamletsList(assignedHamletsList);
					
					List<Long> assignedHamletsIdsList = validationVO.getAssignedHamletsIdsList();
					assignedHamletsIdsList.add((Long)params[2]);
					validationVO.setAssignedHamletsIdsList(assignedHamletsIdsList);
					
					validationVO.setHamletAssignedVoters(validationVO.getHamletAssignedVoters()+(Long)params[4]);
				}
				for(DataValidationVO validationVO2 : result)
				{
					validationVO2.setHamletsNotAssignedVoters(validationVO2.getTotalVoters()-validationVO2.getHamletAssignedVoters());
					for(SelectOptionVO optionVO : validationVO2.getHamletsList())
						if(!validationVO2.getAssignedHamletsIdsList().contains(optionVO.getId()))
						{
							List<SelectOptionVO> notAssignedHamletsList = validationVO2.getNotAssignedHamletsList();
							notAssignedHamletsList.add(optionVO);
							validationVO2.setNotAssignedHamletsList(notAssignedHamletsList);
						}
							
				}
			}
			return result;
		}catch (Exception e) {
			LOG.error("Exception occured in getHamletsAssignedValidation() method ",e);
			return result;
		}
	}
	
    public DataValidationVO getDataValidationVOFromList(List<DataValidationVO> result,Long panchayatId)
    {
    	try{
    		for(DataValidationVO dataValidationVO : result)
    			if(dataValidationVO.getPanchayatId().equals(panchayatId))
    				return dataValidationVO;
    		return null;
    	}catch (Exception e) {
    		LOG.error("Exception Ocuured in getDataValidationVOFromList()");
    		return null;
    	}
    }
    
    public DataVerificationVO validateVotersBasicInfo(Long constituencyId,Long publicationId,Long userId)
    {
    	DataVerificationVO dataVerificationVO = null;
    	try{
    		
    		List<Long> constituencyList = new ArrayList<Long>(0);
    		constituencyList.add(constituencyId);
    		
    		dataVerificationVO = new DataVerificationVO();
    		String areaType = constituencyDAO.get(constituencyId).getAreaType();
    		dataVerificationVO.setAreaType(areaType);
    		
    		dataVerificationVO.setConstituencyIdsList(constituencyList);
    		dataVerificationVO.setTotalConstituencies(new Long(constituencyList.size()));
    		
    		if(areaType.equalsIgnoreCase(IConstants.RURAL) || areaType.equalsIgnoreCase(IConstants.RURALURBAN))
    		{
    			List<Long> mandalIdsList = boothDAO.getMandalsListByConstituencyId(constituencyId, publicationId);
    			List<Long> panchayatList = boothDAO.getPanchayatsListByConstituencyId(constituencyId, publicationId);
    			
    			if(mandalIdsList != null && mandalIdsList.size() > 0)
    				dataVerificationVO.setTotalmandals(new Long(mandalIdsList.size()));
    			
    			if(panchayatList != null && panchayatList.size() > 0)
    				dataVerificationVO.setTotalPanchayats(new Long(panchayatList.size()));
    			
    			dataVerificationVO.setMandalIdsList(mandalIdsList);
    			dataVerificationVO.setPanchayatIdsList(panchayatList);
    			
    		}
    		if(areaType.equalsIgnoreCase(IConstants.URBAN) || areaType.equalsIgnoreCase(IConstants.RURALURBAN))
    		{
    			List<Long> muncipalitiesList = boothDAO.getMuncipalitiesListByConstituencyId(constituencyId, publicationId);
    			List<Long> wardsList = boothDAO.getWardsListByConstituencyId(constituencyId, publicationId);
    			
    			if(muncipalitiesList != null && muncipalitiesList.size() > 0)
    				dataVerificationVO.setTotalMuncipalities(new Long(muncipalitiesList.size()));
    			
    			if(wardsList != null && wardsList.size() > 0)
    				dataVerificationVO.setTotalWards(new Long(wardsList.size()));
    			
    			dataVerificationVO.setMuncipalityIdsList(muncipalitiesList);
    			dataVerificationVO.setWardIdsList(wardsList);
    			
    		}
    		
    		List<Long> boothsList = boothDAO.getBoothsListByConstituencyId(constituencyId, publicationId);
    		
    		dataVerificationVO.setBoothIdsList(boothsList);
    		if(boothsList != null && boothsList.size() > 0)
    		  dataVerificationVO.setTotalBooths(new Long(boothsList.size()));
    		
    		dataVerificationVO.setConstituencyId(constituencyId);
    		dataVerificationVO.setPublicationId(publicationId);
    		
    		checkVotersDataInVoterInfoTable(dataVerificationVO);
    		
    		return dataVerificationVO;
    	}catch (Exception e) {
    		LOG.error("Exception Occured in validateVotersBasicInfo() method, Exception - "+e);
    		return dataVerificationVO;
		}
    }
    
    
    public void checkVotersDataInVoterInfoTable(DataVerificationVO dataVerificationVO)
    {
    	try{
    		
    		 checkConstituencyWiseVotersData(dataVerificationVO);
    		 checkConstituencyWiseVotersFamilyData(dataVerificationVO);
    		 checkConstituencyWiseVotersAgeData(dataVerificationVO);
    		 checkConstituencyWiseVoterModificationData(dataVerificationVO);
    		 validateVoterModificationAgeData(dataVerificationVO);
    		 
    		if(dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.RURAL) || dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
    		{
    			checkMandalWiseVotersData(dataVerificationVO);
    			checkPanchayatWiseVotersData(dataVerificationVO);
    			
    			checkMandalWiseVotersFamilyData(dataVerificationVO);
    			checkPanchayatWiseVotersFamilyData(dataVerificationVO);
    			
    			checkMandalWiseVotersAgeData(dataVerificationVO);
    			checkPanchayatWiseVotersAgeData(dataVerificationVO);
    			
    			checkMandalWiseVoterModificationData(dataVerificationVO);
    			checkPanchayatWiseVoterModificationData(dataVerificationVO);
    			
    		}
    		
    		if(dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.URBAN) || dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
    		{
    			checkMuncipalityWiseVotersData(dataVerificationVO);
    			checkWardWiseVotersData(dataVerificationVO);
    			
    			checkMuncipalityWiseVotersFamilyData(dataVerificationVO);
    			checkWardWiseVotersFamilyData(dataVerificationVO);
    			
    			checkMuncipalityWiseVotersAgeData(dataVerificationVO);
    			checkWardWiseVotersAgeData(dataVerificationVO);
    			
    			checkMuncipalityWiseVoterModificationData(dataVerificationVO);
    			checkWardWiseVoterModificationData(dataVerificationVO);
    		}
    		
    		checkBoothWiseVotersData(dataVerificationVO);
    		checkBoothWiseVotersFamilyData(dataVerificationVO);
    		checkBoothWiseVotersAgeData(dataVerificationVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkVotersDataInVoterInfoTable() method, Exception - "+e);
		}
    }
    
    public void checkConstituencyWiseVotersData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		 DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getVoterInfo();
    		 List<Long> constituencyList = voterInfoDAO.getReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY));
    		 setConstituencyVoterData(dataVerificationVO, dataVerificationInfoVO, constituencyList);
    		
    		 dataVerificationVO.setVoterInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkConstituencyWiseVotersData() method, Exception - "+e);
		}
    }
    
    public void setConstituencyVoterData(DataVerificationVO dataVerificationVO, DataVerificationInfoVO dataVerificationInfoVO, List<Long> constituencyList)
    {
    	try{
    		
    		List<Long> missedConstituencyIdsList = new ArrayList<Long>(0);
    		List<Long> repeatedConstituencyIdsList = new ArrayList<Long>(0);
    		if(constituencyList != null && constituencyList.size() > 0)
  		    {
  			if(dataVerificationVO.getTotalConstituencies()!= null)
  			{
  			   for(Long constituencyId : dataVerificationVO.getConstituencyIdsList())
  				if(!constituencyList.contains(constituencyId))
  					missedConstituencyIdsList.add(constituencyId);
  			   
  			   for(Long constituencyId : constituencyList)
  				 if(!dataVerificationVO.getConstituencyIdsList().contains(constituencyId))
  					repeatedConstituencyIdsList.add(constituencyId); 
  			 }
  			dataVerificationInfoVO.setSavedConstituencyCount(new Long(constituencyList.size()));
  			
  		  }
  		  else
  			missedConstituencyIdsList.addAll(dataVerificationVO.getConstituencyIdsList());
    		
    		if(missedConstituencyIdsList != null && missedConstituencyIdsList.size() > 0)
    		  {
    			  dataVerificationInfoVO.setMissedConstituencyCount(new Long(missedConstituencyIdsList.size()));
    			  List<Object[]> missedConstituencyList = constituencyDAO.getConstituencyNameByConstituencyIdsList(missedConstituencyIdsList);
    			  dataVerificationInfoVO.setMissedConstituencyList(getLocationNameAndIds(missedConstituencyList)) ;
    		  }
    		  
    		  if(repeatedConstituencyIdsList != null && repeatedConstituencyIdsList.size() > 0)
    		  {
    			  dataVerificationInfoVO.setRepeatedConstituencyCount(new Long(repeatedConstituencyIdsList.size()));
    			  List<SelectOptionVO> selectOptionVOsList = new ArrayList<SelectOptionVO>(0);
    			  for(Long constituencyId : repeatedConstituencyIdsList)
    			  {
    				  if(constituencyDAO.getConstituencyNameByConstituencyId(constituencyId) == null)
    					  selectOptionVOsList.add(new SelectOptionVO(constituencyId,"unKnow"));
    				  else
    					  selectOptionVOsList.add(new SelectOptionVO(constituencyId,constituencyDAO.get(constituencyId).getName())); 
    			  }
    			  dataVerificationInfoVO.setRepeatedConstituencyList(selectOptionVOsList);
    			  
    			  //List<Object[]> repeatedConstituencyList = constituencyDAO.getConstituencyNameByConstituencyIdsList(repeatedConstituencyIdsList);
    			  //dataVerificationInfoVO.setRepeatedConstituencyList(getLocationNameAndIds(repeatedConstituencyList));
    		  }
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in setConstituencyVoterData() method, Exception - "+e);
		}
    }
    
    
    public void checkMandalWiseVotersData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getVoterInfo();
    		
    		List<Long> mandalList = voterInfoDAO.getReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.MANDAL));
    		setMandalsVoterData(dataVerificationVO, dataVerificationInfoVO, mandalList);
    		
    		dataVerificationVO.setVoterInfo(dataVerificationInfoVO);
    			  
    	}catch (Exception e) {
    		e.printStackTrace();
    		LOG.error("Exception Occured in checkMandalWiseVotersData() method,Exception - "+e);
		}
    }
    
    public void setMandalsVoterData(DataVerificationVO dataVerificationVO,DataVerificationInfoVO dataVerificationInfoVO,List<Long> mandalList)
    {
    	try{
    		List<Long> missedMandalIdsList = new ArrayList<Long>(0);
    		List<Long> repeatedMandalIdsList = new ArrayList<Long>(0);
    		if(mandalList != null && mandalList.size() > 0)
  		  {
  			if(dataVerificationVO.getTotalmandals()!= null)
  			{
  			   for(Long mandalId : dataVerificationVO.getMandalIdsList())
  				if(!mandalList.contains(mandalId))
  				  missedMandalIdsList.add(mandalId);
  			   
  			   for(Long mandalId : mandalList)
  				 if(!dataVerificationVO.getMandalIdsList().contains(mandalId))
  					 repeatedMandalIdsList.add(mandalId); 
  			 }
  			dataVerificationInfoVO.setSavedMandalsCount(new Long(mandalList.size()));
  			
  		  }
  		  else
  		   missedMandalIdsList.addAll(dataVerificationVO.getMandalIdsList());
  		  
  		  if(missedMandalIdsList != null && missedMandalIdsList.size() > 0)
  		  {
  			  dataVerificationInfoVO.setMissedMandalsCount(new Long(missedMandalIdsList.size()));
  			  List<Object[]> missedMandalList = tehsilDAO.getTehsilNameByTehsilIdsList(missedMandalIdsList);
  			  dataVerificationInfoVO.setMissedMandalList(getLocationNameAndIds(missedMandalList)) ;
  		  }
  		  
  		  if(repeatedMandalIdsList != null && repeatedMandalIdsList.size() > 0)
  		  {
  			  dataVerificationInfoVO.setRepeatedMandalsCount(new Long(repeatedMandalIdsList.size()));
  			  List<SelectOptionVO> extraMandals = new ArrayList<SelectOptionVO>(0);
  			  for(Long id:repeatedMandalIdsList)
  			  {
  				  if(tehsilDAO.getTehsilNameById(id) == null)
  					extraMandals.add(new SelectOptionVO(id,"unKnow")); 
  				  else
  					  extraMandals.add(new SelectOptionVO(id,tehsilDAO.getTehsilNameById(id)));
  			  }
  			dataVerificationInfoVO.setRepeatedMandalList(extraMandals);
  			  //List<Object[]> repeatedMandalList = tehsilDAO.getTehsilNameByTehsilIdsList(repeatedMandalIdsList);
  			  //dataVerificationInfoVO.setRepeatedMandalList(getLocationNameAndIds(repeatedMandalList));
  		  }
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in setMandalsVoterData() method, Exception - "+e);
		}
    }
    
    public List<SelectOptionVO> getLocationNameAndIds(List<Object[]> list)
    {
    	List<SelectOptionVO> selectOptionVOList = null;
    	try{
    		if(list != null && list.size() > 0)
    		{
    			selectOptionVOList = new ArrayList<SelectOptionVO>(0);
    			for(Object[] params : list)
    			 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null ?params[1].toString() :" "));
    		}
    		
    		return selectOptionVOList;
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getMandalDetailsByMandalIdsList() method, Exception - "+e);
			return selectOptionVOList;
		}
    }
    
    
    public void checkPanchayatWiseVotersData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getVoterInfo();
    		List<Long> panchayatList = voterInfoDAO.getReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT));
    		setPanchayatVotersData(dataVerificationVO, dataVerificationInfoVO, panchayatList);
 		  
    		dataVerificationVO.setVoterInfo(dataVerificationInfoVO);	
    	}catch (Exception e) {
    		e.printStackTrace();
    		LOG.error("Exception Occured in checkPanchayatWiseVotersData() method, Exception - "+e);
		}
    }
    
    public void setPanchayatVotersData(DataVerificationVO dataVerificationVO,DataVerificationInfoVO dataVerificationInfoVO,List<Long> panchayatList)
    {
    	try{
    		List<Long> missedPanchayatIdsList = new ArrayList<Long>(0);
    		List<Long> repeatedPanchayatIdsList = new ArrayList<Long>(0);
    		
    		if(panchayatList != null && panchayatList.size() > 0)
 		    {
 		      if(dataVerificationVO.getTotalPanchayats() != null)
 		      {
 			    for(Long panchayatId : dataVerificationVO.getPanchayatIdsList())
   				  if(!panchayatList.contains(panchayatId))
   				    missedPanchayatIdsList.add(panchayatId);
 			    
 			    for(Long panchayatId : panchayatList)
 			     if(!dataVerificationVO.getPanchayatIdsList().contains(panchayatId))
 			    	repeatedPanchayatIdsList.add(panchayatId);
 		      }
 		     dataVerificationInfoVO.setSavedPanchayatsCount(new Long(panchayatList.size()));
 		    }
 			else
 			  missedPanchayatIdsList.addAll(dataVerificationVO.getPanchayatIdsList());
 		    
 		    if(missedPanchayatIdsList != null && missedPanchayatIdsList.size() > 0)
 		    {
 		    	dataVerificationInfoVO.setMissedPanchayatsCount(new Long(missedPanchayatIdsList.size()));
 		    	List<Object[]> missedPanchayats = panchayatDAO.getPanchayatsByPanchayatIdsList(missedPanchayatIdsList);
 		    	dataVerificationInfoVO.setMissedPanchayatList(getLocationNameAndIds(missedPanchayats));
 		    }
 		    
 		   if(repeatedPanchayatIdsList != null && repeatedPanchayatIdsList.size() > 0)
		    {
 			  dataVerificationInfoVO.setRepeatedPanchayatsCount(new Long(repeatedPanchayatIdsList.size()));
 			  if(repeatedPanchayatIdsList != null && repeatedPanchayatIdsList.size() > 0)
 			  {
 				  List<SelectOptionVO> extraPanchayatList = new ArrayList<SelectOptionVO>(0);
 				  for(Long id : repeatedPanchayatIdsList)
 				  {
 					if(panchayatDAO.getPanchayatNameById(id) == null)
 						extraPanchayatList.add(new SelectOptionVO(id,"Unknow"));
 					else
 					  extraPanchayatList.add(new SelectOptionVO(id,panchayatDAO.get(id).getPanchayatName()));	
 				  }
 				 dataVerificationInfoVO.setRepeatedPanchayatList(extraPanchayatList);
 			  }
 			  
		    	//List<Object[]> repeatedPanchayats = panchayatDAO.getPanchayatsByPanchayatIdsList(repeatedPanchayatIdsList);
		    	//dataVerificationInfoVO.setRepeatedPanchayatList(getLocationNameAndIds(repeatedPanchayats));
 			 
		    }
 		    
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in setPanchayatVotersData() method, Exception - "+e);
		}
    }
    
    public void checkBoothWiseVotersData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getVoterInfo();
    		
    		List<Long> boothsList = voterInfoDAO.getReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.BOOTH));
    		setBoothWiseVotersData(dataVerificationVO, dataVerificationInfoVO, boothsList);
    		dataVerificationVO.setVoterInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkBoothWiseVotersData() method, Exception - "+e);
		}
    }
	
    
    public void setBoothWiseVotersData(DataVerificationVO dataVerificationVO,DataVerificationInfoVO dataVerificationInfoVO,List<Long> boothsList)
    {
    	try{
    		List<Long> missedBoothIdsList = new ArrayList<Long>(0);
    		List<Long> extraBoothIdsList = new ArrayList<Long>(0);
    		if(boothsList != null && boothsList.size() > 0)
    		{
    			if(dataVerificationVO.getTotalBooths() != null)
    			{
				  for(Long boothId :dataVerificationVO.getBoothIdsList())
					if(!boothsList.contains(boothId))
						missedBoothIdsList.add(boothId);
				  
				  for(Long boothId :boothsList)
					if(!dataVerificationVO.getBoothIdsList().contains(boothId))
						extraBoothIdsList.add(boothId);
				}
    			dataVerificationInfoVO.setSavedBoothsCount(new Long(boothsList.size()));
    		}
    			else
    				missedBoothIdsList.addAll(dataVerificationVO.getBoothIdsList());
    		
    		if(missedBoothIdsList != null && missedBoothIdsList.size() > 0)
    		{
    			dataVerificationInfoVO.setMissedBoothsCount(new Long(missedBoothIdsList.size()));
    			List<Object[]> missedBoothList = boothDAO.getBoothsByBoothIdsList(missedBoothIdsList);
    			dataVerificationInfoVO.setMissedBoothsList(getLocationNameAndIds(missedBoothList));
    		}
    		
    		if(extraBoothIdsList != null && extraBoothIdsList.size() > 0)
    		{
    			dataVerificationInfoVO.setRepeatedBoothsCount(new Long(extraBoothIdsList.size()));
    			List<SelectOptionVO> extraBoothsList = new ArrayList<SelectOptionVO>(0);
    			for(Long id :extraBoothIdsList)
    			{
    				if(boothDAO.getBoothPartNoByBoothId(id) == null)
    					extraBoothsList.add(new SelectOptionVO(id,"unKnow"));
    				else
    					extraBoothsList.add(new SelectOptionVO(id,boothDAO.getBoothPartNoByBoothId(id)));
    			}
    			dataVerificationInfoVO.setRepeatedBoothsList(extraBoothsList);
    			
    			//List<Object[]> extraBoothList = boothDAO.getBoothsByBoothIdsList(extraBoothIdsList);
    			//dataVerificationInfoVO.setRepeatedBoothsList(getLocationNameAndIds(extraBoothList));
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in setBoothWiseVotersData() method, Exception - "+e);
		}
    }
    
    public void checkMuncipalityWiseVotersData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getVoterInfo();
    		
    		List<Long> muncipalityList = voterInfoDAO.getReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY));
    		setMuncipalityWiseVotersData(dataVerificationVO, dataVerificationInfoVO, muncipalityList);
    		dataVerificationVO.setVoterInfo(dataVerificationInfoVO);
			
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMuncipalityWiseVotersData() method, Exception - "+e);
		}
    }
    
    
    public void setMuncipalityWiseVotersData(DataVerificationVO dataVerificationVO,DataVerificationInfoVO dataVerificationInfoVO,List<Long> muncipalityList)
    {
    	try{
    		List<Long> missedMuncipalityIdsList = new ArrayList<Long>(0);
    		List<Long> extraMuncipalityIdsList = new ArrayList<Long>(0);
    		if(muncipalityList != null && muncipalityList.size() > 0)
			{
				if(dataVerificationVO.getTotalMuncipalities() != null)
				{
				  for(Long muncipalityId : dataVerificationVO.getMuncipalityIdsList())
					if(!muncipalityList.contains(muncipalityId))
						missedMuncipalityIdsList.add(muncipalityId);
				  
				  for(Long muncipalityId : muncipalityList)
					if(!dataVerificationVO.getMuncipalityIdsList().contains(muncipalityId))
						extraMuncipalityIdsList.add(muncipalityId);
				  
				}
				dataVerificationInfoVO.setSavedMuncipalitiesCount(new Long(muncipalityList.size()));
			}
			else
				missedMuncipalityIdsList.addAll(dataVerificationVO.getMuncipalityIdsList());
			
			if(missedMuncipalityIdsList != null && missedMuncipalityIdsList.size() > 0)
    		{
				dataVerificationInfoVO.setMissedMuncipalitiesCount(new Long(missedMuncipalityIdsList.size()));
    			List<Object[]> missedBoothList = boothDAO.getMuncipalitiesByMuncipalityIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedMuncipalityIdsList);
    			dataVerificationInfoVO.setMissedMuncipalityList(getLocationNameAndIds(missedBoothList));
    		}
    		
    		if(extraMuncipalityIdsList != null && extraMuncipalityIdsList.size() > 0)
    		{
    			dataVerificationInfoVO.setRepeatedMandalsCount(new Long(extraMuncipalityIdsList.size()));
    			
    			List<SelectOptionVO> extraMuncipalities = new ArrayList<SelectOptionVO>(0);
    			
    			for(Long id : extraMuncipalityIdsList)
    			{
    			  if(localElectionBodyDAO.getMuncipalityNameById(id)==null)
    				extraMuncipalities.add(new SelectOptionVO(id,"unKnow")); 
    			  else
    				  extraMuncipalities.add(new SelectOptionVO(id,localElectionBodyDAO.getMuncipalityNameById(id))); 
    			}
    			dataVerificationInfoVO.setRepeatedMuncipalityList(extraMuncipalities);
    			
    			//List<Object[]> extraBoothList = boothDAO.getMuncipalitiesByMuncipalityIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),extraMuncipalityIdsList);
    			//dataVerificationInfoVO.setRepeatedMuncipalityList(getLocationNameAndIds(extraBoothList));
    		}
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in setMuncipalityWiseVotersData() method, Exception - "+e);
		}
    }
    public void checkWardWiseVotersData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getVoterInfo();
    		
    		List<Long> wardList = voterInfoDAO.getReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.WARD));
    		setWardWiseVotersData(dataVerificationVO, dataVerificationInfoVO, wardList);
    		dataVerificationVO.setVoterInfo(dataVerificationInfoVO);
			
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkWardWiseVotersData() method, Exception - "+e);
		}
    }
    
    public void setWardWiseVotersData(DataVerificationVO dataVerificationVO,DataVerificationInfoVO dataVerificationInfoVO,List<Long> wardList)
    {
    	try{
    		List<Long> missedWardIdsList = new ArrayList<Long>(0);
    		List<Long> extraWardIdsList = new ArrayList<Long>(0);
    		Long noOfWards = new Long(dataVerificationVO.getTotalWards());
    		if(wardList != null && wardList.size() > 0)
			{
			  if(noOfWards != null && noOfWards.equals(wardList.size()))
			  {
				  for(Long wardId : dataVerificationVO.getWardIdsList())
  					if(!wardList.contains(wardId))
  						missedWardIdsList.add(wardId);
			  
				  for(Long wardId : wardList)
  					if(!dataVerificationVO.getWardIdsList().contains(wardId))
  						extraWardIdsList.add(wardId);
			  }
			  dataVerificationInfoVO.setSavedWardsCount(new Long(wardList.size()));
			}
			else
				missedWardIdsList.addAll(dataVerificationVO.getWardIdsList());
			
			if(missedWardIdsList != null && missedWardIdsList.size() > 0)
    		{
				dataVerificationInfoVO.setMissedWardsCount(new Long(missedWardIdsList.size()));
    			List<Object[]> missedWardList = boothDAO.getWardsByWardIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedWardIdsList);
    			dataVerificationInfoVO.setMissedWardList(getLocationNameAndIds(missedWardList));
    		}
    		
    		if(extraWardIdsList != null && extraWardIdsList.size() > 0)
    		{
    			dataVerificationInfoVO.setRepeatedWardsCount(new Long(extraWardIdsList.size()));
    			List<SelectOptionVO> extraWards = new ArrayList<SelectOptionVO>(0);
    			for(Long id:extraWardIdsList)
    			{
    			  if(constituencyDAO.getConstituencyNameByConstituencyId(id)==null)
    				extraWards.add(new SelectOptionVO(id,"unKnow"));
    			  else
    				extraWards.add(new SelectOptionVO(id,constituencyDAO.getConstituencyNameByConstituencyId(id)));
    			}
    			dataVerificationInfoVO.setRepeatedWardList(extraWards);
    			//List<Object[]> extraWardList = boothDAO.getWardsByWardIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),extraWardIdsList);
    			//dataVerificationInfoVO.setRepeatedWardList(getLocationNameAndIds(extraWardList));
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in setWardWiseVotersData() method, Exception - "+e);
		}
    }
    
    /* Voters Basic data verification End in VoterInfo table */
    
   /* Family wise VotersData verification Start in VoterFamilyInfo table */
    
    public void checkConstituencyWiseVotersFamilyData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		
    	  DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getFamilyInfo();
      	  List<Long> missedFamilyRangeConIds = new ArrayList<Long>(0);
      	  
      	  List<Long> constituencyList = voterFamilyInfoDAO.getVoterReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY));
      	  setConstituencyVoterData(dataVerificationVO, dataVerificationInfoVO, constituencyList);
      	  
      	List<Object[]> voterFamilyList = voterFamilyInfoDAO.getVoterReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY));
		  if(voterFamilyList != null && voterFamilyList.size() > 0)
		  {
			  Long familyRange = voterFamilyRangeDAO.getVoterFamilyRangeDetails().get(0);
			  for(Object[] params : voterFamilyList)
			  {
			     if(dataVerificationVO.getConstituencyIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(familyRange.toString())))
					   missedFamilyRangeConIds.add((Long)params[0]);
			  }
		  }
		  
		  	if(missedFamilyRangeConIds != null && missedFamilyRangeConIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setFamilyConstituencyCount(new Long(missedFamilyRangeConIds.size()));
		  	    List<Object[]> consList = constituencyDAO.getConstituencyNameByConstituencyIdsList(missedFamilyRangeConIds);
		  	    dataVerificationInfoVO.setFamilyConstituencyList(getLocationNameAndIds(consList));
		  	}
		  
  		dataVerificationVO.setFamilyInfo(dataVerificationInfoVO);
  		
    		
    	}catch (Exception e) {
    	  e.printStackTrace();
		  LOG.error("Exception Occured in checkConstituencyWiseVotersFamilyData() method, Exception - "+e);
		}
    }
    
    //Mandal wise Verification in VoterFamilyInfo table
    public void checkMandalWiseVotersFamilyData(DataVerificationVO dataVerificationVO)
    {
       try{
    	  DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getFamilyInfo();
    	  List<Long> missedFamilyRangeManIds = new ArrayList<Long>(0);
    	  
    	  List<Long> mandalList = voterFamilyInfoDAO.getVoterReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.MANDAL));
    	  setMandalsVoterData(dataVerificationVO, dataVerificationInfoVO, mandalList);
    		
  		  List<Object[]> voterFamilyList = voterFamilyInfoDAO.getVoterReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.MANDAL));
  		  if(voterFamilyList != null && voterFamilyList.size() > 0)
  		  {
  			  Long familyRange = voterFamilyRangeDAO.getVoterFamilyRangeDetails().get(0);
  			  for(Object[] params : voterFamilyList)
  			  {
  			     if(dataVerificationVO.getMandalIdsList().contains((Long)params[0]))
  				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(familyRange.toString())))
  					  missedFamilyRangeManIds.add((Long)params[0]);
  			  }
  		  }
  		  
  		  	if(missedFamilyRangeManIds != null && missedFamilyRangeManIds.size() > 0)
  		  	{
  		  		dataVerificationInfoVO.setFamilyMandalCount(new Long(missedFamilyRangeManIds.size()));
  		  	    List<Object[]>mandalsList = tehsilDAO.getTehsilNameByTehsilIdsList(missedFamilyRangeManIds);
  		  	    dataVerificationInfoVO.setFamilyMandalsList(getLocationNameAndIds(mandalsList));
  		  	}
  		  
    		dataVerificationVO.setFamilyInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMandalWiseVotersFamilyData() method, Exception - "+e);
		}
    }
    
    //panchayat wise
    
    public void checkPanchayatWiseVotersFamilyData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getFamilyInfo();
      	  List<Long> missedPanchayatIds = new ArrayList<Long>(0);
      	  
      	  List<Long> panchayatList = voterFamilyInfoDAO.getVoterReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT));
      	  setPanchayatVotersData(dataVerificationVO, dataVerificationInfoVO, panchayatList);
      	  
      	List<Object[]> voterFamilyList = voterFamilyInfoDAO.getVoterReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT));
		  if(voterFamilyList != null && voterFamilyList.size() > 0)
		  {
			  Long familyRange = voterFamilyRangeDAO.getVoterFamilyRangeDetails().get(0);
			  for(Object[] params : voterFamilyList)
			  {
			     if(dataVerificationVO.getPanchayatIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(familyRange.toString())))
					   missedPanchayatIds.add((Long)params[0]);
			  }
		  }
		  
		  	if(missedPanchayatIds != null && missedPanchayatIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setFamilyPanchayatCount(new Long(missedPanchayatIds.size()));
		  	    List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByPanchayatIdsList(missedPanchayatIds);
		  	    dataVerificationInfoVO.setFamilyPanchayatsList(getLocationNameAndIds(panchayatsList));
		  	}
		  
  		  dataVerificationVO.setFamilyInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkPanchayatWiseVotersFamilyData() method, Exception - "+e);
		}
    }
    
    //booth wise
    
    public void checkBoothWiseVotersFamilyData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getFamilyInfo();
      	    List<Long> missedBoothIds = new ArrayList<Long>(0);
      	  
      	  List<Long> boothList = voterFamilyInfoDAO.getVoterReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.BOOTH));
      	  setBoothWiseVotersData(dataVerificationVO, dataVerificationInfoVO, boothList);
      	  
      	List<Object[]> voterFamilyList = voterFamilyInfoDAO.getVoterReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.BOOTH));
		  if(voterFamilyList != null && voterFamilyList.size() > 0)
		  {
			  Long familyRange = voterFamilyRangeDAO.getVoterFamilyRangeDetails().get(0);
			  for(Object[] params : voterFamilyList)
			  {
			     if(dataVerificationVO.getBoothIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(familyRange.toString())))
					   missedBoothIds.add((Long)params[0]);
			  }
		  }
		  	if(missedBoothIds != null && missedBoothIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setFamilyBoothCount(new Long(missedBoothIds.size()));
		  	    List<Object[]> boothsList = boothDAO.getBoothsByBoothIdsList(missedBoothIds);
		  	    dataVerificationInfoVO.setFamilyBoothsList(getLocationNameAndIds(boothsList));
		  	}
		  	dataVerificationVO.setFamilyInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkBoothWiseVotersFamilyData() method, Exception - "+e);
		}
    }
    
    //muncipality wise
    
    public void checkMuncipalityWiseVotersFamilyData(DataVerificationVO dataVerificationVO)
    {
    	try{
    	 DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getFamilyInfo();
      	 List<Long> missedMuncipalityIds = new ArrayList<Long>(0);
      	  
      	 List<Long> MuncipalityList = voterFamilyInfoDAO.getVoterReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY));
      	 setMuncipalityWiseVotersData(dataVerificationVO, dataVerificationInfoVO, MuncipalityList);
      	  
      	 List<Object[]> voterFamilyList = voterFamilyInfoDAO.getVoterReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY));
      	 
		  if(voterFamilyList != null && voterFamilyList.size() > 0)
		  {
			  Long familyRange = voterFamilyRangeDAO.getVoterFamilyRangeDetails().get(0);
			  for(Object[] params : voterFamilyList)
			  {
			     if(dataVerificationVO.getMuncipalityIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(familyRange.toString())))
					   missedMuncipalityIds.add((Long)params[0]);
			  }
		  }
		  	if(missedMuncipalityIds != null && missedMuncipalityIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setFamilyMuncipalityCount(new Long(missedMuncipalityIds.size()));
		  	    List<Object[]> boothsList = boothDAO.getMuncipalitiesByMuncipalityIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedMuncipalityIds);
		  	    dataVerificationInfoVO.setFamilyMuncipalitiesList(getLocationNameAndIds(boothsList));
		  	}
		  	dataVerificationVO.setFamilyInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMuncipalityWiseVotersFamilyData() method, Exception - "+e);
		}
    }
    
    //ward wise 
    
    public void checkWardWiseVotersFamilyData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getFamilyInfo();
         	 List<Long> missedWardIds = new ArrayList<Long>(0);
         	  
         	 List<Long> wardList = voterFamilyInfoDAO.getVoterReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.WARD));
         	 setWardWiseVotersData(dataVerificationVO, dataVerificationInfoVO, wardList);
         	  
         	 List<Object[]> voterFamilyList = voterFamilyInfoDAO.getVoterReportLevelValueByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.WARD));
         	 
         	if(voterFamilyList != null && voterFamilyList.size() > 0)
  		  {
  			  Long familyRange = voterFamilyRangeDAO.getVoterFamilyRangeDetails().get(0);
  			  for(Object[] params : voterFamilyList)
  			  {
  			     if(dataVerificationVO.getWardIdsList().contains((Long)params[0]))
  				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(familyRange.toString())))
  					 missedWardIds.add((Long)params[0]);
  			  }
  		  }  		  
  		  	if(missedWardIds != null && missedWardIds.size() > 0)
  		  	{
  		  		dataVerificationInfoVO.setFamilyWardCount(new Long(missedWardIds.size()));
  		  	    List<Object[]> boothsList = boothDAO.getWardsByWardIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedWardIds);
  		  	    dataVerificationInfoVO.setFamilyWardsList(getLocationNameAndIds(boothsList));
  		  	}
  		  dataVerificationVO.setFamilyInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkWardWiseVotersFamilyData() method, Exception - "+e);
		}
    }
    
    /* Family wise VotersData verification End in VoterFamilyInfo table */
    
    /* Age wise VotersData verification Start in VoterAgeInfo table */
    
    public void checkConstituencyWiseVotersAgeData(DataVerificationVO dataVerificationVO)
    {
       try{
    	  DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getAgeInfo();
    	  List<Long> missedFamilyRangeConIds = new ArrayList<Long>(0);
    	  
    	  List<Long> consList = voterAgeInfoDAO.getVoterAgeInfoByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY));
    	  setConstituencyVoterData(dataVerificationVO, dataVerificationInfoVO, consList);
    		
  		  List<Object[]> voterAgeList = voterAgeInfoDAO.getVoterAgeDetails(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY));
  		  if(voterAgeList != null && voterAgeList.size() > 0)
  		  {
  			  Long ageRange = voterAgeRangeDAO.getVoterAgeRangeDetails().get(0);
  			  for(Object[] params : voterAgeList)
  			  {
  			     if(dataVerificationVO.getConstituencyIdsList().contains((Long)params[0]))
  				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(ageRange.toString())))
  					 missedFamilyRangeConIds.add((Long)params[0]);
  			  }
  		  }
  		  
  		  	if(missedFamilyRangeConIds != null && missedFamilyRangeConIds.size() > 0)
  		  	{
  		  		dataVerificationInfoVO.setAgeWiseConstituencyCount(new Long(missedFamilyRangeConIds.size()));
  		  	    List<Object[]> constsList = constituencyDAO.getConstituencyNameByConstituencyIdsList(missedFamilyRangeConIds);
  		  	    dataVerificationInfoVO.setAgeWiseConstituencyList(getLocationNameAndIds(constsList));
  		  	}
  		  
    		dataVerificationVO.setAgeInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMandalWiseVotersAgeData() method, Exception - "+e);
		}
    }
    
    
    //Mandal wise Verification in VoterFamilyInfo table
    public void checkMandalWiseVotersAgeData(DataVerificationVO dataVerificationVO)
    {
       try{
    	  DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getAgeInfo();
    	  List<Long> missedFamilyRangeManIds = new ArrayList<Long>(0);
    	  
    	  List<Long> mandalList = voterAgeInfoDAO.getVoterAgeInfoByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.MANDAL));
    	  setMandalsVoterData(dataVerificationVO, dataVerificationInfoVO, mandalList);
    		
  		  List<Object[]> voterAgeList = voterAgeInfoDAO.getVoterAgeDetails(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.MANDAL));
  		  if(voterAgeList != null && voterAgeList.size() > 0)
  		  {
  			  Long ageRange = voterAgeRangeDAO.getVoterAgeRangeDetails().get(0);
  			  for(Object[] params : voterAgeList)
  			  {
  			     if(dataVerificationVO.getMandalIdsList().contains((Long)params[0]))
  				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(ageRange.toString())))
  					  missedFamilyRangeManIds.add((Long)params[0]);
  			  }
  		  }  		  
  		  	if(missedFamilyRangeManIds != null && missedFamilyRangeManIds.size() > 0)
  		  	{
  		  		dataVerificationInfoVO.setAgeWiseMandalCount(new Long(missedFamilyRangeManIds.size()));
  		  	    List<Object[]>mandalsList = tehsilDAO.getTehsilNameByTehsilIdsList(missedFamilyRangeManIds);
  		  	    dataVerificationInfoVO.setAgeWiseMandalsList(getLocationNameAndIds(mandalsList));
  		  	}
  		  
    		dataVerificationVO.setAgeInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMandalWiseVotersAgeData() method, Exception - "+e);
		}
    }
    
    //panchayat wise
    
    public void checkPanchayatWiseVotersAgeData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getAgeInfo();
      	  List<Long> missedPanchayatIds = new ArrayList<Long>(0);
      	  
      	  List<Long> panchayatList = voterAgeInfoDAO.getVoterAgeInfoByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT));
      	  setPanchayatVotersData(dataVerificationVO, dataVerificationInfoVO, panchayatList);
      	  
      	List<Object[]> voterAgeList = voterAgeInfoDAO.getVoterAgeDetails(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT));
		  if(voterAgeList != null && voterAgeList.size() > 0)
		  {
			  Long ageRange = voterAgeRangeDAO.getVoterAgeRangeDetails().get(0);
			  for(Object[] params : voterAgeList)
			  {
			     if(dataVerificationVO.getPanchayatIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(ageRange.toString())))
					   missedPanchayatIds.add((Long)params[0]);
			  }
		  }
		  	if(missedPanchayatIds != null && missedPanchayatIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setAgeWisePanchayatCount(new Long(missedPanchayatIds.size()));
		  	    List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByPanchayatIdsList(missedPanchayatIds);
		  	    dataVerificationInfoVO.setAgeWisePanchayatsList(getLocationNameAndIds(panchayatsList));
		  	}
		  
  		  dataVerificationVO.setAgeInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkPanchayatWiseVotersAgeData() method, Exception - "+e);
		}
    }
    
    //booth wise
    
    public void checkBoothWiseVotersAgeData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getAgeInfo();
      	    List<Long> missedBoothIds = new ArrayList<Long>(0);
      	  
      	  List<Long> boothList = voterAgeInfoDAO.getVoterAgeInfoByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.BOOTH));
      	  setBoothWiseVotersData(dataVerificationVO, dataVerificationInfoVO, boothList);
      	  
      	List<Object[]> voterAgeList = voterAgeInfoDAO.getVoterAgeDetails(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.BOOTH));
		  if(voterAgeList != null && voterAgeList.size() > 0)
		  {
			  Long ageRange = voterAgeRangeDAO.getVoterAgeRangeDetails().get(0);
			  for(Object[] params : voterAgeList)
			  {
			     if(dataVerificationVO.getBoothIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(ageRange.toString())))
					   missedBoothIds.add((Long)params[0]);
			  }
		  }
		  	if(missedBoothIds != null && missedBoothIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setAgeWiseBoothCount(new Long(missedBoothIds.size()));
		  	    List<Object[]> boothsList = boothDAO.getBoothsByBoothIdsList(missedBoothIds);
		  	    dataVerificationInfoVO.setAgeWiseBoothsList(getLocationNameAndIds(boothsList));
		  	}
		  	dataVerificationVO.setAgeInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkBoothWiseVotersAgeData() method, Exception - "+e);
		}
    }
    
    //muncipality wise
    public void checkMuncipalityWiseVotersAgeData(DataVerificationVO dataVerificationVO)
    {
    	try{
    	 DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getAgeInfo();
      	 List<Long> missedMuncipalityIds = new ArrayList<Long>(0);
      	  
      	 List<Long> MuncipalityList = voterAgeInfoDAO.getVoterAgeInfoByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY));
      	 setMuncipalityWiseVotersData(dataVerificationVO, dataVerificationInfoVO, MuncipalityList);
      	  
      	 List<Object[]> voterAgeList = voterAgeInfoDAO.getVoterAgeDetails(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY));
      	 
		  if(voterAgeList != null && voterAgeList.size() > 0)
		  {
			  Long ageRange = voterAgeRangeDAO.getVoterAgeRangeDetails().get(0);
			  for(Object[] params : voterAgeList)
			  {
			     if(dataVerificationVO.getMuncipalityIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(ageRange.toString())))
					   missedMuncipalityIds.add((Long)params[0]);
			  }
		  }
		  
		  	if(missedMuncipalityIds != null && missedMuncipalityIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setAgeWiseMuncipalityCount(new Long(missedMuncipalityIds.size()));
		  	    List<Object[]> boothsList = boothDAO.getMuncipalitiesByMuncipalityIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedMuncipalityIds);
		  	    dataVerificationInfoVO.setAgeWiseMuncipalitiesList(getLocationNameAndIds(boothsList));
		  	}
		  	dataVerificationVO.setAgeInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMuncipalityWiseVotersAgeData() method, Exception - "+e);
		}
    }
    
    //ward info
    public void checkWardWiseVotersAgeData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getAgeInfo();
         	 List<Long> missedWardIds = new ArrayList<Long>(0);
         	  
         	 List<Long> wardList = voterAgeInfoDAO.getVoterAgeInfoByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.WARD));
         	 setWardWiseVotersData(dataVerificationVO, dataVerificationInfoVO, wardList);
         	  
         	 List<Object[]> voterAgeList = voterAgeInfoDAO.getVoterAgeDetails(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.WARD));
         	 
         	if(voterAgeList != null && voterAgeList.size() > 0)
         	{
         		 Long ageRange = voterAgeRangeDAO.getVoterAgeRangeDetails().get(0);
  			  for(Object[] params : voterAgeList)
  			  {
  			     if(dataVerificationVO.getWardIdsList().contains((Long)params[0]))
  				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase(ageRange.toString())))
  					 missedWardIds.add((Long)params[0]);
  			  }
  		  }
  		  	if(missedWardIds != null && missedWardIds.size() > 0)
  		  	{
  		  		dataVerificationInfoVO.setAgeWiseWardCount(new Long(missedWardIds.size()));
  		  	    List<Object[]> boothsList = boothDAO.getWardsByWardIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedWardIds);
  		  	    dataVerificationInfoVO.setAgeWiseWardsList(getLocationNameAndIds(boothsList));
  		  	}
  		  dataVerificationVO.setAgeInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkWardWiseVotersAgeData() method, Exception - "+e);
		}
    }
    
    
    /* Age wise VotersData verification End in VoterAgeInfo table */
    
    
    /* Status(Added/deleted) Wise votersData verification Start in VoterModificationInfo table */
    
    public void checkConstituencyWiseVoterModificationData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getModificationInfo();
    		List<Long> constituencyList = voterModificationInfoDAO.getReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY));
    		
    		List<Long> missedStatusConIds = new ArrayList<Long>(0);
      	  	setConstituencyVoterData(dataVerificationVO, dataVerificationInfoVO, constituencyList);
      		
    		  List<Object[]> voterModificationList = voterModificationInfoDAO.getModificationDetailsByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY));
    		  if(voterModificationList != null && voterModificationList.size() > 0)
    		  {
    			  for(Object[] params : voterModificationList)
    			  {
    			     if(dataVerificationVO.getConstituencyIdsList().contains((Long)params[0]))
    				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase("2")))
    					   missedStatusConIds.add((Long)params[0]);
    			  }
    		  }
    		  
    		  	if(missedStatusConIds != null && missedStatusConIds.size() > 0)
    		  	{
    		  		dataVerificationInfoVO.setStatusWiseConstituencyCount(new Long(missedStatusConIds.size()));
    		  	    List<Object[]> constitList = constituencyDAO.getConstituencyNameByConstituencyIdsList(missedStatusConIds);
    		  	    dataVerificationInfoVO.setStatusWiseConstituencyList(getLocationNameAndIds(constitList));
    		  	}
    		  
      		dataVerificationVO.setModificationInfo(dataVerificationInfoVO);
      		
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkConstituencyWiseVoterModificationData() method, Exception - "+e);
		}
    }
    //mandal Info
    public void checkMandalWiseVoterModificationData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getModificationInfo();
    		List<Long> mandalList = voterModificationInfoDAO.getReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.MANDAL));
    		
    		List<Long> missedStatusManIds = new ArrayList<Long>(0);
      	  	setMandalsVoterData(dataVerificationVO, dataVerificationInfoVO, mandalList);
      		
    		  List<Object[]> voterModificationList = voterModificationInfoDAO.getModificationDetailsByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.MANDAL));
    		  if(voterModificationList != null && voterModificationList.size() > 0)
    		  {
    			  for(Object[] params : voterModificationList)
    			  {
    			     if(dataVerificationVO.getMandalIdsList().contains((Long)params[0]))
    				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase("2")))
    					   missedStatusManIds.add((Long)params[0]);
    			  }
    		  }
    		  
    		  	if(missedStatusManIds != null && missedStatusManIds.size() > 0)
    		  	{
    		  		dataVerificationInfoVO.setStatusWiseMandalCount(new Long(missedStatusManIds.size()));
    		  	    List<Object[]>mandalsList = tehsilDAO.getTehsilNameByTehsilIdsList(missedStatusManIds);
    		  	    dataVerificationInfoVO.setStatusWiseMandalsList(getLocationNameAndIds(mandalsList));
    		  	}
    		  
      		dataVerificationVO.setModificationInfo(dataVerificationInfoVO);
      		
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMandalWiseVoterModificationData() method, Exception - "+e);
		}
    }
    
    //panchayat info
    
    public void checkPanchayatWiseVoterModificationData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getModificationInfo();
      	  List<Long> missedPanchayatIds = new ArrayList<Long>(0);
      	  
      	  List<Long> panchayatList = voterModificationInfoDAO.getReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT));
      	  setPanchayatVotersData(dataVerificationVO, dataVerificationInfoVO, panchayatList);
      	  
      	 List<Object[]> voterModificationList = voterModificationInfoDAO.getModificationDetailsByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT));
		  if(voterModificationList != null && voterModificationList.size() > 0)
		  {
			  for(Object[] params : voterModificationList)
			  {
			     if(dataVerificationVO.getPanchayatIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase("2")))
					   missedPanchayatIds.add((Long)params[0]);
			  }
		  }
		  
		  	if(missedPanchayatIds != null && missedPanchayatIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setStatusWisePanchayatCount(new Long(missedPanchayatIds.size()));
		  	    List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByPanchayatIdsList(missedPanchayatIds);
		  	    dataVerificationInfoVO.setStatusWisePanchayatsList(getLocationNameAndIds(panchayatsList));
		  	}
		  
  		  dataVerificationVO.setModificationInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkPanchayatWiseVoterModificationData() method, Exception - "+e);
		}
    }
    
    //muncipality wise
    public void checkMuncipalityWiseVoterModificationData(DataVerificationVO dataVerificationVO)
    {
    	try{
    	 DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getModificationInfo();
      	 List<Long> missedMuncipalityIds = new ArrayList<Long>(0);
      	  
      	 List<Long> MuncipalityList = voterModificationInfoDAO.getReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY));
      	 setMuncipalityWiseVotersData(dataVerificationVO, dataVerificationInfoVO, MuncipalityList);
      	  
      	List<Object[]> voterModificationList = voterModificationInfoDAO.getModificationDetailsByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY));
      	 
		  if(voterModificationList != null && voterModificationList.size() > 0)
		  {
			  for(Object[] params : voterModificationList)
			  {
			     if(dataVerificationVO.getMuncipalityIdsList().contains((Long)params[0]))
				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase("2")))
					   missedMuncipalityIds.add((Long)params[0]);
			  }
		  }
		  
		  	if(missedMuncipalityIds != null && missedMuncipalityIds.size() > 0)
		  	{
		  		dataVerificationInfoVO.setStatusWiseMuncipalityCount(new Long(missedMuncipalityIds.size()));
		  	    List<Object[]> boothsList = boothDAO.getMuncipalitiesByMuncipalityIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedMuncipalityIds);
		  	    dataVerificationInfoVO.setStatusWiseMuncipalitiesList(getLocationNameAndIds(boothsList));
		  	}
		  	dataVerificationVO.setModificationInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkMuncipalityWiseVoterModificationData() method, Exception - "+e);
		}
    }
    
    
    //ward info
    public void checkWardWiseVoterModificationData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getModificationInfo();
         	 List<Long> missedWardIds = new ArrayList<Long>(0);
         	  
         	 List<Long> wardList = voterModificationInfoDAO.getReportLevelValueByReportLevelId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.WARD));
         	 setWardWiseVotersData(dataVerificationVO, dataVerificationInfoVO, wardList);
         	  
         	 List<Object[]> voterModificationList = voterModificationInfoDAO.getModificationDetailsByConstituencyId(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(), votersAnalysisService.getReportLevelId(IConstants.WARD));
         	 
         	if(voterModificationList != null && voterModificationList.size() > 0)
         	{
  			  for(Object[] params : voterModificationList)
  			  {
  			     if(dataVerificationVO.getWardIdsList().contains((Long)params[0]))
  				   if(!(params[1]!= null && params[1].toString().equalsIgnoreCase("2")))
  					 missedWardIds.add((Long)params[0]);
  			  }
  		  }
  		  
  		  	if(missedWardIds != null && missedWardIds.size() > 0)
  		  	{
  		  		dataVerificationInfoVO.setStatusWiseWardCount(new Long(missedWardIds.size()));
  		  	    List<Object[]> boothsList = boothDAO.getWardsByWardIdsList(dataVerificationVO.getConstituencyId(),dataVerificationVO.getPublicationId(),missedWardIds);
  		  	    dataVerificationInfoVO.setStatusWiseWardsList(getLocationNameAndIds(boothsList));
  		  	}
  		  dataVerificationVO.setModificationInfo(dataVerificationInfoVO);
    	}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkWardWiseVoterModificationData() method, Exception - "+e);
		}
    }
    
    /* Status(Added/deleted) Wise votersData verification End in VoterModificationInfo table */
    
    public void validateVoterModificationAgeData(DataVerificationVO dataVerificationVO)
    {
    	try{
    		DataVerificationInfoVO dataVerificationInfoVO = dataVerificationVO.getModificationAgeInfo();
    		List<Long> voterModificationIds = new ArrayList<Long>(0);
    		List<Long> missedModificationIds = new ArrayList<Long>(0);
    		List<Long> consModifIdsList = null;
    		List<Long> mandModifIdsList = null;
    		List<Long> panchayatModifIdsList = null;
    		List<Long> muncipModifIdsList = null;
    		List<Long> wardModifIdsList = null;
    		
    		 if(dataVerificationVO.getConstituencyIdsList() != null && dataVerificationVO.getConstituencyIdsList().size() > 0)
    		    consModifIdsList = voterModificationInfoDAO.getVoterModificationIdsByReportLevelValue(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(),votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY), dataVerificationVO.getConstituencyIdsList());
    		 if(consModifIdsList != null && consModifIdsList.size() > 0)
    			voterModificationIds.addAll(consModifIdsList);
    		
    		if(dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.RURAL) || dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
    		{
    		   if(dataVerificationVO.getMandalIdsList() != null && dataVerificationVO.getMandalIdsList().size() > 0)
    			mandModifIdsList = voterModificationInfoDAO.getVoterModificationIdsByReportLevelValue(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(),votersAnalysisService.getReportLevelId(IConstants.MANDAL), dataVerificationVO.getMandalIdsList());
    		   if(mandModifIdsList != null && mandModifIdsList.size() > 0)
    			 voterModificationIds.addAll(mandModifIdsList);
    		  
    		   if(dataVerificationVO.getPanchayatIdsList() != null && dataVerificationVO.getPanchayatIdsList().size() > 0)
    		     panchayatModifIdsList = voterModificationInfoDAO.getVoterModificationIdsByReportLevelValue(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(),votersAnalysisService.getReportLevelId(IConstants.PANCHAYAT), dataVerificationVO.getPanchayatIdsList());
      		   if(panchayatModifIdsList != null && panchayatModifIdsList.size() > 0)
      			voterModificationIds.addAll(panchayatModifIdsList);
    		}
    		
    		if(dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.URBAN) || dataVerificationVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
    		{
    		  if(dataVerificationVO.getMuncipalityIdsList() != null && dataVerificationVO.getMuncipalityIdsList().size() > 0)
    		    muncipModifIdsList = voterModificationInfoDAO.getVoterModificationIdsByReportLevelValue(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(),votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY), dataVerificationVO.getMuncipalityIdsList());
    		  if(muncipModifIdsList !=null && muncipModifIdsList.size() > 0)
    			voterModificationIds.addAll(muncipModifIdsList);
    		  
    		  if(dataVerificationVO.getWardIdsList() != null && dataVerificationVO.getWardIdsList().size() > 0)
    		    wardModifIdsList = voterModificationInfoDAO.getVoterModificationIdsByReportLevelValue(dataVerificationVO.getConstituencyId(), dataVerificationVO.getPublicationId(),votersAnalysisService.getReportLevelId(IConstants.WARD), dataVerificationVO.getWardIdsList());
    		  if(wardModifIdsList != null && wardModifIdsList.size() > 0)
    			voterModificationIds.addAll(wardModifIdsList);
    		}
    		
    		if(voterModificationIds != null && voterModificationIds.size() > 0)
    		{
    			List<Long> voterModifAgeIds = voterModificationAgeInfoDAO.getVoterModificationIds(voterModificationIds);
    			if(voterModifAgeIds != null && voterModifAgeIds.size() > 0)
    			{
    				for(Long modificationId : voterModificationIds)
    				  if(!voterModifAgeIds.contains(modificationId))
    					  missedModificationIds.add(modificationId);
    			}
    			else
    			  missedModificationIds.addAll(voterModificationIds);
    		}
    		if(missedModificationIds != null && missedModificationIds.size() > 0)
    		{
    			List<Object[]> list = voterModificationInfoDAO.getVoterModificationDetailsByModificationIdsList(missedModificationIds);
    			if(list != null && list.size() > 0)
    			{
    			  List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
    			  for(Object[] params : list)
    			  {
    				 if(votersAnalysisService.getReportLevelById((Long)params[1]).equalsIgnoreCase(IConstants.CONSTITUENCY))
    					selectOptionVOList.add(new SelectOptionVO((Long)params[1],constituencyDAO.get((Long)params[0]).getName(),IConstants.CONSTITUENCY));
    				 else if(votersAnalysisService.getReportLevelById((Long)params[1]).equalsIgnoreCase(IConstants.MANDAL))
    					selectOptionVOList.add(new SelectOptionVO((Long)params[1],tehsilDAO.get((Long)params[0]).getTehsilName(),IConstants.MANDAL));
    				 else if(votersAnalysisService.getReportLevelById((Long)params[1]).equalsIgnoreCase(IConstants.PANCHAYAT))
    					 selectOptionVOList.add(new SelectOptionVO((Long)params[1],panchayatDAO.get((Long)params[0]).getPanchayatName(),IConstants.PANCHAYAT));
    				 else if(votersAnalysisService.getReportLevelById((Long)params[1]).equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
    					 selectOptionVOList.add(new SelectOptionVO((Long)params[1],localElectionBodyDAO.get((Long)params[0]).getName(),IConstants.LOCALELECTIONBODY));
    				 else if(votersAnalysisService.getReportLevelById((Long)params[1]).equalsIgnoreCase(IConstants.WARD))
    					 selectOptionVOList.add(new SelectOptionVO((Long)params[1],constituencyDAO.get((Long)params[0]).getName(),IConstants.WARD));
    			  }
    			  dataVerificationInfoVO.setModificationAgeInfoList(selectOptionVOList);
    			}
    		}
    		dataVerificationVO.setModificationAgeInfo(dataVerificationInfoVO);
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    		LOG.error("Exception Occured in validateVoterModificationAgeData() method, Exception - "+e);
		}
    }
    
    /* voterModification Age Info validation End */
    
    
    public ElectionResultsVerificationVO validateConstituencyEleResults(Long electionId)
    {
    	ElectionResultsVerificationVO electionResultsVerificationVO = new ElectionResultsVerificationVO() ;
    	try{
    		List<Long> constituencyIdsList = new ArrayList<Long>(0);
    		
    		electionResultsVerificationVO.setElectionId(electionId);
    		
    		//get Zero or null valid votes Constituencies
    		List<Object[]> list2 = constituencyElectionResultDAO.getConstituencyDetsBasedOnvalidOrTotVotesNullOrZeroByEleId(electionId,"validVotes");
    		if(list2 != null && list2.size() > 0)
    		{
    			List<ElectionResultsVerificationInfoVO> EleResVerificationInfoVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
    			for(Object[] params : list2)
    			{
    				ElectionResultsVerificationInfoVO verificationInfoVO = new ElectionResultsVerificationInfoVO();
    				verificationInfoVO.setConstituencyId((Long)params[0]);
    				verificationInfoVO.setConstituencyName(params[1] != null ?params[1].toString():" ");
    				EleResVerificationInfoVO.add(verificationInfoVO);
    			}
    			
    			electionResultsVerificationVO.setConsValidVotesVO(EleResVerificationInfoVO);
    		}
    		
    		//get Zero or null total votes Constituencies
    		
    		List<Object[]> list3 = constituencyElectionResultDAO.getConstituencyDetsBasedOnvalidOrTotVotesNullOrZeroByEleId(electionId,"totalVotes");
    		if(list3 != null && list3.size() > 0)
    		{
    			List<ElectionResultsVerificationInfoVO> EleResVerificationInfoVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
    			for(Object[] params : list3)
    			{
    				constituencyIdsList.add((Long)params[0]);
    				ElectionResultsVerificationInfoVO verificationInfoVO = new ElectionResultsVerificationInfoVO();
    				verificationInfoVO.setConstituencyId((Long)params[0]);
    				verificationInfoVO.setConstituencyName(params[1] != null ?params[1].toString():" ");
    				EleResVerificationInfoVO.add(verificationInfoVO);
    			}
    			
    			electionResultsVerificationVO.setConTotalVotesVO(EleResVerificationInfoVO);
    		}
    		
    		//get totalVotes < validVotes constituencies
    		List<Object[]> list = constituencyElectionResultDAO.getConsDetsBasedOnValidVotesGreaterTotVotesByElectionId(electionId);
    		if(list != null && list.size() > 0)
    		{
    			List<ElectionResultsVerificationInfoVO> EleResVerificationInfoVO = new ArrayList<ElectionResultsVerificationInfoVO>(0);
    			for(Object[] params : list)
    			{
    				if(!constituencyIdsList.contains((Long)params[0]))
    				{	
    				  ElectionResultsVerificationInfoVO verificationInfoVO = new ElectionResultsVerificationInfoVO();
    				  verificationInfoVO.setConstituencyId((Long)params[0]);
    				  verificationInfoVO.setConstituencyName(params[1] != null ?params[1].toString():" ");
    				  verificationInfoVO.setTotalVotes(params[2] != null ?new Double((Double)params[2]).longValue():0L);
    				  verificationInfoVO.setValidVotes(params[3] != null ?new Double((Double) params[3]).longValue():0L);
    				  EleResVerificationInfoVO.add(verificationInfoVO);
    				}
    			}
    			
    			electionResultsVerificationVO.setConsValidVotesGreaterTotVotesVO(EleResVerificationInfoVO);
    		}
    		
    		validateBoothWiseEleResults(electionResultsVerificationVO);
    	   return electionResultsVerificationVO;
    	}catch (Exception e) {
    		e.printStackTrace();
    		LOG.error("Exception Occured in validateConstituencyElectionData() method, Exception - "+e);
    		return electionResultsVerificationVO;
		}
    }
    
  
    
    public void validateBoothWiseEleResults(ElectionResultsVerificationVO resultsVerificationVO)
    {
    	try{
    		
    	List<Long> boothIds = new ArrayList<Long>(0);
    	List<Object[]> list = boothConstituencyElectionDAO.getBoothResultsBasedOnTotVotesIsNullByElectionId(resultsVerificationVO.getElectionId());
    	if(list != null && list.size() > 0)
    	{
    	  List<ElectionResultsVerificationInfoVO> boothTotalVotesVOList = new ArrayList<ElectionResultsVerificationInfoVO>(0);
    	  for(Object[] params : list)
    	  {
    		  boothIds.add((Long)params[2]);
    		  ElectionResultsVerificationInfoVO infoVO = new ElectionResultsVerificationInfoVO();
    		  infoVO.setConstituencyName(params[0] != null ? params[0].toString():" ");
    		  infoVO.setPartNo(params[1] != null ? params[1].toString():"");
    		  boothTotalVotesVOList.add(infoVO);
    	  }
    	  resultsVerificationVO.setBoothTotalVotesVO(boothTotalVotesVOList);
    	  
    	}
    	
    	List<Object[]> list2 = boothConstituencyElectionDAO.getBoothResultsBasedOnValidVotesIsNullByElectionId(resultsVerificationVO.getElectionId());
    	if(list2 != null && list2.size() > 0)
    	{
    	  List<ElectionResultsVerificationInfoVO> boothTotalVotesVOList = new ArrayList<ElectionResultsVerificationInfoVO>(0);
    	  for(Object[] params : list2)
    	  {
    		  ElectionResultsVerificationInfoVO infoVO = new ElectionResultsVerificationInfoVO();
    		  infoVO.setConstituencyName(params[0] != null ? params[0].toString():" ");
    		  infoVO.setPartNo(params[1] != null ? params[1].toString():"");
    		  boothTotalVotesVOList.add(infoVO);
    	  }
    	  resultsVerificationVO.setBoothValidVotesVO(boothTotalVotesVOList);
    	  
    	}
    	
    	List<Object[]> list3 = boothConstituencyElectionDAO.getBoothResultsBasedOnTotVotesGreaterValidVotesByElectionId(resultsVerificationVO.getElectionId());
    	if(list3 != null && list3.size() > 0)
    	{
    	  List<ElectionResultsVerificationInfoVO> boothTotalVotesVOList = new ArrayList<ElectionResultsVerificationInfoVO>(0);
    	  for(Object[] params : list3)
    	  {
    		  if(!boothIds.contains((Long)params[4]))
    		  {
    		    ElectionResultsVerificationInfoVO infoVO = new ElectionResultsVerificationInfoVO();
    		   infoVO.setConstituencyName(params[0] != null ? params[0].toString():" ");
    		   infoVO.setPartNo(params[1] != null ? params[1].toString():"");
    		   infoVO.setTotalVotes((Long)params[2]);
    		   infoVO.setValidVotes((Long)params[3]);
    		   boothTotalVotesVOList.add(infoVO);
    		  }
    	  }
    	  resultsVerificationVO.setBoothValidVotesGreaterTotVotesVO(boothTotalVotesVOList);
    	  
    	}
    	
    	List<Object[]> list4 = boothConstituencyElectionDAO.getBoothResultsBasedOnMaleAndFemaleVotesByElectionId(resultsVerificationVO.getElectionId());
    	if(list4 != null && list4.size() > 0)
    	{
    	  List<ElectionResultsVerificationInfoVO> boothTotalVotesVOList = new ArrayList<ElectionResultsVerificationInfoVO>(0);
    	  for(Object[] params : list4)
    	  {
    		  ElectionResultsVerificationInfoVO infoVO = new ElectionResultsVerificationInfoVO();
    		  infoVO.setConstituencyName(params[0] != null ? params[0].toString():" ");
    		  infoVO.setPartNo(params[1] != null ? params[1].toString():"");
    		  infoVO.setTotalVotes((Long)params[2]);
    		  infoVO.setMaleVotes((Long)params[3]);
    		  infoVO.setFeMaleVotes((Long)params[4]);
    		  infoVO.setValidVotes((Long)params[5]);
    		  boothTotalVotesVOList.add(infoVO);
    	  }
    	  resultsVerificationVO.setBoothTotWithMaleAndFemaleVO(boothTotalVotesVOList);
    	  
    	}
    	
    	}catch (Exception e) {
    		e.printStackTrace();
    		LOG.error("Exception Occured in validateBoothWiseEleResults() method, Exception - "+e);
		}
    }
    
}
