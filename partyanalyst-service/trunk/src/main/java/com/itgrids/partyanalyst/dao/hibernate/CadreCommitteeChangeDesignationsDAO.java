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
		
		/*select ccip.cadre_committee_increased_positions_id,date(ccip.inserted_time) as date,
	       tcl.tdp_committee_level_id,tcl.tdp_committee_level ,tc.tdp_committee_level_value,
	       tbc.tdp_basic_committee_id, tbc.name,
	       tcad.first_name,tcad.image,tcad.membership_id,
	       cccd.current_role,cccd.new_role,ccip.status
	from 
	     cadre_committee_increased_positions ccip,
	     cadre_committee_change_designations cccd,
	     tdp_committee_member tcm,
	     tdp_cadre tcad,
	     tdp_committee_role tcr,
	     tdp_committee tc,
	     tdp_basic_committee tbc,
	     tdp_committee_level tcl,
	     user u
	    
	where
	      ccip.cadre_committee_increased_positions_id=cccd.cadre_committee_increased_positions_id and
	      cccd.tdp_committee_member_id=tcm.tdp_committee_member_id and
	      tcm.tdp_cadre_id=tcad.tdp_cadre_id and
	      tcm.tdp_committee_role_id=tcr.tdp_committee_role_id and
	      tcr.tdp_committee_id=tc.tdp_committee_id and 
	      tc.tdp_basic_committee_id=tbc.tdp_basic_committee_id and
	      tc.tdp_committee_level_id=tcl.tdp_committee_level_id and
	      ccip.user_id_request=u.user_id and 
	     
	      date(ccip.inserted_time) is not null and
	      u.user_id=5556

	order by date(inserted_time) desc;*/
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
				"       model.cadreCommitteeIncreasedPositions.type ='changeDesignations' " +
				"order by model.cadreCommitteeIncreasedPositions.insertedTime desc");
		
		query.setParameter("requestUserId", requestUserId);
		
		return query.list();
	}
}
