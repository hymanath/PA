package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dto.EMeetingsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IEMeetingsDashboardService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Transactional
@Service
public class EMeetingsDahboardService implements IEMeetingsDashboardService{

	private static final Logger LOG = Logger.getLogger(EMeetingsDahboardService.class);
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	@Autowired
	private IDistrictDAO districtDAO;
	/*
	 * Author : Nandhini.k,
	 * Date : 28/02/2018,
	 * Input : locationId,locationType,fromDate,toDate,
	 * @Description : To get E Meetings Overview Details
	 */
	public EMeetingsVO getEMeetingsOverViewDetails(InputVO inputVO){
		EMeetingsVO finalVO = new EMeetingsVO();
		try {
			String url = null;
			if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
				url = "http://pris.ap.gov.in/api/meetings/apiq.php?meetingStats=1&fromdate="+inputVO.getFromDate()+"&todate="+inputVO.getToDate()+"";
			}else{
				url = "http://pris.ap.gov.in/api/meetings/apiq.php?getmeetingDataFilter=1&locationType="+inputVO.getLocationType()+"&locationId="+inputVO.getLocationId()+"";
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(url);
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject dataObj = new JSONObject(output);
	 	    		finalVO.setTotalPanchayats(dataObj.has("totalPanchayats") ? (Long.valueOf(!(dataObj.getString("totalPanchayats").equalsIgnoreCase("null") || dataObj.getString("totalPanchayats").equalsIgnoreCase("NA") || dataObj.getString("totalPanchayats").isEmpty()) ? dataObj.getString("totalPanchayats"):"0")):0);
	 	    		finalVO.setConductedPanchayats(dataObj.has("ePanchayats") ? (Long.valueOf(!(dataObj.getString("ePanchayats").equalsIgnoreCase("null") || dataObj.getString("ePanchayats").equalsIgnoreCase("NA") || dataObj.getString("ePanchayats").isEmpty()) ? dataObj.getString("ePanchayats"):"0")):0);
	 	    		finalVO.setNotConductedPanchayts(dataObj.has("not_ePanchayats") ? (Long.valueOf(!(dataObj.getString("not_ePanchayats").equalsIgnoreCase("null") || dataObj.getString("not_ePanchayats").equalsIgnoreCase("NA") || dataObj.getString("not_ePanchayats").isEmpty()) ? dataObj.getString("not_ePanchayats"):"0")):0);
	 	    		finalVO.setTotalMeetings(dataObj.has("totalMeetings") ? (Long.valueOf(!(dataObj.getString("totalMeetings").equalsIgnoreCase("null") || dataObj.getString("totalMeetings").equalsIgnoreCase("NA") || dataObj.getString("totalMeetings").isEmpty()) ? dataObj.getString("totalMeetings"):"0")):0);
	 	    		finalVO.setConductedMeetings(dataObj.has("conducted") ? (Long.valueOf(!(dataObj.getString("conducted").equalsIgnoreCase("null") || dataObj.getString("conducted").equalsIgnoreCase("NA") || dataObj.getString("conducted").isEmpty()) ? dataObj.getString("conducted"):"0")):0);
	 	    		finalVO.setNotConductedMeetings(dataObj.has("notConducted") ? (Long.valueOf(!(dataObj.getString("notConducted").equalsIgnoreCase("null") || dataObj.getString("notConducted").equalsIgnoreCase("NA") || dataObj.getString("notConducted").isEmpty()) ? dataObj.getString("notConducted"):"0")):0);
	 	    		finalVO.setTotalAgendaPts(dataObj.has("totalAgenda") ? (Long.valueOf(!(dataObj.getString("totalAgenda").equalsIgnoreCase("null")  || dataObj.getString("totalAgenda").equalsIgnoreCase("NA") || dataObj.getString("totalAgenda").isEmpty()) ? dataObj.getString("totalAgenda"):"0")):0);
	 	    		finalVO.setApprovedAgendaPts(dataObj.has("approvedAgenda") ? (Long.valueOf(!(dataObj.getString("approvedAgenda").toString().equalsIgnoreCase("null") || dataObj.getString("approvedAgenda").equalsIgnoreCase("NA") || dataObj.getString("approvedAgenda").isEmpty()) ? dataObj.getString("approvedAgenda"):"0")):0);
	 	    		finalVO.setNotApprovedAgendaPts(dataObj.has("declinedAgenda") ? (Long.valueOf(!(dataObj.getString("declinedAgenda").toString().equalsIgnoreCase("null") || dataObj.getString("declinedAgenda").equalsIgnoreCase("NA") || dataObj.getString("declinedAgenda").isEmpty()) ? dataObj.getString("declinedAgenda"):"0")):0);
	 	    		finalVO.setGeneralMeetings(dataObj.has("generalMeetings") ? (Long.valueOf(!(dataObj.getString("generalMeetings").toString().equalsIgnoreCase("null") || dataObj.getString("generalMeetings").equalsIgnoreCase("NA") || dataObj.getString("generalMeetings").isEmpty()) ? dataObj.getString("generalMeetings"):"0")):0);
	 	    		finalVO.setEmergencyMeetings(dataObj.has("emergencyMeetings") ? (Long.valueOf(!(dataObj.getString("emergencyMeetings").equalsIgnoreCase("null")  || dataObj.getString("emergencyMeetings").equalsIgnoreCase("NA") || dataObj.getString("emergencyMeetings").isEmpty()) ? dataObj.getString("emergencyMeetings"):"0")):0);
	 	    		finalVO.setReqMeetings(dataObj.has("requestMeetings") ? (Long.valueOf(!(dataObj.getString("requestMeetings").equalsIgnoreCase("null") || dataObj.getString("requestMeetings").equalsIgnoreCase("NA") || dataObj.getString("requestMeetings").isEmpty()) ? dataObj.getString("requestMeetings"):"0")):0);
	 	    		finalVO.setBeyond60Days(dataObj.has("beyond60Days") ? (Long.valueOf(!(dataObj.getString("beyond60Days").equalsIgnoreCase("null") || dataObj.getString("beyond60Days").equalsIgnoreCase("NA") || dataObj.getString("beyond60Days").isEmpty()) ? dataObj.getString("beyond60Days"):"0")):0);
	 	    		finalVO.setBeyond90Days(dataObj.has("beyond90Days") ? (Long.valueOf(!(dataObj.getString("beyond90Days").equalsIgnoreCase("null")  || dataObj.getString("beyond90Days").equalsIgnoreCase("NA") || dataObj.getString("beyond90Days").isEmpty()) ? dataObj.getString("beyond90Days"):"0")):0);
	 	    		finalVO.setBeyond120Days(dataObj.has("beyond120Days") ? (Long.valueOf(!(dataObj.getString("beyond120Days").equalsIgnoreCase("null")  || dataObj.getString("beyond120Days").equalsIgnoreCase("NA") || dataObj.getString("beyond120Days").isEmpty()) ? dataObj.getString("beyond120Days"):"0")):0);
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception occured at getEMeetingsOverViewDetails() - EMeetingsDahboardService ",e);
		}
		return finalVO;
	}
	
	/*
	 * Author : Nandhini.k,
	 * Date : 28/02/2018,
	 * Input : locationId,locationType,fromDate,toDate,SubLocationType
	 * @Description : To get E meetings LevelWise  OverView Details
	 */
	public List<EMeetingsVO> getEMeetingsLevelWiseOverViewDetails(InputVO inputVO){
		List<EMeetingsVO> returnList = new ArrayList<EMeetingsVO>(0);
		try {
			String name = null;
			Map<String,Long> districtMap = new LinkedHashMap<String,Long>(0);
			if(inputVO.getLocationType() != null && inputVO.getSublocaType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase(inputVO.getSublocaType()))){
				inputVO.setSubLocationId(inputVO.getLocationId());
			}else{
				inputVO.setSubLocationId(0L);
			}
			
			List<Object[]> districtList = districtDAO.getDistrictIdName(1L);
			if(districtList != null && !districtList.isEmpty()){
				for (Object[] param : districtList) {
					Long districtId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String districtName = commonMethodsUtilService.getStringValueForObject(param[1]);
					districtMap.put(districtName, districtId);
				}
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/meetings/apiq.php?getMeetingsByLocation=1&locationId="+inputVO.getSubLocationId()+"&locationType="+inputVO.getSublocaType()+"&filterType="+inputVO.getLocationType()+"&filterId="+inputVO.getLocationId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	      		if(finalArray!=null && finalArray.length()>0){
	 	      			for(int i=0;i<finalArray.length();i++){
	 	      				EMeetingsVO vo = new EMeetingsVO();
	 	      				JSONObject jObj = (JSONObject) finalArray.get(i);	
	 	      				if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().equalsIgnoreCase("district")){
	 	      					name = jObj.get("districtname").toString();
	 	      					if(name != null && name.toString().equalsIgnoreCase("Ananthapur")){
	 	      						name = "Anantapur";
	 	      					}else if(name != null && name.toString().equalsIgnoreCase("YSR District")){
	 	      						name = "Kadapa";
	 	      					}else if(name != null && name.toString().equalsIgnoreCase("SPSR Nellore")){
	 	      						name = "Nellore";
	 	      					}
	 	      					Long locationId = districtMap.get(name);
	 	      					vo.setDistrictId(locationId);
				 	    		vo.setDistrictName(jObj.has("districtname") ? (!jObj.get("districtname").toString().equalsIgnoreCase("null") ? jObj.get("districtname").toString():"0"):"0");
	 	      				}else if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().equalsIgnoreCase("assembly")){
	 	      					vo.setDistrictId(jObj.has("district") ? (Long.valueOf(!jObj.get("district").toString().equalsIgnoreCase("null") ? jObj.get("district").toString():"0")):0);
				 	    		vo.setDistrictName(jObj.has("districtname") ? (!(jObj.getString("districtname").toString().equalsIgnoreCase("null") || jObj.getString("districtname").toString().equalsIgnoreCase("NA") || jObj.getString("districtname").toString().isEmpty()) ? jObj.get("districtname").toString():"0"):"0");
			 	    			vo.setConstituencyId(jObj.has("assemblyid") ? (Long.valueOf(!(jObj.get("assemblyid").toString().equalsIgnoreCase("null") || jObj.getString("assemblyid").toString().equalsIgnoreCase("NA") || jObj.getString("assemblyid").toString().isEmpty())  ? jObj.get("assemblyid").toString():"0")):0);
			 	    			vo.setConstituencyName(jObj.has("assemblyname") ? (!(jObj.get("assemblyname").toString().equalsIgnoreCase("null") || jObj.getString("assemblyname").toString().equalsIgnoreCase("NA") || jObj.getString("assemblyname").toString().isEmpty())  ? jObj.get("assemblyname").toString():"0"):"0");
			 	    		}else if(inputVO.getSublocaType() != null ||  inputVO.getSublocaType().trim().equalsIgnoreCase("mandal")){
			 	    			vo.setMandalId(jObj.has("id") ? (Long.valueOf(!(jObj.get("id").toString().equalsIgnoreCase("null") || jObj.getString("id").toString().equalsIgnoreCase("NA") || jObj.getString("id").toString().isEmpty())  ? jObj.get("id").toString():"0")):0);
			 	    			vo.setMandalName(jObj.has("name") ? (!(jObj.get("name").toString().equalsIgnoreCase("null") || jObj.getString("name").toString().equalsIgnoreCase("NA") || jObj.getString("name").toString().isEmpty())  ? jObj.get("name").toString():"0"):"0");
			 	    			vo.setDistrictId(jObj.has("districtId") ? (Long.valueOf(!(jObj.get("districtId").toString().equalsIgnoreCase("null") || jObj.getString("districtId").toString().equalsIgnoreCase("NA") || jObj.getString("districtId").toString().isEmpty())  ? jObj.get("districtId").toString():"0")):0);
				 	    		vo.setDistrictName(jObj.has("district") ? (!(jObj.get("district").toString().equalsIgnoreCase("null") || jObj.getString("district").toString().equalsIgnoreCase("NA") || jObj.getString("district").toString().isEmpty())  ? jObj.get("district").toString():"0"):"0");
				 	    		vo.setConstituencyId(jObj.has("assemblyId") ? (Long.valueOf(!(jObj.get("assemblyId").toString().equalsIgnoreCase("null") || jObj.getString("assemblyId").toString().equalsIgnoreCase("NA") || jObj.getString("assemblyId").toString().isEmpty())  ? jObj.get("assemblyId").toString():"0")):0);
				 	    		vo.setConstituencyName(jObj.has("assemblyName") ? (!(jObj.get("assemblyName").toString().equalsIgnoreCase("null") || jObj.getString("assemblyName").toString().equalsIgnoreCase("NA") || jObj.getString("assemblyName").toString().isEmpty())  ? jObj.get("assemblyName").toString():"0"):"0");
				 	    		vo.setParliamentId(jObj.has("costituencyId") ? (Long.valueOf(!(jObj.get("costituencyId").toString().equalsIgnoreCase("null") || jObj.getString("costituencyId").toString().equalsIgnoreCase("NA") || jObj.getString("costituencyId").toString().isEmpty())  ? jObj.get("costituencyId").toString():"0")):0);
				 	    		vo.setParliamentName(jObj.has("constituencyName") ? (!(jObj.get("constituencyName").toString().equalsIgnoreCase("null") || jObj.getString("constituencyName").toString().equalsIgnoreCase("NA") || jObj.getString("constituencyName").toString().isEmpty())  ? jObj.get("constituencyName").toString():"0"):"0");
			 	    		}
			 	    		vo.setTotalPanchayats(jObj.has("ePanchayats") ? (Long.valueOf(!(jObj.get("ePanchayats").toString().equalsIgnoreCase("null") || jObj.getString("ePanchayats").toString().equalsIgnoreCase("NA") || jObj.getString("ePanchayats").toString().isEmpty())  ? jObj.get("ePanchayats").toString():"0")):0);
			 	    		vo.setExceptedMeetings(jObj.has("expectedMeetings") ? (Long.valueOf(!(jObj.get("expectedMeetings").toString().equalsIgnoreCase("null") || jObj.getString("expectedMeetings").toString().equalsIgnoreCase("NA") || jObj.getString("expectedMeetings").toString().isEmpty())  ? jObj.get("expectedMeetings").toString():"0")):0);
			 	    		vo.setTotalMeetings(jObj.has("totalMeetings") ? (Long.valueOf(!(jObj.get("totalMeetings").toString().equalsIgnoreCase("null") || jObj.getString("totalMeetings").toString().equalsIgnoreCase("NA") || jObj.getString("totalMeetings").toString().isEmpty())  ? jObj.get("totalMeetings").toString():"0")):0);
			 	    		vo.setConductedMeetings(jObj.has("conducted") ? (Long.valueOf(!(jObj.get("conducted").toString().equalsIgnoreCase("null") || jObj.getString("conducted").toString().equalsIgnoreCase("NA") || jObj.getString("conducted").toString().isEmpty())  ? jObj.get("conducted").toString():"0")):0);
			 	    		vo.setGeneralMeetings(jObj.has("generalMeetings") ? (Long.valueOf(!(jObj.get("generalMeetings").toString().equalsIgnoreCase("null") || jObj.getString("generalMeetings").toString().equalsIgnoreCase("NA") || jObj.getString("generalMeetings").toString().isEmpty())  ? jObj.get("generalMeetings").toString():"0")):0);
			 	    		vo.setEmergencyMeetings(jObj.has("emergencyMeetings") ? (Long.valueOf(!(jObj.get("emergencyMeetings").toString().equalsIgnoreCase("null") || jObj.getString("emergencyMeetings").toString().equalsIgnoreCase("NA") || jObj.getString("emergencyMeetings").toString().isEmpty())  ? jObj.get("emergencyMeetings").toString():"0")):0);
			 	    		vo.setReqMeetings(jObj.has("requestMeetings") ? (Long.valueOf(!(jObj.get("requestMeetings").toString().equalsIgnoreCase("null") || jObj.getString("requestMeetings").toString().equalsIgnoreCase("NA") || jObj.getString("requestMeetings").toString().isEmpty())  ? jObj.get("requestMeetings").toString():"0")):0);
			 	    		vo.setApprovedAgendaPts(jObj.has("approved") ? (Long.valueOf(!(jObj.get("approved").toString().equalsIgnoreCase("null") || jObj.getString("approved").toString().equalsIgnoreCase("NA") || jObj.getString("approved").toString().isEmpty())  ? jObj.get("approved").toString():"0")):0);
			 	    		vo.setNotApprovedAgendaPts(jObj.has("declined") ? (Long.valueOf(!(jObj.get("declined").toString().equalsIgnoreCase("null") || jObj.getString("declined").toString().equalsIgnoreCase("NA") || jObj.getString("declined").toString().isEmpty())  ? jObj.get("declined").toString():"0")):0);
			 	    		vo.setTotalAgendaPts(vo.getApprovedAgendaPts()+vo.getNotApprovedAgendaPts());
			 	    		vo.setBeyond60Days(jObj.has("beyond60Days") ? (Long.valueOf(!(jObj.get("beyond60Days").toString().equalsIgnoreCase("null") || jObj.getString("beyond60Days").toString().equalsIgnoreCase("NA") || jObj.getString("beyond60Days").toString().isEmpty())  ? jObj.get("beyond60Days").toString():"0")):0);
			 	    		vo.setBeyond90Days(jObj.has("beyond90Days") ? (Long.valueOf(!(jObj.get("beyond90Days").toString().equalsIgnoreCase("null") || jObj.getString("beyond90Days").toString().equalsIgnoreCase("NA") || jObj.getString("beyond90Days").toString().isEmpty())  ? jObj.get("beyond90Days").toString():"0")):0);
			 	    		vo.setBeyond120Days(jObj.has("beyond120Days") ? (Long.valueOf(!(jObj.get("beyond120Days").toString().equalsIgnoreCase("null") || jObj.getString("beyond120Days").toString().equalsIgnoreCase("NA") || jObj.getString("beyond120Days").toString().isEmpty())  ? jObj.get("beyond120Days").toString():"0")):0);
			 	    		returnList.add(vo);
	 	      			}
	 	      		}
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception occured at getEMeetingsLevelWiseOverViewDetails() - EMeetingsDahboardService ",e);
		}
		return returnList;
	}
	/*
	 * Author : Nandhini.k,
	 * Date : 01/03/2018,
	 * Input : locationId,locationType
	 * @Description : To get Level Wise Conducted Meeting Details
	 */
	public EMeetingsVO getLevelsWiseConductedMeetingDetails(InputVO inputVO){
		EMeetingsVO finalVO = new EMeetingsVO();
		try {
			JSONArray finalArray = null;
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/meetings/apiq.php?getmeetingDataFilter=1&locationType="+inputVO.getLocationType()+"&locationId="+inputVO.getLocationId()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject dataObj = new JSONObject(output);
	 	    		String meetingData = dataObj.has("meetingsData") ? dataObj.get("meetingsData").toString():null;
	 	    		if(meetingData != null){
	 	    			finalArray = new JSONArray(meetingData);
	 	    		}
	 	    		
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	      			for(int i=0;i<finalArray.length();i++){
	 	      				EMeetingsVO vo = new EMeetingsVO();
	 	      				JSONObject jObj = (JSONObject) finalArray.get(i);	
	 	      				vo.setPanchayatId(jObj.has("panchayat_id") ? (Long.valueOf(!(jObj.get("panchayat_id").toString().equalsIgnoreCase("null") || jObj.get("panchayat_id").toString().equalsIgnoreCase("NA") || jObj.get("panchayat_id").toString().isEmpty())? jObj.get("panchayat_id").toString():"0")):0);
	 	      				vo.setPanchayatName(jObj.has("panchayat") ? (!(jObj.get("panchayat").toString().equalsIgnoreCase("null") || jObj.get("panchayat").toString().equalsIgnoreCase("NA") || jObj.get("panchayat").toString().isEmpty()) ? jObj.get("panchayat").toString():"0"):"0");
	 	      				vo.setMeetingId(jObj.has("meetingId") ? (Long.valueOf(!(jObj.get("meetingId").toString().equalsIgnoreCase("null") || jObj.get("meetingId").toString().equalsIgnoreCase("NA") || jObj.get("meetingId").toString().isEmpty()) ? jObj.get("meetingId").toString():"0")):0);
	 	      				vo.setMeetingName(jObj.has("meetingName") ? (!(jObj.get("meetingName").toString().equalsIgnoreCase("null") || jObj.get("meetingName").toString().equalsIgnoreCase("NA") || jObj.get("meetingName").toString().isEmpty()) ? jObj.get("meetingName").toString():"0"):"0");
	 	      				vo.setMeetingDate(jObj.has("conductedDate") ? (!(jObj.get("conductedDate").toString().equalsIgnoreCase("null") || jObj.get("conductedDate").toString().equalsIgnoreCase("NA") || jObj.get("conductedDate").toString().isEmpty()) ? jObj.get("conductedDate").toString():"0"):"0");
	 	      				vo.setTotalMembers(jObj.has("panchayatMembers") ? (Long.valueOf(!(jObj.get("panchayatMembers").toString().equalsIgnoreCase("null") || jObj.get("panchayatMembers").toString().equalsIgnoreCase("NA") || jObj.get("panchayatMembers").toString().isEmpty()) ? jObj.get("panchayatMembers").toString():"0")):0);
	 	      				vo.setAttendedMembers(jObj.has("attendedMembers") ? (Long.valueOf(!(jObj.get("attendedMembers").toString().equalsIgnoreCase("null") || jObj.get("attendedMembers").toString().equalsIgnoreCase("NA") || jObj.get("attendedMembers").toString().isEmpty()) ? jObj.get("attendedMembers").toString():"0")):0);
	 	      				vo.setAbsentMembers(jObj.has("absentMembers") ? (Long.valueOf(!(jObj.get("absentMembers").toString().equalsIgnoreCase("null") || jObj.get("absentMembers").toString().equalsIgnoreCase("NA") || jObj.get("absentMembers").toString().isEmpty()) ? jObj.get("absentMembers").toString():"0")):0);
	 	      				vo.setMinutesOfMeeting(jObj.has("mom") ? (!(jObj.get("mom").toString().equalsIgnoreCase("null") || jObj.get("mom").toString().equalsIgnoreCase("NA") || jObj.get("mom").toString().isEmpty()) ? jObj.get("mom").toString():"0"):"0");
	 	      				finalVO.getSubList().add(vo);
	 	      			}
	 	      		}
	 	    		finalVO.setTotalPanchayats(Long.valueOf(!(dataObj.get("totalPanchayats").toString().equalsIgnoreCase("null") || dataObj.get("totalPanchayats").toString().equalsIgnoreCase("NA") || dataObj.get("totalPanchayats").toString().isEmpty()) ? dataObj.get("totalPanchayats").toString():"0"));
	 	    		finalVO.setConductedPanchayats(Long.valueOf(!(dataObj.get("ePanchayats").toString().equalsIgnoreCase("null") || dataObj.get("ePanchayats").toString().equalsIgnoreCase("NA") || dataObj.get("ePanchayats").toString().isEmpty()) ? dataObj.get("ePanchayats").toString():"0"));
	 	    		finalVO.setNotConductedPanchayts(Long.valueOf(!(dataObj.get("not_ePanchayats").toString().equalsIgnoreCase("null") || dataObj.get("not_ePanchayats").toString().equalsIgnoreCase("NA") || dataObj.get("not_ePanchayats").toString().isEmpty()) ? dataObj.get("not_ePanchayats").toString():"0"));
	 	    		finalVO.setTotalMeetings(Long.valueOf(!(dataObj.get("totalMeetings").toString().equalsIgnoreCase("null") || dataObj.get("totalMeetings").toString().equalsIgnoreCase("NA") || dataObj.get("totalMeetings").toString().isEmpty()) ? dataObj.get("totalMeetings").toString():"0"));
	 	    		finalVO.setConductedMeetings(Long.valueOf(!(dataObj.get("conducted").toString().equalsIgnoreCase("null") || dataObj.get("conducted").toString().equalsIgnoreCase("NA") || dataObj.get("conducted").toString().isEmpty()) ? dataObj.get("conducted").toString():"0"));
	 	    		finalVO.setNotConductedMeetings(Long.valueOf(!(dataObj.get("notConducted").toString().equalsIgnoreCase("null") || dataObj.get("notConducted").toString().equalsIgnoreCase("NA") || dataObj.get("notConducted").toString().isEmpty()) ? dataObj.get("notConducted").toString():"0"));
	 	    		finalVO.setTotalAgendaPts(Long.valueOf(!(dataObj.get("totalAgenda").toString().equalsIgnoreCase("null") || dataObj.get("totalAgenda").toString().equalsIgnoreCase("NA") || dataObj.get("totalAgenda").toString().isEmpty()) ? dataObj.get("totalAgenda").toString():"0"));
	 	    		finalVO.setApprovedAgendaPts(Long.valueOf(!(dataObj.get("approvedAgenda").toString().equalsIgnoreCase("null") || dataObj.get("approvedAgenda").toString().equalsIgnoreCase("NA") || dataObj.get("approvedAgenda").toString().isEmpty()) ? dataObj.get("approvedAgenda").toString():"0"));
	 	    		finalVO.setNotApprovedAgendaPts(Long.valueOf(!(dataObj.get("declinedAgenda").toString().equalsIgnoreCase("null") || dataObj.get("declinedAgenda").toString().equalsIgnoreCase("NA") || dataObj.get("declinedAgenda").toString().isEmpty()) ? dataObj.get("declinedAgenda").toString():"0"));
	 	    		finalVO.setGeneralMeetings(Long.valueOf(!(dataObj.get("generalMeetings").toString().equalsIgnoreCase("null") || dataObj.get("generalMeetings").toString().equalsIgnoreCase("NA") || dataObj.get("generalMeetings").toString().isEmpty()) ? dataObj.get("generalMeetings").toString():"0"));
	 	    		finalVO.setEmergencyMeetings(Long.valueOf(!(dataObj.get("emergencyMeetings").toString().equalsIgnoreCase("null") || dataObj.get("emergencyMeetings").toString().equalsIgnoreCase("NA") || dataObj.get("emergencyMeetings").toString().isEmpty()) ? dataObj.get("emergencyMeetings").toString():"0"));
	 	    		finalVO.setReqMeetings(Long.valueOf(!(dataObj.get("requestMeetings").toString().equalsIgnoreCase("null") || dataObj.get("requestMeetings").toString().equalsIgnoreCase("NA") || dataObj.get("requestMeetings").toString().isEmpty()) ? dataObj.get("requestMeetings").toString():"0"));
	 	    		finalVO.setBeyond60Days(Long.valueOf(!(dataObj.get("beyond60Days").toString().equalsIgnoreCase("null") || dataObj.get("beyond60Days").toString().equalsIgnoreCase("NA") || dataObj.get("beyond60Days").toString().isEmpty()) ? dataObj.get("beyond60Days").toString():"0"));
	 	    		finalVO.setBeyond90Days(Long.valueOf(!(dataObj.get("beyond90Days").toString().equalsIgnoreCase("null") || dataObj.get("beyond90Days").toString().equalsIgnoreCase("NA") || dataObj.get("beyond90Days").toString().isEmpty()) ? dataObj.get("beyond90Days").toString():"0"));
	 	    		finalVO.setBeyond120Days(Long.valueOf(!(dataObj.get("beyond120Days").toString().equalsIgnoreCase("null") || dataObj.get("beyond120Days").toString().equalsIgnoreCase("NA") || dataObj.get("beyond120Days").toString().isEmpty()) ? dataObj.get("beyond120Days").toString():"0"));
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception occured at getLevelsWiseConductedMeetingDetails() - EMeetingsDahboardService ",e);
		}
		return finalVO;
	}
	/*
	 * Author : Nandhini.k,
	 * Date : 01/03/2018,
	 * Input : meetingId
	 * @Description : To get Meeting Details
	 */
	public EMeetingsVO getMeetingDetails(InputVO inputVO){
		EMeetingsVO finalVO = new EMeetingsVO();
		try {
			JSONArray itemObject = null;
			JSONObject memberObject = null;
			JSONArray finalArray = null;
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/meetings/apiq.php?meetingOverview=1&meeting_id="+inputVO.getDeptId()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject dataObj = new JSONObject(output);
	 	    		String itemData = dataObj.has("item") ? dataObj.get("item").toString():null;
	 	    		if(itemData != null){
	 	    			itemObject = new JSONArray(itemData);
	 	    		}
	 	    		if(itemObject != null){
	 	    			memberObject = new JSONObject(itemObject.getString(0));
	 	    		}
	 	    		if(memberObject != null){
	 	    			String finalObject =  memberObject.has("members") ? memberObject.get("members").toString():null;
	 	    			if(finalObject != null){
	 	    				finalArray = new JSONArray(finalObject);
	 	    			}
	 	    			
	 	    		}
	 	    		
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	      			for(int i=0;i<finalArray.length();i++){
	 	      				EMeetingsVO vo = new EMeetingsVO();
	 	      				JSONObject jObj = (JSONObject) finalArray.get(i);	
	 	      				vo.setMemberId(jObj.has("memberId") ? (Long.valueOf(!(jObj.get("memberId").toString().equalsIgnoreCase("null") || jObj.get("memberId").toString().equalsIgnoreCase("NA") || jObj.get("memberId").toString().isEmpty()) ? jObj.get("memberId").toString():"0")):0);
	 	      				vo.setMemberName(jObj.has("memberName") ? (!(jObj.get("memberName").toString().equalsIgnoreCase("null") || jObj.get("memberName").toString().equalsIgnoreCase("NA") || jObj.get("memberName").toString().isEmpty()) ? jObj.get("memberName").toString():"0"):"0");
	 	      				vo.setDesignation(jObj.has("designation") ? (!(jObj.get("designation").toString().equalsIgnoreCase("null") || jObj.get("designation").toString().equalsIgnoreCase("NA") || jObj.get("designation").toString().isEmpty()) ? jObj.get("designation").toString():"0"):"0");
	 	      				vo.setMobileNo(jObj.has("mobile") ? (!(jObj.get("mobile").toString().equalsIgnoreCase("null") || jObj.get("mobile").toString().equalsIgnoreCase("NA") || jObj.get("mobile").toString().isEmpty()) ? jObj.get("mobile").toString():"0"):"0");
	 	      				vo.setInvitationStatus(jObj.has("invitationStatus") ? (!(jObj.get("invitationStatus").toString().equalsIgnoreCase("null") || jObj.get("invitationStatus").toString().equalsIgnoreCase("NA") || jObj.get("invitationStatus").toString().isEmpty()) ? jObj.get("invitationStatus").toString():"0"):"0");
	 	      				vo.setAttendance(jObj.has("attendance") ? (!(jObj.get("attendance").toString().equalsIgnoreCase("null") || jObj.get("attendance").toString().equalsIgnoreCase("NA") || jObj.get("attendance").toString().isEmpty()) ? jObj.get("attendance").toString():"0"):"0");
	 	      				vo.setVote(jObj.has("vote") ? (!(jObj.get("vote").toString().equalsIgnoreCase("null") || jObj.get("vote").toString().equalsIgnoreCase("NA") || jObj.get("vote").toString().isEmpty()) ? jObj.get("vote").toString():"0"):"0");
	 	      				vo.setRemark(jObj.has("remarks") ? (!(jObj.get("remarks").toString().equalsIgnoreCase("null") || jObj.get("remarks").toString().equalsIgnoreCase("NA") || jObj.getString("remarks").isEmpty()) ? jObj.get("remarks").toString():"0"):"0");
	 	      				finalVO.getSubList().add(vo);
	 	      			}
	 	      		}
	 	    		finalVO.setMeetingType(dataObj.has("meeting_type") ? (!(dataObj.get("meeting_type").toString().equalsIgnoreCase("null") || dataObj.get("meeting_type").toString().equalsIgnoreCase("NA") || dataObj.get("meeting_type").toString().isEmpty()) ? dataObj.get("meeting_type").toString():"0"):"0");
	 	    		finalVO.setMeetingDate(dataObj.has("meetingDate") ? (!(dataObj.get("meetingDate").toString().equalsIgnoreCase("null") || dataObj.get("meetingDate").toString().equalsIgnoreCase("NA") || dataObj.get("meetingDate").toString().isEmpty()) ? dataObj.get("meetingDate").toString():"0"):"0");
	 	    		finalVO.setTotalMembers(dataObj.has("totalMembers") ? (Long.valueOf(!(dataObj.get("totalMembers").toString().equalsIgnoreCase("null") || dataObj.get("totalMembers").toString().equalsIgnoreCase("NA") || dataObj.get("totalMembers").toString().isEmpty()) ? dataObj.get("totalMembers").toString():"0")):0);
	 	    		finalVO.setAttendedMembers(dataObj.has("attendedMembers") ? (Long.valueOf(!(dataObj.get("attendedMembers").toString().equalsIgnoreCase("null") || dataObj.get("attendedMembers").toString().equalsIgnoreCase("NA") || dataObj.get("attendedMembers").toString().isEmpty()) ? dataObj.get("attendedMembers").toString():"0")):0);
	 	    		finalVO.setAbsentMembers(dataObj.has("absentMembers") ? (Long.valueOf(!(dataObj.get("absentMembers").toString().equalsIgnoreCase("null") || dataObj.get("absentMembers").toString().equalsIgnoreCase("NA") || dataObj.get("absentMembers").toString().isEmpty()) ? dataObj.get("absentMembers").toString():"0")):0);
	 	    		finalVO.setMinutesOfMeeting(dataObj.has("minutesOfMom") ? (!(dataObj.get("minutesOfMom").toString().equalsIgnoreCase("null") || dataObj.get("minutesOfMom").toString().equalsIgnoreCase("NA") || dataObj.get("minutesOfMom").toString().isEmpty()) ? dataObj.get("minutesOfMom").toString():"0"):"0");
	 	    		finalVO.setMeetingDuration(dataObj.has("meetingDuration") ? (!(dataObj.get("meetingDuration").toString().equalsIgnoreCase("null") || dataObj.get("meetingDuration").toString().equalsIgnoreCase("NA") || dataObj.get("meetingDuration").toString().isEmpty()) ? dataObj.get("meetingDuration").toString():"0"):"0");
	 	    		finalVO.setTotalAgendaPts(dataObj.has("totalAgendas") ? (Long.valueOf(!(dataObj.get("totalAgendas").toString().equalsIgnoreCase("null") || dataObj.get("totalAgendas").toString().equalsIgnoreCase("NA") || dataObj.get("totalAgendas").toString().isEmpty()) ? dataObj.get("totalAgendas").toString():"0")):0);
	 	    		finalVO.setApprovedAgendaPts(dataObj.has("approvedAgenda") ? (Long.valueOf(!(dataObj.get("approvedAgenda").toString().equalsIgnoreCase("null") || dataObj.get("approvedAgenda").toString().equalsIgnoreCase("NA") || dataObj.get("approvedAgenda").toString().isEmpty()) ? dataObj.get("approvedAgenda").toString():"0")):0);
	 	    		finalVO.setNotApprovedAgendaPts(dataObj.has("declinedAgendas") ? (Long.valueOf(!(dataObj.get("declinedAgendas").toString().equalsIgnoreCase("null") || dataObj.get("declinedAgendas").toString().equalsIgnoreCase("NA") || dataObj.get("declinedAgendas").toString().isEmpty()) ? dataObj.get("declinedAgendas").toString():"0")):0);
	 	    		
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception occured at getMeetingDetails() - EMeetingsDahboardService ",e);
		}
		return finalVO;
	}
	
}
