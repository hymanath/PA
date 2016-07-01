package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.model.DebateParticipantCharcs;

public class DebateParticipantCharcsDAO extends GenericDaoHibernate<DebateParticipantCharcs, Long> implements IDebateParticipantCharcsDAO{

	public DebateParticipantCharcsDAO() {
		super(DebateParticipantCharcs.class);
		// TODO Auto-generated constructor stub
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateCharcsDetails(Long debateId)
	{
		return getHibernateTemplate().find("select model.debateParticipant.candidate.candidateId ,model.debateParticipant.candidate.lastname ," +
				" model.debateParticipant.party.partyId,model.debateParticipant.party.shortName ," +
				" model.characteristics.name,model.scale,model.characteristics.characteristicsId from  DebateParticipantCharcs model where " +
				" model.debateParticipant.debate.debateId = ? ",debateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebatePerformanceCount(){
		Query query = getSession().createQuery("select DP.candidateId , sum(DPC.scale) "+
	          "from DebateParticipantCharcs DPC, DebateParticipant DP, Debate D "+
			  "where D.debateId = DP.debateId and DP.debateParticipantId = DPC.debateParticipantId "+
	          "group by DP.candidateId");
		return query.list();
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebatePerformanceCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("select DP.candidateId , sum(DPC.scale)   from DebateParticipantCharcs DPC, DebateParticipant DP, Debate D  ");	
		sb.append(" where D.debateId = DP.debateId and DP.debateParticipantId = DPC.debateParticipantId and   ");

		sb.append(" date(D.startTime) >= :fromDate and date(D.startTime) <= :toDate ");
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and D.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and DP.candidate.candidateId in (:candidateIds) and DP.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  DP.party.partyId in (:partyIds)  ");
		}
		sb.append(" group by DP.candidateId order by DP.party.partyId");
		
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
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebatePerformanceCountCharcs(){
		Query query = getSession().createQuery("select DP.candidateId ,DPC.characteristicsId, C.name, sum(DPC.scale) " +
				" 	from DebateParticipantCharcs DPC, DebateParticipant DP ,  Debate D , Characteristics C" +
				"	where D.debateId = DP.debateId and " +
				" 	DP.debateParticipantId = DPC.debateParticipantId " +
				"   and DPC.characteristicsId = C.characteristicsId"+
				"  group by DP.candidateId , DPC.characteristicsId");
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")

		public List<Object[]> getDebatePerformanceCountCharForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds)
		{
			StringBuilder sb = new StringBuilder();
			
			sb.append(" select DP.candidateId ,DPC.characteristicsId, C.name, sum(DPC.scale) 	from DebateParticipantCharcs DPC, DebateParticipant DP ,  Debate D , Characteristics C  " );
	
			sb.append(" where D.debateId = DP.debateId and  DP.debateParticipantId = DPC.debateParticipantId " +
				"   and DPC.characteristicsId = C.characteristicsId ");

			sb.append(" and date(D.startTime) >= :fromDate and date(D.startTime) <= :toDate ");
			
			if(channelIds != null && channelIds.size()>0)
			{
				sb.append(" and D.channel.channelId in (:channelIds) ");
			}
			
			if(candidateIds != null  && candidateIds.size()>0)
			{
				sb.append(" and DP.candidate.candidateId in (:candidateIds) and DP.party.partyId in (:partyIds)  ");
			}
			else if(partyIds != null  && partyIds.size()>0)
			{
				sb.append(" and  DP.party.partyId in (:partyIds)  ");
			}
			sb.append(" group by DP.candidateId , DPC.characteristicsId ");
			
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
			return query.list();
		}
	
	public List<Object[]> getPartyWiseTotalDebatesAndScales()
	{
		Query query = getSession().createQuery("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName " +
				",count(distinct model.debateParticipant.debate.debateId) ,sum(model.scale) from DebateParticipantCharcs model" +
				" group by model.debateParticipant.party.partyId");
		
		return query.list();
	}
	
	public List<Object[]> getPartyWiseTotalDebatesAndScalForSelection(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIds,  List<Long> candidateIds)
	{

		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName , count(distinct model.debateParticipant.debate.debateId) ,sum(model.scale)" +
				"  	from DebateParticipantCharcs model " +
				"  where model.debateParticipant.debate.isDeleted = 'N' " );
		
		if(fromDate !=null && toDate !=null){
			sb.append(" and date( model.debateParticipant.debate.startTime) >= :fromDate and date( model.debateParticipant.debate.startTime) <= :toDate ");
		}		
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and model.debateParticipant.candidate.candidateId in (:candidateIds) and model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		sb.append(" group by model.debateParticipant.party.partyId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}		
		
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
		return query.list();
	}
	
	public List<Object[]> getPartyWiseDebatePartiCharsCount()
	{
		Query query = getSession().createQuery("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.characteristics.characteristicsId ,sum(model.scale),model.characteristics.name from DebateParticipantCharcs model group by model.debateParticipant.party.partyId,model.characteristics.characteristicsId");
		
		return query.list();
	}
	
	public List<Object[]> getPartyWiseDebatePartiCharsCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds)
	{

		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName , " +
				" model.characteristics.characteristicsId ,sum(model.scale),model.characteristics.name  " +
				"  	from DebateParticipantCharcs model  " +
				" where model.debateParticipant.debate.isDeleted = 'N' " );

		if(fromDate !=null && toDate !=null){
			sb.append(" and date( model.debateParticipant.debate.startTime) >= :fromDate and date( model.debateParticipant.debate.startTime) <= :toDate ");
		}
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and model.debateParticipant.candidate.candidateId in (:candidateIds) and model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		sb.append(" group by model.debateParticipant.party.partyId,model.characteristics.characteristicsId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
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
		
		return query.list();
	}
	
	
	public List<Object[]> getPartyWiseEachDebateCharsCount()
	{
		Query query = getSession().createQuery("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId ,sum(model.scale) from DebateParticipantCharcs model group by model.debateParticipant.party.partyId,model.debateParticipant.debate.debateId order by model.debateParticipant.debate.debateId , sum(model.scale) desc");
		
		return query.list();
	}
	
	
	public List<Object[]> getPartyWiseEachDebateCharsCounttsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds)
	{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName , " +
				" model.debateParticipant.debate.debateId ,sum(model.scale) " +
				"  	from DebateParticipantCharcs model " +
				" where model.debateParticipant.debate.isDeleted = 'N'  " );

		if(fromDate !=null && toDate !=null){
			sb.append(" and date( model.debateParticipant.debate.startTime) >= :fromDate and date( model.debateParticipant.debate.startTime) <= :toDate ");
		}
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and model.debateParticipant.candidate.candidateId in (:candidateIds) and model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		sb.append(" group by model.debateParticipant.party.partyId,model.debateParticipant.debate.debateId  order by model.debateParticipant.debate.debateId , sum(model.scale) desc");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
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
		
		return query.list();
	}
	
	
	public List<Object[]> getPartyCandidateDetailsTopicWise()
	{
		Query query = getSession().createQuery("select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId ," +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) from DebateParticipantCharcs model ,DebateSubject model2 where model.debateParticipant.debate.debateId = model2.debate.debateId" +
				" group by model.debateParticipant.party.partyId,model.debateParticipant.candidate.candidateId,model2.debateSubjectId order by model2.debateSubjectId ");
		
		return query.list();
	}
	
	public List<Object[]> getPartyCandidateDetailsTopicWiseForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId , " +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) from DebateParticipantCharcs model ,DebateSubject model2 where ");	

		
		
			sb.append(" model.debateParticipant.debate.debateId = model2.debate.debateId and ");
		
			
		sb.append(" date(model2.debate.startTime) >= :fromDate and date(model2.debate.startTime) <= :toDate " +
				" and model2.debate.isDeleted = 'N' ");
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and model2.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and model.debateParticipant.candidate.candidateId in (:candidateIds) and model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		sb.append("  group by model.debateParticipant.party.partyId,model.debateParticipant.candidate.candidateId,model2.debateSubjectId order by model2.debateSubjectId  ");
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
		
		return query.list();
		
	}
	public List<Object[]> getTopicWiseStrongOrWeakCandidates(String sortOrder)
	{
		Query query = getSession().createQuery("select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId ," +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) ,model.debateParticipant.debate.channel.channelName from DebateParticipantCharcs model ,DebateSubject model2 where model.debateParticipant.debate.debateId = model2.debate.debateId" +
				" group by model.debateParticipant.party.partyId,model.debateParticipant.debate.debateId order by model.debateParticipant.debate.debateId ,sum(model.scale) " + sortOrder);
		
		return query.list();
	}
	
	public List<Object[]> getTopicWiseStrongOrWeakCandidatsForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortOrder)
	{
	
		StringBuilder sb = new StringBuilder();
		
		sb.append("select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId , " +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) ,model.debateParticipant.debate.channel.channelName  from DebateParticipantCharcs model ,DebateSubject model2 " +
				"  where model.debateParticipant.debate.debateId = model2.debate.debateId " +
				" and model2.debate.isDeleted = 'N' ");	

		if(fromDate !=null && toDate !=null){
			sb.append(" and date(model2.debate.startTime) >= :fromDate and date(model2.debate.startTime) <= :toDate " );
		}
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and model2.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and model.debateParticipant.candidate.candidateId in (:candidateIds) and model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		sb.append(" group by model.debateParticipant.party.partyId,model.debateParticipant.debate.debateId order by model.debateParticipant.debate.debateId ,sum(model.scale) " + sortOrder+" ");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
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
		
		
		return query.list();
	}
	
	public List<Object[]> getTopicWiseStrongOrWeakCandidatesForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId ," +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) ,model.debateParticipant.debate.channel.channelName  from DebateParticipantCharcs model ,DebateSubject model2 where  ");	

		if(channelIds != null || candidateIds != null || partyIds != null)
		{
			sb.append(" model.debateParticipant.debate.debateId = model2.debate.debateId and  ");
		}
		
		sb.append(" date(model2.debate.startTime) >= :fromDate and date(model2.debate.startTime) <= :toDate " +
				" and model2.debate.isDeleted = 'N' ");
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and model2.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and model.debateParticipant.candidate.candidateId in (:candidateIds) and model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  model.debateParticipant.party.partyId in (:partyIds)  ");
		}
		sb.append(" order by model2.debate.startTime desc ");
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
		
		return query.list();
	}
}
