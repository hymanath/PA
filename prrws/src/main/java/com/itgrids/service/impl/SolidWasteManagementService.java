package com.itgrids.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IWebServiceDataDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.SolidWasteManagementVO;
import com.itgrids.dto.WebServiceDataVO;
import com.itgrids.model.WebServiceData;
import com.itgrids.service.ISolidWasteManagementService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
@Transactional
public class SolidWasteManagementService implements ISolidWasteManagementService {

	private static final Logger LOG = Logger.getLogger(SolidWasteManagementService.class);

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private IWebServiceDataDAO webServiceDataDAO;

	/*
	 * Date : 7/11/2017
	 */
	@Override
	public List<SolidWasteManagementVO> getSolidInfoLocationWise(InputVO inputVO) throws ParseException {
		List<SolidWasteManagementVO> finalList = new ArrayList<SolidWasteManagementVO>(0);

		try {
			WebResource webResource = null;
			String url = null;
			Map<String, SolidWasteManagementVO> locationMap = new HashMap<String, SolidWasteManagementVO>();
			if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal"))
				url = "http://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="
						+ inputVO.getLocationId() + "&locationType=" + inputVO.getLocationType() + "&filterType="
						+ inputVO.getFilterType() + "&filterId=" + inputVO.getFilterId()
						+ "&subFilterType=assembly&subFilterId=" + inputVO.getSubFilterId() +"&fromDate=1-06-2017&toDate=" + inputVO.getToDate();
			else if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("constituency"))
				url = "http://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="
						+ inputVO.getLocationId() + "&locationType=assembly&filterType=" + inputVO.getFilterType()
						+ "&filterId=" + inputVO.getFilterId() + "&fromDate=1-06-2017&toDate="
						+ inputVO.getToDate();
			else if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("district"))
				url = "http://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="
						+ inputVO.getLocationId() + "&locationType=" + inputVO.getLocationType() +"&fromDate=1-06-2017&toDate=" + inputVO.getToDate();

			webResource = commonMethodsUtilService.getWebResourceObject(url.toString());
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					output = output.replaceAll("<br />", "");
					output = output.replaceAll("<b>Warning</b>:  Division by zero in <b>/var/www/html/survey/api/swmapi.php</b> on line <b>326</b>", "");
					output = output.replaceAll("<br />", "");
					output = output.replaceAll("<b>Warning</b>:  Division by zero in <b>/var/www/html/survey/api/swmapi.php</b> on line <b>403</b>", "");
					
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							JSONObject jObj = (JSONObject) finalArray.get(i);
							SolidWasteManagementVO solidWasteManagementVO = new SolidWasteManagementVO();
							solidWasteManagementVO
									.setId(!jObj.getString("id").equalsIgnoreCase("null") ? jObj.getString("id") : "");
							solidWasteManagementVO.setName(
									!jObj.getString("name").equalsIgnoreCase("null") ? jObj.getString("name") : "");
							solidWasteManagementVO.setRfidTags(!jObj.getString("rfid_tags").equalsIgnoreCase("null")
									? jObj.getLong("rfid_tags") : 0l);
							solidWasteManagementVO.setFarmers(
									!jObj.getString("farmers").equalsIgnoreCase("null") ? jObj.getLong("farmers") : 0l);
							solidWasteManagementVO
									.setSwmCollection(!jObj.getString("swmCollection").equalsIgnoreCase("null")
											? jObj.getDouble("swmCollection") : 0.00);
							solidWasteManagementVO.setNadap(
									!jObj.getString("nadap").equalsIgnoreCase("null") ? jObj.getDouble("nadap") : 0.00);
							solidWasteManagementVO.setVermi(
									!jObj.getString("vermi").equalsIgnoreCase("null") ? jObj.getDouble("vermi") : 0.00);
							solidWasteManagementVO.setVermiStock(!jObj.getString("vermiStock").equalsIgnoreCase("null")
									? jObj.getDouble("vermiStock") : 0.00);
							solidWasteManagementVO
									.setHouseCollecion(!jObj.getString("houseCollecion").equalsIgnoreCase("null")
											? jObj.getDouble("houseCollecion") : 0.00);
							solidWasteManagementVO
									.setFarmerCollection(!jObj.getString("farmerCollection").equalsIgnoreCase("null")
											? jObj.getDouble("farmerCollection") : 0.00);
							solidWasteManagementVO.setMgnres(
									!jObj.getString("mgnres").equalsIgnoreCase("null") ? jObj.getLong("mgnres") : 0l);
							solidWasteManagementVO
									.setPr(!jObj.getString("pr").equalsIgnoreCase("null") ? jObj.getLong("pr") : 0l);
							solidWasteManagementVO.setPublicType(
									!jObj.getString("public").equalsIgnoreCase("null") ? jObj.getLong("public") : 0l);
							solidWasteManagementVO.setOnekg(
									!jObj.getString("1kg").equalsIgnoreCase("null") ? jObj.getDouble("1kg") : 0.00);
							solidWasteManagementVO.setFivekg(
									!jObj.getString("5kg").equalsIgnoreCase("null") ? jObj.getDouble("5kg") : 0.00);
							solidWasteManagementVO.setTenkg(
									!jObj.getString("10kg").equalsIgnoreCase("null") ? jObj.getDouble("10kg") : 0.00);
							solidWasteManagementVO.setTwentyFivekg(
									!jObj.getString("25kg").equalsIgnoreCase("null") ? jObj.getDouble("25kg") : 0.00);
							solidWasteManagementVO.setFiftykg(
									!jObj.getString("50kg").equalsIgnoreCase("null") ? jObj.getDouble("50kg") : 0.00);
							solidWasteManagementVO.setTractor(
									!jObj.getString("tractor").equalsIgnoreCase("null") ? jObj.getLong("tractor") : 0l);
							solidWasteManagementVO.setAuto(
									!jObj.getString("auto").equalsIgnoreCase("null") ? jObj.getLong("auto") : 0l);
							solidWasteManagementVO.setTricycle(!jObj.getString("tricycle").equalsIgnoreCase("null")
									? jObj.getLong("tricycle") : 0l);
							solidWasteManagementVO.setEvehicle(!jObj.getString("evehicle").equalsIgnoreCase("null")
									? jObj.getLong("evehicle") : 0l);
							solidWasteManagementVO.setBlocks(
									!jObj.getString("blocks").equalsIgnoreCase("null") ? jObj.getLong("blocks") : 0l);
							finalList.add(solidWasteManagementVO);

						}
					}
				}
			}
			List<SolidWasteManagementVO> data = getRfidTrackingOverAllTargetsData(inputVO);
			for (SolidWasteManagementVO rfidData : data) {
				for (SolidWasteManagementVO finalVo : finalList) {
					if (finalVo.getId().trim().toString()
							.equalsIgnoreCase(rfidData.getLocationId().toString().trim())) {
						finalVo.setTotalRfidTags(rfidData.getTotalRfidTags());
						finalVo.setBlockNo(rfidData.getBlockNo());
						finalVo.setInTime(rfidData.getInTime());
						finalVo.setOutTime(rfidData.getOutTime());
						finalVo.setOutOfTarget(rfidData.getOutOfTarget());
						finalVo.setTarget(rfidData.getTarget());
						finalVo.setAchieve(rfidData.getAchieve());
						finalVo.setAverageInTime(rfidData.getAverageInTime());
						finalVo.setAverageOutTime(rfidData.getAverageOutTime());
						finalVo.setAverageTarget(rfidData.getAverageTarget());
						finalVo.setTrackingPer(rfidData.getTrackingPer());
						finalVo.setAvertrackingPer(rfidData.getAvertrackingPer());
						finalVo.setInTimePer(rfidData.getInTimePer());
						finalVo.setOutTimePer(rfidData.getOutTimePer());
						finalVo.setGpCnt(rfidData.getGpCnt());
						finalVo.setBlockNo(rfidData.getBlockNo());
					}
				}

			}
		} catch (JSONException e) {
			LOG.error("Exception raised at getSolidInfoLocationWise - SolidWasteManagementService service", e);

		}
		return finalList;
	}

	/*
	 * Date : 07/11/2017 Author :Hymavathi
	 * 
	 * @description : getSolidWasteManagementOverAllCounts
	 */
	@Override
	public SolidWasteManagementVO getSolidWasteManagementOverAllCounts(InputVO inputVO) throws ParseException {
		SolidWasteManagementVO solidWasteManagementVO = new SolidWasteManagementVO();

		try {

			
			WebResource webResource = commonMethodsUtilService
		.getWebResourceObject("http://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="
				+ inputVO.getLocationId() + "&locationType=" + inputVO.getLocationType() + "&fromDate=1-06-2017&toDate=" + inputVO.getToDate());
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
										JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							JSONObject jObj = (JSONObject) finalArray.get(i);
							solidWasteManagementVO.setRfidTags(!jObj.getString("rfid_tags").equalsIgnoreCase("null")
									? solidWasteManagementVO.getRfidTags() + jObj.getLong("rfid_tags")
									: solidWasteManagementVO.getRfidTags());
							solidWasteManagementVO.setFarmers(!jObj.getString("farmers").equalsIgnoreCase("null")
									? solidWasteManagementVO.getFarmers() + jObj.getLong("farmers")
									: solidWasteManagementVO.getFarmers());
							solidWasteManagementVO
									.setHouseCollecion(!jObj.getString("houseCollecion").equalsIgnoreCase("null")
											? solidWasteManagementVO.getHouseCollecion()
													+ jObj.getDouble("houseCollecion")
											: solidWasteManagementVO.getHouseCollecion());
							solidWasteManagementVO
									.setFarmerCollection(!jObj.getString("farmerCollection").equalsIgnoreCase("null")
											? solidWasteManagementVO.getFarmerCollection()
													+ jObj.getDouble("farmerCollection")
											: solidWasteManagementVO.getFarmerCollection());
							solidWasteManagementVO
									.setSwmCollection(!jObj.getString("swmCollection").equalsIgnoreCase("null")
											? solidWasteManagementVO.getSwmCollection()
													+ jObj.getDouble("swmCollection")
											: solidWasteManagementVO.getSwmCollection());
							solidWasteManagementVO.setMgnres(!jObj.getString("mgnres").equalsIgnoreCase("null")
									? solidWasteManagementVO.getMgnres() + jObj.getLong("mgnres")
									: solidWasteManagementVO.getMgnres());
							solidWasteManagementVO.setPr(!jObj.getString("pr").equalsIgnoreCase("null")
									? solidWasteManagementVO.getPr() + jObj.getLong("pr")
									: solidWasteManagementVO.getPr());
							solidWasteManagementVO.setPublicType(!jObj.getString("public").equalsIgnoreCase("null")
									? solidWasteManagementVO.getPublicType() + jObj.getLong("public")
									: solidWasteManagementVO.getPublicType());
							solidWasteManagementVO.setOnekg(!jObj.getString("1kg").equalsIgnoreCase("null")
									? solidWasteManagementVO.getOnekg() + jObj.getDouble("1kg")
									: solidWasteManagementVO.getOnekg());
							solidWasteManagementVO.setFivekg(!jObj.getString("5kg").equalsIgnoreCase("null")
									? solidWasteManagementVO.getFivekg() + jObj.getDouble("5kg")
									: solidWasteManagementVO.getFivekg());
							solidWasteManagementVO.setTenkg(!jObj.getString("10kg").equalsIgnoreCase("null")
									? solidWasteManagementVO.getTenkg() + jObj.getDouble("10kg")
									: solidWasteManagementVO.getTenkg());
							solidWasteManagementVO.setTwentyFivekg(!jObj.getString("25kg").equalsIgnoreCase("null")
									? solidWasteManagementVO.getTwentyFivekg() + jObj.getDouble("25kg")
									: solidWasteManagementVO.getTwentyFivekg());
							solidWasteManagementVO.setFiftykg(!jObj.getString("50kg").equalsIgnoreCase("null")
									? solidWasteManagementVO.getFiftykg() + jObj.getDouble("50kg")
									: solidWasteManagementVO.getFiftykg());
							solidWasteManagementVO.setTractor(!jObj.getString("tractor").equalsIgnoreCase("null")
									? solidWasteManagementVO.getTractor() + jObj.getLong("tractor")
									: solidWasteManagementVO.getTractor());
							solidWasteManagementVO.setAuto(!jObj.getString("auto").equalsIgnoreCase("null")
									? solidWasteManagementVO.getAuto() + jObj.getLong("auto")
									: solidWasteManagementVO.getAuto());
							solidWasteManagementVO.setTricycle(!jObj.getString("tricycle").equalsIgnoreCase("null")
									? solidWasteManagementVO.getTricycle() + jObj.getLong("tricycle")
									: solidWasteManagementVO.getTricycle());
							solidWasteManagementVO.setEvehicle(!jObj.getString("evehicle").equalsIgnoreCase("null")
									? solidWasteManagementVO.getEvehicle() + jObj.getLong("evehicle")
									: solidWasteManagementVO.getEvehicle());
							solidWasteManagementVO.setNadap(!jObj.getString("nadap").equalsIgnoreCase("null")
									? solidWasteManagementVO.getNadap() + jObj.getDouble("nadap")
									: solidWasteManagementVO.getNadap());
							solidWasteManagementVO.setVermi(!jObj.getString("vermi").equalsIgnoreCase("null")
									? solidWasteManagementVO.getVermi() + jObj.getDouble("vermi")
									: solidWasteManagementVO.getVermi());
							solidWasteManagementVO.setVermiStock(!jObj.getString("vermiStock").equalsIgnoreCase("null")
									? solidWasteManagementVO.getVermiStock() + jObj.getDouble("vermiStock")
									: solidWasteManagementVO.getVermiStock());
							solidWasteManagementVO.setBlocks(!jObj.getString("blocks").equalsIgnoreCase("null")
									? solidWasteManagementVO.getBlocks() + jObj.getLong("blocks")
									: solidWasteManagementVO.getBlocks());
							solidWasteManagementVO
									.setRfidTracking(!jObj.getString("rfidTracking").equalsIgnoreCase("null")
											? solidWasteManagementVO.getRfidTracking() + jObj.getLong("rfidTracking")
											: solidWasteManagementVO.getRfidTracking());
						}
					}
				}
			}

			List<SolidWasteManagementVO> data = getRfidTrackingOverAllTargetsData(inputVO);
			for (SolidWasteManagementVO rfidData : data) {

				solidWasteManagementVO
						.setTotalRfidTags(solidWasteManagementVO.getTotalRfidTags() + rfidData.getTotalRfidTags());
				solidWasteManagementVO
						.setTrackingPer(solidWasteManagementVO.getTrackingPer() + rfidData.getTrackingPer());
				solidWasteManagementVO.setGpCnt(solidWasteManagementVO.getGpCnt() + rfidData.getGpCnt());
				solidWasteManagementVO.setTarget(solidWasteManagementVO.getTarget() + rfidData.getTarget());
				solidWasteManagementVO.setAchieve(solidWasteManagementVO.getAchieve() + rfidData.getAchieve());
				solidWasteManagementVO.setInTime(solidWasteManagementVO.getInTime() + rfidData.getInTime());
				solidWasteManagementVO.setOutTime(solidWasteManagementVO.getOutTime() + rfidData.getOutTime());
			}
		} catch (JSONException e) {
			LOG.error("Exception raised at getSolidWasteManagementOverAllCounts - SolidWasteManagementService service", e);
		}
		return solidWasteManagementVO;
	}

	public WebServiceDataVO saveRfidTrackingOverAllTargets() {

		WebServiceDataVO webServiceDataVO = new WebServiceDataVO();

		String jsonList = getRfidTrackingData();
		jsonList = jsonList.replaceAll("\\<.*?\\>", "");
		jsonList = jsonList.replaceAll("Notice:  Undefined index: districtId in /var/www/html/api/swm/index.php on line 68", "");

		try {
			WebServiceData model = new WebServiceData();
			model.setWebserviceId(127L);
			// model.setInputData(inputVO.getInputData());
			model.setResponceData(jsonList);
			model.setIsDeleted("N");
			model.setDataDate(dateUtilService.getCurrentDateAndTime());
			model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		
			WebServiceData rfidData =null;
			if(jsonList!=null && jsonList.length()>3 && !jsonList.isEmpty()){
				rfidData = webServiceDataDAO.save(model);
			}
			// WebServiceData data = webServiceDataDAO.get(webserviceId);
			// data.setIsDeleted("Y");
			// webServiceDataDAO.save(data);
			if (rfidData != null) {
				webServiceDataVO.setStatus("success");
			} else {
				webServiceDataVO.setStatus("failure");
			}
		} catch (Exception e) {
			LOG.error("Exception occured at saveRfidTrackingOverAllTargets() in SolidWasteManagementService class", e);
			webServiceDataVO.setStatus("failure");
		}
		return webServiceDataVO;
	}

	public String getRfidTrackingData() {
		String output = null;
		try {
			Date fromDate = dateUtilService.getCurrentDateAndTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateStr = sdf.format(fromDate);
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(
					"http://pris.ap.gov.in/api/swm/index.php?getSwmInfo=1&fromDate="
							+ dateStr + "&toDate=" + dateStr + "");
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				output = response.getEntity(String.class);
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getRfidTrackingOverAllTargetsData() in  SolidWasteManagementService class",
					e);
		}
		return output;

	}

	public List<SolidWasteManagementVO> getRfidTrackingOverAllTargetsData(InputVO inputVO) throws ParseException {
		List<SolidWasteManagementVO> finalList = new ArrayList<SolidWasteManagementVO>(0);
		try {
			Date fromDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = null;
			if (inputVO.getFromDate() != null && inputVO.getFromDate().trim().length() > 0) {
				fromDate = sdf.parse(inputVO.getFromDate());
			}
			Map<Long, SolidWasteManagementVO> Map = new HashMap<Long, SolidWasteManagementVO>();
			Map<Long, SolidWasteManagementVO> locationMap = new HashMap<Long, SolidWasteManagementVO>();
			// Set<Long> gpIds = new HashSet<Long>();
			Set<SolidWasteManagementVO> blockSet = new HashSet<SolidWasteManagementVO>();
			Long webserviceId=0l;
			webserviceId = webServiceDataDAO.getMaxidforRFIDService(fromDate);
			if(webserviceId ==null || webserviceId.longValue() ==0){
				webserviceId =webServiceDataDAO.getMaxidforRFIDService(null);
			}
			String rfidList = webServiceDataDAO.getRfidTrackingOverAllTargetsData(webserviceId);

			if (rfidList != null && rfidList.length() > 0) {
				String jsonData = rfidList;
				if (jsonData != null && !jsonData.isEmpty()) {
					JSONArray finalArray = new JSONArray(jsonData);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							JSONObject jObj = (JSONObject) finalArray.get(i);
							SolidWasteManagementVO solidWasteManagementVO = null;

							if (inputVO.getLocationType().equalsIgnoreCase("state")) {
								solidWasteManagementVO = locationMap.get(1l);
								if (solidWasteManagementVO == null) {
									solidWasteManagementVO = new SolidWasteManagementVO();
									solidWasteManagementVO.setLocationId(1L);
									solidWasteManagementVO.setLocationName("Andra Pradesh");

									locationMap.put(1L, solidWasteManagementVO);
								}
							} else if (inputVO.getLocationType().equalsIgnoreCase("district")) {
								solidWasteManagementVO = locationMap.get(jObj.getLong("districtID"));
								if (solidWasteManagementVO == null) {
									solidWasteManagementVO = new SolidWasteManagementVO();
									solidWasteManagementVO.setLocationId(jObj.getLong("districtID"));
									solidWasteManagementVO.setLocationName(jObj.getString("district").trim());
									locationMap.put(jObj.getLong("districtID"), solidWasteManagementVO);
								}
							} else if (inputVO.getLocationType().equalsIgnoreCase("constituency")) {
								solidWasteManagementVO = locationMap.get(jObj.getLong("constituencyID"));
								if (solidWasteManagementVO == null) {
									solidWasteManagementVO = new SolidWasteManagementVO();
									solidWasteManagementVO.setLocationId(jObj.getLong("constituencyID"));
									solidWasteManagementVO.setLocationName(jObj.getString("constituencyName"));
									locationMap.put(jObj.getLong("constituencyID"), solidWasteManagementVO);
								}
							} else if (inputVO.getLocationType().equalsIgnoreCase("mandal")) {
								solidWasteManagementVO = locationMap.get(jObj.getLong("mandalID"));
								if (solidWasteManagementVO == null) {
									solidWasteManagementVO = new SolidWasteManagementVO();
									solidWasteManagementVO.setLocationId(jObj.getLong("mandalID"));
									solidWasteManagementVO.setLocationName(jObj.getString("mandal"));
									locationMap.put(jObj.getLong("mandalID"), solidWasteManagementVO);
								}
							} else if (inputVO.getLocationType().equalsIgnoreCase("panchayat")) {
								solidWasteManagementVO = locationMap.get(jObj.getLong("gpID"));
								if (solidWasteManagementVO == null) {
									solidWasteManagementVO = new SolidWasteManagementVO();
									solidWasteManagementVO.setLocationId(jObj.getLong("gpID"));
									solidWasteManagementVO.setLocationName(jObj.getString("gp"));
									locationMap.put(jObj.getLong("gpID"), solidWasteManagementVO);
								}
							}
							if (jObj.has("totalRfidTags") && jObj.getLong("totalRfidTags") > 0) {
								solidWasteManagementVO.setTotalRfidTags(
										jObj.getLong("totalRfidTags") + solidWasteManagementVO.getTotalRfidTags());
							}
							if (jObj.has("inTime") && jObj.getLong("inTime") > 0) {
								solidWasteManagementVO
										.setInTime(jObj.getLong("inTime") + solidWasteManagementVO.getInTime());
							}
							if (jObj.has("outTime") && jObj.getLong("outTime") > 0) {
								solidWasteManagementVO
										.setOutTime(jObj.getLong("outTime") + solidWasteManagementVO.getOutTime());
							}
							if (jObj.has("outOfTarget") && jObj.getLong("outOfTarget") > 0) {
								solidWasteManagementVO.setOutOfTarget(
										jObj.getLong("outOfTarget") + solidWasteManagementVO.getOutOfTarget());
							}
							if (jObj.has("target") && jObj.getLong("target") > 0) {
								solidWasteManagementVO
										.setTarget(jObj.getLong("target") + solidWasteManagementVO.getTarget());
							}
							if (jObj.has("averageInTime") && jObj.getLong("averageInTime") > 0) {
								solidWasteManagementVO.setAverageInTime(
										jObj.getLong("averageInTime") + solidWasteManagementVO.getAverageInTime());
							}
							if (jObj.has("averageOutTime") && jObj.getLong("averageOutTime") > 0) {
								solidWasteManagementVO.setAverageOutTime(
										jObj.getLong("averageOutTime") + solidWasteManagementVO.getAverageOutTime());
							}
							if (jObj.has("averageTarget") && jObj.getLong("averageTarget") > 0) {
								solidWasteManagementVO.setAverageTarget(
										jObj.getLong("averageTarget") + solidWasteManagementVO.getAverageTarget());
							}
							if (jObj.has("achieve") && jObj.getLong("achieve") > 0) {
								solidWasteManagementVO
										.setAchieve(jObj.getLong("achieve") + solidWasteManagementVO.getAchieve());
							}
							solidWasteManagementVO.setInTimePer(solidWasteManagementVO.getTarget() > 0 ? round(
									(solidWasteManagementVO.getInTime() * 100.00) / solidWasteManagementVO.getTarget(),
									2) : 0.00);
							solidWasteManagementVO.setOutTimePer(solidWasteManagementVO.getTarget() > 0 ? round(
									(solidWasteManagementVO.getOutTime() * 100.00) / solidWasteManagementVO.getTarget(),
									2) : 0.00);
							solidWasteManagementVO.setTrackingPer(solidWasteManagementVO.getTarget() > 0
									? round(((solidWasteManagementVO.getAchieve() * 100.00)
											/ solidWasteManagementVO.getTarget()), 2)
									: 0.00);
							solidWasteManagementVO.setAvertrackingPer(solidWasteManagementVO.getAverageTarget() > 0
									? round(((solidWasteManagementVO.getAverageTime() * 100.00)
											/ solidWasteManagementVO.getAverageTarget()), 2)
									: 0.00);
						}
					}

				}
				finalList.addAll(locationMap.values());
			}
		} catch (JSONException e) {
			LOG.error("Exception raised at getRfidTrackingOverAllTargetsData - SolidWasteManagementService service", e);
		}
		return finalList;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	@Override
	public List<SolidWasteManagementVO> getGpWiseRfidTrackingOverData(InputVO inputVO) throws ParseException {

		List<SolidWasteManagementVO> finalList = new ArrayList<SolidWasteManagementVO>(0);
		// SolidWasteManagementVO solidWasteManagementVO = new
		// SolidWasteManagementVO();
		try {
			Date fromDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			// Date startDate = null;
			if (inputVO.getFromDate() != null && inputVO.getFromDate().trim().length() > 0) {
				fromDate = sdf.parse(inputVO.getFromDate());
			}
			Map<Long, List<SolidWasteManagementVO>> gpMap = new HashMap<Long, List<SolidWasteManagementVO>>();
			Map<Long, SolidWasteManagementVO> locationMap = new HashMap<Long, SolidWasteManagementVO>();
			Long webserviceId=0l;
			webserviceId = webServiceDataDAO.getMaxidforRFIDService(fromDate);
			if(webserviceId ==null || webserviceId.longValue() ==0){
				webserviceId =webServiceDataDAO.getMaxidforRFIDService(null);
			}
			String rfidList = webServiceDataDAO.getRfidTrackingOverAllTargetsData(webserviceId);
			if (rfidList != null && rfidList.length() > 0) {
				if (rfidList != null && rfidList.length() > 0) {
					String jsonData = rfidList;
					if (jsonData != null && !jsonData.isEmpty()) {
						JSONArray finalArray = new JSONArray(jsonData);
						if (finalArray != null && finalArray.length() > 0) {
							for (int i = 0; i < finalArray.length(); i++) {
								JSONObject jObj = (JSONObject) finalArray.get(i);

								if (inputVO.getLocationId() == jObj.getLong("mandalID")) {
									List<SolidWasteManagementVO> gpWiseBlockList = gpMap.get(jObj.getLong("gpID"));
									if (gpWiseBlockList == null) {
										gpWiseBlockList = new ArrayList<>(0);
										SolidWasteManagementVO blockVO = new SolidWasteManagementVO();
										blockVO.setTotalRfidTags(jObj.getLong("totalRfidTags"));
										blockVO.setLocationName(jObj.getString("gp").trim());
										blockVO.setdName(jObj.getString("district").trim());
										blockVO.setBlockNo(jObj.getLong("blockNo"));
										blockVO.setTarget(jObj.getLong("target"));
										blockVO.setInTime(jObj.getLong("inTime"));
										blockVO.setOutTime(jObj.getLong("outTime"));
										blockVO.setAchieve(jObj.getLong("achieve"));
										blockVO.setInTimePer(blockVO.getTarget() > 0
												? round((blockVO.getInTime() * 100.00) / blockVO.getTarget(), 2)
												: 0.00);
										blockVO.setOutTimePer(blockVO.getTarget() > 0

												? round((blockVO.getOutTime() * 100.00) / blockVO.getTarget(), 2)
												: 0.00);
										blockVO.setTrackingPer(blockVO.getTarget() > 0
												? round(((blockVO.getAchieve() * 100.00) / blockVO.getTarget()), 2)
												: 0.00);
										// blockVO.setMergeSort(blockVO.getLocationName()+"
										// "+blockVO.getBlockNo());

										gpWiseBlockList.add(blockVO);
										gpMap.put(jObj.getLong("gpID"), gpWiseBlockList);
									}
									else {
										SolidWasteManagementVO matchVO = new SolidWasteManagementVO();
										matchVO.setTotalRfidTags(
												jObj.getLong("totalRfidTags") + matchVO.getTotalRfidTags());
										matchVO.setBlockNo(jObj.getLong("blockNo") + matchVO.getBlockNo());
										matchVO.setLocationName(jObj.getString("gp").trim());
										matchVO.setTarget(jObj.getLong("target") + matchVO.getTarget());
										matchVO.setInTime(jObj.getLong("inTime") + matchVO.getInTime());
										matchVO.setOutTime(jObj.getLong("outTime") + matchVO.getOutTime());
										matchVO.setAchieve(jObj.getLong("achieve") + matchVO.getAchieve());
										matchVO.setInTimePer(matchVO.getTarget() > 0
												? round((matchVO.getInTime() * 100.00) / matchVO.getTarget(), 2)
												: 0.00);
										matchVO.setOutTimePer(matchVO.getTarget() > 0
												? round((matchVO.getOutTime() * 100.00) / matchVO.getTarget(), 2)
												: 0.00);
										matchVO.setTrackingPer(matchVO.getTarget() > 0
												? round(((matchVO.getAchieve() * 100.00) / matchVO.getTarget()), 2)
												: 0.00);
										// matchVO.setMergeSort(matchVO.getLocationName()+"
										// "+matchVO.getBlockNo());
										gpWiseBlockList.add(matchVO);

									}
								}
							}
						}
					}
				}
			}
			for (Entry<Long, List<SolidWasteManagementVO>> entry : gpMap.entrySet()) {
				SolidWasteManagementVO mainvo = new SolidWasteManagementVO();
				Long gpId = entry.getKey();
				if (gpId != null) {
					mainvo.setLocationId(gpId);
					mainvo.setLocationName(entry.getValue().get(0).getLocationName());
					mainvo.getSubList().addAll(entry.getValue());
					finalList.add(mainvo);
				}
			}

			if (commonMethodsUtilService.isListOrSetValid(finalList)) {
				for (SolidWasteManagementVO solidWasteManagementVO : finalList) {
					Collections.sort(solidWasteManagementVO.getSubList(), new Comparator<SolidWasteManagementVO>() {
						public int compare(SolidWasteManagementVO o1, SolidWasteManagementVO o2) {
							return o1.getBlockNo().compareTo(o2.getBlockNo());

						}

					});
				}
			/*	for (SolidWasteManagementVO solidWasteManagementVO : finalList) {
					Collections.sort(finalList, new Comparator<SolidWasteManagementVO>() {
						public int compare(SolidWasteManagementVO o1, SolidWasteManagementVO o2) {
							return o1.getLocationName().compareTo(o2.getLocationName());

						}

					});
				}*/

			}

			if (finalList != null && !finalList.isEmpty()) {
				for (SolidWasteManagementVO MainVO : finalList) {
					for (SolidWasteManagementVO subVO : MainVO.getSubList()) {
						MainVO.setTotalRfidTags(MainVO.getTotalRfidTags() + subVO.getTotalRfidTags());
						MainVO.setTarget(MainVO.getTarget() + subVO.getTarget());
						MainVO.setTrackingPer(MainVO.getTrackingPer() + subVO.getTrackingPer());
						MainVO.setInTimePer(MainVO.getInTimePer() + subVO.getInTimePer());
						MainVO.setOutTimePer(MainVO.getOutTimePer() + subVO.getOutTimePer());
					}

				}
			}

		} catch (JSONException e) {
			LOG.error("Exception raised at getGpWiseRfidTrackingOverData - SolidWasteManagementService service", e);
		}
		return finalList;
	}
}
