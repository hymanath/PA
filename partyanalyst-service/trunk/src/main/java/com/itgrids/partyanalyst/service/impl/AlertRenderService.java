package com.itgrids.partyanalyst.service.impl;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.service.IAlertCreationAPIService;
import com.itgrids.partyanalyst.service.IAlertRenderService;
import com.itgrids.partyanalyst.service.IAlertUpdationAPIService;

public class AlertRenderService implements IAlertRenderService{
	
	private IAlertCreationAPIService alertCreationAPIService;
	private IAlertUpdationAPIService alertUpdationAPIService;
	
	
	public void setAlertUpdationAPIService(IAlertUpdationAPIService alertUpdationAPIService) {
		this.alertUpdationAPIService = alertUpdationAPIService;
	}

	public void setAlertCreationAPIService(IAlertCreationAPIService alertCreationAPIService) {
		this.alertCreationAPIService = alertCreationAPIService;
	}

	public JSONObject getJsonStructureOfAlertFromZoho(JSONArray jsonArr){
		JSONObject finalJson = new JSONObject();
		try {			
			if(jsonArr !=null && jsonArr.length()>0){
				for (int i = 0; i < jsonArr.length(); i++) { 
					JSONObject json = (JSONObject)jsonArr.get(i);
					if(json !=null){
						if(json.has("payload") && json.has("eventType")){
							String eventType = json.getString("eventType").trim();
							if(eventType.equalsIgnoreCase("Tickets_Add")){
								finalJson = getRenderedAlertObject(json.getJSONObject("payload"));//alert creation
								if(json.getJSONObject("payload").has("contactId")){
									alertUpdationAPIService.saveAlertAssign(json.getJSONObject("payload"));
								}	
								if(json.getJSONObject("payload").has("status")){
									alertUpdationAPIService.saveAlertStatus(json.getJSONObject("payload"));
								}
							}else if(eventType.equalsIgnoreCase("Ticket_Update")){
								if(json.getJSONObject("payload").has("contactId")){
									alertUpdationAPIService.saveAlertAssign(json.getJSONObject("payload"));
								}	
								if(json.getJSONObject("payload").has("status")){
									alertUpdationAPIService.saveAlertStatus(json.getJSONObject("payload"));
								}
							}
							if(eventType.equalsIgnoreCase("Ticket_Comment_Add") || eventType.equalsIgnoreCase("Ticket_Comment_Update")){
								finalJson = alertUpdationAPIService.saveZohoAlertComment(json.getJSONObject("payload"));
							}
						}
					}
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalJson;
	}
	
	public JSONObject getRenderedAlertObject(JSONObject createJson){
		JSONObject returnJson = new JSONObject();
		
		try {
			
			JSONObject alertJson = new JSONObject();
			
			alertJson.put("title", getValueByKey(createJson,"subject"));;
			alertJson.put("description", getValueByKey(createJson,"description"));;
			alertJson.put("status", getValueByKey(createJson,"status"));
			alertJson.put("severity", getValueByKey(createJson,"priority"));
			alertJson.put("category", getValueByKey(createJson, "category"));
			alertJson.put("zohoTicketId", getValueByKey(createJson, "id"));
			
			String contactId = getValueByKey(createJson, "contactId");// Need to Clarify
			
			String tdpCadreId=null;
			 if(contactId !=null && !contactId.isEmpty()){
				 // getTdpCadreInfoOfContact()
			 }
			
			JSONObject customFields = createJson.getJSONObject("customFields");
			
			alertJson.put("impactScope", getValueByKey(customFields, "Impact Scope"));
			alertJson.put("alertType", getValueByKey(customFields, "Alert Type"));			
			alertJson.put("alertType", getValueByKey(customFields, "Alert Source"));
			alertJson.put("tvNewsChannel", getValueByKey(customFields, "TV News Channel"));
			alertJson.put("editionType", getValueByKey(customFields, "Edition Type"));
			alertJson.put("newsPaperEdition", getValueByKey(customFields, "News Paper Edition"));
			alertJson.put("newsPaper", getValueByKey(customFields, "News Paper"));
			
			alertJson.put("locationLevel", getValueByKey(customFields, "Location Level"));
			alertJson.put("location", getValueByKey(customFields, "Location"));
			alertJson.put("state", getValueByKey(customFields, "State"));
			alertJson.put("district", getValueByKey(customFields, "District"));
			alertJson.put("constituency",getValueByKey(customFields, "Constituency"));
			alertJson.put("tehsil",getValueByKey(customFields, "Tehsil"));
			alertJson.put("municipality",getValueByKey(customFields, "Municipality"));
			alertJson.put("panchayat",getValueByKey(customFields, "Panchayat"));
			alertJson.put("ward",getValueByKey(customFields, "Ward"));
		
			returnJson = alertCreationAPIService.createAlertApi(alertJson,"zoho");
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnJson;
	}
	public String getValueByKey(JSONObject json,String key) throws JSONException {
		if(json!=null && json.has(key) && !json.getString(key).isEmpty()) {
			return json.getString(key);
		}
		return null;
	}
	
}
