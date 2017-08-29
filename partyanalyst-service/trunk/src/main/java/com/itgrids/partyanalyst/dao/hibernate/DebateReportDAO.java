package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateReportDAO;
import com.itgrids.partyanalyst.model.DebateReport;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateReportDAO extends GenericDaoHibernate<DebateReport, Long> implements IDebateReportDAO{

	public DebateReportDAO() {
		super(DebateReport.class);
		// TODO Auto-generated constructor stub
	}

	public Long checkValidUserForReport(Long reportId,Long userId)
	{
		Query query = getSession().createQuery("select count(model.debateReportId) from DebateReport model " +
				" where model.debateReportId = :reportId and model.userId = :userId");
		query.setParameter("reportId", reportId);
		query.setParameter("userId", userId);
		return (Long)query.uniqueResult();
	}
	
	public int deleteDebateReport(String key)
	{
		Query query = getSession().createQuery("delete from DebateReport model where model.key = :key");
		query.setParameter("key", key);
		int i = query.executeUpdate();
		
		return i;
	}
	
	public String getDebateDatils(Long userId,Long debateId,Long stateId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.key from DebateReport model " );
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }
		sb.append(" where ");
		if(debateId != null && debateId.longValue()>0)
			sb.append("  model.debate.debateId =:debateId ");
		if(userId != null && userId.longValue()>0)
			sb.append(" and model.user.userId =:userId ");
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model.debate.debateId = model3.debateParticipant.debateId and model3.isDeleted = 'N' ");
		    }
		    if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId ="+ IConstants.DEBATE_AP_STATE_ID );
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId ="+ IConstants.DEBATE_TS_STATE_ID );
		    }
		Query query = getSession().createQuery(sb.toString());
		if(userId != null && userId.longValue()>0)
		  query.setParameter("userId", userId);
		if(debateId != null && debateId.longValue()>0)
		  query.setParameter("debateId", debateId);
		
		return (String)query.uniqueResult();
	}

}
