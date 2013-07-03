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
	/*public void testfindLatestParliamentForAssembly(){
		List<Long> assemblyIds = new ArrayList<Long>();
		assemblyIds.add(239l);
		assemblyIds.add(341l);
		assemblyIds.add(238l);
		assemblyIds.add(285l);
		assemblyIds.add(286l);
		List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(assemblyIds);
		for(Object[] data:list){
		System.out.println("P: "+data[0]+" A: "+data[1]);
		}
		
	}*/
	public void testfindLatestParliamentForAssembly(){
		List<Long> assemblyIds = new ArrayList<Long>();
		assemblyIds.add(1l);assemblyIds.add(16l);assemblyIds.add(67l);assemblyIds.add(68l);assemblyIds.add(70l);assemblyIds.add(87l);assemblyIds.add(94l);assemblyIds.add(112l);assemblyIds.add(140l);assemblyIds.add(159l);assemblyIds.add(173l);assemblyIds.add(176l);assemblyIds.add(205l);assemblyIds.add(212l);assemblyIds.add(227l);assemblyIds.add(233l);assemblyIds.add(238l);assemblyIds.add(246l);assemblyIds.add(248l);assemblyIds.add(252l);assemblyIds.add(254l);assemblyIds.add(265l);assemblyIds.add(276l);assemblyIds.add(291l);assemblyIds.add(298l);
		List<Long> list = delimitationConstituencyAssemblyDetailsDAO.findAllParliamentForAssembliesForTheGivenYear(assemblyIds,2012l);
		for(Long id:list)
		System.out.println(id);
		
	}
}