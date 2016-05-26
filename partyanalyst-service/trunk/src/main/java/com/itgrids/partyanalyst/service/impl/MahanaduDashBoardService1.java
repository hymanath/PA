package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dto.EmailAttributesVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatesEventVO;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService1;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

public class MahanaduDashBoardService1 implements IMahanaduDashBoardService1{
	
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardService1.class);
	
	DecimalFormat decimalFormat = new DecimalFormat("#.##");
	DateUtilService dateUtilService = new DateUtilService();
	SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy hh:mm a");
	
	private IEventAttendeeDAO eventAttendeeDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IMailService mailService;
	private IEventDAO eventDAO;
	private IMahanaduDashBoardService mahanaduDashBoardService;
	
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
	 public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	
	public IEventDAO getEventDAO() {
		return eventDAO;
	}
	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	public IMahanaduDashBoardService getMahanaduDashBoardService() {
		return mahanaduDashBoardService;
	}
	public void setMahanaduDashBoardService(
			IMahanaduDashBoardService mahanaduDashBoardService) {
		this.mahanaduDashBoardService = mahanaduDashBoardService;
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
				 boolean  isDataAvailable = getAttendeeAndinviteeCounts(locationType,eventStrDate,eventEndDate,subEventIds,locationQueryString.toString(),finalMap,locationIds,betweenDates,parenteventId);
				 setTotalDayDataExist(finalMap);
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
							boolean  isDataAvailable = getAttendeeAndinviteeCounts(locationType,eventStrDate,eventEndDate,subEventIds,apTsLocationQueryString.toString(),finalMap,locationIds,betweenDates,parenteventId);
							setTotalDayDataExist(finalMap);
							if(isDataAvailable){
								getAttendeeAndinviteeCountsDateWise(locationType,eventStrDate,eventEndDate,subEventIds,apTsLocationQueryString.toString(),finalMap);
							}
						}
						if(otherStatesExist){
							boolean  isDataAvailable = getAttendeeAndinviteeCounts(locationType,eventStrDate,eventEndDate,subEventIds,otherStatesLocationQueryString.toString(),finalMap,locationIds,betweenDates,parenteventId);
							
							if(!apTsStatesExist){
								setTotalDayDataExist(finalMap);
							}
							
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
	
	public MahanaduEventVO setTotalDayDataExist(Map<Long,MahanaduEventVO>  finalMap){
		
		MahanaduEventVO firstLocationVO = null;
		
		if(finalMap != null && finalMap.size() > 0){
			
			 Map.Entry<Long,MahanaduEventVO> firstEntry=finalMap.entrySet().iterator().next();
			 firstLocationVO = firstEntry.getValue();
			 if( firstLocationVO != null && firstLocationVO.getSubMap()!=null && firstLocationVO.getSubMap().size()>0){
				 for(Map.Entry<String, MahanaduEventVO> entry : firstLocationVO.getSubMap().entrySet()){
					 entry.getValue().setTotalDaydataExist(false);  
				  }
			 }
		}
		return firstLocationVO;
	}
	
	public boolean getAttendeeAndinviteeCounts(String locationType,Date eventStrDate,Date eventEndDate,List<Long> subEventIds,String locationQueryString, Map<Long,MahanaduEventVO>  finalMap,Set<Long> locationIds,List<Date> betweenDates,Long parentEventId){
		 
		 boolean isDataAvailable = false;
		List<Object[]> totalAttendeeList = eventAttendeeDAO.locationWiseEventAttendeeCountsQuery(locationType,"attendee",eventStrDate,eventEndDate,subEventIds,locationQueryString);
		 
		 if(totalAttendeeList!=null && totalAttendeeList.size() > 0){
			 
			 isDataAvailable = true;
			 List<Object[]> totalInviteeList = eventAttendeeDAO.locationWiseEventAttendeeCountsQuery(locationType,"invitee",eventStrDate,eventEndDate,subEventIds,locationQueryString);
			 
			 setDataToMap(totalAttendeeList,finalMap,"attendee",locationIds,betweenDates,locationType);
			 setDataToMap(totalInviteeList,finalMap,"invitee",locationIds,betweenDates,locationType);
			 
			 Long totalAttended = eventAttendeeDAO.getUniqueVisitorsAttendedCount(parentEventId,eventStrDate,eventEndDate,subEventIds);
		     calCulatinginviteeNonInviteePercantage(finalMap,totalAttended);
		 }
		 return  isDataAvailable;
	}
	
	public void calCulatinginviteeNonInviteePercantage(Map<Long,MahanaduEventVO> finalMap,Long totalAttended){
	    
	    if( finalMap!= null && finalMap.size() > 0){
	       for (Map.Entry<Long, MahanaduEventVO> entry : finalMap.entrySet()) {
	        
	         entry.getValue().setAttendeePercantage(calcPercantage(totalAttended, entry.getValue().getAttendees()));
	         entry.getValue().setInviteePercantage(calcPercantage(entry.getValue().getAttendees(), entry.getValue().getInvitees()));
	         entry.getValue().setNonInviteePercantage(calcPercantage(entry.getValue().getAttendees(), entry.getValue().getNonInvitees()));
	       }
	     }
	    
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
									   
									   //set day data exist.
									   if( dateVO.getAttendees() != null && dateVO.getAttendees().longValue() > 0l){
										  finalMap.entrySet().iterator().next().getValue().getSubMap().get(dateStr).setTotalDaydataExist(true);
									   }
									  
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
	
		public StatesEventVO stateWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds,boolean forPdf){
				
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
					
					List<Date>  betweenDates= commonMethodsUtilService.getBetweenDates(eventStrDate,eventEndDate);
					
					if(forPdf){
						  
						//OverAll Data
						StatesEventVO allStatesVO = getDataToAState(eventStrDate,eventEndDate,subEventIds,betweenDates,null,"overall");
						calcLowHighPercantage(allStatesVO);
						finalVO.setAllStatesVO(allStatesVO);
						
					}else{
						
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
						finalVO.setOtherStatesVO(otherStateVO);
						
						
						//OverAll Data
						StatesEventVO allStatesVO = getDataToAState(eventStrDate,eventEndDate,subEventIds,betweenDates,null,"overall");
						calcLowHighPercantage(allStatesVO);
						finalVO.setAllStatesVO(allStatesVO);
					}
					
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
									stateVO.setCalcPercantage(decimalFormat .format(percantage));
									stateVO.setHighOrlow("Higher");
									stateVO.setDateString1(stateVO.getSubList().get(i).getName());
									stateVO.setDateString2(stateVO.getSubList().get(i-1).getName());
								}else{
									Double percantage = previous - present;
									stateVO.setCalcPercantage(decimalFormat .format(percantage));
									stateVO.setHighOrlow("Lower");
									stateVO.setDateString1(stateVO.getSubList().get(i).getName());
									stateVO.setDateString2(stateVO.getSubList().get(i-1).getName());
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
		
		
		
		/**
		   *   @author    : Sreedhar
		   *   Description:This Service is used to send the mail with attachments using java mail api 
		   *   inputs: emailAttributesVO
		   *   output: ResultStatus
		   *   
		  */
		public ResultStatus sendEmailWithAttachment(EmailAttributesVO emailAttributesVO)
		{
			
			ResultStatus resultStatus = new ResultStatus();
			String host = IConstants.DEFAULT_MAIL_SERVER;
			try{
				
				if(emailAttributesVO.getEmailIds() == null || emailAttributesVO.getEmailIds().size() == 0)
				{
					LOG.warn("Empty Mailing List - Please Check Once");
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
				}
				
				Session session = mailService.getSessionObject(host);
				
				if(session == null)
				{
					LOG.error("MimeMessage Object is Not Created");
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
				}
				
				for(String email : emailAttributesVO.getEmailIds())
				{
				  try{
					  
					 MimeMessage message = new MimeMessage(session);
					
					 message.setFrom(new InternetAddress(IConstants.LOCALFROMEMAILID));
					 message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
					 message.setHeader("Return-Path", IConstants.LOCALFROMEMAILID);
					 message.setSentDate(dateUtilService.getCurrentDateAndTime());
					 message.setSubject(emailAttributesVO.getSubject());
					
					 Multipart multipart = new MimeMultipart();
	        		 
		        	 BodyPart htmlPart = new MimeBodyPart();
		        	 String msgText="Please Find The Attached  Pdf Document For Mahanadu 2016 Event Dashboard on "+emailAttributesVO.getTime() ;
		        	 //htmlPart.setContent(getContent(fileNamesVO.getImages()),"text/html");
		             htmlPart.setContent(emailAttributesVO.getBodyText(),"text/html");
		        	 multipart.addBodyPart(htmlPart);
		        	 
		        	 //attachments
		        	 for(int i=0; i<emailAttributesVO.getPdfNames().size();i++){
		        		 
		        		 String pdfFileName = emailAttributesVO.getPdfNames().get(i);
		        		 String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL + "images" + "/" + IConstants.MAHANADU_IMAGES_2016 + "/" + emailAttributesVO.getDay();
		        		 String pdfPath = staticPath + "/"+ pdfFileName;
		        		 
		        		 DataSource source = new FileDataSource(pdfPath);
			        	 BodyPart  attachment  = new MimeBodyPart();
			        	 attachment.setDataHandler(new DataHandler(source));
			        	 if(i==0){
			        		 attachment.setFileName(emailAttributesVO.getTime()+".pdf");	 
			        	 }else{
			        		 attachment.setFileName(emailAttributesVO.getTime()+" EntryExitDashboard.pdf");
			        	 }
			             
			             multipart.addBodyPart(attachment);
		        	 }
		        		
		        	 message.setContent(multipart);
				    
				    if(host.equalsIgnoreCase(IConstants.LOCALHOST))
					{
				    	Transport transport = session.getTransport("smtp");
			            transport.connect(IConstants.HOST,IConstants.LOCALFROMEMAILID,IConstants.PASSWORD);
			            transport.sendMessage(message, message.getAllRecipients());
			            transport.close();
					}
				    else{
				    	Transport.send(message);
				    }
				    
				    resultStatus.setMessage(IConstants.SUCCESS);
				    resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  }catch(Exception e){
					  LOG.error("Exception in sending mail : ",e);
					  resultStatus.setMessage(IConstants.FAILURE);
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  }
				}
				return resultStatus;
			}catch (Exception e) {
				resultStatus.setExceptionEncountered(e);
				resultStatus.setExceptionMsg(e.getMessage());
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
		
		public String getContent(List<String> images){
			
			String content = "";
			for(String img :images){
				
				String imgPath = IConstants.MAHANADU_IMAGES_RETRIEVE_PATH+"/"+img;
				
				content = content + "<img src='"+imgPath+"'  width='100%'/><br/> ";
			}
			return  content;
		}
		
		/**
		   *   @author    : Sreedhar
		   *   Description:This Service is used to create a pdf file the given string data.
		   *   inputs: buildString
		   *   output: EmailAttributesVO
		   *   
		  */
		public EmailAttributesVO createMainPdfFile(String buildString,EmailAttributesVO fileNamesVO){
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			try{
				
				//folder creation
				String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL + "images" + "/" + IConstants.MAHANADU_IMAGES_2016 ;
				String folderCreation = commonMethodsUtilService.createFolder(staticPath);
				staticPath = staticPath + "/" + fileNamesVO.getDay();
				String folderCreation1 = commonMethodsUtilService.createFolder(staticPath);
				
				Date currentDate = dateUtilService.getCurrentDateAndTime();
				String currentDateString = sdf.format(currentDate);
				
				String pdfName = currentDateString+"_"+RandomNumberGeneraion.randomGenerator(5)+".pdf";
				String pdfFilePath = staticPath+"/"+pdfName;
				
				OutputStream file = new FileOutputStream(new File(pdfFilePath));
				
				Document document = new Document();
				PdfWriter.getInstance(document, file);
				document.open();
				
				HTMLWorker htmlWorker = new HTMLWorker(document);
				htmlWorker.parse(new StringReader(buildString));
				
				document.close();
				file.close();
				if(fileNamesVO.getPdfNames()==null){
					fileNamesVO.setPdfNames(new ArrayList<String>());
				}
				fileNamesVO.getPdfNames().add(pdfName);
				
				/*List<String> images = splitMainPdfToSubPdfs(pdfFilePath,staticPath,currentDateString);
				if(images != null && images.size() > 0){
					fileNamesVO.setImages(images);
				}*/
			}catch(Exception e){
				LOG.error("Exception in createMainPdfFile() : ",e);
			}
			return fileNamesVO;
		}
		
		public List<String> splitMainPdfToSubPdfs(String pdfFilePath,String staticPath,String currentDateString){
			
			List<String> images = new ArrayList<String>();
			
	        try {
	            //String inFile = "E:\\Hall Ticks\\GEST\\sree.pdf";
	            
	            PdfReader reader = new PdfReader(pdfFilePath);
	            int n = reader.getNumberOfPages();
	            System.out.println ("Number of pages : " + n);
	            int i = 0;            
	            while ( i < n ) {
	            	
	                //String outFile = pdfFilePath.substring(0, pdfFilePath.indexOf(".pdf")) + "-" + String.format("%03d", i + 1) + ".pdf"; 
	            	int randomNumber = RandomNumberGeneraion.randomGenerator(5);
	            	String pdfName = currentDateString+"_"+randomNumber+".pdf";
	            	String outFile =  staticPath+"/"+pdfName;
	            	
	                System.out.println ("Writing " + outFile);
	                Document document = new Document(reader.getPageSizeWithRotation(1));
	                PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
	                document.open();
	                PdfImportedPage page = writer.getImportedPage(reader, ++i);
	                writer.addPage(page);
	                document.close();
	                writer.close();
	                
	                //get All image names.
	                String result = convertToImage(outFile);
	                if( result != null && result.equalsIgnoreCase("success")){
	                	images.add(currentDateString+"_"+randomNumber+".jpg");
	                }
	                
	            }
	        } 
	        catch (Exception e) {
	        	LOG.error("Exception in splitMainPdfToSubPdfs() : ",e);
	        }
			return  images;
		}
		
		/**
		   *   @author    : Sreedhar
		   *   Description:This Service is used to convert a pdf file to image
		   *   inputs: buildString
		   *   output: EmailAttributesVO
		   *   
		  */
		public String convertToImage(String indiDEST){
			
			String output = indiDEST.replace(".pdf", ".jpg");
			ProcessBuilder processBuilder = new ProcessBuilder(IConstants.GHOST_SCRIPT_PATH,"-dSAFER", "-dBATCH", "-dNOPAUSE", "-sDEVICE=jpeg","-r210","-dJPEGQ=80","-sOutputFile="+output+"",""+indiDEST+"");
		
			try{
				processBuilder.start();
				return "success";
			}catch(IOException e){
				LOG.error("Exception in splitMainPdfToSubPdfs() : ",e);
				return "fail";
			}
		}
		
		/**
		   *   @author    : Sreedhar
		   *   Description:This Service is used to send  pdf file attachments to mail.
		   *   inputs: buildString
		   *   output: EmailAttributesVO
		   *   
		  */
		
		public String getRequiredDates(List<Date> datesList) throws ParseException{
		    
		    String dateStr = null;
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date currentDate = sdf.parse(sdf.format(dateUtilService.getCurrentDateAndTime()));
		   
		    if(datesList != null && datesList.size() > 0){
		      StringBuilder sb = new StringBuilder();
		      for (int i=0;i<datesList.size();i++) {
		        if(sdf.format(datesList.get(i)).split("-")[0].equalsIgnoreCase(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[0]) ){
		          if(sdf.format(datesList.get(i)).split("-")[2].equalsIgnoreCase(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[2])){
		            
			            if(dateStr == null){
			              dateStr = sdf.format(datesList.get(i));
			            }else{
			              dateStr = dateStr + "," + sdf.format(datesList.get(i));
			            }
		            
		          } 
		          else if(Long.parseLong(sdf.format(datesList.get(i)).split("-")[2])<Long.parseLong(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[2])){
			            if(dateStr == null){
			              dateStr = sdf.format(datesList.get(i));
			            }else{
			              dateStr = dateStr + "," + sdf.format(datesList.get(i));
			            }
		          }
		        }else if(Long.parseLong(sdf.format(datesList.get(i)).split("-")[0])<Long.parseLong(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[0])){
			          if(dateStr == null){
			            dateStr = sdf.format(datesList.get(i));
			          }else{
			            dateStr = dateStr + "," + sdf.format(datesList.get(i));
			          }
		        }
		      }
		    }
		    
		    return dateStr;
		  }
		
		
		
		public void getAllImages(Long parentId,List<Long> subEventIds,String startDate,String endDate,List<Long> stateIds){
			LOG.info("\n\n entered in to getAllImages() method in mahanadu dashboardd service \n" );
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try{
				
				Date currentDateAndTime = dateUtilService.getCurrentDateAndTime();
				
				String time = format.format(currentDateAndTime);
				
				//get event dates
				Object[] dates = eventDAO.getEventDates(parentId);
			    Date dateStart = (Date)dates[0];
			    Date datEnd = (Date)dates[1];
			    List<Date> datesList = new CommonMethodsUtilService().getBetweenDates(dateStart,datEnd);
			    
			    Map<String,String> dateDayMap = new HashMap<String, String>();
				if( datesList != null && datesList.size() > 0){
					for(int i=0;i<datesList.size();i++){
						dateDayMap.put(sdf.format(datesList.get(i)),"day"+(i+1));
					}
				}
				
				
				StringBuffer finalStr = new StringBuffer();
				
				//Event Dashboard
				
				StringBuffer mainDashboardPdfStr = new StringBuffer();
				StatesEventVO  finalVO = stateWiseEventAttendeeCounts(startDate,endDate,parentId,subEventIds,true);
				if( finalVO.getAllStatesVO() != null ){
					StringBuffer allStatesStr = allStatesBlock(finalVO.getAllStatesVO(),time);
					mainDashboardPdfStr.append(allStatesStr);
					mainDashboardPdfStr.append("<br/>");
				}
				
				List<MahanaduEventVO> publicReprestList = getPublicrepresentatives(startDate,endDate,parentId,subEventIds);
				StringBuffer pubStr = PublicrepresentiveBlock(publicReprestList,time);
				mainDashboardPdfStr.append(pubStr);
				mainDashboardPdfStr.append("<br/>");
				
				List<MahanaduEventVO>  distList =LocationWiseEventAttendeeCounts(startDate,endDate,parentId,subEventIds,3l,stateIds,"particular");
				StringBuffer distStr = constWiseCounts(distList,time,"District");
				mainDashboardPdfStr.append(distStr);
				mainDashboardPdfStr.append("<br/>");
				
				List<MahanaduEventVO>  constList = LocationWiseEventAttendeeCounts(startDate,endDate,parentId,subEventIds,4l,stateIds,"particular");
				StringBuffer constStr = constWiseCounts(constList,time,"Constituency");
				mainDashboardPdfStr.append(constStr);
				mainDashboardPdfStr.append("<br/>");
				
				//ENTRY/EXIT DASHBOARD
				String currentDateStr = sdf.format(currentDateAndTime);
				String dateStr = getRequiredDates(datesList);
				
				StringBuffer entryExitPdfStr = new StringBuffer();
				MahanaduVisitVO mahanaduVisitVO = mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfoListNew(parentId,currentDateStr);
			      StringBuffer entryStr = entryExitBlock(mahanaduVisitVO,time);
			      entryExitPdfStr.append(entryStr);
			      entryExitPdfStr.append("<br/><br/><br/>");
			      
			      
			      List<MahanaduVisitVO> hoursWiseVisitorsList = mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfoList(parentId,dateStr);
			      if( hoursWiseVisitorsList != null && hoursWiseVisitorsList.size() > 0){
			        StringBuffer hoursVisitors = hoursWiseVisitors(hoursWiseVisitorsList,time);    
			        entryExitPdfStr.append(hoursVisitors);
			        entryExitPdfStr.append("<br/><br/>");
			      }
			      
			      MahanaduEventVO distWiseVisitorsVO = mahanaduDashBoardService.getDistrictWiseTotalAndPresentCadre(parentId, stateIds,currentDateStr);
			      StringBuffer distWiseVisitors = districtWiseUniqueCampusCount(distWiseVisitorsVO,time);
			      entryExitPdfStr.append(distWiseVisitors);
			      
			      MahanaduEventVO constWiseVisitorsVO = mahanaduDashBoardService.getConstituencyWiseMembersCountInCampus(parentId, stateIds,currentDateStr);
			      StringBuffer constWiseVisitors = constWiseUniqueCampusCount(constWiseVisitorsVO,time);
			      entryExitPdfStr.append(constWiseVisitors);
				
				//MAIL RELATED
			      EmailAttributesVO emailAttributesVO = new EmailAttributesVO();
			      emailAttributesVO.setDay(dateDayMap.get(currentDateStr));
			      
			     createMainPdfFile(mainDashboardPdfStr.toString(),emailAttributesVO);
			     createMainPdfFile(entryExitPdfStr.toString(),emailAttributesVO);
				
				List<String> emailIds = new ArrayList<String>();
				emailIds.add("a.dakavaram@gmail.com");
				emailIds.add("sreedhar.itgrids.hyd@gmail.com");
				emailIds.add("chandu.itgrids.hyd@gmail.com");
				//emailIds.add("aravind.itgrids.hyd@gmail.com");
				
				emailAttributesVO.setEmailIds(emailIds);
				emailAttributesVO.setTime(time);
				emailAttributesVO.setSubject("Mahandu Event 2016 Dashboard");
				emailAttributesVO.setBodyText("Please Find The Attached  Pdf Documents For Mahanadu 2016 Event Dashboard on "+time);
				
				
				if( emailAttributesVO.getPdfNames() != null && emailAttributesVO.getPdfNames().size() > 0){
					sendEmailWithAttachment(emailAttributesVO);
				}
				
			}catch(Exception e){
				LOG.error("Exception in getAllImages() : "+e);
			}
		}
		public StringBuffer PublicrepresentiveBlock(List<MahanaduEventVO> pubList,String time){
			
			StringBuffer str = new StringBuffer();
			String stateColor = "#01AF7C";
			str.append("<font size='2'>");
				str.append("<table width='100%' >");
					str.append("<tr>");
						str.append("<td  bgcolor='#C0C0C0' style='text-align:center;text-transform:uppercase;vertical-align:middle;color:#D64D54'>PUBLIC REPRESENTATIVES</td>");
					str.append("</tr>");
			str.append("</table>");
			
			 if( pubList != null && pubList.size() > 0){
			    	
			    MahanaduEventVO firstLocation = pubList.get(0);
			    str.append("<table width='100%' border='1'>");
			    str.append("<tr>");
				str.append("<td rowspan='2'></td>");
				str.append("<td rowspan='2' style='text-align:center'>Total Invitees</td>");
				for(int i=0; i< firstLocation.getSubList().size() ; i++){
					if( firstLocation.getSubList().get(i).isTotalDaydataExist() ){
						str.append("<th colspan='2' style='text-align:center' >"+firstLocation.getSubList().get(i).getName()+"</th>");
					}
				}
				
				str.append("</tr>");
				str.append("<tr>");
				for(int i=0; i< firstLocation.getSubList().size() ; i++){
					if( firstLocation.getSubList().get(i).isTotalDaydataExist() ){
						str.append("<td style='text-align:center'>Attended</td>");
						str.append("<td style='text-align:center'>Not Attended</td>");
					}
				}
				str.append("</tr>");
				for(int i=0; i<pubList.size();i++){
					str.append("<tr>");
					
					str.append("<td style='text-align:center'>"+pubList.get(i).getName()+"</td>");
		    		str.append("<td style='text-align:center'>"+pubList.get(i).getInvitees()+"</td>");
				
				for(int j=0;j< pubList.get(i).getSubList().size();j++){
	    			
	    			if( firstLocation.getSubList().get(j).isTotalDaydataExist()){
	    				if( pubList.get(i).getSubList().get(j).getAttended() != null && pubList.get(i).getSubList().get(j).getAttended().longValue() > 0l){
	    					str.append("<td style='text-align:center'> "+pubList.get(i).getSubList().get(j).getAttended()+" </td>");
	    				}else{
	    					str.append("<td style='text-align:center'> - </td>");
	    				}
	    				if( pubList.get(i).getSubList().get(j).getNotAttended() != null && pubList.get(i).getSubList().get(j).getNotAttended().longValue() > 0l){
	    					str.append("<td style='text-align:center'> "+pubList.get(i).getSubList().get(j).getNotAttended()+" </td>");
	    				}else{
	    					str.append("<td style='text-align:center'> - </td>");
	    				}
	    				
	    			}
				}
				
				str.append("</tr>");
				
			 } 	
				 str.append("</table>");
			
			 }else{
					str.append("<tr bgcolor='"+stateColor+"'>");
					str.append("<td  >No Data Available</td>");
				str.append("</tr>");
				
			}
				str.append("</font>");
			return str;
	}
		
		public StringBuffer allStatesBlock(StatesEventVO dataVO,String time){
			
			String stateColor = "#01AF7C";
			String borderImagePath = "http://mytdp.com/images/borderImage.png";
			//String borderImagePath = "http://localhost:8080/PartyAnalyst/images/borderImage.png";
			
			StringBuffer str = new StringBuffer();
			
			str.append("<table width='100%'>");
			    str.append("<tr bgcolor='#fed501'>");
			    	str.append("<td style='text-align:center;color:#fff'><font size='6'>MAHANADU 2016 ATTENDANCE SUMMARY</font></td>");
			    str.append("</tr>");
		    str.append("</table>");
		    str.append("<br/>");
		    str.append("<table width='100%'>");
			    str.append("<tr bgcolor='#5c2d25'>");
			    	str.append("<td style='text-align:center;color:#fff'><font size='4'>Last Updated Time : "+time+"</font></td>");
			    str.append("</tr>");
		    str.append("</table>");
		    str.append("<br/>");
			str.append("<table width='100%' style='margin:auto;'>");
					
					str.append("<tr>");
						str.append("<td bgcolor='#C0C0C0' style='text-align:center;text-transform:uppercase;vertical-align:middle;color:#D64D54'><h3> OVERALL[AP,TS,OTHER STATES] </h3></td>");
					str.append("</tr>");
					
					
					if( dataVO.getAttendees() != null && dataVO.getAttendees() > 0l){
						
							String totalVisitors = dataVO.getAttendees() != null ? dataVO.getAttendees().toString() : "-";
							str.append("<tr bgcolor='"+stateColor+"'>"); 
								str.append("<td style='text-align:center;color:#fff'><font size='4'>TOTAL UNIQUE VISITORS "+totalVisitors+"</font></td>");
							str.append("</tr>");
						
							str.append("<tr bgcolor='"+stateColor+"'>"); 
								str.append("<td><img src='"+borderImagePath+"' width='600px'></td>");
							str.append("</tr>");
						
						str.append("<tr bgcolor='"+stateColor+"'>"); 
							str.append("<td style='padding:10px'>");
								str.append("<p style='text-align:left'><font size='4'>DAY WISE</font></p>");
								str.append("<table width='100%' height='900px' bgcolor='"+stateColor+"' style='color:#fff;'>");
								
								Long dayCount = 0l;
								if( dataVO.getSubList() != null && dataVO.getSubList().size() > 0)
								{	
									for( StatesEventVO dayVO : dataVO.getSubList())
									{	    
									    dayCount++;
									    if( dayVO.isDataExist())
									    {
									    	str.append("<tr>");
											
											str.append("<td style='text-align:center;'>");
												str.append("<font size='6'>0"+dayCount+"</font>");
											str.append("</td>");
											
											str.append("<td>");
												str.append("<font size='5'>Total</font><br/>");
												String attendeeCount = dayVO.getAttendees()!=null && dayVO.getAttendees() > 0l ?dayVO.getAttendees().toString() : "-";
												str.append("<span><font size='5'>"+attendeeCount+"</font></span>");
											str.append("</td>");
											
											str.append("<td>");
												str.append("<font size='5'>Invitees</font><br/>");
												String inviteeCount = dayVO.getInvitees()!=null && dayVO.getInvitees() > 0l ?dayVO.getInvitees().toString() : "-";
												str.append("<span><font size='5'>"+inviteeCount+"</font></span>");
											str.append("</td>");
											
											str.append("<td>");
												str.append("<font size='5'>Non Invitees</font><br/>");
												String nonInviteeCount = dayVO.getNonInvitees()!=null && dayVO.getNonInvitees() > 0l ?dayVO.getNonInvitees().toString() : "-";
												str.append("<span><font size='5'>"+nonInviteeCount+"</font></span>");
											str.append("</td>");
											
										str.append("</tr>");
										
										str.append("<tr>");
											str.append("<td colspan='4'><img src='"+borderImagePath+"' width='600px'></td>");
										str.append("</tr>");
									    }
									}
								}
								
								str.append("</table>");
							str.append("</td>");
						str.append("</tr>");
						
						if(dataVO.getCalcPercantage() != null && dataVO.getCalcPercantage().trim().length() > 0){
							str.append("<tr bgcolor='"+stateColor+"'>"); 
							     str.append("<td style='color:#fff;text-align:center;'><font size='4'> "+dataVO.getDateString1()+" Count is "+dataVO.getCalcPercantage()+"% "+dataVO.getHighOrlow()+" than "+dataVO.getDateString2()+"</font></td>");
						    str.append("</tr>");
						}else{
							str.append("<tr bgcolor='"+stateColor+"'>"); 
						     str.append("<td style='color:#fff;text-align:center;'><font size='4'></font></td>");
					        str.append("</tr>");
						} 
						
					}else{
						str.append("<tr bgcolor='"+stateColor+"'>");
							str.append("<td>No Data Available</td>");
						str.append("</tr>");
						
					}
				
				str.append("</table>");
			
			return str;
		}
		
		public StringBuffer constWiseCounts(List<MahanaduEventVO> constList,String time,String reportType){
			
			String reportHeading = null;
			if(reportType.equalsIgnoreCase("constituency")){
				reportHeading = "CONSTITUENCY WISE";
			}else if(reportType.equalsIgnoreCase("district")){
				reportHeading = "DISTRICT WISE";
			}
			
			StringBuffer str = new StringBuffer();
			str.append("<table width='100%'>");
				str.append("<tr>");
					str.append("<td bgcolor='#C0C0C0' style='text-align:center;text-transform:uppercase;vertical-align:middle;color:#D64D54'><h3> "+reportHeading+" </h3></td>");
				str.append("</tr>");
		    str.append("</table>");
		    str.append("<font size='1'>");
			
		    if( constList != null && constList.size() > 0){
		    	
		    	MahanaduEventVO firstLocation = constList.get(0);
		    	
		    	boolean dataExist = true;
		    	if( firstLocation.getLocationName() != null && !firstLocation.getLocationName().equalsIgnoreCase("NO DATA")){
		    		dataExist = false;
		    	}
		    	
		    	if( dataExist){
		    		
		    		str.append("<table width='100%' border='1' bgcolor='#EFF3F4'>");
		    		str.append("<thead >");
		    		
		    		str.append("<tr>");
		    			str.append("<th rowspan='2' > "+reportType+" </th>");
		    			str.append("<th rowspan='2' cellpadding='2' >Total Attended</th>");
		    			str.append("<th rowspan='2' cellpadding='2' >%</th>");
		    			str.append("<th rowspan='2' cellpadding='2'>Total Invitees</th>");
		    			for(int i=0; i< firstLocation.getSubList().size() ; i++){
		    				if( firstLocation.getSubList().get(i).isTotalDaydataExist() ){
		    					str.append("<th colspan='3' >"+firstLocation.getSubList().get(i).getName()+" ATTENDED</th>");
		    				}
		    			}
		    		str.append("</tr>");
		    		
		    		str.append("<tr>");
			    		for(int i=0; i< firstLocation.getSubList().size() ; i++){
		    				if( firstLocation.getSubList().get(i).isTotalDaydataExist() ){
		    					str.append("<th>Total</th>");
		    	    			str.append("<th>Invitees</th>");
		    	    			str.append("<th>Non Invitees</th>");
		    				}
		    			}
		    		str.append("</tr>");
		    		
		    		
		    		str.append("</thead>");
		    		str.append("<tbody >");
		    		
		    		for(int i=0; i<constList.size();i++){
		    			
		    			
		    			str.append("<tr>");
		    			
			    		str.append("<td width='200%'>"+constList.get(i).getName()+"</td>");
			    		str.append("<td style='text-align:center;'>"+constList.get(i).getAttendees()+"</td>");
			    		str.append("<td style='text-align:center;'>"+constList.get(i).getAttendeePercantage()+"%</td>");
			    		
			    		if( constList.get(i).getInviteesCalled()!= null && constList.get(i).getInviteesCalled().longValue() > 0l){
			    			str.append("<td style='text-align:center;'>"+constList.get(i).getInviteesCalled()+"</td>");
			    		}else{
			    			str.append("<td style='text-align:center;'> - </td>");
			    		}
			    		
			    		for(int j=0;j< constList.get(i).getSubList().size();j++){
			    			
			    			if( firstLocation.getSubList().get(j).isTotalDaydataExist()){
			    				
			    				if( constList.get(i).getSubList().get(j).getAttendees() != null && constList.get(i).getSubList().get(j).getAttendees().longValue() > 0l){
			    					str.append("<td style='text-align:center;'> "+constList.get(i).getSubList().get(j).getAttendees()+" </td>");
			    				}else{
			    					str.append("<td style='text-align:center;'> - </td>");
			    				}
			    				
			    				if( constList.get(i).getSubList().get(j).getInvitees() != null && constList.get(i).getSubList().get(j).getInvitees().longValue() > 0l){
			    					str.append("<td style='text-align:center;'> "+constList.get(i).getSubList().get(j).getInvitees()+" </td>");
			    				}else{
			    					str.append("<td style='text-align:center;'> - </td>");
			    				}
			    				
			    				if( constList.get(i).getSubList().get(j).getNonInvitees() != null && constList.get(i).getSubList().get(j).getNonInvitees().longValue() > 0l){
			    					str.append("<td style='text-align:center;'> "+constList.get(i).getSubList().get(j).getNonInvitees()+" </td>");
			    				}else{
			    					str.append("<td style='text-align:center;'> - </td>");
			    				}
			    			}
			    		}
			    		str.append("</tr>");
		    		}
		    		
		    		str.append("</tbody>");
		    		str.append("</table>");
		    	}
		    }
			
			str.append("</font>");
			str.append("<br/>");
			return str;
		}
		
		public StringBuffer entryExitBlock(MahanaduVisitVO result,String time){
		    
		    StringBuffer str = new StringBuffer();
		    str.append("<table width='100%'>");
		        str.append("<tr bgcolor='#5c2d25'>");
		          str.append("<td style='text-align:center;color:#fff'><font size='5'>ENTRY/EXIT  DASHBOARD</font><font size='3'>(Updated On "+time+")</font></td>");
		        str.append("</tr>");
		      str.append("</table>");
		      str.append("<br/>");
		      str.append("<table width='100%'  cellspacing='5'>");
		      
		         str.append("<tr style='text-align:center'>");
		          str.append("<td width='50%' border='1' >");
		            str.append("<font size='4'>TODAY TOTAL VISITORS</font><br/>");
		            String totalVisitors = result.getTotalVisitors() != null ? result.getTotalVisitors().toString() : "0";
		            str.append("<font size='5'> "+totalVisitors+"</font>");
		          str.append("</td>");
		          
		          
		          str.append("<td width='50%' border='1' >");
		            str.append("<font size='4'>TODAY VISITORS PRESENT IN CAMPUS</font><br/>");
		            String currentVisitors = result.getCurrentVisitors() != null ? result.getCurrentVisitors().toString() : "0";
		            str.append("<font size='5'>"+currentVisitors+"</font>");
		          str.append("</td>");
		        str.append("</tr>");
		        
		      str.append("</table>");
		    return str;
		  }
		
		
		public StringBuffer hoursWiseVisitors(List<MahanaduVisitVO> hoursWiseVisitorsList,String time){
		    
		    Map<Long,String> colorsMap = new HashMap<Long,String>();
		    colorsMap.put(1l,"#DAEEF7");
		    colorsMap.put(2l,"#C2ECE0");
		    colorsMap.put(3l,"#F2EBD9");
		    
		    int dayscount = hoursWiseVisitorsList.size();
		    
		    StringBuffer str = new StringBuffer();
		    str.append("<font size='4'>");
		    str.append("<table width='100%' border='1'>");
		    str.append("<tr bgcolor='#ccc'>");
		    int colspan = dayscount + 1;
		    str.append("<td colspan='"+colspan+"' style='text-align:center;color:#D64D54'><font size='4'>HOURS WISE VISITORS PRESENT IN CAMPUS <font size = '2'>("+hoursWiseVisitorsList.get(hoursWiseVisitorsList.size()-1).getLastUpdated()+")</font></font></td>");
		    str.append("</tr>");
		    
		    str.append("<tr bgcolor='#eaeaea' style='text-align:center;color:#01AF7C'>");
		    str.append("<td>TIME STATUS</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		       Long dayCount = i+1l;
		       str.append("<td bgcolor='"+colorsMap.get(dayCount)+"'>DAY "+dayCount+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>Above 8 hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getAbove8hrs()!=null ? hoursWiseVisitorsList.get(i).getAbove8hrs().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>7 to 8 Hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getSeventoeight()!=null ? hoursWiseVisitorsList.get(i).getSeventoeight().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>6 to 7 Hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getSixtoseven()!=null ? hoursWiseVisitorsList.get(i).getSixtoseven().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>5 to 6 Hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getFivetosix()!=null ? hoursWiseVisitorsList.get(i).getFivetosix().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>4 to 5 Hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getFourtofive()!=null ? hoursWiseVisitorsList.get(i).getFourtofive().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>3 to 4 Hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getThreetofour()!=null ? hoursWiseVisitorsList.get(i).getThreetofour().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>2 to 3 Hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getTwotothree()!=null ? hoursWiseVisitorsList.get(i).getTwotothree().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>1 to 2 Hours</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getOnetotwo()!=null ? hoursWiseVisitorsList.get(i).getOnetotwo().toString() :"0";

		
		str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>Half an hour  to 1 Hour</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getHalfanhour()!=null ? hoursWiseVisitorsList.get(i).getHalfanhour().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    
		    str.append("<tr style='text-align:center;'>");
		    str.append("<td>below Half an hour</td>");
		    for(int i=0 ; i<hoursWiseVisitorsList.size();i++){
		      String value = hoursWiseVisitorsList.get(i).getBelowhalfanhour()!=null ? hoursWiseVisitorsList.get(i).getBelowhalfanhour().toString() :"0";
		      str.append("<td bgcolor='"+colorsMap.get(i+1l)+"'>"+value+"</td>");
		    }
		    str.append("</tr>");
		    
		    str.append("</table>");
		    str.append("</font>");
		    return str;
	  }
		
		public StringBuffer districtWiseUniqueCampusCount(MahanaduEventVO result,String time){
		    
		      StringBuffer str = new StringBuffer();
		      String stateColor = "#fff";
		      str.append("<table width='100%' border='1'>");
		      
		        str.append("<tr bgcolor='#ccc'>");
		          str.append("<td color='#d64d54' colspan='4'><font size='3' style='text-align:center;'>DISTRICT WISE MEMBERS IN CAMPUS NOW & DAY WISE COUNT</font></td>");
		        str.append("</tr>");
		     
		        str.append("<font  size='2'>");
		        if(result != null && result.getSubList() != null && result.getSubList().size() > 0){
		          boolean flag = false;
		             str.append("<tr>");
		              str.append("<td>DISTRICT NAME</td>");
		              str.append("<td>TOTAL ATTENDED</td>");
		              str.append("<td>NOW IN CAMPUS</td>");
		              str.append("<td>NOW IN CAMPUS %</td>");
		            str.append("</tr>");
		           
		          
		          for(  MahanaduEventVO distVO : result.getSubList() ){
		            
		          if(distVO.getTotal() != null && distVO.getTotal().longValue() > 0l && distVO.getCadreCount() != null && distVO.getCadreCount().longValue() > 0l){
		            
		            flag=true;
		            str.append("<tr>");
		            str.append("<td>"+distVO.getName()+"</td>");
		            str.append("<td style='text-align:center;'>"+distVO.getTotal()+"</td>");
		            str.append("<td style='text-align:center;'>"+distVO.getCadreCount()+"</td>");
		            String Percantage =calcPercantage(distVO.getTotal(), distVO.getCadreCount());
		            str.append("<td style='text-align:center;'>"+Percantage+" %</td>");
		            str.append("</tr>");
		          }
		        }
		         
		            if(!flag){
		              str.append("<tr bgcolor='"+stateColor+"'>");
		              str.append("<td colspan='4' style='text-align:center' >No Data Available</td>");
		          str.append("</tr>");
		            }
		        }else{
		          str.append("<tr bgcolor='"+stateColor+"'>");
		          str.append("<td colspan='4' style='text-align:center' >No Data Available</td>");
		      str.append("</tr>");
		        }
		      str.append("</font>");
		      str.append("</table>");  
		      str.append("<br/><br/><br/><br/><br/><br/>");
		      
		      return str;
		    }
		
		public StringBuffer constWiseUniqueCampusCount(MahanaduEventVO result,String time){
		    
		     StringBuffer str = new StringBuffer();
		     String stateColor = "#fff";
		     str.append("<table width='100%' border='1'>");
		         str.append("<tr bgcolor='#ccc'>");
		           str.append("<td color='#d64d54' colspan='4' style='text-align:center;'><font size='3'>CONSTITUENCY WISE MEMBERS IN CAMPUS NOW & DAY WISE COUNT</font></td>");
		        str.append("</tr>");
		        
		        str.append("<font  size='2'>");
		        if(result != null && result.getSubList() != null && result.getSubList().size() > 0l){
		          boolean flag = false;
		             str.append("<tr>");
		              str.append("<td>CONSTITUENCY NAME</td>");
		              str.append("<td>TOTAL ATTENDED</td>");
		              str.append("<td>NOW IN CAMPUS</td>");
		              str.append("<td>NOW IN CAMPUS %</td>");
		            str.append("</tr>");
		          for(  MahanaduEventVO constVO : result.getSubList() ){
		            
		          if(constVO.getTotal() != null && constVO.getTotal().longValue() > 0l && constVO.getCadreCount() != null && constVO.getCadreCount().longValue() > 0l){
		            
		            flag=true;
		            str.append("<tr>");
		            str.append("<td>"+constVO.getName()+"</td>");
		            str.append("<td style='text-align:center;'>"+constVO.getTotal()+"</td>");
		            str.append("<td style='text-align:center;'>"+constVO.getCadreCount()+"</td>");
		            String Percantage =calcPercantage(constVO.getTotal(), constVO.getCadreCount());
		            str.append("<td style='text-align:center;'>"+Percantage+" %</td>");
		            str.append("</tr>");
		          }
		        }
		         
		            if(!flag){
		              str.append("<tr bgcolor='"+stateColor+"'>");
		              str.append("<td colspan='4' style='text-align:center' >No Data Available</td>");
		          str.append("</tr>");
		            }
		        }else{
		          str.append("<tr bgcolor='"+stateColor+"'>");
		          str.append("<td colspan='4' style='text-align:center' >No Data Available</td>");
		      str.append("</tr>");
		        }
		        str.append("</font>");
		      str.append("</table>");  
		      str.append("<br/><br/><br/><br/><br/><br/><br/><br/>");
		      
		      return str;
		  }
		
		
		
		/**
		   *   @author    : Sreedhar
		   *   Description:This Service is used to get the days wise count od public representives.
		   *   inputs: startDate,endDate,parenteventId,subEventIds
		   *   output: List<MahanaduEventVO>
		   *   
		  */
		public List<MahanaduEventVO> getPublicrepresentatives(String startDateStr,String endDateStr,Long eventId,List<Long> subEventIds){
			  
			  List<MahanaduEventVO> finallist = new ArrayList<MahanaduEventVO>();
			  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  try{
				  
				  //Dates
				  Date startDate = null;
				  Date endDate = null;
				  if( startDateStr != null && !startDateStr.isEmpty()){
					  startDate = sdf.parse(startDateStr);
				  }
				  if( endDateStr != null && !endDateStr.isEmpty()){
					  endDate = sdf.parse(endDateStr);
				  }
				  List<Date>  betweenDates=new CommonMethodsUtilService().getBetweenDates(startDate,endDate);
				  
				  //designations
				  List<Long> designationIds =Arrays.asList(IConstants.PUBLIC_REPR_DESIGNATION_IDS);
				  
				  
				  Map<Long,MahanaduEventVO> designationsMap = new LinkedHashMap<Long, MahanaduEventVO>();
				  
				  //TOTAL INVITEES
				  List<Object[]> inviteesList = eventInviteeDAO.getPublicRepresentiveInvitessForEvent(eventId,designationIds);
				  if( inviteesList != null && inviteesList.size() > 0){
					  
					  for(Object[] obj : inviteesList){
						  
						  MahanaduEventVO desgVO = new MahanaduEventVO();
						  desgVO.setId( obj[0]!= null ? (Long)obj[0] :0l);
						  desgVO.setName(obj[1]!= null ? obj[1].toString() :"");
						  desgVO.setInvitees(obj[2]!= null ? (Long)obj[2] :0l);
						  
						  //false
						  if(betweenDates != null && betweenDates .size() > 0){
							  for( int i=0;i<betweenDates.size();i++){
								  MahanaduEventVO dayVO = new MahanaduEventVO();
								  dayVO.setName("Day"+(i+1));
								  dayVO.setDateStr(format.format(betweenDates.get(i)));
								  dayVO.setNotAttended( desgVO.getInvitees() );
								  dayVO.setTotalDaydataExist(false);
								  if(desgVO.getSubMap() == null){
									  desgVO.setSubMap(new LinkedHashMap<String, MahanaduEventVO>(0));  
								  }
								  desgVO.getSubMap().put(dayVO.getDateStr(),dayVO);
							  }
						  }
						  designationsMap.put( desgVO.getId() , desgVO);
					  }
				  }
				  
				  //Day Wise Attended.
				  List<Object[]> dayWiseList = eventInviteeDAO.dayWisePublicRepInviteesAttendedForEvent(startDate,endDate,subEventIds,designationIds);
				  if( dayWiseList != null && dayWiseList.size() > 0){
					  
					  for( Object[] obj : dayWiseList){
						  
						  MahanaduEventVO designationVO = designationsMap.get((Long)obj[0]);
						  if( designationVO != null ){
							  MahanaduEventVO dayVO = designationVO.getSubMap().get(obj[2].toString());
							  
							  if( dayVO != null){
								  
								  dayVO.setAttended(obj[3]!=null ? (Long)obj[3]:0l);
								  dayVO.setNotAttended( designationVO.getInvitees() - dayVO.getAttended());
								  
								  designationsMap.entrySet().iterator().next().getValue().getSubMap().get(obj[2].toString()).setTotalDaydataExist(true);
								  
							  }
						  }
					  }
				  }
				  
				  
				  if( designationsMap!= null && designationsMap.size() > 0){
						 for (Map.Entry<Long, MahanaduEventVO> entry : designationsMap.entrySet()) {
							 if (entry.getValue().getSubMap() != null){
								 entry.getValue().getSubList().addAll(entry.getValue().getSubMap().values());
								 entry.getValue().getSubMap().clear();
							 } 
						 }
						 finallist.addAll(designationsMap.values());
					 }
				  
			  }catch (Exception e) {
				  Log.error("Exception rised in getPublicrepresentatives()",e); 
			  }
			  return finallist;
		  }
		
		
		
		
  }

