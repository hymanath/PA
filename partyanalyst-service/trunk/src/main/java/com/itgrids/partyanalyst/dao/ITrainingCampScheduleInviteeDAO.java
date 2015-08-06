package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;

public interface ITrainingCampScheduleInviteeDAO extends GenericDao<TrainingCampScheduleInvitee, Long>{
	public List<Object[]> getCampusWiseBatchWiseMembersDetails(String membersType, String searchType, Date startDate, Date endDate);
	public List<Object[]> getTrainingProgramMembersBatchCount(Date startDate,Date endDate,String status,String type);
}
