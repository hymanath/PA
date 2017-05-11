package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCommitteeRole;

public interface ICadreCommitteeRoleDAO extends GenericDao<CadreCommitteeRole, Long>{

	
	public List<Long> getCadreCommitteRoleIdBySelection(Long cadreCommitteLevelId,Long cadreRoleId,Long cadreCommitteId);
	
	public List<Object[]> getCommitteeRolesByLevelId(Long cadreCommitteeLevelId);
	
	public List<Object[]> getCommitteeRolesByLevelIdAndCommitteeId(Long cadreCommitteeLevelId,Long cadreCommiteeId);
	public List<Object[]> getPartyCommitteeLeadersDeatails(Long stateId,List<Long> enrollmentIdsList,List<Long>  committeeLevelIdsList,List<Long> committeeTypeIdsLsit,
			 Long levelId, List<Long> locationIdsList,List<Long> designationIdsList,int firstIndex,int maxIndex);
}
