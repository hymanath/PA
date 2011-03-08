package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IGroupDAO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.Group;
import com.itgrids.partyanalyst.utils.IConstants;


public class ElectionAllianceDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionAllianceDAO electionAllianceDAO;
	private IGroupDAO groupDAO;
	
	public IGroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO){
		this.electionAllianceDAO = electionAllianceDAO;
	}
	
	//@Test
	/*public void testFindByElectionYearAndType(){
		List<ElectionAlliance> alliances = electionAllianceDAO.findByElectionYearAndType("2009", new Long(2));
		Assert.assertEquals(6, alliances.size());
	}
	
	public void testFindAllianceGroupsInElections(){
		List list = electionAllianceDAO.findAllianceGroupsInElections("1,2");
		
		for(int i=0; i<list.size(); i++){
			Object[] values = (Object[])list.get(0);
			String group = values[0].toString();
			AllianceGroup eles = (AllianceGroup)values[1];
			
				System.out.println(group+"::"+eles.getParty().getShortName());
		}
		
		
		for(Object[] gp:(List<Object[]>)list){
			 String elecType = gp[0].toString();
			 String elecYear = gp[1].toString();
			// List<AllianceGroup> list1 = ((Group)gp[2]).getAllianceGroups();
			for(AllianceGroup al:list1)
				System.out.println(((Group)gp[2]).getGroupName()+"::"+al.getParty().getShortName());
				
		}
		
		
	}
	
	public void testFindAllianceGroupsInElections(){
		List list = electionAllianceDAO.findAllianceGroupsInElections("2");
		Group group = null;
		for(Object[] values:(List<Object[]>)list){
			group = groupDAO.get((Long)values[3]);
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]);
			System.out.println(group.getAllianceGroups().size());
		}
		
		
	}*/
	
	/*public void testGetGroups(){
		List list = electionAllianceDAO.findAllGroupsForAnElection("Assembly", "2009");
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				Object[] params = (Object[])list.get(i);
				System.out.println(" Group Name :" + (String)params[0]);
				System.out.println(" Group Id   :" + (Long)params[1]);
			}
		}
	}*/
	
	public void testGetElectionYears(){
		List list = electionAllianceDAO.getAllAllianceElectionYearsForAParty(885l,IConstants.ELECTION_SUBTYPE_MAIN,1l);		
		for(int i=0;i<list.size();i++){
			Object[] params = (Object[])list.get(i);
			System.out.println(params[0]+"-----"+params[1]);	
		}
	}

	
}
