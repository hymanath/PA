package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IApprovalDetailsDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ApprovalDetailsDAOHibernateTest extends BaseDaoTestCase{
	private IApprovalDetailsDAO approvalDetailsDAO;
	
	public IApprovalDetailsDAO getApprovalDetailsDAO() {
		return approvalDetailsDAO;
	}
    public void setApprovalDetailsDAO(IApprovalDetailsDAO approvalDetailsDAO) {
		this.approvalDetailsDAO = approvalDetailsDAO;
	}

    @SuppressWarnings("unchecked")
	public void testGetCountOfNewlyPostedCommentsByUser()
	{
		System.out.println(approvalDetailsDAO.getCountOfNewlyPostedCommentsByUser());
	}

}
