package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoiceSmsResponseDetailsDAO;
import com.itgrids.partyanalyst.model.VoiceSmsResponseDetails;

public class VoiceSmsResponseDetailsDAO  extends GenericDaoHibernate<VoiceSmsResponseDetails,Long> implements IVoiceSmsResponseDetailsDAO {
	
	public VoiceSmsResponseDetailsDAO() {
		super(VoiceSmsResponseDetails.class);
	}
	
	public List<VoiceSmsResponseDetails> getVoiceSmsHistoryForAuser(Long userId)
	{
		
		Query query = getSession().createQuery("select model from VoiceSmsResponseDetails model where model.user.userId = :userId");
		
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<VoiceSmsResponseDetails> getVoiceSmsHistoryForAllSubUsers(List<Long> userIds ,Integer startIndex , Integer maxResults)
	{
		Query query = getSession().createQuery("select model from VoiceSmsResponseDetails model " +
				"where model.user.userId in( :userIds)");
		
		query.setParameterList("userIds", userIds);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();
	}
	
	public List<Long> getVoiceSmsHistoryCountForAllSubUsers(List<Long> userIds)
	{
		Query query = getSession().createQuery("select count(*) from VoiceSmsResponseDetails model " +
				"where model.user.userId in( :userIds)");
		
		query.setParameterList("userIds", userIds);
		
		
		return query.list();
	}
	
	public List<Object[]> getVoiceSmsSentUserDetails(Date fromDate,Date toDate)
	{
		
		Query query = getSession().createQuery("select distinct model.user.firstName , model.user.lastName , model.user.userId " +
				"from  VoiceSmsResponseDetails model where  model.sentDate between date(:fromDate) and date(:toDate)");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	public List<String> getResponseCodesForAnUser(Long userId)
	{
		Query query = getSession().createQuery("select model.responseCode from VoiceSmsResponseDetails model " +
				"where model.user.userId = :userId");
		
		query.setParameter("userId", userId);
		return query.list();
	}
}

