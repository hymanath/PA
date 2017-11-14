package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.JbCommitteeRole;

   public interface IJbCommitteeRoleDAO extends GenericDao<JbCommitteeRole, Long> {
	   public List<Object[]> getDesignationsIdsByCommitteeId(Long committeeId);
	   public  List<Object[]> getCommitteeWiseTotalMemberCount(String type);
}
