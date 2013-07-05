package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateNewsResponseDAO;

public class CandidateNewsResponseDAOHibernateTest extends BaseDaoTestCase{
	
	ICandidateNewsResponseDAO candidateNewsResponseDAO;

	
	public void setCandidateNewsResponseDAO(
			ICandidateNewsResponseDAO candidateNewsResponseDAO) {
		this.candidateNewsResponseDAO = candidateNewsResponseDAO;
	}


	public void testGetFileGallaryIdsByResponseGallaryId(){
		List<Long> fileGallaryIds = candidateNewsResponseDAO.getFileGallaryIdsByResponseGallaryId(23l);
		
		System.out.println(fileGallaryIds.size());
		
	}

}
