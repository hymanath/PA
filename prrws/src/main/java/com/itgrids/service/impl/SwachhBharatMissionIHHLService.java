package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SwachhBharatMissionIHHLDtlsVO;
import com.itgrids.service.ISwachhBharatMissionIHHLService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class SwachhBharatMissionIHHLService implements ISwachhBharatMissionIHHLService{

	private static final Logger LOG = Logger.getLogger(SwachhBharatMissionIHHLService.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO 
	 * @description {This service is used to get Swachh Bharat Mission overview details.}
	 * @return SwachhBharatMissionIHHLDtlsVO
	 * @Date 14-10-2017
	 */
	public SwachhBharatMissionIHHLDtlsVO getSwachhBharatMissionOverviewDtls(InputVO inputVO) {
		SwachhBharatMissionIHHLDtlsVO resultVO = new SwachhBharatMissionIHHLDtlsVO();
		try {
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/IHHLDashBoardUI/GetIHHLDashBoardUIDetails", str);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray locationDtlsArr = jsonObject.getJSONArray("MinisterDBStateFundMngmntData");
	 	    		if(locationDtlsArr != null && locationDtlsArr.length() > 0){
	 	    			for(int i=0;i<locationDtlsArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) locationDtlsArr.get(i);
	 	    				resultVO.setTarget(jObj.has("TARGET") ? jObj.getLong("TARGET"):0l);
	 	    				resultVO.setGrounded(jObj.has("GROUNDED") ? jObj.getLong("GROUNDED"):0l);
	 	    				resultVO.setNoTGrounded(jObj.has("NOTGROUNDED") ? jObj.getLong("NOTGROUNDED"):0l);
	 	    				resultVO.setInProgress(jObj.has("INPROGRESS") ? jObj.getLong("INPROGRESS"):0l);
	 	    				resultVO.setCompleted(jObj.has("COMPLETEED") ? jObj.getLong("COMPLETEED"):0l);
	 	    			}	
	 	    		}
	 	    		JSONArray categoryWiseDataArr = jsonObject.getJSONArray("categorycount");
	 	    		if(categoryWiseDataArr != null && categoryWiseDataArr.length() > 0){
	 	    			List<SwachhBharatMissionIHHLDtlsVO> categoryList = new ArrayList<>();
	 	    			for(int i=0;i<categoryWiseDataArr.length();i++){
	 	    				SwachhBharatMissionIHHLDtlsVO categoryVO = new SwachhBharatMissionIHHLDtlsVO();
	 	    				JSONObject jObj = (JSONObject) categoryWiseDataArr.get(i);
	 	    				categoryVO.setRange(jObj.has("CAT") ? jObj.getString("CAT"):"");
	 	    				String[] strArr = getRankIdBasedOnCategory(categoryVO.getRange());
	 	    				if (strArr.length > 0) {
	 	    					categoryVO.setId(Long.valueOf(strArr[0]));
	 	    					categoryVO.setName(strArr[1]);
	 	    				}
	 	    				categoryVO.setDistrictCount(jObj.has("D") ? jObj.getLong("D"):0l);
	 	    				categoryVO.setConstituencyCount(jObj.has("C") ? jObj.getLong("C"):0l);
	 	    				categoryVO.setMandalCount(jObj.has("M") ? jObj.getLong("M"):0l);
	 	    				categoryList.add(categoryVO);
	 	    			}	
	 	    			if (categoryList.size() > 0) {
	 	    				Collections.sort(categoryList, sortListBasedOnId);
	 	    				resultVO.setSubList(categoryList);
	 	    			}
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception occured at getSwachhBharatMissionOverviewDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultVO;
	}
	public static Comparator<SwachhBharatMissionIHHLDtlsVO> sortListBasedOnId = new Comparator<SwachhBharatMissionIHHLDtlsVO>() {
		public int compare(SwachhBharatMissionIHHLDtlsVO o1, SwachhBharatMissionIHHLDtlsVO o2) {
			if(o1.getId() != null && o2.getId() != null){
				return o1.getId().compareTo(o2.getId());
			}
			return 0;
		}
	};
	private String[] getRankIdBasedOnCategory(String category) {
		String[] strArr = new String[2];
		 try {
			 
			 if (category != null && category.trim().length() > 0) {
				 if(category.equalsIgnoreCase("100 TO 76")){
					 strArr[0] = "1";
					 strArr[1] = "A";
				 } else if (category.equalsIgnoreCase("75 TO 51")) {
					 strArr[0] = "2";
					 strArr[1] = "B";
				 }else if (category.equalsIgnoreCase("50 TO 26")) {
					 strArr[0] = "3";
					 strArr[1] = "C";
				 }else if (category.equalsIgnoreCase("25 TO 0")){
					 strArr[0] = "4";
					 strArr[1] = "D";
				 } else {
					 strArr[0] = "5";
					 strArr[1] = "E";
				 }
			 }
		 } catch (Exception e) {
			 LOG.error("Exception occured at getRankIdBasedOnCategory() in SwachhBharatMissionIHHLService class",e); 
		 }
		 return strArr;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Swachh Bharat Mission Status details.}
	 * @return SwachhBharatMissionIHHLDtlsVO
	 * @Date 14-10-2017
	 */
	public SwachhBharatMissionIHHLDtlsVO getSwachhBharatMissionStatusOverviewDtls(InputVO inputVO) {
		SwachhBharatMissionIHHLDtlsVO resultVO = new SwachhBharatMissionIHHLDtlsVO();
		try {
			resultVO.setTarget(10000l);
			resultVO.setGrounded(2000l);
			resultVO.setNoTGrounded(8000l);
			resultVO.setInProgress(1000l);
			resultVO.setCompleted(1000l);
		} catch (Exception e) {
			LOG.error("Exception occured at getSwachhBharatMissionStatusDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Swachh Bharat Mission category wise analysis details.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLCategoryWiseAnalysis(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			SwachhBharatMissionIHHLDtlsVO vo = new SwachhBharatMissionIHHLDtlsVO();
			vo.setRange("100% TO 76%");
			vo.setDistrictCount(10l);
			vo.setConstituencyCount(5l);
			vo.setMandalCount(2l);
			SwachhBharatMissionIHHLDtlsVO vo1 = new SwachhBharatMissionIHHLDtlsVO();
			vo1.setRange("75% TO 51%");
			vo1.setDistrictCount(10l);
			vo1.setConstituencyCount(5l);
			vo1.setMandalCount(2l);
			resultList.add(vo);
			resultList.add(vo1);
		} catch (Exception e) {
			LOG.error("Exception occured at getIHHLCategoryWiseAnalysis() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get  Swachh Bharat Mission IHHL Achivement progress details.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLAchivementProgressDtls(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			SwachhBharatMissionIHHLDtlsVO timeRangeVO = new SwachhBharatMissionIHHLDtlsVO();
			timeRangeVO.setRange("01-08-2017");
			timeRangeVO.setTarget(10000l);
			timeRangeVO.setCompleted(1000l);
			SwachhBharatMissionIHHLDtlsVO timeRangeVO1 = new SwachhBharatMissionIHHLDtlsVO();
			timeRangeVO1.setRange("02-08-2017");
			timeRangeVO1.setTarget(10000l);
			timeRangeVO1.setCompleted(2000l);
			SwachhBharatMissionIHHLDtlsVO timeRangeVO2 = new SwachhBharatMissionIHHLDtlsVO();
			timeRangeVO2.setRange("03-08-2017");
			timeRangeVO2.setTarget(10000l);
			timeRangeVO2.setCompleted(3000l);
			resultList.add(timeRangeVO);
			resultList.add(timeRangeVO1);
			resultList.add(timeRangeVO2);
		} catch (Exception e) {
			LOG.error("Exception occured at getIHHLAchivementProgressDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Swachh Bharat Mission details location wise.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getSwachhBharatMissionLocationWiseDetails(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			SwachhBharatMissionIHHLDtlsVO locationVO = new SwachhBharatMissionIHHLDtlsVO();
			locationVO.setLocationId(1l);
			locationVO.setName("Andhra Pradesh");
			locationVO.setTarget(10000l);
			locationVO.setGrounded(2000l);
			locationVO.setNoTGrounded(8000l);
			locationVO.setInProgress(1000l);
			locationVO.setCompleted(1000l);
			locationVO.setPercentage(50d);
			resultList.add(locationVO);
		} catch (Exception e) {
			LOG.error("Exception occured at getSwachhBharatMissionLocationWiseDetails() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	public String convertingInputVOToString(InputVO inputVO){
		String str = "";
		try {
			str = "{";
			
			if(inputVO.getFromDate() != null )
			/*	str += "\"fromMonth\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"toMonth\" : \""+inputVO.getToDate()+"\",";*/
			if(inputVO.getLocation() != null)
				str += "\"Location\" : \""+inputVO.getLocation()+"\",";
			/*if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";else*/
			 if(inputVO.getLocationId() != null)
				str += "\"LocationID\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSubLocation() != null)
				str += "\"SubLocation\" : \""+inputVO.getSubLocation()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception Occured in convertingInputVOToString() method, Exception - ",e);
		}
		return str;
	}
}
