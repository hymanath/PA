package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamIssueTypeDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueType;

public class MeekosamIssueTypeDAO extends GenericDaoHibernate<MeekosamIssueType, Long> implements IMeekosamIssueTypeDAO {
	
	public MeekosamIssueTypeDAO(){
		super(MeekosamIssueType.class);
	}
	
	public List<Object[]> getMeekosamSubIssueTypeListForParentIssueType(Long parentIssueTypeId){
		Query query = getSession().createQuery("select distinct model.meekosamIssueTypeId," +
											" model.issueType" +
											" from MeekosamIssueType model" +
											" where model.isActive = 'Y'" +
											" and model.parentMeekosamIssueTypeId = :parentIssueTypeId");
		query.setParameter("parentIssueTypeId", parentIssueTypeId);
		return query.list();
	}
	
	public Long getParentIssueType(Long issueTypeId){
		Query query = getSession().createQuery("select distinct model.parentMeekosamIssueTypeId" +
											" from MeekosamIssueType model" +
											" where model.isActive = 'Y'" +
											" and model.meekosamIssueTypeId = :issueTypeId");
		query.setParameter("issueTypeId", issueTypeId);
		return (Long) query.uniqueResult();
	}
}
