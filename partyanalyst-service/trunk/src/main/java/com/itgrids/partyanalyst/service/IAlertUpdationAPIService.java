package com.itgrids.partyanalyst.service;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.JsonObject;

public interface IAlertUpdationAPIService {
	public 	String  getMembershipNoForCadreIds(List<Long> cadreIds,Long alertId);
	public String sendAssignedCandidateCantactId(List<Long> cadreIds,Long alertId);
	public String updateAlertStatus(Long alertId);
	public String sendAlertUpdationDetails(Long alertId,List<String> fileNames,String comment);
	public JSONObject saveZohoAlertComment(JSONObject jobj); 
	public JSONObject saveAlertAssign(JSONObject jobj); 
	public JSONObject saveAlertStatus(JSONObject jobj);
	public String callForZohoContact(List<Long> cadreIds);
}
