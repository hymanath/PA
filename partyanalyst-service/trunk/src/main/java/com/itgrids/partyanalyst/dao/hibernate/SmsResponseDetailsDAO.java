package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISmsResponseDetailsDAO;
import com.itgrids.partyanalyst.model.SmsResponseDetails;

public class SmsResponseDetailsDAO  extends GenericDaoHibernate<SmsResponseDetails,Long> implements ISmsResponseDetailsDAO {
	
	public SmsResponseDetailsDAO() {
		super(SmsResponseDetails.class);
	}
	
	public List<SmsResponseDetails> getVoiceSmsHistoryForAuser(Long userId)
	{
		
		Query query = getSession().createQuery("select model from SmsResponseDetails model where model.user.userId = :userId");
		
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<SmsResponseDetails> getVoiceSmsHistoryForAllSubUsers(List<Long> userIds ,Integer startIndex , Integer maxResults)
	{
		Query query = getSession().createQuery("select model from SmsResponseDetails model " +
				"where model.user.userId in( :userIds)");
		
		query.setParameterList("userIds", userIds);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();
	}
	
	public List<Long> getVoiceSmsHistoryCountForAllSubUsers(List<Long> userIds)
	{
		Query query = getSession().createQuery("select count(*) from SmsResponseDetails model " +
				"where model.user.userId in( :userIds)");
		
		query.setParameterList("userIds", userIds);
		
		
		return query.list();
	}
	
	public List<Object[]> getVoiceSmsSentUserDetails(Date fromDate,Date toDate)
	{
		
		Query query = getSession().createQuery("select distinct model.user.firstName , model.user.lastName , model.user.userId " +
				"from  SmsResponseDetails model where  model.timeSent between date(:fromDate) and date(:toDate)");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	public List<String> getResponseCodesForAnUser(Long userId)
	{
		Query query = getSession().createQuery("select model.responseCode from SmsResponseDetails model " +
				"where model.user.userId = :userId");
		
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSmsDetails(Long userId,Long typeId){
		Query query = getSession().createQuery("select model.noOfSmsSent,model.timeSent,model.smsDescription," +
				"model.mobileNumbers,model.smsResponseDetailsId from SmsResponseDetails model " +
				"where model.user.userId = :userId and model.smsType.smsTypeId = :typeId");
		
		query.setParameter("userId", userId);
		query.setParameter("typeId", typeId);
		return query.list();
	}
	
	public Integer deleteSmsDetails(Long smsResponseid){
		Query query = getSession().createQuery("update SmsResponseDetails model set model.isDeleted = 'true' " +
				"where model.id = :smsResponseid");
				
		query.setParameter("smsResponseid", smsResponseid);
		return query.executeUpdate();
	}
}

