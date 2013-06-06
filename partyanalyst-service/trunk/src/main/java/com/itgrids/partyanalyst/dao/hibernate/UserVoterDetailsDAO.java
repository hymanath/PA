package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
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
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 25  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun , " +
				
				
				" SUM( CASE WHEN model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " +		
				" SUM( CASE WHEN model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCoun , " +	
				" SUM( CASE WHEN model.voter.age BETWEEN  36 and 45 THEN 1 ELSE 0 END) as ageCou , " +
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
								
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount1 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount2 , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 25  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount3 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as malecoun4 , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount5 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount6 , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as malecoun11 , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount7 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount8 , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun12 , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount9 , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount10 , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun13 , " +
				
				
				" SUM( CASE WHEN model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " +		
				" SUM( CASE WHEN model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCoun , " +	
				" SUM( CASE WHEN model.voter.age BETWEEN  36 and 45 THEN 1 ELSE 0 END) as ageCou , " +
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
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 25  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun , " +
				
				" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +	
				" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " +
				" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun , " +
				
				
				" SUM( CASE WHEN model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " +		
				" SUM( CASE WHEN model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCoun , " +	
				" SUM( CASE WHEN model.voter.age BETWEEN  36 and 45 THEN 1 ELSE 0 END) as ageCou , " +
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
			public List<Voter> getVoterIdsForuserinHamletByBoothsandByCasteId(Long userId ,Long hamletId,Long casteStateId ,long boothId , long publicationId)
			{ 
				
				Query query = getSession().createQuery("select model.voter from UserVoterDetails model, " +
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
						
				  query.append(	" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(	" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(	" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  18 and 25  THEN 1 ELSE 0 END) as malecoun , " );
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as malecoun , ");
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  36 and 45  THEN 1 ELSE 0 END) as malecoun , " );
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age BETWEEN  46 and 60  THEN 1 ELSE 0 END) as malecoun , " );
						
				  query.append(" SUM( CASE WHEN model.voter.gender='F' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " );	
				  query.append(" SUM( CASE WHEN model.voter.gender='M' and model.voter.age >60  THEN 1 ELSE 0 END) as ageCount , " );
				  query.append(" SUM( CASE WHEN model.voter.gender != 'M' and model.voter.gender != 'F' and model.voter.age >60  THEN 1 ELSE 0 END) as malecoun , " );
						
						
				  query.append(" SUM( CASE WHEN model.voter.age BETWEEN  18 and 25 THEN 1 ELSE 0 END) as ageCount , " );		
				  query.append(" SUM( CASE WHEN model.voter.age BETWEEN  26 and 35 THEN 1 ELSE 0 END) as ageCoun , " );
				  query.append(" SUM( CASE WHEN model.voter.age BETWEEN  36 and 45 THEN 1 ELSE 0 END) as ageCou , " );
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
							  
		  query.append(" count( distinct model.voter.voterId), ");	   
		
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
	 public List<Object[]> getWardsBYLocalElectionBodyId(Long id , Long publicationId ,Long userId)
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
	 * This DAO  is used For Getting Age Wise Details(18-25,26-35 ..) for Seleted custom ward in the Selected Muncipality
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
	public List<Voter> getVoterDetailsForCustomWardByBooth(Long boothId,Long publicationDateId,Long userId,Long casteStateId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.voter from BoothPublicationVoter model,UserVoterDetails model2 ");
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
	
	
	
	
}