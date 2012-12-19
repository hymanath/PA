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
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserSocialNetworkSiteDAO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.UserSocialNetworkSite;



public class UserSocialNetworkSiteDAO extends GenericDaoHibernate<UserSocialNetworkSite, Long> implements IUserSocialNetworkSiteDAO{

	
	public UserSocialNetworkSiteDAO() {
		super(UserSocialNetworkSite.class);
		
	}

	@SuppressWarnings("unchecked")
	public List getTwitterIdByPartyName(String partyName){
		
		Query query = getSession().createQuery("select model.twitterProfileId from UserSocialNetworkSite model where model.partyName=?");
		query.setParameter(0, partyName);
		return  query.list();
		
		
		
	}

}
