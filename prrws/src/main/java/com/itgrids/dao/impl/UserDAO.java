package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IUserDAO;
import com.itgrids.model.User;

@Repository
public class UserDAO extends GenericDaoHibernate<User, Long> implements IUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public UserDAO() {
		super(User.class);

	}

	@Override
	public User loginAuthentication(String userName, String password) {
		Query query = getSession().createQuery("select model from User model where model.username=:userName and model.passwordHashText=:password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return (User) query.uniqueResult();
	}
	@Override
	public Object[] getUrlForMatchedCredentials(String userName, String password) {
		StringBuilder sb = new StringBuilder();
			sb.append("select model.redirectUrl.url,model.userId,model.username from User model ");
			sb.append(" where model.username =:userName and  model.password =:password ");
			sb.append(" and model.redirectUrl.isDeleted ='N' and model.isDeleted ='N' and model.isEnabled ='Y' ");
		Query query  = getSession().createQuery(sb.toString());
			query.setParameter("userName", userName);
			query.setParameter("password", password);
		return (Object[]) query.uniqueResult();
	}
}
