package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserWorkTypeDAO;
import com.itgrids.model.MobileAppUserWorkType;

@Repository
public class MobileAppUserWorkTypeDAO extends GenericDaoHibernate<MobileAppUserWorkType, Long> implements IMobileAppUserWorkTypeDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserWorkTypeDAO() {
		super(MobileAppUserWorkType.class);		
	}
	
	public List<Object[]> getUsersGovtWorkTypes(Long userId){
		Query query = getSession().createQuery(" select model.govtWorkType.govtWorkTypeId,model.govtWorkType.workType "
				+ " from MobileAppUserWorkType model where model.mobileAppUserId=:userId ");
		query.setParameter("userId", userId);
		return query.list();
	}
}
