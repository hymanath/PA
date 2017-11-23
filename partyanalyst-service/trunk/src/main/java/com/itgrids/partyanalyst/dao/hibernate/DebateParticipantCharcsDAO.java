package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.model.DebateParticipantCharcs;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateParticipantCharcsDAO extends GenericDaoHibernate<DebateParticipantCharcs, Long> implements IDebateParticipantCharcsDAO{

	public DebateParticipantCharcsDAO() {
		super(DebateParticipantCharcs.class);
		// TODO Auto-generated constructor stub
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateCharcsDetails(Long debateId)
	{
		/*return getHibernateTemplate().find("select model.debateParticipant.candidate.candidateId ,model.debateParticipant.candidate.lastname ," +
				" model.debateParticipant.party.partyId,model.debateParticipant.party.shortName ," +
				" model.characteristics.name,model.scale,model.characteristics.characteristicsId from  DebateParticipantCharcs model where " +
				" model.debateParticipant.debate.debateId = ? ",debateId);*/
		StringBuilder sb =new StringBuilder();
		 sb.append("select model.debateParticipant.candidate.candidateId ,model.debateParticipant.candidate.lastname ," +
				" model.debateParticipant.party.partyId,model.debateParticipant.party.shortName ," +
				" model.characteristics.name,model.scale,model.characteristics.characteristicsId from  DebateParticipantCharcs model " );
		 /*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }*/
		 sb.append(" where " );
		 if(debateId != null && debateId.longValue()>0)
		  sb.append(" model.debateParticipant.debate.debateId =:debateId ");
		 /*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipant.debateParticipantId  and model3.isDeleted = 'N' ");
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebatePerformanceCount(){
		Query query = getSession().createQuery("select DP.candidateId , sum(DPC.scale) "+
	          "from DebateParticipantCharcs DPC, DebateParticipant DP, Debate D "+
			  "where D.debateId = DP.debateId and DP.debateParticipantId = DPC.debateParticipantId "+
	          "group by DP.candidateId");
		return query.list();
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebatePerformanceCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("select DP.candidateId , sum(DPC.scale)   from DebateParticipantCharcs DPC, DebateParticipant DP, Debate D  ");
		sb.append(" where D.debateId = DP.debateId and DP.debateParticipantId = DPC.debateParticipantId and   " +
				" D.isDeleted = 'N'  ");
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
		if(fromDate !=null && toDate !=null){
			sb.append(" and date(D.startTime) >= :fromDate and date(D.startTime) <= :toDate ");
		}		
		
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

		public List<Object[]> getDebatePerformanceCountCharForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId)
		{
			StringBuilder sb = new StringBuilder();
			
			sb.append(" select DP.candidateId ,DPC.characteristicsId, C.name, sum(DPC.scale) 	from DebateParticipantCharcs DPC, DebateParticipant DP ,  Debate D , Characteristics C  " );
			sb.append(" where D.debateId = DP.debateId and  DP.debateParticipantId = DPC.debateParticipantId " +
				"   and DPC.characteristicsId = C.characteristicsId " +
				" and D.isDeleted = 'N' ");
			if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
				sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
			}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
				sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
			}
			if(fromDate !=null && toDate !=null){
				sb.append(" and date(D.startTime) >= :fromDate and date(D.startTime) <= :toDate ");
			}
						
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
	
	public List<Object[]> getPartyWiseTotalDebatesAndScales()
	{
		Query query = getSession().createQuery("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName " +
				",count(distinct model.debateParticipant.debate.debateId) ,sum(model.scale) from DebateParticipantCharcs model" +
				" group by model.debateParticipant.party.partyId");
		
		return query.list();
	}
	
	public List<Object[]> getPartyWiseTotalDebatesAndScalForSelection(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIds,  List<Long> candidateIds,Long stateId)
	{

		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName , count(distinct model.debateParticipant.debate.debateId) ,sum(model.scale)" +
				"  	from DebateParticipantCharcs model ");
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" , DebateParticipant model3 ");
		}
		sb.append("  where model.debateParticipant.debate.isDeleted = 'N' " );
		
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId ");
		}
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
		
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
	
	public List<Object[]> getPartyWiseDebatePartiCharsCountsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds,Long stateId)
	{

		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName , " +
				" model.characteristics.characteristicsId ,sum(model.scale),model.characteristics.name  " +
				"  	from DebateParticipantCharcs model  ");
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" , DebateParticipant model3 ");
		}
		
		sb.append(" where model.debateParticipant.debate.isDeleted = 'N' " );
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId  ");
		}
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
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
	
	
	public List<Object[]> getPartyWiseEachDebateCharsCounttsForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIds,List<Long>  candidateIds,Long stateId)
	{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName , " +
				" model.debateParticipant.debate.debateId ,sum(model.scale) " +
				"  	from DebateParticipantCharcs model " );
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" , DebateParticipant model3 ");
		}
		sb.append(" where model.debateParticipant.debate.isDeleted = 'N'  " );
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId  ");
		}
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
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
	
	public List<Object[]> getPartyCandidateDetailsTopicWiseForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId , " +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) from DebateParticipantCharcs model ,DebateSubject model2  ");	

		if(stateId != null && stateId.longValue() > 0){
			sb.append(" , DebateParticipant model3 ");
		}
		
		sb.append(" where model.debateParticipant.debate.debateId = model2.debate.debateId and model2.debate.isDeleted = 'N'  ");
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId ");
		}
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
		
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
		sb.append("  group by model.debateParticipant.party.partyId,model.debateParticipant.candidate.candidateId,model2.debateSubjectId order by model2.debateSubjectId  ");
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
	public List<Object[]> getTopicWiseStrongOrWeakCandidates(String sortOrder)
	{
		Query query = getSession().createQuery("select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId ," +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) ,model.debateParticipant.debate.channel.channelName from DebateParticipantCharcs model ,DebateSubject model2 where model.debateParticipant.debate.debateId = model2.debate.debateId" +
				" group by model.debateParticipant.party.partyId,model.debateParticipant.debate.debateId order by model.debateParticipant.debate.debateId ,sum(model.scale) " + sortOrder);
		
		return query.list();
	}
	
	public List<Object[]> getTopicWiseStrongOrWeakCandidatsForSelection(Date fromDate,Date toDate, List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortOrder,Long stateId)
	{
	
		StringBuilder sb = new StringBuilder();
		
		sb.append("select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId , " +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) ,model.debateParticipant.debate.channel.channelName  from DebateParticipantCharcs model ,DebateSubject model2 ");
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" , DebateParticipant model3 ");
		}
		sb.append("  where model.debateParticipant.debate.debateId = model2.debate.debateId " +
				" and model2.debate.isDeleted = 'N' ");	
		if(stateId != null && stateId.longValue() > 0){
			sb.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId  ");
		}
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and model3.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}

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
		sb.append(" group by model.debateParticipant.party.partyId,model.debateParticipant.debate.debateId order by sum(model.scale) " + sortOrder+" ");
		
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
	
	public List<Object[]> getPartyWiseScalesOfEachCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){
		
		StringBuilder str = new StringBuilder();		
		str.append(" select model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
				"model.characteristics.characteristicsId,model.characteristics.name," +
				" sum(model.scale)" +
				" from DebateParticipantCharcs model " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" , Debate model3 ");
		    }
		str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
				" and model.characteristics.isDeleted ='N' " +
				" and model.debateParticipant.party.isNewsPortal = 'Y' " +
				" and model.debateParticipant.candidate.isDebateCandidate ='Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" and model.debateParticipant.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		if(debateLocationIdList != null  && debateLocationIdList.size() >0  && debateLocationIdList.size() != 3L && !debateLocationIdList.contains(2L)){
		      str.append(" and model3.address.state.stateId in (:debateLocationIdList) " );
		}
		else if(debateLocationIdList != null && debateLocationIdList.size() > 0 && debateLocationIdList.size() == 1L && debateLocationIdList.contains(2L)){
		    	  str.append(" and model3.address.state.stateId is null ");
		  }else if(debateLocationIdList != null  && debateLocationIdList.size() >0  && debateLocationIdList.size() == 3L && debateLocationIdList.contains(0L)){
				   str.append(" and model3.address.state.stateId  in (:debateLocationIdList) " );
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
				
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			 str.append(" and model.debateParticipant.candidate.state.stateId in (:debateParticipantLocationIdList) " );
		}
		str.append(" group by model.debateParticipant.party.partyId,model.characteristics.characteristicsId" +
				" order by model.debateParticipant.party.newsOrderNo,model.characteristics.characteristicsId ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0  && debateLocationIdList.size() != 3L && !debateLocationIdList.contains(2L)){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}else if(debateLocationIdList != null  && debateLocationIdList.size() >0  && debateLocationIdList.size() == 3L && debateLocationIdList.contains(0L)){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list(); 
		
	}
	
	public List<Object[]> getPartywiseCandidateScaling(Date startDate,Date endDate,String searchType,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList){		
		StringBuilder str = new StringBuilder();		
		str.append(" select model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
				" model.debateParticipant.candidate.candidateId,model.debateParticipant.candidate.lastname,sum(model.scale) " +
				" from DebateParticipantCharcs model " );
		str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
				" and model.characteristics.isDeleted ='N' " +
				" and model.debateParticipant.party.isNewsPortal = 'Y'" +
				" and model.debateParticipant.candidate.isDebateCandidate ='Y' " );
				//" and  model.debateParticipant.candidate.isDebateCandidate ='Y' " );
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0){
		      str.append(" and model.debateParticipant.candidate.state.stateId in (:debateParticipantLocationIdList) ");
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}				
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model.debateParticipant.debate.address.state.stateId in (:debateLocationIdList) " );
		}
		str.append(" group by model.debateParticipant.party.partyId, model.debateParticipant.candidate.candidateId" +
				" order by model.debateParticipant.party.newsOrderNo ");
		/*
		if(searchType !=null && searchType.trim().equalsIgnoreCase("top")){
			str.append(" order by sum(model.scale) desc ");
		}else if(searchType !=null && searchType.trim().equalsIgnoreCase("poor")){
			str.append(" order by sum(model.scale) ");
		}
		*/
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0 ){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
        if(debateLocationIdList != null && debateLocationIdList.size()>0){
        	query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		return query.list(); 
		
	}
	
	public List<Object[]> getPartywiseCandidateCharectersScaling(Date startDate,Date endDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList){
		
		StringBuilder str = new StringBuilder();		
		str.append(" select model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
				" model.debateParticipant.candidate.candidateId,model.debateParticipant.candidate.lastname," +
				" model.characteristics.characteristicsId," +
				"model.characteristics.name," +
				" sum(model.scale) " +
				" from DebateParticipantCharcs model " );
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0){
		      str.append(" , DebateParticipant model3 ");
		    }
		str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
				" and model.characteristics.isDeleted ='N' " +
				" and model.debateParticipant.party.isNewsPortal = 'Y' " +
				" and  model.debateParticipant.candidate.isDebateCandidate ='Y' " );
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0){
		      str.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId  ");
		    }
		   if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0 ){
		      str.append(" and model3.candidate.state.stateId in (:debateParticipantLocationIdList)");
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
			
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			 str.append(" and model.debateParticipant.debate.address.state.stateId in (:debateLocationIdList)");
		}
		str.append(" group by model.debateParticipant.party.partyId, model.debateParticipant.candidate.candidateId,model.characteristics.characteristicsId " +
				" order by model.debateParticipant.party.newsOrderNo,model.debateParticipant.candidate.candidateId,model.characteristics.characteristicsId ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0 ){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
        if(debateLocationIdList != null && debateLocationIdList.size()>0){
        	query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		return query.list(); 
		
	}
	
	public List<Object[]> getChannelAndPartyWiseCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocIdList){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.debateParticipant.debate.channel.channelId,model.debateParticipant.debate.channel.channelName," +
				" model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
				" sum(model.scale) " +
				" from DebateParticipantCharcs model " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" , Debate model3 ");
		    }
		str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
				" and model.characteristics.isDeleted ='N'" +
				" and model.debateParticipant.party.isNewsPortal = 'Y'" );
		  if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" and model.debateParticipant.debateId = model3.debateId  and model3.isDeleted = 'N' ");
		    }
		  if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
		      str.append(" and model3.address.state.stateId in (:debateLocationIdList) " );
		    }
			if(state !=null && state.trim().equalsIgnoreCase("ap")){
				str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
			}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
				str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
			}	
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
		}
		if(debateParticipantLocIdList != null && debateParticipantLocIdList.size()>0l){
			str.append(" and model.debateParticipant.candidate.state.stateId in (:debateParticipantLocIdList) " );
		}
		str.append(" group by model.debateParticipant.debate.channel.channelId, model.debateParticipant.party.partyId " +
				" order by model.debateParticipant.debate.channel.channelId,model.debateParticipant.party.newsOrderNo ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}	
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			 query.setParameterList("debateLocationIdList", debateLocationIdList);
		 }
       if(debateParticipantLocIdList != null && debateParticipantLocIdList.size()>0l){
    	   query.setParameterList("debateParticipantLocIdList", debateParticipantLocIdList);
		}
		return query.list(); 
	}
	
	public List<Object[]> getRoleBasedPerformanceCohort(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model1.debateParticipant.party.partyId,model1.debateParticipant.party.shortName," +
				"model1.debateRoles.debateRolesId,model1.debateRoles.aliasName,sum(model.scale),count(distinct model1.debateParticipant.debate.debateId), " +
				" count(distinct model1.debateParticipant.candidate.candidateId) " +
				"  from DebateParticipantCharcs model ,DebateParticipantRole model1 " );
		   if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			    str.append(" , Debate model3 ");
		      }
		str.append(" where model.debateParticipant.debateParticipantId = model1.debateParticipant.debateParticipantId" +
				" and model.characteristics.isDeleted = 'N' " +
				" and model1.debateRoles.isDeleted ='N' " +
				" and model.debateParticipant.party.isNewsPortal = 'Y' " +
				" and model1.debateParticipant.debate.isDeleted = 'N' " );
		   if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		 	  str.append(" and model.debateParticipant.debateId = model3.debateId ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId in(:debateLocationIdList) " );
		    }
			if(state !=null && state.trim().equalsIgnoreCase("ap")){
				str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
			}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
				str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
			}
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model.debateParticipant.candidate.state.stateId in(:debateParticipantLocationIdList)");
		}
		str.append(" group by model1.debateParticipant.party.partyId, model1.debateRoles.debateRolesId " +
				" order by model.debateParticipant.party.newsOrderNo ");

		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}	
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
       if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
    	   query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list(); 		
	}
	
	public List<Object[]> getScaleOfCandidate(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList){
		
		StringBuilder str = new StringBuilder();
		
		str.append( "  select C.candidate_id as candidateId,C.lastname as candidateName,P.party_id as partyId,P.short_name as partyName," +
				"  sum(DPC.scale) as sum,count(distinct D.debate_id) as count " +
				" from debate_participant DP,debate_participant_charcs DPC,debate D,candidate C,party P,characteristics CH" );
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append( " ,user_address UA " );
		}
		str.append( " where DP.debate_participant_id = DPC.debate_participant_id " +
				" and DP.debate_id = D.debate_id  and DP.candidate_id = C.candidate_id " +
				" and P.party_id = DP.party_id  and DPC.characteristics_id = CH.characteristics_id " );
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append( "  and D.address_id = UA.user_address_id " );
		 }
		str.append( " and D.is_deleted ='N'  and C.is_debate_candidate = 'Y' " +
				"  and CH.is_deleted ='N' " );
		   if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
		      str.append(" and C.state_id in(:participantLocationIdList)");
		    }
				if(startDate !=null && endDate !=null){
					str.append(" and date(D.start_time) >= :startDate and date(D.end_time) <= :endDate " );
				}
				
				if(state !=null && state.trim().equalsIgnoreCase("ap")){
					str.append(" and P.party_id not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
				}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
					str.append(" and P.party_id not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
				}
				if(debateLocationIdList != null && debateLocationIdList.size()>0){
					str.append(" and UA.state_id in(:debateLocationIdList) ");
				}
				str.append(" group by C.candidate_id,P.party_id " +
						"  order by sum(DPC.scale) desc  " );
				
				Query query = getSession().createSQLQuery(str.toString())
						.addScalar("candidateId",Hibernate.LONG)
						.addScalar("candidateName",Hibernate.STRING)
						.addScalar("partyId",Hibernate.LONG)
						.addScalar("partyName",Hibernate.STRING)
						.addScalar("sum",Hibernate.DOUBLE)
						.addScalar("count",Hibernate.LONG);	
				
				if(startDate !=null && endDate !=null){
					query.setParameter("startDate", startDate);
					query.setParameter("endDate", endDate);
				}
				if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
					query.setParameterList("participantLocationIdList", participantLocationIdList);
				}
				if(debateLocationIdList != null && debateLocationIdList.size()>0){
					query.setParameterList("debateLocationIdList", debateLocationIdList);
				}
				return query.list();
		
	}
	
	public List<Object[]> getScaleOfCandidateNew(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select distinct model.debateParticipant.candidate.candidateId,model.debateParticipant.candidate.lastname" +
				",model.debateParticipant.party.partyId,model.debateParticipant.party.shortName,sum(model.scale)," +
				" count(distinct model.debateParticipant.debate.debateId) " +
				" from DebateParticipantCharcs model ,DebateParticipantRole model1 " );
		if(participantLocationIdList != null && participantLocationIdList.size() > 0){
		      str.append(" , DebateParticipant model3 ");
		    }
		str.append(" where model.debateParticipant.debateParticipantId = model1.debateParticipant.debateParticipantId " +
				" and model.characteristics.isDeleted = 'N' " +
				" and model1.debateRoles.isDeleted ='N' " +
				" and model.debateParticipant.party.isNewsPortal = 'Y'" +
				" and model.debateParticipant.candidate.isDebateCandidate = 'Y' " +
				" and model.debateParticipant.debate.isDeleted = 'N' " );
		 if(participantLocationIdList != null && participantLocationIdList.size() > 0){
		      str.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId  ");
		    }
		   if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
		      str.append(" and model3.candidate.state.stateId in(:participantLocationIdList)");
		    }
			if(state !=null && state.trim().equalsIgnoreCase("ap")){
				str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
			}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
				str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
			}
		
			if(startDate !=null && endDate !=null){
				str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
			}
			
			if(roles !=null && roles.size()>0){
				str.append(" and model1.debateRoles.debateRolesId in (:roles) ");
			}
			if(debateLocationIdList != null && debateLocationIdList.size()>0){
				str.append(" and model.debateParticipant.debate.address.state.stateId in(:debateLocationIdList)");
			}
			str.append(" group by model.debateParticipant.candidate.candidateId, model.debateParticipant.party.partyId " +
					" order by model.debateParticipant.party.newsOrderNo ");
		
			Query query = getSession().createQuery(str.toString());	
			
			if(startDate !=null && endDate !=null){
				query.setParameter("startDate", startDate);
				query.setParameter("endDate", endDate);
			}	
			if(roles !=null && roles.size()>0){
				query.setParameterList("roles", roles);
			}
			if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
				query.setParameterList("participantLocationIdList", participantLocationIdList);
			}
			if(debateLocationIdList != null && debateLocationIdList.size()>0){
				query.setParameterList("debateLocationIdList", debateLocationIdList);
			}
			return query.list(); 		

	}
public List<Object[]> getPartyWiseScalesOfOthersEachCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){
		
		StringBuilder str = new StringBuilder();		
		str.append(" select model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
				"model.characteristics.characteristicsId,model.characteristics.name," +
				" sum(model.scale)" +
				" from DebateParticipantCharcs model " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" , Debate model3 ");
		    }
		str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
				" and model.characteristics.isDeleted ='N' " +
				" and model.debateParticipant.party.isNewsPortal = 'Y'" +
				" and model.debateParticipant.candidate.isDebateCandidate ='Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" and model.debateParticipant.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
		      str.append(" and model3.address.state.stateId is null " );
		}
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
				
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			 str.append(" and model.debateParticipant.candidate.state.stateId in(:debateParticipantLocationIdList)");
		}
		str.append(" group by model.debateParticipant.party.partyId,model.characteristics.characteristicsId" +
				" order by model.debateParticipant.party.newsOrderNo,model.characteristics.characteristicsId ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
       if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
    	   query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list(); 
		
	}
public List<Object[]> getChannelAndPartyWiseOthersCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocIdList){
	
	StringBuilder str = new StringBuilder();
	
	str.append(" select model.debateParticipant.debate.channel.channelId,model.debateParticipant.debate.channel.channelName," +
			" model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
			" sum(model.scale) " +
			" from DebateParticipantCharcs model " );
	if(debateLocationIdList != null && debateLocationIdList.size() > 0){
	      str.append(" , Debate model3 ");
	    }
	str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
			" and model.characteristics.isDeleted ='N'" +
			" and model.debateParticipant.party.isNewsPortal = 'Y'" );
	  if(debateLocationIdList != null && debateLocationIdList.size() > 0){
	      str.append(" and model.debateParticipant.debateId = model3.debateId  and model3.isDeleted = 'N' ");
	    }
	  if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
	      str.append(" and model3.address.state.stateId is null  " );
	    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}	
	
	if(startDate !=null && endDate !=null){
		str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
	}
	if(debateParticipantLocIdList != null && debateParticipantLocIdList.size()>0l){
		 str.append(" and model.debateParticipant.candidate.state.stateId in(:debateParticipantLocIdList) ");
	}
	str.append(" group by model.debateParticipant.debate.channel.channelId, model.debateParticipant.party.partyId " +
			" order by model.debateParticipant.debate.channel.channelId,model.debateParticipant.party.newsOrderNo ");
	
	Query query = getSession().createQuery(str.toString());	
	
	if(startDate !=null && endDate !=null){
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
	}	
  if(debateParticipantLocIdList != null && debateParticipantLocIdList.size()>0l){
	  query.setParameterList("debateParticipantLocIdList", debateParticipantLocIdList);
	}
	return query.list(); 
}
public List<Object[]> getRoleBasedOthersPerformanceCohort(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){
	
	StringBuilder str = new StringBuilder();
	
	str.append(" select model1.debateParticipant.party.partyId,model1.debateParticipant.party.shortName," +
			"model1.debateRoles.debateRolesId,model1.debateRoles.aliasName,sum(model.scale),count(distinct model1.debateParticipant.debate.debateId)," +
			" count(distinct model1.debateParticipant.candidate.candidateId) " +
			"  from DebateParticipantCharcs model ,DebateParticipantRole model1 " );
	   if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		    str.append(" , Debate model3 ");
	      }
	str.append(" where model.debateParticipant.debateParticipantId = model1.debateParticipant.debateParticipantId" +
			" and model.characteristics.isDeleted = 'N' " +
			" and model1.debateRoles.isDeleted ='N' " +
			" and model.debateParticipant.party.isNewsPortal = 'Y' " +
			" and model1.debateParticipant.debate.isDeleted = 'N' " );
	   if(debateLocationIdList != null && debateLocationIdList.size() > 0){
	 	  str.append(" and model.debateParticipant.debateId = model3.debateId ");
	    }
	   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
		   str.append(" and model3.address.state.stateId is null " );
	    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
	
	if(startDate !=null && endDate !=null){
		str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
	}
	if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
		str.append(" and model.debateParticipant.candidate.state.stateId in(:debateParticipantLocationIdList) ");
	}
	str.append(" group by model1.debateParticipant.party.partyId, model1.debateRoles.debateRolesId " +
			" order by model.debateParticipant.party.newsOrderNo ");

	Query query = getSession().createQuery(str.toString());	
	
	if(startDate !=null && endDate !=null){
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
	}	
   if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
	   query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
	}
	return query.list(); 		
}
public List<Object[]> getScaleOfCandidateOthersNew(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList){
	
	StringBuilder str = new StringBuilder();
	
	str.append(" select model.debateParticipant.candidate.candidateId,model.debateParticipant.candidate.lastname" +
			",model.debateParticipant.party.partyId,model.debateParticipant.party.shortName,sum(model.scale)," +
			" count(distinct model.debateParticipant.debate.debateId) " +
			" from DebateParticipantCharcs model ,DebateParticipantRole model1 " );
	if(participantLocationIdList != null && participantLocationIdList.size() > 0){
	      str.append(" , DebateParticipant model3 ");
	    }
	str.append(" where model.debateParticipant.debateParticipantId = model1.debateParticipant.debateParticipantId " +
			" and model.characteristics.isDeleted = 'N' " +
			" and model1.debateRoles.isDeleted ='N' " +
			" and model.debateParticipant.party.isNewsPortal = 'Y'" +
			" and model.debateParticipant.debate.isDeleted = 'N' " );
	 if(participantLocationIdList != null && participantLocationIdList.size() > 0){
	      str.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId  ");
	    }
	   if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
	      str.append(" and model3.candidate.state.stateId in(:participantLocationIdList)");
	    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
	
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
		}
		
		if(roles !=null && roles.size()>0){
			str.append(" and model1.debateRoles.debateRolesId in (:roles) ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model.debateParticipant.debate.address.state.stateId is null ");
		}
		str.append(" group by model.debateParticipant.candidate.candidateId, model.debateParticipant.party.partyId " +
				" order by model.debateParticipant.party.newsOrderNo ");
	
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}	
		if(roles !=null && roles.size()>0){
			query.setParameterList("roles", roles);
		}
		if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
			query.setParameterList("participantLocationIdList", participantLocationIdList);
		}
		/*if(debateLocationIdList != null && debateLocationIdList.size()>0){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}*/
		return query.list(); 		

}
public List<Object[]> getScaleOfOthersCandidate(Date startDate,Date endDate,List<Long> roles,String state,List<Long> participantLocationIdList,List<Long> debateLocationIdList){
	
	StringBuilder str = new StringBuilder();
	
	str.append( "  select C.candidate_id as candidateId,C.lastname as candidateName,P.party_id as partyId,P.short_name as partyName," +
			"  sum(DPC.scale) as sum,count(distinct D.debate_id) as count " +
			" from debate_participant DP,debate_participant_charcs DPC,debate D,candidate C,party P,characteristics CH" );
	if(debateLocationIdList != null && debateLocationIdList.size()>0){
		str.append( " ,user_address UA " );
	}
	str.append( " where DP.debate_participant_id = DPC.debate_participant_id " +
			" and DP.debate_id = D.debate_id  and DP.candidate_id = C.candidate_id " +
			" and P.party_id = DP.party_id  and DPC.characteristics_id = CH.characteristics_id " );
	if(debateLocationIdList != null && debateLocationIdList.size()>0){
		str.append( "  and D.address_id = UA.user_address_id " );
	 }
	str.append( " and D.is_deleted ='N'  and C.is_debate_candidate = 'Y' " +
			"  and CH.is_deleted ='N' " );
	   if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
	      str.append(" and C.state_id in(:participantLocationIdList)");
	    }
			if(startDate !=null && endDate !=null){
				str.append(" and date(D.start_time) >= :startDate and date(D.end_time) <= :endDate " );
			}
			
			if(state !=null && state.trim().equalsIgnoreCase("ap")){
				str.append(" and P.party_id not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
			}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
				str.append(" and P.party_id not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
			}
			if(debateLocationIdList != null && debateLocationIdList.size()>0){
				str.append(" and UA.state_id is null ");
			}
			str.append(" group by C.candidate_id,P.party_id" +
					" " +
					"  order by sum(DPC.scale) desc  " );
			
			Query query = getSession().createSQLQuery(str.toString())
					.addScalar("candidateId",Hibernate.LONG)
					.addScalar("candidateName",Hibernate.STRING)
					.addScalar("partyId",Hibernate.LONG)
					.addScalar("partyName",Hibernate.STRING)
					.addScalar("sum",Hibernate.DOUBLE)
					.addScalar("count",Hibernate.LONG);	
			
			if(startDate !=null && endDate !=null){
				query.setParameter("startDate", startDate);
				query.setParameter("endDate", endDate);
			}
			if(participantLocationIdList != null && participantLocationIdList.size() > 0 ){
				query.setParameterList("participantLocationIdList", participantLocationIdList);
			}
			/*if(debateLocationIdList != null && debateLocationIdList.size()>0){
				query.setParameterList("debateLocationIdList", debateLocationIdList);
			}*/
			return query.list();
	
}
public List<Object[]> getPartywiseOthersCandidateCharectersScaling(Date startDate,Date endDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList){
	
	StringBuilder str = new StringBuilder();		
	str.append(" select model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
			" model.debateParticipant.candidate.candidateId,model.debateParticipant.candidate.lastname," +
			" model.characteristics.characteristicsId," +
			"model.characteristics.name," +
			" sum(model.scale) " +
			" from DebateParticipantCharcs model " );
	if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0){
	      str.append(" , DebateParticipant model3 ");
	    }
	str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
			" and model.characteristics.isDeleted ='N' " +
			" and model.debateParticipant.party.isNewsPortal = 'Y'" +
			" and model.debateParticipant.candidate.isDebateCandidate = 'Y' " );
	if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0){
	      str.append(" and model.debateParticipant.debateParticipantId = model3.debateParticipantId  ");
	    }
	   if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0 ){
	      str.append(" and model3.candidate.state.stateId in (:debateParticipantLocationIdList)");
	    }
	if(state !=null && state.trim().equalsIgnoreCase("ap")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
	}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
	}
		
	if(startDate !=null && endDate !=null){
		str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
	}
	if(debateLocationIdList != null && debateLocationIdList.size()>0){
		 str.append(" and model.debateParticipant.debate.address.state.stateId is null ");
	}
	str.append(" group by model.debateParticipant.party.partyId, model.debateParticipant.candidate.candidateId,model.characteristics.characteristicsId " +
			" order by model.debateParticipant.party.newsOrderNo,model.debateParticipant.candidate.candidateId,model.characteristics.characteristicsId ");
	
	Query query = getSession().createQuery(str.toString());	
	
	if(startDate !=null && endDate !=null){
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
	}
	if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0 ){
		query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
	}
	return query.list(); 
	
}
public List<Object[]> getPartywiseCandidateOthersScaling(Date startDate,Date endDate,String searchType,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList){		
	StringBuilder str = new StringBuilder();		
	str.append(" select model.debateParticipant.party.partyId,model.debateParticipant.party.shortName," +
			" model.debateParticipant.candidate.candidateId,model.debateParticipant.candidate.lastname,sum(model.scale) " +
			" from DebateParticipantCharcs model " );
	
	str.append(" where model.debateParticipant.debate.isDeleted = 'N' " +
			" and model.characteristics.isDeleted ='N' " +
			" and model.debateParticipant.party.isNewsPortal = 'Y' " +
			" and  model.debateParticipant.candidate.isDebateCandidate ='Y' ");
			//" and  model.debateParticipant.candidate.isDebateCandidate ='Y' " );
	if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0){
		 str.append(" and model.debateParticipant.candidate.state.stateId in (:debateParticipantLocationIdList) " );
	    }
	if(state !=null && state.trim().equalsIgnoreCase("ap")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
	}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
	}				
	if(startDate !=null && endDate !=null){
		str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
	}
	if(debateLocationIdList != null && debateLocationIdList.size()>0){
		str.append(" and model.debateParticipant.debate.address.state.stateId is null " );
	}
	str.append(" group by model.debateParticipant.party.partyId, model.debateParticipant.candidate.candidateId" +
			" order by model.debateParticipant.party.newsOrderNo ");
	/*
	if(searchType !=null && searchType.trim().equalsIgnoreCase("top")){
		str.append(" order by sum(model.scale) desc ");
	}else if(searchType !=null && searchType.trim().equalsIgnoreCase("poor")){
		str.append(" order by sum(model.scale) ");
	}
	*/
	Query query = getSession().createQuery(str.toString());	
	
	if(startDate !=null && endDate !=null){
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
	}
	if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0 ){
		query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
	}
	return query.list(); 
	
}
public List<Object[]> getDesignationWiseScalesOfEachCharecter(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){
	
	StringBuilder str = new StringBuilder();		
	str.append(" select DCD.debateCandidateDesignationId,DCD.debateRepresentativeType.debateRepresentativeTypeId," +
			" DCD.debateRepresentativeType.type," +
			" model.characteristics.characteristicsId,model.characteristics.name," +
			" sum(model.scale) " +
			" from DebateParticipantCharcs model,DebateCandidateDesignation DCD " );
	
	str.append(" where model.debateParticipant.candidateId = DCD.candidateId and model.debateParticipant.debate.isDeleted = 'N' " +
			" and model.characteristics.isDeleted ='N' " +
			//" and model.debateParticipant.party.isNewsPortal = 'Y' " +
			" and model.debateParticipant.candidate.isDebateCandidate ='Y' and DCD.isActive ='Y' and DCD.isDeleted = 'N' " );
	
	/*if(state !=null && state.trim().equalsIgnoreCase("ap")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
	}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
	}*/
			
	if(startDate !=null && endDate !=null){
		str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
	}		
	if(debateLocationIdList != null && debateLocationIdList.size()>0l){
		str.append(" and model.debateParticipant.debate.addressId in (:debateLocationIdList) " );
	}
	if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
		str.append(" and model.debateParticipant.candidate.state.stateId in (:debateParticipantLocationIdList) ");
	}
	str.append(" group by DCD.debateRepresentativeType.debateRepresentativeTypeId,model.characteristics.characteristicsId" +
			" order by DCD.debateRepresentativeType.debateRepresentativeTypeId,model.characteristics.characteristicsId ");
	
	Query query = getSession().createQuery(str.toString());	
	
	if(startDate !=null && endDate !=null){
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
	}
	if(debateLocationIdList != null  && debateLocationIdList.size() >0l){
		query.setParameterList("debateLocationIdList", debateLocationIdList);
	}
    if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
    	query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
	}
	return query.list(); 
	
}
public List<Object[]> getDesignationwiseCandidateCharectersScaling(Date startDate,Date endDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList){
	
	StringBuilder str = new StringBuilder();		
	str.append(" select  DCD.debateCandidateDesignationId,DCD.debateRepresentativeType.debateRepresentativeTypeId," +
			" DCD.debateRepresentativeType.type," +
			" model.debateParticipant.candidate.candidateId,model.debateParticipant.candidate.lastname," +
			" model.characteristics.characteristicsId," +
			" model.characteristics.name," +
			" sum(model.scale), model.debateParticipant.party.partyId,model.debateParticipant.party.shortName " +
			" from DebateParticipantCharcs model,DebateCandidateDesignation DCD " );
	str.append(" where model.debateParticipant.candidateId=DCD.candidateId and model.debateParticipant.debate.isDeleted = 'N' " +
			" and model.characteristics.isDeleted ='N' " +
			//" and model.debateParticipant.party.isNewsPortal = 'Y' " +
			" and  model.debateParticipant.candidate.isDebateCandidate ='Y' and DCD.isActive ='Y' and DCD.isDeleted = 'N' " );
	
	   if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0 ){
	      str.append(" and model.debateParticipant.candidate.state.stateId in (:debateParticipantLocationIdList)");
	    }
	/*if(state !=null && state.trim().equalsIgnoreCase("ap")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
	}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
		str.append(" and model.debateParticipant.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
	}
	*/	
	if(startDate !=null && endDate !=null){
		str.append(" and date(model.debateParticipant.debate.startTime) >= :startDate and date(model.debateParticipant.debate.endTime) <= :endDate  ");
	}
	if(debateLocationIdList != null && debateLocationIdList.size()>0){
		 str.append(" and model.debateParticipant.debate.addressId in (:debateLocationIdList) ");
	}
	str.append(" group by DCD.debateRepresentativeType.debateRepresentativeTypeId, model.debateParticipant.candidate.candidateId,model.characteristics.characteristicsId " +
			" order by DCD.debateRepresentativeType.debateRepresentativeTypeId,model.debateParticipant.candidate.candidateId,model.characteristics.characteristicsId ");
	
	Query query = getSession().createQuery(str.toString());	
	
	if(startDate !=null && endDate !=null){
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
	}
	if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0 ){
		query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
	}
    if(debateLocationIdList != null && debateLocationIdList.size()>0){
    	query.setParameterList("debateLocationIdList", debateLocationIdList);
	}
	return query.list(); 
	
}
}
