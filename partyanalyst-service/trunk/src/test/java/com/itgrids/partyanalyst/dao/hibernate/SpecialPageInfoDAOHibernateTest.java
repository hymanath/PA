package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageInfoDAO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.model.SpecialPageInfo;

public class SpecialPageInfoDAOHibernateTest extends BaseDaoTestCase{

	private ISpecialPageInfoDAO specialPageInfoDAO;

	public void setSpecialPageInfoDAO(ISpecialPageInfoDAO specialPageInfoDAO) {
		this.specialPageInfoDAO = specialPageInfoDAO;
	}

	/*public void test()
	{
		specialPageInfoDAO.getAll();
	}*/
	
	/*public void testGetSpecialPageInfo()
	{
		List<SpecialPageInfo> list = specialPageInfoDAO.getSpecialPageInfo(8l);
		System.out.println(list.size());
		System.out.println(list);
						
		for(SpecialPageInfo spe : list)
		{
			System.out.println(spe.getTitle());
			System.out.println(spe.getDescription());
		}
	}*/
	
	/*public void testGetSpecialPagesForHomePage()
	{
		List<Object[]> list = specialPageInfoDAO.getSpecialPagesForHomePage();
		System.out.println(list.size());
		for(Object[] list1:list)
		{
			System.out.println(list1[0].toString());
		}
	}*/
	
	
	/*public void testGetAllSpecialPageListForHomePage()
	{
		List<SpecialPageInfo> list = specialPageInfoDAO.getAllSpecialPageListForHomePage();
		System.out.println(list.size());
		if(list != null && list.size() >0)
		{
			for(SpecialPageInfo specialPage:list)
			{
			System.out.println("Title: "+specialPage.getTitle());
			System.out.println("Description: "+specialPage.getDescription());
			System.out.println("File Path: "+specialPage.getShowImgPath());
			}
		}
	} 
	*/
	
	public void testgetSpecialPageInfoIdBySpecialPageId()
	{
		System.out.println(specialPageInfoDAO.getSpecialPageInfoIdBySpecialPageId(8l));
	}
}
