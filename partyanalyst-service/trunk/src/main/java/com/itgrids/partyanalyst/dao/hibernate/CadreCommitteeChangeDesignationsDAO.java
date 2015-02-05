package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreCommitteeChangeDesignationsDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeChangeDesignations;
import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;

public class CadreCommitteeChangeDesignationsDAO extends GenericDaoHibernate<CadreCommitteeChangeDesignations, Long>  implements ICadreCommitteeChangeDesignationsDAO
{
	public CadreCommitteeChangeDesignationsDAO() {
		super(CadreCommitteeChangeDesignations.class);
	}
	
	public List<Object[]> changeDesignationRecordsForAUser(Long requestUserId){
		
	
		Query query=getSession().createQuery("select model.cadreCommitteeIncreasedPositions.cadreCommitteeIncreasedPositionsId, " + //0-incPositionsId
				"date(model.cadreCommitteeIncreasedPositions.insertedTime)," +//1 date
				"model.tdpCommitteeMember.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId," +//2  LEVEL ID
				"model.tdpCommitteeMember.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," +//3 LEVEL NAME
				"model.tdpCommitteeMember.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," + // 4 -- LEVEL VALUE
				"model.tdpCommitteeMember.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +// 5 -- COMMITTEE TYPE ID
				"model.tdpCommitteeMember.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name," +//6 COMMITTEE TYPE NAME
				"model.tdpCommitteeMember.tdpCadre.firstname," +//7 cadrename
				"model.tdpCommitteeMember.tdpCadre.image," +//8 image 
				"model.tdpCommitteeMember.tdpCadre.memberShipNo," +//9 memberShipNo
				"model.currentRole.tdpCommitteeRoleId," +//10 current role id
				"model.currentRole.tdpRoles.role," +//11 current role
				"model.newRole.tdpCommitteeRoleId," +////12 new role id
				"model.newRole.tdpRoles.role," +//13 new role
				"model.cadreCommitteeIncreasedPositions.status," +//14 status
				"model.cadreCommitteeIncreasedPositions.refNo" +//15 refNo
				" from CadreCommitteeChangeDesignations model" +
				" where model.cadreCommitteeIncreasedPositions.userIdRequest.userId=:requestUserId and " +
				"       date(model.cadreCommitteeIncreasedPositions.insertedTime) is not null and " +
				"       model.cadreCommitteeIncreasedPositions.type ='changeDesignations' and " +
				"       model.tdpCommitteeMember.isActive='Y' " +
				"order by model.cadreCommitteeIncreasedPositions.insertedTime desc");
		
		query.setParameter("requestUserId", requestUserId);
		
		return query.list();
	}
}
