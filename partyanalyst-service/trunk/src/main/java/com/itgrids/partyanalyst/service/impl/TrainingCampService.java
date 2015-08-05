package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBatchStatusDAO;
import com.itgrids.partyanalyst.dao.ICampCallPurposeDAO;
import com.itgrids.partyanalyst.dao.ICampCallStatusDAO;
import com.itgrids.partyanalyst.dao.IScheduleInviteeStatusDAO;
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
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.service.ITrainingCampService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

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
	
	public void getCallerWiseCallsDetails(List<Long> callerIdsList,String searchTypeId,String startDate,String endDate)
	{
		try {
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getCallerWiseCallsDetails method in TrainingCampService class.",e);
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
	
}
