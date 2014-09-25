package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
import com.itgrids.partyanalyst.model.DebateParticipant;


public class DebateParticipantDAO extends GenericDaoHibernate<DebateParticipant, Long> implements IDebateParticipantDAO{

	public DebateParticipantDAO() {
		super(DebateParticipant.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebatePaticepantsAndSummeryDetails(Long debateId)
	{
		Query query = getSession().createQuery("select model.candidate.lastname," +
				" model.party.shortName,model.summary ,model.candidate.candidateId , model.party.partyId from DebateParticipant model " +
				" where model.debate.debateId = :debateId");
		query.setParameter("debateId", debateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyWiseDebateAnalysis(Long partyId)
	{
		Query query = getSession().createQuery("select model.party.partyId ,model.party.shortName , model.candidate.candidateId , " +
				" model.candidate.lastname , model2.debateRoles.debateRolesId ,model2.debateRoles.name ,sum(model1.scale) from  " +
				" DebateParticipant model , DebateParticipantRole model2 ,DebateParticipantCharcs model1 " +
				" where model.debateParticipantId = model1.debateParticipant.debateParticipantId and " +
				" model1.debateParticipant.debateParticipantId = model2.debateParticipant.debateParticipantId and  " +
				" model.party.partyId = :partyId group by model2.debateRoles.debateRolesId");
		query.setParameter("partyId", partyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateWiseDebateAnalysis(Long partyId,Long candidateId)
	{
		Query query = getSession().createQuery("select model.party.partyId ,model.party.shortName , model.candidate.candidateId , " +
				" model.candidate.lastname , model2.debateRoles.debateRolesId ,model2.debateRoles.name ,sum(model1.scale) from  " +
				" DebateParticipant model , DebateParticipantRole model2 ,DebateParticipantCharcs model1 " +
				" where model.debateParticipantId = model1.debateParticipant.debateParticipantId and " +
				" model1.debateParticipant.debateParticipantId = model2.debateParticipant.debateParticipantId and  " +
				" model.party.partyId = :partyId and model.candidate.candidateId = :candidateId group by model2.debateRoles.debateRolesId");
		query.setParameter("partyId", partyId);
		query.setParameter("candidateId", candidateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBasicDebateAnalysis(Long partyId,Long candidateId,String type,StringBuffer queryString)
	{
		Query query = getSession().createQuery(queryString.toString());
		
		if(type.equalsIgnoreCase("party"))
		{
			query.setParameter("partyId", partyId);
		}
		else if(type.equalsIgnoreCase("party"))
		{
			query.setParameter("partyId", partyId);
			query.setParameter("candidateId", candidateId);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateTotalScaleForEachParty(Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery("select distinct model1.party.partyId,sum(model2.scale) from " +
				" Debate model , DebateParticipant model1 ,DebateParticipantCharcs model2 " +
				" where model.debateId = model1.debate.debateId  and model1.debateParticipantId = model2.debateParticipant.debateParticipantId " +
				" and model.isDeleted = 'N' and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate " +
				" group by model1.party.partyId");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalDabtesCountsForEachParty(Date fromDate , Date toDate)
	{
		Query query = getSession().createQuery("select distinct model1.party.partyId,count(model.debateId) ,model1.party.shortName from " +
				" Debate model , DebateParticipant model1 " +
				" where  model.debateId = model1.debate.debateId and model.isDeleted = 'N' " +
				" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate " +
				" group by model1.party.partyId");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateTotalScaleForEachCandidate(Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery("select distinct model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname,sum(model2.scale) from " +
				" Debate model , DebateParticipant model1 ,DebateParticipantCharcs model2 " +
				" where model.debateId = model1.debate.debateId  and model1.debateParticipantId = model2.debateParticipant.debateParticipantId " +
				" and model.isDeleted = 'N' and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate " +
				" group by model1.party.partyId,model1.candidate.candidateId");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalDabtesCountsForEachCandidate(Date fromDate , Date toDate)
	{
		Query query = getSession().createQuery("select distinct model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname,count(model.debateId)  from " +
				" Debate model , DebateParticipant model1 " +
				" where  model.debateId = model1.debate.debateId and model.isDeleted = 'N' " +
				" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate " +
				" group by model1.party.partyId,model1.candidate.candidateId");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCanidatesListForDebateForSelectedDates(Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery("select distinct  model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname from " +
				" Debate model , DebateParticipant model1 " +
				" where  model.debateId = model1.debate.debateId and model.isDeleted = 'N' " +
				" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate " +
				" ");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}


	public List<Object[]> getPartiesAndCanidatesIds()
	{  
		Query query = getSession().createQuery(" select DP.partyId ,DP.party.shortName, DP.candidateId ,DP.candidate.lastname, count(*) " +
				                                       "from DebateParticipant DP " +
				                                        "where DP.debate.debateId = DP.debateId " +
				                                        "group by DP.partyId , DP.candidateId " +
				                                        "order by DP.partyId");
		return query.list();
	}
	
	public List<Object[]> getPartiesAndCanidatesIdForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("select DP.partyId ,DP.party.shortName, DP.candidateId ,DP.candidate.lastname, count(*) " +
				                                       "from DebateParticipant DP  ");	
		sb.append(" where DP.debate.debateId = DP.debateId and DP.debate.isDeleted = 'N' and  ");

		sb.append(" date(DP.debate.startTime) >= :fromDate and date(DP.debate.startTime) <= :toDate ");
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and DP.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and DP.candidate.candidateId in (:candidateIds) and DP.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  DP.party.partyId in (:partyIds)  ");
		}
		sb.append(" group by DP.partyId , DP.candidateId order by DP.partyId ");
		
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
	
	public List<Object[]> getDebateCandidateCharacteristicsDetails(){
		
		Query query = getSession().createQuery("select DP.party.partyId ,DP.party.shortName, DS.debateSubjectId ,DS.subject," +
				" DP.candidate.candidateId,DP.candidate.lastname," +
				" DPC.characteristics.characteristicsId ,DPC.characteristics.name, sum(DPC.scale) " +
				" from DebateSubject DS , DebateParticipant DP , DebateParticipantCharcs DPC where " +
				" DS.debate.debateId = DP.debate.debateId and DP.debateParticipantId = DPC.debateParticipant.debateParticipantId " +
				" and DPC.characteristics.isDeleted = 'N' and DS.debate.isDeleted = 'N'" +
				" group by DS.debateSubjectId,DP.party.partyId , DPC.characteristics.characteristicsId");
		return query.list();
	}
	
public List<Object[]> getDebateCandidateCharacteristicsDetailForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("select DP.party.partyId ,DP.party.shortName, DS.debateSubjectId ,DS.subject, " +
				" DP.candidate.candidateId,DP.candidate.lastname, " +
				" DPC.characteristics.characteristicsId ,DPC.characteristics.name, sum(DPC.scale)  ");	
		sb.append("  from DebateSubject DS , DebateParticipant DP , DebateParticipantCharcs DPC where ");
		
		sb.append(" DS.debate.debateId = DP.debate.debateId and DP.debateParticipantId = DPC.debateParticipant.debateParticipantId ");
		sb.append(" and DPC.characteristics.isDeleted = 'N' and DS.debate.isDeleted = 'N' and ");
		
		sb.append(" date(DS.debate.startTime) >= :fromDate and date(DS.debate.startTime) <= :toDate ");
		
		if(channelIds != null && channelIds.size()>0)
		{
			sb.append(" and DS.debate.channel.channelId in (:channelIds) ");
		}
		
		if(candidateIds != null  && candidateIds.size()>0)
		{
			sb.append(" and DP.candidate.candidateId in (:candidateIds) and DP.party.partyId in (:partyIds)  ");
		}
		else if(partyIds != null  && partyIds.size()>0)
		{
			sb.append(" and  DP.party.partyId in (:partyIds)  ");
		}
		sb.append(" group by DS.debateSubjectId,DP.party.partyId , DPC.characteristics.characteristicsId");
		
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

	public List<Object[]> getDistinctDebateParties()
	{
		Query query = getSession().createQuery("select distinct model.party.partyId,model.party.shortName from DebateParticipant model");
		
		return query.list();
	}
	
	public List<Object[]> getDistinctDebatePartiesForSelection(Date fromDate,Date toDate, List<Long> partyIds)
	{
		StringBuilder br = new StringBuilder();
		br.append(" select distinct model.party.partyId,model.party.shortName from DebateParticipant model where model.debate.isDeleted = 'N' and " +
				" date(model.debate.startTime) >= :fromDate and date(model.debate.endTime) <= :toDate  ");
		if(partyIds != null && partyIds.size()>0)
		{
			br.append(" and model.party.partyId in (:partyIds)");
		}
		Query query = getSession().createQuery(br.toString());
		
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		if(partyIds != null && partyIds.size()>0)
		{
			query.setParameterList("partyIds", partyIds);
		}
		return query.list();
	}
}
