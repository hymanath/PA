package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;

public class PartialBoothPanchayatDAOHibernateTest extends BaseDaoTestCase{

	private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;

	public IPartialBoothPanchayatDAO getPartialBoothPanchayatDAO() {
		return partialBoothPanchayatDAO;
	}

	public void setPartialBoothPanchayatDAO(
			IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
		this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
	}
	
	/*public void testgetPartialBoothPanchayatDetailsByPanchayatId(){
		List<PartialBoothPanchayat> list1 = new ArrayList<PartialBoothPanchayat>();
		System.out.println("hyderabad");
		list1 = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatId(3282L);
		System.out.println(list1.size());
		System.out.println(list1);
	}*/
	
	/*public void testgetPanchayatByBoothId()
	{
		List<Object[]> values = partialBoothPanchayatDAO.getPanchayatByBoothId(160563l);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	/*public void testgetParitialBooths()
	{
		List<Long> panchayatIds = new ArrayList<Long>();
		panchayatIds.add(3282l);
		List<Object[]> values = partialBoothPanchayatDAO.getParitialBooths(panchayatIds,282l,8l);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	/*public void testgetBoothPanchayatDetails()
	{
		Long id = partialBoothPanchayatDAO.getBoothPanchayatDetails(3282l,1605l);
		System.out.println(id);
	}*/
	
	/*public void testgetPartialBoothsAndPanchayats()
	{
		List<Object[]> values = partialBoothPanchayatDAO.getPartialBoothsAndPanchayats(844l,8l);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	/*public void testgetSelectdPartialPanchayatDetails()
	{
		List<Object[]> values = partialBoothPanchayatDAO.getSelectdPartialPanchayatDetails(26l);
		
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1] +":"+ objects[2] +":"+ objects[3] +":"+ objects[4] +":"+ objects[5] +":"+ objects[6]);
		}
	}*/
	
	/*public void testgetDescriptionForSelectedPartalPanchay()
	{
		List<String> values = partialBoothPanchayatDAO.getDescriptionForSelectedPartalPanchay(1l,122995l);
		
		for (String string : values) {
			System.out.println(string);
		}
	}*/
	
	/*public void testdeleteSelectedPartialBoothPanchayat()
	{
		int result = partialBoothPanchayatDAO.deleteSelectedPartialBoothPanchayat(29l);
		System.out.println(result);
	}*/
	
	/*public void testgetCountForPartianBooths()
	{
		Long count = partialBoothPanchayatDAO.getCountForPartianBooths(122995l);
		System.out.println(count);
	}*/
	
	/*public void testdeleteSelectedMultiplePartialBoothPanchayat()
	{
		int result = partialBoothPanchayatDAO.deleteSelectedMultiplePartialBoothPanchayat(122995l);
		System.out.println(result);
	}*/
	
	public void testupdateDescriptionForPartialPanchayat()
	{
		int result = partialBoothPanchayatDAO.updateDescriptionForPartialPanchayat(1l,122995l,"prasad");
		System.out.println(result);
	}
	
	
}
