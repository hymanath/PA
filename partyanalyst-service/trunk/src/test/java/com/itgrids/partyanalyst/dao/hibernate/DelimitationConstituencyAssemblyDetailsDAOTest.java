package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyAssemblyDetailsDAOTest extends BaseDaoTestCase{

	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	
	/*public void testFindAssemblyConstituencies(){
		List<Constituency> list = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(new Long(405), new Long(2010));
		for(Constituency constituency:list)
			System.out.println(constituency.getName());
	}
	
	public void testFindConstituenciesByDelimitationAssembly(){
		List<Constituency> list = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesByDelimitationConstituencyId(new Long(15));
		assertEquals(4, list.size());
	}
	*/
	/*public void testFindParliamentForAssembly(){
		List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(232l);
		assertEquals(list.size(), 1);
		assertEquals(404l, ((Object[])list.get(0))[1]);
	}*/
	
	/*public void testFindAssembliesConstituencies(){
		List list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(404l);
		for(int i=0; i<list.size(); i++){
			Object[] params = (Object[])list.get(i);
			System.out.println(params[0] + "--" + params[1]);
		}	
	}*/
	
	/*public void testGetAllAssembliesOfParliament(){
		List list = delimitationConstituencyAssemblyDetailsDAO.getAllAssembliesOfParliament(404l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}
	
	public void testGetAllAssembliesIdsAndNamesByParliamentId(){
		List list = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesIdsAndNames(403l,2009l);
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0]+"\t"+parms[1]);
		}	
	}
	
	*/
	/*public void testParliamentIdByAssemblyId(){
		List list = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(232l,2009l);
		System.out.println(list.size());
		System.out.println(list.get(0));
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testFindDistrictsOfParliamentConstituency(){
		List<Object[]> list =  delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituency(506l);
		
		if(list != null && list.size() > 0)
		{
			System.out.println(list.size());
			
			for(Object[] obj : list)
			{
				System.out.println(obj[0].toString());
				System.out.println(obj[1].toString());
			}
		}
		
		
	}
	
	/*public void testFindAssemblyConstituenciesOfParliamentConstituency(){
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(404));
		List result = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(list);
		System.out.println(result);
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testFindParliamentConstituenciesByDistrictId()
	{
		List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesByDistrictId(19L, IConstants.DELIMITATION_YEAR);
		
		if(list != null && list.size() > 0)
		{
			System.out.println(list.size());
			for(Object[] param :list)
			{
				System.out.println(param[0]);
				System.out.println(param[1]);
			}
		}
	}*/
	
	/*public void testFindLatestParliamentForAssembly()
	{
		
		List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(232L);
		
		System.out.println(list.size());
	}
	public void testFindParliamentConstituenciesForAAssemblyConstituency() {
		List lst = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesForAAssemblyConstituency(240l);
		//System.out.println(lst.size());
		for(int i=0;i<lst.size();i++){
			Object[] param =(Object[]) lst.get(i);
			System.out.println(param[0].toString());
			//System.out.println(param[1].toString());
			System.out.println(param[2].toString());
		}
	}*/
	/*public void testfindParliamentForAssemblyForTheGivenYear(){
		List list = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheMaxOfGivenYear(496l);
		System.out.println(list.size());
	}*/
	
	/*public void testfindDistrictsOfParliamentConstituencies()
	{
		 List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituencies(485l);
		 {
			 for(Object[] params: list)
			 {
			 System.out.println(params[0]);
			 System.out.println(params[1].toString());
		 }}
	}*/
	
	
	/*public void testFindParliamentForAssembly(){
		List list = delimitationConstituencyAssemblyDetailsDAO.findParliamentByAssemblyIdAndElectionYear(232l,2004l);
		System.out.println(((Object[])list.get(0))[1]);
		
	}*/
	public void testfindLatestParliamentForAssembly(){
		List<Long> assemblyIds = new ArrayList<Long>();
		
		assemblyIds.add(181L);
		assemblyIds.add(218L);
		assemblyIds.add(108L);
		assemblyIds.add(146L);
		assemblyIds.add(133L);
		assemblyIds.add(147L);
		assemblyIds.add(359L);
		assemblyIds.add(241L);
		assemblyIds.add(182L);
		assemblyIds.add(209L);
		assemblyIds.add(167L);
		assemblyIds.add(368L);
		assemblyIds.add(122L);
		assemblyIds.add(120L);
		assemblyIds.add(199L);
		assemblyIds.add(168L);
		assemblyIds.add(219L);
		assemblyIds.add(134L);
		assemblyIds.add(221L);
		assemblyIds.add(169L);
		assemblyIds.add(135L);
		assemblyIds.add(170L);
		assemblyIds.add(109L);
		assemblyIds.add(121L);
		assemblyIds.add(358L);
		assemblyIds.add(310L);
		assemblyIds.add(184L);
		assemblyIds.add(222L);
		assemblyIds.add(171L);
		assemblyIds.add(185L);
		assemblyIds.add(231L);
		assemblyIds.add(311L);
		assemblyIds.add(312L);
		assemblyIds.add(203L);
		assemblyIds.add(111L);
		assemblyIds.add(149L);
		assemblyIds.add(186L);
		assemblyIds.add(187L);
		assemblyIds.add(308L);
		assemblyIds.add(307L);
		assemblyIds.add(223L);
		assemblyIds.add(224L);
		assemblyIds.add(232L);
		assemblyIds.add(225L);
		assemblyIds.add(152L);
		assemblyIds.add(233L);
		assemblyIds.add(172L);
		assemblyIds.add(360L);
		assemblyIds.add(205L);
		assemblyIds.add(328L);
		assemblyIds.add(136L);
		assemblyIds.add(309L);
		assemblyIds.add(206L);
		assemblyIds.add(226L);
		assemblyIds.add(153L);
		assemblyIds.add(191L);
		assemblyIds.add(192L);
		assemblyIds.add(112L);
		assemblyIds.add(173L);
		assemblyIds.add(208L);
		assemblyIds.add(137L);
		assemblyIds.add(361L);
		assemblyIds.add(340L);
		assemblyIds.add(341L);
		assemblyIds.add(366L);
		assemblyIds.add(193L);
		assemblyIds.add(227L);
		assemblyIds.add(138L);
		assemblyIds.add(174L);
		assemblyIds.add(113L);
		assemblyIds.add(352L);
		assemblyIds.add(329L);
		assemblyIds.add(228L);
		assemblyIds.add(124L);
		assemblyIds.add(114L);
		assemblyIds.add(140L);
		assemblyIds.add(327L);
		assemblyIds.add(210L);
		assemblyIds.add(155L);
		assemblyIds.add(330L);
		assemblyIds.add(141L);
		assemblyIds.add(156L);
		assemblyIds.add(176L);
		assemblyIds.add(211L);
		assemblyIds.add(157L);
		assemblyIds.add(212L);
		assemblyIds.add(304L);
		assemblyIds.add(305L);
		assemblyIds.add(353L);
		assemblyIds.add(303L);
		assemblyIds.add(159L);
		assemblyIds.add(306L);
		assemblyIds.add(160L);
		assemblyIds.add(213L);
		assemblyIds.add(125L);
		assemblyIds.add(229L);
		assemblyIds.add(236L);
		assemblyIds.add(214L);
		assemblyIds.add(116L);
		assemblyIds.add(127L);
		assemblyIds.add(237L);
		assemblyIds.add(177L);
		assemblyIds.add(215L);
		assemblyIds.add(178L);
		assemblyIds.add(117L);
		assemblyIds.add(216L);
		assemblyIds.add(194L);
		assemblyIds.add(163L);
		assemblyIds.add(238L);
		assemblyIds.add(179L);
		assemblyIds.add(180L);
		assemblyIds.add(217L);
		assemblyIds.add(239L);
		assemblyIds.add(331L);
		assemblyIds.add(195L);
		assemblyIds.add(196L);
		assemblyIds.add(207L);
		assemblyIds.add(354L);
		assemblyIds.add(356L);
		assemblyIds.add(355L);
		assemblyIds.add(357L);
		assemblyIds.add(129L);
		assemblyIds.add(344L);
		
		List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(assemblyIds);
		for(Object[] data:list){
		System.out.println("P: "+data[0]+" A: "+data[1]);
		}
		
	}
	/*public void testfindLatestParliamentForAssembly1(){
		List<Long> assemblyIds = new ArrayList<Long>();
		assemblyIds.add(1l);assemblyIds.add(16l);assemblyIds.add(67l);assemblyIds.add(68l);assemblyIds.add(70l);assemblyIds.add(87l);assemblyIds.add(94l);assemblyIds.add(112l);assemblyIds.add(140l);assemblyIds.add(159l);assemblyIds.add(173l);assemblyIds.add(176l);assemblyIds.add(205l);assemblyIds.add(212l);assemblyIds.add(227l);assemblyIds.add(233l);assemblyIds.add(238l);assemblyIds.add(246l);assemblyIds.add(248l);assemblyIds.add(252l);assemblyIds.add(254l);assemblyIds.add(265l);assemblyIds.add(276l);assemblyIds.add(291l);assemblyIds.add(298l);
		List<Long> list = delimitationConstituencyAssemblyDetailsDAO.findAllParliamentForAssembliesForTheGivenYear(assemblyIds,2012l);
		for(Long id:list)
		System.out.println(id);
		
	}*/
}