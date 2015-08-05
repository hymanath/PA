package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeDAO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;

public class TrainingCampScheduleInviteeDAO extends GenericDaoHibernate<TrainingCampScheduleInvitee, Long> implements ITrainingCampScheduleInviteeDAO{

	public TrainingCampScheduleInviteeDAO() {
		super(TrainingCampScheduleInvitee.class);
	}

	public List<Object[]> getCampusWiseBatchWiseMembersDetails(String membersType, String searchType, Date startDate, Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select TCSI.trainingCampSchedule.trainingCamp.campName,TCSI.trainingCampSchedule.trainingCampScheduleCode ");
		queryStr.append(" ,TCSI.scheduleInviteeStatus.scheduleInviteeStatusId,TCSI.scheduleInviteeStatus.status ,count(distinct TCSI.tdpCadreId) ");
		
		queryStr.append(" from TrainingCampScheduleInvitee TCSI,TrainingCampBatch TCB where TCSI.trainingCampSchedule.trainingCampId = TCB.trainingCampBatchId ");
		
		if(startDate != null && endDate != null)
		{
			queryStr.append(" and (date(TCSI.trainingCampSchedule.fromDate) >=:startDate and date(TCSI.trainingCampSchedule.fromDate) <=:endDate) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("notStarted"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status ='Not Started' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("running"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status ='Progress' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("completed"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status ='Completed' ");
		}
		queryStr.append(" group by TCSI.trainingCampSchedule.trainingCamp.trainingCampId,TCSI.trainingCampSchedule.trainingCampScheduleCode,TCSI.scheduleInviteeStatus.scheduleInviteeStatusId order by " +
				" TCSI.trainingCampSchedule.trainingCamp.trainingCampId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
}
