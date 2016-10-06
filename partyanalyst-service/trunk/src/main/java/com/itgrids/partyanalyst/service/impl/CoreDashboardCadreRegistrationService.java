package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CoreDashboardCadreRegistrationService implements ICoreDashboardCadreRegistrationService {
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;   
	private IEducationalQualificationsDAO educationalQualificationsDAO;       
	private ICasteStateDAO casteStateDAO;
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}
	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}
	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}
	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}
	private final static Logger LOG = Logger.getLogger(CoreDashboardCadreRegistrationService.class);
	
	
	public CadreRegistratedCountVO showCadreRegistreredCount(String retrieveType){
	    CadreRegistratedCountVO regCountVO = null;
	    try {
	      
	      
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/showCadreRegisteredCount/"+retrieveType;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(CadreRegistratedCountVO.class);
	        
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at showCadreRegistreredCount", e);
	    }
	    return regCountVO;
	  }
	public Object getRegistrationCountDtls(String location, Long constId, String scope){  
	    Object regCountVO = null;
	    try {
	      
	      
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getRegistrationCountDtls/"+location+"/"+constId+"/"+scope;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(Object.class);
	         return regCountVO;
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at showCadreRegistreredCount", e);    
	    }
	    return null;    
	  }
	
	public Object getCadreRegistrationCountByConstituency(Long constituencyId,String fromDate,String toDate){  
	    Object regCountVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getCadreRegistrationCountByConstituency/"+constituencyId+"/"+fromDate+"/"+toDate;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(Object.class);
	         return regCountVO;
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at getCadreRegistrationCountByConstituency", e);
	    }
	    return null;    
	  }
	public Object getDaysByCadreRegistrationCount(Long constituencyId,Long cadreSurveyUserId,Long tabUserInfoId,String fromDate,String toDate){  
	    Object regCountVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getDaysByCadreRegistrationCount/"+constituencyId+"/"+cadreSurveyUserId+"/"+tabUserInfoId+"/"+fromDate+"/"+toDate;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(Object.class);
	         return regCountVO;
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at getDaysByCadreRegistrationCount", e);
	    }
	    return null;    
	  }
	
	public CadreRegistratedCountVO getEnumeratorsInfo(String retrieveType){
	    CadreRegistratedCountVO regCountVO = null;
	    try {
	      
	      
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getEnumeratorsInfo/"+retrieveType;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(CadreRegistratedCountVO.class);
	        
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at getEnumeratorsInfo", e);
	    }
	    return regCountVO;
	  }
	public String getCadreLastUpdatedTime(){
		String status = null;
	    try {
	      
	      
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getCadreLastUpdatedTime";
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         status = webResource.accept("application/json").post(String.class);
	        
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at getCadreLastUpdatedTime", e);
	    }
	    return status;
	  }
	public Object getNoRegistrationReceiveTabUserPersonCountByTimeWise(Long constituencyId,String date){  
	    Object regCountVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getNotReceiveRegistrationPerson/"+constituencyId+"/"+date;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(Object.class);
	         return regCountVO;
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at getNoRegistrationReceiveTabUserPersonCountByTimeWise", e);
	    }
	    return null;    
	  }
	public Object getTabUserInfoDetails(String tabUserInfoIds){  
	    Object regCountVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getTabUserInfoDetails/"+tabUserInfoIds;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(Object.class);
	         return regCountVO;
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at getTabUserInfoDetails", e);
	    }
	    return null;    
	  }
	/*public CadreRegistrationVO getFamilyVoterDetails(Long voterId,CadreRegistrationVO vo)
	{
		List<CadreFamilyVO> returnList = new ArrayList<CadreFamilyVO>();
	  	try{
	  		String houseNo=null;
	  		Long boothId=null;
	  		List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterDetails(voterId);
	  		if(voterDetails!=null && voterDetails.size()>0)
	  		{
	  			for(Object[] objects :voterDetails)
	  			{
	  				
	  				boothId=objects[0] != null ? (Long) objects[0] : 0l;
	  				houseNo=objects[1] != null ? objects[1].toString() : "";
	  				
	  			}
	  		}
	  		List<Object[]> familyVoterDetails = boothPublicationVoterDAO.getFamilyVoterDetails(boothId,houseNo);
	  		if(familyVoterDetails != null && familyVoterDetails.size() > 0){
	  			for (Object[] objects : familyVoterDetails) {
	  				CadreFamilyVO cadreRegistration = new CadreFamilyVO();
	  				cadreRegistration.setVoterId(objects[0] != null ? (Long) objects[0] : 0l);
	  				cadreRegistration.setHouseNo(objects[1] != null ? objects[1].toString() : "");
	  				cadreRegistration.setRelativeName(objects[2] != null ? objects[2].toString() : "");
	  				cadreRegistration.setVoterName(objects[3] != null ? objects[3].toString() : "");
	  				cadreRegistration.setGender(objects[4] != null ? objects[4].toString() : "");
	  				cadreRegistration.setAge(objects[5] != null ? (Long) objects[5] : 0l);
	  				cadreRegistration.setRelationshipType(objects[6] != null ? objects[6].toString() : "");
	  				cadreRegistration.setVoterCadreNO(objects[7] != null ? objects[7].toString() : "");
	  				cadreRegistration.setMobileNo(objects[8] != null ? objects[8].toString() : "");
	  				cadreRegistration.setImagePath(objects[9] != null ? objects[9].toString() : "");
	  				returnList.add(cadreRegistration);
	  			}
	  			vo.setCadreFamilyDetails(returnList);
	  		}
	  	}
	  	catch(Exception e)
	  	{
	  		e.printStackTrace();
	  		LOG.error("Exception Occured in getFamilyVoterDetails() Method - Exception is : ",e);
	  	}
	  	return vo;
	  	
		
	}
	
	public List<IdAndNameVO> getEducationalQualifications( ) {
		List<IdAndNameVO> QualificationList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> educationalQualifications = educationalQualificationsDAO.getEducationalQualifications();
			if (educationalQualifications != null && educationalQualifications.size() > 0) {
				for (Object[] objects : educationalQualifications) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					QualificationList.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getStateWiseConstituency() in CoreDashboardCadreRegistrationService class", e);
		}
		return QualificationList;
	}
	public List<IdAndNameVO> getStatewisesCastNames(Long stateId) {
		List<IdAndNameVO> castNamesList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> castNames = casteStateDAO.getStatewisesCastNames(stateId);
			if (castNames != null && castNames.size() > 0) {
				for (Object[] objects : castNames) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					castNamesList.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getStatewisesCastNames() in CoreDashboardCadreRegistrationService class", e);
		}
		return castNamesList;
	}*/



}
