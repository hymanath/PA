package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserEmailDAO;
import com.itgrids.partyanalyst.model.UserEmail;

public class UserEmailDAO extends GenericDaoHibernate<UserEmail, Long> implements IUserEmailDAO {
	public UserEmailDAO(){
		super(UserEmail.class);
	}
	public List<Object[]> getEmailList(Long emailId){
		Query query = getSession().createQuery("select model.userEmailId, model.email from UserEmail model where model.userEmailId = :emailId");
		query.setParameter("emailId", emailId);
		return query.list();
	}
}
