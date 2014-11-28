package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.model.VoterInfo;

public class VoterInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterInfoDAO voterInfoDAO;

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	
	/*public void test()
	{
		voterInfoDAO.getAll();
	}*/
	
	
	/*public void testGetVotersCount1()

	{
		List<VoterInfo> list = voterInfoDAO.getVotersCount(1l, 232l, 7l);
		if(list != null && list.size() > 0)
		{
			for(VoterInfo params : list)
			{
				System.out.println(params.getMaleVoters());
			}
		}
	}*/
	
	/*public void testDeleteVotersInfoByReportLevelValue()

	{
		List<Long> reportLevelValue = new ArrayList<Long>(0);
		reportLevelValue.add(232l);
		System.out.println(voterInfoDAO.deleteVotersInfoByReportLevelValue(1l, reportLevelValue, 7l));
		
	}*/
	
	
	/*public void testGetTotalVotersByReportLevelValue()

	{
		System.out.println(voterInfoDAO.getTotalVotersByReportLevelValue(1l, 232l, 7l));
	}*/
	
	/*public void testgetVoterInfoByPublicationDateIds()

	{
		List<Long> publicationDateIds = new ArrayList<Long>(0);
		publicationDateIds.add(7l);
		List<Object[]> list = voterInfoDAO.getVoterInfoByPublicationDateIds(1l, 232l, publicationDateIds);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]);
		}
		
	}
	*/
	
	
	/*public void testGetTotalVotersByPublicationDateIdsList()

	{
		List<Long> publicationDateIds = new ArrayList<Long>(0);
		publicationDateIds.add(7l);
		publicationDateIds.add(8l);
		List<Object[]> list = voterInfoDAO.getTotalVotersByPublicationDateIdsList(publicationDateIds, 1l, 232l, 232l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	/*public void testGetPublicationListFromVoterInfoByConstituencyId()
	{
		List<Object[]> list = voterInfoDAO.getPublicationDetailsBasedOnConstituencyId(7l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
		
	}*/
	
	/*public void testGetVoterPublicationIdsBetweenTwoPublications()
	{
		List<Long> list = voterInfoDAO.getVoterPublicationIdsBetweenTwoPublications(7l, 8l);
		if(list != null && list.size() > 0)
		  for(Long id : list)
			  System.out.println(id);
	}*/
	
	/*public void testgetPreviousPublicationIds()
	{
		List<Long> list = voterInfoDAO.getPreviousPublicationIds(8l);
		
		if(list != null && list.size() > 0)
			for(Long id : list)
				System.out.println(id);
	}*/
	
	/*public void testGetPanchayatWiseVotersCount()
	{
		List<Object[]> list = voterInfoDAO.getPanchayatWiseVotersCount(232l,8l);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object object :params)
				System.out.print("\t"+object.toString());
		}
	}*/
	
/*	public void testGetReportLevelValueByConstituencyId()
	{
		List<Long> list = voterInfoDAO.getReportLevelValueByConstituencyId(299l, 8l, 3l);
		System.out.println(list.get(0));
		
	}
	*/
	/*public void testGetgetVoterDetailedCountByLocation()
	{
		VoterInfo voterInfo = null;
		List<Object[]> votersDetailesCount = voterInfoDAO.getVoterDetailedCountByLocation(1L,181L,10L,181L);
		System.out.println(votersDetailesCount.size());
		if(votersDetailesCount!=null && votersDetailesCount.size()>0){
		voterInfo = new VoterInfo();
		for (Object[] objects : votersDetailesCount) {
			System.out.println((Long) objects[0]);
			System.out.println((Long) objects[1]);
			System.out.println((Long) objects[2]);
		}
	}
	}

*/
	/*public void testGetgetVoterDetailedCountByLocation()
	{
		VoterInfo voterInfo = null;
		List<Long> votersDetailesCount = voterInfoDAO.getNONURBANConstituencyIds(2l,2009l,1L);
		System.out.println(votersDetailesCount.size());
		if(votersDetailesCount!=null && votersDetailesCount.size()>0){
		voterInfo = new VoterInfo();
		for (Long long1 : votersDetailesCount) {
			System.out.println(long1);
		}
	}
	}*/
	
	/*public void testgetVoterInfoList()
	{
		Long count = voterInfoDAO.getTotalVotersForSelectdLevel(5l,83l,8l,232l);
		System.out.println(count);
	}*/
	
	/*public void testgetTotalVotersForHamletBooth()
	{
		List<Object[]> list = voterInfoDAO.getTotalVotersForHamletBooth(232l, 8l, 2l);
		if(list != null && list.size() > 0)
		{
		  for(Object[] params:list)
			System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	/*public void testgetVoterCountInPanchayatLevel()
	{
		List<Object[]> values = voterInfoDAO.getVoterCountInPanchayatLevel(347l,8l,6l);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	
	/*public void testgetVoterCountInPanchayatLevel()
	{
		List<Long> values = voterInfoDAO.getCountForSelectdCountRange(347l,8l,30000l,40000l,6l);
		for (Long long1 : values) {
			System.out.println(long1);
		}
	}*/
	
	/*public void testgetLatestPublicationDate()
	{
		Long id = voterInfoDAO.getLatestPublicationDate(347l);
		System.out.println(id);
	}*/
	
	/*public void testgetTotalVotersInAPanchayat()
	{
		List<Long> panchayatIds = new ArrayList<Long>();
		panchayatIds.add(1l);
		panchayatIds.add(2l);
		panchayatIds.add(3l);
		List<Object[]> values = voterInfoDAO.getVotersCountInPunchayatAndLocalElecBody(342l,8l);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	/*public void testgetTotalVotersInAPanchayat()
	{
		List<Long> panchayatIds = new ArrayList<Long>();
		panchayatIds.add(1l);
		panchayatIds.add(2l);
		panchayatIds.add(3l);
		Long count = voterInfoDAO.getVotersCountInALocation(11l, 12l, 8l, 309l);
		System.out.println(count);
	}*/
	
	/*public void testGetFamiliesCountInAPanchayats()
	{
		List<Long> panchayatIds = new ArrayList<Long>();
		panchayatIds.add(1l);
		panchayatIds.add(2l);
		List<Object[]> list = voterInfoDAO.getFamiliesCountInAPanchayats(panchayatIds,10l);
		System.out.println(list.size()); 
	}*/
	
	/*public void testGetVotersCountInADistrict()
	{
		Long count = voterInfoDAO.getVotersCountInADistrict(3l,10l);
		System.out.println(count);
	}
	
	public void testGetVotersCountInADistrictsList()
	{
		List<Long> districtIdsList = new ArrayList<Long>(0);
		districtIdsList.add(1l);
		districtIdsList.add(2l);
		districtIdsList.add(3l);
		
		List<Object[]> list = voterInfoDAO.getVotersCountInADistrictsList(districtIdsList, 10l);
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("\t"+obj);
		}
	}
	
	public void testGetVotersCountInConstituenciesByDistrictsList()
	{
		List<Long> districtIdsList = new ArrayList<Long>(0);
		districtIdsList.add(1l);
		districtIdsList.add(2l);
		districtIdsList.add(3l);
		
		List<Object[]> list = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIdsList, 10l);
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("\t"+obj);
		}
	}*/
	
	public void test(){
		List<Long> lst = new ArrayList<Long>();
		lst.add(17l);
		lst.add(18l);
		
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(209L);
		locationIds.add(213L);
		locationIds.add(217L);
		locationIds.add(228L);
		locationIds.add(229L);
		locationIds.add(218L);
		locationIds.add(219L);
		
		//List<Object[]> list = voterInfoDAO.getVotersCountInADistrictsList(lst, 11l);
		List<Object[]> list = voterInfoDAO.getVotersCountInConstituenciesByDistrictsListAndConstituencies(lst, 11l,locationIds);
		System.out.println(list.size());
		
	}

}
