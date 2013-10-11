package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
	public List<Object[]> getConstituencyIdsAndPercentages(String censusParam,Long stateId) {
		return getHibernateTemplate().find("select model.constituencyId, "+ censusParam +" from ConstituencyCensusDetails model where model.stateId = ?",stateId);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyIdsAndPercentagesOfADistrict(String censusParam,List<Long> constituencyIds)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.constituencyId, "+ censusParam +" from ConstituencyCensusDetails model ");
		query.append("where model.constituencyId in(:constituencyIds)");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("constituencyIds", constituencyIds);
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyCensusDetails> getCensusConstituencyByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find(" from ConstituencyCensusDetails model where model.constituencyId = ? ",constituencyId);	
	}
	
	
	public List<ConstituencyCensusDetails> getCensusConstituencyByConstituencyIdAndYears(
			Long constituencyId, List<Long> years)	{
		
		Query query = getSession().createQuery("from ConstituencyCensusDetails  model " +
				"where model.constituencyId = :constituencyId and " +
				"model.year in(:years) order by model.year");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("years", years);
		
		return query.list();
		
		
	}
}
