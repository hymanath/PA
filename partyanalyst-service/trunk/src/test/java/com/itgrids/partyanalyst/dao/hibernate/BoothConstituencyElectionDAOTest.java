package com.itgrids.partyanalyst.dao.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dto.VillageBoothElectionVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Constituency;

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
	}
	
	public void testFindByBoothAndConstiuencyElection(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection("200", new Long(1063));
		assertEquals(list.size(), 5);
	}
	
	public void testFindByConstituencyAndElection(){
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
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByConstituencyIdAndElectionYear(new Long(241), "2009");
		System.out.println("List Size:"+list.size());
	}*/
	
	/*public void testFindPartNoConstituencyNameForTehsil(){
		List list = boothConstituencyElectionDAO.findPartNoConstituencyNameForTehsil(new Long(844), "Assembly", "2009");
		System.out.println(list.size());
	}*/
	
	/*public void testmandalInfo()
	{
		List votersList  = boothConstituencyElectionDAO.findVoterInformationByMandalIdsAndDelimitationYear("849,851,852,853,854,859,860,862","2004", 235l);
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
	}*/
	
	/*public void testFindByConstituencyElectionAndPartNo(){
		List list = boothConstituencyElectionDAO.findByConstituencyElectionAndPartNo(519l, "243, 233, 222");
		System.out.println(list);
	}*/
	
	public void testGetBoothsInRevenueVillageForElection(){
		List booths = boothConstituencyElectionDAO.getBoothsInRevenueVillageForElection(21806l, 1l);
		for(int i=0; i<booths.size(); i++)
			System.out.println(((Object[])booths.get(i))[0]+" "+((Object[])booths.get(i))[1]);
		
	}
	
	
}
