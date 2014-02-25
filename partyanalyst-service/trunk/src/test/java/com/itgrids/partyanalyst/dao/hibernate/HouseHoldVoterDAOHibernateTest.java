package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
import com.itgrids.partyanalyst.model.HouseHoldVoter;

public class HouseHoldVoterDAOHibernateTest extends BaseDaoTestCase{
	
	private IHouseHoldVoterDAO houseHoldVoterDAO;

	public void setHouseHoldVoterDAO(IHouseHoldVoterDAO houseHoldVoterDAO) {
		this.houseHoldVoterDAO = houseHoldVoterDAO;
	}



	public void test(){
		//System.out.println("hhOptionsDAO");
		
		/*Long houseHoldId= houseHoldVoterDAO.getHouseHoldIdForVoter(6979790l);
		System.out.println(houseHoldId);*/
		
		/*int count = houseHoldVoterDAO.childMembersDelete(10l);
		System.out.println(count);*/
		
		/*List<HouseHoldVoter> list=houseHoldVoterDAO.getHouseHoldsVoterdDetailsByHouseHoldId1(51l);
		
		System.out.println(list.size());*/
		List<Long> vtrIds=new ArrayList<Long>();
		vtrIds.add(6979790l);
		List<Long> list = houseHoldVoterDAO.getVoterIdsExistByVoterIds(vtrIds);
		for(Long ni:list){
			System.out.println(ni);
		}
	}
	
	

}
