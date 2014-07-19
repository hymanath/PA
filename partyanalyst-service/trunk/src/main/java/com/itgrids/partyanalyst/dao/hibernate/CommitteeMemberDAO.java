package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommitteeMemberDAO;
import com.itgrids.partyanalyst.model.CommitteeMember;


public class CommitteeMemberDAO extends GenericDaoHibernate<CommitteeMember, Long> implements ICommitteeMemberDAO{

	public CommitteeMemberDAO() {
		super(CommitteeMember.class);
		
	}

	
	public List<Object[]> getCommitteeCadreInfo(Long committeeId)
	{
		
		Query query = getSession().createQuery("select model.cadre.cadreId,model.cadre.firstName,model.cadre.lastName,model.age,model.cadre.mobile" +
				" model.cadre.fatherOrSpouseName,model.casteState.caste.casteId,model.casteState.caste.casteName,model.committeeRole.roles.roleId,model.committeeRole.roles.role from CommitteeMember model" +
				" where model.committeeRole.committee.committeeId = :committeeId and model.isActive = 'Y'");
		query.setParameter("committeeId", committeeId);
		return query.list();
		
		
	}
}