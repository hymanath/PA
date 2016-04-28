package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateTypeDAO;
import com.itgrids.partyanalyst.dto.AppointmentLocVO;
import com.itgrids.partyanalyst.service.IAppointmentReportDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AppointmentReportDashBoardService implements IAppointmentReportDashBoardService {
		
	private static Logger LOG = Logger.getLogger(AppointmentReportDashBoardService.class);
	
	//ATTRIBUTES
	private IAppointmentCandidateRelationDAO appointmentCandidateRelationDAO;
	private IAppointmentCandidateTypeDAO    appointmentCandidateTypeDAO;
	
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
			
			
			List<Long>  scheduledList = Arrays.asList(IConstants.APPOINTMENT_STATUS_SCHEDULED_LIST);
			String totalScheduledQuery = getQuery(appointmentUserId,scheduledList,startDate,endDate,locType,stateId);
			List<Object[]>  totalScheduledCountsList = appointmentCandidateRelationDAO.getTotalCountCandiByLocation(totalScheduledQuery,appointmentUserId,scheduledList,startDate,endDate);
			
			List<Long>  waitingList = Arrays.asList(IConstants.APPOINTMENT_STATUS_WAITING_LIST);
			String totalWaitingQuery = getQuery(appointmentUserId,waitingList,startDate,endDate,locType,stateId);
			List<Object[]>  totalWaitingCountsList = appointmentCandidateRelationDAO.getTotalCountCandiByLocation(totalWaitingQuery,appointmentUserId,waitingList,startDate,endDate);
			
			setTotalCountsToLocation(totalReqCountsList,finalMap,"requested");
			setTotalCountsToLocation(totalScheduledCountsList,finalMap,"scheduled");
			setTotalCountsToLocation(totalWaitingCountsList,finalMap,"waiting");
			 
			
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
			setFilteredCountsToLocation(filterReqCountsList,finalMap,"requested");
			setFilteredCountsToLocation(filterScheduledCountsList,finalMap,"scheduled");
			setFilteredCountsToLocation(filterWaitingCountsList,finalMap,"waiting");
			
			
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
	

	public void setFilteredCountsToLocation(List<Object[]> dataList,Map<Long,AppointmentLocVO> finalMap,String reqType){
		try{
			 if(dataList != null && dataList.size() >0){
				 for(Object[] obj : dataList){
					 
					 Long locId = obj[0]!=null ?(Long)obj[0]:0l; 
					 AppointmentLocVO locVO = finalMap.get(locId);
					 if(locVO !=null){
						 Long candiTypeId = obj[2]!=null ?(Long)obj[2]:0l;
						 if(candiTypeId.longValue() > 0l){
							 AppointmentLocVO candiVO = locVO.getSubMap().get(candiTypeId);
							 if(candiVO!=null){
								 AppointmentLocVO typeVO = getMatchedRequestedType(candiVO.getSubList(),reqType);
								 if(typeVO!=null){
									 typeVO.setCount(obj[3]!=null ?(Long)obj[3]:0l);
									 typeVO.setUniqueCount(obj[4]!=null ?(Long)obj[4]:0l);
								 }
							 }
						 }
						 
					 }
				 }
			 }
			
		}catch(Exception e){
			LOG.error("Exception raised at setFilteredCountsToLocation() method of AppointmentReportDashBoardService", e);
		}
		
	}
	public void setTotalCountsToLocation(List<Object[]> dataList,Map<Long,AppointmentLocVO> finalMap,String reqType){
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
}
