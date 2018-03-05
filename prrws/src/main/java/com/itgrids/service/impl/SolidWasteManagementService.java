package com.itgrids.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
						+ inputVO.getFilterType() + "&filterId=" + inputVO.getFilterId() + "&subFilterType=assembly&subFilterId=" + inputVO.getSubFilterId() + "&fromDate="
						+ inputVO.getFromDate() + "&toDate=" + inputVO.getToDate();
			else if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("constituency"))
				url = "http://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="
						+ inputVO.getLocationId() + "&locationType=assembly&filterType=" + inputVO.getFilterType()
						+ "&filterId=" + inputVO.getFilterId() + "&fromDate=" + inputVO.getFromDate() + "&toDate="
						+ inputVO.getToDate();
			else if (inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("district"))
				url = "http://pris.ap.gov.in/survey/api/swmapi.php?getSwmInfo=true&locationId="
						+ inputVO.getLocationId() + "&locationType=" + inputVO.getLocationType() + "&fromDate="
						+ inputVO.getFromDate() + "&toDate=" + inputVO.getToDate();

			webResource = commonMethodsUtilService.getWebResourceObject(url.toString());
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
							+ inputVO.getLocationId() + "&locationType=" + inputVO.getLocationType() + "&fromDate="
							+ inputVO.getFromDate() + "&toDate=" + inputVO.getToDate());
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
				solidWasteManagementVO.setBlockNo(solidWasteManagementVO.getBlockNo() + rfidData.getBlockNo());
				solidWasteManagementVO.setTarget(solidWasteManagementVO.getTarget() + rfidData.getTarget());
				solidWasteManagementVO.setAchieve(solidWasteManagementVO.getAchieve() + rfidData.getAchieve());
				solidWasteManagementVO.setInTime(solidWasteManagementVO.getInTime() + rfidData.getInTime());
				solidWasteManagementVO.setOutTime(solidWasteManagementVO.getOutTime() + rfidData.getOutTime());
			}
		} catch (JSONException e) {
			LOG.error("Exception raised at getDrainsInfobyLocation - DrainsService service", e);
		}
		return solidWasteManagementVO;
	}

	public WebServiceDataVO saveRfidTrackingOverAllTargets() {

		WebServiceDataVO webServiceDataVO = new WebServiceDataVO();

		String jsonList = getRfidTrackingData();
		jsonList = jsonList.replaceAll("\\<.*?\\>", "");
		jsonList = jsonList
				.replaceAll("Notice:  Undefined index: districtId in /var/www/html/api/swm/index.php on line 68", "");

		try {
			// Long webserviceId = webServiceDataDAO.getLatestDataId();
			WebServiceData model = new WebServiceData();
			model.setWebserviceId(127L);
			// model.setInputData(inputVO.getInputData());
			model.setResponceData(jsonList);
			model.setIsDeleted("N");
			model.setDataDate(dateUtilService.getCurrentDateAndTime());
			model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			WebServiceData rfidData = webServiceDataDAO.save(model);
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
			Date fromDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateStr = sdf.format(fromDate);
			WebResource webResource = commonMethodsUtilService
					.getWebResourceObject("http://10.0.3.56/PD/download/getSolidInfoLocationWise/getSwmInfo=1&districtId=0&fromDate="
							+ dateStr + "&toDate=" + dateStr + "");
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					// JSONArray finalArray = new JSONArray(output);
					// return finalArray.toString();
				}
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
			String rfidList = webServiceDataDAO.getRfidTrackingOverAllTargetsData(fromDate);
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

							if (jObj.has("gpID") && jObj.getLong("gpID") > 0) {
								solidWasteManagementVO.setGpCnt(jObj.getLong("gpID") );
							}

							if (jObj.has("blockNo") && jObj.getLong("blockNo") > 0) {
								solidWasteManagementVO.setBlockNo(jObj.getLong("blockNo"));
							}

						}
					}

				}
				finalList.addAll(locationMap.values());
			}
		} catch (JSONException e) {
			LOG.error("Exception raised at getSolidInfoLocationWise - SolidWasteManagementService service", e);
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

	/*
	 * @Override public List<SolidWasteManagementVO>
	 * getGpWiseRfidTrackingOverData(InputVO inputVO) {
	 * List<SolidWasteManagementVO> finalList = new
	 * ArrayList<SolidWasteManagementVO>(0); try { Map<Long,
	 * List<SolidWasteManagementVO>> locationMap = new HashMap<Long,
	 * List<SolidWasteManagementVO>>(); String rfidList =
	 * webServiceDataDAO.getRfidTrackingOverAllTargetsData(inputVO.
	 * getWebserviceId()); if (rfidList != null && rfidList.length() > 0) { if
	 * (rfidList != null && !rfidList.isEmpty()) { JSONArray finalArray = new
	 * JSONArray(rfidList); if (finalArray != null && finalArray.length() > 0) {
	 * for (int i = 0; i < finalArray.length(); i++) { JSONObject jObj =
	 * (JSONObject) finalArray.get(i); SolidWasteManagementVO
	 * solidWasteManagementVO = new SolidWasteManagementVO(); if
	 * (inputVO.getLocationType().equalsIgnoreCase("gp")) {
	 * List<SolidWasteManagementVO> list =
	 * locationMap.get(jObj.getLong("gpID")); if
	 * (commonMethodsUtilService.isListOrSetValid(list)) { for
	 * (SolidWasteManagementVO rfidVO : list) { SolidWasteManagementVO Vo =
	 * getMatchedVOForGpId(list, jObj.getLong("blockNo")); if (Vo != null) {
	 * Vo.setLocationId(jObj.getLong("gpID"));
	 * Vo.setLocationName(jObj.getString("gp").trim());
	 * 
	 * if (jObj.has("totalRfidTags") && jObj.getLong("totalRfidTags") > 0) {
	 * Vo.setTotalRfidTags(jObj.getLong("totalRfidTags")+
	 * solidWasteManagementVO.getTotalRfidTags()); } if (jObj.has("inTime") &&
	 * jObj.getLong("inTime") > 0) {
	 * Vo.setInTime(jObj.getLong("inTime")+solidWasteManagementVO.getInTime());
	 * } if (jObj.has("outTime") && jObj.getLong("outTime") > 0) {
	 * Vo.setOutTime(jObj.getLong("outTime")+solidWasteManagementVO.getOutTime()
	 * ); } if (jObj.has("outOfTarget") && jObj.getLong("outOfTarget") > 0) {
	 * Vo.setOutOfTarget(jObj.getLong("outOfTarget")+solidWasteManagementVO.
	 * getOutOfTarget()); } if (jObj.has("target") && jObj.getLong("target") >
	 * 0) {
	 * Vo.setTarget(jObj.getLong("target")+solidWasteManagementVO.getTarget());
	 * } if (jObj.has("averageInTime") && jObj.getLong("averageInTime") > 0) {
	 * Vo.setAverageInTime(jObj.getLong("averageInTime")+solidWasteManagementVO.
	 * getAverageInTime()); } if (jObj.has("averageOutTime") &&
	 * jObj.getLong("averageOutTime") > 0) {
	 * Vo.setAverageOutTime(jObj.getLong("averageOutTime")+
	 * solidWasteManagementVO.getAverageOutTime()); } if
	 * (jObj.has("averageTarget") && jObj.getLong("averageTarget") > 0) {
	 * Vo.setAverageTarget(jObj.getLong("averageTarget")+solidWasteManagementVO.
	 * getAverageTarget()); } if (jObj.has("achieve") && jObj.getLong("achieve")
	 * > 0) {
	 * Vo.setAchieve(jObj.getLong("achieve")+solidWasteManagementVO.getAchieve()
	 * ); } Vo.setInTimePer(Vo.getTarget() > 0 ? round((Vo.getInTime() * 100.00)
	 * / Vo.getTarget(), 2) : 0.00); Vo.setOutTimePer(Vo.getTarget() > 0 ?
	 * round((Vo.getOutTime() * 100.00) / Vo.getTarget(), 2) : 0.00);
	 * Vo.setTrackingPer(Vo.getTarget() > 0 ? round(((Vo.getAchieve() * 100.00)
	 * / Vo.getTarget()), 2) : 0.00);
	 * Vo.setAvertrackingPer(Vo.getAverageTarget() > 0 ?
	 * round(((Vo.getAverageTime() * 100.00) / Vo.getAverageTarget()), 2) :
	 * 0.00); solidWasteManagementVO.getSubList().add(Vo); }
	 * 
	 * rfidVO.setLocationId(jObj.getLong("gpID"));
	 * rfidVO.setLocationName(jObj.getString("gp").trim());
	 * 
	 * if (jObj.has("totalRfidTags") && jObj.getLong("totalRfidTags") > 0) {
	 * Vo.setTotalRfidTags(jObj.getLong("totalRfidTags")+
	 * solidWasteManagementVO.getTotalRfidTags()); } if (jObj.has("inTime") &&
	 * jObj.getLong("inTime") > 0) {
	 * Vo.setInTime(jObj.getLong("inTime")+solidWasteManagementVO.getInTime());
	 * } if (jObj.has("outTime") && jObj.getLong("outTime") > 0) {
	 * Vo.setOutTime(jObj.getLong("outTime")+solidWasteManagementVO.getOutTime()
	 * ); } if (jObj.has("outOfTarget") && jObj.getLong("outOfTarget") > 0) {
	 * Vo.setOutOfTarget(jObj.getLong("outOfTarget")+solidWasteManagementVO.
	 * getOutOfTarget()); } if (jObj.has("target") && jObj.getLong("target") >
	 * 0) {
	 * Vo.setTarget(jObj.getLong("target")+solidWasteManagementVO.getTarget());
	 * } if (jObj.has("averageInTime") && jObj.getLong("averageInTime") > 0) {
	 * Vo.setAverageInTime(jObj.getLong("averageInTime")+solidWasteManagementVO.
	 * getAverageInTime()); } if (jObj.has("averageOutTime") &&
	 * jObj.getLong("averageOutTime") > 0) {
	 * Vo.setAverageOutTime(jObj.getLong("averageOutTime")+
	 * solidWasteManagementVO.getAverageOutTime()); } if
	 * (jObj.has("averageTarget") && jObj.getLong("averageTarget") > 0) {
	 * Vo.setAverageTarget(jObj.getLong("averageTarget")+solidWasteManagementVO.
	 * getAverageTarget()); } if (jObj.has("achieve") && jObj.getLong("achieve")
	 * > 0) {
	 * Vo.setAchieve(jObj.getLong("achieve")+solidWasteManagementVO.getAchieve()
	 * ); } rfidVO.setInTimePer(rfidVO.getTarget() > 0 ?
	 * round((rfidVO.getInTime() * 100.00) / rfidVO.getTarget(), 2) : 0.00);
	 * rfidVO.setOutTimePer(rfidVO.getTarget() > 0 ? round((rfidVO.getOutTime()
	 * * 100.00) / rfidVO.getTarget(), 2) : 0.00);
	 * rfidVO.setTrackingPer(rfidVO.getTarget() > 0 ?
	 * round(((rfidVO.getAchieve() * 100.00) / rfidVO.getTarget()), 2) : 0.00);
	 * rfidVO.setAvertrackingPer(rfidVO.getAverageTarget() > 0 ? round(
	 * ((rfidVO.getAverageTime() * 100.00) / rfidVO.getAverageTarget()), 2) :
	 * 0.00); solidWasteManagementVO.getSubList().add(rfidVO);
	 * list.add(solidWasteManagementVO); }
	 * locationMap.put(solidWasteManagementVO.getGpID(), list); } } } } }
	 * 
	 * finalList.addAll(locationMap.values()); }
	 * 
	 * } catch (JSONException e) { LOG.
	 * error("Exception raised at getSolidInfoLocationWise - SolidWasteManagementService service"
	 * , e); }
	 * 
	 * public SolidWasteManagementVO
	 * getMatchedVOForGpId(List<SolidWasteManagementVO> rfidVOs,Long id){ try{
	 * if(rfidVOs != null && rfidVOs.size() > 0){ for(SolidWasteManagementVO
	 * param : rfidVOs){ if(param.getBlockNo().longValue() == id){ return param;
	 * } } } } }catch(JSONException e) { LOG.
	 * error("Exception raised at getSolidInfoLocationWise - SolidWasteManagementService service"
	 * , e); }
	 */
}
