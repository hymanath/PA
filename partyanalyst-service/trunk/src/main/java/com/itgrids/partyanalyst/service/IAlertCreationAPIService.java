package com.itgrids.partyanalyst.service;

import java.io.File;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface IAlertCreationAPIService {
	public void sendApiDetailsOfAlertToZoho(Long alertId,String contactId,String deptId);
	public JSONObject createAlertApi(final JSONObject jsonObject,final String apiType) throws JSONException;
	public String uploadMultiPartFile(String URL,File uploadFile);
}
