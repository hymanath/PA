package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.model.UserAddress;

public class UserAddressDAO extends GenericDaoHibernate<UserAddress, Long> implements IUserAddressDAO {

	public UserAddressDAO() {
		super(UserAddress.class);		
	}
	public Integer deleteInfluencingPeopleById(Long userAddressId){
		Query queryObject = getSession().createQuery("delete from UserAddress model where model.userAddressId = ?");
		queryObject.setParameter(0, userAddressId);
		return queryObject.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAddress> getUserAddressList()
	{
		return getHibernateTemplate().find(" from UserAddress model ");
	}
}
