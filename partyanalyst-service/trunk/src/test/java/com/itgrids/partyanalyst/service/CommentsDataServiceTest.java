package com.itgrids.partyanalyst.service;

import org.appfuse.dao.BaseDaoTestCase;

public class CommentsDataServiceTest extends BaseDaoTestCase{

	private ICommentsDataService commentsDataService;

	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}

	public void setCommentsDataService(ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}
	
	public void testCategoryData(){
		commentsDataService.getAnalyzedResonsWithRatingsForConstituencyInAnElection(false, 1098l);
	}
	
	
}
