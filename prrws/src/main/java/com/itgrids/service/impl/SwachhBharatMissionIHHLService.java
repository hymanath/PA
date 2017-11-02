package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.impl.LightMonitoringDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.SwachhBharatMissionIHHLDtlsVO;
import com.itgrids.service.ISwachhBharatMissionIHHLService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.SwachhBharatMissionIHHLThread;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class SwachhBharatMissionIHHLService implements ISwachhBharatMissionIHHLService{

	private static final Logger LOG = Logger.getLogger(SwachhBharatMissionIHHLService.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private LightMonitoringDAO lightMonitoringDAO;
	
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
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/IhhlNew/GetIHHLDashBoardUIDetails?", str);

																			//http://125.17.121.167/rwsapwebapi/api/IhhlNew/GetIHHLDashBoardUIDetails? 
			if(response.getStatus() != 200){								//http://125.17.121.167/rwsapwebapi/api/IHHLDashBoardUI/GetIHHLDashBoardUIDetails
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());///kkb
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray locationDtlsArr = jsonObject.getJSONArray("MinisterDBStateFundMngmntData");
	 	    		if(locationDtlsArr != null && locationDtlsArr.length() > 0){
	 	    			for(int i=0;i<locationDtlsArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) locationDtlsArr.get(i);
	 	    				resultVO.setTarget(jObj.has("TARGET") ? jObj.getLong("TARGET"):0l);
	 	    				//resultVO.setGrounded(jObj.has("GROUNDED") ? jObj.getLong("GROUNDED"):0l);
	 	    				resultVO.setNoTGrounded(jObj.has("NOTGROUNDED") ? jObj.getLong("NOTGROUNDED"):0l);
	 	    				resultVO.setInProgress(jObj.has("INPROGRESS") ? jObj.getLong("INPROGRESS"):0l);
	 	    				resultVO.setCompleted(jObj.has("COMPLETEED") ? jObj.getLong("COMPLETEED"):0l);
	 	    				resultVO.setSanctioned(jObj.has("SANCTIONED") ? jObj.getLong("SANCTIONED"):0l);
	 	    				resultVO.setExcessben(jObj.has("EXCESSBEN") ? jObj.getLong("EXCESSBEN"):0l);
	 	    				resultVO.setNotSanctioned(resultVO.getTarget()-resultVO.getSanctioned());
	 	    				resultVO.setNotStarted(resultVO.getSanctioned()-(resultVO.getInProgress()+resultVO.getCompleted()));
	 	    				resultVO.setSanctionpercentage(calculatePercantage(resultVO.getSanctioned(),resultVO.getTarget()).toString());
	 	    				resultVO.setNotSanctionPercentage(calculatePercantage(resultVO.getNotSanctioned(),resultVO.getTarget()).toString());
	 	    				resultVO.setNotStaredPercentage(calculatePercantage(resultVO.getNotStarted(),resultVO.getSanctioned()).toString());
	 	    				resultVO.setInProgressPerc(calculatePercantage(resultVO.getInProgress(),resultVO.getSanctioned()).toString());
	 	    				resultVO.setCompletedPerce(calculatePercantage(resultVO.getCompleted(),resultVO.getSanctioned()).toString());

	 	    				//calculatePercantage
	 	    			}	
	 	    		}
	 	    		JSONArray categoryWiseDataArr = jsonObject.getJSONArray("categorycount");
	 	    		if(categoryWiseDataArr != null && categoryWiseDataArr.length() > 0){
	 	    			List<SwachhBharatMissionIHHLDtlsVO> categoryList = new ArrayList<>();
	 	    			for(int i=0;i<categoryWiseDataArr.length();i++){
	 	    				SwachhBharatMissionIHHLDtlsVO categoryVO = new SwachhBharatMissionIHHLDtlsVO();
	 	    				JSONObject jObj = (JSONObject) categoryWiseDataArr.get(i);
	 	    				String range = jObj.has("CAT") ? jObj.getString("CAT"):"";
	 	    				String[] strArr = getRankIdBasedOnCategory(range);
	 	    				if (strArr.length > 0) {
	 	    					categoryVO.setRange(strArr[2]);
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
		String[] strArr = new String[3];
		 try {
			 
			 if (category != null && category.trim().length() > 0) {
				 if(category.equalsIgnoreCase("80% to 100%")){
					 strArr[0] = "1";
					 strArr[1] = "A";
					 strArr[2] = "80% TO >= 100%";
				 } else if (category.equalsIgnoreCase("60% to Less than 80%")) {
					 strArr[0] = "2";
					 strArr[1] = "B";
					 strArr[2] = "60% TO < 80%";
				 }else if (category.equalsIgnoreCase("40% to Less than 60%")) {
					 strArr[0] = "3";
					 strArr[1] = "C";
					 strArr[2] = "40% TO < 60%";
				 }else if (category.equalsIgnoreCase("0% to Less than 40%")){
					 strArr[0] = "4";
					 strArr[1] = "D";
					 strArr[2] = "0% TO < 40%";
				 } else {
					 strArr[0] = "5";
					 strArr[1] = "E";
					 strArr[2] = " ";
				 }
			 }
		 } catch (Exception e) {
			 LOG.error("Exception occured at getRankIdBasedOnCategory() in SwachhBharatMissionIHHLService class",e); 
		 }
		 return strArr;
	}
	
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLCategoryWiseAnalysisBySelectedDate(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> categoryList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/GetDateWiseTarAchOverview/GetDateWiseTarAchOverviewDetails", str);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		
	 	    		JSONArray categoryWiseDataArr = jsonObject.getJSONArray("categorycount");
	 	    		if(categoryWiseDataArr != null && categoryWiseDataArr.length() > 0){
	 	    			for(int i=0;i<categoryWiseDataArr.length();i++){
	 	    				SwachhBharatMissionIHHLDtlsVO categoryVO = new SwachhBharatMissionIHHLDtlsVO();
	 	    				JSONObject jObj = (JSONObject) categoryWiseDataArr.get(i);
	 	    				String range = jObj.has("CAT") ? jObj.getString("CAT"):"";
	 	    				String[] strArr = getRankIdBasedOnCategory(range);
	 	    				if (strArr.length > 0) {
	 	    					categoryVO.setRange(strArr[2]);
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
	 	    				//resultVO.setSubList(categoryList);
	 	    			}
	 	    		}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception occured at getIHHLCategoryWiseAnalysisBySelectedDate() in SwachhBharatMissionIHHLService class",e); 
		}
		return categoryList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Swachh Bharat Mission category wise analysis details.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLCategoryWiseAnalysisBySelectedDateOld(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (inputVO.getFromDate() != null && inputVO.getFromDate().trim().length() > 0 && inputVO.getToDate() != null && inputVO.getToDate().trim().length() > 0) {
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			List<String[]> categoryTemplateList = new ArrayList<>();
			categoryTemplateList.add(new String[]{"1","A","80% TO >= 100%"});
			categoryTemplateList.add(new String[]{"2","B","60% TO < 80%"});
			categoryTemplateList.add(new String[]{"3","C","40% TO < 60%"});
			categoryTemplateList.add(new String[]{"4","D","0% TO < 40%"});
			
			for (String[] strings : categoryTemplateList) {
				SwachhBharatMissionIHHLDtlsVO categoryVO = new SwachhBharatMissionIHHLDtlsVO();
					categoryVO.setId(Long.valueOf(strings[0]));
					categoryVO.setName(strings[1]);
					categoryVO.setRange(strings[2]);
					categoryVO.setDistrictCount(0l);
					categoryVO.setConstituencyCount(0l);
					categoryVO.setMandalCount(0l);
					resultList.add(categoryVO);
			}
			
			Map<String,ClientResponse> responseMap = new HashMap<>();
			String[] locationArr = { "district", "constituency", "mandal" };
			String URL = "http://125.17.121.167/rwsapwebapi/api/GetDateWiseTarAchOverview/GetDateWiseTarAchOverviewDetails";
			Long startTime = System.currentTimeMillis();
			if (locationArr != null && locationArr.length > 0) {
				ExecutorService executor = Executors.newFixedThreadPool(4);
				for (String location : locationArr) {
					     inputVO.setSubLocation(location);
					     String str = convertingInputVOToString(inputVO);
					     Runnable worker = new SwachhBharatMissionIHHLThread(URL,responseMap,str,location);
						 executor.execute(worker);
				}
				executor.shutdown();
				while(!executor.isTerminated()) {}
				Long endTime = System.currentTimeMillis();
				System.out.println("Thread time taken : "+(endTime-startTime));
				System.out.println("All work has finished "+responseMap.values());
			}
			if (responseMap.size() > 0) {
				for (Entry<String, ClientResponse> resonseEntry : responseMap.entrySet()) {
					String subLcation = resonseEntry.getKey();
					inputVO.setSubLocation(subLcation);
					String output = resonseEntry.getValue().getEntity(String.class);
					
					if (output != null && !output.isEmpty()) {
						JSONObject jsonObject = new JSONObject(output);

						Map<String,SwachhBharatMissionIHHLDtlsVO> targetAchivementDtlsMap = getDailyTargetAndAchivemetnDtlsLocationWise(inputVO, jsonObject);//getting data based day wise

							if (inputVO.getDisplayType() != null && !inputVO.getDisplayType().trim().isEmpty() && inputVO.getDisplayType().trim().length() > 0) {

								if (inputVO.getDisplayType().trim().equalsIgnoreCase("day")) {

									List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
									List<SwachhBharatMissionIHHLDtlsVO> returnList = getLocationWiseDayReportDtls(targetAchivementDtlsMap, dayList);

									if (returnList != null && returnList.size() > 0) {

										for (SwachhBharatMissionIHHLDtlsVO vo : returnList) {

											if (vo != null && vo.getPercentage() != null) {
												
												Double percentage = Double.parseDouble(vo.getPercentage());
												
												SwachhBharatMissionIHHLDtlsVO matchVO = null;

												if (percentage != null) {
													if (percentage >= 80d ) {//&& percentage <= 100d
														matchVO = getCategoryMatchVO(resultList, "A");
													}
													if (percentage >= 60d && percentage < 80d) {
														matchVO = getCategoryMatchVO(resultList, "B");
													}
													if (percentage >= 40d && percentage < 60d) {
														matchVO = getCategoryMatchVO(resultList, "C");
													}
													if (percentage >= 0d && percentage < 40) {
														matchVO = getCategoryMatchVO(resultList, "D");
													}
												}
												if (matchVO != null) {
													if (subLcation != null && subLcation.trim().equalsIgnoreCase("district")) {
														matchVO.setDistrictCount(matchVO.getDistrictCount() + 1);
													} else if (subLcation != null && subLcation.trim().equalsIgnoreCase("constituency")) {
														matchVO.setConstituencyCount(matchVO.getConstituencyCount() + 1);
													} else if (subLcation != null && subLcation.trim().equalsIgnoreCase("mandal")) {
														matchVO.setMandalCount(matchVO.getMandalCount() + 1);
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
		} catch (Exception e) {
			LOG.error("Exception occured at getIHHLCategoryWiseAnalysisBySelectedDate() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	private SwachhBharatMissionIHHLDtlsVO getCategoryMatchVO(List<SwachhBharatMissionIHHLDtlsVO> list, String name) {
		try {
			if (list == null || list.size() == 0)
				return null;
			for (SwachhBharatMissionIHHLDtlsVO vo : list) {
				if (vo.getName().equalsIgnoreCase(name)) {
					return vo;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getCategoryMatchVO() in SwachhBharatMissionIHHLService class",e);
		}
		return null;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get  Swachh Bharat Mission IHHL Achivement progress details.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLAchivementProgressDtls(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (inputVO.getFromDate() != null && inputVO.getFromDate().trim().length() > 0 && inputVO.getToDate() != null && inputVO.getToDate().trim().length() > 0) {
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			Long startTime = System.currentTimeMillis();
            String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/GetDateWiseTarAchOverview/GetDateWiseTarAchOverviewDetails",str);
			Long endTime = System.currentTimeMillis();
			 System.out.println("GetIHHLAchivementProgressDtls  service time take "+(endTime-startTime));
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONObject jsonObject = new JSONObject(output);
					JSONArray dateWiseStateDtlsArr = jsonObject.getJSONArray("MinisterDBStateFundMngmntData");
					if (dateWiseStateDtlsArr != null && dateWiseStateDtlsArr.length() > 0) {

						List<SwachhBharatMissionIHHLDtlsVO> dateWiseTargetAchivementList = getDateWiseTargetAndAchivementDtls(dateWiseStateDtlsArr);

						if (inputVO.getDisplayType() != null && !inputVO.getDisplayType().trim().isEmpty() && inputVO.getDisplayType().trim().length() > 0) {
							if (inputVO.getDisplayType().trim().equalsIgnoreCase("day")) {

								List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
								resultList = getDayWiseReportDtls(dateWiseTargetAchivementList, dayList);

							} else if (inputVO.getDisplayType().trim().equalsIgnoreCase("week")) {

								Map<String, List<String>> weekAndDaysMap = DateUtilService.getTotalWeeksMapInWeekOrder(fromDate, toDate);
								resultList = getWeekMonthWiseReportDtls(dateWiseTargetAchivementList,weekAndDaysMap);

							} else if (inputVO.getDisplayType().trim().equalsIgnoreCase("month")) {

								Map<String, List<String>> monthMap = getMonthWeekAndDaysList(inputVO.getFromDate(),inputVO.getToDate(), "month");
								resultList = getWeekMonthWiseReportDtls(dateWiseTargetAchivementList, monthMap);
							}
						}

					}

				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getIHHLAchivementProgressDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}

	private List<SwachhBharatMissionIHHLDtlsVO> getDayWiseReportDtls(List<SwachhBharatMissionIHHLDtlsVO> dateWiseTargetAchivementList,List<String> dayList) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			if (dayList != null && dayList.size() > 0) {
				for (String date : dayList) {
					SwachhBharatMissionIHHLDtlsVO rangeVO = new SwachhBharatMissionIHHLDtlsVO();
					rangeVO.setRange(date);
					SwachhBharatMissionIHHLDtlsVO dateDtlsVO = getMatchVO(dateWiseTargetAchivementList, date);
					if (dateDtlsVO != null) {
						rangeVO.setTarget(dateDtlsVO.getTarget());
						rangeVO.setCompleted(dateDtlsVO.getAchivement());
					}
					resultList.add(rangeVO);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured at getDayWiseReportDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}

	private List<SwachhBharatMissionIHHLDtlsVO> getWeekMonthWiseReportDtls(List<SwachhBharatMissionIHHLDtlsVO> dateWiseTargetAchivementList,Map<String, List<String>> weekMap) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			if (weekMap != null && weekMap.size() > 0) {
				for (Entry<String, List<String>> entry : weekMap.entrySet()) {
					SwachhBharatMissionIHHLDtlsVO rangeVO = new SwachhBharatMissionIHHLDtlsVO();
					rangeVO.setRange(entry.getKey());
					//rangeVO.setList(entry.getValue());
					rangeVO.setFromDate(entry.getValue().get(0));
					rangeVO.setToDate(entry.getValue().get(entry.getValue().size()-1));
					if (entry.getValue() != null && entry.getValue().size() > 0) {
						for (String date : entry.getValue()) {
							SwachhBharatMissionIHHLDtlsVO dateDtlsVO = getMatchVO(dateWiseTargetAchivementList, date);
							if (dateDtlsVO != null) {
								rangeVO.setTarget(rangeVO.getTarget()+ dateDtlsVO.getTarget());
								rangeVO.setCompleted(rangeVO.getCompleted()+ dateDtlsVO.getAchivement());
							}
						}
					}
					resultList.add(rangeVO);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occured at getWeekWiseReportDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	private SwachhBharatMissionIHHLDtlsVO getMatchVO(List<SwachhBharatMissionIHHLDtlsVO> list, String date) {
		try {
			if (list == null || list.size() == 0)
				return null;
			for (SwachhBharatMissionIHHLDtlsVO vo : list) {
				if (vo.getRange().equalsIgnoreCase(date)) {
					return vo;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getMatchVO() in SwachhBharatMissionIHHLService class",e);
		}
		return null;
	}
	private LinkedHashMap<String,List<String>> getMonthWeekAndDaysList(String startDate,String endDate,String type){
		LinkedHashMap<String,List<String>> returnDays = new LinkedHashMap<String, List<String>>();
    	try{
		List<String> daysArr = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-yyyy");
		if(type != null && type.trim().equalsIgnoreCase("month")){
		 List<String> mntDays = lightMonitoringDAO.getMonthAndYear(sdf.parse(startDate),sdf.parse(endDate));
			int i = 1;
			for (String string : mntDays) {
				Date dateee = sdf1.parse(string);
				cal.setTime(dateee);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				Date monthStart = cal.getTime();
				
				cal.setTime(dateee);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date monthEnd = cal.getTime();
				 
				cal.setTime(sdf.parse(startDate));
				Date  strDate = cal.getTime();
				cal.setTime(sdf.parse(endDate));
				Date  edDate = cal.getTime();
				
				if(i == 1){
					if(strDate.compareTo(monthStart) > 0){
						monthStart = strDate;
					}
				}
				if(i == mntDays.size()){
					if(monthEnd.compareTo(edDate) > 0){
						monthEnd = edDate;
					}
				}
				
				daysArr = DateUtilService.getDaysBetweenDatesStringFormat(monthStart,monthEnd);
				returnDays.put(string,daysArr);
				i++;
			}
		}
    	}catch(Exception e){
    		LOG.error("Error occured getMonthWeekAndDaysList() method of SwachhBharatMissionIHHLService",e);
    	}
		
		return returnDays;
	}
	
	private List<SwachhBharatMissionIHHLDtlsVO> getDateWiseTargetAndAchivementDtls(JSONArray dateWiseStateDtlsArr) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			for (int i = 0; i < dateWiseStateDtlsArr.length(); i++) {
				SwachhBharatMissionIHHLDtlsVO rangeVO = new SwachhBharatMissionIHHLDtlsVO();
				JSONObject jObj = (JSONObject) dateWiseStateDtlsArr.get(i);
				String dateStr = jObj.has("DATESEQ") ? jObj.getString("DATESEQ") : "";
				if (dateStr != null && dateStr.trim().length() > 2) {
					Date date = sdf.parse(dateStr);
					rangeVO.setRange(sdf1.format(date));
					rangeVO.setTarget(jObj.has("TARGET") ? jObj.getLong("TARGET") : 0l);
					rangeVO.setAchivement(jObj.has("ACHIVED") ? jObj.getLong("ACHIVED") : 0l);
					resultList.add(rangeVO);
				}

			}
		} catch (Exception e) {
			LOG.error("Exception occured at getDateWiseTargetAndAchivementDtls() in SwachhBharatMissionIHHLService class",e);
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
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (inputVO.getFromDate() != null && inputVO.getFromDate().trim().length() > 0 && inputVO.getToDate() != null && inputVO.getToDate().trim().length() > 0) {
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}

			String str = convertingInputVOToString(inputVO);
			ClientResponse response = null;
			if (inputVO.getReportType() != null && inputVO.getReportType().equalsIgnoreCase("status")) {
				response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/IhhlNew/GetIHHLDashBoardUIDetails?",str);
															//http://125.17.121.167/rwsapwebapi/api/IHHLDashBoardUI/GetIHHLDashBoardUIDetails
			} else  {
				response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/GetDateWiseTarAchOverview/GetDateWiseTarAchOverviewDetails",str);
			}
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && output.length() > 0) {
					JSONObject jsonObject = new JSONObject(output);

					if (inputVO.getReportType().equalsIgnoreCase("status")) {
						resultList = getStatusWiseLocationDetails(inputVO,jsonObject);
					} else if (inputVO.getReportType().equalsIgnoreCase("daily")) {
                       resultList  = getDateWiseLocationDetails(inputVO,jsonObject,fromDate,toDate);
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occured at getSwachhBharatMissionLocationWiseDetails() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	private List<SwachhBharatMissionIHHLDtlsVO> getDateWiseLocationDetails(InputVO inputVO, JSONObject jsonObject,Date fromDate,Date toDate) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		 try {
			 Map<String,SwachhBharatMissionIHHLDtlsVO> targetAchivementDtlsMap = getDailyTargetAndAchivemetnDtlsLocationWise(inputVO, jsonObject);//getting target and achivement details location wise
			 
			 if (inputVO.getDisplayType() != null && !inputVO.getDisplayType().trim().isEmpty() && inputVO.getDisplayType().trim().length() > 0) {
					if (inputVO.getDisplayType().trim().equalsIgnoreCase("day")) {

						List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
						resultList = getLocationWiseDayReportDtls(targetAchivementDtlsMap, dayList);

					} else if (inputVO.getDisplayType().trim().equalsIgnoreCase("week")) {

						Map<String, List<String>> weekAndDaysMap = DateUtilService.getTotalWeeksMapInWeekOrder(fromDate, toDate);
						resultList = getLocationWiseWeekMonthReportDtls(targetAchivementDtlsMap,weekAndDaysMap);

					} else if (inputVO.getDisplayType().trim().equalsIgnoreCase("month")) {

						Map<String, List<String>> monthMap = getMonthWeekAndDaysList(inputVO.getFromDate(),inputVO.getToDate(), "month");
						resultList = getLocationWiseWeekMonthReportDtls(targetAchivementDtlsMap, monthMap);
					}
				}
			 
		 } catch (Exception e) {
			 LOG.error("Exception occured at getDateWiseLocationDetails() in SwachhBharatMissionIHHLService class",e);
		 }
		 return resultList;
	}
    private List<SwachhBharatMissionIHHLDtlsVO> getLocationWiseDayReportDtls(Map<String,SwachhBharatMissionIHHLDtlsVO> locationDtlsMap,List<String> dayList) {
    	List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
    	try {
    		 if(locationDtlsMap != null && locationDtlsMap.size() > 0) {
    			 for (Entry<String, SwachhBharatMissionIHHLDtlsVO> locationEntry : locationDtlsMap.entrySet()) {
    				 List<SwachhBharatMissionIHHLDtlsVO> tempList = new ArrayList<>(locationEntry.getValue().getSubList());
    				 locationEntry.getValue().getSubList().clear();
    				 if (dayList != null && dayList.size() > 0 ) {
    					 for (String date : dayList) {
    						 SwachhBharatMissionIHHLDtlsVO targetAchivementDtlsVO = new SwachhBharatMissionIHHLDtlsVO();
    						 targetAchivementDtlsVO.setRange(date);
    							SwachhBharatMissionIHHLDtlsVO dateDtlsVO = getMatchVO(tempList, date);
    							if (dateDtlsVO != null) {
    								targetAchivementDtlsVO.setTarget(dateDtlsVO.getTarget());
    								targetAchivementDtlsVO.setCompleted(dateDtlsVO.getAchivement());
    								targetAchivementDtlsVO.setPercentage(dateDtlsVO.getPercentage());
    								//overall location data
        							locationEntry.getValue().setTarget(locationEntry.getValue().getTarget()+dateDtlsVO.getTarget());
        							locationEntry.getValue().setCompleted(locationEntry.getValue().getCompleted()+dateDtlsVO.getAchivement());
        						}
    							locationEntry.getValue().getSubList().add(targetAchivementDtlsVO);
						}
    				 }
    				 //over all location percentage
    				 locationEntry.getValue().setPercentage(calculatePercantage(locationEntry.getValue().getCompleted(),locationEntry.getValue().getTarget()).toString());
    				 resultList.add(locationEntry.getValue());
    			}
    		 }
    	 } catch (Exception e) {
    		 LOG.error("Exception occured at getLocationWiseDayReportDtls() in SwachhBharatMissionIHHLService class",e);
    	 }
    	return resultList;
    }
    private List<SwachhBharatMissionIHHLDtlsVO> getLocationWiseWeekMonthReportDtls(Map<String,SwachhBharatMissionIHHLDtlsVO> locationDtlsMap,Map<String,List<String>> map) {
    	List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
    	try {
    		 if(locationDtlsMap != null && locationDtlsMap.size() > 0) {
    			 for (Entry<String, SwachhBharatMissionIHHLDtlsVO> locationEntry : locationDtlsMap.entrySet()) {
    				 List<SwachhBharatMissionIHHLDtlsVO> tempList = new ArrayList<>(locationEntry.getValue().getSubList());
    				 locationEntry.getValue().getSubList().clear();
    				 if (map != null && map.size() > 0 ) {
    					 for (Entry<String, List<String>> rangeEntry : map.entrySet()) {
    						 SwachhBharatMissionIHHLDtlsVO targetAchivementDtlsVO = new SwachhBharatMissionIHHLDtlsVO();
    						 targetAchivementDtlsVO.setRange(rangeEntry.getKey());
    						 targetAchivementDtlsVO.setFromDate(rangeEntry.getValue().get(0));
    						 targetAchivementDtlsVO.setToDate(rangeEntry.getValue().get(rangeEntry.getValue().size()-1));
    						   if (rangeEntry.getValue() != null && rangeEntry.getValue().size() > 0) {
    							   for(String date:rangeEntry.getValue()) {
    								   SwachhBharatMissionIHHLDtlsVO dateDtlsVO = getMatchVO(tempList, date);
    	    							if (dateDtlsVO != null) {
    	    								targetAchivementDtlsVO.setTarget(targetAchivementDtlsVO.getTarget()+dateDtlsVO.getTarget());
    	    								targetAchivementDtlsVO.setCompleted(targetAchivementDtlsVO.getCompleted()+dateDtlsVO.getAchivement());
    	    								//over all location count
        	    							locationEntry.getValue().setTarget(locationEntry.getValue().getTarget()+dateDtlsVO.getTarget());
        	    							locationEntry.getValue().setCompleted(locationEntry.getValue().getCompleted()+dateDtlsVO.getAchivement());
    	    							}
    	    					   }
    						   }
    						   //calculating percentage week or month wise
							   targetAchivementDtlsVO.setPercentage(calculatePercantage(targetAchivementDtlsVO.getCompleted(),targetAchivementDtlsVO.getTarget()).toString());
							locationEntry.getValue().getSubList().add(targetAchivementDtlsVO);
    					}
    				 }
    				 //over all location percentage
    				 locationEntry.getValue().setPercentage(calculatePercantage(locationEntry.getValue().getCompleted(),locationEntry.getValue().getTarget()).toString());
    				 resultList.add(locationEntry.getValue());
    			}
    		 }
    	 } catch (Exception e) {
    		 LOG.error("Exception occured at getLocationWiseWeekMonthReportDtls() in SwachhBharatMissionIHHLService class",e);
    	 }
    	return resultList;
    }
	public Map<String, SwachhBharatMissionIHHLDtlsVO> getDailyTargetAndAchivemetnDtlsLocationWise(InputVO inputVO, JSONObject jsonObject) {
		Map<String, SwachhBharatMissionIHHLDtlsVO> locationMap = new HashMap<String, SwachhBharatMissionIHHLDtlsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			JSONArray locationDtlsArr = getJsonArrayBasedOnLocationType(inputVO, jsonObject); //getting JSONArray from jsonObject based on location

			if (locationDtlsArr != null && locationDtlsArr.length() > 0) {
				for (int i = 0; i < locationDtlsArr.length(); i++) {
					JSONObject jObj = (JSONObject) locationDtlsArr.get(i);
					String dateStr = jObj.has("DATESEQ") ? jObj.getString("DATESEQ") : "";
					if (dateStr != null && dateStr.trim().length() > 2) {
						Date date = sdf.parse(dateStr);
						String locationIdStr = null;
						 locationIdStr = getLocationIdByLocationType(inputVO.getSubLocation(), jObj);// getting  locationId
						if (inputVO.getSubLocation() != null && inputVO.getSubLocation().equalsIgnoreCase("mandal")) {
							String districtCode = jObj.has("DID") ? jObj.getString("DID"):"";
							locationIdStr = districtCode+ locationIdStr;
						}
						SwachhBharatMissionIHHLDtlsVO lcatnVO = locationMap.get(locationIdStr);
						if (lcatnVO == null) {
							lcatnVO = new SwachhBharatMissionIHHLDtlsVO();
							
							setBaseLocationByLocationType(jObj, lcatnVO,inputVO.getSubLocation(),inputVO.getReportType());// setting location based on location type
							
							lcatnVO.setSubList(new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0));
							locationMap.put(locationIdStr, lcatnVO);
						}
						SwachhBharatMissionIHHLDtlsVO achivementTargetDltsVO = new SwachhBharatMissionIHHLDtlsVO();
						achivementTargetDltsVO.setRange(sdf1.format(date));
						achivementTargetDltsVO.setTarget(jObj.has("TARGET") ? jObj.getLong("TARGET") : 0l);
						achivementTargetDltsVO.setAchivement(jObj.has("ACHIVED") ? jObj.getLong("ACHIVED") : 0l);
						achivementTargetDltsVO.setPercentage(calculatePercantage(achivementTargetDltsVO.getAchivement(),achivementTargetDltsVO.getTarget()).toString());
						lcatnVO.getSubList().add(achivementTargetDltsVO);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getDailyTargetAndAchivemetnDtlsLocationWise() in SwachhBharatMissionIHHLService class",e);
		}
		return locationMap;
	}
	private String getLocationIdByLocationType(String subLocation,JSONObject jObj) {
		String locationIdStr="";
		try {
			if (subLocation != null && subLocation.trim().equalsIgnoreCase("state")){
				locationIdStr="01";
			} else if (subLocation != null && subLocation.trim().equalsIgnoreCase("district")){
				locationIdStr=jObj.has("DID") ? jObj.getString("DID"):"";
			} else if (subLocation != null && subLocation.trim().equalsIgnoreCase("constituency")){
				locationIdStr=jObj.has("ACODE") ? jObj.getString("ACODE"):"";
			} else if (subLocation != null && subLocation.trim().equalsIgnoreCase("mandal")){
				locationIdStr=jObj.has("MID") ? jObj.getString("MID"):"";
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationIdByLocationType() in SwachhBharatMissionIHHLService class",e);
		}
		return locationIdStr;
	}
	
	private List<SwachhBharatMissionIHHLDtlsVO> getStatusWiseLocationDetails(InputVO inputVO, JSONObject jsonObject) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			
			JSONArray locationDtlsArr = getJsonArrayBasedOnLocationType(inputVO, jsonObject); //getting JSONArray from jsonObject based on location

			if (locationDtlsArr != null && locationDtlsArr.length() > 0) {
				for (int i = 0; i < locationDtlsArr.length(); i++) {
					SwachhBharatMissionIHHLDtlsVO locationVO = new SwachhBharatMissionIHHLDtlsVO();

					JSONObject jObj = (JSONObject) locationDtlsArr.get(i);

					setBaseLocationByLocationType(jObj, locationVO,inputVO.getSubLocation(),inputVO.getReportType());// setting location based on location type
					setBasicInformationBasedOnLocationType(locationVO, jObj);//setting other information
					resultList.add(locationVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getStatusWiseLocationDetails() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	 private void setBaseLocationByLocationType(JSONObject jObj,SwachhBharatMissionIHHLDtlsVO locatioVO,String subLocation,String type){
			try {
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("state")){
					locatioVO.setStateName("Andhra Pradesh");
					locatioVO.setStateCode("01");
					locatioVO.setLocationIdStr("01");
				}
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("district") || subLocation.trim().equalsIgnoreCase("constituency") || subLocation != null && subLocation.trim().equalsIgnoreCase("mandal")){
					if (type != null && type.equalsIgnoreCase("daily")) {
						locatioVO.setDistrictName(jObj.has("DTNAME") ? jObj.getString("DTNAME"):"");
					} else {
						locatioVO.setDistrictName(jObj.has("DNAME") ? jObj.getString("DNAME"):"");	
					}
					locatioVO.setDistrictCode(jObj.has("DID") ? jObj.getString("DID"):"0");
					locatioVO.setLocationIdStr(locatioVO.getDistrictCode());
				}
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("constituency") || subLocation != null && subLocation.trim().equalsIgnoreCase("mandal")){
					locatioVO.setConstName(jObj.has("ANAME") ? jObj.getString("ANAME"):"");
					locatioVO.setConstituencyCode(jObj.has("ACODE") ? jObj.getString("ACODE"):"0");
					locatioVO.setLocationIdStr(locatioVO.getConstituencyCode());
				}
				if (subLocation != null && subLocation.trim().equalsIgnoreCase("mandal")){
					locatioVO.setMandalName(jObj.has("MNAME") ? jObj.getString("MNAME"):"");
					locatioVO.setMandalCode(jObj.has("MID") ? jObj.getString("MID"):"0");
					if (type != null && type.equalsIgnoreCase("daily")) {
						locatioVO.setMandalCode(locatioVO.getDistrictCode()+locatioVO.getMandalCode());
					}
					locatioVO.setLocationIdStr(locatioVO.getMandalCode());
				}
			} catch (Exception e) {
				LOG.error("Exception raised at setBaseLocationByLocationType - SwachhBharatMissionIHHLService service", e);
			}
		}
	 private Double calculatePercantage(Long subCount,Long totalCount){
		    Double d=0.0d;
		    if(subCount.longValue()>0l && totalCount.longValue()==0l){
		    	LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);
		    	return 100d;
		    }
		    

		    if(totalCount.longValue() > 0l){
		       d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();   
		    }
		    return d;
	 }
	 private JSONArray getJsonArrayBasedOnLocationType(InputVO inputVO,JSONObject jsonObject) {
		  JSONArray locationDtlsArr = null;
		  try {
				if (inputVO.getSubLocation() != null && inputVO.getSubLocation().equalsIgnoreCase("State")) {
					locationDtlsArr = jsonObject.getJSONArray("MinisterDBStateFundMngmntData");
				} else if (inputVO.getSubLocation() != null && inputVO.getSubLocation().equalsIgnoreCase("district")){
					locationDtlsArr = jsonObject.getJSONArray("MinisterDBDistrictFundMngmntData");
				} else if (inputVO.getSubLocation() != null && inputVO.getSubLocation().equalsIgnoreCase("constituency")) {
					locationDtlsArr = jsonObject.getJSONArray("MinisterDBAssemblyFundMngmntData");
				} else if (inputVO.getSubLocation() != null && inputVO.getSubLocation().equalsIgnoreCase("mandal")) {
					locationDtlsArr = jsonObject.getJSONArray("MinisterDBMandalFundMngmntData");
				}
		  } catch (Exception e) {
			  LOG.error("Exception raised at getJsonArray - SwachhBharatMissionIHHLService service", e);
		  }
		  return locationDtlsArr;
	 }
	     /**
		 * @author Santosh Kumar Verma
		 * @param InputVO inputVO
		 * @description {This service is used to get Location Details based on category.}
		 * @return List<SwachhBharatMissionIHHLDtlsVO>
		 * @Date 17-10-2017
		 */
	 public List<SwachhBharatMissionIHHLDtlsVO> getLocationDetailsBasedOnCategory(InputVO inputVO) {
		 List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<>(0);
		  try {
			    Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if (inputVO.getFromDate() != null && inputVO.getFromDate().trim().length() > 0 && inputVO.getToDate() != null && inputVO.getToDate().trim().length() > 0) {
					fromDate = sdf.parse(inputVO.getFromDate());
					toDate = sdf.parse(inputVO.getToDate());
				}

				String str = convertingInputVOToString(inputVO);
				ClientResponse response = null;
				if (inputVO.getReportType() != null && inputVO.getReportType().equalsIgnoreCase("status")) {
					Long startTime = System.currentTimeMillis();
					response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/IhhlNew/GetIHHLDashBoardUIDetails?",str);
					Long endTime = System.currentTimeMillis();//kkb  http://125.17.121.167/rwsapwebapi/api/IHHLDashBoardUI/GetIHHLDashBoardUIDetails
					System.out.println(inputVO.getSubLocation()+" status service Time Take "+(endTime-startTime));
				} else  {
					Long startTime = System.currentTimeMillis();
					response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/GetDateWiseTarAchOverview/GetDateWiseTarAchOverviewDetails",str);
					Long endTime = System.currentTimeMillis();
					System.out.println(inputVO.getSubLocation()+" daily service Time Take "+(endTime-startTime));
				}
				 String output = response.getEntity(String.class);
				if (output != null && output.length() > 0) {
					JSONObject jsonObject = new JSONObject(output);

					if (inputVO.getReportType().equalsIgnoreCase("status")) {
						resultList = filterRequiredLocationBasedOnCategory(inputVO,jsonObject);
					} else if (inputVO.getReportType().equalsIgnoreCase("daily")) {
                       resultList  = filterRequiredLocationBasedOnCategoryBasedOnSelectedDate(inputVO,jsonObject,fromDate,toDate);
					}
				}
				
				
		  } catch (Exception e) {
			  LOG.error("Exception raised at getLocationDetailsBasedOnCategory - SwachhBharatMissionIHHLService service", e);
		  }
		  return resultList;
	 }
	 
	private List<SwachhBharatMissionIHHLDtlsVO> filterRequiredLocationBasedOnCategory(InputVO inputVO, JSONObject jsonObject) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {

			JSONArray locationDtlsArr = getJsonArrayBasedOnLocationType(inputVO, jsonObject); // getting JSONArray from jsonObject based on location

			if (locationDtlsArr != null && locationDtlsArr.length() > 0) {
				for (int i = 0; i < locationDtlsArr.length(); i++) {

					SwachhBharatMissionIHHLDtlsVO locationVO = null;
					JSONObject jObj = (JSONObject) locationDtlsArr.get(i);
					Double percentage = Double.parseDouble(jObj.has("ACHIVEMENTPERCENTAGE") ? jObj.getString("ACHIVEMENTPERCENTAGE") : "0");
					if (inputVO.getDisplayType() != null) {
						if (inputVO.getDisplayType().equalsIgnoreCase("A")) {
							if (percentage >= 80d && percentage <= 100d) {
								locationVO = new SwachhBharatMissionIHHLDtlsVO();
								setBaseLocationByLocationType(jObj, locationVO,inputVO.getSubLocation(),inputVO.getReportType());// setting location based on  location type
								setBasicInformationBasedOnLocationType(locationVO, jObj);// setting other information
								resultList.add(locationVO);
							}

						} else if (inputVO.getDisplayType().equalsIgnoreCase("B")) {
							if (percentage >= 60d && percentage < 80d) {
								locationVO = new SwachhBharatMissionIHHLDtlsVO();
								setBaseLocationByLocationType(jObj, locationVO,inputVO.getSubLocation(),inputVO.getReportType());
								setBasicInformationBasedOnLocationType(locationVO, jObj);
								resultList.add(locationVO);
							}
						} else if (inputVO.getDisplayType().equalsIgnoreCase("C")) {
							if (percentage >= 40d && percentage < 60d) {
								locationVO = new SwachhBharatMissionIHHLDtlsVO();
								setBaseLocationByLocationType(jObj, locationVO,inputVO.getSubLocation(),inputVO.getReportType());
								setBasicInformationBasedOnLocationType(locationVO, jObj);
								resultList.add(locationVO);
							}
						} else if (inputVO.getDisplayType().equalsIgnoreCase("D")) {
							if (percentage > 0d && percentage < 40) {
								locationVO = new SwachhBharatMissionIHHLDtlsVO();
								setBaseLocationByLocationType(jObj, locationVO,inputVO.getSubLocation(),inputVO.getReportType());
								setBasicInformationBasedOnLocationType(locationVO, jObj);
								resultList.add(locationVO);
							}
						}

					}

				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at filterRequiredLocationBasedOnCategory() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}

	private List<SwachhBharatMissionIHHLDtlsVO> filterRequiredLocationBasedOnCategoryBasedOnSelectedDate(InputVO inputVO, JSONObject jsonObject, Date fromDate, Date toDate) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {

			Map<String, SwachhBharatMissionIHHLDtlsVO> targetAchivementDtlsMap = getDailyTargetAndAchivemetnDtlsLocationWise(inputVO, jsonObject);

			if (inputVO.getDisplayType() != null && !inputVO.getDisplayType().trim().isEmpty() && inputVO.getDisplayType().trim().length() > 0) {

					List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
					List<SwachhBharatMissionIHHLDtlsVO> returnList = getLocationWiseDayReportDtls(targetAchivementDtlsMap, dayList);

					if (returnList != null && returnList.size() > 0) {
						for (SwachhBharatMissionIHHLDtlsVO swachhBharatMissionIHHLDtlsVO : returnList) {
							if (swachhBharatMissionIHHLDtlsVO != null && swachhBharatMissionIHHLDtlsVO.getPercentage() != null) {
								Double percentage = Double.parseDouble(swachhBharatMissionIHHLDtlsVO.getPercentage());
								if (inputVO.getDisplayType() != null) {
									if (inputVO.getDisplayType().equalsIgnoreCase("A")) {
										if (percentage >= 80d) {//&& percentage <= 100d
											resultList.add(swachhBharatMissionIHHLDtlsVO);
										}

									} else if (inputVO.getDisplayType().equalsIgnoreCase("B")) {
										if (percentage >= 60d && percentage < 80d) {
											resultList.add(swachhBharatMissionIHHLDtlsVO);
										}
									} else if (inputVO.getDisplayType().equalsIgnoreCase("C")) {
										if (percentage >= 40d && percentage < 60d) {
											resultList.add(swachhBharatMissionIHHLDtlsVO);
										}
									} else if (inputVO.getDisplayType().equalsIgnoreCase("D")) {
										if (percentage >= 0d && percentage < 40) {
											resultList.add(swachhBharatMissionIHHLDtlsVO);
										}
									}

								}
							}
						}
					}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at filterRequiredLocationBasedOnCategoryBasedOnSelectedDate() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	 
	 private void setBasicInformationBasedOnLocationType(SwachhBharatMissionIHHLDtlsVO locationVO,JSONObject jObj) {
		  try {
				locationVO.setTarget(jObj.has("TARGET") ? jObj.getLong("TARGET") : 0l);
				//locationVO.setGrounded(jObj.has("GROUNDED") ? jObj.getLong("GROUNDED") : 0l);
				locationVO.setNoTGrounded(jObj.has("NOTGROUNDED") ? jObj.getLong("NOTGROUNDED") : 0l);
				locationVO.setInProgress(jObj.has("INPROGRESS") ? jObj.getLong("INPROGRESS") : 0l);
				locationVO.setCompleted(jObj.has("COMPLETEED") ? jObj.getLong("COMPLETEED") : 0l);
				locationVO.setPercentage(jObj.has("ACHIVEMENTPERCENTAGE") ? jObj.getString("ACHIVEMENTPERCENTAGE") : "");
				locationVO.setSanctioned(jObj.has("SANCTIONED") ? jObj.getLong("SANCTIONED") : 0l);
				locationVO.setExcessben(jObj.has("EXCESSBEN") ? jObj.getLong("EXCESSBEN") : 0l);
				locationVO.setNotSanctioned(locationVO.getTarget()-locationVO.getSanctioned());
				locationVO.setNotStarted(locationVO.getSanctioned()-(locationVO.getInProgress()+locationVO.getCompleted()));
				locationVO.setSanctionpercentage(calculatePercantage(locationVO.getSanctioned(),locationVO.getTarget()).toString());
				locationVO.setNotSanctionPercentage(calculatePercantage(locationVO.getNotSanctioned(),locationVO.getTarget()).toString());
				locationVO.setNotStaredPercentage(calculatePercantage(locationVO.getNotStarted(),locationVO.getSanctioned()).toString());
				locationVO.setInProgressPerc(calculatePercantage(locationVO.getInProgress(),locationVO.getSanctioned()).toString());
				locationVO.setCompletedPerce(calculatePercantage(locationVO.getCompleted(),locationVO.getSanctioned()).toString());

		  } catch (Exception e) {
			  LOG.error("Exception occured at setBasicInformationBasedOnLocationType() in SwachhBharatMissionIHHLService class",e);
		  }
	 }
	 
	private String convertingInputVOToString(InputVO inputVO){
		String str = "";
		try {
			str = "{";
			
			if (inputVO.getReportType() != null && inputVO.getReportType().equalsIgnoreCase("daily")) {
				if(inputVO.getFromDate() != null )
					str += "\"fromdate\" : \""+inputVO.getFromDate()+"\",";
				if(inputVO.getToDate() != null)
					str += "\"todate\" : \""+inputVO.getToDate()+"\",";
				if(inputVO.getLocation() != null)
					str += "\"Location\" : \""+inputVO.getLocation()+"\",";
				 if(inputVO.getLocationId() != null)
					str += "\"LocationID\" : \""+inputVO.getLocationId()+"\",";
				if(inputVO.getSubLocation() != null)
					str += "\"SUBLOCATION\" : \""+inputVO.getSubLocation()+"\",";
				
			} else {
				if(inputVO.getLocation() != null)
					str += "\"Location\" : \""+inputVO.getLocation()+"\",";
				 if(inputVO.getLocationId() != null)
					str += "\"LocationID\" : \""+inputVO.getLocationId()+"\",";
				if(inputVO.getSubLocation() != null)
					str += "\"SubLocation\" : \""+inputVO.getSubLocation()+"\",";
			}
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception Occured in convertingInputVOToString() method, Exception - ",e);
		}
		return str;
	}
}
