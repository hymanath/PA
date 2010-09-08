package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreParticipatedTrainingCampsDAO;
import com.itgrids.partyanalyst.model.CadreParticipatedTrainingCamps;


public class CadreParticipatedTrainingCampsDAOHibernateTest extends BaseDaoTestCase {
	ICadreParticipatedTrainingCampsDAO cadreParticipatedTrainingCampsDAO;

	public ICadreParticipatedTrainingCampsDAO getCadreParticipatedTrainingCampsDAO() {
		return cadreParticipatedTrainingCampsDAO;
	}

	public void setCadreParticipatedTrainingCampsDAO(
			ICadreParticipatedTrainingCampsDAO cadreParticipatedTrainingCampsDAO) {
		this.cadreParticipatedTrainingCampsDAO = cadreParticipatedTrainingCampsDAO;
	} 
	
	/*public void testFindByCadreId()
	{
		List<CadreParticipatedTrainingCamps> result=cadreParticipatedTrainingCampsDAO.findByCadreId(12L);
		for(CadreParticipatedTrainingCamps obj: result){
			
			System.out.print(" id:"+ obj.getCadreParticipatedTrainingCampId()+"\t");
			System.out.print("camp Id"+obj.getPartyTrainingCamps().getPartyTrainingCampsId()+"\t");
			System.out.print("cadre Id:"+obj.getCadre().getCadreId()+"\t");			
			
		}	
				
	}*/
	
	public void testDeleteSkillsByCadreId()
	{
		int result=cadreParticipatedTrainingCampsDAO.deleteCadreTrainingCamps(3l);
		System.out.println("No of records deleted:"+result);
		setComplete();
		
	}
	
	
	
}
