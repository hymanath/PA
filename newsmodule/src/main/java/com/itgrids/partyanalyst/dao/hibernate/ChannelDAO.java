package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IChannelDAO;
import com.itgrids.partyanalyst.model.Channel;

public class ChannelDAO extends GenericDaoHibernate<Channel, Long> implements IChannelDAO {

	 public ChannelDAO(){
	    	super(Channel.class);
	    }
	 
	 @SuppressWarnings("unchecked")
	public List<Channel> getChannelDetails(){
		Query query = getSession().createQuery("select model from Channel model");
		 
		return query.list();
	 }
}
