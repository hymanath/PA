package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCadreVO;
import com.itgrids.partyanalyst.dto.TrainingCampCallStatusVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeCaller;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeTrack;
import com.itgrids.partyanalyst.service.ITrainingCampService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TrainingCampService implements ITrainingCampService{

	public static Logger LOG = Logger.getLogger(TrainingCampService.class);
	private ITrainingCampScheduleInviteeCallerDAO 	trainingCampScheduleInviteeCallerDAO;
	private ITrainingCampDAO 						trainingCampDAO;
	private ITrainingCampDistrictDAO 				trainingCampDistrictDAO;
	private IScheduleInviteeStatusDAO 				scheduleInviteeStatusDAO;
	private ITrainingCampScheduleDAO 				trainingCampScheduleDAO;
	private ITrainingCampScheduleInviteeTrackDAO 	trainingCampScheduleInviteeTrackDAO;
	private ITrainingCampProgramDAO 				trainingCampProgramDAO;
	private ITrainingCampBatchDAO 					trainingCampBatchDAO;
	private IBatchStatusDAO 						batchStatusDAO;
	private ITrainingCampScheduleInviteeDAO 		trainingCampScheduleInviteeDAO;
	private ITrainingCampUserDAO 					trainingCampUserDAO;
	private ITrainingCampUserTypeDAO 				trainingCampUserTypeDAO;
	private ICampCallPurposeDAO 					campCallPurposeDAO;
	private ICampCallStatusDAO 						campCallStatusDAO;
	private TransactionTemplate            			transactionTemplate;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private DateUtilService dateUtilService;
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
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
	
	public List<BasicVO> getAllPrograms()
	{
		try{
			List<Object[]> programs = trainingCampProgramDAO.getPrograms();
			return getBasicList1(programs);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	public List<IdNameVO> getAllTrainingCampsInfoByDistrictIds(List<Long> districtIds)
	{
		List<IdNameVO> trainingCampsList = null;
		try {
			List<Object[]> trainingCamps = trainingCampDistrictDAO.getCampDetailsByDistrictIds(districtIds);
			if(trainingCamps != null && trainingCamps.size()>0){
				trainingCampsList = new ArrayList<IdNameVO>();
				for (Object[] camp : trainingCamps) {
					Long campId = commonMethodsUtilService.getLongValueForObject(camp[0]);
					String campName = commonMethodsUtilService.getStringValueForObject(camp[1]);
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(campId);
					idNameVO.setName(campName);
					trainingCampsList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAllScheduleInfo method in TrainingCampService class.",e);
		}
		return trainingCampsList;
	}
	
	public List<IdNameVO> getProgrammesDetailsByCamps(List<Long> campIdsList)
	{
		List<IdNameVO> programmesList = null;
		try {
			List<Object[]> programmes = trainingCampScheduleDAO.getProgrammesListByCampsList(campIdsList);
			if(programmes != null && programmes.size()>0){
				programmesList = new ArrayList<IdNameVO>();
				for (Object[] camp : programmes) {
					Long campId = commonMethodsUtilService.getLongValueForObject(camp[0]);
					String campName = commonMethodsUtilService.getStringValueForObject(camp[1]);
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(campId);
					idNameVO.setName(campName);
					programmesList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAllScheduleInfo method in TrainingCampService class.",e);
		}
		return programmesList;
	}
	
	public List<IdNameVO> getScheduledDetailsByProgrammes(List<Long> programIds)
	{
		List<IdNameVO> scheduleList = null;
		try {
			List<Object[]> programmes = trainingCampScheduleDAO.getScheduledDetailsByProgrammes(programIds);
			if(programmes != null && programmes.size()>0){
				scheduleList = new ArrayList<IdNameVO>();
				for (Object[] camp : programmes) {
					Long campId = commonMethodsUtilService.getLongValueForObject(camp[0]);
					String campName = commonMethodsUtilService.getStringValueForObject(camp[1]);
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(campId);
					idNameVO.setName(campName);
					scheduleList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAllScheduleInfo method in TrainingCampService class.",e);
		}
		return scheduleList;
	}
	
	public Long getAvailableCountForMemberConfirmation(Long scheduleId)
	{
		Long availableCount = 0L;
		try {
			availableCount = trainingCampScheduleInviteeDAO.getAvailableInvitedCandidatesListByScheduleIdAndCount(scheduleId);
			if(availableCount != null && availableCount.longValue()>0L)
			{
				Long alreadyAssignedCount = trainingCampScheduleInviteeDAO.getAreadyAssignedCandidatesListByScheduleIdAndCount(scheduleId);
				if(alreadyAssignedCount != null && alreadyAssignedCount.longValue()>0L){
					availableCount = availableCount - alreadyAssignedCount;
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAvailableCountForMemberConfirmation method in TrainingCampService class.",e);
		}
		return availableCount;
	}
	
	public TrainingMemberVO getAvailableMembersCountDetails(Long scheduleId,Long callerId)
	{
		TrainingMemberVO returnVO = new TrainingMemberVO();
		try {
			List<Long> alreadyInvitedMemberIdsForCallerList = trainingCampScheduleInviteeCallerDAO.getInterestedAndInvitedMembersListForBatchConfirmation( callerId, scheduleId, "Invitation", "Interested");
			List<Long> AllalreadyInvitedMemberIdsForCallerList = trainingCampScheduleInviteeCallerDAO.getInterestedAndInvitedMembersListForBatchConfirmation( null, scheduleId, "Invitation", "Interested");
			
			if(alreadyInvitedMemberIdsForCallerList != null && alreadyInvitedMemberIdsForCallerList.size()>0)
				returnVO.setAvailableCount(commonMethodsUtilService.getIntegerToLong(alreadyInvitedMemberIdsForCallerList.size()));
			if(AllalreadyInvitedMemberIdsForCallerList != null && AllalreadyInvitedMemberIdsForCallerList.size()>0)
				returnVO.setTotalCount(commonMethodsUtilService.getIntegerToLong(AllalreadyInvitedMemberIdsForCallerList.size()));
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getAvailableMembersCountDetails method in TrainingCampService class.",e);
			returnVO = null;
		}
		return returnVO;
	}
	
	public ResultStatus assignMembersToCallerForMemberConfirmation(final Long userId, final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId)
	{
		ResultStatus status  = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					List<Long> alreadyInvitedMemberIdsList = trainingCampScheduleInviteeCallerDAO.getAlreadyInvitedMembersInviteeIdsListByScheduleId(scheduleId,callPurposeId);
					List<Long> invitedMemberIdsList = trainingCampScheduleInviteeDAO.getInvitedCandidatesListByScheduleIdAndCount(scheduleId,null,Integer.valueOf(membersCount.toString()));
					if(invitedMemberIdsList != null && invitedMemberIdsList.size()>0)
					{
						DateUtilService dateutilService = new DateUtilService();
						if(alreadyInvitedMemberIdsList == null) alreadyInvitedMemberIdsList = new ArrayList<Long>(0);
						for (Long trainingCampScheduleInviteeId : invitedMemberIdsList) {
							if(!alreadyInvitedMemberIdsList.contains(trainingCampScheduleInviteeId))
							{
								TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = new TrainingCampScheduleInviteeCaller();
								trainingCampScheduleInviteeCaller.setTrainingCampScheduleInviteeId(trainingCampScheduleInviteeId);
								trainingCampScheduleInviteeCaller.setCallPurposeId(callPurposeId);
								trainingCampScheduleInviteeCaller.setTrainingCampCallerId(callerId);
								trainingCampScheduleInviteeCaller.setInsertedBy(userId);
								trainingCampScheduleInviteeCaller.setUpdatedBy(userId);
								trainingCampScheduleInviteeCaller.setInsertedTime(dateutilService.getCurrentDateAndTime());
								trainingCampScheduleInviteeCaller.setUpdatedTime(dateutilService.getCurrentDateAndTime());
								trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
							}
						}
					}
				}
			});			
			status.setResultCode(0);
			status.setMessage("SUCCESS");
		} catch (Exception e) {
			status.setResultCode(1);
			status.setMessage("FAILURE");
			LOG.error(" Exception occured in assignMembersToCallerForMemberConfirmation method in TrainingCampService class.",e);
		}
		
		return status;
	}
	 
	public ResultStatus assignMembersToCallerForBatchConfirmation(final Long userId, final boolean isOwnMembers , final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId)
	{
		ResultStatus status  = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					List<Long> alreadyInvitedMemberIdsList = null;
					if(isOwnMembers){
						alreadyInvitedMemberIdsList = trainingCampScheduleInviteeCallerDAO.getInterestedAndInvitedMembersListForBatchConfirmation(callerId,scheduleId, "Invited", "Interested");
					}
					else{
						alreadyInvitedMemberIdsList = trainingCampScheduleInviteeCallerDAO.getInterestedAndInvitedMembersListForBatchConfirmation(null,scheduleId, "Invited", "Interested");
					}
					List<Long> invitedMemberIdsList = trainingCampScheduleInviteeDAO.getInvitedCandidatesListByScheduleIdAndCount(scheduleId,null,Integer.valueOf(membersCount.toString()));
					if(invitedMemberIdsList != null && invitedMemberIdsList.size()>0)
					{
						DateUtilService dateutilService = new DateUtilService();
						if(alreadyInvitedMemberIdsList == null) alreadyInvitedMemberIdsList = new ArrayList<Long>(0);
						for (Long trainingCampScheduleInviteeId : invitedMemberIdsList) {
								if(!alreadyInvitedMemberIdsList.contains(trainingCampScheduleInviteeId))
								{
									TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = new TrainingCampScheduleInviteeCaller();
									trainingCampScheduleInviteeCaller.setTrainingCampScheduleInviteeId(trainingCampScheduleInviteeId);
									trainingCampScheduleInviteeCaller.setCallPurposeId(callPurposeId);
									trainingCampScheduleInviteeCaller.setTrainingCampCallerId(callerId);
									trainingCampScheduleInviteeCaller.setInsertedBy(userId);
									trainingCampScheduleInviteeCaller.setUpdatedBy(userId);
									trainingCampScheduleInviteeCaller.setInsertedTime(dateutilService.getCurrentDateAndTime());
									trainingCampScheduleInviteeCaller.setUpdatedTime(dateutilService.getCurrentDateAndTime());
									trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
								}
						}
					}
				}
			});
			status.setResultCode(0);
			status.setMessage("SUCCESS");
		} catch (Exception e) {
			status.setResultCode(1);
			status.setMessage("FAILURE");
			LOG.error(" Exception occured in assignMembersToCallerForBatchConfirmation method in TrainingCampService class.",e);
		}
		return status;
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
			Map<Long,TrainingCampScheduleVO> assignedMap=new HashMap<Long, TrainingCampScheduleVO>();
			if(totalAssignedList !=null && totalAssignedList.size()>0){
				setResultToMap(totalAssignedList,assignedMap);
			}
			
			Map<Long,TrainingCampScheduleVO> completedMap=new HashMap<Long, TrainingCampScheduleVO>();
			if(completedCallsList !=null && completedCallsList.size()>0){
				setResultToMap(completedCallsList,completedMap);
			}
			
			Map<Long,TrainingCampScheduleVO> pendingMap=new HashMap<Long, TrainingCampScheduleVO>();

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
						dialedCallsCount=dialedCallsCount+commonMethodsUtilService.getLongValueForObject(dialed[1]) ;//allocating dialedCallsCount
					}
				}
			}
			
			finalCallersVODetails.setDialedCallsCount(dialedCallsCount);
			 //
			
			//statusWiseCount For upper Block in call center admin page
			List<Object[]> statusWiseCountList = trainingCampScheduleInviteeCallerDAO.getStatusWiseCount(userIds,startDate,endDate,null);
			
			List<TrainingCampScheduleVO> statusWiseCountLi = new ArrayList<TrainingCampScheduleVO>();
			if(statusWiseCountList !=null && statusWiseCountList.size()>0){
				for (Object[] object : statusWiseCountList) {
					TrainingCampScheduleVO vo =new TrainingCampScheduleVO();
					
					vo.setStatusId(commonMethodsUtilService.getLongValueForObject(object[0]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(object[1]));
					vo.setCount(commonMethodsUtilService.getLongValueForObject(object[2]));
					
					statusWiseCountLi.add(vo);
				}
			}
			
			
			if(finalList !=null && finalList.size()>0)
			{
				finalCallersVODetails.setTrainingCampVOList(finalList);
			}
			
			if(statusWiseCountLi !=null && statusWiseCountLi.size()>0){
				finalCallersVODetails.setTrainingCampScheduleVOList(statusWiseCountLi);
			}
			
			return finalCallersVODetails;
			
		}catch (Exception e){
			LOG.error(" Exception occured in getCallerWiseCallsDetails method in TrainingCampService class.",e);
		}
		return finalCallersVODetails;
	}
	public void setResultToMap(List<Object[]> listObj,Map<Long,TrainingCampScheduleVO> corespondentmap)
	{
		try{
			if(listObj !=null && listObj.size()>0){
				for(Object[] Obj:listObj){
					
					TrainingCampScheduleVO vo=new TrainingCampScheduleVO();
					
					Long assignedCount=commonMethodsUtilService.getLongValueForObject(Obj[1]);
					
					vo.setId(Long.parseLong(commonMethodsUtilService.getStringValueForObject(Obj[0])));
					vo.setName(Obj[2] !=null ? Obj[2].toString() : "");
					vo.setCount(assignedCount);
					
					corespondentmap.put(Long.parseLong(commonMethodsUtilService.getStringValueForObject(Obj[0])),vo);
				}
			}
		}catch (Exception e) {
			LOG.error(" Exception occured in setResultToMap method in TrainingCampService class.",e);
		}
	}
	public void setResultToAssignedMap(Map<Long,TrainingCampScheduleVO> assignedMap,Map<Long,TrainingCampScheduleVO> finalMap,String type,List<Object[]> allStatus,List<Object[]> callStatusOfinviteesList){
		
		try{
			if(type.equalsIgnoreCase("status")){
				
				for(Object[] obj:callStatusOfinviteesList){////0.callerId,1.scheduleInviteeStatusId,2.status,3.count,name
					TrainingCampScheduleVO assignCampVo =finalMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					boolean agentExist=true;
					
					if(assignCampVo==null){
						agentExist=false;
						assignCampVo=new TrainingCampScheduleVO();
						assignCampVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						assignCampVo.setName("");
						assignCampVo.setAssignedCallsCount(0l);
						assignCampVo.setCompletedCallsCount(0l);
						assignCampVo.setPendingCallsCount(0l);
						setStatusSchedules(assignCampVo,allStatus);
					}
					if(obj[1]!=null){
						Long statusId=commonMethodsUtilService.getLongValueForObject(commonMethodsUtilService.getLongValueForObject(obj[1]));
						List<TrainingCampScheduleVO> schedulesList=assignCampVo.getTrainingCampVOList();
						if(schedulesList!=null && schedulesList.size()>0){
							for(TrainingCampScheduleVO schedule:schedulesList){
								if(schedule.getStatusId().longValue()==statusId.longValue()){
									schedule.setCount(commonMethodsUtilService.getLongValueForObject(obj[3]));
								}
							}
								
						}
					}
					if(!agentExist){
						finalMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), assignCampVo);
					}
					
				}
				
			}else{
				for (Entry<Long, TrainingCampScheduleVO> assignedEntry : assignedMap.entrySet()){
					
					Long callerId=assignedEntry.getKey();
					TrainingCampScheduleVO assingedCount=assignedEntry.getValue();
					
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
						assignCampVo.setAssignedCallsCount(assingedCount.getCount());
						assignCampVo.setName(assingedCount.getName());
					}
					else if(type.equalsIgnoreCase("completed")){
						assignCampVo.setCompletedCallsCount(assingedCount.getCount());
						assignCampVo.setName(assingedCount.getName());
					}
					else if(type.equalsIgnoreCase("pending")){
						assignCampVo.setPendingCallsCount(assingedCount.getCount());
						assignCampVo.setName(assingedCount.getName());
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
					trainingCampScheduleVO.setStatusId(commonMethodsUtilService.getLongValueForObject(status[0]));
					trainingCampScheduleVO.setStatus(commonMethodsUtilService.getStringValueForObject(status[1]));
					statusScheduleList.add(trainingCampScheduleVO);
				  }
			}
			assignCampVo.setTrainingCampVOList(statusScheduleList);
			
		}catch (Exception e) {
			LOG.error(" Exception occured in setStatusSchedules method in TrainingCampService class.",e);
		}
	}
	
	public TrainingCampVO getCampusWiseBatchWiseMembersDetails(List<Long> callerIdsList,String searchType,String startDateStr,String endDateStr)
	{
		TrainingCampVO returnVO = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate= format.parse(startDateStr);
			Date endDate= format.parse(endDateStr);
			
			if(searchType != null && searchType.equalsIgnoreCase("notStarted") && (callerIdsList == null || callerIdsList.size() == 0))
			{

				String[] memberTypesArr = {"INTERESTED","NOT NOW","NOT INTERESTED"};
				List<Object[]> campAndSchedulewiseResultsList = trainingCampScheduleInviteeDAO.getCampusWiseScheduleWiseMembersDetails(searchType, startDate, endDate);
				
				Map<String,Map<String,Map<Long,List<TrainingCampVO>>>> programWiseSceduleWiseMap = new LinkedHashMap<String,Map<String,Map<Long,List<TrainingCampVO>>>>(0);
				Map<Long,Long> interestedMembersForSchedulMap = new LinkedHashMap<Long, Long>(0);
				if(campAndSchedulewiseResultsList != null && campAndSchedulewiseResultsList.size()>0)
				{
					returnVO = new TrainingCampVO();
					for (Object[] campObj : campAndSchedulewiseResultsList) {
						
						
						String memberStatus = commonMethodsUtilService.getStringValueForObject(campObj[4]);
						if(memberStatus != null && ( memberStatus.equalsIgnoreCase("NOT NOW") || memberStatus.equalsIgnoreCase("Interested") || 
								memberStatus.equalsIgnoreCase("Not Interested")) )
						{

							String campName = commonMethodsUtilService.getStringValueForObject(campObj[0]);
							String programName = commonMethodsUtilService.getStringValueForObject(campObj[1]);
							String scheduleName =commonMethodsUtilService.getStringValueForObject(campObj[2]);
							Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[6]);
							Map<String,Map<Long,List<TrainingCampVO>>> campWiseMap = new LinkedHashMap<String,Map<Long,List<TrainingCampVO>>>(0);
							Map<Long,List<TrainingCampVO>> scheduleWiseMap = new LinkedHashMap<Long,List<TrainingCampVO>>(0);
							List<TrainingCampVO> trainingCampVOList = null;
							if(programWiseSceduleWiseMap.get(programName) != null)
							{
								campWiseMap = programWiseSceduleWiseMap.get(programName);
							}
							if(campWiseMap.get(campName) != null)
							{
								scheduleWiseMap = campWiseMap.get(campName);
							}
							if(scheduleWiseMap.get(scheduleId) != null)
							{
								trainingCampVOList = scheduleWiseMap.get(scheduleId);
							}
							else
							{
								trainingCampVOList = new ArrayList<TrainingCampVO>(0);
								for (int i = 0; i < memberTypesArr.length; i++) {
									TrainingCampVO trainingCampVO = new TrainingCampVO();
									trainingCampVO.setMemberStatus(memberTypesArr[i].toString());
									trainingCampVOList.add(trainingCampVO);
								}
							}
							
							TrainingCampVO trainingCampVO = getMatchedVOforMemberStatus(trainingCampVOList,memberStatus);
							if(trainingCampVO != null)
							{
								if(memberStatus != null && memberStatus.equalsIgnoreCase("NOT NOW"))
									trainingCampVO.setMemberStatus("LATER");
								
								trainingCampVO.setTrainingCampName(campName);
								trainingCampVO.setScheduleName(scheduleName);
								//trainingCampVO.setMemberStatus(memberStatus);
								trainingCampVO.setNextBatchInterestedCount(commonMethodsUtilService.getLongValueForObject(campObj[5]));
								//trainingCampVOList.add(trainingCampVO);
								if(trainingCampVO.getMemberStatus() != null && !trainingCampVO.getMemberStatus().isEmpty() && 
										trainingCampVO.getMemberStatus().trim().equalsIgnoreCase("Interested"))
									interestedMembersForSchedulMap.put(scheduleId, trainingCampVO.getNextBatchInterestedCount());
								
								scheduleWiseMap.put(scheduleId, trainingCampVOList);
								campWiseMap.put(campName,scheduleWiseMap);
								programWiseSceduleWiseMap.put(programName, campWiseMap);
							}
						}
					}
					
					Map<Long,Long> scheduleWiseBatchConfirmedCountMap = new LinkedHashMap<Long,Long>(0);
					List<Object[]> batchConfirmedDetails = trainingCampScheduleInviteeCallerDAO.getBatchConfirmedMemberDetails(null, startDate, endDate,"Interested","confirmation");
					if(batchConfirmedDetails != null && batchConfirmedDetails.size()>0)
					{
						for (Object[] campObj : batchConfirmedDetails) {
							
							Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[3]);
							
							Long confirmedCount =commonMethodsUtilService.getLongValueForObject(campObj[5]);
							
							if(scheduleWiseBatchConfirmedCountMap.get(scheduleId) != null)
							{
								confirmedCount = scheduleWiseBatchConfirmedCountMap.get(scheduleId);
							}
							
							scheduleWiseBatchConfirmedCountMap.put(scheduleId, confirmedCount);
						}
					}
					if(programWiseSceduleWiseMap != null && programWiseSceduleWiseMap.size()>0)
					{
						List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
						for (String programStr : programWiseSceduleWiseMap.keySet()) {
							Map<String,Map<Long,List<TrainingCampVO>>> campWiseMap = programWiseSceduleWiseMap.get(programStr);
							TrainingCampVO programVO = new TrainingCampVO();
							programVO.setName(programStr);
							if(campWiseMap != null && campWiseMap.size()>0)
							{
								for (String campNameStr : campWiseMap.keySet()) {
									Map<Long,List<TrainingCampVO>> scheduleWiseCountMap = campWiseMap.get(campNameStr);
									programVO.setTrainingCampName(campNameStr);
									if(scheduleWiseCountMap != null && scheduleWiseCountMap.size()>0)
									{
										for (Long scheduleId : scheduleWiseCountMap.keySet()) {
											List<TrainingCampVO> scheduleVOList = scheduleWiseCountMap.get(scheduleId);
											if(scheduleVOList != null)
											{

												Long  batchConfirmationCount= scheduleWiseBatchConfirmedCountMap.get(scheduleId);
												if(batchConfirmationCount == null)
													batchConfirmationCount=0L;
												TrainingCampVO batchConfirmedVO = new TrainingCampVO();
												batchConfirmedVO.setMemberStatus(" BATCH CONFIRMED COUNT ");
												if(batchConfirmationCount != null && batchConfirmationCount.longValue()>0L)
													batchConfirmedVO.setBatchConfirmationCount(batchConfirmationCount);
												else
													batchConfirmedVO.setBatchConfirmationCount(0L);
												
												scheduleVOList.add(batchConfirmedVO);
												
												Long  interestedCount= interestedMembersForSchedulMap.get(scheduleId);
												Long availableCount = interestedCount - batchConfirmationCount;
												TrainingCampVO availableMembersVO = new TrainingCampVO();
												availableMembersVO.setMemberStatus("AVAILABLE INTERESTED MEMBERS");
												if(availableCount != null && availableCount.longValue()>0L)
													availableMembersVO.setAvailableMembersCount(availableCount);
												else
													availableMembersVO.setAvailableMembersCount(0L);
												
												scheduleVOList.add(availableMembersVO);
												
												programVO.setTrainingCampVOList(scheduleVOList);
											}
										}
									}
								}
								trainingCampVOList.add(programVO);
							}
							
							returnVO.setTrainingCampVOList(trainingCampVOList);
						}
					}
				}
			}
			else if(callerIdsList == null || callerIdsList.size() == 0)
			{
				List<Object[]> campAndSchedulewiseResultsList = trainingCampScheduleInviteeDAO.getCampusWiseBatchWiseMembersDetails(searchType, startDate, endDate);
				
				Map<String,Map<String,Map<Long,List<TrainingCampVO>>>> programWiseSceduleWiseMap = new LinkedHashMap<String,Map<String,Map<Long,List<TrainingCampVO>>>>(0);
				if(campAndSchedulewiseResultsList != null && campAndSchedulewiseResultsList.size()>0)
				{
					returnVO = new TrainingCampVO();
					for (Object[] campObj : campAndSchedulewiseResultsList) {

							String campName = commonMethodsUtilService.getStringValueForObject(campObj[0]);
							String programName = commonMethodsUtilService.getStringValueForObject(campObj[1]);
							String scheduleName =commonMethodsUtilService.getStringValueForObject(campObj[2]);
							String batchName =commonMethodsUtilService.getStringValueForObject(campObj[3]);
							String startDate1Str =commonMethodsUtilService.getStringValueForObject(campObj[4]);
							String endDate1Str =commonMethodsUtilService.getStringValueForObject(campObj[5]);
							String status =commonMethodsUtilService.getStringValueForObject(campObj[6]);
							Long count =commonMethodsUtilService.getLongValueForObject(campObj[7]);
							Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[8]);
							
							Map<String,Map<Long,List<TrainingCampVO>>> campWiseMap = new LinkedHashMap<String,Map<Long,List<TrainingCampVO>>>(0);
							Map<Long,List<TrainingCampVO>> scheduleWiseMap = new LinkedHashMap<Long,List<TrainingCampVO>>(0);
							List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
							if(programWiseSceduleWiseMap.get(programName) != null)
							{
								campWiseMap = programWiseSceduleWiseMap.get(programName);
							}
							if(campWiseMap.get(campName) != null)
							{
								scheduleWiseMap = campWiseMap.get(campName);
							}
							if(scheduleWiseMap.get(scheduleId) != null)
							{
								trainingCampVOList = scheduleWiseMap.get(scheduleId);
							}
								TrainingCampVO trainingCampVO = new TrainingCampVO();
								trainingCampVO.setTrainingCampName(campName);
								trainingCampVO.setScheduleName(scheduleName);
								trainingCampVO.setName(batchName);
								trainingCampVO.setStartDateStr(startDate1Str);
								trainingCampVO.setEndDateStr(endDate1Str);
								trainingCampVO.setMemberStatus(status);
								trainingCampVO.setAvailableMembersCount(count);
								trainingCampVOList.add(trainingCampVO);
								scheduleWiseMap.put(scheduleId, trainingCampVOList);
								campWiseMap.put(campName,scheduleWiseMap);
								programWiseSceduleWiseMap.put(programName, campWiseMap);
							
						
					}
					if(programWiseSceduleWiseMap != null && programWiseSceduleWiseMap.size()>0)
					{
						List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
						for (String programStr : programWiseSceduleWiseMap.keySet()) {
							Map<String,Map<Long,List<TrainingCampVO>>> campWiseMap = programWiseSceduleWiseMap.get(programStr);
							TrainingCampVO programVO = new TrainingCampVO();
							programVO.setName(programStr);
							if(campWiseMap != null && campWiseMap.size()>0)
							{
								for (String campNameStr : campWiseMap.keySet()) {
									Map<Long,List<TrainingCampVO>> scheduleWiseCountMap = campWiseMap.get(campNameStr);
									programVO.setTrainingCampName(campNameStr);
									if(scheduleWiseCountMap != null && scheduleWiseCountMap.size()>0)
									{
										for (Long scheduleId : scheduleWiseCountMap.keySet()) {
											List<TrainingCampVO> scheduleVOList = scheduleWiseCountMap.get(scheduleId);
											if(scheduleVOList != null)
											{
												programVO.setTrainingCampVOList(scheduleVOList);
											}
										}
									}
								}
								trainingCampVOList.add(programVO);
							}
							returnVO.setTrainingCampVOList(trainingCampVOList);
						}
					}
			}
		 }
		} catch (Exception e) {
			LOG.error(" Exception occured in getCampusWiseBatchWiseMembersDetails method in TrainingCampService class.",e);
			returnVO = null;
		}
		
		return returnVO;
	}
	
	public TrainingCampVO getMatchedVOforMemberStatus(List<TrainingCampVO> list,String memberStatus)
	{
		TrainingCampVO returnVO = null;
		try {
			if(list != null && list.size()>0 && memberStatus != null && !memberStatus.isEmpty())
			{
				for (TrainingCampVO trainingCampVO : list) {
					if(trainingCampVO.getMemberStatus().equalsIgnoreCase(memberStatus))
						return trainingCampVO;
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getMatchedVOforMemberStatus method in TrainingCampService class.",e);
		}
		return returnVO;
	}
	
	public TrainingCampVO getCampusWiseDateWiseInterestedMembersDetails(String searchType,String startDateStr,String endDateStr)
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		TrainingCampVO returnVO = new TrainingCampVO();
		try {
			Date startDate= format.parse(startDateStr);
			Date endDate= format.parse(endDateStr);
			List<Object[]> interestedMembersList = trainingCampScheduleInviteeDAO.getBatchWiseProgramWiseInterestedMembersDetails("interested",  searchType,  startDate,  endDate);
			Map<String,Map<String,List<TrainingCampVO>>> programWiseInterestedMembersMap = new LinkedHashMap<String,Map<String, List<TrainingCampVO>>>(0);
			if(interestedMembersList != null && interestedMembersList.size()>0)
			{
				for (Object[] member : interestedMembersList) {
					String programName = commonMethodsUtilService.getStringValueForObject(member[1]);
					String campName = commonMethodsUtilService.getStringValueForObject(member[3]);
					Map<String,List<TrainingCampVO>> campsWiseInterestedMembersMap = new LinkedHashMap<String, List<TrainingCampVO>>(0);
					List<TrainingCampVO>  scheduleVOList = new ArrayList<TrainingCampVO>(0);
					
					if(programWiseInterestedMembersMap.get(programName) != null)
					{
						campsWiseInterestedMembersMap = programWiseInterestedMembersMap.get(programName);
					}
					if(campsWiseInterestedMembersMap.get(campName) != null)
					{
						scheduleVOList = campsWiseInterestedMembersMap.get(campName);
					}
					
					TrainingCampVO vo = new TrainingCampVO();
					vo.setName(programName);
					vo.setTrainingCampName(campName);
					vo.setStartDateStr(commonMethodsUtilService.getStringValueForObject(member[4]));
					vo.setEndDateStr(commonMethodsUtilService.getStringValueForObject(member[5]));
					vo.setBatchConfirmationCount(commonMethodsUtilService.getLongValueForObject(member[6]));
					scheduleVOList.add(vo);
					campsWiseInterestedMembersMap.put(campName, scheduleVOList);
					programWiseInterestedMembersMap.put(programName, campsWiseInterestedMembersMap);
					
				}
				
				if(programWiseInterestedMembersMap != null && programWiseInterestedMembersMap.size()>0)
				{
					List<TrainingCampVO> trainingProgramsList = new ArrayList<TrainingCampVO>(0);
					for (String programNameStr : programWiseInterestedMembersMap.keySet()) {
						TrainingCampVO programVO = new TrainingCampVO();
						programVO.setName(programNameStr);
						
						Map<String,List<TrainingCampVO>> campsWiseInterestedMembersMap = programWiseInterestedMembersMap.get(programNameStr);
						if(campsWiseInterestedMembersMap != null && campsWiseInterestedMembersMap.size()>0)
						{
							List<TrainingCampVO> campusVOList = new ArrayList<TrainingCampVO>(0);
							for (String campusName : campsWiseInterestedMembersMap.keySet()) {
								TrainingCampVO campVO = new TrainingCampVO();
								campVO.setTrainingCampVOList(campsWiseInterestedMembersMap.get(campusName));
								campusVOList.add(campVO);
							}
							if(campusVOList != null && campusVOList.size()>0)
							{
								programVO.setTrainingCampVOList(campusVOList);
							}
							
						}
						trainingProgramsList.add(programVO);
					}
					
					returnVO.setTrainingCampVOList(trainingProgramsList);
				}
			}
			
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
					TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params[1])); // Program
						if(programVo == null)
						{
							programVo = new TraingCampCallerVO()	;
							programVo.setId(commonMethodsUtilService.getLongValueForObject(params[1]));
							programVo.setName(commonMethodsUtilService.getStringValueForObject(params[2]));
							returnList.add(programVo);
						}
						
						TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[3])); // Camp
						if(campVo == null)
						{
							campVo = new TraingCampCallerVO()	;
							campVo.setId(commonMethodsUtilService.getLongValueForObject(params[3]));
							campVo.setName(commonMethodsUtilService.getStringValueForObject(params[4]));
							
							programVo.getSubList().add(campVo);
						}
						TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[5])); // Schedule
						if(scheduleVo == null)
						{
							scheduleVo = new TraingCampCallerVO()	;
							scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(params[5]));
							scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(params[6]));
							scheduleVo.setSubList(getStatusList());
							scheduleVo.setScheduleStatusList(getScheduleStatusList());
							campVo.getSubList().add(scheduleVo);
							programVo.setSpanCnt(programVo.getSpanCnt()+1);
						}
						scheduleVo.setTotal(scheduleVo.getTotal() + commonMethodsUtilService.getLongValueForObject(params[0]));
						TraingCampCallerVO schedulestatusVo  = getMatchedVo(scheduleVo.getScheduleStatusList(),commonMethodsUtilService.getLongValueForObject(params[9])); // Status
						if(schedulestatusVo != null)
						{
							schedulestatusVo.setCount(schedulestatusVo.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
						}
						if(params[7] != null)
						{
							TraingCampCallerVO statusVo  = getMatchedVo(scheduleVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[7])); // Status
							if(statusVo != null)
							{
								statusVo.setCount(statusVo.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
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
					TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params[1])); // Program
						if(programVo == null)
						{
							programVo = new TraingCampCallerVO()	;
							programVo.setId(commonMethodsUtilService.getLongValueForObject(params[1]));
							programVo.setName(commonMethodsUtilService.getStringValueForObject(params[2]));
							returnList.add(programVo);
						}
						
						TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[3])); // Camp
						if(campVo == null)
						{
							campVo = new TraingCampCallerVO()	;
							campVo.setId(commonMethodsUtilService.getLongValueForObject(params[3]));
							campVo.setName(commonMethodsUtilService.getStringValueForObject(params[4]));
							
							programVo.getSubList().add(campVo);
						}
						TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[5])); // Schedule
						if(scheduleVo == null)
						{
							scheduleVo = new TraingCampCallerVO()	;
							scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(params[5]));
							scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(params[6]));
							campVo.getSubList().add(scheduleVo);
						}
						
						TraingCampCallerVO batchVo  = getMatchedVo(scheduleVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[10])); // Batch
						if(batchVo == null)
						{
							batchVo = new TraingCampCallerVO()	;
							batchVo.setId(commonMethodsUtilService.getLongValueForObject(params[10]));
							batchVo.setName(commonMethodsUtilService.getStringValueForObject(params[11]));
							batchVo.setSubList(getStatusList());
							batchVo.setScheduleStatusList(getScheduleStatusList());
							scheduleVo.getSubList().add(batchVo);
							programVo.setSpanCnt(programVo.getSpanCnt() + 1);
							campVo.setSpanCnt(campVo.getSpanCnt() + 1);
							scheduleVo.setSpanCnt(scheduleVo.getSpanCnt() + 1);

						} 
						batchVo.setTotal(batchVo.getTotal() + commonMethodsUtilService.getLongValueForObject(params[0]));
						TraingCampCallerVO batchstatusVo  = getMatchedVo(batchVo.getScheduleStatusList(),commonMethodsUtilService.getLongValueForObject(params[9])); // Status
						if(batchstatusVo != null)
						{
							batchstatusVo.setCount(batchstatusVo.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
						}
						if(params[7] != null)
						{
							TraingCampCallerVO batchstatusVo1  = getMatchedVo(batchVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[7])); // Status
							if(batchstatusVo1 != null)
							{
								batchstatusVo1.setCount(batchstatusVo1.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
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

	public List<TraingCampCallerVO> getMembersCountByBatchStatus(Long campCallerId,String batchStatus)
	{
		List<TraingCampCallerVO> returnList = null;
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getMembersCountByBatchStatusAndCallerId(campCallerId, batchStatus);
			
			if(list != null && list.size() > 0)
			{
				returnList = new ArrayList<TraingCampCallerVO>(0);
				for(Object[] params : list)
				{
					TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params[1])); //program
					if(programVo == null)
					{
						programVo = new TraingCampCallerVO();
						programVo.setId(commonMethodsUtilService.getLongValueForObject(params[1]));
						programVo.setName(commonMethodsUtilService.getStringValueForObject(params[2]));
						returnList.add(programVo);
					}
					
					TraingCampCallerVO campVo = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[3])); //camp
					if(campVo == null)
					{
						campVo = new TraingCampCallerVO();
						campVo.setId(commonMethodsUtilService.getLongValueForObject(params[3]));
						campVo.setName(commonMethodsUtilService.getStringValueForObject(params[4]));
						programVo.getSubList().add(campVo);
					}
					TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[5])); // Schedule
					if(scheduleVo == null)
					{
						scheduleVo = new TraingCampCallerVO()	;
						scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(params[5]));
						scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(params[6]));
						campVo.getSubList().add(scheduleVo);
					}
					TraingCampCallerVO batchVo  = getMatchedVo(scheduleVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[7])); // Batch
					if(batchVo == null)
					{
						batchVo = new TraingCampCallerVO()	;
						batchVo.setId(commonMethodsUtilService.getLongValueForObject(params[7]));
						batchVo.setName(commonMethodsUtilService.getStringValueForObject(params[8]));
						batchVo.setCount(commonMethodsUtilService.getLongValueForObject(params[0]));
						batchVo.setSpanCnt(1l);
						
						scheduleVo.getSubList().add(batchVo);
						
						programVo.setSpanCnt(programVo.getSpanCnt() + 1);
						campVo.setSpanCnt(campVo.getSpanCnt() + 1);
						scheduleVo.setSpanCnt(scheduleVo.getSpanCnt()+1);
					} 
					
					
				}
				
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getMembersCountByBatchStatus() method, Exception - ", e);
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
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					String fname = commonMethodsUtilService.getStringValueForObject(params[1]);
					String lname = commonMethodsUtilService.getStringValueForObject(params[2]);
					vo.setName(fname +" "+lname);
					vo.setMobileNumber(commonMethodsUtilService.getStringValueForObject(params[3]));
					vo.setImage(commonMethodsUtilService.getStringValueForObject(params[4]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(params[6]));
					vo.setAge(commonMethodsUtilService.getStringValueForObject(params[7]));
					vo.setLocation(commonMethodsUtilService.getStringValueForObject(params[8]));
					vo.setInviteeId(params[9] != null ? (Long)params[9] : 0l);
					vo.setInviteeCallerId(params[10] != null ? (Long)params[10] : 0l);
					vo.setRemarks(params[11] != null ? params[11].toString() : "");
					returnList.add(vo);
					if(!cadreIds.contains(commonMethodsUtilService.getLongValueForObject(params[0])))
						cadreIds.add(commonMethodsUtilService.getLongValueForObject(params[0]));
				}
				 List<Object[]> roles = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(cadreIds);
				 if(roles != null && roles.size() > 0)
				 {
					 for(Object[] params : roles)
					 {
						 TrainingMemberVO vo =  getMatchedVo1(returnList, commonMethodsUtilService.getLongValueForObject(params[0]));
						 if(vo != null)
						 {
							 vo.setRole(commonMethodsUtilService.getStringValueForObject(params[2]));
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
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]));
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
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]));
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
			
	public List<IdNameVO> getBasicList(List<Object[]> list)
	{
		List<IdNameVO>  returnList = new ArrayList<IdNameVO>();
		try{
			List<Object[]> programs = trainingCampProgramDAO.getPrograms();
			return getBasicList(programs);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	public List<BasicVO> getCampsByProgramId(Long programId)
	{
		try{
			List<Object[]> camps = trainingCampScheduleDAO.getCampsForProgram(programId);
			return getBasicList1(camps);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<BasicVO> getSchedulesByCampId(Long campId)
	{
		try{
			List<Object[]> schedules = trainingCampScheduleDAO.getSchedules(campId);
			return getBasicList1(schedules);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<BasicVO> getBatchesByScheduleId(Long scheduleId)
	{
		try{
			List<Object[]> batches = trainingCampBatchDAO.getBatchesForSchedule(scheduleId);
			return getBasicList1(batches);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	public List<BasicVO> getBasicList1(List<Object[]> list)
	{
		List<BasicVO>  returnList = new ArrayList<BasicVO>();
		try{
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					BasicVO vo = new BasicVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]));
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
				Long programCount=commonMethodsUtilService.getIntegerToLong(listForProgramVo.size());
				finalTrainingVo.setProgramCount(programCount);
				finalTrainingVo.setTrainingCampVOList(listForProgramVo);//ProgramWise List
			}
			if(listForCampVo !=null && listForCampVo.size()>0){
				Long campCount=commonMethodsUtilService.getIntegerToLong(listForCampVo.size());
				finalTrainingVo.setCampCount(campCount);
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
			
			progamVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
			progamVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
			progamVo.setTotalCount(commonMethodsUtilService.getLongValueForObject(objects[2]));//members Count
			progamVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[3]));//batchCount
			
			listVo.add(progamVo);
		}
	}
	
	public ResultStatus updateCadreStatusForTraining(TrainingCadreVO inputVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		DateUtilService date = new DateUtilService();
		try{
			if(inputVO.getCallStatusId() == 1l && inputVO.getStatus().equalsIgnoreCase("callstatus"))//Answered
			{
			TrainingCampScheduleInvitee trainingCampScheduleInvitee = trainingCampScheduleInviteeDAO.get(inputVO.getInvitteId());
					if(inputVO.getBatchId() > 0)
						trainingCampScheduleInvitee.setAttendingBatchId(inputVO.getBatchId());	
					if(inputVO.getRamarks() != null && inputVO.getRamarks().length() > 0)
						trainingCampScheduleInvitee.setRemarks(inputVO.getRamarks());
					if(inputVO.getScheduleStatusId() > 0)
						trainingCampScheduleInvitee.setScheduleInviteeStatusId(inputVO.getScheduleStatusId());
						trainingCampScheduleInvitee.setInsertedBy(inputVO.getUserId());
						trainingCampScheduleInvitee.setInsertedTime(date.getCurrentDateAndTime());
						trainingCampScheduleInviteeDAO.save(trainingCampScheduleInvitee);
			}
					
			TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = trainingCampScheduleInviteeCallerDAO.get(inputVO.getInviteeCallerId());
			trainingCampScheduleInviteeCaller.setCallStatusId(inputVO.getCallStatusId());
			trainingCampScheduleInviteeCaller.setInsertedBy(inputVO.getUserId());
			trainingCampScheduleInviteeCaller.setInsertedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeCaller.setUpdatedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
			saveTrackingInfo(inputVO);	
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					
			
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in updateCadreStatusForTraining()", e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
	}
	public void saveTrackingInfo(TrainingCadreVO inputVO)
	{
		try{
			DateUtilService date= new DateUtilService();
			TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = trainingCampScheduleInviteeCallerDAO.get(inputVO.getInviteeCallerId());
			TrainingCampScheduleInviteeTrack trainingCampScheduleInviteeTrack = new TrainingCampScheduleInviteeTrack();
		
			if(inputVO.getRamarks() != null && !inputVO.getRamarks().isEmpty())
				trainingCampScheduleInviteeTrack.setRemarks(inputVO.getRamarks());
			trainingCampScheduleInviteeTrack.setCampCallPurposeId(2l);
			trainingCampScheduleInviteeTrack.setCampCallStatusId(inputVO.getCallStatusId());
			if(inputVO.getScheduleStatusId() != null && inputVO.getScheduleStatusId() > 0)
			trainingCampScheduleInviteeTrack.setScheduleInviteeStatusId(inputVO.getScheduleStatusId());
			//trainingCampScheduleInviteeTrack.setTdpCadreId(inputVO.getTdpCadreId());
			trainingCampScheduleInviteeTrack.setTrainingCampCallerId(inputVO.getUserId());
			trainingCampScheduleInviteeTrack.setCalledTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeTrack.setInsertedBy(inputVO.getUserId());
			trainingCampScheduleInviteeTrack.setUpdatedBy(inputVO.getUserId());
			trainingCampScheduleInviteeTrack.setInsertedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeTrack.setUpdatedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeTrack.setTrainingCampScheduleInviteeId(inputVO.getInvitteId());
			trainingCampScheduleInviteeTrack.setTrainingCampScheduleInviteeCallerId(inputVO.getInviteeCallerId());
			if(trainingCampScheduleInviteeCaller.getTrainingCampCallerAdminId() != null)
			trainingCampScheduleInviteeTrack.setTrainingCampCallerAdminId(trainingCampScheduleInviteeCaller.getTrainingCampCallerAdminId());
			
			if(inputVO.getCallBackDate() != null)
			{
				String dateSample =inputVO.getCallBackDate()+ " "+inputVO.getCallBackTime();
				 trainingCampScheduleInviteeTrack.setCampCallStatusId(1l);
				 String oldScheduledDate = inputVO.getCallBackDate() +" "+inputVO.getCallBackTime();
			     DateFormat oldFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			     DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			     Date oldDate = (Date)oldFormatter .parse(oldScheduledDate);
			trainingCampScheduleInviteeTrack.setCallBackTime(oldDate);
			}
			trainingCampScheduleInviteeTrackDAO.save(trainingCampScheduleInviteeTrack);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ResultStatus updateCallBackCadreStatusForTraining(TrainingCadreVO inputVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		DateUtilService date = new DateUtilService();
		try{
			SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		    Date date1 = parseFormat.parse(inputVO.getCallBackTime());
		    System.out.println(parseFormat.format(date1) + " = " + displayFormat.format(date1));
		    inputVO.setCallBackTime(displayFormat.format(date1));
			TrainingCampScheduleInvitee trainingCampScheduleInvitee = trainingCampScheduleInviteeDAO.get(inputVO.getInvitteId());
					
					if(inputVO.getRamarks() != null && inputVO.getRamarks().length() > 0)
						trainingCampScheduleInvitee.setRemarks(inputVO.getRamarks());
					if(inputVO.getScheduleStatusId() > 0)
						trainingCampScheduleInvitee.setScheduleInviteeStatusId(inputVO.getScheduleStatusId());
						trainingCampScheduleInvitee.setInsertedBy(inputVO.getUserId());
						String dateSample =inputVO.getCallBackDate()+ " "+inputVO.getCallBackTime();

						 String oldScheduledDate = inputVO.getCallBackDate() +" "+inputVO.getCallBackTime();
					     DateFormat oldFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					     DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					     Date oldDate = (Date)oldFormatter .parse(oldScheduledDate);
						trainingCampScheduleInvitee.setCallBackTime(oldDate);
						trainingCampScheduleInvitee.setInsertedTime(date.getCurrentDateAndTime());
						trainingCampScheduleInviteeDAO.save(trainingCampScheduleInvitee);
			
					
			TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = trainingCampScheduleInviteeCallerDAO.get(inputVO.getInviteeCallerId());
			trainingCampScheduleInviteeCaller.setCallStatusId(1l);
			trainingCampScheduleInviteeCaller.setInsertedBy(inputVO.getUserId());
			trainingCampScheduleInviteeCaller.setInsertedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeCaller.setUpdatedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
			saveTrackingInfo(inputVO);		
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					
			
			
		}
		catch (Exception e) {
			LOG.error("Exception Occured in updateCallBackCadreStatusForTraining()", e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
	}
	
	
	public TrainingCampScheduleVO getScheduleAndConfirmationCallsOfCallerToAgent(List<Long> userIds,String startDateString,String endDateString){
		TrainingCampScheduleVO returnVO = new TrainingCampScheduleVO();
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
					vo.setId(commonMethodsUtilService.getLongValueForObject(purpose[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(purpose[1]));
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
				
				//For total assigned And Total Dialed Calls Count
				Long totalAssignedCount=0l;
				Long totalDialedCount=0l;
				if(finalList !=null && finalList.size()>0){
					for(TrainingCampScheduleVO vo:finalList){
						totalAssignedCount =totalAssignedCount+vo.getCount();
						totalDialedCount=totalDialedCount+vo.getDialedCallsCount();
					}
					
					TrainingCampScheduleVO listVo=finalList.get(0);
					
					listVo.setTotalAssignedCount(totalAssignedCount);//totalAssignedCalls To Agents
					listVo.setTotalDialedCallsCount(totalDialedCount);//total Agent Dialed calls
				}
			}
			
			if(finalList != null && finalList.size()>0)
				returnVO.setTrainingCampScheduleVOList(finalList);
			
			return returnVO;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return returnVO;
	}
	

	public void setScheduleAndConfirmationCallsOfCallerToAgent(List<Object[]>  scheduleAndConfirmationCalls,Map<Long,TrainingCampScheduleVO> finalVo,String type){
		
		if(scheduleAndConfirmationCalls !=null && scheduleAndConfirmationCalls.size()>0){
			
			for (Object[] objects : scheduleAndConfirmationCalls) {
				
				TrainingCampScheduleVO scheduleVo =finalVo.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
				
				if(scheduleVo ==null){
					scheduleVo=new TrainingCampScheduleVO();
				}
				scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				
				if(type.equalsIgnoreCase("total")){
					scheduleVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));//purpose wise assigned calls
				}
				else if(type.equalsIgnoreCase("dialed")){
					scheduleVo.setDialedCallsCount(commonMethodsUtilService.getLongValueForObject(objects[2]));//purpose wise dialed calls
				}
				
				finalVo.put(scheduleVo.getId(), scheduleVo);
			}
			
		}
		
	}
	
	public TrainingCampCallStatusVO getCallStatusCountByTrainingCampCallerId(Long trainingCampCallerId)
	{
		TrainingCampCallStatusVO callStatusVO = new TrainingCampCallStatusVO();
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getCallStatusCountByTrainingCampCallerId(trainingCampCallerId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params: list)
				{
					if(params[1] !=null && params[1].toString().equalsIgnoreCase("Call Answered"))
						callStatusVO.setAnswerdeCallsCount((Long)params[0]);
						
					else if(params[1] !=null && (params[1].toString().equalsIgnoreCase("Switchoff") || params[1].toString().equalsIgnoreCase("User Busy")))
						callStatusVO.setUserBusyCallsCount(callStatusVO.getUserBusyCallsCount()+(Long)params[0]);
					
					
					callStatusVO.setAllocatedCallsCount(callStatusVO.getAllocatedCallsCount()+(Long)params[0]);
					
				}
				callStatusVO.setDialledCallsCount(callStatusVO.getAnswerdeCallsCount()+callStatusVO.getUserBusyCallsCount());
			}
			
			List<Object[]> list1 = trainingCampScheduleInviteeCallerDAO.getInterestedMembersCountByCampCallerId(trainingCampCallerId);
			if(list1 != null && list1.size() > 0)
			{
				for(Object[] obj: list1)
				{
					if(obj[1] !=null && obj[1].toString().equalsIgnoreCase("Interested"))
						callStatusVO.setInterestedMemCount((Long)obj[0]);
						
					else if(obj[1] !=null && obj[1].toString().equalsIgnoreCase("Not Interested"))
						callStatusVO.setNotIntereMemCount((Long)obj[0]);
					
					else
					 callStatusVO.setCurrentlyNotIntMemCount((Long)obj[0]);
					
				}
				
			}
			
			
			
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCallStatusCountByTrainingCampCallerId() method, Exception - ",e);
		}
		return callStatusVO;
		
		
	}
	public List<IdNameVO> getUserIdsByType(){
		
		List<IdNameVO> listOfUsers=new ArrayList<IdNameVO>();
		try{
			List<Object[]> users=trainingCampUserDAO.getUserIdsByType(5l);
			if(users !=null && users.size()>0){
				for(Object[] user:  users){
					IdNameVO vo = new IdNameVO();
					vo.setId(user[0] !=null ? (Long)user[0] :0l);//userId
					vo.setName(user[1] !=null ? user[1].toString() : "");
					
					listOfUsers.add(vo);
				}
				return listOfUsers;
			}
		}catch(Exception e){
			LOG.error(" Exception Occured in getUserIdsByType() method, Exception - ",e);
		}
		return listOfUsers;
	}
	
}
