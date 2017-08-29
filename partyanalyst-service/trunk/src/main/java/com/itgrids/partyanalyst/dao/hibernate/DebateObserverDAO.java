package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateObserverDAO;
import com.itgrids.partyanalyst.model.DebateObserver;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateObserverDAO extends GenericDaoHibernate<DebateObserver, Long> implements IDebateObserverDAO{

	public DebateObserverDAO() {
		super(DebateObserver.class);

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getObsersListForDebate(Long debateId)
	{
		/*return getHibernateTemplate().find("select model.observer.observerName,model.debateObserverid,model.observer.observerId from DebateObserver model " +
				" where model.debate.debateId = ? ",debateId);*/
		StringBuilder sb= new StringBuilder();
		sb.append("select model.observer.observerName,model.debateObserverid,model.observer.observerId from DebateObserver model " );
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }*/
		sb.append(" where " );
		if(debateId != null && debateId.longValue()>0)
		   sb.append(" model.debate.debateId =:debateId  ");
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model.debate.debateId = model3.debateParticipant.debateId  and model3.isDeleted = 'N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }*/
		Query query =getSession().createQuery(sb.toString());
		if(debateId != null && debateId.longValue()>0)
			query.setParameter("debateId", debateId);
		return query.list();
	}
}
