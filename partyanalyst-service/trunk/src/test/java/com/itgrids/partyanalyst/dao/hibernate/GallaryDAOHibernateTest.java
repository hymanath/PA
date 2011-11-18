package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.utils.IConstants;
public class GallaryDAOHibernateTest extends BaseDaoTestCase{
	
	private IGallaryDAO gallaryDAO;

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}
	
	/*public void test()
	{
		gallaryDAO.getAll();
	}*/
	
	/*public void testGetCandidateGallaryDetail()
	{
		gallaryDAO.getCandidateGallaryDetail(2L,"","");
	}*/
	
	/*public void testGetGallariesByCandidateId()
	{
		List<Object[]> list = gallaryDAO.getGallariesByCandidateId(900L,IConstants.PHOTO_GALLARY);
		
		System.out.println(list.size());
		
		for(Object[] params :list)
		{
			System.out.println(params[0].toString()+"---"+params[1].toString());
		}
	} */
	//public Integer deleteGallary(Long gallaryId)
	/*public void testDeleteGallary()
	{
		int i = gallaryDAO.deleteGallary(300l);
		System.out.println("i "+i);
		
		
	}*/
   public void testGetCandidatesGallaryDescForUpdate()
   {
	   
	   List<Object[]> result = gallaryDAO.getCandidatesGallaryDescForUpdate(1l,900l);
	   System.out.println("size "+ result.size());
	   for (Object[] object : result) {
		System.out.println((Long)object[0]);
		
		System.out.println(object[1]!=null?object[1].toString():null);
		System.out.println(object[2]!=null?object[2].toString():null);
		System.out.println(object[3]!=null?object[3].toString():null);
	} 
   }
}
