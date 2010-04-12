package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.Group;


public class ElectionAllianceDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionAllianceDAO electionAllianceDAO;

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO){
		this.electionAllianceDAO = electionAllianceDAO;
	}
	
	//@Test
	/*public void testFindByElectionYearAndType(){
		List<ElectionAlliance> alliances = electionAllianceDAO.findByElectionYearAndType("2009", new Long(2));
		Assert.assertEquals(6, alliances.size());
	}*/
	
	public void testFindAllianceGroupsInElections(){
		List list = electionAllianceDAO.findAllianceGroupsInElections("1,2");
		
		/*for(int i=0; i<list.size(); i++){
			Object[] values = (Object[])list.get(0);
			String group = values[0].toString();
			AllianceGroup eles = (AllianceGroup)values[1];
			
				System.out.println(group+"::"+eles.getParty().getShortName());
		}*/
		
		
		for(Object[] gp:(List<Object[]>)list){
			 String elecType = gp[0].toString();
			 String elecYear = gp[1].toString();
			// List<AllianceGroup> list1 = ((Group)gp[2]).getAllianceGroups();
			/*for(AllianceGroup al:list1)
				System.out.println(((Group)gp[2]).getGroupName()+"::"+al.getParty().getShortName());*/
				
		}
		
		
	}

	
}
