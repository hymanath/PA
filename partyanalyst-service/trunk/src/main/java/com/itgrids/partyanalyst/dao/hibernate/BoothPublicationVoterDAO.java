package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Voter;

public class BoothPublicationVoterDAO extends
		GenericDaoHibernate<BoothPublicationVoter, Long> implements
		IBoothPublicationVoterDAO {
	
	
	public BoothPublicationVoterDAO(){
		super(BoothPublicationVoter.class);
		
	}
 public List<BoothPublicationVoter> checkForVoterExistenceInBoothPublicationVoter(Long boothId , Long voterId){
		
		
		String queryString = "select model from BoothPublicationVoter model where model.booth.boothId = ? and model.voter.voterId = ?";
		
		Query query  = getSession().createQuery(queryString);
		
		
		query.setParameter(0, boothId);
		query.setParameter(1, voterId);
		
		return query.list();
		
		
	}
 
 
	public List<Voter> getVotersDetailsByBoothId(Long boothId, Integer startIndex,
			Integer maxRecords, String order, String columnName) {  
	 
		String queryString = "select model.voter from BoothPublicationVoter model " +
				"where model.booth.boothId = ? order by model.voter."+columnName+" "+order;

		Query query = getSession().createQuery(queryString);

		query.setParameter(0, boothId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);

		return query.list();
	 
 }  

	public List<Voter> getVotersDetailsForPanchayatByPublicationId(
			Long panchayatId, Long publicationDateId, Integer startIndex,
			Integer maxRecords, String order, String columnName) {
		
		Query query = getSession()
				.createQuery(
						"select model.voter from BoothPublicationVoter model where " +
						"model.booth.publicationDate.publicationDateId = :publicationDateId and "+
						" model.booth.boothId in(select distinct model1.booth.boothId from " +
						" HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
						"and  model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from " +
						"PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId )) order by model.voter."+columnName+" "+order);
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("panchayatId", panchayatId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		return query.list();
	}
	
	
	public List getVotersCountForPanchayatByPublicationId(Long panchayatId,Long publicationDateId){
		
		Query query = getSession()
				.createQuery(
						"select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and "
								+ " model.booth.boothId in(select distinct model1.booth.boothId from HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId and "
								+ " model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId ))");
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("panchayatId", panchayatId);

		return query.list();
		
	}
	
	public List getVotersCountByBoothId(Long boothId){
		
		String queryString = "select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.boothId = ?";

		Query query = getSession().createQuery(queryString);

		query.setParameter(0, boothId);

		return query.list();
		
		
	}
	

	
}
