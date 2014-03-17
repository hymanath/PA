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
	
	public void deleteUserAddressByFileId(Long fileId){
		Query query = getSession().createQuery(" delete from UserAddress model where model.file.fileId = :fileId");
		query.setParameter("fileId", fileId);
		query.executeUpdate();
	}
	
	public List<UserAddress> getAllAddress(Long fileId,Long districtId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" Select model from UserAddress model where model.file.fileId = :fileId ");
		if(districtId != null && districtId.longValue() > 0){
			queryStr.append(" and model.district.districtId =:districtId ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(districtId != null && districtId.longValue() > 0){
			query.setParameter("districtId", districtId);
		}
		query.setParameter("fileId", fileId);
		return query.list();
	}
	public List<Object[]> getfileAddressListByFileId(Long fileId){

		return getHibernateTemplate().find("select model.regionScopes.regionScopesId,model.locationValue,model.constituency.constituencyId from UserAddress model where model.file.fileId = ?",fileId);
		}
}
