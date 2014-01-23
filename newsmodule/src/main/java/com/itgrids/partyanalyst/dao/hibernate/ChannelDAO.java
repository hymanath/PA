package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IChannelDAO;
import com.itgrids.partyanalyst.model.Channel;

public class ChannelDAO extends GenericDaoHibernate<Channel, Long> implements IChannelDAO {

	 public ChannelDAO(){
	    	super(Channel.class);
	    }
}
