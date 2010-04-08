package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.model.Constituency;

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
	
	public void testGetAllAssembliesOfParliament(){
		List list = delimitationConstituencyAssemblyDetailsDAO.getAllAssembliesOfParliament(404l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}
	
	public void testParliamentIdByAssemblyId(){
		List list = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(3382l,2004l);
		Assert.assertEquals(2, list.size());
	}
}