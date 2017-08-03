package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;

public class PartyMeetingAtrPointDAO extends GenericDaoHibernate<PartyMeetingAtrPoint,Long> implements IPartyMeetingAtrPointDAO{

	public PartyMeetingAtrPointDAO()
	{
		super(PartyMeetingAtrPoint.class);
	}
	
	public List<Object[]> getPartyMeetingsATRPointsDetls(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId,PMI.partyMeeting.meetingName, count(distinct PMI.partyMeetingAtrPointId)  from PartyMeetingAtrPoint PMI where " +
				"  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		return query.list();
	}
	
	public List<Object[]> getAtrDetailsForAMeeting(Long partyMeetingId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingAtrPointId,model.partyMeetingId,model.request,model.actionTaken,model.requestFrom, " +
				" model.locationScopeId,model.locationValue, " +
				" model.raisedBy,model.insertedBy.userId,model.insertedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName," +
				"model.insertedTime,model.updatedTime,model.partyMeeting.meetingName  " +
				" from PartyMeetingAtrPoint model where " +
				"  model.partyMeeting.partyMeetingId=:partyMeetingId and model.isDeleted='N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingId", partyMeetingId);
		return query.list();
	}
	
	public Integer updateMeetingAtrPoint(Long atrId,String request,String actionTaken,String raisedBy,Long updatedBy,Date updatedTime,Long locationId){
		StringBuilder query = new StringBuilder();
		query.append("update PartyMeetingAtrPoint model set model.request = ?,model.actionTaken=?,model.updatedTime=?,model.raisedBy=?,model.updatedById=?,model.locationValue=? " +
				"where model.partyMeetingAtrPointId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, request);
		queryObject.setParameter(1, actionTaken);
		queryObject.setParameter(2, updatedTime);
		queryObject.setParameter(3, raisedBy);
		queryObject.setParameter(4, updatedBy);
		queryObject.setParameter(5, locationId);
		queryObject.setParameter(6, atrId);
		
		return queryObject.executeUpdate();
	}
	
	public Integer deleteMeetingAtrPoint(Long atrId,Long updatedBy,Date updatedTime){
		StringBuilder query = new StringBuilder();
		query.append("update PartyMeetingAtrPoint model set model.isDeleted = 'Y',model.updatedById=?,model.updatedTime=? " +
				"where model.partyMeetingAtrPointId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, updatedBy);
		queryObject.setParameter(1, updatedTime);
		queryObject.setParameter(2, atrId);

		return queryObject.executeUpdate();
	}
	
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total ATR Points Of Meeting
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, Count of ATR
	 */
	public List<Object[]> getAtrPointsOfMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId," +
				" count(model.partyMeetingAtrPointId)" +
				" from PartyMeetingAtrPoint model " +
				" where model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted = 'N'" +
				" group by model.partyMeeting.partyMeetingId ");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	public List<Long> getAtrHavingMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId from PartyMeetingAtrPoint model " +
				" where model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted = 'N' ");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	public List<Object[]> getAtrDetailsForMeetingsList(List<Long> partyMeetingIdsList){

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingAtrPointId,model.partyMeetingId,model.request,model.actionTaken,model.requestFrom, " +
				" model.locationScopeId,model.locationValue, " +
				" model.raisedBy,model.insertedBy.userId,model.insertedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName," +
				"model.insertedTime,model.updatedTime,model.partyMeeting.meetingName  " +
				" from PartyMeetingAtrPoint model where " +
				"  model.partyMeeting.partyMeetingId in (:partyMeetingIdsList) and model.isDeleted='N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partyMeetingIdsList", partyMeetingIdsList);
		return query.list();
	
	}
}
