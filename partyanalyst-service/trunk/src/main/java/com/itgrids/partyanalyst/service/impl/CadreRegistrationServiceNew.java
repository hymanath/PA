package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoTempDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreHourRegInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreHourRegInfoTemp1DAO;
import com.itgrids.partyanalyst.dao.ITdpCadreHourRegInfoTempDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoTemp1DAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoTempDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfoTemp1DAO;
import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfoTempDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadreDateVO;
import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadreLocationInfoVO;
import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfoTemp;
import com.itgrids.partyanalyst.model.TdpCadreHourRegInfoTemp;
import com.itgrids.partyanalyst.model.TdpCadreHourRegInfoTemp1;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfoTemp;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfoTemp1;
import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfoTemp;
import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfoTemp1;
import com.itgrids.partyanalyst.service.ICadreRegistrationServiceNew;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

public class CadreRegistrationServiceNew implements ICadreRegistrationServiceNew{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationServiceNew.class);
	
	//Attributes
	private ITdpCadreDAO tdpCadreDAO;
	private ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO;
	private ITdpCadreTargetCountDAO tdpCadreTargetCountDAO;
	private ITdpCadreLocationInfoCountDAO tdpCadreLocationInfoCountDAO;
	private TransactionTemplate transactionTemplate;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDistrictDAO districtDAO;
	private ITdpCadreDateWiseInfoDAO tdpCadreDateWiseInfoDAO;
	private ITdpCadreLocationInfoTempDAO tdpCadreLocationInfoTempDAO;
	private ITdpCadreLocationInfoTemp1DAO tdpCadreLocationInfoTemp1DAO ;
	private ITdpCadreDateWiseInfoTempDAO tdpCadreDateWiseInfoTempDAO;
	private ImageAndStringConverter imageAndStringConverter;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IVoterDAO voterDAO;
	private ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO;
	private ITdpCadreHourRegInfoDAO tdpCadreHourRegInfoDAO;
	private ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO;
	private ITdpCadreUserHourRegInfoTempDAO tdpCadreUserHourRegInfoTempDAO;
	private ITdpCadreUserHourRegInfoTemp1DAO tdpCadreUserHourRegInfoTemp1DAO;
	private ITdpCadreHourRegInfoTemp1DAO tdpCadreHourRegInfoTemp1DAO;
	private ITdpCadreHourRegInfoTempDAO tdpCadreHourRegInfoTempDAO;
	//setters
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public void setTdpCadreLocationInfoDAO(
			ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO) {
		this.tdpCadreLocationInfoDAO = tdpCadreLocationInfoDAO;
	}
	public void setTdpCadreTargetCountDAO(
			ITdpCadreTargetCountDAO tdpCadreTargetCountDAO) {
		this.tdpCadreTargetCountDAO = tdpCadreTargetCountDAO;
	}
	public void setTdpCadreLocationInfoCountDAO(
			ITdpCadreLocationInfoCountDAO tdpCadreLocationInfoCountDAO) {
		this.tdpCadreLocationInfoCountDAO = tdpCadreLocationInfoCountDAO;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public void setTdpCadreDateWiseInfoDAO(
			ITdpCadreDateWiseInfoDAO tdpCadreDateWiseInfoDAO) {
		this.tdpCadreDateWiseInfoDAO = tdpCadreDateWiseInfoDAO;
	}
	public void setTdpCadreLocationInfoTempDAO(
			ITdpCadreLocationInfoTempDAO tdpCadreLocationInfoTempDAO) {
		this.tdpCadreLocationInfoTempDAO = tdpCadreLocationInfoTempDAO;
	}
	public void setTdpCadreLocationInfoTemp1DAO(
			ITdpCadreLocationInfoTemp1DAO tdpCadreLocationInfoTemp1DAO) {
		this.tdpCadreLocationInfoTemp1DAO = tdpCadreLocationInfoTemp1DAO;
	}
	
	public void setTdpCadreDateWiseInfoTempDAO(
			ITdpCadreDateWiseInfoTempDAO tdpCadreDateWiseInfoTempDAO) {
		this.tdpCadreDateWiseInfoTempDAO = tdpCadreDateWiseInfoTempDAO;
	}
	
	public void setImageAndStringConverter(
			ImageAndStringConverter imageAndStringConverter) {
		this.imageAndStringConverter = imageAndStringConverter;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public void setTabUserEnrollmentInfoDAO(
			ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO) {
		this.tabUserEnrollmentInfoDAO = tabUserEnrollmentInfoDAO;
	}
	
	public void setTdpCadreHourRegInfoDAO(
			ITdpCadreHourRegInfoDAO tdpCadreHourRegInfoDAO) {
		this.tdpCadreHourRegInfoDAO = tdpCadreHourRegInfoDAO;
	}
	
	public void setTdpCadreUserHourRegInfoDAO(
			ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO) {
		this.tdpCadreUserHourRegInfoDAO = tdpCadreUserHourRegInfoDAO;
	}
	
	public void setTdpCadreUserHourRegInfoTempDAO(
			ITdpCadreUserHourRegInfoTempDAO tdpCadreUserHourRegInfoTempDAO) {
		this.tdpCadreUserHourRegInfoTempDAO = tdpCadreUserHourRegInfoTempDAO;
	}
	
	public void setTdpCadreUserHourRegInfoTemp1DAO(
			ITdpCadreUserHourRegInfoTemp1DAO tdpCadreUserHourRegInfoTemp1DAO) {
		this.tdpCadreUserHourRegInfoTemp1DAO = tdpCadreUserHourRegInfoTemp1DAO;
	}
	
	public void setTdpCadreHourRegInfoTemp1DAO(
			ITdpCadreHourRegInfoTemp1DAO tdpCadreHourRegInfoTemp1DAO) {
		this.tdpCadreHourRegInfoTemp1DAO = tdpCadreHourRegInfoTemp1DAO;
	}
	
	public void setTdpCadreHourRegInfoTempDAO(
			ITdpCadreHourRegInfoTempDAO tdpCadreHourRegInfoTempDAO) {
		this.tdpCadreHourRegInfoTempDAO = tdpCadreHourRegInfoTempDAO;
	}
	//Business methods
	/**
	  *  Service To get All Assembly Constituencies for AP and Ts and the constituency corresponding parliament Constituency.
	  */
	public Map<Long,Long> getAllAssemblyConstsAndItsParliamentConsts(){
		   Map<Long,Long> acpcMap = new HashMap<Long, Long>(0);
		   try{
			    List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.getAllAssemblyConstsAndItsParliamentConsts();
			    if( list != null && list.size() > 0){
			    	for(Object[] obj : list){
			    		acpcMap.put(obj[0]!=null?(Long)obj[0]:0l,obj[2]!=null?(Long)obj[2]:0l);
			    	}
			    }
			    
		  }catch(Exception e){
			  LOG.error("Exception raised in getAllAssemblyConstsAndItsParliamentConsts() in CadreRegistrationServiceNew class", e);
		  }
		  return acpcMap;
	   }
	   
	   
	 /**
	  *  Service To get All Assembly Constituencies for AP and Ts and the constituency corresponding District.
	  */
	   public Map<Long,Long> getAllASsemblyContsAndItsDistricts(){
		   Map<Long,Long> constDistrictMap = new HashMap<Long, Long>(0);
		   try{
			    List<Object[]> list = constituencyDAO.getAllASsemblyContsAndItsDistricts();
			    if( list != null && list.size() > 0){
			    	for(Object[] obj : list){
			    		constDistrictMap.put(obj[0]!=null?(Long)obj[0]:0l,obj[2]!=null?(Long)obj[2]:0l);
			    	}
			    }
			    
		  }catch(Exception e){
			  LOG.error("Exception raised in getAllASsemblyContsAndItsDistricts() in CadreRegistrationServiceNew class", e);
		  }
		  return constDistrictMap;
	   }
	   
	   /**
		*  Service To get All Districts for AP and Ts and the district corresponding State.
		*/
	   public Map<Long,Long> getAllDistrictsAndItsStates(){
		   Map<Long,Long> districtStateMap = new HashMap<Long, Long>(0);
		   try{
			    List<Object[]> list = districtDAO.getStateWiseDistrict(1L);
			    if( list != null && list.size() > 0){
			    	for(Object[] obj : list){
			    		Long districtId = obj[0] != null ? (Long)obj[0]:0l;
			    		if( districtId >= 1l && districtId <= 10l){
			    			districtStateMap.put(districtId, 36l);
			    		}else if(districtId >= 11l && districtId <= 23l){
			    			districtStateMap.put(districtId, 1l);
			    		}
			    	}
			    }
			    
		  }catch(Exception e){
			  LOG.error("Exception raised in getAllDistrictsAndItsStates() in CadreRegistrationServiceNew class", e);
		  }
		  return districtStateMap;
	   }
	   
	   /**
		*  Service To get all locations wise TdpCadre target counts.
		*/
	   public Map<String,Long> getTdpCadreTargetCountLocationWise(){
		   
		   Map<String,Long> locationTargetMap = new HashMap<String, Long>(0);
		   try{
			    List<Object[]> list = tdpCadreTargetCountDAO.getTdpCadreTargetCountLocationWise(4l);
			    if( list != null && list.size() > 0){
			    	for(Object[] obj : list){
			    		Long locationScopeId = obj[0] != null ? (Long)obj[0] : 0l;
			    		Long locationValue = obj[1] != null ? (Long)obj[1] : 0l;
			    		String key = locationScopeId + "_" + locationValue;
			    		locationTargetMap.put(key,obj[2]!=null?(Long)obj[2]:0l);
			    	}
			    }
		  }catch(Exception e){
			  LOG.error("Exception raised in getTdpCadreTargetCountLocationWise() in CadreRegistrationServiceNew class", e);
		  }
		  return locationTargetMap;
	   }
	   
	   /**
		*  Service To get all locations wise Previous Cadre Count.
		*/
	   public Map<String,TdpCadreLocationInfoVO> locationWisePreviousCadreCount(){
	       
		   Map<String,TdpCadreLocationInfoVO> finalMap = null;
		   try{
			   
			   List<Object[]> previousCadreList = tdpCadreLocationInfoCountDAO.getAllLocationsTdpCadreCount(3L);
			   
			   if( previousCadreList != null && previousCadreList.size() > 0){
				   
				   finalMap = new HashMap<String, TdpCadreLocationInfoVO>(0);
				   
				   for(Object[] obj : previousCadreList){
					   
					   Long locationScopeId = obj[0]!=null?(Long)obj[0]:0l;
					   Long locationValue = obj[1]!=null?(Long)obj[1]:0l;
					   String key = locationScopeId + "_" + locationValue;
					   
					   TdpCadreLocationInfoVO locationVO = new TdpCadreLocationInfoVO();
					   locationVO.setCadreCount(obj[2] != null ? (Long)obj[2] : 0l);
					   locationVO.setCadrePercent(obj[3]!= null ? obj[3].toString() : null);
					   
					   finalMap.put(key, locationVO);
				   }
			   }
			   
		   }catch(Exception e) {
			   LOG.error("Exception raised in locationWisePreviousCadreCount() in CadreRegistrationServiceNew class", e);
		   }
		   return finalMap;
	   }
	   
	   
	/** 1)
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  Pushing tdpcadre data to intermediate tables
	  *  @since 18-OCTOBER-2016
	  */
	public ResultStatus pushTotalTodayTdpCadreDataToIntermediate(){
		
		   ResultStatus rs = null;
		   try{
			   
			   Map<Long,Long> acpcMap = getAllAssemblyConstsAndItsParliamentConsts();
			   Map<Long,Long> constDistrictMap = getAllASsemblyContsAndItsDistricts();
			   Map<Long,Long> distStateMap = getAllDistrictsAndItsStates();
			   Map<String,Long> locationTargetMap = getTdpCadreTargetCountLocationWise();
			   Map<String,TdpCadreLocationInfoVO> previousCadreMap = locationWisePreviousCadreCount();
			   
			   List<TdpCadreLocationInfoVO> finalList = new ArrayList<TdpCadreLocationInfoVO>(0);
			   
			   //Total
			   TdpCadreLocationInfoVO totalDataVO = new TdpCadreLocationInfoVO();
			   getLocationWiseData("Total",totalDataVO,acpcMap,constDistrictMap,distStateMap,locationTargetMap,previousCadreMap);
			   finalList.add(totalDataVO);
			   
			   //Today
			   TdpCadreLocationInfoVO todayDataVO = new TdpCadreLocationInfoVO();
			   getLocationWiseData("Today",todayDataVO,acpcMap,constDistrictMap,distStateMap,locationTargetMap,previousCadreMap);
			   finalList.add(todayDataVO);
			   
			   
			   long tempStartTime = System.currentTimeMillis();
			   rs =  saveTotalTodayTdpCadreDataToIntermediateTemp(finalList);
			   Double tempSeconds = (System.currentTimeMillis() - tempStartTime)/1000.0;
			   
			   List<Long> locationScopeIds = new ArrayList<Long>(0);
			   locationScopeIds.add(4L);//assembly
			   locationScopeIds.add(10L);//parliament
			    locationScopeIds.add(3L);//district
			   locationScopeIds.add(2L);//state
			   
			   long intermediateStartTime = System.currentTimeMillis();
			    int deletedRecords =  tdpCadreLocationInfoDAO.deleteAllRecords(locationScopeIds);
			    int insertedRecordsCount = tdpCadreLocationInfoDAO.insertTdpCadreLocationInfoUpToConstituencyLevel();
			    Double interSeconds = (System.currentTimeMillis() - intermediateStartTime)/1000.0;
			   LOG.error(" totaltoday const level temp insert time is : " + tempSeconds  + " seconds &&  totaltoday const level intermediate insert time is :" +interSeconds +" seconds ");
			   
		  }catch(Exception e){
			  LOG.error("Exception raised in pushTotalTodayTdpCadreDataToIntermediate() in CadreRegistrationServiceNew class", e);
		  }
		   return rs;
	   }
	   
	   
	   public void getLocationWiseData(String dateType,TdpCadreLocationInfoVO finalVO,Map<Long,Long> acpcMap,Map<Long,Long> constDistrictMap,Map<Long,Long> distStateMap,Map<String,Long> locationTargetMap,Map<String,TdpCadreLocationInfoVO> previousCadreMap){
		  
		   try{ 
			     List<TdpCadreLocationInfoVO> constituencyList =  getTotalTodayTdpCadreDataConstituencyWise(dateType,locationTargetMap,previousCadreMap);
			     finalVO.setAssemblyList(constituencyList);
			     
			     setCorrespondingLevelData("parliament" , finalVO , acpcMap , dateType,locationTargetMap,previousCadreMap);
			     setCorrespondingLevelData("district"   , finalVO , constDistrictMap , dateType,locationTargetMap,previousCadreMap);
			     setCorrespondingLevelData("state"      , finalVO , distStateMap , dateType,locationTargetMap,previousCadreMap);
			   
		   }catch(Exception e){
			   LOG.error("Exception raised in pushTotalTodayTdpCadreDataToIntermediate() in CadreRegistrationServiceNew class", e);
		   }
	   }
	   
	   public List<TdpCadreLocationInfoVO> getTotalTodayTdpCadreDataConstituencyWise(String dateType,Map<String,Long> locationTargetMap,Map<String,TdpCadreLocationInfoVO> previousCadreMap){
		   
		   List<TdpCadreLocationInfoVO> finalList = null;
		   
		   try{
			   
			   //INSTANTIATE
			   Map<Long,TdpCadreLocationInfoVO> constMap = null;
			   List<Long> constituenciesList = constituencyDAO.getStateConstituencyIds(1L);
			   if(constituenciesList != null && constituenciesList.size() > 0)
			   {
				   constMap= new LinkedHashMap<Long, TdpCadreLocationInfoVO>(0);
				   for(Long constituencyId : constituenciesList)
				   {
					   TdpCadreLocationInfoVO constVO = new TdpCadreLocationInfoVO();
					   constVO.setId(constituencyId!=null?(Long)constituencyId:0l);
					   constVO.setLocationScopeId(4L);
					   constVO.setDateType(dateType);
					   
					   //2014 counts
					   String key = constVO.getLocationScopeId() + "_" + constVO.getId();
					   TdpCadreLocationInfoVO previousCadreData = previousCadreMap.get(key);
					   if(previousCadreData != null){
						   constVO.setCadreCount(previousCadreData.getCadreCount());
						   constVO.setCadrePercent(previousCadreData.getCadrePercent());
					   }
					   
					   constMap.put(constVO.getId(), constVO);
				   }
			   }
			   
			     Date currentDate = null;
			     if(dateType != null && dateType.equalsIgnoreCase("Today")){
			    	 currentDate = new DateUtilService().getCurrentDateAndTime();
			     }
			     
			     //all Records
			      List<Object[]> constTotalList = tdpCadreDAO.getTdpCadreRecordsCountLocWise(currentDate);
			      if(constTotalList != null && constTotalList.size() > 0)
			      {  
			    	  for(Object[] obj : constTotalList )
			    	  {  
			    		  TdpCadreLocationInfoVO constVO = null;
			    		  if(obj[0]!=null)////constituencyId
			    		  {
			    			  constVO = constMap.get((Long)obj[0]);
			    			  if(constVO != null){
			    				  constVO.setCadre2016Records(obj[1]!=null ? (Long)obj[1]:0l);
					    		  constVO.setCadre2016NewRecords(constVO.getCadre2016Records());
					    		  constMap.put(constVO.getId(), constVO); 
			    			  }
			    		  }
			    	  }
			      }
			      
			      //Renewal Records
			      List<Object[]> constRenewalList = tdpCadreDAO.getRenewalTdpCadreRecordsCountLocWise(currentDate);
			      if(constRenewalList != null && constRenewalList.size() > 0)
			      {
			    	  for( Object[] obj : constRenewalList)
			    	  {
			    		  if(obj[0] != null)//constituencyId
			    		  {
			    			  TdpCadreLocationInfoVO constVO = constMap.get((Long)obj[0]);
			    			  if(constVO != null){
			    				  Long renewalRecords =  obj[1]!= null ? (Long)obj[1] : 0l;
			    				  constVO.setCadre2016RenewalRecords(renewalRecords );
			    				  constVO.setCadre2016NewRecords( constVO.getCadre2016Records() - constVO.getCadre2016RenewalRecords() );
			    			  }
			    		  }
			    	  }
			      }
			      
			      if(constMap != null && constMap.size() > 0)
			      {
			    	  finalList = new ArrayList<TdpCadreLocationInfoVO>(constMap.values());
			    	  if(finalList != null && finalList.size() > 0)
			    	  {
			    		  for(TdpCadreLocationInfoVO VO : finalList )
			    		  {  
			    			  String key = VO.getLocationScopeId() + "_" + VO.getId();
			    			  Long targetCount = locationTargetMap.get(key); 
			    			  if(targetCount != null && targetCount > 0l){
			    				  VO.setCadre2016RecordsPerc( calcPercantage( VO.getCadre2016Records() , targetCount ) );  
			    			  }
			    			  
			    			  if(VO.getCadre2016Records() != null && VO.getCadre2016Records() > 0l){
			    				  VO.setCadre2016RenewalRecordsPerc( calcPercantage( VO.getCadre2016RenewalRecords() , VO.getCadre2016Records()) );
			    				  VO.setCadre2016NewRecordsPerc( calcPercantage( VO.getCadre2016NewRecords() , VO.getCadre2016Records()) );
			    			  }
			    		  }
			    	  }
			      }
			      
		   }catch(Exception e){
			   //e.printStackTrace();
			   LOG.error("Exception raised in getTdpCadredetailsConstituencyWise() in CadreRegistrationServiceNew class", e);
		   }
		   return finalList;
	   }
	   public void setCorrespondingLevelData(String scope,TdpCadreLocationInfoVO finalVO ,Map<Long,Long> constHighLevelMap,String dateType, Map<String,Long> locationTargetMap,Map<String,TdpCadreLocationInfoVO> previousCadreMap){
		   
		     Map<Long,TdpCadreLocationInfoVO> locationsMap = new LinkedHashMap<Long,TdpCadreLocationInfoVO>(0);
		     List<TdpCadreLocationInfoVO> locationsList = null;
		   try{
			  
			    List<TdpCadreLocationInfoVO> dataList = null;
			    if(scope.equalsIgnoreCase("parliament") || scope.equalsIgnoreCase("district")){
			    	dataList = finalVO.getAssemblyList();
				 }else if(scope.equalsIgnoreCase("state")){
					 dataList = finalVO.getDistrictList();
				 }
			   
			   if(dataList != null && dataList.size() > 0)
			    { 
			    	 for(TdpCadreLocationInfoVO dataVO : dataList)
			    	 {	 
			    		 Long dataId = dataVO.getId();
			    		 if(dataId != null && dataId > 0l)
			    		 {	 
			    			 Long locationId = constHighLevelMap.get(dataId);
			    			 
			    			 if(locationId != null && locationId > 0l)
			    			 { 
			    				 TdpCadreLocationInfoVO locationVO = null;
			    				 locationVO = locationsMap.get(locationId);
			    				 if(locationVO == null)
			    				 {	 
			    					 locationVO = new  TdpCadreLocationInfoVO();
			    					 
			    					 locationVO.setId(locationId);
			    					 if(scope.equalsIgnoreCase("parliament")){
			    						 locationVO.setLocationScopeId(10L);
			    					 }else if(scope.equalsIgnoreCase("district")){
			    						 locationVO.setLocationScopeId(3L);
			    					 }else if(scope.equalsIgnoreCase("state")){
			    						 locationVO.setLocationScopeId(2L);
			    					 }
			    					 locationVO.setDateType(dateType);
			    					 
			    					//2014 counts
			    					 String key = locationVO.getLocationScopeId() + "_" + locationVO.getId();
			    					 TdpCadreLocationInfoVO previousCadreData = previousCadreMap.get(key);
			    					 if(previousCadreData != null){
			    						 locationVO.setCadreCount(previousCadreData.getCadreCount());
			    						 locationVO.setCadrePercent(previousCadreData.getCadrePercent());
			    					 }
			    					 
			    					 locationsMap.put(locationId, locationVO);
			    				 }
			    				 locationVO = locationsMap.get(locationId);
			    				 
			    				 locationVO.setCadre2016Records(locationVO.getCadre2016Records() + dataVO.getCadre2016Records() );
			    				 locationVO.setCadre2016RenewalRecords( locationVO.getCadre2016RenewalRecords() + dataVO.getCadre2016RenewalRecords());
			    				 locationVO.setCadre2016NewRecords(locationVO.getCadre2016NewRecords() + dataVO.getCadre2016NewRecords() );
			    				 
			    			 }
			    			 
			    		 }
			    	 }
			    	 
			    	 if(locationsMap != null && locationsMap.size() > 0)
			    	 {
			    		 locationsList = new ArrayList<TdpCadreLocationInfoVO>(locationsMap.values());
			    		 if(locationsList != null && locationsList.size() > 0){
				    		  for(TdpCadreLocationInfoVO VO : locationsList ){
				    			  
				    			  String key = VO.getLocationScopeId() + "_" + VO.getId();
				    			  Long targetCount = locationTargetMap.get(key); 
				    			  if(targetCount != null && targetCount > 0l){
				    				  VO.setCadre2016RecordsPerc( calcPercantage( VO.getCadre2016Records() , targetCount ) );  
				    			  }
				    			  
				    			  if(VO.getCadre2016Records() != null && VO.getCadre2016Records() > 0l){
				    				  VO.setCadre2016RenewalRecordsPerc( calcPercantage( VO.getCadre2016RenewalRecords() , VO.getCadre2016Records()) );
				    				  VO.setCadre2016NewRecordsPerc( calcPercantage( VO.getCadre2016NewRecords() , VO.getCadre2016Records()) );
				    			  }
				    		  }
				    	  }
			    		 
			    		 if(scope.equalsIgnoreCase("parliament")){
			    			 finalVO.setParliamentList(locationsList);
						 }else if(scope.equalsIgnoreCase("district")){
							 finalVO.setDistrictList(locationsList);
						 }else if(scope.equalsIgnoreCase("state")){
							 finalVO.setStateList(locationsList);
						 }
			    	 }
			     }
			   
		   }catch(Exception e){
			   LOG.error("Exception raised in setCorrespondingLevelData() in CadreRegistrationServiceNew class", e);
		   }
	   }
	   
	   public ResultStatus saveTotalTodayTdpCadreDataToIntermediateTemp(final List<TdpCadreLocationInfoVO> finalList){
		   
			final ResultStatus rs = new ResultStatus();
			final DateUtilService dateUtilService = new DateUtilService();
			
			try {
				
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			        	
			        	if(finalList != null && finalList.size() > 0){
			        		
			        		int deletedRecords = tdpCadreLocationInfoTempDAO.deleteAllRecords();
						    //int count = tdpCadreLocationInfoTempDAO.setPrimaryKeyAutoIncrementToOne();
						    
						    Date currentTime = dateUtilService.getCurrentDateAndTime();
						    
						    for(TdpCadreLocationInfoVO dateTypeVO : finalList){
			        			
						    	if(dateTypeVO.getAssemblyList() != null && dateTypeVO.getAssemblyList().size() > 0){
						    		savingService(dateTypeVO.getAssemblyList(),currentTime);
						    	}
						    	
				        		if(dateTypeVO.getParliamentList() != null && dateTypeVO.getParliamentList().size() > 0){
				        			savingService(dateTypeVO.getParliamentList(),currentTime);
				        		}
				        		
				        		
				        		if(dateTypeVO.getDistrictList() != null && dateTypeVO.getDistrictList().size() > 0){
				        			savingService(dateTypeVO.getDistrictList(),currentTime);
				        		}
				        		
				        		if(dateTypeVO.getStateList() != null && dateTypeVO.getStateList().size() > 0){
				        			savingService(dateTypeVO.getStateList(),currentTime);
				        		}
				        		
				        	}
			        	}
			        	   
				          rs.setResultCode(1);
				          rs.setMessage("success");
			         }
			    });
				
			} catch (Exception e) {
				LOG.error("Exception raised at CadreRegistrationServiceNew", e);
				rs.setResultCode(0);
				rs.setMessage("failure");
			}
			return rs;
		}
	   public void savingService(List<TdpCadreLocationInfoVO> list , Date currentTime){
	   	
	   	try{
				if( list != null && list.size() > 0)
				{	
					int i= 0;
					for(TdpCadreLocationInfoVO locationVO : list  )
					{
			    		  i = i +1;
			    		  
						  TdpCadreLocationInfoTemp info = new TdpCadreLocationInfoTemp();
		    			  
		    			  info.setLocationScopeId(locationVO.getLocationScopeId());
		    			  info.setLocationValue(locationVO.getId());
		    			  
		    			  if(locationVO.getCadreCount() != null && locationVO.getCadreCount() > 0l){
		    				  info.setCadre2014(locationVO.getCadreCount());  
		    			  }
		    			  if(locationVO.getCadrePercent() != null){
		    				  info.setCadre2014Percent(locationVO.getCadrePercent());
		    			  }
		    			  
		    			  if( locationVO.getCadre2016Records() != null && locationVO.getCadre2016Records() > 0l){
		    				  info.setCadre2016(locationVO.getCadre2016Records()); 
		    			  }
		    			  if(locationVO.getCadre2016RecordsPerc() != null && locationVO.getCadre2016RecordsPerc() > 0l){
		    				  info.setCadre2016Percent(locationVO.getCadre2016RecordsPerc().toString());
		    			  }
		    			  
		    			  if( locationVO.getCadre2016RenewalRecords() != null && locationVO.getCadre2016RenewalRecords() > 0l){
		    				  info.setRenewalCadre(locationVO.getCadre2016RenewalRecords()); 
		    			  }
		    			  if(locationVO.getCadre2016RenewalRecordsPerc() != null && locationVO.getCadre2016RenewalRecordsPerc() > 0l){
		    				  info.setRenewalCadrePercent(locationVO.getCadre2016RenewalRecordsPerc().toString());
		    			  }
		    			  
		    			  if( locationVO.getCadre2016NewRecords() != null && locationVO.getCadre2016NewRecords() > 0l){
		    				  info.setNewCadre(locationVO.getCadre2016NewRecords()); 
		    			  }
		    			  if(locationVO.getCadre2016NewRecordsPerc() != null && locationVO.getCadre2016NewRecordsPerc() > 0l){
		    				  info.setNewCadrePercent(locationVO.getCadre2016NewRecordsPerc().toString());
		    			  }
		    			  
		    			  info.setType(locationVO.getDateType());
		    			  info.setInsertedTime(currentTime);
		    			  
		    			  tdpCadreLocationInfoTempDAO.save(info);
		    			  
		    			  if( i % 100 == 0 ) { 
		    			       //flush a batch of inserts and release memory:
		    				  tdpCadreDAO.flushAndclearSession();
		    			  }
			    	 }
				}
	   		
			}catch(Exception e){
				LOG.error("Exception raised at savingService", e);
			}
	   }
	   
	   
	   
	   
	   /** 2) 
		  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
		  *  Pushing tdpcadre data to intermediate tables
		  *  @since 18-OCTOBER-2016
		  */
		public ResultStatus pushTotalTodayTdpCadreDataToIntermediateByLowLevel(){
			
			   ResultStatus rs = null;
			   try{
				   Map<String,Long> locationTargetMap = getTdpCadreTargetCountLocationWise();
				   Map<String,TdpCadreLocationInfoVO> previousCadreMap = locationWisePreviousCadreCount();
				   
				   List<TdpCadreLocationInfoVO> finalList = new ArrayList<TdpCadreLocationInfoVO>(0);
				   
				   //Total
				   TdpCadreLocationInfoVO totalDataVO = new TdpCadreLocationInfoVO();
				   getLevelWiseDataTodayOrTotal("Total",totalDataVO,locationTargetMap,previousCadreMap);
				   finalList.add(totalDataVO);
				   
				   //Today
				   TdpCadreLocationInfoVO todayDataVO = new TdpCadreLocationInfoVO();
				   getLevelWiseDataTodayOrTotal("Today",todayDataVO,locationTargetMap,previousCadreMap);
				   finalList.add(todayDataVO);
				   
				   long tempStartTime = System.currentTimeMillis();
				   rs =  saveTotalTodayTdpCadreDataToIntermediateTempByLowLevel(finalList);
				   Double tempSeconds = (System.currentTimeMillis() - tempStartTime)/1000.0;
				   
				   
				   List<Long> locationScopeIds = new ArrayList<Long>();
				   locationScopeIds.add(5L);
				   locationScopeIds.add(6L);
				   locationScopeIds.add(7L);
				   locationScopeIds.add(8L);
				   locationScopeIds.add(9L);
				   
				    long intermediateStartTime = System.currentTimeMillis();
				    int deletedRecords =  tdpCadreLocationInfoDAO.deleteAllRecords(locationScopeIds);
				    int insertedRecordsCount = tdpCadreLocationInfoDAO.insertTdpCadreLocationInfoUpToLowLevel();
				    Double interSeconds = (System.currentTimeMillis() - intermediateStartTime)/1000.0;
				    LOG.error("totaltoday low level temp insert time is : " + tempSeconds  + " seconds && totaltoday low level intermediate insert time is :"+interSeconds + " seconds" );
				   
			  }catch(Exception e){
				  LOG.error("Exception raised in pushTotalTodayTdpCadreDataToIntermediate() in CadreRegistrationServiceNew class", e);
			  }
			   return rs;
		   }
		
		
		   public void getLevelWiseDataTodayOrTotal(String dateType,TdpCadreLocationInfoVO finalVO,Map<String,Long> locationTargetMap,Map<String,TdpCadreLocationInfoVO> previousCadreMap){
			  
			   try{ 
				    //tehsil
				       List<TdpCadreLocationInfoVO> tehsilList = levelWiseTdpCareDataByTodayOrTotal(dateType,"tehsil",locationTargetMap,previousCadreMap);
				       finalVO.setTehsilList(tehsilList);
				    
				  //leb
				      List<TdpCadreLocationInfoVO> lebList = levelWiseTdpCareDataByTodayOrTotal(dateType,"leb",locationTargetMap,previousCadreMap);
				      finalVO.setAssemblyList(lebList);
				    
				  //panchayat
				      List<TdpCadreLocationInfoVO> panchayatList = levelWiseTdpCareDataByTodayOrTotal(dateType,"panchayat",locationTargetMap,previousCadreMap);
				      finalVO.setParliamentList(panchayatList);
				      
				   //ward
				      List<TdpCadreLocationInfoVO> wardList = levelWiseTdpCareDataByTodayOrTotal(dateType,"ward",locationTargetMap,previousCadreMap);
				      finalVO.setDistrictList(wardList);
				      
				    //booth
				      List<TdpCadreLocationInfoVO> boothList = levelWiseTdpCareDataByTodayOrTotal(dateType,"booth",locationTargetMap,previousCadreMap);
				      finalVO.setStateList(boothList);
				   
			   }catch(Exception e){
				   LOG.error("Exception raised in getLevelWiseDataTodayOrTotal() in CadreRegistrationServiceNew class", e);
			   }
		   }
	      
		
		public List<TdpCadreLocationInfoVO> levelWiseTdpCareDataByTodayOrTotal(String dateType,String levelType,Map<String,Long> locationTargetMap,Map<String,TdpCadreLocationInfoVO> previousCadreMap){
			   
			   List<TdpCadreLocationInfoVO> finalList = null;
			   Map<Long,TdpCadreLocationInfoVO> levelMap = null;
			   
			   try{
				   
				   Long locationscopeId = null;
				   if(levelType.equalsIgnoreCase("tehsil")){
					   locationscopeId = 5L;
				   }else  if(levelType.equalsIgnoreCase("leb")){
					   locationscopeId = 7L;
				   }else  if(levelType.equalsIgnoreCase("panchayat")){
					   locationscopeId = 6L;
				   }else  if(levelType.equalsIgnoreCase("ward")){
					   locationscopeId = 8L;
				   }else  if(levelType.equalsIgnoreCase("booth")){
					   locationscopeId = 9L;
				   }
				   
				   
				     Date currentDate = null;
				     if(dateType != null && dateType.equalsIgnoreCase("Today")){
				    	 currentDate = new DateUtilService().getCurrentDateAndTime();
				     }
				     
				     //all Records
				      List<Object[]> totalList = tdpCadreDAO.levelWiseTdpCareDataByTodayOrTotal(currentDate,levelType);
				      if(totalList != null && totalList.size() > 0)
				      {  
				    	  levelMap = new TreeMap<Long, TdpCadreLocationInfoVO>();
				    	  for(Object[] obj : totalList )
				    	  {  
				    		  TdpCadreLocationInfoVO locationVO = null;
				    		  if(obj[0]!=null)//tehsilId
				    		  {
				    			  locationVO = levelMap.get((Long)obj[0]);
				    			  if(locationVO == null)
				    			  {
				    				  locationVO = new TdpCadreLocationInfoVO();
				    				  
				    				  locationVO.setId((Long)obj[0]);
				    				  locationVO.setLocationScopeId(locationscopeId);
				    				  locationVO.setDateType(dateType);
				    				  
				    				  locationVO.setCadre2016Records(obj[1]!=null ? (Long)obj[1]:0l);
				    				  locationVO.setCadre2016NewRecords(locationVO.getCadre2016Records());
				    				  
				    				  //2014 cadre data
				    				  String key = locationVO.getLocationScopeId() + "_" + locationVO.getId();
									  TdpCadreLocationInfoVO previousCadreData = previousCadreMap.get(key);
									   if(previousCadreData != null){
										   locationVO.setCadreCount(previousCadreData.getCadreCount());
										   locationVO.setCadrePercent(previousCadreData.getCadrePercent());
									   }
									   
									   levelMap.put(locationVO.getId(),locationVO);
				    			  }
				    		  }
				    	  }
				      }
				      
				      //Renewal Records
				      List<Object[]> renewalList = tdpCadreDAO.levelWiseRenewalTdpCareDataByTodayOrTotal(currentDate,levelType);
				      if(renewalList != null && renewalList.size() > 0)
				      {
				    	  for( Object[] obj : renewalList)
				    	  {
				    		  if(obj[0] != null)//tehsilId
				    		  {
				    			  TdpCadreLocationInfoVO locationVO = levelMap.get((Long)obj[0]);
				    			  if(locationVO != null){
				    				  locationVO.setCadre2016RenewalRecords(obj[1]!= null ? (Long)obj[1] : 0l);
				    				  locationVO.setCadre2016NewRecords( locationVO.getCadre2016Records() - locationVO.getCadre2016RenewalRecords() );
				    			  }
				    		  }
				    	  }
				      }
				      
				      //calculating percantage
				      if(levelMap != null && levelMap.size() > 0)
				      {
				    	  finalList = new ArrayList<TdpCadreLocationInfoVO>(levelMap.values());
				    	  if(finalList != null && finalList.size() > 0)
				    	  {
				    		  for(TdpCadreLocationInfoVO VO : finalList )
				    		  {  
				    			  String key = VO.getLocationScopeId() + "_" + VO.getId();
				    			  Long targetCount = locationTargetMap.get(key); 
				    			  if(targetCount != null && targetCount > 0l){
				    				  VO.setCadre2016RecordsPerc( calcPercantage( VO.getCadre2016Records() , targetCount ) );  
				    			  }
				    			  
				    			  if(VO.getCadre2016Records() != null && VO.getCadre2016Records() > 0l){
				    				  VO.setCadre2016RenewalRecordsPerc( calcPercantage( VO.getCadre2016RenewalRecords() , VO.getCadre2016Records()) );
				    				  VO.setCadre2016NewRecordsPerc( calcPercantage( VO.getCadre2016NewRecords() , VO.getCadre2016Records()) );
				    			  }
				    		  }
				    	  }
				      }
				      
			   }catch(Exception e){
				   //e.printStackTrace();
				   LOG.error("Exception raised in levelWiseTdpCareDataByTodayOrTotal() in CadreRegistrationServiceNew class", e);
			   }
			   return finalList;
		   }
		public ResultStatus saveTotalTodayTdpCadreDataToIntermediateTempByLowLevel(final List<TdpCadreLocationInfoVO> finalList){
			   
			final ResultStatus rs = new ResultStatus();
			final DateUtilService dateUtilService = new DateUtilService();
			
			try {
				
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			        	
			        	if(finalList != null && finalList.size() > 0){
			        		
			        		List<Long> locationScopeIds = new ArrayList<Long>();
			        		
			        		int deletedRecords = tdpCadreLocationInfoTemp1DAO.deleteAllRecords();
						    //int count = tdpCadreLocationInfoTemp1DAO.setPrimaryKeyAutoIncrementToOne();
						    
						    Date currentTime = dateUtilService.getCurrentDateAndTime();
						    
						    for(TdpCadreLocationInfoVO dateTypeVO : finalList){
				        		
						    	if(dateTypeVO.getTehsilList() != null && dateTypeVO.getTehsilList().size() > 0){//tehsil
						    		savingServiceLowLevel(dateTypeVO.getTehsilList(),currentTime);
						    	}
						    	
						    	if(dateTypeVO.getAssemblyList() != null && dateTypeVO.getAssemblyList().size() > 0){//leb
						    		savingServiceLowLevel(dateTypeVO.getAssemblyList(),currentTime);
						    	}
						    	
				        		if(dateTypeVO.getParliamentList() != null && dateTypeVO.getParliamentList().size() > 0){//panchayat
				        			savingServiceLowLevel(dateTypeVO.getParliamentList(),currentTime);
				        		}
				        		
				        		if(dateTypeVO.getDistrictList() != null && dateTypeVO.getDistrictList().size() > 0){//ward
				        			savingServiceLowLevel(dateTypeVO.getDistrictList(),currentTime);
				        		}
				        		if(dateTypeVO.getStateList() != null && dateTypeVO.getStateList().size() > 0){//booth
				        			savingServiceLowLevel(dateTypeVO.getStateList(),currentTime);
				        		}
				        		
				        	}
			        	}
			        	   
				          rs.setResultCode(1);
				          rs.setMessage("success");
			         }
			   });
				
			} catch (Exception e) {
				LOG.error("Exception raised at CadreRegistrationServiceNew", e);
				rs.setResultCode(0);
				rs.setMessage("failure");
			}
			return rs;
		}
		public void savingServiceLowLevel(List<TdpCadreLocationInfoVO> list , Date currentTime){
		   	
		   	try{
					if( list != null && list.size() > 0)
					{
						int i= 0;
						for(TdpCadreLocationInfoVO locationVO : list  )
						{
				    		  i = i + 1;
				    		  
							  TdpCadreLocationInfoTemp1 info = new TdpCadreLocationInfoTemp1();
			    			  
			    			  info.setLocationScopeId(locationVO.getLocationScopeId());
			    			  info.setLocationValue(locationVO.getId());
			    			  
			    			  if(locationVO.getCadreCount() != null && locationVO.getCadreCount() > 0l){
			    				  info.setCadre2014(locationVO.getCadreCount());  
			    			  }
			    			  if(locationVO.getCadrePercent() != null){
			    				  info.setCadre2014Percent(locationVO.getCadrePercent());
			    			  }
			    			  
			    			  if( locationVO.getCadre2016Records() != null && locationVO.getCadre2016Records() > 0l){
			    				  info.setCadre2016(locationVO.getCadre2016Records()); 
			    			  }
			    			  if(locationVO.getCadre2016RecordsPerc() != null && locationVO.getCadre2016RecordsPerc() > 0l){
			    				  info.setCadre2016Percent(locationVO.getCadre2016RecordsPerc().toString());
			    			  }
			    			  
			    			  if( locationVO.getCadre2016RenewalRecords() != null && locationVO.getCadre2016RenewalRecords() > 0l){
			    				  info.setRenewalCadre(locationVO.getCadre2016RenewalRecords()); 
			    			  }
			    			  if(locationVO.getCadre2016RenewalRecordsPerc() != null && locationVO.getCadre2016RenewalRecordsPerc() > 0l){
			    				  info.setRenewalCadrePercent(locationVO.getCadre2016RenewalRecordsPerc().toString());
			    			  }
			    			  
			    			  if( locationVO.getCadre2016NewRecords() != null && locationVO.getCadre2016NewRecords() > 0l){
			    				  info.setNewCadre(locationVO.getCadre2016NewRecords()); 
			    			  }
			    			  if(locationVO.getCadre2016NewRecordsPerc() != null && locationVO.getCadre2016NewRecordsPerc() > 0l){
			    				  info.setNewCadrePercent(locationVO.getCadre2016NewRecordsPerc().toString());
			    			  }
			    			  
			    			  info.setType(locationVO.getDateType());
			    			  info.setInsertedTime(currentTime);
			    			  
			    			  tdpCadreLocationInfoTemp1DAO.save(info);
			    			  
			    			  if( i % 100 == 0 ) { 
			    			       //flush a batch of inserts and release memory:
			    				  tdpCadreDAO.flushAndclearSession();
			    			  }
				    	 }
					};
		   		
				}catch(Exception e){
					LOG.error("Exception raised at savingService", e);
				}
		   }
		
	   
	   
	   /** 3)
		  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
		  *  Pushing tdpcadre data to intermediate tables date wise.
		  *  @since 18-OCTOBER-2016
		  */
	   
		public ResultStatus pushTdpCadreDataToIntermediateDateWise(){
			
			   ResultStatus rs = null;
			   try{
				   
				   Map<Long,Long> acpcMap = getAllAssemblyConstsAndItsParliamentConsts();
				   Map<Long,Long> constDistrictMap = getAllASsemblyContsAndItsDistricts();
				   Map<Long,Long> distStateMap = getAllDistrictsAndItsStates();
				   Map<String,Long> locationTargetMap = getTdpCadreTargetCountLocationWise();
				   
				   
				   //Get From Date.
				    Date fromDate = null; 
				    if(IConstants.DATE_WISE_CADRE_INTERMEDIATE_PUSH_DAYS >= 0){
				    	Calendar cal = Calendar.getInstance();
				    	cal.add(Calendar.DATE, - IConstants.DATE_WISE_CADRE_INTERMEDIATE_PUSH_DAYS);
				    	fromDate = cal.getTime();
				    }
				    
				   TdpCadreLocationInfoVO finalVO = new TdpCadreLocationInfoVO();
				  
				   geTdpCadreDataByDateAndLocation(finalVO,acpcMap,constDistrictMap,distStateMap,locationTargetMap,fromDate);
				    
				   long startTime = System.currentTimeMillis();
				   rs =  saveTdpCadreDataToIntermediateTempDateWise(finalVO,fromDate);
				   Double tempSeconds = (System.currentTimeMillis() - startTime)/1000.0;
				   
				   long intermediateStartTime = System.currentTimeMillis();
				    int deletedRecords =  tdpCadreDateWiseInfoDAO.deleteAllRecords(fromDate);
				    int insertedRecordsCount = tdpCadreDateWiseInfoDAO.insertTdpCadreLocInfoDateWiseUpToConstituencyLevel();
				    Double interSeconds = (System.currentTimeMillis() - intermediateStartTime)/1000.0;
				    
				    LOG.error("datewise const level temp inserted time is : " + tempSeconds  + " seconds && datewise const level intermediate inserted time is :" +interSeconds + " seconds" );
				   
			  }catch(Exception e){
				  LOG.error("Exception raised in pushTdpCadreDataToIntermediateDateWise() in CadreRegistrationServiceNew class", e);
			  }
			   return rs;
		   }
		   
		   
		   public void geTdpCadreDataByDateAndLocation(TdpCadreLocationInfoVO finalVO,Map<Long,Long> acpcMap,Map<Long,Long> constDistrictMap,Map<Long,Long> distStateMap,Map<String,Long> locationTargetMap,Date fromDate){
			  
			   try{ 
				     Map<String,Map<Long,TdpCadreLocationInfoVO>> constituencyMap =  geTdpCadreDataByDateAndConstituency(locationTargetMap,fromDate);
				     Map<String,Map<Long,TdpCadreLocationInfoVO>> parliamentMap = setDataDateWise("parliament", constituencyMap , acpcMap  , locationTargetMap );
				     Map<String,Map<Long,TdpCadreLocationInfoVO>> districtMap = setDataDateWise("district"  , constituencyMap , constDistrictMap , locationTargetMap );
				     Map<String,Map<Long,TdpCadreLocationInfoVO>> stateMap = setDataDateWise("state" , districtMap , distStateMap , locationTargetMap );
				     
				     finalVO.setAssemblyList( getList(constituencyMap) );
				     finalVO.setParliamentList( getList(parliamentMap) );
				     finalVO.setDistrictList( getList(districtMap) );
				     finalVO.setStateList( getList(stateMap) );
				     
			   }catch(Exception e){
				   LOG.error("Exception raised in geTdpCadreDataByDateAndLocation() in CadreRegistrationServiceNew class", e);
			   }
		   }
		   
		   public Map<String,Map<Long,TdpCadreLocationInfoVO>> geTdpCadreDataByDateAndConstituency(Map<String,Long> locationTargetMap,Date fromDate){
			   
			   Map<String,Map<Long,TdpCadreLocationInfoVO>> finalMap = new LinkedHashMap<String, Map<Long,TdpCadreLocationInfoVO>>(0);
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			   try{  
				   
				     //all Records
				      List<Object[]> constTotalList = tdpCadreDAO.getTdpCadreDataByDateAndConstituency(fromDate);
				      
				      if(constTotalList != null && constTotalList.size() > 0)
				      {  
				    	  for(Object[] obj : constTotalList )
				    	  {   
				    		  if(obj[0]!=null)//surveydate
				    		  {
				    			  String surveydate = obj[0].toString();
				    			  Map<Long,TdpCadreLocationInfoVO> dateMap = finalMap.get(surveydate);
				    			  if(dateMap == null)
				    			  {
					    			  dateMap = new LinkedHashMap<Long, TdpCadreLocationInfoVO>(0);
					    			  finalMap.put(surveydate, dateMap);
					    		  }
				    			  dateMap = finalMap.get(surveydate);
				    			  
				    			  if(obj[1]!=null)//constituencyId
				    			  {  
				    				  TdpCadreLocationInfoVO constVO = dateMap.get((Long)obj[1]);
				    				  if(constVO == null)
				    				  {  
				    					  constVO = new TdpCadreLocationInfoVO();
				    					  
				    					  constVO.setSurveyDateStr(surveydate);
				    					  constVO.setSurveyDate(sdf.parse(surveydate));
				    					  constVO.setId((Long)obj[1]);
				    					  constVO.setLocationScopeId(4L);
				    					  
				    					  constVO.setCadre2016Records(obj[2]!=null ? (Long)obj[2]:0l);
							    		  constVO.setCadre2016NewRecords(constVO.getCadre2016Records());
							    		  
							    		  dateMap.put((Long)obj[1], constVO);
				    				  }
				    			  }
				    		 
				    		  }
				    	  }
				      }
				      
				      //Renewal Records
				      List<Object[]> constRenewalList = tdpCadreDAO.getRenewalTdpCadreDataByDateAndConstituency(fromDate);
				      if(constRenewalList != null && constRenewalList.size() > 0)
				      {
				    	  for( Object[] obj : constRenewalList)
				    	  {  
				    		  if( obj[0] != null && obj[1] != null)
				    		  { 
				    			  Map<Long,TdpCadreLocationInfoVO> dateMap = finalMap.get(obj[0].toString());
				    			  if(dateMap != null)
				    			  {
				    				  TdpCadreLocationInfoVO constVO = dateMap.get((Long)obj[1]);
				    				  if(constVO != null)
				    				  {
				    					 constVO.setCadre2016RenewalRecords(obj[2]!= null ? (Long)obj[2] : 0l );
				    					 constVO.setCadre2016NewRecords( constVO.getCadre2016Records() - constVO.getCadre2016RenewalRecords() );
				    				  }
					    		  }
				    		  }
				    	  }
				      }
				      
				      //Calculating percantages.
				      calculateCadrePercantage(finalMap,locationTargetMap);
				      
			   }catch(Exception e){
				   //e.printStackTrace();
				   LOG.error("Exception raised in geTdpCadreDataByDateAndConstituency() in CadreRegistrationServiceNew class", e);
			   }
			   return finalMap;
		   }
	   
		   public Map<String,Map<Long,TdpCadreLocationInfoVO>> setDataDateWise(String scope,Map<String,Map<Long,TdpCadreLocationInfoVO>> dataDateMap ,Map<Long,Long> constParliamentMap,Map<String,Long> locationTargetMap){
			   
			     Map<String,Map<Long,TdpCadreLocationInfoVO>> returnDateMap = null; 
			     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			     
			   try{
				   
				   if(dataDateMap != null && dataDateMap.size() > 0)
				    { 
					     returnDateMap = new LinkedHashMap<String, Map<Long,TdpCadreLocationInfoVO>>(0);
					     
				    	 for(String surveydate : dataDateMap.keySet())
				    	 {  
				    		 if(surveydate!=null)
				    		 { 
				    			 Map<Long,TdpCadreLocationInfoVO> constMap = dataDateMap.get(surveydate);
				    			 
				    			 //check date exits
				    			  Map<Long,TdpCadreLocationInfoVO> parliamentMap = returnDateMap.get(surveydate);
				    			  if(parliamentMap == null)
				    			  {
				    				  parliamentMap = new LinkedHashMap<Long, TdpCadreLocationInfoVO>(0);
				    				  returnDateMap.put(surveydate, parliamentMap);
					    		  }
				    			  parliamentMap = returnDateMap.get(surveydate);
				    			  
				    			 //check parliamentId exists.
					    		  if(constMap != null && constMap.size() > 0)
					    		  {
					    			  for(Long constituencyId : constMap.keySet())
					    			  {  
					    				  if(constituencyId != null && constituencyId > 0l)
					    				  {
					    					  TdpCadreLocationInfoVO constVO = constMap.get(constituencyId);
					    					  
					    					  Long parliamentId = constParliamentMap.get(constituencyId);
					    					  if(parliamentId != null && parliamentId > 0l)
					    					  {
					    						  TdpCadreLocationInfoVO parliamentVO = parliamentMap.get(parliamentId);
					    						  if(parliamentVO == null){
					    							  
					    							  parliamentVO = new TdpCadreLocationInfoVO();
					    							  parliamentVO.setSurveyDateStr(surveydate);
					    							  parliamentVO.setSurveyDate(sdf.parse(surveydate));
					    							  parliamentVO.setId(parliamentId);
					    							  if(scope.equalsIgnoreCase("parliament")){
					    								  parliamentVO.setLocationScopeId(10L);
							    					  }else if(scope.equalsIgnoreCase("district")){
							    						  parliamentVO.setLocationScopeId(3L);
							    					  }else if(scope.equalsIgnoreCase("state")){
							    						  parliamentVO.setLocationScopeId(2L);
							    					  }
					    							  parliamentMap.put(parliamentId,parliamentVO);
					    						  }
					    						  parliamentVO = parliamentMap.get(parliamentId);
					    						  
					    						  parliamentVO.setCadre2016Records(parliamentVO.getCadre2016Records() + constVO.getCadre2016Records() );
					    						  parliamentVO.setCadre2016RenewalRecords( parliamentVO.getCadre2016RenewalRecords() + constVO.getCadre2016RenewalRecords());
					    						  parliamentVO.setCadre2016NewRecords(parliamentVO.getCadre2016NewRecords() + constVO.getCadre2016NewRecords() );
					    					  }
					    				  }
					    			  }
					    		  }
				    		  }
				    	 }
				    	 
				    	//Calculating percantages.
					      calculateCadrePercantage(returnDateMap,locationTargetMap);
				     }
				   
			   }catch(Exception e){
				   LOG.error("Exception raised in setCorrespondingLevelData() in CadreRegistrationServiceNew class", e);
			   }
			   return returnDateMap;
		   }
		   
		   public void calculateCadrePercantage(Map<String,Map<Long,TdpCadreLocationInfoVO>> finalMap,Map<String,Long> locationTargetMap){
			   
			   try{	
				      if( finalMap != null && finalMap.size() > 0)
				      {
				    	  for( String surveydate : finalMap.keySet())
				    	  {
				    		  Map<Long,TdpCadreLocationInfoVO> constMap = finalMap.get(surveydate);
				    		  if(constMap != null && constMap.size() > 0)
				    		  {
				    			  for(Long constituencyId : constMap.keySet())
				    			  {
				    				  TdpCadreLocationInfoVO constVO = constMap.get(constituencyId);
				    				  if(constVO != null)
				    				  {  
				    					  Long targetCount = locationTargetMap.get(constVO.getLocationScopeId() + "_" + constVO.getId());
				    					  if (targetCount != null && targetCount.longValue() > 0l)
				    					  {
				    						  constVO.setCadre2016RecordsPerc( calcPercantage( constVO.getCadre2016Records() , targetCount ) );  
				    					  }
				    					  
				    					  if(constVO.getCadre2016Records() != null && constVO.getCadre2016Records().longValue() > 0l)
				    					  {
				    						  constVO.setCadre2016RenewalRecordsPerc( calcPercantage( constVO.getCadre2016RenewalRecords() , constVO.getCadre2016Records()) );
				    						  constVO.setCadre2016NewRecordsPerc( calcPercantage( constVO.getCadre2016NewRecords() , constVO.getCadre2016Records()) );
						    			  }
				    				  }
				    			  }
				    		  }
				    	  }
				      }
				   
			  }catch(Exception e){
				  LOG.error("Exception raised in calculateCadrePercantage() in CadreRegistrationServiceNew class", e);
			  }
		   }
		   
		   List<TdpCadreLocationInfoVO> getList(Map<String,Map<Long,TdpCadreLocationInfoVO>> constituencyMap){
			   List<TdpCadreLocationInfoVO> finalList = null;
			  try{
				  
				  if(constituencyMap != null && constituencyMap.size() > 0){
			    	 finalList = new ArrayList<TdpCadreLocationInfoVO>(0);
			    	 for(Map.Entry<String,Map<Long,TdpCadreLocationInfoVO>> entry : constituencyMap.entrySet()){  
			    	    if(entry.getValue() != null && entry.getValue().size() > 0){
			    	    	finalList.addAll(entry.getValue().values());
			    	    }
			    	 }
			     }
			  }catch(Exception e){
				  LOG.error("Exception raised in getList() in CadreRegistrationServiceNew class", e);
			  }
			  return finalList;
		   }
		   
		   public ResultStatus saveTdpCadreDataToIntermediateTempDateWise(final TdpCadreLocationInfoVO finalVO,final Date fromDate ){
			   
				final ResultStatus rs = new ResultStatus();
				final DateUtilService dateUtilService = new DateUtilService();
				
				try {
					
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				        	
				        	if(finalVO != null)
				        	{
				        		
				        		int deletedRecords = tdpCadreDateWiseInfoTempDAO.deleteAllRecords();
							    //int count = tdpCadreDateWiseInfoTempDAO.setPrimaryKeyAutoIncrementToOne();
							    
							    Date currentTime = dateUtilService.getCurrentDateAndTime();
					    		
					    		if( finalVO.getAssemblyList() != null && finalVO.getAssemblyList().size() > 0){
					    			savingServiceDateWise(finalVO.getAssemblyList(),currentTime);
					    		}
				        		
				        		if(finalVO.getParliamentList() != null && finalVO.getParliamentList().size() > 0){
				        			savingServiceDateWise(finalVO.getParliamentList(),currentTime);
				        		}
				        		
				        		if( finalVO.getDistrictList() != null && finalVO.getDistrictList().size() > 0){
				        			savingServiceDateWise(finalVO.getDistrictList(),currentTime);
				        		}
				        		
				        		if(finalVO.getStateList() != null && finalVO.getStateList().size() > 0){
				        			savingServiceDateWise(finalVO.getStateList(),currentTime);
				        		}
				        		
				        	}
				        	   
					          rs.setResultCode(1);
					          rs.setMessage("success");
				         }
				    });
					
				} catch (Exception e) {
					LOG.error("Exception raised at saveTdpCadreDataToIntermediateDateWise() in CadreRegistrationServiceNew service", e);
					rs.setResultCode(0);
					rs.setMessage("failure");
				}
				return rs;
			}
		   public void savingServiceDateWise(List<TdpCadreLocationInfoVO> list , Date currentTime){
		   	
		   	try{
					if( list != null && list.size() > 0)
					{
						int i= 0;
						for(TdpCadreLocationInfoVO locationVO : list  )
						{	
							   i = i + 1;
				    		  if(locationVO != null)
				    		  {
				    			  
				    			  TdpCadreDateWiseInfoTemp info = new TdpCadreDateWiseInfoTemp();
				    			  
				    			  info.setSurveyDate(locationVO.getSurveyDate());
				    			  
				    			  info.setLocationScopeId(locationVO.getLocationScopeId());
				    			  info.setLocationValue(locationVO.getId());
				    			  
				    			  if( locationVO.getCadre2016Records() != null && locationVO.getCadre2016Records() > 0l){
				    				  info.setCadre2016(locationVO.getCadre2016Records()); 
				    			  }
				    			  if(locationVO.getCadre2016RecordsPerc() != null && locationVO.getCadre2016RecordsPerc() > 0l){
				    				  info.setCadre2016Percent(locationVO.getCadre2016RecordsPerc().toString());
				    			  }
				    			  
				    			  if( locationVO.getCadre2016RenewalRecords() != null && locationVO.getCadre2016RenewalRecords() > 0l){
				    				  info.setRenewalCadre(locationVO.getCadre2016RenewalRecords()); 
				    			  }
				    			  if(locationVO.getCadre2016RenewalRecordsPerc() != null && locationVO.getCadre2016RenewalRecordsPerc() > 0l){
				    				  info.setRenewalCadrePercent(locationVO.getCadre2016RenewalRecordsPerc().toString());
				    			  }
				    			  
				    			  if( locationVO.getCadre2016NewRecords() != null && locationVO.getCadre2016NewRecords() > 0l){
				    				  info.setNewCadre(locationVO.getCadre2016NewRecords()); 
				    			  }
				    			  if(locationVO.getCadre2016NewRecordsPerc() != null && locationVO.getCadre2016NewRecordsPerc() > 0l){
				    				  info.setNewCadrePercent(locationVO.getCadre2016NewRecordsPerc().toString());
				    			  }
				    			  
				    			  info.setInsertedTime(currentTime);
				    			  
				    			  tdpCadreDateWiseInfoTempDAO.save(info);
				    		  }
				    	 }
						 if( i % 100 == 0 ) { 
		    			       //flush a batch of inserts and release memory:
		    				  tdpCadreDAO.flushAndclearSession();
		    			  }
					}
		   		
				}catch(Exception e){
					LOG.error("Exception raised at savingServiceDateWise", e);
				}
		   }
		   
		   
		   //Calculating percantage.
		   public Double calcPercantage(Long subCount,Long totalCount){
			   Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			   return d;
		  }
		   
		   /** 
		    *  SAVING CADRE IMAGE.
		    * 
		    */
		  public void saveCadreImage(ImageCadreVO inputVO)
		  {	
				try{
					
					Long constituencyId = inputVO.getConstituencyId();
					
					String pathSeparator = System.getProperty(IConstants.FILE_SEPARATOR);
					
					String filePath = IConstants.STATIC_CONTENT_FOLDER_PATH+pathSeparator+"images"+pathSeparator+IConstants.CADRE_IMAGES+pathSeparator;
					String imageUrl = "";
					
					if(constituencyId != null && constituencyId.longValue() > 0)
					{
						filePath = filePath +constituencyId.toString()+pathSeparator;
						imageUrl = constituencyId.toString()+"/";
					}
					
					filePath = filePath+inputVO.getMemberShipNo()+".jpg";
					imageUrl = imageUrl + inputVO.getMemberShipNo()+".jpg";
					inputVO.setImagePath(imageUrl);
					
					new File(filePath).getParentFile().mkdirs();
					
					if(inputVO.getPhotoType() != null && inputVO.getPhotoType().equalsIgnoreCase(IConstants.CADRE_IMAGE_TYPE_NEW))
					{
						if(inputVO.getDataSourceType().equalsIgnoreCase(IConstants.CADRE_DATA_SOURCE_TYPE_TAB) && inputVO.getImageBase64String() != null && inputVO.getImageBase64String().trim().length() > 0)
						{
							inputVO.setImageBase64String(inputVO.getImageBase64String().replace("_", "/"));
							inputVO.setImageBase64String(inputVO.getImageBase64String().replace("-", "+"));
							
							boolean status = imageAndStringConverter.convertBase64StringToImage(inputVO.getImageBase64String(),filePath);
							if(status)
							{
								LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Base64 String - Available - Success");
								inputVO.setImageSaveStatus(IConstants.STATUS_SUCCESS);
							}
							else
							{
								LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Base64 String - Available - Fail");
								inputVO.setImageSaveStatus(IConstants.STATUS_FAIL);
								inputVO.setImageIssue("Base64 String Available But Saving Failed");
							}
						}
						
						//Consider here Online-Tab
						else if(inputVO.getDataSourceType().equalsIgnoreCase(IConstants.CADRE_DATA_SOURCE_TYPE_WEB) || inputVO.getDataSourceType().equalsIgnoreCase(IConstants.CADRE_DATA_SOURCE_TYPE_ONLINE))
						{
							/*if(inputVO.getUploadImage() != null)
							{
								boolean status = imageAndStringConverter.convertBase64StringToImage(inputVO.getImageBase64String(),filePath);
								if(status)
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Base64 String  - Available - Success");
									inputVO.setImageSaveStatus(IConstants.STATUS_SUCCESS);
								}
								else
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Base64 String  - Available Fail - Trying with Voter");
									inputVO.setPhotoType(IConstants.CADRE_IMAGE_TYPE_VOTER);
									saveCadreImage(inputVO);
								}								
								
								boolean status = commonMethodsUtilService.fileCopy(inputVO.getUploadImage().getAbsolutePath(),filePath);
								
								if(status)
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Upload Image - Available - Success");
									inputVO.setImageSaveStatus(IConstants.STATUS_SUCCESS);
								}
								else
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Upload Image - Available Fail - Trying with Voter");
									inputVO.setPhotoType(IConstants.CADRE_IMAGE_TYPE_VOTER);
									saveCadreImage(inputVO);
								}
								
							}*/
							
							if(inputVO.getImageBase64String() != null && inputVO.getImageBase64String().trim().length() > 0)
							{
								inputVO.setImageBase64String(inputVO.getImageBase64String().replace("_", "/"));
								inputVO.setImageBase64String(inputVO.getImageBase64String().replace("-", "+"));
								
								boolean status = imageAndStringConverter.convertBase64StringToImage(inputVO.getImageBase64String(),filePath);
								if(status)
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Base64 String - Available - Success");
									inputVO.setImageSaveStatus(IConstants.STATUS_SUCCESS);
								}
								else
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Base64 String - Available - Fail");
									inputVO.setImageSaveStatus(IConstants.STATUS_FAIL);
									inputVO.setImageIssue("Web Base64 String Available But Saving Failed");
								}
							}
							else
							{
								LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Upload Image - Not Available Trying with Voter");
								
								if(inputVO.getVoterId() != null)
								{	
									inputVO.setPhotoType(IConstants.CADRE_IMAGE_TYPE_VOTER);
									saveCadreImage(inputVO);
								}
							}
						}
						else
						{
							LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Base64 String - Not Available - Fail");
							inputVO.setImageSaveStatus(IConstants.STATUS_FAIL);
							inputVO.setImageIssue("Base64 String Not Available, Failed");
						}
					}
					else if(inputVO.getPhotoType() != null && inputVO.getPhotoType().equalsIgnoreCase(IConstants.CADRE_IMAGE_TYPE_VOTER))
					{
						if(inputVO.getVoterId() != null)
						{
							String voterImgPath = voterDAO.getVoterImagePathByVoterId(inputVO.getVoterId());
							
							if(voterImgPath.trim().length() == 0)
							{
								LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Voter Image Path - Not Available - Fail");
								inputVO.setImageSaveStatus(IConstants.STATUS_FAIL);
								inputVO.setImageIssue("Voter Image Path - Not Available - Failed");
							}
							else
							{
								String sourcePath = IConstants.STATIC_CONTENT_FOLDER_PATH+pathSeparator+IConstants.VOTER_IMAGES+pathSeparator+voterImgPath;
								boolean status = commonMethodsUtilService.fileCopy(sourcePath,filePath);
								
								if(status)
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Voter Image - Available Copy - Success");
									inputVO.setImageSaveStatus(IConstants.STATUS_SUCCESS);
								}
								else
								{
									LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Voter Image - Available Copy - Fail");
									inputVO.setImageSaveStatus(IConstants.STATUS_FAIL);
									inputVO.setImageIssue("Voter Image - Available Copy - Failed");
								}
								
							}
						}
						else
						{
							LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Voter Id - Not Available - Fail");
							inputVO.setImageSaveStatus(IConstants.STATUS_FAIL);
							inputVO.setImageIssue("Voter Id - Not Available - Failed");
						}
					}
					
					else if(inputVO.getPhotoType() != null && inputVO.getPhotoType().equalsIgnoreCase(IConstants.CADRE_IMAGE_TYPE_CADRE))
					{
						String imgPath = tdpCadreDAO.getCadreImagePathByTdpCadreId(inputVO.getTdpCadreId());
						
						if(imgPath.trim().length() == 0)
						{
							if(inputVO.getVoterId() != null)
							{
								inputVO.setPhotoType(IConstants.CADRE_IMAGE_TYPE_VOTER);
								saveCadreImage(inputVO);
							}
							else
							{
								LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Cadre Image Path - Not Available and Voter Id Not Available - Fail ");
								inputVO.setImageSaveStatus(IConstants.STATUS_FAIL);
								inputVO.setImageIssue("Cadre Image Path - Not Available and Voter Id Not Available - Failed");
							}
						}
						else
						{
							LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Cadre Image Path - Available Ignored - Success");
							
							boolean status = commonMethodsUtilService.fileCopy(IConstants.STATIC_CONTENT_FOLDER_PATH+pathSeparator+"images"+pathSeparator+IConstants.CADRE_IMAGES+pathSeparator+imgPath.trim(),filePath);	
							
							if(status)
							{
								LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Cadre Image - Available - Success");
								inputVO.setImageSaveStatus(IConstants.STATUS_SUCCESS);
							}
							else
							{
								LOG.fatal("Cadre Image Save Status - For Cadre - "+inputVO.getMemberShipNo()+" - Photo Type - "+inputVO.getPhotoType()+" Cadre Image - Available Fail - Trying with Voter");
								if(inputVO.getVoterId() != null)
								{
									inputVO.setPhotoType(IConstants.CADRE_IMAGE_TYPE_VOTER);
									saveCadreImage(inputVO);
								}
							}	
						}
					}
					else
					{
						if(!inputVO.isUpdate())
						{
							if(inputVO.getVoterId() != null)
							{
								inputVO.setPhotoType(IConstants.CADRE_IMAGE_TYPE_VOTER);
								saveCadreImage(inputVO);
							}
						}
					}
				}catch (Exception e) {
					LOG.error("Exception occured in saveCadreImage() Method - ",e);
				}
			}
	
	public ResultStatus pushTabUserInfoIntoIntermediateTable(){
		ResultStatus rs = new ResultStatus();
		try {
			
			int count = tabUserEnrollmentInfoDAO.pushTabUserInfoIntoIntermediateTable();  
			LOG.error("Updated row:"+count);  
		} catch (Exception e) {
			LOG.error("Exception raised at CadreRegistrationServiceNew", e);
			rs.setResultCode(0);
			rs.setMessage("failure");
		}
		return rs;    
	}
	
	 //HOUR WISE INTERMEDIATE PUSHING.
	/** 4)
	 *  @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	 *  Pushing tdpcadre data to intermediate tables hour wise for Today.
	 *  @since 03-NOVEMBER-2016 
	 */ 
       
	public ResultStatus pushHourWiseTdpCadreDetailsByToday(){
		   
		 ResultStatus rs = null;
		 
		try {
			  List<CadreDateVO> list = getHourWiseTdpCadreDetailsByToday();
			  if(list != null && list.size() > 0){
				  
				 long startTime = System.currentTimeMillis();
				 rs = saveInToHourRegInfoTempByToday(list);
				 Double tempSeconds = (System.currentTimeMillis() - startTime)/1000.0;
				 
		         if(rs != null){
		        	Date currentDate = new DateUtilService().getCurrentDateAndTime();
		        	
		        	long intermediateStartTime = System.currentTimeMillis();
		        	int deletedRecords =  tdpCadreHourRegInfoDAO.deleteAllRecords(currentDate);
		        	int insertedRecordsCount = tdpCadreHourRegInfoDAO.insertCadreDataHourWiseToday();
		        	Double interSeconds = (System.currentTimeMillis() - intermediateStartTime)/1000.0;
		        	
		        	LOG.error("Today hour wise tdpcadre temp insert time is : " + tempSeconds  + " seconds && Today hour wise tdpcadre intermediate insert time is :" +interSeconds + " seconds" );
		         }
			  }
		} catch (Exception e) {
			LOG.error("Exception raised at pushHourWiseTdpCadreDetailsByToday", e);
		}
		return rs;
	}
	public List<CadreDateVO> getHourWiseTdpCadreDetailsByToday(){
 	   
 	   List<CadreDateVO> finalList = new ArrayList<CadreDateVO>(0);
 	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 	 try{
		     
 		 Date todayDate = new DateUtilService().getCurrentDateAndTime();
 		 
 		 List<Object[]> apList = tdpCadreHourRegInfoDAO.getDateHourWiseTdpCadreCount(todayDate,1L);
 		 if(apList != null && apList.size() > 0){
 			 for(Object[] obj : apList){
 				CadreDateVO hourVO = new CadreDateVO();
		   		    	
	   		    	hourVO.setStateId(1L);
	   		    	if(obj[0]!=null){
	   		    		hourVO.setDateStr(sdf.format((Date)obj[0]));
	   		    	}
	   		    	hourVO.setDayHour(obj[1]!=null ? (Integer)obj[1] : 0);
	   		    	hourVO.setRegCount(obj[2]!=null ? (Long)obj[2] : 0l);
	   		    	hourVO.setUsersCount(obj[3]!=null ? (Long)obj[3] : 0l);
	   		    	
	   		    	finalList.add(hourVO);
 			 }
 		 }
 		 
 		 
 		 List<Object[]> tsList = tdpCadreHourRegInfoDAO.getDateHourWiseTdpCadreCount(todayDate,36L);
 		 if(tsList != null && tsList.size() > 0){
 			 for(Object[] obj : tsList){
 				CadreDateVO hourVO = new CadreDateVO();
		   		    	
	   		    	hourVO.setStateId(36L);
	   		    	if(obj[0]!=null){
	   		    		hourVO.setDateStr(sdf.format((Date)obj[0]));
	   		    	}
	   		    	hourVO.setDayHour(obj[1]!=null ? (Integer)obj[1] : 0);
	   		    	hourVO.setRegCount(obj[2]!=null ? (Long)obj[2] : 0l);
	   		    	hourVO.setUsersCount(obj[3]!=null ? (Long)obj[3] : 0l);
	   		    	
	   		    	finalList.add(hourVO);
 			 }
 		 }
 		 
		 }catch(Exception e){
			 LOG.error("Exception raised at getHourWiseTdpCadreDetailsByToday", e);
		 } 
 	 return finalList;
    }
	public ResultStatus saveInToHourRegInfoTempByToday(final List<CadreDateVO> finalList){
	    final  ResultStatus rs = new ResultStatus();
	   	try{
				if( finalList != null && finalList.size() > 0)
				{
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					
							int deletedRecords = tdpCadreHourRegInfoTempDAO.deleteAllRecords();
							//int count = tdpCadreHourRegInfoTempDAO.setPrimaryKeyAutoIncrementToOne();
							
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date currentTime = new DateUtilService().getCurrentDateAndTime();
							
							int i= 0;
							for (CadreDateVO dataVO : finalList )
							{
					    		  i = i +1;
					    		  if(dataVO != null){
					    			  TdpCadreHourRegInfoTemp info = new TdpCadreHourRegInfoTemp();
					    			  info.setStateId(dataVO.getStateId());
					    			  if(dataVO.getDateStr() != null){
					    				  try {
											info.setSurveyDate(sdf.parse(dataVO.getDateStr()));
										} catch (ParseException e) {
											e.printStackTrace();
										}
					    			  }
					    			  info.setHour(dataVO.getDayHour().longValue());
					    			  if(dataVO.getRegCount() != null && dataVO.getRegCount() > 0l){
					    				  info.setTotalRegistrations(dataVO.getRegCount());
					    			  }
					    			  if(dataVO.getUsersCount() != null && dataVO.getUsersCount() > 0){
					    				  info.setCadreSurveyUsers(dataVO.getUsersCount());
					    			  }
					    			  info.setInsertedTime(currentTime);
					    			  
					    			  tdpCadreHourRegInfoTempDAO.save(info);
					    		  }
				    			  if( i % 100 == 0 ) { 
				    			       //flush a batch of inserts and release memory:
				    				  tdpCadreDAO.flushAndclearSession();
				    			  }
					    	 }
							 rs.setResultCode(1);
					         rs.setMessage("success");
				        }
				    });
				}
	   		
			}catch(Exception e){
				LOG.error("Exception raised at saveInToHourRegInfoTempByToday", e);
				 rs.setResultCode(0);
			     rs.setMessage("failure");
			}
	   	  return rs;
	   }
     /** 5)
   	 *  @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
   	 *  Pushing tdpcadre data hour wise overall(For All Dates)
   	 *  @since 03-NOVEMBER-2016 
   	 */
       public ResultStatus pushHourWiseTdpCadreDetailsByOverall(){
		   
			ResultStatus rs = null;
			try {
				
				List<CadreDateVO> list = getHourWiseTdpCadreDetailsByOverall();
				if(list != null && list.size() > 0){
					
					long startTime = System.currentTimeMillis();
					rs = saveInToHourRegInfoTempByOverall(list);
					Double tempSeconds = (System.currentTimeMillis() - startTime)/1000.0;
					
			        if(rs != null){
			        	
			        	long intermediateStartTime = System.currentTimeMillis();
			        	int deletedRecords =  tdpCadreHourRegInfoDAO.deleteAllRecords(null);
			        	int insertedRecordsCount = tdpCadreHourRegInfoDAO.insertCadreDataHourWiseOverall();
			        	Double interSeconds = (System.currentTimeMillis() - intermediateStartTime)/1000.0;
			        	
			        	LOG.error("All Dates hour wise tdpcadre temp insert time is : " + tempSeconds  + " seconds && All Dates hour wise tdpcadre temp insert time is :" +interSeconds + " seconds" );
			        }
				}
			} catch (Exception e) {
				LOG.error("Exception raised at pushHourWiseTdpCadreDetailsByOverall", e);
			}
			return rs;
		}
       
       public List<CadreDateVO> getHourWiseTdpCadreDetailsByOverall(){
    	   
    	   List<CadreDateVO> finalList = new ArrayList<CadreDateVO>(0);
    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	 try{
 		     
    		 List<Object[]> apList = tdpCadreHourRegInfoDAO.getDateHourWiseTdpCadreCount(null,1L);
    		 if(apList != null && apList.size() > 0){
    			 for(Object[] obj : apList){
    				CadreDateVO hourVO = new CadreDateVO();
		   		    	
	   		    	hourVO.setStateId(1L);
	   		    	if(obj[0]!=null){
	   		    		hourVO.setDateStr(sdf.format((Date)obj[0]));
	   		    	}
	   		    	hourVO.setDayHour(obj[1]!=null ? (Integer)obj[1] : 0);
	   		    	hourVO.setRegCount(obj[2]!=null ? (Long)obj[2] : 0l);
	   		    	hourVO.setUsersCount(obj[3]!=null ? (Long)obj[3] : 0l);
	   		    	
	   		    	finalList.add(hourVO);
    			 }
    		 }
    		 
    		 
    		 List<Object[]> tsList = tdpCadreHourRegInfoDAO.getDateHourWiseTdpCadreCount(null,36L);
    		 if(tsList != null && tsList.size() > 0){
    			 for(Object[] obj : tsList){
    				CadreDateVO hourVO = new CadreDateVO();
		   		    	
	   		    	hourVO.setStateId(36L);
	   		    	if(obj[0]!=null){
	   		    		hourVO.setDateStr(sdf.format((Date)obj[0]));
	   		    	}
	   		    	hourVO.setDayHour(obj[1]!=null ? (Integer)obj[1] : 0);
	   		    	hourVO.setRegCount(obj[2]!=null ? (Long)obj[2] : 0l);
	   		    	hourVO.setUsersCount(obj[3]!=null ? (Long)obj[3] : 0l);
	   		    	
	   		    	finalList.add(hourVO);
    			 }
    		 }
    		 
		 }catch(Exception e){
			 LOG.error("Exception raised at getHourWiseTdpCadreDetailsByOverall", e);
		 } 
    	 return finalList;
       }
       
       public ResultStatus saveInToHourRegInfoTempByOverall(final List<CadreDateVO> finalList){
    	    final  ResultStatus rs = new ResultStatus();
		   	try{
					if( finalList != null && finalList.size() > 0)
					{
						transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
						
								int deletedRecords = tdpCadreHourRegInfoTemp1DAO.deleteAllRecords();
								//int count = tdpCadreHourRegInfoTemp1DAO.setPrimaryKeyAutoIncrementToOne();
								
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date currentTime = new DateUtilService().getCurrentDateAndTime();
								
								int i= 0;
								for (CadreDateVO dataVO : finalList )
								{
						    		  i = i +1;
						    		  if(dataVO != null){
						    			  TdpCadreHourRegInfoTemp1 info = new TdpCadreHourRegInfoTemp1();
						    			  info.setStateId(dataVO.getStateId());
						    			  if(dataVO.getDateStr() != null){
						    				  try {
												info.setSurveyDate(sdf.parse(dataVO.getDateStr()));
											} catch (ParseException e) {
												e.printStackTrace();
											}
						    			  }
						    			  info.setHour(dataVO.getDayHour().longValue());
						    			  if(dataVO.getRegCount() != null && dataVO.getRegCount() > 0l){
						    				  info.setTotalRegistrations(dataVO.getRegCount());
						    			  }
						    			  if(dataVO.getUsersCount() != null && dataVO.getUsersCount() > 0){
						    				  info.setCadreSurveyUsers(dataVO.getUsersCount());
						    			  }
						    			  info.setInsertedTime(currentTime);
						    			  
						    			  tdpCadreHourRegInfoTemp1DAO.save(info);
						    		  }
					    			  if( i % 100 == 0 ) { 
					    			       //flush a batch of inserts and release memory:
					    				  tdpCadreDAO.flushAndclearSession();
					    			  }
						    	 }
								 rs.setResultCode(1);
						         rs.setMessage("success");
					        }
					    });
					}
		   		
				}catch(Exception e){
					LOG.error("Exception raised at saveInToHourRegInfoTempByOverall", e);
					 rs.setResultCode(0);
				     rs.setMessage("failure");
				}
		   	  return rs;
		   }
      
       //USER WISE HOUR WISE INTERMEDIATE PUSHING.
     /** 6)
   	 *  @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
   	 *  Pushing tdpcadre data hour wise for Tab Users For Today.
   	 *  @since 03-NOVEMBER-2016 
   	 */ 
       
     public ResultStatus pushTdpCadreDataHourWiseForTabUsersByToday(){
    	  
    	 ResultStatus rs = null;
    	 try{
    		 
    		   //get current hour from current time.
 		       Date currentTime = new DateUtilService().getCurrentDateAndTime();
 		       Calendar calendar = Calendar.getInstance();
 		       calendar.setTime(currentTime);
 		       Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
 		    
 			  final List<CadreDateVO> finalList = getTdpCadreDataHourWiseForTabUsersByToday(currentTime,hour);
 			  
 			  long startTime = System.currentTimeMillis();
 			  rs = pushUserWiseHoursDataToTempTable(finalList);
 			  Double tempSeconds = (System.currentTimeMillis() - startTime)/1000.0;
 			 
 			  if(rs != null){
 				 long intermediateStartTime = System.currentTimeMillis();
 				  
 				 int deletedRecords =  tdpCadreUserHourRegInfoDAO.deleteAllRecords(currentTime);
 				 int insertedRecordsCount = tdpCadreUserHourRegInfoDAO.insertCadreDataByUserWiseHourWise();
 				 
 				 Double interSeconds = (System.currentTimeMillis() - intermediateStartTime)/1000.0;
 				 LOG.error("Today User Wise hour wise tdpcadre temp insert time is : " + tempSeconds  + " seconds && Today User Wise hour wise tdpcadre intermediate insert time is :" +interSeconds + " seconds" );
 			  }
    		 
		}catch(Exception e){
			 LOG.error("Exception occured in pushTdpCadreDataHourWiseForTabUsersByToday() Method - ",e);
		}
    	 return rs;
     }
      public ResultStatus pushUserWiseHoursDataToTempTable(final List<CadreDateVO> finalList){
    	  final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	  final  ResultStatus rs = new ResultStatus();
    	  try{
    		
    		  if(finalList != null && finalList.size() > 0){
    			 
    				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				        		
				         Date time = new DateUtilService().getCurrentDateAndTime();
				         
				         int deletedRecords = tdpCadreUserHourRegInfoTempDAO.deleteAllRecords();
				         //int count = tdpCadreUserHourRegInfoTempDAO.setPrimaryKeyAutoIncrementToOne();
				         
				         int i= 0;	    
					     for(CadreDateVO VO  : finalList ){
					    	 i = i + 1;	    
					    	 TdpCadreUserHourRegInfoTemp info = new TdpCadreUserHourRegInfoTemp();
					    	 info.setCadreSurveyUserId(VO.getCadreSurveyUserId());
					    	 info.setTabUserInfoId(VO.getTabUserInfoId());
					    	 if(VO.getDateStr() != null){
					    		 try {
									info.setSurveyDate(sdf.parse(VO.getDateStr()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
					    	 }
					    	 info.setHour(VO.getHour());
					    	 if(VO.getRegCount() != null && VO.getRegCount() > 0l){
					    		 info.setRegCount(VO.getRegCount());
					    	 }
					    	 info.setInsertedTime(time);
					    	 
					    	 tdpCadreUserHourRegInfoTempDAO.save(info);
					    	 
					    	 if( i % 100 == 0 ) { 
	 		    			     //flush a batch of inserts and release memory,
	 		    				 tdpCadreDAO.flushAndclearSession();
	 		    			 }
					     }	   
					          rs.setResultCode(1);
					          rs.setMessage("success");
				         }
				    });
    		 }
    		  
		  }catch(Exception e){
			  LOG.error("Exception occured in pushUserWiseHoursDataToTempTable() Method - ",e);
			  rs.setResultCode(0);
		      rs.setMessage("failure");
		  }
    	  return rs;
      }
       
       public List<CadreDateVO> getTdpCadreDataHourWiseForTabUsersByToday(Date currentTime,Integer hour){
    	   List<CadreDateVO> finalList = null;
    	   try{
    		    
    		    List<Object[]> list = tdpCadreUserHourRegInfoDAO.getTdpCadreDataHourWiseForTabUsers(currentTime,hour);//5500
    		    if(list != null && list.size() > 0)
    		    {	
    		    	finalList = new ArrayList<CadreDateVO>();
    		    	
    		    	for(Object[] obj : list)
    		    	{
    		    		if(obj[2] != null && obj[3]!=null )
    		    		{	
    		    			CadreDateVO dataVO = new CadreDateVO();
    		    			dataVO.setDateStr(obj[0] != null ? obj[0].toString() : null);
    		    			dataVO.setHour(obj[0]!=null ? ((Integer)obj[1]).longValue() : null);
    		    			dataVO.setCadreSurveyUserId(obj[2]!=null?(Long)obj[2]:null);
    		    			dataVO.setTabUserInfoId(obj[3]!=null?(Long)obj[3]:null);
    		    			dataVO.setRegCount(obj[4]!=null?(Long)obj[4]:0l);
    		    			
    		    			finalList.add(dataVO);
    		    		}
    		    	}
    		    } 
    		    
		   }catch(Exception e){
			   LOG.error("Exception occured in getTdpCadreDataHourWiseForTabUsers() Method - ",e);
		   }
    	   return finalList;
       }
       
       /** 7)
      	 *  @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
      	 *  Pushing tdpcadre data hour wise for Tab Users overall (For All Dates)
      	 *  @since 03-NOVEMBER-2016 //tdpCadreUserHourRegInfoTemp1DAO
      	 */
       
       public ResultStatus pushTdpCadreDataHourWiseForTabUsersByOverall(){
     	  ResultStatus rs = null;
     	 try{ 
  		    
  			  List<CadreDateVO> finalList = getTdpCadreDataHourWiseForTabUsersByOverall();
  			  
  			  long startTime = System.currentTimeMillis();
  			  rs = pushUserWiseHoursDataToTempTableOverall(finalList);
  			  Double tempSeconds = (System.currentTimeMillis() - startTime)/1000.0;
  			  
  			  if(rs != null){
  				 long intermediateStartTime = System.currentTimeMillis();
  	  			 int deletedRecords =  tdpCadreUserHourRegInfoDAO.deleteAllRecords(null);
  				 int insertedRecordsCount = tdpCadreUserHourRegInfoDAO.insertCadreDataByUserWiseHourWiseOverall();
  				 Double interSeconds = (System.currentTimeMillis() - intermediateStartTime)/1000.0;
  				 LOG.error("All Dates User Wise hour wise tdpcadre temp insert time is : " + tempSeconds  + " seconds && All Dates User Wise hour wise tdpcadre intermediate insert time is :" +interSeconds + " seconds" );
  			  }
			 
 		}catch(Exception e){
 			 LOG.error("Exception occured in pushTdpCadreDataHourWiseForTabUsersByOverall() Method - ",e);
 		}
     	 return rs;
      }
       
       public List<CadreDateVO> getTdpCadreDataHourWiseForTabUsersByOverall(){
    	   List<CadreDateVO> finalList = null;
    	   try{
    		    
    		    List<Object[]> list = tdpCadreUserHourRegInfoDAO.getTdpCadreDataHourWiseForTabUsersOverall();
    		    if(list != null && list.size() > 0)
    		    {	
    		    	finalList = new ArrayList<CadreDateVO>();
    		    	
    		    	for(Object[] obj : list)
    		    	{
    		    		if(obj[2] != null && obj[3]!=null )
    		    		{	
    		    			CadreDateVO dataVO = new CadreDateVO();
    		    			dataVO.setDateStr(obj[0] != null ? obj[0].toString() : null);
    		    			dataVO.setHour(obj[0]!=null ? ((Integer)obj[1]).longValue() : null);
    		    			dataVO.setCadreSurveyUserId(obj[2]!=null?(Long)obj[2]:null);
    		    			dataVO.setTabUserInfoId(obj[3]!=null?(Long)obj[3]:null);
    		    			dataVO.setRegCount(obj[4]!=null?(Long)obj[4]:0l);
    		    			
    		    			finalList.add(dataVO);
    		    		}
    		    	}
    		    } 
    		    
		   }catch(Exception e){
			   LOG.error("Exception occured in getTdpCadreDataHourWiseForTabUsersByOverall() Method - ",e);
		   }
    	   return finalList;
       }
       
       public ResultStatus pushUserWiseHoursDataToTempTableOverall(final List<CadreDateVO> finalList){
    	   
     	  final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     	  final  ResultStatus rs = new ResultStatus();
     	  try{
     		
     		 if(finalList != null && finalList.size() > 0){
     			 
  				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				        		
				         Date time = new DateUtilService().getCurrentDateAndTime();
				         
				         int deletedRecords = tdpCadreUserHourRegInfoTemp1DAO.deleteAllRecords();
 				         //int count = tdpCadreUserHourRegInfoTemp1DAO.setPrimaryKeyAutoIncrementToOne();
 				         
				         int i= 0;
					     for(CadreDateVO VO  : finalList ){
					    	 i = i + 1;	    
					    	 
					    	 TdpCadreUserHourRegInfoTemp1 info = new TdpCadreUserHourRegInfoTemp1();
					    	 info.setCadreSurveyUserId(VO.getCadreSurveyUserId());
					    	 info.setTabUserInfoId(VO.getTabUserInfoId());
					    	 if(VO.getDateStr() != null){
					    		 try {
									info.setSurveyDate(sdf.parse(VO.getDateStr()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
					    	 }
					    	 info.setHour(VO.getHour());
					    	 if(VO.getRegCount() != null && VO.getRegCount() > 0l){
					    		 info.setRegCount(VO.getRegCount());
					    	 }
					    	 info.setInsertedTime(time);
					    	 
					    	 tdpCadreUserHourRegInfoTemp1DAO.save(info);
					    	 
					    	if( i % 100 == 0 ) { 
		    			       //flush a batch of inserts and release memory:
		    				  tdpCadreDAO.flushAndclearSession();
		    			    }
					    	
					     }	   
					          rs.setResultCode(1);
					          rs.setMessage("success");
				         }
				    });
  		 }
     		  
 		  }catch(Exception e){
 			  LOG.error("Exception occured in pushUserWiseHoursDataToTempTableOverall() Method - ",e);
 			  rs.setResultCode(0);
 		      rs.setMessage("failure");
 		  }
     	  return rs;
       } 
       
       
}