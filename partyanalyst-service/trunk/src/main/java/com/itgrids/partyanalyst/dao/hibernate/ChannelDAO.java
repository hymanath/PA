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
	 
	 public Long checkForExists(String name)
		{
			Query query = getSession().createQuery("select count(model.channelId) from Channel model " +
					" where model.channelName = :name");
			query.setParameter("name", name);
			return (Long)query.uniqueResult();
		}
	 
	 @SuppressWarnings("unchecked")
		public List<Object[]> getChannelDetailsNew(){
			Query query = getSession().createQuery("select model.channelId,model.channelName from Channel model " +
					" where model.isDeleted ='N' ");
			 
			return query.list();
		 }
}
