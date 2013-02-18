package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
}