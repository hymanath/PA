package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TrainingCampVO;

public interface ITrainingCampService {

	public void getCallerWiseCallsDetails(List<Long> callerIdsList,String searchTypeId,String startDate,String endDate);
	public ResultStatus assignInviteesToCallersForScheduleConfirmation(Long inviteesCount, Long adminId, Long callerId,String searchTypeId,String startDate,String endDate);
	public ResultStatus assignInviteesToCallersForBatchConfirmation(Long inviteesCount, Long adminId, Long callerId,String searchTypeId,String startDate,String endDate);
	public TrainingCampVO getCampusWiseBatchWiseMembersDetails(List<Long> callerIdsList,String searchTypeId,String startDate,String endDate);
	public TrainingCampVO getCampusWiseDateWiseCampDetails(List<Long> campusIdsList,String searchTypeId,String startDate,String endDate);
	public TrainingCampVO getCampusWiseDateWiseInterestedMembersDetails(List<Long> campusIdsList,String searchTypeId,String startDate,String endDate);
}
