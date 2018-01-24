package com.itgrids.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.BioAuthSoapProxy;

import com.itgrids.dao.IBiometricHolidayDAO;
import com.itgrids.dto.BioMetricDashBoardDtlsVO;
import com.itgrids.service.IBioMetricService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;

@Service
@Transactional
public class BioMetricService implements IBioMetricService {

	private static final Logger LOG = Logger.getLogger(BioMetricService.class);
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	@Autowired
	private IBiometricHolidayDAO  biometricHolidayDAO;
	
	
	/**
	 * @author Santosh Kumar Verma
	 * @param String fromDate 
	 * @param String toDate 
	 * @description {This service is used to biometric employee overview details.}
	 * @return List<BioMetricDashBoardDtlsVO>
	 * @Date 4-11-2017
	 */
	public BioMetricDashBoardDtlsVO getBioMetricDashboardOverViewDtls(String deptCode) {
		BioMetricDashBoardDtlsVO resultVO = new BioMetricDashBoardDtlsVO();
		try {
			BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
			
			String totalEmpJsonString = bioAuthSoapObj.registeredEmployees(deptCode);
			String todayPresentEmpJsonString = bioAuthSoapObj.presentToday(deptCode);
			
			resultVO.setTotalCount(Long.valueOf(getEmployeeIds(totalEmpJsonString).size()));//getting total employee
			resultVO.setPresentCount(Long.valueOf(getEmployeeIds(todayPresentEmpJsonString).size()));//getting today total present employee
			resultVO.setAbsentCount(resultVO.getTotalCount()- resultVO.getPresentCount());
			
		} catch (Exception e) {
			LOG.error("Exception raised at getBioMetricDashboardOverViewDtls - BioMetricService service",e);
		}
		return resultVO;
	}

	private Set<String> getEmployeeIds(String jsonString) {
		Set<String> empIdSet = new HashSet<String>(0);
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONArray jsonDataArray = new JSONArray(jsonString);
				if (jsonDataArray != null && jsonDataArray.length() > 0) {
					for (int i = 0; i < jsonDataArray.length(); i++) {
						JSONObject jsonObj = (JSONObject) jsonDataArray.get(i);
							if (jsonObj.has("EMP_ID") && jsonObj.get("EMP_ID").toString().length() > 0) {
								empIdSet.add(jsonObj.get("EMP_ID").toString());
							}
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeIds - BioMetricService service",e);
		}
		return empIdSet;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param String fromDate 
	 * @param String toDate 
	 * @description {This service is used to get time period wise employee attendance details.}
	 * @return List<BioMetricDashBoardDtlsVO>
	 * @Date 4-11-2017
	 */
	public List<BioMetricDashBoardDtlsVO> getEmployeeAttendenceTimePeriodWise(String deptCode,String todayDate) {
		List<BioMetricDashBoardDtlsVO> resultList = new ArrayList<BioMetricDashBoardDtlsVO>(0);
		 JSONObject jsonObject=null;
		 try {
			
			 String[] tempArr = {"Before - 10:00","10:00 - 10:30","10:30 - 11:00","After - 11:00"};
			 BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
			
			 String jsonString = bioAuthSoapObj.employeeIntimeStatistics(todayDate);
			 if(deptCode.equals("27001701024")){
			  jsonObject = filterRequiredObject(jsonString);
			 }
			 else if(deptCode.equals("27001701035")){
				 jsonObject = filterRequiredObjectsPr(jsonString);
			  }
				 for(String timePeriod:tempArr) {
					 BioMetricDashBoardDtlsVO timePeriodVO = new BioMetricDashBoardDtlsVO();
					 timePeriodVO.setName(timePeriod);
					 if (jsonObject != null && jsonObject.length() > 0) {
						 Long totalCount = getEmployeeAttendedCountTimePeriodWise(timePeriod,jsonObject);//getting employee attended count time period wise
						 timePeriodVO.setTotalCount(totalCount);
					 }
					 resultList.add(timePeriodVO);
				 }
				 Format formatter = new SimpleDateFormat("hh:mm:ss a");
				 SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
				 if(jsonObject != null && jsonObject.length() > 0 && jsonObject.has("AVERAGEIN_TIME")){
					 String dateStr=jsonObject.getString("AVERAGEIN_TIME");
					 if(dateStr != null && dateStr.length() > 0){
						  Date date = sdf1.parse(dateStr.trim());
						  resultList.get(0).setInTime(formatter.format(date));
					 }
					}
				 else{
					 resultList.get(0).setInTime("0");
				 }
				 JSONObject outTimejsonObject=null;		 
				 String outTimeJsonString = bioAuthSoapObj.employeeOuttimeStatistics(todayDate);
				 if(deptCode.equals("27001701024")){
				 outTimejsonObject = filterRequiredObject(outTimeJsonString);
				 }
				 else if(deptCode.equals("27001701035")){
				 outTimejsonObject = filterRequiredObjectsPr(outTimeJsonString);
				 }
				 if(outTimejsonObject != null && outTimejsonObject.length() > 0 && outTimejsonObject.has("AVERAGEOUT_TIME")){
					 String dateStr=outTimejsonObject.getString("AVERAGEOUT_TIME");
					 if(dateStr != null && dateStr.length() > 0){
						 Date date = sdf1.parse(dateStr.trim()); 
						 resultList.get(0).setOutTime(formatter.format(date));
					 }
					 }	
				 else{
					 resultList.get(0).setOutTime("0");
				 }
						 
		     } catch (Exception e) {
			 LOG.error("Exception raised at getEmployeeAttendenceTimePeriodWise - BioMetricService service",e);
		 }
		 return resultList;
	}
    private JSONObject filterRequiredObject(String jsonString) {
    	 try {
    		  if (jsonString != null && jsonString.trim().length() > 0) {
    			  JSONArray finalJSONArray = new JSONArray(jsonString);
    			   if (finalJSONArray != null && finalJSONArray.length() > 0) {
    				   for (int i = 0; i < finalJSONArray.length(); i++) {
    					    JSONObject jsonObj = (JSONObject) finalJSONArray.get(i);
    					    String deptName = jsonObj.has("DEPARTMENT") ? jsonObj.getString("DEPARTMENT") : "";
    					    if (deptName.trim().equalsIgnoreCase("INFORMATION TECHNOLOGY, ELECTRONICS AND COMMUNICATIONS")) {
    					    	return jsonObj;
    					    }
    				   }
     			   }
    		  }
    		  
    		 
    	 } catch (Exception e) {
    		 LOG.error("Exception raised at filterRequiredObject - BioMetricService service",e);
    	 }
    	 return null;
    }
	private Long getEmployeeAttendedCountTimePeriodWise(String timePeriod,JSONObject jsonObject) {
		Long empCount = 0l;
		try {
			switch (timePeriod) {
			case "Before - 10:00":
				empCount = jsonObject.has("BEFORE10AM") ? jsonObject.getLong("BEFORE10AM") : 0l;
				break;
			case "10:00 - 10:30":
				empCount = jsonObject.has("from10To10_30AM") ? jsonObject.getLong("from10To10_30AM") : 0l;
				break;
			case "10:30 - 11:00":
				empCount = jsonObject.has("from10_30To1100AM") ? jsonObject.getLong("from10_30To1100AM") : 0l;
				break;
			case "After - 11:00":
				empCount = jsonObject.has("AFTER11AM") ? jsonObject.getLong("AFTER11AM") : 0l;
				break;
			default:
				empCount = 0l;
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeAttendenceTimePeriodWise - BioMetricService service",e);
		}
		return empCount;
	}
   
	
	/**
	 * @author Santosh Kumar Verma
	 * @param String fromDate 
	 * @param String toDate 
	 * @description {This service is used to get day wise employee attendance details.}
	 * @return List<BioMetricDashBoardDtlsVO>
	 * @Date 4-11-2017
	 */
	public List<BioMetricDashBoardDtlsVO> getDateWiseEmployeeAttendenceDetails(String fromDate, String toDate,String deptCode) {
		List<BioMetricDashBoardDtlsVO> resultList = new ArrayList<BioMetricDashBoardDtlsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date[] dateArr = getDates(fromDate, toDate,sdf);
			if (dateArr != null && dateArr.length > 0) {
				
				 BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
				 
				 String totalRegisgrateEmpStr = bioAuthSoapObj.registeredEmployees(deptCode);
				 String empJsonString = bioAuthSoapObj.empfromtodateAttendance(deptCode, fromDate,toDate);
				 List<Object[]> holiDayObjList = biometricHolidayDAO.getBioMetricHolidays(dateArr[0],dateArr[1]);
				 
				 Map<String,Set<String>> empAttendanceMap = getDateWiseEmployeeAttendanceDtls(empJsonString);//getting employee attendance details date wise
				 Map<String,BioMetricDashBoardDtlsVO> holidayMap = getHolidayDetails(holiDayObjList);//getting holiday list
				 Long totalEmployeeCount = Long.valueOf(getEmployeeIds(totalRegisgrateEmpStr).size());//getting total employee
				 
				 List<String> dateList = DateUtilService.getDaysBetweenDatesStringFormat(dateArr[0], dateArr[1]);
			
				if (dateList != null && dateList.size() > 0) {
					Long order = 0l;
					for (String date : dateList) {
						
						String dayOfWeek = getDayOfWeekNameByDate(sdf, date);
						Set<String> empIdSet = empAttendanceMap.get(sdf1.format(sdf.parse(date)));
						BioMetricDashBoardDtlsVO holidDayVO = holidayMap.get(sdf1.format(sdf.parse(date)));
						
						    BioMetricDashBoardDtlsVO dayVO = new BioMetricDashBoardDtlsVO();
							dayVO.setName(date);
							dayVO.setDayOfWeek(dayOfWeek);
							dayVO.setType((holidDayVO != null ) ? holidDayVO.getType():"");
							dayVO.setOrder(order < 9 ? "0" + ++order : ""+ ++order);
							dayVO.setTotalCount(totalEmployeeCount);
							dayVO.setPresentCount((empIdSet != null && empIdSet.size() > 0) ? empIdSet.size() : 0l);
							if (dayVO.getTotalCount() > dayVO.getPresentCount()) {
								dayVO.setAbsentCount(dayVO.getTotalCount()- dayVO.getPresentCount());	
							}
							

						resultList.add(dayVO);
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getDateWiseEmployeeAttendenceDetails - BioMetricService service",e);
		}
		return resultList;
	}

	private Map<String, Set<String>> getDateWiseEmployeeAttendanceDtls(String jsonString) {
		Map<String, Set<String>> empAttendanceMap = new HashMap<>(0);
		try {
			if (jsonString != null && jsonString.trim().length() > 0) {
				JSONArray finalArray = new JSONArray(jsonString);
				if (finalArray != null && finalArray.length() > 0) {
					for (int i = 0; i < finalArray.length(); i++) {
						JSONObject jsonObj = (JSONObject) finalArray.get(i);
						String dateStr = jsonObj.has("date") ? jsonObj.getString("date").trim() : null;
						String empId = jsonObj.has("EMP_CODE") ? jsonObj.getString("EMP_CODE") : null;
						if (dateStr != null && dateStr.trim().length() > 0) {
							Set<String> empIdSet = empAttendanceMap.get(dateStr.substring(0, 10));
							if (empIdSet == null) {
								empIdSet = new HashSet<String>(0);
								empAttendanceMap.put(dateStr.substring(0, 10), empIdSet);
							}
							empIdSet.add(empId);
						}

					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getDateWiseEmployeeAttendanceDtls - BioMetricService service",e);
		}
		return empAttendanceMap;
	}

	private Map<String, BioMetricDashBoardDtlsVO> getHolidayDetails(List<Object[]> objList) {
		Map<String, BioMetricDashBoardDtlsVO> holidayMap = new HashMap<String, BioMetricDashBoardDtlsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String dateStr = (param[0] != null && param[0].toString().length() > 0) ? sdf.format((Date) param[0]) : null;
					BioMetricDashBoardDtlsVO holidDayVO = holidayMap.get(dateStr);
					if (holidDayVO == null) {
						holidDayVO = new BioMetricDashBoardDtlsVO();
						holidDayVO.setType(commonMethodsUtilService.getStringValueForObject(param[1]) + " Holiday");
						holidDayVO.setName(dateStr);
						holidayMap.put(holidDayVO.getName(), holidDayVO);
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getHolidayDetails - BioMetricService service",e);
		}
		return holidayMap;
	}

	/**
	 * @author Santosh Kumar Verma
	 * @param String fromDate 
	 * @param String toDate 
	 * @description {This service is used to get employee wise attendance count and basic employee information.}
	 * @return List<BioMetricDashBoardDtlsVO>
	 * @Date 4-11-2017
	 */
	public List<BioMetricDashBoardDtlsVO> getEmployeeWiseAttendenceCount(String fromDate, String toDate,String deptCode) {
		List<BioMetricDashBoardDtlsVO> resultList = new ArrayList<BioMetricDashBoardDtlsVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date[] dateArr = getDates(fromDate, toDate,sdf);
			
			if (dateArr != null && dateArr.length > 0) {

				 BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
				 
				 String empJsonString = bioAuthSoapObj.empfromtodateAttendance(deptCode, fromDate,toDate);
				 List<Object[]> holiDayObjList = biometricHolidayDAO.getBioMetricHolidays(dateArr[0],dateArr[1]);
				 List<String> dateList = DateUtilService.getDaysBetweenDatesStringFormat(dateArr[0], dateArr[1]);
				 
				 Map<String,BioMetricDashBoardDtlsVO> holidayMap = getHolidayDetails(holiDayObjList);
				 Set<String> workingDays = getWorkingDays(holidayMap.keySet(), dateList);
				 Map<String,BioMetricDashBoardDtlsVO> empDtlsMap = getDateWiseEmployeeAttendanceDetails(empJsonString,"AllEmployee",null);
  				 
				if (empDtlsMap != null && empDtlsMap.size() > 0) {
					for (Entry<String, BioMetricDashBoardDtlsVO> entry : empDtlsMap.entrySet()) {
						BioMetricDashBoardDtlsVO employeeVO = new BioMetricDashBoardDtlsVO();
						employeeVO.setEmpId(entry.getValue().getEmpId());
						employeeVO.setType(entry.getValue().getType());
						employeeVO.setDesignation(entry.getValue().getDesignation());
						employeeVO.setName(entry.getValue().getName());
						employeeVO.setTotalWorkingDays(Long.valueOf(workingDays.size()));
						employeeVO.setSubList1(entry.getValue().getSubList1());
						employeeVO.setPresentCount(Long.valueOf(entry.getValue().getSet().size()));
						entry.getValue().getSet().clear();
						if (employeeVO.getTotalWorkingDays() > employeeVO.getPresentCount()) {
							employeeVO.setAbsentCount(employeeVO.getTotalWorkingDays() - employeeVO.getPresentCount());	
						}
						
						resultList.add(employeeVO);

					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeWiseAttendenceCount - BioMetricService service",e);
		}
		return resultList;
	}

	private Set<String> getWorkingDays(Set<String> holidaySet,List<String> dateList) {
		Set<String> workingDaySet = new HashSet<String>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if (dateList != null && dateList.size() > 0) {
				for (String date : dateList) {
					String tempDate = sdf1.format(sdf.parse(date));
					String dayOfWeek = getDayOfWeekNameByDate(sdf, date);
					if (!holidaySet.contains(tempDate) && !dayOfWeek.equalsIgnoreCase("Sunday") && !dayOfWeek.equalsIgnoreCase("Saturday") ) {
						workingDaySet.add(date);
					}
				}

			}

		} catch (Exception e) {
			LOG.error("Exception raised at getWorkingDays - BioMetricService service",e);
		}
		return workingDaySet;
	}
	private Map<String,BioMetricDashBoardDtlsVO> getDateWiseEmployeeAttendanceDetails(String jsonString,String filterType,String empCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		Map<String,BioMetricDashBoardDtlsVO> empAttendanceMap = new HashMap<String,BioMetricDashBoardDtlsVO>(); 
		try {
			 if (jsonString != null && jsonString.trim().length() > 0) {
				 JSONArray finalArray = new JSONArray(jsonString);
				 if (finalArray != null && finalArray.length() > 0) {
					 for(int i = 0; i < finalArray.length() ;i++) {
						 JSONObject jsonObj = (JSONObject) finalArray.get(i);
						 String employeeCode = jsonObj.getString("EMP_CODE");
						 
						  if (filterType.equalsIgnoreCase("individualEmployee") && empCode != null && !empCode.equalsIgnoreCase(employeeCode)) { 
							  continue;
						  }
						  
						  BioMetricDashBoardDtlsVO employeeVO = empAttendanceMap.get(employeeCode);
						   if (employeeVO == null ) {
							   employeeVO = new BioMetricDashBoardDtlsVO();
							   employeeVO.setEmpId(employeeCode);
							   employeeVO.setType(jsonObj.has("category") ? jsonObj.getString("category") : "");
							   employeeVO.setDesignation(jsonObj.has("DESIGNATION") ? jsonObj.getString("DESIGNATION") : "");
							   employeeVO.setName(jsonObj.has("Employee_Name") ? jsonObj.getString("Employee_Name") : "");
							   employeeVO.setSubList1(getStaticTemplate());//setting template
							   employeeVO.setSet(new HashSet<String>());
							   empAttendanceMap.put(employeeVO.getEmpId(), employeeVO);
						   }
						   
						   String dateStr = jsonObj.has("date") ? jsonObj.getString("date") : null;
						   String inTime = jsonObj.has("IN_TIME") ? jsonObj.getString("IN_TIME") : null;
						   if (dateStr != null && dateStr.trim().length() > 0) {
							   employeeVO.getSet().add(dateStr.substring(0, 10));
						   }
						   BioMetricDashBoardDtlsVO timeMatchVO = getTimeMatchVO(employeeVO.getSubList1(),inTime,sdf);
						    if (timeMatchVO != null ) {
						    	timeMatchVO.setTotalCount(timeMatchVO.getTotalCount()+1);
						    }
					 }
				 }
			 }
			 
		 } catch (Exception e) {
			 LOG.error("Exception raised at getDateWiseEmployeeAttendanceDetails - BioMetricService service",e);
		 }
		return empAttendanceMap;
		 
	}

	private BioMetricDashBoardDtlsVO getTimeMatchVO(List<BioMetricDashBoardDtlsVO> subList, String inTime,SimpleDateFormat sdf) {

		try {
			Long inTimeInMilisecond = null;

			if (inTime != null && inTime.trim().length() > 0) {
				inTimeInMilisecond = sdf.parse(inTime.substring(0, inTime.length()-2)+" "+inTime.substring(inTime.length()-2, inTime.length())).getTime();
			}
			if (subList == null || subList.size() == 0)
				return null;

			if (inTimeInMilisecond != null ) {
				for (BioMetricDashBoardDtlsVO bioMetricDashBoardDtlsVO : subList) {
					String[] tempArr = bioMetricDashBoardDtlsVO.getName().split("-");
					if (tempArr[0].trim().equalsIgnoreCase("Before")) {
						if (inTimeInMilisecond < bioMetricDashBoardDtlsVO.getFromTimeMiliSecond()) {
							return bioMetricDashBoardDtlsVO;
						}
					} else if (tempArr[0].trim().equalsIgnoreCase("After")) {
						if (inTimeInMilisecond > bioMetricDashBoardDtlsVO.getFromTimeMiliSecond()) {
							return bioMetricDashBoardDtlsVO;
						}
					} else {
						if (inTimeInMilisecond >= bioMetricDashBoardDtlsVO.getFromTimeMiliSecond() && inTimeInMilisecond <= bioMetricDashBoardDtlsVO.getToTimeMiliSecond()) {
							return bioMetricDashBoardDtlsVO;
						}
					}
				}
			}
			return null;
	
		} catch (Exception e) {
			LOG.error("Exception raised at getTimeMatchVO - BioMetricService service",e);
		}
		return null;
	}
	private List<BioMetricDashBoardDtlsVO> getStaticTemplate() {
		List<BioMetricDashBoardDtlsVO> resultList = new ArrayList<BioMetricDashBoardDtlsVO>();
		try {
			String[] templateArr = {"Before - 10:00","10:00 - 10:30","10:30 - 11:00","After - 11:00"};
				 for(String timePeriod:templateArr) {
					 Date[] dateArr = getTimes(timePeriod);
					 String[] tempArr = timePeriod.split("-");
					 BioMetricDashBoardDtlsVO timePeriodVO = new BioMetricDashBoardDtlsVO();
					 timePeriodVO.setName(timePeriod);
					 if (tempArr[0].trim().equalsIgnoreCase("Before") ||tempArr[0].trim().equalsIgnoreCase("After") ) { 
						 timePeriodVO.setFromTimeMiliSecond(dateArr[0].getTime());
					 } else {
						 timePeriodVO.setFromTimeMiliSecond(dateArr[0].getTime());
						 timePeriodVO.setToTimeMiliSecond(dateArr[1].getTime());
					 }
					 resultList.add(timePeriodVO);
				 }
		} catch (Exception e) {
			 LOG.error("Exception raised at getStaticTemplate - BioMetricService service",e);
		}
		return resultList;
	} 
	/**
	 * @author Santosh Kumar Verma
	 * @param String fromDate 
	 * @param String toDate 
	 * @param String empId 
	 * @description {This service is used to get individual employee attendance details.}
	 * @return BioMetricDashBoardDtlsVO
	 * @Date 6-11-2017
	 */
	public BioMetricDashBoardDtlsVO getIndividualEmployeeAttendenceDetails(String fromDate, String toDate,String empId,String deptCode) {
		BioMetricDashBoardDtlsVO resultVO = new BioMetricDashBoardDtlsVO();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date[] dateArr = getDates(fromDate, toDate,sdf);
			if (dateArr != null && dateArr.length > 0) {
				BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
				 
				 String empJsonString = bioAuthSoapObj.empfromtodateAttendance(deptCode, fromDate,toDate);
				 List<Object[]> holiDayObjList = biometricHolidayDAO.getBioMetricHolidays(dateArr[0],dateArr[1]);
				 List<String> dateList = DateUtilService.getDaysBetweenDatesStringFormat(dateArr[0], dateArr[1]);
				 
				 Map<String,BioMetricDashBoardDtlsVO> holidayMap = getHolidayDetails(holiDayObjList);
				 Set<String> nonWorkingDays = getNonWorkingDays(holidayMap,dateList);
				 Set<String> workingDays = getWorkingDays(holidayMap.keySet(), dateList);
				 Map<String,BioMetricDashBoardDtlsVO> empDtlsMap = getDateWiseEmployeeAttendanceDetails(empJsonString,"individualEmployee",empId);
				 List<BioMetricDashBoardDtlsVO> empAttendencList = getEmployeeAttendenceDetails(dateList,(empDtlsMap != null && empDtlsMap.size() > 0) ? empDtlsMap.get(empId).getSet():null,holidayMap);
				
				if (empDtlsMap != null && empDtlsMap.size() > 0) {
					resultVO.setEmpId(empDtlsMap.get(empId).getEmpId());
					resultVO.setType(empDtlsMap.get(empId).getType());
					resultVO.setDesignation(empDtlsMap.get(empId).getDesignation());
					resultVO.setName(empDtlsMap.get(empId).getName());
					resultVO.setSubList1(empAttendencList);// setting date wise attendance
					resultVO.setSubList2(empDtlsMap.get(empId).getSubList1());// setting time period wise details
					resultVO.setTotalWorkingDays(Long.valueOf(workingDays.size()));

					Set<String> presentDaysSet = empDtlsMap.get(empId).getSet();
					// calculating non working present days
					if (empDtlsMap != null && presentDaysSet != null && nonWorkingDays != null) {
						Iterator<String> iterator = presentDaysSet.iterator();
						while (iterator.hasNext()) {
							String presentDay = iterator.next();
							if (nonWorkingDays.contains(presentDay)) {
								resultVO.setNonWorkingDayPresent(resultVO.getNonWorkingDayPresent() + 1);
								iterator.remove();
							}
						}
					}
					resultVO.setPresentCount(Long.valueOf(presentDaysSet.size()));
					if (resultVO.getTotalWorkingDays() > resultVO.getPresentCount()) {
						resultVO.setAbsentCount(resultVO.getTotalWorkingDays()- resultVO.getPresentCount());
						resultVO.setAbsentCount(resultVO.getAbsentCount()- resultVO.getNonWorkingDayPresent());
					}
				}

				if (nonWorkingDays != null) {
					resultVO.setNonWorkingDays(Long.valueOf(nonWorkingDays.size()));
				}

			}

		} catch (Exception e) {
			LOG.error("Exception raised at getIndividualEmployeeAttendenceDetails - BioMetricService service",e);
		}
		return resultVO;
	}
	private List<BioMetricDashBoardDtlsVO> getEmployeeAttendenceDetails(List<String> dateList,Set<String> empAttendenceDays,Map<String,BioMetricDashBoardDtlsVO> holidayMap) {
		List<BioMetricDashBoardDtlsVO>  empAttendenceList = new ArrayList<>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if (dateList != null && dateList.size() > 0) {
				Long order = 0l;
				for (String date : dateList) {
					    String dayOfWeek = getDayOfWeekNameByDate(sdf, date);
					    BioMetricDashBoardDtlsVO holidayVO = holidayMap.get(sdf1.format(sdf.parse(date)));
					    BioMetricDashBoardDtlsVO dayVO = new BioMetricDashBoardDtlsVO();
						dayVO.setName(date);
						dayVO.setDayOfWeek(dayOfWeek);
						dayVO.setType((holidayVO != null ) ? holidayVO.getType() : "");
						dayVO.setOrder(order < 9 ? "0" + ++order : ""+ ++order);
						String status = getPresentStatusByMatchingDate(empAttendenceDays, sdf1.format(sdf.parse(date)));
						dayVO.setIsPresent(status);
						dayVO.setTotalCount(1l);
						if (status.equalsIgnoreCase("No")) {
							dayVO.setAbsentCount(1l);
						} else if (status.equalsIgnoreCase("yes")) {
							dayVO.setPresentCount(1l);
						}
					   empAttendenceList.add(dayVO);
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeAttendenceDetails - BioMetricService service",e);
		}
		return empAttendenceList;
	}

	private String getPresentStatusByMatchingDate(Set<String> empAttendenceDays,String dateStr) {
		String isPresent = "No";
		try {
			if (empAttendenceDays != null && empAttendenceDays.size() > 0) {
				for (String date : empAttendenceDays) {
					if (date != null) {
						if (date.equalsIgnoreCase(dateStr)) {
							isPresent = "yes";
							return isPresent;
						}
					}

				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getPresentStatusByMatchingDate - BioMetricService service",e);
		}
		return isPresent;

	}
    private Set<String> getNonWorkingDays(Map<String,BioMetricDashBoardDtlsVO> holidayMap, List<String> dateList) {
    	 try {
    		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    		 if (holidayMap != null) {
    			 Set<String> nonWokringDaysSet = new HashSet<String>(holidayMap.keySet());
    			  if (dateList != null ) {
    				  for (String date : dateList) {
    				      String dayOfWeek = getDayOfWeekNameByDate(sdf, date);
						 if (dayOfWeek.equalsIgnoreCase("Sunday") || dayOfWeek.equalsIgnoreCase("Saturday")) {
							 nonWokringDaysSet.add(sdf1.format(sdf.parse(date)));
						 }
					}
    			  }
    			  return nonWokringDaysSet;
    		 }
    		 
    	 } catch (Exception e) {
    		 LOG.error("Exception raised at getNonWorkingDays - BioMetricService service",e);
    	 }
    	 return null;
    }
    /**
	 * @author Santosh Kumar Verma
	 * @param String deptCode 
	 * @param String employeeType 
	 * @description {This service is used to get individual employee attendance details.}
	 * @return List<BioMetricDashBoardDtlsVO>
	 * @Date 10-11-2017
	 */
    public List<BioMetricDashBoardDtlsVO> getEmployeeDetailsByEmployeeType(String deptCode,String employeeType) {
    	List<BioMetricDashBoardDtlsVO> resultList = new ArrayList<BioMetricDashBoardDtlsVO>(0);
    	 try {
    		 BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
    		 if (employeeType != null && employeeType.equalsIgnoreCase("totalEmployee")) {
    			  String totalEmpJsonString = bioAuthSoapObj.registeredEmployees(deptCode);
    			  resultList = getEmployeeDetails(totalEmpJsonString,null,employeeType);
    		 } else if (employeeType != null && employeeType.equalsIgnoreCase("todayPresentEmployee")) {
    			 String todayPresentEmpJsonString = bioAuthSoapObj.presentToday(deptCode);
    			 resultList = getEmployeeDetails(todayPresentEmpJsonString,null,employeeType);
    		 } else if (employeeType != null && employeeType.equalsIgnoreCase("absentEmployee")) {
    			  String totalEmpJsonString = bioAuthSoapObj.registeredEmployees(deptCode);
    			  String todayPresentEmpJsonString = bioAuthSoapObj.presentToday(deptCode);
    			  Set<String> presentEmployees = getEmployeeIds(todayPresentEmpJsonString);
    			  resultList = getEmployeeDetails(totalEmpJsonString,presentEmployees,employeeType);
    		 }
    		  
    	 } catch (Exception e) {
    		 LOG.error("Exception raised at getEmployeeDetailsByEmployeeType - BioMetricService service",e);
    	 }
    	 return resultList;
    }
    private List<BioMetricDashBoardDtlsVO> getEmployeeDetails(String jsonString,Set<String> presentEmployeeIdSet,String employeeType) {
    	List<BioMetricDashBoardDtlsVO> empList = new ArrayList<>(0);
    	 try {
    		 if (jsonString != null && jsonString.trim().length() > 0) {
    			 JSONArray finalArray = new JSONArray(jsonString);
    			 if (finalArray != null && finalArray.length() > 0) {
    				 for(int i = 0; i < finalArray.length() ;i++) {
    					 JSONObject jsonObj = (JSONObject) finalArray.get(i);
    					 String empId = jsonObj.has("EMP_ID") ? jsonObj.getString("EMP_ID") : "";
    					  if (employeeType.equalsIgnoreCase("absentEmployee") && presentEmployeeIdSet.contains(empId)) {
    						   continue;
    					  }
    					 BioMetricDashBoardDtlsVO empVO = new BioMetricDashBoardDtlsVO();
    					 empVO.setEmpId(empId); 
    					 empVO.setType(jsonObj.has("Emp_Type") ? jsonObj.getString("Emp_Type") : "");
    					 empVO.setDesignation(jsonObj.has("Designation") ? jsonObj.getString("Designation") : "");
    					 empVO.setName(jsonObj.has("Emp_Name") ? jsonObj.getString("Emp_Name") : "");
    					 empVO.setDeptCode(jsonObj.has("DEPT_CODE") ? jsonObj.getString("DEPT_CODE") : "");
    					 empVO.setDeptName(jsonObj.has("Dept") ? jsonObj.getString("Dept") : "");
    					 empList.add(empVO);
    				 }
    			 }
    		 }
    		 
    	 } catch (Exception e) {
    		 LOG.error("Exception raised at getEmployeeDetails - BioMetricService service",e);
    	 }
     	return empList;
    }
    /**
	 * @author Santosh Kumar Verma
	 * @param String deptCode 
	 * @param String employeeType 
	 * @description {This service is used to get  employee attendance details based on selected date.}
	 * @return List<BioMetricDashBoardDtlsVO>
	 * @Date 10-11-2017
	 */
    public List<BioMetricDashBoardDtlsVO> getDateWisePresentEmployeeDetails(String deptCode,String employeeType,String fromDate,String toDate) {
    	List<BioMetricDashBoardDtlsVO> resultList = new ArrayList<BioMetricDashBoardDtlsVO>(0);
    	 try {
    		   BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
    		 if (employeeType != null && employeeType.equalsIgnoreCase("presentEmployee")) {
    			  String totalAttendancEmployeeJsonString = bioAuthSoapObj.empfromtodateAttendance(deptCode, fromDate, toDate);
    			  resultList = getEmployeeBaiscDetails(totalAttendancEmployeeJsonString,null,employeeType);
    		 } else if (employeeType != null && employeeType.equalsIgnoreCase("absentEmployee")) {
    			  String totalEmpJsonString =bioAuthSoapObj.empfromtodateAttendance(deptCode, fromDate, toDate);
    			  String totalRegistratedEmployee = bioAuthSoapObj.registeredEmployees(deptCode);
    			  Set<String> presentEmployeeIdSet = getPresnetEmployeeIds(totalEmpJsonString);
    			  resultList = getEmployeeBaiscDetails(totalRegistratedEmployee,presentEmployeeIdSet,employeeType);
    		 }
    		  
    	 } catch (Exception e) {
    		 LOG.error("Exception raised at getEmployeeDetailsByEmployeeType - BioMetricService service",e);
    	 }
    	 return resultList;
    }

	private Set<String> getPresnetEmployeeIds(String jsonString) {
		Set<String> empIdSet = new HashSet<String>(0);
		try {
			if (jsonString != null && jsonString.length() > 0) {
				JSONArray jsonDataArray = new JSONArray(jsonString);
				if (jsonDataArray != null && jsonDataArray.length() > 0) {
					for (int i = 0; i < jsonDataArray.length(); i++) {
						JSONObject jsonObj = (JSONObject) jsonDataArray.get(i);
						String date = jsonObj.has("date") ? jsonObj.getString("date") : "";
						if (date.trim().length() > 0) {
							if (jsonObj.has("EMP_CODE") && jsonObj.get("EMP_CODE").toString().length() > 0) {
								empIdSet.add(jsonObj.get("EMP_CODE").toString());
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeIds - BioMetricService service",e);
		}
		return empIdSet;
	}

	private List<BioMetricDashBoardDtlsVO> getEmployeeBaiscDetails(String jsonString, Set<String> presentEmployeeIdSet,String employeeType) {
		List<BioMetricDashBoardDtlsVO> empList = new ArrayList<>(0);
		try {
			if (jsonString != null && jsonString.trim().length() > 0) {
				JSONArray finalArray = new JSONArray(jsonString);
				if (finalArray != null && finalArray.length() > 0) {
					for (int i = 0; i < finalArray.length(); i++) {
						JSONObject jsonObj = (JSONObject) finalArray.get(i);
						BioMetricDashBoardDtlsVO empVO = new BioMetricDashBoardDtlsVO();
						if (employeeType.equalsIgnoreCase("presentEmployee")) {
							String date = jsonObj.has("date") ? jsonObj.getString("date") : "";
							if (date.trim().length() == 0l) {
								continue;
							}
							setPresentEmployeeBasicInformation(jsonObj, empVO);
						} else {
							String empId = jsonObj.has("EMP_ID") ? jsonObj.getString("EMP_ID") : "";
							if (employeeType.equalsIgnoreCase("absentEmployee") && presentEmployeeIdSet.contains(empId)) {
								continue;
							}

							empVO.setEmpId(empId);
							empVO.setType(jsonObj.has("Emp_Type") ? jsonObj.getString("Emp_Type") : "");
							empVO.setDesignation(jsonObj.has("Designation") ? jsonObj.getString("Designation") : "");
							empVO.setName(jsonObj.has("Emp_Name") ? jsonObj.getString("Emp_Name") : "");
							empVO.setDeptCode(jsonObj.has("DEPT_CODE") ? jsonObj.getString("DEPT_CODE") : "");
							empVO.setDeptName(jsonObj.has("Dept") ? jsonObj.getString("Dept") : "");
						}

						empList.add(empVO);
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeDetails - BioMetricService service",e);
		}
		return empList;
	}
    private void setPresentEmployeeBasicInformation(JSONObject jsonObj,BioMetricDashBoardDtlsVO empDtlsVO) {
    	 try {
    		 empDtlsVO.setEmpId(jsonObj.has("EMP_CODE") ? jsonObj.getString("EMP_CODE") : ""); 
    		 empDtlsVO.setType(jsonObj.has("category") ? jsonObj.getString("category") : "");
    		 empDtlsVO.setDesignation(jsonObj.has("DESIGNATION") ? jsonObj.getString("DESIGNATION") : "");
    		 empDtlsVO.setName(jsonObj.has("Employee_Name") ? jsonObj.getString("Employee_Name") : "");
    		 empDtlsVO.setTime(jsonObj.has("IN_TIME") ? jsonObj.getString("IN_TIME") : "");
    		 empDtlsVO.setDate(jsonObj.has("date") ? jsonObj.getString("date") : "");
    	 } catch (Exception e) {
    		 LOG.error("Exception raised at setEmployeeBasicInformation - BioMetricService service",e);
    	 }
    }
        
    /*
    * @author Santosh Kumar Verma
	 * @param String deptCode 
	 * @param String timePeriod 
	 * @description {This service is used to get time period wise employee attendance details by clicking on count in user interface.}
	 * @return List<BioMetricDashBoardDtlsVO>
	 * @Date 4-11-2017
	 */
	public List<BioMetricDashBoardDtlsVO> getTimePeriodWiseEmployeeAttendenceDetails(String deptCode,String timePeriod) {
		List<BioMetricDashBoardDtlsVO> resultList = null;
		 try {
			         BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
					 String jsonString = callWebServiceByTimePeriod(timePeriod,bioAuthSoapObj,deptCode);//calling web service by time period wise
			         resultList = getEmployeeDetailsByTimePeriod(jsonString);
		 } catch (Exception e) {
			 LOG.error("Exception raised at getEmployeeAttendenceTimePeriodWise - BioMetricService service",e);
		 }
		 return resultList;
	}

	private List<BioMetricDashBoardDtlsVO> getEmployeeDetailsByTimePeriod(String jsonString) {
		List<BioMetricDashBoardDtlsVO> resultList = new ArrayList<>(0);
		try {
			if (jsonString != null && jsonString.trim().length() > 0) {
				JSONArray finalArray = new JSONArray(jsonString);
				if (finalArray != null && finalArray.length() > 0) {
					Map<String,BioMetricDashBoardDtlsVO> empDtlsMap = new HashMap<>();
					for (int i = 0; i < finalArray.length(); i++) {
						JSONObject jsonObj = (JSONObject) finalArray.get(i);
						BioMetricDashBoardDtlsVO empDtlsVO = new BioMetricDashBoardDtlsVO();
						String empId = jsonObj.has("EMP_ID") ? jsonObj.getString("EMP_ID") : "";
						if (empId.trim().length() > 0) {
							if (empDtlsMap.get(empId) == null) {
								empDtlsVO.setEmpId(jsonObj.has("EMP_ID") ? jsonObj.getString("EMP_ID") : "");
								empDtlsVO.setType(jsonObj.has("Emp_Type") ? jsonObj.getString("Emp_Type") : "");
								empDtlsVO.setDesignation(jsonObj.has("Designation") ? jsonObj.getString("Designation") : "");
								empDtlsVO.setName(jsonObj.has("Emp_Name") ? jsonObj.getString("Emp_Name") : "");
								empDtlsVO.setDeptCode(jsonObj.has("DEPT_CODE") ? jsonObj.getString("DEPT_CODE") : "");
								empDtlsVO.setDeptName(jsonObj.has("Dept") ? jsonObj.getString("Dept") : "");
								empDtlsVO.setDate(jsonObj.has("Date") ? jsonObj.getString("Date") : "");
								empDtlsVO.setTime(jsonObj.has("time") ? jsonObj.getString("time") : "");
								empDtlsMap.put(empId, empDtlsVO);
							}
						}
					}
					resultList.addAll(empDtlsMap.values());
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeDetailsByTimePeriod - BioMetricService service",e);
		}
		return resultList;
	}
	
	private String callWebServiceByTimePeriod(String timePeriod,BioAuthSoapProxy bioAuthSoapObj, String deptCode) {
		String jsonString = null;
		try {
			switch (timePeriod) {
			case "Before - 10:00":
				jsonString = bioAuthSoapObj.presenBefore10AM(deptCode);
				break;
			case "10:00 - 10:30":
				jsonString = bioAuthSoapObj.presentfrom10AMTo1030Am(deptCode);
				break;
			case "10:30 - 11:00":
				jsonString = bioAuthSoapObj.presentfrom1030AmTo1100Am(deptCode);
				break;
			case "After - 11:00":
				jsonString = bioAuthSoapObj.presentafter1100Am(deptCode);
				break;
			default:
				jsonString = null;
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getEmployeeAttendenceTimePeriodWise - BioMetricService service",e);
		}
		return jsonString;
	}
    
    
   private String getDayOfWeekNameByDate(SimpleDateFormat sdf,String date) {
		String dayOfWeek = "";
		try {
			 dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(sdf.parse(date));
		} catch (Exception e) {
			 LOG.error("Exception raised at getDayOfWeekNameByDate - BioMetricService service",e);
		}
		return dayOfWeek;
	}
	private Date[] getTimes(String timePeriod) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		Date[] dateArr = new Date[2];
		try {
		      String[] tempArr = timePeriod.split("-");
			 if (tempArr[0].trim().equalsIgnoreCase("Before") ||tempArr[0].trim().equalsIgnoreCase("After") ) {
				    dateArr[0] = sdf.parse(tempArr[1].trim()+" AM");
		     } else {
				   dateArr[0] = sdf.parse(tempArr[0].trim()+" AM");
				   dateArr[1] = sdf.parse(tempArr[1].trim()+" AM");
				
		     }
		} catch (Exception e) {
			LOG.error("Exception raised at getTimes - BioMetricService service",e);
		}
		return dateArr;
	}
	private Date[] getDates(String fromDate, String toDate,SimpleDateFormat sdf) {
		Date[] dateArr = new Date[2];
		try {
			if (fromDate != null && fromDate.length() > 0 && toDate != null && toDate.length() > 0) {
				dateArr[0] = sdf.parse(fromDate);
				dateArr[1] = sdf.parse(toDate);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getDates - BioMetricService service",e);
		}
		return dateArr;
	}
	
	private JSONObject filterRequiredObjectsPr(String jsonString) {
    	 try {
    		  if (jsonString != null && jsonString.trim().length() > 0) {
    			  JSONArray finalJSONArray = new JSONArray(jsonString);
    			   if (finalJSONArray != null && finalJSONArray.length() > 0) {
    				   for (int i = 0; i < finalJSONArray.length(); i++) {
    					    JSONObject jsonObj = (JSONObject) finalJSONArray.get(i);
    					    String deptName = jsonObj.has("DEPARTMENT") ? jsonObj.getString("DEPARTMENT") : "";
    					    if (deptName.trim().equalsIgnoreCase("PANCHAYATI RAJ \u0026 RURAL DEVELOPMENT")) {
    					    	return jsonObj;
    					    }
    				   }
     			   }
    		  }
    	 } catch (Exception e) {
    		 LOG.error("Exception raised at filterRequiredObject - BioMetricService service",e);
    	 }
    	 return null;
    }
	
	
}
