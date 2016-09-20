/**
 * All implementation methods related to core dash board attendance goes here.
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDepartmentDAO;
import com.itgrids.partyanalyst.dao.IEmployeeDepartmentDAO;
import com.itgrids.partyanalyst.dao.IEmployeeWorkLocationDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
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
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
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
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
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
	public List<IdNameVO> getAttendanceOverViewForPartyOfficeDeptWise(String fromDateStr, String toDateStr){
		LOG.info("Entered into getAttendanceOverViewForPartyOfficeDeptWise method of AttendanceCoreDashBoardService class");
		try{
			List<Long> departmentIdList = new ArrayList<Long>();
			List<Long> presentedCaderIdList = new ArrayList<Long>();
			Map<Long,String> employeeIdLocationMap = new HashMap<Long,String>();   
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
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
	
}
