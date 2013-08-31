package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.model.VoterModification;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterModificationDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationDAO voterModificationDAO;

	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}
	
	/*public void test()
	{
		voterModificationDAO.getAll();
	}*/
	
	/*public void testGetAddedAndDeletedVotersCountInBetweenPublicationsInALocation()
	{	
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		 publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",299l,299l,publicationIdsList);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
	}*/
	
	/*public void testGetAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation()
	{
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		List<Object[]> list = null;
		publicationIdsList.add(8l);
		list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",299l,299l,publicationIdsList,18l,25l);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
		list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",299l,299l,publicationIdsList,26l,35l);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
		list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",299l,299l,publicationIdsList,36l,45l);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
		list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",299l,299l,publicationIdsList,46l,60l);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
		list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",299l,299l,publicationIdsList,60l,null);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
	}*/
	
	/*public void testGetGenderWiseVoterModificationsBetweenPublications()
	{
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationsBetweenPublications("constituency",299l,299l, publicationIdsList);
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetModifiedVotersInALocationBetweenPublucations()
	{
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getModifiedVotersInALocationBetweenPublucations("localElectionBody",83l,232l, publicationIdsList, "All");
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetModifiedVotersByPartNo()
	{
		List<Long> list = voterModificationDAO.getModifiedVotersByPartNo("1",241l,8l,IConstants.STATUS_ADDED);
		System.out.println(list.size());
	}*/
	
	/*public void testGetGenderWiseVoterModificationByPublicationId()
	{
		List<Long> locationValuesList = new ArrayList<Long>(0);
		locationValuesList.add(232l);
		String locationType = "localElectionBody";
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct model.voter.voterId),model.status,model.voter.gender ");
		 
		 if(locationType.equalsIgnoreCase("panchayat"))
			 queryStr.append(" ,model2.booth.constituency.constituencyId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				queryStr.append(", model2.booth.tehsil.tehsilId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				queryStr.append(" ,model2.booth.panchayat.panchayatId ");
			else if(locationType.equalsIgnoreCase("booth"))
				queryStr.append(" ,model2.booth.boothId ");
			else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
				queryStr.append(" ,model.booth.localBody.localElectionBodyId ");
			else if(locationType.equalsIgnoreCase("ward"))
				queryStr.append(" ,model2.localBodyWard.constituencyId ");
		
		List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationByPublicationId(locationType, locationValuesList, 232l, 83l,queryStr.toString());
		
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			Long addedTotal = 0l;
			Long deletedTot = 0l;
			for(Object[] params : list)
			{
				if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
				 {
					 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
						 System.out.println("Added male : "+(Long)params[0]);
					 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
						 System.out.println("added female :"+(Long)params[0]);
					 System.out.println(addedTotal += (Long)params[0]); 
					 
				 }
				 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
				 {
					 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
						 System.out.println("deleted male : "+(Long)params[0]);
					 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
						 System.out.println("deleted female : "+(Long)params[0]);
					 System.out.println(deletedTot += (Long)params[0]); 
				 }
			}
				
				
		}
	}*/
	
	/*public void testGetAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation()
	{
		List<Long> locationValuesList = new ArrayList<Long>(0);
		locationValuesList.add(232l);
		List<Object[]> list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation("constituency", locationValuesList, 232l, 7l, 18l, 25l);
		
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[1]);
		} 
	}*/
	
	/*public void testgetSublevelVoterModificationDetailsByLocationValues()
	{
		List<Long> publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		
		String queryStr = "select count(model.voter.voterId),model.status,model.voter.gender,model2.booth.partNo ";
		List<Long> locationValuesList = new ArrayList<Long>(0);
		locationValuesList.add(122923l);
		locationValuesList.add(122924l);
 		List<Object[]> list = voterModificationDAO.getSublevelVoterModificationDetailsByLocationValues(232l, publicationIdsList, locationValuesList, "booth", queryStr);
		
 		System.out.println(list.size());
 		
 		for(Object[] params : list)
 		{
 			if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
 				System.out.println("deleted - "+params[0]);
 		}
 			
	}*/
	
	/*public void testgetVoterModificationsByConstituencyId()
	{
		List<Long> ids = new ArrayList<Long>(0);
		ids.add(6616l);
		int list = voterModificationDAO.updateVoterStatus(1l,ids);
		System.out.println(list);
	}*/
	
	/*public void testGetBoothWiseVotersDataByBoothIds()
	{
		List<Long> partNosList = new ArrayList<Long>(0);
		
		partNosList.add(127l);
		List<Object[]> list = voterModificationDAO.getBoothWiseVotersDataByBoothIds(299l, 8l, partNosList);
		System.out.println(list.size());
		Long added = 0l;
		Long deleted = 0l;
		Long moved = 0l;
		Long relocated = 0l;
		for(Object[] params:list)
		{
			if(params[1].toString().equalsIgnoreCase("Added"))
				added = (Long)params[0];
			if(params[1].toString().equalsIgnoreCase("Deleted"))
				deleted = (Long)params[0];
			if(params[1].toString().equalsIgnoreCase("Moved"))
				moved = (Long)params[0];
			if(params[1].toString().equalsIgnoreCase("Relocated"))
				relocated = (Long)params[0];
			
			System.out.println("added - "+added);
			System.out.println("deleted - "+deleted);
			System.out.println(" moved - "+ moved);
			System.out.println("relocated- "+relocated);
			System.out.println("partno- "+params[2]);
			System.out.println("boothid- "+params[3]);
			System.out.println("villages- "+params[4]);
		}
	}
	*/
	
	/*public void testgetSelectedVotersDetails()
	{
		List<Long> partNosList = new ArrayList<Long>(0);
		
		partNosList.add(127l);
		partNosList.add(126l);
		List<Long> publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		
		List<Object[]> list = voterModificationDAO.getSelectedVotersDetails(299l, publicationIdsList, partNosList, 1l);
		System.out.println(list.size());
		for(Object[] params:list)
		{
			System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]+" "+params[4]+" "+params[5]);
		}
	}*/
	
	/*public void testgetPartNoForMovedOrRelocatedVoter()
	{
		List<Long> list = voterModificationDAO.getPartNoForMovedOrRelocatedVoter(225017l, 8l, 299l, 4l);
		if(list != null && list.size() > 0)
			System.out.println(list.get(0));
	}*/
	
	
	/*public void testGetMovedOrRelocatedVoterDetails()
	{
		List<Long> partNosList = new ArrayList<Long>(0);
		partNosList.add(93l);
		
		List<Object[]> list = voterModificationDAO.getMovedOrRelocatedVoterDetails(232l, 8l, partNosList);
		System.out.println(list.size());
		
	}*/
	
	/*public void testGetVoterModificationDetailsOfAConstituencyForAPublication()
	{
		List<Object[]> list = voterModificationDAO.getVoterModificationDetailsOfAConstituencyForAPublication(232l, 8l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetAvailableConstituenciesInAPublication()
	{
		List<Long> list = voterModificationDAO.getAvailableConstituenciesInAPublication(8l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetListOfVoterIdsInAPublicationBasedOnCount()
	{
		List<Long> list = voterModificationDAO.getListOfVoterIdsInAPublicationBasedOnCount(1l,8l,1l);
		System.out.println(list.size());
		for(Long voterId : list)
			System.out.println(voterId);
	}*/
	
	/*public void testGetObjectsByVoterIdsList()
	{
		List<Long> voterIdsList = new ArrayList<Long>(0);
		voterIdsList.add(1l);
		List<VoterModification> list = voterModificationDAO.getObjectsByVoterIdsList(221l,8l, voterIdsList);
		System.out.println(list.size());
	}*/
	
	/*public void testGetVMVoterIdsAndStatusList()
	{
		List<Long> voterIdsList = new ArrayList<Long>(0);
		voterIdsList.add(1l);
		List<Object[]> list = voterModificationDAO.getVMVoterIdsAndStatusList(221l,8l, voterIdsList);
		System.out.println(list.size());
	}*/
	
	/*public void testUpdateVoterStatus()
	{
		List<Long> voterModificationIdsList = new ArrayList<Long>(0);
		voterModificationIdsList.add(305882l);
		int count = voterModificationDAO.updateVoterStatus(voterModificationIdsList, 3l);
		System.out.println(count);
	}*/
	public void testUpdateVoterStatusInVoterModification()
	{
		try{
			List<Long> constituenciesList = voterModificationDAO.getAvailableConstituenciesInAPublication(8l);
			if(constituenciesList != null && constituenciesList.size() > 0)
			{
				for(Long constituencyId : constituenciesList)
				{
					try{
						System.out.println(constituencyId +" -- Constituency has Started.....");
						List<Long> addNDeleteList = voterModificationDAO.getListOfVoterIdsInAPublicationBasedOnCount(constituencyId,8l,1l);
						List<Long> rList = new ArrayList<Long>(0);
						if(addNDeleteList != null && addNDeleteList.size() > 0)
						{
							List<Object[]> vmlist = new ArrayList<Object[]>(0);
							System.out.println("Add deletion List Size : "+addNDeleteList.size());
							if(addNDeleteList.size() <= 1000)
							{
								vmlist = voterModificationDAO.getVMVoterIdsAndStatusList(constituencyId,8l,addNDeleteList);
								
								if(vmlist != null && vmlist.size() > 0)
								{
									rList = getList(vmlist,"added");
									updateVoterStatus(rList,1l);
									rList = getList(vmlist,"deleted");
									updateVoterStatus(rList,2l);
								}
							}
							else
							{
								int fromIndex = 0;
								int toIndex = 1000;
								for(;;)
								{
									if(fromIndex >= toIndex)
										break;
									vmlist = voterModificationDAO.getVMVoterIdsAndStatusList(constituencyId,8l,addNDeleteList.subList(fromIndex, toIndex));
									
									fromIndex+=1000;
									toIndex+=1000;
									
									if(toIndex >= addNDeleteList.size())
										toIndex = addNDeleteList.size();
									
									if(vmlist != null && vmlist.size() > 0)
									{
										rList = getList(vmlist,"added");
										updateVoterStatus(rList,1l);
										rList = getList(vmlist,"deleted");
										updateVoterStatus(rList,2l);
									}
								}
							}
							
						}
						
						List<Long> movOrRelList = voterModificationDAO.getListOfVoterIdsInAPublicationBasedOnCount(constituencyId,8l,2l);
						if(movOrRelList != null && movOrRelList.size() > 0)
						{
							List<Object[]> vmlist = new ArrayList<Object[]>(0);
							System.out.println("Add deletion List Size : "+movOrRelList.size());
							if(movOrRelList.size() <= 1000)
							{
								vmlist = voterModificationDAO.getVMVoterIdsAndStatusList(constituencyId,8l,movOrRelList);
								if(vmlist != null && vmlist.size() > 0)
								{
									rList = getList(vmlist,"added");
									updateVoterStatus(rList,4l);
									rList = getList(vmlist,"deleted");
									updateVoterStatus(rList,3l);
								}
							}
							else
							{
								int fromIndex = 0;
								int toIndex = 1000;
								for(;;)
								{
									if(fromIndex >= toIndex)
										break;
									vmlist = voterModificationDAO.getVMVoterIdsAndStatusList(constituencyId,8l,movOrRelList.subList(fromIndex, toIndex));
									fromIndex+=1000;
									toIndex+=1000;
									
									if(toIndex >= movOrRelList.size())
										toIndex = movOrRelList.size();
									
									if(vmlist != null && vmlist.size() > 0)
									{
										rList = getList(vmlist,"added");
										updateVoterStatus(rList,4l);
										rList = getList(vmlist,"deleted");
										updateVoterStatus(rList,3l);
									}
								}
							}
							
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateVoterStatus(List<Long> list, Long statusId)
	{
		try{
			if(list.size() > 0)
			{
				int count = voterModificationDAO.updateVoterStatus(list,statusId);
				System.out.println(count+" Records are updated");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public List<Long> getList(List<Object[]> inList, String status)
	{
		List<Long> list = new ArrayList<Long>(0);
		try{
			for(Object[] params : inList)
			{
				if(params[1].toString().equalsIgnoreCase(status))
					list.add((Long)params[0]);
			}
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
			return list;
		}
		
	}
}
