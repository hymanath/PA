package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightsVendorUserDAO;
import com.itgrids.model.LightsVendorUser;

@Repository
public class LightsVendorUserDAO extends GenericDaoHibernate<LightsVendorUser, Long> implements ILightsVendorUserDAO{

	public LightsVendorUserDAO() {
		super(LightsVendorUser.class);
	}
	
	public List<Object[]> getVendorIdByUserId(Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.lightsVendor.lightsVendorId,"
				+ "model.lightsVendor.vendorName,model.user.username "
				+ " from LightsVendorUser model"
				+ " where model.isDeleted = 'N'"
				+ " and model.user.isDeleted ='N' and model.user.isEnabled ='Y'"
				+ " and model.user.redirectUrl.isDeleted ='N'");
		if(userId != null && userId.longValue() > 0L){
			sb.append(" and model.user.userId = :userId");
		}
		Query query = getSession().createQuery(sb.toString());
		if(userId != null && userId.longValue() > 0L)
			query.setParameter("userId", userId);
		
		return query.list();
	}

}
