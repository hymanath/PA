package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantRoleDAO;
import com.itgrids.partyanalyst.model.DebateParticipantRole;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateParticipantRoleDAO extends GenericDaoHibernate<DebateParticipantRole, Long> implements IDebateParticipantRoleDAO{

	public DebateParticipantRoleDAO() {
		super(DebateParticipantRole.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getParticepentRoles(Long debateId)
	{
		/*return getHibernateTemplate().find("select model.debateParticipant.candidate.candidateId , model.debateParticipant.candidate.lastname, " +
				" model.debateParticipant.party.partyId , model.debateParticipant.party.shortName , " +
				" model.debateRoles.name,model.debateRoles.debateRolesId  from DebateParticipantRole model where model.debateParticipant.debate.debateId = ? ",debateId);*/
		StringBuilder sb= new StringBuilder();
		sb.append(" select model.debateParticipant.candidate.candidateId , model.debateParticipant.candidate.lastname, " +
				" model.debateParticipant.party.partyId , model.debateParticipant.party.shortName , " +
				" model.debateRoles.name,model.debateRoles.debateRolesId  from DebateParticipantRole model " );
		/*if(stateId != null && stateId.longValue() > 0){
		      sb.append(" , DebateParticipantLocation model3 ");
		    }*/
			  sb.append(" where ");
		if(debateId != null && debateId.longValue()>0)
			sb.append(" model.debateParticipant.debate.debateId =:debateId  ");
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
	public List<DebateParticipantRole> getDebateParticipantRoleDetails(){
		Query query = getSession().createQuery("select model from DebateParticipantRole model");
		 
		return query.list();
	 }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateParticipantRoleDetailsNew(){
		Query query = getSession().createQuery("select model.debateParticipantRoleId,model.debateRoles.name from DebateParticipantRole model");
		 
		return query.list();
	 }
}
