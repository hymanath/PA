package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.utils.IConstants;

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
		return getHibernateTemplate().find("select distinct model.booth.publicationDate.publicationDateId, " +
				"model.booth.publicationDate.date from BoothPublicationVoter model where " +
				"model.booth.constituency.constituencyId = ? order by model.booth.publicationDate.year desc)",constituencyId);
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
/*	public List<Object[]> getVotersCountByPublicationId(String type,Long id,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		else if(type.equalsIgnoreCase("ward"))
			query.append(" model.booth.localBodyWard.constituencyId = :id ");
		query.append(" group by model.voter.gender ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		queryObj.setParameter("publicationDateId", publicationDateId);
		return queryObj.list();
	}
	*/
	
	public List<Object[]> getVotersCountByPublicationId(String type,Long id,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		else if(type.equalsIgnoreCase("ward"))
			query.append(" model.booth.localBodyWard.constituencyId = :id ");
		query.append(" group by model.voter.gender ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		queryObj.setParameter("publicationDateId", publicationDateId);
		return queryObj.list();
	}
	
/*	public List<Object[]> getVotersDetailsForBoothForPublication(Long publicationId,String partNo,Long tehsilId){
		
		
		Query query = getSession().createQuery("select sum(model.booth.maleVoters),sum(model.booth.femaleVoters),sum(model.booth.totalVoters) from BoothPublicationVoter model " +
				" where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.tehsil.tehsilId = :tehsilId and model.booth.partNo = :partNo");
		
		query.setParameter("publicationDateId", publicationId);
		query.setParameter("tehsilId", tehsilId);
		query.setParameter("partNo", partNo);
		
		return query.list();
		
		
	}*/
	
	public List<Object[]> getVotersDetailsForBoothForPublication(Long publicationId,String partNo,Long tehsilId){
		
		
		Query query = getSession().createQuery("select sum(model.maleVoters),sum(model.femaleVoters),sum(model.totalVoters) from Booth model " +
				" where model.publicationDate.publicationDateId = :publicationDateId and " +
				" model.tehsil.tehsilId = :tehsilId and model.partNo = :partNo");
		
		query.setParameter("publicationDateId", publicationId);
		query.setParameter("tehsilId", tehsilId);
		query.setParameter("partNo", partNo);
		
		return query.list();
		
		
	}
	
	public Long findVotersCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || 
				locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findFamiliesCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.voter.houseNo) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || 
				locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		str.append(" group by model.booth.boothId ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findVotersGenderWiseCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.voter.voterId),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");			
		else if(locationType.equalsIgnoreCase("localElectionBody") || 
				locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		
		str.append(" group by model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCastCategoryWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.casteCategoryGroup.casteCategory.categoryName, count(model.voter.voterId) from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		
		str.append(" group by model2.casteState.casteCategoryGroup.casteCategory.casteCategoryId order by model2.casteState.casteCategoryGroup.casteCategory.categoryName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.caste.casteName,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		str.append(" group by model2.casteState.caste.casteId,model.voter.gender order by model2.casteState.caste.casteName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	//Party Wise getCastAndGenderWiseVotersCountByPublicationIdInALocation
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.party.shortName,model.voter.gender,count(model.voter.voterId),model2.party.partyId from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		str.append("group by model2.party.partyId,model.voter.gender order by model2.party.shortName ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
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
	return getHibernateTemplate().find("select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where model.booth.tehsil.tehsilId = ? and model.booth.localBody is null and model.booth.publicationDate.publicationDateId = ? group by model.voter.cast,model.voter.gender order by model.voter.cast ", params);
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
					"and model.booth.tehsil.tehsilId = ? and model.booth.localBody is null " +
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
			query.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		else if(type.equalsIgnoreCase("ward"))
			query.append(" model.booth.localBodyWard.constituencyId = :id ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		return (Long)queryObj.uniqueResult();
	}
	
	public Long getVotersCountForLocalElectionBody(Long lclElecBodyId,Long publicationDateId){
		Query query = getSession().createQuery("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId =:lclElecBodyId ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("lclElecBodyId", lclElecBodyId);
		  return (Long)query.uniqueResult();
	}
	public List<Object[]> findAllImpFamiles(Long id,Long publicationDateId,String type,String queryString){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId) ,model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		else if(type.equalsIgnoreCase("ward"))
			query.append(" model.booth.localBodyWard.constituencyId = :id ");
		query.append(" group by model.booth.boothId,model.voter.houseNo");
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
	  return queryObj.list();
	}
	
	public List<Object[]> getVotersImpFamilesForLocalElectionBody(Long lclElecBodyId,Long publicationDateId,String queryString){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId) ,model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId  = :lclElecBodyId  ") ;
		query.append(" group by model.booth.boothId,model.voter.houseNo ");
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("lclElecBodyId", lclElecBodyId);
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
		return getHibernateTemplate().find("select model.voter.name,model.voter.houseNo, model.voter.age,model.voter.cast,model.booth.boothId ,model.voter.voterId, " +
				" model.voter.gender,model.voter.age,model.booth.partNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? and " +
				" model.booth.panchayat.panchayatId = ? order by model.voter.voterId ",params) ;
	
	}
	
	public List findFamiliesVotersInfoForBooth(Long id,Long publicationDateId) {
		Object[] params = {publicationDateId,id};
		return getHibernateTemplate().find("select model.voter.name, model.voter.houseNo, model.voter.age, " +
				"model.voter.cast,model.booth.boothId,model.voter.voterId, model.voter.gender,model.voter.age,model.booth.partNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? " +
				" and model.booth.boothId = ?",params) ;
     }
	
	public List<Voter> findFamiliesInfo(Long boothId,Long publicationDateId,String houseNo) {
		Object[] params = {publicationDateId,boothId,houseNo};
		return getHibernateTemplate().find("select model.voter from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? " +
				" and model.booth.boothId = ? and model.voter.houseNo = ?",params) ;
     }
	
	//caste Info For Municipality
	public List getVotersCastInfoFromLocalElectionBody(Long lclElecBodyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId =:lclElecBodyId group by model.voter.cast,model.voter.gender order by model.voter.cast");
		 query.setParameter("publicationDateId", publicationDateId);
		 query.setParameter("lclElecBodyId", lclElecBodyId);
		  return query.list();
	}
	
	//get voter details By caste
	public List<Voter> getVoterDetailsByCaste(Long boothid,Long publicationDateId,String caste)
	{
		Object[] params={boothid,publicationDateId,caste};
	return getHibernateTemplate().find("select model.voter from BoothPublicationVoter model where model.booth.boothId=? and model.booth.publicationDate.publicationDateId = ? and model.voter.cast =? ",params);
	
	
	}

	
	//get voter details By caste_state ID
	public List<Voter> getVoterDetailsByCasteStateForBooth(Long boothid,Long publicationDateId,Long casteStateId)
	{
		Object[] params={boothid,publicationDateId,casteStateId};
	return getHibernateTemplate().find("select model.voter from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.boothId=? and model.booth.publicationDate.publicationDateId = ? and model2.casteState.casteStateId =? ",params);
	
	}
	
	//get voter details By caste_state ID
		public List<Voter> getVoterDetailsByCasteStateForPanchayat(Long panchayatId,Long publicationDateId,Long casteStateId)
		{
			Object[] params={panchayatId,publicationDateId,casteStateId};
		return getHibernateTemplate().find("select model.voter from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.panchayat.panchayatId=? and model.booth.publicationDate.publicationDateId = ? and model2.casteState.casteStateId =? ",params);
		
		}
	
	
	
	  public List<Long> getVoterStateId(Long voterId){
		  return getHibernateTemplate().find("select distinct model.booth.tehsil.district.state.stateId from BoothPublicationVoter model where model.voter.voterId =? ",voterId);
	  }
	  
	  public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString) {
			
			 Query query = getSession().createQuery("select model.voter,model.booth.boothId,model.booth.partNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			  query.setParameter("id", id);
	 			query.setFirstResult(startRecord);
	 			query.setMaxResults(maxRecords);
	 			return query.list();
		}
	  
	  public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString) {
			
			 Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			  query.setParameter("id", id);
	 			return query.list();
		}
	  
	  public List<Object[]> getCastWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select model2.casteState.caste.casteName,count(model2.casteState.caste.casteName),model2.casteState.casteStateId from BoothPublicationVoter model,UserVoterDetails model2 ");
			str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" model.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
			str.append("group by model2.casteState.caste.casteName order by model2.casteState.caste.casteName ");
			Query query =getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("locationId", locationId);
			
			return query.list();
		}
	  
	  public List<Object[]> getPartyWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select model2.casteState.caste.casteName,model2.party.shortName,count(model2.party.partyId),model2.party.partyId from BoothPublicationVoter model,UserVoterDetails model2 ");
			str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" model.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
			str.append("group by model2.casteState.caste.casteName,model2.party.partyId order by model2.casteState.caste.casteName,model2.party.shortName ");
			Query query =getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("locationId", locationId);
			
			return query.list();
		}
	  
	  public List<Object[]> getParties(Long userId,String locationType,Long locationId,Long publicationDateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select distinct model2.party.shortName,model2.party.partyId from BoothPublicationVoter model,UserVoterDetails model2 ");
			str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" model.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
			Query query =getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("locationId", locationId);
			
			return query.list();
		}
	  public List<Object[]> getConstituencies()
	  {
		return getHibernateTemplate().find("select distinct(model.booth.constituency.constituencyId), model.booth.constituency.name from BoothPublicationVoter model");  
	  }
	  
	  @SuppressWarnings("unchecked")
	public List<Long> getConstituenciesIds()
	  {
		return getHibernateTemplate().find("select distinct(model.booth.constituency.constituencyId) from BoothPublicationVoter model");  
	  }
	  
	  
	  //voter Family info
	  
	@SuppressWarnings("unchecked")
	public List<Long> getAllImpFamilesCount(String locationType, Long locationValue,Long publicationDateId)
	{
			StringBuilder str = new StringBuilder();
			str.append("select count(model.voter.houseNo) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.constituency.constituencyId = :id ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :id ");
			else if(locationType.equals(IConstants.LOCALELECTIONBODY) 
					|| locationType.equals("localElectionBody"))
				str.append(" model.booth.localBody.localElectionBodyId = :id ");
			else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				str.append(" model.booth.panchayat.panchayatId = :id ");
			else if(locationType.equalsIgnoreCase(IConstants.WARD))
				str.append(" model.booth.localBodyWard.constituencyId = :id ");
			
			str.append(" group by model.booth.boothId,model.voter.houseNo");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("id", locationValue);
		  return query.list();
	}
	
	public Long getVotersCountInAAgeRange(String locationType, Long locationValue,Long publicationDateId,Long ageFrom, Long ageTo,String gender)
	{
			StringBuilder str = new StringBuilder();
			str.append("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.constituency.constituencyId = :id ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :id ");
			else if(locationType.equals(IConstants.LOCALELECTIONBODY) || 
					locationType.equals("localElectionBody"))
				str.append(" model.booth.localBody.localElectionBodyId = :id ");
			else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				str.append(" model.booth.panchayat.panchayatId = :id ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" model.booth.localBodyWard.constituencyId = :id ");
			
			if(ageTo != null)
				str.append(" and model.voter.age between :ageFrom and :ageTo ");
			else
				str.append(" and model.voter.age > :ageFrom ");
			
			if(gender != null)
				str.append(" and model.voter.gender = :gender ");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("id", locationValue);
			query.setParameter("ageFrom",ageFrom);
			
			if(ageTo != null)
			query.setParameter("ageTo",ageTo);
			if(gender != null)
			query.setParameter("gender",gender);	
		  return (Long) query.uniqueResult();
	}
	/**
	 * This method is used for getting the voter contact details based on voter id
	 * @author Prasad Thiragabathina
	 * @param Long voterId
	 * @return List<Object[]>
	 */
	public List<BoothPublicationVoter> findVoterContactDetails(Long voterId) {
		
		return getHibernateTemplate().find("select model from BoothPublicationVoter model where model.voter.voterId = ? order by model.booth.publicationDate.date desc",voterId);
	}
	
	/**
	 * This method is used to get the familey members count based on house no and booth id
	 * @author Prasad Thiragabathina
	 * @param  String houseNo
	 * @param Long boothId
	 * @return List<Long>
	 */
	public List<Long> getFamilyMemberCount(String houseNo,Long boothId) {
		
		Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model where" +
				" model.voter.houseNo = :houseNo and model.booth.boothId =:boothId ");
		query.setParameter("houseNo", houseNo);
		query.setParameter("boothId", boothId);
		return query.list(); 
	} 
	
	public List<Long> getVotersCountForMultipleBooths(List<Long> ids,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		query.append(" model.booth.boothId in (:ids) ");
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("id", ids);
		return queryObj.list();
	}
	
	public List<Object[]> getGenderWiseVotersCountForWard(Long wardId,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		query.append(" model.booth.localBodyWard.constituencyId = :wardId group by model.voter.gender ");
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("wardId", wardId);
		return queryObj.list();
	}
	
	public List<Object[]> getImpFamilesForWard(List<Long> boothids,Long publicationDateId,String queryString){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId),model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.boothId in(:boothids) group by model.booth.boothId,model.voter.houseNo ") ;
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("boothids",boothids);
		  return queryObj.list();
	}
	
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
			List<Long> boothIds, Long publicationDateId, Long startAge, Long endAge) {
		
		Query query = getSession().createQuery("select count(*) ,model.voter.gender " +
				"from BoothPublicationVoter model where model.booth.boothId in(:boothIds) and " +
				"model.booth.publicationDate.publicationDateId = :publicationDateId " +
				"and model.voter.age >= "+startAge+" and model.voter.age<= "+endAge+
				" group by model.voter.gender");
		
		query.setParameterList("boothIds", boothIds);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	
	/**
	 * This method is used to get the familey member detais based on  houseno and boothid
	 * @author Prasad Thiragabathina
	 * @param String houseNo
	 * @param Long boothId
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getFamileyMembersDetailsForHouseNo(String houseNo,Long boothId,Long voterId)
	{
		Object[] parms = {houseNo,boothId,voterId};
		return getHibernateTemplate().find("select model.voter.voterId,model.voter.name," +
				"model.voter.relationshipType  from BoothPublicationVoter model where model.voter.houseNo = ? " +
				"and model.booth.boothId = ? and model.voter.voterId != ? ",parms);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId, Long toPublicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.booth.publicationDate.publicationDateId from BoothPublicationVoter model " +
				" where model.booth.publicationDate.date between " +
				" (select model2.date from PublicationDate model2 where model2.publicationDateId = :fromPublicationDateId) and " +
				" (select model3.date from PublicationDate model3 where model3.publicationDateId = :toPublicationDateId) order by model.booth.publicationDate.date desc ");
		
		query.setParameter("fromPublicationDateId",fromPublicationDateId);
		query.setParameter("toPublicationDateId",toPublicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPreviousPublicationIds(Long publicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.booth.publicationDate.publicationDateId from BoothPublicationVoter model " +
				" where model.booth.publicationDate.date < (select model2.date from PublicationDate model2 where model2.publicationDateId = :publicationDateId) " +
				" order by model.booth.publicationDate.date desc ");
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
}
