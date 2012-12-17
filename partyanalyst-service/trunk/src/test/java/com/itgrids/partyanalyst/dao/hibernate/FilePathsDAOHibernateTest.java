package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;

public class FilePathsDAOHibernateTest extends BaseDaoTestCase {
   
	private IFilePathsDAO filePathsDAO;

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}
	
	/*public void test(){
		
		filePathsDAO.getAll();
	 }*/
	/*public void testGetMaxOrderNo()
	{
		List<Object> list = filePathsDAO.getMaxOrderNo();
		System.out.println(list.size());
	}*/
	
	/*public void testGetProblemFiles()
	{
		List<Object[]> list = filePathsDAO.getProblemFiles(1l,null,IConstants.PUBLIC);
		System.out.println(list.size());
	}*/
	
	/*public void testGetFilePaths()
	{
		System.out.println(filePathsDAO.getFilePaths(15343l));
	}*/
	public void testGetFilePathsBasedOnFileTypeIds()
	{long[] ids= {1,2,5,10,11,24,25,35};
	List<Long> filetypeIds =new ArrayList<Long>();
	for (int index = 0; index < ids.length; index++)
    {
    	filetypeIds.add(ids[index]);
    }
		System.out.println((filePathsDAO.getFilePathsBasedOnFileTypeIds(filetypeIds)).size());
	}
}
