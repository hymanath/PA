package com.itgrids.partyanalyst.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public interface IAlertRenderService {

	public JSONObject getJsonStructureOfAlertFromZoho(JSONArray jArry);
}
