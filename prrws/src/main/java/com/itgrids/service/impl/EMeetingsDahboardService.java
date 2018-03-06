package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.EMeetingsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregsDataVO;
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
	 	    		finalVO.setTotalPanchayats(Long.valueOf(dataObj.get("totalPanchayats").toString()));
	 	    		finalVO.setConductedPanchayats(Long.valueOf(dataObj.get("ePanchayats").toString()));
	 	    		finalVO.setNotConductedPanchayts(Long.valueOf(dataObj.get("not_ePanchayats").toString()));
	 	    		finalVO.setTotalMeetings(Long.valueOf(dataObj.get("totalMeetings").toString()));
	 	    		finalVO.setConductedMeetings(Long.valueOf(dataObj.get("conducted").toString()));
	 	    		finalVO.setNotConductedMeetings(Long.valueOf(dataObj.get("notConducted").toString()));
	 	    		finalVO.setTotalAgendaPts(Long.valueOf(dataObj.get("totalAgenda").toString()));
	 	    		finalVO.setApprovedAgendaPts(Long.valueOf(dataObj.get("approvedAgenda").toString()));
	 	    		finalVO.setNotApprovedAgendaPts(Long.valueOf(dataObj.get("declinedAgenda").toString()));
	 	    		finalVO.setGeneralMeetings(Long.valueOf(dataObj.get("generalMeetings").toString()));
	 	    		finalVO.setEmergencyMeetings(Long.valueOf(dataObj.get("emergencyMeetings").toString()));
	 	    		finalVO.setReqMeetings(Long.valueOf(dataObj.get("requestMeetings").toString()));
	 	    		finalVO.setBeyond60Days(Long.valueOf(dataObj.get("beyond60Days").toString()));
	 	    		finalVO.setBeyond90Days(Long.valueOf(dataObj.get("beyond90Days").toString()));
	 	    		finalVO.setBeyond120Days(Long.valueOf(dataObj.get("beyond120Days").toString()));
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
			if(inputVO.getLocationType() != null && inputVO.getSublocaType() != null && (inputVO.getLocationType().trim().equalsIgnoreCase(inputVO.getSublocaType()))){
				inputVO.setSubLocationId(inputVO.getLocationId());
			}else{
				inputVO.setSubLocationId(0L);
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
	 	      					vo.setDistrictId(Long.valueOf(jObj.get("district").toString()));
				 	    		vo.setDistrictName(jObj.get("districtname").toString());
	 	      				}else if(inputVO.getSublocaType() != null && inputVO.getSublocaType().trim().equalsIgnoreCase("assembly")){
	 	      					vo.setDistrictId(Long.valueOf(jObj.get("district").toString()));
				 	    		vo.setDistrictName(jObj.getString("districtname").toString());
			 	    			vo.setConstituencyId(Long.valueOf(jObj.get("assemblyid").toString()));
			 	    			vo.setConstituencyName(jObj.get("assemblyname").toString());
			 	    		}else if(inputVO.getSublocaType() != null &&  inputVO.getSublocaType().trim().equalsIgnoreCase("mandal")){
			 	    			vo.setMandalId(Long.valueOf(jObj.get("id").toString()));
			 	    			vo.setMandalName(jObj.get("name").toString());
			 	    			vo.setDistrictId(Long.valueOf(jObj.get("districtId").toString()));
				 	    		vo.setDistrictName(jObj.get("district").toString());
				 	    		vo.setConstituencyId(Long.valueOf(jObj.get("assemblyId").toString()));
				 	    		vo.setConstituencyName(jObj.get("assemblyName").toString());
				 	    		vo.setParliamentId(Long.valueOf(jObj.get("costituencyId").toString()));
				 	    		vo.setParliamentName(jObj.get("constituencyName").toString());
			 	    		}
			 	    		vo.setTotalPanchayats(Long.valueOf(jObj.get("ePanchayats").toString()));
			 	    		vo.setExceptedMeetings(Long.valueOf(jObj.get("expectedMeetings").toString()));
			 	    		vo.setTotalMeetings(Long.valueOf(jObj.get("totalMeetings").toString()));
			 	    		vo.setConductedMeetings(Long.valueOf(jObj.get("conducted").toString()));
			 	    		vo.setGeneralMeetings(Long.valueOf(jObj.get("generalMeetings").toString()));
			 	    		vo.setEmergencyMeetings(Long.valueOf(jObj.get("emergencyMeetings").toString()));
			 	    		vo.setReqMeetings(Long.valueOf(jObj.get("requestMeetings").toString()));
			 	    		vo.setApprovedAgendaPts(Long.valueOf(jObj.get("approved").toString()));
			 	    		vo.setNotApprovedAgendaPts(Long.valueOf(jObj.get("declined").toString()));
			 	    		vo.setTotalAgendaPts(vo.getApprovedAgendaPts()+vo.getNotApprovedAgendaPts());
			 	    		vo.setBeyond60Days(Long.valueOf(jObj.get("beyond60Days").toString()));
			 	    		vo.setBeyond90Days(Long.valueOf(jObj.get("beyond90Days").toString()));
			 	    		vo.setBeyond120Days(Long.valueOf(jObj.get("beyond120Days").toString()));
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
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/meetings/apiq.php?getmeetingDataFilter=1&locationType="+inputVO.getLocationType()+"&locationId="+inputVO.getLocationId()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject dataObj = new JSONObject(output);
	 	    		JSONArray finalArray = new JSONArray(dataObj.get("meetingsData").toString());
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	      			for(int i=0;i<finalArray.length();i++){
	 	      				EMeetingsVO vo = new EMeetingsVO();
	 	      				JSONObject jObj = (JSONObject) finalArray.get(i);	
	 	      				vo.setPanchayatId(Long.valueOf(jObj.get("panchayat_id").toString()));
	 	      				vo.setPanchayatName(jObj.get("panchayat").toString());
	 	      				vo.setMeetingId(Long.valueOf(jObj.get("meetingId").toString()));
	 	      				vo.setMeetingName(jObj.get("meetingName").toString());
	 	      				vo.setMeetingDate(jObj.get("conductedDate").toString());
	 	      				vo.setTotalMembers(Long.valueOf(jObj.get("panchayatMembers").toString()));
	 	      				vo.setAttendedMembers(Long.valueOf(jObj.get("attendedMembers").toString()));
	 	      				vo.setAbsentMembers(Long.valueOf(jObj.get("absentMembers").toString()));
	 	      				vo.setMinutesOfMeeting(jObj.get("mom").toString());
	 	      				finalVO.getSubList().add(vo);
	 	      			}
	 	      		}
	 	    		finalVO.setTotalPanchayats(Long.valueOf(dataObj.get("totalPanchayats").toString()));
	 	    		finalVO.setConductedPanchayats(Long.valueOf(dataObj.get("ePanchayats").toString()));
	 	    		finalVO.setNotConductedPanchayts(Long.valueOf(dataObj.get("not_ePanchayats").toString()));
	 	    		finalVO.setTotalMeetings(Long.valueOf(dataObj.get("totalMeetings").toString()));
	 	    		finalVO.setConductedMeetings(Long.valueOf(dataObj.get("conducted").toString()));
	 	    		finalVO.setNotConductedMeetings(Long.valueOf(dataObj.get("notConducted").toString()));
	 	    		finalVO.setTotalAgendaPts(Long.valueOf(dataObj.get("totalAgenda").toString()));
	 	    		finalVO.setApprovedAgendaPts(Long.valueOf(dataObj.get("approvedAgenda").toString()));
	 	    		finalVO.setNotApprovedAgendaPts(Long.valueOf(dataObj.get("declinedAgenda").toString()));
	 	    		finalVO.setGeneralMeetings(Long.valueOf(dataObj.get("generalMeetings").toString()));
	 	    		finalVO.setEmergencyMeetings(Long.valueOf(dataObj.get("emergencyMeetings").toString()));
	 	    		finalVO.setReqMeetings(Long.valueOf(dataObj.get("requestMeetings").toString()));
	 	    		finalVO.setBeyond60Days(Long.valueOf(dataObj.get("beyond60Days").toString()));
	 	    		finalVO.setBeyond90Days(Long.valueOf(dataObj.get("beyond90Days").toString()));
	 	    		finalVO.setBeyond120Days(Long.valueOf(dataObj.get("beyond120Days").toString()));
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
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://pris.ap.gov.in/api/meetings/apiq.php?meetingOverview=1&meeting_id="+inputVO.getDeptId()+"");
	        ClientResponse response = webResource.get(ClientResponse.class);
				
        	if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject dataObj = new JSONObject(output);
	 	    		JSONArray itemObject = new JSONArray(dataObj.get("item").toString());
	 	    		JSONObject memberObject = new JSONObject(itemObject.getString(0));
	 	    		JSONArray finalArray = new JSONArray(memberObject.get("members").toString());
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	      			for(int i=0;i<finalArray.length();i++){
	 	      				EMeetingsVO vo = new EMeetingsVO();
	 	      				JSONObject jObj = (JSONObject) finalArray.get(i);	
	 	      				vo.setMemberId(Long.valueOf(jObj.get("memberId").toString()));
	 	      				vo.setMemberName(jObj.get("memberName").toString());
	 	      				vo.setDesignation(jObj.get("designation").toString());
	 	      				vo.setMobileNo(jObj.get("mobile").toString());
	 	      				vo.setInvitationStatus(jObj.get("invitationStatus").toString());
	 	      				vo.setAttendance(jObj.get("attendance").toString());
	 	      				vo.setVote(jObj.get("vote").toString());
	 	      				vo.setRemark(jObj.get("remarks").toString());
	 	      				finalVO.getSubList().add(vo);
	 	      			}
	 	      		}
	 	    		finalVO.setMeetingType(dataObj.get("meeting_type").toString());
	 	    		finalVO.setMeetingDate(dataObj.get("meetingDate").toString());
	 	    		finalVO.setTotalMembers(Long.valueOf(dataObj.get("totalMembers").toString()));
	 	    		finalVO.setAttendedMembers(Long.valueOf(dataObj.get("attendedMembers").toString()));
	 	    		finalVO.setAbsentMembers(Long.valueOf(dataObj.get("absentMembers").toString()));
	 	    		finalVO.setMinutesOfMeeting(dataObj.get("minutesOfMom").toString());
	 	    		finalVO.setMeetingDuration(dataObj.get("meetingDuration").toString());
	 	    		finalVO.setTotalAgendaPts(Long.valueOf(dataObj.get("totalAgendas").toString()));
	 	    		finalVO.setApprovedAgendaPts(Long.valueOf(dataObj.get("approvedAgenda").toString()));
	 	    		finalVO.setNotApprovedAgendaPts(Long.valueOf(dataObj.get("declinedAgendas").toString()));
	 	    		
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception occured at getMeetingDetails() - EMeetingsDahboardService ",e);
		}
		return finalVO;
	}
	
}
