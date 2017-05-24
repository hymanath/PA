package com.itgrids.dao;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.model.UserAddress;

@Repository("userAddressDAO")
public class UserAdressDAO extends GenericDaoHibernate<UserAddress,Long> implements IUserAdressDAO{

	public UserAdressDAO()
	{
		super(UserAddress.class);
	}
	
	public UserAddress getUserAddressById(Long userAddressId)
	{
		Query query = getSession().createQuery("Select model from UserAddress model where model.userAddressId = :userAddressId");
		query.setParameter("userAddressId",userAddressId);
		return (UserAddress) query.uniqueResult();
	}
	
	public Integer updateUserAddress(Long stateId,Long districtId)
	{
		Query query = getSession().createQuery("update UserAddress model set model.stateId = :stateId where model.districtId = :districtId");
		query.setParameter("stateId",stateId);
		query.setParameter("districtId",districtId);
		return query.executeUpdate();
	}
}
