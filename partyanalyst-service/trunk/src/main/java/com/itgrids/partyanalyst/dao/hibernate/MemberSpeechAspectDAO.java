package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMemberSpeechAspectDAO;
import com.itgrids.partyanalyst.model.MemberSpeechAspect;

public class MemberSpeechAspectDAO extends GenericDaoHibernate<MemberSpeechAspect, Long> implements IMemberSpeechAspectDAO{

	public MemberSpeechAspectDAO() {
		super(MemberSpeechAspect.class);
		
	}
	public List<Object[]> getNoOfDaysForSession(Long termId,String sesYear,List<Long> sessionIds,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
			sb.append("select date(model.adminHouseSessionDay.adminHouseSession.fromDate)," +
				" model.adminHouseSessionDay.adminHouseSession.houseSession.houseSessionId, " +
				" model.adminHouseSessionDay.adminHouseSession.houseSession.sessionName, " +
				" count(distinct model.adminHouseSessionDay.adminHouseSessionDayId)," +
				" model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId " +
				" from MemberSpeechAspect model " +
				" where model.isDeleted = 'N' ");
		if(termId != null && termId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSession.adminHouseTerm.adminHouseTermId = :termId ");
		}
		if(sesYear != null && !sesYear.toString().isEmpty())
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSession.year = :sesYear");
		}
		if(sessionIds != null && sessionIds.size() > 0l)
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId in (:sessionIds)");
		}
		if(startDate != null && endDate != null)
	    {
			sb.append(" and date(model.adminHouseSessionDay.sessionDate)  between :startDate and :endDate  " );
	    }
		sb.append(" group by model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId,model.adminHouseSessionDay.adminHouseSession.fromDate");
		
		Query query = getSession().createQuery(sb.toString());
			if(termId != null && termId.longValue() > 0l)
				query.setParameter("termId", termId);
			if(sesYear != null && !sesYear.toString().isEmpty())
				query.setParameter("sesYear", sesYear);
			if(sessionIds != null && sessionIds.size() > 0l)
				query.setParameterList("sessionIds", sessionIds);
			if(startDate != null && endDate != null){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
			}
		return query.list();
	}
	
	public List<Object[]> getDayWisePartyWiseCount(Long termId,String sesYear,List<Long> sessionIds,Date startDate,Date endDate){
	    
	    StringBuilder str = new StringBuilder();
	    
	    str.append(" select count(distinct model.adminHouseMember.adminHouseMemberId),model.adminHouseMember.party.partyId," +
	        " date(model.adminHouseSessionDay.sessionDate),model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId,model.adminHouseSessionDay.adminHouseSessionDayId " +
	        ",model.adminHouseMember.party.shortName from MemberSpeechAspect model " +
	        " where model.isDeleted = 'N'  and model.adminHouseSessionDay.isDeleted = 'N' " );
	    
	    if(termId != null && termId.longValue() >0l)
	    {
	      str.append(" and model.adminHouseSessionDay.adminHouseSession.adminHouseTerm.adminHouseTermId = :termId ");
	    }
	    if(sesYear != null && !sesYear.toString().isEmpty())
		{
			str.append(" and model.adminHouseSessionDay.adminHouseSession.year = :sesYear");
		}
	    if(sessionIds != null && sessionIds.size() >0l)
	    {
	      str.append(" and model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId in (:sessionIds) ");
	    }
	    if(startDate != null && endDate != null)
	    {
	      str.append(" and date(model.adminHouseSessionDay.sessionDate)  between :startDate and :endDate " );
	    }
	    
	    str.append(" group by model.adminHouseMember.partyId,model.adminHouseSessionDay.sessionDate,model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId,model.adminHouseSessionDay.adminHouseSessionDayId  " );
	    
	    Query query = getSession().createQuery(str.toString());
	    	if(termId != null && termId.longValue() >0l)
	    		query.setParameter("termId", termId);
	    	if(sesYear != null && !sesYear.toString().isEmpty())
				query.setParameter("sesYear", sesYear);
	    	if(sessionIds != null && sessionIds.size() > 0l)
				query.setParameterList("sessionIds", sessionIds);
	    	 if(startDate != null && endDate != null){
	    		 query.setDate("startDate", startDate);
				 query.setDate("endDate", endDate);
	    	 }
	    return query.list();
	  }
	
	public List<Object[]> getDayWiseCountDetails(Long admHsSessDayId,Long partyId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.adminHouseMember.party.partyId,model.adminHouseMember.party.shortName," +
				" model.adminHouseMember.memberName,model.adminHouseMember.candidate.candidateId," +
				" model.adminHouseMember.adminHouseMemberId,model.speechAspect.speechAspectId," +
				" model.score " +
				" from MemberSpeechAspect model " +
				" where model.isDeleted = 'N' and model.adminHouseMember.isDeleted = 'N' ");
		if(admHsSessDayId != null && admHsSessDayId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSessionDayId = :admHsSessDayId");
		}
		if(partyId != null && partyId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseMember.party.partyId = :partyId");
		}
		Query query = getSession().createQuery(sb.toString());
		if(admHsSessDayId != null && admHsSessDayId.longValue() > 0l)
			query.setParameter("admHsSessDayId", admHsSessDayId);
		if(partyId != null && partyId.longValue() > 0l)
			query.setParameter("partyId", partyId);
		
		return query.list();
		
	}
	
	public List<MemberSpeechAspect> updateMemberDetails(Long adminHouseMemberId,Long adminHouseSessionDayId,Long speechAspectId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model" +
					" from MemberSpeechAspect model " +
					" where model.isDeleted = 'N' and model.adminHouseMember.isDeleted = 'N' ");
		if(adminHouseMemberId != null && adminHouseMemberId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseMember.adminHouseMemberId = :adminHouseMemberId");	
		}
		if(adminHouseSessionDayId != null && adminHouseSessionDayId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSessionDayId = :adminHouseSessionDayId");
		}
		if(speechAspectId != null && speechAspectId.longValue() > 0l)
		{
			sb.append(" and model.speechAspect.speechAspectId = :speechAspectId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(adminHouseMemberId != null && adminHouseMemberId.longValue() > 0l)
			query.setParameter("adminHouseMemberId", adminHouseMemberId);
		if(adminHouseSessionDayId != null && adminHouseSessionDayId.longValue() > 0l)
			query.setParameter("adminHouseSessionDayId", adminHouseSessionDayId);
		if(speechAspectId != null && speechAspectId.longValue() > 0l)
			query.setParameter("speechAspectId", speechAspectId);
		
		return  query.list();
	}
	
	public MemberSpeechAspect getPrimaryKey(Long adminHouseSessionId,Long memberId,Long speechAspectId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model from MemberSpeechAspect model where model.isDeleted = 'N' ");
		
		if(adminHouseSessionId != null && adminHouseSessionId.longValue() > 0l){
			sb.append(" and model.adminHouseSessionDay.adminHouseSessionDayId = :adminHouseSessionDay ");
		}
		if(memberId != null && memberId.longValue() > 0l){
			sb.append(" and model.adminHouseMember.adminHouseMemberId = :memberId ");
		}
		if(speechAspectId != null && speechAspectId.longValue() > 0l){
			sb.append(" and model.speechAspect.speechAspectId = :speechAspectId ");
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(adminHouseSessionId != null && adminHouseSessionId.longValue() > 0l){
			query.setParameter("adminHouseSessionDay", adminHouseSessionId);
		}
		if(memberId != null && memberId.longValue() > 0l){
			query.setParameter("memberId", memberId);
		}
		
		if(speechAspectId != null && speechAspectId.longValue() > 0l){
			query.setParameter("speechAspectId", speechAspectId);
		}
		 return (MemberSpeechAspect)query.uniqueResult();
	}

}
