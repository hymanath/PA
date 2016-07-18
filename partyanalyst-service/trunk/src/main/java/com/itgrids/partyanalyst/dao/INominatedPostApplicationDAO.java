package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostApplication;

public interface INominatedPostApplicationDAO extends GenericDao<NominatedPostApplication, Long>{
	public List<Object[]> getNominatedPostsAppliedApplciationsDtals(Long levelId,Date startDate,Date endDate);
	public List<Object[]> getPendingApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate);
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type);
	public List<Object[]> getNominatedPostsRunningAppliedApplicationsDtals(Long levelId,Date startDate,Date endDate);
	public List<Object[]> getCandidateAppliedPostsByCadre(Long tdpCadreId);
}
