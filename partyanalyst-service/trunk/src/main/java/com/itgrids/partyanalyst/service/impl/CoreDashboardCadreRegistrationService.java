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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreRegUserTabUserDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyTehsilDAO;
import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoSourceDAO;
import com.itgrids.partyanalyst.dao.ITabUserInfoDAO;
import com.itgrids.partyanalyst.dao.ITabUserLocationDetailsDAO;
import com.itgrids.partyanalyst.dao.ITabUserOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreHourRegInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreOnlineDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreSmsLeaderLocationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CadreBasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.dto.FieldReportVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.SurveyInfoVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.TabUserOtpDetails;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreOnline;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
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
    private ITabUserEnrollmentInfoSourceDAO tabUserEnrollmentInfoSourceDAO;
    private IDistrictConstituenciesDAO districtConstituenciesDAO;
    private IConstituencyDAO constituencyDAO;
    private ITdpCadreOnlineDAO tdpCadreOnlineDAO;
    private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
    private IConstituencyTehsilDAO constituencyTehsilDAO;    
    private ITdpCadreSmsLeaderLocationDAO tdpCadreSmsLeaderLocationDAO;
    private ITdpCadreHourRegInfoDAO tdpCadreHourRegInfoDAO;
    private ICadreRegistrationService cadreRegistrationService;
    private ICadreSurveyUserDAO cadreSurveyUserDAO;
    private ITabUserLocationDetailsDAO tabUserLocationDetailsDAO;
    private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
    private ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO;
    private ICadreRegUserTabUserDAO cadreRegUserTabUserDAO;
	
	
	
	public ICadreRegUserTabUserDAO getCadreRegUserTabUserDAO() {
		return cadreRegUserTabUserDAO;
	}

	public void setCadreRegUserTabUserDAO(
			ICadreRegUserTabUserDAO cadreRegUserTabUserDAO) {
		this.cadreRegUserTabUserDAO = cadreRegUserTabUserDAO;
	}
    
	public void setTdpCadreSmsLeaderLocationDAO(
			ITdpCadreSmsLeaderLocationDAO tdpCadreSmsLeaderLocationDAO) {
		this.tdpCadreSmsLeaderLocationDAO = tdpCadreSmsLeaderLocationDAO;
	}
	public ITdpCadreOnlineDAO getTdpCadreOnlineDAO() {
		return tdpCadreOnlineDAO;
	}
	public void setTdpCadreOnlineDAO(ITdpCadreOnlineDAO tdpCadreOnlineDAO) {
		this.tdpCadreOnlineDAO = tdpCadreOnlineDAO;
	}
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
	public void setTabUserEnrollmentInfoSourceDAO(
			ITabUserEnrollmentInfoSourceDAO tabUserEnrollmentInfoSourceDAO) {
		this.tabUserEnrollmentInfoSourceDAO = tabUserEnrollmentInfoSourceDAO;
	}
	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public void setConstituencyTehsilDAO(
			IConstituencyTehsilDAO constituencyTehsilDAO) {
		this.constituencyTehsilDAO = constituencyTehsilDAO;
	}
	public void setTdpCadreHourRegInfoDAO(
			ITdpCadreHourRegInfoDAO tdpCadreHourRegInfoDAO) {
		this.tdpCadreHourRegInfoDAO = tdpCadreHourRegInfoDAO;
	}
	
	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}
	public void setCadreRegistrationService(ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}
	public void setTabUserLocationDetailsDAO(
			ITabUserLocationDetailsDAO tabUserLocationDetailsDAO) {
		this.tabUserLocationDetailsDAO = tabUserLocationDetailsDAO;
	}
	public void setCadreSurveyUserAssignDetailsDAO(
			ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO) {
		this.cadreSurveyUserAssignDetailsDAO = cadreSurveyUserAssignDetailsDAO;
	}
	
	public void setTdpCadreUserHourRegInfoDAO(
			ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO) {
		this.tdpCadreUserHourRegInfoDAO = tdpCadreUserHourRegInfoDAO;
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
		Date date = null;
	    try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");  
	    	Date today = dateUtilService.getCurrentDateAndTime();
	    	date = tdpCadreDAO.getLastUpdatedTime(today);
	    	status = sdf.format(date);
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
	  		//Long publicatioDateId = IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID;
	  		Long publicatioDateId = boothPublicationVoterDAO.getPublicationDateIdByVoterID(voterId);
	  		List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterDetails(voterId,publicatioDateId);
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
	  		List<Long> voterIdsList = new ArrayList<Long>(0);
	  		voterIdsList.add(voterId);
	  		vo.setVoterCardNo(voterCardNo.trim());
	  		Map<String,NewCadreRegistrationVO> addressMap = new HashMap<String, NewCadreRegistrationVO>(0);
	  		getAddressDetailsForVoter(addressMap,voterIdsList);
	  		
	  		if(addressMap.get(vo.getVoterCardNo()) != null){
				NewCadreRegistrationVO vo1 = addressMap.get(voterCardNo.trim()) ;
				if(vo1 != null){
					if(vo.getHouseNo() == null || vo.getHouseNo().trim().isEmpty())
						vo.setHouseNo(vo1.getHouseNo());
					if(vo.getStateId() == null || vo.getStateId().longValue()==0L)
						vo.setStateId(vo1.getStateId());
					if(vo.getDistrictId() == null || vo.getDistrictId().longValue()==0L)
						vo.setDistrictId(vo1.getDistrictId());
					if(vo.getConstituencyId() == null || vo.getConstituencyId().trim().equalsIgnoreCase("0"))
						vo.setConstituencyId(vo1.getConstituencyId());
					if(vo.getMandalId() == null || vo.getMandalId().longValue()==0L)
						vo.setMandalId(vo1.getMandalId());
					if(vo.getVillageId() == null || vo.getVillageId().longValue()==0L)
						vo.setVillageId(vo1.getVillageId());
					if(vo.getPincode() == null || vo.getPincode().longValue()==0L)
						vo.setPincode(vo1.getPincode());
					
					if(vo.getPaymentGatewayVO().getSubList() == null || vo.getPaymentGatewayVO().getSubList().size() == 0)
						vo.getPaymentGatewayVO().getSubList().addAll(vo.getPaymentGatewayVO().getSubList());
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
				//Long publicationDateId = IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID;
				Long publicationDateId = boothPublicationVoterDAO.getPublicationDateIdByVoterID(voterId);
				List<Object[]> voterList = voterDAO.getVoterDetailsByVoterId(voterId,publicationDateId);
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
			returnVO.setDobStr(objects[6]!=null?objects[6].toString().substring(0,10):"");//DOB
			
			if((returnVO.getDobStr() == null || returnVO.getDobStr().toString().trim().length()<=0) && returnVO.getAge() != null){
			  
				Calendar startDate = new GregorianCalendar();
				Calendar endDate = new GregorianCalendar(); 
				
				endDate.setTime(new Date());

				int diffYear = endDate.get(Calendar.YEAR) - returnVO.getAge().intValue();
				
				returnVO.setDobStr(String.valueOf(diffYear)+"-01-01");
				
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

			/*String isDeleted=commonMethodsUtilService.getStringValueForObject(objects[23]);// ONLINE - 'O'
			if(isDeleted.trim().equalsIgnoreCase("O")){
				returnVO.setPaymentStatus(commonMethodsUtilService.getStringValueForObject(objects[22]));
			}*/
			returnVO.setPaymentStatus(commonMethodsUtilService.getStringValueForObject(objects[22]));
			if(returnVO.getPaymentStatus().trim().equalsIgnoreCase(IConstants.NOT_PAID_STATUS)){
				String otherAmountType = commonMethodsUtilService.getStringValueForObject(objects[26]);
				String refNo = commonMethodsUtilService.getStringValueForObject(objects[27]);
				returnVO.setNameType(otherAmountType);
				PaymentGatewayVO pamentGateWayVO = paymentGatewayService.getPaymentBasicInfoByPaymentGateWayType(1L,returnVO.getMemberTypeId().trim(),refNo.trim(),"2016 CADRE ONLINE REGISTRATION","NORMAL REGISTRATION",otherAmountType);
				if(pamentGateWayVO != null)
					returnVO.setPaymentGatewayVO(pamentGateWayVO);
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
					
					if(returnVO.getPaymentGatewayVO().getSubList() == null || returnVO.getPaymentGatewayVO().getSubList().size() == 0)
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
							
							if(commonMethodsUtilService.getStringValueForObject(param[2]).trim().equalsIgnoreCase(IConstants.NOT_PAID_STATUS)){
								vo.setPaymentStatus(IConstants.NOT_PAID_STATUS);
								String otherAmountType = commonMethodsUtilService.getStringValueForObject(param[14]);
								vo.setNameType(otherAmountType);
								pamentGateWayVO = paymentGatewayService.getPaymentBasicInfoByPaymentGateWayType(1L,vo.getMembershipNo().trim(),commonMethodsUtilService.getStringValueForObject(param[13]).trim(),"2016 CADRE ONLINE REGISTRATION","NORMAL REGISTRATION",otherAmountType);
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

					returnVO.setDobStr(objects[4]!=null?objects[4].toString().substring(0,10):"");//DOB
					if((returnVO.getDobStr() == null || returnVO.getDobStr().toString().trim().length()<=0) && returnVO.getAge() != null){
						  
						Calendar startDate = new GregorianCalendar();
						Calendar endDate = new GregorianCalendar(); 
						
						endDate.setTime(new Date());
		
						int diffYear = endDate.get(Calendar.YEAR) - returnVO.getAge().intValue();
						returnVO.setDobStr(String.valueOf(diffYear)+"-01-01");
						
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
						if(returnVO.getPaymentGatewayVO().getSubList() == null || returnVO.getPaymentGatewayVO().getSubList().size() == 0)
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
		 Gson gson = new Gson();
		 LOG.error("entered into ONLINE for "+cadreRegistrationVO.getMobileNumber()+" - VOTER ID - "+cadreRegistrationVO.getVoterId()+" registration savingCadreDetails() start Time : "+new DateUtilService().getCurrentDateAndTimeInStringFormat()+" with json Format: "+gson.toJson(cadreRegistrationVO));
		CadreResponseVO responceVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/saveFieldDataForCadre";
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         responceVO = webResource.accept("application/json").type("application/json").post(CadreResponseVO.class,cadreRegistrationVO);
	        
	         if(responceVO.getSaveStatus().equalsIgnoreCase("Success") && cadreRegistrationVO.getDataSourceType() != null && 
	        		   cadreRegistrationVO.getDataSourceType().trim().equalsIgnoreCase("WEB")){
	        	/*
	        	 String mobileNo = cadreRegistrationVO.getMobileNumber();
	        	 if(mobileNo != null && mobileNo.length()>10)
					mobileNo = mobileNo.substring(mobileNo.length()-10, mobileNo.length());
				cadreRegistrationService.sendSMSInTelugu(mobileNo.trim(), commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava("\u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C38\u0C2D\u0C4D\u0C2F\u0C24\u0C4D\u0C35\u0C02 \u0C24\u0C40\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C2E\u0C32\u0C41. ")+"Ref. No: "+responceVO.getRefNo()));
				*/
	         }				
	         else if(responceVO.getSaveStatus().equalsIgnoreCase("Success") && responceVO.getTdpCadreId() != null && responceVO.getTdpCadreId().longValue()>0L &&
	        		 cadreRegistrationVO.getShipAddress() != null && !cadreRegistrationVO.getShipAddress().isEmpty()){
	        	 LOG.error("entered into ONLINE registration savingCadreDetails() end Time : "+cadreRegistrationVO.getMobileNumber()+" - VOTER ID - "+cadreRegistrationVO.getVoterId()+" with json Format: "+gson.toJson(cadreRegistrationVO));
	        	 TdpCadreOnline  tdpCadreOnline = new TdpCadreOnline();
	        	 tdpCadreOnline.setTdpCadreId(responceVO.getTdpCadreId());
	        	 tdpCadreOnline.setShipAddress(cadreRegistrationVO.getShipAddress());
	        	 tdpCadreOnline.setPermanentAddress(cadreRegistrationVO.getShipAddress());
	        	 tdpCadreOnlineDAO.save(tdpCadreOnline);
	        	 //return "SUCCESS";
	         }else{
	        	;// return "FAIL";
	         }
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at savingCadreDetails", e);
	      LOG.error(" Exception occured in ONLINE for "+cadreRegistrationVO.getMobileNumber()+" - VOTER ID - "+cadreRegistrationVO.getVoterId()+" registration savingCadreDetails() end Time : "+new DateUtilService().getCurrentDateAndTimeInStringFormat()+" with json Format: "+gson.toJson(cadreRegistrationVO));
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
   public List<List<UserTypeVO>> getUserTypeWiseTotalCadreRegistrationCount(Long activityMemberId,Long stateId,Long userTypeId,Long userId,String fromDateStr,String toDateStr,String sortingType){
		 
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
					     Long accessLevelValue =0l;	
					     if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency in the case of core dashboard
					    	 accessLevelValue = 10l; //region scope 10  means parliament constituency in intermediate table so that we are replacing value
					     }else if(entry.getKey().longValue()==5l){// user level 5 means constituency in the case of core dashboard
					    	 accessLevelValue = 4l;  //region scope 4  means constituency in intermediate table so that we are replacing value
					     }else{
					    	 accessLevelValue = entry.getKey();	 
					     }
						List<Object[]> returnObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(accessLevelValue,entry.getValue(),4l,null,null);
						  
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
				//Update the locationValuesSet whose locationValuesSet contains districtId[517-Visakhapatnam Rural]
				// get the constituency id of Visakhapatnam Rural and update the locationValuesSet and locationLevelId.
				Set<Long> locationIdSet = null;
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					
					  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {  
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  locationIdSet = vo.getLocationValuesSet();
					    	  if(locationIdSet.contains(517l)){
					    		  List<Long> constIds = districtConstituenciesDAO.getConstituenciesOfDistrictById(517l);
					    		  if(constIds != null && constIds.size() > 0){
					    			  locationIdSet.addAll(constIds);  
					    		  }
					    		  vo.setLocationLevelId(5l);
					    	  }
					      }
				     }  
				}
				//Update the locationValuesSet whose locationValuesSet contains districtId[13-Visakhapatnam]
				// get the constituency id of Visakhapatnam and constituency Id of other dists then update the locationValuesSet and locationLevelId.
				List<Long> totalLocationIds = null;
				if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
					
					  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {    
						  
					      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
					      
					      for(UserTypeVO vo:userTypeMap.values()){
					    	  locationIdSet = vo.getLocationValuesSet();
					    	  if(locationIdSet.contains(13l)){
					    		  totalLocationIds = new ArrayList<Long>();
					    		  List<Long> extraConstIds = districtConstituenciesDAO.getConstituenciesOfDistrictById(517l);
					    		  List<Object[]> allConstIds = constituencyDAO.getConstituenciesList(new ArrayList<Long>(locationIdSet));
					    		  if(allConstIds != null && allConstIds.size() > 0){
					    			  for(Object[] param : allConstIds){
					    				  totalLocationIds.add(param[2] != null ? (Long)param[2] : 0l);
					    			  }
					    			  if(extraConstIds != null && extraConstIds.size() > 0){
					    				  totalLocationIds.removeAll(extraConstIds);
					    			  }
					    		  }
					    		  if(totalLocationIds != null && totalLocationIds.size() > 0){
					    			  locationIdSet.clear();
					    			  locationIdSet.addAll(totalLocationIds); 
					    			  vo.setLocationLevelId(5l);
					    		  }
					    		  
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
						if(sortingType != null && sortingType.equalsIgnoreCase("TargetWise")){
							Collections.sort(memberList, cadreRegistrationCountPercDesc);	
						}else if(sortingType != null && sortingType.equalsIgnoreCase("2016CadreWise")){
							Collections.sort(memberList, cadreRegistrationCountDesc);
						}
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
	   public static Comparator<UserTypeVO> cadreRegistrationCountDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {
		Long count2 = member2.getTotalCadreCount();
		Long count1 = member1.getTotalCadreCount();
		//descending order of percantages.
		 return count1.compareTo(count2);
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
 public List<CadreReportVO> getCadreDetailsBasedOnUserType(Long activityMemberId,Long stateId,Long userTypeId,String fromDateStr,String toDateStr,String sortingType){
	 
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,Long>  cadreTarget2014Map = new HashMap<Long, Long>();
	 Map<Long,Long> cadreTarget2016Map = new HashMap<Long, Long>();
	 Map<Long,CadreReportVO> locationWiseCadreDetaislMap = new HashMap<Long, CadreReportVO>();
	 Map<Long,CadreReportVO> VskhptnmAndVskhptnmRrlDtlsMap = new HashMap<Long, CadreReportVO>();
	 Map<Long,Set<Long>> userAccessLevelMap = new HashMap<Long, Set<Long>>();
	 Map<Long,String> locationIdAndNameMap = new HashMap<Long, String>();
	 Long userAccessLevelId = 0l;
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
				  userAccessLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
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
				List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(4l, null,3l,activityMemberId,"District");// 2014 target 
				setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
				List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(4l, null, 4l,activityMemberId,"District");// 2016 target 
				setCadreTargetCntToMap(rtrn2016CadreTargetObjList,cadreTarget2016Map);
	  }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	     	  || userTypeId.longValue()==IConstants.MP_USER_TYPE_ID || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
	     	  || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userTypeId.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
			List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(4l,null, 3l,null,null);// 2014 target 
			setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
			List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(4l,null, 4l,null,null);// 2016 target 
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
					 	List<Object[]> rtrn2014CadreDtlsObjLst = tdpCadreLocationInfoDAO.get2014TotalCadreCountBasedOnUserType(locationValue, userTypeId,activityMemberId);
						set2014CadreCountToMap(rtrn2014CadreDtlsObjLst, locationWiseCadreDetaislMap,locationIdAndNameMap);
						List<Object[]> rtrnCadreDtlsObjLst = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountBasedOnUserType(locationValue, fromDate, toDate, userTypeId,activityMemberId);
						set2016CadreCountToMap(rtrnCadreDtlsObjLst,locationWiseCadreDetaislMap);
						List<Object[]> rtrnRenewalObjList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountBasedOnUserType(locationValue, fromDate, toDate, userTypeId,activityMemberId);
						setRenewalCountToMap(rtrnRenewalObjList,locationWiseCadreDetaislMap);
			 }
		 }
		if(userAccessLevelId == 2l || activityMemberId == 2l){//stateAccess && activityMemberId 2 is One GS
			 List<Long> VskhptnmCnsttuencesIds = new ArrayList<Long>(){{add(354l);add(355l);add(356l);add(357l);add(358l);add(368l);}};
			    List<Long> VskhptnmRrlCnsttuncesIds = new ArrayList<Long>(){{add(133l);add(134l);add(135l);add(136l);add(137l);add(138l);add(140l);add(141l);add(359l);}};
			    Map<Long,Long> VskhptnmAndVskhptnmRrlDst2014TrtMap = new HashMap<Long, Long>();
			    Map<Long,Long> VskhptnmAndVskhptnmRrlDst2016TrtMap = new HashMap<Long, Long>();
			    Map<Long,String> dstrctMap = new HashMap<Long, String>();
			    /* calculating Visakhapatnam and Visakhapatnam Rural AP district Details start */
			    List<Object[]> rtrnDistOblLst = districtDAO.getSpecificDistrictIdAndName(new ArrayList<Long>(){{add(13l);add(517l);}});
			    setRequiredLocationName(rtrnDistOblLst,dstrctMap);
			    
			    List<Object[]> rtrn2014VskhptnmDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(3l, VskhptnmCnsttuencesIds,"Visakhapatnam");
			    setCadreTargetCntToMap(rtrn2014VskhptnmDistTrtOblLst,VskhptnmAndVskhptnmRrlDst2014TrtMap);
			    
			    List<Object[]> rtrn2016VskhptnmDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(4l, VskhptnmCnsttuencesIds,"Visakhapatnam");
			    setCadreTargetCntToMap(rtrn2016VskhptnmDistTrtOblLst,VskhptnmAndVskhptnmRrlDst2016TrtMap);
			    
			    List<Object[]> rtrn2014VskhptnmRrlDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(3l, VskhptnmRrlCnsttuncesIds,"Visakhapatnam Rural");
			    setCadreTargetCntToMap(rtrn2014VskhptnmRrlDistTrtOblLst,VskhptnmAndVskhptnmRrlDst2014TrtMap);
			    
			    List<Object[]> rtrn201VskhptnmRrlDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(4l, VskhptnmRrlCnsttuncesIds,"Visakhapatnam Rural");
			    setCadreTargetCntToMap(rtrn201VskhptnmRrlDistTrtOblLst,VskhptnmAndVskhptnmRrlDst2016TrtMap);
			    
			    List<Object[]> rtrn2014CadreVskhptnmOblLst = tdpCadreLocationInfoDAO.get2014CadreDistWise(VskhptnmCnsttuencesIds,"Visakhapatnam");
			    set2014CadreCountToMap(rtrn2014CadreVskhptnmOblLst,VskhptnmAndVskhptnmRrlDtlsMap,dstrctMap);
			    
			    List<Object[]> rtrn2014CadreVskhptnmRrlOblLst = tdpCadreLocationInfoDAO.get2014CadreDistWise(VskhptnmRrlCnsttuncesIds,"Visakhapatnam Rural");
			    set2014CadreCountToMap(rtrn2014CadreVskhptnmRrlOblLst,VskhptnmAndVskhptnmRrlDtlsMap,dstrctMap);
			    
			    List<Object[]> rtrn2016CadreVskhptnmOblLst = tdpCadreDateWiseInfoDAO.get2016CadreCntDistWise(VskhptnmCnsttuencesIds, fromDate, toDate,"Visakhapatnam");
			    set2016CadreCountToMap(rtrn2016CadreVskhptnmOblLst,VskhptnmAndVskhptnmRrlDtlsMap);
			    List<Object[]> rtrn2016CadreVskhptnmRrlOblLst = tdpCadreDateWiseInfoDAO.get2016CadreCntDistWise(VskhptnmRrlCnsttuncesIds, fromDate, toDate,"Visakhapatnam Rural");
			    set2016CadreCountToMap(rtrn2016CadreVskhptnmRrlOblLst,VskhptnmAndVskhptnmRrlDtlsMap);
			    
			    List<Object[]> rtrn2016RenewalCadreVskhptnmOblLst = tdpCadreDateWiseInfoDAO.get2016RenewalCadreCntDistWise(VskhptnmCnsttuencesIds, fromDate, toDate,"Visakhapatnam");
			    setRenewalCountToMap(rtrn2016RenewalCadreVskhptnmOblLst,VskhptnmAndVskhptnmRrlDtlsMap);
			    List<Object[]> rtrn2016RenewalVskhptnmRrlOblLst = tdpCadreDateWiseInfoDAO.get2016RenewalCadreCntDistWise(VskhptnmRrlCnsttuncesIds, fromDate, toDate,"Visakhapatnam Rural");
			    setRenewalCountToMap(rtrn2016RenewalVskhptnmRrlOblLst,VskhptnmAndVskhptnmRrlDtlsMap);
			   
			    calculateNewCadreAnddPercentage(VskhptnmAndVskhptnmRrlDtlsMap,VskhptnmAndVskhptnmRrlDst2014TrtMap,VskhptnmAndVskhptnmRrlDst2016TrtMap);	
		}
			
		 //calculating new cadre and percentage
		   calculateNewCadreAnddPercentage(locationWiseCadreDetaislMap,cadreTarget2014Map,cadreTarget2016Map);
		   
		    //Removing Visakhapatnam District Which Contains All 15 Constituencies Data and Replacing With Updated Constituencies Data.And Adding Visakhapatnam Rural District Data
		    if(userAccessLevelId == 2l){//state Access
		    	locationWiseCadreDetaislMap.remove(13l);
		    	locationWiseCadreDetaislMap.putAll(VskhptnmAndVskhptnmRrlDtlsMap);
		    }
		    if(activityMemberId == 2l){
		    	locationWiseCadreDetaislMap.put(13l, VskhptnmAndVskhptnmRrlDtlsMap.get(13l));
		    }
		    
		 //sorting list
		 if(locationWiseCadreDetaislMap != null && locationWiseCadreDetaislMap.size() > 0){
			  resultList = new ArrayList<CadreReportVO>(locationWiseCadreDetaislMap.values());
			   if(sortingType != null && sortingType.equalsIgnoreCase("TargetWise")){
				   Collections.sort(resultList, cadreRegistrationCountPerDeccAsc);	
				}else if(sortingType != null && sortingType.equalsIgnoreCase("2016CadreWise")){
				   Collections.sort(resultList, cadreRegistrationCountDecc);
				}
			 
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
					if(cadreReportVO.getTotal2016CadrePer() > 90 && cadreReportVO.getTotal2016CadrePer() <= 100){
				      goodCnt=goodCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() > 80 && cadreReportVO.getTotal2016CadrePer() <= 90){
					  okCnt=okCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() > 60 && cadreReportVO.getTotal2016CadrePer() <= 80){
					  poorCnt=poorCnt+1;
				   }
				   if(cadreReportVO.getTotal2016CadrePer() < 60){
					 veryPoorCnt=veryPoorCnt+1;
				   }
		  }
		  resultList.get(0).setAllDistrictCnt(new Long(resultList.size()));
		  resultList.get(0).setVeryGoodCnt(veryGoodCnt);
		  resultList.get(0).setGoodCnt(goodCnt);
		  resultList.get(0).setOkCnt(okCnt);
		  resultList.get(0).setPoorCnt(poorCnt);
		  resultList.get(0).setVeryPoorCnt(veryPoorCnt); 
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
 public static Comparator<CadreReportVO> cadreRegistrationCountPerDeccAsc = new Comparator<CadreReportVO>() {
		public int compare(CadreReportVO location2, CadreReportVO location1) {
		Double perc2 = location2.getTotal2016CadrePer();
		Double perc1 = location1.getTotal2016CadrePer();
		//dcending order of percantages.
		 return perc1.compareTo(perc2);  
		}
}; 
public static Comparator<CadreReportVO> cadreRegistrationCountDecc = new Comparator<CadreReportVO>() {
	public int compare(CadreReportVO location2, CadreReportVO location1) {
	Long count2 = location2.getTotal2016CadreCnt();
	Long  count1 = location1.getTotal2016CadreCnt();
	//dcending order of percantages.
	 return count1.compareTo(count2);  
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
 public List<CadreReportVO> getApAndTsConstituenciesDtls(Long stateId,String locationType,String fromDateStr,String toDateStr,Long accessLevelId,List<Long> userAccessLevelValues,String isKuppamExcluded,String sortingType){
	
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,Long>  cadreTarget2014Map = new HashMap<Long, Long>();
	 Map<Long,Long> cadreTarget2016Map = new HashMap<Long, Long>();
	 Map<Long,CadreReportVO> locationWiseCadreDetaislMap = new HashMap<Long, CadreReportVO>(0);
	 Map<Long,String> locationIdAndNameMap = new HashMap<Long, String>();
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 Date toDate=null;
	 Date fromDate=null;
	 
	 try{
			  if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
			  }
			  if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
					List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(4l, null, 3l,null,null);// 2014 target 
					setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
					List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(4l, null, 4l,null,null);// 2016 target 
					setCadreTargetCntToMap(rtrn2016CadreTargetObjList,cadreTarget2016Map);
			   }
			   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
				   for(Long id:userAccessLevelValues){
					   if(stateId != null && stateId.longValue()==1l){
						   if(userAccessLevelValues.contains(13l) || userAccessLevelValues.contains(517l)){
							   if(id.longValue() == 13l){//Visakhapatnam District
								   List<Object[]> consituencyIdsAndNameObjLst = constituencyDAO.getConstituenciesByConstituenciesIds(new ArrayList<Long>(){{add(354l);add(355l);add(356l);add(357l);add(358l);add(368l);}},stateId); 
								   setRequiredLocationName(consituencyIdsAndNameObjLst,locationIdAndNameMap);
								   userAccessLevelValues.remove(id);
								   userAccessLevelValues.add(0l);
							   }else if(id.longValue()==517l){//Visakhapatnam Rural District
								   List<Object[]> consituencyIdsAndNameObjLst = constituencyDAO.getConstituenciesByConstituenciesIds(new ArrayList<Long>(){{add(133l);add(134l);add(135l);add(136l);add(137l);add(138l);add(140l);add(141l);add(359l);}},stateId);
								   setRequiredLocationName(consituencyIdsAndNameObjLst,locationIdAndNameMap);
								   userAccessLevelValues.remove(id);
								   userAccessLevelValues.add(0l);
							   }
						   }
					   }else if(stateId != null && stateId.longValue()==36l){
						   if(userAccessLevelValues.contains(1l) || userAccessLevelValues.contains(518l)){
							   if(id.longValue() == 1l){ //Adilabad District
								   List<Object[]> consituencyIdsAndNameObjLst = constituencyDAO.getConstituenciesByConstituenciesIds(new ArrayList<Long>(){{add(1l);add(3l);add(5l);add(6l);add(7l);}},1l); 
								   setRequiredLocationName(consituencyIdsAndNameObjLst,locationIdAndNameMap);
								   userAccessLevelValues.remove(id);
								   userAccessLevelValues.add(0l);
							   }else if(id.longValue()==518l){//Mancherial District
								   List<Object[]> consituencyIdsAndNameObjLst = constituencyDAO.getConstituenciesByConstituenciesIds( new ArrayList<Long>(){{add(2l);add(4l);add(8l);add(296l);add(295l);}},1l);
								   setRequiredLocationName(consituencyIdsAndNameObjLst,locationIdAndNameMap);
								   userAccessLevelValues.remove(id);
								   userAccessLevelValues.add(0l);
							   }
						   }  
					   }
				   }
			   }
			 // setting location name 
			  List<Object[]> locationIdsAndNameObjList = userAddressDAO.getLocationTypeWiseLocationName(stateId,locationType,accessLevelId,userAccessLevelValues,isKuppamExcluded);
			  setRequiredLocationName(locationIdsAndNameObjList,locationIdAndNameMap);
			  Long locationScopeId=0l;
		      if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		    	 locationScopeId = 4l; 
		      }
		       userAccessLevelValues = new ArrayList<Long>(locationIdAndNameMap.keySet()); 
		  
		   List<Object[]> total2014CadreObjList = tdpCadreLocationInfoDAO.get2014TotalCadreCountLocationWise(locationScopeId, userAccessLevelValues);//2014 cadre
		   set2014CadreCountToMap(total2014CadreObjList,locationWiseCadreDetaislMap,locationIdAndNameMap);
		   List<Object[]> total2016CadreObjList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWise(locationScopeId, userAccessLevelValues, fromDate, toDate);
		   set2016CadreCountToMap(total2016CadreObjList,locationWiseCadreDetaislMap);
		   List<Object[]> total2016RenewalCadreObjList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountLocationWise(locationScopeId, userAccessLevelValues, fromDate, toDate);
		   setRenewalCountToMap(total2016RenewalCadreObjList,locationWiseCadreDetaislMap);
		  //calculating new cadre and percentage
		  calculateNewCadreAnddPercentage(locationWiseCadreDetaislMap,cadreTarget2014Map,cadreTarget2016Map);
		  //sortring 
		  if(locationWiseCadreDetaislMap != null && locationWiseCadreDetaislMap.size() > 0){
			  resultList = new ArrayList<CadreReportVO>(locationWiseCadreDetaislMap.values());
		  }
		  if(resultList != null && resultList.size() > 0){
			  if(sortingType != null && sortingType.equalsIgnoreCase("TargetWise")){
				   Collections.sort(resultList, cadreRegistrationCountPerDeccAsc);	
				}else if(sortingType != null && sortingType.equalsIgnoreCase("2016CadreWise")){
					Collections.sort(resultList, cadreRegistrationCountDecc);
				}
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
					if(cadreReportVO.getTotal2016CadrePer() > 90 && cadreReportVO.getTotal2016CadrePer()<=100){
				      goodCnt=goodCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() > 80 && cadreReportVO.getTotal2016CadrePer()<=90){
					  okCnt=okCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() > 60 && cadreReportVO.getTotal2016CadrePer() <= 80){
					  poorCnt=poorCnt+1;
				   }
				   if(cadreReportVO.getTotal2016CadrePer() < 60){
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
 public List<CadreReportVO> getTsDistrictDetails(Long stateId,String locationType,String fromDateStr,String toDateStr,Long accessLevelId,List<Long> userAccessLevelValues,String sortingType){
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,Long>  cadreTarget2014Map = new HashMap<Long, Long>();
	 Map<Long,Long> cadreTarget2016Map = new HashMap<Long, Long>();
	 Map<Long,CadreReportVO> locationWiseCadreDetaislMap = new HashMap<Long, CadreReportVO>(0);
	 Map<Long,CadreReportVO> adilabadAndMancherialDistMap = new HashMap<Long, CadreReportVO>(0);
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
			    List<Long> adilabadCnsttuencesIds = new ArrayList<Long>(){{add(1l);add(3l);add(5l);add(6l);add(7l);}};
			    List<Long> mancherialDstrctCnsttuncesIds = new ArrayList<Long>(){{add(2l);add(4l);add(8l);add(296l);add(295l);}};
			    Map<Long,Long> adlbdAndMnchrlDst2014TrtMap = new HashMap<Long, Long>();
			    Map<Long,Long> adlbdAndMnchrlDst2016TrtMap = new HashMap<Long, Long>();
			    Map<Long,String> adlbdAndMnchrlDstrctIdAndNameMap = new HashMap<Long, String>();
			    /* calculating Adilabad and Mancherial Ts district Details start */
			    List<Object[]> rtrnDistOblLst = districtDAO.getSpecificDistrictIdAndName(new ArrayList<Long>(){{add(1l);add(518l);}});
			    setRequiredLocationName(rtrnDistOblLst,adlbdAndMnchrlDstrctIdAndNameMap);
			    
			    List<Object[]> rtrn2014AdlbdDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(3l, adilabadCnsttuencesIds,"Adilabad");
			    setCadreTargetCntToMap(rtrn2014AdlbdDistTrtOblLst,adlbdAndMnchrlDst2014TrtMap);
			    
			    List<Object[]> rtrn2016AdlbdDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(4l, adilabadCnsttuencesIds,"Adilabad");
			    setCadreTargetCntToMap(rtrn2016AdlbdDistTrtOblLst,adlbdAndMnchrlDst2016TrtMap);
			    
			    List<Object[]> rtrn2014MnchrlDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(3l, mancherialDstrctCnsttuncesIds,"Mancherial");
			    setCadreTargetCntToMap(rtrn2014MnchrlDistTrtOblLst,adlbdAndMnchrlDst2014TrtMap);
			    
			    List<Object[]> rtrn2016MnchrlDistTrtOblLst = tdpCadreTargetCountDAO.getTtalCadreTargetCntDistWise(4l, mancherialDstrctCnsttuncesIds,"Mancherial");
			    setCadreTargetCntToMap(rtrn2016MnchrlDistTrtOblLst,adlbdAndMnchrlDst2016TrtMap);
			    
			    List<Object[]> rtrn2014CadreAdlbdOblLst = tdpCadreLocationInfoDAO.get2014CadreDistWise(adilabadCnsttuencesIds,"Adilabad");
			    set2014CadreCountToMap(rtrn2014CadreAdlbdOblLst,adilabadAndMancherialDistMap,adlbdAndMnchrlDstrctIdAndNameMap);
			    
			    List<Object[]> rtrn2014CadreMnchrlOblLst = tdpCadreLocationInfoDAO.get2014CadreDistWise(mancherialDstrctCnsttuncesIds,"Mancherial");
			    set2014CadreCountToMap(rtrn2014CadreMnchrlOblLst,adilabadAndMancherialDistMap,adlbdAndMnchrlDstrctIdAndNameMap);
			    
			    List<Object[]> rtrn2016CadreAdlbdOblLst = tdpCadreDateWiseInfoDAO.get2016CadreCntDistWise(adilabadCnsttuencesIds, fromDate, toDate,"Adilabad");
			    set2016CadreCountToMap(rtrn2016CadreAdlbdOblLst,adilabadAndMancherialDistMap);
			    List<Object[]> rtrn2016CadreMnchrlOblLst = tdpCadreDateWiseInfoDAO.get2016CadreCntDistWise(mancherialDstrctCnsttuncesIds, fromDate, toDate,"Mancherial");
			    set2016CadreCountToMap(rtrn2016CadreMnchrlOblLst,adilabadAndMancherialDistMap);
			    
			    List<Object[]> rtrn2016RenewalCadreAdlbdOblLst = tdpCadreDateWiseInfoDAO.get2016RenewalCadreCntDistWise(adilabadCnsttuencesIds, fromDate, toDate,"Adilabad");
			    setRenewalCountToMap(rtrn2016RenewalCadreAdlbdOblLst,adilabadAndMancherialDistMap);
			    List<Object[]> rtrn2016RenewalMnchrlOblLst = tdpCadreDateWiseInfoDAO.get2016RenewalCadreCntDistWise(mancherialDstrctCnsttuncesIds, fromDate, toDate,"Mancherial");
			    
			    setRenewalCountToMap(rtrn2016RenewalMnchrlOblLst,adilabadAndMancherialDistMap);
			    calculateNewCadreAnddPercentage(adilabadAndMancherialDistMap,adlbdAndMnchrlDst2014TrtMap,adlbdAndMnchrlDst2016TrtMap);
			    
			    /* End */
			 	List<Object[]> rtrn2014CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(3l, null,3l,null,null);// 2014 target 
				setCadreTargetCntToMap(rtrn2014CadreTargetObjList,cadreTarget2014Map);
				List<Object[]> rtrn2016CadreTargetObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWise(3l, null, 4l,null,null);// 2016 target 
				setCadreTargetCntToMap(rtrn2016CadreTargetObjList,cadreTarget2016Map);
				
		  }
			 // setting location name 
			  List<Object[]> locationIdsAndNameObjList = userAddressDAO.getLocationTypeWiseLocationName(stateId,locationType,accessLevelId,userAccessLevelValues,null);
			  setRequiredLocationName(locationIdsAndNameObjList,locationIdAndNameMap);
			 Long locationScopeId=0l;
		      if(locationType != null && locationType.equalsIgnoreCase("District")){
		    	 locationScopeId = 3l;
		      }
			   userAccessLevelValues = new ArrayList<Long>(locationIdAndNameMap.keySet()); 
			   List<Object[]> total2014CadreObjList = tdpCadreLocationInfoDAO.get2014TotalCadreCountLocationWise(locationScopeId, userAccessLevelValues);//2014 cadre
			   set2014CadreCountToMap(total2014CadreObjList,locationWiseCadreDetaislMap,locationIdAndNameMap);
			   List<Object[]> total2016CadreObjList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWise(locationScopeId, userAccessLevelValues, fromDate, toDate);
			   set2016CadreCountToMap(total2016CadreObjList,locationWiseCadreDetaislMap);
			   List<Object[]> total2016RenewalCadreObjList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountLocationWise(locationScopeId, userAccessLevelValues, fromDate, toDate);
			   setRenewalCountToMap(total2016RenewalCadreObjList,locationWiseCadreDetaislMap);
		  //calculating new cadre and percentage
		   calculateNewCadreAnddPercentage(locationWiseCadreDetaislMap,cadreTarget2014Map,cadreTarget2016Map);
		  
		  if(locationType != null && locationType.equalsIgnoreCase("District")){
			  locationWiseCadreDetaislMap.remove(1l);//removing Adilabad District 
			  locationWiseCadreDetaislMap.putAll(adilabadAndMancherialDistMap);//Adding Adilabad and Mancherial Ts district Data
		  }
		  //sortring 
		  if(locationWiseCadreDetaislMap != null && locationWiseCadreDetaislMap.size() > 0){
			  resultList = new ArrayList<CadreReportVO>(locationWiseCadreDetaislMap.values());
		 }
		 if(resultList != null && resultList.size() > 0){
			  if(sortingType != null && sortingType.equalsIgnoreCase("TargetWise")){
				   Collections.sort(resultList, cadreRegistrationCountPerDeccAsc);	
				}else if(sortingType != null && sortingType.equalsIgnoreCase("2016CadreWise")){
					Collections.sort(resultList, cadreRegistrationCountDecc);
				}
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
					if(cadreReportVO.getTotal2016CadrePer() > 90 && cadreReportVO.getTotal2016CadrePer()<=100){
				      goodCnt=goodCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() > 80 && cadreReportVO.getTotal2016CadrePer()<=90){
					  okCnt=okCnt+1;
				    }
					if(cadreReportVO.getTotal2016CadrePer() > 60 && cadreReportVO.getTotal2016CadrePer() <= 80){
					  poorCnt=poorCnt+1;
				   }
				   if(cadreReportVO.getTotal2016CadrePer() < 60){
					 veryPoorCnt=veryPoorCnt+1;
				   }
		  }
		  resultList.get(0).setAllDistrictCnt(new Long(resultList.size()));
		  resultList.get(0).setVeryGoodCnt(veryGoodCnt);
		  resultList.get(0).setGoodCnt(goodCnt);
		  resultList.get(0).setOkCnt(okCnt);
		  resultList.get(0).setPoorCnt(poorCnt);
		  resultList.get(0).setVeryPoorCnt(veryPoorCnt); 
		 }
	 }catch(Exception e){
		 LOG.error("Exception raised in getTsDistrictDetails() in CadreRegistrationService service", e);	 
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
	/**aaaaaa//position one
	* @param  Long parentActivityMemberId
	* @param  Long childUserTypeId
	* @param Long stateId
	* @return List<UserTypeVO>
	* @author Swadhin Lenka
	* @Description :This Service Method is used to get selected child member and for userType.. 
	* @since 14-Oct-2016
	*/
	public List<UserTypeVO> getSelectedChildTypeMembersForCadreRegistration(Long parentActivityMemberId,List<Long> childUserTypeIds,Long stateId,String fromDateStr,String toDateStr, String sortingType){
		try{  
			Date toDay = new Date();
			List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0); 
			Map<String,Long> targetCdrCntMap = new HashMap<String, Long>(0);
			Map<String,Long> regCdrCntMap = new HashMap<String, Long>(0);
			Map<String,Long> regCdrCntMapToday = new HashMap<String, Long>(0);
			Map<String,Long> regCdrCntMap2014 = new HashMap<String, Long>(0);
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
			Set<Long> tempCostIds = new HashSet<Long>(){{add(133l);add(134l);add(135l);add(136l);add(137l);add(138l);add(140l);
			add(141l);add(354l);add(355l);add(356l);add(357l);add(358l);add(359l);add(368l);add(146l);add(147l);add(149l);add(152l);
			add(153l);add(155l);add(156l);add(157l);add(159l);add(160l);add(163l);add(303l);add(304l);add(305l);add(306l);add(307l);
			add(308l);add(309l);add(310l);add(182l);add(184l);add(185l);add(186l);add(187l);add(191l);add(192l);add(193l);add(194l);
			add(195l);add(196l);add(327l);add(328l);add(329l);add(330l);add(331l);}};
			  
			Set<Long> conIdSet = locationLevelIdsMap.get(5l);
			if(conIdSet != null){  
				conIdSet.addAll(tempCostIds);
			}else{
				locationLevelIdsMap.put(5l, tempCostIds);      
			}
			
			  
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
					Long accessLevelValue =0l;  
					if(entry.getKey().longValue() == 4l){// user level 4 means parliament constituency
						accessLevelValue = 10l; //region scope 10  means parliament constituency 
					}else if(entry.getKey().longValue()==5l){
						accessLevelValue = 4l;   
					}else{  
						accessLevelValue = entry.getKey();   
					} // xxxx    
					
					//List<Object[]> returnObjList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(entry.getKey(),entry.getValue(),stateId,4l,null);
					List<Object[]> returnObjList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCount( accessLevelValue,entry.getValue(),4l);
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
					List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,"total",null, null);
					if(totalRegCadreList != null && totalRegCadreList.size() > 0){  
						for (Object[] param : totalRegCadreList) {
							String locationLevelAndId = entry.getKey()+"_"+param[0].toString();
							regCdrCntMap.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l); 
						}
					}
				}
			}
			
			//calculate registered count for today  
			//regCdrCntMap.clear();
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
					List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,"today",null, null);
					if(totalRegCadreList != null && totalRegCadreList.size() > 0){
						for (Object[] param : totalRegCadreList) {
							String locationLevelAndId = entry.getKey()+"_"+param[0].toString();
							regCdrCntMapToday.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);  
						}
					}
				}
			}
			
			//calculate registered count for 2014  
			//regCdrCntMap.clear();
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
							regCdrCntMap2014.put(locationLevelAndId, param[1] != null ? (Long)param[1]:0l);
						}
					}
				}      
			}
			//for DP
			Set<Long> locationIdSet = null;
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				for(UserTypeVO vo:childActivityMembersMap.values()){
					locationIdSet = vo.getLocationValuesSet();
			    	  if(locationIdSet.contains(517l)){
			    		  List<Long> constIds = districtConstituenciesDAO.getConstituenciesOfDistrictById(517l);
			    		  if(constIds != null && constIds.size() > 0){
			    			  locationIdSet.addAll(constIds);  
			    		  }
			    		  vo.setLocationLevelId(5l);
			    	  }
				}
			}
			
			//for GS
			List<Long> totalLocationIds = null;  
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				for(UserTypeVO vo:childActivityMembersMap.values()){
					locationIdSet = vo.getLocationValuesSet();
			    	  if(locationIdSet.contains(13l)){
			    		  totalLocationIds = new ArrayList<Long>();
			    		  List<Long> extraConstIds = districtConstituenciesDAO.getConstituenciesOfDistrictById(517l);
			    		  List<Object[]> allConstIds = constituencyDAO.getConstituenciesList(new ArrayList<Long>(locationIdSet));
			    		  if(allConstIds != null && allConstIds.size() > 0){
			    			  for(Object[] param : allConstIds){
			    				  totalLocationIds.add(param[2] != null ? (Long)param[2] : 0l);
			    			  }
			    			  if(extraConstIds != null && extraConstIds.size() > 0){
			    				  totalLocationIds.removeAll(extraConstIds);
			    			  }  
			    		  }
			    		  if(totalLocationIds != null && totalLocationIds.size() > 0){
			    			  locationIdSet.clear();
			    			  locationIdSet.addAll(totalLocationIds); 
			    			  vo.setLocationLevelId(5l);
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
			
			//pushing reg count for today
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){  
				for(UserTypeVO vo:childActivityMembersMap.values()){
					for(Long locationValueId:vo.getLocationValuesSet()){
						String key = vo.getLocationLevelId()+"_"+locationValueId;
						if(regCdrCntMapToday.get(key) != null){
							setTodayCount(locationValueId,vo.getSubLocationList(),regCdrCntMapToday.get(key));
							//nameVO.setCount(regCdrCntMap.get(key));
							//vo.getSubLocationList().add(nameVO);
							vo.setTotalCadreCountToday(vo.getTotalCadreCountToday()+regCdrCntMapToday.get(key));
						}
					}
				}
			}
			
			//pushing reg count for 2014
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				for(UserTypeVO vo:childActivityMembersMap.values()){
					for(Long locationValueId:vo.getLocationValuesSet()){
						String key = vo.getLocationLevelId()+"_"+locationValueId;
						if(regCdrCntMap2014.get(key) != null){
							vo.setTotalTargetCount2014(vo.getTotalTargetCount2014()+regCdrCntMap2014.get(key));
						}  
					}
				}
			}  
			//Setting Location Name
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
			 //Setting District Name for District President of Visakhapatnam and Visakhapatnam Rural
			 if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				 if(childActivityMembersMap.containsKey(50l)){
					 UserTypeVO vo = childActivityMembersMap.get(50l);
					 List<Object[]> distObjLst = constituencyDAO.getDistAndConDtslByConstituenciesIds(new ArrayList<Long>(vo.getLocationValuesSet()));
					 if(distObjLst != null && distObjLst.size() > 0){
						 vo.getLocationValuesSet().clear();
						 vo.getLocationValuesSet().add(commonMethodsUtilService.getLongValueForObject(distObjLst.get(0)[0]));
						 vo.setLocationName(commonMethodsUtilService.getStringValueForObject(distObjLst.get(0)[1])+" District");
					 }
				 }
				 if(childActivityMembersMap.containsKey(53l)){
					 UserTypeVO vo = childActivityMembersMap.get(53l);
					 List<Object[]> distObjLst = districtConstituenciesDAO.getDistrictByConstituenciesIds(vo.getLocationValuesSet());
					 if(distObjLst != null && distObjLst.size() > 0){
						 vo.getLocationValuesSet().clear();
						 vo.getLocationValuesSet().add(commonMethodsUtilService.getLongValueForObject(distObjLst.get(0)[0]));
						 vo.setLocationName(commonMethodsUtilService.getStringValueForObject(distObjLst.get(0)[1])+" District");
					 }
				 }
			 }
			 
			if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				resultList.addAll(childActivityMembersMap.values());
			}
			if(sortingType.equalsIgnoreCase("TargetWise")){
				if(resultList != null && resultList.size() > 0){
					Collections.sort(resultList, trainingMemberEligibleAttendedPercDesc);
				}
			}else{
				if(resultList != null && resultList.size() > 0){
					Collections.sort(resultList, cadreMemberEligibleAttendedPercDesc);
				}  
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
			
			CadreRegistratedCountVO  cadreRegistratedCountVO = new CadreRegistratedCountVO();
			
			Date today = dateUtilService.getCurrentDateAndTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);  
			Date lastOneHourTime = cal.getTime();
			
			//LAST ONE HOUR IN FIELD
			Long ttlTabUserInField = tabUserEnrollmentInfoDAO.getTodayInFieldList(stateId,lastOneHourTime);
			//TODAY TOTAL IN FIELD
			Long ttlTabUserInFieldToday = tabUserEnrollmentInfoDAO.getTodayPresentList(stateId,today);
			
			cadreRegistratedCountVO.setInField(ttlTabUserInField != null ? ttlTabUserInField : 0l); 
			cadreRegistratedCountVO.setTodayFieldMembersCount(ttlTabUserInFieldToday != null ? ttlTabUserInFieldToday : 0l);  
			
			//LOCATIONS RELATED
			
			//CONSTITUENCIES STARTED AND NOT STARTED.
			 List<Object[]> allStateConstituencies = constituencyDAO.getStateWiseAssemblyConstituency(stateId);
			 List<Long> apCnsttuncesIds = new ArrayList<Long>();
			   if(allStateConstituencies != null && allStateConstituencies.size() > 0 ){
				   for(Object [] param:allStateConstituencies){
					   apCnsttuncesIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));  
				   }
			   }
			   
			List<Long> todayStaredConstituencyCntList = tdpCadreLocationInfoDAO.getTodayTotalStartedRegistrationConstituencyStateWise(stateId);
			if(todayStaredConstituencyCntList != null && todayStaredConstituencyCntList.size() > 0){
				  cadreRegistratedCountVO.setTodayStartedConsttuncyCnt(Long.valueOf(todayStaredConstituencyCntList.size()));
			 }
			 
			 apCnsttuncesIds.removeAll(todayStaredConstituencyCntList);
			 cadreRegistratedCountVO.setTodayNotStartedConsttuncyCnt(Long.valueOf(apCnsttuncesIds.size()));
			 cadreRegistratedCountVO.setLocationIdsList(apCnsttuncesIds);
			   
			//TEHSILS
			 List<Long> mandalIdsList = new ArrayList<Long>(0); 
			 List<Long> rtrnAllMandalIds = constituencyTehsilDAO.getAllStateWiseTehsilIds(stateId);
			 setRequiredDIdsToList(rtrnAllMandalIds,mandalIdsList,"Mandal");
			 
			 List<Long> mandalTodayStartedIdsList = new ArrayList<Long>(0);
			 List<Long> rtrnTodayStatedMandalIdsLst = tdpCadreLocationInfoDAO.getTodayMandalStartedStateWise(stateId);
			 setRequiredDIdsToList(rtrnTodayStatedMandalIdsLst,mandalTodayStartedIdsList,"Mandal");
			
			 mandalIdsList.removeAll(mandalTodayStartedIdsList);
			 
			
			 //LEB
			   List<Long> muncipalityIdsList = new ArrayList<Long>(0);
			   List<Long> rtrnAllMuncipalityIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdsStateWise(stateId);
			   setRequiredDIdsToList(rtrnAllMuncipalityIdsList,muncipalityIdsList,"Muncipality");
			   
			   List<Long> muncipalityTodayStartedIdsList = new ArrayList<Long>(0);
			   List<Long> rtrnTodayStatedMuncipalityIdsLst = tdpCadreLocationInfoDAO.getTodayLocalElectionBodyStartedStateWise(stateId);
			   setRequiredDIdsToList(rtrnTodayStatedMuncipalityIdsLst,muncipalityTodayStartedIdsList,"Muncipality");
			   
			   muncipalityIdsList.removeAll(muncipalityTodayStartedIdsList);
			   
			   cadreRegistratedCountVO.setTodayStartedMandalMuncipalityCnt(Long.valueOf(rtrnTodayStatedMandalIdsLst.size()+rtrnTodayStatedMuncipalityIdsLst.size()));
			   cadreRegistratedCountVO.setTodayNotStartedMandalMuncipalityCnt(Long.valueOf(mandalIdsList.size()+muncipalityIdsList.size()));
			   
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(mandalIdsList);
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(muncipalityIdsList);
			   
			   return cadreRegistratedCountVO;
			
		}catch(Exception e){
			LOG.error("Exception raised in getStateDtls() in CoreDashboardCadreRegistrationService service", e);
		}
		return null;    
	}
	
	public CadreRegistratedCountVO getStateDtlsTS(Long activityMemberId,Long stateId,String startDate, String endDate){
try{
			
			CadreRegistratedCountVO  cadreRegistratedCountVO = new CadreRegistratedCountVO();
			
			Date today = dateUtilService.getCurrentDateAndTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);  
			Date lastOneHourTime = cal.getTime();
			
			//LAST ONE HOUR IN FIELD
			Long ttlTabUserInField = tabUserEnrollmentInfoDAO.getTodayInFieldList(stateId,lastOneHourTime);
			//TODAY TOTAL IN FIELD
			Long ttlTabUserInFieldToday = tabUserEnrollmentInfoDAO.getTodayPresentList(stateId,today);
			
			cadreRegistratedCountVO.setInField(ttlTabUserInField != null ? ttlTabUserInField : 0l); 
			cadreRegistratedCountVO.setTodayFieldMembersCount(ttlTabUserInFieldToday != null ? ttlTabUserInFieldToday : 0l);  
			
			//LOCATIONS RELATED
			
			//CONSTITUENCIES STARTED AND NOT STARTED.
			 List<Object[]> allStateConstituencies = constituencyDAO.getStateWiseAssemblyConstituency(stateId);
			 List<Long> apCnsttuncesIds = new ArrayList<Long>();
			   if(allStateConstituencies != null && allStateConstituencies.size() > 0 ){
				   for(Object [] param:allStateConstituencies){
					   apCnsttuncesIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));  
				   }
			   }
			   
			List<Long> todayStaredConstituencyCntList = tdpCadreLocationInfoDAO.getTodayTotalStartedRegistrationConstituencyStateWise(stateId);
			if(todayStaredConstituencyCntList != null && todayStaredConstituencyCntList.size() > 0){
				  cadreRegistratedCountVO.setTodayStartedConsttuncyCnt(Long.valueOf(todayStaredConstituencyCntList.size()));
			 }
			 
			 apCnsttuncesIds.removeAll(todayStaredConstituencyCntList);
			 cadreRegistratedCountVO.setTodayNotStartedConsttuncyCnt(Long.valueOf(apCnsttuncesIds.size()));
			 cadreRegistratedCountVO.setLocationIdsList(apCnsttuncesIds);
			   
			//TEHSILS
			 List<Long> mandalIdsList = new ArrayList<Long>(0); 
			 List<Long> rtrnAllMandalIds = constituencyTehsilDAO.getAllStateWiseTehsilIds(stateId);
			 setRequiredDIdsToList(rtrnAllMandalIds,mandalIdsList,"Mandal");
			 
			 List<Long> mandalTodayStartedIdsList = new ArrayList<Long>(0);
			 List<Long> rtrnTodayStatedMandalIdsLst = tdpCadreLocationInfoDAO.getTodayMandalStartedStateWise(stateId);
			 setRequiredDIdsToList(rtrnTodayStatedMandalIdsLst,mandalTodayStartedIdsList,"Mandal");
			
			 mandalIdsList.removeAll(mandalTodayStartedIdsList);
			 
			
			 //LEB
			   List<Long> muncipalityIdsList = new ArrayList<Long>(0);
			   List<Long> rtrnAllMuncipalityIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdsStateWise(stateId);
			   setRequiredDIdsToList(rtrnAllMuncipalityIdsList,muncipalityIdsList,"Muncipality");
			   
			   List<Long> muncipalityTodayStartedIdsList = new ArrayList<Long>(0);
			   List<Long> rtrnTodayStatedMuncipalityIdsLst = tdpCadreLocationInfoDAO.getTodayLocalElectionBodyStartedStateWise(stateId);
			   setRequiredDIdsToList(rtrnTodayStatedMuncipalityIdsLst,muncipalityTodayStartedIdsList,"Muncipality");
			   
			   muncipalityIdsList.removeAll(muncipalityTodayStartedIdsList);
			   
			   cadreRegistratedCountVO.setTodayStartedMandalMuncipalityCnt(Long.valueOf(rtrnTodayStatedMandalIdsLst.size()+rtrnTodayStatedMuncipalityIdsLst.size()));
			   cadreRegistratedCountVO.setTodayNotStartedMandalMuncipalityCnt(Long.valueOf(mandalIdsList.size()+muncipalityIdsList.size()));
			   
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(mandalIdsList);
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(muncipalityIdsList);
			   
			   return cadreRegistratedCountVO;
			
		}catch(Exception e){
			LOG.error("Exception raised in getStateDtlsTS() in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}
	
	public CadreRegistratedCountVO getStateDtlsOld(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{
			
			//SWADHIN
			DateUtilService dateUtilService = new DateUtilService();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = dateUtilService.getCurrentDateAndTime();
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
			
			//List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l,null);
			Long accessLevelValue =0l;      
			if(accessLvlId.longValue() == 4l){// user level 4 means parliament constituency
				accessLevelValue = 10l; //region scope 10  means parliament constituency 
			}else if(accessLvlId.longValue()==5l){
				accessLevelValue = 4l;  
			}else{
				accessLevelValue = accessLvlId;     
			}
			List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCount( accessLevelValue,new HashSet<Long>(accessLvlValue), 4l);
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
			
			//ttlContStarted = tdpCadreDAO.getTotalConstituencyForCdrRegStarted(1l);
			ttlContStarted = tdpCadreLocationInfoDAO.getTotalConstituencyForCdrRegStarted(1l);
			if(ttlContStarted != null){
				cadreRegistratedCountVO.setConstStartedCount(ttlContStarted);   
				cadreRegistratedCountVO.setConstStartedCountPer(calculatePercantage(ttlContStarted,175l));
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
			//Long ttlTabUserInField = tabUserEnrollmentInfoDAO.getTotalTabUserWorkingInField(accessLvlId,accessLvlValue,stateId,now);    
			//Long ttlTabUserInField = tabUserEnrollmentInfoSourceDAO.getTotalTabUserWorkingInField(stateId,lastOneHourTime,today);      
			Long ttlTabUserInField = tabUserEnrollmentInfoDAO.getTodayInFieldList(stateId,lastOneHourTime);
			cadreRegistratedCountVO.setInField(ttlTabUserInField != null ? ttlTabUserInField : 0l);    
			Long ttlTabUserInFieldToday = tabUserEnrollmentInfoDAO.getTodayPresentList(stateId,toDt);
			cadreRegistratedCountVO.setTodayFieldMembersCount(ttlTabUserInFieldToday != null ? ttlTabUserInFieldToday : 0l);   
			//total submitted data
			//Long ttlSubmittedDataToday = tabUserEnrollmentInfoDAO.getTotalRecordSubmitedByTabUser(today, 1l);
			cadreRegistratedCountVO.setTotalSubmittedToday(totalCadreToday != null ? totalCadreToday : 0l);  
			 
			//SANTOSH
			/* showing stated constituency,mandal and MUNCIPALITY  */
			  List<Long> todayStaredConstituencyCntList = tdpCadreLocationInfoDAO.getTodayTotalStartedRegistrationConstituencyStateWise(stateId);
			  if(todayStaredConstituencyCntList != null && todayStaredConstituencyCntList.size() > 0){
				  cadreRegistratedCountVO.setTodayStartedConsttuncyCnt(Long.valueOf(todayStaredConstituencyCntList.size()));
			  }
			  List<Object[]> allStateConstituencies = constituencyDAO.getStateWiseAssemblyConstituency(stateId);
			  List<Long> apCnsttuncesIds = new ArrayList<Long>();
			   if(allStateConstituencies != null && allStateConstituencies.size() > 0 ){
				   for(Object [] param:allStateConstituencies){
					   apCnsttuncesIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));  
				   }
			   }
			   apCnsttuncesIds.removeAll(todayStaredConstituencyCntList);
			   cadreRegistratedCountVO.setTodayNotStartedConsttuncyCnt(Long.valueOf(apCnsttuncesIds.size()));
			   cadreRegistratedCountVO.setLocationIdsList(apCnsttuncesIds);
			   
			   List<Long> mandalIdsList = new ArrayList<Long>(0);
			   List<Long> muncipalityIdsList = new ArrayList<Long>(0);
			   List<Long> mandalTodayStartedIdsList = new ArrayList<Long>(0);
			   List<Long> muncipalityTodayStartedIdsList = new ArrayList<Long>(0);
			   
			   
			   List<Long> rtrnTodayStatedMandalIdsLst = tdpCadreLocationInfoDAO.getTodayMandalStartedStateWise(stateId);
			   setRequiredDIdsToList(rtrnTodayStatedMandalIdsLst,mandalTodayStartedIdsList,"Mandal");
			   
			   List<Long> rtrnTodayStatedMuncipalityIdsLst = tdpCadreLocationInfoDAO.getTodayLocalElectionBodyStartedStateWise(stateId);
			   setRequiredDIdsToList(rtrnTodayStatedMuncipalityIdsLst,muncipalityTodayStartedIdsList,"Muncipality");
			   
			   cadreRegistratedCountVO.setTodayStartedMandalMuncipalityCnt(Long.valueOf(rtrnTodayStatedMandalIdsLst.size()+rtrnTodayStatedMuncipalityIdsLst.size()));
			   
			   List<Long> rtrnAllMandalIds = constituencyTehsilDAO.getAllStateWiseTehsilIds(stateId);
			   setRequiredDIdsToList(rtrnAllMandalIds,mandalIdsList,"Mandal");
			   mandalIdsList.removeAll(mandalTodayStartedIdsList);
			   
			   List<Long> rtrnAllMuncipalityIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdsStateWise(stateId);
			   setRequiredDIdsToList(rtrnAllMuncipalityIdsList,muncipalityIdsList,"Muncipality");
			   muncipalityIdsList.removeAll(muncipalityTodayStartedIdsList);
			   
			   cadreRegistratedCountVO.setTodayNotStartedMandalMuncipalityCnt(Long.valueOf(mandalIdsList.size()+muncipalityIdsList.size()));
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(mandalIdsList);
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(muncipalityIdsList);
			   
			return cadreRegistratedCountVO;           
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getStateDtls in CoreDashboardCadreRegistrationService service", e);
		}
		return null;  
	}
	public IdAndNameVO getInFieldCount(String fromDateStr, String toDateStr){
		try{
			IdAndNameVO idNameVO = new IdAndNameVO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);  
			Date lastOneHourTime = cal.getTime();
			Long tsInFieldCount = 0l;
			Long apInFieldCount = 0l;
			Long tsTotalFieldCount = 0l; 
			Long apTotalFieldCount = 0l;  
			/*List<Object[]> todayInFieldList = tabUserEnrollmentInfoDAO.getTodayInFieldList(lastOneHourTime);
			if(todayInFieldList != null && todayInFieldList.size() > 0){
				for(Object[] param : todayInFieldList){
					if(((Long)param[0]) == 1l){
						apInFieldCount = (Long)param[1];
					}else{
						tsInFieldCount = (Long)param[1];
					}
				}
			}
			if(toDateStr != null && toDateStr.trim().length() > 0 ){
				today = sdf.parse(toDateStr);
			}
			List<Object[]> todayPresentList = tabUserEnrollmentInfoDAO.getTodayPresentList(today);
			if(todayPresentList != null && todayPresentList.size() > 0){
				for(Object[] param : todayPresentList){
					if(((Long)param[0]) == 1l){
						apTotalFieldCount = (Long)param[1];
					}else{
						tsTotalFieldCount = (Long)param[1];
					}
				}
			}    */
			/*List<Long> tsDistId = new ArrayList<Long>(){{
				add(1l);add(2l);add(3l);add(4l);add(5l);add(6l);add(7l);add(8l);add(9l);add(10l);
			}};
			
			Long tsInFieldCount = 0l;
			Long apInFieldCount = 0l;
			List<Object[]> inFieldCountList = tdpCadreEnrollmentYearDAO.getInFieldCount(lastOneHourTime);
			if(inFieldCountList != null && inFieldCountList.size() > 0){
				for(Object[] param : inFieldCountList){
					if(tsDistId.contains((Long)param[0])){
						tsInFieldCount = tsInFieldCount + (Long)param[1];
					}else{
						apInFieldCount = apInFieldCount + (Long)param[1];
					}
					
				}
			}
			if(toDateStr != null && toDateStr.trim().length() > 0 ){
				today = sdf.parse(toDateStr);
			}
			Long tsTotalFieldCount = 0l; 
			Long apTotalFieldCount = 0l;
			List<Object[]> todayFieldCount = tdpCadreEnrollmentYearDAO.getTodayFieldCount(today);
			if(todayFieldCount != null && todayFieldCount.size() > 0){
				for(Object[] param : todayFieldCount){
					if(tsDistId.contains((Long)param[0])){
						tsTotalFieldCount = tsTotalFieldCount + (Long)param[1];
					}else{
						apTotalFieldCount = apTotalFieldCount + (Long)param[1];
					}
					
				}
			}*/
			idNameVO.setTsNow(tsInFieldCount);//inFieldCount
			idNameVO.setApNow(apInFieldCount);//inFieldCount
			idNameVO.setTsTotal(tsTotalFieldCount);
			idNameVO.setApTotal(apTotalFieldCount);
			return idNameVO;    
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getInFieldCount in CoreDashboardCadreRegistrationService service", e);
		}
		return null;
	}
	
	public CadreRegistratedCountVO getStateDtlsTSOld(Long activityMemberId,Long stateId,String startDate, String endDate){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			DateUtilService dateUtilService = new DateUtilService();
			Date today = dateUtilService.getCurrentDateAndTime();
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
			accessLvlValue.add(36l);
			
			//List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,4l);
			Long totalRegCadre2016 = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWiseTS(stateId,frmDt, toDt);
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
			Long totalRegCadreForToday = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWiseTS(stateId,toDt, toDt);
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
			
			//List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l,null);
			
			List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCount( 2l,new HashSet<Long>(){{add(36l);}}, 4l);
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
			
			//ttlContStarted = tdpCadreDAO.getTotalConstituencyForCdrRegStarted(36l);
			ttlContStarted = tdpCadreLocationInfoDAO.getTotalConstituencyForCdrRegStarted(36l);
			if(ttlContStarted != null){
				cadreRegistratedCountVO.setConstStartedCount(ttlContStarted);
				cadreRegistratedCountVO.setConstStartedCountPer(calculatePercantage(ttlContStarted,119l));
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
			//Long ttlTabUserInField = tabUserEnrollmentInfoDAO.getTotalTabUserWorkingInField(accessLvlId,accessLvlValue,stateId,now);    
			//Long ttlTabUserInField = tabUserEnrollmentInfoSourceDAO.getTotalTabUserWorkingInField(stateId,lastOneHourTime,today);      
			Long ttlTabUserInField = tabUserEnrollmentInfoDAO.getTodayInFieldList(stateId,lastOneHourTime);
			cadreRegistratedCountVO.setInField(ttlTabUserInField != null ? ttlTabUserInField : 0l);    
			Long ttlTabUserInFieldToday = tabUserEnrollmentInfoDAO.getTodayPresentList(stateId,toDt);
			cadreRegistratedCountVO.setTodayFieldMembersCount(ttlTabUserInFieldToday != null ? ttlTabUserInFieldToday : 0l);       
			
			//total submitted data  
			//Long ttlSubmittedDataToday = tabUserEnrollmentInfoDAO.getTotalRecordSubmitedByTabUser(today, 1l);
			cadreRegistratedCountVO.setTotalSubmittedToday(totalCadreToday != null ? totalCadreToday : 0l);  
			
			 /* showing stated constituency,mandal and MUNCIPALITY  */
			
			  List<Long> todayStaredConstituencyCntList = tdpCadreLocationInfoDAO.getTodayTotalStartedRegistrationConstituencyStateWise(36l);
			  if(todayStaredConstituencyCntList != null && todayStaredConstituencyCntList.size() > 0){
				  cadreRegistratedCountVO.setTodayStartedConsttuncyCnt(Long.valueOf(todayStaredConstituencyCntList.size()));;
			  }
			  List<Object[]> allStateConstituencies = constituencyDAO.getStateWiseAssemblyConstituency(stateId);
			  List<Long> tsCnsttuncesIds = new ArrayList<Long>();
			   if(allStateConstituencies != null && allStateConstituencies.size() > 0 ){
				   for(Object [] param:allStateConstituencies){
					   tsCnsttuncesIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));  
				   }
			   }
			   tsCnsttuncesIds.removeAll(todayStaredConstituencyCntList);
			   cadreRegistratedCountVO.setTodayNotStartedConsttuncyCnt(Long.valueOf(tsCnsttuncesIds.size()));
			   cadreRegistratedCountVO.setLocationIdsList(tsCnsttuncesIds);
			   
			   List<Long> mandalIdsList = new ArrayList<Long>(0);
			   List<Long> muncipalityIdsList = new ArrayList<Long>(0);
			   List<Long> mandalTodayStartedIdsList = new ArrayList<Long>(0);
			   List<Long> muncipalityTodayStartedIdsList = new ArrayList<Long>(0);
			   
			   
			   List<Long> rtrnTodayStatedMandalIdsLst = tdpCadreLocationInfoDAO.getTodayMandalStartedStateWise(stateId);
			   setRequiredDIdsToList(rtrnTodayStatedMandalIdsLst,mandalTodayStartedIdsList,"Mandal");
			   
			   List<Long> rtrnTodayStatedMuncipalityIdsLst = tdpCadreLocationInfoDAO.getTodayLocalElectionBodyStartedStateWise(stateId);
			   setRequiredDIdsToList(rtrnTodayStatedMuncipalityIdsLst,muncipalityTodayStartedIdsList,"Muncipality");
			   
			   cadreRegistratedCountVO.setTodayStartedMandalMuncipalityCnt(Long.valueOf(rtrnTodayStatedMandalIdsLst.size()+rtrnTodayStatedMuncipalityIdsLst.size()));
			   
			   List<Long> rtrnAllMandalIds = constituencyTehsilDAO.getAllStateWiseTehsilIds(stateId);
			   setRequiredDIdsToList(rtrnAllMandalIds,mandalIdsList,"Mandal");
			   mandalIdsList.removeAll(mandalTodayStartedIdsList);
			   
			   List<Long> rtrnAllMuncipalityIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdsStateWise(stateId);
			   setRequiredDIdsToList(rtrnAllMuncipalityIdsList,muncipalityIdsList,"Muncipality");
			   muncipalityIdsList.removeAll(muncipalityTodayStartedIdsList);
			   
			   cadreRegistratedCountVO.setTodayNotStartedMandalMuncipalityCnt(Long.valueOf(mandalIdsList.size()+muncipalityIdsList.size()));
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(mandalIdsList);
			   cadreRegistratedCountVO.getLocationIdsList1().addAll(muncipalityIdsList);
			   
			return cadreRegistratedCountVO;          
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getStateDtlsTS in CoreDashboardCadreRegistrationService service", e);
		}
		return null;  
	}
	public void setRequiredDIdsToList(List<Long> idsList,List<Long> requiredList,String type){
		try{
			if(idsList != null && idsList.size() > 0){
				for(Long id:idsList){
					if(type.equalsIgnoreCase("Mandal")){
						requiredList.add(Long.valueOf("1"+id));
					}else if(type.equalsIgnoreCase("Muncipality")){
						requiredList.add(Long.valueOf("2"+id));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in setRequiredDIdsToList() in CoreDashboardCadreRegistrationService service", e);	
		}
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
			String type = "Total";
			//List<Object[]> totalRegCadreList = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,4l);
			//later open this query 
			//Long totalRegCadre2016 = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			Long totalRegCadre2016 = tdpCadreLocationInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,type);
			//select * from tdp_cadre_location_info TCLI where TCLI.location_scope_id = 2 and TCLI.location_value = 1 and TCLI.type = 'Total';
			//List<Object[]> totalRegCadreRenewalList = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			//later open this query
			//Long totalRegCadreRenewal2016 = tdpCadreDateWiseInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			Long totalRegCadreRenewal2016 = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,type);
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
			Long accessLevelValue =0l;      
			if(accessLvlId.longValue() == 4l){// user level 4 means parliament constituency
				accessLevelValue = 10l; //region scope 10  means parliament constituency 
			}else if(accessLvlId.longValue()==5l){
				accessLevelValue = 4l;    
			}else{
				accessLevelValue = accessLvlId;       
			}
			
			//List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l,null);
			List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCount( accessLevelValue,new HashSet<Long>(accessLvlValue), 4l);
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
			type = "Today";
			//List<Object[]> totalRegCadreListForToday = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,today, today,4l);
			//Long totalRegCadreForToday = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,toDt, toDt);
			Long totalRegCadreForToday = tdpCadreLocationInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,type);
			//List<Object[]> totalRegCadreRenewalListForToday = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,today, today);
			//Long totalRegCadreRenewalForToday = tdpCadreDateWiseInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,toDt, toDt);
			Long totalRegCadreRenewalForToday = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,type);
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
	public CadreRegistratedCountVO getTotalNewRenewalCadreStateWiseTS(Long activityMemberId,Long stateId,String startDate, String endDate){
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
			Long accessLvlId = 2l;
			List<Long> accessLvlValue = new ArrayList<Long>();
			accessLvlValue.add(36l);
			String type = "Total";
			//Long totalRegCadre2016 = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			//Long totalRegCadre2016 = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWiseTS(stateId,frmDt, toDt);
			Long totalRegCadre2016 = tdpCadreLocationInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,type);
			//Long totalRegCadreRenewal2016 = tdpCadreDateWiseInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt);
			//Long totalRegCadreRenewal2016 = tdpCadreDateWiseInfoDAO.getTotalRenewlCadreLocationWiseTS(stateId,frmDt, toDt);
			Long totalRegCadreRenewal2016 = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,type);
			//get total reg cadre
			if(totalRegCadre2016 != null){
				totalCadre = totalRegCadre2016;
			}
			
			//get total renewal cadre
			if(totalRegCadreRenewal2016 != null){
				totalRenewal = totalRegCadreRenewal2016;
			}
			
			//get tolal new cadre
			totalNew = totalCadre - totalRenewal;      
			//get target Count
			
			Long totalTarget = 0l;
			//List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l,null);
			List<Object[]> totalTargetList = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCount( 2l,new HashSet<Long>(accessLvlValue), 4l);  
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
			type = "Today";
			//List<Object[]> totalRegCadreListForToday = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,today, today,4l);
			//Long totalRegCadreForToday = tdpCadreDateWiseInfoDAO.getTotalCadreCountLocationWiseTS(stateId,toDt, toDt); 
			Long totalRegCadreForToday = tdpCadreLocationInfoDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,type);
			//List<Object[]> totalRegCadreRenewalListForToday = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,stateId,today, today);
			//Long totalRegCadreRenewalForToday = tdpCadreDateWiseInfoDAO.getTotalRenewlCadreLocationWiseTS(stateId,toDt, toDt);     
			Long totalRegCadreRenewalForToday = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWise(accessLvlId,accessLvlValue,type);
			//get total reg cadre for today
			totalCadreToday = 0l;
			if(totalRegCadreForToday != null){
				totalCadreToday = totalRegCadreForToday;    
			}
			
			//get total renewal cadre 
			totalCadreTodayRenewal = 0l;  
			if(totalRegCadreRenewalForToday != null){
				totalCadreTodayRenewal = totalRegCadreRenewalForToday;  
			}
			
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
	/**position two
	* @param  Long parentActivityMemberId  
	* @param  Long childUserTypeId
	* @param Long stateId
	* @return List<UserTypeVO>
	* @author Swadhin Lenka 
	* @Description :This Service Method is used to get selected child member and for userType.. 
	*  @since 15-Oct-2016
	*/
	public List<UserTypeVO> getSelectedChildTypeMembersForCadreReg(Long parentActivityMemberId,List<Long> childUserTypeIds,Long stateId,String startDate, String endDate, String sortingType){
	
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
		 			  List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,"total",null, null);
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
		 			  List<Object[]> totalRegCadreList = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCount(accessLevelValue,new ArrayList<Long>(entry.getValue()),stateId,"today",null, null);
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
		 	  /*if(resultList != null && resultList.size() > 0)  
		 	  {
		 		  Collections.sort(resultList, trainingMemberEligibleAttendedPercDesc);
		 	  }*/
		 	 if(sortingType.equalsIgnoreCase("TargetWise")){
		 		 if(resultList != null && resultList.size() > 0){
		 			 Collections.sort(resultList, trainingMemberEligibleAttendedPercDesc);
		 		 }
		 	 }else{  
		 		 if(resultList != null && resultList.size() > 0){
		 			 Collections.sort(resultList, cadreMemberEligibleAttendedPercDesc);      
		 		 }  
		 	 }
		 	 return resultList;	            
		}catch(Exception e){
			LOG.error("Error occured at getSelectedChildTypeMembersForTrainingProgram() in CoreDashboardMainService ",e);
		}
		return null;  
	}

	public String generatingAndSavingOTPDetails(Long tdpCadreId,String mobileNoStr){
		
		String status = null;
		try {
			String mobileNo=mobileNoStr.trim();
			if(mobileNoStr.length()>10)
				mobileNo = mobileNoStr.substring(mobileNoStr.length() - 10,mobileNoStr.length());
			//mobileNo=mobileNoStr;
			if(mobileNo.trim().equalsIgnoreCase("0")){
				mobileNo = tdpCadreDAO.getCadreMobileNumber(tdpCadreId);
			}else{
				TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
				cadreRegistrationService.saveDataToHistoryTable(tdpCadre);
				tdpCadre.setMobileNo(mobileNoStr);
				tdpCadreDAO.save(tdpCadre);
			}

			List<Object[]> existingOTPDtls = tabUserOtpDetailsDAO.isExistOTPDetails(mobileNo,new DateUtilService().getCurrentDateAndTime());
			if(existingOTPDtls != null && existingOTPDtls.size()>0L){
			//	Object[] obj = existingOTPDtls.get(existingOTPDtls.size()-1);
				Object[] obj = existingOTPDtls.get(0);
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
						//smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
						smsCountrySmsService.sendOTPSmsFromAdmin(message, true,phoneNumbers);
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
			try {
				if(mobileNo != null && mobileNo.trim().length() ==10)
					tabUserOtpDetailsDAO.getAllOtpsForSameMobile(mobileNo.trim());
			} catch (Exception e) {
				LOG.error(" Error occured while updating getAllOtpsForSameMobile as invalid ",e);
			}
		
			while(otpRand <= 0 && refRand <= 0){
				 otpRand = rnd.randomGenerator(6);
				 refRand = rnd.randomGenerator(6);
			}
				String refeRenceNo = String.valueOf(refRand);
				String otpNum = String.valueOf(otpRand);
				String message = "your OTP is "+otpRand+" for Reference Id # " +refRand+" This OTP Validate for Next 15 mins.";
				String[] phoneNumbers = {mobileNo.toString()};
				//smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
				smsCountrySmsService.sendOTPSmsFromAdmin(message, true,phoneNumbers);
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
			List<Object[]> locationIdAndNameList = null;    
			
			locationIdAndNameList = tdpCadreDAO.getLocationIdAndName(accessLvlId,accessLvlValue,stateId);
			List<Long> locIdList = new ArrayList<Long>();
			if(locationIdAndNameList != null && locationIdAndNameList.size() > 0){
				for(Object[] param: locationIdAndNameList){
					cadreRegistratedCountVO = new CadreRegistratedCountVO();
					cadreRegistratedCountVO.setId(param[0] != null ? (Long)param[0] : 0l);
					cadreRegistratedCountVO.setSourceName(param[1] != null ? param[1].toString() : "");
					cadreRegistratedCountVOs.add(cadreRegistratedCountVO);
					locIdList.add(param[0] != null ? (Long)param[0] : 0l);
				}
			}
			//get 2014 total target location wise 
			//List<Object[]> totalTargetList2014 = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 3l,null);
			Long accessLevelValue =0l;      
			if(accessLvlId.longValue() == 4l){// user level 4 means parliament constituency
				accessLevelValue = 10l; //region scope 10  means parliament constituency 
			}else if(accessLvlId.longValue()==5l){
				accessLevelValue = 4l;  
			}else{
				accessLevelValue = accessLvlId;     
			}
			List<Object[]> totalTargetList2014 = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCountSpecial(accessLevelValue,new HashSet<Long>(accessLvlValue), 3l);
			
			if(totalTargetList2014 != null && totalTargetList2014.size() > 0){       
				for(Object[] param : totalTargetList2014){
					if(locIdList.contains((Long)param[0])){
						setTarget2014ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}
				}
			}
			
			//get 2014 totalcadre location wise//xxxx 
			//List<Object[]> totalRegCadreList2014 = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,3l);
			List<Object[]> totalRegCadreList2014 = tdpCadreLocationInfoDAO.getTotalCadreCountLocationWise2014(accessLevelValue,accessLvlValue);
			if(totalRegCadreList2014 != null && totalRegCadreList2014.size() > 0){    
				for(Object[] param : totalRegCadreList2014){
					if(locIdList.contains((Long)param[0])){  
						setCdrCount2014ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}
				}
			}          
			//get 2016 total target location wise
			//List<Object[]> totalTargetList2016 = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId, 4l);
			//List<Object[]> totalTargetList2016 = tdpCadreTargetCountDAO.getTotalCadreTargetCountLocationWise(accessLvlId,new HashSet<Long>(accessLvlValue),stateId,4l,null);
			//List<Object[]> totalTargetList2016 = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCount(accessLvlId,new HashSet<Long>(accessLvlValue), 4l);
			List<Object[]> totalTargetList2016 = tdpCadreTargetCountDAO.getTtalCadreTargetCountScopeWiseCountSpecial(accessLevelValue,new HashSet<Long>(accessLvlValue), 4l);

			if(totalTargetList2016 != null && totalTargetList2016.size() > 0){    
				for(Object[] param : totalTargetList2016){
					if(locIdList.contains((Long)param[0])){  
						setTarget2016ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}  
				}
			}
			//get 2016 total cadre location wise
			
			//List<Object[]> totalRegCadreList2016 = tdpCadreDAO.getTotalCadreCountLocationWise(accessLvlId,accessLvlValue,stateId,frmDt, toDt,4l);
			List<Object[]> totalRegCadreList2016 = tdpCadreDateWiseInfoDAO.get2016TotalCadreCountLocationWiseCountSpecial(accessLevelValue,accessLvlValue,stateId,frmDt, toDt);
			if(totalRegCadreList2016 != null && totalRegCadreList2016.size() > 0){
				for(Object[] param : totalRegCadreList2016){
					if(locIdList.contains((Long)param[0])){
						setCdrCount2016ValueToMatchedVO((Long)param[0],cadreRegistratedCountVOs,(Long)param[1]);
					}
				}
			}
			//get 2016 renewal cadre  //
			List<Object[]> totalRegCadreRenewalList = tdpCadreDateWiseInfoDAO.get2016TotalRenewalCadreCountLocationWiseCount(accessLevelValue,accessLvlValue,stateId,frmDt, toDt);
			if(totalRegCadreRenewalList != null && totalRegCadreRenewalList.size() > 0){
				for(Object[] param : totalRegCadreRenewalList){  
					if(locIdList.contains((Long)param[0])){  
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
					if(count != null)
					vo.setTarget2014(count);
				}
			}
		}
	}
	public void setCdrCount2014ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					if(count != null)
					vo.setTotalCount2014(count);
				}
			}
		}  
	}
	public void setTarget2016ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					if(count != null)
					vo.setTarget(count);
				}
			}
		}
	}
	public void setCdrCount2016ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					if(count != null)
					vo.setTotalCount(count);
				}
			}
		}
	}
	public void setRenewCdrCount2016ValueToMatchedVO(Long locationValueId,List<CadreRegistratedCountVO> cadreRegistratedCountVOs,Long count){
		if(cadreRegistratedCountVOs != null && cadreRegistratedCountVOs.size() > 0){
			for(CadreRegistratedCountVO vo : cadreRegistratedCountVOs){
				if(vo.getId().equals(locationValueId)){
					if(count != null){
						vo.setRenewalCount(count);
					}
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
 /**
	* @param Long stateId
	* @param Long activityMemberId
	* @param startDate
	* @param endDate
	* @param sortingType
	* @return List<CadreReportVO>
	* @author Santosh 
	* @Description :This service method is used to get constituency wise details based on User. 
	* @since 16-OCT-2016
	*/
 public List<CadreReportVO> getConstituencyWiseReportBasedOnUserType(Long activityMemberId,Long stateId,String startDate, String endDate,String sortingType){
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,Long>  cadreTarget2014Map = new HashMap<Long, Long>();
	 Map<Long,Long> cadreTarget2016Map = new HashMap<Long, Long>();
	 Map<Long,CadreReportVO> locationWiseCadreDetaislMap = new HashMap<Long, CadreReportVO>();
	 Map<Long,String> locationIdAndNameMap = new HashMap<Long, String>();
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
            List<Object[]> rturnLctnObjLst = userAddressDAO.getConstituencyIdAndName(stateId,"Constituency");
             if(rturnLctnObjLst != null && rturnLctnObjLst.size() > 0){
            	 for(Object[] param:rturnLctnObjLst){
            		 locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])); 
            	 }
             }
		    List<Object[]> rtrn2014TargetObj = tdpCadreTargetCountDAO.getConstitiuencyWiseTargetBasedOnUserType(accessLvlId, new HashSet<Long>(accessLvlValue),3l);
		    setCadreTargetCntToMap(rtrn2014TargetObj,cadreTarget2014Map);
		    List<Object[]> rtrn2016TargetObj = tdpCadreTargetCountDAO.getConstitiuencyWiseTargetBasedOnUserType(accessLvlId, new HashSet<Long>(accessLvlValue),4l);
		    setCadreTargetCntToMap(rtrn2016TargetObj,cadreTarget2016Map);
		    
		    List<Object[]> rtrn2014CadreDtlsObjLst = tdpCadreLocationInfoDAO.getConstitiuencyWise2014CadreCountBasedOnUserType(accessLvlId, new HashSet<Long>(accessLvlValue));
			set2014CadreCountToMap(rtrn2014CadreDtlsObjLst, locationWiseCadreDetaislMap,locationIdAndNameMap);
			List<Object[]> rtrn2016CadreDtlsObjLst = tdpCadreDateWiseInfoDAO.getConstitiuencyWise2016CadreCountBasedOnUserType(accessLvlId,new HashSet<Long>(accessLvlValue),frmDt,toDt);
			set2016CadreCountToMap(rtrn2016CadreDtlsObjLst,locationWiseCadreDetaislMap);
			List<Object[]> rtrnR2016enewalObjList = tdpCadreDateWiseInfoDAO.getConstitiuencyWise2016RenewalCadreCountBasedOnUserType(accessLvlId,new HashSet<Long>(accessLvlValue),frmDt,toDt);
			setRenewalCountToMap(rtrnR2016enewalObjList,locationWiseCadreDetaislMap);
			
			  calculateNewCadreAnddPercentage(locationWiseCadreDetaislMap,cadreTarget2014Map,cadreTarget2016Map);
			  //sortring 
			  if(locationWiseCadreDetaislMap != null && locationWiseCadreDetaislMap.size() > 0){
				  resultList = new ArrayList<CadreReportVO>(locationWiseCadreDetaislMap.values());
			 }
			 if(resultList != null && resultList.size() > 0){
				   if(sortingType != null && sortingType.equalsIgnoreCase("TargetWise")){
					   Collections.sort(resultList, cadreRegistrationCountPerDeccAsc);	
					}else if(sortingType != null && sortingType.equalsIgnoreCase("2016CadreWise")){
						Collections.sort(resultList, cadreRegistrationCountDecc);
					}
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
						if(cadreReportVO.getTotal2016CadrePer() > 90 && cadreReportVO.getTotal2016CadrePer() <= 100){
					      goodCnt=goodCnt+1;
					    }
						if(cadreReportVO.getTotal2016CadrePer() > 80 && cadreReportVO.getTotal2016CadrePer() <= 90){
						  okCnt=okCnt+1;
					    }
						if(cadreReportVO.getTotal2016CadrePer() > 60 && cadreReportVO.getTotal2016CadrePer() <= 80){
						  poorCnt=poorCnt+1;
					   }
					   if(cadreReportVO.getTotal2016CadrePer() < 60){
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
		 LOG.error("Exception raised in getConstituencyWiseReportBasedOnUserType() in CoreDashboardCadreRegistrationService service", e); 
	 }
	 return resultList;
 }
 /**
	* @param Long stateId
	* @param List<Long> locationIdsList
	* @return List<CadreReportVO>
	* @author Santosh 
	* @Description :This service is used to get Started and Not Stared Constituencies Details. 
	* @since 20-OCT-2016
	*/
 public List<CadreReportVO> getLocationWiseCadreInfoTodayDetails(Long stateId,List<Long> locationIdsList){
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,CadreReportVO> cnsttncsDstrctMap = new HashMap<Long, CadreReportVO>();
	 try{
		 List<Object[]> rtrnDistObjList = districtConstituenciesDAO.getConstituenciesOfDistrictStateWise(stateId);
		 setDistrictToMap(rtrnDistObjList,cnsttncsDstrctMap);
		 List<Object[]> rtrnObjList = null;
		 if(locationIdsList != null && locationIdsList.size() > 0){
			    rtrnObjList = constituencyDAO.getDistAndConDtslByConstituenciesIds(locationIdsList);
			 	setLocationDtlsToList(rtrnObjList,resultList,cnsttncsDstrctMap,"False","Constituency");
		 }else{
			    rtrnObjList = tdpCadreLocationInfoDAO.getTodayTotalStartedRegistrationConstituencyDetailsStateWise(stateId); 
			    setLocationDtlsToList(rtrnObjList,resultList,cnsttncsDstrctMap,"True","Constituency");
		 }
	 }catch(Exception e){
		 LOG.error("Exception raised in getLocationWiseCadreInfo() in CoreDashboardCadreRegistrationService service", e); 
	 }
	 return resultList;
 }
 public void setLocationDtlsToList(List<Object[]> objList, List<CadreReportVO> resultList, Map<Long,CadreReportVO> cnsttncsDstrctMap,String isStartedCon,String locationType){
	 try{
		  if(objList != null && !objList.isEmpty()){
			  for(Object[] param:objList){
				  CadreReportVO locationVO = new CadreReportVO();
				  locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				  locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				  locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[2]));
				  if(cnsttncsDstrctMap.get(locationVO.getLocationId()) != null ){
				   locationVO.setId(cnsttncsDstrctMap.get(locationVO.getLocationId()).getId());
				   locationVO.setName(cnsttncsDstrctMap.get(locationVO.getLocationId()).getName());
				  }
				  locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[3]));
				  if(locationType != null && locationType.equalsIgnoreCase("Mandal") || locationType.equalsIgnoreCase("MUNCIPALITY")){
					  locationVO.setLocationId2(commonMethodsUtilService.getLongValueForObject(param[4]));
					  locationVO.setLocationName2(commonMethodsUtilService.getStringValueForObject(param[5]));
				  }
				  if(locationType != null && locationType.equalsIgnoreCase("Mandal") || locationType.equalsIgnoreCase("MUNCIPALITY")){
					 if(isStartedCon.equalsIgnoreCase("True")){
						  locationVO.setTotal2016CadreCnt(commonMethodsUtilService.getLongValueForObject(param[6]));	  
					 }
				  }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
					  if(isStartedCon.equalsIgnoreCase("True")){
						  locationVO.setTotal2016CadreCnt(commonMethodsUtilService.getLongValueForObject(param[4]));	  
					  }
				  }
				  resultList.add(locationVO);
			  }
		  } 
	 }catch(Exception e){
		 LOG.error("Exception raised in setLocationDtlsToList() in CoreDashboardCadreRegistrationService service", e); 
	 }
 }
 public void setDistrictToMap(List<Object[]> objList,Map<Long,CadreReportVO> cnsttncsDstrctMap){
	 if(objList != null && objList.size() > 0){
		 for(Object[] param:objList){
			 CadreReportVO cnsttuncyDistVO = new CadreReportVO(); 
			 cnsttuncyDistVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
			 cnsttuncyDistVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
			 cnsttncsDstrctMap.put(commonMethodsUtilService.getLongValueForObject(param[2]),cnsttuncyDistVO);
		 }
	 }
 }
    /**
	* @param Long stateId
	* @param List<Long> locationIdsList
	* @return List<CadreReportVO>
	* @author Santosh 
	* @Description :This service is used to get Mandal Muncipality Started and Not Stared Details. 
	* @since 21-OCT-2016
	*/
 public List<CadreReportVO> getMandalMuncipalityStatedAndNotStatedDetails(Long stateId,List<Long> locationIdsList){
	 List<CadreReportVO> resultList = new ArrayList<CadreReportVO>();
	 Map<Long,CadreReportVO> cnsttncsDstrctMap = new HashMap<Long, CadreReportVO>();
	 try{
		 List<Object[]> rtrnDistObjList = districtConstituenciesDAO.getConstituenciesOfDistrictStateWise(stateId);
		 setDistrictToMap(rtrnDistObjList,cnsttncsDstrctMap);
		 
		 List<Object[]> rtrnObjList = null;
		 if(locationIdsList != null && locationIdsList.size() > 0){
			  List<Long> mandalIdsList = new ArrayList<Long>();
			  List<Long> muncipalityIdsList = new ArrayList<Long>();
			 for(Long id:locationIdsList){
				 	String strLocationId = id.toString();
				 	Long firstDigit = Long.valueOf(strLocationId.substring(0, 1));
				 		if(firstDigit.longValue() == 1l){ //mandal
				 			mandalIdsList.add(Long.valueOf(strLocationId.substring(1,strLocationId.length()))); 
				 		}else if(firstDigit.longValue() == 2l){//MUNCIPALITY
				 			muncipalityIdsList.add(Long.valueOf(strLocationId.substring(1,strLocationId.length())));
				 		}
			 }
				 rtrnObjList = constituencyTehsilDAO.getTehsilDtlsStateWise(stateId, mandalIdsList);
			 	 setLocationDtlsToList(rtrnObjList,resultList,cnsttncsDstrctMap,"False","Mandal");
			 	 rtrnObjList = assemblyLocalElectionBodyDAO.getLocalElectionBodyStateWise(stateId, muncipalityIdsList);
			 	 setLocationDtlsToList(rtrnObjList,resultList,cnsttncsDstrctMap,"False","MUNCIPALITY");
		 }else{
			    rtrnObjList = tdpCadreLocationInfoDAO.getTodayMandalStartedDtlsStateWise(stateId); 
			    setLocationDtlsToList(rtrnObjList,resultList,cnsttncsDstrctMap,"True","Mandal");
			    rtrnObjList = tdpCadreLocationInfoDAO.getTodayLocalElectionBodyStartedDtlsStateWise(stateId); 
			    setLocationDtlsToList(rtrnObjList,resultList,cnsttncsDstrctMap,"True","MUNCIPALITY");
		 }
	 }catch(Exception e){
		 LOG.error("Exception raised in getMandalMuncipalityStatedAndNotStatedDetails() in CoreDashboardCadreRegistrationService service", e); 
	 }
	 return resultList;
 }
 
 	public String getLocationWiseRegistrationSMSTracking()
	{
 		String result="";
 		String message="";
		try{
			
			SimpleDateFormat remainingDateSdf = new SimpleDateFormat("dd/MM/yyyy");
			
			List<SurveyInfoVO> finalMobileList = new ArrayList<SurveyInfoVO>();
			Map<String,Map<Long,Map<Long,SurveyInfoVO>>> mobileMap = new HashMap<String, Map<Long,Map<Long,SurveyInfoVO>>>();
			//0.mobileNo,1.locationScopeId,2.locationValue,3.locationName,4.targetCount,5.type,6.cadreCount,7.personName,8.group
			//9.2014Cadre,10.renewel
			List<Object[]> listObj =  tdpCadreSmsLeaderLocationDAO.locationWiseRegistrationSMSTracking();			
			
			Map<String,String> mobileNameMap = new HashMap<String, String>();
			Map<String,String> groupMap = new LinkedHashMap<String, String>();
			
			if(listObj !=null && listObj.size()>0){
				for (Object[] objects : listObj) {
					Map<Long,Map<Long,SurveyInfoVO>> locationScopeMap = mobileMap.get(objects[0] !=null ? objects[0].toString():"");					
					if(locationScopeMap == null){
						locationScopeMap = new HashMap<Long,Map<Long,SurveyInfoVO>>();
						mobileMap.put(objects[0].toString(), locationScopeMap);
					}
					Map<Long,SurveyInfoVO> locationMap = locationScopeMap.get(objects[1] !=null ? (Long)objects[1]:0l);
					
					if(locationMap == null){
						locationMap = new HashMap<Long, SurveyInfoVO>();
						locationScopeMap.put(objects[1] !=null ? (Long)objects[1]:0l, locationMap);
					}
					SurveyInfoVO VO = locationMap.get(objects[2] !=null ? (Long)objects[2]:0l);
					
					if(VO == null){
						VO = new SurveyInfoVO();
						VO.setTotal2014Cadre(objects[9] !=null ? (Long)objects[9]:0l);
						locationMap.put(objects[2] !=null ? (Long)objects[2]:0l, VO);
					}
					
					VO.setPhoneNo(objects[0] !=null ? objects[0].toString():"");					
					VO.setLocationId(objects[1] !=null ? (Long)objects[1]:0l);
					VO.setLocationValue(objects[2] !=null ? (Long)objects[2]:0l);
					VO.setName(objects[3] !=null ? objects[3].toString():"");
					VO.setTargetCount(objects[4] !=null ? (Long)objects[4]:0l);
					if(objects[5] !=null && !objects[5].toString().trim().isEmpty() && objects[5].toString().trim().equalsIgnoreCase("today")){						
						VO.setTodayCount(objects[6] !=null ? (Long)objects[6]:0l);						
					}else if(objects[5] !=null && !objects[5].toString().trim().isEmpty() && objects[5].toString().trim().equalsIgnoreCase("total")){
						VO.setTotalCount(objects[6] !=null ? (Long)objects[6]:0l);	
						VO.setTotalRenewalCadre(objects[10] !=null ? (Long)objects[10]:0l);
					}
					VO.setEmailId(objects[7] !=null ? objects[7].toString():"");
					VO.setVoterCardNo(objects[8] !=null ? objects[8].toString():"");

					VO.setRemainingRenewalCadre(VO.getTotal2014Cadre() - VO.getTotalRenewalCadre());
					
					mobileNameMap.put(VO.getPhoneNo(), VO.getEmailId());
					groupMap.put(VO.getPhoneNo(), VO.getVoterCardNo());
					
				}
				
				if(mobileMap !=null && mobileMap.size()>0){
					for (Entry<String, Map<Long, Map<Long, SurveyInfoVO>>> mobile : mobileMap.entrySet()) {
						
						SurveyInfoVO mainVo = new SurveyInfoVO();
						
						mainVo.setPhoneNo(mobile.getKey());						
						Map<Long, Map<Long, SurveyInfoVO>> scopeMap = mobile.getValue();
						
						if(scopeMap !=null){							
							List<SurveyInfoVO> scopeList = new ArrayList<SurveyInfoVO>();
							for (Entry<Long, Map<Long, SurveyInfoVO>> scope : scopeMap.entrySet()) {
								SurveyInfoVO scopeVo = new SurveyInfoVO();									
								scopeVo.setLocationId(scope.getKey());									
								Map<Long,SurveyInfoVO> locationMap = scope.getValue();
								if(locationMap !=null){																		
									scopeVo.setSurveyInfoVOList(new ArrayList<SurveyInfoVO>(locationMap.values()));
									scopeList.add(scopeVo);
								}																
							}
							mainVo.setSurveyInfoVOList(scopeList);
						}	
						
						finalMobileList.add(mainVo);
					}
				}
				
				if(finalMobileList !=null && finalMobileList.size()>0){
					for (SurveyInfoVO mainVo : finalMobileList) {	
						
						StringBuilder dearStr = new StringBuilder();
						StringBuilder overallStr = new StringBuilder();
						StringBuilder  messageStr= new StringBuilder();
						StringBuilder  bottomStr= new StringBuilder();
						
						Long totalTarget=0l;
						Long totalAchieved=0l;
						Long totalToDay=0l;	                 
						
						Long totalCadre = 0l;
						Long totalRenewal = 0l;
						Long totalYetToRenewal = 0l;
						
						if(mainVo !=null && mainVo.getSurveyInfoVOList() !=null && mainVo.getSurveyInfoVOList().size()>0){
							
							for (SurveyInfoVO scopeVo : mainVo.getSurveyInfoVOList()) {									
																
								if(scopeVo !=null && scopeVo.getSurveyInfoVOList() !=null &&
										scopeVo.getSurveyInfoVOList().size()>0){
									
									for (SurveyInfoVO location : scopeVo.getSurveyInfoVOList()) {	
										
										Double achevedPerc= 0.0 ;
										if(location.getTotalCount() !=null && location.getTotalCount()>0l){
											achevedPerc=calcPercantage(location.getTotalCount(),location.getTargetCount());
										}
									
										//mobileNameMap.put(location.getPhoneNo(), location.getEmailId());
										//groupMap.put(location.getPhoneNo(), location.getVoterCardNo());
										Double renewalPerc= 0.0 ;
										if(location.getTotal2014Cadre() !=null && location.getTotal2014Cadre()>0l){
											renewalPerc=calcPercantage(location.getTotalRenewalCadre(),location.getTotal2014Cadre());
										}
										Double yetToRenewal= 0.0 ;
										if(location.getTotal2014Cadre() !=null && location.getTotal2014Cadre()>0l){
											yetToRenewal=calcPercantage(location.getRemainingRenewalCadre(),location.getTotal2014Cadre());
										} 
										if(achevedPerc !=null && achevedPerc>0.0){
											messageStr.append("\n"+location.getName()+ 
													":\n Membership Target  : "+location.getTargetCount()+ 
													",\n Achieved : "+location.getTotalCount()+" ("+achevedPerc+ " % )" +
													",\n Today : "+location.getTodayCount()+
													",\n 2014 total cadre :" +location.getTotal2014Cadre()+
													",\n 2014 Renewal Cadre :"+location.getTotalRenewalCadre() +"("+renewalPerc+" %)" +
													",\n Not Yet Renewal Cadre: "+location.getRemainingRenewalCadre() +" ("+yetToRenewal+"%) \n");  
										}else{
											messageStr.append("\n"+location.getName()+ 
													":\n Membership Target  : "+location.getTargetCount()+
													",\n Achieved : "+location.getTotalCount()+
													",\n Today : "+location.getTodayCount()+
													",\n 2014 total cadre :" +location.getTotal2014Cadre()+
													",\n 2014 Renewal Cadre :"+location.getTotalRenewalCadre() +"("+renewalPerc+" %)" +
													",\n Not Yet Renewal Cadre: "+location.getRemainingRenewalCadre() +" ("+yetToRenewal+"%) \n");     
										}             
										
										totalTarget = totalTarget + location.getTargetCount();  
										totalAchieved = totalAchieved + location.getTotalCount();
										totalToDay = totalToDay + location.getTodayCount();
										
										totalCadre = totalCadre + location.getTotal2014Cadre();
										totalRenewal = totalRenewal + location.getTotalRenewalCadre();
										totalYetToRenewal = totalYetToRenewal + location.getRemainingRenewalCadre();
										
									}
								}
																
							}
							
							Double totalAchevedPerc= 0.0 ;
							if(totalAchieved !=null && totalAchieved>0l){
								totalAchevedPerc=calcPercantage(totalAchieved,totalTarget);
							}
							Double totalRenewalPerc= 0.0 ;
							if(totalCadre !=null && totalCadre>0l){
								totalRenewalPerc=calcPercantage(totalRenewal,totalCadre);
							}
							Double totalYetToRenewalPerc= 0.0 ;
							if(totalCadre !=null && totalCadre>0l){
								totalYetToRenewalPerc=calcPercantage(totalYetToRenewal,totalCadre);  
							}  
							
							if(groupMap.get(mainVo.getPhoneNo()) !=null && groupMap.get(mainVo.getPhoneNo()).trim().equalsIgnoreCase("Y")){
								if(totalAchevedPerc !=null && totalAchevedPerc>0.0){
									overallStr.append(" Membership Total Target :"+totalTarget+
											" ,\n Total Achieved : "+totalAchieved+" ("+totalAchevedPerc+" %) " +
											" ,\n Today :"+totalToDay+
											" ,\n Total Cadre 2014 :"+totalCadre+
											" ,\n Total Renewal 2014: "+totalRenewal+" ("+totalRenewalPerc+" %)" +
										    " ,\n Total Yet To Renewal : "+totalYetToRenewal+" ("+totalYetToRenewalPerc+" %)  \n\n");
								}else{
									overallStr.append(" Membership Total Target :"+totalTarget+
											" ,\n Total Achieved : "+totalAchieved+
											" ,\n Today :"+totalToDay+
											" ,\n Total Cadre 2014 :"+totalCadre+
											" ,\n Total Renewal 2014: "+totalRenewal+" ("+totalRenewalPerc+" %) " +
											" ,\n Total Yet To Renewal : "+totalYetToRenewal+" ("+totalYetToRenewalPerc+" %)  \n\n ");
								}   
							}   
							
							//overallStr.append(" ............................................ \n ");
							
							
							dearStr.append(" Dear "+mobileNameMap.get(mainVo.getPhoneNo())+" Garu, \n" );
							
							
							
							String startDate = IConstants.CADRE_REGISTRATION_START_DATE;
							Long totalDays = IConstants.CADRE_REGISTRATION_2016_DAYS;
							String currentDate = dateUtilService.getCurrentDateInStringFormat();
							
							
							//get nRemaining Days Of Registration
							 	Date date1 = remainingDateSdf.parse(startDate);
							    Date date2 = remainingDateSdf.parse(currentDate);
							    long diff = date2.getTime() - date1.getTime();
							    Long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1;
								
							    Long remainingDays =0l;
							    Long delayDays =0l;
							    if(totalDays > days)
							    	remainingDays = totalDays.longValue() - days.longValue();	
							    else
							    	delayDays = days.longValue() - totalDays.longValue();
							
							//bottomStr.append(" ................... \n\n ");
							
							/* if(totalDays > days)
								 bottomStr.append("\n We have Only "+remainingDays+" Days left to reach our target.\n" +
								 		          " PLEASE CONCENTRATE ON THE RENEWALS TO COMPLETE AS SOON AS POSSIBLE .\n ");*/    
							    
							    bottomStr.append(" PLEASE CONCENTRATE ON THE RENEWALS TO COMPLETE AS SOON AS POSSIBLE .\n ");
							
							
							 dearStr.append(overallStr.toString()).append(messageStr.toString()).append(bottomStr.toString());
							
							if(dearStr !=null && !dearStr.toString().trim().isEmpty())
							{
								String[] mobile = new String[1];									
								mobile[0] = mainVo.getPhoneNo();
								//System.out.println(dearStr.toString());
								message=message+" ";
								smsCountrySmsService.sendSmsFromAdmin(dearStr.toString(), true, mobile);																	
							}
						}	
					}
				}
			}
			result="success";
		}catch(Exception e){
			result="failure";
			LOG.error("Exception raised in getLocationWiseRegistrationSMSTracking() and message is "+message+" in CoreDashboardCadreRegistrationService service", e); 
		}
		return result;
	}
 
 	public Double calcPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
 	//swadhin
 	public List<FieldReportVO> getHourWiseRegDtls(Long stateId, String option){
 		try{
 			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
 			DateUtilService dateUtilService = new DateUtilService();
 			Date dt = dateUtilService.getCurrentDateAndTime();
 			Calendar calendar = Calendar.getInstance();
 			calendar.setTime(dt);
 			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);    
			Date lastOneDayTime = calendar.getTime(); 
 			
 			Date surveyDate = dateUtilService.getCurrentDateAndTime();
 			List<Long> hourList = new ArrayList<Long>(){{add(0l);add(1l);add(2l);add(3l);add(4l);add(5l);add(6l);add(7l);}};
 			//put("Upto 8 AM",8l);
 			Map<String,Long> labelAndPositionMap = new HashMap<String,Long>(){{  
 				put("08-09",8l);put("09-10",9l);put("10-11",10l);put("11-12",11l);put("12-13 PM",12l);put("13-14",13l);put("14-15",14l);put("15-16",15l);
 				put("16-17",16l);put("17-18",17l);put("18-19",18l);put("19-20",19l);put("20-21",20l);put("21-22",21l);put("22-23",22l);put("23-24",23l);
 			}};  
 			List<FieldReportVO> fieldReportVOs = new ArrayList<FieldReportVO>();
 			FieldReportVO fieldReportVO = null;
 			List<Object[]> regDtlsListForToday = null;
 			List<Object[]> regDtlsListForYesterDay = null;
 			if(option.equalsIgnoreCase("total")){
 				regDtlsListForToday = tdpCadreHourRegInfoDAO.getHourWiseCombineReport(surveyDate);  
 	 			regDtlsListForYesterDay = tdpCadreHourRegInfoDAO.getHourWiseCombineReport(lastOneDayTime);
 			}else{
 				regDtlsListForToday = tdpCadreHourRegInfoDAO.getHourWiseReport(stateId,surveyDate);
 	 			regDtlsListForYesterDay = tdpCadreHourRegInfoDAO.getHourWiseReport(stateId,lastOneDayTime);
 			}
 			
 			Map<Long,Long> hourAndRegCountMap = new HashMap<Long,Long>();
 			Map<Long,Long> hourAndUserCountMap = new HashMap<Long,Long>();
 			if(regDtlsListForToday != null && regDtlsListForToday.size() > 0){
 				for(Object[] param : regDtlsListForToday){
 					hourAndRegCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
 					hourAndUserCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
 				}
 			}
 			Long totalCadreUpto8amToday = 0l;
 			Long totalSurveyUserUpto8amToday = 0l;
 			
 			for(Long param : hourList){
 				if( hourAndRegCountMap.get(param) != null)
 					totalCadreUpto8amToday = totalCadreUpto8amToday + hourAndRegCountMap.get(param);
 				if( hourAndUserCountMap.get(param) != null)
 					totalSurveyUserUpto8amToday = totalSurveyUserUpto8amToday + hourAndUserCountMap.get(param);
 			}
 			//for yesterday
 			Map<Long,Long> hourAndRegCountYestMap = new HashMap<Long,Long>();
 			Map<Long,Long> hourAndUserCountYestMap = new HashMap<Long,Long>();
 			if(regDtlsListForYesterDay != null && regDtlsListForYesterDay.size() > 0){
 				for(Object[] param : regDtlsListForYesterDay){
 					hourAndRegCountYestMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
 					hourAndUserCountYestMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
 				}
 			}
 			Long totalCadreUpto8amYesterDay = 0l;
 			Long totalSurveyUserUpto8amYesterDay = 0l;
 			for(Long param : hourList){
 				if( hourAndRegCountYestMap.get(param) != null)
 					totalCadreUpto8amYesterDay = totalCadreUpto8amYesterDay + hourAndRegCountYestMap.get(param);
 				if( hourAndUserCountYestMap.get(param) != null)
 					totalSurveyUserUpto8amYesterDay = totalSurveyUserUpto8amYesterDay + hourAndUserCountYestMap.get(param);
 			}
 			//special for upto 8am
 			fieldReportVO = new FieldReportVO();
 			fieldReportVO.setLabel("Upto 8 AM");
 			fieldReportVO.setOrder(7l);
 			fieldReportVO.setTodayTotalReg(totalCadreUpto8amToday);
 			fieldReportVO.setTodayTotalUsers(totalSurveyUserUpto8amToday);
 			fieldReportVO.setLastDayTotalReg(totalCadreUpto8amYesterDay);
 			fieldReportVO.setLastDayTotalUsers(totalSurveyUserUpto8amYesterDay);
 			
 			//add first
 			fieldReportVOs.add(fieldReportVO);
 			//now add all
 			for(Entry<String,Long> entry : labelAndPositionMap.entrySet()){
 				fieldReportVO = new FieldReportVO();
 				fieldReportVO.setLabel(entry.getKey());
 				fieldReportVO.setOrder(entry.getValue());
 				if(hourAndRegCountMap.get(entry.getValue()) != null)
 					fieldReportVO.setTodayTotalReg(hourAndRegCountMap.get(entry.getValue()));
 				if(hourAndUserCountMap.get(entry.getValue()) != null)
 					fieldReportVO.setTodayTotalUsers(hourAndUserCountMap.get(entry.getValue()));
 				if(hourAndRegCountYestMap.get(entry.getValue()) != null)
 					fieldReportVO.setLastDayTotalReg(hourAndRegCountYestMap.get(entry.getValue()));
 				if(hourAndUserCountYestMap.get(entry.getValue()) != null)
 					fieldReportVO.setLastDayTotalUsers(hourAndUserCountYestMap.get(entry.getValue()));
 				fieldReportVOs.add(fieldReportVO);
 			}
 			if(fieldReportVOs != null && fieldReportVOs.size() > 0){
				Collections.sort(fieldReportVOs, shortTimeAsc);
			}
 			return fieldReportVOs;  
 		}catch(Exception e){
 			e.printStackTrace();
 			LOG.error("Exception raised in getHourWiseRegDtls() in CoreDashboardCadreRegistrationService service", e);
 		}
 		return null;  
 		
 	}
 	public static Comparator<FieldReportVO> shortTimeAsc = new Comparator<FieldReportVO>() {
		public int compare(FieldReportVO member2, FieldReportVO member1) {

			Long perc2 = member2.getOrder();  
			Long perc1 = member1.getOrder();      
			//ascending order of percantages.
			 return perc2.compareTo(perc1);      
		}
	}; 
 	public List<CadreReportVO> getTodayAndYesterdayTabUserRgstrtnComparisonDetails(Long stateId){
 		List<CadreReportVO> resultList = new ArrayList<CadreReportVO>(0);
 		Map<Long,Long> activeUserMap = new HashMap<Long, Long>();
 		Map<Long,CadreReportVO> tabUserDetailsMap = new HashMap<Long, CadreReportVO>();
 		Date today = dateUtilService.getCurrentDateAndTime();
 		try{
 			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);  
			Date lastOneHourTime = cal.getTime();
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(today);
			cal1.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);  
			Date yesterdayDate = cal1.getTime();
			
 		   List<Object[]> rtrnActiveUserList = tabUserEnrollmentInfoDAO.getActiveTabUserDtls(stateId, lastOneHourTime);
 		   List<Object[]> todayTabUserDtlsObjLst = tabUserEnrollmentInfoDAO.getTabUserWiseTotalRegistrationDetails(stateId, today);
 		   List<Object[]> yesTabUserDtlsObjLst = tabUserEnrollmentInfoDAO.getTabUserWiseTotalRegistrationDetails(stateId, yesterdayDate);
	 	   setActiveUserDtls(rtrnActiveUserList,activeUserMap);
	 	   setYesterDayTabUserDetails(yesTabUserDtlsObjLst,tabUserDetailsMap);
	 	   setTodayTabUserDetails(todayTabUserDtlsObjLst,tabUserDetailsMap,activeUserMap);
 		   
		   if(tabUserDetailsMap != null && tabUserDetailsMap.size() > 0){
			   resultList.addAll(new ArrayList<CadreReportVO>(tabUserDetailsMap.values()));
			   tabUserDetailsMap.clear();
		   }
	 	}catch(Exception e){
 			LOG.error("Exception raised in getTodayAndYesterdayTabUserComparisonDetails() in CoreDashboardCadreRegistrationService service", e);	
 		}
 		return resultList;
 	}
 	public void setActiveUserDtls(List<Object[]> objList,Map<Long,Long> activeUserMap){
 		try{
 			if(objList != null && objList.size() > 0){
 	 			for(Object[] param:objList){
 	 				activeUserMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
 	 			}
 	 		}
 		}catch(Exception e){
			LOG.error("Exception raised in setActiveUserDtls() in CoreDashboardCadreRegistrationService service", e);	
 		}
 	}
  public void setTodayTabUserDetails(List<Object[]> objList,Map<Long,CadreReportVO> tabUserMap,Map<Long,Long> activeUserMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  CadreReportVO tabUserVO = tabUserMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				   if(tabUserVO == null){
					    tabUserVO = new CadreReportVO();
		 				tabUserVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
		 				tabUserVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
		 				tabUserVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
		 				tabUserVO.setImagePath(commonMethodsUtilService.getStringValueForObject(param[3]));
		 				tabUserVO.setTodayRegCount(commonMethodsUtilService.getLongValueForObject(param[4]));
		 				if(activeUserMap.get(tabUserVO.getId()) != null && activeUserMap.get(tabUserVO.getId()) > 0l){
		 					tabUserVO.setIsActive("Yes");	
		 				}
		 				tabUserVO.setTodayPersent("Present");
		 				tabUserVO.setYesPersent("Absent");
		 				tabUserMap.put(tabUserVO.getId(), tabUserVO);   
				   }else{
					    tabUserVO.setTodayRegCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					    tabUserVO.setTodayPersent("Present");
						if(activeUserMap.get(tabUserVO.getId()) != null && activeUserMap.get(tabUserVO.getId()) > 0l){
		 					tabUserVO.setIsActive("Yes");	
		 				}
		 		   }
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception raised in setTodayTabUserDetails() in CoreDashboardCadreRegistrationService service", e);  
	  }
  }
  public void setYesterDayTabUserDetails(List<Object[]> objList,Map<Long,CadreReportVO> tabUserDtlsMap){
 	try{
 		if(objList != null && objList.size() > 0){
 			for(Object[] param:objList){
 				CadreReportVO tabUserVO = new CadreReportVO();
 				tabUserVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
 				tabUserVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
 				tabUserVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
 				tabUserVO.setImagePath(commonMethodsUtilService.getStringValueForObject(param[3]));
 				tabUserVO.setYesRegCount(commonMethodsUtilService.getLongValueForObject(param[4]));
 				tabUserVO.setYesPersent("Present");
 				tabUserVO.setTodayPersent("Absent");
 				tabUserDtlsMap.put(tabUserVO.getId(), tabUserVO);
 			}
 		}
 	}catch(Exception e){
 		LOG.error("Exception raised in setActiveUserDtls() in CoreDashboardCadreRegistrationService service", e);	
 	}
 }
  public List<CadreBasicVO> getCadreSurveyUserDetailsByLocationIds(String locationType,List<Long> locationValues,String fromDateStr,String toDateStr){
	  List<CadreBasicVO> resultList = new ArrayList<CadreBasicVO>(0);
	  Map<Long,CadreBasicVO> sryUsrTackngDtlsMap = new LinkedHashMap<Long, CadreBasicVO>(0);
	  Map<Long,CadreBasicVO> surveyUserMap = new HashMap<Long, CadreBasicVO>(0);
 	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	  Date toDate=null;
	  Date fromDate=null;
	  try{
		  if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
		 }
		List<Object[]> rtrnUserTrackingObjList = tabUserLocationDetailsDAO.getSurveyUserLatestTimeLongitudeAndLatituedeLocationWise(locationType, locationValues, fromDate, toDate); 
		setSurveyTrackingDtlsToMap(rtrnUserTrackingObjList,sryUsrTackngDtlsMap);
		List<Object[]> rtrnSurveyUserLst = cadreSurveyUserAssignDetailsDAO.getCadreSurveyUserDtlsLocationWise(locationType, locationValues, fromDate, toDate);
		setSurveyUserDtls(rtrnSurveyUserLst,surveyUserMap,sryUsrTackngDtlsMap,locationType);
		List<Object[]> rtrnUserBasicInfoObjLst = tabUserInfoDAO.getSurveyUserBasicDetailsBySurveyUserIds(new ArrayList<Long>(surveyUserMap.keySet()));
		if(rtrnUserBasicInfoObjLst != null && rtrnUserBasicInfoObjLst.size() > 0){
			for(Object[] param:rtrnUserBasicInfoObjLst){
				Long surveyUserId = commonMethodsUtilService.getLongValueForObject(param[0]);
				if(surveyUserMap.get(surveyUserId) != null ){
					surveyUserMap.get(surveyUserId).setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					surveyUserMap.get(surveyUserId).setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
				}
			}
		}
	  }catch (Exception e){
		 LOG.error("Exception raised in setActiveUserDtls() in CoreDashboardCadreRegistrationService service", e);
	  }
	  return resultList;
  }
  public void setSurveyUserDtls(List<Object[]> objList,Map<Long,CadreBasicVO> surveyUserMap,Map<Long,CadreBasicVO> sryUsrTackngDtlsMap,String locationType){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  CadreBasicVO surveyUserVO = new CadreBasicVO();
				  surveyUserVO.setSurveyUserId(commonMethodsUtilService.getLongValueForObject(param[0]));
				  surveyUserVO.setUserName(commonMethodsUtilService.getStringValueForObject(param[1]));
				//  surveyUserVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[2]));
			//	  surveyUserVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
				  if(locationType != null && locationType.equalsIgnoreCase("District")){
				//	surveyUserVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[4]));
					//surveyUserVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[5]));
				  }
				  if(sryUsrTackngDtlsMap != null && sryUsrTackngDtlsMap.size() > 0 && sryUsrTackngDtlsMap.get(surveyUserVO.getSurveyUserId()) != null){
					  surveyUserVO.setSurveyTime(sryUsrTackngDtlsMap.get(surveyUserVO.getSurveyUserId()).getSurveyTime());
					  surveyUserVO.setLatitude(sryUsrTackngDtlsMap.get(surveyUserVO.getSurveyUserId()).getLatitude());
					  surveyUserVO.setLatitude(sryUsrTackngDtlsMap.get(surveyUserVO.getSurveyUserId()).getLongititude());
				  }
			  }
		  }
		  
	  }catch(Exception e){
		  LOG.error("Exception raised in setSurveyUserDtls() in CoreDashboardCadreRegistrationService service", e); 
	  }
  }
  public void setSurveyTrackingDtlsToMap(List<Object[]> objList, Map<Long,CadreBasicVO> sryUsrTackngDtlsMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  CadreBasicVO suveyUserVO = new CadreBasicVO();
				   suveyUserVO.setSurveyUserId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   suveyUserVO.setSurveyTime(commonMethodsUtilService.getStringValueForObject(param[1]));
				   suveyUserVO.setLatitude(commonMethodsUtilService.getStringValueForObject(param[2]));
				   suveyUserVO.setLongititude(commonMethodsUtilService.getStringValueForObject(param[3]));
				   suveyUserVO.setName(" ");
				   suveyUserVO.setMobileNo(" ");
				   sryUsrTackngDtlsMap.put(suveyUserVO.getSurveyUserId(), suveyUserVO);
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception raised in setSurveyTrackingDtlsToMap() in CoreDashboardCadreRegistrationService service", e);  
	  }
  }
  
//swadhin
 public CadreBasicVO getUserTrackngDtslBySurveyUserId(Long cadreSurveyUserId,String fromDateStr,String toDateStr){
	  CadreBasicVO resultVO = new CadreBasicVO();
	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	  Date toDate=null;
	  Date fromDate=null;
	  Date today = null;
	 try{
		 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 toDate = sdf.parse(toDateStr);
			 fromDate = sdf.parse(fromDateStr);
		 }else{
			 toDate = new DateUtilService().getCurrentDateAndTime();
			 fromDate = new DateUtilService().getCurrentDateAndTime();
		 }
		 today = new DateUtilService().getCurrentDateAndTime();
		 List<CadreBasicVO> userTrackingDtlList = new ArrayList<CadreBasicVO>(0);
		 List<CadreBasicVO> eryFveMntsDtlsLstUsrTrckngLst = new ArrayList<CadreBasicVO>(0);
		 
		 List<Object[]> rtrnTrackingDtlsLst = tdpCadreDAO.getUserTrackingDetails(cadreSurveyUserId, fromDate, toDate);
		 List<Object[]> rtrnEvry5MinutesUsrTrkngDtlList = tabUserLocationDetailsDAO.getSurveyUserTrackingDtls(cadreSurveyUserId, fromDate, toDate);
		 
		 setDUserTrackingDtlsToList(rtrnTrackingDtlsLst,userTrackingDtlList,"samples");
		 setDUserTrackingDtlsToList(rtrnEvry5MinutesUsrTrkngDtlList,eryFveMntsDtlsLstUsrTrckngLst,null);
		 
 		 if(userTrackingDtlList != null && userTrackingDtlList.size() > 0){
 			 resultVO.setSubList1(userTrackingDtlList);
 		 }
 		 if(eryFveMntsDtlsLstUsrTrckngLst != null && eryFveMntsDtlsLstUsrTrckngLst.size() > 0){
 			 resultVO.setSubList2(eryFveMntsDtlsLstUsrTrckngLst);  
 		 }
 		//between 8pm -> 11PM
 		List<Long> eightPmTo11PMHursList = new ArrayList<Long>(){{add(20l);add(21l);add(22l);add(23l);}};
 		//between 12am -> 8am
 		List<Long> twelveAmTo8AmHursList = new ArrayList<Long>(){{add(0l);add(1l);add(2l);add(3l);add(4l);add(5l);add(6l);add(7l);}};
 		
 		
 		//between 8am -> 8pm  
		Map<String,Long> labelAndPositionMap = new HashMap<String,Long>(){{  
			put("8am-9am",8l);put("9am-10am",9l);put("10am-11am",10l);put("11am-12pm",11l);
			put("12pm-1pm",12l);put("1pm-2pm",13l);put("2pm-3pm",14l);put("3pm-4pm",15l);
			put("4pm-5pm",16l);put("5pm-6pm",17l);put("6pm-7pm",18l);put("7pm-8pm",19l);
		}}; 
 		 
 		 
 		 	List<Object[]> regDtlsHourWiseList = tdpCadreUserHourRegInfoDAO.getRegDtlsHourWiseList(cadreSurveyUserId,today);
 		 	Map<Long,Long> hourAndRegCountMap = new HashMap<Long,Long>();
			
			if(regDtlsHourWiseList != null && regDtlsHourWiseList.size() > 0){
				for(Object[] param : regDtlsHourWiseList){
					hourAndRegCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
			Long totalRegToday = 0l;
			Long eightPmTo11PmTodalRegCnt =0l;
			for(Long param : eightPmTo11PMHursList){
				if( hourAndRegCountMap.get(param) != null){
					eightPmTo11PmTodalRegCnt = eightPmTo11PmTodalRegCnt + hourAndRegCountMap.get(param);
					totalRegToday = totalRegToday + hourAndRegCountMap.get(param);
				}
					
			}
			
			Long totalCadreReg12AmTo8AmRegCnt = 0l;
			for(Long param : twelveAmTo8AmHursList){
				if( hourAndRegCountMap.get(param) != null){
					totalCadreReg12AmTo8AmRegCnt = totalCadreReg12AmTo8AmRegCnt + hourAndRegCountMap.get(param);
					totalRegToday = totalRegToday + hourAndRegCountMap.get(param);
				}
			}
			
			List<FieldReportVO> fieldReportVOs = new ArrayList<FieldReportVO>();
			
			FieldReportVO eightPMto12AmVO = new FieldReportVO();
			//special for 8pm -> 12am
			eightPMto12AmVO = new FieldReportVO();
			eightPMto12AmVO.setLabel("8pm-12am");
			eightPMto12AmVO.setOrder(20l);
			eightPMto12AmVO.setTodayTotalReg(eightPmTo11PmTodalRegCnt);
 			//add first
 			fieldReportVOs.add(eightPMto12AmVO);
			
			
			FieldReportVO twelveAmTo8AMVO = new FieldReportVO();
			//special for 12am -> 8am
			twelveAmTo8AMVO = new FieldReportVO();
			twelveAmTo8AMVO.setLabel("12am-8am");
			twelveAmTo8AMVO.setOrder(7l);
			twelveAmTo8AMVO.setTodayTotalReg(totalCadreReg12AmTo8AmRegCnt);
 			//add second
 			fieldReportVOs.add(twelveAmTo8AMVO);
 			//now add all
 			for(Entry<String,Long> entry : labelAndPositionMap.entrySet()){
 				FieldReportVO fieldReportVO = new FieldReportVO();
 				fieldReportVO.setLabel(entry.getKey());
 				fieldReportVO.setOrder(entry.getValue());
 				if(hourAndRegCountMap.get(entry.getValue()) != null){
 					fieldReportVO.setTodayTotalReg(hourAndRegCountMap.get(entry.getValue()));
 					totalRegToday = totalRegToday + hourAndRegCountMap.get(entry.getValue());
 				}
 				else{
 					fieldReportVO.setTodayTotalReg(0l);
 				}
 				fieldReportVOs.add(fieldReportVO);
 			}
 			if(fieldReportVOs != null && fieldReportVOs.size() > 0){
				Collections.sort(fieldReportVOs, shortTimeAsc);
			}
 			resultVO.setSubList3(fieldReportVOs);
 			//calculate total reg for cadreSurveyUser.
 			Long overAllRegCount = 0l;
 			List<Object[]> totalRegCount = tdpCadreUserHourRegInfoDAO.getTotalRegCount(cadreSurveyUserId);
 			if(totalRegCount != null && totalRegCount.size() > 0){
 				overAllRegCount = commonMethodsUtilService.getLongValueForObject(totalRegCount.get(0)[1]);
 			}
 			Calendar cal = Calendar.getInstance();
 			cal.setTime(today);
 			Long hr = (long) cal.get(Calendar.HOUR_OF_DAY); 
 			if(hr == 0l){  
 				hr = 23l;  
 			}else{
 				hr = hr-1l;
 			}
 			Long currentHourRegCnt = 0l;   
 			List<Object[]> totalRegForCurrentHour = tdpCadreUserHourRegInfoDAO.getTotalRegForAHour(cadreSurveyUserId,today,hr);
 			if(totalRegForCurrentHour != null && totalRegForCurrentHour.size() > 0){
 				for(Object[] param : totalRegForCurrentHour){
 					currentHourRegCnt = currentHourRegCnt + commonMethodsUtilService.getLongValueForObject(param[2]);
 				}
 			}
 			resultVO.setTotalRegCount(overAllRegCount);    
 			resultVO.setTodayRegCount(totalRegToday);
 			resultVO.setCurrentHourRegCount(currentHourRegCnt);
 		if(rtrnTrackingDtlsLst != null && rtrnTrackingDtlsLst.size() > 0){  
 			 Object[] cadreSurveyUserObj = rtrnTrackingDtlsLst.get(0);
			 resultVO.setSurveyUserId(cadreSurveyUserId);
			 resultVO.setUserName(commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[5]));
			 resultVO.setDeviceUserName(commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[8]));
			 resultVO.setUserMobileNo(commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[9]));
			 resultVO.setImagePathStr("http://www.mytdp.com/CR/tab_user_images/"+commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[10]));
		 }
 		
	 }catch(Exception e){
		 LOG.error("Exception raised in getUserTrackingDtslBySurveyUserId() in CoreDashboardCadreRegistrationService service", e);	 
	 }
	 return resultVO;
 }
  
 //srujana , srishailam 
 public CadreBasicVO getUserTrackingDtslBySurveyUserId(Long cadreSurveyUserId,String fromDateStr,String toDateStr,Long fieldUserId,Long constitunecyId,Long fromTime,Long toTime)
 {
	  CadreBasicVO resultVO = new CadreBasicVO();
	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	  Date toDate=null;
	  Date fromDate=null;
	  Date today = null;
	 try{
		 if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
			 toDate = sdf.parse(toDateStr);
			 fromDate = sdf.parse(fromDateStr);
		 }else{
			 toDate = new DateUtilService().getCurrentDateAndTime();
			 fromDate = new DateUtilService().getCurrentDateAndTime();
		 }
		 today = new DateUtilService().getCurrentDateAndTime();
		 List<CadreBasicVO> userTrackingDtlList = new ArrayList<CadreBasicVO>(0);
		 List<CadreBasicVO> eryFveMntsDtlsLstUsrTrckngLst = new ArrayList<CadreBasicVO>(0);
		 
		 Date updatedFromDate = null;
		 Date updatedToDate = null;
		 
		 if(fromTime != null && toTime != null && (fromTime > 0l || toTime > 0l)){
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(today);
			 cal.set(Calendar.HOUR_OF_DAY, fromTime.intValue());
			 updatedFromDate = cal.getTime();
			 
			 Calendar cal1 = Calendar.getInstance();
			 cal1.setTime(today);
			 cal1.set(Calendar.HOUR_OF_DAY, toTime.intValue());
			 updatedToDate = cal1.getTime();
		 }
		 
		 List<Object[]> rtrnTrackingDtlsLst = tdpCadreDAO.getUserTrackingDetails(cadreSurveyUserId, fromDate, toDate);
		 List<Object[]> rtrnEvry5MinutesUsrTrkngDtlList = tabUserLocationDetailsDAO.getSurveyUserTrackingDtlsByFieldUser(fieldUserId, cadreSurveyUserId, fromDate, toDate,updatedFromDate,updatedToDate);
		 
		 setDUserTrackingDtlsToList(rtrnTrackingDtlsLst,userTrackingDtlList,"samples");
		 setDUserTrackingDtlsToList(rtrnEvry5MinutesUsrTrkngDtlList,eryFveMntsDtlsLstUsrTrckngLst,null);
		 if(cadreSurveyUserId.longValue() == 0l){
		 List<Long> cadreSurveyUserIds = new ArrayList<Long>();
		 List<Object[]> reportDetails = cadreRegUserTabUserDAO.getFieldMonitoringMapReportDetails(constitunecyId,fieldUserId);
		 
		 if(reportDetails != null && reportDetails.size() > 0){
				
				for (Object[] param : reportDetails) {
					cadreSurveyUserIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
		 
		 List<Object[]> latestObjets = tabUserLocationDetailsDAO.getLatestLattitudeAndLongitude(cadreSurveyUserIds);
		 setDUserTrackingDtlsToList(latestObjets,userTrackingDtlList,"all");
		 }
 		 if(userTrackingDtlList != null && userTrackingDtlList.size() > 0){
 			 resultVO.setSubList1(userTrackingDtlList);
 		 }
 		 if(eryFveMntsDtlsLstUsrTrckngLst != null && eryFveMntsDtlsLstUsrTrckngLst.size() > 0){
 			 resultVO.setSubList2(eryFveMntsDtlsLstUsrTrckngLst);  
 		 }
 		//between 8pm -> 8am
 		List<Long> hourList = new ArrayList<Long>(){{add(20l);add(21l);add(22l);add(23l);add(0l);add(1l);add(2l);add(3l);add(4l);add(5l);add(6l);add(7l);}};
		//between 8am -> 8pm  
 		
		Map<String,Long> labelAndPositionMap = new HashMap<String,Long>(){{  
			put("8am-9am",8l);put("9am-10am",9l);put("10am-11am",10l);put("11am-12pm",11l);
			put("12pm-1pm",12l);put("1pm-2pm",13l);put("2pm-3pm",14l);put("3pm-4pm",15l);
			put("4pm-5pm",16l);put("5pm-6pm",17l);put("6pm-7pm",18l);put("7pm-8pm",19l);
		}}; 
 		 
 		 
 		 	List<Object[]> regDtlsHourWiseList = tdpCadreUserHourRegInfoDAO.getRegDtlsHourWiseList(cadreSurveyUserId,today);
 		 	Map<Long,Long> hourAndRegCountMap = new HashMap<Long,Long>();
			
			if(regDtlsHourWiseList != null && regDtlsHourWiseList.size() > 0){
				for(Object[] param : regDtlsHourWiseList){
					hourAndRegCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			Long totalCadreUpto8amToday = 0l;
			Long totalRegToday = 0l;
			
			for(Long param : hourList){
				if( hourAndRegCountMap.get(param) != null){
					totalCadreUpto8amToday = totalCadreUpto8amToday + hourAndRegCountMap.get(param);
					totalRegToday = totalRegToday + hourAndRegCountMap.get(param);
				}
					
			}
			FieldReportVO fieldReportVO = new FieldReportVO();
			List<FieldReportVO> fieldReportVOs = new ArrayList<FieldReportVO>();
			//special for 8pm -> 8am
 			fieldReportVO = new FieldReportVO();
 			fieldReportVO.setLabel("8pm-8am");
 			fieldReportVO.setOrder(13l);
 			fieldReportVO.setTodayTotalReg(totalCadreUpto8amToday);
 			//add first
 			fieldReportVOs.add(fieldReportVO);
 			//now add all
 			for(Entry<String,Long> entry : labelAndPositionMap.entrySet()){
 				fieldReportVO = new FieldReportVO();
 				fieldReportVO.setLabel(entry.getKey());
 				fieldReportVO.setOrder(entry.getValue());
 				if(hourAndRegCountMap.get(entry.getValue()) != null){
 					fieldReportVO.setTodayTotalReg(hourAndRegCountMap.get(entry.getValue()));
 					totalRegToday = totalRegToday + hourAndRegCountMap.get(entry.getValue());
 				}
 				else{
 					fieldReportVO.setTodayTotalReg(0l);
 				}
 				fieldReportVOs.add(fieldReportVO);
 			}
 			if(fieldReportVOs != null && fieldReportVOs.size() > 0){
				Collections.sort(fieldReportVOs, shortTimeAsc);
			}
 			resultVO.setSubList3(fieldReportVOs);
 			//calculate total reg for cadreSurveyUser.
 			Long overAllRegCount = 0l;
 			List<Object[]> totalRegCount = tdpCadreUserHourRegInfoDAO.getTotalRegCount(cadreSurveyUserId);
 			if(totalRegCount != null && totalRegCount.size() > 0){
 				overAllRegCount = commonMethodsUtilService.getLongValueForObject(totalRegCount.get(0)[1]);
 			}
 			Calendar cal = Calendar.getInstance();
 			cal.setTime(today);
 			Long hr = (long) cal.get(Calendar.HOUR_OF_DAY); 
 			if(hr == 0l){  
 				hr = 23l;  
 			}else{
 				hr = hr-1l;
 			}
 			Long currentHourRegCnt = 0l;   
 			List<Object[]> totalRegForCurrentHour = tdpCadreUserHourRegInfoDAO.getTotalRegForAHour(cadreSurveyUserId,today,hr);
 			if(totalRegForCurrentHour != null && totalRegForCurrentHour.size() > 0){
 				for(Object[] param : totalRegForCurrentHour){
 					currentHourRegCnt = currentHourRegCnt + commonMethodsUtilService.getLongValueForObject(param[2]);
 				}
 			}
 			resultVO.setTotalRegCount(overAllRegCount);    
 			resultVO.setTodayRegCount(totalRegToday);
 			resultVO.setCurrentHourRegCount(currentHourRegCnt);
 		if(rtrnTrackingDtlsLst != null && rtrnTrackingDtlsLst.size() > 0){  
 			 Object[] cadreSurveyUserObj = rtrnTrackingDtlsLst.get(0);
			 resultVO.setSurveyUserId(cadreSurveyUserId);
			 resultVO.setUserName(commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[5]));
			 resultVO.setDeviceUserName(commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[8]));
			 resultVO.setUserMobileNo(commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[9]));
			 resultVO.setImagePathStr("http://www.mytdp.com/CR/tab_user_images/"+commonMethodsUtilService.getStringValueForObject(cadreSurveyUserObj[10]));
		 }
 		
	 }catch(Exception e){
		 LOG.error("Exception raised in getUserTrackingDtslBySurveyUserId() in CoreDashboardCadreRegistrationService service", e);	 
	 }
	 return resultVO;
 }
 
 
 public void setDUserTrackingDtlsToList(List<Object[]> objList,List<CadreBasicVO> resultList,String type){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 CadreBasicVO regDtlsVO = new CadreBasicVO();
				 regDtlsVO.setSurveyTime(commonMethodsUtilService.getStringValueForObject(param[0]));
				 regDtlsVO.setLongititude(commonMethodsUtilService.getStringValueForObject(param[1]));
				 regDtlsVO.setLatitude(commonMethodsUtilService.getStringValueForObject(param[2]));
				 if(type != null && type.trim().equalsIgnoreCase("all")){
				 regDtlsVO.setTdpCadreName(commonMethodsUtilService.getStringValueForObject(param[3]));
				 regDtlsVO.setTdpCadreMbl(commonMethodsUtilService.getStringValueForObject(param[4]));
				 }
				 
				 if(type != null && type.trim().equalsIgnoreCase("samples")){
					 regDtlsVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
					 regDtlsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[4]));
					 regDtlsVO.setUserName(commonMethodsUtilService.getStringValueForObject(param[5]));
					 regDtlsVO.setTdpCadreName(commonMethodsUtilService.getStringValueForObject(param[6]));
					 regDtlsVO.setTdpCadreMbl(commonMethodsUtilService.getStringValueForObject(param[7]));
				 }
				 
				 resultList.add(regDtlsVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception raised in setDUserTrackingDtlsToList() in CoreDashboardCadreRegistrationService service", e); 
	 }
 }
  
  public CadreRegistratedCountVO getStateWiseMandalMuncipalityNotStartedCount(Long stateId){
	  try{
		  CadreRegistratedCountVO cadreRegistratedCountVO = new CadreRegistratedCountVO(); 
		  
		  List<Long> mandalIdsList = new ArrayList<Long>(0);
		   List<Long> muncipalityIdsList = new ArrayList<Long>(0);
		   List<Long> mandalTodayStartedIdsList = new ArrayList<Long>(0);
		   List<Long> muncipalityTodayStartedIdsList = new ArrayList<Long>(0);
		   
		   
		   List<Long> rtrnTodayStatedMandalIdsLst = tdpCadreLocationInfoDAO.getTodayMandalStartedStateWise(stateId);
		   setRequiredDIdsToList(rtrnTodayStatedMandalIdsLst,mandalTodayStartedIdsList,"Mandal");
		   
		   List<Long> rtrnTodayStatedMuncipalityIdsLst = tdpCadreLocationInfoDAO.getTodayLocalElectionBodyStartedStateWise(stateId);
		   setRequiredDIdsToList(rtrnTodayStatedMuncipalityIdsLst,muncipalityTodayStartedIdsList,"Muncipality");
		   
		   cadreRegistratedCountVO.setTodayStartedMandalMuncipalityCnt(Long.valueOf(rtrnTodayStatedMandalIdsLst.size()+rtrnTodayStatedMuncipalityIdsLst.size()));
		   
		   List<Long> rtrnAllMandalIds = constituencyTehsilDAO.getAllStateWiseTehsilIds(stateId);
		   setRequiredDIdsToList(rtrnAllMandalIds,mandalIdsList,"Mandal");
		   mandalIdsList.removeAll(mandalTodayStartedIdsList);
		   
		   List<Long> rtrnAllMuncipalityIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdsStateWise(stateId);
		   setRequiredDIdsToList(rtrnAllMuncipalityIdsList,muncipalityIdsList,"Muncipality");
		   muncipalityIdsList.removeAll(muncipalityTodayStartedIdsList);
		   
		   cadreRegistratedCountVO.setTodayNotStartedMandalMuncipalityCnt(Long.valueOf(mandalIdsList.size()+muncipalityIdsList.size()));
		   cadreRegistratedCountVO.getLocationIdsList1().addAll(mandalIdsList);
		   cadreRegistratedCountVO.getLocationIdsList1().addAll(muncipalityIdsList);
		   
		return cadreRegistratedCountVO;  
	  }catch(Exception e){
		  LOG.error("Exception raised in getStateWiseMandalMuncipalityCount() in CoreDashboardCadreRegistrationService service", e);	
	  }
	  return null;
  }
  
}
