package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateParticipantExceptedRoleDAO;
import com.itgrids.partyanalyst.model.DebateParticipantExpectedRole;
import com.itgrids.partyanalyst.utils.IConstants;
import org.hibernate.Query;

public class DebateParticipantExceptedRoleDAO extends GenericDaoHibernate<DebateParticipantExpectedRole, Long> implements IDebateParticipantExceptedRoleDAO{

	public DebateParticipantExceptedRoleDAO() {
		super(DebateParticipantExpectedRole.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPaticepentExpRoles(Long debateId)
	{
		/*return getHibernateTemplate().find("select model.debateRoles.name,model.debateParticipant.candidate.firstname ,model.debateParticipant.candidate.candidateId" +
				"  ,model.debateRoles.debateRolesId  from DebateParticipantExpectedRole model " +
				" where model.debateParticipant.debate.debateId = ? ",debateId);*/
		StringBuilder sb =new StringBuilder();
		sb.append("select model.debateRoles.name,model.debateParticipant.candidate.firstname ,model.debateParticipant.candidate.candidateId" +
				"  ,model.debateRoles.debateRolesId  from DebateParticipantExpectedRole model " );
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }*/
		sb.append(" where " );
		if(debateId != null && debateId.longValue()>0)	
			sb.append("model.debateParticipant.debate.debateId =:debateId ");
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

	
}
