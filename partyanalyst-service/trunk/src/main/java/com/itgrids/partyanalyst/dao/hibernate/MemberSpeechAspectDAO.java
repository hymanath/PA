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
				" count(model.adminHouseSessionDay.adminHouseSessionDayId)," +
				" model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId " +
				" from MemberSpeechAspect model " +
				" where model.isDeleted = 'N' ");
		if(termId != null && termId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSession.adminHouseTerm.adminHouseTermId = :termId ");
		}
		if(sesYear != null)
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSession.year = :sesYear");
		}
		if(sessionIds != null && sessionIds.size() > 0l)
		{
			sb.append(" and model.adminHouseSessionDay.adminHouseSession.houseSession.houseSessionId in (:sessionIds)");
		}
		if(startDate != null && endDate != null)
	    {
			sb.append(" and date(model.adminHouseSessionDay.adminHouseSession.fromDate)  between :startDate and :endDate " );
	    }
		sb.append(" group by model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId,model.adminHouseSessionDay.adminHouseSession.fromDate");
		
		Query query = getSession().createQuery(sb.toString());
			if(termId != null && termId.longValue() > 0l)
				query.setParameter("termId", termId);
			if(sesYear != null)
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
	    
	    str.append(" select count(distinct model.adminHouseMember.adminHouseMemberId),model.adminHouseMember.partyId," +
	        " model.adminHouseSessionDay.sessionDate,model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId," +
	        " model.adminHouseSessionDay.adminHouseSessionDayId " +
	        " from MemberSpeechAspect model " +
	        " where model.isDeleted = 'N' " );
	    
	    if(termId != null && termId.longValue() >0l)
	    {
	      str.append(" and model.adminHouseSessionDay.adminHouseSession.adminHouseTerm.adminHouseTermId = :termId ");
	    }
	    if(sesYear != null)
		{
			str.append(" and model.adminHouseSessionDay.adminHouseSession.year = :sesYear");
		}
	    if(sessionIds != null && sessionIds.size() >0l)
	    {
	      str.append(" and model.adminHouseSessionDay.adminHouseSession.houseSession.houseSessionId in (:sessionIds) ");
	    }
	    if(startDate != null && endDate != null)
	    {
	      str.append(" and date(model.adminHouseSessionDay.adminHouseSession.fromDate)  between :startDate and :endDate " );
	    }
	    
	    str.append(" group by model.adminHouseMember.partyId,model.adminHouseSessionDay.sessionDate,model.adminHouseSessionDay.adminHouseSession.adminHouseSessionId  " );
	    
	    Query query = getSession().createQuery(str.toString());
	    	if(termId != null && termId.longValue() >0l)
	    		query.setParameter("termId", termId);
	    	if(sesYear != null)
				query.setParameter("sesYear", sesYear);
	    	if(sessionIds != null && sessionIds.size() > 0l)
				query.setParameterList("sessionIds", sessionIds);
	    	 if(startDate != null && endDate != null){
	    		 query.setDate("startDate", startDate);
				 query.setDate("endDate", endDate);
	    	 }
	    return query.list();
	  }
	
	public List<Object[]> getDayWiseCountDetails(Long admHsSessDayId){
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
		
		Query query = getSession().createQuery(sb.toString());
		if(admHsSessDayId != null && admHsSessDayId.longValue() > 0l)
			query.setParameter("admHsSessDayId", admHsSessDayId);
		
		return query.list();
		
	}

}
