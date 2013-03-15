package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;

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
	public void updateUserVoterDetails3(Long voterId,Long userId,Long castStateId,Long localitityId,Long hamletId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.casteState.casteStateId = :castStateId,model.locality.localityId = :localityId,model.hamlet.hamletId = :hamletId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("castStateId",castStateId);
		query.setParameter("localityId",localitityId);
		query.setParameter("hamletId",hamletId);
		
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
	
	public List<Long> getUserHamletsByPanchayatId(Long userId , Long panchayatId)
	{
		
		
		Query query = getSession().createQuery("select distinct model.hamlet.hamletId from UserVoterDetails model, PanchayatHamlet model1" +
				" where model.user.userId = :userId and model.hamlet.hamletId = model1.hamlet.hamletId and " +
				" model1.panchayat.panchayatId = :panchayatId");
		
		query.setParameter("userId", userId);
		query.setParameter("panchayatId", panchayatId);
		
		return query.list();
	}
	public List<Long> getVotersCountForALocality(Long hamletId,Long id,Long userId)
	{
		Query query = getSession().createQuery("select count(*) from UserVoterDetails model where model.hamlet.hamletId = :hamletId and" +
				" model.user.userId = :userId and model.locality.localityId = :localityId");
		
		query.setParameter("hamletId", hamletId);
		query.setParameter("localityId", id);
		query.setParameter("userId", userId);
		
		
		return query.list();
		
	}
	
	public List<Long> getVoterIdsBasedOnHamletAndLocality(Long hamletId ,Long localityId , Long userId)
	{
		
		
		Query query = getSession().createQuery("select distinct model.voter.voterId " +
							"from UserVoterDetails model  where  model.hamlet.hamletId = ? " +
							"and model.locality.localityId = ? and model.user.userId = ? ");
		
		query.setParameter(0, hamletId);
		query.setParameter(1, localityId);
		query.setParameter(2, userId);
		
		return query.list();
	}
	
	public List<Object[]> getVotersCountByGenderForLocalityInHamlet(Long userId , Long hamletId , Long localityId)
	{
		Query query = getSession().createQuery("select count(*),model.voter.gender   from UserVoterDetails model " +
				"where model.hamlet.hamletId = ? and model.user.userId =? and model.locality.localityId = ? " +
				"group by model.voter.gender ");
		
		query.setParameter(0, hamletId);
		query.setParameter(1, userId);
		query.setParameter(2, localityId);
		
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
	
	public List<Object[]> getVotersCountByGenderForLocalAreas(List<?> voterIds)
	{
		
		Query query = getSession().createQuery("select distinct model.locality.name," +
				"SUM( CASE WHEN model.voter.gender='F' THEN 1 ELSE 0 END) as femalecount ," +
				"SUM( CASE WHEN model.voter.gender='M' THEN 1 ELSE 0 END) as malecount " +
				" from UserVoterDetails model  " +
		        " join model.locality " +
				"where model.voter.voterId in(:voterIds)    group by model.locality.name ");
		
		query.setParameterList("voterIds", voterIds);
		
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
	
	public List<?> getVotersIdsByHamletId(Long hamletId,Long userId)
	{
		
		Query query = getSession().createQuery("select model.voter.voterId from UserVoterDetails model where" +
				" model.hamlet.hamletId = ? and model.user.userId = ? ");
		
		query.setParameter(0, hamletId);
		query.setParameter(1, userId);
	
		
		
		//return query.list();
		
		return query.list();
			
	}
	public List<?> getVoterIdsBasedOnVoterIdsAndPublication(
			 Long publicationDateId , List<?> voterIds) {		
			
			
		Query query = getSession().createQuery("select distinct b.voter.voterId " +
				" from BoothPublicationVoter b where  b.voter.voterId in (:voterIds) " +
				"   and  b.booth.publicationDate.publicationDateId = :publicationDateId " 
								) ;
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("voterIds", voterIds);	
		  return query.list();
		
	}
	
	public List<Long> getVoterIdsByLocalityForUser(Long localityId,Long hamletId,Long userId,Long casteStateId)
	{
		
		Query query = getSession().createQuery("select distinct model.voter.voterId from UserVoterDetails model" +
				" where model.locality.localityId = :localityId and " +
				"model.hamlet.hamletId = :hamletId and model.user.userId = :userId and model.casteState.casteStateId = :casteStateId");
		
		query.setParameter("localityId", localityId);
		query.setParameter("hamletId", hamletId);
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
	
}