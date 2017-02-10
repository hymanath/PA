package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITvNewsChannelDAO;
import com.itgrids.partyanalyst.model.TvNewsChannel;

public class TvNewsChannelDAO extends GenericDaoHibernate<TvNewsChannel, Long> implements ITvNewsChannelDAO{

	public TvNewsChannelDAO() {
		super(TvNewsChannel.class);
		
	}
	public List<Object[]> getChannelList(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select tvNewsChannel.tvNewsChannelId, tvNewsChannel.channelName from TvNewsChannel tvNewsChannel order by tvNewsChannel.channelName ");
		Query query = getSession().createQuery(queryStr.toString());  
		return query.list();
	}

}
