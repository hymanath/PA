package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterFlag;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserVoterDetailsDAO extends GenericDaoHibernate<UserVoterDetails, Long> implements 
IUserVoterDetailsDAO{

	public UserVoterDetailsDAO() {
		super(UserVoterDetails.class);
			}

	@SuppressWarnings("unchecked")
	public List<UserVoterDetails> getUserVoterDetails(Long voterId,Long userId){
		//List<UserVoterDetails> list = getHibernateTemplate().find("from UserVoterDetails as model where model.voter.voterId = ? and model.user.userId=?",voterId,userId);
		Query query = getSession().createQuery("from UserVoterDetails model where model.voter.voterId = ? and model.user.userId=?");
		query.setParameter(0,voterId);
		query.setParameter(1,userId);
		
		return query.list();
		
	}
	
	public UserVoterDetails getUserVoterDetailsByUserIdAndVoterId(Long userId,Long voterId){
		Query query = getSession().createQuery("select model from UserVoterDetails model where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("userId",userId);
		query.setParameter("voterId",voterId);
		return (UserVoterDetails) query.uniqueResult();
	}
	
	public List<Object[]> getUserVoterDetailsByVoterIds(List<Long> voterIds,Long userId){
		
		Query query = getSession().createQuery("select model.voter.voterId , model.party.shortName," +
				"model.casteState.caste.casteName from UserVoterDetails model where model.voter.voterId in(:voterIds)" +
				" and model.user.userId = :userId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("userId", userId);
		
		return query.list();
		
		
	}
	
  public List<UserVoterDetails> getUserVoterDtlsVoterIds(List<Long> voterIds,Long userId){
		
		Query query = getSession().createQuery("select model from UserVoterDetails model where model.voter.voterId in(:voterIds)" +
				" and model.user.userId = :userId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("userId", userId);
		
		return query.list();
		
		
	}

	/*public Candidate findCandidateByLastName(String lastName){
		Candidate candidate = null;
		List<Candidate> list = getHibernateTemplate().find("from Candidate as model where model.lastname=?", lastName);
		if(list!=null && list.size()>0){
			candidate = list.get(0);
		}
		return candidate;
	}*/
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId){
	
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId,model.casteState.casteStateId = :castStateId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("castStateId",castStateId);
		
		query.executeUpdate();
		
	}
	public void updateUserVoterDetails2(Long voterId,Long userId,Long partyId,Long localitityId,Long hamletId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId,model.locality.localityId = :localityId,model.hamlet.hamletId = :hamletId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("localityId",localitityId);
		query.setParameter("hamletId",hamletId);
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterDetails2WithWard(Long voterId,Long userId,Long partyId,Long localitityId,Long wardId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId," +
				"model.locality.localityId = :localityId,model.ward.constituencyId = :wardId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("localityId",localitityId);
		query.setParameter("wardId",wardId);
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterDetails3(Long voterId,Long userId,Long castStateId,Long localitityId,Long hamletId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.casteState.casteStateId = :castStateId,model.locality.localityId = :localityId,model.hamlet.hamletId = :hamletId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("castStateId",castStateId);
		query.setParameter("localityId",localitityId);
		query.setParameter("hamletId",hamletId);
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterDetails3WithWard(Long voterId,Long userId,Long castStateId,Long localitityId,Long wardId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.casteState.casteStateId = :castStateId," +
				"model.locality.localityId = :localityId,model.ward.constituencyId = :wardId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("castStateId",castStateId);
		query.setParameter("localityId",localitityId);
		query.setParameter("wardId",wardId);
		
		query.executeUpdate();
		
	}
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long hamletId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId,model.casteState.casteStateId = :castStateId,model.locality.localityId = :localityId,model.hamlet.hamletId = :hamletId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("castStateId",castStateId);
		query.setParameter("localityId",localitityId);
		query.setParameter("hamletId",hamletId);
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterDetailsWithWard(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long wardId){
	{
		
		Query query = getSession().createQuery("update UserVoterDetails model set " +
				"model.party.partyId = :partyId,model.casteState.casteStateId = :castStateId,model.locality.localityId = :localityId,model.ward.constituencyId = :wardId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("castStateId",castStateId);
		query.setParameter("localityId",localitityId);
		query.setParameter("wardId",wardId);
		
		query.executeUpdate();
	}
		
		
	}
	public void updateUserVoterDetailsForLocality(Long voterId,Long userId,Long localitityId, Long hamletId){
		Query query = getSession().createQuery("update UserVoterDetails model set " +
				"model.locality.localityId = :localityId,model.hamlet.hamletId = :hamletId  " +
				"where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		
		query.setParameter("localityId",localitityId);
		query.setParameter("hamletId",hamletId);
		
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterDetailsForLocalityWithWard(Long voterId,Long userId,Long localitityId, Long wardId){
		Query query = getSession().createQuery("update UserVoterDetails model set " +
				"model.locality.localityId = :localityId,model.ward.constituencyId = :wardId  " +
				"where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		
		query.setParameter("localityId",localitityId);
		query.setParameter("wardId",wardId);
		
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterPartyDetails(Long voterId,Long userId,Long partyId,Long castStateId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterCastDetails(Long voterId,Long userId,Long partyId,Long castStateId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.casteState.casteStateId = :castStateId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("castStateId",castStateId);
		
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteByVoterId(Long userId,List<Long> voterId)
	{
		Query query =getSession().createQuery("select model.voter.voterId,model.casteState.caste.casteName from UserVoterDetails model where model.user.userId = :userId and model.voter.voterId in (:voterId)");
		query.setParameter("userId", userId);
		query.setParameterList("voterId", voterId);
		return query.list();
	
	}
	
	public List<UserVoterDetails> getAllUserVoterDetails(List<Long> voterIds,Long userId){
		//List<UserVoterDetails> list = getHibernateTemplate().find("from UserVoterDetails as model where model.voter.voterId = ? and model.user.userId=?",voterId,userId);
		Query query = getSession().createQuery("from UserVoterDetails model where model.voter.voterId in (:voterIds) and model.user.userId= :userId");
		query.setParameterList("voterIds",voterIds);
		query.setParameter("userId",userId);
		
		return query.list();
		
	}
	/**
	 * This method is used to get the party name and caste categoery of a voter based on voter id and user id
	 * @author Prasad Thiragabathina
	 * @param Long voterId
	 * @param Long userId
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyAndCasteDetails(Long voterId , Long userId) {
		Object[] param = {voterId,userId};
		return getHibernateTemplate().find("select model.casteState.caste.casteId," +
				" model.casteState.caste.casteName,model.party.partyId,model.party.shortName " +
				"from UserVoterDetails model where model.voter.voterId = ? and model.user.userId = ? ",param);
	}
	public List<Object[]> getHamletsIdsForUser(Long panchayatId , Long userId) {
		Object[] param = {panchayatId,userId};
		return getHibernateTemplate().find("select distinct model.hamlet.hamletId,model.hamlet.hamletName " +
							"from UserVoterDetails model join model.hamlet.panchayathHamlets p  where  p.panchayat.panchayatId = ? and model.user.userId = ? ",param);
	}
	public List<Object> getVoterIdsBasedOnHamletId(Long hamletId, Long userId)
	{
		Object[] param = {hamletId,userId};
		return getHibernateTemplate().find("select distinct model.voter.voterId " +
							"from UserVoterDetails model  where  model.hamlet.hamletId = ? and model.user.userId = ? ",param);
	}
	
	
	public List<Object[]> getVotersCountByGenderForHamlet(Long hamletId , Long userId)
	{
		Query query = getSession().createQuery("select model.voter.gender , count(*) from UserVoterDetails model " +
				"where model.hamlet.hamletId = ? and model.user.userId =? group by model.voter.gender ");
		
		query.setParameter(0, hamletId);
		query.setParameter(1, userId);
		
		return query.list();
		
	}
	
	
	public List<Voter> getVoterDetailsByHamletId(Long hamletId , Long userId)
	{
		
		Query query = getSession().createQuery("select model.voter from UserVoterDetails model where" +
				" model.hamlet.hamletId = ? and model.user.userId = ?");
		
		query.setParameter(0, hamletId);
		query.setParameter(1, userId);
		
		
		return query.list();
		
	}
	
	 public List<?> getVotersDetailsByHamletPublication(Long hamletId,Long userID, Integer startIndex,
				Integer maxRecords, String order, String columnName) {  
		 
			String queryString = "select model.voter.voterId from UserVoterDetails model " +
					"where model.hamlet.hamletId = ? and model.user.userId = ? ";

			Query query = getSession().createQuery(queryString);

			query.setParameter(0, hamletId);
			query.setParameter(1, userID);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxRecords);

			return query.list();
		 
	 }  
	public List<Long> getUserHamletsByPanchayatId(Long userId , Long panchayatId)
	{
		
		
		Query query = getSession().createQuery("select distinct model.hamlet.hamletId from UserVoterDetails model, PanchayatHamlet model1" +
				" where model.user.userId = :userId and model.hamlet.hamletId = model1.hamlet.hamletId and " +
				" model1.panchayat.panchayatId = :panchayatId");
		
		query.setParameter("userId", userId);
		query.setParameter("panchayatId", panchayatId);
		
		return query.list();
	}
	public List<Long> getVotersCountForALocality(Long hamletId,Long id,Long userId ,String query1 , Long publicationDateId )
	{
		Query query = getSession().createQuery("select count(*) from UserVoterDetails model , BoothPublicationVoter model1 " +
				" where model.voter.voterId = model1.voter.voterId and "+ query1+" and " +
				"model.locality.localityId = :localityId and  model.user.userId = :userId and model1.booth.publicationDate.publicationDateId = :publicationDateId ");
		
		query.setParameter("id", hamletId);
		query.setParameter("localityId", id);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);

	
		
		return query.list();
		
	}
	
	public List<Long> getVoterIdsBasedOnHamletAndLocality(Long hamletId ,Long localityId , Long userId ,String cond )
	{
		
		
		Query query = getSession().createQuery("select distinct model.voter.voterId " +
							"from UserVoterDetails model join model.locality  where  " +cond+  
							" and model.locality.localityId = :locId and model.user.userId = :userId and  model.locality.localityId is not null");
		
		query.setParameter("id", hamletId);
		query.setParameter("locId", localityId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Object[]> getVotersCountByGenderForLocalityInHamlet(Long userId , Long hamletId , Long localityId,Long publicationDateId,String query1)
	{
		Query query = getSession().createQuery("select count(*),model.voter.gender   from UserVoterDetails model,BoothPublicationVoter model1 " +
				" where model.voter.voterId = model1.voter.voterId and "+ query1+" and " +
				"model.locality.localityId = :localityId and  model.user.userId = :userId and model1.booth.publicationDate.publicationDateId = :publicationDateId "+
				"group by model.voter.gender ");
		
		
		query.setParameter("id", hamletId);
		query.setParameter("localityId", localityId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);

	
		
		return query.list();
		
	}
	
	
	public Long getTotalVotersCountInALocality(Long userId ,Long hamletId,Long localityId,Long publicationDateId)
	{
		
		StringBuilder query = new StringBuilder();
		
		query.append("select count(model.voter.voterId) from UserVoterDetails model , " +
				"BoothPublicationVoter model1 " +
				" where model.voter.voterId = model1.voter.voterId and " +
				" model1.booth.publicationDate.publicationDateId = :publicationDateId" +
				" and model.user.userId = :userId and model.hamlet.hamletId = :hamletId");
		
		
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("hamletId", hamletId);
		queryObj.setParameter("userId", userId);
		return (Long)queryObj.uniqueResult();
		
	}
	
	public List<Long> getVoterIdsForuserByHamletIdsByCaste(Long userId ,Long hamletId,Long casteStateId)
	{
		
		Query query = getSession().createQuery("select model.voter.voterId from UserVoterDetails model where " +
				" model.user.userId = :userId and model.hamlet.hamletId =:hamletId and model.casteState.casteStateId = :casteStateId ");
		
		
		query.setParameter("userId", userId);
		query.setParameter("hamletId", hamletId);
		query.setParameter("casteStateId", casteStateId);
		
		return query.list();
		
	}
	
	
	
	public List<Long> getLocalitiesByHamletId(Long userId , Long hamletId)
	{
		
		
		Query query = getSession().createQuery("select distinct model.hamlet.hamletId from UserVoterDetails model, PanchayatHamlet model1" +
				" where model.user.userId = :userId and model.hamlet.hamletId = model1.hamlet.hamletId and " +
				" model1.panchayat.panchayatId = :panchayatId");
		
		query.setParameter("userId", userId);
		query.setParameter("panchayatId", hamletId);
		
		return query.list();
	}
	
	public List<Object[]> getVotersCountByGenderForLocalAreas(List<?> voterIds , Long userId)
	{
		
		Query query = getSession().createQuery("select distinct model.locality.name," +
				"SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
				"SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount " +
				" from UserVoterDetails model  " +
		        " join model.locality " +
				"where model.voter.voterId in(:voterIds) and model.user.userId = :id    group by model.locality.name ");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("id", userId);
		
		return query.list();
		
		
	}
	/* test method by anil*/
	public List<Object[]> getLocalityIdsForUser(Long hamletId , Long userId,List<?> voterIds) {
		Object[] param = {voterIds};
		return getHibernateTemplate().findByNamedParam("select distinct model.locality.localityId,model.locality.name ," +
				"count( distinct model.voter.voterId), " +
				
				" SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
				" SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F'  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 22  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun , " +
				
				
				" SUM( CASE WHEN model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " +		
				" SUM( CASE WHEN model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCoun , " +	
				" SUM( CASE WHEN model.voter.age BETWEEN  31 and 45 THEN 1 ELSE 0 END) as ageCou , " +
				" SUM( CASE WHEN model.voter.age BETWEEN  46 and 60 THEN 1 ELSE 0 END) as ageCo , " +
				" SUM( CASE WHEN model.voter.age >60 THEN 1 ELSE 0 END) as ageC " +
				
				"from UserVoterDetails model  where  model.voter.voterId in(:ids) " +
							" and model.locality.localityId is not null group by model.locality.localityId order by model.locality.name","ids",voterIds);
	}
	
	public List<?> getVotersIdsByHamletId(Long hamletId,Long userId,String locationType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.voter.voterId from UserVoterDetails model where model.user.userId = ? ");
		
		if(locationType.equalsIgnoreCase(IConstants.HAMLET))
			queryStr.append(" and model.hamlet.hamletId = ? ");
		else if(locationType.equalsIgnoreCase(IConstants.CUSTOMWARD))
			queryStr.append(" and model.ward.constituencyId = ? ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter(0, userId);
		query.setParameter(1, hamletId);
		
		//return query.list();
		
		return query.list();
			
	}
	public List<?> getVoterIdsBasedOnVoterIdsAndPublication(
			 Long publicationDateId ,Long id,Long userId,String type) {		
			
		StringBuilder queryString = new StringBuilder();	
		queryString.append("select distinct b.voter.voterId " +
				" from BoothPublicationVoter b,UserVoterDetails UVD where   " +
				" b.booth.publicationDate.publicationDateId = :publicationDateId and b.voter.voterId = UVD.voter.voterId " +
				" and UVD.user.userId = :userId  and ") ;
		   
		   if(type.equalsIgnoreCase(IConstants.HAMLET))
			  queryString.append("UVD.hamlet.hamletId  = :id  ");
			else
			  queryString.append("UVD.ward.constituencyId = :id  ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId", userId);	
		query.setParameter("id", id);	
		  return query.list();
		
	}
	public List<?> getVotersBasedOnVoterIdsAndPublication(
			 Long publicationDateId , List<?> voterIds,String columnName ,String order) {		
			
		Query query = null;
		if("initial".equalsIgnoreCase(columnName))
		 {
			query = getSession().createQuery("select distinct b.voter,b.booth.partNo,b.serialNo " +
				" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
				"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
				"order by cast(b.booth.partNo , int),b.serialNo,b.voter.houseNo");
		 }
		 else if("partNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select distinct b.voter,b.booth.partNo,b.serialNo " +
						" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
						"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
						"order by cast(b.booth.partNo , int) "+order); 
		 }
		 else if("serialNo".equalsIgnoreCase(columnName))
		 {
			 query = getSession().createQuery("select distinct b.voter,b.booth.partNo,b.serialNo " +
						" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
						"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
						" order by b.serialNo  "+order); 
		 }
		 else
		 {
			 query = getSession().createQuery("select distinct b.voter,b.booth.partNo,b.serialNo " +
						" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
						"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " +
						" order by b.voter."+columnName+" "+order);
		 }
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("voterIds", voterIds);	
		  return query.list();
		
	}
	
	public List<Long> getVoterIdsByLocalityForUser(Long localityId,Long hamletId,Long userId,Long casteStateId,String queryStr)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.voter.voterId from UserVoterDetails model where model.locality.localityId = :localityId and ");
		str.append(" model.user.userId = :userId and model.casteState.casteStateId = :casteStateId and ");
		str.append(queryStr);
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("localityId", localityId);
		query.setParameter("id", hamletId);
		query.setParameter("userId", userId);
		query.setParameter("casteStateId", casteStateId);
		
		return query.list();
		
	}
	//for age wise voters aniltest2 method
	public List<Object[]> getAgeWiseInfoForUser(List<?> voterIds) {
		Object[] param = {voterIds};
		return getHibernateTemplate().findByNamedParam("select " +
				"count( distinct model.voter.voterId), " +
								
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount1 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount2 , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 22  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount3 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as malecoun4 , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount5 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount6 , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as malecoun11 , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount7 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount8 , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun12 , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount9 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount10 , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun13 , " +
				
				
				" SUM( CASE WHEN model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " +		
				" SUM( CASE WHEN model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCoun , " +	
				" SUM( CASE WHEN model.voter.age BETWEEN  31 and 45 THEN 1 ELSE 0 END) as ageCou , " +
				" SUM( CASE WHEN model.voter.age BETWEEN  46 and 60 THEN 1 ELSE 0 END) as ageCo , " +
				" SUM( CASE WHEN model.voter.age >60 THEN 1 ELSE 0 END) as ageC " +
				
				"from UserVoterDetails model  where  model.voter.voterId in(:ids) " ,"ids",voterIds);
							
	}
	public List<Object[]> getAgeDataForPanchayatUser(List<?> voterIds) {
		Object[] param = {voterIds};
		return getHibernateTemplate().findByNamedParam("select distinct model.hamlet.hamletId,model.hamlet.hamletName ," +
				"count( distinct model.voter.voterId), " +
				
				" SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
				" SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F'  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 22  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun , " +
				
				
				" SUM( CASE WHEN model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " +		
				" SUM( CASE WHEN model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCoun , " +	
				" SUM( CASE WHEN model.voter.age BETWEEN  31 and 45 THEN 1 ELSE 0 END) as ageCou , " +
				" SUM( CASE WHEN model.voter.age BETWEEN  46 and 60 THEN 1 ELSE 0 END) as ageCo , " +
				" SUM( CASE WHEN model.voter.age >60 THEN 1 ELSE 0 END) as ageC " +
				
				"from UserVoterDetails model  where  model.voter.voterId in(:ids) " +
							" and model.hamlet.hamletId is not null group by model.hamlet.hamletId order by model.hamlet.hamletName","ids",voterIds);
	}
	public List<Object> getHamletsIdsForUserByPanchayat(Long panchayatId , Long userId ) {
		Object[] param = {panchayatId,userId};
		return getHibernateTemplate().find("select distinct model.voter.voterId " +
							"from UserVoterDetails model join model.hamlet.panchayathHamlets p  where  p.panchayat.panchayatId = ? and model.user.userId = ?  ",param);
	}
	 public List<?> getVotersCountByHamlet(Long hamletId,Long userID)
				 {  
		 
			String queryString = "select count (distinct model.voter.voterId) from UserVoterDetails model " +
					"where model.hamlet.hamletId = ? and model.user.userId = ? group by model.hamlet.hamletId ";

			Query query = getSession().createQuery(queryString);

			query.setParameter(0, hamletId);
			query.setParameter(1, userID);
			

			return query.list();
		 
	 }  
	 public List<Object[]> getTotalVotersCountInABooth(Long userId ,Long boothId,Long publicationDateId)
		{
			
			StringBuilder query = new StringBuilder();
			
			query.append("select distinct model.hamlet.hamletName , " +
					"SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
					"SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount " +
					"from UserVoterDetails model , " +
					"BoothPublicationVoter model1 " +
					   " join model.hamlet " +
					" where model.voter.voterId = model1.voter.voterId and " +
					" model1.booth.publicationDate.publicationDateId = :publicationDateId and " +
					" model1.booth.boothId = :boothId " +
					"and model.user.userId = :userId and model.hamlet.hamletId is not null " +
					"group by model.hamlet.hamletName ");
			
			
			
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("boothId", boothId);
			queryObj.setParameter("userId", userId);
			return queryObj.list();
			
			/*Query query = getSession().createQuery("select distinct model.locality.name," +
					"SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
					"SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount " +
					" from UserVoterDetails model  " +
			        " join model.locality " +
					"where model.voter.voterId in(:voterIds)    group by model.locality.name ");*/
			
		}
		public List<Long> getUserHamletsByBoothId(Long userId , Long boothId , Long pubId)
		{
			
			
			Query query = getSession().createQuery("select distinct model.hamlet.hamletId from UserVoterDetails model," +
					"BoothPublicationVoter model1 "+					
					" where model.user.userId = :userId and model.voter.voterId = model1.voter.voterId and " +
					" model1.booth.publicationDate.publicationDateId = :publicationDateId and " +
					"model.hamlet.hamletId is not null and "+
					" model1.booth.boothId = :boothId");
			
			query.setParameter("userId", userId);
			query.setParameter("boothId", boothId);
			query.setParameter("publicationDateId", pubId);
			
			return query.list();
		}
		 @SuppressWarnings("unchecked")
		public List<Object[]> getTotalVotersCountInABoothForHamlet(Long userId ,Long id,Long publicationDateId,String type,Long constituencyId)
			{
				StringBuilder query = new StringBuilder();
				query.append("select distinct concat('Booth-',model1.booth.partNo) ,SUM( CASE WHEN model1.voter.gender='F' THEN 1 ELSE 0 END) as femalecount , ");
				query.append(" SUM( CASE WHEN model1.voter.gender='M' THEN 1 ELSE 0 END) as malecount ");
				query.append(" from UserVoterDetails model , BoothPublicationVoter model1 join model1.booth ");
				query.append(" where model.voter.voterId = model1.voter.voterId and model.user.userId = :userId and ");
				query.append(" model1.booth.publicationDate.publicationDateId = :publicationDateId and ");
				if(type.equalsIgnoreCase(IConstants.HAMLET))
				 query.append(" model.hamlet.hamletId = :id ");
				else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
					query.append(" model.ward.constituencyId = :id and model1.booth.constituency.constituencyId = :constituencyId");
				
				query.append(" group by model1.booth.partNo");
				
				Query queryObj = getSession().createQuery(query.toString()) ;
				if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
				{
					queryObj.setParameter("constituencyId", constituencyId);
				}
				queryObj.setParameter("publicationDateId", publicationDateId);
				queryObj.setParameter("id", id);
				queryObj.setParameter("userId", userId);
				return queryObj.list();
		
				
			}
		 
			public List<Long> getUserBoothsByHamletId(Long userId , Long hamletId , Long pubId ,String con)
			{
				
				//18111pending
				Query query = getSession().createQuery("select distinct model1.booth.boothId" +
						" from UserVoterDetails model," +
						"BoothPublicationVoter model1 "+					
						" where model.user.userId = :userId and model.voter.voterId = model1.voter.voterId and " +
						" model1.booth.publicationDate.publicationDateId = :publicationDateId and " +
						" "+con );
				
				query.setParameter("userId", userId);
				query.setParameter("hamletId", hamletId);
				query.setParameter("publicationDateId", pubId);
				
				return query.list();
			}
			public List<Object[]> getVoterIdsForuserinHamletByBoothsandByCasteId(Long userId ,Long hamletId,Long casteStateId ,long boothId , long publicationId)
			{ 
				
				Query query = getSession().createQuery("select model.voter,model1.booth.boothId,model.mobileNo from UserVoterDetails model, " +
						"BoothPublicationVoter model1 join model1.booth"+	
						" where model.voter.voterId = model1.voter.voterId " +
						"and  model.user.userId = :userId " +
						"and model.hamlet.hamletId =:hamletId " +
						"and model.casteState.casteStateId = :casteStateId " +
						"and model1.booth.boothId= :boothId " +
						"");
				
				query.setParameter("boothId", boothId);
				query.setParameter("userId", userId);
				query.setParameter("hamletId", hamletId);
				query.setParameter("casteStateId", casteStateId);
				
				return query.list();
				
			}
			public List<Object[]> getAgeDataForBoothByHamlets(Long userId,Long publicationDateId,Long boothId ,String type) {
				  StringBuilder query = new StringBuilder();
				  if(type.equalsIgnoreCase("boothHamlets"))
					  query.append("select distinct model.hamlet.hamletId,model.hamlet.hamletName ,");
				  else if(type.equalsIgnoreCase("hamletBooths"))
					  query.append("select distinct model1.booth.boothId,concat('Booth-',model1.booth.partNo),");
				  
					  query.append("count( distinct model.voter.voterId), " );
						
				  query.append(	" SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," );
				  query.append	(" SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount , " );
				  query.append	(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F'  THEN 1 ELSE 0 END) as malecoun , " );
						
				  query.append(	" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(	" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(	" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 22  THEN 1 ELSE 0 END) as malecoun , " );
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as malecoun , ");
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  31 and 45  THEN 1 ELSE 0 END) as malecoun , " );
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun , " );
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun , " );
						
						
				  query.append(" SUM( CASE WHEN model.voter.age BETWEEN  18 and 22 THEN 1 ELSE 0 END) as ageCount , " );		
				  query.append(" SUM( CASE WHEN model.voter.age BETWEEN  23 and 30 THEN 1 ELSE 0 END) as ageCoun , " );
				  query.append(" SUM( CASE WHEN model.voter.age BETWEEN  31 and 45 THEN 1 ELSE 0 END) as ageCou , " );
				  query.append(" SUM( CASE WHEN model.voter.age BETWEEN  46 and 60 THEN 1 ELSE 0 END) as ageCo , " );
				  query.append(" SUM( CASE WHEN model.voter.age >60 THEN 1 ELSE 0 END) as ageC " );
						
				  query.append("from UserVoterDetails model , " );
				  query.append("BoothPublicationVoter model1 " );
				  if(type.equalsIgnoreCase("boothHamlets"))
				  query.append(" join model.hamlet " );
				  else if(type.equalsIgnoreCase("hamletBooths"))
					  query.append(" join model1.booth " );
						   query.append(" where model.voter.voterId = model1.voter.voterId and " );
						   query.append(" model1.booth.publicationDate.publicationDateId = :publicationDateId and " );
						  
						   if(type.equalsIgnoreCase("boothHamlets"))
						   query.append(" model1.booth.boothId = :boothId " );
						   else if(type.equalsIgnoreCase("hamletBooths"))
							   query.append(" model.hamlet.hamletId = :boothId " );

						   query.append("and model.user.userId = :userId " );
						   
						   if(type.equalsIgnoreCase("boothHamlets")){
							 query.append("and model.hamlet.hamletId is not null ");
						   query.append("group by model.hamlet.hamletId order by model.hamlet.hamletName");
						   
						   }
						   else if(type.equalsIgnoreCase("hamletBooths"))
							   query.append("group by model1.booth.boothId order by model1.booth.partNo");

						   Query queryObj = getSession().createQuery(query.toString()) ;
						   queryObj.setParameter("publicationDateId", publicationDateId);
				queryObj.setParameter("boothId", boothId);
				queryObj.setParameter("userId", userId);
				return queryObj.list();
				
}
			
			
			public List<Long> getHamletsExistedInABoothForUser(Long userId,Long  id,Long  publicationDateId,Long  constituencyId)
			{
				
				Query query = getSession().createQuery("select distinct(model1.hamlet.hamletId) from UserVoterDetails model1 , " +
						"BoothPublicationVoter model2 where model1.voter.voterId = model2.voter.voterId and " +
						"model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
						"model1.user.userId = :userId and model2.booth.boothId = :boothId and " +
						"model2.booth.constituency.constituencyId = :constituencyId and model1.hamlet.hamletId is not null ");
				
				
				query.setParameter("publicationDateId", publicationDateId);
				query.setParameter("userId", userId);
				query.setParameter("boothId", id);
				query.setParameter("constituencyId", constituencyId);
				
				return query.list();
				
			}

			@SuppressWarnings("unchecked")
			public List<Object[]> getVoterDataForHamlet(Long hamletId,
					Long userId, Long startIndex, Long maxIndex,
					String sort, String order) {
				
				Query query = getSession().createQuery("select model.voter from UserVoterDetails model where " +
						"model.hamlet.hamletId = :hamletId and model.user.userId = :userId order by model.voter."+sort+" "+order);
				
				query.setParameter("userId", userId);
				query.setParameter("hamletId", hamletId);
				query.setFirstResult(startIndex.intValue());
				query.setMaxResults(maxIndex.intValue());
				return query.list();
			}
			
	/**
 	* getAgeDataForPanchayatUser method is an overload method is used to get panchayathWise 
	* voters total count based on ages
	* @author srishailam
	* @param List<?>
	* @param String
	* @param String
	* @param Long varargs array[]
	* @return Object[]  
	*/
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeDataForPanchayatUser(List<Long> ids , Long publicationDateId , List<?> voterIds,Long userId,String type,String male,String female,long ...ages){
		StringBuilder query = new StringBuilder();
		Query queryObject = null;
		  if(type.equalsIgnoreCase(IConstants.HAMLET))
				
				query.append("select distinct model.hamlet.hamletId,model.hamlet.hamletName ," );
						
				   else if (type.equalsIgnoreCase(IConstants.CUSTOMWARD))
					   query.append("select distinct model.ward.constituencyId,model.ward.name ,");
							  
		  query.append(" count(model.voter.voterId), ");	   
		
		query.append(getAgeQuery(null,"GENDER"));	
		if(ages.length%2==0)
			for(int i=0;i<ages.length;){				
				query.append(getAgeQuery("AGE"+ages[i],"AGE"+ages[i+1]));
				i=i+2;
			}			
		query.append(getAgeQuery("AGE"+ages[ages.length-1],null));				
		query.append(getAllAgesQuery(ages));
		query.append(" from UserVoterDetails model  ,BoothPublicationVoter model1 where   model.user.userId = :userid and model.voter.voterId = model1.voter.voterId and model1.booth.publicationDate.publicationDateId = :publicationDateId and");
		 if(type.equalsIgnoreCase(IConstants.HAMLET))
		query.append(" model.hamlet.hamletId in(:ids) and  model.hamlet.hamletId is not null group by model.hamlet.hamletId order by model.hamlet.hamletName");
		  else if (type.equalsIgnoreCase(IConstants.CUSTOMWARD))
		query.append(" model.ward.constituencyId in(:ids) and model.ward.constituencyId is not null group by model.ward.constituencyId order by model.ward.name");	
		queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter("male", male);
		queryObject.setParameter("female", female);			
		//queryObject.setParameterList("voterIds", voterIds);
		queryObject.setParameter("userid", userId);	
		queryObject.setParameterList("ids", ids);	
		queryObject.setParameter("publicationDateId", publicationDateId);
		for(int i=0;i<ages.length;i++)
				queryObject.setParameter("AGE"+ages[i], ages[i]);
		return queryObject.list();
	}
	/**
	 * getAgeDataForBoothByHamlets this method is an overload method is used to get BoothByHamlet Wise 
	 * voters total count based on ages
	 * @author srishailam
	 * @param Long
	 * @param Long
	 * @param Long
	 * @param String
	 * @param String
	 * @param String
	 * @param Long varargs array[]
	 * @return Object[]  
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeDataForBoothByHamlets(Long constituencyId,Long userId,Long publicationDateId,Long boothId ,String type,String male,String female,long ...ages) {
		  StringBuilder query = new StringBuilder();
		  if(type.equalsIgnoreCase("boothHamlets"))
			  query.append("select distinct model.hamlet.hamletId,model.hamlet.hamletName ,");
		  else if(type.equalsIgnoreCase("hamletBooths") || type.equalsIgnoreCase("wardBooths") )
			  query.append("select distinct model1.booth.boothId,concat('Booth-',model1.booth.partNo),");
			  query.append("count( distinct model.voter.voterId), " );
			  query.append(getAgeQuery(null,"GENDER"));	
			  if(ages.length%2==0)
				for(int i=0;i<ages.length;){				
					query.append(getAgeQuery("AGE"+ages[i],"AGE"+ages[i+1]));
					i=i+2;
				}			
			query.append(getAgeQuery("AGE"+ages[ages.length-1],null));				
			query.append(getAllAgesQuery(ages));		
			query.append("from UserVoterDetails model , " );
			query.append("BoothPublicationVoter model1 " );
		  if(type.equalsIgnoreCase("boothHamlets"))
			query.append(" join model.hamlet " );
		  else if(type.equalsIgnoreCase("hamletBooths")  || type.equalsIgnoreCase("wardBooths"))
			query.append(" join model1.booth " );
			query.append(" where model.voter.voterId = model1.voter.voterId and " );
			query.append(" model1.booth.publicationDate.publicationDateId = :publicationDateId and " );
				  
		  if(type.equalsIgnoreCase("boothHamlets"))
			query.append(" model1.booth.boothId = :boothId  " );
		 else if(type.equalsIgnoreCase("hamletBooths"))
			query.append(" model.hamlet.hamletId = :boothId " );
		 else if( type.equalsIgnoreCase("wardBooths"))
			 query.append(" model.ward.constituencyId = :boothId  and model1.booth.constituency.constituencyId = :constituencyId  " );

			query.append("and model.user.userId = :userId " );
				   
		if(type.equalsIgnoreCase("boothHamlets")){
			query.append("and model.hamlet.hamletId is not null ");
			query.append("group by model.hamlet.hamletId order by model.hamlet.hamletName");
		}
		else if(type.equalsIgnoreCase("hamletBooths"))
			query.append("group by model1.booth.boothId order by model1.booth.partNo");
		
		else if(type.equalsIgnoreCase("wardBooths"))
			query.append("group by model1.booth.boothId  order by model1.booth.partNo");
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		if(type.equalsIgnoreCase("wardBooths")){
		queryObj.setParameter("constituencyId", constituencyId);
		}
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("boothId", boothId);
		queryObj.setParameter("userId", userId);
		queryObj.setParameter("male", male);
		queryObj.setParameter("female", female);	
		for(int i=0;i<ages.length;i++)
			queryObj.setParameter("AGE"+ages[i], ages[i]);
		return queryObj.list();
	}
	/**
	 * getAgeWiseInfoForUser method is an overload method is used to get AgeWiseInfoForUser 
	 * voters total count based on ages
	 * @author srishailam
	 * @param List<?>
	 * @param String
	 * @param String
	 * @param Long varargs array[]
	 * @return Object[]  
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeWiseInfoForUser(List<?> voterIds,Long userId,String male,String female,long ...ages) {
		StringBuilder query = new StringBuilder();
		query.append(" select count( distinct model.voter.voterId), ");
			if(ages.length%2==0)
				for(int i=0;i<ages.length;){				
					query.append(getAgeQuery("AGE"+ages[i],"AGE"+ages[i+1]));
					i=i+2;
			}			
			query.append(getAgeQuery("AGE"+ages[ages.length-1],null));				
			query.append(getAllAgesQuery(ages));							
			query.append(" from UserVoterDetails model  where  model.voter.voterId in(:voterIds)  and model.user.userId = :id ");
			Query queryObject = getSession().createQuery(query.toString());		
			queryObject.setParameterList("voterIds", voterIds);
			queryObject.setParameter("male", male);
			queryObject.setParameter("female", female);
			queryObject.setParameter("id", userId);
			for(int i=0;i<ages.length;i++)
				queryObject.setParameter("AGE"+ages[i], ages[i]);
		return queryObject.list();					
	}
	/**
	 * getLocalityIdsForUser method is an overload method is used to get LocalityWise 
	 * voters total count based on ages
	 * @author srishailam
	 * @param Long
	 * @param Long
	 * @param List<?>
	 * @param String
	 * @param String
	 * @param Long varargs array[]
	 * @return Object[]  
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityIdsForUser(Long hamletId , Long userId,List<?> voterIds,String male,String female,long ...ages) {
		StringBuilder query = new StringBuilder();
		query.append(" select distinct model.locality.localityId,model.locality.name , count( distinct model.voter.voterId), " );
		query.append(getAgeQuery(null,"GENDER"));	
		if(ages.length%2==0)
				for(int i=0;i<ages.length;){				
					query.append(getAgeQuery("AGE"+ages[i],"AGE"+ages[i+1]));
					i=i+2;
			}			
			query.append(getAgeQuery("AGE"+ages[ages.length-1],null));				
			query.append(getAllAgesQuery(ages));						
		
			query.append(" from UserVoterDetails model  where  model.voter.voterId in(:voterIds) and  model.user.userId = :id " +
						" and model.locality.localityId is not null group by model.locality.localityId order by model.locality.name");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter("male", male);
			queryObject.setParameter("female", female);			
			queryObject.setParameterList("voterIds", voterIds);
			queryObject.setParameter("id", userId);
			for(int i=0;i<ages.length;i++)
					queryObject.setParameter("AGE"+ages[i], ages[i]);
			return queryObject.list();	
	}
	
	/**
	 * getAgeQuery(String age1,String age2) method is used to get query for  between two ages
	 * @author srishialam
	 * @param long
	 * @param long
	 * @return String
	 */
	public String getAgeQuery(String age1,String age2){	
		String query =null;
		if(age1 != null && age2 != null)
			query=" SUM( CASE WHEN model.voter.gender=:female and model.voter.age BETWEEN  :"+age1+" and :"+age2+" THEN 1 ELSE 0 END) as ageCount , " +	
					" SUM( CASE WHEN model.voter.gender=:male and model.voter.age BETWEEN  :"+age1+" and :"+age2+" THEN 1 ELSE 0 END) as ageCount , " +	
					" SUM( CASE WHEN model.voter.gender !=:male and model.voter.gender !=:female and model.voter.age BETWEEN  :"+age1+" and :"+age2+"  THEN 1 ELSE 0 END) as malecoun , ";
		else if(age1!= null && age2 == null)
			query =" SUM( CASE WHEN model.voter.gender=:female and model.voter.age > :"+age1+"  THEN 1 ELSE 0 END) as ageCount , " +	
					" SUM( CASE WHEN model.voter.gender=:male and model.voter.age > :"+age1+"  THEN 1 ELSE 0 END) as ageCount , " +
					" SUM( CASE WHEN model.voter.gender !=:male and model.voter.gender !=:female and model.voter.age > :"+age1+"  THEN 1 ELSE 0 END) as malecoun , " ;
		else if(age1== null && age2 != null)
			query = " SUM( CASE WHEN model.voter.gender=:female THEN 1 ELSE 0 END) as femalecount ," +
					" SUM( CASE WHEN model.voter.gender=:male THEN 1 ELSE 0 END) as malecount , " +
					" SUM( CASE WHEN model.voter.gender !=:male and model.voter.gender != :female  THEN 1 ELSE 0 END) as malecoun , ";
		return query;
	}
	/**
	 * getAllAgesQuery(long ...ages) method is used to get query for all ages
	 * @author Srishailam
	 * @param long[] (varargs array)
	 * @return String
	 */
	public String getAllAgesQuery(long ...ages){
		StringBuilder query = new StringBuilder();
		if(ages != null && ages.length>0)
			for(int i=0;i<ages.length;){
				query.append(" SUM( CASE WHEN model.voter.age BETWEEN  :AGE"+ages[i]+" and :AGE"+ages[i+1]+" THEN 1 ELSE 0 END) as ageCount , ") ;
				i=i+2;
			}
		query.append(" SUM( CASE WHEN model.voter.age > :AGE"+ages[ages.length-1]+" THEN 1 ELSE 0 END) as ageC ");
		return query.toString();
	}
	 /*public List<Object[]> getWardsBYLocalElectionBodyId(Long id , Long publicationId ,Long userId)
	 {
		 Query query = getSession().createQuery("select distinct model.ward.constituencyId , model.ward.name " +
					" from UserVoterDetails model," +
					" BoothPublicationVoter model1 "+					
					" where model.user.userId = :userId and model.voter.voterId = model1.voter.voterId and " +
					"  model1.booth.publicationDate.publicationDateId = :publicationDateId and model.ward.localElectionBody.localElectionBodyId = :id and " +
					" model.ward.constituencyId is not null order by model.ward.name ");
		 
		 query.setParameter("publicationDateId", publicationId);
		 query.setParameter("id", id);
		 query.setParameter("userId", userId);
		 
		 return query.list();
		 
	 }*/
	 
	@SuppressWarnings("unchecked")
	 public List<Object[]> getWardsBYLocalElectionBodyId(Long id , Long publicationId ,Long userId)
	 {
		 Query query = getSession().createQuery(" select distinct UVD.ward.constituencyId, UVD.ward.name from UserVoterDetails UVD, BoothPublicationVoter BPV where " +
		 		" UVD.voter.voterId = BPV.voter.voterId and BPV.booth.localBody.localElectionBodyId = :localElectionBodyId and BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
		 		" UVD.user.userId = :userId order by UVD.ward.constituencyId ");
		 
		 query.setParameter("publicationDateId", publicationId);
		 query.setParameter("localElectionBodyId", id);
		 query.setParameter("userId", userId);
		 
		 return query.list();
	 }
	 
	 public List<?> getVoterIdsBYLocalElectionBodyId(Long id , Long publicationId ,Long userId ,String type)
	 {
		 Query query = getSession().createQuery("select distinct model.voter.voterId " +
					" from UserVoterDetails model," +
					" BoothPublicationVoter model1 "+					
					" where model.user.userId = :userId and model.voter.voterId = model1.voter.voterId and " +
					"  model1.booth.publicationDate.publicationDateId = :publicationDateId and model.ward.localElectionBody.localElectionBodyId = :id and " +
					" model.ward.constituencyId is not null ");
		 
		 query.setParameter("publicationDateId", publicationId);
		 query.setParameter("id", id);
		 query.setParameter("userId", userId);
		 
		 return query.list();
		 
	 }
	
	/**
	 * This DAO is Uesd For Getting all voter gender details count for a customward
	 * @param Long id
	 * @param Long publicationDateId
	 * @return List<Object[]>
	 * @date 25-04-2014
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getGenderWiseVoterDetailsForCustomWard(Long id , Long publicationDateId , Long userId )
	{
		String queryString = "select count(*) , model.voter.gender  from UserVoterDetails model " +
				",BoothPublicationVoter BPV where model.user.userId = :userId and model.voter.voterId = BPV.voter.voterId and " +
				"model.ward.constituencyId = :id and " +
				"BPV.booth.publicationDate.publicationDateId = :publicationDateId group by model.voter.gender";
				
		 Query query = getSession().createQuery(queryString);
		 query.setParameter("id", id);
		 query.setParameter("publicationDateId", publicationDateId);
		 query.setParameter("userId", userId);
		return query.list();
	}

	/**
	 * This DAO is Uesd For Getting all voters count in a family
	 * @param List<Long> wardIds
	 * @param Long publicationDateId
	 * @param String queryString
	 * @return List<Object[]>
	 * @date 25-04-2014
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getImpFamilesForCustomWard(List<Long> wardIds,
			Long publicationDateId, String queryString , Long userId) {
		StringBuilder query = new StringBuilder();
		query.append("select count(model.voter.voterId),model.voter.houseNo from UserVoterDetails UVD ,BoothPublicationVoter model " +
				"where model.user.userId = :userId and UVD.voter.voterId = model.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				"UVD.ward.constituencyId in (:wardIds)  group by model.voter.houseNo ") ;
		if(queryString != null)
			query.append(queryString);
		
		Query queryObj = getSession().createQuery(query.toString()) ;
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("wardIds",wardIds);
		queryObj.setParameter("userId", userId);
		  return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByCustomWardId(Long customWardId,Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery(" select distinct BPV.booth.boothId from BoothPublicationVoter BPV, UserVoterDetails UVD where " +
				" BPV.voter.voterId = UVD.voter.voterId and BPV.booth.constituency.constituencyId = :constituencyId and " +
				" BPV.booth.publicationDate.publicationDateId = :publicationDateId and UVD.ward.constituencyId = :customWardId ");
		query.setParameter("customWardId",customWardId);
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllLocalitiesForHamletOrWard(String type,Long userId, Long id,Long publicationDateId,String queryCond)
	{
		StringBuilder queryString = new StringBuilder();
		if(type.equalsIgnoreCase("muncipalityCustomWard"))
			queryString.append(" select distinct model.ward.constituencyId,model.ward.name ");
		else
			queryString.append(" select distinct model.locality.localityId,model.locality.name ");
		
		queryString.append(" from UserVoterDetails model,BoothPublicationVoter model1 where model.user.userId =:userId and ");
		queryString.append(" model1.voter.voterId = model.voter.voterId and model1.booth.publicationDate.publicationDateId = :publicationDateId and ");
		queryString.append( queryCond);
		Query queryObj = getSession().createQuery(queryString.toString());
		queryObj.setParameter("id", id);
		queryObj.setParameter("userId", userId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		return queryObj.list();
		
	}
	public List<Object[]> getAgeDataForWARDUser(List<?> voterIds,Long userId,String male,String female,long ...ages){
		StringBuilder query = new StringBuilder();
		Query queryObject = null;
		query.append("select distinct model.ward.constituencyId,model.ward.name ," +
					"count( distinct model.voter.voterId), ");
		query.append(getAgeQuery(null,"GENDER"));	
		if(ages.length%2==0)
			for(int i=0;i<ages.length;){				
				query.append(getAgeQuery("AGE"+ages[i],"AGE"+ages[i+1]));
				i=i+2;
			}			
		query.append(getAgeQuery("AGE"+ages[ages.length-1],null));				
		query.append(getAllAgesQuery(ages));
		query.append("from UserVoterDetails model  where  model.voter.voterId in(:voterIds) and model.user.userId = :id " +
					" and model.ward.constituencyId is not null group by model.ward.constituencyId order by model.ward.name");
							
		queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter("male", male);
		queryObject.setParameter("female", female);			
		queryObject.setParameterList("voterIds", voterIds);
		queryObject.setParameter("id", userId);		
		for(int i=0;i<ages.length;i++)
				queryObject.setParameter("AGE"+ages[i], ages[i]);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatWiseHamletsAssignedDetailsInAConstituency(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select B.tehsil.tehsilId, B.tehsil.tehsilName, B.panchayat.panchayatId, B.panchayat.panchayatName,VI.totalVoters, count(UVD.voter.voterId) from UserVoterDetails UVD, Booth B, VoterInfo VI, " +
				" PanchayatHamlet PH where B.panchayat.panchayatId = PH.panchayat.panchayatId and PH.hamlet.hamletId = UVD.hamlet.hamletId and B.panchayat.panchayatId = VI.reportLevelValue and B.constituency.constituencyId = :constituencyId " +
				" and VI.voterReportLevel.reportLevel = :reportLevel and B.publicationDate.publicationDateId = :publicationDateId group by B.panchayat.panchayatId ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("reportLevel","panchayat");
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothsForCustomWard(Long wardId,Long constituencyId,Long publicationDateId,Long userId)
	{
		Query queryObj = getSession().createQuery("select distinct BPV.booth.boothId,BPV.booth.partNo from BoothPublicationVoter BPV , UserVoterDetails UVD where " +
				" BPV.voter.voterId = UVD.voter.voterId and UVD.ward.constituencyId =:wardId and UVD.user.userId =:userId and BPV.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and BPV.booth.constituency.constituencyId =:constituencyId order by BPV.booth.partNo ");
		
		queryObj.setParameter("wardId", wardId);
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("userId", userId);
		
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothsForCustomWardIdsList(List<Long> wardIdsList,Long constituencyId,Long publicationDateId,Long userId)
	{
		Query queryObj = getSession().createQuery("select UVD.ward.constituencyId, BPV.booth.boothId,BPV.booth.partNo from BoothPublicationVoter BPV , UserVoterDetails UVD where " +
				" BPV.voter.voterId = UVD.voter.voterId and UVD.ward.constituencyId in(:wardIdsList) and UVD.user.userId =:userId and BPV.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and BPV.booth.constituency.constituencyId =:constituencyId group by UVD.ward.constituencyId, BPV.booth.boothId order by BPV.booth.boothId ");
		
		queryObj.setParameterList("wardIdsList", wardIdsList);
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("userId", userId);
		
		return queryObj.list();
	}
	
	public List<Object[]> getPanchayatWiseHamletsAssignedDetails(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("select B.panchayat.panchayatId,B.panchayat.panchayatName,UVD.hamlet.hamletId,UVD.hamlet.hamletName,Count(UVD.voter.voterId) from UserVoterDetails UVD, BoothPublicationVoter BPV, Booth B where " +
				" UVD.voter.voterId = BPV.voter.voterId and BPV.booth.boothId = B.boothId and UVD.hamlet is not null and UVD.user.userId = :userId and B.constituency.constituencyId = :constituencyId and B.publicationDate.publicationDateId = :publicationDateId " +
				" group by UVD.hamlet.hamletId order by B.tehsil.tehsilName,B.panchayat.panchayatName,UVD.hamlet.hamletName ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId",userId);
		return query.list();
	}
	
	/**
	 * This DAO is Used For Getting Count For Selected Type (Cadre,InfluencingPeople and Candiadte..)
	 * @param Long hamletId
	 * @param Long userId
	 * @param String type
	 * @return List<Cadre>
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getCountForSelectedTypeInHamlet(Long hamletId,Long userId,String type,String selLevel)
	{
		StringBuffer queryString = new StringBuffer();
		if(type.equalsIgnoreCase("InfluencePeople"))
		{
			queryString.append("select count(model.influencingPeopleId) from InfluencingPeople model,");
		}
		else if(type.equalsIgnoreCase("Cadre"))
		{
			queryString.append("select count(model.cadreId) from Cadre model,");
		}
		else if(type.equalsIgnoreCase("Politician"))
		{
			queryString.append("select count(model.candidateId) from Candidate model,");
		}
		queryString.append(" UserVoterDetails UVD where " +
		" model.voter.voterId = UVD.voter.voterId  and UVD.user.userId = :userId  ");
		if(selLevel.equalsIgnoreCase("hamlet"))
		{
			queryString.append(" and UVD.hamlet.hamletId = :hamletId");
		}
		else
		{
			queryString.append(" and UVD.ward.constituencyId = :hamletId");
		}
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		return query.list();
	}
	/**
	 * This DAO is Used For Getting Cadre Details For Selected Hamlet
	 * @param Long hamletId
	 * @param Long userId
	 * @return List<Cadre>
	 */
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreDetailsForSelectedHamlet(Long hamletId,Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model from Cadre model,UserVoterDetails UVD where " +
				" model.voter.voterId = UVD.voter.voterId  and UVD.user.userId = :userId  ");
		if(selLevel.equalsIgnoreCase("hamlet"))
		{
			queryString.append(" and UVD.hamlet.hamletId = :hamletId");
		}
		else
		{
			queryString.append(" and UVD.ward.constituencyId = :hamletId");
		}
		
		
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
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		return query.list();
	}

	/**
	 * This DAO is Used For Getting InfluencingPeople Details For Selected Hamlet
	 * @param Long hamletId
	 * @param Long userId
	 * @return List<InfluencingPeople>
	 */
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> getInfluencingPeopleDetailsForSelectedHamlet(
			Long hamletId, Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model from InfluencingPeople model,UserVoterDetails UVD where " +
				" model.voter.voterId = UVD.voter.voterId  and UVD.user.userId = :userId  ");
		if(selLevel.equalsIgnoreCase("hamlet"))
		{
			queryString.append(" and UVD.hamlet.hamletId = :hamletId");
		}
		else
		{
			queryString.append(" and UVD.ward.constituencyId = :hamletId");
		}
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
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		return query.list();
	}

	/**
	 * This DAO is Used For Getting Candidate Details For Selected Hamlet
	 * @param Long hamletId
	 * @param Long userId
	 * @return List<Candidate>
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateDetailsForSelectedHamlet(Long hamletId,
			Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model from Candidate model,UserVoterDetails UVD where " +
				" model.voter.voterId = UVD.voter.voterId  and UVD.user.userId = :userId  ");
		if(selLevel.equalsIgnoreCase("hamlet"))
		{
			queryString.append(" and UVD.hamlet.hamletId = :hamletId");
		}
		else
		{
			queryString.append(" and UVD.ward.constituencyId = :hamletId ");
		}
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
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	
	public List<Object[]> getVotersCountBasedOnGenderForSelectedWard(Long userId,Long customWardId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select count(UVD.voter.voterId),UVD.voter.gender from UserVoterDetails UVD ,BoothPublicationVoter BPV" +
				" where UVD.voter.voterId = BPV.voter.voterId and UVD.user.userId = :userId and " +
				" UVD.ward.constituencyId = :customWardId and BPV.booth.publicationDate.publicationDateId = :publicationDateId " +
				" group by UVD.voter.gender");
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("customWardId", customWardId);
		return query.list();
	}
	
	public List<Object> getDistinctWardsOfLocalElectionBodyId(Long id,Long publicationDateId,Long userId){
		Query query = getSession().createQuery("select count(distinct model.ward.constituencyId) from UserVoterDetails model, " +
				" BoothPublicationVoter model1 " +
				" where model.user.userId = :userId and model.voter.voterId = model1.voter.voterId and " +
				"  model1.booth.publicationDate.publicationDateId = :publicationDateId and model.ward.localElectionBody.localElectionBodyId = :id " +
				" and model.ward is not null");
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("id", id);
		return query.list();
	}
	
	public List<Object[]> getCountDetailsInSelectdCustomWard(List<Long> boothIds,Long userId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select count(UVD.voter.voterId),BPV.booth.boothId from UserVoterDetails UVD ,BoothPublicationVoter BPV " +
				" where  UVD.voter.voterId = BPV.voter.voterId and  BPV.booth.boothId in (:boothIds) and " +
				" UVD.user.userId =:userId  and BPV.booth.publicationDate.publicationDateId = :publicationDateId " +
				"  group by BPV.booth.boothId");
		query.setParameterList("boothIds", boothIds);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	/**
	 * This DAO  is used For Getting Age Wise Details(18-22,23-30 ..) for Seleted custom ward in the Selected Muncipality
	 * @param Long wardId
	 * @param Long userId
	 * @param Long publicationDateId
	 * @param Long minAge
	 * @param Long maxAge
	 * @param Long constituencyId
	 * @return List<Object[]>
	 */
	public List<Object[]> getAgeWiseDetailsInSelectdCustomWard(Long wardId,Long userId,Long publicationDateId,Long minAge,Long maxAge,Long constituencyId)
	{
		Query query = getSession().createQuery("select BPV.booth.boothId,count(UVD.voter.voterId),BPV.booth.partNo from UserVoterDetails UVD ,BoothPublicationVoter BPV " +
				" where  UVD.voter.voterId = BPV.voter.voterId and  UVD.ward.constituencyId =:wardId and " +
				" UVD.user.userId =:userId  and BPV.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and UVD.voter.age between :minAge and :maxAge and BPV.booth.constituency.constituencyId = :constituencyId " +
				" group by BPV.booth.boothId order by BPV.booth.partNo ");
		query.setParameter("wardId", wardId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("minAge", minAge);
		query.setParameter("maxAge", maxAge);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	/**
	 * This DAO  is used For Getting Age Wise Details(Above 60) for Seleted custom ward in the Selected Muncipality
	 * @param Long wardId
	 * @param Long userId
	 * @param Long publicationDateId
	 * @param Long age
	 * @param Long constituencyId
	 * @return List<Object[]>
	 */
	public List<Object[]> getAbove60AgeWiseDetailsInSelectdCustomWard(Long wardId,Long userId,Long publicationDateId,Long age,Long constituencyId)
	{
		Query query = getSession().createQuery("select BPV.booth.boothId,count(UVD.voter.voterId),BPV.booth.partNo from UserVoterDetails UVD ,BoothPublicationVoter BPV " +
				" where  UVD.voter.voterId = BPV.voter.voterId and  UVD.ward.constituencyId =:wardId and " +
				" UVD.user.userId =:userId  and BPV.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and UVD.voter.age >= :age and BPV.booth.constituency.constituencyId = :constituencyId  " +
				" group by BPV.booth.boothId order by BPV.booth.partNo");
		query.setParameter("wardId", wardId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("age", age);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	public List<Long> getBoothsInACustomWard(Long wardId,Long userId,Long publicationDateId,Long constituencyId)
	{
		
		Query query = getSession().createQuery("select distinct BPV.booth.boothId from UserVoterDetails UVD ,BoothPublicationVoter BPV " +
				" where BPV.voter.voterId = UVD.voter.voterId and UVD.ward.constituencyId = :wardId " +
				" and UVD.user.userId =:userId and BPV.booth.publicationDate.publicationDateId = :publicationDateId " +
				" and BPV.booth.constituency.constituencyId = :constituencyId  order by BPV.booth.boothId");
		query.setParameter("wardId", wardId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}

	//get voter details in a caste by caste stateId and boothId and publicationId
	public List<Object[]> getVoterDetailsForCustomWardByBooth(Long boothId,Long publicationDateId,Long userId,Long casteStateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.voter,model.booth.boothId,model2.mobileNo from BoothPublicationVoter model,UserVoterDetails model2 ");
		str.append(" where model2.user.userId = :userId and model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId and model2.casteState.casteStateId =:casteStateId and");
		str.append(" model.booth.boothId = :boothId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("boothId", boothId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("casteStateId", casteStateId);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getcasteForVoter(List<Long> voterIds,Long userId)
	{
		Query query = getSession().createQuery("select distinct model.casteState.caste.casteName,model.voter.voterId from UserVoterDetails model" +
				" where model.voter.voterId in (:voterIds) and model.user.userId = :userId");
		query.setParameterList("voterIds", voterIds);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteForVoterList(List<Long> voterIds,Long userId)
	{
		Query query = getSession().createSQLQuery("SELECT UVD.voter_id,C.caste_name FROM user_voter_details UVD,caste_state CS,caste C where UVD.caste_state_id = CS.caste_state_id and " +
				" CS.caste_id = C.caste_id and UVD.user_id = :userId and UVD.voter_id in (:voterIds) and UVD.caste_state_id is NOT NULL ");
		query.setParameterList("voterIds",voterIds);
		query.setParameter("userId", userId);
		return query.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterNamesByVoterIdsList(List<Long> voterIds)
	{
		Query query = getSession().createSQLQuery("SELECT DISTINCT VN.voter_id,VN.firstname,VN.lastname FROM voter_names VN where VN.voter_id in (:voterIds)");
		query.setParameterList("voterIds",voterIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getWardsCountByLocalEleBodyId(Long localEleBodyId,Long constituencyId,Long userId,Long publicationDateId)
	{
		Query query = getSession().createQuery(" select count(distinct model.ward.constituencyId) from UserVoterDetails model, BoothPublicationVoter model2 " +
				" where model2.voter.voterId = model.voter.voterId and model.user.userId =:userId and model2.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.localBody.localElectionBodyId =:localEleBodyId and model.ward.constituencyId is not null ");
		
		query.setParameter("userId", userId);
		query.setParameter("localEleBodyId", localEleBodyId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletOrWardList(Long userId,Long Id,String type)
	{
		StringBuffer queryString = new StringBuffer();
		if(type.equalsIgnoreCase("hamlet"))
		{
			queryString.append("select model.hamlet.hamletId , model.hamlet.hamletName from UserVoterDetails model ");
		}
		else
		{
			queryString.append("select model.ward.constituencyId , model.ward.name from UserVoterDetails model ");
		}
		queryString.append(" where model.user.userId = :userId and model.voter.voterId = :Id");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("userId", userId);
		query.setParameter("Id", Id);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothsCountForCustomWard(Long wardId,Long constituencyId,Long publicationDateId,Long userId)
	{
		Query queryObj = getSession().createQuery("select count(distinct BPV.booth.boothId) from BoothPublicationVoter BPV , UserVoterDetails UVD where " +
				" BPV.voter.voterId = UVD.voter.voterId and UVD.ward.constituencyId =:wardId and UVD.user.userId =:userId and BPV.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and BPV.booth.constituency.constituencyId =:constituencyId ");
		
		queryObj.setParameter("wardId", wardId);
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("userId", userId);
		
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalAreaWiseAgeDetailsForCustomWard(Long constituencyId,Long publicationDateId,Long customWardId,Long userId,String ageRange)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select distinct UVD.locality.localityId,UVD.locality.name,count(distinct UVD.voter.voterId),UVD.voter.gender from UserVoterDetails UVD,BoothPublicationVoter BPV where UVD.voter.voterId = BPV.voter.voterId ");
		stringBuilder.append(" and UVD.ward.constituencyId =:customWardId and BPV.booth.constituency.constituencyId =:constituencyId and BPV.booth.publicationDate.publicationDateId =:publicationDateId and UVD.user.userId =:userId ");
		
		if(ageRange.equalsIgnoreCase(IConstants.AGE18to22))
		  stringBuilder.append(" and UVD.voter.age >=18 and UVD.voter.age <=22 ");
		else if(ageRange.equalsIgnoreCase(IConstants.AGE23to30))
		  stringBuilder.append(" and UVD.voter.age >=23 and UVD.voter.age <=30 ");
		else if(ageRange.equalsIgnoreCase(IConstants.AGE31to45))
		  stringBuilder.append(" and UVD.voter.age >=31 and UVD.voter.age <=45 ");
		else if(ageRange.equalsIgnoreCase(IConstants.AGE46to60))
		  stringBuilder.append(" and UVD.voter.age >=46 and UVD.voter.age <=60 ");
		else if(ageRange.equalsIgnoreCase(IConstants.Above60))
		  stringBuilder.append(" and UVD.voter.age >60 ");
		
		stringBuilder.append(" group by UVD.locality.localityId,UVD.voter.gender ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("customWardId", customWardId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalAreaWiseAgeDetails(Long constituencyId,Long publicationDateId,Long id,Long userId,Long minAge,Long maxAge,String type)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if(type.equalsIgnoreCase("customWardLocalArea"))
		 stringBuilder.append("select distinct UVD.locality.localityId,UVD.locality.name ");
		
		stringBuilder.append(" ,count(distinct UVD.voter.voterId) from UserVoterDetails UVD,BoothPublicationVoter BPV where UVD.voter.voterId = BPV.voter.voterId ");
		stringBuilder.append(" and BPV.booth.constituency.constituencyId =:constituencyId and BPV.booth.publicationDate.publicationDateId =:publicationDateId and UVD.user.userId =:userId ");
		stringBuilder.append(" and UVD.voter.age >='"+minAge+"' and UVD.voter.age <='"+maxAge+"' ");
		if(type.equalsIgnoreCase(IConstants.CUSTOMWARD) || type.equalsIgnoreCase("customWardLocalArea"))
		 stringBuilder.append(" and UVD.ward.constituencyId =:id ");
		
		if(type.equalsIgnoreCase("customWardLocalArea"))
		  stringBuilder.append(" group by UVD.locality.localityId order by UVD.locality.name ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("id", id);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getLocalityByCustomWardId(Long constituencyId,Long publicationDateId,Long id,Long userId,String type)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select distinct UVD.locality.name from UserVoterDetails UVD,BoothPublicationVoter BPV where UVD.voter.voterId = BPV.voter.voterId ");
		stringBuilder.append(" and UVD.user.userId =:userId and BPV.booth.publicationDate.publicationDateId =:publicationDateId and BPV.booth.constituency.constituencyId =:constituencyId ");
		stringBuilder.append(" and UVD.ward.constituencyId =:id order by UVD.locality.name ");
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("id", id);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterDetailsForSurveyForm(Long voterId,Long userId)
	{
		Query queryObj = getSession().createQuery("select model.casteState.casteStateId " +
				" ,model.casteState.caste.casteName  from UserVoterDetails model " +
				" where model.voter.voterId = :voterId and model.user.userId = :userId");
		queryObj.setParameter("voterId", voterId);
		queryObj.setParameter("userId", userId);
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserVoterDetails> getVoterDetailsByVoterId(Long voterId,Long userId)
	{
		Query queryObj = getSession().createQuery("select model from UserVoterDetails model " +
				" where model.voter.voterId = :voterId and model.user.userId = :userId");
		queryObj.setParameter("voterId", voterId);
		queryObj.setParameter("userId", userId);
		return queryObj.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWardsForMuncByConsIdAndUserId(Long constituencyId,Long localEleBodyId,Long publicationDateId,Long userId)
	{
	  Query query = getSession().createQuery(" select distinct model.ward.constituencyId,model.ward.name from UserVoterDetails model,BoothPublicationVoter model2 " +
	  	" where model.voter.voterId = model2.voter.voterId and model2.booth.constituency.constituencyId =:constituencyId and model.user.userId =:userId and model2.booth.publicationDate.publicationDateId =:publicationDateId " +
	  	"  and model.ward.localElectionBody.localElectionBodyId =:localElectionBodyId and model.ward is not null order by model.ward.name ");
	  
	  query.setParameter("constituencyId", constituencyId);
	  query.setParameter("userId", userId);
	  query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("localElectionBodyId", localEleBodyId);
	  
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString) {
		
		 Query query = getSession().createQuery("select count(model.voter.voterId) from BoothPublicationVoter model,UserVoterDetails model2 where " +
		 		" model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
			  query.setParameter("publicationDateId", publicationDateId);
			 if(id != 0l)
			  query.setParameter("id", id);
			return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString) {
		
		 Query query = getSession().createQuery("select model.voter,model.booth.boothId,model.booth.partNo,model.serialNo from BoothPublicationVoter model,UserVoterDetails model2 where " +
		 		" model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId "+queryString) ;
			  query.setParameter("publicationDateId", publicationDateId);
			 if(id != 0l)
			  query.setParameter("id", id);
			query.setFirstResult(startRecord);
			query.setMaxResults(maxRecords);
			return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteAssignedVotersList(Long constituencyId,Long publicationId,String type,Long userId)
	{
		StringBuilder str = new StringBuilder();
		if(type != null && type.equalsIgnoreCase(IConstants.BOOTH))
			str.append(" select model2.booth.boothId,count (distinct model.voter.voterId) ");
		else
			str.append(" select model2.booth.panchayat.panchayatId,count (distinct model.voter.voterId) ");
		
		str.append(" from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId ");
		str.append(" and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and model.casteState.caste is not null and model.user.userId =:userId ");
		if(type != null && type.equalsIgnoreCase(IConstants.BOOTH))
		 str.append(" group by model2.booth.boothId ");
		else
			str.append(" group by model2.booth.panchayat.panchayatId ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWardWiseTotalVotersCount(Long constituencyId,Long publicationDateId,Long localEleBodyId,String type,Long userId)
	{
	  StringBuilder str = new StringBuilder();
	  if(type != null && type.equalsIgnoreCase("casteAssignedVoters"))
		  str.append(" select model.ward.constituencyId,count (distinct model.voter.voterId)  ");
	  else
	   str.append(" select model.ward.constituencyId, model.ward.name,count (distinct model.voter.voterId),model2.booth.localBody.name  ");
	  
	  str.append(" from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and  ");
	  str.append(" model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and model2.booth.localBody.localElectionBodyId =:localElectionBodyId ");
	  str.append(" and model.user.userId =:userId ");
	  if(type != null && type.equalsIgnoreCase("casteAssignedVoters"))
	   str.append(" and model.casteState.caste is not null ");
	  
	  str.append(" group by model.ward.constituencyId order by model2.booth.localBody.name,model.ward.name ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("constituencyId", constituencyId);
	  query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("localElectionBodyId", localEleBodyId);
	  query.setParameter("userId", userId);
	  return query.list();
	}
	@SuppressWarnings("unchecked")
	 public List<Object[]> getWardsBYLocalElectionBodyId(List<Long> ids , Long publicationId ,Long userId)
	 {
		 Query query = getSession().createQuery(" select distinct UVD.ward.constituencyId, UVD.ward.name from UserVoterDetails UVD, BoothPublicationVoter BPV where " +
		 		" UVD.voter.voterId = BPV.voter.voterId and BPV.booth.localBody.localElectionBodyId in (:ids) and BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
		 		" UVD.user.userId = :userId order by UVD.ward.constituencyId ");
		 
		 query.setParameter("publicationDateId", publicationId);
		 query.setParameterList("ids", ids);
		 query.setParameter("userId", userId);
		 
		 return query.list();
	 }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterIdAndMobileNoByLocalityForUser(Long localityId,Long hamletId,Long userId,Long casteStateId,String queryStr)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.voter.voterId,model.mobileNo from UserVoterDetails model where model.locality.localityId = :localityId and ");
		str.append(" model.user.userId = :userId and model.casteState.casteStateId = :casteStateId and ");
		str.append(queryStr);
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("localityId", localityId);
		query.setParameter("id", hamletId);
		query.setParameter("userId", userId);
		query.setParameter("casteStateId", casteStateId);
		
		return query.list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterIdAndMobileNoForuserByHamletIdsByCaste(Long userId ,Long hamletId,Long casteStateId)
	{
		
		Query query = getSession().createQuery("select model.voter.voterId,model.mobileNo from UserVoterDetails model where " +
				" model.user.userId = :userId and model.hamlet.hamletId =:hamletId and model.casteState.casteStateId = :casteStateId ");
		
		
		query.setParameter("userId", userId);
		query.setParameter("hamletId", hamletId);
		query.setParameter("casteStateId", casteStateId);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterIdAndMobileNoByVoterIdsList(List<Long> voterIdsList,Long userId)
	{
		Query query = getSession().createQuery(" select model.voter.voterId, model.mobileNo from UserVoterDetails model where model.voter.voterId in (:voterIdsList) and model.user.userId =:userId ");
		query.setParameterList("voterIdsList", voterIdsList);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getHamletIdsListByUserIdAndConstituencyId(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery(" select distinct model.hamlet.hamletId from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.user.userId =:userId and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and model.hamlet is not null");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getWardIdsByLocalEleBodyId(Long constituencyId,Long userId,Long publicationDateId,Long localEleBodyId)
	{
		Query query = getSession().createQuery(" select distinct model.ward.constituencyId from UserVoterDetails model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.user.userId =:userId and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and model.ward is not null ");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletIdsListByMandalIdsList(Long constituencyId,Long publicationDateId,Long userId,List<Long> locationValuesList,String type)
	{
		StringBuilder str = new StringBuilder();
		
		if(type != null && type.equalsIgnoreCase("mandalHamlets"))
		 str.append(" select model2.booth.tehsil.tehsilId, ");
		else if(type != null && type.equalsIgnoreCase("panchayatHamlets"))
		 str.append(" select model2.booth.panchayat.panchayatId, ");
		
	   else if(type != null && type.equalsIgnoreCase("boothHamlets"))
		 str.append(" select model2.booth.boothId, ");
		
		str.append(" count(distinct model.hamlet.hamletId) ");
		
		str.append(" from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and model.user.userId =:userId ");
		str.append(" and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and  model.hamlet is not null ");
		
		if(type != null && type.equalsIgnoreCase("mandalHamlets"))
		 str.append(" and model2.booth.tehsil.tehsilId in (:locationValuesList) group by model2.booth.tehsil.tehsilId ");
		else if(type != null && type.equalsIgnoreCase("panchayatHamlets"))
		 str.append(" and model2.booth.panchayat.panchayatId in (:locationValuesList) group by model2.booth.panchayat.panchayatId ");
		
		else if(type != null && type.equalsIgnoreCase("boothHamlets"))
			 str.append(" and model2.booth.boothId in (:locationValuesList) group by model2.booth.boothId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("locationValuesList", locationValuesList);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothsCountByWardIdsList(List<Long> locationIdsList,Long constituencyId,Long publicationId,Long userId,String type)
	{
		StringBuilder str = new StringBuilder();
		if(type != null && type.equalsIgnoreCase("wardBooths"))
		 str.append(" select model.ward.constituencyId, ");
		else if(type != null && type.equalsIgnoreCase("hamletBooths"))
		 str.append(" select model.hamlet.hamletId, ");
		
		str.append(" count(distinct model2.booth.boothId) from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId ");
		str.append(" and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and model.user.userId =:userId ");
		
		if(type != null && type.equalsIgnoreCase("wardBooths"))
		 str.append(" and model.ward.constituencyId in(:locationIdsList) group by model.ward.constituencyId ");
		else if(type != null && type.equalsIgnoreCase("hamletBooths"))
			str.append(" and model.hamlet.hamletId in(:locationIdsList) and model.hamlet is not null group by model.hamlet.hamletId  ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationId);
		query.setParameterList("locationIdsList", locationIdsList);
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWardsCountByBoothIdsList(List<Long> boothIdsList,Long constituencyId,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery(" select model2.booth.boothId, count(distinct model.ward.constituencyId) from UserVoterDetails model,BoothPublicationVoter model2 " +
				" where model.voter.voterId = model2.voter.voterId and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and model.user.userId =:userId and model2.booth.boothId in(:boothIdsList) group by model2.booth.boothId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationId);
		query.setParameterList("boothIdsList", boothIdsList);
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWardsCountByMuncipalityIdsList(List<Long> muncipalityIdsList,Long constituencyId,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery(" select model2.booth.localBody.localElectionBodyId, count(distinct model.ward.constituencyId) from UserVoterDetails model,BoothPublicationVoter model2 " +
				" where model.voter.voterId = model2.voter.voterId and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and model.user.userId =:userId and model2.booth.localBody.localElectionBodyId in(:muncipalityIdsList) group by model2.booth.localBody.localElectionBodyId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationId);
		query.setParameterList("muncipalityIdsList", muncipalityIdsList);
		
		return query.list();
	}
	
	public List<Object[]> getCasteDetailsOfVoterByBoothId(Long boothId,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery("select UVD.casteState.caste.casteName,count(UVD.casteState.caste.casteName), UVD.casteState.casteStateId " +
				" from UserVoterDetails UVD,BoothPublicationVoter BPV where BPV.voter.voterId = UVD.voter.voterId " +
				" and BPV.booth.boothId = :boothId and UVD.user.userId = :userId " +
				"and BPV.booth.publicationDate.publicationDateId = :publicationId " +
				" group by UVD.casteState.caste.casteName order by count(UVD.casteState.caste.casteName) desc");
		query.setParameter("boothId", boothId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getCasteDetailsOfVoterByLocationId(Long locationId,Long publicationId,Long userId,String locationType)
	{
		StringBuffer queryString = new StringBuffer();
		queryString = queryString.append("select UVD.casteState.caste.casteName,count(UVD.casteState.caste.casteName), UVD.casteState.caste.casteId " +
				" from UserVoterDetails UVD,BoothPublicationVoter BPV where BPV.voter.voterId = UVD.voter.voterId ");
		if(locationType.equalsIgnoreCase("panchayat")){
			queryString.append(" and BPV.booth.panchayat.panchayatId = :panchayatId ");
		}
		else if(locationType.equalsIgnoreCase("booth")){
			queryString.append(" and BPV.booth.boothId = :boothId ");
		}
		else if(locationType.equalsIgnoreCase("constituency")){
			queryString.append(" and BPV.booth.constituency.constituencyId = :constituencyId ");
		}
		queryString = queryString.append("and UVD.user.userId = :userId " +
				"and BPV.booth.publicationDate.publicationDateId = :publicationId " +
				" group by UVD.casteState.caste.casteName order by count(UVD.casteState.caste.casteName) desc");
		
		Query query = getSession().createQuery(queryString.toString());
		if(locationType.equalsIgnoreCase("panchayat"))
			query.setParameter("panchayatId", locationId);
		else if(locationType.equalsIgnoreCase("booth"))
			query.setParameter("boothId", locationId);
		else if(locationType.equalsIgnoreCase("constituency"))
			query.setParameter("constituencyId", locationId);		
		
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getCasteDetailsOfVoterByBooths(List<Long> boothIds,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery("select UVD.casteState.caste.casteName,count(BPV.voter.voterId),BPV.booth.boothId " +
				" from UserVoterDetails UVD,BoothPublicationVoter BPV where BPV.voter.voterId = UVD.voter.voterId " +
				" and BPV.booth.boothId in (:boothIds) and UVD.user.userId = :userId " +
				"and BPV.booth.publicationDate.publicationDateId = :publicationId " +
				" group by BPV.booth.boothId,UVD.casteState.casteStateId order by BPV.booth.boothId,count(UVD.casteState.caste.casteName) desc");
		query.setParameterList("boothIds", boothIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getUserAssignedVotersCastesByCosntiId(Long constituencyId,Long userId){
		Query query = getSession().createQuery(" select model.casteId ,model.casteName from Caste model where model.casteId in " +
				" ( select distinct model1.caste.casteId from CasteState model1  where model1.casteStateId in " +
				" ( select distinct model2.casteState.casteStateId from VoterCastInfo model2 where " +
				" model2.userId = :userId and  model2.constituency.constituencyId = :constituencyId )) " +
				" order by model.casteName asc");
		query.setParameter("userId",userId);
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public List<Object[]> getUserAssignedCasteCountByBoothIdAndPublication(List<Long> boothIds,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery("select BPV.booth.boothId,count(UVD.casteState.caste.casteId)" +
				" from UserVoterDetails UVD,BoothPublicationVoter BPV where BPV.voter.voterId = UVD.voter.voterId " +
				" and BPV.booth.boothId in(:boothIds) and UVD.user.userId = :userId " +
				"and BPV.booth.publicationDate.publicationDateId = :publicationId group by BPV.booth.boothId");
		query.setParameterList("boothIds", boothIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteInHamlet(List<Long> hamletIds,Long userId,Long publicationId){
		Query query = getSession().createQuery("select uvd.hamlet.hamletId,count(uvd.voter.voterId),uvd.casteState.caste.casteName,uvd.casteState.casteStateId,uvd.casteState.casteStateId from UserVoterDetails uvd,BoothPublicationVoter bpv  "+
				"where  uvd.hamlet.hamletId in (:hamletIds) and uvd.user.userId = :userId and bpv.voter.voterId =  uvd.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationId group by uvd.hamlet.hamletId,uvd.casteState.caste.casteId order by uvd.hamlet.hamletId,count(uvd.voter.voterId) desc");
		query.setParameterList("hamletIds", hamletIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterCountByHamlet(List<Long> hamletIds,Long userId,Long publicationId){
		Query query = getSession().createQuery("select uvd.hamlet.hamletId,uvd.hamlet.hamletName,count(uvd.voter.voterId),count(uvd.casteState.casteStateId) from UserVoterDetails uvd,BoothPublicationVoter bpv " +
					"where uvd.hamlet.hamletId in (:hamletIds)  and uvd.user.userId = :userId and bpv.voter.voterId =  uvd.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationId group by uvd.hamlet.hamletId");
		query.setParameterList("hamletIds", hamletIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public Integer updateVoterMobileNo(String mobileNo, Long voterId)
	{
		Query query = getSession().createQuery(" update UserVoterDetails model set model.mobileNo = :mobileNo where model.voter.voterId = :voterId ");
		
		query.setParameter("mobileNo",mobileNo);
		query.setParameter("voterId",voterId);
		return query.executeUpdate();
	}
	public Integer updateCasteStateId(Long casteStateId, Long voterId)
	{
		Query query = getSession().createQuery(" update UserVoterDetails model set model.casteState.casteStateId = :casteStateId , " +
				" model.casteInsertType.casteInsertTypeId = 1 where model.voter.voterId = :voterId and " +
				" model.casteInsertType.casteInsertTypeId = 2 or model.casteInsertType.casteInsertTypeId is null");
		
		query.setParameter("casteStateId",casteStateId);
		query.setParameter("voterId",voterId);
		return query.executeUpdate();
	}
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long hamletId,String mobileNo){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId,model.casteState.casteStateId = :castStateId,model.locality.localityId = :localityId,model.hamlet.hamletId = :hamletId,model.mobileNo = :mobileNo where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("castStateId",castStateId);
		query.setParameter("localityId",localitityId);
		query.setParameter("hamletId",hamletId);
		query.setParameter("mobileNo",mobileNo);

		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterDetailsWithWard(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long wardId,String mobileNo)
		{
			
			Query query = getSession().createQuery("update UserVoterDetails model set " +
					"model.party.partyId = :partyId,model.casteState.casteStateId = :castStateId,model.locality.localityId = :localityId,model.ward.constituencyId = :wardId,model.mobileNo = :mobileNo where model.voter.voterId = :voterId and model.user.userId = :userId");
			query.setParameter("voterId",voterId);
			query.setParameter("userId",userId);
			query.setParameter("partyId",partyId);
			query.setParameter("castStateId",castStateId);
			query.setParameter("localityId",localitityId);
			query.setParameter("wardId",wardId);
			query.setParameter("mobileNo",mobileNo);
			
			query.executeUpdate();
		}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatWiseCasteAssigned()
	{
		Query query = getSession().createQuery("select BPV.booth.panchayat.panchayatName,count(UVD.voter.voterId) from UserVoterDetails UVD,BoothPublicationVoter BPV where " +
				" UVD.voter.voterId = BPV.voter.voterId and BPV.booth.constituency.constituencyId = 282 and BPV.booth.publicationDate.publicationDateId = 8 and UVD.casteState.casteStateId is not null " +
				" group by BPV.booth.panchayat.panchayatId "+
				" order by BPV.booth.tehsil.tehsilName,BPV.booth.panchayat.panchayatName");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCaste3()
	{
		Query query = getSession().createQuery("select BPV.booth.panchayat.panchayatName,UVD.casteState.caste.casteName,count(UVD.voter.voterId) from UserVoterDetails UVD,BoothPublicationVoter BPV where " +
				" UVD.voter.voterId = BPV.voter.voterId and BPV.booth.constituency.constituencyId = 282 and BPV.booth.publicationDate.publicationDateId = 8 group by BPV.booth.panchayat.panchayatId, UVD.casteState.casteStateId " +
				" order by BPV.booth.tehsil.tehsilName,BPV.booth.panchayat.panchayatName, count(UVD.voter.voterId) desc");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserVoterDetails> getUserVoterDetailsOfAConstituencyForAPublication(Long constituencyId, Long publicationDateId, Long userId)
	{
		Query query = getSession().createQuery("select model from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.user.userId = :userId and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId",userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterAgeDetailsForHamlet(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.voter.voterId),model.voter.gender,model.voter.voterAgeRange.voterAgeRangeId,model2.hamlet.hamletId");
		str.append(" from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId ");
		str.append(" and model2.hamlet.hamletId in(:locationIdsList) group by model2.hamlet.hamletId,model.voter.voterAgeRange.voterAgeRangeId,model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("locationIdsList", locationIdsList);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	
	
	public List<Long> getVoterDetailsByCaste()
	{
		
		Query query = getSession().createQuery("select distinct(UVD.casteState.caste.casteId) from UserVoterDetails UVD , BoothPublicationVoter BPV " +
				"where UVD.user.userId = 1 and UVD.voter.voterId = BPV.voter.voterId and " +
				"BPV.booth.publicationDate.publicationDateId = 8 and BPV.booth.constituency.constituencyId = 232");
		
		//query.setFirstResult(1);
		//query.setMaxResults(100);
		
		return query.list();
		
	}
	
	public List<Object[]> getAllTheCastesOfConstituency(Long constituencyId , Long userId,Long publicationDateId)
	{
		
	/*	Query query = getSession().createQuery("select UVD.casteState.caste.casteId , UVD.casteState.caste.casteName from UserVoterDetails UVD ,  " +
				"BoothPublicationVoter BPV where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and " +
				" BPV.booth.publicationDate.publicationDateId = :publicationDateId and BPV.booth.constituency.constituencyId = :constituencyId");*/
		
		
		/*Query query = getSession().createQuery("select UVD.casteState.casteStateId, UVD.casteState.caste.casteName from UserVoterDetails UVD ,  " +
				"BoothPublicationVoter BPV where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and " +
				" BPV.booth.publicationDate.publicationDateId = :publicationDateId and BPV.booth.constituency.constituencyId = :constituencyId " +
				"group by UVD.casteState.casteStateId order by UVD.casteState.caste.casteName");
		*/
		Query query = getSession().createQuery("select distinct model.casteState.casteStateId , model.casteState.caste.casteName from VoterCastInfo model " +
				"where model.userId = :userId and model.publicationDateId = :publicationDateId and model.reportLevelValue = :constituencyId and model.voterReportLevel.voterReportLevelId = 1");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	
	public List<Object[]> getVotersDetailsBySearchToSendSMS(String queryString,SMSSearchCriteriaVO searchVO)
	{
		Query query = getSession().createQuery(queryString);
		
		if(searchVO.isFamilySelected())
			query.setParameter("houseNo", searchVO.getHouseNo());
		
		if(searchVO.isCasteSelected())
			query.setParameterList("casteIds", searchVO.getSelectedCastes());
		
		if(searchVO.isGenderSelected())
			query.setParameter("gender", searchVO.getGender());
		
		
		query.setParameter("locationValue", searchVO.getLocationValue());
		//query.setParameter("gender", searchVO.getGender());
		query.setParameter("userId", searchVO.getUserId());
		query.setParameter("publicationDate", searchVO.getPublicationDateId());
		
		if(!searchVO.isDirectSent()){
		 query.setFirstResult(searchVO.getStartIndex());
		 query.setMaxResults(searchVO.getMaxRecords());
		}
		
		return query.list();
		
	}
	
	public List<Long> getVotersDetailsCountBySearchToSendSMS(String queryString,SMSSearchCriteriaVO searchVO)
	{
		Query query = getSession().createQuery(queryString);
		
		if(searchVO.isFamilySelected())
			query.setParameter("houseNo", searchVO.getHouseNo());
		
		if(searchVO.isCasteSelected())
			query.setParameterList("casteIds", searchVO.getSelectedCastes());
		
		if(searchVO.isGenderSelected())
			query.setParameter("gender", searchVO.getGender());
		
		
		query.setParameter("locationValue", searchVO.getLocationValue());
		//query.setParameter("gender", searchVO.getGender());
		query.setParameter("userId", searchVO.getUserId());
		query.setParameter("publicationDate", searchVO.getPublicationDateId());
		
		
		return query.list();
		
	}
	
	public List<Object[]> getCasteDetailsOfVoterByBoothIds(List<Long> constituencyIds,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery("select BPV.booth.boothId,UVD.casteState.caste.casteName,count(UVD.casteState.caste.casteName), UVD.casteState.casteStateId " +
				" from UserVoterDetails UVD,BoothPublicationVoter BPV where BPV.voter.voterId = UVD.voter.voterId " +
				" and BPV.booth.localBodyWard.constituencyId in (:constituencyIds) and UVD.user.userId = :userId " +
				"and BPV.booth.publicationDate.publicationDateId = :publicationId " +
				" group by BPV.booth.boothId,UVD.casteState.caste.casteName order by BPV.booth.boothId,count(UVD.casteState.caste.casteName) desc");
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	/*public List<Object[]> getCasteDetailsOfVoterByConstituency(Long constituencyId,Long publicationId,Long userId,List<Long> casteIds)
	{
		Query query = getSession().createQuery("select BPV.booth.boothId,UVD.casteState.casteStateId,count(UVD.casteState.caste.casteName)  " +
				" from UserVoterDetails UVD,BoothPublicationVoter BPV where BPV.voter.voterId = UVD.voter.voterId " +
				" and BPV.booth.constituency.constituencyId = :constituencyId and UVD.user.userId = :userId " +
				"and BPV.booth.publicationDate.publicationDateId = :publicationId and UVD.casteState.casteStateId in (:casteIds)" +
				" group by BPV.booth.boothId,UVD.casteState.caste.casteName order by BPV.booth.boothId desc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		query.setParameterList("casteIds", casteIds);
		return query.list();
	}*/
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getYoungerVoterDetailsForHamlet(Long publicationDateId ,Long id,Long userId,String type,Long ageFrom,Long ageTo)
	{
	  StringBuilder str = new StringBuilder();
	  if(type.equalsIgnoreCase(IConstants.HAMLET))
	   str.append(" select distinct model2.hamlet.hamletId, model2.hamlet.hamletName ");
	  else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
	   str.append(" select distinct model2.ward.constituencyId, model2.ward.name ");
	  
	  str.append(" ,count(distinct model.voter.voterId),model.voter.gender ");
	  str.append(" from BoothPublicationVoter model, UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId ");
	  str.append(" and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId ");
	  if(type.equalsIgnoreCase(IConstants.HAMLET))
	   str.append(" and model2.hamlet.hamletId =:id ");
	  else if(type.equalsIgnoreCase(IConstants.CUSTOMWARD))
	   str.append(" and model2.ward.constituencyId =:id ");
	  str.append(" and model.voter.age between :ageFrom and :ageTo group by model.voter.gender ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("ageFrom", ageFrom);
	  query.setParameter("ageTo", ageTo);
	  query.setParameter("id", id);
	  query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("userId", userId);
	  
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getYoungerVoterDetailsForHamletBylocationIdsList(List<Long> locationIds, Long publicationDateId,Long userId,String type,Long ageFrom,Long ageTo)
	{
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.HAMLET))
		 str.append("select distinct model.hamlet.hamletId,model.hamlet.hamletName ," );
		else if (type.equalsIgnoreCase(IConstants.CUSTOMWARD))
		 str.append("select distinct model.ward.constituencyId,model.ward.name ,");
						  
		str.append(" count(model.voter.voterId), model.voter.gender from UserVoterDetails model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and model.user.userId =:userId and model2.booth.publicationDate.publicationDateId = :publicationDateId ");
		str.append(" and model.voter.age between :ageFrom and :ageTo ");
		
		if(type.equalsIgnoreCase(IConstants.HAMLET))
		 str.append(" and model.hamlet.hamletId in(:locationIds) and model.hamlet.hamletId is not null group by model.hamlet.hamletId,model.voter.gender order by model.hamlet.hamletName ");
		else if (type.equalsIgnoreCase(IConstants.CUSTOMWARD))
		 str.append(" and model.ward.constituencyId in(:locationIds) and model.ward.constituencyId is not null group by model.ward.constituencyId,model.voter.gender order by model.ward.name ");	
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("userId", userId);	
		query.setParameterList("locationIds", locationIds);	
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("ageFrom", ageFrom);
		query.setParameter("ageTo", ageTo);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalVoterForHamletBylocationIdsList(List<Long> locationIds, Long publicationDateId,Long userId,String type)
	{
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.HAMLET))
		 str.append("select distinct model.hamlet.hamletId," );
		else if (type.equalsIgnoreCase(IConstants.CUSTOMWARD))
		 str.append("select distinct model.ward.constituencyId,");
						  
		str.append(" count(model.voter.voterId) from UserVoterDetails model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and model.user.userId =:userId and model2.booth.publicationDate.publicationDateId = :publicationDateId ");
		
		if(type.equalsIgnoreCase(IConstants.HAMLET))
		 str.append(" and model.hamlet.hamletId in(:locationIds) and model.hamlet.hamletId is not null group by model.hamlet.hamletId order by model.hamlet.hamletName ");
		else if (type.equalsIgnoreCase(IConstants.CUSTOMWARD))
		 str.append(" and model.ward.constituencyId in(:locationIds) and model.ward.constituencyId is not null group by model.ward.constituencyId order by model.ward.name ");	
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("userId", userId);	
		query.setParameterList("locationIds", locationIds);	
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityIdsForUser(Long hamletId , Long userId,List<?> voterIds,String male,String female,long ...ages) {
		StringBuilder query = new StringBuilder();
		query.append(" select distinct model.locality.localityId,model.locality.name , count( distinct model.voter.voterId), " );
		query.append(getAgeQuery(null,"GENDER"));	
		if(ages.length%2==0)
				for(int i=0;i<ages.length;){				
					query.append(getAgeQuery("AGE"+ages[i],"AGE"+ages[i+1]));
					i=i+2;
			}			
			query.append(getAgeQuery("AGE"+ages[ages.length-1],null));				
			query.append(getAllAgesQuery(ages));						
		
			query.append(" from UserVoterDetails model  where  model.voter.voterId in(:voterIds) and  model.user.userId = :id " +
						" and model.locality.localityId is not null group by model.locality.localityId order by model.locality.name");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter("male", male);
			queryObject.setParameter("female", female);			
			queryObject.setParameterList("voterIds", voterIds);
			queryObject.setParameter("id", userId);
			for(int i=0;i<ages.length;i++)
					queryObject.setParameter("AGE"+ages[i], ages[i]);
			return queryObject.list();	
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getYoungerVotersForLocality(
			 Long publicationDateId ,Long id,Long userId,String type,Long ageFrom,Long ageTo) {		
			
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select distinct model2.locality.localityId,model2.locality.name , count(distinct model.voter.voterId),model.voter.gender ");
		queryString.append(" from BoothPublicationVoter model,UserVoterDetails model2 where  " +
				" model.booth.publicationDate.publicationDateId = :publicationDateId and model.voter.voterId = model2.voter.voterId " +
				" and model2.user.userId = :userId and model.voter.age between :ageFrom and :ageTo and ") ;
		   
		   if(type.equalsIgnoreCase(IConstants.HAMLET))
			  queryString.append(" model2.hamlet.hamletId = :id  ");
			else
			  queryString.append(" model2.ward.constituencyId = :id  ");
		   queryString.append(" group by model2.locality.localityId,model.voter.gender ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId", userId);	
		query.setParameter("id", id);	
		query.setParameter("ageFrom", ageFrom);
		query.setParameter("ageTo", ageTo);
		  return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getYoungVotersForHamlet(Long constituencyId,Long userId,Long publicationDateId,Long boothId,Long ageFrom,Long ageTo,String type)
	{
		StringBuilder str = new StringBuilder();
		
		 if(type.equalsIgnoreCase("boothHamlets"))
			str.append("select distinct model.hamlet.hamletId,model.hamlet.hamletName ,");
		 else if(type.equalsIgnoreCase("hamletBooths") || type.equalsIgnoreCase("wardBooths") )
			str.append("select distinct model2.booth.boothId,concat('Booth-',model2.booth.partNo),");
		 
		 str.append(" count(distinct model.voter.voterId), model.voter.gender from UserVoterDetails model,BoothPublicationVoter model2 ");
		 str.append(" where model.voter.voterId = model2.voter.voterId and model2.booth.publicationDate.publicationDateId = :publicationDateId "); 
		 str.append(" and model.user.userId =:userId and model.voter.age between :ageFrom and :ageTo and ");
		 
		 if(type.equalsIgnoreCase("boothHamlets"))
		  str.append(" model2.booth.boothId = :boothId and model.hamlet.hamletId is not null group by model.hamlet.hamletId,model.voter.gender order by model.hamlet.hamletName " );
		 else if(type.equalsIgnoreCase("hamletBooths"))
		  str.append(" model.hamlet.hamletId = :boothId group by model2.booth.boothId,model.voter.gender order by model2.booth.partNo ");
		 else if( type.equalsIgnoreCase("wardBooths"))
		  str.append(" model.ward.constituencyId = :boothId and model2.booth.constituency.constituencyId = :constituencyId group by model2.booth.boothId,model.voter.gender order by model2.booth.partNo " );
		 
		Query query = getSession().createQuery(str.toString());
		 if(type.equalsIgnoreCase("wardBooths")){
			 query.setParameter("constituencyId", constituencyId);
			}
		 query.setParameter("publicationDateId", publicationDateId);
		 query.setParameter("boothId", boothId);
		 query.setParameter("userId", userId);
		 query.setParameter("ageFrom", ageFrom);
		 query.setParameter("ageTo", ageTo);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalYoungVotersForHamlet(Long constituencyId,Long userId,Long publicationDateId,Long boothId,String type)
	{
		StringBuilder str = new StringBuilder();
		
		 if(type.equalsIgnoreCase("boothHamlets"))
			str.append("select distinct model.hamlet.hamletId,");
		 else if(type.equalsIgnoreCase("hamletBooths") || type.equalsIgnoreCase("wardBooths") )
			str.append("select distinct model2.booth.boothId,");
		 
		 str.append(" count(distinct model.voter.voterId) from UserVoterDetails model,BoothPublicationVoter model2 ");
		 str.append(" where model.voter.voterId = model2.voter.voterId and model2.booth.publicationDate.publicationDateId = :publicationDateId "); 
		 str.append(" and model.user.userId =:userId and ");
		 
		 if(type.equalsIgnoreCase("boothHamlets"))
		  str.append(" model2.booth.boothId = :boothId and model.hamlet.hamletId is not null group by model.hamlet.hamletId order by model.hamlet.hamletName " );
		 else if(type.equalsIgnoreCase("hamletBooths"))
		  str.append(" model.hamlet.hamletId = :boothId group by model2.booth.boothId order by model2.booth.partNo ");
		 else if( type.equalsIgnoreCase("wardBooths"))
		  str.append(" model.ward.constituencyId = :boothId and model2.booth.constituency.constituencyId = :constituencyId group by model2.booth.boothId order by model2.booth.partNo " );
		 
		Query query = getSession().createQuery(str.toString());
		 if(type.equalsIgnoreCase("wardBooths")){
			 query.setParameter("constituencyId", constituencyId);
			}
		 query.setParameter("publicationDateId", publicationDateId);
		 query.setParameter("boothId", boothId);
		 query.setParameter("userId", userId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getYoungVoterAgeDetailsForHamlet(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,Long userId,Long ageFrom,Long ageTo)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.voter.voterId),model.voter.gender,model2.hamlet.hamletId");
		str.append(" from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId ");
		str.append(" and model2.hamlet.hamletId in(:locationIdsList) ");
		
		if(ageTo != null)
		 str.append(" and model.voter.age between :ageFrom and :ageTo ");
		else
		 str.append(" and model.voter.age > :ageFrom ");
		
		str.append("group by model2.hamlet.hamletId,model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("locationIdsList", locationIdsList);
		query.setParameter("userId", userId);
		if(ageTo != null)
		 query.setParameter("ageTo", ageTo);
		query.setParameter("ageFrom", ageFrom);
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGenderWiseVotersCountForLocality(Long constituencyId,Long publicationDateId,Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.voter.voterId), model.voter.gender, model2.locality.localityId from BoothPublicationVoter model,UserVoterDetails model2 " +
				" where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId =:constituencyId and model2.locality is not null and " +
				" model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId group by model2.locality.localityId,model.voter.gender ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityIdsForHamletAndBooth(Long constituencyId,Long publicationDateId,Long userId,String tempVar)
	{
	  StringBuilder str = new StringBuilder();
	  if(tempVar != null && tempVar.equalsIgnoreCase("hamletLocalities"))
	   str.append(" select model2.hamlet.hamletId, ");
	  else if(tempVar != null && tempVar.equalsIgnoreCase("wardLocalities"))
	   str.append(" select model2.ward.constituencyId, ");
	  
	  str.append("model2.locality.localityId from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId ");
	  str.append(" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and " +
	  		" model2.user.userId =:userId and model2.locality is not null ");
	  
	  if(tempVar != null && tempVar.equalsIgnoreCase("hamletLocalities"))
	   str.append(" and model2.hamlet is not null group by model2.locality.localityId ");
	  else if(tempVar != null && tempVar.equalsIgnoreCase("wardLocalities"))
	   str.append(" and model2.ward is not null group by model2.locality.localityId ");
		  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("constituencyId", constituencyId);
	  query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("userId", userId);
	  
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFamiliesCountForLocality(Long constituencyId,Long publicationDateId,Long userId)
	{
	  Query query = getSession().createQuery(" select count(distinct model.voter.houseNo),model2.locality.localityId from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId " +
	  		" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId and model2.locality is not null group by model2.locality.localityId ");
	  
	  query.setParameter("constituencyId", constituencyId);
      query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("userId", userId);
	  
	  return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityWiseVoterAgeInfo(Long constituencyId,Long publicationDateId,Long userId)
	{
	  Query query = getSession().createQuery(" select count(distinct model.voter.voterId),model.voter.gender," +
	  		" model2.locality.localityId,model.voter.voterAgeRange.voterAgeRangeId from BoothPublicationVoter model,UserVoterDetails model2 " +
	  		" where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId =:constituencyId and " +
	  		" model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId and model2.locality is not null " +
	  		" group by model2.locality.localityId,model.voter.gender,model.voter.voterAgeRange.voterAgeRangeId ");
	  
	  query.setParameter("constituencyId", constituencyId);
      query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("userId", userId);
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityWiseYoungVotersAgeInfo(Long constituencyId,Long publicationDateId,Long userId,Long ageFrom,Long ageTo)
	{
	
	  StringBuilder str = new StringBuilder();
	  str.append("select count(distinct model.voter.voterId),model.voter.gender,model2.locality.localityId from BoothPublicationVoter model,UserVoterDetails model2 " +
	  		" where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and " +
	  		" model2.user.userId =:userId and model2.locality is not null and ");
	  if(ageFrom != null && ageTo != null)
	   str.append(" model.voter.age between :ageFrom and :ageTo ");
	  else 
	   str.append(" model.voter.age > :ageFrom ");
	  
	  str.append(" group by model2.locality.localityId,model.voter.gender ");
	  
	  Query query = getSession().createQuery(str.toString());
	  
	  query.setParameter("constituencyId", constituencyId);
      query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("userId", userId);
	  query.setParameter("ageFrom", ageFrom);
	  if(ageTo != null)
	   query.setParameter("ageTo", ageTo);
	  
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityWiseFamilyDetails(Long constituencyId,Long publicationDateId,Long userId)
	{
	  Query query = getSession().createQuery(" select model2.locality.localityId,model.voter.houseNo,count(model.voter.voterId) from BoothPublicationVoter model,UserVoterDetails model2 " +
	  		" where model.voter.voterId = model2.voter.voterId and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId " +
	  		" and model2.user.userId =:userId and model2.locality is not null group by model2.locality.localityId,model.voter.houseNo ");
	  
	  query.setParameter("constituencyId", constituencyId);
      query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("userId", userId);
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityWiseCasteDetails(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery(" select model2.locality.localityId,count(distinct model.voter.voterId),model.voter.gender,model2.casteState.casteStateId," +
				" model2.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId " +
				" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId " +
				" and model2.locality is not null group by model2.locality.localityId,model2.casteState.casteStateId,model.voter.gender ");
		
		query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		  
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocalityTotalCastes(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery(" select model2.locality.localityId,count(distinct model2.casteState.casteStateId)" +
				"  from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId " +
				" and model.booth.constituency.constituencyId =:constituencyId and model.booth.publicationDate.publicationDateId =:publicationDateId and model2.user.userId =:userId " +
				" and model2.locality is not null group by model2.locality.localityId ");
		
		query.setParameter("constituencyId", constituencyId);
	    query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		  
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCadreCaste(List<Long> cadreIdsList)
	{
		Query query = getSession().createQuery("select model2.cadreId, model.casteState.casteStateId from UserVoterDetails model,Cadre model2 where model.voter.voterId = model2.voter.voterId " +
				" and model2.cadreId in (:cadreIdsList)");
		query.setParameterList("cadreIdsList",cadreIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletBoothInfo(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("select model.hamlet.hamletId,model2.booth.boothId,model2.booth.publicationDate.publicationDateId from UserVoterDetails model,BoothPublicationVoter model2 " +
				" where model.voter.voterId = model2.voter.voterId and model.hamlet is not null and model.user.userId = :userId and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId " +
				" group by model2.booth.publicationDate.publicationDateId,model.hamlet.hamletId,model2.booth.boothId order by model2.booth.publicationDate.publicationDateId ");
		
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId",userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWardIdsByLocalEleBodyIdPublicationId(Long constituencyId,Long userId,Long publicationDateId,Long localEleBodyId)
	{
		Query query = getSession().createQuery(" select distinct model.ward.constituencyId,model.ward.name,model.ward.localElectionBody.localElectionBodyId from UserVoterDetails model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.user.userId =:userId and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and model.ward is not null and model.ward.localElectionBody.localElectionBodyId =:localEleBodyId ");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("localEleBodyId", localEleBodyId);
		return query.list();
	}

	
	@SuppressWarnings("unchecked")
	public List<VoterFlag> getFlagDetailsForSelectedHamlet(Long hamletId,
			Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model from VoterFlag model,UserVoterDetails UVD where " +
				" model.voter.voterId = UVD.voter.voterId  and UVD.user.userId = :userId  ");
		if(selLevel.equalsIgnoreCase("hamlet"))
		{
			queryString.append(" and UVD.hamlet.hamletId = :hamletId");
		}
		else
		{
			queryString.append(" and UVD.ward.constituencyId = :hamletId ");
		}
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
		query.setParameter("hamletId", hamletId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	

	public List<Long> getAllWardIdsByLocalEleBodyIdPublicationId(Long constituencyId,Long userId,Long publicationDateId,Long localEleBodyId)
	{
		Query query = getSession().createQuery(" select distinct model.ward.constituencyId from UserVoterDetails model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.user.userId =:userId and model2.booth.constituency.constituencyId =:constituencyId and model2.booth.publicationDate.publicationDateId =:publicationDateId and model.ward is not null and model.ward.localElectionBody.localElectionBodyId =:localEleBodyId ");
		
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("localEleBodyId", localEleBodyId);
		return query.list();
	}
	
	public List getCasteCategory(Long userId,Long voterId)
	{
		Query query = getSession().createQuery("select distinct model.casteState.casteCategoryGroup.casteCategoryGroupId from UserVoterDetails model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
				" model.user.userId =:userId and model.voter.voterId = :voterId");
		
		query.setParameter("userId", userId);
		query.setParameter("voterId", voterId);
		
		return query.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Locality> getAllLocatiesInAConstituency(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("select distinct model.locality from UserVoterDetails model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and model.user.userId = :userId and " +
				" model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId",userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCountForCasteType(Long userId)
	{
		Query query = getSession().createQuery("select count(model.userVoterDetailsId),model.casteInsertType.type from UserVoterDetails model where model.user.userId = :userId group by model.casteInsertType.casteInsertTypeId");
		query.setParameter("userId",userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getMatchtedRecordsForACaste(Long userId,Long casteStateId,List<Long> voterIdsList)
	{
		Query query = getSession().createQuery("select model.userVoterDetailsId from UserVoterDetails model where model.user.userId = :userId and model.casteState.casteStateId = :casteStateId and model.voter.voterId in(:voterIdsList)");
		query.setParameter("userId",userId);
		query.setParameter("casteStateId",casteStateId);
		query.setParameterList("voterIdsList",voterIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getUnmatchtedRecordsForACaste(Long userId,Long casteStateId,List<Long> voterIdsList)
	{
		Query query = getSession().createQuery("select model.userVoterDetailsId from UserVoterDetails model where model.user.userId = :userId and model.casteState.casteStateId != :casteStateId and model.voter.voterId in(:voterIdsList)");
		query.setParameter("userId",userId);
		query.setParameter("casteStateId",casteStateId);
		query.setParameterList("voterIdsList",voterIdsList);
		return query.list();
	}
	
	public Integer updateCasteInsertType(List<Long> userVoterDetailsIdsList,Long casteInsertTypeId)
	{
		Query query = getSession().createQuery("update UserVoterDetails model set model.casteInsertType.casteInsertTypeId = :casteInsertTypeId where model.userVoterDetailsId in(:userVoterDetailsIdsList)");
		query.setParameter("casteInsertTypeId", casteInsertTypeId);
		query.setParameterList("userVoterDetailsIdsList", userVoterDetailsIdsList);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAvailableVoterIdsList(Long userId,Long casteStateId,List<Long> voterIdsList)
	{
		Query query = getSession().createQuery("select model.voter.voterId from UserVoterDetails model where model.user.userId = :userId and model.voter.voterId in(:voterIdsList)");
		query.setParameter("userId",userId);
		query.setParameterList("voterIdsList",voterIdsList);
		return query.list();
	}

	public Integer updateVoterCasteByPrediction(Long userId,Long casteStateId,Long casteInsertTypeId,List<Long> voterIdsList)
	{
		Query query = getSession().createQuery("update UserVoterDetails model set model.casteState.casteStateId = :casteStateId,model.casteInsertType.casteInsertTypeId = :casteInsertTypeId where " +
				" model.user.userId = :userId and model.voter.voterId in (:voterIdsList) and model.casteState is null "); 
		query.setParameter("userId",userId);
		query.setParameter("casteStateId",casteStateId);
		query.setParameter("casteInsertTypeId",casteInsertTypeId);
		query.setParameterList("voterIdsList",voterIdsList);
		return query.executeUpdate();
	}
	
	public List<Object[]> getCasteForVoter(List<Long> voterIds)
	{
		Query query = getSession().createQuery("select model.voter.voterId,model.casteState.caste.casteName from UserVoterDetails model where model.voter.voterId in(:voterIds) ");
		query.setParameterList("voterIds",voterIds);
		return query.list();	
	}
	 public List<UserVoterDetails> getUserVoterDetailsByVoterCardNo(String voterCardNo){
			
			Query query = getSession().createQuery("select model from UserVoterDetails model, BoothPublicationVoter model1 " +
					" where model.voter.voterId = model1.voter.voterId and model.voter.voterIDCardNo=:voterCardNo order by " +
					" model1.booth.publicationDate.publicationDateId desc ");
			
			query.setParameter("voterCardNo", voterCardNo);
			
			return query.list();
			
			
		}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteForVoter(List<Long> voterIds,Long userId)
	{
		Query query = getSession().createSQLQuery("SELECT UVD.voter_id,C.caste_name FROM user_voter_details UVD,caste_state CS,caste C where UVD.caste_state_id = CS.caste_state_id and CS.caste_id = C.caste_id and " +
				" UVD.user_id = :userId and UVD.voter_id in (:voterIds)");
		query.setParameterList("voterIds",voterIds);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteReport(Long constituencyId,Long publicationId,String type,Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct count(model.voter.voterId),model.casteState.caste.casteName,model.casteState.caste.casteId, ");
		if(type.equalsIgnoreCase(IConstants.MANDAL))
			str.append("bpv.booth.tehsil.tehsilId,bpv.booth.tehsil.tehsilName ");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			str.append("bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName,bpv.booth.tehsil.tehsilId,bpv.booth.tehsil.tehsilName ");
		else if(type.equalsIgnoreCase(IConstants.BOOTH))
			str.append("bpv.booth.boothId,bpv.booth.partNo,bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName ");
		else if(type.equalsIgnoreCase(IConstants.HAMLET))
			str.append("model.hamlet.hamletId,model.hamlet.hamletName,bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName ");
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("bpv.booth.boothId,bpv.booth.partNo,bpv.booth.localBody.localElectionBodyId,bpv.booth.localBody.name ");
			str.append("from UserVoterDetails model,BoothPublicationVoter bpv where model.user.userId = :userId and bpv.voter.voterId = model.voter.voterId" +
				" and bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.constituency.constituencyId = :constituencyId");
			
			
			if(type.equalsIgnoreCase(IConstants.MANDAL))
				str.append("  and bpv.booth.localBody is null group by model.casteState.caste.casteId,bpv.booth.tehsil.tehsilId order by model.casteState.caste.casteName");
		
			else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
				str.append("  group by model.casteState.caste.casteId,bpv.booth.panchayat.panchayatId order by bpv.booth.tehsil.tehsilName,bpv.booth.panchayat.panchayatId,model.casteState.caste.casteName");
			else if(type.equalsIgnoreCase(IConstants.BOOTH))
				str.append("  group by model.casteState.caste.casteId,bpv.booth.boothId order by bpv.booth.panchayat.panchayatName, bpv.booth.boothId," +
						" model.casteState.caste.casteName");
			else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append("  group by model.casteState.caste.casteId,bpv.booth.boothId order by bpv.booth.localBody.name, bpv.booth.boothId," +
						" model.casteState.caste.casteName");
			else if(type.equalsIgnoreCase(IConstants.HAMLET))
				str.append("  group by model.casteState.caste.casteId,model.hamlet.hamletId order by bpv.booth.panchayat.panchayatName, model.hamlet.hamletId," +
						" model.casteState.caste.casteName");	
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersDetailsBySearchCriteriaForHouseHolds(Long publicationDateId,String qry) {
		
		 	Query query = getSession().createQuery("select model.voter,model.booth.boothId,model.booth.partNo,model.serialNo from BoothPublicationVoter model where " +
		 		"  model.booth.publicationDate.publicationDateId = :publicationDateId "+qry) ;
			
		 	query.setParameter("publicationDateId", publicationDateId);
			
			return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterHnoAndBooths(Long constituencyId,Long publicationId)
	{
	    StringBuilder str =new StringBuilder();
		
		str.append("select model.booth.boothId,model.voter.houseNo from BoothPublicationVoter model where model.booth.constituency.constituencyId = :constituencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationId");
		
		str.append(" group by model.booth.boothId,model.voter.houseNo ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		//query.setFirstResult(0);
		//query.setMaxResults(100);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterHnoAndBoothsForPanchayatList(List<Long> panchayatIdsList,Long publicationId)
	{
	    Query query = getSession().createQuery("select model.booth.boothId,model.voter.houseNo,count(model.voter.voterId) from BoothPublicationVoter model where model.booth.panchayat.panchayatId in(:panchayatIdsList) and " +
				" model.booth.publicationDate.publicationDateId = :publicationId group by model.booth.boothId,model.voter.houseNo ");
		query.setParameterList("panchayatIdsList", panchayatIdsList);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getElderVoterDetails(Long boothId,String hno)
	{
		Query query = getSession().createQuery("select model.voter.voterId,model.voter.name,model.voter.age,model.voter.gender,model.voter.relativeName,model.voter.relationshipType,model.voter.voterIDCardNo from BoothPublicationVoter model where  model.booth.boothId = :boothId and model.voter.houseNo =:hno order by model.voter.age desc");
		query.setParameter("boothId", boothId);
		query.setParameter("hno", hno);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationForVoter(List<Long> boothIds,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.booth.boothId, ");
		if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append("booth.tehsil.tehsilName");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
		str.append("booth.panchayat.panchayatName");		
		str.append(" from BoothPublicationVoter model where model.booth.boothId in(:boothIds) ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("boothIds",boothIds);
		return query.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletForVoter(List<Long> voterIds,Long userId)
	{
		Query query = getSession().createSQLQuery("SELECT UVD.voter_id,H.hamlet_name FROM user_voter_details UVD,hamlet H where UVD.hamlet_id = H.hamlet_id and" +
				" UVD.user_id = :userId and UVD.voter_id in (:voterIds)");
		query.setParameterList("voterIds",voterIds);
		query.setParameter("userId", userId);
		return query.list();	
	}
	
	public List<UserVoterDetails> getVoterDetailsByUserIdAndVoterId(Long voterId,Long userId)
	{
		Query query = getSession().createQuery("select model from UserVoterDetails model " +
				"where model.voter.voterId = :voterId and model.user.userId =:userId");
		
		query.setParameter("voterId", voterId);
		query.setParameter("userId", userId);
		
		return query.list();
		
		
	}
	public List<Object[]> getLocalbodyCasteReport(Long constituencyId,Long publicationId,Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct count(model.voter.voterId),model.casteState.caste.casteName,model.casteState.caste.casteId, ");
		str.append("bpv.booth.localBody.localElectionBodyId,bpv.booth.localBody.name");
		str.append(" from UserVoterDetails model,BoothPublicationVoter bpv where model.user.userId = :userId and bpv.voter.voterId = model.voter.voterId" +
				" and bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.constituency.constituencyId = :constituencyId");
		str.append("  group by model.casteState.caste.casteId,bpv.booth.localBody.localElectionBodyId order by model.casteState.caste.casteName");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Long> getTehsils(Long constituencyId,Long publicationId)
	{
		Query query = getSession().createQuery("select model.tehsil.tehsilId from Booth model where model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId = :constituencyId group by model.tehsil.tehsilName");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
		
	}
	public List<Object[]> getPartialPanchayats(Long tehsilId,Long constituencyId,Long publicationId)
	{
		Query query = getSession().createQuery("select pbp.panchayat.panchayatId,b.panchayat.panchayatId from PartialBoothPanchayat pbp,Booth b where pbp.booth.boothId = b.boothId and b.constituency.constituencyId =:constituencyId and b.publicationDate.publicationDateId =:publicationId and b.tehsil.tehsilId =:tehsilId");
		query.setParameter("tehsilId", tehsilId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();	
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteReportForPartial(Long constituencyId,Long publicationId,String type,Long userId,String partialIds,Long tehsilId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct count(model.voter.voterId),model.casteState.caste.casteName,model.casteState.caste.casteId, ");
	    str.append("bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName,bpv.booth.tehsil.tehsilId,bpv.booth.tehsil.tehsilName ");
		str.append("from UserVoterDetails model,BoothPublicationVoter bpv where model.user.userId = :userId and bpv.voter.voterId = model.voter.voterId" +
				" and bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.constituency.constituencyId = :constituencyId and bpv.booth.tehsil.tehsilId =:tehsilId and bpv.booth.panchayat.panchayatId in("+partialIds+")");
		str.append("  group by model.casteState.caste.casteId,bpv.booth.panchayat.panchayatId order by bpv.booth.tehsil.tehsilName,bpv.booth.panchayat.panchayatId,model.casteState.caste.casteName");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		query.setParameter("tehsilId", tehsilId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteReportForNotPartial(Long constituencyId,Long publicationId,String type,Long userId,String partialIds,Long tehsilId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct count(model.voter.voterId),model.casteState.caste.casteName,model.casteState.caste.casteId, ");
	    str.append("bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName,bpv.booth.tehsil.tehsilId,bpv.booth.tehsil.tehsilName ");
		str.append("from UserVoterDetails model,BoothPublicationVoter bpv where model.user.userId = :userId and bpv.voter.voterId = model.voter.voterId" +
				" and bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.constituency.constituencyId = :constituencyId and bpv.booth.tehsil.tehsilId =:tehsilId and bpv.booth.panchayat.panchayatId not in("+partialIds+")");
		str.append("  group by model.casteState.caste.casteId,bpv.booth.panchayat.panchayatId order by bpv.booth.tehsil.tehsilName,bpv.booth.panchayat.panchayatId,model.casteState.caste.casteName");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		query.setParameter("tehsilId", tehsilId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterCountInLocation(Long constituencyId,Long publicationId,Long userId,Set<Long> panchayatIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select  count(distinct bpv.voter.voterId),");
	    str.append(" bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName ");
		str.append("from BoothPublicationVoter bpv  " +
				" where bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.constituency.constituencyId = :constituencyId");
		str.append(" and bpv.booth.localBody.localElectionBodyId is null ");
		if(panchayatIds != null && panchayatIds.size() > 0){
			str.append(" and bpv.booth.panchayat.panchayatId not in(:panchayatIds)");
		}
		str.append(" group by bpv.booth.panchayat.panchayatId order by bpv.booth.panchayat.panchayatName");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		if(panchayatIds != null && panchayatIds.size() > 0){
		  query.setParameterList("panchayatIds", panchayatIds);
		}
	
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterCountInLocation1(Long constituencyId,Long publicationId,Long userId,Set<Long> panchayatIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select  count(distinct bpv.voter.voterId),");
	    str.append(" ph.panchayat.panchayatId,ph.panchayat.panchayatName ");
		str.append("from UserVoterDetails uvd,BoothPublicationVoter bpv,PanchayatHamlet ph where bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.constituency.constituencyId = :constituencyId and " +
				" bpv.voter.voterId = uvd.voter.voterId and uvd.user.userId = :userId and ");
		str.append("  uvd.hamlet.hamletId = ph.hamlet.hamletId and bpv.booth.localBody is null and ph.panchayat.panchayatId in(:panchayatIds) group by ph.panchayat.panchayatId order by ph.panchayat.panchayatName ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameterList("panchayatIds", panchayatIds);
		query.setParameter("userId", userId);
	
		return query.list();
	}
	
	public List<Object[]> getPartialPanchayatsForConstituency(Long constituencyId,Long publicationId)
	{
		Query query = getSession().createQuery("select pbp.panchayat.panchayatId,b.panchayat.panchayatId from PartialBoothPanchayat pbp,Booth b where pbp.booth.boothId = b.boothId and b.constituency.constituencyId =:constituencyId and b.publicationDate.publicationDateId =:publicationId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();	
	}
	
	public Long getCasteVoterNamesOfAConstituency(Long constituencyId,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery("select distinct count(model.voter.voterId) from UserVoterDetails model,BoothPublicationVoter bpv where model.user.userId = :userId and bpv.voter.voterId = model.voter.voterId" +
				" and bpv.booth.publicationDate.publicationDateId = :publicationId and bpv.booth.constituency.constituencyId = :constituencyId and model.casteState.casteStateId is not null");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId",userId);
		return (Long)query.uniqueResult();	
	}
	
	
	@SuppressWarnings("unchecked")
	 public List<Object[]> getWardBYLocalElectionBodyId(Long id , Long publicationId ,Long userId)
	 {
		 Query query = getSession().createQuery(" select distinct UVD.voter.voterId, UVD.ward.name from UserVoterDetails UVD, BoothPublicationVoter BPV where " +
		 		" UVD.voter.voterId = BPV.voter.voterId and BPV.booth.localBody.localElectionBodyId = :localElectionBodyId and BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
		 		" UVD.user.userId = :userId order by UVD.ward.constituencyId ");
		 
		 query.setParameter("publicationDateId", publicationId);
		 query.setParameter("localElectionBodyId", id);
		 query.setParameter("userId", userId);
		 
		 return query.list();
	 }
	 
	 public List<Hamlet> getHamletByVoterId(Long voterId)
	 {
		 Query query = getSession().createQuery("select UVD.hamlet from UserVoterDetails UVD where UVD.voter.voterId = :voterId");
		 query.setParameter("voterId", voterId);
		 return query.list(); 
	 }
	 
	 public List<Constituency> getWardByVoterId(Long voterId)
	 {
		 Query query = getSession().createQuery("select UVD.ward from UserVoterDetails UVD where UVD.voter.voterId = :voterId");
		 query.setParameter("voterId", voterId);
		 return query.list();
				 
	 }
	 
	 public List<Object[]> getCasteCountByConstituencyIds(Long publicationDateId,Long userId)
	 {
		 StringBuilder str = new StringBuilder();
		   str.append("select distinct model.booth.constituency.constituencyId,model.booth.constituency.name,count(uvd.casteState.caste.casteId), model.booth.constituency.district.districtId,model.booth.constituency.district.districtName from BoothPublicationVoter model,UserVoterDetails uvd " +
		   		" where model.voter.voterId = uvd.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
		   str.append(" and uvd.user.userId = :userId and uvd.casteInsertType.casteInsertTypeId = :casteInsertTypeId  group by model.booth.constituency.constituencyId ");
		   Query query = getSession().createQuery(str.toString());
		   		
		   		query.setParameter("userId", userId);
		   		query.setParameter("publicationDateId", publicationDateId);
		   		query.setParameter("casteInsertTypeId", IConstants.CTP_CASTE_INSERT_TYPE);
		   		return query.list();	 
	 }
	 public List<Object[]> getCasteCountBylocationType(Long publicationDateId,Long userId,Long constituencyId,String locationType)
	 {
		 StringBuilder str = new StringBuilder();
		 if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select distinct model.booth.constituency.constituencyId,model.booth.constituency.name");	 
		 else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
		   str.append("select distinct model.booth.tehsil.tehsilId,model.booth.tehsil.tehsilName");
		 else if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			 str.append("select distinct model.booth.localBody.localElectionBodyId,model.booth.localBody.name");
		 else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append("select distinct model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName"); 
		 else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
			 str.append("select distinct model.booth.boothId,model.booth.partNo"); 
		 
		 str.append(" ,count(uvd.casteState.caste.casteId),uvd.voter.gender from BoothPublicationVoter model,UserVoterDetails uvd " +
		   		" where model.voter.voterId = uvd.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
		   str.append(" and uvd.user.userId = :userId and uvd.casteInsertType.casteInsertTypeId = :casteInsertTypeId and model.booth.constituency.constituencyId = :constituencyId");
		   if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			   str.append(" group by model.booth.constituency.constituencyId,uvd.voter.gender");
		   else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			   str.append(" group by model.booth.tehsil.tehsilId,uvd.voter.gender");
		   else if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			   str.append(" group by model.booth.localBody.localElectionBodyId,uvd.voter.gender");
		   else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			   str.append(" group by model.booth.panchayat.panchayatId,uvd.voter.gender");
			   else if(locationType.equalsIgnoreCase(IConstants.BOOTH))  
				   str.append(" group by model.booth.boothId,uvd.voter.gender");
		   Query query = getSession().createQuery(str.toString());
		   query.setParameter("userId", userId);
		   query.setParameter("publicationDateId", publicationDateId);
		   query.setParameter("casteInsertTypeId", IConstants.CTP_CASTE_INSERT_TYPE);
		   query.setParameter("constituencyId", constituencyId);
		   return query.list();	 
	 }
	 public List<Object[]> getCasteVotersCountBylocationTypeInConstituency(Long publicationDateId,Long userId,Long constituencyId,String locationType)
	 {
		 StringBuilder str = new StringBuilder();
		 if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select distinct model.booth.constituency.constituencyId,model.booth.constituency.name");	 
		 else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
		   str.append("select distinct model.booth.tehsil.tehsilId,model.booth.tehsil.tehsilName");
		 else if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			 str.append("select distinct model.booth.localBody.localElectionBodyId,model.booth.localBody.name");
		 else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append("select distinct model.booth.panchayat.panchayatId,model.booth.panchayat.panchayatName"); 
		 else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
			 str.append("select distinct model.booth.boothId,model.booth.partNo"); 
		 
		 str.append(" ,count(uvd.casteState.caste.casteId),uvd.voter.gender,uvd.casteState.caste.casteId,uvd.casteState.caste.casteName,uvd.casteState.casteCategoryGroup.casteCategory.categoryName from BoothPublicationVoter model,UserVoterDetails uvd " +
		   		" where model.voter.voterId = uvd.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
		   str.append(" and uvd.user.userId = :userId and uvd.casteInsertType.casteInsertTypeId = :casteInsertTypeId and model.booth.constituency.constituencyId = :constituencyId");
		   if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			   str.append(" group by model.booth.constituency.constituencyId,uvd.casteState.caste.casteId,uvd.voter.gender");
		   else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			   str.append(" group by model.booth.tehsil.tehsilId,uvd.casteState.caste.casteId,uvd.voter.gender");
		   else if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			   str.append(" group by model.booth.localBody.localElectionBodyId,uvd.casteState.caste.casteId,uvd.voter.gender");
		   else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			   str.append(" group by model.booth.panchayat.panchayatId,uvd.casteState.caste.casteId,uvd.voter.gender");
			   else if(locationType.equalsIgnoreCase(IConstants.BOOTH))  
				   str.append(" group by model.booth.boothId,uvd.casteState.caste.casteId,uvd.voter.gender");
		   Query query = getSession().createQuery(str.toString());
		   query.setParameter("userId", userId);
		   query.setParameter("publicationDateId", publicationDateId);
		   query.setParameter("casteInsertTypeId", IConstants.CTP_CASTE_INSERT_TYPE);
		   query.setParameter("constituencyId", constituencyId);
		   return query.list();	 
	 }
	 public List<Object[]> getCasteVotersDetailsBylocationTypeInConstituency(Long publicationDateId,Long userId,Long constituencyId,String locationType,Long casteId,String gender,Long locationId,String queryStr)
	 {
		 StringBuilder str = new StringBuilder();
		 str.append("select distinct uvd.voter,model.booth.boothId,model.booth.partNo,model.serialNo,uvd.casteState.caste.casteName");	 
		 str.append("  from BoothPublicationVoter model,UserVoterDetails uvd " +
		   		" where model.voter.voterId = uvd.voter.voterId and model.booth.publicationDate.publicationDateId = :publicationDateId ");
		   str.append(" and uvd.user.userId = :userId and uvd.casteInsertType.casteInsertTypeId = :casteInsertTypeId and model.booth.constituency.constituencyId = :constituencyId "+queryStr);
		  if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			   str.append(" and model.booth.tehsil.tehsilId = :locationId");
		   else if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			   str.append(" and model.booth.localBody.localElectionBodyId = :locationId");
		   else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			   str.append(" and model.booth.panchayat.panchayatId = :locationId");
			   else if(locationType.equalsIgnoreCase(IConstants.BOOTH))  
				   str.append(" and model.booth.boothId = :locationId");
		  if(casteId > 0)
			  str.append(" and uvd.casteState.caste.casteId = :casteId");
		  if(!gender.trim().equalsIgnoreCase(IConstants.ALL))
			  str.append(" and uvd.voter.gender = :gender");  
		 
			 
		   Query query = getSession().createQuery(str.toString());
		   query.setParameter("userId", userId);
		   query.setParameter("publicationDateId", publicationDateId);
		   query.setParameter("casteInsertTypeId", IConstants.CTP_CASTE_INSERT_TYPE);
		   query.setParameter("constituencyId", constituencyId);
		   if(!locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		   query.setParameter("locationId", locationId);
		   if(!gender.trim().equalsIgnoreCase(IConstants.ALL))
			query.setParameter("gender", gender.trim());
		   if(casteId > 0)  
			   query.setParameter("casteId", casteId);
		   return query.list();	 
	 }
	 
	 public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId)
		{
			StringBuilder queryStr = new StringBuilder();
			boolean isStateWise = false;
			StringBuilder str  = new StringBuilder();
			
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" select distinct model.booth.constituency.constituencyId,count(*) ");
				str.append(" , model.booth.constituency.name from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.constituency.district.districtId in (:locationIdsList) ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.constituency.constituencyId order by model.booth.constituency.name ");
			}			
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{				
				str.append(" select distinct model.booth.tehsil.tehsilId,count(*) ");
				str.append(" , model.booth.tehsil.tehsilName from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.constituency.constituencyId in (:locationIdsList) and  model.booth.localBody.localElectionBodyId is null ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.tehsil.tehsilId order by model.booth.tehsil.tehsilName ");				
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" select distinct model.booth.panchayat.panchayatId,count(*) ");
				str.append(" , model.booth.panchayat.panchayatName from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.tehsil.tehsilId in (:locationIdsList) ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.panchayat.panchayatId order by model.booth.panchayat.panchayatName ");				
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" select distinct model.booth.localBody.localElectionBodyId,count(*) ");
				str.append(" , model.booth.localBody.name from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.localBody.localElectionBodyId in (:locationIdsList) and  model.booth.localBody.localElectionBodyId is not null ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.localBody.localElectionBodyId order by model.booth.localBody.name ");				
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{				
				str.append(" select distinct model.booth.localBodyWard.constituencyId,count(*) ");
				str.append(" , model.booth.localBodyWard.name from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.localBodyWard.constituencyId in (:locationIdsList) and  model.booth.localBody.localElectionBodyId is not null and model.booth.localBodyWard.constituencyId is not null  ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.localBodyWard.constituencyId order by model.booth.localBodyWard.name ");	
			}
			else if(stateId != null && stateId.longValue() == 0L) //AP & TS
			{
				isStateWise = true;
				str.append(" select distinct  model.booth.constituency.district.districtId,count(*) ");
				str.append(" ,  model.booth.constituency.district.districtName from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.constituency.district.districtId between 1 and 23 ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.constituency.district.districtId  order by model.booth.constituency.district.districtName ");
				
			}
			else if(stateId != null && stateId.longValue() == 1L) //AP
			{
				isStateWise = true;
				str.append(" select distinct  model.booth.constituency.district.districtId,count(*) ");
				str.append(" ,  model.booth.constituency.district.districtName from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.constituency.district.districtId between 11 and 23 ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.constituency.district.districtId  order by model.booth.constituency.district.districtName ");
			}
			else if(stateId != null && stateId.longValue() == 2L) //TS
			{
				isStateWise = true;
				str.append(" select distinct  model.booth.constituency.district.districtId,count(*) ");
				str.append(" ,  model.booth.constituency.district.districtName from BoothPublicationVoter model,UserVoterDetails model2 where model.voter.voterId = model2.voter.voterId and model.booth.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and " +
						" model.booth.constituency.district.districtId between 1 and 10 ");
				str.append(" and model2.casteState.casteStateId =:casteStateId group by model.booth.constituency.district.districtId  order by model.booth.constituency.district.districtName ");
			}
			
			
			queryStr.append(str.toString());
			
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("casteStateId", casteStateId);
			
			if(!isStateWise && (locationIdsList != null && locationIdsList.size()>0))
			{
				query.setParameterList("locationIdsList", locationIdsList);
			}
			
			return query.list();
		}
	 
	 public List<Object[]> getVotersCasteNAgeGroupWiseCount(Long casteGroupId,Long casteId,List<Long> constituencyIds,Long publicationDateId){
		//0-ageRangeId,1-ageRange,2-gender,3-votersCount
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select model.voter.voterAgeRange.voterAgeRangeId,model.voter.voterAgeRange.ageRange, " +
			 		" model.voter.gender,count(model.voter.voterId) " +
			 		" from UserVoterDetails model,BoothPublicationVoter model1 " +
			 		" where model.voter.voterId = model1.voter.voterId " );
		 if(constituencyIds!= null && constituencyIds.size()>0){
			 sb.append("and model1.booth.constituency.constituencyId in (:constituencyId)");
		 }
		 if(publicationDateId!= null && publicationDateId >0){
			 sb.append(" and model1.booth.publicationDate.publicationDateId = :publicationDateId ");
		 }
		 sb.append("and model.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId " +
		 		" and model.casteState.caste.casteId = :casteId " +
		 		" group by model.voter.voterAgeRange.voterAgeRangeId,model.voter.gender " +
		 		" order by model.voter.voterAgeRange.voterAgeRangeId ");
		 
		 Query query = getSession().createQuery(sb.toString());
		 if(constituencyIds!= null && constituencyIds.size()>0){		
		 query.setParameterList("constituencyId", constituencyIds);
		 }
		 if(publicationDateId!= null && publicationDateId >0){
			 query.setParameter("publicationDateId", publicationDateId);
		 }
		 query.setParameter("casteGroupId", casteGroupId);
		 query.setParameter("casteId", casteId);
		 
		 return query.list();
	 }

	@Override
	public List<Object[]> getVotersCasteNAgeGroupWiseCounts(Long casteGroupId,Long casteId, Long reportLevelId,Long locationTypeId, List<Long> locationValues,Long publicationDateId) {
		 StringBuilder sb = new StringBuilder();
		 //0 ageRangeId,1 gender,2 count
		 sb.append(" select model.voter_age_range_id as ageRangeId, model.gender as gender,SUM(model.total_voters) as count from voter_caste_age_range_info model ");
		 sb.append(",caste_state cs ");
		
		 if(locationTypeId == 2l){
			 sb.append(" , state s "); 
		 }
		 else if(locationTypeId == 3l){
			 sb.append(" , district d ");
		 }else  if(locationTypeId == 4l || locationTypeId == 10L){
			 sb.append(" , constituency c ");
		 }else   if(locationTypeId == 5l){
			sb.append(" , tehsil t ");
		 }
	     else  if(locationTypeId == 6l){
			sb.append(" , panchayat p ");
		 }
			 sb.append(" where ");
			 sb.append(" model.caste_state_id=cs.caste_state_id and ");
			 if(locationTypeId == 2l){
				 sb.append(" model.location_scope_id =:locationTypeId and s.state_id in (:locationValues)");
			 }else if(locationTypeId == 3l){
				 sb.append(" model.location_scope_id =:locationTypeId and d.district_id in (:locationValues)");
			 }else if(locationTypeId == 4l || locationTypeId == 10L){
				 sb.append(" model.location_scope_id =:locationTypeId and c.constituency_id in (:locationValues)");
			 }
	
			
			 if(locationTypeId == 5l){
				 sb.append(" model.location_scope_id =:locationTypeId and t.tehsil_id in (:locationValues)");
			 }if(locationTypeId == 6l){
				 sb.append(" model.location_scope_id =:locationTypeId and p.panchayat_id in (:locationValues)");
			 }
			 
		 
			 if(locationTypeId == 2l){
					sb.append(" and model.location_value = s.state_id ");
			 }else if(locationTypeId == 3l){
					sb.append(" and model.location_value = d.district_id ");
			 }else if(locationTypeId == 4l || locationTypeId == 10L){
					sb.append(" and model.location_value = c.constituency_id ");
			 }else if(locationTypeId == 5l){
					sb.append(" and model.location_value = t.tehsil_id ");
			  }
				
			  else if(locationTypeId == 6l){
					sb.append(" and model.location_value = p.panchayat_id ");
			 }
		 sb.append(" and cs.caste_id =:casteId and model.publication_date_id =:publicationDateId ");
		 
		 sb.append(" group by model.voter_age_range_id, model.gender ");
		 
		 SQLQuery query = getSession().createSQLQuery(sb.toString())
					.addScalar("ageRangeId",Hibernate.LONG)
					.addScalar("gender",Hibernate.STRING)
					.addScalar("count",Hibernate.LONG);
					
		
		
		if(casteId != null && casteId > 0l){
	    	   query.setParameter("casteId", casteId);
	    }
		if(locationValues != null && locationValues.size() > 0l){
	    	   query.setParameterList("locationValues", locationValues);
	    }
		if(locationTypeId != null && locationTypeId > 0l){
	    	   query.setParameter("locationTypeId", locationTypeId);
	    }
		if (publicationDateId != null && publicationDateId.longValue() > 0L) {
			query.setParameter("publicationDateId", publicationDateId);
		}
		 return query.list();
	}
	
public List<Object[]> getLocationWiseVoterCounts(Long locationTypeId,List<Long> locationValue,Long casteId,Long publicationDateId){
		

	StringBuilder sb = new StringBuilder();

	sb.append("SELECT ");
	if (locationTypeId != null && locationTypeId.longValue() == 2L) {
		sb.append("d.district_id as locationId, d.district_name as locationName ");
	} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L)) {
		sb.append("c.constituency_id as locationId, c.name as locationName ");
	}else if (locationTypeId != null && locationTypeId.longValue() == 4L) {
		sb.append("t.tehsil_id as locationId, t.tehsil_name as locationName ");
	}else if (locationTypeId != null && locationTypeId.longValue() == 5L) {
		sb.append("p.panchayat_id as locationId, p.panchayat_name as locationName ");
	}

	sb.append(",model.gender as gender,sum(model.total_voters) as count from voter_caste_age_range_info model ");
	sb.append(",caste_state cs ");
	if (locationTypeId != null && locationTypeId.longValue() == 2L) {
		sb.append(" , district d ");
	} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L )) {
		sb.append(" , constituency c ");
	}else if (locationTypeId != null && locationTypeId.longValue() == 4L) {
		sb.append(" , tehsil t , panchayat p ");
	}else if (locationTypeId != null && locationTypeId.longValue() == 5L) {
		sb.append(" , panchayat p ");
	}

	sb.append("where ");
	sb.append(" model.caste_state_id=cs.caste_state_id and ");
	if (locationTypeId != null && locationTypeId.longValue() == 2L) {
		sb.append(" model.location_scope_id =3 and model.location_value = d.district_id ");
		sb.append(" and (d.district_id between 11 and 23) ");
	} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L)) {
		sb.append(" model.location_scope_id =3 and model.location_value = c.constituency_id ");
	}else if (locationTypeId != null && locationTypeId.longValue() == 4L) {
		sb.append(" model.location_scope_id =4 and model.location_value = p.panchayat_id ");
		sb.append(" and p.tehsil_id=t.tehsil_id ");
	}else if (locationTypeId != null && locationTypeId.longValue() == 5L) {
		sb.append(" model.location_scope_id =5 and model.location_value = p.panchayat_id ");
	}

	sb.append(" and model.publication_date_id =:publicationDateId and cs.caste_id =:casteId ");

	if (locationTypeId !=null && locationTypeId.longValue() >2l && locationValue != null && locationValue.size() >0) {
		sb.append(" and model.location_value in (:locationValue) ");
	}
	
	if (locationTypeId != null && locationTypeId.longValue() == 2L) {
		sb.append(" GROUP BY d.district_id ");
	} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L)) {
		sb.append(" GROUP BY c.constituency_id ");
	}else if (locationTypeId != null && locationTypeId.longValue() == 4L) {
		sb.append(" GROUP BY p.tehsil_id ");
	}
	else if (locationTypeId != null && locationTypeId.longValue() == 5L) {
		sb.append(" GROUP BY p.panchayat_id ");
	}
	
	sb.append(" , model.gender ");
	Query query = getSession().createSQLQuery(sb.toString())
			.addScalar("locationId", Hibernate.LONG)
			.addScalar("locationName", Hibernate.STRING)
			.addScalar("gender", Hibernate.STRING)
			.addScalar("count", Hibernate.LONG);
   
	if (locationTypeId !=null && locationTypeId.longValue() >2l && locationValue != null && locationValue.size() >0) {
		query.setParameterList("locationValue", locationValue);
	}
	if (casteId != null && casteId.longValue() > 0L) {
		query.setParameter("casteId", casteId);
	}
	if (publicationDateId != null && publicationDateId.longValue() > 0L) {
		query.setParameter("publicationDateId", publicationDateId);
	}

	return query.list();
	}
}

