package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.model.DebateSubject;

public class DebateSubjectDAO extends GenericDaoHibernate<DebateSubject, Long> implements IDebateSubjectDAO{

	public DebateSubjectDAO()
	{
		super(DebateSubject.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateSubjectDetails(Long debateId)
	{
		return getHibernateTemplate().find("select model.subject , model.debateSubjectId from DebateSubject model  " +
				" where model.debate.debateId = ? ",debateId);
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
	public List<Object[]> getDebateDetalsForSelectedCriteria(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortBy,String sort,int startIndex,int maxIndex,String isCount)
	{
		StringBuilder sb = new StringBuilder();
		if(isCount != null){
			sb.append("select count(distinct model.debate.debateId),model.subject from DebateSubject model ");
		}
		else{
			sb.append("select distinct model.debate.debateId,model.subject,model.debate.startTime from DebateSubject model ");	
		}
		
		if(channelIds != null || candidateIds != null || partyIds != null)
		{
			sb.append(" , DebateParticipant model1 where model.debate.debateId = model1.debate.debateId and ");
		}
		else
		{
			sb.append(" where ");
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
	public List<Object[]> getDebateSubjectDetailsOfList(Set<Long> debateIds)
	{
		
		Query query = getSession().createQuery("select  model.debate.debateId,model.debateSubjectId,model.subject from DebateSubject model  " +
				" where model.debate.debateId in (:debateIds) ");
		
		query.setParameterList("debateIds", debateIds);
		return query.list();
	}

}
