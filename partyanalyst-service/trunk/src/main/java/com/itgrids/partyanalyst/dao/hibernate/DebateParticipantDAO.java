package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
import com.itgrids.partyanalyst.model.DebateParticipant;
import com.itgrids.partyanalyst.utils.IConstants;


public class DebateParticipantDAO extends GenericDaoHibernate<DebateParticipant, Long> implements IDebateParticipantDAO{

	public DebateParticipantDAO() {
		super(DebateParticipant.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebatePaticepantsAndSummeryDetails(Long debateId,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select model.candidate.lastname," +
				" model.party.shortName,model.summary ,model.candidate.candidateId , model.party.partyId ");
		
		sb.append(" ,model.candidate.state.stateId " );
		
		sb.append(" from DebateParticipant model " );
		
		sb.append(" where" );
		if(debateId != null && debateId.longValue()>0)
		  sb.append(" model.debate.debateId = :debateId  ");
		
		 Query query = getSession().createQuery(sb.toString());
		if(debateId != null && debateId.longValue()>0)
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
	public List<Object[]> getDebateTotalScaleForEachParty(Date fromDate,Date toDate,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.party.partyId,sum(model2.scale) from " +
				" Debate model , DebateParticipant model1 ,DebateParticipantCharcs model2 " );
		  if(stateId != null && stateId.longValue() > 0){
		       sb.append(" , Debate model3 ");
		     }
				sb.append(" where" );
				sb.append(" model.debateId = model1.debate.debateId  and model1.debateParticipantId = model2.debateParticipant.debateParticipantId " );
				sb.append(" and model.isDeleted = 'N' " );
		
		if(fromDate != null)
			sb.append(" and date(model.startTime) >= :fromDate ");
		if(toDate != null)
			sb.append(" and date(model.endTime) <= :toDate ");
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model1.debateId = model3.debateId  and model3.isDeleted = 'N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 2L){
		    	sb.append(" and model3.address.state.stateId is null ");
		    }
		sb.append(" group by model1.party.partyId");
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null)
		  query.setParameter("fromDate", fromDate);
		if(toDate != null)
		   query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalDabtesCountsForEachParty(Date fromDate , Date toDate,Long stateId)
	{
		StringBuilder sb =new StringBuilder();
		sb.append(" select distinct model1.party.partyId,count(model.debateId) ,model1.party.shortName from " +
				" Debate model , DebateParticipant model1  " );
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , Debate model3 ");
		    }
		sb.append(" where " );
		sb.append(" model.debateId = model1.debate.debateId and model.isDeleted = 'N' " );
		
		if(fromDate != null)
			sb.append(" and date(model.startTime) >= :fromDate ");
		if(toDate != null)
			sb.append(" and date(model.endTime) <= :toDate ");
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model1.debateId = model3.debateId  and model3.isDeleted = 'N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 2L){
		    	sb.append(" and model3.address.state.stateId is null ");
		    }
		    sb.append(" group by model1.party.partyId");
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		if(toDate != null)
		 query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateTotalScaleForEachCandidate(Date fromDate,Date toDate,Long stateId)
	{
		StringBuilder sb= new StringBuilder();
		sb.append("select distinct model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname,sum(model2.scale) from " +
				" Debate model , DebateParticipant model1 ,DebateParticipantCharcs model2 " );
		sb.append(" where " );
		sb.append(" model.debateId = model1.debate.debateId  and model1.debateParticipantId = model2.debateParticipant.debateParticipantId " +
				" and model.isDeleted = 'N' ");
		if(fromDate != null)
				sb.append(" and date(model.startTime) >= :fromDate " );
		if(toDate != null)
				sb.append(" and date(model.endTime) <= :toDate " );
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model1.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model1.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 2L){
			      sb.append(" and model1.candidate.state.stateId="+IConstants.DEBATE_OTHERS_ID);
			    }
		sb.append(" group by model1.party.partyId,model1.candidate.candidateId ");
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		if(toDate != null)
		 query.setParameter("toDate", toDate);
       
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalDabtesCountsForEachCandidate(Date fromDate , Date toDate,Long stateId)
	{
		StringBuilder sb= new StringBuilder();
		sb.append(" select distinct model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname,count(model.debateId)  from " +
				" Debate model , DebateParticipant model1 " );
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }
		 sb.append(" where  ");
		 sb.append("  model.debateId = model1.debate.debateId and model.isDeleted = 'N' " );
		
		if(fromDate != null)
			sb.append(" and date(model.startTime) >= :fromDate " );
		if(toDate != null)
				sb.append(" and date(model.endTime) <= :toDate " );
			
			if(stateId != null && stateId.longValue() > 0){
			      sb.append(" and model1.debateParticipantId = model3.debateParticipant.debateParticipantId  and model3.isDeleted = 'N' ");
			    }
			 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
			    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
			    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 2L){
				      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_OTHERS_ID);
				    }
			 sb.append("  group by model1.party.partyId,model1.candidate.candidateId");
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
	 	if(toDate != null)
		 query.setParameter("toDate", toDate);
	 	
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCanidatesListForDebateForSelectedDates(Date fromDate,Date toDate,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname from " +
				" Debate model , DebateParticipant model1 " );
		if(stateId != null && stateId.longValue() > 0){
	      sb.append(" , DebateParticipantLocation model3 ");
	    }
		sb.append(" where  " );
		sb.append(" model.debateId = model1.debate.debateId and model.isDeleted = 'N' " );
		if(fromDate != null)
		 sb.append(" and date(model.startTime) >= :fromDate " );
		if(toDate != null)
		 sb.append(" and date(model.endTime) <= :toDate ");
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model1.debateParticipantId = model3.debateParticipant.debateParticipantId  and model3.isDeleted = 'N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 2L){
			      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_OTHERS_ID);
			    }
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		if(toDate != null)
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
	
	public List<Object[]> getPartiesAndCanidatesIdForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("select DP.partyId ,DP.party.shortName, DP.candidateId ,DP.candidate.lastname, count(*) " +
				                                       "from DebateParticipant DP  ");
		sb.append(" where DP.debate.debateId = DP.debateId and DP.debate.isDeleted = 'N'  ");
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
		if(fromDate !=null && toDate !=null){
			sb.append(" and date(DP.debate.startTime) >= :fromDate and date(DP.debate.startTime) <= :toDate ");
		}
		
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
	
public List<Object[]> getDebateCandidateCharacteristicsDetailForSelection(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("select DP.party.partyId ,DP.party.shortName, DS.debateSubjectId ,DS.subject, " +
				" DP.candidate.candidateId,DP.candidate.lastname, " +
				" DPC.characteristics.characteristicsId ,DPC.characteristics.name, sum(DPC.scale)  ");	
		sb.append("  from DebateSubject DS , DebateParticipant DP , DebateParticipantCharcs DPC ");
		sb.append(" where DS.debate.debateId = DP.debate.debateId and DP.debateParticipantId = DPC.debateParticipant.debateParticipantId ");
		
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			sb.append(" and DP.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
		sb.append(" and DPC.characteristics.isDeleted = 'N' and DS.debate.isDeleted = 'N' ");
		
		if(fromDate !=null && toDate !=null){
			sb.append(" and date(DS.debate.startTime) >= :fromDate and date(DS.debate.startTime) <= :toDate ");
		}
		
		
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

	public List<Object[]> getDistinctDebateParties()
	{
		Query query = getSession().createQuery("select distinct model.party.partyId,model.party.shortName from DebateParticipant model");
		
		return query.list();
	}
	
	public List<Object[]> getDistinctDebatePartiesForSelection(Date fromDate,Date toDate, List<Long> partyIds, Long stateId)
	{
		StringBuilder br = new StringBuilder();
		br.append(" select distinct model.party.partyId,model.party.shortName from DebateParticipant model ");
		br.append(" where model.debate.isDeleted = 'N'" );
		
		if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
			br.append(" and model.candidate.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		}else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
			br.append(" and model.candidate.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		}
		
		if(fromDate !=null && toDate  !=null){
			br.append(" and date(model.debate.startTime) >= :fromDate and date(model.debate.endTime) <= :toDate  ");
		}			
		if(partyIds != null && partyIds.size()>0)
		{
			br.append(" and model.party.partyId in (:partyIds)");
		}
		Query query = getSession().createQuery(br.toString());
		
		if(fromDate !=null && toDate  !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(partyIds != null && partyIds.size()>0)
		{
			query.setParameterList("partyIds", partyIds);
		}
		return query.list();
	}
	
	/*Candidate Page*/
	
	public List<Object[]> getTotalAttendedDebatesOfCadre(Long tdpCadreId,Long stateId){
		
		/*Query query = getSession().createQuery(" select distinct model.debate.debateId,model1.candidate.candidateId  " +
				" from DebateParticipant model,TdpCadreCandidate model1 " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model1.tdpCadre.tdpCadreId = :tdpCadreId " +
				" and model.debate.isDeleted = 'N' ");
		
		query.setParameter("tdpCadreId", tdpCadreId);*/
		//srujana
		StringBuilder sb =new StringBuilder();
		  sb.append("select distinct model.debate.debateId,model1.candidate.candidateId, " +
		  		" model2.address.state.stateId " +
				" from DebateParticipant model,TdpCadreCandidate model1,DebateParticipantLocation model2 " +
				" where model.candidate.candidateId = model1.candidate.candidateId  and model.debateParticipantId =model2.debateParticipantId " );
		  if(tdpCadreId != null && tdpCadreId.longValue()>0)
			  sb.append(" and model1.tdpCadre.tdpCadreId = :tdpCadreId " );
		  sb.append(" and model.debate.isDeleted = 'N'");
		  Query query=getSession().createQuery(sb.toString());
		 if(tdpCadreId != null && tdpCadreId.longValue()>0)
		     query.setParameter("tdpCadreId", tdpCadreId);
		return  query.list();
	}
	
	/*select D.debate_id,DS.subject,date(D.start_time),D.start_time,D.end_time,O.observer_id ,O.observer_name,
	C.characteristics_id,C.name,DPC.scale,CAN.candidate_id,CAN.lastname,P.party_id,P.short_name
	 from debate_participant DP,debate D,debate_subject DS,debate_observer DO,observer O,debate_participant_charcs DPC,
	 characteristics C,candidate CAN,party P
	where D.debate_id = DP.debate_id 
	and DS.debate_id = D.debate_id
	and DO.debate_id = D.debate_id
	and O.observer_id = DO.observer_id
	and DP.debate_participant_id = DPC.debate_participant_id
	and DPC.characteristics_id = C.characteristics_id
	and DP.candidate_id = CAN.candidate_id
	and P.party_id = CAN.party_id
	and D.is_deleted = 'N'
	and D.debate_id = 474
	;*/
	public List<Object[]> getDebateParticipatedDetailsOfCadre(List<Long> debetIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT  DP.debate.debateId,DS.subject,date(DP.debate.startTime),DP.debate.startTime,DP.debate.endTime," +
				" DO.observer.observerId,DO.observer.observerName,DPC.characteristics.characteristicsId,DPC.characteristics.name,DPC.scale," +
				" candidate.candidateId,candidate.lastname,party.partyId,party.shortName " +
				" FROM  DebateParticipant DP,DebateSubject DS,DebateObserver DO, " +
				" DebateParticipantCharcs DPC  left join DPC.debateParticipant.candidate candidate" +
				" left join DPC.debateParticipant.candidate.party  party  " +
				" " +
				" WHERE DP.debate.debateId = DS.debate.debateId " +
				" AND DP.debate.debateId = DO.debate.debateId " +
				" AND DPC.debateParticipant.debateParticipantId = DP.debateParticipantId" +
				" AND DP.debate.isDeleted = 'N' " +
				" AND  DO.observer.isDeleted = 'N'" +
				" AND  DPC.characteristics.isDeleted = 'N' " +
				" AND DP.debate.debateId in (:debetIds) ") ;
		
		Query query = getSession().createQuery(str.toString());		
		query.setParameterList("debetIds", debetIds);
		
		return query.list();
		
	}
	
	//Core DashBoard Queries
	
	public List<Object[]> getPartyWiseDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId),count(model.candidate.candidateId)" +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " +
				" and model.candidate.isDebateCandidate ='Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId in (:debateLocationIdList) " );
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		/*if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) ");
		}*/
		str.append(" group by model.party.partyId " +
				" order by model.party.newsOrderNo ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			 query.setParameterList("debateLocationIdList", debateLocationIdList);
		 }
        /*if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
        	query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}*/
		return query.list();
	}
	
	public List<Object[]> getTotalDabtesCountsForEachCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList)
	{
		StringBuilder str = new StringBuilder();		
		 str.append("select distinct model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname,count(distinct model.debateId)  from " +
				" Debate model , DebateParticipant model1 ");
		 str.append(" where  model.debateId = model1.debate.debateId and model.isDeleted = 'N' " +
				" and model1.party.isNewsPortal = 'Y'" +
				//"  and  model1.candidate.isDebateCandidate ='Y' " +
				" and  model1.candidate.isDebateCandidate ='Y' " );
		 if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0){
			 str.append(" and model1.candidate.state.stateId in (:debateParticipantLocationIdList) ");
		 }
	 	if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		 
		if(fromDate !=null && toDate !=null){
			 str.append(" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model1.debate.address.state.stateId in(:debateLocationIdList)  ");
		}
		str.append(" group by model1.party.partyId,model1.candidate.candidateId " +
				" order by model1.party.newsOrderNo ");		
		Query query = getSession().createQuery(str.toString());		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0 ){
			 query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		 }
        if(debateLocationIdList != null && debateLocationIdList.size()>0){
        	 query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		return query.list();
	}
	public List<Object[]> getChannelWiseDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.debate.channel.channelId,model.debate.channel.channelName,model.party.partyId,model.party.shortName," +
				"count(distinct model.debate.debateId),count(model.candidate.candidateId) " +
				" from DebateParticipant model ");
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " );
		   if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			 str.append(" and model.debateId = model3.debateId ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId in (:debateLocationIdList) " );
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		
		str.append(" group by model.debate.channel.channelId,model.party.partyId " +
				" order by model.debate.channel.channelId,model.party.newsOrderNo   ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		return query.list();
	}
	
	public List<Object[]> getDebatesCountOfCandidate(Date startDate,Date endDate,List<Long> roles,String state){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select C.candidate_id as candidateId,C.lastname as candidateName,P.party_id as partyId,P.short_name partyName,count(distinct D.debate_id) as count" +
				" from debate_participant_role DPR " +
				" ,debate_roles DR,debate_participant DP,debate D,candidate C,party P " +
				" where DP.debate_participant_id = DPR.debate_participant_id " +
				" and DPR.debate_roles_id  = DR.debate_roles_id " +
				" and DP.debate_id = D.debate_id" +
				" and DP.candidate_id = C.candidate_id" +
				" and P.party_id = DP.party_id " +
				" and D.is_deleted = 'N' " +
				" and C.is_debate_candidate = 'Y'" );
		
				if(roles !=null && roles.size()>0){
					str.append( " and DR.debate_roles_id in (:debateRoles) " );
				}				
				if(startDate !=null && endDate !=null){
					str.append( " and date(D.start_time) >= :startDate and date(D.end_time) <= :endDate ");
				}			  
				
				if(state !=null && state.trim().equalsIgnoreCase("ap")){
					str.append(" and P.party_id not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
				}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
					str.append(" and P.party_id not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
				}
				
				str.append( " group by C.candidate_id,P.party_id ");
				str.append( " order by count(distinct D.debate_id) desc ");
				
				Query query = getSession().createSQLQuery(str.toString())	
						
				.addScalar("candidateId",Hibernate.LONG)
				.addScalar("candidateName",Hibernate.STRING)
				.addScalar("partyId",Hibernate.LONG)
				.addScalar("partyName",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG);
				
				if(roles !=null && roles.size()>0){
					query.setParameterList("debateRoles",roles);
				}
				
				if(startDate !=null && endDate !=null){
					query.setParameter("startDate", startDate);
					query.setParameter("endDate", endDate);
				}
				
				return query.list();
	}
	
	public List<Object[]> getPartyWiseDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId,Long casteId,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select ");
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			//str.append(" '','',");
			str.append(" distinct model.candidate.candidateId,model.candidate.lastname, ");
		}else if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
			str.append(" distinct model.candidate.candidateId,model.candidate.lastname, ");
		}else{
			//str.append(" distinct model.candidate.candidateId,model.candidate.lastname, ");
		}
		
		str.append(" DS.debate.debateId,model.debate.startTime,model.debate.endTime," +
				" DOB.observer.observerId,DOB.observer.observerName,model.debate.channel.channelId,model.debate.channel.channelName, " +
				" state.stateId,state.stateName,model.partyId,model.party.shortName " +
				"  FROM DebateSubject DS" );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" ,DebateParticipantRole DPR ");
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" ,DebateCandidateDesignation DCD ");
		}
		str.append(" ,DebateObserver DOB,DebateParticipant model " +
				" left join model.debate.address address " +
				" left join address.state state " );
		/*if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" , Debate model3 ");
		    }*/
		str.append(" WHERE model.debateId = DS.debate.debateId  " +
				" and model.debate.debateId = DOB.debate.debateId" +
				" and model.debate.isDeleted = 'N' and model.candidate.isDebateCandidate = 'Y' " );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" and model.debateParticipantId = DPR.debateParticipant.debateParticipantId  and DPR.debateRoles.debateRolesId =:roleId ");	
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" and  model.candidateId = DCD.candidateId and DCD.debateRepresentativeTypeId =:designationId  and DCD.isActive='Y' and DCD.isDeleted='N' ");
		}else if(partyIds !=null && partyIds.size() > 0L && !partyIds.contains(0L)){
			str.append(" and model.partyId in (:partyIds) ");
		}else{
			str.append(" and model.partyId = 872 ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){		
		  str.append(" and model.candidate.isDebateCandidate = 'Y' ");
		}
		
		if(candidateId != null && candidateId.longValue() >0){
			str.append(" and model.candidate.candidateId =:candidateId " );
		}
		
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime)  between :startDate and :endDate  ");
		}
		/*if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" and model.debateId = model3.debateId and model3.isDeleted ='N' ");
		    }*/
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
		if(debateLocationIdList != null  && debateLocationIdList.size() >0l ){
		      str.append(" and model.debate.address.state.stateId in (:debateLocationIdList) " );
		    }
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			      str.append(" and model.debate.address.state.stateId in (:debateLocationIdList) " );
			    }
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and model.candidate.state.stateId in (:debateParticipantLocationIdList) " );
		}
		}
		if(casteId != null && casteId.longValue()>0 ){
			str.append(" and model.candidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId =:casteId ");
		}else if(casteId != null && type.equalsIgnoreCase("casteOthers")){
			str.append(" and model.candidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId is null ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			str.append(" group by DS.debate.debateId ");
		}else{
			str.append(" group by model.candidate.candidateId,model.debate.channel.channelId,DS.debate.debateId ");
		}
		//str.append(" group by DS.debate.debateId ");
		str.append(" order by model.debate.startTime desc ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(candidateId != null && candidateId.longValue() >0){
			query.setParameter("candidateId", candidateId);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
        if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
        	query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		}
		if(roleId != null && roleId.longValue()>0L){
			query.setParameter("roleId", roleId);
		}
		if(designationId != null && designationId.longValue()>0L){
			query.setParameter("designationId", designationId);
		}else if(partyIds !=null && partyIds.size()>0L && !partyIds.contains(0L)){
			query.setParameterList("partyIds", partyIds);
		}
		if(casteId != null && casteId.longValue()>0 ){
			query.setParameter("casteId", casteId);
		}
		return query.list();
	}
	public Long getTotalAttendedDebatesOfCadreNew(Long tdpCadreId){
		
		Query query = getSession().createQuery(" select count(distinct model.debate.debateId)  " +
				" from DebateParticipant model,TdpCadreCandidate model1 " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model1.tdpCadre.tdpCadreId = :tdpCadreId " +
				" and model.debate.isDeleted = 'N' ");
		
		query.setParameter("tdpCadreId", tdpCadreId);
		
		return  (Long) query.uniqueResult();
	}
	
public List<Object[]> getPartyAndCandidateWiseDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,List<Long> candidateIds,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select ");
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			str.append(" '','',");
		}else if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
			str.append(" model.candidate.candidateId,model.candidate.lastname, ");
		}else{
			str.append(" model.candidate.candidateId,model.candidate.lastname, ");
		}
		
		str.append("  DS.debate.debateId,model.debate.startTime,model.debate.endTime," +
				" DOB.observer.observerId,DOB.observer.observerName,model.debate.channel.channelId,model.debate.channel.channelName, " +
				" model.debate.address.state.stateId " +
				"  FROM DebateParticipant model,DebateSubject DS,DebateObserver DOB " );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" ,DebateParticipantRole DPR ");
		}
		if(designationId != null && designationId.longValue()>0L){
        	str.append(" ,DebateCandidateDesignation DCD ");
		}
		str.append("  WHERE model.debateId = DS.debate.debateId  " +
				" and model.debate.debateId = DOB.debate.debateId" +
				" and model.debate.isDeleted = 'N' " );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" and model.debateParticipantId = DPR.debateParticipant.debateParticipantId  and DPR.debateRoles.debateRolesId =:roleId ");	
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" and DCD.candidateId = model.candidateId and DCD.debateRepresentativeTypeId =:designationId  and DCD.isActive ='Y' and DCD.isDeleted = 'N' ");
		}else if(partyIds !=null && partyIds.size()>0 && !partyIds.contains(0l)){
			str.append(" and model.partyId in (:partyIds) ");
		}
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime)  between :startDate and :endDate  ");
		}
		
		if(candidateIds !=null && candidateIds.size()>0){
			str.append(" and model.candidate.candidateId in (:candidateIds) " );
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model.debate.address.state.stateId in (:debateLocationIdList) " );
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and  model.candidate.state.stateId in (:debateParticipantLocationIdList) ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			str.append(" group by DS.debate.debateId ");
		}else{
			str.append(" group by model.candidate.candidateId ");
		}
		//str.append(" group by DS.debate.debateId ");
		str.append(" order by model.debate.startTime desc ");
		
		Query query = getSession().createQuery(str.toString());
		
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		if(candidateIds !=null && candidateIds.size()>0){
			query.setParameterList("candidateIds", candidateIds);
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		if(roleId != null && roleId.longValue()>0L){
			query.setParameter("roleId", roleId);
		}
		if(designationId != null && designationId.longValue()>0L){
	    	   query.setParameter("designationId", designationId);
		}else if(partyIds !=null && partyIds.size()>0 && !partyIds.contains(0l)){
			query.setParameterList("partyIds", partyIds);
		}
		return query.list();
	}
   public List<Integer> getDebateParticipantId(Long debateId)
   {
	StringBuilder sb = new StringBuilder();
	sb.append("select model.debateParticipantId from DebateParticipant model " );
	sb.append(" where" );
	if(debateId != null && debateId.longValue()>0)
	  sb.append(" model.debate.debateId = :debateId  ");
	 Query query = getSession().createQuery(sb.toString());
	if(debateId != null && debateId.longValue()>0)
	 query.setParameter("debateId", debateId);
	return query.list();
}
   public List<Object[]> getPartyWiseDebateOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId),count(model.candidate.candidateId) " +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " +
				" and model.candidate.isDebateCandidate = 'Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId is null ");
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		/*if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) ");
		}*/
		str.append(" group by model.party.partyId " +
				" order by model.party.newsOrderNo ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
       /*if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
    	   query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}*/
		return query.list();
	}
   public List<Object[]> getChannelWiseOthersDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.debate.channel.channelId,model.debate.channel.channelName,model.party.partyId,model.party.shortName," +
				"count(distinct model.debate.debateId),count(model.candidate.candidateId)" +
				" from DebateParticipant model ");
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " );
		   if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			 str.append(" and model.debateId = model3.debateId ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId is null " );
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		
		str.append(" group by model.debate.channel.channelId,model.party.partyId " +
				" order by model.debate.channel.channelId,model.party.newsOrderNo   ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}
   public List<Object[]> getPartyWiseOthersDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId,Long casteId,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select ");
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			//str.append(" '','',");
			str.append(" model.candidate.candidateId,model.candidate.lastname, ");
		}else if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
			str.append(" model.candidate.candidateId,model.candidate.lastname, ");
		}else{
			str.append(" model.candidate.candidateId,model.candidate.lastname, ");
		}
		
		str.append("  DS.debate.debateId,model.debate.startTime,model.debate.endTime," +
				" DOB.observer.observerId,DOB.observer.observerName,model.debate.channel.channelId,model.debate.channel.channelName, " +
				" state.stateId,state.stateName,model.partyId,model.party.shortName " +
				"  FROM DebateSubject DS,DebateObserver DOB " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" , Debate model3 ");
	       }
		
		if(roleId != null && roleId.longValue()>0L){
			str.append(" ,DebateParticipantRole DPR ");
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" ,DebateCandidateDesignation DCD ");
		}
		str.append(" ,DebateParticipant model " +
				" left join model.debate.address address " +
				" left join address.state state ");
		/*if(casteId != null && casteId.longValue()>0 && casteId.longValue() == 0){
			str.append("  left join model.candidate candidate " +
				" left join candidate.casteState casteState left join casteState.casteCategoryGroup  casteCategoryGroup " +
				" left join casteCategoryGroup.casteCategory casteCategory ");
		}*/
		
		str.append(" WHERE model.debateId = DS.debate.debateId  " +
				" and model.debate.debateId = DOB.debate.debateId" +
				" and model.debate.isDeleted = 'N' and model.candidate.isDebateCandidate = 'Y' " );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" and model.debateParticipantId = DPR.debateParticipant.debateParticipantId  and DPR.debateRoles.debateRolesId =:roleId ");	
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" and  model.candidateId = DCD.candidateId and DCD.debateRepresentativeTypeId =:designationId ");
		}else if(partyIds !=null && partyIds.size()>0){
			str.append(" and model.partyId in (:partyIds) ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
			str.append(" and model.candidate.isDebateCandidate = 'Y' ");
		}
		
		if(candidateId != null && candidateId.longValue() >0){
			str.append(" and model.candidate.candidateId =:candidateId " );
		}
		
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime)  between :startDate and :endDate  ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" and model.debateId = model3.debateId and model3.isDeleted ='N' ");
		    }
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
		      str.append(" and model3.address.state.stateId is null " );
		    }
		}else if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
			if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			      str.append(" and model.debateId = model3.debateId and model3.isDeleted ='N' ");
			 }
			if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
				      str.append(" and model3.address.state.stateId is null " );
			}
	        if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
	        	str.append(" and model.candidate.state.stateId in (:debateParticipantLocationIdList) " );
			}
		}
		if(casteId != null && casteId.longValue()>0 ){
			str.append(" and model.candidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId =:casteId ");
		}else if(casteId != null && type.equalsIgnoreCase("casteOthers")){
			str.append(" and model.candidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId is null ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			str.append(" group by DS.debate.debateId ");
		}else{
			str.append(" group by model.candidate.candidateId,model.debate.channel.channelId,DS.debate.debateId ");
		}
		//str.append(" group by DS.debate.debateId ");
		str.append(" order by model.debate.startTime desc ");
		
		Query query = getSession().createQuery(str.toString());
		
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(candidateId != null && candidateId.longValue() >0){
			query.setParameter("candidateId", candidateId);
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
        if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
        	query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		}
		if(roleId != null && roleId.longValue()>0L){
			query.setParameter("roleId", roleId);
		}
		if(designationId != null && designationId.longValue()>0L){
			query.setParameter("designationId", designationId);
		}else if(partyIds !=null && partyIds.size()>0l){
			query.setParameterList("partyIds", partyIds);
		}
		if(casteId != null && casteId.longValue()>0 ){
			query.setParameter("casteId", casteId);
		}
		return query.list();
	} 
   public List<Object[]> getPartyWiseDebateParticipantOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList ){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId),count(distinct model.candidate.candidateId),count(model.candidate.candidateId) " +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " +
				" and model.candidate.isDebateCandidate = 'Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId is null ");
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) "); 
		}
		str.append(" group by model.party.partyId " +
				" order by model.party.newsOrderNo ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list();
	}
   public List<Object[]> getPartyWiseDebateParticipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId),count(distinct model.candidate.candidateId),count(model.candidate.candidateId)" +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " +
				" and  model.candidate.isDebateCandidate = 'Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId  in (:debateLocationIdList) " );
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) "); 
		}
		str.append(" group by model.party.partyId " +
				" order by model.party.newsOrderNo ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			 query.setParameterList("debateLocationIdList", debateLocationIdList);
		 }
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list();
	}
   public List<Object[]> getPartyAndCandidateWiseOthersDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,List<Long> candidateIds,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select ");
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			str.append(" '','',");
		}else if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
			str.append(" model.candidate.candidateId,model.candidate.lastname, ");
		}else{
			str.append(" model.candidate.candidateId,model.candidate.lastname, ");
		}
		
		str.append("  DS.debate.debateId,model.debate.startTime,model.debate.endTime," +
				" DOB.observer.observerId,DOB.observer.observerName,model.debate.channel.channelId,model.debate.channel.channelName, " +
				" model.debate.address.state.stateId " +
				"  FROM DebateParticipant model,DebateSubject DS,DebateObserver DOB " );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" ,DebateParticipantRole DPR ");
		}
        if(designationId != null && designationId.longValue()>0L){
        	str.append(" ,DebateCandidateDesignation DCD ");
		}
		str.append("  WHERE model.debateId = DS.debate.debateId  " +
				" and model.debate.debateId = DOB.debate.debateId" +
				" and model.debate.isDeleted = 'N' " );
	
		if(roleId != null && roleId.longValue()>0L){
			str.append(" and model.debateParticipantId = DPR.debateParticipant.debateParticipantId  and DPR.debateRoles.debateRolesId =:roleId ");	
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" and DCD.candidateId = model.candidateId and DCD.debateRepresentativeTypeId =:designationId  and DCD.isActive ='Y' and DCD.isDeleted = 'N' ");
		}else if(partyIds !=null && partyIds.size()>0 && !partyIds.contains(0l)){
			str.append(" and model.partyId in (:partyIds) ");
		}
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime)  between :startDate and :endDate  ");
		}
		
		if(candidateIds !=null && candidateIds.size()>0){
			str.append(" and model.candidate.candidateId in (:candidateIds) " );
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model.debate.address.state.stateId is null " );
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and  model.candidate.state.stateId in (:debateParticipantLocationIdList) ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			str.append(" group by DS.debate.debateId ");
		}else{
			str.append(" group by model.candidate.candidateId ");
		}
		//str.append(" group by DS.debate.debateId ");
		str.append(" order by model.debate.startTime desc ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		if(candidateIds !=null && candidateIds.size()>0){
			query.setParameterList("candidateIds", candidateIds);
		}
		/*if(debateLocationIdList != null && debateLocationIdList.size()>0){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}*/
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		if(roleId != null && roleId.longValue()>0L){
			query.setParameter("roleId", roleId);
		}
       if(designationId != null && designationId.longValue()>0L){
    	   query.setParameter("designationId", designationId);
		}else if(partyIds !=null && partyIds.size()>0 && !partyIds.contains(0l)){
			query.setParameterList("partyIds", partyIds);
		}
		return query.list();
	}
   public List<Object[]> getTotalDabtesCountsForEachOtherCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList)
	{
		StringBuilder str = new StringBuilder();		
		 str.append("select distinct model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname,count(distinct model.debateId)  from " +
				" Debate model , DebateParticipant model1 ");
		 str.append(" where  model.debateId = model1.debate.debateId and model.isDeleted = 'N' " +
				" and model1.party.isNewsPortal = 'Y' " +
				" and model1.candidate.isDebateCandidate = 'Y' " );
		 if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0  && debateParticipantLocationIdList.size() != 3L && !debateParticipantLocationIdList.contains(2L)){
		      str.append(" and model1.candidate.state.stateId in (:debateParticipantLocationIdList) " );
		    }else if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size() > 0 && debateParticipantLocationIdList.size() == 1L && debateParticipantLocationIdList.contains(2L)){
		    	str.append(" and model3.address.state.stateId is null  ");
		    }
	 	if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		 
		if(fromDate !=null && toDate !=null){
			 str.append(" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model1.debate.address.state.stateId is null ");
		}
		str.append(" group by model1.party.partyId,model1.candidate.candidateId " +
				" order by model1.party.newsOrderNo ");		
		Query query = getSession().createQuery(str.toString());		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0  && debateParticipantLocationIdList.size() != 3L && !debateParticipantLocationIdList.contains(2L)){
			 query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		 }
       /*if(debateLocationIdList != null && debateLocationIdList.size()>0){
       	 query.setParameterList("debateLocationIdList", debateLocationIdList);
		}*/
		return query.list();
	}
   public List<Object[]> getTotalDabtesCountsForEachCandidateOthersNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList)
	{
		StringBuilder str = new StringBuilder();		
		 str.append("select distinct model1.party.partyId,model1.party.shortName,model1.candidate.candidateId,model1.candidate.lastname,count(distinct model.debateId)  from " +
				" Debate model , DebateParticipant model1 ");
		 str.append(" where  model.debateId = model1.debate.debateId and model.isDeleted = 'N' " +
				" and model1.party.isNewsPortal = 'Y' " +
				" and model1.candidate.isDebateCandidate ='Y' " );
				//" and  model.debateParticipant.candidate.isDebateCandidate ='Y'" );
		 if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0){
			 str.append(" and model1.candidate.state.stateId in (:debateParticipantLocationIdList) ");
		 }
	 	if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		 
		if(fromDate !=null && toDate !=null){
			 str.append(" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model1.debate.address.state.stateId is null ");
		}
		str.append(" group by model1.party.partyId,model1.candidate.candidateId " +
				" order by model1.party.newsOrderNo ");		
		Query query = getSession().createQuery(str.toString());		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0 ){
			 query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		 }
		return query.list();
	}
   public List<Object[]> getPartyWiseDebateParticipantStateOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList ){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId),model.candidate.candidateId " +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " +
				" and model.candidate.isDebateCandidate = 'Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId is null ");
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) "); 
		}
		str.append(" group by model.party.partyId, model.candidate.candidateId " +
				" order by model.party.newsOrderNo ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list();
	}
   public List<Object[]> getPartyWiseStateDebateParticipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId), model.candidate.candidateId " +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " +
				" and  model.candidate.isDebateCandidate = 'Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId  in (:debateLocationIdList) " );
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) "); 
		}
		str.append(" group by model.party.partyId,model.candidate.candidateId " +
				" order by model.party.newsOrderNo ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			 query.setParameterList("debateLocationIdList", debateLocationIdList);
		 }
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list();
	}
   public List<Object[]> getPartyWiseDebateParticipantsOtherDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId),count(model.candidate.candidateId) " +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " );
				//" and model.candidate.isDebateCandidate = 'Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId is null ");
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) ");
		}
		str.append(" group by model.party.partyId " +
				" order by model.party.newsOrderNo ");
		
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
   public List<Object[]> getPartyWiseDebatePartipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select model.party.partyId,model.party.shortName,count(distinct model.debate.debateId),count(model.candidate.candidateId)" +
				" from DebateParticipant model" );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			   str.append(" , Debate model3 ");
		    }
		str.append(" where model.debate.isDeleted = 'N' " +
				" and model.party.isNewsPortal = 'Y' " );
				//" and model.candidate.isDebateCandidate ='Y' " );
		if(debateLocationIdList != null && debateLocationIdList.size() > 0){
			str.append(" and model.debateId = model3.debateId and model3.isDeleted = 'N' ");
		    }
		   if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			   str.append(" and model3.address.state.stateId in (:debateLocationIdList) " );
		    }
		if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) ");
		}
		str.append(" group by model.party.partyId " +
				" order by model.party.newsOrderNo ");
		
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
   public List<Object[]> getDesignationWiseDebateDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.designationId,1.designation Name,2.debateCount,3.candidateCount
		str.append(" select DCD.debateCandidateDesignationId,DCD.debateRepresentativeType.debateRepresentativeTypeId," +
				" DCD.debateRepresentativeType.type," +
				" count(distinct model.debate.debateId),count(distinct model.candidate.candidateId) " +
				" from DebateParticipant model,DebateCandidateDesignation DCD ");
		
		str.append(" where model.candidateId=DCD.candidateId and model.debate.isDeleted = 'N' and DCD.isActive ='Y' and DCD.isDeleted = 'N' " );
				//" and model.party.isNewsPortal = 'Y' " );
		
		/*if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}*/
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateLocationIdList != null && debateLocationIdList.size()>0l){
			str.append(" and model.debate.addressId in (:debateLocationIdList) " );
		}
		str.append(" group by DCD.debateRepresentativeType.debateRepresentativeTypeId " +
				" order by DCD.debateRepresentativeType.debateRepresentativeTypeId ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			 query.setParameterList("debateLocationIdList", debateLocationIdList);
		 }
		return query.list();
	}
   public List<Object[]> getDesignationWiseDebateParticipantDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.partyId,1.shortName,2.debateCount,3.candidateCount
		str.append(" select DCD.debateCandidateDesignationId,DCD.debateRepresentativeType.debateRepresentativeTypeId," +
				" DCD.debateRepresentativeType.type,count(distinct model.debate.debateId),count(distinct model.candidate.candidateId)" +
				" from DebateParticipant model,DebateCandidateDesignation DCD " );
		
		str.append(" where  model.candidateId=DCD.candidateId and model.debate.isDeleted = 'N' " +
				//" and model.party.isNewsPortal = 'Y' " +
				" and  model.candidate.isDebateCandidate = 'Y'  and DCD.isActive ='Y' and DCD.isDeleted = 'N' " );
		
		/*if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}*/
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			str.append(" and model.debate.addressId in (:debateLocationIdList) ");
		}
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			str.append(" and model.candidate.state.stateId in(:debateParticipantLocationIdList) "); 
		}
		str.append(" group by DCD.debateRepresentativeType.debateRepresentativeTypeId " +
				" order by DCD.debateRepresentativeType.debateRepresentativeTypeId ");
		
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			 query.setParameterList("debateLocationIdList", debateLocationIdList);
		 }
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list();
	}
   public List<Object[]> getTotalDesignationsWiseDabtesCountsForEachCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList)
	{
		StringBuilder str = new StringBuilder();		
		 str.append("select distinct DCD.debateCandidateDesignationId,DCD.debateRepresentativeType.debateRepresentativeTypeId," +
				" DCD.debateRepresentativeType.type,model1.candidate.candidateId," +
		 		" model1.candidate.lastname,count(distinct model.debateId)  from " +
				" Debate model,DebateParticipant model1,DebateCandidateDesignation DCD ");
		 str.append(" where  model.debateId = model1.debate.debateId and  model1.candidateId=DCD.candidateId " +
		 		" and model.isDeleted = 'N' " +
				//" and model1.party.isNewsPortal = 'Y'" +
				" and  model1.candidate.isDebateCandidate ='Y' and DCD.isActive ='Y' and DCD.isDeleted = 'N' " );
		 if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0l){
			 str.append(" and model1.candidate.state.stateId in (:debateParticipantLocationIdList) ");
		 }
	 	/*if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}*/
		 
		if(fromDate !=null && toDate !=null){
			 str.append(" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0l){
			str.append(" and model.addressId in(:debateLocationIdList)");
		}
		str.append(" group by DCD.debateRepresentativeType.debateRepresentativeTypeId,model1.candidate.candidateId " +
				" order by DCD.debateRepresentativeType.debateRepresentativeTypeId ");		
		Query query = getSession().createQuery(str.toString());		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0l ){
			 query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		 }
       if(debateLocationIdList != null && debateLocationIdList.size()>0l){
       	 query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		return query.list();
	}
   public List<Long> getDesignationWiseDebateParticipantCandidatesDetails(Date startDate,Date endDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList){		
		StringBuilder str = new StringBuilder();		//0.candidateId
		str.append(" select distinct model.candidate.candidateId " +
				" from DebateParticipant model ");
		
		str.append(" where  model.debate.isDeleted = 'N'  " +
				" and model.candidate.isDebateCandidate ='Y' " );
				//" and model.party.isNewsPortal = 'Y' " );
		
						
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime) >= :startDate and date(model.debate.endTime) <= :endDate  ");
		}		
		if(debateLocationIdList != null && debateLocationIdList.size()>0l){
			str.append(" and model.debate.addressId in (:debateLocationIdList) " );
		}
		if(debateParticipantLocationIdList!= null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model.candidate.state.stateId in (:debateParticipantLocationIdList) " );
		}
		str.append(" and model.party.partyId = 872");
		Query query = getSession().createQuery(str.toString());	
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0l){
			 query.setParameterList("debateLocationIdList", debateLocationIdList);
		 }
		if(debateParticipantLocationIdList!= null && debateParticipantLocationIdList.size()>0l){
			query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		return query.list();
	}
   public List<Object[]> getDebateDesignationWiseDeatils(Date startDate,Date endDate,String state,List<Long> candidateIds){		
		StringBuilder str = new StringBuilder();		//0.candidateId
		str.append(" select distinct DP.candidate_id as candidateId,DRT.debate_representative_type_id as typeId," +
				" DRT.position as type from  debate_participant DP " +
				" left outer join debate_candidate_designation DCD ON DP.candidate_id =DCD.candidate_id " +
				" and DCD.is_active='Y' and DCD.is_deleted ='N'  and  DP.party_id = 872 " +
				" left outer join debate_representative_type DRT ON DRT.debate_representative_type_id = DCD.debate_representative_type_id " );
		if(candidateIds != null && candidateIds.size()>0l){
			str.append(" where DP.candidate_id in (:candidateIds)");
		}
		str.append(" group by DP.candidate_id ");
		 SQLQuery query = getSession().createSQLQuery(str.toString());
			query.addScalar("candidateId",Hibernate.LONG);
			query.addScalar("typeId",Hibernate.LONG);
			query.addScalar("type",Hibernate.STRING);
		if(candidateIds != null && candidateIds.size()>0l){
			query.setParameterList("candidateIds", candidateIds);
		}
		return query.list();
	}
   public List<Object[]> getTotalDesignationsWiseOthersDabtesCountsForEachCandidateNew(Date fromDate , Date toDate,String state,List<Long> debateParticipantLocationIdList,List<Long> debateLocationIdList,List<Long> candidateIds)
	{
		StringBuilder str = new StringBuilder();		
		 str.append("select distinct model1.candidate.candidateId," +
		 		" model1.candidate.lastname,count(distinct model.debateId)  from " +
				" Debate model,DebateParticipant model1 ");
		 str.append(" where  model.debateId = model1.debate.debateId " +
		 		//" and  model1.candidateId=DCD.candidateId " +
		 		" and model.isDeleted = 'N' " +
				//" and model1.party.isNewsPortal = 'Y'" +
				" and  model1.candidate.isDebateCandidate ='Y' " );
				//" and DCD.isActive ='Y' and DCD.isDeleted = 'N' " );
		 if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0l){
			 str.append(" and model1.candidate.state.stateId in (:debateParticipantLocationIdList) ");
		 }
	 	
		 
		if(fromDate !=null && toDate !=null){
			 str.append(" and date(model.startTime) >= :fromDate and date(model.endTime) <= :toDate ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0l){
			str.append(" and model.addressId in(:debateLocationIdList)");
		}
		if(candidateIds != null && candidateIds.size()>0l){
			 str.append(" and model1.candidate.candidateId in(:candidateIds) ");
		}
		str.append(" group by model1.candidate.candidateId " );
						
		Query query = getSession().createQuery(str.toString());		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(debateParticipantLocationIdList != null  && debateParticipantLocationIdList.size() >0l ){
			 query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		 }
        if(debateLocationIdList != null && debateLocationIdList.size()>0l){
      	 query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
        if(candidateIds != null && candidateIds.size()>0l){
        	query.setParameterList("candidateIds", candidateIds);
        }
		return query.list();
	}
   public List<Object[]> getDesignationWiseDebates(List<Long> partyIds,Date startDate,Date endDate,String state,String searchType,Long candidateId,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList,Long roleId,Long designationId,List<Long> candiadteIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select ");
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			//str.append(" '','',");
			str.append(" distinct model.candidate.candidateId,model.candidate.lastname, ");
		}else if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
			str.append(" distinct model.candidate.candidateId,model.candidate.lastname, ");
		}else{
			//str.append(" distinct model.candidate.candidateId,model.candidate.lastname, ");
		}
		
		str.append(" DS.debate.debateId,model.debate.startTime,model.debate.endTime," +
				" DOB.observer.observerId,DOB.observer.observerName,model.debate.channel.channelId,model.debate.channel.channelName, " +
				" state.stateId,state.stateName,model.partyId,model.party.shortName " +
				"  FROM DebateSubject DS" );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" ,DebateParticipantRole DPR ");
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" ,DebateCandidateDesignation DCD ");
		}
		str.append(" ,DebateObserver DOB,DebateParticipant model " +
				" left join model.debate.address address " +
				" left join address.state state " );
		/*if(debateLocationIdList != null && debateLocationIdList.size() > 0){
		      str.append(" , Debate model3 ");
		    }*/
		str.append(" WHERE model.debateId = DS.debate.debateId  " +
				" and model.debate.debateId = DOB.debate.debateId" +
				" and model.debate.isDeleted = 'N'  and model.candidate.isDebateCandidate = 'Y' " );
		if(roleId != null && roleId.longValue()>0L){
			str.append(" and model.debateParticipantId = DPR.debateParticipant.debateParticipantId  and DPR.debateRoles.debateRolesId =:roleId ");	
		}
		if(designationId != null && designationId.longValue()>0L){
			str.append(" and  model.candidateId = DCD.candidateId and DCD.debateRepresentativeTypeId =:designationId  and DCD.isActive='Y' and DCD.isDeleted='N' ");
		}else if(partyIds !=null && partyIds.size() > 0L && !partyIds.contains(0L)){
			str.append(" and model.partyId in (:partyIds) ");
		}else{
			str.append(" and model.partyId = 872 ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){		
		  str.append(" and model.candidate.isDebateCandidate = 'Y' ");
		}
		
		if(candidateId != null && candidateId.longValue() >0){
			str.append(" and model.candidate.candidateId =:candidateId " );
		}
		
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.debate.startTime)  between :startDate and :endDate  ");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
		if(debateLocationIdList != null  && debateLocationIdList.size() >0l ){
		      str.append(" and model.debate.addressId in (:debateLocationIdList) " );
		    }
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
		if(debateLocationIdList != null  && debateLocationIdList.size() >0l ){
			      str.append(" and model.debate.addressId in (:debateLocationIdList) " );
			    }
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model.candidate.state.stateId in (:debateParticipantLocationIdList) " );
		}
		}
		if(candiadteIds != null && candiadteIds.size()>0l){
			str.append(" and model.candidate.candidateId in(:candiadteIds)");
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("debate")){
			str.append(" group by DS.debate.debateId ");
		}else{
			str.append(" group by model.candidate.candidateId,model.debate.channel.channelId,DS.debate.debateId ");
		}
		//str.append(" group by DS.debate.debateId ");
		str.append(" order by model.debate.startTime desc ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(candidateId != null && candidateId.longValue() >0){
			query.setParameter("candidateId", candidateId);
		}
		if(debateLocationIdList != null  && debateLocationIdList.size() >0 ){
			query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim().equalsIgnoreCase("candidate")){
       if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0){
       	query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
		}
		}
		if(roleId != null && roleId.longValue()>0L){
			query.setParameter("roleId", roleId);
		}
		if(designationId != null && designationId.longValue()>0L){
			query.setParameter("designationId", designationId);
		}else if(partyIds !=null && partyIds.size()>0L && !partyIds.contains(0L)){
			query.setParameterList("partyIds", partyIds);
		}
		if(candiadteIds != null && candiadteIds.size()>0l){
			query.setParameterList("candiadteIds", candiadteIds);
		}
		return query.list();
	}
   public List<Object[]> getTotalDabtesCountsForPartyWiseCasteDetails(Date fromDate , Date toDate,String state,List<Long> debateLocationIdList)
	{
		StringBuilder str = new StringBuilder();		
		 str.append("select distinct model1.party.partyId,model1.party.shortName,count(distinct model1.debate.debateId)" +
		 		//", model1.candidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
		 		//" model1.candidate.casteState.casteCategoryGroup.casteCategory.categoryName  " +
				" from DebateParticipant model1  " );
				//" left join model1.candidate candidate " +
				//" left join candidate.casteState casteState left join casteState.casteCategoryGroup  casteCategoryGroup " +
				//" left join casteCategoryGroup.casteCategory casteCategory ");
		 str.append(" where  model1.debate.isDeleted = 'N' " +
				" and model1.party.isNewsPortal = 'Y' "  +
				" and  model1.candidate.isDebateCandidate ='Y' ");
		 
	 	if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		 
		if(fromDate !=null && toDate !=null){
			 str.append(" and date(model1.debate.startTime) >= :fromDate and date(model1.debate.endTime) <= :toDate ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model1.debate.addressId in(:debateLocationIdList)  ");
		}
		str.append(" group by model1.party.partyId" +
				//",model1.candidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId " +
				" order by model1.party.newsOrderNo ");		
		Query query = getSession().createQuery(str.toString());		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
       if(debateLocationIdList != null && debateLocationIdList.size()>0){
       	 query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
		return query.list();
	}
  
   public List<Object[]> getTotalDabteParticipantCountsForPartyWiseCasteDetails(Date fromDate , Date toDate,String state,List<Long> debateLocationIdList,List<Long> debateParticipantLocationIdList)
	{
		StringBuilder str = new StringBuilder();		
		 str.append("select distinct model1.party.partyId,model1.party.shortName,'',count(distinct candidate.candidateId), " +
		 		" casteCategory.casteCategoryId," +
		 		" casteCategory.categoryName from " +
				"  DebateParticipant model1 left join model1.candidate candidate " +
				" left join candidate.casteState casteState left join casteState.casteCategoryGroup  casteCategoryGroup " +
				" left join casteCategoryGroup.casteCategory casteCategory ");
		 str.append(" where   model1.debate.isDeleted = 'N' " +
				" and model1.party.isNewsPortal = 'Y'" +
				" and  candidate.isDebateCandidate ='Y' " );
		 
	 	if(state !=null && state.trim().equalsIgnoreCase("ap")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
		}
		 
		if(fromDate !=null && toDate !=null){
			 str.append(" and date(model1.debate.startTime) >= :fromDate and date(model1.debate.endTime) <= :toDate ");
		}
		if(debateLocationIdList != null && debateLocationIdList.size()>0){
			str.append(" and model1.debate.addressId in(:debateLocationIdList) ");
		}
		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and candidate.state.stateId in(:debateParticipantLocationIdList)  ");
		}
		str.append(" group by model1.party.partyId,casteCategory.casteCategoryId " +
				" order by model1.party.newsOrderNo ");		
		Query query = getSession().createQuery(str.toString());		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
      if(debateLocationIdList != null && debateLocationIdList.size()>0){
      	 query.setParameterList("debateLocationIdList", debateLocationIdList);
		}
      if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
    	  query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList); 
      }
		return query.list();
	}
   public List<Object[]> getTotalDabtesCountsForPartyWiseCasteDetailsClick(Date fromDate,Date toDate,String state,List<Long> debateLocationIdList,Long casteId,String type,Long partyId,List<Long> debateParticipantLocationIdList)
  	{
  		StringBuilder str = new StringBuilder();		
  		 str.append("select distinct candidate.candidateId,candidate.lastname, " +
  		 		  " DS.debate.debateId,model1.debate.startTime,model1.debate.endTime," +
			      " DOB.observer.observerId,DOB.observer.observerName,model1.debate.channel.channelId," +
			      " model1.debate.channel.channelName, " +
			      " model1.partyId,model1.party.shortName" );
  		//if(casteId != null && casteId.longValue()>0l)
  		   str.append(",casteCategory.casteCategoryId,casteCategory.categoryName ");
  		   if(!type.equalsIgnoreCase("others") && casteId.longValue()>0){
  			   str.append(",casteState.caste.casteId,casteState.caste.casteName ");
  		       }
  		 str.append(" from DebateSubject DS,DebateObserver DOB, " +
  				  "  DebateParticipant model1  left join model1.candidate candidate " );
  		 str.append(" left join candidate.casteState casteState left join casteState.casteCategoryGroup  casteCategoryGroup " +
  				 " left join casteCategoryGroup.casteCategory casteCategory ");
  		 str.append(" where  model1.debate.isDeleted = 'N' and model1.debateId = DS.debate.debateId  " +
  				" and model1.debate.debateId = DOB.debate.debateId " +
  				" and model1.party.isNewsPortal = 'Y' " +
  				" and  model1.candidate.isDebateCandidate ='Y' "); 
  		 
  	 	if(state !=null && state.trim().equalsIgnoreCase("ap")){
  			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_AP+") " );
  		}else if(state !=null && state.trim().equalsIgnoreCase("ts")){
  			str.append(" and model1.party.partyId not in ("+IConstants.CORE_DEBATE_ELIMINATED_PARTIES_TS+") " );
  		}
  		 
  		if(fromDate !=null && toDate !=null){
  			 str.append(" and date(model1.debate.startTime) >= :fromDate and date(model1.debate.endTime) <= :toDate ");
  		}
  		if(debateLocationIdList != null && debateLocationIdList.size()>0){
  			str.append(" and model1.debate.addressId in(:debateLocationIdList)  ");
  		}
  		if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
			str.append(" and model1.debate.address.state.stateId in(:debateParticipantLocationIdList)  ");
		}
  		if(casteId != null && casteId.longValue()>0l){
  			str.append(" and casteCategory.casteCategoryId =:casteId ");
  		}else if(casteId.longValue() == 0l && type.equalsIgnoreCase("others")){
  			str.append(" and casteCategory.casteCategoryId is null ");
  		}
  		if(partyId != null && partyId.longValue()>0l){
  			str.append(" and model1.party.partyId =:partyId ");
  		}
  		//str.append(" group by model1.party.partyId,casteCategory.casteCategoryId " +
  				//" order by model1.party.newsOrderNo ");		
  		Query query = getSession().createQuery(str.toString());		
  		if(fromDate !=null && toDate !=null){
  			query.setParameter("fromDate", fromDate);
  			query.setParameter("toDate", toDate);
  		}
  		
         if(debateLocationIdList != null && debateLocationIdList.size()>0){
         	 query.setParameterList("debateLocationIdList", debateLocationIdList);
  		}
         if(casteId != null && casteId.longValue()>0l){
        	 query.setParameter("casteId", casteId); 
         }
         if(partyId != null && partyId.longValue()>0l){
        	 query.setParameter("partyId", partyId); 
         }
         if(debateParticipantLocationIdList != null && debateParticipantLocationIdList.size()>0l){
        	 query.setParameterList("debateParticipantLocationIdList", debateParticipantLocationIdList);
         }
  		return query.list();
  	}
   public Object[] getCandidateGroupMatchedDetails(Date fromDate,Date toDate,Long candidateId,String type,Long categoryId,Long partyId)
 	{
 		StringBuilder str = new StringBuilder();		
 		 str.append("select distinct candidate.candidateId,candidate.lastname, " +
 		 		  " DS.debate.debateId,model1.debate.startTime,model1.debate.endTime," +
			      " DOB.observer.observerId,DOB.observer.observerName,model1.debate.channel.channelId," +
			      " model1.debate.channel.channelName, " +
			      " model1.partyId,model1.party.shortName" );
 		//if(casteId != null && casteId.longValue()>0l)
 		   str.append(",casteCategory.casteCategoryId,casteCategory.categoryName ");
 		   if(!type.equalsIgnoreCase("others")){
 			   str.append(",casteState.caste.casteId,casteState.caste.casteName ");
 		       }
 		 str.append(" from DebateSubject DS,DebateObserver DOB, " +
 				  "  DebateParticipant model1  left join model1.candidate candidate " );
 		 str.append(" left join candidate.casteState casteState left join casteState.casteCategoryGroup  casteCategoryGroup " +
 				 " left join casteCategoryGroup.casteCategory casteCategory ");
 		 str.append(" where  model1.debate.isDeleted = 'N' and model1.debateId = DS.debate.debateId  " +
 				" and model1.debate.debateId = DOB.debate.debateId " +
 				" and model1.party.isNewsPortal = 'Y' " +
 				" and  model1.candidate.isDebateCandidate ='Y' "); 
 		if(candidateId != null && candidateId.longValue()>0l){
 			str.append(" and candidate.candidateId =:candidateId ");
 		}
 		if(categoryId != null && categoryId.longValue()>0l){
 			str.append(" and casteCategory.casteCategoryId =:categoryId ");
 		}
 	 	
 		if(partyId != null && partyId.longValue()>0){
 			str.append(" and model1.partyId =:partyId ");
 		}
 		if(fromDate !=null && toDate !=null){
 			 str.append(" and date(model1.debate.startTime) >= :fromDate and date(model1.debate.endTime) <= :toDate ");
 		}
 		str.append(" group by candidate.candidateId ");
 		Query query = getSession().createQuery(str.toString());		
 		
 		if(candidateId != null && candidateId.longValue()>0l){
 			query.setParameter("candidateId", candidateId); 
 		}
 		if(categoryId != null && categoryId.longValue()>0l){
 			query.setParameter("categoryId", categoryId); 
 		}
 		if(partyId != null && partyId.longValue()>0){
 			query.setParameter("partyId", partyId); 
 		}
 		if(fromDate !=null && toDate !=null){
  			query.setParameter("fromDate", fromDate);
  			query.setParameter("toDate", toDate);
  		}
 		return  (Object[]) query.uniqueResult();
 	}
   
}
