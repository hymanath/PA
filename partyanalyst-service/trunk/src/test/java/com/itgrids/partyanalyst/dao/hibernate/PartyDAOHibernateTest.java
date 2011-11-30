package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyDAOHibernateTest extends BaseDaoTestCase{

	private IPartyDAO partyDAO;

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	
	/*public void testGetAllParties(){
		List<Party> parties = partyDAO.findByShortNames("TRS");
		for(Party result : parties){
			System.out.println(result.getShortName());
		}
	//	Assert.assertEquals(2, parties.size());
	}
	
	@Test
	public void testFindPartysInState(){
		
		List<SelectOptionVO> partiesLst = new ArrayList<SelectOptionVO>();
		List<Party> party = partyDAO.findStaticPartiesByRecognization("NP",IConstants.NATIONAL_STATIC_PARTIES);
		for(Party part:party){
			//System.out.println(" Party Id :" + part.getPartyId() + "  " + " Party :" + part.getLongName() + " Short Name :" + part.getShortName());
			
			SelectOptionVO option = new SelectOptionVO();
			option.setId(part.getPartyId());
			option.setName(part.getLongName());
			
			partiesLst.add(option);
		}
		List<SelectOptionVO> partiesLst1 = new ArrayList<SelectOptionVO>();
		partiesLst1.add(new SelectOptionVO(1L,"a"));
		partiesLst1.add(new SelectOptionVO(2L,"b"));
		
		partiesLst.addAll(partiesLst1);
		
		for(SelectOptionVO optn:partiesLst){
			System.out.println(" ID :" + optn.getId());
		}
	}*/
	
	/*public void testGetPartyIdByPartyName(){
		List parties = partyDAO.findPartyIdByShortName("TRS");
		System.out.println(parties.get(0));
	}*/
	public void testfindAllPartyNames()
	{
		List<Object[]> list=partyDAO.findAllPartyNames();
		System.out.println(list.size());
		for(Object[] params:list)
		{
			System.out.println(params[0]);
			System.out.println(params[1]);
			System.out.println(params[2]);

		}
	}
	
/*	public void testgetPartyDetails()
	{
		
		List<Party> result = partyDAO.getPartyDetails(362l);
		for (Party party : result) {
			System.out.println(party.getLongName());
			System.out.println(party.getPartyFlag());
			System.out.println(party.getPartyRecognization());
			System.out.println(party.getShortName());
		}
		System.out.println();
	}*/
}
