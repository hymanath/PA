package com.itgrids.partyanalyst.dao.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dto.VillageBoothElectionVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothConstituencyElectionDAOTest extends BaseDaoTestCase{

	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	
/*	public void testFindByTehsil(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByTehsilElectionAndScope("2004", new Long(10), new Long(835), new Long(408));
		System.out.println("Total Booth Constituency Elections:"+ list.size());
	}
	
	public void testFindBoothsForConstituencyElection(){
		List<Booth> list = boothConstituencyElectionDAO.findBoothsByConstituencyElection(new Long(1058));
		assertEquals(256, list.size());
	}*/
	
	/*public void testFindByTehsilElectionAndScope(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByElectionConstituencyAndTehsil("2004", new Long(846), new Long(240));
		assertEquals(list.size(), 5);
	}*/
	
	/*public void testFindByBoothElectionAndScope(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByBoothElectionAndScope(new Long(4500), "2009", new Long(1));
		assertEquals(list.size(), 5);
	}*/
	
	/*public void testFindByBoothAndConstiuencyElection(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection("200", new Long(1063));
		assertEquals(list.size(), 5);
	}*/
	
	/*public void testFindByConstituencyAndElection(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByConstituencyAndElection("kavali", "2004", new Long(2));
		assertEquals(list.size(), 5);
	}
	
	public void testFindConstituencyByElectionYearAndElectionScope(){
		List<Constituency> list = boothConstituencyElectionDAO.findConstituencyByElectionYearAndElectionScope("2004", new Long(1));
		for(Constituency obj:list)
			System.out.println("Name::"+obj.getName());
		assertEquals(list.get(0).getName(), "Ongole");
	}
	
	public void testFindElectionYearsForBoothData(){
		List<String> list = boothConstituencyElectionDAO.findElectionYearsForBoothData();
		for(String obj:list)
			System.out.println("Name::"+obj);
		assertEquals(list.get(0), "2004");
	}*/
	
	/*public void testFindByElectionConstituencyAndBooth(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByElectionConstituencyAndBooth(new Long(2123), "2009", new Long(404));
		assertEquals(1, list.size());
	}*/
	
	/*public void testFindByConstituencyIdAndElectionYear(){
		List list = boothConstituencyElectionDAO.findByConstituencyIdAndElectionYear(270l, 476l, "2009");
		System.out.println("List Size:"+list);
	}*/
	
	/*public void testFindPartNoConstituencyNameForTehsil(){
		List list = boothConstituencyElectionDAO.findPartNoConstituencyNameForTehsil(new Long(844), "Assembly", "2009");
		System.out.println(list.size());
	}*/
	
	/*public void test(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByConstituencyDistrictAndPartNo("Kavali", 19l, "243", "2009");
		for(BoothConstituencyElection obj:list)
			System.out.println(obj.getBooth().getPartNo()+"\t"+obj.getConstituencyElection().getConstituency().getName());
		System.out.println(list.size());
	}*/
	
	/*public void testFind(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByBoothIds("2344, 2355, 2365");
		System.out.println(list.size());
	}
	*/
	/*public void testFindByConstituencyElectionAndPartNo(){
		List list = boothConstituencyElectionDAO.findByConstituencyElectionAndPartNo(519l, "243, 233, 222");
		System.out.println(list);
	}*/
	
	/*public void testGetBoothsInRevenueVillageForElection(){
		List booths = boothConstituencyElectionDAO.getBoothsInRevenueVillageForElection(21806l, 1l);
		for(int i=0; i<booths.size(); i++)
			System.out.println(((Object[])booths.get(i))[0]+" "+((Object[])booths.get(i))[1]);
		
	}*/
	
	/*public void testFindElectionsHappendInConstituency(){
		List list = boothConstituencyElectionDAO.findElectionsHappendInConstituency(3492l);
		for(Object[] values:(List<Object[]>) list)
			System.out.println(values[0]+" "+values[1]);
	}*/
	
	/*public void testFindBoothwiseResultsConstituency(){
		List list = boothConstituencyElectionDAO.findBoothwiseResultsConstituency(342l);
		assertEquals(1, list.size());
	}
	
	
/*	public void testFindTotalBoothWiseValidVotesForConstituencyElection(){
		List list = boothConstituencyElectionDAO.findTotalBoothWiseValidVotesForConstituencyElection(403l, "2009");
		System.out.println(list.get(0));
	}*/
	
	/*public void testGet(){
		List list = boothConstituencyElectionDAO.getTotalVotesInAMandal(141l,"3,4");
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0].toString()+"=========="+parms[1].toString()+"=========="+parms[2].toString());
		}

	}*/
	
	/*@Test
	public void testGetTotalVoters(){
		List totalVoters = boothConstituencyElectionDAO.getTotalVotersInAnElectionInMandal(new Long(52),"Assembly","2009");
		
		if(totalVoters != null){
			Object params = (Object)totalVoters.get(0);
			System.out.println(" Total Voters :" + (Long)params );
		}
	}*/

	/*public void testFindByConstituencyIdPartNosAndYear(){
		List list = boothConstituencyElectionDAO.findByConstituencyIdPartNosAndYear(232l, 2009l, "10,20");
		System.out.println(list.size());
	}*/
	
	/*public void testGetElectionYears()
	{
		List list = boothConstituencyElectionDAO.getElectionYears();
		if(list != null && list.size() > 0)
		{
		 for(Object str : list)
		 {
			 System.out.println(str);
		 }
			
		}
	}
	
	*/
	/*public void testGetElectionIdInMandal()
	{
		System.out.println(boothConstituencyElectionDAO.getElectionIdInMandal(844l,IConstants.ASSEMBLY_ELECTION_TYPE,"2009"));
	}*/
	
	public void testGetVotersCountInAConstituency()
	{
		List<Object[]> dataList = boothConstituencyElectionDAO.getVotersCountInAConstituency(18l,241l);
		for(Object[] data:dataList){
			System.out.println(data[0]+" - "+data[1]+" - "+data[2]+" - "+data[3]);
		}
	}
	
	/*public void testGetVotersCountInAMandal()
	{
		List<Long> constiIds = new ArrayList<Long>();
		constiIds.add(299l);
		constiIds.add(476l);
		List<Object[]> dataList = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(38l,1007l,"mandal","1",null,constiIds);
		for(Object[] data:dataList){
			System.out.println(data[0]+" - "+data[1]+" - "+data[2]+" - "+data[3]);
		}
	}*/
	
	/*public void testgetBoothIdsByLocalEleBodyId()
	{
		List<Long> boothIds = boothConstituencyElectionDAO.getBoothIdsByLocalEleBodyId(83l, 38l);
		if(boothIds != null && boothIds.size() > 0)
		 for(Long id : boothIds)
			 System.out.println(id);
	}*/
	
	/*public void testgetBoothIdsByConstituencyId()
	{
		List<Long> boothIds = boothConstituencyElectionDAO.getBoothIdsByConstituencyId(232l, 38l);
		if(boothIds != null && boothIds.size() > 0)
		 for(Long id : boothIds)
			 System.out.println(id);
	}*/
	/*public void testgetElectionYearsByMandalId()
	{
		List list = boothConstituencyElectionDAO.getElectionYearsByMandalId(IConstants.CONSTITUENCY,232l);
		System.out.println(list);
	}*/
	
	/*public void testGetConstituencyIdByMandalId()
	{
		List<Long list = boothConstituencyElectionDAO.getConstituencyIdByMandalId(844l, "2009", IConstants.PARLIAMENT_ELECTION_TYPE);
		System.out.println(list);
			
	}*/
	
	/*public void testgetElectionYearsByLocalEleBodyId()
	{
		List l = boothConstituencyElectionDAO.getElectionYearsByLocalEleBodyId(83l);
		System.out.println(l);
	}*/
	
	/*public void testGetConstituencyIdByLocalEleBodyId()
	{
		List<Long> l = boothConstituencyElectionDAO.getConstituencyIdByLocalEleBodyId(83l, "2009", IConstants.ASSEMBLY_ELECTION_TYPE);
		System.out.println(l);
	}*/
	
	/*public void testgetConstituencyIdByMandalId()
	{
		List<Long> l = boothConstituencyElectionDAO.getConstituencyIdByElectionTypeAndYear(IConstants.LOCALELECTIONBODY, 83l, "2009", IConstants.PARLIAMENT_ELECTION_TYPE);
		System.out.println(l);
	}*/
	
	/*public void testgetConstituencyIdAndElectionYearByElectionType()
	{
		List<Object[]> list = boothConstituencyElectionDAO.getConstituencyIdAndElectionYearByElectionType(IConstants.CONSTITUENCY, 232l, "2004", IConstants.PARLIAMENT_ELECTION_TYPE);
		if(list != null && list.size() > 0)
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
	}*/
	
}
