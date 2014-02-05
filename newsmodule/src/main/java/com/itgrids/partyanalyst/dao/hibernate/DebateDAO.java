package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateDAO;
import com.itgrids.partyanalyst.model.Debate;

public class DebateDAO  extends GenericDaoHibernate<Debate, Long> implements IDebateDAO{

	public DebateDAO() {
		super(Debate.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateDetailsForSelectedDebate(Long debateId)
	{
		return getHibernateTemplate().find("select model.debateId,model.startTime,model.endTime," +
				" model.channel.channelId,model.channel.channelName , " +
				" model.summary from Debate model " +
				" where model.debateId = ? and model.isDeleted = 'N'",debateId);
	}

}
