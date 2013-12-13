package com.itgrids.partyanalyst.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;


import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.INewsResponseDAO;
import com.itgrids.partyanalyst.dao.IReportFilesDAO;

public class ReportFilesDAOHibernateTest  extends BaseDaoTestCase {
	private IReportFilesDAO reportFilesDAO;
	private INewsResponseDAO newsResponseDAO;
	private IFileSourceLanguageDAO fileSourceLanguageDAO;
	
	public void setFileSourceLanguageDAO(
			IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}

	public void setReportFilesDAO(IReportFilesDAO reportFilesDAO) {
		this.reportFilesDAO = reportFilesDAO;
	}
	
	/*public void testGetData(){
		reportFilesDAO.getOtherLvlReportDetails(1l,1l);
	}*/
	
	public void setNewsResponseDAO(INewsResponseDAO newsResponseDAO) {
		this.newsResponseDAO = newsResponseDAO;
	}

	/*public void testgetOtherLvlReportDetails(){
		List<Object[]> val = reportFilesDAO.getOtherLvlReportDetails(20l,1l);
		System.out.println(val.size());
	}*/
	
	/*public void testgetLatestNewsResponses(){
		List<Object[]> val = newsResponseDAO.getLatestNewsResponses();
		for(Object[] param:val){
			System.out.println(param[0]);
		}
	}*/
	
	/*public void testgetCandidateNewsResponseFileIdsByFileID(){
		List<Long> val = newsResponseDAO.getCandidateNewsResponseFileIdsByFileID(71l);
		System.out.println(val.size());
		
	}*/
	
	public void testgetCandidateDetailsByFileIds(){
		Set<Long> ids = new HashSet<Long>();
		//ids.add(55l);
		ids.add(79l);
		List<Object[]> val = fileSourceLanguageDAO.getCandidateDetailsByFileIds(ids);
		System.out.println(val.size());
	}
}
