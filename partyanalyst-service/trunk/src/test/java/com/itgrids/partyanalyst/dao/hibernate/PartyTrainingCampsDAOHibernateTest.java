package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICadreParticipatedTrainingCampsDAO;

public class PartyTrainingCampsDAOHibernateTest extends BaseDaoTestCase {

	private ICadreParticipatedTrainingCampsDAO cadreParticipatedTrainingCampsDAO;

	public ICadreParticipatedTrainingCampsDAO getCadreParticipatedTrainingCampsDAO() {
		return cadreParticipatedTrainingCampsDAO;
	}

	public void setCadreParticipatedTrainingCampsDAO(
			ICadreParticipatedTrainingCampsDAO cadreParticipatedTrainingCampsDAO) {
		this.cadreParticipatedTrainingCampsDAO = cadreParticipatedTrainingCampsDAO;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetCadreParticipatedTrainingCamps(){
		
		List<Long> cadreIds = new ArrayList<Long>();
		cadreIds.add(1l);
		cadreIds.add(2l);
		
		List<Long> campIds = new ArrayList<Long>();
		campIds.add(1l);
		campIds.add(2l);
		
		//List results = cadreParticipatedTrainingCampsDAO.getCadreByCampsAndCadreIds(1l, cadreIds);
		//List results = cadreParticipatedTrainingCampsDAO.getCadreByCampsListAndCadreIds(campIds, cadreIds);	
		//List results = cadreParticipatedTrainingCampsDAO.getCadreIdsByCadreCampsAndUser(7l, 1l);
		List results = cadreParticipatedTrainingCampsDAO.getCadreIdsByCadreCampsListAndUser(7l, campIds);
		
		System.out.println(" Results Size :" + results.size());
	}
	
} 
