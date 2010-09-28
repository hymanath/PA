package com.itgrids.partyanalyst.dao.hibernate;


import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Constituency;


import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;

public class AnanymousUserDAO extends GenericDaoHibernate<AnanymousUser, Long> implements IAnanymousUserDAO {

	public AnanymousUserDAO() {
		super(AnanymousUser.class);
	}

	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkAnonymousUserLogin(String userId,
			String password) {
		Object[] parameters = {userId,password};
		return getHibernateTemplate().find("from AnanymousUser model where model.username = ? and model.password = ?",parameters);
	}

	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkForUserNameAvailabiity(String userName) {
		return getHibernateTemplate().find("select model.username from AnanymousUser model where model.username = ?",userName);
	}
	
	
}
