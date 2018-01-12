package com.itgrids.partyanalyst.service;

import java.util.List;


public interface IAlertUpdationAPIService {
	public 	String  getMembershipNoForCadreIds(List<Long> cadreIds,Long alertId);
	public String sendAssignedCandidateCantactId(Long cadreId,Long alertId);
	public String sendAttachements(Long alertId);
	public String updateAlertComment(Long alertId);
}
