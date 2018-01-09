package com.itgrids.partyanalyst.service;

public interface IAlertCreationAPIService {
	public void sendApiDetailsOfAlertToZoho(Long alertId,String contactId,String deptId);
}
