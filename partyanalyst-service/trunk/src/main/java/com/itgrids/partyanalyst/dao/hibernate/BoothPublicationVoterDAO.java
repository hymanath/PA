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
		
		
		public List getVotersCountForPanchayat(Long panchayatId,Long publicationDateId){
			
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
			

		
		/**
		 * @return object[]
		 * @author prasad
		 * @param constituencyId
		 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPublicationDetailsBasedOnConstituency(Long constituencyId)
	{
		return getHibernateTemplate().find("select distinct model.publicationDate.publicationDateId, " +
				"model.publicationDate.date from Booth model where " +
				"model.constituency.constituencyId = ? order by model.publicationDate.date)",constituencyId);
	}
	/**
	 * @return object[]
	 * @author prasad
	 * @param PanchayatId
	 * @param publicationDateId
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findImpFamilesBasedOnPanchayat(Long PanchayatId,Long publicationDateId){
		Object[] params = {PanchayatId,publicationDateId};
		return getHibernateTemplate().find("select model.voter.houseNo,count(model.voter.houseNo) from " +
				"BoothPublicationVoter model where model.voter.hamlet.hamletId in" +
				"(select model1.hamlet.hamletId from PanchayatHamlet model1 " +
				"where model1.panchayat.panchayatId = ? ) and model.booth.publicationDate.publicationDateId = ? " +
				" group by model.voter.hamlet.hamletId,model.voter.houseNo", params);
		}
	
	/**
	 * @return object[]
	 * @author prasad
	 * @param constituencyId
	 * @param publicationDateId
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findImpFamilesBasedOnConstituencyId(Long constituencyId,Long publicationDateId){
		Object[] params = {constituencyId,publicationDateId};
		return getHibernateTemplate().find("select model.voter.houseNo,count(model.voter.houseNo) from " +
				"BoothPublicationVoter model where model.booth.constituency.constituencyId = ? and" +
				" model.booth.publicationDate.publicationDateId = ? " +
				" group by model.voter.hamlet.hamletId,model.voter.houseNo", params);
		}
	
	/**
	 * @return object[]
	 * @author prasad
	 * @param boothId
	 * @param publicationDateId
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findImpFamilesBasedOnBoothId(Long boothId,Long publicationDateId){
		Object[] parms = {boothId,publicationDateId};
		return getHibernateTemplate().find("select model.voter.houseNo,count(model.voter.houseNo) from " +
				"BoothPublicationVoter model where model.booth.boothId= ? and " +
				"model.booth.publicationDate.publicationDateId = ? " +
				"group by model.voter.hamlet.hamletId,model.voter.houseNo",parms);
		
	}
	
	public List<Object[]> getVotersCountByPublicationId(String type,Long id,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		query.append(" group by model.voter.gender ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		queryObj.setParameter("publicationDateId", publicationDateId);
		return queryObj.list();
	}
	
	public List<Object[]> getVotersCountForPanchayatByPublicationId(Long panchayatId,Long publicationDateId){
		Query query = getSession().createQuery("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.boothId in(select distinct model1.booth.boothId from HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId ))  group by model.voter.gender ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("panchayatId", panchayatId);
		  return query.list();
	}
	
	public List<Object[]> getVotersCountFromLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId){
		Query query = getSession().createQuery("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId in(select distinct model1.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model1 where model1.assemblyLocalElectionBodyId = :assemblyLclElecBodyId )") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("assemblyLclElecBodyId", assemblyLclElecBodyId);
		  return query.list();
	}
}
