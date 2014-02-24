package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
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
	
	
}
