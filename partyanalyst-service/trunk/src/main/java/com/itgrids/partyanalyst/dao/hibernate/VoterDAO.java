package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Voter;

public class VoterDAO extends GenericDaoHibernate<Voter, Long> implements IVoterDAO{
	
	public VoterDAO(){
		super(Voter.class);
	}

	@SuppressWarnings("unchecked")
	public List<Voter> findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo(
			String firstName, String lastName, String relativeFirstName,
			String relativeLastName, String voterIDCardNo) {
		Object[] params = {firstName, lastName, relativeFirstName, relativeLastName, voterIDCardNo};
		return getHibernateTemplate().find("from Voter model where model.firstName = ?" +
				" and model.lastName = ? and model.relativeFirstName = ?" +
				"and model.relativeLastName = ? and model.voterIDCardNo = ?",params);
	}
	@SuppressWarnings("unchecked")
	public List findCastWiseVotersForMandal(Long mandalID){
		return getHibernateTemplate().find("select model.cast, count(model.cast) from Voter model " +
				"where model.hamlet.township.tehsil.tehsilId=? " +
				"group by model.cast order by model.cast",mandalID);
	}
	
	public List findGenderAgeWiseVotersForMandal(Long mandalID,Long minAge, Long maxAge){
		Object[] params = {mandalID,minAge, maxAge};
		return getHibernateTemplate().find("select model.gender, count(model.gender) from Voter model " +
				"where model.hamlet.township.tehsil.tehsilId=? and model.age>=? and " +
				" model.age<? group by model.gender",params);
	}
	
	public List getCastCatageory(){
		return getHibernateTemplate().find("select model.castCatagery  from Voter model ");
	}
	
	public List getCastSubatageroryDetails(String castCatageory){
		return getHibernateTemplate().find("select model.castSubCatagery  from Voter model ");
	}
	
	public List getsubCastCatageoryCastDetails(String castCatageory,String subCastCatageory){
		Object[] params = {castCatageory, subCastCatageory};
		return getHibernateTemplate().find("select model.cast from Voter model where model.castCatagery = ? and model.castSubCatagery = ? order by asc");
	}
	
	public List getCastDetails(String castCatageory){
		return getHibernateTemplate().find("select model.cast from Voter model where model.castCatagery = ? ",castCatageory);
	}
	
	public List<Object[]> getVotersInfoForPollingStationAndElectionYear(Long boothId,
			String year)
	{
		Object[] params = {boothId, year};
		return getHibernateTemplate().find("select model.houseNo,count(model.houseNo) from Voter model,HamletBoothElection model2 where" +
				" model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.boothId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.hamlet.hamletId,model.houseNo", params);
		}
	
			//Important family details
			public List findVotersInfoForPollingStationAndElectionYear(Long boothId,String year)
			{
				Object[] params = {boothId, year};
				
				return getHibernateTemplate().find("select model.name,model.houseNo,model.age,model.cast,model.castCatagery,model.castSubCatagery,model.gender,model.relativeName," +
						" model.relationshipType,model.voterId  from Voter model,HamletBoothElection model2 where  model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.boothId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 order by model.voterId", params);
				
				
			}
			
			//Impotant families age wise count For Polling station
			public Long getVotersAgeInfoForPollingstationAndElectionYear(Long panchayatId,
					String year,Long minAge,Long maxAge)
			{
			Object[] params = {panchayatId, year};
			Query query = getSession().createQuery("select count(model.voterId) from Voter model,HamletBoothElection model2 where  model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.boothId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 and model.age BETWEEN ? AND ?");
			
			query.setParameter(0,panchayatId);
			query.setParameter(1,"2009");
			query.setParameter(2,minAge);
			query.setParameter(3,maxAge);
			
			return (Long)query.uniqueResult();
		}
			
			//Impotant families Aboveage wise count For Polling station
			public Long getVotersAboveAgeInfoForPollingstationAndElectionYear(Long panchayatId,
					String year)
			{
			Object[] params = {panchayatId, year};
			Query query = getSession().createQuery("select count(model.voterId) from Voter model,HamletBoothElection model2 where  model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.boothId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 and model.age > 60");
			
			query.setParameter(0,panchayatId);
			query.setParameter(1,"2009");
			
			
			return (Long)query.uniqueResult();
				}
			
	
			
			// voters data for Polling station
			
			public List findTotalVotersCountByPollingStationAndElectionYear(Long boothId, String year){
				Query queryObject = getSession().createQuery("select count(model.voterId) from Voter model,HamletBoothElection model2 where model.hamlet.hamletId =model2.hamlet.hamletId and model2.boothConstituencyElection.booth.boothId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38");
				queryObject.setString(1, year);
				queryObject.setLong(0, boothId);
				return queryObject.list();
			}
			//caste info for Polling station
			
			
			public List findVotersCastInfoByPollingStationAndElectionYear(Long hamletId, String year){
				Object[] params = {hamletId, year};
				return getHibernateTemplate().find("select count(model.voterId),model.gender,model.cast from Voter model,HamletBoothElection model2 where model.hamlet.hamletId =model2.hamlet.hamletId and model2.boothConstituencyElection.booth.boothId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.cast order by model.cast", params);
				}
	
			//caste info for Constituency
			
			
			public List findVotersCastInfoByConstituencyAndElectionYear(Long hamletId, String year){
				Object[] params = {hamletId, year};
				return getHibernateTemplate().find("select count(model.voterId),model.gender,model.cast from Voter model,HamletBoothElection model2 where model.hamlet.hamletId =model2.hamlet.hamletId and model2.boothConstituencyElection.constituencyElection.constituency.constituencyId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.cast order by model.cast", params);
				}
	
	
			//caste info for Mandal
			
			
			public List findVotersCastInfoByMandalAndElectionYear(Long hamletId, String year){
				Object[] params = {hamletId, year};
				return getHibernateTemplate().find("select count(model.voterId),model.gender,model.cast from Voter model,HamletBoothElection model2 where model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.tehsil.tehsilId = ? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.cast order by model.cast", params);
				}	
			
			//caste info for Urban
			
			
			public List findVotersCastInfoByUrbanAndElectionYear(Long urbanId, String year){
				Object[] params = {urbanId, year};
				return getHibernateTemplate().find("select count(model.voterId),model.gender,model.cast from Voter model,HamletBoothElection model2 where model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.localBody.localElectionBodyId = ? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.cast order by model.cast", params);
				}
			
			//Basic info for Urban
			
			
			public List getVotersBasicInfoByUrbanId(Long urbanId, String year){
				Object[] params = {urbanId, year};
				return getHibernateTemplate().find("select count(model.voterId),model.gender from Voter model,HamletBoothElection model2 where model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.localBody.localElectionBodyId = ? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.gender", params);
				}	
			//Basic Info for ConstituencyId
			@SuppressWarnings("unchecked")
			public List<Object[]> getVotersBasicInfoByConstituencyId(Long constituencyId, String year)
			{
				Object[] params = {constituencyId, year};
				return getHibernateTemplate().find("select count(model.voterId), model.gender from Voter model,HamletBoothElection model2 where model.hamlet.hamletId =model2.hamlet.hamletId and model2.boothConstituencyElection.constituencyElection.constituency.constituencyId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.gender", params);
				
			}
			//Basic Info for ManadalId
			@SuppressWarnings("unchecked")
			public List<Object[]> getVotersBasicInfoByManadalId(Long manadalId, String year)
			{
				Object[] params = {manadalId, year};
				return getHibernateTemplate().find("select count(model.voterId), model.gender from  Voter model, HamletBoothElection model2 " +
						" where model.hamlet.hamletId = model2.hamlet.hamletId and model2.boothConstituencyElection.booth.tehsil.tehsilId = ? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.gender", params);
				
			}
			
			
			//Basic Info for PollingStationId
			@SuppressWarnings("unchecked")
			public List<Object[]> getVotersBasicInfoByPollingStationId(Long boothId, String year){
				Object[] params = {boothId, year};
				return getHibernateTemplate().find("select count(model.voterId), model.gender from Voter model,HamletBoothElection model2 where model.hamlet.hamletId =model2.hamlet.hamletId and model2.boothConstituencyElection.booth.boothId=? and  model2.boothConstituencyElection.constituencyElection.election.electionYear = ? and model2.boothConstituencyElection.constituencyElection.election.electionId=38 group by model.gender", params);
			}	

			
			@SuppressWarnings("unchecked")
			public List<Voter> getVoterPersonalDetailsByVoterId(Long voterId){
				
				Query queryObject = getSession().createQuery("from Voter model where model.voterId=?");
				queryObject.setParameter(0, voterId);
			
				return queryObject.list();
			}
	
			public void flushAndclearSession(){
				getSession().flush();
				getSession().clear();
			}
			
			public Integer updateCasteByVoterCardId(String voterCardId, String caste)
			{
				Query query = getSession().createQuery("update Voter model set model.cast = :caste where model.voterIDCardNo = :voterCardId");
				query.setParameter("voterCardId", voterCardId);
				query.setParameter("caste", caste);
				return query.executeUpdate();
			}
			
			public Voter getVoterByVoterIDCardNo(String voterID)
			{
				Query query = getSession().createQuery("select model from Voter model where model.voterIDCardNo = :voterID");
				query.setParameter("voterID", voterID);
				return (Voter)query.uniqueResult();
			}
			
			public Integer updateVoterNameAndRelativeName(String name, String relativeName,Long voterId)
			{
				Query query = getSession().createQuery(" update Voter model set model.name = :name, model.relativeName = :relativeName where model.voterId = :voterId");
				query.setParameter("name",name);
				query.setParameter("relativeName",relativeName);
				query.setParameter("voterId",voterId);
				return query.executeUpdate();
			}
			
			@SuppressWarnings("unchecked")
			public List<Object[]>getVoterIdsByVoterIdCardNos(List<String> voterIdCardNosList)
			{
				Query query = getSession().createQuery(" select model.voterId,model.voterIDCardNo from Voter model where model.voterIDCardNo in (:voterIdCardNosList)");
				query.setParameterList("voterIdCardNosList",voterIdCardNosList);
				return query.list();
			}

			/**
			 * This method is used for checking weather voteruserId is present are there in voter table
			 * @author Prasad Thiragabathina
			 * @param voterCardId
			 * @return List<Voter>
			 */
			@SuppressWarnings("unchecked")
			public List<Voter> getVoterByVoterCardNo(String voterCardId) {
				
				Query query = getSession().createQuery("select model from Voter model where model.voterIDCardNo = :voterCardId");
				query.setParameter("voterCardId", voterCardId);
				return query.list(); 
			}

			public Integer updateVoterMobileNo(String mobileNo,Long voterId)
			{
				Query query = getSession().createQuery("update Voter model set model.mobileNo = :mobileNo where model.voterId = :voterId");
				
				query.setParameter("mobileNo",mobileNo);
				query.setParameter("voterId",voterId);
				return query.executeUpdate();
			}
			
			@SuppressWarnings("unchecked")
			public List<Object[]> getVoterIdsByCardNos(List<String> voterIdCardNosList)
			{
				Query query = getSession().createQuery("select model.voterId,model.voterIDCardNo from Voter model where model.voterIDCardNo in (:voterIdCardNosList)");
				query.setParameterList("voterIdCardNosList",voterIdCardNosList);
				return query.list();
			}
			/**
			 * This method is user for get all the details of the voter based on voterId
			 * @author Prasad Thiragabathina
			 * @param Long voterId
			 * @return List<Voter>
			 */
			public List<Voter> findVoterDetailsBasedOnVoterId(Long voterId) {
				
				return getHibernateTemplate().find("select model from Voter model where model.voterId = ?",voterId);
			}

			
			/*@SuppressWarnings("unchecked")
			public List<Object> getTestVotersInfo(){
				return getHibernateTemplate().find("select userInfo.party.shortName,v.gender," +
				"count(model.voter.voterId),model2.party.partyId " +
				"from Voter as v left join v.uservoterdetails as userInfo" +
				" with userInfo.party.partyId > 1");
			}*/

			@SuppressWarnings("unchecked")
			public List<Object> getTestVotersInfo1(){
				return getHibernateTemplate().find("select userInfo.party.shortName,v.gender," +
				"count(v.voterId),userInfo.party.partyId " +
				"from Voter as v inner join v.boothPublicationVoters as BPV " +
				"inner join v.uservoterdetails as userInfo " +
				" where BPV.booth.publicationDate.publicationDateId = 7" +
				" and BPV.booth.constituency.constituencyId = 232" +
				"and userInfo.user.userId = 1 " +
				"group by userInfo.party.partyId,v.gender order by userInfo.party.shortName");
			}

			@SuppressWarnings("unchecked")
			public List<Object> getTestVotersInfo(){
			return getHibernateTemplate().find("select userInfo.party.shortName,userInfo.voter.gender," +
				"count(userInfo.voter.voterId),userInfo.party.partyId " +
				"from UserVoterDetails as userInfo where userInfo.voter.voterId in (select BPV.voter.voterId " +
				"from BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = 7" +
				" and BPV.booth.constituency.constituencyId = 232 ) and userInfo.user.userId = 1 " +
				"group by userInfo.party.partyId,userInfo.voter.gender order by userInfo.party.shortName");
			}

			@SuppressWarnings("unchecked")
			public List<Object[]> getSnoFromVoterTemp(Long constituencyId)
			{
				return getHibernateTemplate().find("select model.voterId, model2.serialNo from Voter model, VoterTemp model2 where model.voterIDCardNo = model2.voterId and model2.constituencyId = ?",constituencyId);
			}

			/**
			    * This Method Is Used to Store Missing Voters
			    * @param Set<String> voterCardIds
			    * @return  List<String>
			    * @date 13/03/2013
			    */
			public List<String> checkForVoterCardId(Set<String> voterCardIds) {
				String query = "select model.voterIDCardNo from Voter model where model.voterIDCardNo in (:voterIds)";
				Query queryString = getSession().createQuery(query);
				queryString.setParameterList("voterIds", voterCardIds);
				return queryString.list();
			}
	
			/** This Method is used to get voterInfo based on voterIds for AdminEdit */
			public List<Voter> getVoterInfoByVoterId(List<Long> voterIds){
				String queryString = "select model from Voter model " +
						"where model.voterId in (:voterIds)";
				Query query = getSession().createQuery(queryString);

				query.setParameterList("voterIds", voterIds);
				return query.list();
			}

			public Voter getVoterByVoterID(Long voterID)
			{
				Query query = getSession().createQuery("select model from Voter model where model.voterId = :voterID");
				query.setParameter("voterID", voterID);
				return (Voter)query.uniqueResult();
			}
			
			public List<Object[]> getVoterIdsByVoterIDCardNumbers(List<String> voterIDCardsNumbers)
			{
				Query query = getSession().createQuery("select model.voterIDCardNo , model.voterId from Voter model " +
						"where model.voterIDCardNo in(:voterIDCardsNumbers)");
				
				query.setParameterList("voterIDCardsNumbers", voterIDCardsNumbers);
				
				return query.list();
				
			}
			
			@SuppressWarnings("unchecked")
			public List<Long> getVoterIdByVoterIDCardNumber(String voterID)
			{
				Query query = getSession().createQuery("select model.voterId from Voter model " +
						"where model.voterIDCardNo =:voterID");
				
				query.setParameter("voterID", voterID);
				
				return query.list();
				
			}
			
			
			@SuppressWarnings("unchecked")
			public List<Object[]> getVoterNames(Integer startIndex,Integer maxIndex)
			{
				Query query = getSession().createQuery("select model.voterId,model.name,model.relativeName from Voter model ");
				
				query.setFirstResult(startIndex);
				query.setMaxResults(maxIndex);
							
				return query.list();	
			}
			public Long getVoterCount()
			{
				Query query = getSession().createQuery("select count(model.voterId) from Voter model");
				
			   return (Long) query.uniqueResult();	
			}
			
			public Integer updateVoter(Long voterId,String name,String relativeName)
			{
				Query query = getSession().createQuery("update Voter model set model.name =:name,model.relativeName = :relativeName where model.voterId = :voterId");
				query.setParameter("voterId", voterId);
				query.setParameter("name", name);
				query.setParameter("relativeName", relativeName);
				return query.executeUpdate();
			}
			
			@SuppressWarnings("unchecked")
			public List<Object[]> getVoterIdCardNo(List<String> idCardnos)
			{
			   Query query = getSession().createQuery("select model.voterIDCardNo,model.voterId from Voter model where model.voterIDCardNo in( :idCardnos)");
			   query.setParameterList("idCardnos", idCardnos);
			   return query.list();
			}
         
			public List<Long> getVoterId(String voterCardId){
				Query query = getSession().createQuery("select model.voterId from Voter model where model.voterIDCardNo =:voterCardId ");
				query.setParameter("voterCardId", voterCardId);
				 return query.list();
			}
			
			public Long getVoterIdByIdCardNo(String voterCardId){
				Query query = getSession().createQuery("select model.voterId from Voter model where model.voterIDCardNo =:voterCardId ");
				query.setParameter("voterCardId", voterCardId);
				 return (Long) query.uniqueResult();
			}
			
			@SuppressWarnings("unchecked")
			public List<Object[]> getVoterInfoByVoterId(Long voterId)
			{
			   Query query = getSession().createQuery("select distinct model.voterIDCardNo,model.voterId from Voter model where model.voterId = :voterId " +
			   		" order by model.voterId desc ");
			   query.setParameter("voterId", voterId);
			   
			   return query.list();
			}

			@SuppressWarnings("unchecked")
			public List<Object[]> getVotersInfoByVoterId(List<Long> voterIds)
			{
			   Query query = getSession().createQuery("select distinct model.voterId,model.voterIDCardNo from Voter model where model.voterId in (:voterIds) " +
			   		" order by model.voterId desc ");
			   query.setParameterList("voterIds", voterIds);
			   
			   return query.list();
			}
			
			@SuppressWarnings("unchecked")
			public List<Object[]> getVoterCardNoByVoterId(Long voterId)
			{
			   Query query = getSession().createQuery("select  model.voterId,model.voterIDCardNo from Voter model where model.voterId = :voterId " +
			   		" order by model.voterId desc ");
			   query.setParameter("voterId", voterId);
			   
			   return query.list();
			}
			
			public String getVoterImagePathByVoterId(Long voterId)
			{
				Query query = getSession().createQuery("SELECT model.imagePath FROM Voter model where model.voterId = :voterId");
				query.setParameter("voterId",voterId);
				
				Object str = query.uniqueResult();
				
				if(str != null )
					return str.toString();
				else
					return "";
			}
			
			public Long getVoterIdByIdCardNoNew(String voterCardId){
				Query query = getSession().createQuery("select model.voterId from Voter model where model.voterIDCardNo =:voterCardId order by model.voterId desc ");
				query.setParameter("voterCardId", voterCardId);
				
				query.setMaxResults(1);
				 return (Long)query.uniqueResult();
			}
			
			
			
}