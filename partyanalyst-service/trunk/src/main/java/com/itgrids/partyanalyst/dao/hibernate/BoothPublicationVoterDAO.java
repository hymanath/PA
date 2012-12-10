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

		/*public List<Voter> getVotersDetailsForPanchayatByPublicationId(
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
		}*/
	 public List<Voter> getVotersDetailsForPanchayatByPublicationId(
				Long panchayatId, Long publicationDateId, Integer startIndex,
				Integer maxRecords, String order, String columnName) {
			
			 Query query = getSession().createQuery("select BPV.voter from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.panchayat.panchayatId = :panchayatId  order by BPV.voter."+columnName+" "+order) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			  query.setParameter("panchayatId", panchayatId);
	 			query.setFirstResult(startIndex);
	 			query.setMaxResults(maxRecords);
	 			return query.list();
		}
		
		/*public List getVotersCountForPanchayat(Long panchayatId,Long publicationDateId){
			
			Query query = getSession()
					.createQuery(
							"select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and "
									+ " model.booth.boothId in(select distinct model1.booth.boothId from HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId and "
									+ " model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId ))");
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("panchayatId", panchayatId);

			return query.list();
			
		}*/
		
         public List getVotersCountForPanchayat(Long panchayatId,Long publicationDateId){
			
        	 Query query = getSession().createQuery("select count(BPV.voter.voterId) from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
 					" BPV.booth.panchayat.panchayatId = :panchayatId  ") ;
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
	/*@SuppressWarnings("unchecked")
	public List<Object[]> findImpFamilesBasedOnPanchayat(Long PanchayatId,Long publicationDateId){
		Object[] params = {PanchayatId,publicationDateId};
		return getHibernateTemplate().find("select model.voter.houseNo,count(model.voter.houseNo) from " +
				"BoothPublicationVoter model where model.voter.hamlet.hamletId in" +
				"(select model1.hamlet.hamletId from PanchayatHamlet model1 " +
				"where model1.panchayat.panchayatId = ? ) and model.booth.publicationDate.publicationDateId = ? " +
				" group by model.voter.hamlet.hamletId,model.voter.houseNo", params);
		}*/
	@SuppressWarnings("unchecked")
	public List<Object[]> findImpFamilesBasedOnPanchayat(Long PanchayatId,Long publicationDateId){
		Object[] params = {publicationDateId,PanchayatId};
		
	return getHibernateTemplate().find("select BPV.voter.houseNo,count(BPV.voter.houseNo)  from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = ? and " +
				" BPV.booth.panchayat.panchayatId = ?  group by BPV.voter.hamlet.hamletId,BPV.voter.houseNo ", params) ;
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
	
	/*public List<Object[]> getVotersCountForPanchayatByPublicationId(Long panchayatId,Long publicationDateId){
		Query query = getSession().createQuery("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.boothId in(select distinct model1.booth.boothId from HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId ))  group by model.voter.gender ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("panchayatId", panchayatId);
		  return query.list();
	}*/
	
	public List<Object[]> getVotersCountForPanchayatByPublicationId(Long panchayatId,Long publicationDateId){
		Query query = getSession().createQuery("select count(*),BPV.voter.gender from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" BPV.booth.panchayat.panchayatId = :panchayatId group by BPV.voter.gender ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("panchayatId", panchayatId);
		  return query.list();
	}
	
	public List<Object[]> getVotersCountFromLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId){
		Query query = getSession().createQuery("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId in(select distinct model1.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model1 where model1.assemblyLocalElectionBodyId = :assemblyLclElecBodyId ) group by model.voter.gender ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("assemblyLclElecBodyId", assemblyLclElecBodyId);
		  return query.list();
	}


	// Total count 
			
	public Long findTotalVotersCastInfoByConstituencyAndPublicationDate(Long constituencyId, Long publicationDate){
	Object[] params = {constituencyId, publicationDate};
	Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.constituency.constituencyId = ? and model.booth.publicationDate.publicationDateId = ? ");
	query.setParameter(0,constituencyId);
	query.setParameter(1, publicationDate);
	return (Long)query.uniqueResult();
	}
			
	//caste info for Mandal


	public List findVotersCastInfoByMandalAndPublicationDate(Long mandalId, Long publicationDate){
	Object[] params = {mandalId, publicationDate};
	return getHibernateTemplate().find("select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where model.booth.tehsil.tehsilId = ? and model.booth.publicationDate.publicationDateId = ? group by model.voter.cast,model.voter.gender order by model.voter.cast ", params);
	}		
	
	
	
	//caste info for Panchayat
	/*public List findVotersCastInfoByPanchayatAndPublicationDate(Long panchayatId, Long publicationDateId){
		
		Query query = getSession()
				.createQuery(
						"select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where " +
						"model.booth.publicationDate.publicationDateId = :publicationDateId and "+
						" model.booth.boothId in(select distinct model1.booth.boothId from " +
						" HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
						"and  model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from " +
						"PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId )) group by model.voter.cast,model.voter.gender order by model.voter.cast");
		
		
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
	
		return query.list();
		
	}*/
	
public List findVotersCastInfoByPanchayatAndPublicationDate(Long panchayatId, Long publicationDateId){
		

		Query query = getSession().createQuery("select count(BPV.voter.voterId),BPV.voter.gender,BPV.voter.cast from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" BPV.booth.panchayat.panchayatId = :panchayatId  group by BPV.voter.cast,BPV.voter.gender order by BPV.voter.cast ") ;
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
		  return query.list();
		
	}
	
	//caste info for PollingStation
		public List findVotersCastInfoByBoothIdAndPublicationDate(Long boothId, Long publicationDateId){
			Object[] params = {boothId, publicationDateId};
			return getHibernateTemplate().find("select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where model.booth.boothId= ? and model.booth.publicationDate.publicationDateId = ? group by model.voter.cast,model.voter.gender order by model.voter.cast", params);
		}	
		
		//caste info for Constituency

		
		public List getGenderWiseCountInConstituency(Long constituencyId, Long publicationDate)
		{
			Object[] params = {constituencyId, publicationDate};
			Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where model.booth.constituency.constituencyId = ? and model.booth.publicationDate.publicationDateId = ? group by model.voter.cast,model.voter.gender order by model.voter.cast");
			query.setParameter(0,constituencyId);
			query.setParameter(1, publicationDate);
			return query.list();	
			
		}
		
		public List<Object[]> getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
				 Long constituencyId ,Long publicationDateId, Long startAge,
				Long endAge) {
			
			String qyeryString = "select count(*),model.voter.gender from BoothPublicationVoter " +
					"model where model.booth.publicationDate.publicationDateId = ? " +
					"and model.booth.constituency.constituencyId = ? " +
					"and model.voter.age >= "+startAge+" and model.voter.age<= "+endAge+" group by model.voter.gender";
			
			Query query = getSession().createQuery(qyeryString);
			
			
			query.setParameter(0, publicationDateId);
			query.setParameter(1, constituencyId);
			
			return query.list();
			
		}
		
		
		
		public List<Object[]> getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
				Long tehsilId, Long publicationDateId, Long startAge,
				Long endAge){
			
			
			String queryString = "select count(*),model.voter.gender from BoothPublicationVoter " +
					"model where model.booth.publicationDate.publicationDateId = ? " +
					"and model.booth.tehsil.tehsilId = ? " +
					"and model.voter.age >= "+startAge+" and model.voter.age<= "+endAge+" group by model.voter.gender";
			
			Query query = getSession().createQuery(queryString);
			
			query.setParameter(0, publicationDateId);
			query.setParameter(1, tehsilId);
			
			return query.list();
			
		}
		
/*		public List<Object[]> getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
				Long panchayatId, Long publicationDateId , Long startAge, Long endAge) {		
			
			Query query = getSession()
					.createQuery(
							"select count(*),model.voter.gender from BoothPublicationVoter model where " +
							"model.booth.publicationDate.publicationDateId = :publicationDateId and model.voter.age between "+startAge+" and "+endAge+
							" and model.booth.boothId in(select distinct model1.booth.boothId from " +
							" HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
							"and  model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from " +
							"PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId )) group by model.voter.gender");
			
			
			query.setParameter("publicationDateId",publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			
			return query.list();		
			
		}*/
		
		public List<Object[]> getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
				Long panchayatId, Long publicationDateId , Long startAge, Long endAge) {		
				
			Query query = getSession().createQuery("select count(*),BPV.voter.gender from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId  and BPV.voter.age between "+startAge+" and "+endAge+" and " +
					" BPV.booth.panchayat.panchayatId = :panchayatId  group by BPV.voter.gender  ") ;
			query.setParameter("publicationDateId",publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			  return query.list();
			
		}
		
		public List<Object[]> getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
				Long boothId, Long publicationDateId, Long startAge, Long endAge) {
			
			Query query = getSession().createQuery("select count(*) ,model.voter.gender " +
					"from BoothPublicationVoter model where model.booth.boothId = ? and " +
					"model.booth.publicationDate.publicationDateId = ? " +
					"and model.voter.age >= "+startAge+" and model.voter.age<= "+endAge+
					" group by model.voter.gender");
			
			query.setParameter(0, boothId);
			query.setParameter(1, publicationDateId);
			
			return query.list();
			
		}
		
		public List<Object[]> getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
				Long localElectionBodyId, Long publicationDateId, Long startAge, Long endAge) {
			
			Query query = getSession().createQuery("select count(*) ,model.voter.gender " +
					"from BoothPublicationVoter model where model.booth.localBody.localElectionBodyId = ? and " +
					"model.booth.publicationDate.publicationDateId = ? " +
					"and model.voter.age >= "+startAge+" and " +
					"model.voter.age<= "+endAge+" group by model.voter.gender");
			
			query.setParameter(0, localElectionBodyId);
			query.setParameter(1, publicationDateId);
			
			return query.list();
			
		}
		
	public Long getTotalVotersCount(Long id,Long publicationDateId,String type){
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		return (Long)queryObj.uniqueResult();
	}
	
	public Long getVotersCountForLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId){
		Query query = getSession().createQuery("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId in(select distinct model1.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model1 where model1.assemblyLocalElectionBodyId = :assemblyLclElecBodyId ) ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("assemblyLclElecBodyId", assemblyLclElecBodyId);
		  return (Long)query.uniqueResult();
	}
	public List<Object[]> findAllImpFamiles(Long id,Long publicationDateId,String type,String queryString){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId) ,model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		query.append(" group by model.booth.boothId,model.voter.houseNo");
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
	  return queryObj.list();
	}
	
	public List<Object[]> getVotersImpFamilesForLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId,String queryString){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId) ,model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId in(select distinct model1.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model1 where model1.assemblyLocalElectionBodyId = :assemblyLclElecBodyId ) ") ;
		query.append(" group by model.booth.boothId,model.voter.houseNo ");
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("assemblyLclElecBodyId", assemblyLclElecBodyId);
		  return queryObj.list();
	}
	
	/*public List<Object[]> getImpFamilesForPanchayatByPublicationId(Long panchayatId,Long publicationDateId,String queryString){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId),model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.boothId in(select distinct model1.booth.boothId from HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId =:panchayatId ))  group by model.booth.boothId,model.voter.houseNo ") ;
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("panchayatId", panchayatId);
		  return queryObj.list();
	}*/
	
	public List<Object[]> getImpFamilesForPanchayatByPublicationId(Long panchayatId,Long publicationDateId,String queryString){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId),model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.panchayat.panchayatId = :panchayatId    group by model.booth.boothId,model.voter.houseNo ") ;
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("panchayatId", panchayatId);
		  return queryObj.list();
	}
	
/*	public List findFamiliesVotersInfoForPanchayat(Long id,Long publicationDateId) {
				Object[] params = {publicationDateId,publicationDateId,id};
				return getHibernateTemplate().find("select model.voter.firstName, model.voter.lastName, model.voter.houseNo, model.voter.age, " +
						"model.voter.cast,model.booth.boothId from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? " +
						" and model.booth.boothId in(select distinct model1.booth.boothId from HamletBoothPublication model1 where model1.booth.publicationDate.publicationDateId = ? and " +
						" model1.hamlet.hamletId in(select distinct model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId = ? ))  order by model.voter.voterId ",params) ;
		}*/
	
	public List findFamiliesVotersInfoForPanchayat(Long id,Long publicationDateId) {
		Object[] params = {publicationDateId,id};
		return getHibernateTemplate().find("select  model.voter.firstName, model.voter.lastName, model.voter.houseNo, model.voter.age,model.voter.cast,model.booth.boothId  " +
				" from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? and " +
				" model.booth.panchayat.panchayatId = ? order by model.voter.voterId ",params) ;
	
	}
	
	public List findFamiliesVotersInfoForBooth(Long id,Long publicationDateId) {
		Object[] params = {publicationDateId,id};
		return getHibernateTemplate().find("select model.voter.firstName, model.voter.lastName, model.voter.houseNo, model.voter.age, " +
				"model.voter.cast,model.booth.boothId from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? " +
				" and model.booth.boothId = ?",params) ;
     }
	
	public List<Voter> findFamiliesInfo(Long boothId,Long publicationDateId,String houseNo) {
		Object[] params = {publicationDateId,boothId,houseNo};
		return getHibernateTemplate().find("select model.voter from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? " +
				" and model.booth.boothId = ? and model.voter.houseNo = ?",params) ;
     }
	
	//caste Info For Municipality
	public List getVotersCastInfoFromLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId in(select distinct model1.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model1 where model1.assemblyLocalElectionBodyId = :assemblyLclElecBodyId ) group by model.voter.cast,model.voter.gender order by model.voter.cast");
		 query.setParameter("publicationDateId", publicationDateId);
		 query.setParameter("assemblyLclElecBodyId", assemblyLclElecBodyId);
		  return query.list();
	}

	
	}
