package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ApprovalDetails;


public interface IApprovalDetailsDAO extends GenericDao<ApprovalDetails, Long> {

	public  Integer updateSetIsApprovedStatusToPostedProblems(List<Long> approvalDetailsIds, String isApproved);

	
	
}
