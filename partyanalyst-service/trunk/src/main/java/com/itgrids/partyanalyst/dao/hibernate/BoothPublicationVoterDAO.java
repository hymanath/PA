package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.SurveyDetailsInfo;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterFlag;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothPublicationVoterDAO extends GenericDaoHibernate<BoothPublicationVoter, Long> implements	IBoothPublicationVoterDAO {
	
	
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

		public List<Object[]> getVotersDetailsForPanchayatByPublicationIdAndPAnchayatId(
				Long panchayatId, Long publicationDateId) {
		
			Query query = getSession()
					.createQuery("select " +
							"BPV.voter.voterId,BPV.voter.relationshipType," +
							"BPV.voter.relativeName,BPV.voter.gender , " +
							"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name,BPV.booth.panchayat.panchayatName  " +
							" from " +
							"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
							" BPV.booth.panchayat.panchayatId = :panchayatId");
			
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			return query.list();
		}
		
		
		public List<Object[]> getUrbanVotersDetails(
				Long constituencyId, Long publicationDateId) {
		
			Query query = getSession()
					.createQuery("select " +
							"BPV.voter.voterId,BPV.voter.relationshipType," +
							"BPV.voter.relativeName,BPV.voter.gender , " +
							"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name  " +
							" from " +
							"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
							" BPV.booth.constituency.constituencyId = :constituencyId and booth.panchayat is null");
			
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
		
		
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
			
			String queryString = "select count( distinct model.voter.voterId) from BoothPublicationVoter model where model.booth.boothId = ?";

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
	
	
	/*public List<Object[]> addedDeletedVoters(String locationType,Long locationId,Long fromPublicationDateId,Long toPublicationDateId,Long constituencyId){
		
		Query query = getSession().createSQLQuery(" select count(BPV.voter_id) from booth_publication_voter BPV,Booth B " +
		  		" where BPV.booth_id = B.booth_id and B.publication_date_id =:toPublicationDateId " +
		  		"  and B.constituency_id =:constituencyId and BPV.voter_id not in (select BPV2.voter_id from booth_publication_voter BPV2,Booth B2 " +
		  		" where BPV2.booth_id = B2.booth_id and " +
		  		"  B2.constituency_id = :constituencyId and B2.publication_date_id =:fromPublicationDateId) ");
		  
		  query.setParameter("constituencyId", constituencyId);
		  query.setParameter("fromPublicationDateId", fromPublicationDateId);
		  query.setParameter("toPublicationDateId", toPublicationDateId);
		  
		  return query.list();
		
		
		StringBuilder str = new StringBuilder();
		StringBuilder strs = new StringBuilder();
		str.append("select count(model.voter.voterId) from BoothPublicationVoter model," +
				" Booth model2 " +
				"where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.boothId = model2.boothId and ");
		
		if(locationType.equalsIgnoreCase("constituency")){
			str.append(" model.booth.constituency.constituencyId = :locationId ");
			strs.append(" bpv.booth.constituency.constituencyId = :locationId ");
		}
		else if(locationType.equalsIgnoreCase("mandal")){
			str.append(" model.booth.tehsil.tehsilId = :locationId and model.booth.constituency.constituencyId = :constituencyId and model.booth.localBody is null ");
			strs.append(" bpv.booth.tehsil.tehsilId = :locationId and model.booth.constituency.constituencyId = :constituencyId and model.booth.localBody is null ");
		}
		else if(locationType.equalsIgnoreCase("booth")){
			str.append(" model.booth.boothId = :locationId ");
			strs.append(" bpv.booth.boothId = :locationId ");
		}
		else if(locationType.equalsIgnoreCase("panchayat")){
			str.append(" model.booth.panchayat.panchayatId = :locationId ");
			strs.append(" bpv.booth.panchayat.panchayatId = :locationId ");
		}
		else if(locationType.equalsIgnoreCase("localElectionBody") || 
				locationType.equalsIgnoreCase("Local Election Body")){
			str.append(" model.booth.localBody.localElectionBodyId = :locationId  and model.booth.constituency.constituencyId = :constituencyId ");
			strs.append(" bpv.booth.localBody.localElectionBodyId = :locationId  and model.booth.constituency.constituencyId = :constituencyId ");
		}
		else if(locationType.equalsIgnoreCase("ward")){
			str.append(" model.booth.localBodyWard.constituencyId = :locationId ");
			strs.append(" bpv.booth.localBodyWard.constituencyId = :locationId ");
		}
		
		
		str.append(" and model.voter.voterId not in(select bpv.voter.voterId from BoothPublicationVoter bpv where" +
				" bpv.booth.boothId=model2.boothId and bpv.booth.publicationDate.publicationDateId = :topublicationDateId and ");
		str.append(strs);
		str.append(" ) ");
		//str.append(" group by model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("publicationDateId", fromPublicationDateId);
		query.setParameter("topublicationDateId", toPublicationDateId);
		query.setParameter("locationId", locationId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			query.setParameter("constituencyId", constituencyId);
		return query.list();
		
	}*/
	
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
		else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" model2.ward.constituencyId = :locationId ");
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
		else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" model2.ward.constituencyId = :locationId ");
		
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
		else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" model2.ward.constituencyId =  :locationId ");
		
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
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdForLocality(Long userId,Long localityId,Long  hamletId,Long  publicationDateId,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.caste.casteName,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(type.equalsIgnoreCase("muncipalityCustomWard"))
			str.append(" model2.ward.localElectionBody.localElectionBodyId =:hamletId and model2.ward.constituencyId = :localityId ");
		else
		  str.append("  model2.locality.localityId = :localityId and ");
		
		
		if(type.equalsIgnoreCase(IConstants.HAMLET))
			str.append(" model2.hamlet.hamletId = :hamletId  ");	
		else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
			str.append(" model2.ward.constituencyId = :hamletId  ");
		
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
		else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" model2.ward.constituencyId = :locationId ");
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
	
	public List<Object[]> getPartyAndGenderWiseVotersCountByPublicationIdInAndPanchayatWithHamlets(Long userId , Long panchayatId ,Long publicationDateId)
	{
		Query query = getSession().createQuery("select UVD.party.shortName , UVD.voter.gender ,count(UVD.voter.voterId) , UVD.party.partyId , PH.panchayat.panchayatId " +
		" from BoothPublicationVoter BPV , UserVoterDetails UVD ,PanchayatHamlet PH " +
		"where UVD.hamlet.hamletId = PH.hamlet.hamletId and PH.panchayat.panchayatId =:panchayatId and " +
		"BPV.booth.publicationDate.publicationDateId = :publicationDateId and UVD.voter.voterId = BPV.voter.voterId " +
		"and UVD.user.userId = :userId group by PH.panchayat.panchayatId ,UVD.party.partyId ,UVD.voter.gender ");
	
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
	
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
	return getHibernateTemplate().find("select count(model.voter.voterId),model.voter.gender from BoothPublicationVoter model where model.booth.tehsil.tehsilId = ? and model.booth.localBody is null and model.booth.publicationDate.publicationDateId = ? group by model.voter.gender ", params);
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
		

		Query query = getSession().createQuery("select count(BPV.voter.voterId),BPV.voter.gender from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" BPV.booth.panchayat.panchayatId = :panchayatId  group by BPV.voter.gender ") ;
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
		  return query.list();
		
	}
	
	//caste info for PollingStation
		public List findVotersCastInfoByBoothIdAndPublicationDate(Long boothId, Long publicationDateId){
			Object[] params = {boothId, publicationDateId};
			return getHibernateTemplate().find("select count(model.voter.voterId),model.voter.gender from BoothPublicationVoter model where model.booth.boothId= ? and model.booth.publicationDate.publicationDateId = ? group by model.voter.gender ", params);
		}	
		
		//caste info for Constituency

		
		public List getGenderWiseCountInConstituency(Long constituencyId, Long publicationDate)
		{
			Object[] params = {constituencyId, publicationDate};
			Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender from BoothPublicationVoter model where model.booth.constituency.constituencyId = ? and model.booth.publicationDate.publicationDateId = ? group by model.voter.gender ");
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
							"model.booth.publicationDate.publicationDateId = :publicationDateId and model.voter.age 
 "+startAge+" and "+endAge+
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
		
		public List<Object[]> getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(Long panchayatId,Long publicationDateId,Long startAge,Long endAge ,Long userId)
		{
			
			Query query = getSession().createQuery("select count(UVD.voter.voterId),UVD.voter.gender from BoothPublicationVoter BPV , UserVoterDetails UVD ," +
					"PanchayatHamlet PH where " +
					"UVD.voter.age between "+startAge+" and "+endAge+" and " +
					"BPV.voter.voterId = UVD.voter.voterId and " +
					"UVD.user.userId =:userId and " +
					"PH.hamlet.hamletId = UVD.hamlet.hamletId and " +
					"PH.panchayat.panchayatId = :panchayatId and " +
					"BPV.booth.publicationDate.publicationDateId = :publicationDateId group by UVD.voter.gender");
			
			query.setParameter("userId", userId);
			query.setParameter("panchayatId", panchayatId);
			query.setParameter("publicationDateId", publicationDateId);
			
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
				" model.booth.boothId,model.voter.voterId, model.voter.gender,model.voter.age,model.booth.partNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = ? " +
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
		Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.booth.localBody.localElectionBodyId =:lclElecBodyId group by model.voter.gender");
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

	
/*	//get voter details By caste_state ID
	public List<Voter> getVoterDetailsByCasteStateForBooth(Long boothid,Long publicationDateId,Long casteStateId,Long userId,Long constituencyId)
	{
		Object[] params={boothid,publicationDateId,casteStateId};
	return getHibernateTemplate().find("select distinct model.voter from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.boothId=? and model.booth.publicationDate.publicationDateId = ? and model2.casteState.casteStateId =? and model.user.userId=? and model2.user.userId=",params);
	
	}*/
	
	//get voter details By caste_state ID
		public List<Object[]> getVoterDetailsByCasteStateForBooth(Long boothid,Long publicationDateId,Long casteStateId,Long userId,Long constituencyId)
		{
			Query query = getSession().createQuery("select distinct model.voter,model.booth.boothId,model2.mobileNo from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.boothId=:boothid and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.casteState.casteStateId =:casteStateId and model2.user.userId=:userId and model.booth.constituency.constituencyId=:constituencyId");
		
			query.setParameter("boothid", boothid);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("casteStateId", casteStateId);
			query.setParameter("userId", userId);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
	
	//get voter details By caste_state ID
		public List<Object[]> getVoterDetailsByCasteStateForPanchayat(Long panchayatId,Long publicationDateId,Long casteStateId,Long userId)
		{
			Object[] params={panchayatId,publicationDateId,casteStateId,userId};
		return getHibernateTemplate().find("select distinct model.voter,model.booth.boothId,model2.mobileNo from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.panchayat.panchayatId=? and model.booth.publicationDate.publicationDateId = ? and model2.casteState.casteStateId =? and model2.user.userId = ?",params);
		
		}
		
		public List<Object[]> getVoterDetailsByCasteStateForPanchayatByHamlet(List<Long> voterIds,Long publicationDateId)
		{
             Query query = getSession().createQuery("select model.voter,model.booth.boothId from BoothPublicationVoter model " +
				" where model.voter.voterId in(:voterIds)  and model.booth.publicationDate.publicationDateId = :publicationDateId");
             
             query.setParameterList("voterIds", voterIds);
             query.setParameter("publicationDateId",publicationDateId);
             
             return query.list();
             
    		
		}
	
	
	
	  public List<Long> getVoterStateId(Long voterId){
		  return getHibernateTemplate().find("select distinct model.booth.tehsil.district.state.stateId from BoothPublicationVoter model where model.voter.voterId =? ",voterId);
	  }
	  
	  /*public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString) {
			
			 Query query = getSession().createQuery("select model.voter,model.booth.boothId,model.booth.partNo,model.serialNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			 if(id != 0l)
	 			  query.setParameter("id", id);
	 			query.setFirstResult(startRecord);
	 			query.setMaxResults(maxRecords);
	 			return query.list();
		}*/
	  
	  public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString,String locationType,List<Long> boothIds) {
			
			 Query query = getSession().createQuery("select model.voter,model.booth.boothId,model.booth.partNo,model.serialNo from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			  if(locationType.equalsIgnoreCase("panchayat") && boothIds != null && boothIds.size() >0)
	 				 query.setParameterList("id", boothIds);
	 			  else if(id != 0l)
	 				query.setParameter("id", id);
	 			query.setFirstResult(startRecord);
	 			query.setMaxResults(maxRecords);
	 			return query.list();
		}
	  /*public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString) {
			
			 Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			 if(id != 0l)
	 			  query.setParameter("id", id);
	 			return query.list();
		}*/
	  
	  public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString,List<Long> boothIds,String locationType) {
			
			 Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
	 			  query.setParameter("publicationDateId", publicationDateId);
	 			 if(locationType.equalsIgnoreCase("panchayat") && boothIds != null && boothIds.size()>0 )
	 				query.setParameterList("id", boothIds);
	 			 else if(id != 0l)
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
				str.append(" model2.hamlet.hamletId = :locationId ");
			else if(locationType.equalsIgnoreCase(IConstants.CUSTOMWARD))
				str.append(" model2.ward.constituencyId = :locationId ");

				
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
			else if( locationType.equalsIgnoreCase(IConstants.CUSTOMWARD))
				str.append(" model2.ward.constituencyId = :locationId " );
			
			
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
	  
	  
	  public List<Object[]> getPartyWiseCountForPanchayatByHamlets(Long userId,Long locationId,Long publicationDateId)
		{
		  
		  Query query = getSession().createQuery("select UVD.caste.casteName,UVD.party.shortName," +
		  		" count(UVD.party.partyId),UVD.party.partyId" +
		  		" from UserVoterDetails UVD ,BoothPublicationVoter BPV ," +
		  		" PanchayatHamlet PH where " +
		  		" UVD.voterId = BPV.voterId and " +
		  		" UVD.user.userId = :userId and " +
		  		" BPV.booth.publicationDate.publicationDateId = :publicationDateId and" +
		  		" PH.hamletId = UVD.hamletId and" +
		  		" PH.panchayat.panchayatId =:panchayatId");
		  
		  query.setParameter("panchayatId", locationId);
		  query.setParameter("userId" ,userId);
		  query.setParameter("publicationDateId", publicationDateId);
		
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
	public List<Long> getVotersCountForBooths(Long ids,Long publicationDateId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		query.append(" model.booth.boothId =:ids ");
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("ids", ids);
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

Query query = getSession().createQuery("select distinct p.hamlet.hamletId, p.hamlet.hamletName from PanchayatHamlet p " +
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
	else if(type.equalsIgnoreCase("WARD")||type.equalsIgnoreCase("customWard"))
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
		Integer maxRecords,String columnName ,String order)
{
	if(columnName.equalsIgnoreCase("firstName"))
	{
		columnName = "name";
	}
	StringBuilder query = new StringBuilder();
	
	query.append("select model.voter.name,model.voter.voterId,model.voter.gender,model.voter.name,model.voter.relativeName,model.voter.age,model.voter.houseNo,model.voter.relationshipType,model.voter.voterIDCardNo,model2.candidateId from BoothPublicationVoter model,Candidate model2"); 
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
	
	query.append(" and model.booth.publicationDate.publicationDateId=:publicationDateId ");
	query.append(" order by model.voter."+columnName+" "+order);
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
	
	query.append("select count(model.voter.voterId) from UserVoterDetails model , BoothPublicationVoter model1 ");
	query.append(" where model.voter.voterId = model1.voter.voterId and model1.booth.publicationDate.publicationDateId = :publicationDateId ");
	query.append(" and model.user.userId = :userId and ");
	if(type.equalsIgnoreCase(IConstants.HAMLET))
	 query.append(" model.hamlet.hamletId = :id ");
	else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
	 query.append(" model.ward.constituencyId = :id ");
	else if(type.equalsIgnoreCase("muncipalityCustomWard"))
	 query.append(" model.ward.localElectionBody.localElectionBodyId = :id ");
		
	Query queryObj = getSession().createQuery(query.toString()) ;
	queryObj.setParameter("publicationDateId", publicationDateId);
	queryObj.setParameter("id", id);
	queryObj.setParameter("userId", userId);
	return (Long)queryObj.uniqueResult();

}

public List findVotersCastInfoByBoothIdAndPublicationDateForHamlet(Long hamletId, Long publicationDateId){
	Object[] params = {hamletId, publicationDateId};
	return getHibernateTemplate().find("select count(model.voter.voterId),model.voter.gender from BoothPublicationVoter model where model.booth.boothId= ? and model.booth.publicationDate.publicationDateId = ? group by model.voter.gender ", params);
}	



//


public List<Object[]> getImpFamilesForPanchayatByPublicationIdAndVoters(Long publicationDateId ,Long userId,String type,List<Long> ids)
{
	StringBuilder queryString = new StringBuilder();
	
	     queryString.append("select count(model.voter.voterId),model.voter.houseNo, " +
			" SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
			" SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount  " +
			"from BoothPublicationVoter model,UserVoterDetails model1 " +
			"where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
			"model.voter.voterId = model1.voter.voterId and model1.user.userId = :userId  and ");
	     
	   if(type.equalsIgnoreCase(IConstants.HAMLET))
		  queryString.append("model1.hamlet.hamletId in (:ids)  ");
		else
		  queryString.append("model1.ward.constituencyId in (:ids)  ");
	 
	queryString.append(" group by model.voter.houseNo ");
	
	Query query = getSession().createQuery(queryString.toString());
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("userId", userId);
	query.setParameterList("ids", ids);
	
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
			" model1.user.userId = :userId and model1.hamlet.hamletId = :hamletId group by model.voter.gender");
	
	 query.setParameter("hamletId", hamletId);
	 query.setParameter("publicationDateId", publicationDateId);
	 query.setParameter("userId", userId);
	 
	 return query.list();
}

public List<Object[]> getVotersCountByHamletForPartialPanchayat(Long panchayatId, Long userId , Long publicationDateId,Long constituencyId)
{
	
	
	Query query = getSession().createQuery("select count(*),bpv.voter.gender from BoothPublicationVoter bpv," +
			"UserVoterDetails uvd,PanchayatHamlet ph where ph.panchayat.panchayatId = :panchayatId and " +
			" ph.hamlet.hamletId = uvd.hamlet.hamletId and uvd.voter.voterId = bpv.voter.voterId  and uvd.user.userId = :userId " +
			" and bpv.booth.publicationDate.publicationDateId = :publicationDateId and bpv.booth.constituency.constituencyId = :constituencyId  group by bpv.voter.gender");
	
	 query.setParameter("panchayatId", panchayatId);
	 query.setParameter("publicationDateId", publicationDateId);
	 query.setParameter("userId", userId);
	 query.setParameter("constituencyId", constituencyId);

	 return query.list();
}

public List<Long> getTotalVotersCountForHamletByVoterIds(List<Long> voterIds,Long publicationDateId)
{
  Query query = getSession().createQuery("select count(distinct model.voter.voterId) from BoothPublicationVoter model " +
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


public List<Object> getVoterIdsBasedOnHamletId(Long hamletId, Long userId , String type)
{  

	
	StringBuilder  query = new StringBuilder();
	query.append("select distinct model.voter.voterId from UserVoterDetails model  where ");
	if(type.equalsIgnoreCase(IConstants.HAMLET))
	query.append("model.hamlet.hamletId = ?  ");
	else
	query.append("model.ward.constituencyId = ?  ");
	query.append("and model.user.userId = ? ");
	Object[] param = {hamletId,userId};
	return getHibernateTemplate().find(query.toString(),param);
/*	return getHibernateTemplate().find("select distinct model.voter.voterId " +
						"from UserVoterDetails model  where  model.hamlet.hamletId = ? and model.user.userId = ? ",param);*/
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
		
		query.append("select distinct(model2.mobileNo) from BoothPublicationVoter model1 ,UserVoterDetails model2 " +
					 "where model2.user.userId = :userId and model2.mobileNo !='' and " +
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssignedAndUnassignedVtrsOfLclBdy(Long id,Long userId,String type){ 
		
		StringBuilder str = new StringBuilder();
		str.append(" select SUM( CASE when u.locality.localityId is not NULL THEN 1 else 0 END ) as result1 ,");
		str.append(" SUM( CASE when u.locality.localityId is NULL THEN 1 else 0 END) as result2 from UserVoterDetails u ");
		str.append(" where u.user.userId = :userId and ");
		if(type.equalsIgnoreCase(IConstants.HAMLET))
			str.append(" u.hamlet.hamletId = :id ");
		else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
			str.append(" u.ward.constituencyId = :id ");
		
		Query query =getSession().createQuery(str.toString());
		query.setParameter("id", id);
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
	
	/*public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdMultipleALocation(Long userId,String locationType,List<Long> locationIds,Long publicationDateId,Long constituencyId)
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
			
			str.append("model2.hamlet.hamletId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and model.booth.constituency.constituencyId = :constituencyId and ");
			str.append(" model2.hamlet.hamletId  in (:locationId)  group by model2.hamlet.hamletId,");
		}
		str.append("  model2.casteState.caste.casteId,model.voter.gender order by model2.casteState.caste.casteName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("locationId", locationIds);
		if(locationType.equalsIgnoreCase("hamlet") || locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") ||  "Local Election Body".equalsIgnoreCase(locationType)){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}*/
	
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdMultipleALocation(Long userId,String locationType,List<Long> locationIds,Long publicationDateId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName,  ");
		
		
		if(locationType.equalsIgnoreCase("constituency")){
			
			str.append(" model.booth.constituency.constituencyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.constituency.constituencyId = :constituencyId2 group by ");
			
		}else if(locationType.equalsIgnoreCase("mandal")){
			
			str.append(" model.booth.tehsil.tehsilId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.tehsil.tehsilId in (:locationId) and model3.constituency.constituencyId = :constituencyId and model3.localBody is null  and model3.publicationDate.publicationDateId = :publicationDateId ) " +
					" group by model.booth.tehsil.tehsilId,");
			
		}else if(locationType.equalsIgnoreCase("booth")){
			
			str.append(" model.booth.boothId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.constituency.constituencyId = :constituencyId2  group by model.booth.boothId,");
			
		}else if(locationType.equalsIgnoreCase("panchayat")){
			
			str.append(" model.booth.panchayat.panchayatId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.constituency.constituencyId = :constituencyId2 and model.booth.panchayat.panchayatId is not null group by model.booth.panchayat.panchayatId, ");
			
		}else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType)){
			
			str.append("model.booth.localBody.localElectionBodyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.boothId in(select model3.boothId from Booth model3 where model3.localBody.localElectionBodyId  in (:locationId) and model3.constituency.constituencyId = :constituencyId and model3.publicationDate.publicationDateId = :publicationDateId ) " +
					"  group by model.booth.localBody.localElectionBodyId,");
			
		}else if(locationType.equalsIgnoreCase("ward")){
			
			str.append(" model.booth.localBodyWard.constituencyId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
			str.append(" model.booth.constituency.constituencyId = :constituencyId2 group by model.booth.localBodyWard.constituencyId,");
			
		}else if(locationType.equalsIgnoreCase("hamlet")){
			
			str.append("model2.hamlet.hamletId from BoothPublicationVoter model,UserVoterDetails model2 where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and model.booth.constituency.constituencyId = :constituencyId and ");
			str.append(" model2.hamlet.hamletId  in (:locationId)  group by model2.hamlet.hamletId,");
		}
		str.append("  model2.casteState.caste.casteId,model.voter.gender order by model2.casteState.caste.casteName ");
		
		Query query = getSession().createQuery(str.toString()) ;
		
		if(locationType.equalsIgnoreCase("constituency") || locationType.equalsIgnoreCase("booth") || locationType.equalsIgnoreCase("panchayat") ||
				locationType.equalsIgnoreCase("ward")){
			query.setParameter("constituencyId2", constituencyId);
		}
		else
			query.setParameterList("locationId", locationIds);
		
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		
		if(locationType.equalsIgnoreCase("hamlet") || locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") ||  "Local Election Body".equalsIgnoreCase(locationType)){
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
	public Long getTotalVotersCountForHamletByBooth(Long userId , Long id,Long publicationDateId,String type , Long boothId ,String myType){
		StringBuilder query = new StringBuilder();
		
		query.append("select count( distinct model.voter.voterId) from UserVoterDetails model , " +
				"BoothPublicationVoter model1 " +
				" where model.voter.voterId = model1.voter.voterId and " +
				" model1.booth.publicationDate.publicationDateId = :publicationDateId" +
				" and model.user.userId = :userId and " +
				" model1.booth.boothId = :boothId and ");
		if(myType.equalsIgnoreCase(IConstants.HAMLET) || type.equalsIgnoreCase("booth") )
			query.append("  model.hamlet.hamletId= :id  ");
			else 
				query.append("  model.ward.constituencyId = :id  ");	
			//	"model.hamlet.hamletId = :id");
		
		
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("id", id);
		queryObj.setParameter("userId", userId);
		queryObj.setParameter("boothId", boothId);
		return (Long)queryObj.uniqueResult();

	}
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdInALocationByBooth(Long userId,String locationType,Long locationId,Long publicationDateId,Long boothId, String type)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model2.casteState.caste.casteName,model.voter.gender,count(model.voter.voterId),model2.casteState.casteStateId,model2.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		str.append("  model.booth.boothId= :boothId and ");
		
		if(type.equalsIgnoreCase(IConstants.HAMLET) || locationType.equalsIgnoreCase("booth") )
		str.append("  model2.hamlet.hamletId= :locationId  ");
		else 
		str.append("  model2.ward.constituencyId = :locationId  ");	
		
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
	public List<Object[]> getVoterDetailsByHamletId(Long hamletId,Long publicationDateId,Long userId ,String cond)
	{
		Query query = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age," +
				" model.booth.boothId ,model.voter.voterId, " +
				" model.voter.gender,model.voter.age,model.booth.partNo,model1.locality.name  from BoothPublicationVoter model , UserVoterDetails model1  " +
				" where  " +
				" model.voter.voterId = model1.voter.voterId and model.booth.publicationDate.publicationDateId =:publicationDateId and " 
				+cond+" and model1.user.userId = :userId "+
				" order by model1.locality.name");
		
		query.setParameter("id", hamletId);
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
	public List<Object[]> getPublicationUserCount(Long userId,Long publicationDateId , Long Id,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select  count(b.voter.voterId) , SUM( CASE when u.locality.localityId is not NULL THEN 1 else 0 END ) as result1 , ");
		str.append(" SUM( CASE when u.locality.localityId is NULL THEN 1 else 0 END) as result2 from BoothPublicationVoter b , UserVoterDetails u ");
		str.append(" join b.booth bb where b.voter.voterId=u.voter.voterId and u.user.userId = :userId and bb.publicationDate.publicationDateId = :publicationDateId and ");
		if(type.equalsIgnoreCase(IConstants.HAMLET))
			str.append(" u.hamlet.hamletId = :id ");
		else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
			str.append(" u.ward.constituencyId = :id ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("id", Id);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	
	
	
	public List<Object[]> getFamiliesInBooth(Long userId, Long hamletId , Long boothId,Long publicationDateId , Long constituencyId , String cond)
	{
		
		Query query = getSession().createQuery("select count( distinct model.voter.voterId),model.voter.houseNo " +
				" from BoothPublicationVoter model1 ,UserVoterDetails model " +
				"where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and model.voter.voterId = model1.voter.voterId and " +
				" model.user.userId = :userId and " +
				" model1.booth.boothId = :boothId and " +
				" " +cond+
				" and model1.booth.constituency.constituencyId = :constituencyId group by model.voter.houseNo ");
		
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("boothId", boothId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("id", hamletId);
		
		return query.list();
		
	}
	
	
	public List<Voter> getVoterDetailsByHamletForUser(Long userId,Long id,Long publicationDateId ,String cond)
	{
		
		Query query = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age," +
				" model.booth.boothId ,model.voter.voterId,model.voter.gender," +
				" model.voter.age,model.booth.partNo from BoothPublicationVoter model , " +
				" UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId and " +
				" model1.user.userId = :userId and " +cond+
				" and model.booth.publicationDate.publicationDateId = :publicationDateId");
		
		
		query.setParameter("userId", userId);
		query.setParameter("id", id);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
		
	}
	
	public List<Object[]> getVotersCountByGenderInBooth(Long userId ,Long hametId , Long  boothId ,Long  publicationDateId,Long constituencyId ,String cond)
	{
		
		Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender " +
				" from BoothPublicationVoter model1 ,UserVoterDetails model " +
				"where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and model.voter.voterId = model1.voter.voterId and" +
				" model.user.userId = :userId and " +
				" model1.booth.boothId = :boothId  and" +
				" " +cond+
				" and model1.booth.constituency.constituencyId = :constituencyId group by model.voter.gender");
		
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("boothId", boothId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("id", hametId);
		
		return query.list();
		
		
	}
	
	
	public List<Long> getAllBoothsInHamletByUser(Long userId,Long hamletId,Long publicationDateId , Long constituencyId ,String query1)
	{
		
		Query query = getSession().createQuery("select " +
				"distinct(model1.booth.boothId)" +
				" from BoothPublicationVoter model1 ,UserVoterDetails model " +
				" where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and model.voter.voterId = model1.voter.voterId and" +
				" model.user.userId = :userId and " +
				""+query1+
				" and model1.booth.constituency.constituencyId = :constituencyId");
		
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		query.setParameter("id", hamletId);
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

	public List<Object[]> getVoterAttributeDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(BPV.voter.voterId),UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender,UVCV.categoryValue from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				" VCV.voter.voterId = BPV.voter.voterId  and UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  " +
				" and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			queryString.append(" BPV.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			queryString.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("booth"))
			queryString.append(" BPV.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			queryString.append(" BPV.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
			queryString.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			queryString.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
		
		queryString.append("  group by UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") ||  "Local Election Body".equalsIgnoreCase(locationType)){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	public List<Object[]> getVoterAttributeDetailsForHamlet(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(*),UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV,UserVoterDetails UVD where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				" VCV.voter.voterId = BPV.voter.voterId and BPV.voter.voterId = UVD.voter.voterId and UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  " +
				" and UVD.user.userId = :userId and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId and ");
		if("hamlet".equalsIgnoreCase(locationType))
		  queryString.append(" UVD.hamlet.hamletId = :locationId ");
		else
		  queryString.append(" UVD.ward.constituencyId = :locationId ");
		
		queryString.append("  group by UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	
	public List<Object[]> getAgeWiseDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId,Long startAge,
			Long endAge){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(BPV.voter.voterId),BPV.voter.gender,UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,UVCV.categoryValue  from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				" VCV.voter.voterId = BPV.voter.voterId  and UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  " +
				" and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId and BPV.voter.age >="+startAge+" and BPV.voter.age <="+endAge+" and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			queryString.append(" BPV.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			queryString.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("booth"))
			queryString.append(" BPV.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			queryString.append(" BPV.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
			queryString.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			queryString.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
		
		queryString.append("  group by UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") ||  "Local Election Body".equalsIgnoreCase(locationType)){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	public List<Object[]> getCasteWiseDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select UVD.casteState.caste.casteId , UVD.casteState.caste.casteName ," +
				" count(BPV.voter.voterId),BPV.voter.gender,UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,UVCV.categoryValue  from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				" BoothPublicationVoter BPV ,UserVoterDetails UVD where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				" VCV.voter.voterId = BPV.voter.voterId  and UVD.voter.voterId = BPV.voter.voterId and UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  " +
				" and VCV.user.userId = :userId and UVCV.user.userId = :userId and UVD.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId  and ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			queryString.append(" BPV.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			queryString.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("booth"))
			queryString.append(" BPV.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			queryString.append(" BPV.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
			queryString.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			queryString.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			queryString.append(" UVD.hamlet.hamletId = :locationId ");
		else if(locationType.equalsIgnoreCase("customWard"))
		queryString.append(" UVD.ward.constituencyId = :locationId");
		queryString.append("  group by UVCV.userVoterCategoryValueId,UVD.casteState.caste.casteId,BPV.voter.gender ");
		queryString.append(" order by UVCV.categoryValue, UVD.casteState.caste.casteName ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") ||  "Local Election Body".equalsIgnoreCase(locationType)){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	
	public List<Object[]> getAgeWiseDetailsForHamlet(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId,Long startAge,
			Long endAge){
		StringBuilder queryString = new StringBuilder();
	
		queryString.append("select count(BPV.voter.voterId),BPV.voter.gender,UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,UVCV.categoryValue from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV,UserVoterDetails UVD where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
			" VCV.voter.voterId = BPV.voter.voterId and BPV.voter.voterId = UVD.voter.voterId and UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  " +
			" and UVD.user.userId = :userId and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId and " +
			" UVD.hamlet.hamletId = :locationId and BPV.voter.age >="+startAge+" and BPV.voter.age <="+endAge+"  group by UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
	
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getCasteWiseDetailsForHamlet(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId			){
		StringBuilder queryString = new StringBuilder();
		
		/*queryString.append("select UVD.casteState.caste.casteId , UVD.casteState.caste.casteName ," +
				" count(BPV.voter.voterId),BPV.voter.gender,UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,UVCV.categoryValue  from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				" BoothPublicationVoter BPV ,UserVoterDetails UVD where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				" VCV.voter.voterId = BPV.voter.voterId  and UVD.voter.voterId = BPV.voter.voterId and UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  " +
				" and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId  and ");
		*/
		
	
		queryString.append("select UVD.casteState.caste.casteId , UVD.casteState.caste.casteName ," +
				" count(BPV.voter.voterId),BPV.voter.gender,UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,UVCV.categoryValue from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV,UserVoterDetails UVD where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
			" VCV.voter.voterId = BPV.voter.voterId and BPV.voter.voterId = UVD.voter.voterId and UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  " +
			" and UVD.user.userId = :userId and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId and " +
			" UVD.hamlet.hamletId = :locationId  group by UVD.casteState.caste.casteId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
	
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
		
	public List<Object[]> getCategoryWiseVoterDetailsByCategoryId(String locationType, Long locationValue, Long constituencyId, Long categoryId,Long publicationId,Long userId,Integer startIndex,
			Integer maxRecords, String order, String columnName)
	{
		StringBuilder queryString = new StringBuilder();
		queryString.append("select BPV.voter,BPV.serialNo,BPV.booth.partNo from BoothPublicationVoter BPV, VoterCategoryValue VCV ");
		queryString.append(" where BPV.voter.voterId = VCV.voter.voterId  and VCV.userVoterCategoryValue.userVoterCategory.userVoterCategoryId =:categoryId ");
		queryString.append(" and VCV.user.userId =:userId and BPV.booth.constituency.constituencyId =:constituencyId and BPV.booth.publicationDate.publicationDateId =:publicationId ");
		
		if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			queryString.append(" and BPV.booth.panchayat.panchayatId =:locationValue ");
		else if(locationType.equalsIgnoreCase(IConstants.WARD))
			queryString.append(" and BPV.booth.localBodyWard.constituencyId =:locationValue ");
		else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
			queryString.append(" and BPV.booth.boothId =:locationValue ");
				
		if(columnName.equalsIgnoreCase("initial"))
			queryString.append(" order by cast(BPV.booth.partNo , int),BPV.serialNo,BPV.voter.houseNo");
		else if(columnName.equalsIgnoreCase("serialNo"))
		  queryString.append(" order by BPV.serialNo "+order+"");
		else if(columnName.equalsIgnoreCase("partNo"))
			queryString.append(" order by BPV.booth.partNo "+order+"");
		else
			queryString.append(" order by BPV.voter."+columnName+" "+order+"");
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("locationValue", locationValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("categoryId", categoryId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		 
		return query.list();
	}
	
	
	public List<Object[]> getCategoryWiseVoterDetailsByHamletId(Long locationValue, Long constituencyId, Long categoryId,Long publicationId,Long userId,Integer startIndex,
			Integer maxRecords, String order, String columnName)
	{
		StringBuilder queryString = new StringBuilder();
		queryString.append("select BPV.voter,BPV.serialNo,BPV.booth.partNo from BoothPublicationVoter BPV, VoterCategoryValue VCV,UserVoterDetails UVD ");
		queryString.append(" where BPV.voter.voterId = VCV.voter.voterId and BPV.voter.voterId = UVD.voter.voterId and VCV.userVoterCategoryValue.userVoterCategory.userVoterCategoryId =:categoryId ");
		queryString.append(" and VCV.user.userId=UVD.user.userId and VCV.user.userId =:userId and BPV.booth.constituency.constituencyId =:constituencyId and BPV.booth.publicationDate.publicationDateId =:publicationId ");
		queryString.append(" and UVD.hamlet.hamletId =:locationValue ");
		
		if(columnName.equalsIgnoreCase("initial"))
			queryString.append(" order by cast(BPV.booth.partNo , int),BPV.serialNo,BPV.voter.houseNo");
		else if(columnName.equalsIgnoreCase("serialNo"))
		  queryString.append(" order by BPV.serialNo "+order+"");
		else if(columnName.equalsIgnoreCase("partNo"))
			queryString.append(" order by BPV.booth.partNo "+order+"");
		else
			queryString.append(" order by BPV.voter."+columnName+" "+order+"");
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("locationValue", locationValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("categoryId", categoryId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		 
		return query.list();
	}
	
	
	 public List getcategoryWiseVotersCount(String locationType, Long locationValue, Long constituencyId, Long categoryId,Long publicationId,Long userId){
			
		 StringBuilder queryString = new StringBuilder();
			queryString.append("select count(BPV.voter.voterId) from BoothPublicationVoter BPV, VoterCategoryValue VCV,UserVoterDetails UVD ");
			queryString.append(" where BPV.voter.voterId = VCV.voter.voterId and BPV.voter.voterId = UVD.voter.voterId and VCV.userVoterCategoryValue.userVoterCategory.userVoterCategoryId =:categoryId ");
			queryString.append(" and VCV.user.userId =:userId and BPV.booth.constituency.constituencyId =:constituencyId and BPV.booth.publicationDate.publicationDateId =:publicationId ");
			
			if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				queryString.append(" and BPV.booth.panchayat.panchayatId =:locationValue ");
			else if(locationType.equalsIgnoreCase(IConstants.WARD))
				queryString.append(" and BPV.booth.localBodyWard.constituencyId =:locationValue ");
			else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
				queryString.append(" and BPV.booth.boothId =:locationValue ");
			else if(locationType.equalsIgnoreCase(IConstants.HAMLET))
				queryString.append(" and UVD.hamlet.hamletId =:locationValue ");
			
			
			Query query = getSession().createQuery(queryString.toString());
			
		  query.setParameter("locationValue", locationValue);
		  query.setParameter("constituencyId", constituencyId);
		  query.setParameter("categoryId", categoryId);
		  query.setParameter("publicationId", publicationId);
		  query.setParameter("userId", userId);
				
		return query.list();

	}
		
	public List<Object[]> getVoterAttributeDetailsForDifferentLocations(Long userId,Long attributeId,String locationType,Set<Long> locationIds,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		String str = ",UVCV.userVoterCategoryValueId,BPV.voter.gender from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				" VCV.voter.voterId = BPV.voter.voterId  and UVCV.userVoterCategory.userVoterCategoryId = :attributeId " +
				" and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId and ";
		
		if(locationType.equalsIgnoreCase("constituency"))
			queryString.append("select count(*),BPV.booth.constituency.constituencyId"+str+" BPV.booth.constituency.constituencyId in (:locationIds) group by BPV.booth.constituency.constituencyId,");
		else if(locationType.equalsIgnoreCase("mandal"))
			queryString.append("select count(*),BPV.booth.tehsil.tehsilId"+str+" BPV.booth.tehsil.tehsilId in (:locationIds) and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId group by BPV.booth.tehsil.tehsilId,");
		else if(locationType.equalsIgnoreCase("booth"))
			queryString.append("select count(*),BPV.booth.boothId"+str+" BPV.booth.boothId in (:locationIds) group by BPV.booth.boothId,");
		else if(locationType.equalsIgnoreCase("panchayat"))
			queryString.append("select count(*),BPV.booth.panchayat.panchayatId"+str+" BPV.booth.panchayat.panchayatId in (:locationIds) group by BPV.booth.panchayat.panchayatId,");
		else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
			queryString.append("select count(*),BPV.booth.localBody.localElectionBodyId"+str+" BPV.booth.localBody.localElectionBodyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId  group by BPV.booth.localBody.localElectionBodyId,");
		else if(locationType.equalsIgnoreCase("ward"))
			queryString.append("select count(*),BPV.booth.localBodyWard.constituencyId"+str+" BPV.booth.localBodyWard.constituencyId in (:locationIds)  group by BPV.booth.localBodyWard.constituencyId,");
		
		queryString.append(" UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("locationIds", locationIds);
		query.setParameter("attributeId", attributeId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("localElectionBody") ||  "Local Election Body".equalsIgnoreCase(locationType)){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	public List<Object[]> getVoterAttributeDetailsForHamletForDifferentLocations(Long userId,Long attributeId,String locationType,Set<Long> locationIds,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(*),UVD.hamlet.hamletId,UVCV.userVoterCategoryValueId,BPV.voter.gender from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV,UserVoterDetails UVD where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				" VCV.voter.voterId = BPV.voter.voterId and BPV.voter.voterId = UVD.voter.voterId and UVCV.userVoterCategory.userVoterCategoryId = :attributeId  " +
				" and UVD.user.userId = :userId and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId and ");
		    
		    if("hamlet".equalsIgnoreCase(locationType))
			  queryString.append(" UVD.hamlet.hamletId in (:locationIds)  group by UVD.hamlet.hamletId,");
			else
			  queryString.append(" UVD.ward.constituencyId in (:locationIds)  group by UVD.ward.constituencyId,");
			
			queryString.append("  UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("locationIds", locationIds);
		query.setParameter("attributeId", attributeId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	
	public List<Object[]> getVotersCountForCustomWardBooths(Long constituencyId,Long wardId,Long publicationDateId,Long userId)
	{
		Query queryObj = getSession().createQuery("select count(model.voter.voterId),model.voter.gender,model.booth.boothId,model.booth.partNo from BoothPublicationVoter model,UserVoterDetails model2 " +
				" where model.voter.voterId = model2.voter.voterId and model2.user.userId =:userId and model2.ward.constituencyId =:wardId and model.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and model.booth.constituency.constituencyId =:constituencyId group by model.booth.boothId, model.voter.gender");
		
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("wardId", wardId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("userId", userId);
		
		return queryObj.list();
	}
	

	
	public List<Object[]> getVotersCountForCustomWardByPublicationId(Long customwardId,Long publicationDateId,Long constituencyId,Long userId){
		Query query = getSession().createQuery("select count(BPV.voter.voterId),BPV.voter.gender from BoothPublicationVoter BPV,UserVoterDetails UVD where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" UVD.ward.constituencyId = :customwardId and BPV.voter.voterId = UVD.voter.voterId and UVD.user.userId = :userId and BPV.booth.constituency.constituencyId = :constituencyId group by BPV.voter.gender ") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("customwardId", customwardId);
		  query.setParameter("constituencyId", constituencyId);
		  query.setParameter("userId", userId);
		  return query.list();
	}
	
	
	 public List<Object[]> getVotersDetailsByCustomWardId(Long wardId,Long publicationDateId,Long constituencyId,Long userId, Integer startIndex,
				Integer maxRecords, String order, String columnName) {  
		 Query query = null;
		 
		 if("initial".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select BPV.voter,BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV,UserVoterDetails UVD " +
					"where UVD.ward.constituencyId =:wardId and BPV.voter.voterId = UVD.voter.voterId and UVD.user.userId = :userId and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by cast(BPV.booth.partNo , int),BPV.serialNo,BPV.voter.houseNo");
		 }		 else if("partNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select BPV.voter,BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV,UserVoterDetails UVD " +
						"where UVD.ward.constituencyId =:wardId and BPV.voter.voterId = UVD.voter.voterId and UVD.user.userId = :userId and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by cast(BPV.booth.partNo , int) "+order); 
		 }
		 else if("serialNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select BPV.voter,BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV,UserVoterDetails UVD " +
						"where UVD.ward.constituencyId =:wardId and BPV.voter.voterId = UVD.voter.voterId and UVD.user.userId = :userId and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by BPV.serialNo  "+order); 
		 }
		 else
		 {
			 query = getSession().createQuery("select BPV.voter,BPV.booth.partNo,BPV.serialNo from BoothPublicationVoter BPV,UserVoterDetails UVD " +
						"where UVD.ward.constituencyId =:wardId and BPV.voter.voterId = UVD.voter.voterId and UVD.user.userId = :userId and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by BPV.voter."+columnName+" "+order); 
		 }

		 query.setParameter("wardId", wardId);
		 query.setParameter("userId", userId);
		 query.setParameter("constituencyId", constituencyId);
		 query.setParameter("publicationDateId", publicationDateId);
		 query.setFirstResult(startIndex);
		 query.setMaxResults(maxRecords);

		 return query.list();
		 
	 }  
	 
	 public List getVotersCountByCustomWardId(Long wardId,Long publicationDateId,Long constituencyId,Long userId)
	 {
		 Query query = null;
		 
		 
			 query = getSession().createQuery("select count(BPV.serialNo) from BoothPublicationVoter BPV,UserVoterDetails UVD " +
					"where UVD.ward.constituencyId =:wardId and BPV.voter.voterId = UVD.voter.voterId and UVD.user.userId = :userId and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDateId ");
		

		 query.setParameter("wardId", wardId);
		 query.setParameter("userId", userId);
		 query.setParameter("constituencyId", constituencyId);
		 query.setParameter("publicationDateId", publicationDateId);
		

		 return query.list();
		 
	 } 
		/**
		 * This  is Uesd For Getting all voter in a house  count for a customward good for muncipality Level
		 *  
		 * @author Anilkumar Ravula
		 * @param Long userId
		 * @param Long id
		 * @param Long publicationDateId
		 * @param Long constituencyId
		 * @param String type 
		 * @return List<Object[]>
		 */
		public List<Object[]> getFamiliesInWard(Long userId, Long id ,Long publicationDateId , Long constituencyId , String type)
		{
			
			Query query = getSession().createQuery("select count( distinct model.voter.voterId),model.voter.houseNo " +
					" from BoothPublicationVoter model1 ,UserVoterDetails model " +
					"where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
					" and model.voter.voterId = model1.voter.voterId and " +
					" model.user.userId = :userId and " +
					" model.ward.constituencyId = :id "+
					" and model1.booth.constituency.constituencyId = :constituencyId group by model.voter.houseNo ");
			
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("id", id);
			
			return query.list();
			
		}
		/**
		 * This  is Uesd For getting male and female count
		 *  
		 * @author Anilkumar Ravula
		 * @param Long userId
		 * @param Long id
		 * @param Long publicationDateId
		 * @param Long constituencyId
		 * @param String type 
		 * @return List<Object[]>
		 */
		
		public List<Object[]> getVotersCountByGender(Long userId ,Long id ,Long  publicationDateId,Long constituencyId ,String type)
		{
			
			Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender " +
					" from BoothPublicationVoter model1 ,UserVoterDetails model " +
					"where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
					" and model.voter.voterId = model1.voter.voterId and" +
					" model.user.userId = :userId and " +
					" model.ward.constituencyId = :id "+
					" and model1.booth.constituency.constituencyId = :constituencyId group by model.voter.gender");
			
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
		
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("id", id);
			
			return query.list();
			
			
		}
	 public List<Object[]> getCustomWardWiseVotersInfoByLocalEleId(Long constituencyId,Long publicationId,Long localEleBodyId,Long userId)
	 {
		 Query queryObj = getSession().createQuery("select UVD.ward.constituencyId,UVD.ward.name, count(BPV.voter.voterId)," +
		 		" BPV.voter.gender from BoothPublicationVoter BPV,UserVoterDetails UVD where UVD.voter.voterId = BPV.voter.voterId " +
		 		" and BPV.booth.publicationDate.publicationDateId =:publicationId and BPV.booth.constituency.constituencyId =:constituencyId " +
		 		" and UVD.ward.localElectionBody.localElectionBodyId =:localEleBodyId and UVD.user.userId =:userId group by UVD.ward.constituencyId,UVD.voter.gender ");
		 
		 queryObj.setParameter("constituencyId", constituencyId);
		 queryObj.setParameter("publicationId", publicationId);
		 queryObj.setParameter("localEleBodyId", localEleBodyId);
		 queryObj.setParameter("userId", userId);
		 
		 return queryObj.list();
	 }
	 
	 public Long getCasteCountForLocalEleBody(Long userId,Long publicationId,Long constituencyId,Long localEleBodyId)
	 {
		 Query queryObj = getSession().createQuery("select count(UVD.casteState.casteStateId) from BoothPublicationVoter BPV,UserVoterDetails UVD where UVD.voter.voterId = BPV.voter.voterId " +
				 " and BPV.booth.publicationDate.publicationDateId =:publicationId and BPV.booth.constituency.constituencyId =:constituencyId " +
			 		" and UVD.ward.localElectionBody.localElectionBodyId =:localEleBodyId and UVD.user.userId =:userId ");
		 
		 queryObj.setParameter("constituencyId", constituencyId);
		 queryObj.setParameter("publicationId", publicationId);
		 queryObj.setParameter("localEleBodyId", localEleBodyId);
		 queryObj.setParameter("userId", userId);
		 
		 return (Long) queryObj.uniqueResult();
	 }
	 
	  public List<Object[]> getWardsByLocalEleBodyIdId(Long userId,Long publicationId,Long localEleBodyId,Long constituencyId)
	  {
		  Query queryObj = getSession().createQuery("select distinct UVD.ward.constituencyId,UVD.ward.name from BoothPublicationVoter BPV,UserVoterDetails UVD where " +
		  		" UVD.voter.voterId = BPV.voter.voterId and UVD.user.userId =:userId and BPV.booth.constituency.constituencyId =:constituencyId and " +
		  		" UVD.ward.localElectionBody.localElectionBodyId =:localEleBodyId and BPV.booth.publicationDate.publicationDateId =:publicationDateId order by UVD.ward.name ");
		  
		  queryObj.setParameter("userId", userId);
		  queryObj.setParameter("publicationDateId", publicationId);
		  queryObj.setParameter("constituencyId", constituencyId);
		  queryObj.setParameter("localEleBodyId", localEleBodyId);
			
		  return queryObj.list();
	  }
	  
	  public List<Object[]> getVoterDetailsForCustomWard(Long wardId, Long publicationDateId, Long userId,Long casteStateId)
	  {
		  Query query = getSession().createQuery("select distinct BPV.voter,BPV.booth.boothId,UVD.mobileNo from BoothPublicationVoter BPV , UserVoterDetails UVD where BPV.voter.voterId = UVD.voter.voterId and " +
		  		"  BPV.booth.publicationDate.publicationDateId =:publicationDateId and UVD.user.userId =:userId and " +
		  		" UVD.casteState.casteStateId =:casteStateId and UVD.ward.constituencyId =:wardId ");
		  
		  query.setParameter("wardId", wardId);
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("userId", userId);
		  query.setParameter("casteStateId", casteStateId);
		  
		  return query.list();
	  }
	  
	  public List<Object[]> getVoterDetailsForCustomWardBooths(Long customWardId,Long boothId,Long userId,Long publicationDateId,Long casteStateId)
	  {
		  Query queryObj = getSession().createQuery("select distinct BPV.voter,BPV.booth.boothId,UVD.mobileNo from BoothPublicationVoter BPV, UserVoterDetails UVD where BPV.voter.voterId = UVD.voter.voterId and " +
		  		" BPV.booth.publicationDate.publicationDateId =:publicationDateId and UVD.user.userId =:userId and UVD.casteState.casteStateId =:casteStateId and UVD.ward.constituencyId =:wardId " +
		  		" and BPV.booth.boothId =:boothId ");
		  
		  queryObj.setParameter("casteStateId",casteStateId);
		  queryObj.setParameter("boothId", boothId);
		  queryObj.setParameter("userId", userId);
		  queryObj.setParameter("publicationDateId", publicationDateId);
		  queryObj.setParameter("wardId", customWardId);
		  
		  return queryObj.list();
	  }
	
	  
	  public List<Object[]> getPartysOrCatstesForSelectedLevel(Long userId,List<Long> ids ,String type,String status,Long publicationId)
	  {
		  StringBuffer queryString = new StringBuffer();
		  if(status.equalsIgnoreCase("cast"))
		  {
			  queryString.append("select distinct(UVD.casteState.casteStateId) ,UVD.casteState.caste.casteName from  UserVoterDetails UVD ,BoothPublicationVoter BPV " +
		  		" where UVD.voter.voterId = BPV.voter.voterId and UVD.user.userId =:userId and ");
		  }
		  else if(status.equalsIgnoreCase("party"))
		  {
			  queryString.append("select distinct(UVD.party.partyId) ,UVD.party.shortName from  UserVoterDetails UVD ,BoothPublicationVoter BPV " +
				  		" where UVD.voter.voterId = BPV.voter.voterId and UVD.user.userId =:userId and "); 
		  }
		  if(type.equalsIgnoreCase("mandal"))
		  {
			  queryString.append("BPV.booth.tehsil.tehsilId in (:ids)");
		  }
		  else if(type.equalsIgnoreCase("panchayat"))
		  {
			  queryString.append("BPV.booth.panchayat.panchayatId in (:ids)");
		  }
		  else if(type.equalsIgnoreCase("booth"))
		  {
			  queryString.append("BPV.booth.boothId in (:ids) ");
		  }
		  queryString.append(" and BPV.booth.publicationDate.publicationDateId = :publicationId order by UVD.casteState.caste.casteName");
		  Query query = getSession().createQuery(queryString.toString());
		  query.setParameter("userId", userId);
		  query.setParameter("publicationId", publicationId);
		 query.setParameterList("ids", ids);
		  return query.list(); 
	  }
	  
	  public List<Object[]> getCatstesForBooths(Long userId,List<Long> boothIds ,Long publicationId)
	  {
		  Query query = getSession().createQuery("select distinct(UVD.casteState.casteStateId) ,UVD.casteState.caste.casteName from  UserVoterDetails UVD ,BoothPublicationVoter BPV , QueryTemp QT where " +
		  		" UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId and UVD.user.userId =:userId and BPV.booth.boothId =  QT.value " +
		  		" order by UVD.casteState.caste.casteName ");
		  query.setParameter("userId", userId);
		  query.setParameter("publicationId", publicationId);
		  return query.list(); 
	  }
	  
	  public List<Object[]> getCastesListForSelectedMuncipality(Long userId , Long id,Long constituencyId,String status)
	  {
		  StringBuffer queryString = new StringBuffer();
		  if(status.equalsIgnoreCase("cast"))
		  {
			  queryString.append("select distinct(UVD.casteState.casteStateId) ,UVD.casteState.caste.casteName from  UserVoterDetails UVD ,BoothPublicationVoter BPV ");  
		  }
		  else if(status.equalsIgnoreCase("party"))
		  {
			  queryString.append("select distinct(UVD.party.partyId) ,UVD.party.shortName from  UserVoterDetails UVD ,BoothPublicationVoter BPV " );  
		  }
		  queryString.append(" where UVD.voter.voterId = BPV.voter.voterId and UVD.user.userId =:userId and BPV.booth.localBody.localElectionBodyId = :id " +
			  		" and BPV.booth.constituency.constituencyId = :constituencyId");
		  
		  Query query = getSession().createQuery(queryString.toString());
		  query.setParameter("userId", userId);
		  query.setParameter("id", id);
		  query.setParameter("constituencyId", constituencyId);
		  return query.list();
		  
	  }
	  
	  public List<Object[]> getAllCastesOrPartesForSelectedWards(Long userId , List<Long> ids ,Long constituencyId,String status)
	  {
		  StringBuffer queryString = new StringBuffer();
		  if(status.equalsIgnoreCase("cast"))
		  {
			  queryString.append("select distinct(UVD.casteState.casteStateId) ,UVD.casteState.caste.casteName from  UserVoterDetails UVD ,BoothPublicationVoter BPV ");  
		  }
		  else if(status.equalsIgnoreCase("party"))
		  {
			  queryString.append("select distinct(UVD.party.partyId) ,UVD.party.shortName from  UserVoterDetails UVD ,BoothPublicationVoter BPV " );  
		  }
		  queryString.append(" where UVD.voter.voterId = BPV.voter.voterId and UVD.user.userId =:userId and BPV.booth.localBodyWard.constituencyId in (:ids) " +
			  		" and BPV.booth.constituency.constituencyId = :constituencyId");
		  
		  Query query = getSession().createQuery(queryString.toString());
		  query.setParameter("userId", userId);
		  query.setParameterList("ids", ids);
		  query.setParameter("constituencyId", constituencyId);
		  return query.list(); 
	  }
	  
	  public List<Object[]> getVoterCastAndPartyCountForDifferentLocations(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,String type,List<String> partNos){
			StringBuilder queryString = new StringBuilder();
			StringBuilder queryString1 = new StringBuilder();
			String var ="";
			if("party".equalsIgnoreCase(type)){
			    queryString1.append(",UVD.party.partyId,UVD.party.shortName");
			    var = "UVD.party.partyId,";
			}else{
				queryString1.append(",UVD.casteState.casteStateId,UVD.casteState.caste.casteName");
				var = "UVD.casteState.casteStateId,";
			}
			queryString1.append("  from UserVoterDetails UVD,BoothPublicationVoter BPV " +
					" where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId  ");
			if("party".equalsIgnoreCase(type)){
				queryString1.append(" and UVD.party is not null  and UVD.party.partyId in(:attributeIds) ");
			}
			else if("caste".equalsIgnoreCase(type)){
				queryString1.append(" and UVD.casteState is not null and UVD.casteState.casteStateId in(:attributeIds) ");
			}
			
			 if(locationType.equalsIgnoreCase("mandal"))
				queryString.append("select count(*),BPV.booth.tehsil.tehsilId,BPV.booth.tehsil.tehsilName "+queryString1+" and BPV.booth.tehsil.tehsilId in (:locationIds) and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId group by "+var+" BPV.booth.tehsil.tehsilId ");
			else if(locationType.equalsIgnoreCase("booth"))
				queryString.append("select count(*),BPV.booth.partNo,BPV.booth.partNo "+queryString1+"  and BPV.booth.partNo in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId group by "+var+" BPV.booth.partNo ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				queryString.append("select count(*),BPV.booth.panchayat.panchayatId,BPV.booth.panchayat.panchayatName "+queryString1+" and BPV.booth.panchayat.panchayatId in (:locationIds)  group by "+var+" BPV.booth.panchayat.panchayatId ");
			else if(locationType.equalsIgnoreCase("ward"))
				queryString.append("select count(*),BPV.booth.localBodyWard.constituencyId,BPV.booth.localBodyWard.name "+queryString1+" and BPV.booth.localBodyWard.constituencyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId group by "+var+" BPV.booth.localBodyWard.constituencyId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				queryString.append("select count(*),BPV.booth.localBody.localElectionBodyId,BPV.booth.localBody.name "+queryString1+" and BPV.booth.localBody.localElectionBodyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId  group by "+var+" BPV.booth.localBody.localElectionBodyId ");
			
			 
			Query query = getSession().createQuery(queryString.toString());
			
			
			if(locationType.equalsIgnoreCase("booth"))
				query.setParameterList("locationIds",partNos);
			else
				query.setParameterList("locationIds", locationIds);
			
			query.setParameterList("attributeIds", attributeIds);	
			query.setParameter("publicationId", publicationId);
			query.setParameter("userId", userId);
			if(locationType.equalsIgnoreCase("booth") || locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("ward") || locationType.equalsIgnoreCase("localElectionBody")){
				query.setParameter("constituencyId", constituencyId);
			}
			return query.list();
		}
	  
	  public List<Object[]> getVoterCastAndPartyCount(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,String type,List<String> partNos){
			StringBuilder queryString = new StringBuilder();
			StringBuilder queryString1 = new StringBuilder();
			queryString1.append("  from UserVoterDetails UVD,BoothPublicationVoter BPV " +
					" where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId  ");
			if("party".equalsIgnoreCase(type)){
				queryString1.append(" and UVD.party is not null ");
			}
			else if("caste".equalsIgnoreCase(type)){
				queryString1.append(" and UVD.casteState is not null ");
			}
			 if(locationType.equalsIgnoreCase("mandal"))
				queryString.append("select count(*),BPV.booth.tehsil.tehsilId,BPV.booth.tehsil.tehsilName "+queryString1+" and BPV.booth.tehsil.tehsilId in (:locationIds) and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId group by BPV.booth.tehsil.tehsilId ");
			else if(locationType.equalsIgnoreCase("booth"))
				queryString.append("select count(*),BPV.booth.partNo,BPV.booth.partNo "+queryString1+"  and BPV.booth.partNo in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId group by BPV.booth.partNo ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				queryString.append("select count(*),BPV.booth.panchayat.panchayatId,BPV.booth.panchayat.panchayatName "+queryString1+" and BPV.booth.panchayat.panchayatId in (:locationIds) group by BPV.booth.panchayat.panchayatId ");
			else if(locationType.equalsIgnoreCase("ward"))
				queryString.append("select count(*),BPV.booth.localBodyWard.constituencyId,BPV.booth.localBodyWard.name "+queryString1+" and BPV.booth.localBodyWard.constituencyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId group by BPV.booth.localBodyWard.constituencyId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				queryString.append("select count(*),BPV.booth.localBody.localElectionBodyId,BPV.booth.localBody.name"+queryString1+" and BPV.booth.localBody.localElectionBodyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId  group by BPV.booth.localBody.localElectionBodyId ");
			
			 
			Query query = getSession().createQuery(queryString.toString());
			
			if(locationType.equalsIgnoreCase("booth"))
				query.setParameterList("locationIds",partNos);
			else
				query.setParameterList("locationIds", locationIds);
			
			query.setParameter("publicationId", publicationId);
			query.setParameter("userId", userId);
			if(locationType.equalsIgnoreCase("booth") || locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("ward") || locationType.equalsIgnoreCase("localElectionBody")){
				query.setParameter("constituencyId", constituencyId);
			}
			return query.list();
		}
	  
	  public List<Object[]> getVoterAttributeCountForDifferentLocations(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,List<String> partNos){
			StringBuilder queryString1 = new StringBuilder();
			StringBuilder queryString = new StringBuilder();
			queryString1.append(",UVCV.userVoterCategoryValueId,UVCV.categoryValue from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
					"BoothPublicationVoter BPV where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and VCV.voter.voterId = BPV.voter.voterId and  " +
					" VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId   and UVCV.userVoterCategoryValueId in( :attributeIds) ");
			
			if(locationType.equalsIgnoreCase("mandal"))
				queryString.append("select count(*),BPV.booth.tehsil.tehsilId,BPV.booth.tehsil.tehsilName "+queryString1+" BPV.booth.tehsil.tehsilId in (:locationIds) and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId group by UVCV.userVoterCategoryValueId,BPV.booth.tehsil.tehsilId");
			else if(locationType.equalsIgnoreCase("booth"))
				queryString.append("select count(*),BPV.booth.partNo,BPV.booth.partNo "+queryString1+" BPV.booth.partNo in (:locationIds) group by UVCV.userVoterCategoryValueId,BPV.booth.partNo");
			else if(locationType.equalsIgnoreCase("panchayat"))
				queryString.append("select count(*),BPV.booth.panchayat.panchayatId,BPV.booth.panchayat.panchayatName "+queryString1+" BPV.booth.panchayat.panchayatId in (:locationIds) group by UVCV.userVoterCategoryValueId,BPV.booth.panchayat.panchayatId");
			else if(locationType.equalsIgnoreCase("ward"))
				queryString.append("select count(*),BPV.booth.localBodyWard.constituencyId,BPV.booth.localBodyWard.name "+queryString1+" and BPV.booth.localBodyWard.constituencyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId group by UVCV.userVoterCategoryValueId,BPV.booth.localBodyWard.constituencyId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				queryString.append("select count(*),BPV.booth.localBody.localElectionBodyId,BPV.booth.localBody.name "+queryString1+" BPV.booth.localBody.localElectionBodyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId  group by UVCV.userVoterCategoryValueId,BPV.booth.localBody.localElectionBodyId ");
			
			Query query = getSession().createQuery(queryString.toString());
			if(locationType.equalsIgnoreCase("booth"))
				query.setParameterList("locationIds",partNos);
			else
				query.setParameterList("locationIds", locationIds);
			query.setParameterList("attributeIds", attributeIds);
			query.setParameter("publicationId", publicationId);
			query.setParameter("userId", userId);
			if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("ward") || locationType.equalsIgnoreCase("localElectionBody")){
				query.setParameter("constituencyId", constituencyId);
			}
			return query.list();
		}
	  
	  public List<Object[]> getVoterAttributeCount(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,List<String> partNos){
			StringBuilder queryString1 = new StringBuilder();
			StringBuilder queryString = new StringBuilder();
			queryString1.append(" from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
					"BoothPublicationVoter BPV where VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
					" VCV.voter.voterId = BPV.voter.voterId and VCV.user.userId = :userId and UVCV.user.userId = :userId and BPV.booth.publicationDate.publicationDateId = :publicationId  " +
				    " and UVCV.userVoterCategory.userVoterCategoryId in(select UVCV1.userVoterCategory.userVoterCategoryId from UserVoterCategoryValue UVCV1 where UVCV1.userVoterCategoryValueId in (:attributeIds) )");
			
			if(locationType.equalsIgnoreCase("mandal"))
				queryString.append("select count(*),BPV.booth.tehsil.tehsilId,BPV.booth.tehsil.tehsilName "+queryString1+" BPV.booth.tehsil.tehsilId in (:locationIds) and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId group by BPV.booth.tehsil.tehsilId");
			else if(locationType.equalsIgnoreCase("booth"))
				queryString.append("select count(*),BPV.booth.partNo,BPV.booth.partNo "+queryString1+" BPV.booth.partNo in (:locationIds) group by BPV.booth.partNo");
			else if(locationType.equalsIgnoreCase("panchayat"))
				queryString.append("select count(*),BPV.booth.panchayat.panchayatId,BPV.booth.panchayat.panchayatName "+queryString1+" BPV.booth.panchayat.panchayatId in (:locationIds) group by BPV.booth.panchayat.panchayatId");
			else if(locationType.equalsIgnoreCase("ward"))
				queryString.append("select count(*),BPV.booth.localBodyWard.constituencyId,BPV.booth.localBodyWard.name "+queryString1+" and BPV.booth.localBodyWard.constituencyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId group by BPV.booth.localBodyWard.constituencyId ");
			else if(locationType.equalsIgnoreCase("localElectionBody"))
				queryString.append("select count(*),BPV.booth.localBody.localElectionBodyId,BPV.booth.localBody.name"+queryString1+" BPV.booth.localBody.localElectionBodyId in (:locationIds) and BPV.booth.constituency.constituencyId = :constituencyId  group by BPV.booth.localBody.localElectionBodyId ");
			
			Query query = getSession().createQuery(queryString.toString());
			if(locationType.equalsIgnoreCase("booth"))
				query.setParameterList("locationIds",partNos);
			else
				query.setParameterList("locationIds", locationIds);
			query.setParameterList("attributeIds", attributeIds);
			query.setParameter("publicationId", publicationId);
			query.setParameter("userId", userId);
			if(locationType.equalsIgnoreCase("mandal") || locationType.equalsIgnoreCase("ward") || locationType.equalsIgnoreCase("localElectionBody")){
				query.setParameter("constituencyId", constituencyId);
			}
			return query.list();
		}
	  
	  /*public List<Object[]> test1(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,String type,List<String> partNos){
			StringBuilder queryString = new StringBuilder();
			
			    queryString.append("select count(*),BPV.booth.partNo,BPV.booth.partNo,UVD.casteState.casteStateId,UVD.casteState.caste.casteName");
				queryString.append("  from UserVoterDetails UVD,BoothPublicationVoter BPV " +
					" where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId  and BPV.booth.constituency.constituencyId = :constituencyId ");
				queryString.append(" and UVD.casteState is not null and UVD.casteState.casteStateId in(:attributeIds) ");
				queryString.append(" and BPV.booth.partNo in (:locationIds) group by UVD.casteState.casteStateId, BPV.booth.partNo ");
			    Query query = getSession().createQuery(queryString.toString());
			
				query.setParameterList("locationIds",partNos);
			    query.setParameterList("attributeIds", attributeIds);	
			    query.setParameter("publicationId", publicationId);
			    query.setParameter("userId", userId);
			    query.setParameter("constituencyId", constituencyId);
			
			return query.list();
		}*/
	  
	  public List<Object[]> getUserCategValuesForSelectedMuncipalWards(Long userId,Long constituencyId,String type,List<Long>  ids,Long publicationId)
	  {
		  Query query = getSession().createQuery("select distinct(UVD.casteState.casteStateId) ," +
		  		"UVD.casteState.caste.casteName from  UserVoterDetails UVD ,BoothPublicationVoter BPV  " +
		  		"where UVD.voter.voterId = BPV.voter.voterId and UVD.user.userId =:userId " +
		  		"and BPV.booth.localBodyWard.constituencyId in (:ids) " +
			  		" and BPV.booth.publicationDate.publicationDateId = :publicationId    ");
		  query.setParameter("userId", userId);
		  query.setParameter("publicationId", publicationId);
		  //query.setParameter("constituencyId", constituencyId);
		  query.setParameterList("ids", ids);
		  return query.list();
	  }
	  
	  public List<Object[]> getVotersCountByGenderInHamlet(Long userId ,Long hametId ,Long  publicationDateId,Long constituencyId)
		{
			Query query = getSession().createQuery("select count(model.voter.voterId),model.voter.gender " +
					" from BoothPublicationVoter model1 ,UserVoterDetails model " +
					"where model1.booth.publicationDate.publicationDateId = :publicationDateId " +
					" and model.voter.voterId = model1.voter.voterId and" +
					" model.user.userId = :userId and  model.hamlet.hamletId = :id "+
					" and model1.booth.constituency.constituencyId = :constituencyId group by model.voter.gender");
			
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("id", hametId);
			
			return query.list();
			
			
		}
	  /**
	   * This DAO is Used For getting The Cadre Count For Selected Level.
	   * @param List<Long> boothIds
	   * @param long constituencyId
	   * @param Long userId
	   * @return List<Long>
	   */
	  public List<Long> getCadreCountForSelectedLevel(List<Long> boothIds ,long constituencyId,Long userId)
	  {
		  StringBuffer queryString = new StringBuffer();
		  queryString.append("select distinct count(model.cadreId) from Cadre model ,BoothPublicationVoter BPV where" +
		  		" model.voter.voterId = BPV.voter.voterId and model.user.userId = :userId" +
		  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds)");		  
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameterList("boothIds", boothIds);
		return query.list();
	  }
	  
	  /**
	   * This DAO is Used For getting The Influencing People Count For Selected Level.
	   * @param List<Long> boothIds
	   * @param long constituencyId
	   * @param Long userId
	   * @return List<Long>
	   */
	  public List<Long> getInfluencingPeopleCountForSelectedLevel(List<Long> boothIds,Long constituencyId,Long userId)
	  {
		  StringBuffer queryString = new StringBuffer();
		  queryString.append("select count(model.influencingPeopleId) from InfluencingPeople model ,BoothPublicationVoter BPV where" +
		  		" model.voter.voterId = BPV.voter.voterId and model.user.userId = :userId" +
		  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds)");		  
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameterList("boothIds", boothIds);
		return query.list();
	  }
	  /**
	   * This DAO is Used For getting The Politicains Count For Selected Level.
	   * @param List<Long> boothIds
	   * @param long constituencyId
	   * @return List<Long>
	   */
	  public List<Long> getPoliticianCountForSelectedLevel(List<Long> boothIds , long constituencyId)
	  {
		  StringBuffer queryString = new StringBuffer();
		  queryString.append("select count(model.candidateId) from Candidate model ,BoothPublicationVoter BPV where" +
		  		" model.voter.voterId = BPV.voter.voterId " +
		  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds)");		  
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("boothIds", boothIds);
		return query.list();
	  }
	  /**
	   * This DAO is Used For getting The Cadre People Details For  Selected Level.
	   * @param List<Long> boothIds
	   * @param long constituencyId
	   * @param Long userId
	   * @param Integer startIndex
	   * @param Integer maxIndex
	   * @param String order
	   * @param String columnName
	   * @return List<Long>
	   */
	  public List<Cadre> getCadreDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId , Long userId,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
		  StringBuffer queryString = new StringBuffer();
		  queryString.append("select model from Cadre model ,BoothPublicationVoter BPV where" +
			  		" model.voter.voterId = BPV.voter.voterId and model.user.userId = :userId" +
			  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds) ");
		  if(columnName.equalsIgnoreCase("voterId"))
		  {
			  queryString.append(" order by model.voter."+columnName+" "+order);
		  }
		  else if(columnName.equalsIgnoreCase("gender"))
		  {
			  columnName = "gender";
			  queryString.append(" order by model.voter."+columnName+" "+order);
		  }
		  else if(columnName.equalsIgnoreCase("houseNo"))
		  {
			  columnName = "houseNo";
			  queryString.append(" order by model.voter."+columnName+" "+order);
		  }
		  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		  {
			  columnName = "relativeName";
			  queryString.append(" order by model.voter."+columnName+" "+order);
		  }
		  else
		  {
			  columnName = "name";
			  
		  }
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		query.setParameter("userId", userId);
		query.setParameterList("boothIds", boothIds);
		return query.list(); 
	  }
	  /**
	   * This DAO is Used For getting The Influencing People Details For  Selected Level.
	   * @param List<Long> boothIds
	   * @param long constituencyId
	   * @param Long userId
	   * @param Integer startIndex
	   * @param Integer maxIndex
	   * @param String order
	   * @param String columnName
	   * @return List<Long>
	   */
	  public List<InfluencingPeople> getInfluencingPeopleDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId , Long userId,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
		  StringBuffer queryString = new StringBuffer();
		  queryString.append("select model from InfluencingPeople model ,BoothPublicationVoter BPV where" +
			  		" model.voter.voterId = BPV.voter.voterId and model.user.userId = :userId" +
			  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds) ");
		  if(columnName.equalsIgnoreCase("voterId"))
		  {
			  queryString.append(" order by model.voter."+columnName+" "+order);  
		  }
		  else if(columnName.equalsIgnoreCase("gender"))
		  {
			  columnName = "gender";
			  queryString.append(" order by model.voter."+columnName+" "+order);
		  }
		  else if(columnName.equalsIgnoreCase("houseNo"))
		  {
			  columnName = "houseNo";
			  queryString.append(" order by model.voter."+columnName+" "+order);
		  }
		  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		  {
			  columnName = "relativeName";
			  queryString.append(" order by model.voter."+columnName+" "+order);
		  }
		  else
		  {
			  columnName = "name";
			  queryString.append("order by model.voter."+columnName+" "+order);
		  }
		Query query = getSession().createQuery(queryString.toString());
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameterList("boothIds", boothIds);
		return query.list(); 
	  }
	  /**
	   * This DAO is Used For getting The Politicans Details For  Selected Level.
	   * @param List<Long> boothIds
	   * @param long constituencyId
	   * @param Integer startIndex
	   * @param Integer maxIndex
	   * @param String order
	   * @param String columnName
	   * @return List<Long>
	   */
	  public List<Candidate> getPoliticanDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
		  StringBuffer queryString = new StringBuffer();
		  queryString.append("select model from Candidate model ,BoothPublicationVoter BPV where" +
			  		" model.voter.voterId = BPV.voter.voterId " +
			  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds) ");
		  if(columnName.equalsIgnoreCase("voterId"))
		  {
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else if(columnName.equalsIgnoreCase("gender"))
		  {
			  columnName = "gender";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else if(columnName.equalsIgnoreCase("houseNo"))
		  {
			  columnName = "houseNo";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		  {
			  columnName = "relativeName";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else
		  {
			  columnName = "name";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		Query query = getSession().createQuery(queryString.toString());
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("boothIds", boothIds);
		return query.list(); 
	  }
	  
	  public List<Object[]> getAgeWiseCustomVoterDetails(Long constituencyId,Long locationValue,Long publicationDateId,String areaType,Long userId,String age)
	  {
		  StringBuilder stringBuilder = new StringBuilder();
		  
		  stringBuilder.append("select count(distinct CV.voter.voterId), CV.voter.gender, CV.customVoterGroup.customVoterGroupId, CV.customVoterGroup.name from ");
		  stringBuilder.append(" BoothPublicationVoter BPV, UserVoterDetails UVD, CustomVoter CV where BPV.voter.voterId = UVD.voter.voterId and BPV.voter.voterId = CV.voter.voterId and ");
		  stringBuilder.append(" BPV.booth.constituency.constituencyId =:constituencyId and CV.customVoterGroup.user.userId =:userId and BPV.booth.publicationDate.publicationDateId =:publicationDateId and ");
		  
		  if(areaType.equalsIgnoreCase(IConstants.RURAL))
		   stringBuilder.append("  BPV.booth.tehsil.tehsilId=:id and ");
		  else if(areaType.equalsIgnoreCase(IConstants.URBAN))
		   stringBuilder.append(" BPV.booth.localBody.localElectionBodyId=:id and ");
		  
		  if(age.equalsIgnoreCase(IConstants.AGE18to25))
		   stringBuilder.append(" CV.voter.age >=18 and CV.voter.age <= 22 ");
		  else if(age.equalsIgnoreCase(IConstants.AGE23to30))
		   stringBuilder.append(" CV.voter.age >=23 and CV.voter.age <= 30 ");
		  else if(age.equalsIgnoreCase(IConstants.AGE31to45))
		   stringBuilder.append(" CV.voter.age >= 31 and CV.voter.age <= 45 ");
		  else if(age.equalsIgnoreCase(IConstants.AGE46to60))
		   stringBuilder.append(" CV.voter.age >= 46 and CV.voter.age <= 60 ");
		  else if(age.equalsIgnoreCase("60Above"))
		   stringBuilder.append(" CV.voter.age > 60 ");
		  else if(age.equalsIgnoreCase(IConstants.YOUNG_VOTERS))
		   stringBuilder.append(" CV.voter.age between :ageFrom and :ageTo ");
		  
		  stringBuilder.append(" group by CV.customVoterGroup.customVoterGroupId, CV.voter.gender ");
		  
		  Query query = getSession().createQuery(stringBuilder.toString());
		 
		  query.setParameter("constituencyId", constituencyId);
		  query.setParameter("id", locationValue);
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("userId", userId);
		  if(age.equalsIgnoreCase(IConstants.YOUNG_VOTERS))
		  {
			query.setParameter("ageFrom", IConstants.YOUNG_VOTERS_AGE_FROM);
			query.setParameter("ageTo", IConstants.YOUNG_VOTERS_AGE_TO);
		  }
		  
		  return query.list();
	  }
	  public List<Long> getVotersCountForAttribute(Long userId,Long categoryValueId,Long casteId,String gender,String locationType,Long locationId,Long publicationId)
	  
		 {
		  Query query = null;
			StringBuilder str = new StringBuilder();
			str.append("select BPV.voter.voterId from VoterCategoryValue VCV, " +
				" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
		str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
		" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
		" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
			
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" BPV.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" BPV.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
				str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append(" UVD.hamlet.hamletId = :locationId ");
			else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" UVD.ward.constituencyId = :locationId");
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
				str.append(" and BPV.voter.gender = :gender"); 
				
			query = getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			query.setParameter("gender", gender);
			query.setParameter("categoryValueId", categoryValueId);
			query.setParameter("casteId", casteId);
			query.setParameter("publicationId", publicationId);
			query.setParameter("locationId", locationId);
			
			return query.list();	 
		 }
	  public List<Object[]> getVoterDetailsByAttributeIdAndCasteId(Long userId,Long categoryValueId,Long casteId,String gender,Integer startIndex,
				Integer maxRecords, String order, String columnName,String locationType,Long locationId,Long publicationId)
	  {
		Query query = null;
		StringBuilder str = new StringBuilder();
		str.append("select BPV.voter,BPV.booth.partNo,BPV.serialNo from VoterCategoryValue VCV, " +
				" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
		str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
		" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
		" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" BPV.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" BPV.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
			str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			str.append(" UVD.hamlet.hamletId = :locationId ");
		else if(locationType.equalsIgnoreCase("customWard"))
		str.append(" UVD.ward.constituencyId = :locationId");
	
		if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			str.append(" and BPV.voter.gender = :gender "); 
		
		if("initial".equalsIgnoreCase(columnName))
		 {
		str.append(" order by cast(BPV.booth.partNo , int),BPV.serialNo,BPV.voter.houseNo");
		 }
		 else if("partNo".equalsIgnoreCase(columnName))
		 {
			 str.append(" order by cast(BPV.booth.partNo , int) "+order);
		 }
		 else if("serialNo".equalsIgnoreCase(columnName))
		 {
			 str.append(" order by BPV.serialNo  "+order);
		 }
		 else
		str.append(" order by BPV.voter."+columnName+" "+order);
		
		query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("locationId", locationId);
		if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
		query.setParameter("gender", gender);
		query.setParameter("categoryValueId", categoryValueId);
		query.setParameter("casteId", casteId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		return query.list();
	  }
	 
	  public List<Object[]> getTotalVotersCountbyCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId)
	  {
		Query query = null;
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct BPV.voter.voterId),BPV.voter.gender from VoterCategoryValue VCV, " +
				" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
		str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
		" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
		" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" BPV.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" BPV.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
			str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			str.append(" UVD.hamlet.hamletId = :locationId ");
		else if(locationType.equalsIgnoreCase("customWard"))
		str.append(" UVD.ward.constituencyId = :locationId");
		str.append(" group by BPV.voter.gender");
		query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("locationId", locationId);
		query.setParameter("categoryValueId", categoryValueId);
		query.setParameter("casteId", casteId);
		
		return query.list();
		
	  }
	  
	  public List<Long> getInfluencingPeopleCountByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type)
	  {
		StringBuilder str = new StringBuilder();
		Query query = null;
		if(type.equalsIgnoreCase("InfluencePeople"))
		str.append("select count(model.influencingPeopleId) from InfluencingPeople model");
		else if(type.equalsIgnoreCase("Cadre"))
			str.append("select count(model.cadreId) from Cadre model");	
		else if(type.equalsIgnoreCase("Politician"))
		str.append("select count(model.candidateId) from Candidate model");
		str.append(" ,VoterCategoryValue VCV, " +
		" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
		str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
		" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
		" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
		str.append(" model.voter.voterId = VCV.voter.voterId and model.voter.voterId = BPV.voter.voterId and ");
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" BPV.booth.constituency.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" BPV.booth.boothId = :locationId ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
			str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
		else if(locationType.equalsIgnoreCase("hamlet"))
			str.append(" UVD.hamlet.hamletId = :locationId ");
		else if(locationType.equalsIgnoreCase("customWard"))
		str.append(" UVD.ward.constituencyId = :locationId");
		if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
		str.append(" and BPV.voter.gender = :gender "); 
		query = getSession().createQuery(str.toString());
		if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
		query.setParameter("gender", gender);
		query.setParameter("userId", userId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("locationId", locationId);
		query.setParameter("categoryValueId", categoryValueId);
		query.setParameter("casteId", casteId);
		
		return query.list();
		
	  }
	  
	  /*public List<Object[]> getInfluencingPeopleDataByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
	 	 
		  StringBuilder str = new StringBuilder();
			Query query = null;
			if(type.equalsIgnoreCase("InfluencePeople"))
			str.append("select model.voter,VCV.userVoterCategoryValue.userVoterCategory.categoryName,VCV.userVoterCategoryValue.categoryValue,model.influencingScope,model.influencingScopeValue from InfluencingPeople model");
			else if(type.equalsIgnoreCase("Cadre"))
				str.append("select model.voter,VCV.userVoterCategoryValue.userVoterCategory.categoryName,VCV.userVoterCategoryValue.categoryValue from Cadre model");	
			else if(type.equalsIgnoreCase("Politician"))
			str.append("select model.voter,VCV.userVoterCategoryValue.userVoterCategory.categoryName,VCV.userVoterCategoryValue.categoryValue from Candidate model");
			str.append(" ,VoterCategoryValue VCV, " +
			" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
			str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
			" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
			" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
			str.append(" model.voter.voterId = VCV.voter.voterId and model.voter.voterId = BPV.voter.voterId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" BPV.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" BPV.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
				str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append(" UVD.hamlet.hamletId = :locationId ");
			else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" UVD.ward.constituencyId = :locationId");
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			str.append(" and BPV.voter.gender = :gender "); 
			if(columnName.equalsIgnoreCase("voterId"))
		 	  {
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("gender"))
		 	  {
		 		  columnName = "gender";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("houseNo"))
		 	  {
		 		  columnName = "houseNo";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		 	  {
		 		  columnName = "relativeName";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else
		 	  {
		 		  columnName = "name";
		 		  
		 	  }
			query = getSession().createQuery(str.toString());
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			query.setParameter("gender", gender);
			query.setParameter("userId", userId);
			query.setParameter("publicationId", publicationId);
			query.setParameter("locationId", locationId);
			query.setParameter("categoryValueId", categoryValueId);
			query.setParameter("casteId", casteId);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
			return query.list();
	 	  
	 	
	  }
		*/
	  
	  public List<InfluencingPeople> getInfluencingPeopleDataByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
	 	 
		  StringBuilder str = new StringBuilder();
			Query query = null;
			str.append("select model from InfluencingPeople model");
			str.append(" ,VoterCategoryValue VCV, " +
			" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
			str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
			" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
			" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
			str.append(" model.voter.voterId = VCV.voter.voterId and model.voter.voterId = BPV.voter.voterId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" BPV.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" BPV.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
				str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append(" UVD.hamlet.hamletId = :locationId ");
			else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" UVD.ward.constituencyId = :locationId");
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			str.append(" and BPV.voter.gender = :gender "); 
			if(columnName.equalsIgnoreCase("voterId"))
		 	  {
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("gender"))
		 	  {
		 		  columnName = "gender";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("houseNo"))
		 	  {
		 		  columnName = "houseNo";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		 	  {
		 		  columnName = "relativeName";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else
		 	  {
		 		  columnName = "name";
		 		  
		 	  }
			query = getSession().createQuery(str.toString());
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			query.setParameter("gender", gender);
			query.setParameter("userId", userId);
			query.setParameter("publicationId", publicationId);
			query.setParameter("locationId", locationId);
			query.setParameter("categoryValueId", categoryValueId);
			query.setParameter("casteId", casteId);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
			return query.list();
	 	  
	 	
	  }
	  public List<Cadre> getCadreDataByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
	 	 
		  StringBuilder str = new StringBuilder();
			Query query = null;
			str.append("select model from Cadre model");
			str.append(" ,VoterCategoryValue VCV, " +
			" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
			str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
			" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
			" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
			str.append(" model.voter.voterId = VCV.voter.voterId and model.voter.voterId = BPV.voter.voterId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" BPV.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" BPV.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
				str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append(" UVD.hamlet.hamletId = :locationId ");
			else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" UVD.ward.constituencyId = :locationId");
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			str.append(" and BPV.voter.gender = :gender "); 
			if(columnName.equalsIgnoreCase("voterId"))
		 	  {
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("gender"))
		 	  {
		 		  columnName = "gender";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("houseNo"))
		 	  {
		 		  columnName = "houseNo";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		 	  {
		 		  columnName = "relativeName";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else
		 	  {
		 		  columnName = "name";
		 		  
		 	  }
			query = getSession().createQuery(str.toString());
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			query.setParameter("gender", gender);
			query.setParameter("userId", userId);
			query.setParameter("publicationId", publicationId);
			query.setParameter("locationId", locationId);
			query.setParameter("categoryValueId", categoryValueId);
			query.setParameter("casteId", casteId);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
			return query.list();
	 	  
	 	
	  }
	  public List<Candidate> getPoliticianDataByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
	 	 
		  StringBuilder str = new StringBuilder();
			Query query = null;
			str.append("select model from Candidate model");
			str.append(" ,VoterCategoryValue VCV, " +
			" BoothPublicationVoter BPV ,UserVoterDetails UVD ");
			str.append(" where VCV.userVoterCategoryValue.userVoterCategoryValueId = :categoryValueId  and  " +
			" VCV.voter.voterId = BPV.voter.voterId  and  VCV.voter.voterId = UVD.voter.voterId and " +
			" VCV.user.userId = :userId and UVD.user.userId = :userId and UVD.casteState.caste.casteId=:casteId and BPV.booth.publicationDate.publicationDateId=:publicationId and ");
			str.append(" model.voter.voterId = VCV.voter.voterId and model.voter.voterId = BPV.voter.voterId and ");
			if(locationType.equalsIgnoreCase("constituency"))
				str.append(" BPV.booth.constituency.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				str.append(" BPV.booth.tehsil.tehsilId = :locationId and BPV.booth.localBody is null and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("booth"))
				str.append(" BPV.booth.boothId = :locationId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				str.append(" BPV.booth.panchayat.panchayatId = :locationId ");
			else if(locationType.equalsIgnoreCase("localElectionBody") || "Local Election Body".equalsIgnoreCase(locationType))
				str.append(" BPV.booth.localBody.localElectionBodyId = :locationId and BPV.booth.constituency.constituencyId = :constituencyId ");
			else if(locationType.equalsIgnoreCase("ward"))
				str.append(" BPV.booth.localBodyWard.constituencyId = :locationId ");
			else if(locationType.equalsIgnoreCase("hamlet"))
				str.append(" UVD.hamlet.hamletId = :locationId ");
			else if(locationType.equalsIgnoreCase("customWard"))
			str.append(" UVD.ward.constituencyId = :locationId");
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			str.append(" and BPV.voter.gender = :gender "); 
			if(columnName.equalsIgnoreCase("voterId"))
		 	  {
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("gender"))
		 	  {
		 		  columnName = "gender";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("houseNo"))
		 	  {
		 		  columnName = "houseNo";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		 	  {
		 		  columnName = "relativeName";
		 		  str.append(" order by model.voter."+columnName+" "+order);
		 	  }
		 	  else
		 	  {
		 		  columnName = "name";
		 		  
		 	  }
			query = getSession().createQuery(str.toString());
			if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))
			query.setParameter("gender", gender);
			query.setParameter("userId", userId);
			query.setParameter("publicationId", publicationId);
			query.setParameter("locationId", locationId);
			query.setParameter("categoryValueId", categoryValueId);
			query.setParameter("casteId", casteId);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
			return query.list();
	 	  
	 	
	  }
	  
		
		//get voter details By Panchayat hamlet caste_state ID
		public List<Object[]> getVoterDetailsByCasteStateForHamlet(Long hamletId,Long publicationDateId,Long casteStateId,Long userId,Long constituencyId)
		{
				Object[] params={hamletId,publicationDateId,casteStateId,userId,constituencyId};
				return getHibernateTemplate().find("select distinct model.voter,model.booth.boothId,model2.mobileNo from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model2.hamlet.hamletId =? and model.booth.publicationDate.publicationDateId = ? and model2.casteState.casteStateId =? and model2.user.userId = ? and model.booth.constituency.constituencyId=?",params);
				
		}
		
		public List<Object[]> getBoothWiseTotalVoters(Long constituencyId,Long publicationId,String type)
		{
			StringBuilder str = new StringBuilder();
			if(type != null && type.equalsIgnoreCase(IConstants.BOOTH))
			 str.append(" select model.booth.boothId,model.booth.partNo,count(distinct model.voter.voterId),model.booth.panchayat.panchayatName ");
			else if(type!= null && type.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append(" select model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName,count(distinct model.voter.voterId),model.booth.tehsil.tehsilName  ");
			
			str.append(" from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId ");
			
			if(type.equalsIgnoreCase(IConstants.BOOTH))
			 str.append(" and model.booth.localBody is null group by model.booth.boothId order by model.booth.boothId ");
			else if(type!= null && type.equalsIgnoreCase(IConstants.PANCHAYAT))
			  str.append(" group by model.booth.panchayat.panchayatId order by model.booth.tehsil.tehsilName,model.booth.panchayat.panchayatName ");
			
			Query query = getSession().createQuery(str.toString());
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		public List<Object[]> getBoothWiseTotalVotersInALocalEleBody(Long constituencyId,Long publicationId)
		{
			StringBuilder str = new StringBuilder();
			str.append(" select model.booth.boothId,model.booth.partNo,count(distinct model.voter.voterId) from BoothPublicationVoter model ");
			str.append(" where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model.booth.localBody is not null ");
			str.append(" group by model.booth.boothId order by model.booth.partNo,model.booth.localBody.name ");
			Query query = getSession().createQuery(str.toString());
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		public List<Object[]> getWardWiseTotalVotersCount(Long constituencyId,Long publicationDateId,Long localEleBodyId)
		{
			Query query = getSession().createQuery(" select model.booth.localBodyWard.constituencyId,model.booth.localBodyWard.name, count (distinct model.voter.voterId),model.booth.localBody.name " +
					" from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and " +
					" model.booth.localBody.localElectionBodyId =:localElectionBodyId group by model.booth.localBodyWard.constituencyId order by model.booth.localBody.name,model.booth.localBodyWard.name ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("localElectionBodyId", localEleBodyId);
			
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getPanchayatsCountByMandalIdsList(List<Long> mandalIdsList,Long constituencyId,Long publicationId,String type)
		{
			StringBuilder str = new StringBuilder();
			
			str.append(" select model.booth.tehsil.tehsilId, ");
			if(type != null && type.equalsIgnoreCase("panchayatsCount"))
			str.append(" count(distinct model.booth.panchayat.panchayatId) ");
			else
			 str.append(" count(distinct model.booth.boothId) ");
			
			str.append(" from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and " +
					" model.booth.localBody is null and model.booth.tehsil.tehsilId in (:mandalIdsList) group by model.booth.tehsil.tehsilId ");
						
			Query query = getSession().createQuery(str.toString());
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			query.setParameterList("mandalIdsList", mandalIdsList);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsCountByPanchayatIdsList(List<Long> locationValuesList,Long constituencyId,Long publicationId,String type)
		{
			StringBuilder str = new StringBuilder();
			
			if(type != null && type.equalsIgnoreCase("panchayatBooths"))
			 str.append(" select model.booth.panchayat.panchayatId, ");
			
			else if(type != null && type.equalsIgnoreCase("wardBooths"))
			 str.append(" select model.booth.localBodyWard.constituencyId, ");
			
			else if(type != null && type.equalsIgnoreCase("muncipalityBooths"))
			 str.append(" select model.booth.localBody.localElectionBodyId, ");
			
			str.append(" count(distinct model.booth.boothId) ");
			
			str.append(" from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId  ");
			
			if(type != null && type.equalsIgnoreCase("panchayatBooths"))
			 str.append(" and model.booth.panchayat.panchayatId in (:locationValuesList) group by model.booth.panchayat.panchayatId ");
			
			else if(type != null && type.equalsIgnoreCase("wardBooths"))
			 str.append(" and model.booth.localBodyWard.constituencyId in (:locationValuesList) group by model.booth.localBodyWard.constituencyId ");
						
			else if(type != null && type.equalsIgnoreCase("muncipalityBooths"))
			 str.append(" and model.booth.localBody.localElectionBodyId in (:locationValuesList) group by model.booth.localBody.localElectionBodyId ");
			
			Query query = getSession().createQuery(str.toString());
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			query.setParameterList("locationValuesList", locationValuesList);
			return query.list();
		}
		
		
		public List<Object[]> getMuncipalityWardsCount(Long constituencyId,Long publicationDateId,List<Long> muncipalityIdsList)
		{
		  Query query = getSession().createQuery(" select model.booth.localBody.localElectionBodyId, count(distinct model.booth.localBodyWard.constituencyId) from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and " +
		  		" model.booth.publicationDate.publicationDateId =:publicationDateId and model.booth.localBody.localElectionBodyId in(:muncipalityIdsList) group by model.booth.localBody.localElectionBodyId ");
		  
		  query.setParameter("constituencyId", constituencyId);
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameterList("muncipalityIdsList", muncipalityIdsList);
		  return query.list();
		}
		
		public List<Object[]> getVotersCountAgeWise(Long fromAge,Long toAge,List<Long> boothIds){
			String qyeryString = "select count(*),model.voter.gender,model.booth.boothId from BoothPublicationVoter " +
					"model where model.booth.boothId in(:boothIds) " +
					"and model.voter.age >= :fromAge and model.voter.age<= :toAge group by model.booth.boothId,model.voter.gender";
			
			Query query = getSession().createQuery(qyeryString);
			
			
			query.setParameterList("boothIds", boothIds);
			query.setParameter("fromAge", fromAge);
			query.setParameter("toAge", toAge);
			
			return query.list();
		}
		public List<Object[]> getVotersCountAgeWiseInBooth(Long fromAge,Long toAge,Long boothIds){
			String qyeryString = "select count(*),model.voter.gender from BoothPublicationVoter " +
					"model where model.booth.boothId =:boothIds " +
					"and model.voter.age >= :fromAge and model.voter.age<= :toAge group by model.voter.gender";
			
			Query query = getSession().createQuery(qyeryString);
			
			
			query.setParameter("boothIds", boothIds);
			query.setParameter("fromAge", fromAge);
			query.setParameter("toAge", toAge);
			
			return query.list();
		}
		
		public List<Object[]> getVotersCountAgeWiseForPanchayat(Long fromAge,Long toAge,List<Long> boothIds){
			String qyeryString = "select count(*),model.voter.gender from BoothPublicationVoter " +
					"model where model.booth.boothId in(:boothIds) " +
					"and model.voter.age >= :fromAge and model.voter.age<= :toAge group by model.voter.gender";
			
			Query query = getSession().createQuery(qyeryString);
			
			
			query.setParameterList("boothIds", boothIds);
			query.setParameter("fromAge", fromAge);
			query.setParameter("toAge", toAge);
			
			return query.list();
		}
		
		public List<Object[]> getVotersCasteDetailsForAgeRange(Long fromAge,Long toAge,List<Long> boothIds,Long userId){
			String qyeryString = "select count(*),uvd.casteState.caste.casteName,uvd.casteState.casteStateId from BoothPublicationVoter " +
					" model,UserVoterDetails uvd where model.booth.boothId in(:boothIds) " +
					"and model.voter.age >= :fromAge and model.voter.age<= :toAge and model.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId group by uvd.casteState.casteStateId ";
			
			Query query = getSession().createQuery(qyeryString);
			
			
			query.setParameterList("boothIds", boothIds);
			query.setParameter("fromAge", fromAge);
			query.setParameter("toAge", toAge);
			query.setParameter("userId", userId);
			
			return query.list();
		}
		
		public List<Object[]> getVotersCasteDetailsForAgeRangeForSelectedCastes(Long fromAge,Long toAge,List<Long> boothIds,Long userId,List<Long> casteIds){
			String qyeryString = "select count(*),uvd.casteState.caste.casteName,uvd.casteState.casteStateId from BoothPublicationVoter " +
					" model,UserVoterDetails uvd where model.booth.boothId in(:boothIds) " +
					"and model.voter.age >= :fromAge and model.voter.age<= :toAge and model.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
					" and uvd.casteState.casteStateId in (:casteIds) group by uvd.casteState.casteStateId ";
			
			Query query = getSession().createQuery(qyeryString);
			
			query.setParameterList("casteIds", casteIds);
			query.setParameterList("boothIds", boothIds);
			query.setParameter("fromAge", fromAge);
			query.setParameter("toAge", toAge);
			query.setParameter("userId", userId);
			
			return query.list();
		}
		
		
		public List<Object[]> getVotersCasteDetailsForAgeRangeInBooth(Long fromAge,Long toAge,Long boothId,Long userId){
			String qyeryString = "select count(*),uvd.casteState.caste.casteName,uvd.casteState.casteStateId from BoothPublicationVoter " +
					" model,UserVoterDetails uvd where model.booth.boothId =:boothIds  " +
					"and model.voter.age >= :fromAge and model.voter.age<= :toAge and model.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId group by uvd.casteState.casteStateId ";
			
			Query query = getSession().createQuery(qyeryString);
			
			
			query.setParameter("boothIds", boothId);
			query.setParameter("fromAge", fromAge);
			query.setParameter("toAge", toAge);
			query.setParameter("userId", userId);
			
			return query.list();
		}
		public List<Object[]> getVotersCasteDetailsForAgeRangeInBoothForSelectedCastes(Long fromAge,Long toAge,Long boothId,Long userId,List<Long> casteIds){
			String qyeryString = "select count(*),uvd.casteState.caste.casteName,uvd.casteState.casteStateId from BoothPublicationVoter " +
					" model,UserVoterDetails uvd where model.booth.boothId =:boothIds  " +
					"and model.voter.age >= :fromAge and model.voter.age<= :toAge and model.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
					" and uvd.casteState.casteStateId in(:casteIds) group by uvd.casteState.casteStateId";
			
			Query query = getSession().createQuery(qyeryString);
			
			query.setParameterList("casteIds", casteIds);
			query.setParameter("boothIds", boothId);
			query.setParameter("fromAge", fromAge);
			query.setParameter("toAge", toAge);
			query.setParameter("userId", userId);
			
			return query.list();
		}
		
		public Long getTotalVoters(Long boothId)
		{
			 Query query = getSession().createQuery("select count(*) from BoothPublicationVoter model " +
						" where model.booth.boothId = :boothId");
			 query.setParameter("boothId", boothId);
			 return (Long) query.uniqueResult();
		}
		
		public List<Object[]> getTotalVotersByBooths(List<Long> boothIds)
		{
			 Query query = getSession().createQuery("select model.booth.boothId,count(*) from BoothPublicationVoter model " +
						" where model.booth.boothId in (:boothIds) group by model.booth.boothId");
			 query.setParameterList("boothIds", boothIds);
			 return query.list();
		}
		
		
		public List<Long> findFamiliesCountByPublicationIdInAHamlet(Long locationValue,Long publicationDateId,Long userId)
		{
			Query query = getSession().createQuery("select count(distinct model.voter.houseNo) from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and model.user.userId = :userId" +
					" and model2.booth.publicationDate.publicationDateId = :publicationDateId and model.hamlet.hamletId = :locationValue group by model.hamlet.hamletId");
			query.setParameter("locationValue", locationValue);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("userId", userId);
			return query.list();
			
		}
		public List<Long> getAllImpFamilesCountForHamlet(Long locationValue,Long publicationDateId,Long userId)
		{
			Query query = getSession().createQuery("select count(model.voter.houseNo) from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and model.user.userId = :userId" +
					" and model2.booth.publicationDate.publicationDateId = :publicationDateId and model.hamlet.hamletId = :locationValue group by model.hamlet.hamletId,model.voter.houseNo");
			query.setParameter("locationValue", locationValue);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("userId", userId);
			
			return query.list();
			
		}
		public List<Long> getAllImpFamilesCountForPartialPanchayat(Long panchayatId,Long publicationDateId,Long userId,Long constituencyId){
		
			Query query = getSession().createQuery("select count(uvd.voter.houseNo) from BoothPublicationVoter bpv," +
				"UserVoterDetails uvd,PanchayatHamlet ph where ph.panchayat.panchayatId = :panchayatId and " +
				" ph.hamlet.hamletId = uvd.hamlet.hamletId and uvd.voter.voterId = bpv.voter.voterId  and uvd.user.userId = :userId " +
				" and bpv.booth.publicationDate.publicationDateId = :publicationDateId and bpv.booth.constituency.constituencyId = :constituencyId  group by uvd.voter.houseNo");
		
		   query.setParameter("panchayatId", panchayatId);
		   query.setParameter("publicationDateId", publicationDateId);
		   query.setParameter("userId", userId);
		   query.setParameter("constituencyId", constituencyId);

		 return query.list();
		}
		
		public Long getVotersCountInAAgeRangeByHamlet(Long hamletId,Long publicationDateId,Long userId,Long ageFrom, Long ageTo,String gender)
		{
			StringBuilder str = new StringBuilder();
			str.append("select count(distinct model.voter.voterId) from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and model.user.userId = :userId" +
					" and model2.booth.publicationDate.publicationDateId = :publicationDateId and model.hamlet.hamletId = :hamletId");
			if(ageTo != null)
				str.append(" and model.voter.age between :ageFrom and :ageTo ");
			else
				str.append(" and model.voter.age > :ageFrom ");
			
			if(gender != null)
				str.append(" and model.voter.gender = :gender ");
			Query query = getSession().createQuery(str.toString());
			query.setParameter("hamletId", hamletId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("userId", userId);
			
			query.setParameter("ageFrom",ageFrom);
			if(ageTo != null)
			query.setParameter("ageTo",ageTo);
			if(gender != null)
			query.setParameter("gender",gender);
			return (Long) query.uniqueResult();	
		}
		
		
		public List<Object[]> getVoterDetailsForMessageCenter(Long constituencyId,Long publicationDateId,Long locationValue,String queryStr,Integer startIndex, Integer maxIndex,Long userId)
		{
			StringBuilder str = new StringBuilder();
			str.append(" select model.voter,model2.mobileNo,model.booth.boothId,model.booth.partNo from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId ");
			str.append(" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId and model2.mobileNo is not null ");
			str.append( queryStr);
			str.append(" order by model.voter.name ");
			
			Query query = getSession().createQuery(str.toString());
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("locationValue", locationValue);
			query.setParameter("userId", userId);
			
			if(startIndex != null)
			 query.setFirstResult(startIndex);
			if(maxIndex != null)
			 query.setMaxResults(maxIndex);
			
			return query.list();
		}
		
		public List<Object[]> getLocationNameForVoter(List<Long> voterIdsList,String locationType)
		{
			StringBuilder str = new StringBuilder();
			str.append(" select model2.voter.voterId, ");
			if(locationType != null && locationType.equalsIgnoreCase("mandal"))
			 str.append(" model.booth.tehsil.tehsilName ");
			else if(locationType != null && locationType.equalsIgnoreCase("muncipality"))
			 str.append(" model.booth.localBody.name ");
			else if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
			 str.append(" model.booth.panchayat.panchayatName ");
			else if(locationType != null && locationType.equalsIgnoreCase("booth"))
			 str.append(" model.booth.partNo ");
			//else if(locationType != null && locationType.equalsIgnoreCase("booth"))
			 
			
			Query query = getSession().createQuery(str.toString());
			return query.list();
		}
		
		public List<Object[]> getRecordsFromBoothPublicationVoter(Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery(" select model.boothPublicationVoterId, model.booth.boothId, model.voter.voterId, model.serialNo from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("publicationDateId",publicationDateId);
			query.setParameter("constituencyId",constituencyId);
			return query.list();
		}
		
		/*public List<Object[]> getVoterDetailsOfAConstituency(Long constituencyId, Long publicationDateId, Long userId)
		{
			Query query = getSession().createQuery("select distinct model.voter.voterId, model.voter.houseNo, model.voter.name,model.voter.relationshipType,model.voter.relativeName,model.voter.gender,model.voter.age,model.voter.voterIDCardNo,model2.mobileNo  from BoothPublicationVoter model, UserVoterDetails model2 where " +
					" model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId = :constituencyId and model.booth.publicationDate.publicationDateId = :publicationDateId and model2.user.userId = :userId ");
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			query.setParameter("userId",userId);
			return query.list();
		}*/
		
		public List<Object[]> getVoterDetailsOfAConstituency(Long constituencyId, Long publicationDateId, Long userId)
		{
			Query query = getSession().createSQLQuery("SELECT DISTINCT V.voter_id,V.house_no,V.name,V.relationship_type,V.relative_name,V.gender,V.age,V.voter_id_card_no,UVD.mobile_no from booth B,booth_publication_voter BPV,voter V " +
					" LEFT OUTER JOIN user_voter_details UVD ON V.voter_id = UVD.voter_id AND UVD.user_id = :userId WHERE V.voter_id = BPV.voter_id AND B.booth_id = BPV.booth_id AND B.constituency_id = :constituencyId AND " +
					" B.publication_date_id = :publicationDateId ORDER BY B.booth_id,BPV.serial_no ");
			
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			query.setParameter("userId",userId);
			return query.list();
		}
		
		public List<Object[]> getVoterDetailsOfAConstituencyAndPublication(Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery("select distinct model.voter.voterId, model.voter.houseNo, model.voter.name,model.voter.relationshipType,model.voter.relativeName,model.voter.gender,model.voter.age," +
					" model.voter.voterIDCardNo from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return query.list();
		}
		
		public List<Object[]> getVoterAgeDetailsForSelectedLocation(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,String locationType)
		{
			StringBuilder str = new StringBuilder();
			str.append(" select count(distinct model.voter.voterId),model.voter.gender,model.voter.voterAgeRange.voterAgeRangeId");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(",model.booth.constituency.constituencyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
			 str.append(",model.booth.tehsil.tehsilId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append(",model.booth.panchayat.panchayatId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			 str.append(",model.booth.boothId  ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			 str.append(",model.booth.localBody.localElectionBodyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			 str.append(",model.booth.localBodyWard.constituencyId ");
			
			str.append(" from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId ");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(" and model.booth.constituency.constituencyId in(:locationIdsList) group by model.booth.constituency.constituencyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
			 str.append(" and model.booth.tehsil.tehsilId in (:locationIdsList) group by model.booth.tehsil.tehsilId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append(" and model.booth.panchayat.panchayatId in(:locationIdsList) and model.booth.localBody is null group by model.booth.panchayat.panchayatId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			 str.append(" and model.booth.boothId in(:locationIdsList) group by model.booth.boothId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			 str.append(" and model.booth.localBody.localElectionBodyId in(:locationIdsList) group by model.booth.localBody.localElectionBodyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			 str.append(" and model.booth.localBodyWard.constituencyId in (:locationIdsList) group by model.booth.localBodyWard.constituencyId ");
			
			str.append(",model.voter.voterAgeRange.voterAgeRangeId,model.voter.gender ");
			Query query = getSession().createQuery(str.toString());
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("locationIdsList", locationIdsList);
			
			return query.list();
		}
		
		public List<Object[]> getAgeAndGenderWiseVotersCountInPanchayatOfConstituency(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,String type,Long userId){
			StringBuilder str=new StringBuilder();
			if(type.equalsIgnoreCase("rural")){
				str.append("select model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName,");
			}else{
				str.append("select model.booth.boothId,model.booth.partNo,");
			}
			
			str.append(" model1.casteState.caste.casteName,model1.casteState.casteStateId,count(model.voter.voterId) from" +
					" BoothPublicationVoter model,UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId" +
					" and model.booth.constituency.constituencyId =:constituencyId" +
					" and model.booth.publicationDate.publicationDateId =:publicationDateId and model1.user.userId=:userId");
			
			if(ageTo != null){
				str.append(" and model.voter.age between :ageFrom and :ageTo");
				}
			else{
				str.append(" and model.voter.age > :ageFrom");
			}
			
			str.append(" group by model1.casteState.casteStateId,");
			
			if(type.equalsIgnoreCase("rural")){
				str.append(" model.booth.panchayat.panchayatId");
			}else{
				str.append(" model.booth.boothId");
			}
			
			Query qry=getSession().createQuery(str.toString());
			qry.setParameter("constituencyId", constituencyId);
			qry.setParameter("publicationDateId", publicationDateId);
			qry.setParameter("userId",userId);
			qry.setParameter("ageFrom",ageFrom);
			
			if(ageTo != null){
				qry.setParameter("ageTo",ageTo);
			}
			
			return qry.list();
		}
		public List<Object[]> getTotalVotersInPanchayatOfConstituency(Long constituencyId,Long publicationDateId,String type){
			StringBuilder str=new StringBuilder();
			if(type.equalsIgnoreCase("rural")){
				str.append("select model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName,");
			}else{
				str.append("select model.booth.boothId,model.booth.partNo,");
			}
			str.append(" count(model.voter.voterId) from" +
					" BoothPublicationVoter model" +
					" where model.booth.constituency.constituencyId =:constituencyId" +
					" and model.booth.publicationDate.publicationDateId =:publicationDateId");
			
			if(type.equalsIgnoreCase("rural")){
				str.append(" group by model.booth.panchayat.panchayatId ");
			}else{
				str.append(" group by model.booth.boothId");
			}
			
			Query qry=getSession().createQuery(str.toString());
			qry.setParameter("constituencyId", constituencyId);
			qry.setParameter("publicationDateId", publicationDateId);
						
			return qry.list();
		}
		
		public List<Object[]> getTotalVotersInPanchayatOfConstituencyByAge(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,String type){
			StringBuilder str=new StringBuilder();
			if(type.equalsIgnoreCase("rural")){
				str.append("select model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName," );
			}else{
				str.append("select model.booth.boothId,model.booth.partNo,");
			}
			
			str.append(" count(model.voter.voterId),model.voter.gender from" +
					" BoothPublicationVoter model" +
					" where model.booth.constituency.constituencyId =:constituencyId" +
					" and model.booth.publicationDate.publicationDateId =:publicationDateId");
			
			if(ageTo != null){
				str.append(" and model.voter.age between :ageFrom and :ageTo");
				}
			else{
				str.append(" and model.voter.age > :ageFrom");
			}
			
			str.append(" group by model.voter.gender, ");
			
			if(type.equalsIgnoreCase("rural")){
				str.append(" model.booth.panchayat.panchayatId");
			}else{
				str.append(" model.booth.boothId");
			}
			
			Query qry=getSession().createQuery(str.toString());
			qry.setParameter("constituencyId", constituencyId);
			qry.setParameter("publicationDateId", publicationDateId);
			qry.setParameter("ageFrom",ageFrom);
			
			if(ageTo != null){
				qry.setParameter("ageTo",ageTo);
			}
						
			return qry.list();
		}
		
		public List<Object[]> getTotalVotersInBoothOfMuncipalityByConstituencyId(Long constituencyId,Long publicationDateId){
			StringBuilder str=new StringBuilder();
			str.append("select model.booth.boothId,model.booth.partNo," +
					" count(model.voter.voterId),model.booth.localBody.localElectionBodyId,model.booth.localBody.name," +
					" model.booth.localBody.electionType.electionType from" +
					" BoothPublicationVoter model" +
					" where model.booth.constituency.constituencyId =:constituencyId" +
					" and model.booth.publicationDate.publicationDateId =:publicationDateId and booth.localBody!=null");
			
			str.append(" group by model.booth.boothId,model.booth.localBody.localElectionBodyId");
			
			Query qry=getSession().createQuery(str.toString());
			qry.setParameter("constituencyId", constituencyId);
			qry.setParameter("publicationDateId", publicationDateId);
						
			return qry.list();
		}
		
		public List<Object[]> getAgeAndGenderWiseVotersCountInBoothsOfMuncipalityOfConstituency(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId){
			StringBuilder str=new StringBuilder();
			str.append("select model.booth.boothId,model.booth.partNo," +
					" model1.casteState.caste.casteName,model1.casteState.casteStateId,count(model.voter.voterId),model.booth.localBody.localElectionBodyId,model.booth.localBody.name," +
					" model.booth.localBody.electionType.electionType from" +
					" BoothPublicationVoter model,UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId" +
					" and model.booth.constituency.constituencyId =:constituencyId" +
					" and model.booth.publicationDate.publicationDateId =:publicationDateId and model.booth.localBody!=null" +
					" and model1.user.userId=:userId ");
			
			if(ageTo != null){
				str.append(" and model.voter.age between :ageFrom and :ageTo");
				}
			else{
				str.append(" and model.voter.age > :ageFrom");
			}
			
			str.append(" group by model.booth.boothId,model.booth.localBody.localElectionBodyId, model1.casteState.casteStateId");
			
			Query qry=getSession().createQuery(str.toString());
			qry.setParameter("constituencyId", constituencyId);
			qry.setParameter("publicationDateId", publicationDateId);
			qry.setParameter("userId", userId);
			qry.setParameter("ageFrom",ageFrom);
			
			if(ageTo != null){
				qry.setParameter("ageTo",ageTo);
			}
			
			return qry.list();
		}
		
		public List<Object[]> getTotalVotersInBoothOfMuncipalityOfConstituencyByAge(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo){
			StringBuilder str=new StringBuilder();
			str.append("select model.booth.boothId,model.booth.partNo," +
					" count(model.voter.voterId),model.voter.gender,model.booth.localBody.localElectionBodyId,model.booth.localBody.name," +
					" model.booth.localBody.electionType.electionType " +
					" from" +
					" BoothPublicationVoter model" +
					" where model.booth.constituency.constituencyId =:constituencyId" +
					" and model.booth.publicationDate.publicationDateId =:publicationDateId and booth.localBody!=null");
			
			if(ageTo != null){
				str.append(" and model.voter.age between :ageFrom and :ageTo");
				}
			else{
				str.append(" and model.voter.age > :ageFrom");
			}
			
			str.append(" group by model.booth.boothId,model.booth.localBody.localElectionBodyId,model.voter.gender ");
			
			Query qry=getSession().createQuery(str.toString());
			qry.setParameter("constituencyId", constituencyId);
			qry.setParameter("publicationDateId", publicationDateId);
			qry.setParameter("ageFrom",ageFrom);
			
			if(ageTo != null){
				qry.setParameter("ageTo",ageTo);
			}
						
			return qry.list();
		}
		
		 
		 public List<Object[]> getVotersDetailsAndCountDetailsByBoothId(Long userId , Long boothId, Integer startIndex,
					Integer maxRecords, String order, String columnName,String queryString,String queryForCategories,String queryForselect,boolean isCount) {  
			 Query query = null;
			 
			 
			 StringBuffer strBuffer = new StringBuffer();
			 
			 if(!isCount)
			 {
			 
				 strBuffer.append("SELECT * FROM (select " +
				 		"v.voter_id," +
				 		"v.voter_id_card_no," +
				 		"v.name," +
				 		"v.house_no," +
				 		"v.relative_name," +
				 		"v.gender," +
				 		"v.age," +
				 		"b.part_no," +
				 		"b.location," +
				 		"c.caste_name," +
				 		"pa.short_name," +
	                    "bpv.serial_no ,"+
				 		"uvd.mobile_no " );
				 
				 if(queryForselect.length() >0)
					 strBuffer.append(queryForselect);
			 }else
				 strBuffer.append("SELECT * FROM (" +
					 		"select count(v.voter_id) ");
				 
			 		/*"uvcu.user_voter_category_id ," +
			 		"uvcu.category_value," +
			 		"uvcu1.user_voter_category_id ," +
			 		"uvcu1.category_value " +*/
			 		strBuffer.append("from " +
			 		"booth b," +
			 		"booth_publication_voter bpv," +
			 		"voter v " +
			 		"LEFT JOIN (user_voter_details uvd JOIN caste_state cs on uvd.caste_state_id = cs.caste_state_id JOIN caste c on cs.caste_id = c.caste_id ) on v.voter_id = uvd.voter_id and uvd.user_id = :userId " +
			 		"LEFT JOIN (user_voter_details uvd1 join party pa on pa.party_id = uvd1.party_id )on v.voter_id = uvd1.voter_id and uvd1.user_id = :userId ");
			 		/*"LEFT JOIN (voter_category_value vcu JOIN user_voter_category_value uvcu on uvcu.user_voter_category_value_id = vcu.user_voter_category_value_id and uvcu.user_voter_category_id = 5)on v.voter_id = vcu.voter_id " +
			 		"LEFT JOIN (voter_category_value vcu1 JOIN user_voter_category_value uvcu1 on uvcu1.user_voter_category_value_id = vcu1.user_voter_category_value_id and uvcu1.user_voter_category_id = 8)on v.voter_id = vcu1.voter_id " +
			 	*/
			 		
					if(queryForCategories.length() >0)
						strBuffer.append(queryForCategories);
			 		
			 		strBuffer.append("where " +
			 		"v.voter_id = bpv.voter_id and " +
			 		"bpv.booth_id = b.booth_id and " +
			 		"b.booth_id = :boothId  " );
			 		
			 		if(queryString.length() >0)
			 		strBuffer.append(queryString);
			 		
			 		if("initial".equalsIgnoreCase(columnName))
					 {
			 			strBuffer.append(" order by b.part_no ,bpv.serial_no  ,v.house_no ");
					 }
					 else if("partNo".equalsIgnoreCase(columnName))
					 {
						 strBuffer.append(" order by b.part_no "+order);
					 }
					 else if("serialNo".equalsIgnoreCase(columnName))
					 {
						 strBuffer.append(" order by bpv.serial_no "+order);
					 }
					 else if("gender".equalsIgnoreCase(columnName))
					 {
						 strBuffer.append(" order by v.gender "+order);
					 }
					 else if("age".equalsIgnoreCase(columnName))
					 {
						 strBuffer.append(" order by v.age "+order);
					 }
					 else if("houseNo".equalsIgnoreCase(columnName))
					 {
						 strBuffer.append(" order by v.house_no "+order);
					 }
					 else if("voterId".equalsIgnoreCase(columnName))
					 {
						 strBuffer.append(" order by v.voter_id_card_no "+order);
					 }
					 else if("name".equalsIgnoreCase(columnName))
					 {
						 strBuffer.append(" order by v.name "+order);
					 }
			 	
			 		strBuffer.append(") AS subselection");
			 		
			 
			 
			 query = getSession().createSQLQuery(strBuffer.toString());

			 query.setParameter("boothId", boothId);
			 query.setParameter("userId", userId);
			 
			 if(!isCount)
			 {
			  query.setFirstResult(startIndex);
			  query.setMaxResults(maxRecords);
			 }

			 return query.list();
			 
		 }
		 
		  public List<Object[]> getVotersDetailsAnCountDetailsForPanchayatByPublicationId(
					Long userId , Long panchayatId, Long publicationDateId, Integer startIndex,
					Integer maxRecords, String order, String columnName,String queryString,String queryForCategories,String queryForselect,boolean isCount,List<Long> boothIds) {
			 
			 StringBuffer str = new StringBuffer();		 
			 
			 if(!isCount)
			 {
			 str.append("SELECT * FROM (" +
				 		"select v.voter_id ,v.voter_id_card_no,v.name,v.house_no,v.relative_name," +
				 		"v.gender,v.age,b.part_no,p.panchayat_name,c.caste_name,pa.short_name,bpv.serial_no ," +
				 		"uvd.mobile_no ");
				 		
				 		if(queryForselect.length() >0)
				 			str.append(queryForselect);
			 }else
			 {
				 str.append("SELECT * FROM (" +
					 		"select count(v.voter_id) ");
			 }
				 		
				 		str.append("from" +
				 		" booth b," +
				 		"panchayat p," +
				 		"booth_publication_voter bpv," +
				 		"voter v LEFT JOIN (user_voter_details uvd JOIN caste_state cs on uvd.caste_state_id = cs.caste_state_id JOIN caste c on cs.caste_id = c.caste_id ) on v.voter_id = uvd.voter_id and uvd.user_id = :userId " +
				 		"LEFT JOIN (user_voter_details uvd1 join party pa on pa.party_id = uvd1.party_id )on v.voter_id = uvd1.voter_id and uvd1.user_id = :userId " );
				 		/*"LEFT JOIN (voter_category_value vcu JOIN user_voter_category_value uvcu on uvcu.user_voter_category_value_id = vcu.user_voter_category_value_id and uvcu.user_voter_category_id = 5)on v.voter_id = vcu.voter_id " +
				 		"LEFT JOIN (voter_category_value vcu1 JOIN user_voter_category_value uvcu1 on uvcu1.user_voter_category_value_id = vcu1.user_voter_category_value_id and uvcu1.user_voter_category_id = 8  )on v.voter_id = vcu1.voter_id " +*/
				 		
				 		if(queryForCategories.length() >0)
				 			str.append(queryForCategories);
				 		
				 		str.append("where " +
				 		"v.voter_id = bpv.voter_id and " +
				 		"bpv.booth_id = b.booth_id and " +
				 		"b.panchayat_id = p.panchayat_id and " +
				 		//"b.constituency_id = 232 and " +
				 		"b.publication_date_id = :publicationDateId and " +
				 		//"b.panchayat_id in(2) ");
					    "b.panchayat_id =:panchayatId and " +
					    "b.booth_id in(:boothIds)");
			 
			 if(queryString.length() >0)
				 str.append(queryString);
			 
			 if("initial".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by b.part_no ,bpv.serial_no  ,v.house_no ");
			 }
			 else if("partNo".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by b.part_no "+order);
			 }
			 else if("serialNo".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by bpv.serial_no "+order);
			 }
			 else if("gender".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by v.gender "+order);
			 }
			 else if("age".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by v.age "+order);
			 }
			 else if("houseNo".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by v.house_no "+order);
			 }
			 else if("voterId".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by v.voter_id_card_no "+order);
			 }
			 else if("name".equalsIgnoreCase(columnName))
			 {
				 str.append(" order by v.name "+order);
			 }
			 
			 str.append(") AS subselection"); 
			 
			 Query query =  getSession().createSQLQuery(str.toString());

		 	query.setParameter("publicationDateId", publicationDateId);
		 	query.setParameter("panchayatId", panchayatId);
		 	query.setParameter("userId", userId);
		 	query.setParameterList("boothIds", boothIds);
		 	
		 	if(!isCount){
		 	 query.setFirstResult(startIndex);
		 	 query.setMaxResults(maxRecords);
		 	}
		 	
		 	return query.list();
	}
		  
		  public List getVotersDetailsAnCountDetailsForHamletByPublicationId(Long userId , Long hamletId, Long publicationdateId, int startIndex,
					int maxIndex, String order, String columnName, String queryStr,
					String queryForCategories, String queryForselect, boolean isCount)
		  {
			  
			  StringBuffer queryString = new StringBuffer();
			  
			  
				 if(!isCount)
				 {
					 queryString.append("SELECT * FROM (" +
					 		"select v.voter_id,v.voter_id_card_no,v.name,v.house_no," +
			  		        "v.relative_name,v.gender,v.age,b.part_no,p.long_name," +
			  		        "c.caste_name,p.short_name," +
			  		        "bpv.serial_no , uvd.mobile_no");
					 		
					 		if(queryForselect.length() >0)
					 			queryString.append(queryForselect);
				 }else
				 {
					 queryString.append("SELECT * FROM (" +
						 		"select count(v.voter_id) ");
				 }
			  
				 queryString.append(" from " +
			  		" booth b," +
			  		" booth_publication_voter bpv," +
			  		"voter v ," +
			  		"user_voter_details uvd LEFT JOIN ( user_voter_details uvd1 JOIN caste_state cs on uvd1.caste_state_id = cs.caste_state_id JOIN caste c on cs.caste_id = c.caste_id) on uvd.user_voter_details_id = uvd1.user_voter_details_id " +
			  		"LEFT JOIN (user_voter_details uvd2 JOIN party p on uvd2.party_id = p.party_id ) on uvd.user_voter_details_id = uvd2.user_voter_details_id " );		  	
			  
					if(queryForCategories.length() >0)
						queryString.append(queryForCategories);
		
					queryString.append(" where  " +
							" b.publication_date_id = :publicationDateId and " +
							" b.booth_id = bpv.booth_id and " +
							" bpv.voter_id = v.voter_id and " +
							" bpv.voter_id = uvd.voter_id and " +
							" uvd.user_id = :userId and " +
							" uvd.hamlet_id = :hamletId " );
							
				 
				 if(queryStr.length() >0)
					 queryString.append(queryStr);
				 
				 if("initial".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by b.part_no ,bpv.serial_no  ,v.house_no ");
				 }
				 else if("partNo".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by b.part_no "+order);
				 }
				 else if("serialNo".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by bpv.serial_no "+order);
				 }
				 else if("gender".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.gender "+order);
				 }
				 else if("age".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.age "+order);
				 }
				 else if("houseNo".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.house_no "+order);
				 }
				 else if("voterId".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.voter_id_card_no "+order);
				 }
				 else if("name".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.name "+order);
				 }
				 
				 queryString.append(") AS subselection"); 
				 
			  Query query = getSession().createSQLQuery(queryString.toString());
			  
			  query.setParameter("hamletId", hamletId);
			  query.setParameter("publicationDateId", publicationdateId);
			  query.setParameter("userId", userId);
			  
			  
			  if(!isCount)
			  {
				  query.setFirstResult(startIndex);
				  query.setMaxResults(maxIndex);
				  
			  }
			  
		  return query.list();
			  
		  }
		  
		 public List getVotersDetailsAnCountDetailsForWardByPublicationId(Long wardId,Long userId,Long constituencyId,Long publicationId,int startIndex,int  maxIndex,String order,String columnName,String queryStr,
					String queryForCategories, String queryForselect, boolean isCount)
		  {
			  
			  StringBuffer queryString = new StringBuffer();
			  
			  
				 if(!isCount)
				 {
					 queryString.append("SELECT * FROM (" +
					 		"select v.voter_id,v.voter_id_card_no,v.name,v.house_no," +
			  		        "v.relative_name,v.gender,v.age,b.part_no,p.long_name," +
			  		        "c.caste_name,p.short_name," +
			  		        "bpv.serial_no , uvd.mobile_no");
					 		
					 		if(queryForselect.length() >0)
					 			queryString.append(queryForselect);
				 }else
				 {
					 queryString.append("SELECT * FROM (" +
						 		"select count(v.voter_id) ");
				 }
				 
				 queryString.append(" from booth b, booth_publication_voter bpv ,voter v,");
				 queryString.append("user_voter_details uvd LEFT JOIN (user_voter_details uvd1 JOIN caste_state CS on uvd1.caste_state_id = cs.caste_state_id JOIN caste c on cs.caste_id = c.caste_id ) on uvd.user_voter_details_id = uvd1.user_voter_details_id ");
				 queryString.append("LEFT JOIN");
				 queryString.append("(user_voter_details uvd2 JOIN party p on uvd2.party_id = p.party_id ) on uvd.user_voter_details_id = uvd2.user_voter_details_id");
				 
					if(queryForCategories.length() >0)
						queryString.append(queryForCategories);
					
				 queryString.append(" where ");
				 
				 queryString.append(" b.publication_date_id = :publicationDateId and ");
				 queryString.append(" bpv.voter_id = uvd.voter_id and ");
				 queryString.append(" bpv.booth_id = b.booth_id and ");
				 queryString.append(" uvd.user_id = :userId and ");
				 queryString.append(" uvd.ward_id = :wardId and ");
				 queryString.append(" v.voter_id = uvd.voter_id and ");
				 queryString.append(" b.constituency_id = :constituencyId  ");
				 
				 
				 if(queryStr.length() >0)
					 queryString.append(queryStr);
				 
				 if("initial".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by b.part_no ,bpv.serial_no  ,v.house_no ");
				 }
				 else if("partNo".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by b.part_no "+order);
				 }
				 else if("serialNo".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by bpv.serial_no "+order);
				 }
				 else if("gender".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.gender "+order);
				 }
				 else if("age".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.age "+order);
				 }
				 else if("houseNo".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.house_no "+order);
				 }
				 else if("voterId".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.voter_id_card_no "+order);
				 }
				 else if("name".equalsIgnoreCase(columnName))
				 {
					 queryString.append(" order by v.name "+order);
				 }
				 
				 queryString.append(") AS subselection"); 
				 
			  Query query = getSession().createSQLQuery(queryString.toString());
					
			
			  query.setParameter("wardId", wardId);
			  query.setParameter("publicationDateId", publicationId);
			  query.setParameter("userId", userId);
			  query.setParameter("constituencyId", constituencyId);
			  
			  
			  if(!isCount)
			  {
				  query.setFirstResult(startIndex);
				  query.setMaxResults(maxIndex);
				  
			  }
			  
		  return query.list();
			  
		  }
		 public List<Object[]>  getHamletsForPartialBooth(Long boothId){
				
				Query query = getSession().createSQLQuery("select distinct h.hamlet_id,h.hamlet_name from partial_booth_panchayat pbp,panchayat_hamlet ph,hamlet h where " +
						" pbp.booth_id = :boothId and pbp.panchayat_id = ph.panchayat_id and ph.hamlet_id = h.hamlet_id") ;
				query.setParameter("boothId", boothId);
				
			     return query.list();
			}
		 public List<Object[]> getPartialBoothHamlets(Long panchayatId,Long publicationId){
			 
				Query query = getSession().createQuery("select distinct ph.hamlet.hamletId, ph.hamlet.hamletName from PanchayatHamlet ph,PartialBoothPanchayat pbp where pbp.booth.panchayat.panchayatId = :panchayatId " +
						" and pbp.booth.publicationDate.publicationDateId = :publicationId and pbp.panchayat.panchayatId = ph.panchayat.panchayatId ");
				query.setParameter("panchayatId", panchayatId);
				query.setParameter("publicationId", publicationId);
				return query.list();
			}		 
		public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,List<Long> locationIds,Long publicationDateId,Long constituencyId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select uvd.casteState,bpv.voter.gender,count(bpv.voter.voterId),uvd.casteState.casteStateId,uvd.casteState.casteCategoryGroup.casteCategory.categoryName, " +
              " ph.panchayat.panchayatId from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where ph.panchayat.panchayatId in (:locationId) and ph.hamlet.hamletId = uvd.hamlet.hamletId " +
              " and uvd.user.userId = :userId and uvd.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId and " +
			  " bpv.booth.constituency.constituencyId = :constituencyId   group by ph.panchayat.panchayatId, " +
			  "  uvd.casteState.caste.casteId,bpv.voter.gender ");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("locationId", locationIds);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
		
		public List<Object[]> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,List<Long> locationIds,Long publicationDateId,Long constituencyId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select uvd.party.shortName,bpv.voter.gender,count(bpv.voter.voterId),uvd.party.partyId, " +
			   " ph.panchayat.panchayatId from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where " +
			   " ph.panchayat.panchayatId in (:locationIds) and ph.hamlet.hamletId = uvd.hamlet.hamletId and uvd.user.userId = :userId " +
			   " and uvd.voter.voterId = bpv.voter.voterId  and bpv.booth.publicationDate.publicationDateId = :publicationDateId and " +
			   " bpv.booth.constituency.constituencyId = :constituencyId  group by ph.panchayat.panchayatId, " +
			   " uvd.party.partyId,bpv.voter.gender ");
			Query query =getSession().createQuery(str.toString());
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("locationIds", locationIds);
			query.setParameter("constituencyId", constituencyId);
			
			return query.list();
		}
		
		public Long findVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select count(bpv.voter.voterId) from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where ph.panchayat.panchayatId = :panchayatId and ph.hamlet.hamletId = uvd.hamlet.hamletId " +
              " and uvd.user.userId = :userId and uvd.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId ");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			
			return (Long)query.uniqueResult();
		}
		
		public List<Object[]> getCastCategoryWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select uvd.casteState.casteCategoryGroup.casteCategory.categoryName, count(uvd.voter.voterId) from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where ph.panchayat.panchayatId = :panchayatId and ph.hamlet.hamletId = uvd.hamlet.hamletId " +
              " and uvd.user.userId = :userId and uvd.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId and uvd.casteState.casteStateId is not null " +
              " group by uvd.casteState.casteCategoryGroup.casteCategory.casteCategoryId order by uvd.casteState.casteCategoryGroup.casteCategory.categoryName ");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			
			return query.list();
		}
		
		public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId){
			
			StringBuilder str = new StringBuilder();
			
			str.append("select uvd.casteState.caste.casteName,uvd.voter.gender,count(uvd.voter.voterId),uvd.casteState.casteStateId,uvd.casteState.casteCategoryGroup.casteCategory.categoryName" +
					" from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where ph.panchayat.panchayatId = :panchayatId and ph.hamlet.hamletId = uvd.hamlet.hamletId " +
              " and uvd.user.userId = :userId and uvd.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId and uvd.casteState.casteStateId is not null " +
              " group by uvd.casteState.caste.casteId,uvd.voter.gender order by uvd.casteState.caste.casteName ");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			
			return query.list();
		}
		
		public Long getTotalCastCountForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select count(uvd.casteState.casteStateId) from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where ph.panchayat.panchayatId = :panchayatId and ph.hamlet.hamletId = uvd.hamlet.hamletId " +
              " and uvd.user.userId = :userId and uvd.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId ");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			
			return (Long)query.uniqueResult();
		}
		
		public List<Object[]> getImpFamilesForPartialPanchayatByPublicationId(Long panchayatId,Long publicationDateId,Long userId){
			StringBuilder query = new StringBuilder();

			query.append("select count(UVD.voter.voterId),UVD.voter.houseNo from UserVoterDetails UVD , PanchayatHamlet PH,BoothPublicationVoter bpv " +
			"where PH.panchayat.panchayatId = :panchayatId and PH.hamlet.hamletId = UVD.hamlet.hamletId and UVD.user.userId =:userId " +
			"and UVD.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId " +
			"group by UVD.voter.houseNo");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("panchayatId", panchayatId);
			queryObj.setParameter("userId", userId);
			  return queryObj.list();
		}
		
		public List<Object[]> getVoterDetailsByCasteStateForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId,Long casteStateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select distinct uvd.voter,bpv.booth.boothId,uvd.mobileNo from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where ph.panchayat.panchayatId = :panchayatId and ph.hamlet.hamletId = uvd.hamlet.hamletId " +
              " and uvd.user.userId = :userId and uvd.casteState.casteStateId = :casteStateId and uvd.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId ");
			
			Query query = getSession().createQuery(str.toString()) ;
			query.setParameter("userId", userId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("panchayatId", panchayatId);
			query.setParameter("casteStateId", casteStateId);
			
			return query.list();
		}
		 
		 
		 public List<Object[]> getVotersCountForPanchayatByPublicationIdAndHamlet(Long panchayatId,Long publicationDateId,Long userId){
				Query query = getSession().createQuery("select count(*),UVD.voter.gender from UserVoterDetails UVD , PanchayatHamlet PH,BoothPublicationVoter bpv " +
							       "where PH.panchayat.panchayatId = :panchayatId and PH.hamlet.hamletId = UVD.hamlet.hamletId and " +
							       "UVD.user.userId =:userId and UVD.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId group by UVD.voter.gender") ;
				  query.setParameter("publicationDateId", publicationDateId);
				  query.setParameter("panchayatId", panchayatId);
				  query.setParameter("userId", userId);
				  return query.list();
		}
		 

	public List<Object[]> getBoothAndHamletIdsByConstituencyId(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("select model.booth.boothId,model2.hamlet.hamletId from BoothPublicationVoter model,UserVoterDetails model2 " +
				" where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and model2.user.userId =:userId and model2.hamlet is not null group by model.booth.boothId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Object[]> getVoterAgeDetailsForPartialPanchayats(Long userId,Long publicationDateId,List<Long> panchayatIds){
		Query query = getSession().createQuery("select count(UVD.voter.voterId),UVD.voter.gender,UVD.voter.voterAgeRange.voterAgeRangeId,PH.panchayat.panchayatId from UserVoterDetails UVD ,  " +
				" PanchayatHamlet PH,BoothPublicationVoter bpv where PH.panchayat.panchayatId in (:panchayatIds) and PH.hamlet.hamletId = UVD.hamlet.hamletId and " +
				" UVD.user.userId =:userId and UVD.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId group by PH.panchayat.panchayatId," +
				" UVD.voter.voterAgeRange.voterAgeRangeId,UVD.voter.gender") ;
				
		query.setParameterList("panchayatIds", panchayatIds);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Object[]> getVotersCountForMultiplePartialPanchayats(Set<Long> panchayatIds,Long publicationDateId,Long userId){
		Query query = getSession().createQuery("select count(*),UVD.voter.gender,PH.panchayat.panchayatId from UserVoterDetails UVD , PanchayatHamlet PH,BoothPublicationVoter bpv " +
					       "where PH.panchayat.panchayatId in (:panchayatIds) and PH.hamlet.hamletId = UVD.hamlet.hamletId and " +
					       "UVD.user.userId =:userId and UVD.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId group by PH.panchayat.panchayatId,UVD.voter.gender") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameterList("panchayatIds", panchayatIds);
		  query.setParameter("userId", userId);
		  return query.list();
    }
	
	public List<Object[]> getVoterAttributeDetailsForPartialPanchayat(Long userId,List<Long> attributeIds,Long locationId,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(*),UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				" BoothPublicationVoter BPV,UserVoterDetails UVD,PanchayatHamlet ph where ph.panchayat.panchayatId = :locationId and UVD.hamlet.hamletId = ph.hamlet.hamletId and UVD.user.userId = :userId and " +
				"  UVD.voter.voterId = VCV.voter.voterId and VCV.user.userId = :userId and VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId  and  " +
				" UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  and UVCV.user.userId = :userId   and UVD.voter.voterId = BPV.voter.voterId    " +
				"    and BPV.booth.publicationDate.publicationDateId = :publicationId  ");
		
		queryString.append("  group by UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getVoterAttributeDetailsForPartialPanchayatByHamlet(Long userId,List<Long> attributeIds,List<Long> locationIds,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(*),UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				" BoothPublicationVoter BPV,UserVoterDetails UVD where  UVD.hamlet.hamletId in (:locationIds) and UVD.user.userId = :userId and " +
				"  UVD.voter.voterId = VCV.voter.voterId and VCV.user.userId = :userId and VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId  and  " +
				" UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds)  and UVCV.user.userId = :userId   and UVD.voter.voterId = BPV.voter.voterId    " +
				"    and BPV.booth.publicationDate.publicationDateId = :publicationId  ");
		
		queryString.append("  group by UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("locationIds", locationIds);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getVoterAttributeDetailsForPartialPanchayat(Long userId,Long attributeId,String locationType,Set<Long> locationIds,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		Query query = getSession().createQuery("select count(*),ph.panchayat.panchayatId,UVCV.userVoterCategoryValueId,BPV.voter.gender from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				" BoothPublicationVoter BPV,UserVoterDetails UVD,PanchayatHamlet ph where ph.panchayat.panchayatId in (:locationIds) and ph.hamlet.hamletId = UVD.hamlet.hamletId and " +
				" UVD.voter.voterId = VCV.voter.voterId and VCV.user.userId = :userId and VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
				"  UVCV.userVoterCategory.userVoterCategoryId = :attributeId " +
				"  and UVCV.user.userId = :userId and VCV.voter.voterId = BPV.voter.voterId  and BPV.booth.publicationDate.publicationDateId = :publicationId    " +
		        " group by ph.panchayat.panchayatId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
		
		query.setParameterList("locationIds", locationIds);
		query.setParameter("attributeId", attributeId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getAgeWiseDetailsForPartialPanchayat(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId,Long startAge,Long endAge){
		StringBuilder queryString = new StringBuilder();
	
		queryString.append("select count(BPV.voter.voterId),BPV.voter.gender,UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,UVCV.categoryValue from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				"BoothPublicationVoter BPV,UserVoterDetails UVD,PanchayatHamlet ph where ph.panchayat.panchayatId = :locationId and ph.hamlet.hamletId = UVD.hamlet.hamletId and UVD.user.userId = :userId and " +
				" UVD.voter.voterId = VCV.voter.voterId and VCV.user.userId = :userId and UVCV.user.userId = :userId and VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and  " +
			"   UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds) and UVD.voter.voterId = BPV.voter.voterId    " +
			"   and BPV.booth.publicationDate.publicationDateId = :publicationId and " +
			"   BPV.voter.age >="+startAge+" and BPV.voter.age <="+endAge+"  group by UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,BPV.voter.gender ");
	
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getCasteWiseDetailsForPartialPanchayat(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select UVD.casteState.caste.casteId , UVD.casteState.caste.casteName ," +
				" count(BPV.voter.voterId),BPV.voter.gender,UVCV.userVoterCategory.userVoterCategoryId,UVCV.userVoterCategoryValueId,UVCV.categoryValue  from VoterCategoryValue VCV,UserVoterCategoryValue UVCV," +
				" BoothPublicationVoter BPV ,UserVoterDetails UVD,PanchayatHamlet ph where ph.panchayat.panchayatId = :locationId and ph.hamlet.hamletId = UVD.hamlet.hamletId and UVD.user.userId = :userId and VCV.userVoterCategoryValue.userVoterCategoryValueId = UVCV.userVoterCategoryValueId and VCV.user.userId = :userId and UVCV.user.userId = :userId and  " +
				"  UVCV.userVoterCategory.userVoterCategoryId in (:attributeIds) and VCV.voter.voterId = UVD.voter.voterId  and UVD.voter.voterId = BPV.voter.voterId   " +
				"   and BPV.booth.publicationDate.publicationDateId = :publicationId   ");		
		queryString.append("  group by UVCV.userVoterCategoryValueId,UVD.casteState.caste.casteId,BPV.voter.gender ");
		queryString.append(" order by UVCV.categoryValue, UVD.casteState.caste.casteName ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("locationId", locationId);
		query.setParameterList("attributeIds", attributeIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	
	public List<Object[]> getPanchayatAgeWiseDetailsByHamletWise(Long userId , Long publicationDateId , List<Long> panchayatIds)
	{
		StringBuffer queryString = new StringBuffer();
		
		
		//queryString.append("select SUM( CASE WHEN v.age>=18 and v.age <=22 THEN 1 ELSE 0 END) / count(*) from panchayat_hamlet PH,user_voter_details UVD ,booth_publication_voter BPV ,booth B ,voter V where PH.panchayat_id = 2 and PH.hamlet_id =UVD.hamlet_id and UVD.user_id = 1 and UVD.hamlet_id = PH.hamlet_id and UVD.voter_id = BPV.voter_id and BPV.booth_id = B.booth_id and B.publication_date_id =8 and BPV.voter_id = V.voter_id");
		queryString.append("select " +
				"SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 THEN 1 ELSE 0 END)," +
				"ROUND(SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 THEN 1 ELSE 0 END)/COUNT(UVD.voter.voterId)*100 ,2)," +
				"SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 THEN 1 ELSE 0 END))*100,2) ," +
				"SUM (CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 and UVD.voter.gender = 'F'THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 and UVD.voter.gender = 'F' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=25 THEN 1 ELSE 0 END))*100,2) ," +

				"SUM(CASE  WHEN UVD.voter.age>=26 and UVD.voter.age <=35 THEN 1 ELSE 0 END) ," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=26 and UVD.voter.age <=35 THEN 1 ELSE 0 END)/COUNT(UVD.voter.voterId))*100,2) ," +
				"SUM(CASE  WHEN UVD.voter.age>=26 and UVD.voter.age <=35 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=26 and UVD.voter.age <=35 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=23 and UVD.voter.age <=26 THEN 1 ELSE 0 END))*100,2) ," +
				"SUM (CASE  WHEN UVD.voter.age>=26 and UVD.voter.age <=35 and UVD.voter.gender = 'F'THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=26 and UVD.voter.age <=35 and UVD.voter.gender = 'F' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=23 and UVD.voter.age <=26 THEN 1 ELSE 0 END))*100,2), " +

				"SUM(CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 THEN 1 ELSE 0 END) ," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 THEN 1 ELSE 0 END)/COUNT(UVD.voter.voterId))*100,2) ," +
				"SUM(CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 THEN 1 ELSE 0 END))*100,2) ," +
				"SUM (CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 and UVD.voter.gender = 'F'THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 and UVD.voter.gender = 'F' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=36 and UVD.voter.age <=45 THEN 1 ELSE 0 END))*100,2), " +

				"SUM(CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 THEN 1 ELSE 0 END) ," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 THEN 1 ELSE 0 END)/COUNT(UVD.voter.voterId))*100,2) ," +
				"SUM(CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 THEN 1 ELSE 0 END))*100,2) ," +
				"SUM (CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 and UVD.voter.gender = 'F'THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 and UVD.voter.gender = 'F' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=46 and UVD.voter.age <=60 THEN 1 ELSE 0 END))*100,2) ," +

				"SUM(CASE  WHEN UVD.voter.age>60 THEN 1 ELSE 0 END) , " +
				"ROUND((SUM(CASE  WHEN UVD.voter.age > 60   THEN 1 ELSE 0 END)/COUNT(UVD.voter.voterId))*100,2) ,"+
				"SUM(CASE  WHEN UVD.voter.age>60  and UVD.voter.gender = 'M'   THEN 1 ELSE 0 END) ," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>60 and UVD.voter.gender = 'M'  THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>60  THEN 1 ELSE 0 END))*100,2) ,"+				
				"SUM(CASE  WHEN UVD.voter.age>60  and UVD.voter.gender = 'F'  THEN 1 ELSE 0 END) ," +				
				"ROUND((SUM(CASE  WHEN UVD.voter.age>60 and UVD.voter.gender = 'F'  THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>60  THEN 1 ELSE 0 END))*100,2) ," + 
				"COUNT(UVD.voter.voterId ) ,PH.panchayat.panchayatId,PH.panchayat.panchayatName ," +
				
				"SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=22 THEN 1 ELSE 0 END) ," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=22 THEN 1 ELSE 0 END)/COUNT(UVD.voter.voterId))*100,2) ," +
				"SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=22 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=22 and UVD.voter.gender = 'M' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=18 THEN 1 ELSE 0 END))*100,2) ," +
				"SUM (CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=22 and UVD.voter.gender = 'F'THEN 1 ELSE 0 END)," +
				"ROUND((SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=22 and UVD.voter.gender = 'F' THEN 1 ELSE 0 END)/SUM(CASE  WHEN UVD.voter.age>=18 and UVD.voter.age <=18 THEN 1 ELSE 0 END))*100,2) " +

				
				"from PanchayatHamlet PH , BoothPublicationVoter BPV , UserVoterDetails UVD " +
				"where " +
				"PH.hamlet.hamletId = UVD.hamlet.hamletId and " +
				"UVD.user.userId = :userId and " +
				"UVD.voter.voterId = BPV.voter.voterId and " +
				"BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				"PH.panchayat.panchayatId in(:panchayatIds) group by PH.panchayat.panchayatId ");
		
		
		queryString.append("");
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("panchayatIds", panchayatIds);
		
		return query.list();
		
	}
	
	/*
	select 
	COUNT(v.voter_id) as totalCount,
	SUM(IF(v.age>=18 and v.age <=22 and v.gender = 'M',1,0)) as 18To22Male ,
	SUM(IF(v.age>=18 and v.age <=22 and v.gender = 'F',1,0)) as 18To22FeMale ,
	SUM(IF(v.age>=23 and v.age <=30 and v.gender = 'M',1,0)) as 23To30Male ,
	SUM(IF(v.age>=23 and v.age <=30 and v.gender = 'F',1,0)) as 23To30FeMale ,
	SUM(IF(v.age>=31 and v.age <=45 and v.gender = 'M',1,0)) as 31To45Male ,
	SUM(IF(v.age>=31 and v.age <=45 and v.gender = 'F',1,0)) as 31To45FeMale ,
	SUM(IF(v.age>=46 and v.age <=60 and v.gender = 'M',1,0)) as 46To60Male ,
	SUM(IF(v.age>=46 and v.age <=60 and v.gender = 'F',1,0)) as 46To60FeMale ,
	SUM(IF(v.age>60 and v.gender = 'M',1,0)) as above60Male ,
	SUM(IF(v.age>60 and v.gender = 'F',1,0)) as above60FeMale ,
	SUM(IF(v.age>=18 and v.age <=22,1,0)) as 18To22Total ,
	SUM(IF(v.age>=23 and v.age <=30,1,0)) as 23To30Total ,
	SUM(IF(v.age>=31 and v.age <=45,1,0)) as 31To45Total ,
	SUM(IF(v.age>=46 and v.age <=60,1,0)) as 46To60Total ,
	SUM(IF(v.age>60,1,0)) as above60Total ,
	ROUND((SUM(IF(v.age>=18 and v.age <=22,1,0))/COUNT(v.voter_id))*100,2) as 18To22TotalPercent,
	ROUND((SUM(IF(v.age>=18 and v.age <=22 and v.gender = 'M',1,0))/SUM(IF(v.age>=18 and v.age <=22,1,0)))*100,2) as 18To22MalelPercent,
	ROUND((SUM(IF(v.age>=18 and v.age <=22 and v.gender = 'F',1,0))/SUM(IF(v.age>=18 and v.age <=22,1,0)))*100,2) as 18To22FemalePercent,
	ROUND((SUM(IF(v.age>=23 and v.age <=30,1,0))/COUNT(v.voter_id))*100,2) as 23To30TotalPercent,
	ROUND((SUM(IF(v.age>=23 and v.age <=30 and v.gender = 'M',1,0))/SUM(IF(v.age>=23 and v.age <=30,1,0)))*100,2) as 23To30MalelPercent,
	ROUND((SUM(IF(v.age>=23 and v.age <=30 and v.gender = 'F',1,0))/SUM(IF(v.age>=23 and v.age <=30,1,0)))*100,2) as 23To30FemalePercent,
	####100-ROUND((SUM(IF(v.age>=23 and v.age <=30 and v.gender = 'M',1,0))/SUM(IF(v.age>=23 and v.age <=30,1,0)))*100,2) as 23To30FemalePercent,

	ROUND((SUM(IF(v.age>=31 and v.age <=45,1,0))/COUNT(v.voter_id))*100,2) as 31To45TotalPercent,
	ROUND((SUM(IF(v.age>=31 and v.age <=45 and v.gender = 'M',1,0))/SUM(IF(v.age>=31 and v.age <=45,1,0)))*100,2) as 31To45MalelPercent,
	ROUND((SUM(IF(v.age>=31 and v.age <=45 and v.gender = 'F',1,0))/SUM(IF(v.age>=31 and v.age <=45,1,0)))*100,2) as 31To45FemalePercent,

	ROUND((SUM(IF(v.age>=46 and v.age <=60,1,0))/COUNT(v.voter_id))*100,2) as 46To60TotalPercent,
	ROUND((SUM(IF(v.age>=46 and v.age <=60 and v.gender = 'M',1,0))/SUM(IF(v.age>=46 and v.age <=60,1,0)))*100,2) as 46To60MalelPercent,
	ROUND((SUM(IF(v.age>=46 and v.age <=60 and v.gender = 'F',1,0))/SUM(IF(v.age>=46 and v.age <=60,1,0)))*100,2) as 46To60FeMalelPercent,


	ROUND((SUM(IF(v.age>60,1,0))/COUNT(v.voter_id))*100,2) as above60TotalPercent,
	ROUND((SUM(IF(v.age>60 and v.gender = 'M',1,0))/SUM(IF(v.age>60,1,0)))*100,2) as above60MalelPercent,
	ROUND((SUM(IF(v.age>60 and v.gender = 'F',1,0))/SUM(IF(v.age>60,1,0)))*100,2) as above60FeMalelPercent

	from 
	panchayat_hamlet PH,
	user_voter_details UVD , 
	booth_publication_voter BPV ,
	booth B ,
	voter V
	where
	PH.panchayat_id = 2 and
	PH.hamlet_id =UVD.hamlet_id and
	UVD.user_id = 1 and
	UVD.hamlet_id = PH.hamlet_id and
	UVD.voter_id = BPV.voter_id and 
	BPV.booth_id = B.booth_id and
	B.publication_date_id =8 and
	BPV.voter_id = V.voter_id;*/

	
	public List<Object[]> getYoungVotersCount(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,String locationType,Long ageFrom,Long ageTo)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.voter.voterId),model.voter.gender ");
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(",model.booth.constituency.constituencyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
			 str.append(",model.booth.tehsil.tehsilId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append(",model.booth.panchayat.panchayatId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			 str.append(",model.booth.boothId  ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			 str.append(",model.booth.localBody.localElectionBodyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			 str.append(",model.booth.localBodyWard.constituencyId ");
			
			str.append(" from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId ");
			
			if(ageTo != null)
			 str.append(" and model.voter.age between :ageFrom and :ageTo ");
			else
			 str.append(" and model.voter.age > :ageFrom ");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(" and model.booth.constituency.constituencyId in(:locationIdsList) group by model.booth.constituency.constituencyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
			 str.append(" and model.booth.tehsil.tehsilId in (:locationIdsList) group by model.booth.tehsil.tehsilId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append(" and model.booth.panchayat.panchayatId in(:locationIdsList) and model.booth.localBody is null group by model.booth.panchayat.panchayatId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			 str.append(" and model.booth.boothId in(:locationIdsList) group by model.booth.boothId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			 str.append(" and model.booth.localBody.localElectionBodyId in(:locationIdsList) group by model.booth.localBody.localElectionBodyId ");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			 str.append(" and model.booth.localBodyWard.constituencyId in (:locationIdsList) group by model.booth.localBodyWard.constituencyId ");
			
			str.append(",model.voter.gender ");
			Query query = getSession().createQuery(str.toString());
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("locationIdsList", locationIdsList);
			query.setParameter("ageFrom", ageFrom);
			if(ageTo != null)
			 query.setParameter("ageTo", ageTo);
			
		return query.list();
	}
	
	public List<Object[]> getAgeAndGenderWiseVotersCountInBoothsOfMuncipality(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId){
		StringBuilder str=new StringBuilder();
		str.append("select " +
				" model1.casteState.caste.casteName," +
				" model1.casteState.casteStateId," +
				" count(model.voter.voterId)," +
				" model.booth.localBody.localElectionBodyId," +
				" model.booth.localBody.name," +
				" model.booth.localBody.electionType.electionType from" +
				" BoothPublicationVoter model,UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId" +
				" and model.booth.constituency.constituencyId =:constituencyId" +
				" and model.booth.publicationDate.publicationDateId =:publicationDateId and model.booth.localBody!=null" +
				" and model1.user.userId=:userId ");
		
		if(ageTo != null){
			str.append(" and model.voter.age between :ageFrom and :ageTo");
			}
		else{
			str.append(" and model.voter.age > :ageFrom");
		}
		
		str.append(" group by  model1.casteState.casteStateId");
		
		Query qry=getSession().createQuery(str.toString());
		qry.setParameter("constituencyId", constituencyId);
		qry.setParameter("publicationDateId", publicationDateId);
		qry.setParameter("userId", userId);
		qry.setParameter("ageFrom",ageFrom);
		
		if(ageTo != null){
			qry.setParameter("ageTo",ageTo);
		}
		
		return qry.list();
	}
	
	public Long getAgeAndGenderWisesMuncipaltiyVotersCount(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId){
		StringBuilder str=new StringBuilder();
		str.append("select " +
				" count(model.voter.voterId) " +
				" from" +
				" BoothPublicationVoter model where " +
				" model.booth.constituency.constituencyId =:constituencyId" +
				" and model.booth.publicationDate.publicationDateId =:publicationDateId and model.booth.localBody!=null" +
				" ");
		
		if(ageTo != null){
			str.append(" and model.voter.age >= :ageFrom and model.voter.age <= :ageTo");
			}
		else{
			str.append(" and model.voter.age > :ageFrom");
		}
		
		//str.append(" group by  model1.casteState.casteStateId");
		
		Query qry=getSession().createQuery(str.toString());
		qry.setParameter("constituencyId", constituencyId);
		qry.setParameter("publicationDateId", publicationDateId);
		//qry.setParameter("userId", userId);
		qry.setParameter("ageFrom",ageFrom);
		
		if(ageTo != null){
			qry.setParameter("ageTo",ageTo);
		}
		
		return (Long) qry.uniqueResult();
	}
	
	public List<Object[]> getExpCasteForAgeAndGenderWisesMuncipaltiyVotersCount(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId,List<Long> casteIds){
		StringBuilder str=new StringBuilder();
		str.append("select " +
				" model1.casteState.caste.casteName," +
				" model1.casteState.casteStateId," +
				" count(model.voter.voterId)" +
				" from " +
				" BoothPublicationVoter model,UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId" +
				" and model.booth.constituency.constituencyId =:constituencyId" +
				" and model.booth.publicationDate.publicationDateId =:publicationDateId and model.booth.localBody!=null" +
				" and model1.user.userId=:userId and model1.casteState.casteStateId in (:casteIds) ");
		
		if(ageTo != null){
			str.append(" and model.voter.age between :ageFrom and :ageTo");
			}
		else{
			str.append(" and model.voter.age > :ageFrom");
		}
		
		str.append(" group by  model1.casteState.casteStateId");
		
		Query qry=getSession().createQuery(str.toString());
		qry.setParameter("constituencyId", constituencyId);
		qry.setParameter("publicationDateId", publicationDateId);
		qry.setParameter("userId", userId);
		qry.setParameter("ageFrom",ageFrom);
		qry.setParameterList("casteIds", casteIds);
		if(ageTo != null){
			qry.setParameter("ageTo",ageTo);
		}
		
		return qry.list();
	}
	
	
	public List<Object[]> getAddedVotersBetweenTwoPublications(Long constituencyId,Long fromPublicationDateId, Long toPublicationDateId)
	{
	  Query query = getSession().createSQLQuery(" select B.part_no,BPV.voter_id from booth_publication_voter BPV,Booth B " +
	  		" where BPV.booth_id = B.booth_id and B.publication_date_id =:toPublicationDateId " +
	  		"  and B.constituency_id =:constituencyId and BPV.voter_id not in (select BPV2.voter_id from booth_publication_voter BPV2,Booth B2 " +
	  		" where BPV2.booth_id = B2.booth_id and " +
	  		"  B2.constituency_id = :constituencyId and B2.publication_date_id =:fromPublicationDateId) ");
	  
	  query.setParameter("constituencyId", constituencyId);
	  query.setParameter("fromPublicationDateId", fromPublicationDateId);
	  query.setParameter("toPublicationDateId", toPublicationDateId);
	  
	  return query.list();
	}
	
	public List<Object[]> getDeletedVotersBetweenTwoPublications(Long constituencyId,Long fromPublicationDateId, Long toPublicationDateId)
	{
		Query query = getSession().createSQLQuery(" select B.part_no,BPV.voter_id from booth_publication_voter BPV, booth B where BPV.booth_id = B.booth_id " +
				" and B.publication_date_id =:fromPublicationDateId and B.constituency_id =:constituencyId and BPV.voter_id not in (select BPV2.voter_id from booth_publication_voter BPV2," +
				" Booth B2 where BPV2.booth_id = B2.booth_id and B2.constituency_id =:constituencyId and B2.publication_date_id = :toPublicationDateId ) ");
		
		query.setParameter("constituencyId", constituencyId);
		  query.setParameter("fromPublicationDateId", fromPublicationDateId);
		  query.setParameter("toPublicationDateId", toPublicationDateId);
		return query.list();
	}
	
	public List<Object[]> getCustomWardWiseTotalVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),uvd.ward.constituencyId from BoothPublicationVoter bpv,UserVoterDetails uvd  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and uvd.ward.constituencyId is not null group by uvd.ward.constituencyId ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getWardBoothWiseTotalVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),wb.wardBoothId  from BoothPublicationVoter bpv,UserVoterDetails uvd ,WardBooth wb where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and uvd.ward.constituencyId = wb.ward.constituencyId and bpv.booth.boothId = wb.booth.boothId and wb.publicationDate.publicationDateId = :publicationId and" +
				" wb.ward.localElectionBody.localElectionBodyId = :localElectionBodyId group by wb.wardBoothId ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]>  getCasteGroupContsByWardBoothWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId){
		Query query = getSession().createQuery("select wb.wardBoothId ,uvd.casteState.casteCategoryGroup.casteCategory.categoryName, " +
				"count(distinct bpv.voter.voterId) from BoothPublicationVoter bpv,UserVoterDetails uvd,WardBooth wb where bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId " +
				"  and bpv.booth.constituency.constituencyId = :constituencyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId and  uvd.ward.constituencyId = wb.ward.constituencyId " +
				" and bpv.booth.boothId = wb.booth.boothId and wb.publicationDate.publicationDateId = :publicationId and wb.ward.localElectionBody.localElectionBodyId = :localElectionBodyId " +
				" and uvd.casteState.casteCategoryGroup.casteCategory.categoryName is not null group by wb.wardBoothId,uvd.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	public List<Object[]>  getCasteWiseGenderWiseContsByWardBoothWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId){
		Query query = getSession().createQuery("select wb.wardBoothId,uvd.casteState.casteStateId,uvd.voter.gender, " +
				"count(distinct bpv.voter.voterId) from BoothPublicationVoter bpv,UserVoterDetails uvd,WardBooth wb where bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId " +
				"  and bpv.booth.constituency.constituencyId = :constituencyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId and uvd.ward.constituencyId = wb.ward.constituencyId " +
				"  and bpv.booth.boothId = wb.booth.boothId and wb.publicationDate.publicationDateId = :publicationId and wb.ward.localElectionBody.localElectionBodyId = :localElectionBodyId " +
				" and uvd.casteState.casteStateId is not null and uvd.voter.gender is not null group by wb.wardBoothId,uvd.casteState.casteStateId,uvd.voter.gender ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]>  getCasteGroupContsByCustomWardWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId){
		Query query = getSession().createQuery("select uvd.ward.constituencyId,uvd.casteState.casteCategoryGroup.casteCategory.categoryName, " +
				"count(distinct bpv.voter.voterId) from BoothPublicationVoter bpv,UserVoterDetails uvd where bpv.booth.publicationDate.publicationDateId = :publicationDateId and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId " +
				"  and bpv.booth.constituency.constituencyId = :constituencyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId and uvd.ward.constituencyId is not null " +
				" and uvd.casteState.casteCategoryGroup.casteCategory.categoryName is not null group by uvd.ward.constituencyId,uvd.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	public List<Object[]>  getCasteWiseGenderWiseContsByCustomWardWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId){
		Query query = getSession().createQuery("select uvd.ward.constituencyId,uvd.casteState.casteStateId,uvd.voter.gender, " +
				"count(distinct bpv.voter.voterId) from BoothPublicationVoter bpv,UserVoterDetails uvd where bpv.booth.publicationDate.publicationDateId = :publicationDateId and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId " +
				"  and bpv.booth.constituency.constituencyId = :constituencyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId and uvd.ward.constituencyId is not null " +
				" and uvd.casteState.casteStateId is not null and uvd.voter.gender is not null group by uvd.ward.constituencyId,uvd.casteState.casteStateId,uvd.voter.gender ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getCustomWardWiseTotalMaleFemaleVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),uvd.ward.constituencyId,SUM( CASE WHEN bpv.voter.gender='F' THEN 1 ELSE 0 END) as femalecount" +
				" ,SUM( CASE WHEN bpv.voter.gender='M' THEN 1 ELSE 0 END) as malecount from BoothPublicationVoter bpv,UserVoterDetails uvd  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and uvd.ward.constituencyId is not null group by uvd.ward.constituencyId ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getWardBoothTotalMaleFemaleVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),wb.wardBoothId,SUM( CASE WHEN bpv.voter.gender='F' THEN 1 ELSE 0 END) as femalecount" +
				" ,SUM( CASE WHEN bpv.voter.gender='M' THEN 1 ELSE 0 END) as malecount from BoothPublicationVoter bpv,UserVoterDetails uvd,WardBooth wb where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and  uvd.ward.constituencyId = wb.ward.constituencyId and bpv.booth.boothId = wb.booth.boothId and wb.publicationDate.publicationDateId = :publicationId " +
				" and wb.ward.localElectionBody.localElectionBodyId = :localElectionBodyId group by wb.wardBoothId ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getCustomWardWiseFamilyVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),uvd.ward.constituencyId " +
				"  from BoothPublicationVoter bpv,UserVoterDetails uvd  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and uvd.ward.constituencyId is not null group by uvd.ward.constituencyId,bpv.voter.houseNo ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getWardBoothWiseFamilyVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),wb.wardBoothId " +
				"  from BoothPublicationVoter bpv,UserVoterDetails uvd,WardBooth wb  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and uvd.ward.constituencyId = wb.ward.constituencyId and bpv.booth.boothId = wb.booth.boothId and wb.publicationDate.publicationDateId = :publicationId " +
				" and wb.ward.localElectionBody.localElectionBodyId = :localElectionBodyId group by wb.wardBoothId,bpv.voter.houseNo ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getCustomWardAgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),bpv.voter.gender,bpv.voter.voterAgeRange.voterAgeRangeId,uvd.ward.constituencyId " +
				"  from BoothPublicationVoter bpv,UserVoterDetails uvd  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and uvd.ward.constituencyId is not null and bpv.voter.voterAgeRange.voterAgeRangeId is not null group by uvd.ward.constituencyId,bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getCustomWard18To22AgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),bpv.voter.gender,1,uvd.ward.constituencyId " +
				"  from BoothPublicationVoter bpv,UserVoterDetails uvd  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId and bpv.voter.age >=18 and bpv.voter.age <=22 " +
				" and uvd.ward.constituencyId is not null group by uvd.ward.constituencyId,bpv.voter.gender ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}

	
	 public List<VoterFlag> getFlagDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId,Integer startIndex,Integer maxIndex,String order,String columnName)
	  {
		  StringBuffer queryString = new StringBuffer();
		  queryString.append("select model from VoterFlag model ,BoothPublicationVoter BPV where" +
			  		" model.voter.voterId = BPV.voter.voterId " +
			  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds) ");
		  if(columnName.equalsIgnoreCase("voterId"))
		  {
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else if(columnName.equalsIgnoreCase("gender"))
		  {
			  columnName = "gender";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else if(columnName.equalsIgnoreCase("houseNo"))
		  {
			  columnName = "houseNo";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else if(columnName.equalsIgnoreCase("relativeFirstName"))
		  {
			  columnName = "relativeName";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		  else
		  {
			  columnName = "name";
			  queryString.append(" order by model.voter."+columnName+" "+order);	
		  }
		Query query = getSession().createQuery(queryString.toString());
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("boothIds", boothIds);
		return query.list(); 
	  }
	  

	public List<Object[]> getWardBoothAgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),bpv.voter.gender,bpv.voter.voterAgeRange.voterAgeRangeId,wb.wardBoothId " +
				"  from BoothPublicationVoter bpv,UserVoterDetails uvd,WardBooth wb  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId " +
				" and  uvd.ward.constituencyId = wb.ward.constituencyId and bpv.booth.boothId = wb.booth.boothId and wb.publicationDate.publicationDateId = :publicationId" +
				" and bpv.voter.voterAgeRange.voterAgeRangeId is not null and wb.ward.localElectionBody.localElectionBodyId = :localElectionBodyId " +
				"  group by wb.wardBoothId,bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getWard18BoothTo22AgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId)
	{  
		Query query = getSession().createQuery("select count(distinct bpv.voter.voterId),bpv.voter.gender,1,wb.wardBoothId " +
				"  from BoothPublicationVoter bpv,UserVoterDetails uvd,WardBooth wb  where " +
				" bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.localBody.localElectionBodyId = :localElectionBodyId and bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId and bpv.voter.age >=18 and bpv.voter.age <=22 " +
				" and  uvd.ward.constituencyId = wb.ward.constituencyId and bpv.booth.boothId = wb.booth.boothId and wb.publicationDate.publicationDateId = :publicationId " +
				" and wb.ward.localElectionBody.localElectionBodyId = :localElectionBodyId group by wb.wardBoothId,bpv.voter.gender ");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<?> getVoterDetailsByBoothAndConstituency(List<Long> boothId,
			long constituencyId) {
		Query query =	getSession().createQuery("select  bpv.voter as voter ,bpv.booth.boothId as boothId,bpv.serialNo as sno ,bpv.booth.publicationDate.publicationDateId as pDate , bpv.booth.publicationDate.name as name , bpv.booth.constituency.constituencyId as cId  from BoothPublicationVoter bpv where bpv.booth.boothId in(:boothId) and bpv.booth.constituency.constituencyId = :constituencyId group by  bpv.voter");
	//	query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameterList("boothId", boothId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<?> getVoterDetailsByBoothAndConstituency(long boothId,long constituencyId) {
		Query query =	getSession().createQuery("select  bpv.voter as voter ,bpv.booth.boothId as boothId,bpv.serialNo as sno ,bpv.booth.publicationDate.publicationDateId as pDate , bpv.booth.publicationDate.name as name , bpv.booth.constituency.constituencyId as cId  from BoothPublicationVoter bpv where bpv.booth.boothId = :boothId and bpv.booth.constituency.constituencyId = :constituencyId group by  bpv.voter");
	//	query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("boothId", boothId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getHouseNosForBooth(Long constituencyId,Long publicationId,Long minVal,Long maxVal,Integer startIndex,Integer maxIndex)
	{
		StringBuilder str =new StringBuilder();
		
		str.append("select model.booth.boothId,model.voter.houseNo,count(model.voter.voterId) from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationId and model.voter.houseNo not in ('0','0-0','00-0','0-00','N.A.','-','--','000','00','0-0-0','0-0-0')");
		
		str.append(" group by model.booth.boothId,model.voter.houseNo ");
		if(maxVal == 0)
			str.append(" having count(model.voter.voterId) >= "+minVal+" ");
		else
			str.append(" having count(model.voter.voterId) between "+minVal+" and "+maxVal+" ");	
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		
		if(startIndex!=null)
			query.setFirstResult(startIndex);
		if(maxIndex != null)
			query.setMaxResults(maxIndex);
		return query.list();
	}
	
	public List<Object[]> getFamilyWiseInfoForBooth(Long boothId,List<String> hnos)
	{
		Query query = getSession().createQuery("select model.voter.houseNo,model.voter,model.booth.constituency.constituencyId from BoothPublicationVoter model where  model.booth.boothId = :boothId and model.voter.houseNo in (:hnos) order by model.voter.age");
		query.setParameter("boothId", boothId);
		query.setParameterList("hnos", hnos);
		return query.list();
	}
	
/*	public List<Object[]> getConstyPublicationIdByVoterId(String voterCardNo){
		Query query=getSession().createQuery("select model.booth.constituency.constituencyId,model.booth.boothId,model.booth.publicationDate.publicationDateId," +
				" model.voter.voterId,model.voter.name,model.voter.age,model.voter.gender,model.booth.constituency.district.districtId,model.voter.relativeName,model.voter.houseNo," +
				" model.voter.relationshipType,model.booth.constituency.name,model.booth.constituency.district.districtName,model.booth.partNo,0," +
				"  model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName,model.booth.tehsil.tehsilId,model.booth.tehsil.tehsilName," +
				" model.booth.constituency.state.stateId,model.booth.constituency.state.stateName " +
				" from BoothPublicationVoter model where model.voter.voterIDCardNo =:voterCardNo order by model.booth.publicationDate.publicationDateId desc ");
		
		query.setParameter("voterCardNo", voterCardNo);
		return query.list();
	}*/
	public List<Object[]> getConstyPublicationIdByVoterId(String voterCardNo){
		Query query=getSession().createSQLQuery("select distinct C.constituency_id as cid,B.booth_id as boothId,B.publication_date_id as pid,V.voter_id as voterId,V.name as voterName ,V.age as age,V.gender as gender,C.district_id as distId" +
				",V.relative_name as relativeName,V.house_no as hno,V.relationship_type as relationType,C.name as constName,D.district_name as distName,B.part_no as partNo,0 as zero," +
				" B.panchayat_id as panchayatId,P.panchayat_name as panchayatName,T.tehsil_id as tehsilId,T.tehsil_name as tehsilName,C.state_id as stateId,S.state_name as stateName,V.image_path as imagePath " +
				" from booth_publication_voter BPV ,voter V,district D,state S,booth B left outer join tehsil T on B.tehsil_id = T.tehsil_id " +
				" left outer join panchayat P on B.panchayat_id=P.panchayat_id " +
				" left outer join constituency C on B.constituency_id=C.constituency_id " +
				" left outer join local_election_body L on B.local_election_body_id=L.local_election_body_id " +
				" where B.booth_id=BPV.booth_id and BPV.voter_id=V.voter_id " +
				" and V.voter_id_card_no=:voterCardNo and B.publication_date_id="+IConstants.VOTER_DATA_PUBLICATION_ID+" and C.district_id=D.district_id and" +
				" C.state_id=S.state_id and S.state_id=1")
			.addScalar("cid", Hibernate.LONG)
			.addScalar("boothId", Hibernate.LONG)
			.addScalar("pid", Hibernate.LONG)
			.addScalar("voterId", Hibernate.LONG)
			.addScalar("voterName", Hibernate.STRING)
			.addScalar("age", Hibernate.LONG)
			.addScalar("gender", Hibernate.STRING)
			.addScalar("distId", Hibernate.LONG)
			.addScalar("relativeName", Hibernate.STRING)
			.addScalar("hno", Hibernate.STRING)
			.addScalar("relationType", Hibernate.STRING)
			.addScalar("constName", Hibernate.STRING)
			.addScalar("distName", Hibernate.STRING)
			.addScalar("partNo", Hibernate.STRING)
			.addScalar("zero", Hibernate.LONG)
			.addScalar("panchayatId", Hibernate.LONG)
			.addScalar("panchayatName", Hibernate.STRING)
			.addScalar("tehsilId", Hibernate.LONG)
			.addScalar("tehsilName", Hibernate.STRING)
			.addScalar("stateId", Hibernate.LONG)
			.addScalar("stateName", Hibernate.STRING)
			.addScalar("imagePath",Hibernate.STRING);
		query.setParameter("voterCardNo", voterCardNo);
		
		return query.list();
	}
	 

	public BoothPublicationVoter getVoterBySerialNo(Long constituencyId,
			String partNo, Long serialNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Object[]> getlist1()
	{
		Query query = getSession().createQuery("select model.booth.boothId,model.voter.houseNo,count(model.voter.voterId) from BoothPublicationVoter model where " +
				" model.booth.constituency.constituencyId = 282 and model.booth.publicationDate.publicationDateId = 10 group by model.booth.boothId, model.voter.houseNo " +
				" order by model.booth.boothId,model.voter.houseNo");
		return query.list();
	}
	
	public List<Object[]> getlist2(Long boothId,String houseNo)
	{
		Query query = getSession().createQuery("select model.booth.partNo,model.voter.voterIDCardNo,model.voter.name,model.voter.gender,model.voter.age from BoothPublicationVoter model " +
				" where model.booth.boothId = :boothId and model.voter.houseNo = :houseNo order by model.voter.age desc");
		query.setParameter("boothId",boothId);
		query.setParameter("houseNo",houseNo);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<Object[]> getlist3(Long boothId,String houseNo)
	{
		Query query = getSession().createQuery("select model.booth.partNo,model.voter.voterIDCardNo,model.voter.name,model.voter.gender,model.voter.age from BoothPublicationVoter model " +
				" where model.booth.boothId = :boothId and model.voter.houseNo = :houseNo order by model.voter.age asc");
		query.setParameter("boothId",boothId);
		query.setParameter("houseNo",houseNo);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<String> getVoterNamesOfAConstituency(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select model.voter.name from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and model.booth.publicationDate.publicationDateId = :publicationDateId");
		
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public List<Voter> getVoterInfoByVoterIds(Long constituencyId,Long publicationDateId,List<Long> voterIds) {
		Query qry=getSession().createQuery("select model.voter from BoothPublicationVoter model " +
				" where model.booth.publicationDate.publicationDateId = :publicationDateId " +
				//" and model.booth.boothId = :boothId " +
				" and model.booth.constituency.constituencyId = :constituencyId " +
				" and model.voter.voterId in (:voterIds)") ;
		
		qry.setParameter("publicationDateId", publicationDateId);
		qry.setParameter("constituencyId", constituencyId);
		qry.setParameterList("voterIds",voterIds);
		
		return qry.list();
     }
	
	public List<Object[]> getpanchayts(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("Select distinct model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}

	public List<Object[]> getConstiPanchayatVoters(Long constituencyId,Long publicationId,Set<Long> partialPanchatyats,boolean isYoungVoters){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct bpv.voter.voterId),bpv.booth.panchayat.panchayatId from BoothPublicationVoter bpv where bpv.booth.constituency.constituencyId = :constituencyId and bpv.booth.publicationDate.publicationDateId = :publicationId ");
		if(partialPanchatyats != null){
			queryStr.append(" and bpv.booth.panchayat.panchayatId not in (:partialPanchatyats) ");
		}
		if(isYoungVoters){
			queryStr.append(" and bpv.voter.age >=18 and  bpv.voter.age <=22 ");
		}
		queryStr.append(" and bpv.booth.panchayat.panchayatId is not null group by bpv.booth.panchayat.panchayatId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationId",publicationId);
		if(partialPanchatyats != null){
			query.setParameterList("partialPanchatyats",partialPanchatyats);
		}
		return query.list();
	}
	
	public List<Object[]> getConstiPanchayatVotersForPartial(Long constituencyId,Long publicationId,Set<Long> partialPanchatyats,boolean isYoungVoters){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct bpv.voter.voterId),ph.panchayat.panchayatId from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph where bpv.booth.constituency.constituencyId = :constituencyId " +
				" and bpv.booth.publicationDate.publicationDateId = :publicationId and  bpv.voter.voterId = uvd.voter.voterId and uvd.hamlet.hamletId = ph.hamlet.hamletId ");
		queryStr.append(" and ph.panchayat.panchayatId in (:partialPanchatyats) ");
		if(isYoungVoters){
			queryStr.append(" and bpv.voter.age >=18 and  bpv.voter.age <=22 ");
		}
		queryStr.append(" group by ph.panchayat.panchayatId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationId",publicationId);
		query.setParameterList("partialPanchatyats",partialPanchatyats);
		return query.list();
	}
	
	public List<Object[]> getConstiLEBVoters(Long constituencyId,Long publicationId,boolean isYoungVoters){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct bpv.voter.voterId),bpv.booth.localBody.localElectionBodyId from BoothPublicationVoter bpv where " +
				" bpv.booth.constituency.constituencyId = :constituencyId and bpv.booth.publicationDate.publicationDateId = :publicationId ");	
		if(isYoungVoters){
			queryStr.append(" and bpv.voter.age >=18 and  bpv.voter.age <=22 ");
		}
		queryStr.append(" and bpv.booth.localBody.localElectionBodyId is not null group by bpv.booth.localBody.localElectionBodyId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationId",publicationId);
		return query.list();
	}
	
	public List<Object[]> getPdfsForVotersAddress()
	{
		Query query = getSession().createSQLQuery("SELECT T.tehsil_name,P.panchayat_name,H.hamlet_name," +
				"B.part_no,BPV.serial_no,V.voter_id,V.voter_id_card_no," +
				"V.`name`,V.gender,V.age,CONCAT('#',V.house_no)," +
						"V.relative_name,V.relationship_type,UVD.mobile_no ,D.district_name " +
						"FROM panchayat P,tehsil T,booth B, District D ," +
						" booth_publication_voter BPV,voter V  LEFT OUTER JOIN user_voter_details " +
						"UVD on V.voter_id = UVD.voter_id LEFT OUTER JOIN hamlet H on " +
						"UVD.hamlet_id = H.hamlet_id WHERE T.tehsil_id = B.tehsil_id AND " +
						"P.panchayat_id = B.panchayat_id AND B.booth_id = BPV.booth_id AND " +
						"V.voter_id = BPV.voter_id AND  " +
						"B.publication_date_id = 10 AND V.age >= 55 and " +
						"B.booth_id = 332344 AND D.district_Id = T.district_id " +
						" ORDER BY B.booth_id,BPV.serial_no");
		return query.list();
	}	
	public List<Object[]>  getHamletsForPartialBooth1(Long boothId){
			
			Query query = getSession().createSQLQuery("select distinct h.hamlet_id,h.hamlet_name from partial_booth_panchayat pbp,hamlet h where " +
					" pbp.booth_id = :boothId and pbp.hamlet_id = h.hamlet_id") ;
			query.setParameter("boothId", boothId);
			
			return query.list();
		}
	 
	 public List<Object[]> getPartialBoothHamlets1(Long panchayatId,Long publicationId){
					
			Query query = getSession().createQuery("select distinct ph.hamlet.hamletId, " +
					"ph.hamlet.hamletName from PanchayatHamlet ph,PartialBoothPanchayat pbp " +
					"where pbp.booth.panchayat.panchayatId = :panchayatId " +
					" and pbp.booth.publicationDate.publicationDateId = :publicationId" +
					" and pbp.panchayat.panchayatId = ph.panchayat.panchayatId ");
			query.setParameter("panchayatId", panchayatId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
	 
	 public List<Object[]> getPartialBoothHamlets2(Long panchayatId,Long publicationId){
		 
			Query query = getSession().createQuery("select distinct pbp.hamlet.hamletId," +
					"pbp.hamlet.hamletName from PartialBoothPanchayat pbp " +
					"where pbp.booth.boothId in (select model.boothId from Booth model where " +
					"model.panchayat.panchayatId = :panchayatId and " +
					"model.publicationDate.publicationDateId = :publicationId)" +
					" and pbp.hamlet.hamletId is not null");
			query.setParameter("panchayatId", panchayatId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
	 
	 public List<Object[]> getPartialBoothHamlet(Long panchayatId,Long publicationId){
		 
			Query query = getSession().createQuery("select distinct pbp.description," +
		        		"pbp.booth.panchayat.panchayatName,pbp.panchayat.panchayatName,pbp.hamlet.hamletName," +
		        		"pbp.booth.partNo,pbp.booth.panchayat.panchayatId,pbp.booth.boothId," +
		        		"pbp.booth.panchayat.panchayatId from PartialBoothPanchayat pbp " +
						"where pbp.booth.boothId in (select model.boothId from Booth model where " +
						"model.panchayat.panchayatId = :panchayatId and " +
						"model.publicationDate.publicationDateId = :publicationId)" +
						" and pbp.hamlet.hamletId is not null");
			query.setParameter("panchayatId", panchayatId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
	
	public List<Object[]> getTotalVotersOfBoothByConstituencyId(Long constituencyId,Long publicationDateId){
			Query query = getSession().createQuery("select count(distinct model.voter.voterId),model.booth.partNo from BoothPublicationVoter model where model.booth.constituency.constituencyId=:constituencyId" +
					" and model.booth.publicationDate.publicationDateId=:publicationDateId group by model.booth.partNo order by model.booth.boothId asc");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
		
	}
	
	public Long getMaxSerialNoOfABooth(Long boothId)
	{
		Query query = getSession().createQuery("select max(model.serialNo) from BoothPublicationVoter model where model.booth.boothId = :boothId");
		query.setParameter("boothId",boothId);
		return (Long) query.uniqueResult();
	}
	public Long getVoterCountForToPublication(Long constituencyId,Long publicationDateId){
		Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model where model.booth.constituency.constituencyId=:constituencyId and model.booth.publicationDate.publicationDateId=:publicationDateId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return (Long)query.uniqueResult();
	
    }
	
	public List<Object[]> getConstituencyDetails(Long publicationDateId,Long constituencyId,String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(distinct model.voter.voterId),");
		if(type.equalsIgnoreCase("constituency"))
			queryStr.append("model.booth.constituency.constituencyId,model.booth.constituency.name,");
		if(type.equalsIgnoreCase("mandal"))
			queryStr.append("model.booth.tehsil.tehsilId,model.booth.tehsil.tehsilName,");
		if(type.equalsIgnoreCase("panchayat"))
			queryStr.append("model.booth.tehsil.tehsilId,model.booth.tehsil.tehsilName,model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName,");
		if(type.equalsIgnoreCase("booth"))
			queryStr.append("model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName,model.booth.boothId,model.booth.partNo,");
		if(type.equalsIgnoreCase("muncipality"))
			queryStr.append("model.booth.localBody.localElectionBodyId,booth.localBody.name,");
		if(type.equalsIgnoreCase("muncipalityBooth"))
			queryStr.append("model.booth.localBody.localElectionBodyId,booth.localBody.name,model.booth.boothId,model.booth.partNo,");
		queryStr.append("avg(model.voter.age) from BoothPublicationVoter model where " +
				"model.booth.constituency.constituencyId=:constituencyId and " +
				"model.booth.publicationDate.publicationDateId=:publicationDateId ");
		
		if(type.equalsIgnoreCase("mandal"))
			queryStr.append(" and model.booth.localBody is null group by model.booth.tehsil.tehsilId");
		if(type.equalsIgnoreCase("muncipality"))
			queryStr.append(" and model.booth.localBody is not null group by model.booth.localBody.localElectionBodyId");
		if(type.equalsIgnoreCase("panchayat"))
			queryStr.append("group by model.booth.tehsil.tehsilId,model.booth.panchayat.panchayatId");
		if(type.equalsIgnoreCase("booth"))
			queryStr.append("group by model.booth.panchayat.panchayatId,model.booth.boothId");
		if(type.equalsIgnoreCase("muncipalityBooth"))
			queryStr.append("group by model.booth.localBody.localElectionBodyId,model.booth.boothId");
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	
	public List<Object[]> getCasteCount(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select bpv.booth.panchayat.panchayatId,uvd.casteState.casteStateId,count(distinct uvd.voter.voterId) from BoothPublicationVoter bpv,UserVoterDetails uvd where " +
				" bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationId and bpv.booth.panchayat.panchayatId is not null and bpv.voter.voterId = uvd.voter.voterId ");
				if(panchayatIds != null && panchayatIds.size() > 0){
					queryStr.append(" and bpv.booth.panchayat.panchayatId not in(:panchayatIds)");
				}
				if(ageFrom != null){
					queryStr.append(" and uvd.voter.age >=:ageFrom ");
				}
				if(ageTo != null){
					queryStr.append(" and uvd.voter.age <=:ageTo ");
				}
				queryStr.append("  group by bpv.booth.panchayat.panchayatId,uvd.casteState.casteStateId");
				
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationId", publicationId);
	    if(panchayatIds != null && panchayatIds.size() > 0){
	      query.setParameterList("panchayatIds", panchayatIds);
	    }
	    if(ageFrom != null){
	      query.setParameter("ageFrom", ageFrom);
	    }
	    if(ageTo != null){
	      query.setParameter("ageTo", ageTo);
	    }
	    //query.setParameterList("casteStateIds", casteStateIds);
	return query.list();
	}
	
	public List<Object[]> getCasteCountForPartial(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select ph.panchayat.panchayatId,uvd.casteState.casteStateId,count(distinct uvd.voter.voterId) from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph where " +
				" bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationId  and bpv.booth.panchayat.panchayatId is not null  and bpv.voter.voterId = uvd.voter.voterId and uvd.hamlet.hamletId = ph.hamlet.hamletId " +
				"  and ph.panchayat.panchayatId in(:panchayatIds) ");
				if(ageFrom != null){
					queryStr.append(" and uvd.voter.age >=:ageFrom ");
				}
				if(ageTo != null){
					queryStr.append(" and uvd.voter.age <=:ageTo ");
				}
				queryStr.append("  group by ph.panchayat.panchayatId,uvd.casteState.casteStateId");
				
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationId", publicationId);
	    query.setParameterList("panchayatIds", panchayatIds);
	    if(ageFrom != null){
	      query.setParameter("ageFrom", ageFrom);
	    }
	    if(ageTo != null){
	      query.setParameter("ageTo", ageTo);
	    }
	    //query.setParameterList("casteStateIds", casteStateIds);
	return query.list();
	}
	
	public List<Object[]> getTotalVotersByAge(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select bpv.booth.panchayat.panchayatId,count(distinct bpv.voter.voterId),bpv.booth.panchayat.panchayatName from BoothPublicationVoter bpv where " +
				" bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationId   and bpv.booth.panchayat.panchayatId is not null  ");
				if(panchayatIds != null && panchayatIds.size() > 0){
					queryStr.append(" and bpv.booth.panchayat.panchayatId not in(:panchayatIds)");
				}
				if(ageFrom != null){
					queryStr.append(" and bpv.voter.age >=:ageFrom ");
				}
				if(ageTo != null){
					queryStr.append(" and bpv.voter.age <=:ageTo ");
				}
				queryStr.append("  group by bpv.booth.panchayat.panchayatId");
				
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationId", publicationId);
	    if(panchayatIds != null && panchayatIds.size() > 0){
	      query.setParameterList("panchayatIds", panchayatIds);
	    }
	    if(ageFrom != null){
	      query.setParameter("ageFrom", ageFrom);
	    }
	    if(ageTo != null){
	      query.setParameter("ageTo", ageTo);
	    }
	return query.list();
	}
	
	public List<Object[]> getTotalVotersCountForPartial(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select ph.panchayat.panchayatId,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph where " +
				" bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationId and bpv.voter.voterId = uvd.voter.voterId and uvd.hamlet.hamletId = ph.hamlet.hamletId  and bpv.booth.panchayat.panchayatId is not null " +
				"  and ph.panchayat.panchayatId in(:panchayatIds) ");
				if(ageFrom != null){
					queryStr.append(" and uvd.voter.age >=:ageFrom ");
				}
				if(ageTo != null){
					queryStr.append(" and uvd.voter.age <=:ageTo ");
				}
				queryStr.append("  group by ph.panchayat.panchayatId");
				
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationId", publicationId);
	    query.setParameterList("panchayatIds", panchayatIds);
	    if(ageFrom != null){
	      query.setParameter("ageFrom", ageFrom);
	    }
	    if(ageTo != null){
	      query.setParameter("ageTo", ageTo);
	    }
	return query.list();
	}
	
	public List<Object[]> getTotalVotersByAgeForMunicipality(Long publicationId,Long constituencyId,Long ageFrom,Long ageTo){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select bpv.booth.localBody.localElectionBodyId,count(distinct bpv.voter.voterId),concat(bpv.booth.localBody.name,' ',bpv.booth.localBody.electionType.electionType) from BoothPublicationVoter bpv where " +
				" bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationId   and bpv.booth.localBody.localElectionBodyId is not null  ");
				
				if(ageFrom != null){
					queryStr.append(" and bpv.voter.age >=:ageFrom ");
				}
				if(ageTo != null){
					queryStr.append(" and bpv.voter.age <=:ageTo ");
				}
				queryStr.append("  group by bpv.booth.localBody.localElectionBodyId ");
				
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationId", publicationId);

	    if(ageFrom != null){
	      query.setParameter("ageFrom", ageFrom);
	    }
	    if(ageTo != null){
	      query.setParameter("ageTo", ageTo);
	    }
	return query.list();
	}
	
	public List<Object[]> getCasteCountForMunicipality(Long publicationId,Long constituencyId,Long ageFrom,Long ageTo){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select bpv.booth.localBody.localElectionBodyId,uvd.casteState.casteStateId,count(distinct uvd.voter.voterId) from BoothPublicationVoter bpv,UserVoterDetails uvd where " +
				" bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationId and bpv.booth.localBody.localElectionBodyId is not null and bpv.voter.voterId = uvd.voter.voterId ");
				
				if(ageFrom != null){
					queryStr.append(" and uvd.voter.age >=:ageFrom ");
				}
				if(ageTo != null){
					queryStr.append(" and uvd.voter.age <=:ageTo ");
				}
				queryStr.append("  group by bpv.booth.localBody.localElectionBodyId,uvd.casteState.casteStateId");
				
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationId", publicationId);
	   
	    if(ageFrom != null){
	      query.setParameter("ageFrom", ageFrom);
	    }
	    if(ageTo != null){
	      query.setParameter("ageTo", ageTo);
	    }
	    //query.setParameterList("casteStateIds", casteStateIds);
	return query.list();
	}
	
	public List<Object[]> getPanchayatwiseImpFamiles(Long publicationId,Long panchayatId)
	{
		Query query = getSession().createQuery("select B.boothId, B.partNo , V.houseNo,B.panchayat.panchayatName ,B.panchayat.panchayatId " +
				" from     BoothPublicationVoter BPV, Booth B,Voter V where B.panchayat.panchayatId = :panchayatId " +
				" and B.publicationDate.publicationDateId = :publicationId  and B.boothId = BPV.booth.boothId and " +
				" BPV.voter.voterId = V.voterId  and V.houseNo not in ('0','0-0','00-0','0-00','N.A.','-','--','000','00','N.A','NL','0-0-0')" +
				" group by  B.boothId , V.houseNo having count(V.voterId) >= 5  order by count(V.voterId) desc  ");
		query.setParameter("publicationId", publicationId);
		query.setParameter("panchayatId", panchayatId);
		return query.list();
	}
	
	public List<Object[]> getImpFamilesForMuncipality(Long publicationId,Long muncipalityId)
	{
		Query query = getSession().createQuery("select B.boothId, B.partNo , V.houseNo,B.localBody.name ,B.localBody.localElectionBodyId " +
				" from   BoothPublicationVoter BPV, Booth B,Voter V where B.localBody.localElectionBodyId = :muncipalityId " +
				" and B.publicationDate.publicationDateId = :publicationId  and B.boothId = BPV.booth.boothId and " +
				" BPV.voter.voterId = V.voterId  and V.houseNo not in ('0','0-0','00-0','0-00','N.A.','-','--','000','00','N.A','NL','0-0-0')" +
				" group by  B.boothId , V.houseNo having count(V.voterId) >= 5  order by count(V.voterId) desc  ");
		query.setParameter("publicationId", publicationId);
		query.setParameter("muncipalityId", muncipalityId);
		return query.list();
	}
	
	
	public List<Object[]> getElderPersonDetails(Long publicationId,Long boothId,String houseNo)
	{
		Query query = getSession().createSQLQuery("select UVD.caste_state_id,V.name,V.gender,V.age,V.voter_id_card_no from booth_publication_voter BPV, booth B," +
				" voter V left join user_voter_details UVD on UVD.voter_id = V.voter_id and UVD.user_id = 1" +
				" where  B.booth_id = :boothId and V.house_no = :houseNo " +
				"and B.publication_date_id = :publicationId and B.booth_id = BPV.booth_id and" +
				"  BPV.voter_id = V.voter_id and V.house_no not in ('0','0-0','00-0','0-00','N.A.','-','--','000','00','0-0-0','N.A','NL') order by V.age desc");
		
		query.setParameter("publicationId", publicationId);
		query.setParameter("boothId", boothId);
		query.setParameter("houseNo", houseNo);
		return query.list();
	}
	
	public List<Object[]> getVoterDetaildsByBoothWise(Long boothId)
	{
		Query query = getSession().createSQLQuery("select B.part_no,B.address,V.name,V.voter_id_card_no," +
				"BPV.serial_no,V.gender,V.age, V.house_no,V.relationship_type,V.relative_name  " +
				"from  booth_publication_voter BPV, Booth B,   voter V where BPV.voter_id = V.voter_id and " +
				"BPV.booth_id = B.booth_id and B.booth_id = :boothId  and B.publication_date_id = 10");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Object[]> getVoterTeluguNames(Long boothId)
	{
		Query query = getSession().createSQLQuery("select V.voter_id_card_no ," +
				" VN.firstname from voter_names VN , booth_publication_voter BPV ,Booth B , " +
				"voter V   where BPV.voter_id = VN.voter_id and BPV.booth_id = B.booth_id " +
				"and BPV.voter_id = V.voter_id and B.publication_date_id  = 10 and B.booth_id = :boothId");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Object[]> searchVoterdetailsByBoothAndName(Long boothId,String searchName,String searchType,Long publicationDateId,int firstRecord,int maxResult,String queryType){
		Query query = null;
		if(queryType !=null){
			
			if(searchType.equalsIgnoreCase("booth")){
				query = getSession().createQuery("select count(BPV.voter.voterId) from BoothPublicationVoter BPV where BPV.booth.boothId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' ");
				query.setParameter("boothId", boothId);
			}			
			else if(searchType.equalsIgnoreCase("panchayat")){
				query = getSession().createSQLQuery("select count(BPV.voter.voterId) from BoothPublicationVoter BPV where  BPV.booth.panchayat.panchayatId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' ");
				query.setParameter("boothId", boothId);
			}
			
			else{
				query = getSession().createSQLQuery("select count(BPV.voter.voterId) from BoothPublicationVoter BPV where  BPV.booth.constituency.constituencyId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' ");
				query.setParameter("boothId", boothId);
			}
			query.setParameter("publicationDateId", publicationDateId);
			
			
		}else{
			
			if(searchType.equalsIgnoreCase("booth")){
				query = getSession().createQuery("select  BPV.voter.voterId,BPV.voter.name,BPV.voter.houseNo,BPV.booth.partNo,BPV.voter.voterIDCardNo,BPV.voter.relativeName from BoothPublicationVoter BPV where BPV.booth.boothId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' order by BPV.voter.name ");
				query.setParameter("boothId", boothId);
			}			
			else if(searchType.equalsIgnoreCase("panchayat")){
				query = getSession().createQuery("select BPV.voter.voterId,BPV.voter.name,BPV.voter.houseNo,BPV.booth.partNo,BPV.voter.voterIDCardNo,BPV.voter.relativeName from BoothPublicationVoter BPV where BPV.booth.panchayat.panchayatId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' order by BPV.voter.name ");
				query.setParameter("boothId", boothId);
			}
			else{
				query = getSession().createQuery("select BPV.voter.voterId,BPV.voter.name,BPV.voter.houseNo,BPV.booth.partNo,BPV.voter.voterIDCardNo,BPV.voter.relativeName from BoothPublicationVoter BPV where BPV.booth.constituency.constituencyId = :boothId  and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' order by BPV.voter.name ");
				query.setParameter("boothId", boothId);
			}
			
			query.setParameter("publicationDateId", publicationDateId);
			query.setFirstResult(firstRecord);
			query.setMaxResults(maxResult);
			
		}
		return query.list();
		
		
		}	
	public Long searchVoterdetailsByBoothAndNameCount(Long boothId,String searchName,String searchType,Long publicationDateId,int firstRecord,int maxResult,String queryType){
		Query query = null;
		
			
			if(searchType.equalsIgnoreCase("booth")){
				query = getSession().createQuery("select count(BPV.voter.voterId) from BoothPublicationVoter BPV where BPV.booth.boothId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' ");
				query.setParameter("boothId", boothId);
			}			
			else if(searchType.equalsIgnoreCase("panchayat")){
				query = getSession().createQuery("select count(BPV.voter.voterId) from BoothPublicationVoter BPV where  BPV.booth.panchayat.panchayatId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' ");
				query.setParameter("boothId", boothId);
			}
			
			else{
				query = getSession().createQuery("select count(BPV.voter.voterId) from BoothPublicationVoter BPV where  BPV.booth.constituency.constituencyId = :boothId and " +
						" BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.voter.name like '%"+searchName+"%' ");
				query.setParameter("boothId", boothId);
			}
			query.setParameter("publicationDateId", publicationDateId);
			
			
		   return (Long)query.uniqueResult();
		
		
		}	
	
	public List<Object[]> getVotersDetailsForConstId(Long constId, Long publicationDateId) {
	
		Query query = getSession()
				.createQuery("select " +
						"BPV.voter.voterId,BPV.voter.relationshipType," +
						"BPV.voter.relativeName,BPV.voter.gender , " +
						"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name " +
						" from " +
						"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
						" BPV.booth.constituency.constituencyId = :constId order by BPV.voter.name ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constId", constId);
		return query.list();
	}
	
	public List<Object[]> getDetailsByVoterIdCardNo(String voterIdCard,Long publicationId)
	{
		Query query = getSession().createQuery("select model.booth.boothId, model.booth.partNo,model.voter.voterId,model.booth.constituency.constituencyId" +
				" ,model.booth.constituency.district.districtId,model.voter.name,model.voter.age,model.voter.relationshipType,model.voter.relativeName,model.voter.gender from BoothPublicationVoter model where model.voter.voterIDCardNo = :voterIdCard and model.booth.publicationDate.publicationDateId = :publicationId");
		query.setParameter("voterIdCard", voterIdCard);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Long> getBoothIdsOfVoterIds(List<Long> voterIds,Long publicationDateId){
		Query query = getSession().createQuery(" select distinct model.booth.boothId from BoothPublicationVoter model" +
				" where model.voter.voterId in (:voterIds) and model.booth.publicationDate.publicationDateId = :publicationDateId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
		
	}
	
	public List<Object[]> getBoothsOfVoterIds(List<Long> voterIds,Long publicationDateId){
		Query query = getSession().createQuery(" select distinct model.voter.voterId, model.booth.boothId from BoothPublicationVoter model" +
				" where model.voter.voterId in (:voterIds) and model.booth.publicationDate.publicationDateId = :publicationDateId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
		
	}
	
	
public List<Object[]> getVotersDetailsForConstIdBasedOnName(Long constId, Long publicationDateId,String voterName) {
		
		Query query = getSession()
				.createQuery("select " +
						"BPV.voter.voterId,BPV.voter.relationshipType," +
						"BPV.voter.relativeName,BPV.voter.gender , " +
						"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name " +
						" from " +
						"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
						" BPV.booth.constituency.constituencyId = :constId and   lower(BPV.voter.name) like lower('"+voterName+"%') order by BPV.voter.name ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constId", constId);
		return query.list();
	}
	
public List<Object[]> checkVoterInState(Long publicationDateId,String voterName,String relativeName,String gender,int age) {
		
		Query query = getSession()
				.createQuery("select " +
						"BPV.voter.voterId,BPV.voter.relationshipType," +
						"BPV.voter.relativeName,BPV.voter.gender , " +
						"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name " +
						" from " +
						"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
						"lower(BPV.voter.name)=:voterName and " +
						"lower(BPV.voter.relativeName)=:relativeName and " +
						"lower(BPV.voter.gender)=:gender and " +
						"BPV.voter.age between "+(age-4)+" and " +(age+4)+//+"and"+
						//"lower(BPV.voter.relationshipType)=:relationshipType and" +
						//"lower(BPV.voter.name)=:voterName and" +
						" order by BPV.voter.name ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("voterName", voterName);
		query.setParameter("relativeName", relativeName);
		query.setParameter("gender", gender);
		
		
		return query.list();
	}

public List<Object[]> getvoterDetailsInAPanchyat(Long panchayatId)
{
	Query query = getSession().createQuery("select model.voter.gender,model.voter.name from BoothPublicationVoter model where model.booth.constituency.constituencyId = :panchayatId and model.booth.publicationDate.publicationDateId = 10");
	query.setParameter("panchayatId",panchayatId);
	return query.list();
}

public List<Object[]> getCasteDetailsForACaste(Long constituencyId,Long publicationDateId,Long casteStateId)
{
	Query query = getSession().createQuery("select model.voter.name,model3.mobile from BoothPublicationVoter model,UserVoterDetails model2,MobileNumbers model3 where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId = :constituencyId and " +
			" model.booth.publicationDate.publicationDateId = :publicationDateId and model2.casteState.casteStateId = :casteStateId and model2.user.userId = 1 and model.voter.voterIDCardNo = model3.idcardNO and model3.mobile is not null ");
	query.setParameter("constituencyId", constituencyId);
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("casteStateId", casteStateId);
	return query.list();
}


	public List<SurveyDetailsInfo> getVotersDetailsByBoothId(Long boothId)
{
	/*	Query query = getSession().createQuery("select SDI.voter.voterId,SDI.voter.name," +
				"SDI.isCadre ,SDI.isInfluencingPeople ,SDI.casteId , SDI.localArea, SDI.hamlet,SDI.surveyUser.surveyUserId ," +
			"SDI.surveyUser.userName , SDI.surveyUser.surveyUserType.description  " +
			" from BoothPublicationVoter BPV , SurveyDetailsInfo SDI " +
			"where" +
			"BPV.booth.boothId = :boothId and" +
			"BPV.voter.voterId = SDI.voter.voterId and " +
				"BPV.booth.publicationDate.publicationDateId = :publicationDateId");*/
		
		Query query = getSession().createQuery("select SDI  " +
				" from BoothPublicationVoter BPV , SurveyDetailsInfo SDI " +
				"where " +
				"BPV.booth.boothId = :boothId and " +
				"BPV.voter.voterId = SDI.voter.voterId and " +
			"BPV.booth.publicationDate.publicationDateId = :publicationDateId");
	
		query.setParameter("publicationDateId",IConstants.VOTER_DATA_PUBLICATION_ID);
	query.setParameter("boothId", boothId);
	
	return query.list();
	


	
}

public List<Object[]> getTotalVotersByBoothsForVerfier(Long boothIds,Long publicationDate)
{
	 Query query = getSession().createQuery("select  model.voter.voterId,model.voter.houseNo,model.voter.gender,model.voter.age ,model.voter.voterIDCardNo,model.voter.name from BoothPublicationVoter model " +
				" where model.booth.boothId in (:boothIds)   ");
	 query.setParameter("boothIds", boothIds);
	 return query.list();
}

public List<Object[]> getTotalVotersByBoothsForVerfierForCTP(Long boothId)
{
	 Query query = getSession().createQuery("select model.voter.voterId,model.voter.houseNo,model.voter.gender,model.voter.age ,model.voter.voterIDCardNo,model.voter.name from BoothPublicationVoter model " +
				" where model.booth.boothId = :boothId ");
	 query.setParameter("boothId", boothId);
	 return query.list();
}

public List<Object[]> getAllBoothsInConstituency(Long constId,Long publicationDateId)
{
	Query query = getSession()
			.createQuery("select booth.partNo,booth.boothId  from Booth booth where booth.constituency.constituencyId = :constId  and booth.publicationDate.publicationDateId = :publicationDateId  order by booth.partNo  ");
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("constId", constId);
	return query.list();
	
}

public List<Object[]> getVotersDetailsForBoothByPublicationIdAndBoothId(Long boothId, Long publicationDateId,int min,int max) {

	Query query = getSession()
			.createQuery("select " +
					"BPV.voter.voterId,BPV.voter.relationshipType," +
					"BPV.voter.relativeName,BPV.voter.gender , " +
					"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name " +
					" from " +
					"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.constituency.constituencyId = :constId " );
					//" BPV.booth.boothId = :boothId");
	
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("constId", boothId);
	query.setFirstResult(min);
	query.setMaxResults(max);

	return query.list();
}
//get all constituencies in a 
//getVoterIds for setOfBooths

public List<Object[]> getVotersDetailsByPublicationIdAndBoothIds(List<?> boothIds, Long publicationDateId,Long constId) {

	Query query = getSession()
			.createQuery("select " +
					"BPV.voter.voterId,BPV.voter.relationshipType," +
					"BPV.voter.relativeName,BPV.voter.gender , " +
					"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name,BPV.booth.boothId,BPV.booth.partNo " +
					" from " +
					"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.constituency.constituencyId = :constId and " +//);
					" BPV.booth.partNo in(:boothIds) group by BPV.voter.voterId order by BPV.voter.name " );
	
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("constId", constId);
	query.setParameterList("boothIds", boothIds);
	/*query.setFirstResult(min);
	query.setMaxResults(max);*/

	return query.list();
}
public List<Object[]> getVotersDetailsByPublicationIdAndAvoidingBoothIds(List<?> boothIds, Long publicationDateId,Long constId) {

	Query query = getSession()
			.createQuery("select " +
					"BPV.voter.voterId,BPV.voter.relationshipType," +
					"BPV.voter.relativeName,BPV.voter.gender , " +
					"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name,BPV.booth.boothId,BPV.booth.partNo " +
					" from " +
					"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.constituency.constituencyId = :constId and " +//);
					" BPV.booth.partNo not in(:boothIds) group by BPV.voter.voterId order by BPV.voter.name " );
	
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("constId", constId);
	query.setParameterList("boothIds", boothIds);
	/*query.setFirstResult(min);
	query.setMaxResults(max);*/

	return query.list();
}
public List<Object[]> getVotersDetailsByPublicationIdAndCOnstituencyIds(Long publicationDateId,Long constId) {

	Query query = getSession()
			.createQuery("select " +
					"BPV.voter.voterId,BPV.voter.relationshipType," +
					"BPV.voter.relativeName,BPV.voter.gender , " +
					"BPV.voter.age,BPV.voter.voterIDCardNo,BPV.voter.name,BPV.booth.boothId " +
					" from " +
					"BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and  BPV.booth.constituency.constituencyId = :constId  " +//);
					"  group by BPV.voter.voterId order by BPV.voter.name " );
	
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("constId", constId);
	//query.setParameterList("boothIds", boothIds);
	/*query.setFirstResult(min);
	query.setMaxResults(max);*/

	return query.list();
}

public List<Long> getAllVoterIdsByBoothIdsAndPublicationDateId(List<Long> boothIds , Long publicationDateId)
{
	
	Query query = getSession().createQuery("select BPV.voter.voterId from BoothPublicationVoter BPV where " +
			"BPV.booth.boothId in(:boothIds) and BPV.booth.publicationDate.publicationDateId  = :publicationDateId");
	
	query.setParameterList("boothIds", boothIds);
	query.setParameter("publicationDateId", publicationDateId);
	
	return query.list();
}

public List<Object[]> getBoothIdsDetailsOfVoterIds(List<Long> voterIds,Long publicationDateId){
	Query query = getSession().createQuery(" select  model.booth.partNo ,model.voter.voterId,model.booth.boothId from BoothPublicationVoter model" +
			" where model.voter.voterId in (:voterIds) and model.booth.publicationDate.publicationDateId = :publicationDateId");
	
	query.setParameterList("voterIds", voterIds);
	query.setParameter("publicationDateId", publicationDateId);
	return query.list();
}

public List<Object[]> getLatestBoothDetailsOfConstituency(Long constituencyId)
{
	
	Query query = getSession().createQuery("select B.boothId,B.partNo from Booth B where B.constituency.constituencyId = :constituencyId and " +
			"B.publicationDate.publicationDateId = :publicationDateId order by B.boothId");
	
	query.setParameter("constituencyId", constituencyId);
	query.setParameter("publicationDateId" ,IConstants.VOTER_DATA_PUBLICATION_ID);
	
	return query.list();
	
}

	public List<Object[]> getBoothWiseCasteDetails(Long boothId){
		Query query = getSession().createQuery(" select CS.casteStateId, C.casteName, count(distinct UVD.voter.voterId) from BoothPublicationVoter BPV ,UserVoterDetails UVD ,CasteState CS, Caste C   where " +
				" BPV.voter.voterId = UVD.voter.voterId and UVD.casteState.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId  and " +
				" UVD.user.userId = 1 and BPV.booth.boothId = :boothId and UVD.casteState != null   group by UVD.casteState.casteStateId order by " +
				" count(UVD.voter.voterId) desc ");
		
		query.setParameter("boothId", boothId);
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		return query.list();
	}
	
	public List<Object[]> getBoothWiseVoterDetails(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select distinct model.booth.boothId , count(model.booth.boothId) from BoothPublicationVoter model where model.booth.boothId in (:boothIds) group  by model.booth.boothId");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}

	public List<Object[]> getTotalVoters(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.booth.boothId ,model.booth.partNo, count(model.booth.boothId) from BoothPublicationVoter model where model.booth.boothId in (:boothIds)and model.booth.publicationDate.publicationDateId = :publicationId group  by model.booth.boothId");
		query.setParameterList("boothIds", boothIds);
		query.setParameter("publicationId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();
	}
	public Long getTotalVotersForPanchayat(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select count(model.booth.boothId) from BoothPublicationVoter model where model.booth.boothId in (:boothIds) and model.booth.publicationDate.publicationDateId = :publicationId");
		query.setParameterList("boothIds", boothIds);
		query.setParameter("publicationId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getVoterDetailsByBoothID(List<Long> boothId)
	{
		Query query = getSession().createQuery("select model.voter.voterId ," +
				" model.voter.name," +
				" model.voter.voterIDCardNo," +
				" model.voter.houseNo," +
				" model.boothId," +
				" model.voter.relativeName ," +
				" model.booth.partNo " +
				"  , model.booth.partNo " + 
			//	" ,model.booth.panchayat.panchayatName  " +
				" from  BoothPublicationVoter model " +
				" where model.boothId in (:boothId)");
		query.setParameterList("boothId", boothId);
		return query.list();
	}
	
	public Long getTotalVotersForConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select count( distinct model.voter.voterId) from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
		query.setParameter("constituencyId", constituencyId);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getTotalVotersForAllConstituencies(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,model.booth.constituency.name,count(distinct model.voter.voterId),count(distinct model.booth.boothId) from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationId and model.booth.constituency.constituencyId in( :constituencyIds) group by model.booth.constituency.constituencyId");
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("publicationId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();
	}
	
	public Long getTotalVotersForBoothId(Long boothId)
	{
		Query query = getSession().createQuery("select count( distinct model.voter.voterId) from BoothPublicationVoter model where model.booth.boothId = :boothId ");
		query.setParameter("boothId", boothId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getTotalVotersForConstituencyByBoothWise(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.booth.boothId, model.booth.partNo,count( distinct model.voter.voterId) from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" group by  model.booth.boothId order by cast(model.booth.partNo , int)");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getTotalVotersAndBoothTypeForConstituencyByBoothWise(Long constituencyId){
		Query query = getSession().createQuery("select model.booth.boothId, model.booth.partNo,count( distinct model.voter.voterId)," +
				" model.booth.tehsil.tehsilId," +
				" model.booth.localBody.localElectionBodyId" +
				" from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" group by  model.booth.boothId order by cast(model.booth.partNo , int)");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getConstyPublicationIdByVoterId1(String voterCardNo){
		Query query=getSession().createQuery("select model.booth.constituency.constituencyId,model.booth.boothId,model.booth.publicationDate.publicationDateId," +
				" model.voter.voterId,model.voter.name,model.voter.age,model.voter.gender,model.booth.constituency.district.districtId,model.voter.relativeName,model.voter.houseNo " +
				" from BoothPublicationVoter model where model.voter.voterIDCardNo =:voterCardNo and model.booth.publicationDate.publicationDateId =:publicationDateId ");
		
		query.setParameter("voterCardNo", voterCardNo);
		query.setParameter("publicationDateId",IConstants.HH_KUPPAM_PUBLICATION_ID);
		return query.list();
	}	
	
	public Long getTotalVoterByBooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select count(*) from BoothPublicationVoter model where model.boothId in (:boothIds)");
		query.setParameterList("boothIds", boothIds);
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getBoothWiseTotalVotersByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery(
				"select count(distinct BPV.voter.voterId),BPV.booth.boothId,BPV.booth.partNo from " +
				"BoothPublicationVoter BPV where BPV.booth.constituency.constituencyId = :constituencyId and " +
				"BPV.booth.isCtpUsed = 'Y' group by BPV.booth.boothId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List getVotersDetailsForCadreRegistratiobByconstituencId(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villageCovered,Integer startIndex,Integer maxIndex)
	{
		
		StringBuilder queryStr1 = new StringBuilder();
		
		queryStr1.append(" select BPV.voter.voterId,BPV.voter.name, BPV.voter.relativeName, BPV.voter.age, BPV.voter.houseNo, BPV.voter.relationshipType, BPV.voter.gender, BPV.voter.voterIDCardNo, ");
		queryStr1.append("   BPV.booth.partNo,BPV.voter.imagePath from BoothPublicationVoter BPV where "+queryStr+"   ");
		queryStr1.append(" BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDate ");
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				queryStr1.append(" and BPV.booth.panchayat.panchayatId = :id ");
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				queryStr1.append(" and BPV.booth.localBody.localElectionBodyId = :id ");
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			queryStr1.append(" and BPV.booth.boothId = :boothId ");
		}
		
		/*if(villageCovered != null && villageCovered.trim().length()>0 && Long.valueOf(villageCovered.trim()))
		{
			queryStr1.append(" and BPV.booth.villagesCovered like '%"+villageCovered+"%' ");
		}*/
		
		Query query = getSession().createQuery(queryStr1.toString());
			
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDate", publicationDate);
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}
		if(startIndex != null && maxIndex != null){
		  query.setFirstResult(startIndex);
		  query.setMaxResults(maxIndex);
		}
		return query.list();	
		
	}
	
	public Long getVotersDetailsForCadreRegistratiobByconstituencIdCount(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villageCovered)
	{
		
		StringBuilder queryStr1 = new StringBuilder();
		
		queryStr1.append(" select count(*) from BoothPublicationVoter BPV where "+queryStr+"   ");
		queryStr1.append(" BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDate ");
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				queryStr1.append(" and BPV.booth.panchayat.panchayatId = :id ");
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				queryStr1.append(" and BPV.booth.localBody.localElectionBodyId = :id ");
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			queryStr1.append(" and BPV.booth.boothId = :boothId ");
		}
		
		/*if(villageCovered != null && villageCovered.trim().length()>0 && Long.valueOf(villageCovered.trim()))
		{
			queryStr1.append(" and BPV.booth.villagesCovered like '%"+villageCovered+"%' ");
		}*/
		
		Query query = getSession().createQuery(queryStr1.toString());
			
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDate", publicationDate);
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}
		
		return (Long)query.uniqueResult();	
		
	}
	
	public List<Object[]> getCTPVoterDetailsByBooth(Long boothId)
	{
		
		Query query = getSession().createQuery("select BPV.voter.voterId,BPV.serialNo, BPV.voter.houseNo ,BPV.voter.name ,BPV.voter.relativeName,BPV.voter.voterIDCardNo  " +
				" from  BoothPublicationVoter BPV where BPV.booth.boothId = :boothId ");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Booth> getVoterAddressDetails(Long voterId)
	{
		Query query = getSession().createQuery("select BPV.booth from BoothPublicationVoter BPV  where BPV.voter.voterId = :voterId and BPV.booth.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("voterId", voterId);
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();
	}
	
	//0voterId,1name,2relativeName,3relationshipType,4gender,5age,6voterIDCardNo,7relativeName
	public List<Object[]> getFamilyDetaislByHouseNoAndBoothId(Long boothId,String houseNo)
	{
		Query query = getSession().createQuery("select model.voter.voterId,model.voter.name, model.voter.relativeName, model.voter.relationshipType, model.voter.gender, model.voter.age, " +
				" model.voter.voterIDCardNo,model.voter.relativeName from BoothPublicationVoter model " +
				" where model.booth.boothId = :boothId and model.voter.houseNo = :houseNo order by model.voter.age desc");
		query.setParameter("boothId",boothId);
		query.setParameter("houseNo",houseNo);
		return query.list();
	}
	
   public List<Object[]> getVotersDetailsByCTPSearchCriteria(Long publicationDateId,Long id,String queryString,Long userId)
   {
	   StringBuilder str = new StringBuilder();
	   str.append("select uvd.voter,model.booth.boothId,model.booth.partNo,model.serialNo,uvd.casteState.caste.casteName from BoothPublicationVoter model,UserVoterDetails uvd " +
	   		" where model.voter.voterId = uvd.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString);
	   str.append(" and uvd.user.userId = :userId and uvd.casteInsertType.casteInsertTypeId = :casteInsertTypeId");
	   Query query = getSession().createQuery(str.toString());
	   		query.setParameter("id", id);
	   		query.setParameter("userId", userId);
	   		query.setParameter("publicationDateId", publicationDateId);
	   		query.setParameter("casteInsertTypeId", IConstants.CTP_CASTE_INSERT_TYPE);
	   		return query.list();
   }
   public List<String> getPartNo(Long constituencyId,Long voterId){
	   Query query = getSession().createQuery("select model.booth.partNo from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" " +
	   		" and model.voter.voterId =:voterId ");
	   query.setParameter("constituencyId", constituencyId);
  		query.setParameter("voterId", voterId);
	   return query.list();
   }
   
   public List<String> getPartNoForRTCRegistration(Long constituencyId,Long voterId){
	   Query query = getSession().createQuery("select model.booth.partNo from BoothPublicationVoter model where model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" " +
	   		" and model.voter.voterId =:voterId ");
	   query.setParameter("constituencyId", constituencyId);
  		query.setParameter("voterId", voterId);
	   return query.list();
   }
   
	public List<Object[]> getLocationWiseVoterAgeRangeCount(List<Long> locationIdsList,String locationType,Long publicationDateId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.voter.voterId),model.voter.voterAgeRange.voterAgeRangeId,model.voter.voterAgeRange.ageRange ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		 str.append(",model.booth.constituency.constituencyId ");
		else if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" ,model.booth.constituency.district.districtId " );
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		 str.append(",model.booth.tehsil.tehsilId ");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			 str.append(",model.booth.localBody.localElectionBodyId ");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		 str.append(",model.booth.panchayat.panchayatId ");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
		 str.append(",model.booth.boothId  ");
		
		
		
		str.append(" from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId =:publicationDateId ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		 str.append(" and model.booth.constituency.district.districtId in (:locationIdsList) group by model.booth.constituency.constituencyId ");
		else if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append("  and model.booth.constituency.district.districtId in (:locationIdsList) group by model.booth.constituency.district.districtId" );
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			str.append(" and model.booth.tehsil.tehsilId in (:locationIdsList) group by model.booth.tehsil.tehsilId ");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			str.append(" and model.booth.localBody.localElectionBodyId in(:locationIdsList) group by model.booth.localBody.localElectionBodyId ");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			str.append(" and model.booth.panchayat.panchayatId in(:locationIdsList) and model.booth.localBody is null group by model.booth.panchayat.panchayatId ");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			str.append(" and model.booth.boothId in(:locationIdsList) group by model.booth.boothId ");
		
		
		str.append(",model.voter.voterAgeRange.voterAgeRangeId ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("locationIdsList", locationIdsList);
		
		return query.list();
	}
   
	public List<Long> getVoterIdsInABoothOfGivenHouseNos(Long constituencyId,Long publicationDateId,String partNo,List<String> houseNos)
	{
		Query query = getSession().createQuery("select model.voter.voterId from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationDateId and model.booth.partNo = :partNo and model.voter.houseNo in(:houseNos)");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("partNo",partNo);
		query.setParameterList("houseNos",houseNos);
		return query.list();
	}
	
	public List<Object[]> getFamilyWiseVotersInABooth(Long constituencyId,Long publicationDateId,String partNo)
	{
		Query query = getSession().createQuery("select model.voter.houseNo,count(model.voter.voterId) from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationDateId and model.booth.partNo = :partNo group by model.voter.houseNo order by count(model.voter.voterId)");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("partNo",partNo);
		return query.list();
	}
  
	public List<Object[]> getVotersCountByPublicationIdLocationIds(String type,Set<Long> ids,Long publicationDateId,Long constituencyId){
		StringBuilder query = new StringBuilder();
		query.append("select count(*),model.voter.gender, ");
		
		if(type.equalsIgnoreCase("constituency")){
		  query.append("model.booth.constituency.constituencyId ");
		}else if(type.equalsIgnoreCase("mandal")){
			query.append(" model.booth.tehsil.tehsilId ");
		}else if(type.equalsIgnoreCase("localbody")){
			query.append(" model.booth.localBody.localElectionBodyId ");
		}
		
		query.append(" from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId = :publicationDateId and ");
		
		if(type.equalsIgnoreCase("constituency")){
			query.append(" model.booth.constituency.constituencyId in (:ids) ");
		}else if(type.equalsIgnoreCase("mandal")){
			query.append(" model.booth.tehsil.tehsilId  in (:ids) and model.booth.localBody is null and model.booth.constituency.constituencyId = :constituencyId ");
		}else if(type.equalsIgnoreCase("localbody")){
			query.append(" model.booth.localBody.localElectionBodyId in (:ids) and model.booth.constituency.constituencyId = :constituencyId ");
		}
		
		query.append("group by ");
		
	   if(type.equalsIgnoreCase("constituency")){
		  query.append(" model.booth.constituency.constituencyId ");
	   }else if(type.equalsIgnoreCase("mandal")){
			query.append(" model.booth.tehsil.tehsilId ");
	   }else if(type.equalsIgnoreCase("localbody")){
			query.append(" model.booth.localBody.localElectionBodyId ");
	   }
		query.append(" , model.voter.gender ");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("ids", ids);
		if(constituencyId != null)
		 queryObj.setParameter("constituencyId", constituencyId);
		return queryObj.list();
	}
	
	public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,Long locationId,Long casteStateId,String nameStr)
	{
		if(locationType != null)
		{
			StringBuilder str = new StringBuilder();
			boolean isStateWise = false;
			str.append(" select ");
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" constituency.constituencyId, count(*), constituency.name ");
			}
			else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.DISTRICT) || locationType.equalsIgnoreCase(IConstants.STATE)))
			{
				str.append(" district.districtId, count(*), district.districtName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" tehsil.tehsilId, count(*), tehsil.tehsilName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" localElectionBody.localElectionBodyId, count(*), localElectionBody.name ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" panc.panchayatId, count(*), panc.panchayatName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				//str.append(" panc.panchayatId, count(*), panc.panchayatName ");
			}
			str.append(" from BoothPublicationVoter model left join model.booth.panchayat panc ");
			str.append(" left join model.booth.tehsil tehsil ");
		    str.append(" left join model.booth.constituency constituency ");
			str.append(" left join model.booth.localBody localElectionBody ");
			str.append(" left join model.booth.constituency.district district ");
			str.append(" left join model.voter voter ");
			str.append(" where  model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append("  and  district.districtId =:locationId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				str.append("  and  district.districtId =:locationId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" and  constituency.constituencyId =:locationId and localElectionBody.localElectionBodyId is null  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" and  constituency.constituencyId =:locationId and localElectionBody.localElectionBodyId is not null  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append("  and  tehsil.tehsilId =:locationId  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				//str.append(" panc.panchayatId, count(*), panc.panchayatName ");
			}
			else if(stateId != null &&  stateId.longValue() ==0L) 	//AP & TS
			{
				isStateWise = true;
				str.append("  and  (district.districtId between 1 and 23)  ");
			}
			else if(stateId != null &&  stateId.longValue() ==1L)	//AP
			{
				isStateWise = true;
				str.append("  and  ( district.districtId between 11 and 23 ) ");
			}
			else if(stateId != null &&  stateId.longValue() ==2L)	//TS
			{
				isStateWise = true;
				str.append("  and ( district.districtId  between 1 and 10 ) ");
			}
			
			if(casteStateId != null && casteStateId.longValue() != 0L)
			{
				str.append(" and casteState.casteStateId =:casteStateId");
			}
			if(nameStr != null && nameStr.trim().length() != 0L)
			{
				str.append("  and ( voter.name like '%"+nameStr+"%' or voter.name like '%"+nameStr+"%' ) ");
			}
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" group by constituency.constituencyId order by constituency.name ");
			}
			else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.DISTRICT) || locationType.equalsIgnoreCase(IConstants.STATE)))
			{
				str.append(" group by district.districtId  order by district.districtName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" group by tehsil.tehsilId  order by tehsil.tehsilName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append("  group by localElectionBody.localElectionBodyId order by localElectionBody.name ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" group by panc.panchayatId order by panc.panchayatName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				//str.append(" panc.panchayatId, count(*), panc.panchayatName ");
			}
			
			Query query = getSession().createQuery(str.toString());
			
			if(casteStateId != null && casteStateId.longValue() != 0L)
			{
				query.setParameter("casteStateId", casteStateId);
			}			
			if(!isStateWise && (locationId != null && locationId.longValue() != 0L))
			{
				query.setParameter("locationId", locationId);
			}
		
			return query.list();
		}
		else
		{
			return null;
		}
	}
	
	public List<Object[]> getVoterCadreCasteDetailsByName(Long stateId,String locationType,Long locationId,Long casteStateId,String nameStr)
	{
		if(locationType != null)
		{
			StringBuilder str = new StringBuilder();
			boolean isStateWise = false;
			str.append(" select distinct model.voter.name,model.voter.relativeName,model.voter.gender,model.voter.age,tehsil.tehsilName, constituency.name,localElectionBody.name," +
					" district.districtName, panc.panchayatName,UVD.casteState.caste.casteName,model.booth.partNo,constituency.constituencyId,model.voter.voterIDCardNo, model.voter.voterId ");
			
			str.append(" from UserVoterDetails UVD, BoothPublicationVoter model left join model.booth.panchayat panc ");
			str.append(" left join model.booth.tehsil tehsil ");
		    str.append(" left join model.booth.constituency constituency ");
			str.append(" left join model.booth.localBody localElectionBody ");
			str.append(" left join model.booth.constituency.district district ");
			str.append(" left join model.voter ");
			str.append(" where  model.voter.voterId = UVD.voter.voterId and UVD.user.userId = 1 and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and ");
			
			if(locationId != null && locationId.longValue() != 0L && locationType != null && locationType.equalsIgnoreCase(IConstants.STATE))
			{
				str.append(" district.districtId =:locationId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" constituency.constituencyId =:locationId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				str.append(" district.districtId =:locationId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" tehsil.tehsilId =:locationId  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" localElectionBody.localElectionBodyId =:locationId  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				str.append(" localElectionBody.localElectionBodyId =:locationId  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" panc.panchayatId =:locationId ");
			}
			else if(stateId != null &&  stateId.longValue() ==0L) 	//AP & TS
			{
				isStateWise = true;
				str.append(" (district.districtId between 1 and 23)  ");
			}
			else if(stateId != null &&  stateId.longValue() ==1L)	//AP
			{
				isStateWise = true;
				str.append(" ( district.districtId between 11 and 23 ) ");
			}
			else if(stateId != null &&  stateId.longValue() ==2L)	//TS
			{
				isStateWise = true;
				str.append("  ( district.districtId  between 1 and 10 ) ");
			}
			
			if(casteStateId != null && casteStateId.longValue() != 0L)
			{
				str.append(" and UVD.casteState.casteStateId =:casteStateId");
			}
			if(nameStr != null && nameStr.trim().length() != 0L)
			{
				str.append("  and ( model.voter.name like '%"+nameStr+"%' ) ");
			}		
			
			Query query = getSession().createQuery(str.toString()+" order by UVD.casteState.caste.casteName asc ");
			
			if(casteStateId != null && casteStateId.longValue() != 0L)
			{
				query.setParameter("casteStateId", casteStateId);
			}
			if(!isStateWise && (locationId != null && locationId.longValue() != 0L))
			{
				query.setParameter("locationId", locationId);
			}
			
			return query.list();
		}
		else
		{
			return null;
		}
	}
	

	public List<Object[]> getVoterCasteWiseCountDetailsByName(Long stateId,String locationType,Long locationId,Long casteStateId,String nameStr)
	{
		if(locationType != null)
		{
			StringBuilder str = new StringBuilder();
			boolean isStateWise = false;
			str.append(" select  UVD.casteState.caste.casteName,UVD.casteState.casteStateId,count(*) ");
			
			str.append(" from UserVoterDetails UVD, BoothPublicationVoter model left join model.booth.panchayat panc ");
			str.append(" left join model.booth.tehsil tehsil ");
		    str.append(" left join model.booth.constituency constituency ");
			str.append(" left join model.booth.localBody localElectionBody ");
			str.append(" left join model.booth.constituency.district district ");
			str.append(" left join model.voter ");
			str.append(" where  model.voter.voterId = UVD.voter.voterId and UVD.user.userId = 1 and  model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"  and ");
			
			if(locationId != null && locationId.longValue() != 0L && locationType != null && locationType.equalsIgnoreCase(IConstants.STATE))
			{
				str.append(" district.districtId =:locationId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" constituency.constituencyId =:locationId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" tehsil.tehsilId =:locationId  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" localElectionBody.localElectionBodyId =:locationId  and  localElectionBody.localElectionBodyId is not null  ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" panc.panchayatId =:locationId ");
			}
			else if(stateId != null &&  stateId.longValue() ==0L && !locationType.equalsIgnoreCase(IConstants.WARD)) 	//AP & TS
			{
				isStateWise = true;
				str.append(" (district.districtId between 1 and 23)  ");
			}
			else if(stateId != null &&  stateId.longValue() ==1L && !locationType.equalsIgnoreCase(IConstants.WARD))	//AP
			{
				isStateWise = true;
				str.append(" ( district.districtId between 11 and 23 ) ");
			}
			else if(stateId != null &&  stateId.longValue() ==2L && !locationType.equalsIgnoreCase(IConstants.WARD))	//TS
			{
				isStateWise = true;
				str.append("  ( district.districtId  between 1 and 10 ) ");
			}
			
			if(casteStateId != null && casteStateId.longValue() != 0L)
			{
				str.append(" and UVD.casteState.casteStateId =:casteStateId");
			}
			if(nameStr != null && nameStr.trim().length() != 0L)
			{
				str.append("  and ( model.voter.name like '%"+nameStr+"%' ) ");
			}	
			
			str.append("  group by  UVD.casteState.casteStateId order by count(*) desc  ");
			
			Query query = getSession().createQuery(str.toString());
			
			if(casteStateId != null && casteStateId.longValue() != 0L)
			{
				query.setParameter("casteStateId", casteStateId);
			}
			if(!isStateWise && (locationId != null && locationId.longValue() != 0L))
			{
				query.setParameter("locationId", locationId);
			}
			
			return query.list();
		}
		else
		{
			return null;
		}
	}
	public List<Object[]> getVotersDetailsForCadreRegistratiobByLocationIds(Long stateId, Long constituencyId, Long publicationDate,String queryStr,
			Long tehsilId,Long boothId,Integer startIndex,Integer maxIndex){
		
		StringBuilder queryStr1 = new StringBuilder();
		//0 voterId,1name,2relative name,3age,4houseno,5relationtype,6gender,7voterIDCardNo,8path
		queryStr1.append(" select BPV.voter.voterId,BPV.voter.name, BPV.voter.relativeName, BPV.voter.age, BPV.voter.houseNo, BPV.voter.relationshipType, ");
		queryStr1.append("   BPV.voter.gender, BPV.voter.voterIDCardNo,BPV.voter.imagePath from BoothPublicationVoter BPV where "+queryStr+"   ");
		//if(stateId != null && stateId.longValue() != 29l)
		//{
			queryStr1.append(" BPV.booth.constituency.constituencyId = :constituencyId and ");
		//}
		queryStr1.append(" BPV.booth.publicationDate.publicationDateId = :publicationDate ");
		
		if(tehsilId.longValue() != 0L){
			if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				queryStr1.append(" and BPV.booth.localBody.localElectionBodyId = :id ");
			}else if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				queryStr1.append(" and BPV.booth.tehsil.tehsilId = :id and BPV.booth.localBody.localElectionBodyId is null ");
			}
		}
		
		if(boothId.longValue() != 0L){
			queryStr1.append(" and BPV.booth.boothId = :boothId ");
		}
		
		Query query = getSession().createQuery(queryStr1.toString());
		//if(stateId != null && stateId.longValue() != 29l)
		//{	
			query.setParameter("constituencyId", constituencyId);
		//}
		
		query.setParameter("publicationDate", publicationDate);
		/*
		if(tehsilId.longValue() != 0L){
	       query.setParameter("id", Long.valueOf(tehsilId.toString().substring(1)));
		}*/
		
		if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
			query.setParameter("id", Long.valueOf(tehsilId.toString().substring(1)));
		}else if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
			query.setParameter("id", Long.valueOf(tehsilId.toString().substring(1)));
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}
		if(startIndex != null && maxIndex != null){
		  query.setFirstResult(startIndex);
		  query.setMaxResults(maxIndex);
		}
		return query.list();	
		
	}
	
	public Long getVotersDetailsForCadreRegistratiobByLocationIdsCount(Long stateId,Long constituencyId, Long publicationDate,String queryStr,Long tehsilId,Long boothId){
		StringBuilder queryStr1 = new StringBuilder();
		
		queryStr1.append(" select count(*) from BoothPublicationVoter BPV where "+queryStr+"   ");
		
		//if(stateId != null && stateId.longValue() != 29L)
		//{
			queryStr1.append(" BPV.booth.constituency.constituencyId = :constituencyId and ");
		//}
		queryStr1.append(" BPV.booth.publicationDate.publicationDateId = :publicationDate ");
		
		if(tehsilId.longValue() != 0L){
			if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				queryStr1.append(" and BPV.booth.localBody.localElectionBodyId = :id ");
			}else if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				queryStr1.append(" and BPV.booth.tehsil.tehsilId = :id and BPV.booth.localBody.localElectionBodyId is null ");
			}
		}
		
		if(boothId.longValue() != 0L){
			queryStr1.append(" and BPV.booth.boothId = :boothId ");
		}
		
		Query query = getSession().createQuery(queryStr1.toString());
		//if(stateId != null && stateId.longValue() != 29L)
		//{
			query.setParameter("constituencyId", constituencyId);
		//}
		query.setParameter("publicationDate", publicationDate);
		
		/*if(tehsilId.longValue() != 0L){
			 query.setParameter("id", Long.valueOf(tehsilId.toString().substring(1)));
		 }
		*/
		if(tehsilId.longValue() != 0L){
			if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				 query.setParameter("id", Long.valueOf(tehsilId.toString().substring(1)));
			}else if(tehsilId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				 query.setParameter("id", Long.valueOf(tehsilId.toString().substring(1)));
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}
		
		return (Long)query.uniqueResult();	
		
	}
	
	public List<Booth> getOtherStateVoterAddressDetails(Long voterId)
	{
		Query query = getSession().createQuery("select BPV.booth from BoothPublicationVoter BPV  where BPV.voter.voterId = :voterId and BPV.booth.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("voterId", voterId);
		query.setParameter("publicationDateId", IConstants.OTHER_STATE_PUBLICATION_ID);
		return query.list();
	}
	
	public Object getBoothPartNumberByVoterId(String voterId){
		
		Long voterid=Long.parseLong(voterId);
		
		Query query = getSession().createQuery(" select model.booth.partNo from BoothPublicationVoter model" +
				" where  model.voter.voterId = :voterId and model.booth.publicationDate.publicationDateId="+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
		
		query.setParameter("voterId",voterid);
		return  query.uniqueResult();
		
	}
	
	public Object[] getBoothDetailsByVoterId(String voterId,Long publicationId){
		
		Long voterid=Long.parseLong(voterId);
		
		Query query = getSession().createQuery(" select model.booth.partNo,model.booth.boothId from BoothPublicationVoter model" +
				" where  model.voter.voterId = :voterId and model.booth.publicationDate.publicationDateId=:publicationId");
		
		query.setParameter("voterId",voterid);
		query.setParameter("publicationId",publicationId);
		return  (Object[]) query.uniqueResult();
		
	}

	
	public List<Object[]> getCadreVoterInfo(Long wardId,Integer firstResult,Integer maxResults)
	{
		Query query = getSession().createQuery(" select model.booth.partNo,model.serialNo," +
				" model.booth.villagesCovered,model.voter.name,model.voter.relativeName,model.voter.voterIDCardNo," +
				" model.booth.localBodyWard.name,model1.mobileNo,model.voter.relationshipType,model.voter.gender,model.booth.latitude,model.booth.longitude from BoothPublicationVoter model,TdpCadre model1" +
				" where model.voter.voterId = model1.voter.voterId and model.booth.publicationDate.publicationDateId = 17 " +
				" and model1.mobileNo is not null and length(model1.mobileNo) = 10 and model1.mobileNo <> '9999999999' and model1.mobileNo !='' " +
				" and model.booth.localBodyWard.constituencyId = :wardId and model.serialNo is not null and model.serialNo > 0 " +
				" group by model1.tdpCadreId order by model1.tdpCadreId ");
		
		query.setParameter("wardId",wardId);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);

		return query.list();
	
	}
	
	public List<Object[]> getBoothVoterDetails(Long boothId,Long voterId)
	{
		Query query = getSession().createQuery("SELECT model.serialNo,model.voter.name,model.voter.voterIDCardNo,model.voter.gender,model.voter.relativeName,model.voter.relationshipType, " +
				" model.booth.partNo,model.booth.villagesCovered,model.booth.latitude,model.booth.longitude FROM BoothPublicationVoter model WHERE model.voter.voterId = :voterId AND model.booth.boothId = :boothId");
		query.setParameter("boothId",boothId);
		query.setParameter("voterId",voterId);
		return query.list();
	}
	
	public List<Object[]> getBoothVoterDetails(Long voterId)
	{
		Query query = getSession().createQuery("SELECT model.serialNo,model.voter.name,model.voter.voterIDCardNo,model.voter.gender,model.voter.relativeName,model.voter.relationshipType, " +
				" model.booth.partNo,model.booth.villagesCovered,model.booth.latitude,model.booth.longitude FROM BoothPublicationVoter model WHERE model.voter.voterId = :voterId AND model.booth.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("publicationDateId",IConstants.LATEST_PUBLICATION_DATE_ID);
		query.setParameter("voterId",voterId);
		return query.list();
	}

	public List<Object[]> getVoterInfo(List<String> mobileNos)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.booth.partNo,model.serialNo," +
				" model.booth.villagesCovered,model.voter.name,model.voter.relativeName,model.voter.voterIDCardNo," +
				" model.booth.localBodyWard.name,model1.mobileNo,model.voter.relationshipType,model.voter.gender,model.booth.latitude,model.booth.longitude from BoothPublicationVoter model,TdpCadre model1" +
				" where model.voter.voterId = model1.voter.voterId and model.booth.publicationDate.publicationDateId=17" +
				" and model1.mobileNo is not null and length(model1.mobileNo) = 10 and model1.mobileNo <> '9999999999' and model1.mobileNo !='' ");
		if(mobileNos != null && mobileNos.size() > 0)
			str.append(" and model.mobileNo in(:mobileNos)");
			str.append(" group by model1.tdpCadreId order by model1.tdpCadreId ");
		Query query = getSession().createQuery(str.toString());
		if(mobileNos != null && mobileNos.size() > 0)
			query.setParameterList("mobileNos", mobileNos);
	
		return query.list();
	
	}
	
	public List<Object[]> getConstyPublicationIdByVoterIdPublicationId(String voterCardNo,Long publicationId){
		Query query=getSession().createSQLQuery("select distinct C.constituency_id as cid," +
				"B.booth_id as boothId," +
				"B.publication_date_id as pid," +
				"V.voter_id as voterId," +
				"V.name as voterName ," +
				"V.age as age," +
				"V.gender as gender," +
				"C.district_id as distId" +
				",V.relative_name as relativeName," +
				"V.house_no as hno," +
				"V.relationship_type as relationType," +//10
				"C.name as constName," +
				"D.district_name as distName," +
				"B.part_no as partNo," +
				"0 as zero," +//14
				" B.panchayat_id as panchayatId," +//15
				"P.panchayat_name as panchayatName," +
				"T.tehsil_id as tehsilId," +
				"T.tehsil_name as tehsilName," +
				"C.state_id as stateId," +
				"S.state_name as stateName," +//20
				" L.name as localBodyName," +
				"L.local_election_body_id as localBodyId," +
				"W.constituency_id as wardId," +//23
				"W.name as wardName" +
				" from booth_publication_voter BPV ,voter V,district D,state S,booth B left outer join tehsil T on B.tehsil_id = T.tehsil_id " +
				" left outer join panchayat P on B.panchayat_id=P.panchayat_id " +
				" left outer join constituency C on B.constituency_id=C.constituency_id " +
				" left outer join constituency W on B.ward_id=W.constituency_id " +
				" left outer join local_election_body L on B.local_election_body_id=L.local_election_body_id " +
				" where B.booth_id=BPV.booth_id and BPV.voter_id=V.voter_id " +
				" and V.voter_id_card_no=:voterCardNo and B.publication_date_id=:publicationId and C.district_id=D.district_id and" +
				" C.state_id=S.state_id and S.state_id=1")
			.addScalar("cid", Hibernate.LONG)
			.addScalar("boothId", Hibernate.LONG)
			.addScalar("pid", Hibernate.LONG)
			.addScalar("voterId", Hibernate.LONG)
			.addScalar("voterName", Hibernate.STRING)
			.addScalar("age", Hibernate.LONG)
			.addScalar("gender", Hibernate.STRING)
			.addScalar("distId", Hibernate.LONG)
			.addScalar("relativeName", Hibernate.STRING)
			.addScalar("hno", Hibernate.STRING)
			.addScalar("relationType", Hibernate.STRING)
			.addScalar("constName", Hibernate.STRING)
			.addScalar("distName", Hibernate.STRING)
			.addScalar("partNo", Hibernate.STRING)
			.addScalar("zero", Hibernate.LONG)
			.addScalar("panchayatId", Hibernate.LONG)
			.addScalar("panchayatName", Hibernate.STRING)
			.addScalar("tehsilId", Hibernate.LONG)
			.addScalar("tehsilName", Hibernate.STRING)
			.addScalar("stateId", Hibernate.LONG)
			.addScalar("stateName", Hibernate.STRING)
			.addScalar("localBodyName", Hibernate.STRING)
			.addScalar("localBodyId", Hibernate.LONG)
			.addScalar("wardId", Hibernate.LONG)
			.addScalar("wardName", Hibernate.STRING);
		query.setParameter("voterCardNo", voterCardNo);
		query.setParameter("publicationId", 12);
		return query.list();
	}
	
	public List getVotersDetailsForCadreRegistratiobByconstituencIdRTC(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villageCovered,Integer startIndex,Integer maxIndex)
	{
		
		StringBuilder queryStr1 = new StringBuilder();
		
		queryStr1.append(" select BPV.voter.voterId,BPV.voter.name, BPV.voter.relativeName, BPV.voter.age, BPV.voter.houseNo, BPV.voter.relationshipType, BPV.voter.gender, BPV.voter.voterIDCardNo, ");
		queryStr1.append("   BPV.booth.partNo from BoothPublicationVoter BPV where "+queryStr+"   ");
		queryStr1.append(" BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDate ");
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				queryStr1.append(" and BPV.booth.panchayat.panchayatId = :id ");
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				queryStr1.append(" and BPV.booth.localBody.localElectionBodyId = :id ");
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			queryStr1.append(" and BPV.booth.boothId = :boothId ");
		}
		
		/*if(villageCovered != null && villageCovered.trim().length()>0 && Long.valueOf(villageCovered.trim()))
		{
			queryStr1.append(" and BPV.booth.villagesCovered like '%"+villageCovered+"%' ");
		}*/
		
		Query query = getSession().createQuery(queryStr1.toString());
			
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDate", publicationDate);
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}
		if(startIndex != null && maxIndex != null){
		  query.setFirstResult(startIndex);
		  query.setMaxResults(maxIndex);
		}
		return query.list();	
		
	}
	
	public Long getVotersDetailsForCadreRegistratiobByconstituencIdCountRTC(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villageCovered)
	{
		
		StringBuilder queryStr1 = new StringBuilder();
		
		queryStr1.append(" select count(*) from BoothPublicationVoter BPV where "+queryStr+"   ");
		queryStr1.append(" BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.publicationDate.publicationDateId = :publicationDate ");
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				queryStr1.append(" and BPV.booth.panchayat.panchayatId = :id ");
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				queryStr1.append(" and BPV.booth.localBody.localElectionBodyId = :id ");
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			queryStr1.append(" and BPV.booth.boothId = :boothId ");
		}
		
		/*if(villageCovered != null && villageCovered.trim().length()>0 && Long.valueOf(villageCovered.trim()))
		{
			queryStr1.append(" and BPV.booth.villagesCovered like '%"+villageCovered+"%' ");
		}*/
		
		Query query = getSession().createQuery(queryStr1.toString());
			
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDate", publicationDate);
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}
		
		return (Long)query.uniqueResult();	
		
	}
	
	public List<Object[]> getVoterImagesByVoterIds(List<Long> voterIds,Long publicationId){
		
		if(new CommonMethodsUtilService().isListOrSetValid(voterIds)){
		Query query=getSession().createQuery("" +
		 " select  BPV.voter.voterId,BPV.voter.voterIDCardNo,BPV.booth.constituency.constituencyId,BPV.booth.partNo " +
		 " from    BoothPublicationVoter BPV " +
		 " where   BPV.booth.publicationDate.publicationDateId = :publicationDate and BPV.voter.voterId in (:voterIds)");
		
		query.setParameter("publicationDate", publicationId);
		query.setParameterList("voterIds",voterIds );
		return query.list();
		}
		return null;
		
	}
	
	public List<Object[]> getVoterImageDetailsByVoterId(Long voterId)
	{
		Query query = getSession().createQuery("SELECT model.booth.constituency.constituencyId,model.booth.partNo,model.voter.voterIDCardNo FROM " +
				" BoothPublicationVoter model WHERE model.voter.voterId = :voterId AND  model.booth.publicationDate.publicationDateId = "+IConstants.PUBLICATION_DATE_FOR_VOTER_IMG_PATH);
		query.setParameter("voterId",voterId);
		return query.list();
	}
	
	public List<Object[]> getVoterDetailsVoterId(String voterIDCardNo)
	{
		Query query = getSession().createQuery("SELECT model.voter.voterId, model.voter.name,model.voter.mobileNo,model.booth.constituency.constituencyId,model.booth.constituency.name " +
				" FROM  BoothPublicationVoter model WHERE model.voter.voterIDCardNo = :voterIDCardNo AND  model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_PUBLICATION_ID);
		query.setParameter("voterIDCardNo",voterIDCardNo);
		return query.list();
	}
	public List<Object[]> getVoterImagesVoterIdcardNo(List<String> voterIDCardNos)
	{
		Query query = getSession().createQuery("SELECT model.voterIDCardNo, model.imagePath FROM Voter model WHERE model.voterIDCardNo in(:voterIDCardNos)");
		query.setParameterList("voterIDCardNos",voterIDCardNos);
		return query.list();
	}
	public List<Object[]> getVoterAddressDetailsVoterId(Long voterId)
	{
		Query query = getSession().createQuery("" +
				" SELECT model.booth.constituency.district.districtId,model.booth.constituency.constituencyId, " +
				"        tehsil.tehsilId,leb.localElectionBodyId ," +
				"        panchayat.panchayatId,ward.constituencyId " +
				" FROM  BoothPublicationVoter model left join model.booth.tehsil tehsil " +
				"       left join model.booth.localBody leb " +
				"       left join model.booth.panchayat panchayat " +
				"       left join model.booth.localBodyWard ward " +
				" WHERE model.voter.voterId = :voterId AND  model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_PUBLICATION_ID);
		query.setParameter("voterId",voterId);
		return query.list();
	}
	
	public List<Object[]> getVoterDetailsByVoterCardNumber(String voterIDCardNo,Long constId){
		
		StringBuilder queryStr =new StringBuilder();
		queryStr.append("select model.voter.voterId," +
				" model.voter.name," +
				" model.voter.relativeName," +
				" model.voter.voterIDCardNo," +
				" model.voter.gender," +
				" model.voter.age," +
				" model.voter.dateOfBirth," +
				" model.voter.mobileNo," +
				" model.voter.houseNo," +
				" model.booth.boothId," +
				" model.booth.partNo," +
				" model.booth.constituency.district.districtId," +
				" model.booth.constituency.district.districtName," +
				" model.booth.constituency.constituencyId," +
				" model.booth.constituency.name," +
				" tehsil.tehsilId," +
				" tehsil.tehsilName," +
				" leb.localElectionBodyId," +
				" leb.name" +
		" from BoothPublicationVoter model" +
				" left join model.booth.tehsil tehsil" +
				" left join model.booth.localBody leb  where model.voter.voterIDCardNo = :voterIDCardNo ");
		if(constId != null && constId.longValue()>0L)
			queryStr.append("  and model.booth.constituency.constituencyId = :constId ");
		queryStr.append("   and model.booth.publicationDate.publicationDateId = :publicationDateId");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("voterIDCardNo", voterIDCardNo);
		
		if(constId != null && constId.longValue()>0L)
			query.setParameter("constId", constId);
		
		query.setParameter("publicationDateId", IConstants.AFFILIATED_VOTER_PUBLICATION_ID);
		
		return query.list();
	}
	
	public List<Object[]> getVotersConstituencyDetails(List<Long> voterIds,Long publicationId)
	{
	    Query query = getSession().createQuery("select model.voter.voterId,model.booth.constituency.name " +
	        " from BoothPublicationVoter model  " +
	        " where model.voter.voterId in (:voterIds) and model.booth.publicationDate.publicationDateId = :publicationDateId");
	    query.setParameterList("voterIds", voterIds);
	    query.setParameter("publicationDateId", publicationId);
	    return query.list();
	}
	
  public Long getTotalAvailableVotesByLocationId(Long locationId,String locationType,Long publicationDateId,Long constituencyId,List<Long> constituencyIdsList){
	StringBuilder str = new StringBuilder();
	
	str.append(" select sum(model.totalVoters) from Booth model where model.publicationDate.publicationDateId=:publicationDateId ");
	
	if(locationType.equalsIgnoreCase("constituency"))
	  str.append(" and model.constituency.constituencyId =:locationId ");
	else if(locationType.equalsIgnoreCase("Mandal"))
		  str.append(" and model.booth.tehsil.tehsilId =:locationId ");
	
	else if(locationType.equalsIgnoreCase("panchayat"))
		str.append(" and model.panchayat.panchayatId =:locationId ");
	
	else if(locationType.equalsIgnoreCase("booth"))
	 str.append(" and model.boothId = :locationId ");
	
	else if(locationType.equalsIgnoreCase("muncipality"))
	  str.append(" and model.localBody.localElectionBodyId =:locationId and model.localBody is not null ");
	
	else if(locationType.equalsIgnoreCase("District"))
	 str.append(" and model.constituency.district.districtId =:locationId ");
	
	else if(locationType.equalsIgnoreCase("Parliament"))
	 str.append(" and model.constituency.constituencyId in (:constituencyIdsList) ");
	
	if(constituencyId != null && constituencyId.longValue()>0L && !locationType.equalsIgnoreCase("District") && !locationType.equalsIgnoreCase("Parliament")){
		str.append(" and model.constituency.constituencyId =:constituencyId ");
	}
	Query query = getSession().createQuery(str.toString());
	query.setParameter("publicationDateId", publicationDateId);
	if(!locationType.equalsIgnoreCase("Parliament"))
	  query.setParameter("locationId", locationId);
	
	if(locationType.equalsIgnoreCase("Parliament"))
	  query.setParameterList("constituencyIdsList", constituencyIdsList);
	if(constituencyId != null && constituencyId.longValue()>0L && !locationType.equalsIgnoreCase("District") && !locationType.equalsIgnoreCase("Parliament"))
		query.setParameter("constituencyId", constituencyId);
	
	return (Long) query.uniqueResult();
	
}

	public List<Object[]> getVotersBySearch(Long searchVal,String searchType,String name,String hNo,String voterCardNo,List<Long> assignedBoothIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.voter.voterId," +
						" model.voter.name," +
						" model.voter.relationshipType," +
						" model.voter.relativeName," +
						" model.voter.gender," +
						" model.voter.age," +
						" model.voter.voterIDCardNo," +
						//" model.voter.mobileNo," +
						" model.voter.imagePath," +
						" model.voter.houseNo" +
						" from BoothPublicationVoter model" +
						" where model.booth.publicationDate.publicationDateId = 22 ");
		if(assignedBoothIds != null && assignedBoothIds.size()>0){
			sb.append(" and model.booth.boothId in (:assignedBoothIds)  ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("const") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.constituency.constituencyId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("mandal") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.tehsil.tehsilId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("munci") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.localBody.localElectionBodyId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("village") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.panchayat.panchayatId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("booth") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.boothId = :searchVal");
		
		if(voterCardNo != null && voterCardNo.trim().length() > 0l)
			sb.append(" and model.voter.voterIDCardNo = :voterCardNo");
		if(name != null && name.trim().length() > 0l)
			sb.append(" and model.voter.name like '%"+name+"%'");
		/*if(mobileNo != null && mobileNo.trim().length() > 0l)
			sb.append(" and model.voter.mobileNo = :mobileNo");*/
		if(hNo != null && hNo.trim().length() > 0l)
			sb.append(" and model.voter.houseNo = :hNo");
		
		Query query = getSession().createQuery(sb.toString());
		if(searchVal != null && searchVal.longValue() > 0l)
			query.setParameter("searchVal", searchVal);
		if(voterCardNo != null && voterCardNo.trim().length() > 0l)
			query.setParameter("voterCardNo", voterCardNo);
		/*if(mobileNo != null && mobileNo.trim().length() > 0l)
			query.setParameter("mobileNo", mobileNo);*/
		if(hNo != null && hNo.trim().length() > 0l)
			query.setParameter("hNo", hNo);
		if(assignedBoothIds != null && assignedBoothIds.size()>0){
			query.setParameterList("assignedBoothIds", assignedBoothIds);
		}
		
		return query.list();
	}
	
	public List<Object[]> getRegisteredCadresForVoterIds(List<Long> voterIds){
		Query query = getSession().createQuery("select model.tdpCadre.voter.voterId," +
												" model.tdpCadre.tdpCadreId," +
												" model.tdpCadre.memberShipNo," +
												" model.tdpCadre.mobileNo," +
												" model.enrollmentYearId," +
												" model.tdpCadre.image," +
												" model.tdpCadre.isCsd" +
												" from TdpCadreEnrollmentYear model" +
												" where model.isDeleted = 'N'" +
												" and model.tdpCadre.isDeleted = 'N'" +
												" and model.tdpCadre.enrollmentYear = :enrollmentYear" +
												" and model.tdpCadre.voter.voterId in (:voterIds)" +
												" order by model.enrollmentYear.enrollmentYearId");
		query.setParameterList("voterIds", voterIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		
		return query.list();
	}
	public List<Object[]> getVoterDetails(Long voterId,Long publicationDateId) {
		Query query = getSession().createQuery(
				"select distinct model.booth.boothId,model.voter.houseNo,model.voter.voterIDCardNo "
						+ " from BoothPublicationVoter model  "
						+ " where model.voter.voterId = :voterId and model.booth.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("voterId", voterId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}

	public List<Object[]> getFamilyVoterDetails(Long boothId, String houseNo) {
		Query query = getSession()
				.createQuery(
						"select distinct model.voter.voterId,model.voter.houseNo,"
								+ " model.voter.relativeName,model.voter.name,model.voter.gender,model.voter.age ,"
								+ " model.voter.relationshipType,model.voter.voterIDCardNo,model.voter.mobileNo, "
								+ " model.voter.imagePath"
								+ " from BoothPublicationVoter model  "
								+ " where model.booth.boothId = :boothId and model.voter.houseNo = :houseNo");
		query.setParameter("boothId", boothId);
		query.setParameter("houseNo", houseNo);
		return query.list();
	}
	
public List<Object[]> getOnliCadRegistrSearchVoteDetails(Long searchVal,String searchType,String type,String typeVal){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.voter.voterId," +
						" model.voter.name," +
						" model.voter.relationshipType," +
						" model.voter.relativeName," +
						" model.voter.gender," +
						" model.voter.age," +
						" model.voter.voterIDCardNo," +
						" model.voter.houseNo," +
						" model.voter.imagePath" +
						" from BoothPublicationVoter model" +
						" where model.booth.publicationDate.publicationDateId = 22");
		
		if(searchType != null && searchType.equalsIgnoreCase("const") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.constituency.constituencyId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("mandal") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.tehsil.tehsilId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("munci") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.localBody.localElectionBodyId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("village") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.panchayat.panchayatId = :searchVal");
		else if(searchType != null && searchType.equalsIgnoreCase("booth") && searchVal != null && searchVal.longValue() > 0l)
			sb.append(" and model.booth.boothId = :searchVal");
		
		if(type != null && type.trim().equalsIgnoreCase("voterId"))
			sb.append(" and model.voter.voterIDCardNo = :typeVal");
		else if(type != null && type.trim().equalsIgnoreCase("name"))
			sb.append(" and model.voter.name like '%"+typeVal+"%'");
		else if(type != null && type.trim().equalsIgnoreCase("hNo"))
			sb.append(" and model.voter.houseNo = :typeVal");
		
		Query query = getSession().createQuery(sb.toString());
		if(searchVal != null && searchVal.longValue() > 0l)
			query.setParameter("searchVal", searchVal);
		if(type != null && !type.trim().equalsIgnoreCase("name"))
			query.setParameter("typeVal", typeVal);
		
		return query.list();
	}

   public List<Long> getLocalElectionBodyByVoterId(Long voterId){
	   
	   Query query = getSession().createQuery("" +
	    " select model.booth.localBody.localElectionBodyId " +
	   	" from   BoothPublicationVoter model " +
	   	" where  model.voter.voterId = :voterId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
	   
	   query.setParameter("voterId",voterId );
	   query.setParameter("publicationDateId",IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID );
	   return query.list();
   }


@SuppressWarnings("unchecked")
public List<Object[]> getVoterLocationDetailsByVotersIdsList(List<Long> voterIdsList)//218776952
{
	Query query = getSession().createQuery("" +
			" SELECT model.booth.constituency.district.state.country.countryId, model.booth.constituency.district.state.stateId,"+//1
			"        model.booth.constituency.district.districtId, model.booth.constituency.constituencyId, " +//3
			"        tehsil.tehsilId, leb.localElectionBodyId ," +//5
			"        panchayat.panchayatId,ward.constituencyId, " +//7
			"        model.booth.boothId , model.voter.voterId, model.voter.voterIDCardNo," +//10
			" 		 model.voter.houseNo "+//11
			" FROM  BoothPublicationVoter model "
			+ "     left join model.booth.tehsil tehsil " +
			"       left join model.booth.localBody leb " +
			"       left join model.booth.panchayat panchayat " +
			"       left join model.booth.localBodyWard ward " +
			" WHERE model.voter.voterId in (:voterIdsList) AND  model.booth.publicationDate.publicationDateId = "+IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
	query.setParameterList("voterIdsList",voterIdsList);
	return query.list();
}

public Voter getVoterByVoterIDCardNo(String voterCardNo){
	
	StringBuilder sb = new StringBuilder();
	sb.append("select model.voter " +
					" from BoothPublicationVoter model " +
					" where model.voter.voterIDCardNo = :voterCardNo  and model.booth.publicationDate.publicationDateId = "+IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID );

	Query query = getSession().createQuery(sb.toString());
	
	query.setParameter("voterCardNo", voterCardNo);
	
	return (Voter)query.uniqueResult();
}
public List<Object[]> getTotalVoterGroupByLocation(String location, Long constId ){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append(" select ");
	if(location.equalsIgnoreCase("mandal")){
		queryStr.append(" tehsil.tehsilId,tehsil.tehsilName, count(distinct voter.voterId)  ");
	}
	if(location.equalsIgnoreCase("panchayat")){
		queryStr.append(" tehsil.tehsilId,tehsil.tehsilName,panchayat.panchayatId,panchayat.panchayatName, count(distinct voter.voterId)  ");
	}
	if(location.equalsIgnoreCase("booth")){  
		queryStr.append(" tehsil.tehsilId," +//0
						" tehsil.tehsilName," +//1
						" panchayat.panchayatId," +//2
						" panchayat.panchayatName," +//3
						" localBody.localElectionBodyId," +//4
						" localBody.name," +//5
						" booth.boothId," +//6
						" booth.partNo, " +//7
						" count(distinct voter.voterId)  ");//8 
	}
	queryStr.append(" from  BoothPublicationVoter BPV " +
					" left join BPV.voter voter " +
					" left join BPV.booth booth " +
					" left join booth.panchayat panchayat " +
					" left join panchayat.tehsil tehsil " +
					" left join booth.localBody localBody " +
					" left join booth.constituency constituency " +
					" where " +
					" constituency.constituencyId = :constId and " +
					" constituency.electionScope.electionScopeId = :electionScopeId and " +
					" constituency.deformDate is null and " +
					" booth.publicationDate.publicationDateId = :publicationDateId ");    
	  
	if(location.equalsIgnoreCase("mandal") || location.equalsIgnoreCase("panchayat"))  
		queryStr.append(" and booth.localBody is null "); 
	
	if(location.equalsIgnoreCase("mandal")){
		queryStr.append(" group by tehsil.tehsilId order by tehsil.tehsilId ");
	}	
	else if(location.equalsIgnoreCase("panchayat")){
		queryStr.append(" group by panchayat.panchayatId order by panchayat.panchayatId ");
	}	
	else if(location.equalsIgnoreCase("booth")){
		queryStr.append(" group by booth.boothId order by tehsil.tehsilId,panchayat.panchayatId,localBody.localElectionBodyId,booth.boothId "); 
	}	
	Query query = getSession().createQuery(queryStr.toString());  
	query.setParameter("constId", constId);
	query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID); 
	query.setParameter("electionScopeId", IConstants.ELECTION_SCOPE_ID); 
	
	return query.list();
}
public List<Object[]> getTotalVoterGroupByLocationUrb(String location, Long constId ){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append(" select localBody.localElectionBodyId, localBody.name, count(distinct voter.voterId)  ");
	
	queryStr.append(" from  BoothPublicationVoter BPV " +   
					" left join BPV.voter voter " +
					" left join BPV.booth booth " +
					" left join booth.panchayat panchayat " +
					" left join panchayat.tehsil tehsil " +
					" left join booth.localBody localBody " +
					" left join booth.constituency constituency, " +
					" AssemblyLocalElectionBody ALEB " +
					" where " +
					" constituency.constituencyId = :constId and " +
					" constituency.electionScope.electionScopeId = :electionScopeId and " +
					" constituency.deformDate is null and " +
					" booth.publicationDate.publicationDateId = :publicationDateId and " +
					" localBody.localElectionBodyId = ALEB.localElectionBody.localElectionBodyId and " +
					" localBody.localElectionBodyId is not null ");   
	if(location.equalsIgnoreCase("mandal")){  
		queryStr.append(" group by localBody.localElectionBodyId order by localBody.localElectionBodyId ");
	}	
	
	Query query = getSession().createQuery(queryStr.toString());  
	query.setParameter("constId", constId);
	query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID); 
	query.setParameter("electionScopeId", IConstants.ELECTION_SCOPE_ID); 
	
	return query.list();
}
public List<Object[]> getTotalVoterGroupByLocationUrbWard(String location, Long constId ){  
	StringBuilder queryStr = new StringBuilder();
	queryStr.append(" SELECT B.local_election_body_id as local_election_body_id, " +
			        " L.name as municipalName, " +
			        " ALBW.local_election_body_ward_id as local_election_body_ward_id, " +
			        " C.name as wardName, " +
			        " 0 as count ");
	queryStr.append(" FROM assembly_local_election_body ALB,booth B,assembly_local_election_body_ward ALBW,constituency C,local_election_body L ");
	queryStr.append(" WHERE "); 
	queryStr.append(" B.local_election_body_id = L.local_election_body_id and ");
	queryStr.append(" B.local_election_body_id = ALB.local_election_body_id AND ");
	queryStr.append(" ALB.assembly_local_election_body_id = ALBW.assembly_local_election_body_id AND ");
	queryStr.append(" ALBW.local_election_body_ward_id = C.constituency_id AND ");
	queryStr.append(" B.publication_date_id = :publicationDateId AND ");
	queryStr.append(" B.constituency_id = :constId ");
	queryStr.append(" GROUP BY B.local_election_body_id,ALBW.local_election_body_ward_id; ");
	
	Query query = getSession().createSQLQuery(queryStr.toString())  
			.addScalar("local_election_body_id", Hibernate.LONG)
			.addScalar("municipalName", Hibernate.STRING)
			.addScalar("local_election_body_ward_id", Hibernate.LONG)
			.addScalar("wardName", Hibernate.STRING)
			.addScalar("count", Hibernate.LONG);     
	query.setParameter("constId", constId);  
	query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID); 
	
	return query.list();
}


public List<Object[]> getVoterIdDetailsByPublicationIdAndCardNo(String voterCardNo,Long publicationId){
	Query query=getSession().createSQLQuery("select distinct " +
			" C.constituency_id as cid," +//0
			"B.booth_id as boothId," +//1
			"B.publication_date_id as pid," +//2
			"V.voter_id as voterId," +//3
			"V.name as voterName ," +//4
			"V.age as age," +//5
			"V.gender as gender," +//6
			"C.district_id as distId" +//7
			",V.relative_name as relativeName," +//8
			"V.house_no as hno," +//9
			"V.relationship_type as relationType," +//10
			"C.name as constName," +//11
			"D.district_name as distName," +//12
			"B.part_no as partNo," +//13
			"0 as zero," +//14
			" B.panchayat_id as panchayatId," +//15
			"P.panchayat_name as panchayatName," +//16
			"T.tehsil_id as tehsilId," +//17
			"T.tehsil_name as tehsilName," +//18
			"C.state_id as stateId," +//19
			"S.state_name as stateName," +//20
			" L.name as localBodyName," +//21
			"L.local_election_body_id as localBodyId," +//22
			"W.constituency_id as wardId, " +//23
			"W.name as wardName, " +//24
			"V.voter_id_card_no as voterIdCardNo, " +//25
			"V.mobile_no as mobileNo," +//26
			"V.aadhar_no as aadharNo, " +//27
			"V.email as email " +//28
			" from booth_publication_voter BPV ,voter V,district D,state S,booth B left outer join tehsil T on B.tehsil_id = T.tehsil_id " +
			" left outer join panchayat P on B.panchayat_id=P.panchayat_id " +
			" left outer join constituency C on B.constituency_id=C.constituency_id " +
			" left outer join constituency W on B.ward_id=W.constituency_id " +
			" left outer join local_election_body L on B.local_election_body_id=L.local_election_body_id " +
			" where B.booth_id=BPV.booth_id and BPV.voter_id=V.voter_id " +
			" and V.voter_id_card_no=:voterCardNo and B.publication_date_id=:publicationId and C.district_id=D.district_id and" +
			" C.state_id=S.state_id and S.state_id=1")
		.addScalar("cid", Hibernate.LONG)
		.addScalar("boothId", Hibernate.LONG)
		.addScalar("pid", Hibernate.LONG)
		.addScalar("voterId", Hibernate.LONG)
		.addScalar("voterName", Hibernate.STRING)
		.addScalar("age", Hibernate.LONG)
		.addScalar("gender", Hibernate.STRING)
		.addScalar("distId", Hibernate.LONG)
		.addScalar("relativeName", Hibernate.STRING)
		.addScalar("hno", Hibernate.STRING)
		.addScalar("relationType", Hibernate.STRING)
		.addScalar("constName", Hibernate.STRING)
		.addScalar("distName", Hibernate.STRING)
		.addScalar("partNo", Hibernate.STRING)
		.addScalar("zero", Hibernate.LONG)
		.addScalar("panchayatId", Hibernate.LONG)
		.addScalar("panchayatName", Hibernate.STRING)
		.addScalar("tehsilId", Hibernate.LONG)
		.addScalar("tehsilName", Hibernate.STRING)
		.addScalar("stateId", Hibernate.LONG)
		.addScalar("stateName", Hibernate.STRING)
		.addScalar("localBodyName", Hibernate.STRING)
		.addScalar("localBodyId", Hibernate.LONG)
		.addScalar("wardId", Hibernate.LONG)
		.addScalar("wardName", Hibernate.STRING)
		.addScalar("voterIdCardNo", Hibernate.STRING)
		.addScalar("mobileNo", Hibernate.STRING)
		.addScalar("aadharNo", Hibernate.STRING)
		.addScalar("email", Hibernate.STRING);
	query.setParameter("voterCardNo", voterCardNo);
	query.setParameter("publicationId", publicationId);
	return query.list();
}
public Long getDivisionWiseVoters(Long locationId,Long levelId, Long publicationDateId){
	StringBuilder sb = new StringBuilder();
	sb.append(" select count(distinct model.voter.voterId)" +
			" from BoothPublicationVoter model" +
			" left join model.booth.constituency.district district" +
			" left join model.booth.constituency constituency" +
			" left join model.booth.tehsil tehsil" +
			" left join model.booth.localBody localBody" +
			" left join model.booth.panchayat panchayat" +
			" left join model.booth.localBodyWard ward" +
			" where model.booth.publicationDate.publicationDateId = :publicationDateId");
	/*if(fromDate != null && toDate != null)
		sb.append(" and ( date(model.surveyTime) between :fromDate and :toDate ) ");*/
	
	/*if(locationType.equalsIgnoreCase("Ward")){
		sb.append(" and model.wardId=:locationId");
	}*/
	if(levelId != null && levelId.longValue() > 0l){
		if(levelId == IConstants.DISTRICT_SCOPE_ID){
			sb.append(" and district.districtId = :locationId");
		}else if(levelId == IConstants.CONSTITUENCY_SCOPE_ID){
			sb.append(" and constituency.constituencyId = :locationId");
		}else if(levelId == IConstants.TEHSIL_SCOPE_ID){
			sb.append(" and tehsil.tehsilId = :locationId");
		}else if(levelId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID){
			sb.append(" and localBody.localElectionBodyId = :locationId");
		}else if(levelId == 6l){
			sb.append(" and panchayat.panchayatId = :locationId");
		}else if(levelId == 8l){
			sb.append(" and ward.constituencyId = :locationId");
		}else if(levelId == 9l){
			sb.append(" and model.booth.boothId = :locationId");
		}
	}
	
	/*if(!(userType.contains("All")))
		sb.append(" and model.mobileAppUser.type in (:userType) ");*/
	if(levelId != null && levelId.longValue() > 0l){
		if(levelId == IConstants.DISTRICT_SCOPE_ID){
			sb.append(" group by district.districtId");
		}else if(levelId == IConstants.CONSTITUENCY_SCOPE_ID){
			sb.append(" group by constituency.constituencyId");
		}else if(levelId == IConstants.TEHSIL_SCOPE_ID){
			sb.append(" group by tehsil.tehsilId");
		}else if(levelId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID){
			sb.append(" group by localBody.localElectionBodyId");
		}else if(levelId == 6l){
			sb.append(" group by panchayat.panchayatId");
		}else if(levelId == 8l){
			sb.append(" group by ward.constituencyId");
		}else if(levelId == 9l){
			sb.append(" group by model.booth.boothId");
		}
	}
	
	Query query = getSession().createQuery(sb.toString());
	/*if(fromDate != null && toDate != null){
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
	}*/
	if(levelId != null && levelId.longValue() > 0l)
		query.setParameter("locationId", locationId);
	//if(publicationDateId != null && publicationDateId.longValue() > 0l)
		query.setParameter("publicationDateId", publicationDateId);
	/*if(!(userType.contains("All")))
		query.setParameterList("userType", userType);*/
	return (Long) query.uniqueResult();
}
public Long getPublicationDateIdByVoterIDCardNo(String voterCardNo){
	
	StringBuilder sb = new StringBuilder();
	sb.append("select model.booth.publicationDate.publicationDateId " +
					" from BoothPublicationVoter model " +
					" where model.voter.voterIDCardNo = :voterCardNo  and model.booth.publicationDate.publicationDateId = "+IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);

	Query query = getSession().createQuery(sb.toString());
	
	query.setParameter("voterCardNo", voterCardNo);
	
	return (Long)query.uniqueResult();
}

public Long getPublicationDateIdByVoterID(Long voterId){
	
	StringBuilder sb = new StringBuilder();
	sb.append("select model.booth.publicationDate.publicationDateId " +
					" from BoothPublicationVoter model " +
					" where model.voter.voterId = :voterId  and model.booth.publicationDate.publicationDateId = "+IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);

	Query query = getSession().createQuery(sb.toString());
	
	query.setParameter("voterId", voterId);
	
	return (Long)query.uniqueResult();
}

}
