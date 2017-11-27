package com.itgrids.tpi.rws.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IRwsWorkDAO;
import com.itgrids.dao.IRwsWorkLocationDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.RwsWork;
import com.itgrids.model.RwsWorkLocation;
import com.itgrids.tpi.rws.service.IRwsWorksSchedulerService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@Service
@Transactional
public class RwsWorksSchedulerService implements IRwsWorksSchedulerService {
	private static final Logger LOG = Logger.getLogger(RwsWorksSchedulerService.class);
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;

	@Autowired
	private IRwsWorkDAO rwsWorkDAO;
	
	@Autowired
	private IRwsWorkLocationDAO rwsWorkLocationDAO;

	@Override
	public List<IdNameVO> getWorksDataInsertion(InputVO inputVO) {
		List<IdNameVO> resultList = new ArrayList<>(0);
		try {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
			boolean insertionFlag = getWorkDetails();// getting work completed,dateofTarget date
			if (insertionFlag) {
				List<String> statusList = inputVO.getStatusList();
				if (statusList != null && statusList.size() > 0) {
					String authStringEnc = commonMethodsUtilService.getAuthenticationString("itgrids","Itgrids@123");
					WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickWorkDetails");	  
					for (String status : statusList) {
							InputVO input = prepareRequiredInputDetails(status,inputVO);
							ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class,input);

						if (response.getStatus() != 200) {
							throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
						} else {
							String output = response.getEntity(String.class);
							if (output != null && !output.isEmpty()) {
								JSONObject jsonObj = new JSONObject(output);
								String responsestatus = jsonObj.has("status") ? jsonObj.getString("status") : "";
								if (responsestatus.equalsIgnoreCase("Success")) {
									JSONArray finalArray = jsonObj.has("onClickWorksList") ? jsonObj.getJSONArray("onClickWorksList") : null;
									if (finalArray != null && finalArray.length() > 0) {
										RwsWorkLocation location = null; 
										for (int i = 0; i < finalArray.length(); i++) {
											location = new RwsWorkLocation();
											JSONObject jObj = (JSONObject) finalArray.get(i);
											
											RwsWork work =rwsWorkDAO.getWorkdetailsByIds(jObj.getString("workId"));
											if(work != null && (work.getGroundedDate() == null || work.getCompletedDate() == null ||work.getCommissionedDate() == null)){
												if(work.getGroundedDate() == null && jObj.has("groundingDate") && !jObj.getString("groundingDate").equalsIgnoreCase("--")){
													work.setGroundedDate(sdf.parse(jObj.has("groundingDate") ? jObj.getString("groundingDate") : null));
												}
												if(work.getCompletedDate() == null && jObj.has("completionDate")){
													work.setCompletedDate(sdf.parse(jObj.has("completionDate") ? jObj.getString("completionDate") : null));
												}
												if(work.getCommissionedDate() == null && jObj.has("commssionedDate")){
													work.setCommissionedDate(sdf.parse(jObj.has("commssionedDate") ? jObj.getString("commssionedDate") : null));
												}
												if(work.getCommissionedDate() == null && work.getCompletedDate() == null && work.getGroundedDate()!=null ){
													work.setWorkStatus("Grounded");
												}else if(work.getCommissionedDate() != null && work.getCompletedDate() == null && work.getGroundedDate()!= null ){
													work.setWorkStatus("Commissioned");
												}else if(work.getCommissionedDate() == null && work.getCompletedDate() != null && work.getGroundedDate()!= null ){
													work.setWorkStatus("Completed");
												}

												rwsWorkDAO.save(work);
												RwsWorkLocation workLocation =rwsWorkLocationDAO.getWorkdetailsByHabAndId(work.getRwsWorkId(),jObj.getString("habitationCode"));
											
												if(workLocation == null){
													
													location.setRwsWorkId(work.getRwsWorkId());
													location.setMandalCode(jObj.has("mandalCode") ? jObj.getString("mandalCode") : null);
													location.setConstituencyCode(jObj.has("constituencyCode") ? jObj.getString("constituencyCode") : null);
													location.setConstituencyName(jObj.has("constituencyName") ? jObj.getString("constituencyName") : null);
													location.setDistrictCode(jObj.has("districtCode") ? jObj.getString("districtCode"): null);
													location.setDistrictName(jObj.has("districtName") ? jObj.getString("districtName"): null);
													location.setMandalName(jObj.has("mandalName") ? jObj.getString("mandalName") : null);
													location.setHabitationCode(jObj.has("habitationCode") ? jObj.getString("habitationCode") : null);
													location.setHabitationName(jObj.has("habitationName") ? jObj.getString("habitationName") : null);
													rwsWorkLocationDAO.save(location);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
	 	    	 LOG.error("Exception Occured in getExceededWorkDetailsLocationWise() method, Exception - ",e);
	 	    }
		return resultList;
		
	}

	private boolean getWorkDetails() {
		 try {
			 	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 	Calendar calendar = Calendar.getInstance();
			    WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getAllWorkAdminDetails");	        
				String authStringEnc = commonMethodsUtilService.getAuthenticationString("itgrids","Itgrids@123");	        
				ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class);
				
				if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	    }else{
		 	    	 String output = response.getEntity(String.class);
		 	    	 if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		RwsWork works = null;
		 	    		List<String> workData= rwsWorkDAO.getWorkdetailsById();
		 	    		for(int i=0;i<finalArray.length();i++){
		 	    			works = new RwsWork();
		 	    			JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    			if(jObj.getString("typeOfAssestName").equalsIgnoreCase("PWS") || jObj.getString("typeOfAssestName").equalsIgnoreCase("CPWS")){
		 	    				if(!workData.contains(jObj.getString("workId"))) {
				 	    			works.setWorkId(jObj.getString("workId"));
				 	    			String name =jObj.getString("workName").replace("\u0096", "");
				 	    			LOG.error(jObj.getString("workName"));
				 	    			works.setWorkName(name);
				 	    			works.setWorkStatus("Not Grounded");
				 	    			works.setProgramCode(jObj.getString("programCode"));
				 	    			works.setProgramName(jObj.getString("programName"));
				 	    			works.setAdminNo(jObj.getString("adminNo"));
				 	    			works.setSanctionedAmount(jObj.getDouble("sanctionAmount"));
				 	    			
				 	    			works.setAssetType(jObj.getString("typeOfAssestName"));
				 	    			works.setAdminDate(sdf.parse(jObj.getString("adminDate")));
				 	    			works.setGroundedDate(null);
				 	    			
				 	    			//String target = 
				 	    			calendar.setTimeInMillis(commonMethodsUtilService.getLongValueForObject(jObj.getLong("targetDateComp")));
				 	    			works.setTargetDate(calendar.getTime());
				 	    			
				 	    			works.setCompletedDate(null);
				 	    			works.setCommissionedDate(null);
				 	    			
				 	    			works = rwsWorkDAO.save(works);
		 	    				}
		 	    			}
		 	    		}
		 	    	 }
		 	  }  
				return true;
		 } catch (Exception e) {
			 LOG.error("Exception Occured in getWorkDetails() method, Exception - ",e);
			 return false;
		 }
	}
	
	
	private InputVO prepareRequiredInputDetails(String workStatus,InputVO inputVO){
		InputVO input = new InputVO();
		try {
			input.setDistrictValue(inputVO.getDistrictValue());
			input.setFilterType(inputVO.getFilterType());
			input.setFilterValue(inputVO.getFilterValue());
			input.setFromDateStr(inputVO.getFromDateStr());
			input.setToDateStr(inputVO.getToDateStr());
			input.setWorkStatus(workStatus);
			input.setYear(inputVO.getYear());
		} catch (Exception e) {
			LOG.error("Exception Occured in prepareRequiredInputDetails() method, Exception - ",e);
		}
		return input;
	}	

}
