package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
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
	private ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO;
	private ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO;
	private ICoreDashboardGenericService coreDashboardGenericService;
	private ITdpCadreTargetCountDAO tdpCadreTargetCountDAO;
	
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
	public void setTdpCadreEnrollmentInfoDAO(
			ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO) {
		this.tdpCadreEnrollmentInfoDAO = tdpCadreEnrollmentInfoDAO;
	}
	public void setTabUserEnrollmentInfoDAO(
			ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO) {
		this.tabUserEnrollmentInfoDAO = tabUserEnrollmentInfoDAO;
	}
	public void setTdpCadreTargetCountDAO(
			ITdpCadreTargetCountDAO tdpCadreTargetCountDAO) {
		this.tdpCadreTargetCountDAO = tdpCadreTargetCountDAO;
	}
	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
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
	* @return  Registration2016VO
	* @author srujana 
	* @Description : 
	*  @since 10-October-2016
	*/
	public NewCadreRegistrationVO getFamilyVoterDetails(Long voterId,NewCadreRegistrationVO vo)
	{
		List<CadreFamilyVO> returnList = new ArrayList<CadreFamilyVO>();
	  	try{
	  		String houseNo=null;
	  		Long boothId=null;
	  		List<String> relationTypes= new ArrayList<String>();
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
	  				if(voterId.longValue() != (Long) objects[0])
	  				{
	  				CadreFamilyVO cadreRegistration = new CadreFamilyVO();
	  				cadreRegistration.setVoterId(objects[0] != null ? (Long) objects[0] : 0l);
	  				cadreRegistration.setHouseNo(objects[1] != null ? objects[1].toString() : "");
	  				cadreRegistration.setRelativeName(objects[2] != null ? objects[2].toString() : "");
	  				cadreRegistration.setVoterName(objects[3] != null ? objects[3].toString() : "");
	  				cadreRegistration.setGender(objects[4] != null ? objects[4].toString() : "");
	  				cadreRegistration.setAge(objects[5] != null ? (Long) objects[5] : 0l);
	  				//cadreRegistration.setRelationshipType(objects[6] != null ? objects[6].toString() : "");
	  				if(objects[6].toString()!=null)
	  					{
	  					cadreRegistration.setRelationshipType(objects[6] != null ? objects[6].toString() : "");
                        relationTypes.add(objects[6].toString());
		  				}
	  				cadreRegistration.setVoterCadreNO(objects[7] != null ? objects[7].toString() : "");
	  				cadreRegistration.setMobileNo(objects[8] != null ? objects[8].toString() : "");
	  				cadreRegistration.setImagePath(objects[9] != null ? objects[9].toString() : "");
	  				returnList.add(cadreRegistration);
	  			}
  				
		  	}
	  			List<Object[]> relationDetails = voterRelationDAO.getRelationDetails(relationTypes);
	  			if(relationDetails != null && relationDetails.size() > 0)
	  			{
	  				for(Object[] obj : relationDetails)
	  				{
	  					for(CadreFamilyVO cadreFamilyVO : returnList)
		  				{
						//CadreFamilyVO familyVo =getMatchVO(returnList,obj[1].toString());
	  						if(cadreFamilyVO.getRelationshipType().equalsIgnoreCase(obj[1].toString()))
	  					{
	  							cadreFamilyVO.setRelationshipTypeId(obj[0] != null ? (Long) obj[0] : 0l);
	  					}
		  				}
	  				}
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
	
	public CadreFamilyVO getMatchVO(List<CadreFamilyVO> returnList, String des) {
		if (returnList == null || returnList.size() == 0)
			return null;
		for (CadreFamilyVO familyVO : returnList) {
			if (familyVO.getRelationshipType().equalsIgnoreCase(des)) {
				return familyVO;
			}
		}
		return null;
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
	* @return  Registration2016VO
	* @author Hymavathi 
	* @Description : Showing Cadre Data 
	*  @Date 10-October-2016
	*/
	public NewCadreRegistrationVO getRegistrationPersonDetails(Long voterId,Long familyVoterId,Long tdpCadreId,String status){
	
		NewCadreRegistrationVO returnVO = new NewCadreRegistrationVO(); 
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
	public void setCadreDetailsToVO(NewCadreRegistrationVO returnVO,List<Object[]> tdpCadreList){
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
			returnVO.setConstituencyId(objects[20]!=null?objects[20].toString():"");//constituencyId
			
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
	public void setVoterDetailsToVO(NewCadreRegistrationVO returnVO,List<Object[]> voterList){
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
			returnVO.setConstituencyId(objects[9]!=null?objects[9].toString():"");//constituencyId
			
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
	public CadreRegistratedCountVO getTotalNewRenewalCadreStateWise(String startDate, String endDate){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			//getTotalNewRenewalCadreStateWise
			startDate = null;
			endDate = null;
			Long totalCadre = 0l;
			Long totalNew = 0l;
			Long totalRenewal = 0l;
			Long totalCadreToday = 0l;
			Long totalCadreTodayNew = 0l;
			Long totalCadreTodayRenewal = 0l;
			CadreRegistratedCountVO  cadreRegistratedCountVO = new CadreRegistratedCountVO();
			List<Object[]> totalNewRenewalCadreStateWiseList = tdpCadreEnrollmentInfoDAO.getTotalNewRenewalCadreStateWise(startDate, endDate);
			if(totalNewRenewalCadreStateWiseList != null && totalNewRenewalCadreStateWiseList.size() > 0){
				for(Object[] param : totalNewRenewalCadreStateWiseList){
					totalCadre += param[2] != null ? (Long)param[2] : 0l;
					totalNew += param[3] != null ? (Long)param[3] :0l;
					totalRenewal += param[4] != null ? (Long)param[4] :0l;
					if(((Long)param[0]) == 1l){
						cadreRegistratedCountVO.setAPTotalCount(param[2] != null ? (Long)param[2] : 0l);
					}else{
						cadreRegistratedCountVO.setTSTotalCount(param[2] != null ? (Long)param[2] : 0l);
					}
				}
				cadreRegistratedCountVO.setTotalCount(totalCadre);
				cadreRegistratedCountVO.setNewCount(totalNew);
				cadreRegistratedCountVO.setRenewalCount(totalRenewal);
			}
			startDate = sdf.format(today);
			endDate = sdf.format(today);
			List<Object[]> totalNewRenewalCadreStateWiseListForToday = tdpCadreEnrollmentInfoDAO.getTotalNewRenewalCadreStateWise(startDate, endDate);
			if(totalNewRenewalCadreStateWiseListForToday != null && totalNewRenewalCadreStateWiseListForToday.size() > 0){
				for(Object[] param : totalNewRenewalCadreStateWiseListForToday){
					totalCadreToday += param[2] != null ? (Long)param[2] : 0l;
					totalCadreTodayNew += param[3] != null ? (Long)param[3] :0l;
					totalCadreTodayRenewal += param[4] != null ? (Long)param[4] :0l;
					if(((Long)param[0]) == 1l){
						cadreRegistratedCountVO.setAPTotalCountToday(param[2] != null ? (Long)param[2] : 0l);
					}else{
						cadreRegistratedCountVO.setTSTotalCountToday(param[2] != null ? (Long)param[2] : 0l);
					}
					cadreRegistratedCountVO.setTodayTotalCount(totalCadreToday);
					cadreRegistratedCountVO.setTodayNewCount(totalCadreTodayNew);
					cadreRegistratedCountVO.setTodayRenewalCount(totalCadreTodayRenewal);
				}
			}
			//AP total target
			Long apTarget = tdpCadreTargetCountDAO.getAPTargetCount();
			if(apTarget != null)
			cadreRegistratedCountVO.setTotalPercentAP(calculatePercantage(cadreRegistratedCountVO.getAPTotalCount(),apTarget));
			//Ap today target
			Long apTodayTarget = IConstants.DAY_WISE_AP_TARGET_REGISTRATIONS_COUNT;
			cadreRegistratedCountVO.setTotalPercentAPToday(calculatePercantage(cadreRegistratedCountVO.getAPTotalCountToday(),apTodayTarget));
			//TS total target
			Long tsTarget = tdpCadreTargetCountDAO.getTSTargetCount();
			if(tsTarget != null)
			cadreRegistratedCountVO.setTotalPercentTS(calculatePercantage(cadreRegistratedCountVO.getTSTotalCount(),tsTarget));
			//TS today target
			Long tsTodayTarget = IConstants.DAY_WISE_TS_TARGET_REGISTRATIONS_COUNT;
			cadreRegistratedCountVO.setTotalPercentTSToday(calculatePercantage(cadreRegistratedCountVO.getTSTotalCountToday(),tsTodayTarget));
			//total const started Ap
			Long ttlContStartedAp = tdpCadreEnrollmentInfoDAO.getTotalConstituencyForCdrRegStarted(1l);
			
			cadreRegistratedCountVO.setConstStartedCountAp(ttlContStartedAp);
			cadreRegistratedCountVO.setConstStartedCountPerAp(calculatePercantage(ttlContStartedAp,175l));
			//total const started Ts
			Long ttlContStartedTs = tdpCadreEnrollmentInfoDAO.getTotalConstituencyForCdrRegStarted(36l);
			cadreRegistratedCountVO.setConstStartedCountTs(ttlContStartedTs);
			cadreRegistratedCountVO.setConstStartedCountPerTs(calculatePercantage(ttlContStartedTs,119l));
			//total tab user in field AP
			Long ttlTabUserInFieldAp = tabUserEnrollmentInfoDAO.getTotalTabUserWorkingInField(today, 1l);
			cadreRegistratedCountVO.setInFieldAP(ttlTabUserInFieldAp);
			Long ttlSubmittedDataTodayAp = tabUserEnrollmentInfoDAO.getTotalRecordSubmitedByTabUser(today, 1l);
			cadreRegistratedCountVO.setTotalSubmittedTodayAp(ttlSubmittedDataTodayAp);
			//total tab user in field Ts
			Long ttlTabUserInFieldTs = tabUserEnrollmentInfoDAO.getTotalTabUserWorkingInField(today, 36l);
			cadreRegistratedCountVO.setInFieldTs(ttlTabUserInFieldTs);
			Long ttlSubmittedDataTodayTs = tabUserEnrollmentInfoDAO.getTotalRecordSubmitedByTabUser(today, 36l);
			cadreRegistratedCountVO.setTotalSubmittedTodayTs(ttlSubmittedDataTodayTs);
			return cadreRegistratedCountVO;
		}catch(Exception e){   
			e.printStackTrace();
			LOG.error("Exception raised in getTotalNewRenewalCadreStateWise in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}

	/**
	* @param  cadreRegistrationVO
	* @return  String
	* @author Hymavathi 
	* @Description : Saving Cadre Details By Calling WebService
	*  @since 13-October-2016
	*/
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
	
	/**
	* @param Long activityMemberId
	* @param Long stateId
	* @param Long userTypeId
	* @param userId
	* @param fromDateStr
	* @param toDateStr
	* @return  List<List<UserTypeVO>>
	* @author Santosh 
	* @Description :This Service Method is used to get top5 positive or top5 negative members tdp cadre count. 
	* @since 13-OCT-2016
	*/
   public List<List<UserTypeVO>> getUserTypeWiseTotalCadreRegistrationCount(Long activityMemberId,Long stateId,Long userTypeId,Long userId,String fromDateStr,String toDateStr){
		 
		  List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>();
		  Map<Long,Set<Long>> locationLevelMap = null;
		  Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
		  Map<String,Long> totalCadreTargetMap = new HashMap<String, Long>(0);
		  Map<String,Long> totCadreMap = new HashMap<String, Long>(0);
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  Date toDate=null;
		  Date fromDate=null;
		  
		try{
			   if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
					 toDate = sdf.parse(toDateStr);
					 fromDate = sdf.parse(fromDateStr);
				 }
			     
			     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
			     activityMemberVO.setUserId(userId);
			     activityMemberVO.setActivityMemberId(activityMemberId);
			     activityMemberVO.setUserTypeId(userTypeId);
			     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
			     userTypeMapDtls = activityMemberVO.getUserTypesMap();
			     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
			     
			     if(locationLevelMap != null && locationLevelMap.size() > 0){
			    	 
					  for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
						  
						List<Object[]> returnObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId);
						  
						if(returnObjList != null && returnObjList.size() > 0){
							
							   for (Object[] param : returnObjList) {
								   
								String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
								totalCadreTargetMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
								
							}
						}
					}  
				}
				if(locationLevelMap != null && locationLevelMap.size() > 0){
					
					  for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
						  
						List<Object[]> returnObjList = tdpCadreDAO.getTotalCadreCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId,fromDate,toDate);
						  
						if(returnObjList != null && returnObjList.size() > 0){
							
							   for (Object[] param : returnObjList) {
								   
								 String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
								 totCadreMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
								 
							 }
						}
					 }  
				}
				//setting target Count
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					
					  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	  for(Long locationValueId:vo.getLocationValuesSet()){
					    		  
					    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
					    		  
					    		  if(totalCadreTargetMap.get(key) != null){
					    			  
							    		 vo.setTotalTargetCount(vo.getTotalTargetCount()+totalCadreTargetMap.get(key)); 
							    	 }
					    	  }
					      }
				     }  
				}
				//setting cadre  Count
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					
					  for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	   for(Long locationValueId:vo.getLocationValuesSet()){
					    		   
					    			 String key = vo.getLocationLevelId()+"-"+locationValueId; 
					    			 
					    			 if(totCadreMap.get(key) != null){
					    				 
					    	    		 vo.setTotalCadreCount(vo.getTotalCadreCount()+totCadreMap.get(key)); 
					    	    	 }
					    	   }
					      }
				    }	  
				}
				//Calculating percentage
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					
					  for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	 	vo.setTotalCadreCountPer(calculatePercantage(vo.getTotalCadreCount(),vo.getTotalTargetCount()));
					    	 	
						}
				 }
			  }
				
			 if(userTypeMapDtls!=null && userTypeMapDtls.size()>0){
			        Map<Long,UserTypeVO> orgSecAndSecMap = new LinkedHashMap<Long,UserTypeVO>();
			        Map<Long,UserTypeVO>  secreteriesMap = null;
			        if(userTypeMapDtls.containsKey(11l)){
			          secreteriesMap = userTypeMapDtls.get(11l);
			          orgSecAndSecMap.putAll(secreteriesMap);
			          //remove secreteries from Map
			          userTypeMapDtls.remove(11l); 
			        }
			        
			        Map<Long,UserTypeVO>  organizingSecreteriesMap = null;
			        if(userTypeMapDtls.containsKey(4l)){
			          organizingSecreteriesMap = userTypeMapDtls.get(4l);
			          orgSecAndSecMap.putAll(organizingSecreteriesMap);
			        }
			       
			        if(organizingSecreteriesMap!=null && organizingSecreteriesMap.size()>0){
			        	userTypeMapDtls.put(4l, orgSecAndSecMap); 
			        }
			   }
				
			   if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					  for(Entry<Long, Map<Long, UserTypeVO>> entry:userTypeMapDtls.entrySet()){
					   Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					   resultList.add(new ArrayList<UserTypeVO>(userTypeMap.values()));
				      }
				}
			 	if(resultList != null && resultList.size() > 0){
					for(List<UserTypeVO> memberList:resultList){
						Collections.sort(memberList, cadreRegistrationCountPercDesc);
					}
				}
				
		 }
		   catch(Exception e){
			   LOG.error("Exception raised in getUserTypeWiseTotalCadreRegistrationCount() in CadreRegistrationService service", e); 
		   }
		   return resultList;
	   }
	   public static Comparator<UserTypeVO> cadreRegistrationCountPercDesc = new Comparator<UserTypeVO>() {
			public int compare(UserTypeVO member2, UserTypeVO member1) {
			Double perc2 = member2.getTotalCadreCountPer();
			Double perc1 = member1.getTotalCadreCountPer();
			//descending order of percantages.
			 return perc1.compareTo(perc2);
			}
	  }; 
	   public Double calculatePercantage(Long subCount,Long totalCount){
			Double d=0.0d;
			if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);
			if(totalCount.longValue() > 0l){
				 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
			}
			return d;
	  } 
}
