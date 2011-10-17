package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.utils.IConstants;

public class FileGallaryDAOHibernateTest extends BaseDaoTestCase{

	private IFileGallaryDAO fileGallaryDAO;

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}
	
	/*public void test()
	{
		fileGallaryDAO.getAll();
	}*/
	/*public void testGetAllRecordInGallary()
	{
		List<Object[]> results = fileGallaryDAO.getAllRecordInGallary(300L);
		System.out.println(results.size());
	}*/
	public void testGetNewsRecordsBySearchCriteria()
	{
		FileVO  fileVO = new FileVO();
		fileVO.setCandidateId(13722L);
		fileVO.setKeywords("news");
		List<Object[]> results = fileGallaryDAO.getNewsRecordsBySearchCriteria(fileVO,IConstants.NEWS_GALLARY);
		System.out.println(results.size());
	}
	
}
