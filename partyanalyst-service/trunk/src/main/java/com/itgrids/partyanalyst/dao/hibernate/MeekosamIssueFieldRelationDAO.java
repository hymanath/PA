package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldRelationDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueFieldRelation;

public class MeekosamIssueFieldRelationDAO extends GenericDaoHibernate<MeekosamIssueFieldRelation, Long> implements	IMeekosamIssueFieldRelationDAO {
	public MeekosamIssueFieldRelationDAO() {
		super(MeekosamIssueFieldRelation.class);
	}
	
	public List<Object[]> getDynamicFieldsForIssueType(Long issueTypeId){
		Query query = getSession().createQuery("select distinct model.meekosamIssueFieldRelationId," +
											" model.meekosamIssueType.meekosamIssueTypeId," +
											" model.meekosamIssueType.issueType," +
											" model.meekosamIssueField.meekosamIssueFieldId," +
											" model.meekosamIssueField.field," +
											" model.meekosamIssueFieldType.meekosamIssueFieldTypeId," +
											" model.meekosamIssueFieldType.type" +
											" from MeekosamIssueFieldRelation model" +
											" where model.meekosamIssueType.meekosamIssueTypeId = :issueTypeId" +
											" and model.meekosamIssueType.isActive = 'Y'" +
											" and model.isDeleted = 'N'" +
											" order by model.meekosamIssueField.meekosamIssueFieldId");
		query.setParameter("issueTypeId", issueTypeId);
		return query.list();
	}
}
