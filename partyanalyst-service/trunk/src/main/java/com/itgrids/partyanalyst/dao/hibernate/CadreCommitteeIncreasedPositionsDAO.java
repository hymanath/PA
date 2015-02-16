package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreCommitteeIncreasedPositionsDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;

public class CadreCommitteeIncreasedPositionsDAO extends GenericDaoHibernate<CadreCommitteeIncreasedPositions, Long>  implements ICadreCommitteeIncreasedPositionsDAO
{
	public CadreCommitteeIncreasedPositionsDAO() {
		super(CadreCommitteeIncreasedPositions.class);
	}
	
	public Long getAllRecordsCount(){
		Query query = getSession().createQuery(" select count(model.cadreCommitteeIncreasedPositionsId) from CadreCommitteeIncreasedPositions model" +
				" where model.type ='positionsIncreased'");
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getAllRecordsCountStatusWise(){
		Query query = getSession().createQuery(" select count(model.cadreCommitteeIncreasedPositionsId), model.status" +
				" from CadreCommitteeIncreasedPositions model" +
				" where model.type ='positionsIncreased'" +
				" group by model.status ");
		return query.list();
	}
	
	public List<Object[]> getAllRecordsForApproval(int stIndex, int endIndex){
		Query query = getSession().createQuery(" select model.cadreCommitteeIncreasedPositionsId," + // 0 -- REQUEST NO
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId," + // 1 -- LEVEL ID
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," + // 2 -- LEVEL NAME
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," + // 3 -- LEVEL VALUE
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +// 4 -- COMMITTEE TYPE ID
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name," + // 5 -- COMMITTEE TYPE NAME
				" model.tdpCommitteeRole.tdpRoles.tdpRolesId," + // 6 -- ROLE ID
				" model.tdpCommitteeRole.tdpRoles.role," + // 7 -- ROLE NAME
				" model.currentCount," + // 8 -- CURRENT COUNT
				" model.requestCount," + // 9 -- REQUESTED COUNT
				" model.status," + // 10 -- STATUS 
				" model.tdpCommitteeRole.tdpCommitteeRoleId, " + // 11 -- ROLE ID
				" model.cadreCommitteeIncreasedPositionsId " + // 12 --  
				" from CadreCommitteeIncreasedPositions model" +
				" where model.type ='positionsIncreased'" +
				" order by model.insertedTime desc ");
		
		query.setFirstResult(stIndex);
		query.setMaxResults(endIndex);
		return query.list();
	}
	
	public int updateStatus(String status,Date updatedTime, Long increasedPosId, Long approveCount){
		Query query = getSession().createQuery(" update CadreCommitteeIncreasedPositions model" +
				" set model.status=:status, model.approvedCount=:approveCount, model.updatedTime=:updatedTime" +
				" where model.cadreCommitteeIncreasedPositionsId =:increasedPosId ");
		
		query.setParameter("status", status);
		query.setParameter("increasedPosId", increasedPosId);
		query.setParameter("approveCount", approveCount);
		query.setDate("updatedTime", updatedTime);
		return query.executeUpdate();
	}
	
	public List<Object[]> getRequestDetailsForAUser(Long requestUserId){
		 Query query = getSession().createQuery(" select model.refNo," + // 0 -- ref NO
					" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId," + // 1 -- LEVEL ID
					" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," + // 2 -- LEVEL NAME
					" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," + // 3 -- LEVEL VALUE
					" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +// 4 -- COMMITTEE TYPE ID
					" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name," + // 5 -- COMMITTEE TYPE NAME
					" model.tdpCommitteeRole.tdpRoles.tdpRolesId," + // 6 -- ROLE ID
					" model.tdpCommitteeRole.tdpRoles.role," + // 7 -- ROLE NAME
					" model.currentCount," + // 8 -- CURRENT COUNT
					" model.requestCount," + // 9 -- REQUESTED COUNT
					" model.status," +// 10 -- STATUS 
					" date(model.insertedTime)  " + //11-date
					" from CadreCommitteeIncreasedPositions model" +
					" where model.type ='positionsIncreased' and " +
					" model.userIdRequest.userId=:requestUserId " +
					" order by model.insertedTime desc");
		    query.setParameter("requestUserId", requestUserId);
			return query.list(); 
		 
	 }
	public List<Object[]> statusForChangeDesignationsApproval(){
		Query query = getSession().createQuery(" select count(model.cadreCommitteeIncreasedPositionsId), model.status" +
				" from CadreCommitteeIncreasedPositions model" +
				" where model.type ='changeDesignations'" +
				" group by model.status ");
		return query.list();
	}
}
