package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommitteeMember;

public interface ICommitteeMemberDAO extends GenericDao<CommitteeMember, Long>{
	public List<Object[]> getCommitteeCadreInfo(Long committeeId,Long commiteLevelId);
}