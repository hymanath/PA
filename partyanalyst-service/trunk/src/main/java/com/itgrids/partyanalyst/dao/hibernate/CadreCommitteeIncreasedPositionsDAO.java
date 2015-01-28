package com.itgrids.partyanalyst.dao.hibernate;

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
	
	public List<Object[]> getAllRecordsCount(int stIndex, int endIndex){
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
				" model.status " + // 10 -- STATUS 
				" from CadreCommitteeIncreasedPositions model" +
				" where model.type ='positionsIncreased'");
		return query.list();
	}
}
