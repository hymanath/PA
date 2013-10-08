package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothResultDAOHibernateTest extends BaseDaoTestCase{
	
	private IBoothResultDAO boothResultDAO;

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}
	
	/*public void testFindByBoothConstituencyElection(){
		List<BoothResult> list = boothResultDAO.findByBoothConstituencyElection(new Long(200));
		assertEquals(1, list.size());
	}
	
	public void testFindByConstituencyAndElection(){
		List<BoothResult> list = boothResultDAO.findByConstituencyAndElection("allur", "2004", new Long(2));
		assertEquals(1, list.size());
	}*/
	
	/*public void testGetAllPolledVotesByElectionsInDistrict(){
		List list = boothResultDAO.getAllPolledVotesByElectionsInDistrict(19l, "parliament");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]);
	}*/
	
	/*public void testGetParliamentResultHappenedInAssembly(){
		List list = boothResultDAO.getParliamentResultHappenedInAssembly("Amberpet", 5l, 1l, "2009");
		System.out.println(list.get(0));
	}
	*/
	/*public void testGetAllPolledVotesForMandalsInAnElection(){
		List list = boothResultDAO.getAllPolledVotesForMandalsInAnElection("373, 835, 841, 1069", "2009", "Assembly");
		System.out.println(list);
		if(list.size() > 0)
			System.out.println(((Object[])list.get(0))[0]+"\t"+((Object[])list.get(0))[1]+"\t"+((Object[])list.get(0))[2]+"\t"+((Object[])list.get(0))[3]);
	}*/
	
	/*public void testGetMandalwiseValidVotesForAMappedConstituency(){
		List list = boothResultDAO.getMandalwiseValidVotesForAMappedConstituency(362l, "2004", IConstants.ASSEMBLY_ELECTION_TYPE);
		System.out.println(list.size());
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}*/
	
	public void testgetDelimitationEffectBasedOnVoters()
	{
		List<Object[]> values = boothResultDAO.getAfterDelimitationEffectBasedOnVoters(38l,282l);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}
	
	/*public void testgetBeforeDelimitationEffectBasedOnVoters()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(88400l);
		boothIds.add(88401l);
		boothIds.add(88402l);
		boothIds.add(88404l);
		boothIds.add(88405l);
		boothIds.add(88406l);
		boothIds.add(88407l);
		boothIds.add(88408l);
		boothIds.add(88409l);
		boothIds.add(88410l);
		boothIds.add(88411l);
		boothIds.add(88412l);
		boothIds.add(88413l);
		boothIds.add(88414l);
		boothIds.add(88415l);
		boothIds.add(88416l);
		boothIds.add(88417l);
		boothIds.add(88418l);
		boothIds.add(88419l);
		boothIds.add(88420l);
		boothIds.add(88421l);
		boothIds.add(88422l);
		boothIds.add(88423l);
		boothIds.add(88424l);
		boothIds.add(88425l);
		boothIds.add(88426l);
		boothIds.add(88427l);
		boothIds.add(88428l);
		boothIds.add(88429l);
		boothIds.add(88430l);
		boothIds.add(88431l);
		boothIds.add(88432l);
		boothIds.add(88433l);
		boothIds.add(88434l);
		boothIds.add(88435l);
		boothIds.add(88436l);
		boothIds.add(88437l);
		boothIds.add(88438l);
		boothIds.add(88439l);
		boothIds.add(88440l);
		boothIds.add(88441l);
		boothIds.add(88442l);
		boothIds.add(88443l);
		boothIds.add(88444l);
		boothIds.add(88445l);
		boothIds.add(88446l);
		boothIds.add(88447l);
		boothIds.add(88448l);
		boothIds.add(88449l);
		boothIds.add(88450l);
		boothIds.add(88451l);
		boothIds.add(88452l);
		boothIds.add(88453l);
		boothIds.add(88454l);
		boothIds.add(88455l);
		boothIds.add(88456l);
		boothIds.add(88457l);
		boothIds.add(88458l);
		boothIds.add(88459l);
		boothIds.add(88460l);
		boothIds.add(88461l);
		boothIds.add(88462l);
		boothIds.add(88463l);
		boothIds.add(88464l);
		boothIds.add(88465l);
		boothIds.add(88466l);
		boothIds.add(88467l);
		boothIds.add(88468l);
		boothIds.add(88469l);
		boothIds.add(88470l);
		boothIds.add(88471l);
		boothIds.add(88472l);
		boothIds.add(88473l);
		boothIds.add(88474l);
		boothIds.add(88475l);
		boothIds.add(88476l);
		boothIds.add(88477l);
		boothIds.add(88478l);
		boothIds.add(88479l);
		boothIds.add(88480l);
		boothIds.add(88481l);
		boothIds.add(88482l);
		boothIds.add(88483l);
		boothIds.add(88484l);
		boothIds.add(88485l);
		boothIds.add(88486l);
		boothIds.add(88487l);
		boothIds.add(88488l);
		boothIds.add(88489l);
		boothIds.add(88490l);
		boothIds.add(88491l);
		boothIds.add(88492l);
		boothIds.add(88493l);
		boothIds.add(88494l);
		boothIds.add(88495l);
		boothIds.add(88496l);
		boothIds.add(88497l);
		boothIds.add(88498l);
		boothIds.add(88499l);
		boothIds.add(88500l);
		boothIds.add(88501l);
		boothIds.add(88502l);
		boothIds.add(88503l);
		boothIds.add(88504l);
		boothIds.add(88505l);
		boothIds.add(88506l);
		boothIds.add(88507l);
		boothIds.add(88508l);
		boothIds.add(88509l);
		boothIds.add(88510l);
		boothIds.add(88511l);
		boothIds.add(88512l);
		boothIds.add(88513l);
		boothIds.add(88514l);
		boothIds.add(88515l);
		boothIds.add(88516l);
		boothIds.add(88517l);
		boothIds.add(88518l);
		boothIds.add(88519l);
		boothIds.add(88520l);
		boothIds.add(88521l);
		boothIds.add(88522l);
		boothIds.add(88523l);
		boothIds.add(88524l);
		boothIds.add(88525l);
		boothIds.add(88526l);
		boothIds.add(88527l);
		boothIds.add(88528l);
		boothIds.add(88529l);
		boothIds.add(88530l);
		boothIds.add(88531l);
		boothIds.add(88532l);
		boothIds.add(88533l);
		boothIds.add(88534l);
		boothIds.add(88535l);
		boothIds.add(88536l);
		boothIds.add(88537l);
		boothIds.add(88538l);
		boothIds.add(88539l);
		boothIds.add(88540l);
		boothIds.add(88541l);
		boothIds.add(88542l);
		boothIds.add(88543l);
		boothIds.add(88544l);
		boothIds.add(88545l);
		boothIds.add(88546l);
		boothIds.add(88547l);
		boothIds.add(88548l);
		boothIds.add(88549l);
		boothIds.add(88550l);
		boothIds.add(88551l);
		boothIds.add(88552l);
		boothIds.add(88553l);
		boothIds.add(88554l);
		boothIds.add(88555l);
		boothIds.add(88556l);
		boothIds.add(88557l);
		boothIds.add(88558l);
		boothIds.add(88559l);
		boothIds.add(88560l);
		boothIds.add(88561l);
		boothIds.add(88562l);
		boothIds.add(88563l);
		boothIds.add(88564l);
		boothIds.add(88565l);
		List<Object[]> values = boothResultDAO.getBeforeDelimitationEffectBasedOnVoters(3l,boothIds);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/


}
