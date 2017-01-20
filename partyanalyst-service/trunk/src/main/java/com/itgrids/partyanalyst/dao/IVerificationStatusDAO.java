package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.model.VerificationStatus;

public interface IVerificationStatusDAO extends GenericDao<VerificationStatus, Long> {
	public List<Object[]> getStatusWiseAlertCount(Long stateId,Date fromDate,Date toDate,Long alertTypeId);
	public List<Object[]> getAllAlerts(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate);
}
