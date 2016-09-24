/**
 * All implementation methods related to core dash board attendance goes here.
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDepartmentDAO;
import com.itgrids.partyanalyst.dao.IEmployeeDepartmentDAO;
import com.itgrids.partyanalyst.dao.IEmployeeWorkLocationDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IHolidayDAO;
import com.itgrids.partyanalyst.dto.HolidayListVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.model.Department;
import com.itgrids.partyanalyst.service.IAttendanceCoreDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;
/**
 * @author Swadhin Lenka
 * @since 09/20/2016
 *
 */
public class AttendanceCoreDashBoardService implements IAttendanceCoreDashBoardService {
	
	private static Logger LOG = Logger.getLogger(AttendanceService.class);
	
	DateUtilService dateUtilService = new DateUtilService();
	private IDepartmentDAO departmentDAO;
	private IEmployeeWorkLocationDAO employeeWorkLocationDAO;
	private IEventAttendeeDAO eventAttendeeDAO;
	private IEmployeeDepartmentDAO employeeDepartmentDAO;
	private IHolidayDAO holidayDAO;
	
	public void setDepartmentDAO(IDepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	
	public void setEmployeeWorkLocationDAO(IEmployeeWorkLocationDAO employeeWorkLocationDAO) {
		this.employeeWorkLocationDAO = employeeWorkLocationDAO;
	}
	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}
	public void setEmployeeDepartmentDAO(IEmployeeDepartmentDAO employeeDepartmentDAO) {
		this.employeeDepartmentDAO = employeeDepartmentDAO;
	}
	public void setHolidayDAO(IHolidayDAO holidayDAO) {
		this.holidayDAO = holidayDAO;
	}

	/**
	 * @author Swadhin Lenka
	 * @since 09/20/2016
	 * @param  String fromDateStr
	 * @param  String toDateStr
	 * @Description :This Service Method is used to return total employye, total attended employee and total absent employee 
	 */
	public IdNameVO getAttendanceOverViewForPartyOffice(String fromDateStr, String toDateStr){
		LOG.info("Entered into getAttendanceOverViewForPartyOffice method of AttendanceCoreDashBoardService class");
		try{
			List<Long> departmentIdList = new ArrayList<Long>();
			List<Long> presentedCaderIdList = new ArrayList<Long>();
			Map<Long,String> employeeIdLocationMap = new HashMap<Long,String>(); 
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}  
			
			//collect all the cadres who are attended between following date.
			List<Object[]> eventIdAndPresentedCadreIdList = eventAttendeeDAO.getEventIdAndPresentedCadreIdList(fromDate, toDate);
			if(eventIdAndPresentedCadreIdList != null && eventIdAndPresentedCadreIdList.size() > 0){
				for(Object[] eventIdAndPresentedCadreId : eventIdAndPresentedCadreIdList){
					presentedCaderIdList.add(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l);
					employeeIdLocationMap.put(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l, eventIdAndPresentedCadreId[3] != null ? eventIdAndPresentedCadreId[3].toString() : "");
				}
			} 
			//collect all the department ids
			List<Department> departmentList = departmentDAO.getAll();
			if(departmentList != null && departmentList.size() > 0){
				for(Department department : departmentList){
					departmentIdList.add(department.getDepartmentId() != null ?  department.getDepartmentId() : 0l);  
				}
			}
			presentedCaderIdList.add(0l);
			//total no of employees office wise...
			List<Object[]> officeWiseTotalEmployeeList = employeeWorkLocationDAO.getOfficeWiseTotalEmployeeListFilter(departmentIdList);
			//total no of attended employees office wise... 
			List<Object[]> officeWiseTotalAttendedEmployee = employeeWorkLocationDAO.getOfficeWiseTotalAttendedEmployeeFilter(departmentIdList, presentedCaderIdList);
			//total party office employees 
			Long totalEmp = 0l;
			if(officeWiseTotalEmployeeList != null && officeWiseTotalEmployeeList.size() > 0){
				for(Object[] param : officeWiseTotalEmployeeList){
					totalEmp+=((Long)param[2]);
				}
			}
			//total party office attended employees
			Long attendedEmp = 0l;
			if(officeWiseTotalAttendedEmployee != null && officeWiseTotalAttendedEmployee.size() > 0){
				for(Object[] param : officeWiseTotalAttendedEmployee){
					attendedEmp+=((Long)param[2]);
				}
			}
			//total absent employees
			Long absentEmp = 0l;
			absentEmp = totalEmp - attendedEmp; 
			//create vo to send to the ui
			IdNameVO idNameVO = new IdNameVO();
			idNameVO.setActualCount(totalEmp);
			idNameVO.setAvailableCount(attendedEmp);
			idNameVO.setCount(absentEmp);
			return idNameVO;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getAttendanceOverViewForPartyOffice method of AttendanceCoreDashBoardService class");
		}
		return null;
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/20/2016
	 * @param  String fromDateStr
	 * @param  String toDateStr
	 * @Description :This Service Method is used to return total employee, total attended employee and total absent employee department wise
	 */
	public List<IdNameVO> getAttendanceOverViewForPartyOfficeWise(String fromDateStr, String toDateStr){
		LOG.info("Entered into getAttendanceOverViewForPartyOfficeDeptWise method of AttendanceCoreDashBoardService class");
		try{
			List<Long> departmentIdList = new ArrayList<Long>();
			List<Long> presentedCaderIdList = new ArrayList<Long>();
			Map<Long,String> employeeIdLocationMap = new HashMap<Long,String>(); 
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//collect all the cadres who are attended between following date.
			List<Object[]> eventIdAndPresentedCadreIdList = eventAttendeeDAO.getEventIdAndPresentedCadreIdList(fromDate, toDate);
			if(eventIdAndPresentedCadreIdList != null && eventIdAndPresentedCadreIdList.size() > 0){
				for(Object[] eventIdAndPresentedCadreId : eventIdAndPresentedCadreIdList){
					presentedCaderIdList.add(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l);
					employeeIdLocationMap.put(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l, eventIdAndPresentedCadreId[3] != null ? eventIdAndPresentedCadreId[3].toString() : "");
				}
			} 
			//collect all the department ids
			List<Department> departmentList = departmentDAO.getAll();
			if(departmentList != null && departmentList.size() > 0){
				for(Department department : departmentList){
					departmentIdList.add(department.getDepartmentId() != null ?  department.getDepartmentId() : 0l);  
				}
			}
			//total no of employees office wise...
			List<Object[]> officeWiseTotalEmployeeList = employeeWorkLocationDAO.getOfficeWiseTotalEmployeeListFilter(departmentIdList);
			//total no of attended employees office wise... 
			presentedCaderIdList.add(0l);
			List<Object[]> officeWiseTotalAttendedEmployee = employeeWorkLocationDAO.getOfficeWiseTotalAttendedEmployeeFilter(departmentIdList, presentedCaderIdList);
			//total party office employees
			Map<Long,String> officeIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> officeIdAndToralCountMap = new HashMap<Long,Long>();
			
			List<Long> officeIdList = new ArrayList<Long>(0);
			if(officeWiseTotalEmployeeList != null && officeWiseTotalEmployeeList.size() > 0){
				for(Object[] param : officeWiseTotalEmployeeList){
					officeIdList.add(param[0] != null ? (Long)param[0] : 0l);
					officeIdAndNameMap.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? param[1].toString() : "");
					officeIdAndToralCountMap.put(param[0] != null ? (Long)param[0] : 0l, param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//total party office attended employees
			Map<Long,Long> officeIdAndTotalPresentCountMap = new HashMap<Long,Long>();
			if(officeWiseTotalAttendedEmployee != null && officeWiseTotalAttendedEmployee.size() > 0){
				for(Object[] param : officeWiseTotalAttendedEmployee){
					officeIdAndTotalPresentCountMap.put(param[0] != null ? (Long)param[0] : 0l, param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//prepare vo to send to ui
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>(0);
			IdNameVO idNameVO = null;
			Long totalCount = 0l;
			Long presentCount = 0l;
			Long absentCount = 0l;
			
			if(officeIdList.size() > 0){
				for(Long officeId : officeIdList){
					idNameVO = new IdNameVO();
					idNameVO.setId(officeId);
					idNameVO.setName(officeIdAndNameMap.get(officeId));
					idNameVO.setActualCount(officeIdAndToralCountMap.get(officeId));
					totalCount = officeIdAndToralCountMap.get(officeId);
					idNameVO.setAvailableCount(officeIdAndTotalPresentCountMap.get(officeId) != null ? officeIdAndTotalPresentCountMap.get(officeId) : 0l);
					presentCount = officeIdAndTotalPresentCountMap.get(officeId) != null ? officeIdAndTotalPresentCountMap.get(officeId) : 0l;
					absentCount = totalCount - presentCount;
					idNameVO.setCount(absentCount);
					idNameVOs.add(idNameVO);
				}
			}
			return idNameVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getAttendanceOverViewForPartyOfficeDeptWise method of AttendanceCoreDashBoardService class");
		}
		return null;
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/20/2016
	 *
	 */
	public List<IdNameVO> getAttendanceOverViewForPartyOfficeDeptWise(String fromDateStr, String toDateStr){
		LOG.info("Entered into getAttendanceOverViewForPartyOfficeDeptWise method of AttendanceCoreDashBoardService class");
		try{
			List<Long> departmentIdList = new ArrayList<Long>();
			List<Long> presentedCaderIdList = new ArrayList<Long>();
			Map<Long,String> employeeIdLocationMap = new HashMap<Long,String>();   
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr); 
				toDate = sdf.parse(toDateStr);
			}
			//collect all the cadres who are attended between following date.
			List<Object[]> eventIdAndPresentedCadreIdList = eventAttendeeDAO.getEventIdAndPresentedCadreIdList(fromDate, toDate);
			if(eventIdAndPresentedCadreIdList != null && eventIdAndPresentedCadreIdList.size() > 0){
				for(Object[] eventIdAndPresentedCadreId : eventIdAndPresentedCadreIdList){
					presentedCaderIdList.add(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l);
					employeeIdLocationMap.put(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l, eventIdAndPresentedCadreId[3] != null ? eventIdAndPresentedCadreId[3].toString() : "");
				}
			} 
			//collect all the department ids
			List<Department> departmentList = departmentDAO.getAll();
			if(departmentList != null && departmentList.size() > 0){
				for(Department department : departmentList){
					departmentIdList.add(department.getDepartmentId() != null ?  department.getDepartmentId() : 0l);    
				}
			}
			//total no of employees department wise...
			List<Object[]> departmentWiseTotalEmployeeList = employeeDepartmentDAO.getDepartmentWiseTotalEmployeeListFilter(departmentIdList);
			//total no of attended employees department wise... 
			presentedCaderIdList.add(0l);
			List<Object[]> departmentWiseTotalAttendedEmployee = employeeDepartmentDAO.getDepartmentWiseTotalAttendedEmployeeFilter(departmentIdList, presentedCaderIdList);
			
			//total department wise employees
			Map<Long,String> departmentIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> departmentIdAndToralCountMap = new HashMap<Long,Long>();
			
			List<Long> departmentIdsList = new ArrayList<Long>(0);
			if(departmentWiseTotalEmployeeList != null && departmentWiseTotalEmployeeList.size() > 0){
				for(Object[] param : departmentWiseTotalEmployeeList){
					departmentIdsList.add(param[0] != null ? (Long)param[0] : 0l);
					departmentIdAndNameMap.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? param[1].toString() : "");
					departmentIdAndToralCountMap.put(param[0] != null ? (Long)param[0] : 0l, param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//total department wise attended employees 
			Map<Long,Long> departmentIdAndTotalPresentCountMap = new HashMap<Long,Long>();
			if(departmentWiseTotalAttendedEmployee != null && departmentWiseTotalAttendedEmployee.size() > 0){
				for(Object[] param : departmentWiseTotalAttendedEmployee){
					departmentIdAndTotalPresentCountMap.put(param[0] != null ? (Long)param[0] : 0l, param[2] != null ? (Long)param[2] : 0l);
				}
			}
			//prepare vo to send to ui
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>(0);
			IdNameVO idNameVO = null;
			Long totalCount = 0l;
			Long presentCount = 0l;
			Long absentCount = 0l;
			/* except the following dept all depts are taken as others
			 * Admin -1
			 * InfoCell -2
			 * Library -3
			 * program committee -4
			 * lokesh peshi -14
			 */
			//create a list for the following depts
			List<Long> requiredDepts = new ArrayList<Long>(){{
				add(1l);
				add(2l);
				add(3l);
				add(4l);
				add(14l);
			}};
			//create temp variable for other case
			Long otherTotalCount = 0l;
			Long otherPresentCount = 0l;
			Long otherAbsentCount = 0l;
			if(departmentIdsList.size() > 0){
				for(Long departmentId : departmentIdsList){
					if(requiredDepts.contains(departmentId)){
						idNameVO = new IdNameVO();
						idNameVO.setId(departmentId);
						idNameVO.setName(departmentIdAndNameMap.get(departmentId));
						idNameVO.setActualCount(departmentIdAndToralCountMap.get(departmentId));
						totalCount = departmentIdAndToralCountMap.get(departmentId);
						idNameVO.setAvailableCount(departmentIdAndTotalPresentCountMap.get(departmentId) != null ? departmentIdAndTotalPresentCountMap.get(departmentId) : 0l);
						presentCount = departmentIdAndTotalPresentCountMap.get(departmentId) != null ? departmentIdAndTotalPresentCountMap.get(departmentId) : 0l;
						absentCount = totalCount - presentCount;
						idNameVO.setCount(absentCount);
						idNameVOs.add(idNameVO);
					}else{
						otherTotalCount += departmentIdAndToralCountMap.get(departmentId);
						otherPresentCount += departmentIdAndTotalPresentCountMap.get(departmentId) != null ? departmentIdAndTotalPresentCountMap.get(departmentId) : 0l;
					}
					
				}
				otherAbsentCount = otherTotalCount - otherPresentCount;
			}
			//for other case start
			idNameVO = new IdNameVO();
			idNameVO.setId(0l);
			idNameVO.setName("Others");
			idNameVO.setActualCount(otherTotalCount);
			idNameVO.setAvailableCount(otherPresentCount);
			idNameVO.setCount(otherAbsentCount);
			idNameVOs.add(idNameVO);
			//for other case end
			
			return idNameVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getAttendanceOverViewForPartyOfficeDeptWise method of AttendanceCoreDashBoardService class");
		}
		return null;
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/20/2016
	 *
	 */
	public List<IdNameVO> getAttendeeDtlsOfficeWiseForDay (String fromDateStr, String toDateStr, List<Long> officeIdList, List<Long> deptIdList){
		LOG.info("Entered into getAttendeeDtlsOfficeWise method of AttendanceCoreDashBoardService class");
		try{
			Long diff = 0l;
			Long noOfDays = 0l;
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr); 
				toDate = sdf.parse(toDateStr);
				diff = toDate.getTime() - fromDate.getTime();
				noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				noOfDays++;
			}
			
			
			IdNameVO idNameVO = null;
			List<IdNameVO> attendedEmployeeDtls = new ArrayList<IdNameVO>(0);
			List<IdNameVO> absentEmployeeDtls = new ArrayList<IdNameVO>(0);
			List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
			List<Object[]> emplyeeAttendanceDtlsList = employeeWorkLocationDAO.getAttendanceCountBetweenDatesOfficeWise(fromDate, toDate, officeIdList, deptIdList);
			List<Object[]> lateComingCountList =  employeeWorkLocationDAO.getEmployeeLateComingsCount( fromDate,  toDate,  officeIdList, deptIdList);
			List<Long> employeeCadreIdList = employeeWorkLocationDAO.getEmployeeIdListOfficeWise(officeIdList,deptIdList);
			Long holidayCount = holidayDAO.getHolidayCount(fromDate,toDate);
			//create a map for late attended employees.
			Map<Long,Long> employeeIdAndLateComingCount = new HashMap<Long,Long>();
			if(lateComingCountList != null && lateComingCountList.size() > 0){
				for(Object[] param : lateComingCountList){
					employeeIdAndLateComingCount.put(param[2] != null ? (Long)param[2] : 0l, param[5] != null ? (Long)param[5] : 0l);
				}
			}
			//collect attended cadre id list
			List<Long> attendedCadreIdList = new ArrayList<Long>(0);
			Long absent = 0l;
			if(emplyeeAttendanceDtlsList != null && emplyeeAttendanceDtlsList.size() > 0){
				for(Object[] param : emplyeeAttendanceDtlsList){
					attendedCadreIdList.add(param[2] != null ? (Long)param[2] : 0l);
					//prepare attended cadre details
					idNameVO = new IdNameVO();
					idNameVO.setDistrictid(param[0] != null ? (Long)param[0] : 0l);
					idNameVO.setDistrictName(param[1] != null ? param[1].toString() : "");
					idNameVO.setCadreId(param[2] != null ? (Long)param[2] : 0l);
					idNameVO.setName(param[3] != null ? param[3].toString() : "");
					idNameVO.setMobileNo(param[4] != null ? param[4].toString() : "");
					idNameVO.setStatus("present");  
					idNameVO.setAvailableCount(param[5] != null ? (Long)param[5] : 0l);
					if(holidayCount > 0l){
						idNameVO.setId(noOfDays-holidayCount);
					}else{
						idNameVO.setId(noOfDays);
					}
					
					idNameVO.setOrderId(employeeIdAndLateComingCount.get(param[2] != null ? (Long)param[2] : 0l) != null ? employeeIdAndLateComingCount.get((Long)param[2]) : 0l);
					absent = noOfDays - (param[5] != null ? (Long)param[5] : 0l);
					idNameVO.setCount(absent);  
					idNameVO.setWish(param[6] != null ? param[6].toString() : "");
					attendedEmployeeDtls.add(idNameVO);            
				}  
			}
			//for multiple dates return here  
			if(noOfDays > 1l){
				return attendedEmployeeDtls;  
			}
			//collect absentCadreIds cadre id list By removing all attended cadres from total cadre
			//now employeeCadreIdList treated as absent cadres 
			
			if(attendedCadreIdList != null && attendedCadreIdList.size() > 0){
				employeeCadreIdList.removeAll(attendedCadreIdList);
			}
				employeeCadreIdList.add(0l);
				//prepare absent cadre details
				List<Object[]> absentCadreDtls = employeeDepartmentDAO.getAbsentCadreDtls(employeeCadreIdList);  
				if(absentCadreDtls != null && absentCadreDtls.size() > 0){
					for(Object[] param : absentCadreDtls){
						idNameVO = new IdNameVO();
						idNameVO.setDistrictid(param[1] != null ? (Long)param[1] : 0l);
						idNameVO.setDistrictName(param[2] != null ? param[2].toString() : "");
						idNameVO.setName(param[3] != null ? param[3].toString() : "");
						idNameVO.setMobileNo(param[4] != null ? param[4].toString() : "");
						idNameVO.setStatus("absent");
						absentEmployeeDtls.add(idNameVO);  
					}
				}  
		
			finalList.addAll(attendedEmployeeDtls);			
			finalList.addAll(absentEmployeeDtls);	
			return finalList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getAttendeeDtlsOfficeWise method of AttendanceCoreDashBoardService class");
		}
		return null;
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/21/2016
	 *
	 */
	public List<IdNameVO> getAttendeeDtlsDeptWiseForDay (String fromDateStr, String toDateStr, List<Long> officeIdList, List<Long> deptIdList, String status){
		LOG.info("Entered into getAttendeeDtlsDeptWiseForDay method of AttendanceCoreDashBoardService class");
		try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr); 
				toDate = sdf.parse(toDateStr);
				
			}
			IdNameVO idNameVO = null;
			List<IdNameVO> attendedEmployeeDtls = new ArrayList<IdNameVO>(0);
			List<IdNameVO> absentEmployeeDtls = new ArrayList<IdNameVO>(0);
			List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
			List<Object[]> emplyeeAttendanceDtlsList = employeeWorkLocationDAO.getAttendanceCountBetweenDatesOfficeWise(fromDate, toDate, officeIdList, deptIdList);
			List<Long> employeeCadreIdList = employeeWorkLocationDAO.getEmployeeIdListOfficeWise(officeIdList,deptIdList);
			//collect attended cadre id list
			List<Long> attendedCadreIdList = new ArrayList<Long>(0);
			Long absent = 0l;
			if(emplyeeAttendanceDtlsList != null && emplyeeAttendanceDtlsList.size() > 0){
				for(Object[] param : emplyeeAttendanceDtlsList){
					attendedCadreIdList.add(param[2] != null ? (Long)param[2] : 0l);
					//prepare attended cadre details
					idNameVO = new IdNameVO();
					idNameVO.setDistrictid(param[0] != null ? (Long)param[0] : 0l);
					idNameVO.setDistrictName(param[1] != null ? param[1].toString() : "");
					idNameVO.setCadreId(param[0] != null ? (Long)param[0] : 0l);
					idNameVO.setName(param[3] != null ? param[3].toString() : "");
					idNameVO.setMobileNo(param[4] != null ? param[4].toString() : "");
					idNameVO.setWish(param[6] != null ? param[6].toString() : "");
					idNameVO.setStatus("present");
					
					attendedEmployeeDtls.add(idNameVO);
				}
			}
			if(status.equalsIgnoreCase("present")){  
				return attendedEmployeeDtls;
			}
			//collect absentCadreIds cadre id list By removing all attended cadres from total cadre
			//now employeeCadreIdList treated as absent cadres 
			
			if(attendedCadreIdList != null && attendedCadreIdList.size() > 0){
				employeeCadreIdList.removeAll(attendedCadreIdList);
			}
				employeeCadreIdList.add(0l);  
				//prepare absent cadre details  
				List<Object[]> absentCadreDtls = employeeDepartmentDAO.getAbsentCadreDtls(employeeCadreIdList);
				if(absentCadreDtls != null && absentCadreDtls.size() > 0){  
					for(Object[] param : absentCadreDtls){
						idNameVO = new IdNameVO();
						idNameVO.setDistrictid(param[1] != null ? (Long)param[1] : 0l);
						idNameVO.setDistrictName(param[2] != null ? param[2].toString() : "");  
						idNameVO.setName(param[3] != null ? param[3].toString() : "");
						idNameVO.setMobileNo(param[4] != null ? param[4].toString() : "");
						idNameVO.setStatus("absent");
						absentEmployeeDtls.add(idNameVO);  
					}
				}
			
			if(status.equalsIgnoreCase("absent")){
				return 	absentEmployeeDtls;
			}
			finalList.addAll(attendedEmployeeDtls);			
			finalList.addAll(absentEmployeeDtls);	
			return finalList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getAttendeeDtlsDeptWiseForDay method of AttendanceCoreDashBoardService class");
		}
		return null;
	} 
	public List<Long> getDeptIds(){
		LOG.info("Entered into getDeptIds method of AttendanceCoreDashBoardService class");
		try{
			List<Long> departmentIdList = new ArrayList<Long>();
			//collect all the department ids
			List<Department> departmentList = departmentDAO.getAll();
			if(departmentList != null && departmentList.size() > 0){
				for(Department department : departmentList){
					departmentIdList.add(department.getDepartmentId() != null ?  department.getDepartmentId() : 0l);  
				}
			}
			return departmentIdList;
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getDeptIds method of AttendanceCoreDashBoardService class");
		}
		return null;
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/22/2016
	 *
	 */
	public List<List<IdNameVO>> getTopAbsentAndIregular(String fromDateStr, String toDateStr, List<Long> officeIdList, List<Long> deptIdList){
		LOG.info("Entered into getTopAbsentAndIregular method of AttendanceCoreDashBoardService class");
		try{
			Long diff = 0l;
			Long noOfDays = 0l;
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr); 
				toDate = sdf.parse(toDateStr);
				diff = toDate.getTime() - fromDate.getTime();
				noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				noOfDays++;
			}
			IdNameVO idNameVO = null;
			List<IdNameVO> attendedEmployeeDtls = new ArrayList<IdNameVO>(0);
			
			
			List<Object[]> emplyeeAttendanceDtlsList = employeeWorkLocationDAO.getAttendanceCountBetweenDatesOfficeWise(fromDate, toDate, officeIdList, deptIdList);
			List<Object[]> lateComingCountList =  employeeWorkLocationDAO.getEmployeeLateComingsCount( fromDate,  toDate,  officeIdList, deptIdList);
			
			Long holidayCount = holidayDAO.getHolidayCount(fromDate,toDate);
			//create a map for late attended employees.
			Map<Long,Long> employeeIdAndLateComingCount = new HashMap<Long,Long>();
			if(lateComingCountList != null && lateComingCountList.size() > 0){
				for(Object[] param : lateComingCountList){
					employeeIdAndLateComingCount.put(param[2] != null ? (Long)param[2] : 0l, param[5] != null ? (Long)param[5] : 0l);
				}
			}
			//collect attended cadre id list
			List<Long> attendedCadreIdList = new ArrayList<Long>(0);
			Long absent = 0l;
			if(emplyeeAttendanceDtlsList != null && emplyeeAttendanceDtlsList.size() > 0){
				for(Object[] param : emplyeeAttendanceDtlsList){
					attendedCadreIdList.add(param[2] != null ? (Long)param[2] : 0l);
					//prepare attended cadre details
					idNameVO = new IdNameVO();
					idNameVO.setDistrictid(param[0] != null ? (Long)param[0] : 0l);
					idNameVO.setDistrictName(param[1] != null ? param[1].toString() : "");
					idNameVO.setCadreId(param[2] != null ? (Long)param[2] : 0l);
					idNameVO.setName(param[3] != null ? param[3].toString() : "");
					idNameVO.setMobileNo(param[4] != null ? param[4].toString() : "");
					idNameVO.setStatus("present");  
					idNameVO.setAvailableCount(param[5] != null ? (Long)param[5] : 0l);
					if(holidayCount > 0l){
						idNameVO.setId(noOfDays-holidayCount);
					}else{
						idNameVO.setId(noOfDays);
					}
					
					idNameVO.setOrderId(employeeIdAndLateComingCount.get(param[2] != null ? (Long)param[2] : 0l) != null ? employeeIdAndLateComingCount.get((Long)param[2]) : 0l);
					
					absent = noOfDays - (param[5] != null ? (Long)param[5] : 0l);
					idNameVO.setCount(absent);  
					idNameVO.setWish(param[6] != null ? param[6].toString() : "");
					attendedEmployeeDtls.add(idNameVO);            
				}  
			}
			List<IdNameVO> sortedAbsentList = new ArrayList<IdNameVO>();
			sortedAbsentList.addAll(attendedEmployeeDtls);
			Collections.sort(sortedAbsentList, new Comparator<IdNameVO>(){
			     public int compare(IdNameVO vo1, IdNameVO vo2){
			         if(vo1.getCount() == vo2.getCount())
			             return 0;
			         return vo1.getCount() > vo2.getCount() ? -1 : 1;
			     }
			});
			if(sortedAbsentList.size() > 0){
				sortedAbsentList.get(0).setDateStr("absent");
			}
			List<IdNameVO> sortedIregularList = new ArrayList<IdNameVO>(0);
			sortedIregularList.addAll(attendedEmployeeDtls);
			Collections.sort(sortedIregularList, new Comparator<IdNameVO>(){
			     public int compare(IdNameVO vo1, IdNameVO vo2){
			         if(vo1.getOrderId() == vo2.getOrderId())
			             return 0;
			         return vo1.getOrderId() > vo2.getOrderId() ? -1 : 1;
			     }
			});
			if(sortedIregularList.size() > 0){
				sortedIregularList.get(0).setDateStr("iregular");
			}
			List<List<IdNameVO>> absentAndIregularList = new ArrayList<List<IdNameVO>>(0);
			absentAndIregularList.add(sortedAbsentList);
			absentAndIregularList.add(sortedIregularList);
			return absentAndIregularList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getTopAbsentAndIregular method of AttendanceCoreDashBoardService class");
		}
		return null;
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/22/2016
	 *
	 */
	public IdNameVO getAttendanceCountForMulitDate(Long officeId, Long deptId, String fromDateStr, String toDateStr){
		LOG.info("Entered into getTopAbsentAndIregular method of AttendanceCoreDashBoardService class");
		try{
			Long diff = 0l;
			Long noOfDays = 0l;
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr); 
				toDate = sdf.parse(toDateStr);
				diff = toDate.getTime() - fromDate.getTime();
				noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				noOfDays++;
			}
			//total employee
			List<Long> officeIdList = new ArrayList<Long>();
			officeIdList.add(officeId);
			List<Long> deptIdList = new ArrayList<Long>();
			deptIdList.add(deptId);
			List<Long> employeeCadreIdList = employeeWorkLocationDAO.getEmployeeIdListOfficeWise(officeIdList,deptIdList);
			int noOfEmp = employeeCadreIdList.size();
			//total no of presented employee
			Long totalPresentedEmp = 0l;
			List<Object[]> dayWisePresentCount = employeeWorkLocationDAO.getDayWisePresentCount(officeIdList,deptIdList,fromDate,toDate);
			if(dayWisePresentCount != null && dayWisePresentCount.size() > 0){
				for(Object[] param : dayWisePresentCount){
					totalPresentedEmp+=(Long)param[1];
				}
			}
			//no of holidays between these days
			Long holidayCount = holidayDAO.getHolidayCount(fromDate,toDate);
			if(holidayCount != null){
				noOfDays-=holidayCount;
			}
			//prepare return vo
			IdNameVO idNameVO = new IdNameVO();
			idNameVO.setCount(Long.valueOf(Integer.toString(noOfEmp)));//no of employee
			idNameVO.setActualCount(noOfDays);//no of working days
			idNameVO.setAvailableCount(totalPresentedEmp);
			Long absent = (Long.valueOf(Integer.toString(noOfEmp))*noOfDays) - totalPresentedEmp;
			idNameVO.setId(absent);//absent
			return idNameVO;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getTopAbsentAndIregular method of AttendanceCoreDashBoardService class");
		}
		return null;
		
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/23/2016
	 *
	 */
	public IdNameVO getAttendanceCountForMulitDateTimeWise(Long officeId, Long deptId, String fromDateStr, String toDateStr){
		LOG.info("Entered into getAttendanceCountForMulitDateTimeWise method of AttendanceCoreDashBoardService class");
		try{
			Date fromDate = null;
			Date toDate = null;
			Date fromTime =null;
			Date toTime = null;
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf1.parse(fromDateStr); 
				toDate = sdf1.parse(toDateStr);
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			//take the attended count before 9
			fromTime = sdf.parse("09:00:00"); 
			Long less9 = 0l;
			List<Object[]> totalMemberLess9 = employeeWorkLocationDAO.getTimeWisePresentCount(officeId,deptId,fromDate,toDate,fromTime,null);
			if(totalMemberLess9 != null && totalMemberLess9.size() > 0 ){
				for(Object[] param : totalMemberLess9){
					less9+=(Long)param[1];
				}
			}
			//take the attended count between 9 to 9:30
			fromTime = sdf.parse("09:00:00");  
			toTime = sdf.parse("10:00:00");
			Long between9To10 = 0l;
			List<Object[]> totalMemberBetween9To10 = employeeWorkLocationDAO.getTimeWisePresentCount(officeId,deptId,fromDate,toDate,fromTime,toTime);
			if(totalMemberBetween9To10 != null && totalMemberBetween9To10.size() > 0 ){
				for(Object[] param : totalMemberBetween9To10){
					between9To10+=(Long)param[1];
				}
			}
			//take the attended count between 9:30 to 10
			fromTime = sdf.parse("10:00:00");  
			toTime = sdf.parse("11:00:00");
			Long between10to11 = 0l;
			List<Object[]> totalMemberBetween10To11 = employeeWorkLocationDAO.getTimeWisePresentCount(officeId,deptId,fromDate,toDate,fromTime,toTime);
			if(totalMemberBetween10To11 != null && totalMemberBetween10To11.size() > 0 ){
				for(Object[] param : totalMemberBetween10To11){
					between10to11+=(Long)param[1];
				}
			}
			//take the attended count between 10 to 11
			fromTime = sdf.parse("11:00:00");  
			toTime = sdf.parse("13:00:00");
			Long between11T013 = 0l;
			List<Object[]> totalMemberBetween11To13 = employeeWorkLocationDAO.getTimeWisePresentCount(officeId,deptId,fromDate,toDate,fromTime,toTime);
			if(totalMemberBetween11To13 != null && totalMemberBetween11To13.size() > 0 ){
				for(Object[] param : totalMemberBetween11To13){  
					between11T013+=(Long)param[1];
				}
			}
			//take the attended count after 11
			toTime = sdf.parse("13:00:00");
			Long after13 = 0l;
			List<Object[]> totalMemberGreaterThan13 = employeeWorkLocationDAO.getTimeWisePresentCount(officeId,deptId,fromDate,toDate,null,toTime);
			if(totalMemberGreaterThan13 != null && totalMemberGreaterThan13.size() > 0 ){
				for(Object[] param : totalMemberGreaterThan13){
					after13+=(Long)param[1];
				}
			}
			//prepare vo for ui
			IdNameVO idNameVO = new IdNameVO();
			idNameVO.setId(less9);//less than 9
			idNameVO.setCount(between9To10);//between 9 to 10
			idNameVO.setActualCount(between10to11);//between 10 to 11
			idNameVO.setAvailableCount(between11T013);//between 11 to 13
			idNameVO.setOrderId(after13);//after 13
			
			return idNameVO;  
		}catch(Exception e){    
			e.printStackTrace();
			LOG.error("Error occured in getAttendanceCountForMulitDateTimeWise method of AttendanceCoreDashBoardService class");
		}
		return null;
		
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/23/2016
	 *
	 */
	public List<IdNameVO> getAttendanceReportTimeToTime(Long officeId, Long deptId, String fromDateStr, String toDateStr){
		LOG.info("Entered into getAttendanceReportTimeToTime method of AttendanceCoreDashBoardService class");
		try{
			Long diff = 0l;
			Long noOfDays = 0l;
			List<IdNameVO> employeeAttendanceDtls = new ArrayList<IdNameVO>();
			Map<Long,IdNameVO> empIdAndDtlsMap = new HashMap<Long,IdNameVO>();
			IdNameVO idNameVO = null;
			List<Long> officeIdList = new ArrayList<Long>();
			officeIdList.add(officeId);
			List<Long> deptIdList = new ArrayList<Long>();
			deptIdList.add(deptId);
			Date fromDate = null;
			Date toDate = null;
			Date fromTime =null;
			Date toTime = null;
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf1.parse(fromDateStr); 
				toDate = sdf1.parse(toDateStr);
				diff = toDate.getTime() - fromDate.getTime();
				noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				noOfDays++;
			}
			Long holidayCount = holidayDAO.getHolidayCount(fromDate,toDate);
			if(holidayCount != null){
				noOfDays-=holidayCount;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			//no of attendance per employee irrespective of time
			List<Object[]> emplyeeAttendanceDtlsList = employeeWorkLocationDAO.getAttendanceCountBetweenDatesOfficeWise(fromDate, toDate, officeIdList, deptIdList);
			if(emplyeeAttendanceDtlsList != null && emplyeeAttendanceDtlsList.size() > 0){
				for(Object[] param : emplyeeAttendanceDtlsList){
					idNameVO = new IdNameVO();
					idNameVO.setId(param[2] != null ? (Long)param[2] : 0l);
					idNameVO.setName(param[3] != null ? param[3].toString() : "");
					idNameVO.setMobileNo(param[4] != null ? param[4].toString() : "");
					idNameVO.setCount(param[5] != null ? (Long)param[5] : 0l);
					
					idNameVO.setAvailableCount(noOfDays);
					
					idNameVO.setActualCount(noOfDays-(param[5] != null ? (Long)param[5] : 0l));
					empIdAndDtlsMap.put(param[2] != null ? (Long)param[2] : 0l,idNameVO);
				}
			}
			//take the attended count before 9
			Map<Long,Long> empIdAndLessThan9count= new HashMap<Long,Long>();
			fromTime = sdf.parse("09:00:00"); 
			Long less9 = 0l;
			List<Object[]> totalMemberLess9 = employeeWorkLocationDAO.getAttendanceReportTimeToTime(officeId,deptId,fromDate,toDate,fromTime,null);
			if(totalMemberLess9 != null && totalMemberLess9.size() > 0 ){
				for(Object[] param : totalMemberLess9){
					empIdAndLessThan9count.put((Long)param[0],(Long)param[1]);
				}
			}
			//push into vo
			if(empIdAndLessThan9count.size() > 0){
				for(Entry<Long,Long> entry : empIdAndLessThan9count.entrySet()){
					Long id = entry.getKey();
					IdNameVO idVo = empIdAndDtlsMap.get(id);
					if(idVo != null){
						idVo.setLessThan9(entry.getValue());
					}
				}
			}
			//take the attended count between 9 to 9:30
			Map<Long,Long> empIdAndbetween9To10= new HashMap<Long,Long>();
			fromTime = sdf.parse("09:00:00");  
			toTime = sdf.parse("10:00:00");
			Long between9To10 = 0l;
			List<Object[]> totalMemberBetween9To10 = employeeWorkLocationDAO.getAttendanceReportTimeToTime(officeId,deptId,fromDate,toDate,fromTime,toTime);
			if(totalMemberBetween9To10 != null && totalMemberBetween9To10.size() > 0 ){
				for(Object[] param : totalMemberBetween9To10){
					empIdAndbetween9To10.put((Long)param[0],(Long)param[1]);
				}
			}
			//push into vo
			if(empIdAndbetween9To10.size() > 0){
				for(Entry<Long,Long> entry : empIdAndbetween9To10.entrySet()){
					Long id = entry.getKey();
					IdNameVO idVo = empIdAndDtlsMap.get(id);
					if(idVo != null){
						idVo.setBetween9To10(entry.getValue());
					}
				}
			}
			//take the attended count between 9:30 to 10
			Map<Long,Long> empIdAndbetween10to11= new HashMap<Long,Long>();
			fromTime = sdf.parse("10:00:00");  
			toTime = sdf.parse("11:00:00");
			
			List<Object[]> totalMemberBetween10To11 = employeeWorkLocationDAO.getAttendanceReportTimeToTime(officeId,deptId,fromDate,toDate,fromTime,toTime);
			if(totalMemberBetween10To11 != null && totalMemberBetween10To11.size() > 0 ){
				for(Object[] param : totalMemberBetween10To11){
					empIdAndbetween10to11.put((Long)param[0],(Long)param[1]);
				}
			}
			//push into vo
			if(empIdAndbetween10to11.size() > 0){
				for(Entry<Long,Long> entry : empIdAndbetween10to11.entrySet()){
					Long id = entry.getKey();
					IdNameVO idVo = empIdAndDtlsMap.get(id);
					if(idVo != null){
						idVo.setBetween10To11(entry.getValue());
					}
				}
			}
			//take the attended count between 11 to 13
			Map<Long,Long> empIdAndbetween11To13= new HashMap<Long,Long>();
			fromTime = sdf.parse("11:00:00");  
			toTime = sdf.parse("13:00:00");
			
			List<Object[]> totalMemberBetween11To13 = employeeWorkLocationDAO.getAttendanceReportTimeToTime(officeId,deptId,fromDate,toDate,fromTime,toTime);
			if(totalMemberBetween11To13 != null && totalMemberBetween11To13.size() > 0 ){
				for(Object[] param : totalMemberBetween11To13){  
					empIdAndbetween11To13.put((Long)param[0],(Long)param[1]);
				}
			}
			//push into vo
			if(empIdAndbetween11To13.size() > 0){
				for(Entry<Long,Long> entry : empIdAndbetween11To13.entrySet()){
					Long id = entry.getKey();
					IdNameVO idVo = empIdAndDtlsMap.get(id);
					if(idVo != null){
						idVo.setBetween11To13(entry.getValue());
					}
				}
			}
			//take the attended count after 13
			Map<Long,Long> empIdAndafter13= new HashMap<Long,Long>();
			toTime = sdf.parse("11:00:00");
			
			List<Object[]> totalMemberGreaterThan13 = employeeWorkLocationDAO.getAttendanceReportTimeToTime(officeId,deptId,fromDate,toDate,null,toTime);
			if(totalMemberGreaterThan13 != null && totalMemberGreaterThan13.size() > 0 ){
				for(Object[] param : totalMemberGreaterThan13){
					empIdAndafter13.put((Long)param[0],(Long)param[1]);
				}
			}
			//push into vo
			if(empIdAndafter13.size() > 0){
				for(Entry<Long,Long> entry : empIdAndafter13.entrySet()){
					Long id = entry.getKey();
					IdNameVO idVo = empIdAndDtlsMap.get(id);
					if(idVo != null){
						idVo.setGreaterThan13(entry.getValue());
					}
				}
			}
			List<IdNameVO> finalList = new ArrayList<IdNameVO>(empIdAndDtlsMap.values());
			return finalList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getAttendanceReportTimeToTime method of AttendanceCoreDashBoardService class");
		}
		return null;
		
	}
	/**
	 * @author Swadhin Lenka
	 * @since 09/24/2016
	 *
	 */
	public List<HolidayListVO> getDateWisePresentAbsentDtls(Long officeId, Long deptId, String fromDateStr, String toDateStr){
		LOG.info("Entered into getDateWisePresentAbsentDtls method of AttendanceCoreDashBoardService class");
		try{
			HolidayListVO holidayListVO = null;
			Date fromDate = null;
			Date toDate = null;
			Long diff = 0l;
			Long noOfWorkingDays = 0l;
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf1.parse(fromDateStr); 
				toDate = sdf1.parse(toDateStr);
				diff = toDate.getTime() - fromDate.getTime();
				noOfWorkingDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				noOfWorkingDays++;
			}
			Long holidayCount = holidayDAO.getHolidayCount(fromDate,toDate);
			if(holidayCount != null){
				noOfWorkingDays-=holidayCount;
			}
			List<Object[]> holidayList = holidayDAO.getHolidayList(fromDate, toDate);
			//create a list of holiday
			List<String> holidays = new ArrayList<String>();
			if(holidayList != null && holidayList.size() > 0){
				for(Object[] param : holidayList){
					holidays.add(param[1] != null ? param[1].toString().substring(0, 10) : "");//
				}
			}
			List<String> dateStrList = dateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
			//create isHolidayMap
			Map<String,String> isHolidayMap = new HashMap<String,String>();
			for(String currDate : dateStrList){
				if(holidays.contains(currDate)){
					isHolidayMap.put(currDate, "yes");
				}else{
					isHolidayMap.put(currDate, "no");
				}
			}
			//get Total employee of the dept.
			//total employee
			List<Long> officeIdList = new ArrayList<Long>();
			officeIdList.add(officeId);
			List<Long> deptIdList = new ArrayList<Long>();
			deptIdList.add(deptId);
			List<Long> employeeCadreIdList = employeeWorkLocationDAO.getEmployeeIdListOfficeWise(officeIdList,deptIdList);
			int noOfEmp = employeeCadreIdList.size();
			//no of emp presented per day
			//total no of presented employee
			//create a map for daywise present count  
			Map<String,Long> dayAndNoOfEmpPresentCount = new HashMap<String,Long>();
			List<Object[]> dayWisePresentCount = employeeWorkLocationDAO.getDayWisePresentCount(officeIdList,deptIdList,fromDate,toDate);
			if(dayWisePresentCount != null && dayWisePresentCount.size() > 0){
				for(Object[] param : dayWisePresentCount){
					dayAndNoOfEmpPresentCount.put(param[0] != null ? param[0].toString().substring(0, 10) : "", param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//create a List for  details of the day as value
			List<HolidayListVO> DayDtlsList = new ArrayList<HolidayListVO>();
			/*if(dayAndNoOfEmpPresentCount.size() > 0){
				for(Entry<String,Long> entry : dayAndNoOfEmpPresentCount.entrySet()){
					holidayListVO = new HolidayListVO();
					if(entry.getKey().length() > 1){
						holidayListVO.setDate(entry.getKey());
						holidayListVO.setIsHoliday( isHolidayMap.get(entry.getKey()) );
						holidayListVO.setPresentCount(entry.getValue());  
						
							if(isHolidayMap.get(entry.getKey()).equalsIgnoreCase("yes")){
								holidayListVO.setHolidayAbsentCount(noOfEmp-entry.getValue());
							}else{
								holidayListVO.setAbsentCount(noOfEmp-entry.getValue());
							}
						
						
					}
					DayDtlsList.add(holidayListVO);
				}
			}*/
			for(String dt : dateStrList){
				holidayListVO = new HolidayListVO();
				holidayListVO.setDate(dt);
				holidayListVO.setIsHoliday(isHolidayMap.get(dt));
				holidayListVO.setPresentCount(dayAndNoOfEmpPresentCount.get(dt) != null ? dayAndNoOfEmpPresentCount.get(dt) : 0l);
				if(isHolidayMap.get(dt).equalsIgnoreCase("yes")){
					holidayListVO.setHolidayAbsentCount(noOfEmp-(dayAndNoOfEmpPresentCount.get(dt) != null ? dayAndNoOfEmpPresentCount.get(dt) : 0l));
				}else{
					holidayListVO.setAbsentCount(noOfEmp-(dayAndNoOfEmpPresentCount.get(dt) != null ? dayAndNoOfEmpPresentCount.get(dt) : 0l));
				}
				DayDtlsList.add(holidayListVO);
			}
			return DayDtlsList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getDateWisePresentAbsentDtls method of AttendanceCoreDashBoardService class");
		}
		return null;
		
	}
	public IdNameVO getAttendanceCountForMulitDateForEmp(Long officeId, Long deptId, String fromDateStr, String toDateStr, Long cadreId){
		LOG.info("Entered into getAttendanceCountForMulitDateForEmp method of AttendanceCoreDashBoardService class");
		try{
			Long diff = 0l;
			Long noOfDays = 0l;
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf.parse(fromDateStr); 
				toDate = sdf.parse(toDateStr);
				diff = toDate.getTime() - fromDate.getTime();
				noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				noOfDays++;
			}
			//total employee
			List<Long> officeIdList = new ArrayList<Long>();
			officeIdList.add(officeId);
			List<Long> deptIdList = new ArrayList<Long>();
			deptIdList.add(deptId);
			
			//total no of presented employee and create a map for attended count on day
			Map<String,Long> dateAndCountMap = new HashMap<String,Long>();
			Long totalPresentedEmp = 0l;
			List<Object[]> dayWisePresentCount = employeeWorkLocationDAO.getDayWisePresentCountForEmp(officeIdList,deptIdList,fromDate,toDate,cadreId);
			if(dayWisePresentCount != null && dayWisePresentCount.size() > 0){
				for(Object[] param : dayWisePresentCount){
					totalPresentedEmp+=(Long)param[1];
					dateAndCountMap.put(param[0].toString().substring(0, 10), (Long)param[1]);
				}
			}
			//no of holidays between these days
			Long holidayCount = holidayDAO.getHolidayCount(fromDate,toDate);
			if(holidayCount != null){
				noOfDays-=holidayCount;
			}
			
			//
			List<Object[]> holidayList = holidayDAO.getHolidayList(fromDate, toDate);  
			//create a list of holiday
			List<String> holidays = new ArrayList<String>();
			if(holidayList != null && holidayList.size() > 0){
				for(Object[] param : holidayList){
					holidays.add(param[1] != null ? param[1].toString().substring(0, 10) : "");//
				}
			}
			Long holidayPresentCount = 0l;
			if(holidays.size() > 0){
				for(String hlyDay : holidays){
					if(dateAndCountMap.get(hlyDay) != null){
						holidayPresentCount+=dateAndCountMap.get(hlyDay);
					}
				}
			}
			//
			//prepare return vo
			IdNameVO idNameVO = new IdNameVO();
			
			idNameVO.setActualCount(noOfDays);//no of working days
			idNameVO.setAvailableCount(totalPresentedEmp);//total present
			Long absent = noOfDays - (totalPresentedEmp-holidayPresentCount);
			
			idNameVO.setId(absent);//absent
			return idNameVO;    
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getAttendanceCountForMulitDateForEmp method of AttendanceCoreDashBoardService class");
		}
		return null;
		
	}
	public IdNameVO getAttendanceCountForMulitDateTimeWiseForEmp(Long officeId,Long deptId,String fromDateStr,String toDateStr,Long cadreId){
		LOG.info("Entered into getAttendanceCountForMulitDateTimeWiseForEmp method of AttendanceCoreDashBoardService class");
		try{
			Date fromDate = null;
			Date toDate = null;
			Date fromTime =null;
			Date toTime = null;
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf1.parse(fromDateStr); 
				toDate = sdf1.parse(toDateStr);
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			//take the attended count before 9
			fromTime = sdf.parse("09:00:00"); 
			Long less9 = 0l;
			List<Object[]> totalMemberLess9 = employeeWorkLocationDAO.getTimeWisePresentCountForEmp(officeId,deptId,fromDate,toDate,fromTime,null,cadreId);
			if(totalMemberLess9 != null && totalMemberLess9.size() > 0 ){
				for(Object[] param : totalMemberLess9){
					less9+=(Long)param[1];
				}
			}
			//take the attended count between 9 to 9:30
			fromTime = sdf.parse("09:00:00");  
			toTime = sdf.parse("10:00:00");
			Long between9To10 = 0l;
			List<Object[]> totalMemberBetween9To10 = employeeWorkLocationDAO.getTimeWisePresentCountForEmp(officeId,deptId,fromDate,toDate,fromTime,toTime,cadreId);
			if(totalMemberBetween9To10 != null && totalMemberBetween9To10.size() > 0 ){
				for(Object[] param : totalMemberBetween9To10){
					between9To10+=(Long)param[1];
				}
			}
			//take the attended count between 9:30 to 10
			fromTime = sdf.parse("10:00:00");  
			toTime = sdf.parse("11:00:00");
			Long between10to11 = 0l;
			List<Object[]> totalMemberBetween10To11 = employeeWorkLocationDAO.getTimeWisePresentCountForEmp(officeId,deptId,fromDate,toDate,fromTime,toTime,cadreId);
			if(totalMemberBetween10To11 != null && totalMemberBetween10To11.size() > 0 ){
				for(Object[] param : totalMemberBetween10To11){
					between10to11+=(Long)param[1];
				}
			}
			//take the attended count between 10 to 11
			fromTime = sdf.parse("11:00:00");  
			toTime = sdf.parse("13:00:00");
			Long between11T013 = 0l;
			List<Object[]> totalMemberBetween11To13 = employeeWorkLocationDAO.getTimeWisePresentCountForEmp(officeId,deptId,fromDate,toDate,fromTime,toTime,cadreId);  
			if(totalMemberBetween11To13 != null && totalMemberBetween11To13.size() > 0 ){
				for(Object[] param : totalMemberBetween11To13){  
					between11T013+=(Long)param[1];
				}
			}
			//take the attended count after 11
			toTime = sdf.parse("13:00:00");
			Long after13 = 0l;
			List<Object[]> totalMemberGreaterThan13 = employeeWorkLocationDAO.getTimeWisePresentCountForEmp(officeId,deptId,fromDate,toDate,null,toTime,cadreId);
			if(totalMemberGreaterThan13 != null && totalMemberGreaterThan13.size() > 0 ){
				for(Object[] param : totalMemberGreaterThan13){
					after13+=(Long)param[1];
				}
			}
			//prepare vo for ui
			IdNameVO idNameVO = new IdNameVO();
			idNameVO.setId(less9);//less than 9
			idNameVO.setCount(between9To10);//between 9 to 10
			idNameVO.setActualCount(between10to11);//between 10 to 11
			idNameVO.setAvailableCount(between11T013);//between 11 to 13
			idNameVO.setOrderId(after13);//after 13
			
			return idNameVO;  
		}catch(Exception e){    
			e.printStackTrace();
			LOG.error("Error occured in getAttendanceCountForMulitDateTimeWiseForEmp method of AttendanceCoreDashBoardService class");
		}
		return null;
		
	}
	public List<HolidayListVO> getDateWisePresentAbsentDtlsForEmployee(Long officeId,Long  deptId,String  fromDateStr,String  toDateStr,Long cadreId){
		LOG.info("Entered into getDateWisePresentAbsentDtlsForEmployee method of AttendanceCoreDashBoardService class");
		try{
			HolidayListVO holidayListVO = null;
			Date fromDate = null;
			Date toDate = null;
			Long diff = 0l;
			Long noOfWorkingDays = 0l;
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			if(fromDateStr != null && !fromDateStr.isEmpty() && toDateStr != null  && !toDateStr.isEmpty()){
				fromDate = sdf1.parse(fromDateStr); 
				toDate = sdf1.parse(toDateStr);
				diff = toDate.getTime() - fromDate.getTime();
				noOfWorkingDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				noOfWorkingDays++;
			}
			Long holidayCount = holidayDAO.getHolidayCount(fromDate,toDate);
			if(holidayCount != null){
				noOfWorkingDays-=holidayCount;
			}
			List<Object[]> holidayList = holidayDAO.getHolidayList(fromDate, toDate);  
			//create a list of holiday
			List<String> holidays = new ArrayList<String>();
			if(holidayList != null && holidayList.size() > 0){
				for(Object[] param : holidayList){
					holidays.add(param[1] != null ? param[1].toString().substring(0, 10) : "");//
				}
			}
			List<String> dateStrList = dateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
			//create isHolidayMap
			Map<String,String> isHolidayMap = new HashMap<String,String>();
			for(String currDate : dateStrList){
				if(holidays.contains(currDate)){
					isHolidayMap.put(currDate, "yes");
				}else{
					isHolidayMap.put(currDate, "no");
				}
			}
			//get Total employee of the dept.
			//total employee
			List<Long> officeIdList = new ArrayList<Long>();
			officeIdList.add(officeId);
			List<Long> deptIdList = new ArrayList<Long>();
			deptIdList.add(deptId);
			
			//no of emp presented per day
			//total no of presented employee
			//create a map for daywise present count  
			Map<String,Long> dayAndNoOfEmpPresentCount = new HashMap<String,Long>();
			List<Object[]> dayWisePresentCount = employeeWorkLocationDAO.getDayWisePresentCountForEmp(officeIdList,deptIdList,fromDate,toDate,cadreId);
			if(dayWisePresentCount != null && dayWisePresentCount.size() > 0){  
				for(Object[] param : dayWisePresentCount){
					dayAndNoOfEmpPresentCount.put(param[0] != null ? param[0].toString().substring(0, 10) : "", param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//create a List for  details of the day as value
			List<HolidayListVO> DayDtlsList = new ArrayList<HolidayListVO>();
			
			for(String dt : dateStrList){
				holidayListVO = new HolidayListVO();
				holidayListVO.setDate(dt);
				holidayListVO.setIsHoliday(isHolidayMap.get(dt));
				if(dayAndNoOfEmpPresentCount.get(dt) != null){
					holidayListVO.setPresentCount(1l);  
				}else{
					holidayListVO.setAbsentCount(1l);
				}
				if(dayAndNoOfEmpPresentCount.get(dt) != null && isHolidayMap.get(dt).equalsIgnoreCase("yes")){
					holidayListVO.setHolidayAbsentCount(0l);
				}
				
				DayDtlsList.add(holidayListVO);    
			}
			return DayDtlsList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured in getDateWisePresentAbsentDtlsForEmployee method of AttendanceCoreDashBoardService class");
		}
		return null;
		
	}

	
}
//getDayWisePresentCount
//getHolidayList
//getDaysBetweenDatesStringFormat
