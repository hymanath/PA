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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.itgrids.dao.IEncWorksDAO;
import com.itgrids.dao.IRwsWorkDAO;
import com.itgrids.dao.IRwsWorkLocationDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.EncWorks;
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
	
	@Autowired
	private ITehsilDAO tehsilDAO;
	
	@Autowired
	private IEncWorksDAO encWorksDAO;


	@Override
	public List<IdNameVO> getWorksDataInsertion(InputVO inputVO) {
		List<IdNameVO> resultList = new ArrayList<>(0);
		try {
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sf = new SimpleDateFormat("dd-mm-yyyy");
			SimpleDateFormat sff = new SimpleDateFormat("yyyy-mm-dd");
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
											if(work != null){
												if(work.getGroundedDate() == null || work.getCompletedDate() == null ||work.getCommissionedDate() == null || work.getStipulatedTargetDate() ==null){
													if(work.getGroundedDate() == null && jObj.has("groundingDate")){
														 if(!jObj.getString("groundingDate").equalsIgnoreCase("--")){
															 work.setGroundedDate(sdf.parse(jObj.has("groundingDate") ? jObj.getString("groundingDate") : null));
														 }else{
															 work.setGroundedDate(null);
														 }
													}
													if(work.getCompletedDate() == null && jObj.has("completionDate")){
														work.setCompletedDate(sff.parse(jObj.has("completionDate") ? jObj.getString("completionDate") : null));
													}
													if(work.getCommissionedDate() == null && jObj.has("commssionedDate")){
														work.setCommissionedDate(sff.parse(jObj.has("commssionedDate") ? jObj.getString("commssionedDate") : null));
													}
													if(work.getStipulatedTargetDate() == null && jObj.has("stipulatedDate")){
														if(!jObj.getString("stipulatedDate").equalsIgnoreCase("--")){
															work.setStipulatedTargetDate(sdf.parse(jObj.has("stipulatedDate") ? jObj.getString("stipulatedDate") : null));
														}else{
															work.setStipulatedTargetDate(null);
														}
													}
													if(work.getCommissionedDate() == null && work.getCompletedDate() == null && work.getGroundedDate()!=null ){
														work.setWorkStatus("Grounded");
													}else if(work.getCommissionedDate() != null && work.getCompletedDate() != null && work.getGroundedDate()!= null ){
														work.setWorkStatus("Commissioned");
													}else if(work.getCommissionedDate() == null && work.getCompletedDate() != null && work.getGroundedDate()!= null ){
														work.setWorkStatus("Completed");
													}else if(work.getCommissionedDate() == null && work.getCompletedDate() == null && work.getGroundedDate() ==null){
														work.setWorkStatus("Not Grounded");
													}
													rwsWorkDAO.save(work);
												}
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

	public boolean getWorkDetails() {
		 try {
			 	DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				DateFormat sff = new SimpleDateFormat("yyyy-MM-dd");
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
				 	    			works.setWorkName(name);
				 	    			works.setWorkStatus("Not Grounded");
				 	    			works.setProgramCode(jObj.getString("programCode"));
				 	    			works.setProgramName(jObj.getString("programName"));
				 	    			works.setAdminNo(jObj.getString("adminNo"));
				 	    			works.setSanctionedAmount(jObj.getDouble("sanctionAmount"));
				 	    			
				 	    			works.setAssetType(jObj.getString("typeOfAssestName"));
				 	    			works.setAdminDate(sff.parse(jObj.getString("adminDate")));
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
	
	public boolean getWorkDetails2() {
		 try {
			 	DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				DateFormat sff = new SimpleDateFormat("yyyy-MM-dd");
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
				 	    			works.setWorkName(name);
				 	    			works.setWorkStatus("Not Grounded");
				 	    			works.setProgramCode(jObj.getString("programCode"));
				 	    			works.setProgramName(jObj.getString("programName"));
				 	    			works.setAdminNo(jObj.getString("adminNo"));
				 	    			works.setSanctionedAmount(jObj.getDouble("sanctionAmount"));
				 	    			
				 	    			works.setAssetType(jObj.getString("typeOfAssestName"));
				 	    			works.setAdminDate(sff.parse(jObj.getString("adminDate")));
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

	@Override
	public String getEncworkDataInsertion() {
		
		try{
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			List<Object[]> locationData= tehsilDAO.getEncMandals();
			for (Object[] objects : locationData) {
				JsonObject object = new JsonObject();
				JsonArray jsonarray = new JsonArray();
				object.addProperty("MAND_CODE", commonMethodsUtilService.getLongValueForObject(objects[0]).toString());
				jsonarray.add(object);
				WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://predmis.ap.nic.in/RestWS/PredmisRoadService/WorkDetails");	     
				ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,jsonarray.toString());
				
				if(response.getStatus() != 200){
			 	   	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	}else{
			 		String output = response.getEntity(String.class);

					if (output != null && !output.isEmpty()) {
						JSONArray resultArray= new JSONArray(output);
						JSONObject Obj = resultArray.getJSONObject(0);
						JSONArray array=null;
						array = Obj.has("result") ? Obj.getJSONArray("result"): null;
						if(array !=null && array.length()> 0){
							for (int i = 0; i < array.length(); i++) {
								JSONObject json = array.getJSONObject(i);
								EncWorks work = new EncWorks();
								work.setWorkId(json.has("WORK_ID") ? json.getLong("WORK_ID"): 0l);
								work.setSchemeId(json.has("SCHEME")? json.getLong("SCHEME"):0l);
								work.setAgreementAmount(json.has("AGREEMENT_AMOUNT")? json.getLong("AGREEMENT_AMOUNT"):0l);
								work.setTargetDate(json.has("TARGET_DATE")?  sdf.parse(json.getString("TARGET_DATE")):null);
								work.setDitrictName(json.has("DIST_NAME")? json.getString("DIST_NAME"):"");
								work.setAgrementDate(json.has("AGREEMENT_DATE")? sdf.parse(json.getString("AGREEMENT_DATE")):null);
								work.setHabName(json.has("HABS")? json.getString("HABS"):"");
								work.setAssemblyName(json.has("AC_NAME")? json.getString("AC_NAME"):"");
								work.setMandalId(json.has("MAND_CODE")? json.getLong("MAND_CODE"):0l);
								work.setParlimentId(json.has("PC_CODE")? json.getLong("PC_CODE"):0l);
								work.setParlimentName(json.has("PC_NAME")? json.getString("PC_NAME"):"");
								work.setAdminSanctionDate(json.has("ADMIN_SANC_DT")? sdf.parse(json.getString("ADMIN_SANC_DT")):null);
								work.setWorkName(json.has("WORK_NAME")? json.getString("WORK_NAME"):"");
								//work.setAdmin(json.getString("ADMIN_SANC_AMOUNT"));
								work.setMandalName(json.has("MAND_NAME")? json.getString("MAND_NAME"):"");
								work.setTechnicalsancAmount(json.has("TECHSAN_AMOUNT")? json.getLong("TECHSAN_AMOUNT"):0l);
								work.setSchemeName(json.has("SCHEME_NAME")? json.getString("SCHEME_NAME"):"");
								work.setDistrictId(json.has("DIST_CODE")? json.getLong( "DIST_CODE"):0l);
								work.setAssemblyId(json.has("AC_CODE")? json.getLong("AC_CODE"):0l);
								work.setGroundedDate(json.has("GROUND_DATE")? sdf.parse(json.getString("GROUND_DATE")):null);
								work.setCompletionDate(json.has("DT_COMPLETED")? sdf.parse(json.getString("DT_COMPLETED")):null);
								work.setTechSanctionDate(json.has("TECH_SANCTION_DATE")? sdf.parse(json.getString("TECH_SANCTION_DATE")): null);
								work = encWorksDAO.save(work);
								LOG.error(work.getEncWorkId());
							}
						}
					}
			 	}
			}
			return "Data inserted SuccessFully";
		}catch(Exception e){
			LOG.error("Exception Occured in getEncworkDataInsertion() method, Exception - ",e);
			return e.getMessage();
		}
	
	}	

}
