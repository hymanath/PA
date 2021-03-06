package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyGalleryDAOHibernateTest extends BaseDaoTestCase {

	private IPartyGalleryDAO partyGalleryDAO;

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	/*
	 * public void test() { partyGalleryDAO.getAll(); }
	 */

	// public List<Object[]> getPartyGallaryDetail(Long partyId,int
	// firstResult,int maxResult,String type)
	/*public void testGetPartyGallaryDetail() {
		String type = IConstants.PHOTO_GALLARY;
		List<Object[]> result = partyGalleryDAO.getPartyGallaryDetail(163l, 0,
				20, type);
		System.out.println(result.size());
		for (Object[] objects : result) {
        System.out.println((Long)objects[0]);
        System.out.println(objects[1].toString());
		}
	}*/
/*	
public List<Object[]> getAllRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription,  " +
				" model.gallary.name  from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
				" order by model.file.fileId asc ");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");				
		return query.list(); 
	}*/
	/*public void testGetAllRecordInGallary()
	{
		List<Object[]> result = partyGalleryDAO.getAllRecordInGallary(9l);
		System.out.println(result.size());
		for (Object[] objects : result) {
        System.out.println((Long)objects[0]);
        System.out.println(objects[1].toString());
		}
		
	}*/
	//public List<Object[]> getGallariesByPartyId(Long partyId,String contentType)
	/*public void testGetGallariesByPartyId()
	{
		
		List<Object[]> result = partyGalleryDAO.getGallariesByPartyId(163l, "photo gallary");
		for (Object[] objects : result) {
			System.out.println((Long)objects[0]);
			System.out.println(objects[1].toString());
		}
	}*/
	
	/*public void testGetPartyByGalleryId()
	{
		List<Party> list = partyGalleryDAO.getPartyByGalleryId(891l);
		
		System.out.println(list.size());
		
		System.out.println(list.get(0).getShortName());
	}
	*/
	/*public void testgetPartyVisibility()
	{
		List<Object> result = partyGalleryDAO.getPartyVisibility(1159l);
		for(Object params:result)
	{
		System.out.println(params);
	}
	}*/
	
	/*public void testGetOtherGalleries()
	{
		List<Long> gallaryIds = new ArrayList<Long>(0);
		gallaryIds.add(1197l);
		List<Object> result = partyGalleryDAO.getOtherGalleries(872l,gallaryIds,IConstants.VIDEO_GALLARY);
		
		System.out.println(result.size());
	}*/
	
	/*public void testGetNewsForParty()
	{
		List<Object[]> result = partyGalleryDAO.getNewsForParty(362l,0,20,"Public");
		
		for(Object[] params : result)
		{
			File file = (File)params[0];
			System.out.println(params[1].toString());
			System.out.println(file.getFileName());
		}
	}
	*/
	
	/*public void testgetAllNewsDetails()
	{
		List<Object[]> list = partyGalleryDAO.getAllNewsDetails(872l, 0,100, IConstants.NEWS_GALLARY);
		System.out.println(list.size());
	}*/
	
	/*public void testgetNewsCountByScope()
	{
		List<Long> count = partyGalleryDAO.getNewsCountByScope(872l,2l,"Public");
		System.out.println(count);
		
	}*/
	
	public void testGetPartyGallaryByPartyId()
	{
		List<Object[]> list = partyGalleryDAO.getPartyGallaryByPartyId(362l, IConstants.PHOTO_GALLARY,"AICC PLENARY 2010");
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}
}
