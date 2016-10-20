package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.UserAddress;

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
				"model.updatedTime,model.partyMeeting.meetingName " +
				" from PartyMeetingMinute model where " +
				"  model.partyMeeting.partyMeetingId=:partyMeetingId and model.isDeleted='N' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingId", partyMeetingId);
		
		return query.list();
	}
	
	public Integer updateMeetingPoint(Long minuteId,String minuteText,Long updatedBy,Date updateTime,Long levelId,Long levelValue,String isActionable,Long statusId,
			UserAddress userAddress){
		
		StringBuilder query = new StringBuilder();
		query.append("update PartyMeetingMinute model set model.minutePoint = ?,model.updatedBy.userId=?,model.updatedTime=?" +
				",model.isActionable =:isActionable,model.locationLevel=:levelId,model.locationValue=:levelValue,model.statusId=:statusId," +
				"model.userAddressId=:userAddressId " +
				" where model.partyMeetingMinuteId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, minuteText);
		queryObject.setParameter(1, updatedBy);
		queryObject.setParameter(2, updateTime);
		queryObject.setParameter(3, minuteId);
		
		queryObject.setParameter("isActionable", isActionable);
		if(levelId !=null && levelId>0l){
			queryObject.setParameter("levelId", levelId);
		}else{
			queryObject.setParameter("levelId", null);
		}
		
		if(levelValue !=null && levelValue>0l){
			queryObject.setParameter("levelValue", levelValue);
		}else{
			queryObject.setParameter("levelValue", null);
		}
		
		queryObject.setParameter("statusId", statusId);
		if(userAddress !=null){
			queryObject.setParameter("userAddressId", userAddress.getUserAddressId());
		}else{
			queryObject.setParameter("userAddressId", null);
		}
		
		
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
	
	public List<Long> getMOMHavingMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId from PartyMeetingMinute model " +
				" where model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted = 'N' ");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	public List<Object[]> getPartyMeetingMinuteRetrieveDetails(Long minuteId){
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT model.partyMeetingMinuteId,model.minutePoint,model.isActionable,model.statusId," +
				" model.userAddress,model.partyMeetingId " +
				" FROM PartyMeetingMinute model " +
				" WHERE model.isDeleted = 'N'" +
				" and model.partyMeetingMinuteId =:minuteId ");
		
		Query query = getSession().createQuery(str.toString());		
		query.setParameter("minuteId", minuteId);
		
		return query.list();
		
	} 
}
