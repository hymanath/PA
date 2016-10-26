package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreDataVerification;

public interface ITdpCadreDataVerificationDAO extends GenericDao<TdpCadreDataVerification, Long>{
	public Long getActiveTeamMemberCnt();
}
