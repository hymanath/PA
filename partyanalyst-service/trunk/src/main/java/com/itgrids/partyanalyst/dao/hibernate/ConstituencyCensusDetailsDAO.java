package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.model.ConstituencyCensusDetails;

public class ConstituencyCensusDetailsDAO extends GenericDaoHibernate<ConstituencyCensusDetails,Long> implements IConstituencyCensusDetailsDAO {
	
	public ConstituencyCensusDetailsDAO()
	{
		super(ConstituencyCensusDetails.class);
	}

	 @SuppressWarnings("unchecked")
	 public List<Object[]> findConstituencyWiseCensusDetails(Long stateId,Long constituencyId,Long year){
	 Object[] params = {stateId,constituencyId,year};
	 return getHibernateTemplate().find("select model.totalPopulation,model.populationSC,model.populationST,model.populationLiterates,model.populationIlliterates,model.workingPopulation, "+
				" model.nonWorkingPopulation from ConstituencyCensusDetails model where model.stateId = ? and model.constituencyId = ? and model.year = ? and model.tru = 'Total'",params);
	}

	@SuppressWarnings("unchecked")
	public List<Long> checkForConstituencyExistance(Long constituencyId) {
		return getHibernateTemplate().find("select model.constituencyId from ConstituencyCensusDetails model where model.constituencyId = ?",constituencyId);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyIdsAndPercentages(String censusParam) {
		return getHibernateTemplate().find("select model.constituencyId, "+ censusParam +" from ConstituencyCensusDetails model");	
	}
	
}
