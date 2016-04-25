package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILoginRequiredUrlDAO;
import com.itgrids.partyanalyst.model.LoginRequiredUrl;

public class LoginRequiredUrlDAO extends GenericDaoHibernate<LoginRequiredUrl, Long> implements ILoginRequiredUrlDAO {
	
	public LoginRequiredUrlDAO() {
		super(LoginRequiredUrl.class);
	}
	
	public String getIsLoginUrl(String loginUrl){
		Query query = getSession().createQuery("select model.isLoginRequired from LoginRequiredUrl model where model.url =: loginUrl");
		query.setParameter("loginUrl", loginUrl);
		return (String) query.uniqueResult();
	}
}
