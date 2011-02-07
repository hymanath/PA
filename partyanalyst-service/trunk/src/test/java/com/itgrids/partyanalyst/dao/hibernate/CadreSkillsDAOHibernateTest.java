package com.itgrids.partyanalyst.dao.hibernate;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICadreSkillsDAO;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;
import com.itgrids.partyanalyst.model.CadreSkills;


public class CadreSkillsDAOHibernateTest extends BaseDaoTestCase {

	private ICadreSkillsDAO cadreSkillsDAO;

	public ICadreSkillsDAO getCadreSkillsDAO() {
		return cadreSkillsDAO;
	}

	public void setCadreSkillsDAO(ICadreSkillsDAO cadreSkillsDAO) {
		this.cadreSkillsDAO = cadreSkillsDAO;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCadreSkills(){
		
		List<Long> cadreIds = new ArrayList<Long>();
		cadreIds.add(0l);
		cadreIds.add(1l);
		
		List<Long> skillIds = new ArrayList<Long>();
		skillIds.add(0l);
		skillIds.add(1l);
		
		//List result = cadreSkillsDAO.getCadreBySkillAndCadreIds(1l, cadreIds);
		//List result = cadreSkillsDAO.getCadreBySkillListAndCadreIds(skillIds, cadreIds);
		//List result = cadreSkillsDAO.getCadreIdsByCadreSkillAndUser(7l, 1l);
		List result = cadreSkillsDAO.getCadreIdsByCadreSkillListAndUser(7l, skillIds);
		
		System.out.println(" Result Size :" + result.size());
	}
	
	public void testFindByCadreId()
	{
		List<CadreSkills> result=cadreSkillsDAO.findByCadreId(12L);
		for(CadreSkills obj: result){
			
			System.out.print("id:"+ obj.getCadreSkillId()+"\t");
			System.out.print("skill Id"+obj.getPartyCadreSkills().getPartyCadreSkillId()+"\t");
			System.out.print("cadre Id:"+obj.getCadre().getCadreId()+"\t");			
			
		}
	}		
				
	public void testDeleteSkillsByCadreId()
	{
		int result=cadreSkillsDAO.deleteSkillsByCadreId(4l);
		System.out.println("No of records deleted:"+result);
		setComplete();
		
	}
}
