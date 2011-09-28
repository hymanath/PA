package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.model.FileType;

public class FileTypeDAOHibernateTest extends BaseDaoTestCase{
	private IFileTypeDAO fileTypeDAO;



public void setFileTypeDAO(IFileTypeDAO fileTypeDAO) {
	this.fileTypeDAO = fileTypeDAO;
}

/*public void test()
{
	fileTypeDAO.getAll();
	
}*/

public void testGetFileType(){
	
	List<FileType> result=fileTypeDAO.getFileType("TXT");
	System.out.println(result.size());
}
}
