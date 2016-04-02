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
	
	public List<UserAddress> getUserAddressByUserAddressId(Long userAddressId){
		Query query = getSession().createQuery(" select model from UserAddress model where model.userAddressId =:userAddressId ");
		query.setParameter("userAddressId", userAddressId);
		
		return query.list();
	}
	
	public List<Object[]> getUserAddressDetails(List<Long> candidateIdsList){
		Query query = getSession().createQuery(" select model.district.districtId,model.constituency.constituencyId,model.tehsil.tehsilId,model.hamlet.hamletId,model.parliamentConstituency.constituencyId" +
				" from UserAddress model where model.userAddressId in(:candidateIdsList) ");
		query.setParameterList("candidateIdsList", candidateIdsList);
		return query.list();
	}
}
