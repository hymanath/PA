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

import com.itgrids.partyanalyst.model.CommentCategoryConstituency;

public interface ICommentCategoryConstituencyDAO extends GenericDao<CommentCategoryConstituency, Long> {

	public List<CommentCategoryConstituency> getCommentsOnAConstituencyInAElection(String electionYear,Long constituencyId);
	
	public List<CommentCategoryConstituency> getCommentsOnAConstituencyInAElection(Long electionId,Long constituencyId);
	
	public List<CommentCategoryConstituency> getCommentsOnAConstituencyInAllElections(Long constituencyId);
}
