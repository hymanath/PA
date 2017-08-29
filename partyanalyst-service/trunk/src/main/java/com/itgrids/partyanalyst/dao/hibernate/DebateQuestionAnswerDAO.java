package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateQuestionAnswerDAO;
import com.itgrids.partyanalyst.model.DebateQuestionAnswer;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateQuestionAnswerDAO extends GenericDaoHibernate<DebateQuestionAnswer, Long> implements IDebateQuestionAnswerDAO{

	public DebateQuestionAnswerDAO() {
		super(DebateQuestionAnswer.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateQuestionAndAnswerDetails(Long debateId)
	{
		/*return getHibernateTemplate().find("select model.debateQuestions.question , model.answer ,model.debateQuestions.debateQuestionsId" +
				" from DebateQuestionAnswer model where model.debate.debateId = ? ",debateId);*/
		StringBuilder sb = new StringBuilder();
		sb.append("select model.debateQuestions.question , model.answer ,model.debateQuestions.debateQuestionsId" +
				" from DebateQuestionAnswer model " );
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }*/
			sb.append(" where " );
		if(debateId != null && debateId.longValue()>0)		
		 sb.append(" model.debate.debateId =:debateId   ");
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" and model.debate.debateId = model3.debateParticipant.debateId  and model3.isDeleted = 'N' ");
		    }
		 if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 1L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_AP_STATE_ID);
		    }else if(stateId != null && stateId.longValue() > 0 && stateId.longValue() == 36L){
		      sb.append(" and model3.address.state.stateId="+IConstants.DEBATE_TS_STATE_ID);
		    }*/
		Query query=getSession().createQuery(sb.toString());
		if(debateId != null && debateId.longValue()>0)
			query.setParameter("debateId", debateId);
		return query.list();
	}
}
