package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.IEventSurveyUserDAO;
import com.itgrids.partyanalyst.model.EventSurveyUser;

public class EventSurveyUserDAO extends GenericDaoHibernate<EventSurveyUser, Long> implements IEventSurveyUserDAO{

	public EventSurveyUserDAO() {
		super(EventSurveyUser.class);
		
	}
	
	public List<Object[]> getUserDetailsByUnamePwd(String uname,String pwd)
	{
		
		Query query = getSession().createQuery("select model.eventSurveyUserId,model.firstName,model.lastName from EventSurveyUser model where model.userName = :uname " +
				"and model.passWord = :pwd and model.isEnabled = 'Y' ");
		query.setParameter("uname", uname);
		query.setParameter("pwd", pwd);
		return query.list();
	}

	public List<EventSurveyUser> checkValidUserOrNot(String uname,String pwd){
		Session session = getSession();
		session.setFlushMode(FlushMode.AUTO);
		Query query = session.createQuery("select model from EventSurveyUser model where model.userName = :uname " +
				"and model.passWord = :pwd and model.isEnabled = 'Y' ");
		query.setParameter("uname", uname);
		query.setParameter("pwd", pwd);
		return query.list();
	}
	
	public Long checkUserBlockedOrNot(Long userId){
		Session session = getSession();
		session.setFlushMode(FlushMode.AUTO);
		Query query = session.createQuery("select count(*) from EventSurveyUser model where model.eventSurveyUserId = :userId " +
				" and model.isEnabled = 'N' ");
		query.setParameter("userId", userId);
		return (Long)query.uniqueResult();
	}
}
