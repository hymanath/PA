package com.itgrids.partyanalyst.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.StatesEventVO;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService1;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MahanaduDashBoardService1 implements IMahanaduDashBoardService1{
	
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardService1.class);
	
	DecimalFormat decimalFormat = new DecimalFormat("#.##");
	
	private IEventAttendeeDAO eventAttendeeDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	
	public IEventAttendeeDAO getEventAttendeeDAO() {
		return eventAttendeeDAO;
	}
	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}
	public IEventInviteeDAO getEventInviteeDAO() {
		return eventInviteeDAO;
	}
	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	 /**
	   *   @author    : Sreedhar
	   *   Description:This Service is used to get the Location Wise event attendees and event invitees count
	   *   inputs: startDate,endDate,parenteventId,subEventIds,reportLevelId,stateIds,stateType
	   *   output: List<MahanaduEventVO>
	   *   
	  */	  
	public List<MahanaduEventVO> LocationWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds,Long reportLevelId,List<Long> stateIds,String stateType){
		
		List<MahanaduEventVO>  resultList = new ArrayList<MahanaduEventVO>();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd yyyy hh:mm a");
		try{
				Date eventStrDate = null;
				Date eventEndDate = null;
				if(startDate != null && !startDate.isEmpty()){
				 eventStrDate = format.parse(startDate);
				}
				if(endDate != null && !endDate.isEmpty()){
				 eventEndDate = format.parse(endDate); 
				}
				
				 String locationType = "";
				 if(reportLevelId == 2l){
					 locationType = "State"; 
				 }
				 else if(reportLevelId == 3l){
					 locationType = "District";
				 } 
				 else if(reportLevelId == 4l){
					 locationType = "Constituency"; 
				 }
				
				 List<Date>  betweenDates= commonMethodsUtilService.getBetweenDates(eventStrDate,eventEndDate);
				 
				 Map<Long,MahanaduEventVO>  finalMap = new LinkedHashMap<Long,MahanaduEventVO>();
				 getAttendeesAndinviteesCount(locationType,eventStrDate,eventEndDate,parenteventId,subEventIds,reportLevelId,stateIds,stateType,finalMap,betweenDates);
				
				 
				 if( finalMap!= null && finalMap.size() > 0){
					 for (Map.Entry<Long, MahanaduEventVO> entry : finalMap.entrySet()) {
						 if (entry.getValue().getSubMap() != null){
							 entry.getValue().getSubList().addAll(entry.getValue().getSubMap().values());
							 entry.getValue().getSubMap().clear();
						 } 
					 }
					  resultList.addAll(finalMap.values());
				 }
				 
				 //SET CURRENT DATE AND TIME.
				 if(resultList != null && resultList.size() > 0){
					 resultList.get(0).setLastUpdatedDate(sdf1.format(new DateUtilService().getCurrentDateAndTime()));
				 }else{
					 MahanaduEventVO vo = new MahanaduEventVO();
					 vo.setLocationName("NO DATA");
					 vo.setLastUpdatedDate(sdf1.format(new DateUtilService().getCurrentDateAndTime()));
					 resultList.add(vo);
				 }
				 
		}catch(Exception e){
			Log.error("Exception rised in LocationWiseEventAttendeeCounts()",e); 
		}
		return resultList;
	}
	public void getAttendeesAndinviteesCount(String locationType,Date eventStrDate,Date eventEndDate,Long parenteventId,List<Long> subEventIds,Long reportLevelId,List<Long> stateIds,String stateType,Map<Long,MahanaduEventVO>  finalMap,List<Date> betweenDates){
		
		Set<Long> locationIds = new HashSet<Long>();
		try{
			
			if(stateType.equalsIgnoreCase("ALL")){
				
				 StringBuilder locationQueryString = new StringBuilder();
				 locationQueryString.append(" and model.tdpCadre.userAddress.state.stateId is not null ");
				 boolean  isDataAvailable = getAttendeeAndinviteeCounts(locationType,eventStrDate,eventEndDate,subEventIds,locationQueryString.toString(),finalMap,locationIds,betweenDates);
				 if(isDataAvailable){
					 getAttendeeAndinviteeCountsDateWise(locationType,eventStrDate,eventEndDate,subEventIds,locationQueryString.toString(),finalMap); 
				 }
			 }else{
				 
				 if( stateIds != null && stateIds.size() > 0){
					    
					   List<Long> apAndTsStateIds = new ArrayList<Long>();
					   apAndTsStateIds.add(1l);
					   apAndTsStateIds.add(36l);
					 
					    boolean apTsStatesExist = false;
					    boolean otherStatesExist = false;
					 
					    StringBuilder apTsLocationQueryString = new StringBuilder();
						if(stateIds.containsAll(apAndTsStateIds)){
							apTsStatesExist = true;
							apTsLocationQueryString.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 1 and 23 ");	
						}else if( stateIds.contains(1l) ){
							apTsStatesExist = true;
							apTsLocationQueryString.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23 ");
						}else if( stateIds.contains(36l) ){
							apTsStatesExist = true;
							apTsLocationQueryString.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10 ");
						}
						
						StringBuilder otherStatesLocationQueryString = new StringBuilder();
						if(stateIds.contains(0l)){
							otherStatesExist = true;
							otherStatesLocationQueryString.append(" and model.tdpCadre.userAddress.state.stateId !=1 and model.tdpCadre.userAddress.state.stateId is not null ");
						}
						
						
						if(apTsStatesExist){
							boolean  isDataAvailable = getAttendeeAndinviteeCounts(locationType,eventStrDate,eventEndDate,subEventIds,apTsLocationQueryString.toString(),finalMap,locationIds,betweenDates);
							if(isDataAvailable){
								getAttendeeAndinviteeCountsDateWise(locationType,eventStrDate,eventEndDate,subEventIds,apTsLocationQueryString.toString(),finalMap);
							}
						}
						if(otherStatesExist){
							boolean  isDataAvailable = getAttendeeAndinviteeCounts(locationType,eventStrDate,eventEndDate,subEventIds,otherStatesLocationQueryString.toString(),finalMap,locationIds,betweenDates);
							if(isDataAvailable){
								getAttendeeAndinviteeCountsDateWise(locationType,eventStrDate,eventEndDate,subEventIds,otherStatesLocationQueryString.toString(),finalMap);
							}
						}
				}
				 
			 }
			
			
			if(locationIds != null && locationIds.size() > 0){
				 List<Object[]> inviteesList = eventInviteeDAO.getEventInviteesCountByLocation(locationType,locationIds,parenteventId);
				 if( inviteesList!= null && inviteesList.size() > 0){
					 for( Object[] obj : inviteesList){
						 MahanaduEventVO  locationVO= finalMap.get((Long)obj[0]);
						 if(locationVO != null){
							 locationVO.setInviteesCalled(obj[1]!=null?(Long)obj[1]:0l);
						 }
					 }
				 }
			 }
			 
			 if( reportLevelId.longValue() == 4l && locationIds!=null && locationIds.size() > 0){
				 
				 List<Long> constIds = new ArrayList<Long>(locationIds);
				 
				 List<Object[]> constNoList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(constIds);
				 if( constNoList != null && constNoList.size() > 0){
					 for(Object[] obj : constNoList){
						 MahanaduEventVO  locationVO= finalMap.get((Long)obj[0]);
						 if(locationVO != null){
							 locationVO.setLocationNo(obj[1]!=null?(Long)obj[1]:0l);
						 }
					 }
				 }
			 }
			
		}catch(Exception e){
			Log.error("Exception rised in getAttendeesAndinviteesCount()",e);
		}
		
	}
	
	public boolean getAttendeeAndinviteeCounts(String locationType,Date eventStrDate,Date eventEndDate,List<Long> subEventIds,String locationQueryString, Map<Long,MahanaduEventVO>  finalMap,Set<Long> locationIds,List<Date> betweenDates){
		 
		 boolean isDataAvailable = false;
		 List<Object[]> totalAttendeeList = eventAttendeeDAO.locationWiseEventAttendeeCountsQuery(locationType,"attendee",eventStrDate,eventEndDate,subEventIds,locationQueryString);
		 
		 if(totalAttendeeList!=null && totalAttendeeList.size() > 0){
			 
			 isDataAvailable = true;
			 List<Object[]> totalInviteeList = eventAttendeeDAO.locationWiseEventAttendeeCountsQuery(locationType,"invitee",eventStrDate,eventEndDate,subEventIds,locationQueryString);
			 
			 setDataToMap(totalAttendeeList,finalMap,"attendee",locationIds,betweenDates,locationType);
			 setDataToMap(totalInviteeList,finalMap,"invitee",locationIds,betweenDates,locationType);
		 }
		 return  isDataAvailable;
	}
	
	public void getAttendeeAndinviteeCountsDateWise(String locationType,Date eventStrDate,Date eventEndDate,List<Long> subEventIds,String locationQueryString, Map<Long,MahanaduEventVO>  finalMap){
		 
		 List<Object[]> dateWiseAttendeeList = eventAttendeeDAO.locationWiseEventAttendeeCountsByDateQuery(locationType,"attendee",eventStrDate,eventEndDate,subEventIds,locationQueryString);
		 
		 if(dateWiseAttendeeList!=null && dateWiseAttendeeList.size()>0){
			 
			 List<Object[]> dateWiseInviteeList = eventAttendeeDAO.locationWiseEventAttendeeCountsByDateQuery(locationType,"invitee",eventStrDate,eventEndDate,subEventIds,locationQueryString);
			 
			 setDateDataToMap(dateWiseAttendeeList,finalMap,"attendee");
			 setDateDataToMap(dateWiseInviteeList,finalMap,"invitee");
		 }
	}
	
	public void setDateDataToMap(List<Object[]> list,Map<Long,MahanaduEventVO>  finalMap,String type){
		
		 try{
			
			   if( list != null && list.size() >0 )
			   {   
				   for(Object[] obj : list)
				   {
					   MahanaduEventVO distVO = finalMap.get((Long)obj[0]);
					   
					   if( distVO != null)
					   {   
							   String dateStr = obj[2]!=null?obj[2].toString():"";
							   MahanaduEventVO dateVO = distVO.getSubMap().get(dateStr);
							   if(dateVO!=null)
							   {  
								   dateVO.setDataExist(true);
								   
								   if(type.equalsIgnoreCase("attendee"))
								   {
									   dateVO.setAttendees(obj[3]!=null ?(Long)obj[3]:0l );
									   dateVO.setNonInvitees(obj[3]!=null ?(Long)obj[3]:0l);	
								   }
								   if(type.equalsIgnoreCase("invitee"))
								   {
								   		dateVO.setInvitees(obj[3]!=null ?(Long)obj[3]:0l);
								   		dateVO.setNonInvitees(dateVO.getAttendees() - (obj[3]!=null ?(Long)obj[3]:0l));	
								   }	
							   }
					    }
				    }
			   }
			 
		 }catch(Exception e) {
			 Log.error("Exception rised in setDateDataToMap()",e); 
		}
	 }
	
	public void setDataToMap(List<Object[]> list,Map<Long,MahanaduEventVO>  finalMap,String type,Set<Long> locationIds,List<Date> betweenDates,String locationType){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			
			   if( list != null && list.size() >0 ){
				   
				   for(Object[] obj : list){
					   
					   boolean isDistrictexist = true;
					   MahanaduEventVO distVO = finalMap.get((Long)obj[0]);
					   
					   if( distVO == null){
						   
						   isDistrictexist = false;
						   distVO = new MahanaduEventVO();
						   distVO.setId(obj[0]!=null ? (Long)obj[0]:0l);
						   distVO.setName( obj[1]!=null ? obj[1].toString() :"");
						   
						   if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
							   if(type.equalsIgnoreCase("attendee")){
								   distVO.setDistrictName(obj[3]!=null ?obj[3].toString():"");
							   }
						   }
						   if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
							   if(type.equalsIgnoreCase("attendee")){
								   distVO.setLocationNo(obj[0]!=null ?(Long)obj[0]:0l);
							   }
						   }
						   
						   if(betweenDates != null && betweenDates.size() > 0){
							   for(int i=0;i<betweenDates.size();i++){
								   MahanaduEventVO dateVO = new MahanaduEventVO();
								   dateVO.setDateStr(betweenDates.get(i)!=null?sdf.format(betweenDates.get(i)):"");
								   dateVO.setDataExist(false);
								   int dayCount = i+1;
								   dateVO.setName("Day"+dayCount);
								   if(distVO.getSubMap() == null){
									   distVO.setSubMap(new LinkedHashMap<String, MahanaduEventVO>());
								   }
								   distVO.getSubMap().put(dateVO.getDateStr(),dateVO);
							   }
						   }
					   }
					   
					   if(type.equalsIgnoreCase("attendee")){
						   distVO.setAttendees(obj[2]!=null ?(Long)obj[2]:0l );
						   distVO.setNonInvitees(obj[2]!=null ?(Long)obj[2]:0l);	
					   }
					   	if(type.equalsIgnoreCase("invitee")){
					   		distVO.setInvitees(obj[2]!=null ?(Long)obj[2]:0l);
					   	    distVO.setNonInvitees(distVO.getAttendees() - (obj[2]!=null ?(Long)obj[2]:0l));	
					   }		
					   	
					   	if(!isDistrictexist){
					   		finalMap.put((Long)obj[0], distVO);
					   	} 
					   	
					    //locationIds
						 locationIds.add( (Long)obj[0] );
				   }
				   
			   }
			 
			 
		 }catch(Exception e) {
			 Log.error("Exception rised in setDataToMap()",e); 
		}
	 }
	
	
		/**
		   *   @author    : Sreedhar
		   *   Description:This Service is used to get the State Wise event attendees and event invitees count By Days.
		   *   inputs: startDate,endDate,parenteventId,subEventIds
		   *   output: StatesEventVO
		   *   
		  */
	
		public StatesEventVO stateWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds){
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				
				StatesEventVO finalVO = new StatesEventVO();
				
				try{
					
					Date eventStrDate = null;
					Date eventEndDate = null;
					
					if(startDate != null && !startDate.isEmpty()){
					 eventStrDate = format.parse(startDate);
					}
					
					if(endDate != null && !endDate.isEmpty()){
					 eventEndDate = format.parse(endDate); 
					}
					
					List<Date>  betweenDates= new CommonMethodsUtilService().getBetweenDates(eventStrDate,eventEndDate);
					
					//AP  DATA
					StatesEventVO apStateVO = getDataToAState(eventStrDate,eventEndDate,subEventIds,betweenDates,1l,"particular");
					calcLowHighPercantage(apStateVO);
					finalVO.setApStateVO(apStateVO);
					
					
					//TS  DATA
					StatesEventVO tsStateVO = getDataToAState(eventStrDate,eventEndDate,subEventIds,betweenDates,36l,"particular");
					calcLowHighPercantage(tsStateVO);
					finalVO.setTsStateVO(tsStateVO);
					
					
					//Other States DATA
					StatesEventVO otherStateVO = getDataToAState(eventStrDate,eventEndDate,subEventIds,betweenDates,0l,"particular");
					calcLowHighPercantage(otherStateVO);
					finalVO.setTsStateVO(otherStateVO);
					
					
					//OverAll Data
					StatesEventVO allStatesVO = getDataToAState(eventStrDate,eventEndDate,subEventIds,betweenDates,null,"overall");
					calcLowHighPercantage(allStatesVO);
					finalVO.setAllStatesVO(allStatesVO);
					
					
				}catch(Exception e){
					Log.error("Exception rised in stateWiseEventAttendeeCounts()",e); 
				}
				return finalVO;
			}
			
		public StatesEventVO getDataToAState(Date eventStrDate,Date eventEndDate,List<Long> subEventIds,List<Date>  betweenDates,Long stateId,String statesType){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			StatesEventVO stateVO = new StatesEventVO();
			try{
				
				Long attendeedCount = eventAttendeeDAO.stateWiseEventAttendeeCounts("attendee",eventStrDate,eventEndDate,subEventIds,stateId,statesType);
				
				if(attendeedCount != null && attendeedCount > 0l){
					
					stateVO.setAttendees(attendeedCount);
					stateVO.setNonInvitees(attendeedCount);
					
					Long inviteedCount = eventAttendeeDAO.stateWiseEventAttendeeCounts("invitee",eventStrDate,eventEndDate,subEventIds,stateId,statesType);
					if( inviteedCount != null && inviteedCount > 0l){
						stateVO.setInvitees(inviteedCount);
						stateVO.setNonInvitees(attendeedCount - stateVO.getInvitees());
					}
					
					
					//day wise
					if(betweenDates != null && betweenDates.size() > 0){
						   for(int i=0;i<betweenDates.size();i++){
							   StatesEventVO dateVO = new StatesEventVO();
							   dateVO.setDateStr(betweenDates.get(i)!=null?sdf.format(betweenDates.get(i)):"");
							   dateVO.setDataExist(false);
							   int dayCount = i+1;
							   dateVO.setName("Day"+dayCount);
							   if(stateVO.getSubMap() == null){
								   stateVO.setSubMap(new LinkedHashMap<String, StatesEventVO>());
							   }
							   stateVO.getSubMap().put(dateVO.getDateStr(),dateVO);
						   }
					 }
					
					
					List<Object[]> dayWiseAttendeedCountList = eventAttendeeDAO.stateWiseEventAttendeeCountsByDates("attendee",eventStrDate,eventEndDate,subEventIds,stateId,statesType);
					
					if(dayWiseAttendeedCountList != null && dayWiseAttendeedCountList.size() > 0){
						
						List<Object[]> dayWiseInviteeCountList = eventAttendeeDAO.stateWiseEventAttendeeCountsByDates("invitee",eventStrDate,eventEndDate,subEventIds,stateId,statesType);
						
						setDateDataToCorrespondingState(dayWiseAttendeedCountList,stateVO,"attendee");
						setDateDataToCorrespondingState(dayWiseInviteeCountList,stateVO,"invitee");
					}
					
					if(stateVO.getSubMap() != null && stateVO.getSubMap().size() > 0){
						stateVO.setSubList(new ArrayList<StatesEventVO>(stateVO.getSubMap().values()));
						stateVO.getSubMap().clear();
					}
					
				}
			}catch(Exception e){
				Log.error("Exception rised in getDataToAState()",e); 
			}
			return stateVO;
		}
		
		public void setDateDataToCorrespondingState(List<Object[]> list,StatesEventVO stateVO,String type){
			
			 try{
				
				   if( list != null && list.size() >0 )
				   {   
					   for(Object[] obj : list)
					   {  
						   String dateStr = obj[0]!=null?obj[0].toString():"";
						   StatesEventVO dateVO = stateVO.getSubMap().get(dateStr);
						   if(dateVO!=null)
						   {  
							   dateVO.setDataExist(true);
							   
							   if(type.equalsIgnoreCase("attendee"))
							   {
								   dateVO.setAttendees(obj[1]!=null ?(Long)obj[1]:0l );
								   dateVO.setNonInvitees(obj[1]!=null ?(Long)obj[1]:0l);	
							   }
							   if(type.equalsIgnoreCase("invitee"))
							   {
							   		dateVO.setInvitees(obj[1]!=null ?(Long)obj[1]:0l);
							   		dateVO.setNonInvitees(dateVO.getAttendees() - dateVO.getInvitees());	
							   }	
						   }
						    
					    }
				   }
				 
			 }catch(Exception e) {
				 Log.error("Exception rised in setDateDataToCorrespondingState()",e); 
			}
		 }
		public void calcLowHighPercantage(StatesEventVO stateVO){
			
			try{
				
				if( stateVO.getSubList() != null && stateVO.getSubList().size() > 0)
				{	
					for(int i = stateVO.getSubList().size()-1; i >= 1; i--)
					{	
						if (stateVO.getSubList().get(i).isDataExist() && stateVO.getSubList().get(i-1).isDataExist())
						{
							
								Long presentDayCount  = stateVO.getSubList().get(i).getAttendees();
								Long previousDayCount = stateVO.getSubList().get(i-1).getAttendees();
								
								Long totalcount = presentDayCount + previousDayCount;
								
								String presentDayPercantage  = calcPercantage(totalcount,presentDayCount);
								String previousDayPercantage = calcPercantage(totalcount,previousDayCount);
								
								Double present = Double.parseDouble(presentDayPercantage);
								Double previous = Double.parseDouble(previousDayPercantage);
								
								if( present > previous){
									Double percantage = present - previous;
									stateVO.setCalcPercantage(percantage.toString());
									stateVO.setHighOrlow("Higher");
									stateVO.setCalcString(stateVO.getSubList().get(i).getName());
								}else{
									Double percantage = previous - present;
									stateVO.setCalcPercantage(decimalFormat .format(percantage));
									stateVO.setHighOrlow("Lower");
									stateVO.setCalcString(stateVO.getSubList().get(i).getName());
								}
								break;
						}
						
					}
				}
			}catch(Exception e){
				Log.error("Exception rised in calcLowHighPercantage()",e); 
			}
		}
		
		public String calcPercantage(Long totalValue,Long subValue){
		    
		    String percentage=null;
		    if( (totalValue!=null && totalValue>0l) && (subValue!=null && subValue>0l)){
		      
		        Double percent=(Double)(subValue*100.00)/totalValue;
		        percentage=decimalFormat .format(percent);
		    }
		    return percentage;
		 }
		
		
		
  }

