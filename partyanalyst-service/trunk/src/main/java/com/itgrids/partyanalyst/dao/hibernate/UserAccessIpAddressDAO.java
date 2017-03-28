package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAccessIpAddressDAO;
import com.itgrids.partyanalyst.model.UserAccessIpAddress;

public class UserAccessIpAddressDAO extends GenericDaoHibernate<UserAccessIpAddress, Long> implements IUserAccessIpAddressDAO {
	
	public UserAccessIpAddressDAO() {
		super(UserAccessIpAddress.class);
	}

	public Long checkForUserAccessIPAddress(Long userId,String ipAddress)
	{
		Query query = getSession().createQuery(" Select count(model.userAccessIpAddressId) from UserAccessIpAddress model where " +
				" model.user.userId = :userId and model.ipAddress = :ipAddress");
		query.setParameter("userId", userId);
		query.setParameter("ipAddress",ipAddress);
		
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> checkDuplicateIpForUser(Long userId,String Ip)
	{
		Query query = getSession().createQuery("select model.userAccessIpAddressId from UserAccessIpAddress model where model.user.userId =:userId and model.ipAddress =:Ip");
		query.setParameter("userId",userId);
		query.setParameter("Ip", Ip);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllIpAddressByUser(Long userId)
	{
		return getHibernateTemplate().find("select model.ipAddress,model.userAccessIpAddressId from UserAccessIpAddress model where model.user.userId =?",userId);
	}
	
	public Integer deleteUserIpAddressById(Long userAcessIpAddressId)
	{
		Query query = getSession().createQuery("delete from UserAccessIpAddress model where model.userAccessIpAddressId =:userAcessIpAddressId");
		query.setParameter("userAcessIpAddressId",userAcessIpAddressId);
		return query.executeUpdate();
		
	}
	
	
}
