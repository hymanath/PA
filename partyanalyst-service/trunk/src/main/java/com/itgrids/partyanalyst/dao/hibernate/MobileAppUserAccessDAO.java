package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessDAO;
import com.itgrids.partyanalyst.model.MobileAppUserAccess;
import com.itgrids.partyanalyst.utils.IConstants;

public class MobileAppUserAccessDAO extends GenericDaoHibernate<MobileAppUserAccess, Long> implements IMobileAppUserAccessDAO{

	public MobileAppUserAccessDAO() {
		super(MobileAppUserAccess.class);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAuthorisedRecords(String uniqueCode,String macAddressId)
	{
		Object[] params = {uniqueCode};
		return getHibernateTemplate().find("select model.mobileAppUserAccessId from MobileAppUserAccess model where model.mobileAppUser.uniqueCode =? and " +
				"model.isAuthorised ='"+IConstants.TRUE+"'",params);
	}
	
	
	public List<Object[]> getMobileAppUserdetails()
	{
		return getHibernateTemplate().find("select distinct model1.firstName,model1.lastName,model.mobileAppUser.user.userId,model.mobileAppUser.user.firstName,model.mobileAppUser.user.lastName,model.mobileAppUser.uniqueCode,model.isAuthorised,model.lastAuthorisedTime,model.mobileAppUser.mobileAppUserId,model.mobileAppUser.mobileNo from MobileAppUserAccess model,MobileAppUserProfile model1 where model.mobileAppUser.mobileAppUserId = model1.mobileAppUser.mobileAppUserId");
	}
	

	public List<Long> getMobileAppUserAccessIds(List<Long> mobileAppUserIds)
	{
		Query query = getSession().createQuery("select model.mobileAppUserAccessId from MobileAppUserAccess model where model.mobileAppUser.mobileAppUserId in (:mobileAppUserIds)");
		query.setParameterList("mobileAppUserIds", mobileAppUserIds);
		return query.list();
		
	}
	public List<Object> getMobileAppUserAccesId(Long mobileAppUserId)
	{
		return getHibernateTemplate().find("select model.mobileAppUserAccessId from MobileAppUserAccess model where model.mobileAppUser.mobileAppUserId = ?",mobileAppUserId);
	}
	
	public List<Object[]> getMobileAppUserdetailsByMobileAppUserId(Long mobileAppUserId)
	{
		Query query = getSession().createQuery("select model1.firstName,model1.lastName,model.mobileAppUser.mobileNo,model.mobileAppUser.email,model.mobileAppUser.userName,model.mobileAppUser.password,model.mobileAppUser.uniqueCode,model.isAuthorised,model.lastAuthorisedTime,model.mobileAppUser.mobileAppUserId,model.appId,model.deviceId,model.macAddress,model1.gender from MobileAppUserAccess model,MobileAppUserProfile model1 where model.mobileAppUser.mobileAppUserId = :mobileAppUserId and model.mobileAppUser.mobileAppUserId = model1.mobileAppUser.mobileAppUserId");
		query.setParameter("mobileAppUserId", mobileAppUserId);
		return query.list();
	}
	public List<Object[]> getSuperAdminDetailsByMobileAppUserId(Long mobileAppUserId)
	{
		Query query = getSession().createQuery("select model.mobileAppUser.mobileAppUserId,model.mobileAppUser.mobileAppUser.userName,model.mobileAppUser.mobileAppUser.password,model.mobileAppUser.mobileAppUser.uniqueCode from MobileAppUserAccess model,MobileAppUserProfile model1 where model.mobileAppUser.mobileAppUserId = :mobileAppUserId and model.mobileAppUser.mobileAppUserId = model1.mobileAppUser.mobileAppUserId");
		query.setParameter("mobileAppUserId", mobileAppUserId);
		return query.list();
	}
	public List getMobileAppUserdetailsByUserId(Long mobileAppUserId)
	{
		Query query = getSession().createQuery("select model1.firstName,model1.lastName,model.mobileAppUser.mobileNo,model.mobileAppUser.email,model.mobileAppUser.userName,model.mobileAppUser.password,model.mobileAppUser.uniqueCode,model.appId,model.deviceId,model.macAddress,model1.gender,model.mobileAppUser.mobileAppUser.mobileAppUserId from MobileAppUserAccess model,MobileAppUserProfile model1 where model.mobileAppUser.user.userId = :mobileAppUserId and model.mobileAppUser.mobileAppUserId = model1.mobileAppUser.mobileAppUserId" +
				"  order by model.mobileAppUser.mobileAppUserId desc");
		query.setParameter("mobileAppUserId", mobileAppUserId);
		return query.list();
	}
	
	public List<Object[]> getMobileAppUserAuthorisedTime()
	{
		return getHibernateTemplate().find("select model1.firstName,model1.lastName,model.lastAuthorisedTime,model.mobileAppUser.mobileAppUserId,model.mobileAppUser.mobileNo from MobileAppUserAccess model,MobileAppUserProfile model1 where model.mobileAppUser.mobileAppUserId = model1.mobileAppUser.mobileAppUserId");
	}
	
	
}
