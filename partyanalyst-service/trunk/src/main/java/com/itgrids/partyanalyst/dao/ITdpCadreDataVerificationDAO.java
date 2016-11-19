package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreDataVerification;

public interface ITdpCadreDataVerificationDAO extends GenericDao<TdpCadreDataVerification, Long>{
	public Long getActiveTeamMemberCnt(Long stateId);
	public Long getTotalRegistered(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate);
	public Long getVerifiedPassedCount(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate);
	public Long getVerifiedRejectedCount(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId,Date fromDate,Date toDate);
}
