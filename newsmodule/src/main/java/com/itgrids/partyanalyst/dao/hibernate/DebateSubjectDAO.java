package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateDetalsForSelectedCriteria(Date fromDate,Date toDate,Long channelId,Long partyId,Long candidateId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.debate.debateId,model.subject,model.debate.startTime from DebateSubject model ");
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
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchCriteriaForDebateSearch(String searchString)
	{
		return getHibernateTemplate().find("select distinct model.debate.debateId,model.subject,model.debate.startTime from DebateSubject model " +
				" where model.subject like   '%"+searchString+"%'" );
			
	}

}
