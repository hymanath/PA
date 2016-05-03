package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateTypeDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dto.AppointmentFieldsVO;
import com.itgrids.partyanalyst.dto.AppointmentLocVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
import com.itgrids.partyanalyst.service.IAppointmentReportDashBoardService;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.LocationService;

public class AppointmentReportDashBoardService implements IAppointmentReportDashBoardService {
		
	private static Logger LOG = Logger.getLogger(AppointmentReportDashBoardService.class);
	
	//ATTRIBUTES
	private IAppointmentCandidateRelationDAO appointmentCandidateRelationDAO;
	private IAppointmentCandidateTypeDAO    appointmentCandidateTypeDAO;
	private LocationService locationService;
	private IAppointmentService appointmentService;
	private ITdpCadreDAO tdpCadreDAO;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
    //SETTERS AND GETTERS
	public IAppointmentCandidateRelationDAO getAppointmentCandidateRelationDAO() {
		return appointmentCandidateRelationDAO;
	}

	public void setAppointmentCandidateRelationDAO(
			IAppointmentCandidateRelationDAO appointmentCandidateRelationDAO) {
		this.appointmentCandidateRelationDAO = appointmentCandidateRelationDAO;
	}

	public IAppointmentCandidateTypeDAO getAppointmentCandidateTypeDAO() {
		return appointmentCandidateTypeDAO;
	}

	public void setAppointmentCandidateTypeDAO(
			IAppointmentCandidateTypeDAO appointmentCandidateTypeDAO) {
		this.appointmentCandidateTypeDAO = appointmentCandidateTypeDAO;
	}
	
	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public IAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}

	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	//BUSINESS SERVICES
public List<AppointmentLocVO> getCandiCountsByLocations(String startDateStr,String endDateStr,Long appointmentUserId,String locType,Long stateId,List<Long> candiTypeIds,List<String> requestedTypes){
		
		List<AppointmentLocVO> finalList = null; 
		Map<Long,AppointmentLocVO> finalMap = null;		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			
			Date startDate = null;
			Date endDate   = null;
			
			if(startDateStr != null && startDateStr.trim().length() > 0){
				startDate = sdf.parse(startDateStr);
			}
			if(endDateStr != null && endDateStr.trim().length() > 0){
				endDate = sdf.parse(endDateStr);
			}
			
			
			//TOTAL RELATED.
			
			String totalQuery = getQuery(appointmentUserId,null,startDate,endDate,locType,stateId);
			List<Object[]>  totalReqCountsList = appointmentCandidateRelationDAO.getTotalCountCandiByLocation(totalQuery,appointmentUserId,null,startDate,endDate);
			
			if(totalReqCountsList!=null && totalReqCountsList.size()>0){
				finalMap = new LinkedHashMap<Long,AppointmentLocVO>();
				
				List<Object[]> filteredCandids = null;
				if(candiTypeIds.contains(0l)){
					filteredCandids = appointmentCandidateTypeDAO.getAllCandidateTypes();
				}else{
					filteredCandids = appointmentCandidateTypeDAO.getCandidateTypesByIds(candiTypeIds);
				}
				
				List<String> allRequestedTypes =getAllRequestedTypes();
				
				List<String> filteredRequestedTypes = null;
				if(requestedTypes.contains("All")){
					filteredRequestedTypes = allRequestedTypes;
				}else{
					filteredRequestedTypes = requestedTypes;
				}
				
				preInstantiationLogic(totalReqCountsList,finalMap,filteredCandids,filteredRequestedTypes,allRequestedTypes);
			}else{
				return finalList;
			}
			
			
			List<Long>  scheduledList = Arrays.asList(IConstants.APPOINTMENT_SCHEDULED_LIST);
			String totalScheduledQuery = getQuery(appointmentUserId,scheduledList,startDate,endDate,locType,stateId);
			List<Object[]>  totalScheduledCountsList = appointmentCandidateRelationDAO.getTotalCountCandiByLocation(totalScheduledQuery,appointmentUserId,scheduledList,startDate,endDate);
			
			List<Long>  waitingList = Arrays.asList(IConstants.APPOINTMENT_WAITING_LIST);
			String totalWaitingQuery = getQuery(appointmentUserId,waitingList,startDate,endDate,locType,stateId);
			List<Object[]>  totalWaitingCountsList = appointmentCandidateRelationDAO.getTotalCountCandiByLocation(totalWaitingQuery,appointmentUserId,waitingList,startDate,endDate);
			
			 //get first district to set the total counts.
			 Map.Entry<Long,AppointmentLocVO> districts=finalMap.entrySet().iterator().next();
			 AppointmentLocVO firstLocationVO = districts.getValue();
			 
			 
			setTotalCountsToLocation(totalReqCountsList,finalMap,"requested",firstLocationVO);
			setTotalCountsToLocation(totalScheduledCountsList,finalMap,"scheduled",firstLocationVO);
			setTotalCountsToLocation(totalWaitingCountsList,finalMap,"waiting",firstLocationVO);
			
			
			//FILTERED RELATED.
			List<Object[]>  filterReqCountsList = null;
			List<Object[]> filterScheduledCountsList = null;
			List<Object[]> filterWaitingCountsList = null;
			if(requestedTypes.contains("All")){
				
				String filterQuery = getFilterdQueryByLocation(appointmentUserId,null,startDate,endDate,locType,stateId,candiTypeIds);
				String filterScheduledQuery = getFilterdQueryByLocation(appointmentUserId,scheduledList,startDate,endDate,locType,stateId,candiTypeIds);
				String filterWaitingQuery = getFilterdQueryByLocation(appointmentUserId,waitingList,startDate,endDate,locType,stateId,candiTypeIds);
				
				  filterReqCountsList = appointmentCandidateRelationDAO.getCountsOfCandiByLocation(filterQuery,appointmentUserId,null,startDate,endDate,candiTypeIds);
				  filterScheduledCountsList = appointmentCandidateRelationDAO.getCountsOfCandiByLocation(filterScheduledQuery,appointmentUserId,scheduledList,startDate,endDate,candiTypeIds);
				  filterWaitingCountsList = appointmentCandidateRelationDAO.getCountsOfCandiByLocation(filterWaitingQuery,appointmentUserId,waitingList,startDate,endDate,candiTypeIds);
				
			}else{
				
				if(requestedTypes.contains("requested")){
					
					String filterQuery = getFilterdQueryByLocation(appointmentUserId,null,startDate,endDate,locType,stateId,candiTypeIds);
					filterReqCountsList = appointmentCandidateRelationDAO.getCountsOfCandiByLocation(filterQuery,appointmentUserId,null,startDate,endDate,candiTypeIds);
				}
				if(requestedTypes.contains("scheduled")){
					
					String filterScheduledQuery = getFilterdQueryByLocation(appointmentUserId,scheduledList,startDate,endDate,locType,stateId,candiTypeIds);
					filterScheduledCountsList = appointmentCandidateRelationDAO.getCountsOfCandiByLocation(filterScheduledQuery,appointmentUserId,scheduledList,startDate,endDate,candiTypeIds);
				}
				if(requestedTypes.contains("waiting")){
					
					String filterWaitingQuery = getFilterdQueryByLocation(appointmentUserId,waitingList,startDate,endDate,locType,stateId,candiTypeIds);
					filterWaitingCountsList = appointmentCandidateRelationDAO.getCountsOfCandiByLocation(filterWaitingQuery,appointmentUserId,waitingList,startDate,endDate,candiTypeIds);
				}
			}
			setFilteredCountsToLocation(filterReqCountsList,finalMap,"requested",firstLocationVO);
			setFilteredCountsToLocation(filterScheduledCountsList,finalMap,"scheduled",firstLocationVO);
			setFilteredCountsToLocation(filterWaitingCountsList,finalMap,"waiting",firstLocationVO);
			
			if(finalMap!=null && finalMap.size() >0){
				for(Map.Entry<Long,AppointmentLocVO>  entry  :finalMap.entrySet()){
					
					if(entry.getValue().getSubMap()!=null && entry.getValue().getSubMap().size()>0){
						entry.getValue().setSubList(new ArrayList<AppointmentLocVO>(entry.getValue().getSubMap().values()));
						entry.getValue().getSubMap().clear();
					}
			    }
				finalList = new ArrayList<AppointmentLocVO>(finalMap.values());
				finalMap.clear();
			}
			
			
		}catch(Exception e) {
			LOG.error("Exception raised at getCandiCountsByLocations() method of AppointmentReportDashBoardService", e);
		}
		return finalList;
	}
	

	public void setFilteredCountsToLocation(List<Object[]> dataList,Map<Long,AppointmentLocVO> finalMap,String reqType,AppointmentLocVO firstLocationVO){
	try{
		 if(dataList != null && dataList.size() >0){
			 for(Object[] obj : dataList){
				 
				 Long locId = obj[0]!=null ?(Long)obj[0]:0l; 
				 AppointmentLocVO locVO = finalMap.get(locId);
				 
				 Long candiTypeId = obj[2]!=null ?(Long)obj[2]:0l;
				 if(locVO !=null && candiTypeId.longValue() > 0l)
				 {  
					 AppointmentLocVO candiVO = locVO.getSubMap().get(candiTypeId);
					 if(candiVO!=null)
					 {
						 AppointmentLocVO typeVO = getMatchedRequestedType(candiVO.getSubList(),reqType);
						 if(typeVO!=null)
						 {
							 typeVO.setCount(obj[3]!=null ?(Long)obj[3]:0l);
							 typeVO.setUniqueCount(obj[4]!=null ?(Long)obj[4]:0l);
						 }
					 }
					
				 }
				 
				 //FOR TOTAL COUNTS
				 if(firstLocationVO !=null && candiTypeId.longValue() > 0l)
				 {  
					 AppointmentLocVO candiVO = firstLocationVO.getSubMap().get(candiTypeId);
					 if(candiVO!=null)
					 {
						 AppointmentLocVO typeVO = getMatchedRequestedType(candiVO.getSubList(),reqType);
						 if(typeVO!=null)
						 {
							 typeVO.setTotalCount(  typeVO.getTotalCount() +  (obj[3]!=null ?(Long)obj[3]:0l) );
							 typeVO.setTotalUniqueCount( typeVO.getTotalUniqueCount() + (obj[4]!=null ?(Long)obj[4]:0l) );
						 }
					 }
					
				 }
				 
			 }
		 }
		
	}catch(Exception e){
		LOG.error("Exception raised at setFilteredCountsToLocation() method of AppointmentReportDashBoardService", e);
	}
	
	}
	public void setTotalCountsToLocation(List<Object[]> dataList,Map<Long,AppointmentLocVO> finalMap,String reqType,AppointmentLocVO firstLocationVO){
		try{
			 if(dataList != null && dataList.size() >0){
				 for(Object[] obj : dataList){
					 
					 Long locId = obj[0]!=null ?(Long)obj[0]:0l; 
					 AppointmentLocVO locVO = finalMap.get(locId);
					 
					 if(locVO !=null){
						 
						 AppointmentLocVO typeVO = getMatchedRequestedType(locVO.getTypeList(),reqType);
						 if(typeVO !=null){
							 typeVO.setCount(obj[2]!=null?(Long)obj[2]:0l);
							 typeVO.setUniqueCount(obj[3]!=null?(Long)obj[3]:0l);
						 }
						 
						 //FOR TOTAL COUNTS
						 AppointmentLocVO firstLocationRequestStatusVO = getMatchedRequestedType(firstLocationVO.getTypeList(),reqType);
						 if(firstLocationRequestStatusVO != null ){
							 firstLocationRequestStatusVO.setTotalCount(firstLocationRequestStatusVO.getTotalCount() + (obj[2]!=null?(Long)obj[2]:0l));
							 firstLocationRequestStatusVO.setTotalUniqueCount(firstLocationRequestStatusVO.getTotalUniqueCount()+(obj[3]!=null?(Long)obj[3]:0l));
						 }
						 
					 }
				 }
			 }
			
		}catch(Exception e){
			LOG.error("Exception raised at setTotalCountsToLocation() method of AppointmentReportDashBoardService", e);
		}
	}
	public AppointmentLocVO getMatchedRequestedType(List<AppointmentLocVO> voList,String status){
		if(voList != null && voList.size()>0){
			for (AppointmentLocVO VO : voList) {
				if(VO.getName().equalsIgnoreCase(status)){
					return VO;
				}
			}
		}
		return null;
	}
	
	
	
	public String getFilterdQueryByLocation(Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate,String locType,Long stateId,List<Long> candiTypeIds){
		
		StringBuilder sq = new StringBuilder();
	try{
		
		StringBuilder sbS =  new StringBuilder();
		sbS.append(" select ");
		
		StringBuilder sbM =  new StringBuilder();
		sbM.append(" from   AppointmentCandidateRelation model " +
				"    where  model.appointment.isDeleted='N' and model.appointment.appointmentUser.appointmenUserId =:appointmentUserId ");
		
		if(apptStatusIds!=null && apptStatusIds.size()>0){
			sbM.append(" and model.appointment.appointmentStatusId in (:apptStatusIds)");
		}
		if(startDate!=null){
			sbM.append(" and date(model.appointment.updatedTime) >= :startDate");
		}
		if(endDate!=null){
			sbM.append(" and date(model.appointment.updatedTime) <= :endDate");
		}
		
		if(candiTypeIds != null && candiTypeIds.size() > 0 && !candiTypeIds.contains(0l)){
			sbM.append(" and model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId in (:candiTypeIds)");
		}
		
		StringBuilder sbE =  new StringBuilder();
		if(locType != null && locType.equalsIgnoreCase("district")){
			
			sbS.append(" model.appointmentCandidate.userAddress.district.districtId,model.appointmentCandidate.userAddress.district.districtName ");
			if( stateId != null && stateId > 0l){
				if( stateId.longValue() == 1l ){
					sbM.append(" and model.appointmentCandidate.userAddress.district.districtId between 11 and 23 ");
				}else if(stateId.longValue() == 36l){
					sbM.append(" and model.appointmentCandidate.userAddress.district.districtId between 1 and 10 ");
				}
			}
			sbE.append(" group by model.appointmentCandidate.userAddress.district.districtId,model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId " +
					   " order by model.appointmentCandidate.userAddress.district.districtName  ");
			
		}else if(locType != null && locType.equalsIgnoreCase("constituency")){
			sbS.append(" model.appointmentCandidate.userAddress.constituency.constituencyId,model.appointmentCandidate.userAddress.constituency.name ");
			if( stateId != null && stateId > 0l){
				if( stateId.longValue() == 1l ){
					sbM.append(" and model.appointmentCandidate.userAddress.constituency.district.districtId between 11 and 23 ");
				}else if(stateId.longValue() == 36l){
					sbM.append(" and model.appointmentCandidate.userAddress.constituency.district.districtId between 1 and 10 ");
				}
			}
			sbE.append(" group by model.appointmentCandidate.userAddress.constituency.constituencyId,model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId " +
					   " order by model.appointmentCandidate.userAddress.constituency.name ");
		}
		
		sbS.append(" ,model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId,count(model.appointmentCandidate.appointmentCandidateId),count(distinct model.appointmentCandidate.appointmentCandidateId)");
		
        sq.append(sbS.toString()).append(sbM.toString()).append(sbE.toString());
        
	}catch(Exception e) {
		LOG.error("Exception raised at getFilterdQueryByLocation() method of AppointmentReportDashBoardService", e);
	}
	return sq.toString();
  }
	
	
	
	public String getQuery(Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate,String locType,Long stateId){
		
		StringBuilder sq = new StringBuilder();
	try{
		
		StringBuilder sbS =  new StringBuilder();
		sbS.append(" select ");
		
		StringBuilder sbM =  new StringBuilder();
		sbM.append(" from   AppointmentCandidateRelation model " +
				"    where  model.appointment.isDeleted='N' and model.appointment.appointmentUser.appointmenUserId =:appointmentUserId ");
		
		if(apptStatusIds!=null && apptStatusIds.size()>0){
			sbM.append(" and model.appointment.appointmentStatusId in (:apptStatusIds)");
		}
		if(startDate!=null){
			sbM.append(" and date(model.appointment.updatedTime) >= :startDate");
		}
		if(endDate!=null){
			sbM.append(" and date(model.appointment.updatedTime) <= :endDate");
		}
		
		StringBuilder sbE =  new StringBuilder();
		if(locType != null && locType.equalsIgnoreCase("district")){
			
			sbS.append(" model.appointmentCandidate.userAddress.district.districtId,model.appointmentCandidate.userAddress.district.districtName ");
			if( stateId != null && stateId > 0l){
				if( stateId.longValue() == 1l ){
					sbM.append(" and model.appointmentCandidate.userAddress.district.districtId between 11 and 23 ");
				}else if(stateId.longValue() == 36l){
					sbM.append(" and model.appointmentCandidate.userAddress.district.districtId between 1 and 10 ");
				}
			}
			sbE.append(" group by model.appointmentCandidate.userAddress.district.districtId " +
					   " order by model.appointmentCandidate.userAddress.district.districtName  ");
			
		}else if(locType != null && locType.equalsIgnoreCase("constituency")){
			sbS.append(" model.appointmentCandidate.userAddress.constituency.constituencyId,model.appointmentCandidate.userAddress.constituency.name ");
			if( stateId != null && stateId > 0l){
				if( stateId.longValue() == 1l ){
					sbM.append(" and model.appointmentCandidate.userAddress.constituency.district.districtId between 11 and 23 ");
				}else if(stateId.longValue() == 36l){
					sbM.append(" and model.appointmentCandidate.userAddress.constituency.district.districtId between 1 and 10 ");
				}
			}
			sbE.append(" group by model.appointmentCandidate.userAddress.constituency.constituencyId " +
					   " order by model.appointmentCandidate.userAddress.constituency.name ");
		}
		sbS.append(" ,count(model.appointmentCandidate.appointmentCandidateId),count(distinct model.appointmentCandidate.appointmentCandidateId)");
		
        sq.append(sbS.toString()).append(sbM.toString()).append(sbE.toString());
        
	}catch(Exception e) {
		LOG.error("Exception raised at getQuery() method of AppointmentReportDashBoardService", e);
	}
	return sq.toString();
  }
	
	public void preInstantiationLogic(List<Object[]>  dataList,Map<Long,AppointmentLocVO> finalMap,List<Object[]> candiTypes,List<String> requestedTypes,List<String> allRequestedTypes){
		
		try{
			 for(Object[] obj : dataList)
			 {
				 
				 AppointmentLocVO locVO = new AppointmentLocVO();
				 
				 locVO.setId(obj[0]!=null ? (Long)obj[0] : 0l);
				 locVO.setName(obj[1]!=null ? obj[1].toString() : "");
				 
				  if(candiTypes !=null && candiTypes.size() >0)
				  {
					  for(Object[] param : candiTypes )
					  {
						 
						  AppointmentLocVO candiTypeVO = new AppointmentLocVO();
						  
						  candiTypeVO.setId(param[0]!=null?(Long)param[0]:0l);
						  candiTypeVO.setName(param[1]!=null?param[1].toString():"");
						  
						  if(requestedTypes!=null && requestedTypes.size() > 0)
						  {
							  for(String reqType : requestedTypes)
							  {
								  
								  AppointmentLocVO reqTypeVO = new AppointmentLocVO();
								  reqTypeVO.setName(reqType);
								  
								  if(candiTypeVO.getSubList() ==null){
									  candiTypeVO.setSubList(new ArrayList<AppointmentLocVO>());
								  }
								  candiTypeVO.getSubList().add(reqTypeVO);
							  }
						  }
						  
						  if(locVO.getSubMap()==null){
							  locVO.setSubMap(new LinkedHashMap<Long, AppointmentLocVO>()); 
						  }
						  locVO.getSubMap().put(candiTypeVO.getId(),candiTypeVO);
						  
					  }
				  }
				  
				  if(allRequestedTypes!=null && allRequestedTypes.size()>0)
				  {
					  for(String type :allRequestedTypes)
					  {
						  
						  AppointmentLocVO typeVO = new AppointmentLocVO();
						  typeVO.setName(type);
						  
						  if(locVO.getTypeList() == null){
							  locVO.setTypeList(new ArrayList<AppointmentLocVO>());
						  }
						  locVO.getTypeList().add(typeVO);
					  }
				  }
				  finalMap.put(locVO.getId(),locVO);
			 }
			 
		}catch(Exception e){
			LOG.error("Exception raised at preInstantiationLogic() method of AppointmentReportDashBoardService", e);
		}
	}

	public List<String> getAllRequestedTypes(){
		
		List<String> allRequestedTypes = new ArrayList<String>();
		allRequestedTypes.add("Requested");
		allRequestedTypes.add("Scheduled");
		allRequestedTypes.add("Waiting");
		
		return allRequestedTypes;
	}
	
	public AppointmentFieldsVO getTotalAppointmentDetails(Long appointmentId){
		
		AppointmentFieldsVO finalVO = null;
		
		SimpleDateFormat sdf        = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		
		try{
			
			   List<Long> cadreIds = new ArrayList<Long>();
			   List<Long> voterIds = new ArrayList<Long>();
			   
			   List<Long> partyCommiteeDesigCadreIds = new ArrayList<Long>();
			   Map<Long,String> publicRepresLocaMap = new HashMap<Long,String>();
			   
			   List<Object[]> apptDetails = appointmentCandidateRelationDAO.getTotalAppointmentDetails(appointmentId);
			   
			   if( apptDetails!= null && apptDetails.size() >0)
			   {
				  
				   for(Object[] obj : apptDetails)
				   {
					   
					   if( finalVO == null)
					   {
						   
						   finalVO = new AppointmentFieldsVO();
						   
						   //appt details
						   finalVO.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
						   finalVO.setUniqueId(obj[1]!=null?obj[1].toString():"");
						   finalVO.setApptUserId(obj[2]!=null?(Long)obj[2]:0l);
						   finalVO.setApptUserName(obj[3]!=null?obj[3].toString():"");
						   finalVO.setCreatedDate(obj[4]!=null?obj[4].toString():"");
						   finalVO.setPriority(obj[5]!=null?obj[5].toString():"");
						   finalVO.setReason(obj[6]!=null?obj[6].toString():"");
						   finalVO.setPreferableTimeId(obj[7]!=null?(Long)obj[7]:0l);
						   finalVO.setStatusId(obj[8]!=null?(Long)obj[8]:0l);
						   finalVO.setStatus(obj[9]!=null?obj[9].toString():"");
						   
						   //preferable dates
						   List<Long> aptmnts  = new ArrayList<Long>();
						   aptmnts.add(finalVO.getAppointmentId());
						   finalVO.setAppointmentScheduleVO(new AppointmentScheduleVO());
						   appointmentService.setPreferDatesToAppointment(aptmnts,finalVO.getAppointmentScheduleVO());
						   
						   
						   //time slot
						   finalVO.setDate(obj[10]!=null?obj[10].toString():"");
						 
						   if(obj[11]!=null){
							   Date date = sdf.parse(obj[11].toString().substring(0, 19));
							   finalVO.setFromDate(date.toString()); 
							   finalVO.setFromTime(timeFormat.format(date));
						   }
						   if(obj[12]!=null){
							   Date date = sdf.parse(obj[12].toString().substring(0, 19));
							   finalVO.setToDate(date.toString()); 
							   finalVO.setToTime(timeFormat.format(date));
						   }
						   
					   }
					   
					   AppointmentFieldsVO  candidateVO = new AppointmentFieldsVO();
					   
					   //candidate details
					   candidateVO.setCandidateId(obj[13]!=null?(Long)obj[13]:0l);
					   candidateVO.setCandidateName(obj[14]!=null?obj[14].toString():"");
					   candidateVO.setImageURL(obj[15]!=null?obj[15].toString():"");
					   candidateVO.setCandidateTypeId(obj[16]!=null?(Long)obj[16]:0l);
					   candidateVO.setCandidateType(obj[17]!=null?obj[17].toString():"");
					   candidateVO.setDesignationId(obj[18]!=null?(Long)obj[18]:0l);
					   candidateVO.setDesignation(obj[19]!=null?obj[19].toString():"");
					   candidateVO.setMobileNo(obj[20]!=null?obj[20].toString():"");
					   
					   if(obj[21]!=null){
						   voterIds.add((Long)obj[21]);
						   candidateVO.setVoterId(obj[21]!=null?(Long)obj[21]:0l);
					   }
					   
					   candidateVO.setVoterCardNo(obj[31]!=null?obj[31].toString():"");
					   candidateVO.setTdpCadreId(obj[22]!=null?(Long)obj[22]:0l);
					   candidateVO.setMemberShipId(obj[23]!=null?obj[23].toString():"");
					   
					   //location.
					   candidateVO.setState(obj[24]!=null?obj[24].toString():"");
					   candidateVO.setDistrict(obj[25]!=null?obj[25].toString():"");
					   candidateVO.setConstituency(obj[26]!=null?obj[26].toString():"");
					   candidateVO.setTehsil(obj[27]!=null?obj[27].toString():"");
					   candidateVO.setLocalElectionBody(obj[28]!=null?obj[28].toString():"");
					   candidateVO.setVillage(obj[29]!=null?obj[29].toString():"");
					   candidateVO.setWard(obj[30]!=null?obj[30].toString():"");
					   
					   //candidates designation based on appt cand type
					    Long apptcanditype = obj[16]!=null?(Long)obj[16]:null;
					    Long tdpcadreId =    obj[22]!=null?(Long)obj[22]:null;
					    
						if(apptcanditype  != null ){
							
							if(apptcanditype.longValue() == 4l){
								
								candidateVO.setCandDesignation(candidateVO.getCandidateType());
								candidateVO.setLocation(obj[26] !=null ?WordUtils.capitalize(obj[26].toString().toLowerCase())+" Constituency":"");
								
							}else if(apptcanditype.longValue() == 3l ){
								
								candidateVO.setCandDesignation(candidateVO.getCandidateType());
								if(tdpcadreId!=null && tdpcadreId>0l){
									cadreIds.add(tdpcadreId);
								}
							}else if(apptcanditype.longValue() == 2l && tdpcadreId!=null){
								partyCommiteeDesigCadreIds.add(tdpcadreId);
							}else if(apptcanditype.longValue() == 1l && tdpcadreId!=null && candidateVO.getDesignationId()>0l){
								candidateVO.setCandDesignation(candidateVO.getDesignation());
								publicRepresLocaMap.put(tdpcadreId,locationService.getLocationForDesignation(tdpcadreId,candidateVO.getDesignationId()));
							}
						}
					   
					   if (finalVO.getSubList() == null){
						   finalVO.setSubList(new ArrayList<AppointmentFieldsVO>());
					   }
					   finalVO.getSubList().add(candidateVO);
				   }
			   }
			   
			   if(cadreIds!=null && cadreIds.size()>0){
				   Map<Long,String> constMap = getConstituenciesforTdpcadreIds(cadreIds);
				   setConstituencyforTdpCadreIds(finalVO.getSubList(),constMap);
				}
			   
				if(partyCommiteeDesigCadreIds!=null && partyCommiteeDesigCadreIds.size()>0){
					Map<Long,String> partyCommiteeDesignationsMap = getPartyPositionDesignationsforTdpcadreIds(partyCommiteeDesigCadreIds);
					setConstituencyforTdpCadreIds(finalVO.getSubList(),partyCommiteeDesignationsMap);
				}
				
				if(publicRepresLocaMap!=null && publicRepresLocaMap.size()>0){
					setConstituencyforTdpCadreIds(finalVO.getSubList(),publicRepresLocaMap);
				}
				if(voterIds!=null && voterIds.size()>0){
					 Map<Long,String> constMapForVoters = getConstituenciesByVoterIds(voterIds);
					 setConstituenciesByVoterIds(finalVO.getSubList(),constMapForVoters);
				}
		}catch (Exception e){
			LOG.error("Exception raised at getTotalAppointmentDetails", e);
		}
		return finalVO;
	}
	
	public void setConstituencyforTdpCadreIds(List<AppointmentFieldsVO> list,Map<Long,String> constMap){
	
		try{
			 if(list!=null && list.size() > 0 && constMap!= null && constMap.size() >0){
				 
				 for(AppointmentFieldsVO candi : list){
					 
					 Long candidatetypeId = candi.getCandidateTypeId();
					 
					 if(candidatetypeId!=null && candi.getTdpCadreId()!=null && candi.getTdpCadreId().longValue() >0l ){
						 
						 if( candidatetypeId.longValue() == 3l || candidatetypeId.longValue() == 1l){
							 
							 String location = constMap.get(candi.getTdpCadreId());
							 candi.setLocation(location!=null && !location.isEmpty() ? WordUtils.capitalize(location.toLowerCase())+" Constituency"  : "");
						  }
						  
						 else if( candidatetypeId.longValue() == 2l){
							candi.setCandDesignation(constMap.get(candi.getTdpCadreId()));
						 }
					 }
				 }
			 }
			
		}catch(Exception e){
			LOG.error("Exception raised at setConstituencyforTdpCadreIds", e);
		}
  }
	public void setConstituenciesByVoterIds(List<AppointmentFieldsVO> list,Map<Long,String> constMap){
		
		try{
			 if(list!=null && list.size() > 0 && constMap!= null && constMap.size() >0){
				 
				 for(AppointmentFieldsVO candi : list){
					 
					 if(candi.getVoterId()!=null && candi.getVoterId().longValue() >0l ){
							 
						 String location = constMap.get(candi.getVoterId());
						 candi.setVoterConstituency(location!=null && !location.isEmpty() ? WordUtils.capitalize(location.toLowerCase())+" Constituency"  : "");
					 }
				 }
			 }
			
		}catch(Exception e){
			LOG.error("Exception raised at setConstituenciesByVoterIds", e);
		}
	}
	
	public Map<Long,String> getConstituenciesByVoterIds(List<Long> voterIds){
		
		Map<Long,String> constMap = new HashMap<Long,String>();
		if(voterIds!=null && voterIds.size() >0 ){
			List<Object[]> constlist = boothPublicationVoterDAO.getVotersConstituencyDetails(voterIds,IConstants.APPOINTMENTS_PUBLICATION_ID);
			if(constlist!=null && constlist.size()>0){
				for(Object[] obj :constlist){
					constMap.put((Long)obj[0],obj[1].toString());
				}
			}
		}
		return constMap;
	}
	
	public Map<Long,String> getConstituenciesforTdpcadreIds(List<Long> cadreIds){
		
		Map<Long,String> constMap = new HashMap<Long,String>();
		if(cadreIds!=null && cadreIds.size() >0 ){
			List<Object[]> constlist = tdpCadreDAO.getConstituencyForCadreIds(cadreIds);
			if(constlist!=null && constlist.size()>0){
				for(Object[] obj :constlist){
					constMap.put((Long)obj[0],obj[1].toString());
				}
			}
		}
		return constMap;
	}

	public Map<Long,String> getPartyPositionDesignationsforTdpcadreIds(List<Long> cadreIds){
		
		Map<Long,String> partyCommiteeDesignationsMap = new HashMap<Long, String>();
		try{
			
			 List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(cadreIds);
			 
			 if(partyPositionDetails !=null && partyPositionDetails.size()>0)
			 {
				 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
				 
				 for (Object[] partyPosition : partyPositionDetails) {
	
					 String level=partyPosition[0] != null ? partyPosition[0].toString() : "" ;
					 String role=partyPosition[1] != null ? partyPosition[1].toString() : "";
					 
					 String state = commonMethodsUtilService.getStringValueForObject(partyPosition[6]);
					 
					 String commiteestr=partyPosition[2] != null ? partyPosition[2].toString() : "";
					 
					 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
					 {
						 level = state+" "+level;
					 }
					 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
					 if(!partyPositionStr.isEmpty())
					 { 
						 partyCommiteeDesignationsMap.put((Long)partyPosition[5],partyPositionStr);
					 }
				 }
			 }
		}catch(Exception e){
			LOG.error("Exception raised at getPartyPositionDesignationsforTdpcadreIds", e);
		}
		return partyCommiteeDesignationsMap;
	}
		
}
