package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.model.DebateSubject;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateSubjectDAO extends GenericDaoHibernate<DebateSubject, Long> implements IDebateSubjectDAO{

	public DebateSubjectDAO()
	{
		super(DebateSubject.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateSubjectDetails(Long debateId)
	{
		/*return getHibernateTemplate().find("select model.subject , model.debateSubjectId from DebateSubject model  " +
				" where model.debate.debateId = ? ",debateId);*/
		StringBuilder sb= new StringBuilder();
		sb.append("select model.subject , model.debateSubjectId from DebateSubject model  " );
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }*/
		sb.append("where " );
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
		Query query = getSession().createQuery(sb.toString());
		if(debateId != null && debateId.longValue()>0)
			query.setParameter("debateId", debateId);
		return query.list();
	}
	
	/*
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateDetalsForSelectedDates(Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery("select distinct model.debate.debateId,model.subject,model.debate.startTime " +
				" from DebateSubject model where date(model.debate.startTime) >= :fromDate and date(model.debate.startTime) <= :toDate " +
				" and model.debate.isDeleted = 'N'");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}
	*/
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateDetalsForSelectedCriteria(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortBy,String sort,int startIndex,int maxIndex,String isCount,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		if(isCount != null){
			sb.append("select count(distinct model.debate.debateId),model.subject from DebateSubject model ");
		}
		else{
			sb.append("select distinct model.debate.debateId,model.subject,model.debate.startTime from DebateSubject model ");	
		}
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }
		if(channelIds != null || candidateIds != null || partyIds != null)
		{
			sb.append(" , DebateParticipant model1 where model.debate.debateId = model1.debate.debateId and ");
		}
		else
		{
			sb.append(" where ");
		}
		 
		if(stateId != null && stateId.longValue() > 0){
		      sb.append("  model.debate.debateId = model3.debateParticipant.debateId and  model3.isDeleted ='N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId = " +IConstants.DEBATE_AP_STATE_ID+ " and ");
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId = "+IConstants.DEBATE_TS_STATE_ID+" and ");
		    }
		sb.append(" date(model.debate.startTime) >= :fromDate and date(model.debate.startTime) <= :toDate " +
				" and model.debate.isDeleted = 'N' ");
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and model1.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and model1.candidate.candidateId in (:candidateIds) and model1.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  model1.party.partyId in (:partyIds)  ");
		}
		
		sb.append(" order by model.debate.startTime desc ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		if(channelIds != null   && channelIds.size()>0)
		{
			query.setParameterList("channelIds", channelIds);
		}
		if(candidateIds != null   && candidateIds.size()>0)
		{
			query.setParameterList("partyIds", partyIds);
			query.setParameterList("candidateIds", candidateIds);
		}
		else if(partyIds != null   && partyIds.size()>0)
		{
			query.setParameterList("partyIds", partyIds);
		}
		if(isCount == null){
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
		}
		
		return query.list();
	}
	
	
	/*
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateDetalsForSelectedCriteria(Date fromDate,Date toDate,Long channelId,Long partyId,Long candidateId,String sortBy,String sort,int startIndex,int maxIndex,String isCount)
	{
		StringBuilder sb = new StringBuilder();
		if(isCount != null){
			sb.append("select count(distinct model.debate.debateId),model.subject from DebateSubject model ");
		}
		else{
			sb.append("select distinct model.debate.debateId,model.subject,model.debate.startTime from DebateSubject model ");	
		}
		
		if(channelId != null || candidateId != null || partyId != null)
		{
			sb.append(" , DebateParticipant model1 where model.debate.debateId = model1.debate.debateId and ");
		}
		else
		{
			sb.append(" where ");
		}
		sb.append(" date(model.debate.startTime) >= :fromDate and date(model.debate.startTime) <= :toDate " +
				" and model.debate.isDeleted = 'N' ");
		if(channelId != null && channelId > 0)
		{
			sb.append(" and model1.debate.channel.channelId = :channelId ");
		}
		
		if(candidateId != null && candidateId > 0)
		{
			sb.append(" and model1.candidate.candidateId = :candidateId  and model1.party.partyId = :partyId  ");
		}
		else if(partyId != null && partyId > 0)
		{
			sb.append(" and  model1.party.partyId = :partyId  ");
		}
		sb.append(" order by model.debate.startTime desc ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		if(channelId != null && channelId > 0)
		{
			query.setParameter("channelId", channelId);
		}
		if(candidateId != null && candidateId > 0)
		{
			query.setParameter("partyId", partyId);
			query.setParameter("candidateId", candidateId);
		}
		else if(partyId != null && partyId > 0)
		{
			query.setParameter("partyId", partyId);
		}
		if(isCount == null){
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
		}
		return query.list();
	}
	*/
	@SuppressWarnings("unchecked")
	public List<Object[]> searchCriteriaForDebateSearch(String searchString)
	{
		return getHibernateTemplate().find("select distinct model.debate.debateId,model.subject,model.debate.startTime from DebateSubject model " +
				" where model.subject like   '%"+searchString+"%'" );
			
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateSubjectDetailsOfList(Set<Long> debateIds,Long stateId)
	{
		StringBuilder str =new StringBuilder();
		str.append("select  model.debate.debateId,model.debateSubjectId,model.subject from DebateSubject model " );
		if(stateId != null && stateId.longValue() > 0){
		      str.append(" , Debate model3 ");
		    }
		str.append(" where " );
		if(debateIds != null && debateIds.size()>0)
		   str.append(" model.debate.debateId in (:debateIds)");
		if(stateId != null && stateId.longValue() > 0){
		      str.append(" and model.debate.debateId = model3.debateId  and model3.isDeleted ='N' ");
		    }
		   if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      str.append(" and model3.address.state.stateId ="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      str.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 2L){
		    	str.append(" and model3.address.state.stateId="+IConstants.DEBATE_OTHERS_ID);
		    }
		Query query = getSession().createQuery(str.toString());
		if(debateIds != null && debateIds.size()>0)
		query.setParameterList("debateIds", debateIds);
		return query.list();
	}

}
