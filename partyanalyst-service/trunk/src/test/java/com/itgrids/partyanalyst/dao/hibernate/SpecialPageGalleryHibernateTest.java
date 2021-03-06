package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.utils.IConstants;

public class SpecialPageGalleryHibernateTest extends BaseDaoTestCase {
	ISpecialPageGalleryDAO specialPageGalleryDAO;

	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
	}
	
	/*public void test()
	{
		specialPageGalleryDAO.getAll();
	}

	public void testgetGallariesBySpecialPageId() {
		List<Object[]> list = specialPageGalleryDAO
				.getGallariesBySpecialPageId(1l, "videoGallary");
		for (Object[] params : list) {
			System.out.println(params[0]);
			System.out.println(params[1].toString());

		}
	}*/
	
	public void testGetSpecialPageGallaryDetails(){
		
		List<Object[]> list = specialPageGalleryDAO.getSpecialPageGallaryId(1l,0,20, IConstants.PHOTO_GALLARY);
		System.out.println(list.size());
		/*for(Object[] listObj : list){
			System.out.println((Long)listObj[0]);
			System.out.println(listObj[1].toString());
			System.out.println(listObj[2].toString());
			System.out.println(listObj[3].toString());
		}*/
	}
	
	/*public void testgetGalleryBasedOnSpecialPageId(){
		List<File> result = specialPageGalleryDAO.getGalleryBasedOnSpecialPageId(3l,0, 20, IConstants.NEWS_GALLARY);
		for(File file : result){
		System.out.println(file.getFileDate());
		}
	}*/
	
	public void testGetSpecialPageByGalleryId()
	{
		List<SpecialPage> list = specialPageGalleryDAO.getSpecialPageByGalleryId(963l);
		System.out.println(list.size());
		
		System.out.println(list.get(0).getName());
	}	
}
