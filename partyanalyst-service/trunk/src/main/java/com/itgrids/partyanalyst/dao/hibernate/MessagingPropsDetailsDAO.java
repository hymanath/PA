package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMessagingPropsDetailsDAO;
import com.itgrids.partyanalyst.model.MessagingPropsDetails;
import com.itgrids.partyanalyst.model.WebServiceBaseUrl;

public class MessagingPropsDetailsDAO extends GenericDaoHibernate<MessagingPropsDetails, Long> implements IMessagingPropsDetailsDAO{

	public MessagingPropsDetailsDAO() {
		super(MessagingPropsDetails.class);
		// TODO Auto-generated constructor stub
	}
	public List<MessagingPropsDetails> getMessagingPropsDetails(String appName)
	{
		Query query = getSession().createQuery("select model from MessagingPropsDetails model where model.appName = :appName");
		query.setParameter("appName",appName);
		return query.list();
	}
}
