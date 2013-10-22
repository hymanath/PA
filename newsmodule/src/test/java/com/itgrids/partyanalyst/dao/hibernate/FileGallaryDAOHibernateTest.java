package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.model.FileGallary;

public class FileGallaryDAOHibernateTest extends BaseDaoTestCase{

	private IFileGallaryDAO fileGallaryDAO;


	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}
	
	/*public void test()
	{
		fileGallaryDAO.getAll();
	}*/

	public void testgetFileGallaryByGalleryIdsList()
	{
		List<Long> gallaryIdsList = new ArrayList<Long>(0);
		gallaryIdsList.add(27l);
		
	  List<FileGallary> list = fileGallaryDAO.getFileGallaryByGalleryIdsList(gallaryIdsList, null, null, "", 0L, null, null, "");
	  System.out.println(list.size());
	  
	  
	}
	
}
