package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.model.HouseHoldVoter;
import com.itgrids.partyanalyst.model.HouseHoldsFamilyDetails;
import com.itgrids.partyanalyst.utils.IConstants;


public class HouseHoldVoterDAO extends GenericDaoHibernate<HouseHoldVoter,Long> implements IHouseHoldVoterDAO {
	
	public HouseHoldVoterDAO() {
		super(HouseHoldVoter.class);
	}
	
	public List<Object[]> getVoterRelationsByVoterIds(List<Long> voterIds){
		Query qry=getSession().createQuery(" select model.voterId,model.voterFamilyRelationId from HouseHoldVoter model" +
				" where model.voterId in (:voterIds)");
		qry.setParameterList("voterIds", voterIds);
		return qry.list();
	}
	
	public List<Object[]> getHouseHoldIdOfFamilyHeadForVoter(String houseNo,Long panchayatId,Long localBodyId){
		/*Query qry=getSession().createQuery(" select model.voterId,model.voterFamilyRelationId,model.houseHoldId from HouseHoldVoter model" +
				" where model.houseHolds.houseNo=:houseNo ");
		qry.setParameter("houseNo", houseNo);
		
		return qry.list();*/
		
		StringBuffer sb=new StringBuffer();
		sb.append(" select model.voterId,model.voterFamilyRelationId,model.houseHoldId from HouseHoldVoter model ");
		if(panchayatId!=null){
			sb.append(" where model.houseHolds.panchayat.panchayatId =:panchayatId");
		}else if(localBodyId!=null){
			sb.append(" where model.houseHolds.localElectionBody.localElectionBodyId =:localBodyId");
		}
		
		sb.append(" and model.houseHolds.houseNo =:houseNo and model.isDelete=:deleteStatus");
		
		String query=sb.toString();
		
		Query qry=getSession().createQuery(query);
		
		if(panchayatId!=null){
			qry.setParameter("panchayatId", panchayatId);
		}
		if(localBodyId!=null){
			qry.setParameter("localBodyId", localBodyId);
		}
			qry.setParameter("houseNo", houseNo);
			qry.setParameter("deleteStatus", IConstants.FALSE);
		
		return qry.list();
	}
	
	public List<Object[]> getHouseHoldIdOfVoter(String houseNo){
		Query qry=getSession().createQuery(" select model.houseHoldId from HouseHoldVoter model" +
				" where model.houseHolds.houseNo=:houseNo ");
		qry.setParameter("houseNo", houseNo);
		
		return qry.list();
	}
	
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId(Long houseHoldsId)
	{
		Query query = getSession().createQuery("select HHV from HouseHoldVoter " +
				"HHV where HHV.houseHoldId = :houseHoldsId and HHV.isDelete = :deleteStatus " +
				"and HHV.voterId is not null");	
		
		query.setParameter("houseHoldsId", houseHoldsId);
		query.setParameter("deleteStatus", IConstants.FALSE);
		return query.list();
		
	}
	
	public List<HouseHoldVoter> getHouseHoldsVoterdDetailsByHouseHoldId1(Long houseHoldsId)
	{
		Query query = getSession().createQuery("select HHV from HouseHoldVoter " +
				"HHV where HHV.houseHoldId = :houseHoldsId and HHV.isDelete = :deleteStatus ");	
		
		query.setParameter("houseHoldsId", houseHoldsId);
		query.setParameter("deleteStatus", IConstants.FALSE);
		return query.list();
		
	}
	
	
	
	public Long getHouseHoldIdForVoter(Long voterId){
		Query query = getSession().createQuery(" select model.houseHoldId from HouseHoldVoter model " +
				" where model.voter.voterId = :voterId and model.isDelete=:deleteStatus");
		
		query.setParameter("voterId", voterId);
		query.setParameter("deleteStatus", IConstants.FALSE);
		return (Long) query.uniqueResult();
	}
	
	
	public List<HouseHoldsFamilyDetails> getFamilyMembersDetailsByHouseHoldsId(Long houseHoldsId){
		
		Query query = getSession().createQuery(" select model.houseHoldsFamilyDetails from HouseHoldVoter model " +
				" where model.houseHoldId = :houseHoldsId");
		
		query.setParameter("houseHoldsId", houseHoldsId);
		return query.list();
	}
	
	public List<HouseHoldVoter> getHouseHoldVoterDetailsByFamilyMemberId(Long houseHoldFamilyMemberId)
	{
		
		Query query = getSession().createQuery(" select model " +
				"from HouseHoldVoter model " +
				" where model.houseHoldsFamilyDetails.houseHoldsFamilyDetailsId = :houseHoldFamilyMemberId");
		
		query.setParameter("houseHoldFamilyMemberId", houseHoldFamilyMemberId);
		return query.list();
	}
	
	public List<Object[]> getOwnerMobileAndLeaderIdForVoterId(Long voterId){
		Query query = getSession().createQuery(" select model.leaderId,model.ownerMobileNo from HouseHoldVoter model " +
				" where model.voter.voterId = :voterId and model.isDelete=:deleteStatus");
		
		query.setParameter("voterId", voterId);
		query.setParameter("deleteStatus", IConstants.FALSE);
		
		return query.list();
	}
	
	
	public List<HouseHoldVoter> checkExistanceOfVoterInHouseHoldsVoter(Long voterId)
	{
		Query query = getSession().createQuery("select HHV from HouseHoldVoter HHV " +
				" where HHV.voterId = :voterId and HHV.isDelete = :deletedStatus");
		
		query.setParameter("voterId", voterId);
		query.setParameter("deletedStatus", IConstants.FALSE);
		
		return query.list();
		
	}
	
	public int childMembersDelete(Long voterFamilyId){
		Query qry = getSession().createQuery(" update HouseHoldVoter model set model.isDelete =:deleteStatus where model.houseHoldsFamilyDetailsId =:voterFamilyId");
		qry.setParameter("voterFamilyId", voterFamilyId);
		qry.setParameter("deleteStatus", IConstants.TRUE);
		
		return qry.executeUpdate();
	}
	
	public int updateStatusIfVoterIdExist(Long voterId){
		Query qry = getSession().createQuery(" update HouseHoldVoter model set model.isDelete =:deleteStatus where model.voterId =:voterId");
		qry.setParameter("voterId", voterId);
		qry.setParameter("deleteStatus", IConstants.TRUE);
		
		return qry.executeUpdate();
	}
	
	public List<Long> getVoterIdsExistByVoterIds(List<Long> voterIds){
		Query qry = getSession().createQuery(" select model.voterId from HouseHoldVoter model" +
				"  where model.voterId in (:voterIds) and model.isDelete =:deleteStatus");
		qry.setParameter("deleteStatus", IConstants.FALSE);
		qry.setParameterList("voterIds", voterIds);
		
		return qry.list();
	}
	
	
	public List<HouseHoldVoter> getTotalVoters(){
		Query query=getSession().createQuery(" select model " +
				"from HouseHoldVoter model where model.isDelete='false' order by model.houseHolds.houseHoldId,model.voterFamilyRelation.voterFamilyRelationId");
		return query.list();
	}
	
	public List<HHSurveyAnswers> getAllSurveyAnswers(List<Long> houseHoldIds){
		Query query=getSession().createQuery(" select model from HHSurveyAnswers model where model.houseHolds.houseHoldId in (:houseHoldIds)" +
				" group by model.hhSurveyAnswerId ");
		query.setParameterList("houseHoldIds", houseHoldIds);
		return query.list();
	}
	
	public List<Object[]> getAllQuestions(){
		Query query=getSession().createQuery(" select model.surveyQuestionId,model.question from HHSurveyQuestion model where " +
				" model.isDeleted='false' ");
		return query.list();
	}
	
	public List<Long> getHouseHoldsOfLeader(Long leaderId){
		Query query = getSession().createQuery(" select model.voter.voterId from HouseHoldVoter model,HHBoothLeader model2" +
				" where model.leaderId =  model2.hhLeader.id" +
				" and model.isDelete =:deleteStatus and model.leaderId =:leaderId ");
		
		query.setParameter("deleteStatus", IConstants.FALSE);
		query.setParameter("leaderId", leaderId);
		
		return query.list();
	}
	
	public List<Long> getBooksOfHouseHoldsOfLeader(Long leaderId,Long constituencyId){
		Query query = getSession().createQuery(" select distinct model.hhLeaderBooks.hhLeaderBookId from HouseHoldVoter model,HHBoothLeader model2" +
				" where model.leaderId =  model2.hhLeader.id " +
				" and model2.constituency.constituencyId =:constituencyId " +
				" and model.isDelete =:deleteStatus " +
				" and model.leaderId =:leaderId ");
		
		query.setParameter("deleteStatus", IConstants.FALSE);
		query.setParameter("leaderId", leaderId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public Long getBookIdOfVoter(Long voterId){
		Query query = getSession().createQuery(" select model.hhLeaderBooks.hhLeaderBookId from HouseHoldVoter model" +
				" where model.voter.voterId = :voterId and model.isDelete =:deleteStatus");
		query.setParameter("voterId", voterId);
		query.setParameter("deleteStatus", IConstants.FALSE);
		return (Long) query.uniqueResult();
	}
	
	// CONSTITUENCY WISE PANCHAYAT,TOTAL FAMILIES,LEADERS COUNT
	public List<Object[]> getAllLeadersBooksFamilies(Long constituencyId){
		Query query = getSession().createQuery(" select model.houseHolds.panchayat.panchayatId," +//PANCHAYAT ID -- 1
				" model.houseHolds.panchayat.panchayatName," +// 2 -- PANCHAYAT NAME
				" count(distinct model.houseHolds.houseHoldId)," + // 3 -- HOUSEHOLDS COUNT
				" count(distinct model.hhLeader.id)" +// 4 -- LEADERS COUNT
				" from HouseHoldVoter model,HHBoothLeader model1 where " +
				" model.hhLeader.id = model1.hhLeader.id and " +
				" model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'" +
				" and model1.constituency.constituencyId = :constituencyId" +
				" and model.voter.voterId is not null" +
				" group by model.houseHolds.panchayat.panchayatId ");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	

	public List<Object[]> getAllPanchayatsInHouseHoldsOfConstituency(Long constituencyId){
		Query query = getSession().createQuery(" select distinct model.houseHolds.panchayat.panchayatId," +//PANCHAYAT ID -- 1
				" model.houseHolds.panchayat.panchayatName " +// 2 -- PANCHAYAT NAME
				" from HouseHoldVoter model,HHBoothLeader model1 where " +
				" model.hhLeader.id = model1.hhLeader.id and " +
				" model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'" +
				" and model1.constituency.constituencyId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	
	
	public List<Object[]> getFamilyHeadsInPanchayat(Long panchayatId){
		Query query = getSession().createQuery(" select distinct model.houseHoldVoterId," +
				" model.houseHolds.houseHoldId," +
				" model.houseHolds.houseNo," +
				" model.voter.name," +
				" model.voter.voterIDCardNo," +
				" model.houseHolds.panchayat.panchayatId," +
				" model.houseHolds.panchayat.panchayatName " +
				" from HouseHoldVoter model " +
				" where model.isDelete = 'false' and model.voterFamilyRelation.voterFamilyRelationId = 1" +
				" and model.houseHolds.panchayat.panchayatId = :panchayatId" );
		
		query.setParameter("panchayatId", panchayatId);
		return query.list();
	}
	
	public List<Object[]> getLeadersAndCountInLocality(Long locationId){
		Query query = getSession().createQuery(" select model.houseHolds.panchayat.panchayatId," +
				" model.houseHolds.panchayat.panchayatName," +
				" count(distinct model.houseHolds.houseHoldId)," +
				" model.hhLeader.id," +
				" model.hhLeader.name ," +
				" model.hhLeader.voterId," +
				" model.hhLeader.mobileNo" +
				" from HouseHoldVoter model where " +
				" model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'" +
				" and model.houseHolds.panchayat.panchayatId = :locationId " +
				" group by model.hhLeader.id");
		
		query.setParameter("locationId", locationId);
		
		return query.list();
	}
	
	// TYPE PARAMETER LOCATION -1 , LEADER -2 , BOOK -3
	public List<Object[]> getFamilyAndVotersCountInHouseHolds(Long val,int type){
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select model.houseHolds.houseHoldId,count(model.voter.voterId)," +
				" count(model.houseHoldsFamilyDetails.houseHoldsFamilyDetailsId) " +
				" from HouseHoldVoter model where " +
				" model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'");
		
		if(type ==1){
			sb.append("and model.houseHolds.panchayat.panchayatId = :val");
		}else if(type == 2){
			sb.append(" and model.hhLeader.id =:val");
		}else if(type == 3){
			sb.append(" and model.hhLeaderBooks.hhLeaderBookId =:val");
		}
		sb.append(" group by model.houseHolds.houseHoldId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("val", val);
		return query.list();
	}
	
	public List<Object[]> getFamilyHeadsUnderLeader(Long leaderId){
		Query query = getSession().createQuery(" select distinct model.houseHoldVoterId," +
				" model.houseHolds.houseHoldId," +
				" model.houseHolds.houseNo," +
				" model.voter.name," +
				" model.voter.voterIDCardNo," +
				" model.houseHolds.panchayat.panchayatId," +
				" model.houseHolds.panchayat.panchayatName," +
				" model.hhLeader.id," +
				" model.hhLeader.name " +
				" from HouseHoldVoter model " +
				" where model.isDelete = 'false' and model.voterFamilyRelation.voterFamilyRelationId = 1" +
				" and model.hhLeader.id = :leaderId" );
		
		query.setParameter("leaderId", leaderId);
		return query.list();
	}
	
	public List<Object[]> getFamilyHeadsUnderBook(Long bookId){
		Query query = getSession().createQuery(" select distinct model.houseHoldVoterId," +
				" model.houseHolds.houseHoldId," +
				" model.houseHolds.houseNo," +
				" model.voter.name," +
				" model.voter.voterIDCardNo," +
				" model.houseHolds.panchayat.panchayatId," +
				" model.houseHolds.panchayat.panchayatName " +
				" from HouseHoldVoter model " +
				" where model.isDelete = 'false' and model.voterFamilyRelation.voterFamilyRelationId = 1" +
				" and model.hhLeaderBooks.hhLeaderBookId = :bookId" );
		
		query.setParameter("bookId", bookId);
		return query.list();
	}
	public List<Object[]> getVoterAndNonVoterCountInConstituency(Long constituencyId){
		Query query = getSession().createQuery(" select model1.constituency.constituencyId,model1.constituency.name," +
				" count(model.voter.voterId)," +
				" count(model.houseHoldsFamilyDetails.houseHoldsFamilyDetailsId) " +
				" from HouseHoldVoter model,HHBoothLeader model1 where " +
				" model.hhLeader.id = model1.hhLeader.id" +
				" and model1.constituency.constituencyId = :constituencyId" +
				" and model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getConstituenciesOfHouseHolds(){
		Query query = getSession().createQuery(" select distinct model1.constituency.constituencyId,model1.constituency.name " +
				" from HouseHoldVoter model,HHBoothLeader model1 where " +
				" model.hhLeader.id = model1.hhLeader.id" +
				" and model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'");
		
		return query.list();
	}
	
	public List<Object[]> getHouseHoldsCountInConstituency(Long constituencyId){
		Query query = getSession().createQuery(" select model1.constituency.constituencyId," +
				" model1.constituency.name," +
				" count(distinct model.houseHolds.houseHoldId) " +
				" from HouseHoldVoter model,HHBoothLeader model1 where " +
				" model.hhLeader.id = model1.hhLeader.id and " +
				" model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'" +
				" and model1.constituency.constituencyId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getActiveLeadersOfConstituency(Long constituencyId){
		
		Query query = getSession().createQuery(" select distinct model.hhLeader.id,model.hhLeader.name,model.hhLeader.voterId " +
				" from HouseHoldVoter model,HHBoothLeader model1 where " +
				" model.hhLeader.id = model1.hhLeader.id and " +
				" model.isDelete = 'false'" +
				" and model.hhLeader.is_active = 'YES'" +
				" and model1.constituency.constituencyId = :constituencyId" +
				" and model.hhLeader.voterId is not null ");
		
		query.setParameter("constituencyId", constituencyId);
		
		
		/*Query qry=getSession().createQuery(" select distinct model.hhLeader.id,model.hhLeader.name,model.hhLeader.voterId from HHBoothLeader model " +
				" where model.constituency.constituencyId =:constituencyId " +
				" and model.hhLeader.is_active = 'YES'" +
				"  order by model.hhLeader.voterId asc ");
		
		query.setParameter("constituencyId", constituencyId);*/
		return query.list();
	}
	
	
}
