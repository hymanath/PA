package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;

public interface IAlertService {
	public List<BasicVO> getCandidatesByName(String candidateName);
	public String createAlert(final AlertVO inputVO,final Long userId);
	public List<BasicVO> getLocationLevelWiseAlerts(Long userId,String FromDate,String toDate);
	public List<AlertDataVO> getLocationLevelWiseAlertsData(Long userId,AlertInputVO inputVO);
	public String updateAlertStatus(final Long userId,final Long alertId,final Long alertStatusId,final String comments);
	public List<StatusTrackingVO> getAlertStatusCommentsTrackingDetails(Long alertId);
	public List<BasicVO> getAlertType();
	public List<BasicVO> getAlertSourceForUser(Long userId);
}
