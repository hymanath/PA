package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegIssueTrackDAO;
import com.itgrids.partyanalyst.model.CadreRegIssueTrack;

public class CadreRegIssueTrackDAO extends GenericDaoHibernate<CadreRegIssueTrack, Long> implements ICadreRegIssueTrackDAO {

	public CadreRegIssueTrackDAO() {
		super(CadreRegIssueTrack.class);
	
	}
	
	
	public List<Object[]> trackingRegIssueByRegIssueId(Long cadreRegIssueId){
		
		Query query = getSession().createQuery("" +
		" select  model.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegIssueType.issueType,model.description," +
		"         model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status," +
		"         model.insertedUser.user.userId,model.insertedUser.user.firstName,model.insertedUser.user.lastName,model.insertedTime " +
		" from    CadreRegIssueTrack model " +
		" where   model.cadreRegIssueId = :cadreRegIssueId ");
		query.setParameter("cadreRegIssueId",cadreRegIssueId);
		return query.list();
	}
	
	
}
