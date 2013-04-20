package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Candidate;
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
	 
	 
	 public List<Object[]> getVotersDetailsByBoothId(Long boothId, Integer startIndex,
				Integer maxRecords, String order, String columnName) {  
		 Query query = null;
		 
		 if("initial".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select model.voter,model.booth.partNo,model.serialNo from BoothPublicationVoter model " +
					"where model.booth.boothId = ? order by cast(model.booth.partNo , int),model.serialNo,model.voter.houseNo");
		 }
		 else if("partNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select model.voter,model.booth.partNo,model.serialNo from BoothPublicationVoter model " +
						"where model.booth.boothId = ? order by cast(model.booth.partNo , int) "+order); 
		 }
		 else if("serialNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select model.voter,model.booth.partNo,model.serialNo from BoothPublicationVoter model " +
						"where model.booth.boothId = ? order by model.serialNo  "+order); 
		 }
		 else
		 {
			 query = getSession().createQuery("select model.voter,model.booth.partNo,model.serialNo from BoothPublicationVoter model " +
						"where model.booth.boothId = ? order by model.voter."+columnName+" "+order); 
		 }

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
	 public List<Object[]> getVotersDetailsForPanchayatByPublicationId(
				Long panchayatId, Long publicationDateId, Integer startIndex,
				Integer maxRecords, String order, String columnName) {
		 Query query = null;
		 if("initial".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select BPV.voter, BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.panchayat.panchayatId = :panchayatId  order by cast(BPV.booth.partNo , int),BPV.serialNo,BPV.voter.houseNo") ;
		 }
		 else if("partNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select BPV.voter,BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.panchayat.panchayatId = :panchayatId  order by cast(BPV.booth.partNo , int) "+order) ; 
		 }
		 else if("serialNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select BPV.voter,BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.panchayat.panchayatId = :panchayatId  order by BPV.serialNo  "+order) ; 
		 }
		 else 
		 {
			 query = getSession().createQuery("select BPV.voter,BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.panchayat.panchayatId = :panchayatId  order by BPV.voter."+columnName+" "+order) ;
		 }
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
	
	public List<Object[]> getVotersCountByPublicationId(String type,Long id,Long publicationDateId,Long constituencyId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null and model.booth.constituency.constituencyId = :constituencyId ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId = :id ");
		else if(type.equalsIgnoreCase("ward"))
			query.append(" model.booth.localBodyWard.constituencyId = :id ");
		query.append(" group by model.voter.gender ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		if(type.equalsIgnoreCase("mandal"))
		 queryObj.setParameter("constituencyId", constituencyId);
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
	public List<Object[]> findVotersGenderWiseCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.voter.voterId),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.constituency.constituencyId = :constituencyId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");			
		else if(locationType.equalsIgnoreCase("localElectionBody") || 
				locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationId  and model.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		
		str.append(" group by model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCastCategoryWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.casteCategoryGroup.casteCategory.categoryName, count(model.voter.voterId) from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			//str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.panchayat.panchayatId = :locationId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			str.append(" model2.hamlet.hamletId = :locationId");
		
		str.append(" group by model2.casteState.casteCategoryGroup.casteCategory.casteCategoryId order by model2.casteState.casteCategoryGroup.casteCategory.categoryName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody")){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.caste.casteName,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			//str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.panchayat.panchayatId = :locationId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			str.append(" model2.hamlet.hamletId = :locationId ");
		
		str.append(" group by model2.casteState.caste.casteId,model.voter.gender order by model2.casteState.caste.casteName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody")){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	public Long getTotalCastCountInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count( model2.casteState.casteStateId) from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			//str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.panchayat.panchayatId = :locationId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			str.append(" model2.hamlet.hamletId = :locationId ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody")){
			query.setParameter("constituencyId", constituencyId);
		}
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdForLocality(Long userId,Long localityId,Long  hamletId,Long  publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.caste.casteName,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		str.append(" model2.hamlet.hamletId = :hamletId and ");	
		str.append(" model2.locality.localityId = :localityId ");	
		str.append(" group by model2.casteState.caste.casteId,model.voter.gender order by model2.casteState.caste.casteName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("hamletId", hamletId);
		query.setParameter("localityId", localityId);
		
		return query.list();
	}	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.party.shortName,model.voter.gender,count(model.voter.voterId),model2.party.partyId from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" model.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
			//str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.panchayat.panchayatId = :locationId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
		else if(locationType.equalsIgnoreCase("localElectionBody"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBody.localElectionBodyId = :locationId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			//str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			str.append(" model2.hamlet.hamletId = :locationId ");
		str.append("group by model2.party.partyId,model.voter.gender order by model2.party.shortName ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody")){
			query.setParameter("constituencyId", constituencyId);
		}
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
	
	public List<Object[]> getVotersCountFromLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId,Long constituencyId){
		Query query = getSession().createQuery("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId in(select distinct model1.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model1 where model1.assemblyLocalElectionBodyId = :assemblyLclElecBodyId ) " +
				" and model.booth.constituency.constituencyId = :constituencyId group by model.voter.gender ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("assemblyLclElecBodyId", assemblyLclElecBodyId);
		  query.setParameter("constituencyId", constituencyId);
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
				Long endAge,Long constituencyId){
			
			
			String queryString = "select count(*),model.voter.gender from BoothPublicationVoter " +
					"model where model.booth.publicationDate.publicationDateId = ? " +
					"and model.booth.tehsil.tehsilId = ? and model.booth.localBody is null " +
					"and model.voter.age >= "+startAge+" and model.voter.age<= "+endAge+" and model.booth.constituency.constituencyId = ? group by model.voter.gender";
			
			Query query = getSession().createQuery(queryString);
			
			query.setParameter(0, publicationDateId);
			query.setParameter(1, tehsilId);
			query.setParameter(2, constituencyId);
			
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
		public List<Object[]> getVotersCountDetailsInSpecifiedRangeForHamletByPublicationId(
				Long hamletId, Long publicationDateId , Long startAge, Long endAge) {		
				
			Query query = getSession().createQuery("select count(*),BPV.voter.gender from BoothPublicationVoter BPV join BPV.booth b join b.hamletBoothPublications hp where BPV.booth.publicationDate.publicationDateId = :publicationDateId  and BPV.voter.age between "+startAge+" and "+endAge+" and " +
					"  hp.hamlet.hamletId = :hamletId  group by BPV.voter.gender  ") ;
			query.setParameter("publicationDateId",publicationDateId);
			query.setParameter("hamletId", hamletId);
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
				Long localElectionBodyId, Long publicationDateId, Long startAge, Long endAge,Long constituencyId) {
			
			Query query = getSession().createQuery("select count(*) ,model.voter.gender " +
					"from BoothPublicationVoter model where model.booth.localBody.localElectionBodyId = ? and " +
					"model.booth.publicationDate.publicationDateId = ?  and model.booth.constituency.constituencyId = ? " +
					"and model.voter.age >= "+startAge+" and " +
					"model.voter.age<= "+endAge+" group by model.voter.gender");
			
			query.setParameter(0, localElectionBodyId);
			query.setParameter(1, publicationDateId);
			query.setParameter(2, constituencyId);
			
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
	public List<Object[]> findAllImpFamiles(Long id,Long publicationDateId,String type,String queryString,Long constituencyId){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId) ,model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId = :id ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId = :id and model.booth.localBody is null and model.booth.constituency.constituencyId = :constituencyId ");
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
		if(type.equalsIgnoreCase("mandal"))
			queryObj.setParameter("constituencyId", constituencyId);
	  return queryObj.list();
	}
	
	public List<Object[]> getVotersImpFamilesForLocalElectionBody(Long lclElecBodyId,Long publicationDateId,String queryString,Long constituencyId){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId) ,model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId  = :lclElecBodyId  and model.booth.constituency.constituencyId = :constituencyId ") ;
		query.append(" group by model.booth.boothId,model.voter.houseNo ");
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("lclElecBodyId", lclElecBodyId);
		queryObj.setParameter("constituencyId", constituencyId);
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
		return getHibernateTemplate().find("select model.voter.name,model.voter.houseNo, model.voter.age,model.booth.boothId ,model.voter.voterId, " +
				" model.voter.gender,model.voter.age,model.booth.partNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? and " +
				" model.booth.panchayat.panchayatId = ? order by model.voter.voterId ",params) ;
	
	}
	
	
	public List<Object[]> getVoterDetailsByVoterIds(List<Long> voterIds,Long publicationDateId)
	{
		
		Query query = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age," +
				" model.booth.boothId ,model.voter.voterId , " +
				" model.voter.gender,model.voter.age,model.booth.partNo from BoothPublicationVoter model where  " +
				" model.booth.publicationDate.publicationDateId =:publicationDateId and  " +
				" model.voter.voterId in(:voterIds) "+
				" order by model.voter.voterId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	
	public List<Object[]> findFamiliesVotersInfoForPanchayatByHamlet(List<Long> hamletIds,Long publicationDateId) {

     String queryString = "select model.voter.name,model.voter.houseNo," +
				" model.voter.age,model.booth.boothId ,model.voter.voterId, " +
				" model.voter.gender,model.voter.age,model.booth.partNo from BoothPublicationVoter model ," +
				"UserVoterDetails model1 where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model1.hamlet.hamletId in(:hamletIds) = :hamletIds and model.voter.voterId = model1.voter.voterId " +
				" order by model.voter.voterId ";
 
 
         Query query = getSession().createQuery(queryString);
         
         query.setParameterList("hamletIds",hamletIds);
         query.setParameter("publicationDateId", publicationDateId);
         
         
         return query.list();
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
	
	public List<Voter> findFamiliesInfoBypartNo(String partNo,Long publicationDateId,String houseNo,Long constituencyId) {
		Object[] params = {publicationDateId,partNo,houseNo,constituencyId};
		return getHibernateTemplate().find("select model.voter from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? " +
				" and model.booth.partNo = ? and model.voter.houseNo = ? and model.booth.constituency.constituencyId = ? ",params) ;
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
		
		public List<Voter> getVoterDetailsByCasteStateForPanchayatByHamlet(List<Long> voterIds,Long publicationDateId)
		{
             Query query = getSession().createQuery("select model.voter from BoothPublicationVoter model " +
				" where model.voter.voterId in(:voterIds)  and model.booth.publicationDate.publicationDateId = :publicationDateId");
             
             query.setParameterList("voterIds", voterIds);
             query.setParameter("publicationDateId",publicationDateId);
             
             return query.list();
             
    		
		}
	
	
	
	  public List<Long> getVoterStateId(Long voterId){
		  return getHibernateTemplate().find("select distinct model.booth.tehsil.district.state.stateId from BoothPublicationVoter model where model.voter.voterId =? ",voterId);
	  }
	  
	  public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString) {
			
			 Query query = getSession().createQuery("select model.voter,model.booth.boothId,model.booth.partNo,model.serialNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			 if(id != 0l)
	 			  query.setParameter("id", id);
	 			query.setFirstResult(startRecord);
	 			query.setMaxResults(maxRecords);
	 			return query.list();
		}
	  
	  public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString) {
			
			 Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			 if(id != 0l)
	 			  query.setParameter("id", id);
	 			return query.list();
		}
	  
	  public List<Object[]> getCastWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select model2.casteState.caste.casteName,count(model2.casteState.caste.casteName),model2.casteState.casteStateId from BoothPublicationVoter model,UserVoterDetails model2 ");
			str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			/*if(locationType.equalsIgnoreCase("constituency"))
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
				str.append(" model.booth.localBodyWard.constituencyId = :locationId ");*/
			
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :locationId ");
			/*else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.panchayat.panchayatId = :locationId and model3.publicationDate.publicationDateId = :publicationDateId ) ");*/
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" model.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append("model2.hamlet.hamletId = :locationId ");

				
			str.append("group by model2.casteState.caste.casteName order by model2.casteState.caste.casteName ");
			Query query =getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("locationId", locationId);
			if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody")){
				query.setParameter("constituencyId", constituencyId);
			}
			return query.list();
		}
	  
	  public List<Object[]> getPartyWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select model2.casteState.caste.casteName,model2.party.shortName,count(model2.party.partyId),model2.party.partyId from BoothPublicationVoter model,UserVoterDetails model2 ");
			str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			/*if(locationType.equalsIgnoreCase("constituency"))
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
				str.append(" model.booth.localBodyWard.constituencyId = :locationId ");*/
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" model.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append("model2.hamlet.hamletId = :locationId ");
			
			
			str.append("group by model2.casteState.caste.casteName,model2.party.partyId order by model2.casteState.caste.casteName,model2.party.shortName ");
			Query query =getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("locationId", locationId);
			if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody")){
				query.setParameter("constituencyId", constituencyId);
			}
			return query.list();
		}
	  
	  public List<Object[]> getParties(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select distinct model2.party.shortName,model2.party.partyId from BoothPublicationVoter model,UserVoterDetails model2 ");
			str.append("where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			/*if(locationType.equalsIgnoreCase("constituency"))
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
				str.append(" model.booth.localBodyWard.constituencyId = :locationId ");*/
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" model.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId = :locationId and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId = :locationId  and model3.publicationDate.publicationDateId = :publicationDateId ) ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append(" model2.hamlet.hamletId = :locationId");
			
			
			Query query =getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("locationId", locationId);
			if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody")){
				query.setParameter("constituencyId", constituencyId);
			}
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
	public List<Long> getAllImpFamilesCount(String locationType, Long locationValue,Long publicationDateId,Long constituencyId)
	{
			StringBuilder str = new StringBuilder();
			str.append("select count(model.voter.houseNo) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.constituency.constituencyId = :id ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.tehsil.tehsilId = :id and model.booth.constituency.constituencyId = :constituencyId and model.booth.localBody is null ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :id ");
			else if(locationType.equals(IConstants.LOCALELECTIONBODY) 
					|| locationType.equals("localElectionBody"))
				str.append(" model.booth.localBody.localElectionBodyId = :id and model.booth.constituency.constituencyId = :constituencyId  ");
			else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				str.append(" model.booth.panchayat.panchayatId = :id ");
			else if(locationType.equalsIgnoreCase(IConstants.WARD))
				str.append(" model.booth.localBodyWard.constituencyId = :id ");
			
			str.append(" group by model.booth.boothId,model.voter.houseNo");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("id", locationValue);
			if(locationType.equalsIgnoreCase("mandal") || locationType.equals(IConstants.LOCALELECTIONBODY) || locationType.equals("localElectionBody"))
				query.setParameter("constituencyId", constituencyId);
		  return query.list();
	}
	
	public Long getVotersCountInAAgeRange(String locationType, Long locationValue,Long publicationDateId,Long ageFrom, Long ageTo,String gender,Long constituencyId)
	{
			StringBuilder str = new StringBuilder();
			str.append("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" model.booth.constituency.constituencyId = :id ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" model.booth.tehsil.tehsilId = :id and model.booth.constituency.constituencyId = :constituencyId and model.booth.localBody is null ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" model.booth.boothId = :id ");
			else if(locationType.equals(IConstants.LOCALELECTIONBODY) || 
					locationType.equals("localElectionBody"))
				str.append(" model.booth.localBody.localElectionBodyId = :id and model.booth.constituency.constituencyId = :constituencyId ");
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
			if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
				query.setParameter("constituencyId", constituencyId);
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
	
	@SuppressWarnings("unchecked")
	public List<Voter> getVotersByBoothId(Long boothId)
	{
		return getHibernateTemplate().find("select model.voter from BoothPublicationVoter model where model.booth.boothId = ? ",boothId);
	}	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartNoAndVoterIdByConstituencyInAPublication(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select model.booth.partNo,model.voter.voterId,model.serialNo from BoothPublicationVoter model where " +
				" model.booth.constituency.constituencyId = :constituencyId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	
	
	public List<Object[]>  getLocalitiesForConstituency(Long id)
	{
	
	Query query = getSession().createSQLQuery("(select l.locality_id , l.name from locality l where l.hamlet_id " +
				"in(select h.hamlet_id from hamlet h join  panchayat_hamlet p on p.hamlet_id= h.hamlet_id  " +
				"where   p.panchayat_id in (select p1.panchayat_id from panchayat p1 join tehsil t " +
				"on p1.tehsil_id = t.tehsil_id where t.district_id in (select  c.district_id " +
				"from constituency c where  c.constituency_id = :cid )))) " +
				" union distinct " +
				"( select l.locality_id , l.name from locality l join local_election_body l1 on " +
				" l.local_election_body_id = l1.local_election_body_id where l1.district_id " +
				"in (select  c.district_id from constituency c where  c.constituency_id = :cid ))").setParameter("cid", id) ;
	 
	List<Object[]> result = query.list();
    return result;
	}
	public List<Object[]>  getHamletsForConstituency(Long id)
	{
	
	Query query = getSession().createSQLQuery("select h.hamlet_id, h.hamlet_name from hamlet h " +
			"  join  panchayat_hamlet p on p.hamlet_id= h.hamlet_id " +
			"  where   p.panchayat_id   in (select p1.panchayat_id from panchayat p1" +
			" join tehsil t on p1.tehsil_id = t.tehsil_id  " +
			" where t.district_id in (select  c.district_id from constituency c" +
			" where  c.constituency_id = :cid)  )").setParameter("cid", id) ;
	 List<Object[]> result = query.list();
   return result;
	}
	public List<Object[]>  getLocalitiesForMandals(Long id)
	{
	
	Query query = getSession().createSQLQuery("( select l.locality_id , l.name from locality l where l.hamlet_id " +
				"in( select h.hamlet_id from hamlet h join  panchayat_hamlet p on p.hamlet_id= h.hamlet_id  " +
				"where   p.panchayat_id in (select p1.panchayat_id from panchayat p1 join tehsil t " +
				"on p1.tehsil_id = t.tehsil_id where t. tehsil_id = :id )))" +
				" union distinct " +
				"( select l.locality_id , l.name from locality l join assembly_local_election_body a " +
				"on l.local_election_body_id=  a.local_election_body_id   where a.assembly_local_election_body_id = :id )").setParameter("id", id) ;
	 
	List<Object[]> result = query.list();
    return result;
	}
	public List<Object[]>  getLocalitiesForPanchayat(Long id){
		
		Query query = getSession().createQuery("select l.localityId , l.name from Locality l " +
				" join  l.hamlet h join h.panchayathHamlets p  " +
				"where   p.panchayat.panchayatId = :id )" ).setParameter("id", id) ;
	 
	List<Object[]> result = query.list();
    return result;
	
	}
public List<Object[]>  getLocalitiesForHamlet(Long id){
		
		Query query = getSession().createQuery("select l.localityId , l.name from Locality l " +
				" join  l.hamlet h where   h.hamletId = :id " ).setParameter("id", id) ;
	 
	List<Object[]> result = query.list();
    return result;
	
	}
public List<Object[]>  getLocalitiesForHamlet(Long id,Long userId){
	
	Query query = getSession().createQuery("select distinct l.localityId , l.name from Locality l " +
			" join  l.hamlet h where   h.hamletId = :id and l.user.userId = :userId" ).setParameter("id", id).setParameter("userId", userId) ;
 
List<Object[]> result = query.list();
return result;

}
	
public List<Object[]>  getLocalitiesForBooth(Long id){
		
	Query query = getSession().createQuery(" select  distinct l.localityId , l.name from Locality l " +
			"where l.localElectionBody.localElectionBodyId " +
			" in( select  distinct b.localBody.localElectionBodyId from Booth b  " +
			"where  b.boothId = :id and  b.localBody.localElectionBodyId is not null)" ).setParameter("id", id) ;
 List<Object[]> result = query.list();
return result;
	
	}
public List<Object[]>  getLocalitiesForBooth(Long id,Long userId){
	
	Query query = getSession().createQuery(" select  distinct l.localityId , l.name from Locality l " +
			"where l.localElectionBody.localElectionBodyId " +
			" in( select  distinct b.localBody.localElectionBodyId from Booth b  " +
			"where  b.boothId = :id and  b.localBody.localElectionBodyId is not null) and l.user.userId = :userId"  ).setParameter("id", id).setParameter("userId", userId) ;
 List<Object[]> result = query.list();
return result;
	
	}
public List<Object[]>  getHamletsForBooth(Long id)
{

Query query = getSession().createQuery("select p.hamlet.hamletId, p.hamlet.hamletName from PanchayatHamlet p " +
		"   join  p.panchayat p1 join p1.booths b  where b.boothId = :id " ).setParameter("id", id) ;
 List<Object[]> result = query.list();
return result;
	/*Query query = getSession().createQuery("select h.hamletId, h.hamletName from Hamlet h " +
			"  join  PanchayatHamlet p on p.hamlet.hamletId = h.hamletId " +
			"  join  Panchayat p1 on p1.panchayatId = p.panchayatId " +
			"  join  Booth b on   p1.tehsilId = b.tehsilId where b.boothId = :id " +
			"  and b.localElectionBodyId is  null ").setParameter("id", id) ;
	 List<Object[]> result = query.list();
	return result;*/
}

public List<Object[]>  getLocalitiesForBooth1(Long id){
	
	Query query = getSession().createSQLQuery(" select  distinct l.localityId , l.name from Locality l " +
			"where l.localElectionBody.localElectionBodyId " +
			" = select  distinct b.local_election_body_id from Booth b  " +
			"where  b.booth_id = :id and  b.local_election_body_id is not null" ).setParameter("id", id) ;
 List<Object[]> result = query.list();
return result;
	
	}

/*
 * 
 * 
 */
	/*public List getLocalElectionBodyId(Long assemblyLocalElectionBodyId) {
		return getHibernateTemplate().find("select model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody  model where model.assemblyLocalElectionBodyId = ?", assemblyLocalElectionBodyId);
	
	}*/
	/*
	 * 
	 * public List getH(Long assemblyLocalElectionBodyId) {	
		return getHibernateTemplate().find("select t.tehsil_id from tehsil t ,t..localElectionBody.localElectionBodyId from AssemblyLocalElectionBody  model where model.assemblyLocalElectionBodyId = ?", assemblyLocalElectionBodyId);
	}*/
    
public List<Object[]> getVotersCountInSpecifiedRangeForHamletByPublicationId(
		Long hamletId, Long publicationDateId , Long startAge, Long endAge) {		
		
	Query query = getSession().createQuery("select count(*),uv.voter.gender from UserVoterDetails uv  where " +
			"  uv.voter.age between "+startAge+" and "+endAge+" and uv.hamlet.hamletId = :hamletId  group by uv .voter.gender  ") ;
	//query.setParameter("publicationDateId",publicationDateId);
	query.setParameter("hamletId", hamletId);
	  return query.list();
	
}

/** This Method is used to get VotersCount by using VoterIds and PublicationDateIds */

public List<Object[]> getVotersBasedOnVoterIdsAndPublication(
		 Long publicationDateId , List<?> voterIds) {		
		
		
	Query query = getSession().createQuery("select count(distinct b.voter.voterId),b.voter.gender " +
			" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
			"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
							"group by b.voter.gender   ") ;
	query.setParameter("publicationDateId",publicationDateId);
	query.setParameterList("voterIds", voterIds);	
	  return query.list();
	
}


@SuppressWarnings("unchecked")
public List<Object[]> getSerialNoByVoterIdsList(List<Long> voterIdsList)
{
	Query queryObj = getSession().createQuery("select model.voter.voterId,model.serialNo from BoothPublicationVoter model where " +
			" model.voter.voterId in (:voterIdsList)");
	queryObj.setParameterList("voterIdsList", voterIdsList);
	return queryObj.list();
}

	public Integer updateSerialNoByVoterId(Long serialNo, Long voterId)
	{
		Query query = getSession().createQuery("update BoothPublicationVoter model set model.serialNo = :serialNo where model.voter.voterId = :voterId");
				
		query.setParameter("serialNo",serialNo);
		query.setParameter("voterId",voterId);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Voter> getVotersInABoothsList(List<String> partNosList, Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery("select model.voter from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationDateId and model.booth.partNo in (:partNosList) order by model.voter.houseNo ");
		query.setParameterList("partNosList",partNosList);
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public List getLocationNameByLocationValue(String type, Long locationValue)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			stringBuilder.append("select model.booth.constituency.name from BoothPublicationVoter model where model.booth.constituency.constituencyId = ?");
		else if(type.equalsIgnoreCase(IConstants.MANDAL))
			stringBuilder.append("select model.booth.tehsil.tehsilName from BoothPublicationVoter model where model.booth.tehsil.tehsilId = ?");
		else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
			stringBuilder.append("select model.booth.localBody.name from BoothPublicationVoter model where model.booth.localBody.localElectionBodyId = ?");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			stringBuilder.append("select model.booth.panchayat.panchayatName from BoothPublicationVoter model where model.booth.panchayat.panchayatId = ?");
		else if(type.equalsIgnoreCase(IConstants.BOOTH))
			stringBuilder.append("select model.booth.partNo from BoothPublicationVoter model where model.booth.boothId = ?");
		else if(type.equalsIgnoreCase(IConstants.WARD))
			stringBuilder.append("select model.booth.localBodyWard.name  from BoothPublicationVoter model where model.booth.localBodyWard.constituencyId = ?");
		
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		queryObj.setParameter(0, locationValue);
		
		return  queryObj.list();
	}	
public List<Long> getCandidateCount(List<Long> locationValue,Long publicationDateId,String type)
{
	StringBuilder query = new StringBuilder();
	if(type.equalsIgnoreCase("CONSTITUENCY"))
	query.append(" select count(model.voter.voterId) from BoothPublicationVoter model,Candidate model2 where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId in (:locationValue)");
	else if(type.equalsIgnoreCase("MANDAL"))
	query.append(" select count(model.voter.voterId) from BoothPublicationVoter model,Candidate model2 where model.voter.voterId = model2.voter.voterId and model.booth.tehsil.tehsilId in (:locationValue)");
	else if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
	query.append(" select count(model.voter.voterId) from BoothPublicationVoter model,Candidate model2 where model.voter.voterId = model2.voter.voterId and model.booth.localBody.localElectionBodyId in (:locationValue)");
	else if(type.equalsIgnoreCase("panchayat"))
		query.append(" select count(model.voter.voterId) from BoothPublicationVoter model,Candidate model2 where model.voter.voterId = model2.voter.voterId and model.booth.panchayat.panchayatId in (:locationValue)");
	else if(type.equalsIgnoreCase("BOOTH"))
		query.append(" select count(model.voter.voterId) from BoothPublicationVoter model,Candidate model2 where model.voter.voterId = model2.voter.voterId and model.booth.boothId in (:locationValue)");
	else if(type.equalsIgnoreCase("WARD"))
		query.append(" select count(model.voter.voterId) from BoothPublicationVoter model,Candidate model2 where model.voter.voterId = model2.voter.voterId and model.booth.localBodyWard.constituencyId =(:locationValue)");
	else if(type.equalsIgnoreCase("hamlet"))
		query.append(" select count(model.voter.voterId) from BoothPublicationVoter model,Candidate model2,UserVoterDetails model3 where model3.voter.voterId = model.voter.voterId and model.voter.voterId = model2.voter.voterId and " +
				"model3.hamlet.hamletId in (:locationValue) ");
	
	query.append(" and model.booth.publicationDate.publicationDateId=:publicationDateId");
	Query queryObj = getSession().createQuery(query.toString()) ;
	queryObj.setParameterList("locationValue", locationValue);
	queryObj.setParameter("publicationDateId", publicationDateId);
	
	
	return queryObj.list();
	
}

public List<Object[]> getPoliticianDetails(List<Long> locationValue,Long publicationDateId,String type,Integer startIndex,
		Integer maxRecords)
{
	StringBuilder query = new StringBuilder();
	
	query.append("select model.voter.name,model.voter.voterId,model.voter.gender,model.voter.mobileNo,model.voter.relativeName,model.voter.age,model.voter.houseNo,model.voter.relationshipType,model.voter.voterIDCardNo,model2.candidateId from BoothPublicationVoter model,Candidate model2"); 
	if(type.equalsIgnoreCase("CONSTITUENCY"))
	query.append(" where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId in (:locationValue)");
	else if(type.equalsIgnoreCase("MANDAL"))
	query.append(" where model.voter.voterId = model2.voter.voterId and model.booth.tehsil.tehsilId in (:locationValue)");
	else if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
	query.append(" where model.voter.voterId = model2.voter.voterId and model.booth.localBody.localElectionBodyId in (:locationValue)");
	else if(type.equalsIgnoreCase("panchayat"))
		query.append(" where model.voter.voterId = model2.voter.voterId and model.booth.panchayat.panchayatId in (:locationValue)");
	else if(type.equalsIgnoreCase("BOOTH"))
		query.append(" where model.voter.voterId = model2.voter.voterId and model.booth.boothId in (:locationValue)");
	else if(type.equalsIgnoreCase("WARD"))
		query.append(" where model.voter.voterId = model2.voter.voterId and model.booth.localBodyWard.constituencyId =(:locationValue)");
	else if(type.equalsIgnoreCase("hamlet"))
		query.append(" , UserVoterDetails model3 where model3.voter.voterId = model.voter.voterId and model.voter.voterId = model2.voter.voterId and " +
				"model3.hamlet.hamletId in (:locationValue) ");
	
	query.append(" and model.booth.publicationDate.publicationDateId=:publicationDateId");
	
	Query queryObj = getSession().createQuery(query.toString()) ;
	queryObj.setParameterList("locationValue", locationValue);
	queryObj.setParameter("publicationDateId", publicationDateId);
	queryObj.setFirstResult(startIndex);
	queryObj.setMaxResults(maxRecords);
	
	return queryObj.list();
	
}

public List<Object[]> getVotersCountByPublicationIdForPanchayatByHamlet(Long userId,String type,Long id,Long publicationDateId,Long constituencyId){
	
	  String queryString = "select count(*),model.voter.gender from BoothPublicationVoter model," +
	  		"UserVoterDetails model2 , PanchayatHamlet model3 where model.voter.voterId = model2.voter.voterId and " +
	  		"model.booth.publicationDate.publicationDateId = :publicationDateId and " +
	  		" model3.hamlet.hamletId = model2.hamlet.hamletId "+
	  		"and model.booth.panchayat.panchayatId =:id " +
	  		//"and model.booth.constituency.constituencyId = :constituencyId) " +
	  		"and model2.user.userId = :userId" +
	  		" group by model.voter.gender ";
		
		Query queryObj = getSession().createQuery(queryString) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		//queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("userId",userId);
		return queryObj.list();
	}

public List<Long > getVoterIdsForuserByHamletIds(Long userId,List<Long> hamletIds)
{
	
	Query query = getSession().createQuery("select model.voter.voterId from UserVoterDetails model where " +
			" model.user.userId = :userId and model.hamlet.hamletId in(:hamletIds)");
	
	
	query.setParameter("userId", userId);
	query.setParameterList("hamletIds", hamletIds);
	
	return query.list();
}

public List<Long > getVoterIdsForuserByHamletForLocalities(Long userId,Long hamletId)
{
	
	Query query = getSession().createQuery("select model.voter.voterId from UserVoterDetails model where " +
			" model.user.userId = :userId and model.hamlet.hamletId =:hamletId and model.locality.localityId is not null");
	
	
	query.setParameter("userId", userId);
	query.setParameter("hamletId", hamletId);
	
	return query.list();
}

public List<Object[]> getAllVoterDetailsByPublicationAndVoterId(Long publicationDateId , List<Long> voterIds)
{
	
	
	Query query = getSession().createQuery("select count(*),model.voter.gender from BoothPublicationVoter model" +
			" where model.voter.voterId in(:voterIds) and model.booth.publicationDate.publicationDateId = :publicationDateId " +
			"group by model.voter.gender ");
	
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameterList("voterIds", voterIds);
	
	return query.list();
}

public List<Object[]> getVotersCountByPublicationIdForPanchayatByHamlets(Long userId,String type,List<Long> hamletIds,Long publicationDateId,Long constituencyId){
	
	Query query = getSession().createQuery("select model.voter.voterId from UserVoterDetails model where " +
			" model.user.userId = :userId and model.hamlet.hamletId = :hamletId");
	
	
	query.setParameter("userId", userId);
	query.setParameterList("hamletId", hamletIds);
	
	/*
	  String queryString = "select count(*),model.voter.gender from BoothPublicationVoter model," +
	  		"UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and " +
	  		"model.booth.publicationDate.publicationDateId = :publicationDateId and " +
	  		" model2.hamlet" +
	  	
	  		"and model2.user.userId = :userId" +
	  		" group by model.voter.gender ";
		
		Query queryObj = getSession().createQuery(queryString) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		//queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("userId",userId);
		return queryObj.list();*/
	
	return null;
	}

/*public Long getTotalVotersCountForHamlet(Long userId , Long id,Long publicationDateId)
{
	
	String queryString = "select count(*) from BoothPublicationVoter model , UserVoterDetails model1 " +
			" where model.voter.voterId  = model1.voter.voterId , model1.user.userId = :userId" +
			" model.booth.publicationDate.publicationDateId = :publicationDateId and " +
			"model2.hamlet.hamletId = :id";
	
	Query query = getSession().createQuery(queryString);
	
	query.setParameter("userId", userId);
	query.setParameter("id", id);
	query.setParameter("publicationDateId", publicationDateId);
	
	return (Long)query.uniqueResult();
	
}*/


public Long getTotalVotersCountForHamlet(Long userId , Long id,Long publicationDateId,String type){
	StringBuilder query = new StringBuilder();
	
	query.append("select count(model.voter.voterId) from UserVoterDetails model , " +
			"BoothPublicationVoter model1 " +
			" where model.voter.voterId = model1.voter.voterId and " +
			" model1.booth.publicationDate.publicationDateId = :publicationDateId" +
			" and model.user.userId = :userId and model.hamlet.hamletId = :id");
	
	
	
	Query queryObj = getSession().createQuery(query.toString()) ;
	queryObj.setParameter("publicationDateId", publicationDateId);
	queryObj.setParameter("id", id);
	queryObj.setParameter("userId", userId);
	return (Long)queryObj.uniqueResult();

}

public List findVotersCastInfoByBoothIdAndPublicationDateForHamlet(Long hamletId, Long publicationDateId){
	Object[] params = {hamletId, publicationDateId};
	return getHibernateTemplate().find("select count(model.voter.voterId),model.voter.gender,model.voter.cast from BoothPublicationVoter model where model.booth.boothId= ? and model.booth.publicationDate.publicationDateId = ? group by model.voter.cast,model.voter.gender order by model.voter.cast", params);
}	



//


public List<Object[]> getImpFamilesForPanchayatByPublicationIdAndVoters(Long publicationDateId , List<Long> voterIds)
{
	
	Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.houseNo, " +
			" SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
			" SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount  " +
			"from BoothPublicationVoter model " +
			"where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
			"model.voter.voterId in(:voterIds)  group by model.voter.houseNo");
	
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameterList("voterIds", voterIds);
	
	return query.list();
}


public List<Object[]> getImpFamilesForPanchayatByPublicationIdAndHamlet(Long userId , Long panchayatId,Long publicationDateId,String queryString){
	StringBuilder query = new StringBuilder();
	query.append("select count(model.voter.voterId),model.voter.houseNo from BoothPublicationVoter model ," +
			"UserVoterDetails model1 ,PanchayatHamlet model2 where model.voter.voterId = model1.voter.voterId and " +
			" model.booth.publicationDate.publicationDateId = :publicationDateId and " +
			" model2.hamlet.hamletId = model1.hamlet.hamletId and " +
			" model1.user.userId = :userId and " +
			" model.booth.panchayat.panchayatId = :panchayatId  group by model1.hamlet.hamletId,model.voter.houseNo ") ;
	if(queryString != null)
		query.append(queryString);
	
	Query queryObj = getSession().createQuery(query.toString()) ;
	queryObj.setParameter("publicationDateId", publicationDateId);
	queryObj.setParameter("panchayatId", panchayatId);
	queryObj.setParameter("userId", userId);
	  return queryObj.list();
}

public List<Object[]> getImpFamilesForHamlet(Long userId,Long hamletId,Long publicationDateId,String queryString)
{
	StringBuilder query = new StringBuilder();
	query.append("select count(model2.voter.voterId),model.voter.houseNo from " +
			 "BoothPublicationVoter model , UserVoterDetails model2 " +
			" where model.voter.voterId = model2.voter.voterId and " +
			" model.booth.publicationDate.publicationDateId = :publicationDateId and " +
			" model2.hamlet.hamletId = :hamletId and model2.user.userId = :userId " +
			" group by model.voter.houseNo ") ;
	if(queryString != null)
		query.append(queryString);
	
	Query queryObj = getSession().createQuery(query.toString()) ;
	queryObj.setParameter("publicationDateId", publicationDateId);
	queryObj.setParameter("hamletId",hamletId);
	queryObj.setParameter("userId", userId);
	  return queryObj.list();
	
	
}

public List<Object[]> getFamilyDetailsForHamlet(Long userId , List<Long> voterIds , Long publicationDateId,String queryString)
{
	
	StringBuffer queryStr = new StringBuffer();
	
	queryStr.append("select count(model.voter.voterId),model.voter.houseNo from " +
			"BoothPublicationVoter model ,UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId and " +
			" model1.user.userId = :userId and " +
			" model.voter.voterId in(:voterIds) and model.booth.publicationDate.publicationDateId = :publicationDateId" +
			" group  by model.voter.houseNo ");
	
	if(queryString != null && !queryString.equalsIgnoreCase(""))
		queryStr.append(queryString);
		
		
	Query query = getSession().createQuery(queryStr.toString());
	
	query.setParameterList("voterIds", voterIds);
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("userId", userId);
	
	
	return query.list();
	
}

public List<Long> getVotersInHamletForUser(Long userId , Long hamletId)
{
	Query query = getSession().createQuery("select model.voter.voterId from UserVoterDetails model where " +
			"model.hamlet.hamletId = :hamletId and model.user.userId = :userId");
	
	query.setParameter("hamletId", hamletId);
	query.setParameter("userId", userId);
	
	return query.list();
	
}
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothPublicationVoterIdsByVoterIdsList(String partNo,List<Long> voterIdsList, Long publicationDateId)
	{
		Query query = getSession().createQuery(" Select model.boothPublicationVoterId from BoothPublicationVoter model where " +
				" model.booth.partNo = :partNo and model.voter.voterId in(:voterIdsList) and " +
				" model.booth.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameterList("voterIdsList",voterIdsList);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("partNo",partNo);
		return query.list();
	}
	
	public Integer deleteByIdsList(List<Long> idsList)
	{
		Query query = getSession().createQuery(" delete from BoothPublicationVoter model where model.boothPublicationVoterId in (:idsList)");
		query.setParameterList("idsList", idsList);
		return query.executeUpdate();
	}


public List<Object[]> getVotersCountForHamlet(Long hamletId, Long userId , Long publicationDateId)
{
	
	
	Query query = getSession().createQuery("select count(*),model.voter.gender from BoothPublicationVoter model," +
			"UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId " +
			" and model.booth.publicationDate.publicationDateId = :publicationDateId and " +
			" model1.user.userId = :userId and model1.hamlet.hamletId = :hamletId");
	
	 query.setParameter("hamletId", hamletId);
	 query.setParameter("publicationDateId", publicationDateId);
	 query.setParameter("userId", userId);
	 
	 return query.list();
}


public List<Long> getTotalVotersCountForHamletByVoterIds(List<Long> voterIds,Long publicationDateId)
{
  Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model " +
  		"where model.voter.voterId in(:voterIds) and model.booth.publicationDate.publicationDateId = :publicationDateId");	

  query.setParameter("publicationDateId", publicationDateId);
  query.setParameterList("voterIds", voterIds);
  
  
  return query.list();
}
public List<Object[]> getVotersCountInSpecifiedRangeForHamletByPublicationId(
		Long hamletId, Long publicationDateId , Long startAge, Long endAge, Long userId) {		
		
		
	Query query = getSession().createQuery("select count(distinct uv.voter.voterId),uv.voter.gender " +
			"from UserVoterDetails uv ,BoothPublicationVoter b where  uv.voter.voterId = b.voter.voterId  " +
			"  and uv.user.userId =:userId  and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
					" and uv.hamlet.hamletId = :hamletId and uv.voter.age between "+startAge+" and "+endAge+"  " +
							"group by uv.voter.gender   ") ;
	query.setParameter("publicationDateId",publicationDateId);
	query.setParameter("hamletId", hamletId);
	query.setParameter("userId", userId);
	  return query.list();
	
}
public List<Object[]> getVotersBasedOnVoterIdsAndPublication(
		 Long publicationDateId , Long startAge, Long endAge, List<?> voterIds) {		
		
		
	Query query = getSession().createQuery("select count(distinct b.voter.voterId),b.voter.gender " +
			" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
			"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
					" and  b.voter.age between :startAge and :endAge  " +
							"group by b.voter.gender   ") ;
	query.setParameter("publicationDateId",publicationDateId);
	query.setParameterList("voterIds", voterIds);
	query.setParameter("startAge", startAge);
	query.setParameter("endAge", endAge);
	
	  return query.list();
	
}

public List<Object[]> getVotersBasedOnVoterIdsAndPublicationAndGender(
		Long publicationDateId , List<?> voterIds) {		
		
		
	Query query = getSession().createQuery("select count(distinct b.voter.voterId),b.voter.gender " +
			" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
			"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
					" group by b.voter.gender") ;
	query.setParameter("publicationDateId",publicationDateId);
	query.setParameterList("voterIds", voterIds);
	
	return query.list();
	
}

//Have update this logic of this query till that alternative is there -- sasi
public List<Object[]> getVotersListInPanchayat(Long publicationDateId,Long panchayatId,Long userId){
	List<Long> votersList = new ArrayList<Long>();
	Query query1=getSession().createQuery("select "+
			" SUM( CASE when u.hamlet.hamletId is not NULL THEN 1 else 0 END ) as result1 ,"+
			" SUM( CASE when u.hamlet.hamletId is NULL THEN 1 else 0 END) as result2 from BoothPublicationVoter b,UserVoterDetails u  "+
			" where b.voter.voterId=u.voter.voterId and b.booth.publicationDate.publicationDateId = :publicationDateId "+
			" and b.booth.panchayat.panchayatId= :panchayatId");
/*	Query query1=getSession().createQuery("select  count (distinct b.voter.voterId) from BoothPublicationVoter b,UserVoterDetails u  "+
	"where b.voter.voterId=u.voter.voterId and b.booth.publicationDate.publicationDateId = :publicationDateId "+
			"and b.booth.panchayat.panchayatId= :panchayatId and u.hamlet.hamletId is not Null");
*/	/*Query query1=getSession().createQuery("select distinct (b.voter.voterId) from BoothPublicationVoter b join  booth bo where bo.panchayat.panchayatId =:panchayatId  and  "+
			" b.booth.publicationDate.publicationDateId = :publicationDateId group by bo.panchayat.panchayatId");*/
	query1.setParameter("panchayatId",panchayatId);
	query1.setParameter("publicationDateId",publicationDateId);
	//query1.setParameter("userId", userId);
	votersList=query1.list();
	
	return query1.list();
	
}
/*public List<Object> getUnassignedVotersInPanchayat(List<?> votersList,Long userId){
	Query query =getSession().createQuery("select count(distinct u.voter.voterId) from UserVoterDetails u where " +
			" u.voter.voterId in (:votersList) and u.user.userId = :userId group by u.user.userId") ;
	query.setParameterList("votersList",votersList);
	query.setParameter("userId", userId);
	return query.list();
}*/

public List<Object[]> getUnassignedVotersInPanchayat(Long userId){ 
	Query query =getSession().createQuery("select "+
			" SUM( CASE when u.hamlet.hamletId is not NULL THEN 1 else 0 END ) as result1 ,"+
			" SUM( CASE when u.hamlet.hamletId is NULL THEN 1 else 0 END) as result2 from UserVoterDetails u"+
			" where u.user.userId = :userId");
	query.setParameter("userId", userId);
	return query.list();
	
}


public List<Object> getVoterIdsBasedOnHamletId(Long hamletId, Long userId)
{
	Object[] param = {hamletId,userId};
	return getHibernateTemplate().find("select distinct model.voter.voterId " +
						"from UserVoterDetails model  where  model.hamlet.hamletId = ? and model.user.userId = ? ",param);
}
@SuppressWarnings("unchecked")
public List getCadreMobileDetails(Long userId,List<Long> scopeId,String scope)
{	
	
	Query query=getSession().createQuery("select distinct(model.mobile) from Cadre model where model.user.userId = :userId and " +
				"model.cadreLevel.level = :scope and model.mobile !='' and model.cadreLevelValue in(:scopeId) ");
	query.setParameterList("scopeId", scopeId);
	query.setParameter("userId", userId);
	query.setParameter("scope", scope);
	return query.list();
}	

@SuppressWarnings("unchecked")
public List getInfluencePeopleMobileDetails(Long userId,List<String> scopeId,String scope)
{	
	if(scope.equalsIgnoreCase("Panchayat"))
		scope="Booth";
	if(scope.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
		scope="MUNICIPAL-CORP-GMC";

	Query query=getSession().createQuery("select distinct(model.phoneNo) from InfluencingPeople model " +
				"where model.user.userId = :userId and model.influencingScope = :scope and model.influencingScopeValue in(:scopeId)");
		query.setParameterList("scopeId", scopeId);
		query.setParameter("userId", userId);
		query.setParameter("scope", scope);
		return query.list();

}

	@SuppressWarnings("unchecked")
	public List getVoterMobileDetails(Long userId,Long scopeId,String scope)
	{	
		StringBuilder query = new StringBuilder();
		
		query.append("select distinct(model1.voter.mobileNo) from BoothPublicationVoter model1 ,UserVoterDetails model2 " +
					 "where model2.user.userId = :userId and model1.voter.mobileNo !='' and " +
					 "model2.voter.voterId = model1.voter.voterId "); 
		if(scope.equalsIgnoreCase("Constituency"))
			query.append(" and model1.booth.constituency.constituencyId = :scopeId ");
		if(scope.equalsIgnoreCase("MANDAL"))
			query.append(" and model1.booth.tehsil.tehsilId = :scopeId");
		if(scope.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
			query.append(" and model1.booth.localBody.localElectionBodyId = :scopeId");
		if(scope.equalsIgnoreCase("Panchayat"))
			query.append(" and model1.booth.panchayat.panchayatId = :scopeId");
		if(scope.equalsIgnoreCase("ward"))
			query.append(" and model1.booth.localBodyWard.constituencyId = :scopeId");
		if(scope.equalsIgnoreCase("Booth"))
			query.append(" and model1.booth.boothId = :scopeId ");
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("scopeId", scopeId);
		queryObj.setParameter("userId", userId);
		
		return queryObj.list();
	}
	public Long getLatestpublicationDate(){
		Query queryObj = getSession().createQuery("select distinct model.booth.publicationDate.publicationDateId from BoothPublicationVoter model where " +
				" model.booth.publicationDate.publicationDateId = (select max(model1.publicationDateId) from PublicationDate model1)");
		return (Long)queryObj.uniqueResult();
	}
	
	public List<Object[]> getAssignedAndUnassignedVtrsOfLclBdy(Long hamletId,Long userId){ 
		Query query =getSession().createQuery("select "+
				" SUM( CASE when u.locality.localityId is not NULL THEN 1 else 0 END ) as result1 ,"+
				" SUM( CASE when u.locality.localityId is NULL THEN 1 else 0 END) as result2 from UserVoterDetails u"+
				" where u.hamlet.hamletId = :hamletId and u.user.userId = :userId");
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		return query.list();
		
	}

	/** This Method is Used to get voterIds and serialno from booth_publication_voter based on booth_id */
	public List<Object[]> getVIdsAndSerialNoByBoothId(Long boothId,Long startIndex,Long endIndex){

	Query query = getSession().createQuery("select model.voter.voterId,model.serialNo from BoothPublicationVoter model " +
	"where model.booth.boothId =:boothId and model.serialNo >= :startIndex and model.serialNo <= :endIndex");

	query.setParameter("boothId", boothId);
	query.setParameter("startIndex", startIndex);
	query.setParameter("endIndex", endIndex);
	return query.list();


	}
	public List<Object[]> getConstituenciesToMapPublicationData(Long fromPubliationId,Long toPublicationId)
	{
		Query query =getSession().createQuery("select distinct model.booth.constituency.constituencyId,model.booth.constituency.name from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId =:fromPubliationId and model.booth.publicationDate.publicationDateId !=:toPublicationId");
		query.setParameter("fromPubliationId", fromPubliationId);
		query.setParameter("toPublicationId", toPublicationId);
		return query.list();
	}	
	public List<Object[]> getVotersBasedOnVoterIdsAndPublication1(
			 Long publicationDateId , Long startAge, Long endAge, List<?> voterIds) {		
			
			
		Query query = getSession().createQuery("select " +
				"SUM( CASE WHEN b.voter.age between :startAge1 and :endAge1 THEN 1 ELSE 0 END) as 18251ge ," +
				"SUM( CASE WHEN b.voter.age between :startAge2 and :endAge2 THEN 1 ELSE 0 END) as 26to35age " +
				" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
				"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
						" and  b.voter.age between :startAge and :endAge  " +
								"group by b.voter.gender   ") ;
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("voterIds", voterIds);
		query.setParameter("startAge", startAge);
		query.setParameter("endAge", endAge);
		
		  return query.list();
		
	}
	public List<Object[]> getVoterIdsBasedOnVoterIdsAndPublication(
			 Long publicationDateId , List<?> voterIds) {		
			
			
		Query query = getSession().createQuery("select distinct b.voter.voterId " +
				" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
				"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " 
								) ;
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("voterIds", voterIds);	
		  return query.list();
		
	}

	/**
	 * This Method Is used To Check For Serial No For Voter In The Given Booth No
	 * @param List<Long> serialNos
	 * @param Long boothId
	 * @return List<Long>
	 * @date 13/03/2013
	 */
	public List<Long> checkForSerialNosDao(List<Long> serialNos, Long boothId) {
		
		String query = "select model.serialNo from BoothPublicationVoter model where model.serialNo in (:serialNos) and model.boothId = :boothId";
		Query queryString = getSession().createQuery(query);
		queryString.setParameterList("serialNos", serialNos);
		queryString.setParameter("boothId", boothId);
		return queryString.list();
	}
	
	public List<Object[]> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(Long userId,String locationType,List<Long> locationId,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.party.shortName,model.voter.gender,count(model.voter.voterId),model2.party.partyId,  ");
		
		if(locationType.equalsIgnoreCase("constituency")){
			
			str.append("model.booth.constituency.constituencyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.constituency.constituencyId in(:locationId ) group by ");
			
		}else if(locationType.equalsIgnoreCase("mandal")){
			
			str.append("model.booth.tehsil.tehsilId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.localBody is null and model.booth.tehsil.tehsilId in(:locationId ) and model.booth.constituency.constituencyId = :constituencyId" +
					" group by model.booth.tehsil.tehsilId,");
			
			
		}else if(locationType.equalsIgnoreCase("booth")){
			
			str.append("model.booth.boothId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId  in(:locationId)  group by model.booth.boothId,");
			
		}else if(locationType.equalsIgnoreCase("panchayat")){
			
			str.append("model.booth.panchayat.panchayatId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.panchayat.panchayatId  in(:locationId)  group by model.booth.panchayat.panchayatId,");
			
		}else if(locationType.equalsIgnoreCase("localElectionBody")  || "Local Election Body".equalsIgnoreCase(locationType)){
			
			str.append("model.booth.localBody.localElectionBodyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.localBody.localElectionBodyId in(:locationId) and model.booth.constituency.constituencyId = :constituencyId " +
					"  group by model.booth.localBody.localElectionBodyId,");
			
		}else if(locationType.equalsIgnoreCase("ward")){
			
			str.append("model.booth.localBodyWard.constituencyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.localBodyWard.constituencyId in(:locationId )  group by model.booth.localBodyWard.constituencyId,");
			
		}else if(locationType.equalsIgnoreCase("hamlet")){
			
			str.append("model.booth.hamlet.hamletId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model2.hamlet.hamletId  in(:locationId)  group by model.booth.hamlet.hamletId,");
		}
		str.append(" model2.party.partyId,model.voter.gender order by model2.party.shortName ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("locationId", locationId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType)){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdMultipleALocation(Long userId,String locationType,List<Long> locationIds,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName,  ");
		
		
		if(locationType.equalsIgnoreCase("constituency")){
			
			str.append(" model.booth.constituency.constituencyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.constituency.constituencyId in (:locationId)  and model3.publicationDate.publicationDateId = :publicationDateId )  group by ");
			
		}else if(locationType.equalsIgnoreCase("mandal")){
			
			str.append(" model.booth.tehsil.tehsilId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId in (:locationId) and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) " +
					" group by model.booth.tehsil.tehsilId,");
			
		}else if(locationType.equalsIgnoreCase("booth")){
			
			str.append("model.booth.boothId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId  in (:locationId)  group by model.booth.boothId,");
			
		}else if(locationType.equalsIgnoreCase("panchayat")){
			
			str.append("model.booth.panchayat.panchayatId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.panchayat.panchayatId  in (:locationId) group by model.booth.panchayat.panchayatId, ");
			
		}else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType)){
			
			str.append("model.booth.localBody.localElectionBodyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId  in (:locationId) and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) " +
					"  group by model.booth.localBody.localElectionBodyId,");
			
		}else if(locationType.equalsIgnoreCase("ward")){
			
			str.append("model.booth.localBodyWard.constituencyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBodyWard.constituencyId  in (:locationId)  and model3.publicationDate.publicationDateId = :publicationDateId ) " +
					"  group by model.booth.localBodyWard.constituencyId,");
			
		}else if(locationType.equalsIgnoreCase("hamlet")){
			
			str.append("model.booth.hamlet.hamletId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model2.hamlet.hamletId  in (:locationId)  group by model.booth.hamlet.hamletId,");
		}
		str.append("  model2.casteState.caste.casteId,model.voter.gender order by model2.casteState.caste.casteName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("locationId", locationIds);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") ||  "Local Election Body".equalsIgnoreCase(locationType)){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	public List<Object[]> getPublicationDetailsBasedOnConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select distinct model.booth.publicationDate.publicationDateId , DATE_FORMAT( model.booth.publicationDate.date ,'%d-%m-%Y ')  " +
				"    from BoothPublicationVoter model join model.booth.constituency c join model.booth.publicationDate p where " +
				" c.constituencyId = ? order by p.year desc)",constituencyId);
	}
	public Long getTotalVotersCountForHamletByBooth(Long userId , Long id,Long publicationDateId,String type , Long boothId){
		StringBuilder query = new StringBuilder();
		
		query.append("select count( distinct model.voter.voterId) from UserVoterDetails model , " +
				"BoothPublicationVoter model1 " +
				" where model.voter.voterId = model1.voter.voterId and " +
				" model1.booth.publicationDate.publicationDateId = :publicationDateId" +
				" and model.user.userId = :userId and " +
				" model1.booth.boothId = :boothId and "+
				"model.hamlet.hamletId = :id");
		
		
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		queryObj.setParameter("userId", userId);
		queryObj.setParameter("boothId", boothId);
		return (Long)queryObj.uniqueResult();

	}
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdInALocationByBooth(Long userId,String locationType,Long locationId,Long publicationDateId,Long boothId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.caste.casteName,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		str.append("  model.booth.boothId= :boothId and ");
		str.append("  model2.hamlet.hamletId= :locationId  ");
		str.append(" group by model2.casteState.caste.casteId,model.voter.gender order by model2.casteState.caste.casteName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("locationId", locationId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	 public List<Object[]> getVoterPersonalDetailsByVoterIdAndPuclicationId(Long voterId,Long publicationDateId){
			
			Query queryObject = getSession().createQuery("select model.voter , model.serialNo from  BoothPublicationVoter" +
					" model where model.voter.voterId = ? and " +
					"model.booth.publicationDate.publicationDateId = ?");
			
			queryObject.setParameter(0, voterId);
			queryObject.setParameter(1, publicationDateId);
		
			return queryObject.list();
		}
	
	
	public List<Object[]> getVoterDetailsByPanchayatIds(Long panchayatId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age," +
				" model.booth.boothId ,model.voter.voterId, " +
				" model.voter.gender,model.voter.age,model.booth.partNo,model1.hamlet.hamletName  from BoothPublicationVoter model , UserVoterDetails model1 , " +
				"PanchayatHamlet model2 where  " +
				"model1.hamlet.hamletId = model2.hamlet.hamletId and "+
				" model.voter.voterId = model1.voter.voterId and model.booth.publicationDate.publicationDateId =:publicationDateId and " +
				"model2.panchayat.panchayatId = :panchayatId and model1.user.userId = :userId "+
				" order by model1.hamlet.hamletName");
		
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	public List<Object[]> getVoterDetailsByHamletId(Long hamletId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age," +
				" model.booth.boothId ,model.voter.voterId, " +
				" model.voter.gender,model.voter.age,model.booth.partNo,model1.locality.name  from BoothPublicationVoter model , UserVoterDetails model1  " +
				" where  " +
				" model.voter.voterId = model1.voter.voterId and model.booth.publicationDate.publicationDateId =:publicationDateId and " +
				"model1.hamlet.hamletId = :hamletId and model1.user.userId = :userId "+
				" order by model1.hamlet.hamletName");
		
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}

	
	public Integer updateSerialNoByVIdBId(Long serialNo, Long voterId,Long boothId)
	{
		Query query = getSession().createQuery("update BoothPublicationVoter model set model.serialNo = :serialNo where model.voter.voterId = :voterId and model.booth.boothId = :boothId");
				
		query.setParameter("serialNo",serialNo);
		query.setParameter("voterId",voterId);
		query.setParameter("boothId",boothId);
		return query.executeUpdate();
	}
	
	public List<Long> checkSerialNoandVoterIdDuplicates(Long serialNo, Long voterId,Long boothId)
	{
		Query query = getSession().createQuery("select model.serialNo from BoothPublicationVoter model where model.voter.voterId != :voterId and model.booth.boothId = :boothId and model.serialNo = :serialNo");
		query.setParameter("serialNo",serialNo);
		query.setParameter("voterId",voterId);
		query.setParameter("boothId",boothId);
		return query.list();
	}
	
	public List<BoothPublicationVoter> getAllVoterDetailsByVoterIds(List<Long> voterIds,Long publicationDateId)
	{
		Query query = getSession().createQuery("from BoothPublicationVoter model where  " +
				" model.voter.voterId in (:voterIds) and model.booth.publicationDate.publicationDateId =:publicationDateId " +
				" order by model.voter.voterId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	

	public Long getTotalVotersCountByReportlevelIdsList(List<Long> reportlevelIdsList,Long publicationDateId,String type){
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase("constituency"))
			query.append(" model.booth.constituency.constituencyId in (:id) ");
		else if(type.equalsIgnoreCase("mandal"))
			query.append(" model.booth.tehsil.tehsilId in (:id) and model.booth.localBody is null ");
		else if(type.equalsIgnoreCase("booth"))
			query.append(" model.booth.boothId in (:id) ");
		else if(type.equalsIgnoreCase("ward"))
			query.append(" model.booth.localBodyWard.constituencyId in (:id) ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("id", reportlevelIdsList);
		return (Long)queryObj.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountByAssemblyIdsList(List<Long> assemblyIdsList,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		query.append(" model.booth.constituency.constituencyId in (:assemblyIdsList) ");
		
		query.append(" group by model.voter.gender ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("assemblyIdsList", assemblyIdsList);
		
		return queryObj.list();
	}
	
	
	public Long getTotalVotersCountForParliament(List<Long> assemblyIdsList,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			query.append(" model.booth.constituency.constituencyId in(:assemblyIdsList) ");
		
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("assemblyIdsList", assemblyIdsList);
		return (Long)queryObj.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllImpFamilesForParliament(List<Long> assemblyIdsList, Long publicationDateId, String queryString)
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId) ,model.voter.houseNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		query.append(" model.booth.constituency.constituencyId in(:id) ");
		query.append(" group by model.booth.boothId,model.voter.houseNo");
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("id", assemblyIdsList);
		
	  return queryObj.list();
	}
	public List<Object[]> getPublicationUserCount(Long userId,Long publicationDateId , Long Id)
	{
		Query query = getSession().createQuery(
				"select  count(b.voter.voterId) ," +
						" SUM( CASE when u.locality.localityId is not NULL THEN 1 else 0 END ) as result1 ,"+
						" SUM( CASE when u.locality.localityId is NULL THEN 1 else 0 END) as result2 "+
				"from BoothPublicationVoter b , UserVoterDetails u join b.booth bb where " +
	            " b.voter.voterId=u.voter.voterId and  u.hamlet.hamletId = :hamletId and u.user.userId = :userId and bb.publicationDate.publicationDateId = :publicationDateId " 
				);
		
		query.setParameter("hamletId", Id);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	
	
	
	public List<Object[]> getFamiliesInBooth(Long userId, Long hamletId , Long boothId,Long publicationDateId , Long constituencyId)
	{
		
		Query query = getSession().createQuery("select count( distinct model1.voter.voterId),model1.voter.houseNo " +
				" from BoothPublicationVoter model ,UserVoterDetails model1 " +
				"where model.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and model.voter.voterId = model1.voter.voterId and " +
				" model1.user.userId = :userId and " +
				" model.booth.boothId = :boothId and " +
				" model1.hamlet.hamletId = :hamletId " +
				"and model.booth.constituency.constituencyId = :constituencyId group by model1.voter.houseNo ");
		
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("boothId", boothId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("hamletId", hamletId);
		
		return query.list();
		
	}
	
	
	public List<Voter> getVoterDetailsByHamletForUser(Long userId,Long id,Long publicationDateId)
	{
		
		Query query = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age," +
				" model.booth.boothId ,model.voter.voterId,model.voter.gender," +
				" model.voter.age,model.booth.partNo from BoothPublicationVoter model , " +
				" UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId and " +
				" model1.user.userId = :userId and model1.hamlet.hamletId = :hamletId " +
				" and model.booth.publicationDate.publicationDateId = :publicationDateId");
		
		
		query.setParameter("userId", userId);
		query.setParameter("hamletId", id);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
		
	}
	
	public List<Object[]> getVotersCountByGenderInBooth(Long userId ,Long hametId , Long  boothId ,Long  publicationDateId,Long constituencyId)
	{
		
		Query query = getSession().createQuery("select count(model1.voter.voterId),model1.voter.gender " +
				" from BoothPublicationVoter model ,UserVoterDetails model1 " +
				"where model.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and model.voter.voterId = model1.voter.voterId and" +
				" model1.user.userId = :userId and " +
				" model.booth.boothId = :boothId  and" +
				" model1.hamlet.hamletId = :hamletId " +
				"and model.booth.constituency.constituencyId = :constituencyId group by model1.voter.gender");
		
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("boothId", boothId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("hamletId", hametId);
		
		return query.list();
		
		
	}
	
	
	public List<Long> getAllBoothsInHamletByUser(Long userId,Long hamletId,Long publicationDateId , Long constituencyId)
	{
		
		Query query = getSession().createQuery("select " +
				"distinct(model.booth.boothId)" +
				" from BoothPublicationVoter model ,UserVoterDetails model1 " +
				" where model.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and model.voter.voterId = model1.voter.voterId and" +
				" model1.user.userId = :userId and " +
				"model1.hamlet.hamletId = :hamletId " +
				"and model.booth.constituency.constituencyId = :constituencyId");
		
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		query.setParameter("hamletId", hamletId);
		query.setParameter("constituencyId", constituencyId);
		
		
		return query.list();
		
	}
	
   public List<Voter> findFamiliesInfoForHamlet(Long hamletId,Long boothId,Long publicationDateId,String houseNo) {
		
		Query query = getSession().createQuery("select model.voter  from BoothPublicationVoter model , UserVoterDetails model1 " +
				" where model.voter.voterId = model1.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId" +
				" and model.voter.houseNo =:houseNo and model1.hamlet.hamletId = :hamletId and model.booth.boothId = :boothId");
		
		query.setParameter("publicationDateId", publicationDateId);;
		query.setParameter("houseNo", houseNo);
		query.setParameter("hamletId", hamletId);
		query.setParameter("boothId", boothId);
		
		return query.list();
	
     }

   public List<Voter> findFamiliesInfoForLocalBody(Long hamletId,Long boothId,Long publicationDateId,String houseNo) {
	
	Query query = getSession().createQuery("select model.voter  from BoothPublicationVoter model , UserVoterDetails model1 " +
			" where model.voter.voterId = model1.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId" +
			" and model.voter.houseNo =:houseNo and model1.locality.localityId = :localityId and model.booth.boothId = :boothId");
	
	query.setParameter("publicationDateId", publicationDateId);;
	query.setParameter("houseNo", houseNo);
	query.setParameter("localityId", hamletId);
	query.setParameter("boothId", boothId);
	
	return query.list();

 }
   
   public List<Object[]> findFamiliesVotersInfoForBoothForUser(Long id,Long publicationDateId,Long userId){
		
		Query query = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age," +
				" model.booth.boothId ,model.voter.voterId,model.voter.gender," +
				" model.voter.age,model.booth.partNo,model1.hamlet.hamletName from BoothPublicationVoter model , " +
				" UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId and " +
				" model1.user.userId = :userId and model.booth.boothId = :boothId " +
				" and model.booth.publicationDate.publicationDateId = :publicationDateId");
		
		
		query.setParameter("userId", userId);
		query.setParameter("boothId", id);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
public List<Object[]> getVoterDataForPanchayat(Long panchayatId, Long publicationId,
		Long startIndex, Long maxIndex, String sort, String order) {
	Query query = getSession().createQuery("select model.voter,model.booth.partNo from BoothPublicationVoter model where model.booth.panchayat.panchayatId = :panchayatId " +
			" and model.booth.publicationDate.publicationDateId = :publicationId order by model.voter."+sort+" "+order );
	
	query.setParameter("publicationId", publicationId);
	query.setParameter("panchayatId", panchayatId);
	query.setFirstResult(startIndex.intValue());
	query.setMaxResults(maxIndex.intValue());
	
	return query.list();
}
public List<Object[]> getVoterDataForBooth(Long boothId, Long publicationId,
		Long startIndex, Long maxIndex, String sort, String order) {
	
	Query query = getSession().createQuery("select model.voter,model.booth.partNo from BoothPublicationVoter model where model.booth.boothId = :boothId " +
			"  order by model.voter."+sort+" "+order );
	
	//query.setParameter("publicationId", publicationId);
	query.setParameter("boothId", boothId);
	query.setFirstResult(startIndex.intValue());
	query.setMaxResults(maxIndex.intValue());
	
	return query.list();
}


}
