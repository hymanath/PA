package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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

}
