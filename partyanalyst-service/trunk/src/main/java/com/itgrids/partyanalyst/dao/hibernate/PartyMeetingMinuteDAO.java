package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;

public class PartyMeetingMinuteDAO extends GenericDaoHibernate<PartyMeetingMinute,Long> implements IPartyMeetingMinuteDAO{

	public PartyMeetingMinuteDAO()
	{
		super(PartyMeetingMinute.class);
	}
	
	public List<Object[]> getPartyMeetingsMinutsDetlsByCadreIds(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId,PMI.partyMeeting.meetingName, count(distinct PMI.partyMeetingMinuteId)  from PartyMeetingMinute PMI where " +
				"  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		return query.list();
	}
	
	public List<Object[]> getMinuteDetailsForAMeeting(Long partyMeetingId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingMinuteId,model.partyMeeting.partyMeetingId,model.minutePoint,model.insertedBy.userId,model.insertedBy.firstName," +
				"model.updatedBy.userId,model.updatedBy.firstName,model.insertedTime," +
				"model.updatedTime " +
				" from PartyMeetingMinute model where " +
				"  model.partyMeeting.partyMeetingId=:partyMeetingId and model.isDeleted='N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingId", partyMeetingId);
		
		return query.list();
	}
	
	public Integer updateMeetingPoint(Long minuteId,String minuteText,Long updatedBy,Date updateTime){
		
		StringBuilder query = new StringBuilder();
		query.append("update PartyMeetingMinute model set model.minutePoint = ?,model.updatedBy.userId=?,model.updatedTime=? where model.partyMeetingMinuteId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, minuteText);
		queryObject.setParameter(1, updatedBy);
		queryObject.setParameter(2, updateTime);
		queryObject.setParameter(3, minuteId);
		
		return queryObject.executeUpdate();	
	}
	
	public Integer deleteMeetingMinutePoint(Long minuteId,Long updatedBy,Date updateTime){
		StringBuilder query = new StringBuilder();
		query.append("update PartyMeetingMinute model set model.isDeleted = 'Y',model.updatedBy.userId=?,model.updatedTime=? where model.partyMeetingMinuteId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, updatedBy);
		queryObject.setParameter(1, updateTime);
		queryObject.setParameter(2, minuteId);
		return queryObject.executeUpdate();	
		
	}
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Minutes Of Meeting
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, Count of Minutes
	 */
	public List<Object[]> getMinuteDetailsForMeetings(List<Long> partyMeetingIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeeting.partyMeetingId," +
				" count(model.partyMeetingMinuteId)" +
				" from PartyMeetingMinute model where " +
				" model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted='N'" +
				" group by model.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		
		return query.list();
	}
}
