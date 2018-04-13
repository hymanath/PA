package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateSmsQuestionOptionDAO;
import com.itgrids.partyanalyst.model.DebateSmsQuestionOption;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateSmsQuestionOptionDAO extends GenericDaoHibernate<DebateSmsQuestionOption, Long> implements IDebateSmsQuestionOptionDAO{

	public DebateSmsQuestionOptionDAO() {
		super(DebateSmsQuestionOption.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateSmsQuestionsForSelectedDebate(Long debateId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select model.debateSmsQuestionOptionId , model.option,model.percantage , " +
				" model.debateSmsQuestion.debateSmsQuestionId,model.debateSmsQuestion.question from DebateSmsQuestionOption model " );
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }*/
		sb.append(" where " );
		if(debateId != null && debateId.longValue()>0)
		  sb.append(" model.debateSmsQuestion.debate.debateId = :debateId  ");
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model.debateSmsQuestion.debate.debateId = model3.debateParticipant.debateId  and model3.isDeleted = 'N' ");
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
	public List<Object[]> getSmsQuestionDetails(Date fromDate,Date toDate,Long stateId)
	{
		StringBuilder sb =new StringBuilder();
		sb.append("select model.debateSmsQuestion.debateSmsQuestionId,model.debateSmsQuestion.question ," +
				" model.option , model.percantage,model.debateSmsQuestion.debate.channel.channelName, model.debateSmsQuestion.debate.startTime  " +
				" from DebateSmsQuestionOption model " );
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , Debate model3 ");
		    }
		sb.append(" where model.debateSmsQuestion.debate.isDeleted = 'N' ");
		
		if(fromDate != null)
			sb.append(" and date(model.debateSmsQuestion.debate.startTime) >= :fromDate "); 
		if(toDate != null)
			sb.append(" and date(model.debateSmsQuestion.debate.endTime) <= :toDate " );
		if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model.debateSmsQuestion.debate.debateId = model3.debateId  and model3.isDeleted = 'N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 2L){
		    	sb.append(" and model3.address.state.stateId is null ");
		    }
		sb.append(" order by model.debateSmsQuestion.debateSmsQuestionId desc ");
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null)
		  query.setParameter("fromDate", fromDate);
		if(toDate != null)
		  query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	public List<Object[]> getSmsQuestionOptionDetails(Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		//0-debateId,1-debateSummary,2-questionId,3-question,4-option,5-percentage
		sb.append(" select model.debateSmsQuestion.debate.debateId,model.debateSmsQuestion.debate.summary,model.debateSmsQuestion.debateSmsQuestionId," +
				" model.debateSmsQuestion.question,model.option,model.percantage " +
				" from DebateSmsQuestionOption model " +
				" where model.debateSmsQuestion.isDeleted='N' and model.debateSmsQuestion.debate.isDeleted='N' ");
		if(startDate != null && endDate != null){
			sb.append(" and date(model.debateSmsQuestion.debate.updatedTime) between :startDate and :endDate ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
		
	}
}
