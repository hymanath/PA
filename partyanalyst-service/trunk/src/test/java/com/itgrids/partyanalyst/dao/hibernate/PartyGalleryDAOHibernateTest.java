package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
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
	
	public void testGetPartyByGalleryId()
	{
		List<Party> list = partyGalleryDAO.getPartyByGalleryId(891l);
		
		System.out.println(list.size());
		
		System.out.println(list.get(0).getShortName());
	}
}
