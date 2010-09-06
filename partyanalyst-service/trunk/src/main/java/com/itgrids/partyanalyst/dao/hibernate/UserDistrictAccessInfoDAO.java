package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserDistrictAccessInfo;

public class UserDistrictAccessInfoDAO extends GenericDaoHibernate<UserDistrictAccessInfo, Long> implements IUserDistrictAccessInfoDAO{

	public UserDistrictAccessInfoDAO() {
		super(UserDistrictAccessInfo.class);
	}
	
	public List findByUser(Long userId){
		return getHibernateTemplate().find("select model.district.districtId, model.district.districtName " +
				"from UserDistrictAccessInfo model where model.user.registrationId = ?", userId);
	}
}
