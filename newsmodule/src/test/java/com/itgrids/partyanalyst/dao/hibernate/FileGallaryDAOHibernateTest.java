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

	/*public void testgetFileGallaryByGalleryIdsList()
	{
		List<Long> gallaryIdsList = new ArrayList<Long>(0);
		gallaryIdsList.add(27l);
		
	  List<FileGallary> list = fileGallaryDAO.getFileGallaryByGalleryIdsList(gallaryIdsList, null, null, "", 0L, null, null, "");
	  System.out.println(list.size());
	  
	  
	}*/
	
	/*public void testgetAllTheNewsForAUserBasedOnUserAddressId()
	{
		List<Object[]> list = fileGallaryDAO.getAllTheNewsForAUserBasedOnUserAddressId(1L, null, null, 5L, 1L);
		System.out.println(list.size());
		for(Object[] params:list)
		{
			System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	/*public void testgetNewsForSelectedKeyWord()
	{
		List<FileGallary> list = fileGallaryDAO.getNewsForSelectedKeyWord("11", 872L, "", null, null);
		System.out.println(list.size());
		for(FileGallary fileGallary:list)
		 System.out.println(fileGallary.getFileGallaryId()+" "+fileGallary.getFile().getFileId());
	}*/
	
	public void testgetNewsForSelectedKeyWord()
	{
		List<Long> list= fileGallaryDAO.getLocationValuesByRegionScopeId2(4l,"",872l);
		System.out.println(list);
		
	}
	
}
