package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IApprovalDetailsDAO;
import com.itgrids.partyanalyst.model.ApprovalDetails;


public class ApprovalDetailsDAO extends GenericDaoHibernate<ApprovalDetails, Long> implements IApprovalDetailsDAO {

	public ApprovalDetailsDAO( ) {
		super(ApprovalDetails.class);
		
	}

}
