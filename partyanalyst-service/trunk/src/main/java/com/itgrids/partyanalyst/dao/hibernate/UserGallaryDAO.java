package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserGallaryDAO extends GenericDaoHibernate<UserGallary,Long> implements IUserGallaryDAO{
	
	public UserGallaryDAO()
	{
		super(UserGallary.class);
	}
	
	public List<Object[]> getAllNewsGallaryBasedOnUserId(Long userId){
		
		Query query =  getSession().createQuery("SELECT model.gallary.gallaryId,model.gallary.name FROM UserGallary model " +
				"WHERE model.registration.registrationId =:userId and model.gallary.contentType.contentType =:contentType " +
				"and model.gallary.isDelete =:isDelete order by model.gallary.updateddate asc");
		query.setParameter("userId", userId);
		query.setParameter("contentType", IConstants.NEWS_GALLARY);
		query.setParameter("isDelete", IConstants.FALSE);
		 return query.list();
	}
}
