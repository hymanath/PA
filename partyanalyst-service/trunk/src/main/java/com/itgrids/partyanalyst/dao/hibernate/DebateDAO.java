package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateDAO;
import com.itgrids.partyanalyst.model.Debate;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateDAO  extends GenericDaoHibernate<Debate, Long> implements IDebateDAO{

	public DebateDAO() {
		super(Debate.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateDetailsForSelectedDebate(Long debateId,Long stateId)
	{
		/*return getHibernateTemplate().find("select model.debateId,model.startTime,model.endTime," +
				" model.channel.channelId,model.channel.channelName , " +
				" model.summary,model.youtubeUrl from Debate model " +
				" where model.debateId = ? and model.isDeleted = 'N'",debateId);*/
		StringBuilder sb= new StringBuilder();
		sb.append(" select model.debateId,model.startTime," +
				" model.endTime," +
				" model.channel.channelId,model.channel.channelName , " +
				" model.summary,model.youtubeUrl, model.address.state.stateId from Debate model " );
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , Debate model3 ");
		    }*/
		sb.append(" where  model.isDeleted = 'N' ");
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model.debateId = model3.debateId  and model3.isDeleted = 'N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }*/
		
		if(debateId != null && debateId.longValue()>0 ){
			sb.append(" and model.debateId =:debateId ");
				}
		Query query = getSession().createQuery(sb.toString());
		if(debateId != null && debateId.longValue()>0 ){
			query.setParameter("debateId", debateId);
				}
		
		return query.list();
		
	}
	
	public int removeDebate(Long debateId)
	{
		Query query = getSession().createQuery("delete from Debate model where model.debateId = :debateId");
		query.setParameter("debateId", debateId);
		int result = query.executeUpdate();
		return result;
	}

	
	public int deleteFlagDebate(Long debateId)
	{
		Query query = getSession().createQuery("update  Debate model set model.isDeleted = 'Y' " +
				" where model.debateId = :debateId");
		query.setParameter("debateId", debateId);
		int result = query.executeUpdate();
		return result;
	}
	
	public Object[] getLatestDebate(){
		Query query = getSession().createQuery(" SELECT model.debateId,model.startTime FROM Debate model " +
				" WHERE" +
				" 	model.isDeleted = 'N' " +
				" ORDER BY model.startTime desc ");
		
		query.setMaxResults(1);
		return (Object[]) query.uniqueResult();
		
	}

}
