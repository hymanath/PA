package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothDAOHibernateTest extends BaseDaoTestCase{
	
	private IBoothDAO boothDAO;

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	
	
	/*public void testFindTehsilsByConstituencyElectionScopeAndElection(){
		List<Tehsil> mandals = boothDAO.findTehsilsByElectionAndConstituency("2009", new Long(232));
		assertEquals(mandals.size(), 7);
	}*/
	
	/*public void testFindByConstituencyAndElectionYear(){
		List list = boothDAO.findByConstituencyAndElectionYear(231l, 2009l);
		System.out.println((Long)list.get(0));
	}*/
	
	/*public void testFindByPartNoConstituencyIdAndYear(){
		Long count = (Long)boothDAO.findByPartNoConstituencyIdAndYear(231l, 2009l, "30").get(0);
		System.out.println(count);
	}*/

	/*public void testFindbyConstituencyIdPartnoAndElectionYear() {
		List<Booth> booths = boothDAO.findbyConstituencyNameDistrictIdPartnoAndElectionYear("kavali", 19l, 2009l, "40");
	}*/
	
/*	public void testFindBoothInfoByConstituencyIdAndYear(){
		List list = boothDAO.findBoothInfoByConstituencyIdAndYear(232l, 2009l);
		System.out.println(list.size());
	}*/
	
	/*public void testUpdateLocalBodyInfoByBoothIdsAndWardId(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(9000l);
		boothIds.add(9001l);
		int i = boothDAO.updateLocalBodyInfoByBoothIdsAndWardId(7700l, boothIds);
		setComplete();
		System.out.println(i);
	}*/
	
	/*public void testGetBoothsByIds(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(9000l);
		boothIds.add(9001l);
		List<Booth> booths = boothDAO.findByBoothIds(boothIds);
		System.out.println(booths.size());
	}*/

	/*public void testFindVotersInfoForConstituencyInAnYearByLocalElectionBody(){
		List list = boothDAO.findVotersInfoForConstituencyInAnYearByLocalElectionBody(232l, 2009l, "'"+IConstants.GREATER_ELECTION_TYPE+"'");
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]);
	}*/
	
	/*public void testFindAssemblyConstituenciesDetailsForParliament(){
		List list = boothDAO.findAssemblyConstituenciesDetailsForParliament("223,241,232,233,340,341,238", "2009");
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]);
	}*/
	
	public void testmandalInfo()
	{
		List votersList  = boothDAO.findVoterInformationByMandalIdsAndDelimitationYear("849,851,852,853,854,859,860,862","2004", 235l);
		List<VotersInfoForMandalVO> votersInfoForMandalList = new ArrayList<VotersInfoForMandalVO>();
		for(int j = 0;j<votersList.size();j++)
		{
			VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
			
			Object[] vObj = (Object[]) votersList.get(j);
			votersInfo.setMandalId( vObj[0].toString());
			votersInfo.setMandalName(vObj[1].toString());
			votersInfo.setTotalMaleVoters(vObj[2].toString());
			votersInfo.setTotalFemaleVoters(vObj[3].toString());
			votersInfo.setTotalVoters(vObj[4].toString());
			
			votersInfoForMandalList.add(votersInfo);
		}
		
		for(VotersInfoForMandalVO obj : votersInfoForMandalList)
		{
			System.out.println(obj.getMandalId()+" - "+obj.getMandalName()+" - "+obj.getTotalVoters()+" - "+obj.getTotalMaleVoters()+" - "+obj.getTotalMaleVoters());
		}
	}
	
	/*public void testFindbyConstituencyNameDistrictIdAndElectionYear(){
		List list = boothDAO.findbyConstituencyNameDistrictIdAndElectionYear("Kavali", 19l, 2009l);
	}*/

	/*public void testFindTehsilsByElectionAndConstituency(){
		List list = boothDAO.findTehsilsByElectionAndConstituency("2009", 315l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]);
	}*/
	
	/*public void testFindLocalBodiesByElectionAndConstituency(){
		List list = boothDAO.findLocalBodiesByElectionAndConstituency("2009", 232l, "'"+IConstants.MUNCIPLE_ELECTION_TYPE+"'");
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}*/
	
	/*public void testFindLocalBodyWardsByElectionAndConstituency(){
		List list = boothDAO.findLocalBodyWardsByElectionAndConstituency("2009", 315l, "'"+IConstants.GREATER_ELECTION_TYPE+"'");
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}*/
	
	/*public void testFindByConstituencyElectionAndPartNo(){
		List<Long> list = boothDAO.findByConstituencyElectionAndPartNo(232l, 2009l, "1,2,3");
		System.out.println(list.size());
	}
	*/
	
	/*public void testFindBoothsInfoForAMandalByConstituencyAndYear(){
		List list = boothDAO.findBoothsInfoForAMandalByConstituencyAndYear(835l, 2009l, 232l);
		System.out.println(list.size());
	}*/
	
/*	public void testFindBoothsInfoForALocalElectionBodyByConstituencyAndYear(){
		List list = boothDAO.findBoothsInfoForALocalElectionBodyByConstituencyAndYear(488l, 2009l, 232l);
		System.out.println(list.size());
	}*/
	
	/*public void testFindBoothsInfoForALocalBodyWardByConstituencyAndYear(){
		List list = boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndYear(18381l, 2009l, 315l);
		System.out.println(list.size());
	}*/
	
	/*public void testFindBoothsInLocalElectionBodies(){
		List list = boothDAO.findBoothsInLocalElectionBodies("458, 495, 488", "1,294, 232", 2009l );
		System.out.println(list.size());
		for(Object[] values:(List<Object[]>)list)
			System.out.println("id:"+values[0]+"\t"+"Part No:"+values[1]+"\t"+"const Id "+values[2]+"\t"+"local id:"+values[3]);
	}*/
	
	/*public void testUpdateVillagesCoveredInfoInAConstituency() {
		int update = boothDAO.updateVillagesCoveredInfoInAConstituency(232l, "", "", 2009l);
		System.out.println(update);
	}*/
	
}
