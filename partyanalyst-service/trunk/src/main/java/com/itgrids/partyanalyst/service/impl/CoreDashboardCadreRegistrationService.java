package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CoreDashboardCadreRegistrationService implements ICoreDashboardCadreRegistrationService {
	
private final static Logger LOG = Logger.getLogger(CoreDashboardCadreRegistrationService.class);
	
	private ITdpCadreDAO tdpCadreDAO ;
	private CommonMethodsUtilService commonMethodsUtilService ;
	private IVoterDAO voterDAO ;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;   
	private IEducationalQualificationsDAO educationalQualificationsDAO;         
	private ICasteStateDAO casteStateDAO;
	private IVoterRelationDAO voterRelationDAO;
	
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
	
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public IVoterRelationDAO getVoterRelationDAO() {
		return voterRelationDAO;
	}
	public void setVoterRelationDAO(IVoterRelationDAO voterRelationDAO) {
		this.voterRelationDAO = voterRelationDAO;
	}
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
	/**
	* @param  Long voterId,CadreRegistrationVO vo
	* @return  CadreRegistrationVO
	* @author srujana 
	* @Description : 
	*  @since 10-October-2016
	*/
	public CadreRegistrationVO getFamilyVoterDetails(Long voterId,CadreRegistrationVO vo)
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
	  				//if(voterId != (Long) objects[0])
	  				//{
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
	  			//}
	  				
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
	/**
	* @param  
	* @return  List<IdAndNameVO>
	* @author srujana 
	* @Description : 
	*  @since 10-October-2016
	*/
	public List<IdAndNameVO> getEducationalQualifications( ) {
		List<IdAndNameVO> qualificationList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> educationalQualifications = educationalQualificationsDAO.getEducationalQualifications();
			if (educationalQualifications != null && educationalQualifications.size() > 0) {
				for (Object[] objects : educationalQualifications) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					qualificationList.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getStateWiseConstituency() in CoreDashboardCadreRegistrationService class", e);
		}
		return qualificationList;
	}
	/**
	* @param Long stateId
	* @return  List<IdAndNameVO>
	* @author srujana 
	* @Description : 
	*  @since 10-October-2016
	*/
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
	}

	
	/**
	* @param  Long voterId,Long familyVoterId,Long tdpCadreId
	* @return  CadreRegistrationVO
	* @author Hymavathi 
	* @Description : Showing Cadre Data 
	*  @Date 10-October-2016
	*/
	public CadreRegistrationVO getRegistrationPersonDetails(Long voterId,Long familyVoterId,Long tdpCadreId,String status){
	
		CadreRegistrationVO returnVO = new CadreRegistrationVO(); 
		try{

			if(tdpCadreId != null && tdpCadreId.longValue() > 0l){
				List<Object[]> tdpCadreList = tdpCadreDAO.getRegisteredDetailsByCadreId(tdpCadreId,voterId,familyVoterId,status);
				setCadreDetailsToVO(returnVO,tdpCadreList);
			}else if(voterId != null && voterId.longValue() >0l){
				List<Object[]> voterList = voterDAO.getVoterDetailsByVoterId(voterId);
				setVoterDetailsToVO(returnVO,voterList);
			} 
			if(familyVoterId != null && familyVoterId.longValue() >0l){
				getFamilyVoterDetails(familyVoterId,returnVO);
			}else if(voterId != null && voterId.longValue() >0l){
				getFamilyVoterDetails(voterId,returnVO);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			 LOG.error("Exception raised at getRegistrationPersonDetails", e);
		}
		return returnVO;
	}
	
	/**
	* @param  CadreRegistrationVO returnVO,TdpCadre tdpCadre
	* @return  void
	* @author Hymavathi 
	* @Description : 
	*  @since 10-October-2016
	*/
	public void setCadreDetailsToVO(CadreRegistrationVO returnVO,List<Object[]> tdpCadreList){
		try{
		SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
		if(tdpCadreList != null && tdpCadreList.size()>0 ){
			for (Object[] objects : tdpCadreList) {
				
			returnVO.setTdpCadreId(objects[0]!=null?(Long)objects[0]:0l);//cadreId
			returnVO.setLastName(objects[1]!=null?objects[1].toString():"");//firstName
			returnVO.setNameType(objects[2]!=null?objects[2].toString():"");//lastName
			returnVO.setMemberTypeId(objects[3]!=null?objects[3].toString():"");//memberShipNo
			String gender = null;
			if(objects[4].toString().equalsIgnoreCase("M")){
				gender = "Male";
			}else if(objects[4].toString().equalsIgnoreCase("F")){
				gender = "Female";
			}
			returnVO.setGender(gender);//gender
			returnVO.setAge(objects[5]!=null?(Long)objects[5]:0l);//age 
			if((returnVO.getAge() == null || returnVO.getAge().toString().trim().length()<=0) && objects[6]  != null)
			{
				String dateOfBirth = 	 objects[6] != null ? objects[6].toString().substring(0,10):" "	;
				if(dateOfBirth != null && dateOfBirth.trim().length()>0)
				{
					Calendar startDate = new GregorianCalendar();
					Calendar endDate = new GregorianCalendar();
					
					startDate.setTime(format.parse(dateOfBirth.trim()));
					
					endDate.setTime(new Date());

					int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
					
					returnVO.setAge(Long.valueOf(String.valueOf(diffYear)));
				}
			}
			returnVO.setDobStr(objects[6]!=null?objects[6].toString():"");//DOB
			returnVO.setImageBase64String(objects[7]!=null?objects[7].toString():"");//ImagePath
			returnVO.setMobileNumber(objects[8]!=null?objects[8].toString():"");//mobileNo
			returnVO.setEmail(objects[9]!=null?objects[9].toString():"");//emailId
			returnVO.setCandidateAadherNo(objects[10]!=null?objects[10].toString():"");//cadreAadharNo
			returnVO.setCasteId(objects[11]!=null?(Long)objects[11]:0l);//casteId
			returnVO.setEducationId(objects[12]!=null?(Long)objects[12]:0l);//educationId
			returnVO.setOccupationId(objects[13]!=null?(Long)objects[13]:0l);//occupationId
			returnVO.setNomineeName(objects[14]!=null?objects[14].toString():"");//nomineeName
			returnVO.setNomineeGender(objects[15]!=null?objects[15].toString():"");//nomineeGender
			returnVO.setNomineeAge(objects[16]!=null?(Long)objects[16]:0l);//nomineeAge
			returnVO.setRelativeType(objects[17]!=null?objects[17].toString():"");//relativeType
			
			
			if(objects[18] != null && objects[18].toString().length()> 0l){
				returnVO.setVoterRelationId(objects[19]!=null?(Long)objects[19]:0l);//voterId
				returnVO.setVoterCardNo(objects[18]!=null?objects[18].toString():"");//votercardNo
			}else if(objects[18] != null && objects[18].toString().length() > 0l){
				returnVO.setFamilyVoterId(objects[19]!=null?(Long)objects[19]:0l);//familyvoterId
				returnVO.setVoterCardNumber(objects[18]!=null?objects[18].toString():"");//familyVotercardNo
			}
			
		}
		}
		}catch(Exception e){
			e.printStackTrace();
			 LOG.error("Exception raised at setCadreDetailsToVO", e);
		}
	}
	
	/**
	* @param  CadreRegistrationVO returnVO,TdpCadre tdpCadre
	* @return  void
	* @author Hymavathi 
	* @Description : 
	*  @since 10-October-2016
	*/
	public void setVoterDetailsToVO(CadreRegistrationVO returnVO,List<Object[]> voterList){
		try{
		SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
		if(voterList != null && voterList.size()>0){
			for (Object[] objects : voterList) {
				
			returnVO.setVoterRelationId(objects[0]!=null?(Long)objects[0]:0l);//voterId
			returnVO.setLastName(objects[1]!=null?objects[1].toString():"");//Name
			returnVO.setGender(objects[2]!=null?objects[2].toString():"");//gender
			returnVO.setAge(objects[3]!=null?(Long)objects[3]:0l);//age 
			if((returnVO.getAge() == null || returnVO.getAge().toString().trim().length()<=0) && objects[4]  != null)
			{
				String dateOfBirth = 	objects[4] != null ?objects[4].toString().substring(0,10):" "	;
				if(dateOfBirth != null && dateOfBirth.trim().length()>0)
				{
					Calendar startDate = new GregorianCalendar();
					Calendar endDate = new GregorianCalendar();
					
					startDate.setTime(format.parse(dateOfBirth.trim()));
					
					endDate.setTime(new Date());

					int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
					
					returnVO.setAge(Long.valueOf(String.valueOf(diffYear)));
				}
			}
			returnVO.setDobStr(objects[4]!=null?objects[4].toString():"");//DOB
			returnVO.setImageBase64String(objects[5]!=null?objects[5].toString():"");//ImagePath
			returnVO.setMobileNumber(objects[6]!=null?objects[6].toString():"");//mobileNo
			returnVO.setRelativeType(objects[7]!=null?objects[7].toString():"");//relativeType
			returnVO.setVoterCardNo(objects[8]!=null?objects[8].toString():"");//votercardNo
			
		}
		}
		}catch(Exception e){
			e.printStackTrace();
			 LOG.error("Exception raised at setCadreDetailsToVO", e);
		}
	}
	/**
	* @param  
	* @return  List<IdAndNameVO>
	* @author srujana 
	* @Description : 
	*  @since 10-October-2016
	*/
	public List<IdAndNameVO> getAllRelationDetails(){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try{
		List<Object[]> results = voterRelationDAO.getAllRelationDetails();
		for(Object[] objects:results){
			IdAndNameVO vo = new IdAndNameVO();
			vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
			vo.setName(objects[1] != null ? objects[1].toString() : "");
			returnList.add(vo);
		}
		}catch(Exception e){
			LOG.error("Exception raised in getAllRelationDetails in CadreRegistrationService service", e);
		}
		return returnList;
	}
	public String savingCadreDetails(CadreRegistrationVO cadreRegistrationVO){  
		CadreResponseVO responceVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/saveFieldDataForCadre";
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         responceVO = webResource.accept("application/json").type("application/json").post(CadreResponseVO.class,cadreRegistrationVO);
	        
	         if(responceVO.getSaveStatus().equalsIgnoreCase("Success")){
	        	 return "SUCCESS";
	         }else{
	        	 return "FAIL";
	         }
	         
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at savingCadreDetails", e);
	    }
	    return null;    
	  }

}
