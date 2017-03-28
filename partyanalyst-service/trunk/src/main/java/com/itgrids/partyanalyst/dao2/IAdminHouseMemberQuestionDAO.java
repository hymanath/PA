package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AdminHouseMemberQuestion;

public interface IAdminHouseMemberQuestionDAO extends GenericDao<AdminHouseMemberQuestion, Long>{
	public List<Object[]> getLeaderWiseQuestionsInfo(List<Long> questionIdsList);
}
