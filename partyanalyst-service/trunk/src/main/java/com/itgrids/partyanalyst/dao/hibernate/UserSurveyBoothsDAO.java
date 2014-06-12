package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserSurveyBoothsDAO;
import com.itgrids.partyanalyst.model.UserSurveyBooths;

public class UserSurveyBoothsDAO extends GenericDaoHibernate<UserSurveyBooths, Long> implements IUserSurveyBoothsDAO{

	public UserSurveyBoothsDAO() {
		super(UserSurveyBooths.class );
		
	}
	
	public List<Object[]> getPublicationIdByUserId(Long userId)
	{
		Query query = getSession().createQuery("select model.constituency.constituencyId,model.publicationDate.publicationDateId from UserSurveyBooths model" +
				" where model.user.userId = :userId");
		query.setParameter("userId", userId);
		return query.list();
		
	}

}
