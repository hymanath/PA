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

	
	/*public List<Object[]> getCommitteeCadreInfo(Long committeeId)
	{
		
		Query query = getSession().createQuery("select model.cadre.cadreId,model.cadre.firstName,model.cadre.lastName,model.cadre.age,model.cadre.mobile," +
				" model.cadre.fatherOrSpouseName,model.cadre.casteState.caste.casteId,model.cadre.casteState.caste.casteName,model.committeeRole.roles.roleId,model.committeeRole.roles.role from CommitteeMember model" +
				" where model.committeeRole.committee.committeeId = :committeeId and model.isActive = 'Y'");
		query.setParameter("committeeId", committeeId);
		return query.list();
		
		
	}*/
	
	public List<Object[]> getCommitteeCadreInfo(Long committeeId,Long commiteLevelId)
	{
		
		Query query = getSession().createQuery("select distinct model.cadre," +
				"model.committeeRole.roles.roleId,model.committeeRole.roles.role from CommitteeMember model" +
				" where model.committeeRole.committee.committeeId = :committeeId and model.isActive = 'Y' and model.committeeRole.committee.committeeLevel.commiiteeLevelId = :commiteLevelId");
		query.setParameter("committeeId", committeeId);
		query.setParameter("commiteLevelId", commiteLevelId);
		
		return query.list();
		
		
	}
}