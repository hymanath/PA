package com.itgrids.partyanalyst.dao.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserSmsReceiverDAO;
import com.itgrids.partyanalyst.model.UserSmsReceiver;

public class UserSmsReceiverDAO extends GenericDaoHibernate<UserSmsReceiver, Serializable> implements IUserSmsReceiverDAO{

	public UserSmsReceiverDAO() {
		super(UserSmsReceiver.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSmsDetails(Long userId,Long typeId){
		Query query = getSession().createQuery("select model1.firstName,model.userSmsSent.sentTime,model.userSmsSent.message," +
				"model1.mobile,model.receiverId,model.userSmsReceiverId,model1.regionScopes.regionScopesId,model1.locationValue from UserSmsReceiver model,InformationManager model1 " +
				"where model.userSmsSent.user.userId = :userId and model.userSmsSent.smsType.smsTypeId = :typeId " +
				" and model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
				" and model.isDeleted != 'true'" );
		
		query.setParameter("userId", userId);
		query.setParameter("typeId", typeId);
		return query.list();
	}

	public Integer deleteSmsDetails(List<Long> smsResponseid){
		Query query = getSession().createQuery("update UserSmsReceiver model set model.isDeleted = 'true' " +
				"where model.userSmsReceiverId in( :smsResponseid)");
		
		query.setParameterList("smsResponseid", smsResponseid);
		return query.executeUpdate();
	}
	
	public Long getReceiverIdById(Long userSmsReceiverId)
	{
	Query query =getSession().createQuery("select model.receiverId from UserSmsReceiver model where model.userSmsReceiverId =:userSmsReceiverId ");
	query.setParameter("userSmsReceiverId", userSmsReceiverId);
	return (Long)query.uniqueResult();

	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatewiseSortingDetails(Long userId,Long typeId,Date date){
		Query query = getSession().createQuery("select model1.firstName,model.userSmsSent.sentTime,model.userSmsSent.message," +
				"model1.mobile,model.receiverId,model.userSmsReceiverId,model1.regionScopes.regionScopesId,model1.locationValue from UserSmsReceiver model,InformationManager model1 " +
				"where model.userSmsSent.user.userId = :userId and model.userSmsSent.smsType.smsTypeId = :typeId " +
				" and model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
				" and model.isDeleted != 'true' and date(model.userSmsSent.sentTime) = :date" );
		
		query.setParameter("userId", userId);
		query.setParameter("typeId", typeId);
		query.setParameter("date", date);
		return query.list();
	}
	
	public List<Object[]> getSmsDetailsBySearch(Long userId,Long typeId,String namesearchText,String mobileSearch)
	{
	StringBuilder str = new StringBuilder();

	str.append("select model1.firstName,model.userSmsSent.sentTime,model.userSmsSent.message," +
	"model1.mobile,model.receiverId,model.userSmsReceiverId,model1.regionScopes.regionScopesId,model1.locationValue from UserSmsReceiver model,InformationManager model1 " +
	"where model.userSmsSent.user.userId = :userId and model.userSmsSent.smsType.smsTypeId = :typeId " +
	" and model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
	" and model.isDeleted != 'true'" );
	if(!mobileSearch.equalsIgnoreCase(""))
	str.append(" and model1.mobile like '%"+mobileSearch+"%' " );
	if(!namesearchText.equalsIgnoreCase(""))
	{
	str.append(" and (model1.firstName like '%"+namesearchText+"%' or " );
	str.append("model1.lastName like '%"+namesearchText+"%' )" );
	}

	Query query = getSession().createQuery(str.toString());
	query.setParameter("userId", userId);
	query.setParameter("typeId", typeId);


	return query.list();
	}
	
	
	public List<Long> getSmsDetailsByLocationSearch(Long userId,Long typeId,String locationsearchText,Long scope)
	{
		StringBuilder str = new StringBuilder();

		str.append("select distinct model1.informationManagerId from UserSmsReceiver model,InformationManager model1,Constituency model2 " +
		"where model.userSmsSent.user.userId = :userId and model.userSmsSent.smsType.smsTypeId = :typeId " +
		" and model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
		" and model.isDeleted != 'true' and model1.locationValue = model2.constituencyId and model1.regionScopes.regionScopesId =:scope" );
		if(scope == 4 && !locationsearchText.equalsIgnoreCase(""))
		{
		str.append(" and model2.name like '%"+locationsearchText+"%' )" );
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("typeId", typeId);
		query.setParameter("scope", scope);

		return query.list();
	}
	public List<Long> getSmsDetailsByLocationSearchForMandal(Long userId,Long typeId,String locationsearchText,Long scope)
	{
		StringBuilder str = new StringBuilder();

		str.append("select distinct model1.informationManagerId from UserSmsReceiver model,InformationManager model1,Tehsil model2 " +
		"where model.userSmsSent.user.userId = :userId and model.userSmsSent.smsType.smsTypeId = :typeId " +
		" and model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
		" and model.isDeleted != 'true' and model1.locationValue = model2.tehsilId and model1.regionScopes.regionScopesId =:scope" );
		if(scope == 5 && !locationsearchText.equalsIgnoreCase(""))
		{
		str.append(" and model2.tehsilName like '%"+locationsearchText+"%' )" );
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("typeId", typeId);
		query.setParameter("scope", scope);

		return query.list();
	}
	
	
	public List<Long> getSmsDetailsByLocationSearchForLocalbody(Long userId,Long typeId,String locationsearchText,Long scope)
	{
		StringBuilder str = new StringBuilder();

		str.append("select distinct model1.informationManagerId from UserSmsReceiver model,InformationManager model1,LocalElectionBody model2 " +
		"where model.userSmsSent.user.userId = :userId and model.userSmsSent.smsType.smsTypeId = :typeId " +
		" and model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
		" and model.isDeleted != 'true' and model1.locationValue = model2.localElectionBodyId and model1.regionScopes.regionScopesId =:scope" );
		if(scope == 5 && !locationsearchText.equalsIgnoreCase(""))
		{
		str.append(" and model2.name like '%"+locationsearchText+"%' )" );
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("typeId", typeId);
		query.setParameter("scope", scope);

		return query.list();
	}
	
	
	public List<Long> getSmsDetailsByLocationSearchForBooth(Long userId,Long typeId,String locationsearchText,Long scope)
	{
		StringBuilder str = new StringBuilder();

		str.append("select distinct model1.informationManagerId from UserSmsReceiver model,InformationManager model1,Booth model2 " +
		"where model.userSmsSent.user.userId = :userId and model.userSmsSent.smsType.smsTypeId = :typeId " +
		" and model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
		" and model.isDeleted != 'true' and model1.locationValue = model2.boothId and model1.regionScopes.regionScopesId =:scope" );
		if(scope == 5 && !locationsearchText.equalsIgnoreCase(""))
		{
		str.append(" and model2.partNo like '%"+locationsearchText+"%' )" );
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("typeId", typeId);
		query.setParameter("scope", scope);


		return query.list();
	}
	
	public List<Object[]> getSmsDetailsByIds(List<Long> informationIds)
	{
	StringBuilder str = new StringBuilder();

	str.append("select model1.firstName,model.userSmsSent.sentTime,model.userSmsSent.message," +
	"model1.mobile,model.receiverId,model.userSmsReceiverId,model1.regionScopes.regionScopesId,model1.locationValue from UserSmsReceiver model,InformationManager model1 " +
	"where model1.informationManagerId = model.receiverId and model.receiverType.receiverTypeId = 4 " +
	" and model.isDeleted != 'true' and model1.informationManagerId in (:informationIds)" );
	
	Query query = getSession().createQuery(str.toString());
	query.setParameterList("informationIds", informationIds);
	return query.list();
	}
	
	
	
}
