package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertVerificationUserTypeUser;

public interface IAlertVerificationUserTypeUserDAO extends GenericDao<AlertVerificationUserTypeUser, Long> {
	 public Long getAlertVerificationUserTypeId(Long userId);
	 public List<Object[]> getAlertVerificationUserByUserType(Long verificationUserTypeId);
}
