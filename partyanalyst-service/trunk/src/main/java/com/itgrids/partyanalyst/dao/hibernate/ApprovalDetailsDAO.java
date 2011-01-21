package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IApprovalDetailsDAO;
import com.itgrids.partyanalyst.model.ApprovalDetails;


public class ApprovalDetailsDAO extends GenericDaoHibernate<ApprovalDetails, Long> implements IApprovalDetailsDAO {

	public ApprovalDetailsDAO( ) {
		super(ApprovalDetails.class);
		
	}

	public  Integer updateSetIsApprovedStatusToPostedProblems(List<Long> approvalDetailsIds, String isApproved)
	{
		Query queryObject = getSession().createQuery("update ApprovalDetails model set model.isAdminApproved = ? where model.approvalDetailsId in (:approvalDetailsIds)");
		queryObject.setParameter(0, isApproved);
		queryObject.setParameterList("approvalDetailsIds", approvalDetailsIds);
		return queryObject.executeUpdate();
			
	}

}
