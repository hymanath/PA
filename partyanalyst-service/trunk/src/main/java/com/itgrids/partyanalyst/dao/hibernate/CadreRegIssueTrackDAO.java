package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegIssueTrackDAO;
import com.itgrids.partyanalyst.model.CadreRegIssueTrack;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreRegIssueTrackDAO extends GenericDaoHibernate<CadreRegIssueTrack, Long> implements ICadreRegIssueTrackDAO {

	public CadreRegIssueTrackDAO() {
		super(CadreRegIssueTrack.class);
	
	}
	
	
	public List<Object[]> trackingRegIssueByRegIssueId(Long cadreRegIssueId,Long stateId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select  model.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegIssueType.issueType,model.description," +
		"         model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status," +
		"         model.insertedUser.user.userId,model.insertedUser.user.firstName,model.insertedUser.user.lastName,model.insertedTime," +
		"			model.insertedUser.user.userName " +
		" from    CadreRegIssueTrack model " +
		" where   model.cadreRegIssueId = :cadreRegIssueId ");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and  model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and  model.userAddress.state.stateId = 1 ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("cadreRegIssueId",cadreRegIssueId);
		return query.list();
	}
	
	
}
