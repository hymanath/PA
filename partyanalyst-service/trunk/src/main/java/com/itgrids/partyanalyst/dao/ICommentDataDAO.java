/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommentData;

public interface ICommentDataDAO extends GenericDao<CommentData, Long> {
	
	public Integer updateSetIsApprovedStatusToPostedComments(List<Long> reasonIds, String isApproved);
}
