package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBatchStatusDAO;
import com.itgrids.partyanalyst.dao.ICampCallPurposeDAO;
import com.itgrids.partyanalyst.dao.ICampCallStatusDAO;
import com.itgrids.partyanalyst.dao.IScheduleInviteeStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDistrictDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeCallerDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeTrackDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampUserDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampUserTypeDAO;
import com.itgrids.partyanalyst.dao.hibernate.TdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;
import com.itgrids.partyanalyst.service.ITrainingCampService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TrainingCampService implements ITrainingCampService{

	public static Logger LOG = Logger.getLogger(TrainingCampService.class);
	private ITrainingCampScheduleInviteeCallerDAO trainingCampScheduleInviteeCallerDAO;
	private ITrainingCampDAO trainingCampDAO;
	private ITrainingCampDistrictDAO trainingCampDistrictDAO;
	private IScheduleInviteeStatusDAO scheduleInviteeStatusDAO;
	private ITrainingCampScheduleDAO trainingCampScheduleDAO;
	private ITrainingCampScheduleInviteeTrackDAO trainingCampScheduleInviteeTrackDAO;
	private ITrainingCampProgramDAO trainingCampProgramDAO;
	private ITrainingCampBatchDAO trainingCampBatchDAO;
	private IBatchStatusDAO batchStatusDAO;
	private ITrainingCampScheduleInviteeDAO trainingCampScheduleInviteeDAO;
	private ITrainingCampUserDAO trainingCampUserDAO;
	private ITrainingCampUserTypeDAO trainingCampUserTypeDAO;
	private ICampCallPurposeDAO campCallPurposeDAO;
	private ICampCallStatusDAO campCallStatusDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private DateUtilService dateUtilService;
	
	
	
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}

	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}

	public void setTrainingCampDAO(ITrainingCampDAO trainingCampDAO) {
		this.trainingCampDAO = trainingCampDAO;
	}

	public void setTrainingCampDistrictDAO(
			ITrainingCampDistrictDAO trainingCampDistrictDAO) {
		this.trainingCampDistrictDAO = trainingCampDistrictDAO;
	}

	public void setScheduleInviteeStatusDAO(
			IScheduleInviteeStatusDAO scheduleInviteeStatusDAO) {
		this.scheduleInviteeStatusDAO = scheduleInviteeStatusDAO;
	}

	public void setTrainingCampScheduleDAO(
			ITrainingCampScheduleDAO trainingCampScheduleDAO) {
		this.trainingCampScheduleDAO = trainingCampScheduleDAO;
	}

	public void setTrainingCampScheduleInviteeTrackDAO(
			ITrainingCampScheduleInviteeTrackDAO trainingCampScheduleInviteeTrackDAO) {
		this.trainingCampScheduleInviteeTrackDAO = trainingCampScheduleInviteeTrackDAO;
	}

	public void setTrainingCampProgramDAO(
			ITrainingCampProgramDAO trainingCampProgramDAO) {
		this.trainingCampProgramDAO = trainingCampProgramDAO;
	}

	public void setTrainingCampBatchDAO(ITrainingCampBatchDAO trainingCampBatchDAO) {
		this.trainingCampBatchDAO = trainingCampBatchDAO;
	}

	public void setBatchStatusDAO(IBatchStatusDAO batchStatusDAO) {
		this.batchStatusDAO = batchStatusDAO;
	}

	public void setTrainingCampScheduleInviteeDAO(
			ITrainingCampScheduleInviteeDAO trainingCampScheduleInviteeDAO) {
		this.trainingCampScheduleInviteeDAO = trainingCampScheduleInviteeDAO;
	}

	public void setTrainingCampUserDAO(ITrainingCampUserDAO trainingCampUserDAO) {
		this.trainingCampUserDAO = trainingCampUserDAO;
	}

	public void setTrainingCampUserTypeDAO(
			ITrainingCampUserTypeDAO trainingCampUserTypeDAO) {
		this.trainingCampUserTypeDAO = trainingCampUserTypeDAO;
	}

	public void setCampCallPurposeDAO(ICampCallPurposeDAO campCallPurposeDAO) {
		this.campCallPurposeDAO = campCallPurposeDAO;
	}

	public void setCampCallStatusDAO(ICampCallStatusDAO campCallStatusDAO) {
		this.campCallStatusDAO = campCallStatusDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public void setTrainingCampScheduleInviteeCallerDAO(
			ITrainingCampScheduleInviteeCallerDAO trainingCampScheduleInviteeCallerDAO) {
		this.trainingCampScheduleInviteeCallerDAO = trainingCampScheduleInviteeCallerDAO;
	}
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public TrainingCampScheduleVO getCallerWiseCallsDetails(List<Long> userIds,String searchTypeId,String startDateString,String endDateString)
	{
		List<TrainingCampScheduleVO> finalList=null;
		TrainingCampScheduleVO finalCallersVODetails=new TrainingCampScheduleVO();
		try { 
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    		Date startDate = sdf.parse(startDateString);
    		Date endDate=sdf.parse(endDateString);
			
			/*SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			
			Date startDate=sdf.parse("08/02/2015");
			Date endDate=sdf.parse("08/05/2015");
			
			List<Long> userIds=new ArrayList<Long>();
			
			userIds.add(1l);*/
			
			
			Map<Long,TrainingCampScheduleVO> finalmap=new HashMap<Long,TrainingCampScheduleVO>();
			
			//1)getting All status and set to list.
			  List<Object[]>  allStatus=scheduleInviteeStatusDAO.getAllStatusList();
			//DAO calls.
			  List<Object[]> totalAssignedList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds, startDate, endDate, null);//callerId,count
			  List<Object[]> completedCallsList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds, startDate, endDate, "completedCount");
			  List<Object[]> pendingCallsList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds,startDate,endDate,"pendingCount");
		      List<Object[]> callStatusOfinviteesList = trainingCampScheduleInviteeCallerDAO.getCallStatusContsOfInvitees(userIds,startDate,endDate);//0.callerId,1.scheduleInviteeStatusId,2.status,3.count
		      
			
			//iterating.
			Map<Long,Long> assignedMap=new HashMap<Long, Long>();
			if(totalAssignedList !=null && totalAssignedList.size()>0){
				setResultToMap(totalAssignedList,assignedMap);
			}
			
			Map<Long,Long> completedMap=new HashMap<Long, Long>();
			if(completedCallsList !=null && completedCallsList.size()>0){
				setResultToMap(completedCallsList,completedMap);
			}
			
			Map<Long,Long> pendingMap=new HashMap<Long, Long>();

			if(pendingCallsList !=null && pendingCallsList.size()>0){
				setResultToMap(pendingCallsList,pendingMap);
			}
			
			//FinalMap For Caller Count
			if(assignedMap !=null && assignedMap.size()>0){
				setResultToAssignedMap(assignedMap,finalmap,"assigned",allStatus,null);
			}
			if(completedMap !=null && completedMap.size() > 0){
				setResultToAssignedMap(completedMap,finalmap,"completed",allStatus,null);
			}
			if(pendingMap !=null && pendingMap.size()>0){
				setResultToAssignedMap(pendingMap,finalmap,"pending",allStatus,null);
			}
			
			if(callStatusOfinviteesList !=null && callStatusOfinviteesList.size()>0){
				setResultToAssignedMap(null,finalmap,"status",allStatus,callStatusOfinviteesList);
			}
			
			
			if(finalmap!=null && finalmap.size()>0){
				finalList=new ArrayList<TrainingCampScheduleVO>(finalmap.values());
			}
			
			//totalAssignedCount
			Long totalAssignedCountofAgents=0l;
			if(finalList !=null && finalList.size()>0){
				for (TrainingCampScheduleVO vo : finalList) {
					totalAssignedCountofAgents=totalAssignedCountofAgents+vo.getAssignedCallsCount();
				}
			}
			
			
			//building For allocated Calls
			finalCallersVODetails.setTotalAssignedCount(totalAssignedCountofAgents);//allocating totalAssigned Count
			
			Long countForTotalCallers=trainingCampScheduleInviteeCallerDAO.getAllCallersCount(startDate,endDate,"totalCallers");
			if(countForTotalCallers !=null){
				finalCallersVODetails.setTotalCount(countForTotalCallers);//allocating calls To caller
			}
				
			Date currentDate =dateUtilService.getCurrentDateAndTime();

			Long todayAllocatedCount= trainingCampScheduleInviteeCallerDAO.getAllCallersCount(currentDate,null,"todayCallers");
			
			if(todayAllocatedCount !=null){
				finalCallersVODetails.setTodayAllocatedCalls(todayAllocatedCount);//today Allocated Calls Count
			}
			
			
			 List<Object[]> totalDialedCalls=trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds, startDate, endDate, "dialedCalls");
			
			 Long dialedCallsCount =0l;
			if(totalDialedCalls !=null && totalDialedCalls.size()>0){
				for(Object[] dialed:totalDialedCalls){
					if(dialed[1] !=null){
						dialedCallsCount=dialedCallsCount+(Long)dialed[1] ;//allocating dialedCallsCount
					}
				}
			}
			
			finalCallersVODetails.setDialedCallsCount(dialedCallsCount);
			 //
			 
			
			if(finalList !=null && finalList.size()>0)
			{
				finalCallersVODetails.setTrainingCampVOList(finalList);
			}
		}catch (Exception e){
			LOG.error(" Exception occured in getCallerWiseCallsDetails method in TrainingCampService class.",e);
		}
		return finalCallersVODetails;
	}
	public void setResultToMap(List<Object[]> listObj,Map<Long,Long> corespondentmap)
	{
		try{
			if(listObj !=null && listObj.size()>0){
				for(Object[] Obj:listObj){
					Long assignedCount=Obj[1] !=null ? Long.parseLong(Obj[1].toString()):0l;
					corespondentmap.put(Long.parseLong(Obj[0].toString()),assignedCount);
				}
			}
		}catch (Exception e) {
			LOG.error(" Exception occured in setResultToMap method in TrainingCampService class.",e);
		}
	}
	public void setResultToAssignedMap(Map<Long,Long> assignedMap,Map<Long,TrainingCampScheduleVO> finalMap,String type,List<Object[]> allStatus,List<Object[]> callStatusOfinviteesList){
		
		
		try{
			if(type.equalsIgnoreCase("status")){
				
				for(Object[] obj:callStatusOfinviteesList){////0.callerId,1.scheduleInviteeStatusId,2.status,3.count,name
					TrainingCampScheduleVO assignCampVo =finalMap.get((Long)obj[0]);
					boolean agentExist=true;
					
					if(assignCampVo==null){
						agentExist=false;
						assignCampVo=new TrainingCampScheduleVO();
						assignCampVo.setId((Long)obj[0]);
						assignCampVo.setName("");
						assignCampVo.setAssignedCallsCount(0l);
						assignCampVo.setCompletedCallsCount(0l);
						assignCampVo.setPendingCallsCount(0l);
						setStatusSchedules(assignCampVo,allStatus);
					}
					if(obj[1]!=null){
						Long statusId=(Long)obj[1];
						List<TrainingCampScheduleVO> schedulesList=assignCampVo.getTrainingCampVOList();
						if(schedulesList!=null && schedulesList.size()>0){
							for(TrainingCampScheduleVO schedule:schedulesList){
								if(schedule.getStatusId().longValue()==statusId.longValue()){
									schedule.setId((Long)obj[0]);
									schedule.setName("");
									schedule.setCount(obj[3]!=null?(Long)obj[3]:0l);
								}
							}
								
						}
					}
					if(!agentExist){
						finalMap.put((Long)obj[0], assignCampVo);
					}
					
				}
				
			}else{
				for (Entry<Long, Long> assignedEntry : assignedMap.entrySet()){
					
					Long callerId=assignedEntry.getKey();
					Long assingedCount=assignedEntry.getValue();
					
					TrainingCampScheduleVO assignCampVo =finalMap.get(callerId);
					boolean agentExist=true;
					
					if(assignCampVo ==null){
						agentExist=false;
						assignCampVo=new TrainingCampScheduleVO();
						assignCampVo.setId(callerId);
						assignCampVo.setName("");
						assignCampVo.setAssignedCallsCount(0l);
						assignCampVo.setCompletedCallsCount(0l);
						assignCampVo.setPendingCallsCount(0l);
						setStatusSchedules(assignCampVo,allStatus);
					}
					if(type.equalsIgnoreCase("assigned")){
						assignCampVo.setAssignedCallsCount(assingedCount);
					}
					else if(type.equalsIgnoreCase("completed")){
						assignCampVo.setCompletedCallsCount(assingedCount);
					}
					else if(type.equalsIgnoreCase("pending")){
						assignCampVo.setPendingCallsCount(assingedCount);
					}
					if(!agentExist){
						finalMap.put(callerId, assignCampVo);
					}
				}
			}
		}
		catch (Exception e) {
			LOG.error(" Exception occured in setResultToAssignedMap method in TrainingCampService class.",e);
		}
		
	}
	
	public void setStatusSchedules(TrainingCampScheduleVO assignCampVo,List<Object[]> allStatus){
		try{
			List<TrainingCampScheduleVO> statusScheduleList=new ArrayList<TrainingCampScheduleVO>();
			if(allStatus !=null && allStatus.size()>0){
				for(Object[] status:allStatus){
					TrainingCampScheduleVO trainingCampScheduleVO=new TrainingCampScheduleVO();
					trainingCampScheduleVO.setStatusId(status[0] !=null ? Long.parseLong(status[0].toString()) : 0l);
					trainingCampScheduleVO.setStatus(status[1] !=null ? status[1].toString() :"");
					statusScheduleList.add(trainingCampScheduleVO);
				  }
			}
			assignCampVo.setTrainingCampVOList(statusScheduleList);
			
		}catch (Exception e) {
			LOG.error(" Exception occured in setStatusSchedules method in TrainingCampService class.",e);
		}
	}
	
	public TrainingCampVO getCampusWiseBatchWiseMembersDetails(List<Long> callerIdsList,String searchTypeId,String startDate,String endDate)
	{
		TrainingCampVO returnVO = null;
		try {
			if(callerIdsList == null || callerIdsList.size() == 0)
			{
				//trainingCampScheduleInviteeDAO.g
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getCampusWiseBatchWiseMembersDetails method in TrainingCampService class.",e);
		}
		
		return returnVO;
	}
	
	
	public ResultStatus assignInviteesToCallersForScheduleConfirmation(Long inviteesCount, Long adminId, Long callerId,String searchTypeId,String startDate,String endDate)
	{
		ResultStatus status = new ResultStatus();
		try {
			
		} catch (Exception e) {
			LOG.error(" Exception occured in assignInviteesToCallersForScheduleConfirmation method in TrainingCampService class.",e);
		}
		return status;
	}
	
	public ResultStatus assignInviteesToCallersForBatchConfirmation(Long inviteesCount, Long adminId, Long callerId,String searchTypeId,String startDate,String endDate)
	{
		ResultStatus status = new ResultStatus();
		try {
			
		} catch (Exception e) {
			LOG.error(" Exception occured in assignInviteesToCallersForBatchConfirmation method in TrainingCampService class.",e);
		}
		return status;
	}
	
	public TrainingCampVO getCampusWiseDateWiseInterestedMembersDetails(List<Long> campusIdsList,String searchTypeId,String startDate,String endDate)
	{
		TrainingCampVO returnVO = null;
		try {
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getCampusWiseDateWiseInterestedMembersDetails method in TrainingCampService class.",e);
		}
		return returnVO;
	}
	
	
	public TrainingCampVO getCampusWiseDateWiseCampDetails(List<Long> campusIdsList,String searchTypeId,String startDate,String endDate)
	{
		TrainingCampVO returnVO = null;
		try {
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getCampusWiseDateWiseCampDetails method in TrainingCampService class.",e);
		}
		return returnVO;
	}
	
	public List<TraingCampCallerVO> getScheduleCallStatusCount(Long userId,Long callPurposeId)
	{
		List<TraingCampCallerVO> returnList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getScheduleWiseCallStatusCount(userId,callPurposeId);
			if(list != null)
			{
				
				for(Object[] params : list)
				{
					TraingCampCallerVO programVo  = getMatchedVo(returnList,(Long)params[1]); // Program
						if(programVo == null)
						{
							programVo = new TraingCampCallerVO()	;
							programVo.setId((Long)params[1]);
							programVo.setName(params[2].toString());
							returnList.add(programVo);
						}
						
						TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),(Long)params[3]); // Camp
						if(campVo == null)
						{
							campVo = new TraingCampCallerVO()	;
							campVo.setId((Long)params[3]);
							campVo.setName(params[4].toString());
							
							programVo.getSubList().add(campVo);
						}
						TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),(Long)params[5]); // Schedule
						if(scheduleVo == null)
						{
							scheduleVo = new TraingCampCallerVO()	;
							scheduleVo.setId((Long)params[5]);
							scheduleVo.setName(params[6].toString());
							scheduleVo.setSubList(getStatusList());
							scheduleVo.setScheduleStatusList(getScheduleStatusList());
							campVo.getSubList().add(scheduleVo);
						}
						scheduleVo.setTotal(scheduleVo.getTotal() + (Long)params[0]);
						TraingCampCallerVO schedulestatusVo  = getMatchedVo(scheduleVo.getScheduleStatusList(),(Long)params[9]); // Status
						if(schedulestatusVo != null)
						{
							schedulestatusVo.setCount(schedulestatusVo.getCount() + (Long)params[0]);
						}
						if(params[7] != null)
						{
							TraingCampCallerVO statusVo  = getMatchedVo(scheduleVo.getSubList(),(Long)params[7]); // Status
							if(statusVo != null)
							{
								statusVo.setCount(statusVo.getCount() + (Long)params[0]);
							}
						}
						
				}
			}
			
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService getScheduleCallStatusCount() method", e);
		}
		return returnList;
	}
	public List<TraingCampCallerVO> getBatchCallStatusCount(Long userId,Long callPurposeId)
	{
		List<TraingCampCallerVO> returnList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getBatchWiseWiseCallStatusCount(userId,callPurposeId);
			if(list != null)
			{
				
				for(Object[] params : list)
				{
					TraingCampCallerVO programVo  = getMatchedVo(returnList,(Long)params[1]); // Program
						if(programVo == null)
						{
							programVo = new TraingCampCallerVO()	;
							programVo.setId((Long)params[1]);
							programVo.setName(params[2].toString());
							returnList.add(programVo);
						}
						
						TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),(Long)params[3]); // Camp
						if(campVo == null)
						{
							campVo = new TraingCampCallerVO()	;
							campVo.setId((Long)params[3]);
							campVo.setName(params[4].toString());
							
							programVo.getSubList().add(campVo);
						}
						TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),(Long)params[5]); // Schedule
						if(scheduleVo == null)
						{
							scheduleVo = new TraingCampCallerVO()	;
							scheduleVo.setId((Long)params[5]);
							scheduleVo.setName(params[6].toString());
							campVo.getSubList().add(scheduleVo);
						}
						
						TraingCampCallerVO batchVo  = getMatchedVo(scheduleVo.getSubList(),(Long)params[10]); // Batch
						if(batchVo == null)
						{
							batchVo = new TraingCampCallerVO()	;
							batchVo.setId((Long)params[10]);
							batchVo.setName(params[11].toString());
							batchVo.setSubList(getStatusList());
							batchVo.setScheduleStatusList(getScheduleStatusList());
							scheduleVo.getSubList().add(batchVo);
							scheduleVo.setSpanCnt(new Long(scheduleVo.getSubList().size()));
							programVo.setSpanCnt(programVo.getSpanCnt() + 1);
							campVo.setSpanCnt(new Long(scheduleVo.getSubList().size()));
						} 
						batchVo.setTotal(batchVo.getTotal() + (Long)params[0]);
						TraingCampCallerVO batchstatusVo  = getMatchedVo(batchVo.getScheduleStatusList(),(Long)params[9]); // Status
						if(batchstatusVo != null)
						{
							batchstatusVo.setCount(batchstatusVo.getCount() + (Long)params[0]);
						}
						if(params[7] != null)
						{
							TraingCampCallerVO batchstatusVo1  = getMatchedVo(batchVo.getSubList(),(Long)params[7]); // Status
							if(batchstatusVo1 != null)
							{
								batchstatusVo1.setCount(batchstatusVo1.getCount() + (Long)params[0]);
							}
						}
						
				}
			}
			
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService getScheduleCallStatusCount() method", e);
		}
		return returnList;
	}
	public TrainingMemberVO getScheduleCallMemberDetails(TraingCampDataVO inputVo)
	{
		List<Long> statusIds = new ArrayList<Long>();
		TrainingMemberVO inputVO = new TrainingMemberVO();
		
		try{
			statusIds = getCallStatusIds(inputVo.getStatus());
			
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getScheduleWisememberDetailsCount(inputVo,statusIds,inputVo.getStatusType(),inputVo.getStatus());
			if(list != null && list.size() > 0)
			{
				List<TrainingMemberVO> resultList = setMemberDetails(list);
				inputVO.setSubList(resultList);
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService getScheduleCallMemberDetails() method", e);
		}
		return inputVO;
	}
	
	
	public List<Long> getCallStatusIds(String status)
	{
		List<Long> statusIds = new ArrayList<Long>();
		if(status.equalsIgnoreCase("dialed"))
		{
			statusIds.add(1l);statusIds.add(2l);statusIds.add(3l); //all
		}
		else if(status.equalsIgnoreCase("answered"))
		{
			statusIds.add(1l); // answered
		}
		else if(status.equalsIgnoreCase("busy"))
		{
			statusIds.add(2l);statusIds.add(3l); // userbusy and switchOff
		}
		else if(status.equalsIgnoreCase("callback"))
		{
			statusIds.add(6l);statusIds.add(7l);
		}
		else if(status.equalsIgnoreCase("interested"))
		{
			statusIds.add(4l);
		}
		else if(status.equalsIgnoreCase("notInterested"))
		{
		statusIds.add(5l);
		}
		else if(status.equalsIgnoreCase("later"))
		{
		statusIds.add(3l);
		}
		return statusIds;	
	}
	public List<TrainingMemberVO> setMemberDetails(List<Object[]> list)
	{
		List<Long> cadreIds = new ArrayList<Long>();
		List<TrainingMemberVO>  returnList = new ArrayList<TrainingMemberVO>();
		try{
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					TrainingMemberVO vo =new TrainingMemberVO();
					vo.setId((Long)params[0]);
					String fname = params[1] != null ? params[1].toString() : "";
					String lname = params[2] != null ? params[2].toString() : "";
					vo.setName(fname +" "+lname);
					vo.setMobileNumber(params[3] != null ?params[3].toString() : "");
					vo.setImage(params[4] != null ?params[4].toString() : "");
					vo.setStatus(params[6] != null ?params[6].toString() : "");
					vo.setAge(params[7] != null ?params[7].toString() : "");
					vo.setLocation(params[8] != null ?params[8].toString() : "");
					returnList.add(vo);
					if(!cadreIds.contains((Long)params[0]))
						cadreIds.add((Long)params[0]);
				}
				 List<Object[]> roles = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(cadreIds);
				 if(roles != null && roles.size() > 0)
				 {
					 for(Object[] params : roles)
					 {
						 TrainingMemberVO vo =  getMatchedVo1(returnList, (Long)params[0]);
						 if(vo != null)
						 {
							 vo.setRole(params[2] != null ?params[2].toString() : "");
						 }
					 }
				 }
				 
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService setMemberDetails() method", e);
		}
		return returnList;
	}
	
	public List<TraingCampCallerVO> getStatusList()
	{
		List<TraingCampCallerVO> statusList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = campCallStatusDAO.getCallStatusList();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					TraingCampCallerVO vo = new TraingCampCallerVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1].toString());
					statusList.add(vo);
				}
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return statusList;
	}
	
	public List<TraingCampCallerVO> getScheduleStatusList()
	{
		List<TraingCampCallerVO> statusList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = scheduleInviteeStatusDAO.getAllStatusList();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					TraingCampCallerVO vo = new TraingCampCallerVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1].toString());
					statusList.add(vo);
				}
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return statusList;
	}
	public TraingCampCallerVO getMatchedVo(List<TraingCampCallerVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(TraingCampCallerVO vo : resultList)
		{
			if(vo.getId().longValue() == id.longValue())
			{
			return vo;	
			}
		}
		return null;
	}
	public TrainingMemberVO getMatchedVo1(List<TrainingMemberVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(TrainingMemberVO vo : resultList)
		{
			if(vo.getId().longValue() == id.longValue())
			{
			return vo;	
			}
		}
		return null;
	}
	public List<Long> getTrainingCampUserTypeIds(){
	
		List<Long> users=trainingCampUserDAO.getTrainingCampUserTypeIds(5l);
		
		return users;
	}
	
	public List<BasicVO> getAllPrograms()
	{
		try{
			List<Object[]> programs = trainingCampProgramDAO.getPrograms();
			return getBasicList(programs);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<BasicVO> getAllschedules()
	{
		try{
			List<Object[]> schedules = trainingCampScheduleDAO.getSchedules();
			return getBasicList(schedules);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<BasicVO> getBasicList(List<Object[]> list)
	{
		List<BasicVO>  returnList = new ArrayList<BasicVO>();
		try{
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					BasicVO vo = new BasicVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1] != null ? params[1].toString() : "");
					returnList.add(vo);
					
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return returnList;
	}
	
  public TrainingCampScheduleVO getTrainingProgramMembersBatchCount(String startDateString,String endDateString){
		
		TrainingCampScheduleVO finalTrainingVo=new TrainingCampScheduleVO();
		
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    		Date startDate = sdf.parse(startDateString);
    		Date endDate=sdf.parse(endDateString);
			
			/*SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			
			Date startDate=sdf.parse("09/01/2015");
			Date endDate=sdf.parse("09/30/2015");*/
			
		
			//0.id,1.program/camp name 3.membersCount 4.batchCount 
			List<Object[]> programDetails = trainingCampScheduleInviteeDAO.getTrainingProgramMembersBatchCount(startDate, endDate, "Interested","program");
			List<Object[]> campDetails = trainingCampScheduleInviteeDAO.getTrainingProgramMembersBatchCount(startDate, endDate, "Interested","camp");
			
			
			List<TrainingCampScheduleVO> listForProgramVo=new ArrayList<TrainingCampScheduleVO>();
			List<TrainingCampScheduleVO> listForCampVo=new ArrayList<TrainingCampScheduleVO>();
			
			if(programDetails !=null && programDetails.size()>0){
				setListObjectsForTrainingProgramMembersBatchCount(programDetails,listForProgramVo,"program");
			}
			if(campDetails !=null && campDetails.size()>0){
				setListObjectsForTrainingProgramMembersBatchCount(campDetails,listForCampVo,"camp");
			}
			
			if(listForProgramVo !=null && listForProgramVo.size()>0){
				finalTrainingVo.setTrainingCampVOList(listForProgramVo);//ProgramWise List
			}
			if(listForCampVo !=null && listForCampVo.size()>0){
				finalTrainingVo.setTrainingCampScheduleVOList(listForCampVo);//CampWise List
			}
			
			return finalTrainingVo;
	
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return finalTrainingVo;
		
	}
	public void setListObjectsForTrainingProgramMembersBatchCount(List<Object[]> programDetails,List<TrainingCampScheduleVO> listVo,String type){
		
		for (Object[] objects : programDetails) {
			TrainingCampScheduleVO progamVo=new TrainingCampScheduleVO();
			
			progamVo.setId((Long)objects[0]);
			progamVo.setName(objects[1] !=null ? objects[1].toString() : "");
			progamVo.setTotalCount(objects[2] !=null ? (Long)objects[2] : 0l );//members Count
			progamVo.setCount(objects[3] !=null ? (Long)objects[3]:0l);//batchCount
			
			listVo.add(progamVo);
		}
	}
	
	public List<TrainingCampScheduleVO> getScheduleAndConfirmationCallsOfCallerToAgent(List<Long> userIds,String startDateString,String endDateString){
		
		List<TrainingCampScheduleVO> finalList=null;
		try{
			
			/*SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			
			Date startDate=sdf.parse("08/02/2015");
			Date endDate=sdf.parse("08/05/2015");
			
			List<Long> userIds=new ArrayList<Long>();
			
			userIds.add(1l);*/
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    		Date startDate = sdf.parse(startDateString);
    		Date endDate=sdf.parse(endDateString);
			
			
			
			List<Object[]>  scheduleAndConfirmationCallsTotal= trainingCampScheduleInviteeCallerDAO.getScheduleAndConfirmationCallsOfCallerToAgent(userIds,startDate,endDate,"totalCalls");
			List<Object[]> scheduleAndConfirmationCallsDialed=trainingCampScheduleInviteeCallerDAO.getScheduleAndConfirmationCallsOfCallerToAgent(userIds,startDate,endDate,"dialedCalls");
			
			Map<Long,TrainingCampScheduleVO> finalVo=new HashMap<Long, TrainingCampScheduleVO>();
			
			List<Object[]>  allPurposes = campCallPurposeDAO.getAllCampCallPurpose();
			
			if(allPurposes !=null && allPurposes.size()>0){
				
				for(Object[] purpose:allPurposes){
					TrainingCampScheduleVO vo = new TrainingCampScheduleVO();
					vo.setId(purpose[0] !=null ? (Long)purpose[0] :0l);
					vo.setName(purpose[1] !=null ? purpose[1].toString() : "");
					vo.setCount(0l);//call Purpose wise count
					vo.setDialedCallsCount(0l);//dialed count forcall purpose
					
					finalVo.put(vo.getId(), vo);
				}
				
				
			}
			
			if(scheduleAndConfirmationCallsTotal !=null && scheduleAndConfirmationCallsTotal.size()>0){
				setScheduleAndConfirmationCallsOfCallerToAgent(scheduleAndConfirmationCallsTotal,finalVo,"total");
			}
			if(scheduleAndConfirmationCallsDialed !=null && scheduleAndConfirmationCallsDialed.size()>0){
				setScheduleAndConfirmationCallsOfCallerToAgent(scheduleAndConfirmationCallsDialed,finalVo,"dialed");
			}
			
			if(finalVo !=null && finalVo.size()>0){
				finalList=new ArrayList<TrainingCampScheduleVO>(finalVo.values());
			}
			
			return finalList;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return finalList;
	}
	public void setScheduleAndConfirmationCallsOfCallerToAgent(List<Object[]>  scheduleAndConfirmationCalls,Map<Long,TrainingCampScheduleVO> finalVo,String type){
		
		if(scheduleAndConfirmationCalls !=null && scheduleAndConfirmationCalls.size()>0){
			
			for (Object[] objects : scheduleAndConfirmationCalls) {
				
				TrainingCampScheduleVO scheduleVo =finalVo.get((Long)objects[0]);
				
				if(scheduleVo ==null){
					scheduleVo=new TrainingCampScheduleVO();
				}
				scheduleVo.setId((Long)objects[0]);
				scheduleVo.setName(objects[1].toString());
				
				if(type.equalsIgnoreCase("total")){
					scheduleVo.setCount(objects[2] !=null ? Long.parseLong(objects[2].toString()):0l);
				}
				else if(type.equalsIgnoreCase("dialed")){
					scheduleVo.setDialedCallsCount(objects[2] !=null ? Long.parseLong(objects[2].toString()) : 0l);
				}
				
				finalVo.put(scheduleVo.getId(), scheduleVo);
			}
			
		}
		
	}
	
	
	
}
