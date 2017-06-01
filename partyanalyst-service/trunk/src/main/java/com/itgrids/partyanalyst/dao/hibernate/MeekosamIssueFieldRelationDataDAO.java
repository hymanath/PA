package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldRelationDataDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueFieldRelationData;

public class MeekosamIssueFieldRelationDataDAO extends GenericDaoHibernate<MeekosamIssueFieldRelationData, Long> implements	IMeekosamIssueFieldRelationDataDAO {
	public MeekosamIssueFieldRelationDataDAO() {
		super(MeekosamIssueFieldRelationData.class);
	}
	
	public List<Object[]> getAllDataForRelationIds(List<Long> relationIds){
		Query query = getSession().createQuery("select distinct model.meekosamIssueFieldRelation.meekosamIssueFieldRelationId," +
											" model.meekosamIssueFieldData.meekosamIssueFieldDataId," +
											" model.meekosamIssueFieldData.data" +
											" from MeekosamIssueFieldRelationData model" +
											" where model.meekosamIssueFieldRelation.meekosamIssueFieldRelationId in (:relationIds)" +
											" and model.isDeleted = 'N'");
		query.setParameterList("relationIds", relationIds);
		return query.list();
	}
}
