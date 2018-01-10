package com.itgrids.partyanalyst.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IAlertCreationAPIService {
	public void sendApiDetailsOfAlertToZoho(Long alertId,String contactId,String deptId);
	public JSONObject createAlertApi(final JSONObject jsonObject) throws JSONException;
}
