package com.itgrids.tpi.rws.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.itgrids.dto.InputVO;
import com.itgrids.model.EncWorks;
import com.itgrids.model.RwsWork;
import com.itgrids.model.RwsWorkLocation;
import com.itgrids.tpi.rws.service.IRwsWorksSchedulerService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;


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
	public String getWorksDataInsertion(InputVO inputVO) {
		try {
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
													if(jObj.has("groundingDate") && status.equalsIgnoreCase("ongoing")){
														 if(!jObj.getString("groundingDate").equalsIgnoreCase("--")){
															 work.setGroundedDate(sdf.parse(jObj.getString("groundingDate")));
														 }else{
															 work.setGroundedDate(null);
														 }
													}
													if(jObj.has("completionDate")){
														work.setCompletedDate(sff.parse(jObj.has("completionDate") ? jObj.getString("completionDate") : null));
													}
													if(jObj.has("commssionedDate")){
														work.setCommissionedDate(sff.parse(jObj.has("commssionedDate") ? jObj.getString("commssionedDate") : null));
													}
													if(jObj.has("stipulatedDate")){
														if(!jObj.getString("stipulatedDate").equalsIgnoreCase("--")){
															work.setStipulatedTargetDate(sdf.parse(jObj.has("stipulatedDate") ? jObj.getString("stipulatedDate") : null));
														}else{
															work.setStipulatedTargetDate(null);
														}
													}
													if(jObj.has("technicalSanctionedDate")){
														if(!jObj.getString("technicalSanctionedDate").equalsIgnoreCase("--")){
															work.setStipulatedTargetDate(sdf.parse(jObj.has("technicalSanctionedDate") ? jObj.getString("technicalSanctionedDate") : null));
														}else{
															work.setStipulatedTargetDate(null);
														}
														
													}
													if(jObj.has("entrustedDate")){
														if(!jObj.getString("entrustedDate").equalsIgnoreCase("--")){
															work.setStipulatedTargetDate(sdf.parse(jObj.has("entrustedDate") ? jObj.getString("entrustedDate") : null));
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
													work.setIsActive("Y");
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
			return "success";
		}catch(Exception e){
	 	    	 LOG.error("Exception Occured in getExceededWorkDetailsLocationWise() method, Exception - ",e);
	 	    	return "failure";
	 	    }
		
		
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
		 	    		List<String> workData= rwsWorkDAO.getWorkdetailsById("All");
		 	    		
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
				 	    			works.setAdminNo(removespecialcaharacter(jObj.getString("adminNo").trim()));
				 	    			works.setSanctionedAmount(jObj.getDouble("sanctionAmount"));
				 	    			
				 	    			works.setAssetType(jObj.getString("typeOfAssestName"));
				 	    			works.setAdminDate(sff.parse(jObj.getString("adminDate")));
				 	    			works.setGroundedDate(null);
				 	    			
				 	    			//String target = 
				 	    			calendar.setTimeInMillis(commonMethodsUtilService.getLongValueForObject(jObj.getLong("targetDateComp")));
				 	    			works.setTargetDate(calendar.getTime());
				 	    			
				 	    			works.setCompletedDate(null);
				 	    			works.setCommissionedDate(null);
				 	    			works.setIsActive("Y");
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
	
	public String removespecialcaharacter(String data){
		String dataString= data;
		Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
		Matcher match= pt.matcher(data);
		    while(match.find()){
		    	dataString=dataString.replace(Character.toString(dataString.charAt(match.start()))," ");
		         }
		return dataString;
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
			List<Long> workIds =encWorksDAO.getAllDistinctWorkIds();
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
								if(!workIds.contains(json.getLong("WORK_ID"))) {
									EncWorks work = new EncWorks();
									work.setWorkId(json.has("WORK_ID") ? json.getLong("WORK_ID"): 0l);
									work.setSchemeId(json.has("SCHEME")? json.getLong("SCHEME"):0l);
									if(json.has("AGREEMENT_AMOUNT") && json.getString("AGREEMENT_AMOUNT").trim().length()> 0){
										work.setAgreementAmount(json.has("AGREEMENT_AMOUNT")? json.getDouble("AGREEMENT_AMOUNT"):0.00);
									}else{
										work.setAgreementAmount(null);
									}
									//work.setTargetDate(json.has("TARGET_DATE")?  sdf.parse(json.getString("TARGET_DATE")):null);
									work.setDitrictName(json.has("DIST_NAME")? json.getString("DIST_NAME"):"");
									work.setHabName(json.has("HABS")? json.getString("HABS"):"");
									work.setAssemblyName(json.has("AC_NAME")? json.getString("AC_NAME"):"");
									work.setMandalId(json.has("MAND_CODE")? json.getLong("MAND_CODE"):0l);
									work.setParlimentId(json.has("PC_CODE")? json.getLong("PC_CODE"):0l);
									work.setParlimentName(json.has("PC_NAME")? json.getString("PC_NAME"):"");
									work.setWorkName(json.has("WORK_NAME")? json.getString("WORK_NAME"):"");
									//work.setAdmin(json.getString("ADMIN_SANC_AMOUNT"));
									work.setMandalName(json.has("MAND_NAME")? json.getString("MAND_NAME"):"");
									if(json.has("TECHSAN_AMOUNT") && json.getString("TECHSAN_AMOUNT").trim().length()> 0){
										work.setTechnicalsancAmount(json.has("TECHSAN_AMOUNT")? json.getLong("TECHSAN_AMOUNT"):0l);
									}else{
										work.setTechnicalsancAmount(null);
									}
									
									work.setSchemeName(json.has("SCHEME_NAME")? json.getString("SCHEME_NAME"):"");
									work.setDistrictId(json.has("DIST_CODE")? json.getLong( "DIST_CODE"):0l);
									work.setAssemblyId(json.has("AC_CODE")? json.getLong("AC_CODE"):0l);
									if(json.has("AGREEMENT_DATE") && json.getString("AGREEMENT_DATE").trim().length()>0){
										work.setAgrementDate(json.has("AGREEMENT_DATE")? sdf.parse(json.getString("AGREEMENT_DATE")):null);
									}else{
										work.setAgrementDate(null);
									}
									if(json.has("TARGET_DATE") && json.getString("TARGET_DATE").trim().length()>0){
										work.setTargetDate(json.has("TARGET_DATE")? sdf.parse(json.getString("TARGET_DATE")):null);							
									}else{
										work.setTargetDate(null);
									}
									if(json.has("ADMIN_SANC_DT") && json.getString("ADMIN_SANC_DT").trim().length()>0){
										work.setAdminSanctionDate(json.has("ADMIN_SANC_DT")? sdf.parse(json.getString("ADMIN_SANC_DT")):null);							
									}else{
										work.setAdminSanctionDate(null);
									}
									if(json.has("GROUND_DATE") && json.getString("GROUND_DATE").trim().length()>0){
										work.setGroundedDate(json.has("GROUND_DATE")? sdf.parse(json.getString("GROUND_DATE")):null);
									}else{
										work.setAgrementDate(null);
									}
									if(json.has("DT_COMPLETED") && json.getString("DT_COMPLETED").trim().length()>0){
										work.setCompletionDate(json.has("DT_COMPLETED")? sdf.parse(json.getString("DT_COMPLETED")):null);
									}else{
										work.setCompletionDate(null);
									}
									if(json.has("TECH_SANCTION_DATE") && json.getString("TECH_SANCTION_DATE").trim().length()>0){
										work.setTechSanctionDate(json.has("TECH_SANCTION_DATE")? sdf.parse(json.getString("TECH_SANCTION_DATE")): null);
									}else{
										work.setTechSanctionDate(null);
									}
									work = encWorksDAO.save(work);
								}else{
									EncWorks work =encWorksDAO.findOneByworkId((json.has("WORK_ID") ? json.getLong("WORK_ID"): 0l));
									if(json.has("AGREEMENT_DATE") && json.getString("AGREEMENT_DATE").trim().length()>0){
										work.setAgrementDate(json.has("AGREEMENT_DATE")? sdf.parse(json.getString("AGREEMENT_DATE")):null);
									}else{
										work.setAgrementDate(null);
									}
									if(json.has("ADMIN_SANC_DT") && json.getString("ADMIN_SANC_DT").trim().length()>0){
										work.setAdminSanctionDate(json.has("ADMIN_SANC_DT")? sdf.parse(json.getString("ADMIN_SANC_DT")):null);							
									}else{
										work.setAdminSanctionDate(null);
									}
									if(json.has("GROUND_DATE") && json.getString("GROUND_DATE").trim().length()>0){
										work.setGroundedDate(json.has("GROUND_DATE")? sdf.parse(json.getString("GROUND_DATE")):null);
									}else{
										work.setGroundedDate(null);
									}
									if(json.has("DT_COMPLETED") && json.getString("DT_COMPLETED").trim().length()>0){
										work.setCompletionDate(json.has("DT_COMPLETED")? sdf.parse(json.getString("DT_COMPLETED")):null);
									}else{
										work.setCompletionDate(null);
									}
									if(json.has("TECH_SANCTION_DATE") && json.getString("TECH_SANCTION_DATE").trim().length()>0){
										work.setTechSanctionDate(json.has("TECH_SANCTION_DATE")? sdf.parse(json.getString("TECH_SANCTION_DATE")): null);
									}else{
										work.setTechSanctionDate(null);
									}
									if(json.has("AGREEMENT_AMOUNT") && json.getString("AGREEMENT_AMOUNT").trim().length()> 0){
										work.setAgreementAmount(json.has("AGREEMENT_AMOUNT")? json.getDouble("AGREEMENT_AMOUNT"):0.0);
									}else{
										work.setAgreementAmount(null);
									}
									work = encWorksDAO.save(work);

								}
								
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

	@Override
	public String getWorksDataDeletion() {

		try{
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getOnclickWorkDetails");	  
			InputVO input = new InputVO();
			input.setFromDateStr("01-04-1977");
			input.setToDateStr("01-04-2028");
			input.setWorkStatus("ongoing");
		
			//ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class,jobj.toString());
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickWorkDetails");
			String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, input);
        
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	}else{
		 		 String output = response.getEntity(String.class);
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObj = new JSONObject(output);
	 	    		
	 	    		List<String> workIds= new ArrayList<String>();
	 	    		List<String> workData= rwsWorkDAO.getWorkdetailsById("active");
	 	    		if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
	 	    			
	 	    			JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");
	 	    			if(onClickWorksArray!=null && onClickWorksArray.length()>0){
	 	    				for(int i=0;i<onClickWorksArray.length();i++){
	 		 	    			JSONObject obj = onClickWorksArray.getJSONObject(i);
	 		 	    			workIds.add(obj.getString("workId"));
	 		 	    		}
	 	    			}
	 	    		}
	 	    		
	 	    		for (String workId : workData) {
						if(!workIds.contains(workId.trim())){
							RwsWork work =rwsWorkDAO.getWorkdetailsByIds(workId);
							work.setIsActive("N");
							rwsWorkDAO.save(work);
						}
					}
	 	    		
	 	    	 }
		 	}
			return "success";
		}catch(Exception e){
			LOG.error("Exception Occured in getEncworkDataInsertion() method, Exception - ",e);
			return "failue"+e.getMessage();
		}

	}	
	public String getAuthenticationString(String name,String password){
		try {			
	        String authString = name + ":" + password;
	        return new BASE64Encoder().encode(authString.getBytes());
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAuthenticationString - RWSNICService service", e);
		}
		return null;
	}


}
