package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Voter;

public class BoothConstituencyElectionVoterDAO extends GenericDaoHibernate<BoothConstituencyElectionVoter, Long> implements IBoothConstituencyElectionVoterDAO{
	public BoothConstituencyElectionVoterDAO(){
		super(BoothConstituencyElectionVoter.class);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElection(Long boothConstituencyElectionId) {
		return getHibernateTemplate().find("from BoothConstituencyElectionVoter model where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = ?", boothConstituencyElectionId);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElectionAndVoter(Long boothConstituencyElectionId, Long voterId) {
		Object[] params = {boothConstituencyElectionId, voterId};
		return getHibernateTemplate().find("from BoothConstituencyElectionVoter model where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = ? and " +
				"model.voter.voterId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalVotersCountByHamletAndElectionYear(Long hamletId, String year){
		Query queryObject = getSession().createQuery("select count(model.voter.voterId) from BoothConstituencyElectionVoter model where " +
				"model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? ");
		queryObject.setLong(0, hamletId);
		queryObject.setString(1, year);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalVoterHousesCountByHamletAndElectionYear(Long hamletId, String year){
		Query queryObject = getSession().createQuery("select count(distinct model.voter.houseNo) from BoothConstituencyElectionVoter model where " +
				"model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? ");
		queryObject.setLong(0, hamletId);
		queryObject.setString(1, year);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findVotersForHamletAndElectionYearByStartAndMaxResults(Long hamletId, String year, 
			int startIndex, int maxResult, String order, String columnName){
		Query queryObject = getSession().createQuery("select model.voter.name,model.voter.houseNo, model.voter.age, " +
				"model.voter.cast, model.voter.castCatagery, model.voter.castSubCatagery, model.voter.gender,model.voter.relativeName, " +
				"model.voter.relationshipType, model.voter.voterId from BoothConstituencyElectionVoter model where " +
				"model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				" order by model.voter."+columnName+" "+order);
		queryObject.setLong(0, hamletId);
		queryObject.setString(1, year);
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
		return queryObject.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Voter> findVotersGroupByHouseNoForHamlet(Long hamletId, String year){
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select model.voter from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<String> findVoterHouseNosInHamlet(Long hamletId, String year) {
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select distinct(model.voter.houseNo) from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<Voter> findVotersByHouseNoAndHamlet(String houseNo, Long hamletId,String year) {
		Object[] params = {houseNo, hamletId, "m", year};
		return getHibernateTemplate().find("select model.voter from BoothConstituencyElectionVoter model " +
				"where model.voter.houseNo = ? and model.voter.hamlet.hamletId = ? and model.voter.gender = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? order by age", params);
	}
	
	public List findTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		return getHibernateTemplate().find("select model.voter.hamlet.township.townshipId, model.voter.hamlet.township.townshipName, " +
				"model.boothConstituencyElection.booth.boothId, model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.totalVoters, " +
				"model.boothConstituencyElection.boothResult.validVotes" +
				" from BoothConstituencyElectionVoter model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? group by " +
				"model.boothConstituencyElection.booth.partNo, model.voter.hamlet.hamletName order by " +
				"model.voter.hamlet.township.townshipName, model.boothConstituencyElection.booth.partNo",params);
	}
	
	
	
	public List findTotalVotersForHamlet(Long revenueVillageID, String year, String electionType){
		Object[] params = {revenueVillageID, year,electionType};
		return getHibernateTemplate().find("select model.voter.hamlet.hamletId, model.voter.hamlet.hamletName, count(model.voter) from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.hamlet.hamletName order by model.voter.hamlet.hamletName", params);
		
	}
	
	public List findHamletBoothsForRevenueVillage(Long revenueVillageID, String year, String electionType){
		Object[] params = {revenueVillageID, year, electionType};
		return getHibernateTemplate().find("select model.voter.hamlet.hamletName, model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.boothId " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.hamlet.hamletName, model.boothConstituencyElection.booth.partNo order by model.voter.hamlet.hamletName", params);
	}
	
	public List findCastWiseVoterForRevenueVillage(Long revenueVillageID, String year, String electionType){
		Object[] params = {revenueVillageID, year, electionType};
		return getHibernateTemplate().find("select model.voter.cast, count(model.voter.cast) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.cast order by model.voter.cast", 
				params);
	}
	
	public List findAgeWiseVotersForRevenueVillage(Long revenueVillageID, String year, String electionType, Long minAge, Long maxAge){
		Object[] params = {revenueVillageID, year, electionType, minAge, maxAge};
		return getHibernateTemplate().find("select model.voter.gender, count(model.voter) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and " +
				"model.voter.age>=? and model.voter.age<? " +
				"group by model.voter.gender order by model.voter.gender", 
				params);
	}
	
	/*public List findAllBoothVotersForHamlet(Long hamletID, String year, String electionType){
		Object[] params = {hamletID, year, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.boothConstituencyElectionId, model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.partName, count(model.voter) " +
				"from BoothConstituencyElectionVoter model where model.voter.hamlet.hamletId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.boothConstituencyElection.booth.partNo order by model.boothConstituencyElection.booth.partNo", 
				params);
	}*/

	public List findCastWiseVoterForHamlet(Long hamletID, String year, String electionType){
		Object[] params = {hamletID, year, electionType};
		return getHibernateTemplate().find("select model.voter.cast, count(model.voter.cast) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.cast order by model.voter.cast", 
				params);
	}
	
	public List findAgeWiseVotersForHamlet(Long hamletID, String year, String electionType, Long minAge, Long maxAge){
		Object[] params = {hamletID, year, electionType, minAge, maxAge};
		return getHibernateTemplate().find("select model.voter.gender, count(model.voter) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and " +
				"model.voter.age>=? and model.voter.age<? " +
				"group by model.voter.gender order by model.voter.gender", 
				params);
	}
	
	public List getTownshipVotesByTehsil(Long electionID, Long tehsilID){
//townshipID, townshipName, totalVoters, validVoters, boothID, partNo, hamletId, hamletName	
		Object[] params = {electionID, tehsilID};
		StringBuilder query = new StringBuilder();
		query.append("select model.voter.hamlet.township.townshipId, model.voter.hamlet.township.townshipName, ")
			.append("model.boothConstituencyElection.booth.totalVoters, ")
			.append("model.boothConstituencyElection.boothResult.validVotes, ")
			.append("model.boothConstituencyElection.booth.boothId, ")
			.append("model.boothConstituencyElection.booth.partNo, ")
			.append("model.voter.hamlet.hamletId, ")
			.append("model.voter.hamlet.hamletName ");
		query.append("from BoothConstituencyElectionVoter model ");
		query.append("where model.boothConstituencyElection.constituencyElection.election.electionId=? and ")
			//.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and ")
			.append("model.boothConstituencyElection.booth.tehsil.tehsilId = ? ");
		query.append("group by model.boothConstituencyElection.booth.boothId, model.voter.hamlet.hamletName ");
		query.append("order by model.voter.hamlet.township.townshipName");
		List l = getHibernateTemplate().find(query.toString(),params);
		return l;
	}
	
	/*public List getTownshipWiseParyVotesByTehsil(String electionYear, String electionType, Long tehsilID){
		Object[] params = {electionYear, electionType, tehsilID};
		StringBuilder query = new StringBuilder();
		query.append("select  ");
		query.append("from BoothConstituencyElectionVoter model ");
		query.append("where model.boothConstituencyElection.constituencyElection.election.electionYear=? and ")
			.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and ")
			.append("model.boothConstituencyElection.booth.tehsil.tehsilId = ? ");
		return getHibernateTemplate().find(query.toString(),params);
	}*/
	
	public List getAllCandidateBoothResultsForTownshipsForTehsil(Long tehsilId){
		return getHibernateTemplate().find("select model.voter.hamlet.township.townshipName,model.boothConstituencyElection.candidateBoothResults from BoothConstituencyElectionVoter model where model.voter.hamlet.township.tehsil.tehsilId = ? group by " +
				"model.voter.hamlet.township.townshipId", tehsilId);
	}
	
	public List getBoothsForTownship(Long townshipId){
		return getHibernateTemplate().find("select distinct model.boothConstituencyElection.booth.boothId from " +
				"BoothConstituencyElectionVoter model where model.voter.hamlet.township.townshipId = ?", townshipId);
	}

	
	/*public List findVotersInfoForHamletAndElectionYear(Long hamletId,
			String year) {
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select model.voter.firstName, model.voter.lastName, model.voter.houseNo, model.voter.age, " +
		"model.voter.cast, model.voter.castCatagery, model.voter.castSubCatagery, model.voter.gender,model.voter.relativeFirstName, " +
		"model.voter.relativeLastName, model.voter.relationshipType, model.voter.voterId from BoothConstituencyElectionVoter model where " +
		"model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
		" order by model.voter.voterId", params);
	}*/
	public List findVotersInfoForHamletAndElectionYear(Long panchayatId,
			String year) {
		Object[] params = {1l, year};
		return getHibernateTemplate().find("select model.voter.name,model.voter.houseNo, model.voter.age, " +
		"model.voter.cast, model.voter.castCatagery, model.voter.castSubCatagery, model.voter.gender,model.voter.relativeName, " +
		"model.voter.relationshipType, model.voter.voterId from BoothConstituencyElectionVoter model where " +
		"model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
		" order by model.voter.voterId", params);
	}
	//Impotant families Details For Panchayat
	public List findVotersInfoForPanchayatAndElectionYear(Long hamletId,
	String year) {
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select model.voter.name,model.voter.houseNo, model.voter.age, " +
				"model.voter.cast, model.voter.castCatagery, model.voter.castSubCatagery, model.voter.gender,model.voter.relativeName, " +
				"model.voter.relationshipType, model.voter.voterId from BoothConstituencyElectionVoter model where " +
				"model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				" order by model.voter.voterId", params);
}
	
	//Impotant families count For Panchayat
	public List<Object[]> getVotersInfoForPanchayatAndElectionYear(Long panchayatId,
			String year)
	{
	Object[] params = {panchayatId, year};
	return getHibernateTemplate().find("select model.voter.houseNo,count(model.voter.houseNo) from BoothConstituencyElectionVoter model where " +
			"model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
			" group by model.voter.hamlet.hamletId,model.voter.houseNo", params);
		}
	
	
	
	//Impotant families age wise count For Panchayat
		public Long getVotersAgeInfoForPanchayatAndElectionYear(Long panchayatId,
				String year,Long minAge,Long maxAge)
		{
		Object[] params = {panchayatId, year};
		Query query = getSession().createQuery("select count(model.voter.voterId) from BoothConstituencyElectionVoter model where " +
				"model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				" and model.voter.age BETWEEN ? AND ?");
		
		query.setParameter(0,panchayatId);
		query.setParameter(1,"2009");
		query.setParameter(2,minAge);
		query.setParameter(3,maxAge);
		
		return (Long)query.uniqueResult();
			}
		
		
		public Long getVotersAboveAgeInfoForPanchayatAndElectionYear(Long panchayatId,
				String year)
		{
		Object[] params = {panchayatId, year};
		Query query = getSession().createQuery("select count(model.voter.voterId) from BoothConstituencyElectionVoter model where " +
				"model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				" and model.voter.age > 60");
		
		query.setParameter(0,panchayatId);
		query.setParameter(1,"2009");
		
		
		return (Long)query.uniqueResult();
			}
		
		
	//caste info for Panchayat
		public List findVotersCastInfoByPanchayatAndElectionYear(Long hamletId, String year){
			Object[] params = {hamletId, year};
			return getHibernateTemplate().find("select count(model.voter.voterId), model.voter.gender, model.voter.cast from BoothConstituencyElectionVoter model " +
					"where model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
					"group by model.voter.cast order by model.voter.cast", params);
		}
	
	//caste info for Polling station
		public List findVotersCastInfoByPollingStationAndElectionYear(Long hamletId, String year){
			Object[] params = {hamletId, year};
			return getHibernateTemplate().find("select count(model.voter.voterId), model.voter.gender, model.voter.cast from BoothConstituencyElectionVoter model " +
					"where model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from HamletBoothElection model1 where model1.boothConstituencyElection.boothConstituencyElectionId in(?)) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
			"group by model.voter.cast order by model.voter.cast", params);
		}	
	//caste info for Hamlet
		public List findVotersCastInfoByHamletAndElectionYear(Long hamletId, String year){
			Object[] params = {hamletId, new Long(year)};
			return getHibernateTemplate().find("select count(model.voter.voterId), model.voter.gender, model.voter.cast from BoothConstituencyElectionVoter model " +
					"where model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.booth.year = ? " +
					"group by model.voter.cast, model.voter.gender order by model.voter.cast", params);
		}
	@SuppressWarnings("unchecked")
	public List<Object> getGenderOfVotersInALocation(String queryStr,Long locationId)
	{
		return getHibernateTemplate().find("select model.voter.gender from BoothConstituencyElectionVoter model where "+queryStr + " "+locationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getTotalNoOfVotersInALocation(String queryStr,Long locationId)
	{
		return getHibernateTemplate().find("select count(model.voter.voterId) from BoothConstituencyElectionVoter model where "+queryStr + " "+locationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getTotalNoOfMaleVotersInALocation(String queryStr,Long locationId)
	{
		return getHibernateTemplate().find("select count(model.voter.voterId) from BoothConstituencyElectionVoter model where model.voter.gender = 'M' and "+queryStr + " "+locationId);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]>  getVotersBasicInfoByPanchayatId(Long panchayatId, String year){
		Object[] params = {panchayatId, year};
		return getHibernateTemplate().find("select count(model.voter.voterId), model.voter.gender from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				"group by model.voter.gender ", params);
	}
	
		@SuppressWarnings("unchecked")
		public List<Object[]> findVotersForPanchayatAndElectionYearByStartAndMaxResults(Long hamletId, String year, 
				int startIndex, int maxResult, String order, String columnName)
		{
			Query queryObject = getSession().createQuery("select model.voter.firstName,model.voter.houseNo, model.voter.age, " +
					"model.voter.cast, model.voter.castCatagery, model.voter.castSubCatagery, model.voter.gender,model.voter.relativeName, " +
					"model.voter.relationshipType, model.voter.voterId from BoothConstituencyElectionVoter model where " +
					"model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
					" order by model.voter."+columnName+" "+order);
			queryObject.setLong(0, hamletId);
			queryObject.setString(1, year);
			queryObject.setFirstResult(startIndex);
			queryObject.setMaxResults(maxResult);
			return queryObject.list();
		}



	public List<Object[]> findVotersForBoothAndElectionYearByStartAndMaxResults(
				Long boothId, String year, int startIndex,int maxResult, String order, String columnName) 
				
			{
				Query queryObject = getSession().createQuery("select model.voter.name, model.voter.houseNo, model.voter.age, " +
						"model.voter.cast, model.voter.castCatagery, model.voter.castSubCatagery, model.voter.gender,model.voter.relativeName, " +
						" model.voter.relationshipType, model.voter.voterId from BoothConstituencyElectionVoter model where " +
						"model.voter.hamlet.hamletId in (select model1.hamlet.hamletId from HamletBoothElection model1 where model1.boothConstituencyElection.boothConstituencyElectionId in (select model2.boothConstituencyElectionId from BoothConstituencyElection model2  where model2.booth.boothId = ? )) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
						" order by model.voter."+columnName+" "+order);
						
				queryObject.setLong(0, boothId);
				queryObject.setString(1, year);
				//queryObject.setLong(1, partNo);
				queryObject.setFirstResult(startIndex);
				queryObject.setMaxResults(maxResult);
				return queryObject.list();
			}
		
		@SuppressWarnings("unchecked")
		public List findTotalVotersCountByPanchayatAndElectionYear(Long panchayatId, String year){
			Query queryObject = getSession().createQuery("select count(model.voter.voterId) from BoothConstituencyElectionVoter model " +
					"where model.voter.hamlet.hamletId in(select model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId = ? ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? ");
			queryObject.setLong(0, panchayatId);
			queryObject.setString(1, year);
			return queryObject.list();
		}

}
