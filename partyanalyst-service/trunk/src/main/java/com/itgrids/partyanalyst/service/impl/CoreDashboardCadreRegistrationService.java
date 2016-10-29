package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITabUserInfoDAO;
import com.itgrids.partyanalyst.dao.ITabUserOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.TabUserOtpDetails;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.IPaymentGatewayService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
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
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
    private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
    private IDistrictDAO districtDAO;
    private IOccupationDAO occupationDAO;
    private SmsCountrySmsService smsCountrySmsService;
    private IPaymentGatewayService paymentGatewayService;
    private IUserAddressDAO userAddressDAO;
    private DateUtilService dateUtilService;
    private ITabUserOtpDetailsDAO tabUserOtpDetailsDAO;
    private ITabUserInfoDAO tabUserInfoDAO;
    private ITdpCadreDateWiseInfoDAO tdpCadreDateWiseInfoDAO;
    private ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO;
    private ITdpCadreLocationInfoCountDAO tdpCadreLocationInfoCountDAO;
    private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
    
    
	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}
	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}
	public IPaymentGatewayService getPaymentGatewayService() {
		return paymentGatewayService;
	}
	public void setPaymentGatewayService(
			IPaymentGatewayService paymentGatewayService) {
		this.paymentGatewayService = paymentGatewayService;
	}
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
	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}
	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}
	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}
	
	public SmsCountrySmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}
	public void setSmsCountrySmsService(SmsCountrySmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}
	
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public ITabUserOtpDetailsDAO getTabUserOtpDetailsDAO() {
		return tabUserOtpDetailsDAO;
	}
	public void setTabUserOtpDetailsDAO(ITabUserOtpDetailsDAO tabUserOtpDetailsDAO) {
		this.tabUserOtpDetailsDAO = tabUserOtpDetailsDAO;
	}
	public ITabUserInfoDAO getTabUserInfoDAO() {
		return tabUserInfoDAO;
	}
	public void setTabUserInfoDAO(ITabUserInfoDAO tabUserInfoDAO) {
		this.tabUserInfoDAO = tabUserInfoDAO;
	}
	public void setTdpCadreDateWiseInfoDAO(
			ITdpCadreDateWiseInfoDAO tdpCadreDateWiseInfoDAO) {
		this.tdpCadreDateWiseInfoDAO = tdpCadreDateWiseInfoDAO;
	}
	public void setTdpCadreLocationInfoDAO(
			ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO) {
		this.tdpCadreLocationInfoDAO = tdpCadreLocationInfoDAO;
	}
	public void setTdpCadreLocationInfoCountDAO(
			ITdpCadreLocationInfoCountDAO tdpCadreLocationInfoCountDAO) {
		this.tdpCadreLocationInfoCountDAO = tdpCadreLocationInfoCountDAO;
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
	  		String voterCardNo ="";
	  		if(voterDetails!=null && voterDetails.size()>0)
	  		{
	  			for(Object[] objects :voterDetails)
	  			{
	  				
	  				boothId=objects[0] != null ? (Long) objects[0] : 0l;
	  				houseNo=objects[1] != null ? objects[1].toString() : "";
	  				voterCardNo = objects[2] != null ? objects[2].toString() : "";
	  			}
	  		}
	  		//List<Long> voterIdsList = new ArrayList<Long>(0);
	  		//voterIdsList.add(voterId);
	  		vo.setVoterCardNo(voterCardNo.trim());
	  		//Map<String,NewCadreRegistrationVO> addressMap = new HashMap<String, NewCadreRegistrationVO>(0);
	  		//getAddressDetailsForVoter(addressMap,voterIdsList);
	  		/*
	  		if(addressMap.get(vo.getVoterCardNo()) != null){
				NewCadreRegistrationVO vo1 = addressMap.get(voterCardNo.trim()) ;
				if(vo1 != null){
					vo.setHouseNo(vo1.getHouseNo());
					vo.setStateId(vo1.getStateId());
					vo.setDistrictId(vo1.getDistrictId());
					vo.setConstituencyId(vo1.getConstituencyId());
					vo.setMandalId(vo1.getMandalId());
					vo.setVillageId(vo1.getVillageId());
					vo.setPincode(vo1.getPincode());
					
					vo.getPaymentGatewayVO().getSubList().addAll(vo.getPaymentGatewayVO().getSubList());
				}
			}
	  		*/
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
	  					}
	  				if(cadreRegistration.getRelationshipType().equalsIgnoreCase("Other")){
	  						relationTypes.add("Others");
	  				}else{
	  						relationTypes.add(cadreRegistration.getRelationshipType());
	  				}
	  				cadreRegistration.setVoterCadreNO(objects[7] != null ? objects[7].toString() : "");
	  				cadreRegistration.setMobileNo(objects[8] != null ? objects[8].toString() : "");
	  				cadreRegistration.setImagePath(objects[9] != null ? objects[9].toString() : "");
	  				cadreRegistration.setTotalImagePathStr("https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+cadreRegistration.getImagePath());
	  				returnList.add(cadreRegistration);
	  			}
  				
		  	}
	  			List<Object[]> relationDetails =  null;
	  			if(relationTypes != null && relationTypes.size() >0){
	  				 relationDetails = voterRelationDAO.getRelationDetails(relationTypes);
	  			}
	  			
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
				setCadreDetailsToVO(returnVO,tdpCadreList,familyVoterId,voterId);
			}else if(voterId != null && voterId.longValue() >0l){
				List<Object[]> voterList = voterDAO.getVoterDetailsByVoterId(voterId);
				setVoterDetailsToVO(returnVO,voterList);
			} 
			if(familyVoterId != null && familyVoterId.longValue() >0l){
				getFamilyVoterDetails(familyVoterId,returnVO);
			}else if(voterId != null && voterId.longValue() >0l){
				getFamilyVoterDetails(voterId,returnVO);
			}
			
			//Get LocaElectionBodyId
			Long localElectionBodyId = null;
			if(tdpCadreId != null && tdpCadreId.longValue() > 0l){//update scenario
				
				if(voterId != null && voterId.longValue() > 0l){
					
					localElectionBodyId = getLocalElectionBodyByVoterId(voterId);
					
				}else if(familyVoterId != null && familyVoterId.longValue() >0l){
					
					localElectionBodyId = getLocalElectionBodyByUserAddress(returnVO.getUserAddressId());
				}
			}else{//saving scenario.
				
					if(voterId != null && voterId.longValue() > 0l){
					
						localElectionBodyId = getLocalElectionBodyByVoterId(voterId);
					
					}else if(familyVoterId != null && familyVoterId.longValue() >0l){
					
						localElectionBodyId = getLocalElectionBodyByVoterId(familyVoterId);
					}
			}
			
			if(localElectionBodyId != null && localElectionBodyId > 0l){
				returnVO.setLocalElectionBodyId(localElectionBodyId);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			 LOG.error("Exception raised at getRegistrationPersonDetails", e);
		}
		return returnVO;
	}
	
	public Long getLocalElectionBodyByVoterId(Long voterId){
		
		Long localElectionBodyId = null;
		List<Long> lebList = boothPublicationVoterDAO.getLocalElectionBodyByVoterId(voterId);
		if( lebList != null && lebList.size() > 0 ){
			
			localElectionBodyId = lebList.get(0);
		}
		return localElectionBodyId;
	}

	public Long getLocalElectionBodyByUserAddress(Long userAddressId){
	   Long localElectionBodyId = null;
	   if(userAddressId != null && userAddressId > 0l){
		   localElectionBodyId = userAddressDAO.getLocalElectionBodyByUserAddress(userAddressId);
	   }
		return localElectionBodyId;
	}
	
	/**
	* @param  CadreRegistrationVO returnVO,TdpCadre tdpCadre
	* @return  void
	* @author Hymavathi 
	* @Description : 
	*  @since 10-October-2016
	*/
	public void setCadreDetailsToVO(NewCadreRegistrationVO returnVO,List<Object[]> tdpCadreList,Long familyVoterId,Long voterId){
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
			
			if((returnVO.getDobStr() == null || returnVO.getDobStr().toString().trim().length()<=0) && returnVO.getAge() != null){
			  
				Calendar startDate = new GregorianCalendar();
				Calendar endDate = new GregorianCalendar(); 
				
				endDate.setTime(new Date());

				int diffYear = endDate.get(Calendar.YEAR) - returnVO.getAge().intValue();
				
				returnVO.setDobStr(String.valueOf(diffYear)+"-01-01   ");
				
				}
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
			returnVO.setImagePath("https://mytdp.com/images/cadre_images/"+(objects[7]!=null?objects[7].toString():""));
			if(voterId != null && voterId.longValue() >0l){
				returnVO.setVoterRelationId(objects[19]!=null?(Long)objects[19]:0l);//voterId
				returnVO.setVoterCardNo(objects[18]!=null?objects[18].toString():"");//votercardNo
			}else if(familyVoterId != null && familyVoterId.longValue() >0l){
				returnVO.setFamilyVoterId(objects[19]!=null?(Long)objects[19]:0l);//familyvoterId
				returnVO.setVoterCardNumber(objects[18]!=null?objects[18].toString():"");//familyVotercardNo
			}
			
			returnVO.setConstituencyId(objects[20]!=null?objects[20].toString():"");//constituencyId
			returnVO.setNomineeRelationId(objects[21]!=null?(Long)objects[21]:0l);//nomineeRelationId
			//phototype
			if(objects[24]!=null){
				if(objects[24].toString().equalsIgnoreCase("NEW")){
					returnVO.setPhotoType("CADRE");
				}else{
					returnVO.setPhotoType(objects[24].toString());
				}
			}
			
			returnVO.setUserAddressId(objects[25]!=null?(Long)objects[25]:0l);

			String isDeleted=commonMethodsUtilService.getStringValueForObject(objects[23]);// ONLINE - 'O'
			if(isDeleted.trim().equalsIgnoreCase("O")){
				returnVO.setPaymentStatus(commonMethodsUtilService.getStringValueForObject(objects[22]));
			}
			
			
			Map<String,NewCadreRegistrationVO> addressMap = new HashMap<String, NewCadreRegistrationVO>(0);
			List<Long> voterIdsList = new ArrayList<Long>(0);
			voterIdsList.add(returnVO.getVoterRelationId());
			
			getAddressDetailsForVoter(addressMap,voterIdsList);
			if(addressMap.get(returnVO.getVoterCardNo()) != null){
				NewCadreRegistrationVO vo = addressMap.get(returnVO.getVoterCardNo()) ;
				if(vo != null){
					returnVO.setHouseNo(vo.getHouseNo());
					returnVO.setStateId(vo.getStateId());
					returnVO.setDistrictId(vo.getDistrictId());
					returnVO.setConstituencyId(vo.getConstituencyId());
					returnVO.setMandalId(vo.getMandalId());
					returnVO.setVillageId(vo.getVillageId());
					returnVO.setPincode(vo.getPincode());
					
					if(returnVO.getPaymentGatewayVO().getSubList() != null && returnVO.getPaymentGatewayVO().getSubList().size() == 0)
						returnVO.getPaymentGatewayVO().getSubList().addAll(vo.getPaymentGatewayVO().getSubList());
				}
			}
		}
		}
		}catch(Exception e){
			e.printStackTrace();
			 LOG.error("Exception raised at setCadreDetailsToVO", e);
		}
	}
	
	public void getAddressDetailsForVoter(Map<String,NewCadreRegistrationVO> addressMap,List<Long> voterIdsList){
		try { 
			List<Object[]> addressDetails = boothPublicationVoterDAO.getVoterLocationDetailsByVotersIdsList(new ArrayList<Long>(voterIdsList));
			
			if(addressDetails != null && addressDetails.size() > 0){
				NewCadreRegistrationVO address = new NewCadreRegistrationVO();
				
				 Object[] obj= addressDetails.get(0);
				 address.setHouseNo(commonMethodsUtilService.getStringValueForObject(obj[11]));
				 address.setStateId(obj[1]!=null?(Long)obj[1]:0L);
				 address.setDistrictId(obj[2]!=null?(Long)obj[2]:0L);
				 address.setConstituencyId(obj[3]!=null?obj[3].toString():null);
				/* 
				 if(address.getConstituencyId() != null){
					 List<Long> parliamentIds = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyIdByAssemblyConstituencyId(address.getConstituencyId());
					 if(parliamentIds != null && parliamentIds.size() > 0){
						 address.setParliamentConstituencyId(parliamentIds.get(0));
					 }
				 }*/
				 
				 if(obj[6]!=null)
				 {
					 address.setVillageId(Long.valueOf("1"+commonMethodsUtilService.getStringValueForObject(obj[6])));
					 address.setMandalId(obj[4]!=null?Long.valueOf("1"+commonMethodsUtilService.getStringValueForObject(obj[4])):null);
				 }
				 else
				 {
					 address.setMandalId(Long.valueOf("2"+commonMethodsUtilService.getStringValueForObject(obj[5])));
					 address.setVillageId(obj[7]!=null?Long.valueOf("2"+commonMethodsUtilService.getStringValueForObject(obj[7])):null);
					 
					 List<Object[]> wardsList = assemblyLocalElectionBodyWardDAO.findByLocalElectionBody(commonMethodsUtilService.getLongValueForObject(obj[5]),IConstants.DELIMITATION_YEAR.toString());
					 if(commonMethodsUtilService.isListOrSetValid(wardsList)){
						 List<PaymentGatewayVO> wardsListDtls = new ArrayList<PaymentGatewayVO>(0);
						// PaymentGatewayVO vo1 = new PaymentGatewayVO();
						 for (Object[] param : wardsList) {
							 PaymentGatewayVO vo = new PaymentGatewayVO();
							 vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							 vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							 
							 String[] strArr = vo.getName().split("-");
							 String wardName = "WARD";
							 if(strArr != null && strArr.length>0){
								 int length = strArr[1].toString().length();
								 if(length ==1)
									 wardName = wardName+"-00"+strArr[1].toString();
								 else if(length ==2)
									 wardName = wardName+"-0"+strArr[1].toString();
								 else
									 wardName = wardName+"-"+strArr[1].toString();
								 vo.setOrderNo(wardName);
							 }
							 if(!commonMethodsUtilService.getStringValueForObject(param[2]).isEmpty())
								 vo.setName(vo.getName()+"- ("+commonMethodsUtilService.getStringValueForObject(param[2])+")");
							 wardsListDtls.add(vo);
						}
						 
						 if(commonMethodsUtilService.isListOrSetValid(wardsListDtls)){
							 
							 Collections.sort(wardsListDtls, new Comparator<PaymentGatewayVO>() {
								public int compare(PaymentGatewayVO o1,PaymentGatewayVO o2) {
									return o1.getOrderNo().compareTo(o2.getOrderNo());
								}
							});
							// address.setPaymentGatewayVO(vo1);
							 if(address.getPaymentGatewayVO().getSubList() != null && address.getPaymentGatewayVO().getSubList().size() == 0)
								 address.getPaymentGatewayVO().getSubList().addAll(wardsListDtls);
						 }
					 }
				 }
				// address.setBoothId(obj[8] != null ? (Long)obj[8]:null);
				
				 addressMap.put(commonMethodsUtilService.getStringValueForObject(obj[10]) , address);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
				Set<String> voterCardNos = new HashSet<String>(0);
				Set<Long> voterIdsList = new HashSet<Long>(0);
				Map<String,NewCadreRegistrationVO> registrationStatusMap = new HashMap<String, NewCadreRegistrationVO>(0);
				for (Object[] param : voterList) {
					voterCardNos.add(commonMethodsUtilService.getStringValueForObject(param[8]));
					voterIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
				Map<String,NewCadreRegistrationVO> addressMap = new HashMap<String, NewCadreRegistrationVO>(0);
				voterIdsList.add(returnVO.getTdpCadreId());
				
				getAddressDetailsForVoter(addressMap,new ArrayList<Long>(voterIdsList));
				PaymentGatewayVO pamentGateWayVO  = null;
				if(commonMethodsUtilService.isListOrSetValid(voterCardNos)){
					List<Object[]> existingCAdreList = tdpCadreDAO.checkVoterCardNosListExists(voterCardNos);
					if(commonMethodsUtilService.isListOrSetValid(existingCAdreList)){
						for (Object[] param : existingCAdreList) {
							String isDeleted = commonMethodsUtilService.getStringValueForObject(param[3]);
							
							NewCadreRegistrationVO vo = new NewCadreRegistrationVO();
							
							vo.setMembershipNo(commonMethodsUtilService.getStringValueForObject(param[4]));
							vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[0]));
							vo.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[1]));
							
							if(isDeleted.trim().equalsIgnoreCase("O")){
								vo.setPaymentStatus(commonMethodsUtilService.getStringValueForObject(param[2]));
								pamentGateWayVO = paymentGatewayService.getPaymentBasicInfoByPaymentGateWayType(1L,vo.getMembershipNo().trim(),commonMethodsUtilService.getStringValueForObject(param[13]).trim(),"2016 CADRE ONLINE REGISTRATION","NORMAL REGISTRATION");
								if(pamentGateWayVO != null)
									returnVO.setPaymentGatewayVO(pamentGateWayVO);
							}
							else
								vo.setPaymentStatus("");
							
							registrationStatusMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), vo);
							
							returnVO.setGender(commonMethodsUtilService.getStringValueForObject(param[5]));
							returnVO.setAge(commonMethodsUtilService.getLongValueForObject(param[6]));
							returnVO.setDobStr(commonMethodsUtilService.getStringValueForObject(param[12]));
							returnVO.setMobileNumber(commonMethodsUtilService.getStringValueForObject(param[7]));
							returnVO.setEmail(commonMethodsUtilService.getStringValueForObject(param[8]));
							returnVO.setCasteId(commonMethodsUtilService.getLongValueForObject(param[9]));
							returnVO.setEducationId(commonMethodsUtilService.getLongValueForObject(param[10]));
							returnVO.setOccupationId(commonMethodsUtilService.getLongValueForObject(param[11]));
						}
					}
				}
				
				
				 
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
					if((returnVO.getDobStr() == null || returnVO.getDobStr().toString().trim().length()<=0) && returnVO.getAge() != null){
						  
						Calendar startDate = new GregorianCalendar();
						Calendar endDate = new GregorianCalendar(); 
						
						endDate.setTime(new Date());
		
						int diffYear = endDate.get(Calendar.YEAR) - returnVO.getAge().intValue();
						returnVO.setDobStr(String.valueOf(diffYear)+"-01-01   ");
						
						}
					returnVO.setImageBase64String(objects[5]!=null?objects[5].toString():"");//ImagePath
					returnVO.setPhotoType("VOTER");
					returnVO.setMobileNumber(objects[6]!=null?objects[6].toString():"");//mobileNo
					returnVO.setRelativeType(objects[7]!=null?objects[7].toString():"");//relativeType
					returnVO.setVoterCardNo(objects[8]!=null?objects[8].toString():"");//votercardNo
					returnVO.setConstituencyId(objects[9]!=null?objects[9].toString():"");//constituencyId
					returnVO.setImagePath("https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+(objects[5]!=null?objects[5].toString():""));
					
				}
				
				if(addressMap.get(returnVO.getVoterCardNo()) != null){
					NewCadreRegistrationVO vo = addressMap.get(returnVO.getVoterCardNo()) ;
					if(vo != null){
						returnVO.setHouseNo(vo.getHouseNo());
						returnVO.setStateId(vo.getStateId());
						returnVO.setDistrictId(vo.getDistrictId());
						returnVO.setConstituencyId(vo.getConstituencyId());
						returnVO.setMandalId(vo.getMandalId());
						returnVO.setVillageId(vo.getVillageId());
						returnVO.setPincode(vo.getPincode());
						if(returnVO.getPaymentGatewayVO().getSubList() != null && returnVO.getPaymentGatewayVO().getSubList().size() == 0)
							returnVO.getPaymentGatewayVO().getSubList().addAll(vo.getPaymentGatewayVO().getSubList());
					}
				}
				if(registrationStatusMap.get(returnVO.getVoterCardNo()) != null){
					NewCadreRegistrationVO vo = registrationStatusMap.get(returnVO.getVoterCardNo());
					if(vo != null){
						returnVO.setPaymentStatus(vo.getPaymentStatus());
						returnVO.setTdpCadreId(vo.getTdpCadreId());
						returnVO.setMembershipNo(vo.getMembershipNo());
					}
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
	
	/**
	* @param  cadreRegistrationVO
	* @return  String
	* @author Hymavathi 
	* @Description : Saving Cadre Details By Calling WebService
	*  @since 13-October-2016
	*/
	public CadreResponseVO savingCadreDetails(CadreRegistrationVO cadreRegistrationVO){  
		CadreResponseVO responceVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/saveFieldDataForCadre";
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         responceVO = webResource.accept("application/json").type("application/json").post(CadreResponseVO.class,cadreRegistrationVO);
	        
	         /*if(responceVO.getSaveStatus().equalsIgnoreCase("Success")){
	        	 return "SUCCESS";
	         }else{
	        	 return "FAIL";
	         }*/
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at savingCadreDetails", e);
	    }
	    return responceVO;    
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
		  Map<String,Long> totalRenewalCadreMap = new HashMap<String, Long>(0);
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
						  
						List<Object[]> returnObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(entry.getKey(),entry.getValue(),stateId,4l,null);
						  
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
					   Long accessLevelValue =0l;	
					     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
					    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
					     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
					    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
					     }else{
					    	 accessLevelValue = entry.getKey();	 
					     }
						List<Object[]> returnObjList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWise(accessLevelValue,new ArrayList<Long>(entry.getValue()), fromDate, toDate);
						  
						if(returnObjList != null && returnObjList.size() > 0){
							
							   for (Object[] param : returnObjList) {
								   
								 String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
								 totCadreMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
								 
							 }
						}
					 }  
				}
				if(locationLevelMap != null && locationLevelMap.size() > 0){
					
					for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
						  Long accessLevelValue =0l;	
						     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
						    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value 
						     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
						    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
						     }else{
						    	 accessLevelValue = entry.getKey();	 
						     }
						List<Object[]> returnRenewalObjList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountLocationWise(accessLevelValue,new ArrayList<Long>(entry.getValue()), fromDate, toDate);
						
						if(returnRenewalObjList != null && returnRenewalObjList.size() > 0){
							
							   for (Object[] param : returnRenewalObjList) {
								   
								 String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
								 totalRenewalCadreMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
								 
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
				//setting Renewal cadre  Count
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					
					  for(Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()){
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  
					    	   for(Long locationValueId:vo.getLocationValuesSet()){
					    		   
					    			 String key = vo.getLocationLevelId()+"-"+locationValueId; 
					    			 
					    			 if(totalRenewalCadreMap.get(key) != null){
					    				 
					    	    		 vo.setTotalRenewalCadreCount(vo.getTotalRenewalCadreCount()+totalRenewalCadreMap.get(key)); 
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
					    	    vo.setTotalNewCadreCount(vo.getTotalCadreCount()-vo.getTotalRenewalCadreCount());
					    	    vo.setTotalRenewalCadreCountPer(calculatePercantage(vo.getTotalRenewalCadreCount(), vo.getTotalTargetCount()));
					    	    vo.setTotalNewCadreCountPer(calculatePercantage(vo.getTotalNewCadreCount(),vo.getTotalTargetCount()));
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
	/**
	* @param Long activityMemberId
	* @param Long stateId
	* @param Long userTypeId
	* @param userId
	* @param fromDateStr
	* @param toDateStr
	* @return List<CadreReportVO>
	* @author Santosh 
	* @Description :This Service Method is used get cadre details based on user type. 
	* @since 14-OCT-2016
	*/
 public List<CadreReportVO> getCadreDetailsBasedOnUserType(Long activityMemberId,Long stateId,Long userTypeId,String fromDateStr,String toDateStr){
	 
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,Long>  cadreTarget2014Map = new HashMap<Long, Long>();
	 Map<Long,Long> cadreTarget2016Map = new HashMap<Long, Long>();
	 Map<Long,CadreReportVO> locationWiseCadreDetaislMap = new HashMap<Long, CadreReportVO>();
	 Map<Long,Set<Long>> userAccessLevelMap = new HashMap<Long, Set<Long>>();
	 Map<Long,String> locationIdAndNameMap = new HashMap<Long, String>();
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 Date toDate=null;
	 Date fromDate=null;
	 try{
		 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 toDate = sdf.parse(toDateStr);
			 fromDate = sdf.parse(fromDateStr);
		 }
		 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
			 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
				  Long accessLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				  Set<Long> accessLevelValuesSet= userAccessLevelMap.get(accessLevelId);
				   if(accessLevelValuesSet == null){
					   accessLevelValuesSet = new HashSet<Long>();
					   userAccessLevelMap.put(accessLevelId, accessLevelValuesSet);
				   }
				   accessLevelValuesSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));
			 }
		 }
		     // getting location name 
		     List<Object[]> locationIdAndNameObjList = userAddressDAO.getUserTypeWiseLocationName(stateId, userTypeId);
		     setRequiredLocationName(locationIdAndNameObjList,locationIdAndNameMap);
	 
	 if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
				List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(3l, null, stateId, 3l,activityMemberId);// 2014 target 
				setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
				List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(3l, null, stateId, 4l,activityMemberId);// 2016 target 
				setCadreTargetCntToMap(rtrn2016CadreTargetObjList,cadreTarget2016Map);
	  }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	     	  || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
			List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(5l,null, stateId, 3l,activityMemberId);// 2014 target 
			setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
			List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(5l,null, stateId, 4l,activityMemberId);// 2016 target 
			setCadreTargetCntToMap(rtrn2016CadreTargetObjList,cadreTarget2016Map);
	  }
		if(userAccessLevelMap != null && userAccessLevelMap.size() > 0){
			 for(Entry<Long,Set<Long>> entry:userAccessLevelMap.entrySet()){
					     List<Long> locationValue=null;
					     if(entry.getKey().longValue() == 2l){//state access
					    	 locationValue = new ArrayList<Long>(locationIdAndNameMap.keySet());// getting all district details
					     }else{
					    	 locationValue = new ArrayList<Long>(entry.getValue());	
					     }
					  //List<Object[]> rtrn2014CadreDtlsObjLst = tdpCadreDAO.getTotalCadreCountBasedOnUserType(entry.getKey(),entry.getValue(), stateId, null, null, 3l, userTypeId); //2014 total cadre
						List<Object[]> rtrn2014CadreDtlsObjLst = tdpCadreLocationInfoDAO.get2014TotalCadreCountBasedOnUserType(locationValue, userTypeId,activityMemberId);
						set2014CadreCountToMap(rtrn2014CadreDtlsObjLst, locationWiseCadreDetaislMap,locationIdAndNameMap);
						//List<Object[]> rtrnCadreDtlsObjLst = tdpCadreDAO.getTotalCadreCountBasedOnUserType(entry.getKey(),entry.getValue(), stateId, fromDate, toDate, 4l, userTypeId); //2016 total cadre
						List<Object[]> rtrnCadreDtlsObjLst = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountBasedOnUserType(locationValue, fromDate, toDate, userTypeId,activityMemberId);
						set2016CadreCountToMap(rtrnCadreDtlsObjLst,locationWiseCadreDetaislMap);
						List<Object[]> rtrnRenewalObjList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountBasedOnUserType(locationValue, fromDate, toDate, userTypeId,activityMemberId);
						setRenewalCountToMap(rtrnRenewalObjList,locationWiseCadreDetaislMap);
			 }
		 }
		 //calculating new cadre and percentage
		 calculateNewCadreAnddPercentage(locationWiseCadreDetaislMap,cadreTarget2014Map,cadreTarget2016Map);
		 //sorting list
		 if(locationWiseCadreDetaislMap != null && locationWiseCadreDetaislMap.size() > 0){
			  resultList = new ArrayList<CadreReportVO>(locationWiseCadreDetaislMap.values());
			 Collections.sort(resultList, cadreRegistrationCountDeccAsc);
		 }
	 }catch (Exception e) {
		 LOG.error("Exception raised in getCadreDetailsBasedOnUserType() in CadreRegistrationService service", e);	
	}
	 return resultList;
 }
 public void setRequiredLocationName(List<Object[]> objList, Map<Long,String> locationIdAndNameMap){
	 try{
		 if(objList != null && objList.size() > 0){
			for(Object[] param:objList) {
				locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));	 	
			}
		 }
	 }catch(Exception e){
		 LOG.error("Exception raised in setLocationName() in CadreRegistrationService service", e); 
	 }
 }
 public void set2014CadreCountToMap(List<Object[]> returnObjList,Map<Long,CadreReportVO> locationWiseCadreDetaislMap, Map<Long,String> locationIdAndNameMap){
	 try{
	  	 if(returnObjList != null && !returnObjList.isEmpty()){
	  		 for(Object[] param:returnObjList){
	  			 CadreReportVO locationVO = locationWiseCadreDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
	  			 if(locationVO == null){
	  				locationVO = new CadreReportVO();
	  				locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
	  				if(locationIdAndNameMap != null && locationIdAndNameMap.size() > 0){//setting location name
	  					locationVO.setLocationName(locationIdAndNameMap.get(locationVO.getLocationId()));
	  			 	}
	  				locationVO.setTotal2014CadreCnt(commonMethodsUtilService.getLongValueForObject(param[1]));
	  				locationWiseCadreDetaislMap.put(locationVO.getLocationId(), locationVO);
	  			 }
	  		 }
	  	 }
	 }catch(Exception e){
		 LOG.error("Exception raised in setCadreCountToMap() in CadreRegistrationService service", e);	 
	 }
 }
 public void set2016CadreCountToMap(List<Object[]> returnObjList,Map<Long,CadreReportVO> locationWiseCadreDetaislMap){
	 try{
		 if(returnObjList != null &&  !returnObjList.isEmpty()){
			 for(Object[] param:returnObjList){
				 CadreReportVO locationVO = locationWiseCadreDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				  if(locationVO != null){
					  locationVO.setTotal2016CadreCnt(commonMethodsUtilService.getLongValueForObject(param[1]));  
				  }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception raised in set2016CadreCountToMap() in CadreRegistrationService service", e); 
	 }  
 }
 public void setRenewalCountToMap(List<Object[]> returnObjList,Map<Long,CadreReportVO> locationWiseCadreDetaislMap){
	 try{
	   if(returnObjList != null && returnObjList.size() > 0){
		   for(Object[] param:returnObjList){
			   CadreReportVO locationVO = locationWiseCadreDetaislMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
			    if(locationVO != null ){
			    	locationVO.setTotal2016RenewalCadreCount(commonMethodsUtilService.getLongValueForObject(param[1]));
			    }
		   }
	   }
	 }catch(Exception e){
		 LOG.error("Exception raised in setRenewalCountToMap() in CadreRegistrationService service", e);	 
	 }
 }
 public void calculateNewCadreAnddPercentage(Map<Long,CadreReportVO> locationWiseCadreDetaislMap,Map<Long,Long> cadreTarget2014Map,Map<Long,Long> cadreTarget2016Map){
	 try{
		 if(locationWiseCadreDetaislMap != null && locationWiseCadreDetaislMap.size() > 0){
			 for(Entry<Long,CadreReportVO> entry:locationWiseCadreDetaislMap.entrySet()){
				 CadreReportVO locationVO = entry.getValue();
				 locationVO.setTotal2016NewCadreCount(locationVO.getTotal2016CadreCnt()-locationVO.getTotal2016RenewalCadreCount());//calculating 2016 new cadre
				 if(cadreTarget2014Map.get(entry.getKey()) != null){
					 locationVO.setTotal2014CadreTargetCnt(cadreTarget2014Map.get(entry.getKey()));
					 locationVO.setTotal2014CadrePer(calculatePercantage(locationVO.getTotal2014CadreCnt(),cadreTarget2014Map.get(entry.getKey())));	 
				 }
				 if(cadreTarget2016Map.get(entry.getKey()) != null){
					 Long target2016Count = cadreTarget2016Map.get(entry.getKey());
					 locationVO.setTotal2016CadreTargetCnt(target2016Count);
					 locationVO.setTotal2016CadrePer(calculatePercantage(locationVO.getTotal2016CadreCnt(), target2016Count)); 
					 locationVO.setTotal2016RenewalCadrePer(calculatePercantage(locationVO.getTotal2016RenewalCadreCount(),target2016Count));
					 locationVO.setTotal2016NewCadrePer(calculatePercantage(locationVO.getTotal2016NewCadreCount(), target2016Count));
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception raised in setRenewalCountToMap() in CadreRegistrationService service", e);	 
	 }
 }
 public static Comparator<CadreReportVO> cadreRegistrationCountDeccAsc = new Comparator<CadreReportVO>() {
		public int compare(CadreReportVO location2, CadreReportVO location1) {
		Double perc2 = location2.getTotal2016CadrePer();
		Double perc1 = location1.getTotal2016CadrePer();
		//dcending order of percantages.
		 return perc1.compareTo(perc2);  
		}
}; 
 public void setCadreTargetCntToMap(List<Object[]> returnObjList,Map<Long,Long> targetMap){
	 try{
	   if(returnObjList != null && returnObjList.size() > 0){
		   for(Object[] param:returnObjList){
			   targetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));//locationId & Target count
		   }
	   }
	 }catch(Exception e){
		LOG.error("Exception raised in setCadreTargetCntToMap() in CadreRegistrationService service", e); 
	 }
 }
 /**
	* @param Long stateId
	* @param Long locationType
	* @param fromDateStr
	* @param toDateStr
	* @return List<CadreReportVO>
	* @author Santosh 
	* @Description :This Service Method is used get cadre details Location wise. 
	* @since 14-OCT-2016
	*/
 public List<CadreReportVO> getLocationWiseCadreDetails(Long stateId,String locationType,String fromDateStr,String toDateStr,Long accessLevelId,List<Long> userAccessLevelValues){
	
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,Long>  cadreTarget2014Map = new HashMap<Long, Long>();
	 Map<Long,Long> cadreTarget2016Map = new HashMap<Long, Long>();
	 Map<Long,CadreReportVO> locationWiseCadreDetaislMap = new HashMap<Long, CadreReportVO>();
	 Map<Long,String> locationIdAndNameMap = new HashMap<Long, String>();
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 Date toDate=null;
	 Date fromDate=null;
	 
	 
	 try{
		 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 toDate = sdf.parse(toDateStr);
			 fromDate = sdf.parse(fromDateStr);
		 }
		 if(locationType != null && locationType.equalsIgnoreCase("District")){
				List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(3l, null, stateId, 3l,null);// 2014 target 
				setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
				List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(3l, null, stateId, 4l,null);// 2016 target 
				setCadreTargetCntToMap(rtrn2016CadreTargetObjList,cadreTarget2016Map);
		 }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
				List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(5l, null, stateId, 3l,null);// 2014 target 
				setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
				List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(5l, null, stateId, 4l,null);// 2016 target 
				setCadreTargetCntToMap(rtrn2016CadreTargetObjList,cadreTarget2016Map);
		 }
		    
			 // setting location name 
			  List<Object[]> locationIdsAndNameObjList = userAddressDAO.getLocationTypeWiseLocationName(stateId,locationType,accessLevelId,userAccessLevelValues);
			  setRequiredLocationName(locationIdsAndNameObjList,locationIdAndNameMap);
		   
		 
			 Long locationScopeId=0l;
		     if(locationType != null && locationType.equalsIgnoreCase("District")){
		    	 locationScopeId = 3l;
		     }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		    	 locationScopeId = 4l; 
		     }
		     userAccessLevelValues = new ArrayList<Long>(locationIdAndNameMap.keySet()); 
		  
		   List<Object[]> total2014CadreObjList = tdpCadreLocationInfoDAO.get2014TotalCadreCountLocationWise(locationScopeId, userAccessLevelValues);//2014 cadre
		  set2014CadreCountToMap(total2014CadreObjList,locationWiseCadreDetaislMap,locationIdAndNameMap);
		 // List<Object[]> total2016CadreObjList = tdpCadreDAO.getTotalCadreCountLocationWiseBasedOnYear(locationType, stateId, fromDate, toDate, 4l,accessLevelId,userAccessLevelValues);//2016 cadre
		  List<Object[]> total2016CadreObjList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWise(locationScopeId, userAccessLevelValues, fromDate, toDate);
		  set2016CadreCountToMap(total2016CadreObjList,locationWiseCadreDetaislMap);
		 // List<Object[]> total2016RenewalCadreObjList = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreCntLocationWise(stateId, locationType, fromDate, toDate,accessLevelId,userAccessLevelValues);
		  List<Object[]> total2016RenewalCadreObjList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountLocationWise(locationScopeId, userAccessLevelValues, fromDate, toDate);
		  setRenewalCountToMap(total2016RenewalCadreObjList,locationWiseCadreDetaislMap);
		  //calculating new cadre and percentage
		  calculateNewCadreAnddPercentage(locationWiseCadreDetaislMap,cadreTarget2014Map,cadreTarget2016Map);
		  //sortring 
		  if(locationWiseCadreDetaislMap != null && locationWiseCadreDetaislMap.size() > 0){
			  resultList = new ArrayList<CadreReportVO>(locationWiseCadreDetaislMap.values());
		 }
		 if(resultList != null && resultList.size() > 0){
			  Collections.sort(resultList, cadreRegistrationCountDeccAsc);
		 }
		 Long veryGoodCnt=0l;
		 Long goodCnt=0l;
		 Long okCnt=0l;
		 Long  poorCnt=0l;
		 Long veryPoorCnt=0l;
		 if(resultList != null && resultList.size() > 0){
			 for(CadreReportVO cadreReportVO:resultList){
				   if(cadreReportVO.getTotal2016CadrePer() > 100){
					   veryGoodCnt = veryGoodCnt+1;
					}
					if(cadreReportVO.getTotal2016CadrePer() >= 90 && cadreReportVO.getTotal2016CadrePer()<=100){
				      goodCnt=goodCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() >= 80 && cadreReportVO.getTotal2016CadrePer()<=90){
					  okCnt=okCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() >= 60 && cadreReportVO.getTotal2016CadrePer() <= 80){
					  poorCnt=poorCnt+1;
				   }
				   if(cadreReportVO.getTotal2016CadrePer() <= 60){
					 veryPoorCnt=veryPoorCnt+1;
				   }
		  }
		  resultList.get(0).setAllConstituencyCnt(new Long(resultList.size()));
		  resultList.get(0).setVeryGoodCnt(veryGoodCnt);
		  resultList.get(0).setGoodCnt(goodCnt);
		  resultList.get(0).setOkCnt(okCnt);
		  resultList.get(0).setPoorCnt(poorCnt);
		  resultList.get(0).setVeryPoorCnt(veryPoorCnt); 
		 }
		  
		  
	 }catch(Exception e){
		 LOG.error("Exception raised in getLocationWiseCadreDetails() in CadreRegistrationService service", e);	 
	 }
	 return resultList;
 }
    /**
	* @author Santosh 
	* @Description :This Service Method is used get Ap and TS districts. 
	* @since 15-OCT-2016
	*/
 public CadreReportVO getApAndTsDistrictList(){
	 CadreReportVO resultVO = new CadreReportVO();
	 List<CadreReportVO> apDistrictList = new ArrayList<CadreReportVO>();
	 List<CadreReportVO> tsDistrictList = new ArrayList<CadreReportVO>();
	 try{
		List<Object[]> rtrnapDistrictList = districtDAO.getDistrictListBystateId(1l);
		setDistrictDataToList(rtrnapDistrictList,apDistrictList);
		List<Object[]> rtrnTsDistrictList = districtDAO.getDistrictListBystateId(36l);
		setDistrictDataToList(rtrnTsDistrictList,tsDistrictList);
		resultVO.setSubList1(apDistrictList);
		resultVO.setSubList2(tsDistrictList);
	 }catch(Exception e){
	  LOG.error("Exception raised in getApAndTsDistrictList() in CadreRegistrationService service", e);
	  return null;
	 }
	 return resultVO;
 }
 public void setDistrictDataToList(List<Object[]> rtrnDistrictList,List<CadreReportVO> districtList){
	 try{
		if(rtrnDistrictList != null && rtrnDistrictList.size() > 0){
			for(Object[] param:rtrnDistrictList){
				 CadreReportVO districtVO = new CadreReportVO();
				 districtVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 districtVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
				 districtList.add(districtVO);
			}
		}
	 }catch(Exception e){
		 LOG.error("Exception raised in setDistrictDataToList() in CadreRegistrationService service", e);	 
	 }
 }
 public List<IdAndNameVO> getOccupationList(){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try{
		List<Occupation> results = occupationDAO.getOccupationList();
		for(Occupation objects:results){
			IdAndNameVO vo = new IdAndNameVO();
			vo.setId(objects.getOccupationId());
			vo.setName(objects.getOccupation());
			returnList.add(vo);
			
		}
		}catch(Exception e){
			LOG.error("Exception raised in getOccupationList in CadreRegistrationService service", e);
		}
		return returnList;
	}
 public List<CadreRegistratedCountVO> getSourceOfRegistrationDtls(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date frmDt = null;
			Date toDt = null;
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				frmDt = sdf.parse(startDate);
				toDt = sdf.parse(endDate);  
			}
			Long accessLvlId = null;
			List<Long> accessLvlValue = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				accessLvlId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					accessLvlValue.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountSourceWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			List<String> dataSourceList = new ArrayList<String>();
			Map<String,Long> dataSourceAndCadreCountMap = new HashMap<String,Long>();
			Map<String,Long> dataSourceAndRenewalCadreCountMap = new HashMap<String,Long>();
			if(totalRegCadreList != null && totalRegCadreList.size() > 0){
				for(Object[] param : totalRegCadreList){
					dataSourceList.add(param[0] != null ? param[0].toString() : "");  
					dataSourceAndCadreCountMap.put(param[0] != null ? param[0].toString() : "", param[1] != null ? (Long)param[1] : 0l);  
				}
			}
			List<Object[]> totalRegCadreRenewalList = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreSourceWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			if(totalRegCadreRenewalList != null && totalRegCadreRenewalList.size() > 0){
				for(Object[] param : totalRegCadreRenewalList){
					dataSourceAndRenewalCadreCountMap.put(param[0] != null ? param[0].toString() : "", param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//calculate new count
			List<CadreRegistratedCountVO>  cadreRegistratedCountVOs = new ArrayList<CadreRegistratedCountVO>();
			CadreRegistratedCountVO cadreRegistratedCountVO = null;
			if(dataSourceList != null && dataSourceList.size() > 0){
				for(String source : dataSourceList){
					cadreRegistratedCountVO = new CadreRegistratedCountVO();
					cadreRegistratedCountVO.setSourceName(source);
					cadreRegistratedCountVO.setTotalCount(dataSourceAndCadreCountMap.get(source));
					if(dataSourceAndRenewalCadreCountMap.get(source) != null){
						cadreRegistratedCountVO.setRenewalCount(dataSourceAndRenewalCadreCountMap.get(source));
						cadreRegistratedCountVO.setNewCount(dataSourceAndCadreCountMap.get(source) - dataSourceAndRenewalCadreCountMap.get(source));
					}else{
						cadreRegistratedCountVO.setNewCount(dataSourceAndCadreCountMap.get(source));
					}
					cadreRegistratedCountVOs.add(cadreRegistratedCountVO);
				}
			}
			
			return cadreRegistratedCountVOs;  
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getSourceOfRegistrationDtls() in CoreDashboardCadreRegistrationService service", e); 
		}
		return null;
		
	}
	/**aaaaaa
	* @param  Long parentActivityMemberId
	* @param  Long childUserTypeId
	* @param Long stateId
	* @return List<UserTypeVO>
	* @author Swadhin Lenka
	* @Description :This Service Method is used to get selected child member and for userType.. 
	* @since 14-Oct-2016
	*/
	public List<UserTypeVO> getSelectedChildTypeMembersForCadreRegistration(Long parentActivityMemberId,List<Long> childUserTypeIds,Long stateId,String fromDateStr,String toDateStr){
		try{
			Date toDay = new Date();
			List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0); 
			Map<String,Long> targetCdrCntMap = new HashMap<String, Long>(0);
			Map<String,Long> regCdrCntMap = new HashMap<String, Long>(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date frmDt = null;
			Date toDt=null;
			if(toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0){
				frmDt = sdf.parse(fromDateStr);
				toDt = sdf.parse(toDateStr);
			}
			//calling generic method to get childActivityMembers and there location level and values
			ActivityMemberVO activityMemberVO=null;
			Map<Long,UserTypeVO> childActivityMembersMap=null;
			Map<Long,Set<Long>> locationLevelIdsMap=null;
			Map<String,String>     nameForLocationMap=null;
			activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
			childActivityMembersMap= activityMemberVO.getActivityMembersMap();
			locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
			
			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
			}
			//check location category
			String nameCategory = null;
			String category = null;
			if(nameForLocationMap != null && nameForLocationMap.size() > 0){ 
				List<String> nameList = new ArrayList<String>(nameForLocationMap.values());
				nameCategory = nameList.get(0);
				category = nameCategory.substring(nameCategory.lastIndexOf(" ")+1);
			}
			//calculate target count
			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					/*Long accessLevelValue =0l;  
					if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency
						accessLevelValue = 10l; //region scope 10  means parliament constituency 
					}else if(entry.getKey().longValue()==5l){
						accessLevelValue = 4l;  
					}else{  
						accessLevelValue = entry.getKey();   
					}*/ // xxxx
					List<Object[]> returnObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(entry.getKey(),entry.getValue(),stateId,4l,null);
					if(returnObjList != null && returnObjList.size() > 0){
						for (Object[] param : returnObjList) {
							String locationLevelAndId = entry.getKey()+"_"+param[0].toString();
							targetCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
						}
					}
				}
			}
			//calculate registered count
			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					Long accessLevelValue =0l;  
					if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency
						accessLevelValue = 10l; //region scope 10  means parliament constituency 
					}else if(entry.getKey().longValue()==5l){
						accessLevelValue = 4l;  
					}else{
						accessLevelValue = entry.getKey();   
					}
					//List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,frmDt, toDt,4l);
					List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,frmDt, toDt);
					if(totalRegCadreList != null && totalRegCadreList.size() > 0){
						for (Object[] param : totalRegCadreList) {
							String locationLevelAndId = entry.getKey()+"_"+param[0].toString();
							regCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l); 
						}
					}
				}
			}
			//Pushing target Count
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				for(UserTypeVO vo:childActivityMembersMap.values()){
					for(Long locationValueId:vo.getLocationValuesSet()){
						String key = vo.getLocationLevelId()+"_"+locationValueId;
						if(targetCdrCntMap.get(key) != null){
							vo.setTotalTargetCount(vo.getTotalTargetCount()+targetCdrCntMap.get(key));
						}
					}
				}
			}
			//pushing reg count 2016
			IdNameVO nameVO = null;
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				for(UserTypeVO vo:childActivityMembersMap.values()){
					for(Long locationValueId:vo.getLocationValuesSet()){
						String key = vo.getLocationLevelId()+"_"+locationValueId;
						if(regCdrCntMap.get(key) != null){
							nameVO = new IdNameVO();
							nameVO.setId(locationValueId);
							nameVO.setName(nameForLocationMap.get(key).substring(0, nameForLocationMap.get(key).lastIndexOf(" ")));
							nameVO.setCount(regCdrCntMap.get(key));
							vo.getSubLocationList().add(nameVO);
							vo.setTotalCadreCount(vo.getTotalCadreCount()+regCdrCntMap.get(key));
						}
					}
				}
			}
			//push 2016 percentage
			Long ttlCdr = 0l;
			Long ttlTarget = 0l;
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				for(UserTypeVO vo:childActivityMembersMap.values()){
					for(Long locationValueId:vo.getLocationValuesSet()){
						String key = vo.getLocationLevelId()+"_"+locationValueId;
						if(targetCdrCntMap.get(key) != null){
							ttlTarget = vo.getTotalTargetCount();
							ttlCdr = vo.getTotalCadreCount();
							vo.setTotalCadreCountPer(calculatePercantage(ttlCdr, ttlTarget));
						}
					}
				}
			}
			//calculate registered count for today  
			regCdrCntMap.clear();
			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					//List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId,toDay, toDay,4l);
					Long accessLevelValue =0l;  
					if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency
						accessLevelValue = 10l; //region scope 10  means parliament constituency 
					}else if(entry.getKey().longValue()==5l){
						accessLevelValue = 4l;  
					}else{      
						accessLevelValue = entry.getKey();   
					}
					List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,toDay, toDay);
					if(totalRegCadreList != null && totalRegCadreList.size() > 0){
						for (Object[] param : totalRegCadreList) {
							String locationLevelAndId = entry.getKey()+"_"+param[0].toString();
							regCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
						}
					}
				}
			}
			//pushing reg count for today
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){  
				for(UserTypeVO vo:childActivityMembersMap.values()){
					for(Long locationValueId:vo.getLocationValuesSet()){
						String key = vo.getLocationLevelId()+"_"+locationValueId;
						if(regCdrCntMap.get(key) != null){
							setTodayCount(locationValueId,vo.getSubLocationList(),regCdrCntMap.get(key));
							//nameVO.setCount(regCdrCntMap.get(key));
							//vo.getSubLocationList().add(nameVO);
							vo.setTotalCadreCountToday(vo.getTotalCadreCountToday()+regCdrCntMap.get(key));
						}
					}
				}
			}
			//calculate registered count for 2014  
			regCdrCntMap.clear();
			if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
					Long accessLevelValue =0l;  
					if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency
						accessLevelValue = 10l; //region scope 10  means parliament constituency 
					}else if(entry.getKey().longValue()==5l){
						accessLevelValue = 4l;  
					}else{
						accessLevelValue = entry.getKey();   
					}
					//List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId,toDay, toDay,3l);
					List<Object[]> totalRegCadreList = tdpCadreLocationInfoDAO.get2014TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId);
					if(totalRegCadreList != null && totalRegCadreList.size() > 0){  
						for (Object[] param : totalRegCadreList) {
							String locationLevelAndId = entry.getKey()+"_"+param[0].toString();  
							regCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
						}
					}
				}      
			}
			//pushing reg count for 2014
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				for(UserTypeVO vo:childActivityMembersMap.values()){
					for(Long locationValueId:vo.getLocationValuesSet()){
						String key = vo.getLocationLevelId()+"_"+locationValueId;
						if(regCdrCntMap.get(key) != null){
							vo.setTotalTargetCount2014(vo.getTotalTargetCount2014()+regCdrCntMap.get(key));
						}  
					}
				}
			}  
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				resultList.addAll(childActivityMembersMap.values());
			}
			if(resultList != null && resultList.size() > 0){
				Collections.sort(resultList, cadreMemberEligibleAttendedPercDesc);
			}
			return resultList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getSelectedChildTypeMembersForCadreRegistration() in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}
	public static Comparator<UserTypeVO> cadreMemberEligibleAttendedPercDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {

			Long perc2 = member2.getTotalCadreCount();  
			Long perc1 = member1.getTotalCadreCount();      
			//descending order of percantages.
			 return perc1.compareTo(perc2);  
		}
	}; 
	public static Comparator<UserTypeVO> trainingMemberEligibleAttendedPercDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {

			Double perc2 = member2.getTotalCadreCountPer();  
			Double perc1 = member1.getTotalCadreCountPer();
			//descending order of percantages.
			 return perc1.compareTo(perc2);
		}
	}; 
	public void setTodayCount(Long locationValueId,List<IdNameVO> idNameVOs,Long count){
		if(idNameVOs != null && idNameVOs.size() > 0){
			for(IdNameVO vo : idNameVOs){
				if(vo.getId() == locationValueId){
					vo.setWishCount(count);//today count
				}
			}
		}
	}
	public CadreRegistratedCountVO getStateDtls(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			Date frmDt = null;
			Date toDt = null;
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				frmDt = sdf.parse(startDate);
				toDt = sdf.parse(endDate);
			}
			Long totalCadre = 0l;
			Long totalCadreToday = 0l;
			CadreRegistratedCountVO  cadreRegistratedCountVO = new CadreRegistratedCountVO();
			Long accessLvlId = null;
			List<Long> accessLvlValue = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				accessLvlId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					accessLvlValue.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,4l);
			Long totalRegCadre2016 = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			//get total reg cadre
			if(totalRegCadre2016 != null){
				totalCadre = totalRegCadre2016;
			}
			/*if(totalRegCadreList != null && totalRegCadreList.size() > 0){
				for(Object[] param : totalRegCadreList){
					if(accessLvlValue.contains((Long)param[0]))
					totalCadre += param[1] != null ? (Long)param[1] : 0l;
				}
			}*/
			//List<Object[]> totalRegCadreListForToday = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,today, today,4l);
			Long totalRegCadreForToday = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,toDt, toDt);
			cadreRegistratedCountVO.setTotalCount(totalCadre);
			//get total reg cadre for today
			if(totalRegCadreForToday != null){
				totalCadreToday = totalRegCadreForToday;
			}
			/*if(totalRegCadreListForToday != null && totalRegCadreListForToday.size() > 0){
				for(Object[] param : totalRegCadreListForToday){
					if(accessLvlValue.contains((Long)param[0]))
					totalCadreToday += param[1] != null ? (Long)param[1] : 0l;
				}
			}*/  
			cadreRegistratedCountVO.setTodayTotalCount(totalCadreToday);
			//get target Count
			Long totalTarget = 0l;
			
			List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l,null);
			
			if(totalTargetList != null && totalTargetList.size() > 0){
				for(Object[] param : totalTargetList){
					if(accessLvlValue.contains((Long)param[0]))
						totalTarget += ((Long)param[1]);
				}
			}  
			cadreRegistratedCountVO.setTarget(totalTarget);
			cadreRegistratedCountVO.setTotalPercent(calculatePercantage(cadreRegistratedCountVO.getTotalCount(),totalTarget));
			cadreRegistratedCountVO.setTotalPercentToday(calculatePercantage(cadreRegistratedCountVO.getTodayTotalCount(),totalTarget));
	
			//total const started
			Long ttlContStarted = 0l;  
			if(stateId == 1l){
				
				//ttlContStarted = tdpCadreDAO.getTotalConstituencyForCdrRegStarted(1l);
				ttlContStarted = tdpCadreDateWiseInfoDAO.getTotalConstituencyForCdrRegStarted(1l);
				if(ttlContStarted != null){
					cadreRegistratedCountVO.setConstStartedCount(ttlContStarted);   
					cadreRegistratedCountVO.setConstStartedCountPer(calculatePercantage(ttlContStarted,175l));
				}
			}else{
				//ttlContStarted = tdpCadreDAO.getTotalConstituencyForCdrRegStarted(36l);
				ttlContStarted = tdpCadreDateWiseInfoDAO.getTotalConstituencyForCdrRegStarted(36l);
				if(ttlContStarted != null){
					cadreRegistratedCountVO.setConstStartedCount(ttlContStarted);
					cadreRegistratedCountVO.setConstStartedCountPer(calculatePercantage(ttlContStarted,119l));
				}
			}  
			
			//total tab user in field AP
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			//pending    
			//Long ttlTabUserInField = tdpCadreDAO.getTotalTabUserWorkingInField(accessLvlId,new HashSet<Long>(accessLvlValue),stateId,lastOneHourTime,today,"oneHour");
			String dtStr = sdf1.format(today);
			Date now = sdf1.parse(dtStr);
			Long ttlTabUserInField = tabUserEnrollmentInfoDAO.getTotalTabUserWorkingInField(accessLvlId,accessLvlValue,stateId,now);    
			       
			cadreRegistratedCountVO.setInField(ttlTabUserInField != null ? ttlTabUserInField : 0l);    
			
			
			//total submitted data
			//Long ttlSubmittedDataToday = tabUserEnrollmentInfoDAO.getTotalRecordSubmitedByTabUser(today, 1l);
			cadreRegistratedCountVO.setTotalSubmittedToday(totalCadreToday != null ? totalCadreToday : 0l);  
			
			return cadreRegistratedCountVO;         
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getStateDtls in CoreDashboardCadreRegistrationService service", e);
		}
		return null;  
	}
	public CadreRegistratedCountVO getTotalNewRenewalCadreStateWise(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			//getTotalNewRenewalCadreStateWise
			Date frmDt = null;
			Date toDt = null;
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				frmDt = sdf.parse(startDate);
				toDt = sdf.parse(endDate);
			}
			Long totalCadre = 0l;  
			Long totalNew = 0l;
			Long totalRenewal = 0l;
			Long totalCadreToday = 0l;
			Long totalCadreTodayNew = 0l;
			Long totalCadreTodayRenewal = 0l;
			CadreRegistratedCountVO  cadreRegistratedCountVO = new CadreRegistratedCountVO();
			Long accessLvlId = null;
			List<Long> accessLvlValue = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				accessLvlId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					accessLvlValue.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			
			//List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,4l);
			Long totalRegCadre2016 = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			//List<Object[]> totalRegCadreRenewalList = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			Long totalRegCadreRenewal2016 = tdpCadreDateWiseInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			//get total reg cadre
			if(totalRegCadre2016 != null){
				totalCadre = totalRegCadre2016;
			}
			/*if(totalRegCadreList != null && totalRegCadreList.size() > 0){ 
				for(Object[] param : totalRegCadreList){
					if(accessLvlValue.contains((Long)param[0]))
					totalCadre += param[1] != null ? (Long)param[1] : 0l;
				}
			}*/
			//get total renewal cadre
			if(totalRegCadreRenewal2016 != null){
				totalRenewal = totalRegCadreRenewal2016;
			}
			/*if(totalRegCadreRenewalList != null && totalRegCadreRenewalList.size() > 0){
				for(Object[] param : totalRegCadreRenewalList){
					if(accessLvlValue.contains((Long)param[0]))  
					totalRenewal += param[1] != null ? (Long)param[1] : 0l;
				}
			}*/
			//get tolal new cadre
			totalNew = totalCadre - totalRenewal;
			//get target Count
			
			Long totalTarget = 0l;
			
			List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l,null);
			if(totalTargetList != null && totalTargetList.size() > 0){
				for(Object[] param : totalTargetList){
					if(accessLvlValue.contains((Long)param[0]))
						totalTarget += ((Long)param[1]);
				}
			}
			cadreRegistratedCountVO.setTotalCount(totalCadre);
			cadreRegistratedCountVO.setRenewalCount(totalRenewal);
			cadreRegistratedCountVO.setNewCount(totalNew);
			cadreRegistratedCountVO.setTarget(totalTarget);
			cadreRegistratedCountVO.setTotalPercent(calculatePercantage(cadreRegistratedCountVO.getTotalCount(),totalTarget));
			
			//List<Object[]> totalRegCadreListForToday = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,today, today,4l);
			Long totalRegCadreForToday = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,toDt, toDt);
			//List<Object[]> totalRegCadreRenewalListForToday = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,today, today);
			Long totalRegCadreRenewalForToday = tdpCadreDateWiseInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,toDt, toDt);
			//get total reg cadre for today
			totalCadreToday = 0l;
			if(totalRegCadreForToday != null){
				totalCadreToday = totalRegCadreForToday;
			}
			/*if(totalRegCadreListForToday != null && totalRegCadreListForToday.size() > 0){  
				for(Object[] param : totalRegCadreListForToday){
					if(accessLvlValue.contains((Long)param[0]))
					totalCadreToday += param[1] != null ? (Long)param[1] : 0l;
				}
			}*/
			//get total renewal cadre 
			totalCadreTodayRenewal = 0l;
			if(totalRegCadreRenewalForToday != null){
				totalCadreTodayRenewal = totalRegCadreRenewalForToday;
			}
			/*if(totalRegCadreRenewalListForToday != null && totalRegCadreRenewalListForToday.size() > 0){
				for(Object[] param : totalRegCadreRenewalListForToday){
					if(accessLvlValue.contains((Long)param[0]))  
					totalCadreTodayRenewal += param[1] != null ? (Long)param[1] : 0l;  
				}
			}*/
			//get tolal new cadre
			totalCadreTodayNew = totalCadreToday - totalCadreTodayRenewal;  
			  
			cadreRegistratedCountVO.setTodayTotalCount(totalCadreToday);
			cadreRegistratedCountVO.setTodayRenewalCount(totalCadreTodayRenewal);
			cadreRegistratedCountVO.setTodayNewCount(totalCadreTodayNew);   
			cadreRegistratedCountVO.setTotalPercentToday(calculatePercantage(cadreRegistratedCountVO.getTodayTotalCount(),totalTarget));
			
			return cadreRegistratedCountVO;   
		}catch(Exception e){   
			e.printStackTrace();  
			LOG.error("Exception raised in getTotalNewRenewalCadreStateWise in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}
	/**
	* @param  Long parentActivityMemberId
	* @param  Long childUserTypeId
	* @param Long stateId
	* @return List<UserTypeVO>
	* @author Swadhin Lenka 
	* @Description :This Service Method is used to get selected child member and for userType.. 
	*  @since 15-Oct-2016
	*/
	public List<UserTypeVO> getSelectedChildTypeMembersForCadreReg(Long parentActivityMemberId,List<Long> childUserTypeIds,Long stateId,String startDate, String endDate){
	
		try{
			List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0);
			Map<String,Long> targetCdrCntMap = new HashMap<String, Long>(0);
			Map<String,Long> regCdrCntMap = new HashMap<String, Long>(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date toDate= new Date();
			Date frmDt = null;
			Date toDt = null;
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				frmDt = sdf.parse(startDate);
				toDt = sdf.parse(endDate);
			}
			   //calling generic method to get childActivityMembers and there location level and values
			  ActivityMemberVO activityMemberVO=null;
			  Map<Long,UserTypeVO> childActivityMembersMap=null;
			  
			  
			  Map<Long,Set<Long>> locationLevelIdsMap=null;
			  Map<String,String>     nameForLocationMap=null;
			 
			  if(childUserTypeIds != null && childUserTypeIds.size()>0){
				   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
			  }
			   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
			   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
		
			   
			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
				  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
			  }
			  //get target countxxxx
		 	  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		 		  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		 			  List<Object[]> returnObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(entry.getKey(),entry.getValue(),stateId,4l,null);
		 			  if(returnObjList != null && returnObjList.size() > 0){
		 				  for (Object[] param : returnObjList) {
							   String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
							   targetCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
						   }
					   }
		 		  }  
		 	  } 
		 	  //get reg cadre count
		 	  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		 		  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		 			  Long accessLevelValue =0l;  
		 			  if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency
		 				  accessLevelValue = 10l; //region scope 10  means parliament constituency 
		 			  }else if(entry.getKey().longValue()==5l){
		 				  accessLevelValue = 4l;  
		 			  }else{
		 				  accessLevelValue = entry.getKey();   
		 			  }
		 			  //List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId,frmDt, toDt,4l);
		 			  List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,frmDt, toDt);
		 			  if(totalRegCadreList != null && totalRegCadreList.size() > 0){
				   			for (Object[] param : totalRegCadreList) {  
				   				String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
				   				regCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
				   			}
				   		}
		 		  }  
		 	  } 
		 	  //Pushing Target Count
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		 		  for(UserTypeVO vo:childActivityMembersMap.values()){
		 			  for(Long locationValueId:vo.getLocationValuesSet()){
		 				  String key = vo.getLocationLevelId()+"-"+locationValueId;
		 				  if(targetCdrCntMap.get(key) != null){
		 					  vo.setTotalTargetCount(vo.getTotalTargetCount()+targetCdrCntMap.get(key)); 
		 				  }
		 			  }
		 		  }
		 	  }    
			  //Pushing reg cadre Count
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		 		  for(UserTypeVO vo:childActivityMembersMap.values()){
		 			  for(Long locationValueId:vo.getLocationValuesSet()){
		 				  String key = vo.getLocationLevelId()+"-"+locationValueId;   
		 				  if(regCdrCntMap.get(key) != null){
		 					  vo.setTotalCadreCount(vo.getTotalCadreCount()+regCdrCntMap.get(key)); 
		 				  }
		 			  }
		 		  }
		 	  }
		 	  //calculate registered count for today
		 	  regCdrCntMap.clear();
		 	  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
		 		  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
		 			  Long accessLevelValue =0l;  
		 			  if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency
		 				  accessLevelValue = 10l; //region scope 10  means parliament constituency 
		 			  }else if(entry.getKey().longValue()==5l){
		 				  accessLevelValue = 4l;  
		 			  }else{
		 				  accessLevelValue = entry.getKey();   
		 			  }
		 			  //List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(entry.getKey(),new ArrayList<Long>(entry.getValue()),stateId,toDate, toDate,4l);
		 			  List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,toDate, toDate);
		 			  if(totalRegCadreList != null && totalRegCadreList.size() > 0){
		 				  for (Object[] param : totalRegCadreList) {
		 					  String locationLevelAndId = entry.getKey()+"-"+param[0].toString();
		 					  regCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
		 				  }
		 			  }
		 		  }
		 	  }
		 	  //pushing reg count for today
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		 		  for(UserTypeVO vo:childActivityMembersMap.values()){
		 			  for(Long locationValueId:vo.getLocationValuesSet()){
		 				  String key = vo.getLocationLevelId()+"-"+locationValueId;
		 				  if(regCdrCntMap.get(key) != null){
		 					  vo.setTotalCadreCountToday(vo.getTotalCadreCountToday()+regCdrCntMap.get(key));
		 				  }  
		 			  }
		 		  }
		 	  }
		 	  //Setting Location name  
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		 		  for(UserTypeVO vo:childActivityMembersMap.values()){
		 			  for(Long locationValueId:vo.getLocationValuesSet()){
		 				  String key = vo.getLocationLevelId()+"_"+locationValueId;
		 				  if(vo.getLocationName() == null || vo.getLocationName().isEmpty()){
		 					  vo.setLocationName(nameForLocationMap.get(key));
		 				  }else{
		 					  vo.setLocationName(vo.getLocationName()+","+ nameForLocationMap.get(key) );  
		 				  }
		 			  }
		 		  }
		 	  }    
		 	  //Calculating percentage      
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		 		  for(UserTypeVO vo:childActivityMembersMap.values()){
		 			  vo.setTotalCadreCountPer(calculatePercantage(vo.getTotalCadreCount(),vo.getTotalTargetCount()));
		 		  }
		 	  }
		 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
		 		  resultList.addAll(childActivityMembersMap.values());
		 	  }
		 	  if(resultList != null && resultList.size() > 0)  
		 	  {
		 		  Collections.sort(resultList, trainingMemberEligibleAttendedPercDesc);
		 	  }
		 	 return resultList;	  
		}catch(Exception e){
			LOG.error("Error occured at getSelectedChildTypeMembersForTrainingProgram() in CoreDashboardMainService ",e);
		}
		return null;
	}

	public String generatingAndSavingOTPDetails(String mobileNo){
		
		String status = null;
		try {
			
			List<Object[]> existingOTPDtls = tabUserOtpDetailsDAO.isExistOTPDetails(mobileNo,new DateUtilService().getCurrentDateAndTime());
			if(existingOTPDtls != null && existingOTPDtls.size()>0L){
				Object[] obj = existingOTPDtls.get(existingOTPDtls.size()-1);
				
				String otp = commonMethodsUtilService.getStringValueForObject(obj[0]);
				String referenceNo = String.valueOf(commonMethodsUtilService.getStringValueForObject(obj[1]));
				String dateStr = commonMethodsUtilService.getStringValueForObject(obj[2]);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(dateStr);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				// next 15 min date time
				
				 long duration = (Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis());
		    	 if(duration < 15 * 60 * 1000)// if duration less than 15 min 
		    	 {
		    		    calendar.add(Calendar.MINUTE, 15);
						String finalDateStr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(calendar.getTime());
						String message = "your OTP is "+otp+" for Reference Id # " +referenceNo+" This OTP Validate upto Next "+finalDateStr+" .";
						String[] phoneNumbers = {mobileNo.toString()};
						smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
						status=referenceNo;
		    	 }
				else{
					status=getNewOTPDetails(mobileNo);
				}
				
			}else{
				status=getNewOTPDetails(mobileNo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in generatingAndSavingOTPDetails in CoreDashboardCadreRegistrationService service", e);
			return "failure";
		}
		return status;
	}
	
	public String getNewOTPDetails(String mobileNo){
		TabUserOtpDetails tabUserOtpDetails = null;
		try {
			RandomNumberGeneraion rnd = new RandomNumberGeneraion();
			int otpRand = 0;
			int refRand = 0;
			
			while(otpRand <= 0 && refRand <= 0){
				 otpRand = rnd.randomGenerator(6);
				 refRand = rnd.randomGenerator(6);
			}
				String refeRenceNo = String.valueOf(refRand);
				String otpNum = String.valueOf(otpRand);
				String message = "your OTP is "+otpRand+" for Reference Id # " +refRand+" This OTP Validate for Next 15 mins.";
				String[] phoneNumbers = {mobileNo.toString()};
				smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
				
				tabUserOtpDetails = new TabUserOtpDetails();
				//tabUserOtpDetails.setTabUserInfoId(tabUserInfoId);
				//tabUserOtpDetails.setCadreSurveyUserId(cadreSurveyUserId);
				tabUserOtpDetails.setMobileNo(mobileNo);
				tabUserOtpDetails.setOtpNo(otpNum);
				tabUserOtpDetails.setReferenceId(refeRenceNo);
				tabUserOtpDetails.setGeneratedTime(dateUtilService.getCurrentDateAndTime());
				tabUserOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				tabUserOtpDetails.setIsValid("Y");
				
				tabUserOtpDetails = tabUserOtpDetailsDAO.save(tabUserOtpDetails);
				return refeRenceNo;
		} catch (Exception e) {
			LOG.error("Exception raised in generatingAndSavingOTPDetails in CoreDashboardCadreRegistrationService service", e);
			return "failure";
		}
	}
	public CadreRegistratedCountVO getEnumerationDtlsForMem(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{
			CadreRegistratedCountVO cadreRegistratedCountVO = new CadreRegistratedCountVO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date frmDt = null;
			Date toDt = null;
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				frmDt = sdf.parse(startDate);
				toDt = sdf.parse(endDate);  
			}
			Long accessLvlId = null;
			List<Long> accessLvlValue = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				accessLvlId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					accessLvlValue.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//Long ttlTabUserInField = 0l;  
			//total logged in today
			Date today = new Date();
			String dt = sdf1.format(today);
			Date tDate = sdf1.parse(dt);    
			Long ttlTabUserInField = tdpCadreDAO.getTotalTabUserWorkingInField(accessLvlId,new HashSet<Long>(accessLvlValue),stateId,tDate,tDate,"toDay");
			cadreRegistratedCountVO.setTodayFieldMembersCount(ttlTabUserInField != null ? ttlTabUserInField : 0l);  
			//now in the field
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);  
			Date lastOneHourTime = cal.getTime();
			
			Long ttlTabUserInFieldNow = tdpCadreDAO.getTotalTabUserWorkingInField(accessLvlId,new HashSet<Long>(accessLvlValue),stateId,lastOneHourTime,today,"oneHour");
			cadreRegistratedCountVO.setInField(ttlTabUserInFieldNow != null ? ttlTabUserInFieldNow : 0l); 
			//today submitted data
			Long totalCadreToday = 0l;
			Long totalRegCadreForToday = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,today, today);
			//List<Object[]> totalRegCadreListForToday = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,today, today,4l);
			/*if(totalRegCadreListForToday != null && totalRegCadreListForToday.size() > 0){
				for(Object[] param : totalRegCadreListForToday){  
					if(accessLvlValue.contains((Long)param[0]))
					totalCadreToday += param[1] != null ? (Long)param[1] : 0l;
				}
			}*/
			if(totalRegCadreForToday != null){    
				totalCadreToday = totalRegCadreForToday;
			}
			cadreRegistratedCountVO.setTodayTotalCount(totalCadreToday);
			return cadreRegistratedCountVO;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in generatingAndSavingOTPDetails in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}
	public List<CadreRegistratedCountVO> getDtlsOfBellowLvlMember(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{  
			List<CadreRegistratedCountVO> cadreRegistratedCountVOs = new ArrayList<CadreRegistratedCountVO>();
			CadreRegistratedCountVO cadreRegistratedCountVO = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date frmDt = null;
			Date toDt = null;
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				frmDt = sdf.parse(startDate);
				toDt = sdf.parse(endDate);  
			}
			Long accessLvlId = null;
			List<Long> accessLvlValue = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				accessLvlId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					accessLvlValue.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//get location id and name
			
			List<Object[]> locationIdAndNameList = tdpCadreDAO.getLocationIdAndName(accessLvlId,accessLvlValue,stateId);
			if(locationIdAndNameList != null && locationIdAndNameList.size() > 0){
				for(Object[] param: locationIdAndNameList){
					cadreRegistratedCountVO = new CadreRegistratedCountVO();
					cadreRegistratedCountVO.setId(param[0] != null ? (Long)param[0] : 0l);
					cadreRegistratedCountVO.setSourceName(param[1] != null ? param[1].toString() : "");
					cadreRegistratedCountVOs.add(cadreRegistratedCountVO);
				}
			}
			//get 2014 total target location wise 
			List<Object[]> totalTargetList2014 = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 3l,null);
			if(totalTargetList2014 != null && totalTargetList2014.size() > 0){
				for(Object[] param : totalTargetList2014){
					if(accessLvlValue.contains((Long)param[0])){
						setTarget2014ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}
				}
			}
			Long accessLevelValue =0l;      
			if(accessLvlId.longValue() == 4l){// user level 4 means parliament constituency
				accessLevelValue = 10l; //region scope 10  means parliament constituency 
			}else if(accessLvlId.longValue()==5l){
				accessLevelValue = 4l;  
			}else{
				accessLevelValue = accessLvlId;     
			}
			//get 2014 totalcadre location wise//xxxx 
			//List<Object[]> totalRegCadreList2014 = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,3l);
			List<Object[]> totalRegCadreList2014 = tdpCadreLocationInfoCountDAO.getTotalCadreCountLocationWise2014(accessLevelValue,accessLvlValue);
			if(totalRegCadreList2014 != null && totalRegCadreList2014.size() > 0){  
				for(Object[] param : totalRegCadreList2014){
					if(accessLvlValue.contains((Long)param[0])){
						setCdrCount2014ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}
				}
			}  
			//get 2016 total target location wise
			//List<Object[]> totalTargetList2016 = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l);
			
			List<Object[]> totalTargetList2016 = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId,4l,null);
			
			if(totalTargetList2016 != null && totalTargetList2016.size() > 0){
				for(Object[] param : totalTargetList2016){
					if(accessLvlValue.contains((Long)param[0])){
						setTarget2016ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}  
				}
			}
			//get 2016 total cadre location wise
			
			//List<Object[]> totalRegCadreList2016 = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,4l);
			List<Object[]> totalRegCadreList2016 = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,accessLvlValue,stateId,frmDt, toDt);
			if(totalRegCadreList2016 != null && totalRegCadreList2016.size() > 0){
				for(Object[] param : totalRegCadreList2016){
					if(accessLvlValue.contains((Long)param[0])){
						setCdrCount2016ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}
				}
			}
			//get 2016 renewal cadre  //
			List<Object[]> totalRegCadreRenewalList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountLocationWiseCount(accessLevelValue,accessLvlValue,stateId,frmDt, toDt);
			if(totalRegCadreRenewalList != null && totalRegCadreRenewalList.size() > 0){
				for(Object[] param : totalRegCadreRenewalList){  
					if(accessLvlValue.contains((Long)param[0])){  
						setRenewCdrCount2016ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]); 
					}  
				}
			}
			//get 2016 new cadre
			if(cadreRegistratedCountVOs.size() > 0){
				for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
					vo.setNewCount(vo.getTotalCount() - vo.getRenewalCount());
				}
			}
			//calculate and push % for 2014
			if(cadreRegistratedCountVOs.size() > 0){
				for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
					vo.setCadreCountPer2014(calculatePercantage(vo.getTotalCount2014(),vo.getTarget2014()));
				}
			}
			//calculate and push % for renewal count 2016
			if(cadreRegistratedCountVOs.size() > 0){
				for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
					vo.setRenewalPerCount(calculatePercantage(vo.getRenewalCount(),vo.getTarget()).toString());
				}
			}
			//calculate and push % for new count 2016
			if(cadreRegistratedCountVOs.size() > 0){
				for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
					vo.setNewPercCnt(calculatePercantage(vo.getNewCount(),vo.getTarget()).toString());
				}      
			}
			return cadreRegistratedCountVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in generatingAndSavingOTPDetails in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}
	public void setTarget2014ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					vo.setTarget2014(count);
				}
			}
		}
	}
	public void setCdrCount2014ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					vo.setTotalCount2014(count);
				}
			}
		}  
	}
	public void setTarget2016ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					vo.setTarget(count);
				}
			}
		}
	}
	public void setCdrCount2016ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					vo.setTotalCount(count);
				}
			}
		}
	}
	public void setRenewCdrCount2016ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					vo.setRenewalCount(count);
				}
			}
		}
	}

 public String getOtpStatus(Long cadreId,String otp){
	 String status = null;
		try {
			  Date currentTime = dateUtilService.getCurrentDateAndTime();
              TdpCadre tdpCadre=tdpCadreDAO.getTdpCadreDetailsByOtp(cadreId);
              String mobileNumber=tdpCadre.getMobileNo();
			//Long tabDetsId = tabUserOtpDetailsDAO.checkOTPDetails(mobileNumber,otp,currentTime);
              List<Object[]> existingOTPDtls = tabUserOtpDetailsDAO.isExistOTPDetails(mobileNumber,currentTime);
  			if(existingOTPDtls != null && existingOTPDtls.size()>0L){
  				Object[] obj = existingOTPDtls.get(existingOTPDtls.size()-1);
  				Long tabDetsId = commonMethodsUtilService.getLongValueForObject(obj[3]);
  				String originalotp = commonMethodsUtilService.getStringValueForObject(obj[0]);
  				
				if(originalotp.toString().trim().equalsIgnoreCase(otp.toString().trim()) && tabDetsId != null && tabDetsId.longValue() > 0l){
					TabUserOtpDetails tabUserOtpDetails = tabUserOtpDetailsDAO.get(tabDetsId);
					tabUserOtpDetails.setIsValid("N");
					tabUserOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					tabUserOtpDetails = tabUserOtpDetailsDAO.save(tabUserOtpDetails);
					
					status = "success";
				}
				else
					status = "failure";
  			}
  			else
  				status = "failure";
  			
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception Occured in checkOTPDetails() in CodeDashboardCadreRegistrationService class.",e);
		}
		return status;
     }
 public NewCadreRegistrationVO validateUpdateVoterDetails(String voterCardNo){
	 NewCadreRegistrationVO newCadreRegistrationVO = new NewCadreRegistrationVO();
	 
	 try {
	 
		 Voter tdpVoter = null;
		 Voter voter = boothPublicationVoterDAO.getVoterByVoterIDCardNo(voterCardNo);
		 if(voter != null){ 
			 newCadreRegistrationVO.setNomineeName("Voter Exist");
			 tdpVoter =  tdpCadreDAO.getTdpCadreVoterByvoterId(voter.getVoterId());
			 if(tdpVoter != null){
				 newCadreRegistrationVO.setNameType("Cadre");
			}else{
				 newCadreRegistrationVO.setNameType("No Cadre");
				 newCadreRegistrationVO.setVoterRelationId(voter.getVoterId() !=null?voter.getVoterId():0l);//voterId
				 newCadreRegistrationVO.setLastName(voter.getName() !=null?voter.getName():"");//Name
				 newCadreRegistrationVO.setGender(voter.getGender()!=null?voter.getGender():"");//gender
				 newCadreRegistrationVO.setAge(voter.getAge()!=null?voter.getAge():0l);//age 
				 newCadreRegistrationVO.setRelativeType(voter.getRelationshipType()!=null?voter.getRelationshipType():"");//relativeType
				 newCadreRegistrationVO.setVoterCardNo(voter.getVoterIDCardNo()!=null?voter.getVoterIDCardNo():"");//votercardNo
			}
		 }else{
			 newCadreRegistrationVO.setNomineeName("Voter Not Exist");
		 }
		 
		 
		 
		 
	 } catch (Exception e) {
			LOG.error("Exception Occured in validateUpdateVoterDetails() in CoreDashboardCadreRegistrationService class.",e);
	 }
		return newCadreRegistrationVO;
 }
 public IdAndNameVO getVoterInfo(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{      
			IdAndNameVO idNameVO = new IdAndNameVO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
			Date frmDt = null;
			Date toDt = null;
			if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
				frmDt = sdf.parse(startDate);
				toDt = sdf.parse(endDate);  
			}
			Long accessLvlId = null;
			List<Long> accessLvlValue = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				accessLvlId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					accessLvlValue.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			Long ownVoterCount = tdpCadreDAO.getCadreWithOwnVoter(accessLvlId,accessLvlValue);
			Long familyVoterCount = tdpCadreDAO.getCadreWithFamilyVoter(accessLvlId, accessLvlValue);  
			idNameVO.setId(ownVoterCount);
			idNameVO.setAttenteeCount(familyVoterCount);
			return idNameVO;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in generatingAndSavingOTPDetails in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}
 
}
