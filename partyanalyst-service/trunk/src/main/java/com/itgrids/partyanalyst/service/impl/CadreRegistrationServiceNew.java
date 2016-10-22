package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadreLocationInfoVO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;
import com.itgrids.partyanalyst.service.ICadreRegistrationServiceNew;
import com.itgrids.partyanalyst.utils.DateUtilService;

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
	
	
	//Business methods
	/**
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
			   
			   long startTime = System.currentTimeMillis();
			   rs =  saveTotalTodayTdpCadreDataToIntermediate(finalList);
			   long stopTime = System.currentTimeMillis();
			   System.out.println("time for saving is : " + (stopTime - startTime)/1000.0 + " seconds");
			   
			   
		  }catch(Exception e){
			  LOG.error("Exception raised in pushTotalTodayTdpCadreDataToIntermediate() in FieldMonitoringService class", e);
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
			   LOG.error("Exception raised in pushTotalTodayTdpCadreDataToIntermediate() in FieldMonitoringService class", e);
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
			   e.printStackTrace();
			   LOG.error("Exception raised in getTdpCadredetailsConstituencyWise() in FieldMonitoringService class", e);
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
			    					 String key = dataVO.getLocationScopeId() + "_" + dataVO.getId();
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
			   LOG.error("Exception raised in setCorrespondingLevelData() in FieldMonitoringService class", e);
		   }
	   }
	   
	   public ResultStatus saveTotalTodayTdpCadreDataToIntermediate(final List<TdpCadreLocationInfoVO> finalList ){
		   
			final ResultStatus rs = new ResultStatus();
			final DateUtilService dateUtilService = new DateUtilService();
			
			try {
				
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			        	
			        	if(finalList != null && finalList.size() > 0){
			        		
			        		int deletedRecords = tdpCadreLocationInfoDAO.deleteAllRecords();
						    int count = tdpCadreLocationInfoDAO.setPrimaryKeyAutoIncrementToOne();
						    
						    Date currentTime = dateUtilService.getCurrentDateAndTime();
						    
						    for(TdpCadreLocationInfoVO dateTypeVO : finalList){
				        		
				        		List<TdpCadreLocationInfoVO> assemblyList = dateTypeVO.getAssemblyList();
				        		savingService(assemblyList);
				        		
				        		List<TdpCadreLocationInfoVO> parliamentList = dateTypeVO.getParliamentList();
				        		savingService(parliamentList);
				        		
				        		List<TdpCadreLocationInfoVO> districtList = dateTypeVO.getDistrictList();
				        		savingService(districtList);
				        		
				        		List<TdpCadreLocationInfoVO> stateList = dateTypeVO.getStateList();
				        		savingService(stateList);
				        		
				        	}
			        	}
			        	   
				          rs.setResultCode(1);
				          rs.setMessage("success");
			         }
			    });
				
			} catch (Exception e) {
				LOG.error("Exception raised at saveTotalTodayTdpCadreDataToIntermediate", e);
				rs.setResultCode(0);
				rs.setMessage("failure");
			}
			return rs;
		}
	   public void savingService(List<TdpCadreLocationInfoVO> list){
	   	
	   	try{
				if( list != null && list.size() > 0)
				{
					for(TdpCadreLocationInfoVO locationVO : list  )
					{
			    		 
			    		 TdpCadreLocationInfo info = new TdpCadreLocationInfo();
		    			  
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
		    			  
		    			  tdpCadreLocationInfoDAO.save(info);
			    	 }
				}
	   		
			}catch(Exception e){
				LOG.error("Exception raised at savingService", e);
				throw new RuntimeException("Exception At savingService..");
			}
	   }
	   
	   //
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
			  LOG.error("Exception raised in getAllAssemblyConstsAndItsParliamentConsts() in FieldMonitoringService class", e);
		  }
		  return acpcMap;
	   }
	   
	   
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
			  LOG.error("Exception raised in getAllASsemblyContsAndItsDistricts() in FieldMonitoringService class", e);
		  }
		  return constDistrictMap;
	   }
	   
	   
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
			  LOG.error("Exception raised in getAllDistrictsAndItsStates() in FieldMonitoringService class", e);
		  }
		  return districtStateMap;
	   }
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
			  LOG.error("Exception raised in getAllASsemblyContsAndItsDistricts() in FieldMonitoringService class", e);
		  }
		  return locationTargetMap;
	   }
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
			   LOG.error("Exception raised in locationWisePreviousCadreCount() in FieldMonitoringService class", e);
		   }
		   return finalMap;
	   }
	   public Double calcPercantage(Long subCount,Long totalCount){
			Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return d;
	  }
	
}
