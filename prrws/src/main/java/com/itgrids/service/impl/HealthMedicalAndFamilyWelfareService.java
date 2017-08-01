package com.itgrids.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IDepartmentDiseasesInfoDAO;
import com.itgrids.dto.DiseasesVO;
import com.itgrids.service.IHealthMedicalAndFamilyWelfareService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
@Service
@Transactional
public class HealthMedicalAndFamilyWelfareService implements IHealthMedicalAndFamilyWelfareService {
	private static final Logger LOG = Logger.getLogger( DrainsService.class);

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private IDepartmentDiseasesInfoDAO departmentDiseasesInfoDAO;
	/*
	 * Author : Swadhin K Lenka
	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getCaseCountDiseasesWise(java.lang.String, java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	public List<DiseasesVO> getCaseCountDiseasesWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList){
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
			
			List<Object[]> diseasesList = departmentDiseasesInfoDAO.getCaseCountDiseasesWise(startDate,endDate,diseasesIdList,deptIdList);
			List<DiseasesVO> diseasesVOs = new ArrayList<DiseasesVO>();
			
			//only diseases wise count
			buildCaseCountDiseasesWise(startDate,endDate,diseasesIdList,deptIdList,diseasesVOs,diseasesList);
			return diseasesVOs;
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,getCaseCountDiseasesWise() ",e);
		}
		return null;
	}
	public void buildCaseCountDiseasesWise(Date startDate,Date endDate,List<Long> diseasesIdList,List<Long> deptIdList,List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList){
		try{
			Map<Long,Long> diseasesIdAndCountMap = new HashMap<Long,Long>();
			List<DiseasesVO> tempVOList = null;
			DiseasesVO diseasesVO = null;
			Long totalCount = 0L;
			if(diseasesList != null && diseasesList.size() > 0){
				for(Object[] param : diseasesList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
					diseasesVOs.add(diseasesVO);
					diseasesIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
				}
			}
			//get top districts for all diseases.
			List<Object[]> distCountList = departmentDiseasesInfoDAO.getTotLocationsDiseasesWiseCount(startDate,endDate,diseasesIdList,deptIdList,IConstants.DISTRICT_LEVEL_SCOPE_ID);
			if(distCountList != null && distCountList.size() > 0){
				tempVOList = new ArrayList<DiseasesVO>();
				for(Object[] param : distCountList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(commonMethodsUtilService.getLongValueForObject(param[2]),totalCount));
					tempVOList.add(diseasesVO);
				}
				diseasesVOs.get(0).getDistCountList().addAll(tempVOList);
			}
			//get top mandals for all diseases.
			List<Object[]> mandalCountList = departmentDiseasesInfoDAO.getTotLocationsDiseasesWiseCount(startDate,endDate,diseasesIdList,deptIdList,IConstants.MANDAL_LEVEL_SCOPE_ID);
			if(mandalCountList != null && mandalCountList.size() > 0){
				tempVOList = new ArrayList<DiseasesVO>();
				for(Object[] param : mandalCountList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(commonMethodsUtilService.getLongValueForObject(param[2]),totalCount));
					tempVOList.add(diseasesVO);
				}
				diseasesVOs.get(0).getMandalCountList().addAll(tempVOList);
			}
			//get top districts for Malaria.
			diseasesIdList.clear();
			diseasesIdList.add(1L);//Iconst
			List<Object[]> distCountForMalariaList = departmentDiseasesInfoDAO.getTotLocationsDiseasesWiseCount(startDate,endDate,diseasesIdList,deptIdList,IConstants.DISTRICT_LEVEL_SCOPE_ID);
			if(distCountForMalariaList != null && distCountForMalariaList.size() > 0){
				tempVOList = new ArrayList<DiseasesVO>();
				for(Object[] param : distCountForMalariaList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(commonMethodsUtilService.getLongValueForObject(param[2]),diseasesIdAndCountMap.get(1L)));
					tempVOList.add(diseasesVO);
				}
				diseasesVOs.get(0).getDistCountForMalariaList().addAll(tempVOList);
			}
			//get top mandals for Malaria.
			diseasesIdList.clear();
			diseasesIdList.add(1L);//Iconst
			List<Object[]> mandalCountForMalariaList = departmentDiseasesInfoDAO.getTotLocationsDiseasesWiseCount(startDate,endDate,diseasesIdList,deptIdList,IConstants.MANDAL_LEVEL_SCOPE_ID);
			if(mandalCountForMalariaList != null && mandalCountForMalariaList.size() > 0){
				tempVOList = new ArrayList<DiseasesVO>();
				for(Object[] param : mandalCountForMalariaList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(commonMethodsUtilService.getLongValueForObject(param[2]),diseasesIdAndCountMap.get(1L)));
					tempVOList.add(diseasesVO);
				}
				diseasesVOs.get(0).getMandalCountForMalariaList().addAll(tempVOList);
			}
			//get top districts for Dengue.
			diseasesIdList.clear();
			diseasesIdList.add(2L);
			List<Object[]> distCountForDengueList = departmentDiseasesInfoDAO.getTotLocationsDiseasesWiseCount(startDate,endDate,diseasesIdList,deptIdList,IConstants.DISTRICT_LEVEL_SCOPE_ID);
			if(distCountForDengueList != null && distCountForDengueList.size() > 0){
				tempVOList = new ArrayList<DiseasesVO>();
				for(Object[] param : distCountForDengueList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(commonMethodsUtilService.getLongValueForObject(param[2]),diseasesIdAndCountMap.get(2L)));
					tempVOList.add(diseasesVO);
				}
				diseasesVOs.get(0).getDistCountForDengueList().addAll(tempVOList);
			}
			//get top mandals for Dengue.
			diseasesIdList.clear();
			diseasesIdList.add(2L);
			List<Object[]> mandalCountForDengueList = departmentDiseasesInfoDAO.getTotLocationsDiseasesWiseCount(startDate,endDate,diseasesIdList,deptIdList,IConstants.MANDAL_LEVEL_SCOPE_ID);
			if(mandalCountForDengueList != null && mandalCountForDengueList.size() > 0){
				tempVOList = new ArrayList<DiseasesVO>();
				for(Object[] param : mandalCountForDengueList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVO.setPercent(commonMethodsUtilService.calculatePercantage(commonMethodsUtilService.getLongValueForObject(param[2]),diseasesIdAndCountMap.get(2L)));
					tempVOList.add(diseasesVO);
				}
				diseasesVOs.get(0).getMandalCountForDengueList().addAll(tempVOList);
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildCaseCountDiseasesWise() ",e);
		}
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getCaseCountLocationWise(java.lang.String, java.lang.String, java.util.List, java.util.List, java.lang.Long)
	 */
	@Override
	public List<DiseasesVO> getCaseCountLocationWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,Long scope){
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
			
			List<Object[]> diseasesList = departmentDiseasesInfoDAO.getCaseCountLocationWise(startDate,endDate,diseasesIdList,deptIdList,scope);
			List<DiseasesVO> diseasesVOs = new ArrayList<DiseasesVO>();
			
			//only diseases wise count
			buildCaseCountLocationWise(diseasesVOs,diseasesList);
			return diseasesVOs;
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,getCaseCountLocationWise() ",e);
		}
		return null;
	}
	public void buildCaseCountLocationWise(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList){
		try{
			DiseasesVO diseasesVO = null;
			Long totalCount = 0L;
			if(diseasesList != null && diseasesList.size() > 0){
				for(Object[] param : diseasesList){
					diseasesVO = new DiseasesVO();
					diseasesVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
					diseasesVOs.add(diseasesVO);
				}
			}
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildCaseCountLocationWise() ",e);
		}
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.service.IHealthMedicalAndFamilyWelfareService#getCaseCountDateWise(java.lang.String, java.lang.String, java.util.List, java.util.List, java.lang.String)
	 */
	@Override
	public List<DiseasesVO> getCaseCountDateWise(String fromDateStr,String toDateStr, List<Long> diseasesIdList,List<Long> deptIdList,String rangeType){
		try{
			Date startDate = commonMethodsUtilService.stringTODateConvertion(fromDateStr,"dd/MM/yyyy","");
			Date endDate = commonMethodsUtilService.stringTODateConvertion(toDateStr,"dd/MM/yyyy","");
			diseasesIdList = commonMethodsUtilService.makeEmptyListByZeroValue(diseasesIdList);
			deptIdList = commonMethodsUtilService.makeEmptyListByZeroValue(deptIdList);
			
			List<Object[]> diseasesList = departmentDiseasesInfoDAO.getCaseCountDateWise(startDate,endDate,diseasesIdList,deptIdList);
			List<DiseasesVO> diseasesVOs = new ArrayList<DiseasesVO>();
			if(diseasesList != null && diseasesList.size() > 0){
				buildCaseCountDateWise(diseasesVOs,diseasesList,rangeType,startDate,endDate);
			}
			
			return diseasesVOs;
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,getCaseCountDateWise() ",e);
		}
		return null;
	}
	public void buildCaseCountDateWise(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,String rangeType,Date startDate, Date endDate ){
		try{
			if(rangeType != null && !rangeType.trim().isEmpty() && rangeType.trim().length() > 0){
				if(rangeType.trim().equalsIgnoreCase("day")){
					buildDayWiseCaseCount(diseasesVOs,diseasesList,startDate,endDate);
				}else if(rangeType.trim().equalsIgnoreCase("week")){
					buildWeekWiseCaseCount(diseasesVOs,diseasesList,startDate,endDate);
				}else if(rangeType.trim().equalsIgnoreCase("month")){
					buildMonthWiseCaseCount(diseasesVOs,diseasesList,startDate,endDate);
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildCaseCountDateWise() ",e);
		}
	}
	public void buildDayWiseCaseCount(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,Date fromDate, Date toDate){
		try{
			//for day
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String firstDate = commonMethodsUtilService.getStringValueForObject(diseasesList.get(0)[1]);
			//update fromDate due to no data
			fromDate = sdf.parse(firstDate);
			List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
			//create a template for UI
			DiseasesVO diseasesVO = null;
			for(String param : dayList){
				diseasesVO = new DiseasesVO();
				diseasesVO.setCount(0L);
				diseasesVO.setPercent(0.0D);
				diseasesVO.setName(param);
				diseasesVO.setFromDate(param);
				diseasesVO.setToDate(param);
				diseasesVOs.add(diseasesVO);
			}
			//create a map for each date and no of cases.
			Map<String,Long> dateAndCaseCountMap = new HashMap<String,Long>();
			Long totalCount = 0L;
			for(Object[] param : diseasesList){
				totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				dateAndCaseCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
			//push count and percentage into template
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					if(dateAndCaseCountMap != null && dateAndCaseCountMap.get(param.getName()) != null){
						param.setCount(dateAndCaseCountMap.get(param.getName()));
						param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
					}
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildDayWiseCaseCount() ",e);
		}
	}
	public void buildWeekWiseCaseCount(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,Date fromDate, Date toDate){
		try{
			//for week
			LinkedHashMap<String,List<String>> weekAndDaysMap = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String firstDate = commonMethodsUtilService.getStringValueForObject(diseasesList.get(0)[1]);
			//update fromDate due to no data
			fromDate = sdf.parse(firstDate);
			weekAndDaysMap = DateUtilService.getTotalWeeksMap(fromDate, toDate);
			
			//create a template for UI
			DiseasesVO diseasesVO = null;
			for(Entry<String,List<String>> param : weekAndDaysMap.entrySet()){
				diseasesVO = new DiseasesVO();
				diseasesVO.setCount(0L);
				diseasesVO.setPercent(0.0D);
				diseasesVO.setName(param.getKey());
				diseasesVO.setFromDate(param.getValue().get(0));
				diseasesVO.setToDate(param.getValue().get(param.getValue().size()-1));
				diseasesVOs.add(diseasesVO);
			}
			//create a map for each date and no of cases.
			Map<String,Long> dateAndCaseCountMap = new HashMap<String,Long>();
			Long totalCount = 0L;
			for(Object[] param : diseasesList){
				totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				dateAndCaseCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
			//push count and percentage into template
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					Long weekCount = 0L;
					for(String dateParam : weekAndDaysMap.get(param.getName())){
						if(dateAndCaseCountMap != null && dateAndCaseCountMap.get(dateParam) != null){
							weekCount = weekCount + dateAndCaseCountMap.get(dateParam);
						}
					}
					param.setCount(weekCount);
					param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildDayWiseCaseCount() ",e);
		}
	}
	public void buildMonthWiseCaseCount(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList,Date fromDate, Date toDate){
		try{
			//for week
			LinkedHashMap<String,List<String>> monthAndDaysMap = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			String firstDate = commonMethodsUtilService.getStringValueForObject(diseasesList.get(0)[1]);
			//update fromDate due to no data
			fromDate = sdf.parse(firstDate);
			String fromDateStr = sdf1.format(fromDate);
			String toDateStr = sdf1.format(toDate);
			monthAndDaysMap = getMonthWeekAndDaysList(fromDateStr, toDateStr,"month");
			
			//create a template for UI
			DiseasesVO diseasesVO = null;
			for(Entry<String,List<String>> param : monthAndDaysMap.entrySet()){
				diseasesVO = new DiseasesVO();
				diseasesVO.setCount(0L);
				diseasesVO.setPercent(0.0D);
				diseasesVO.setName(param.getKey());
				diseasesVO.setFromDate(param.getValue().get(0));
				diseasesVO.setToDate(param.getValue().get(param.getValue().size()-1));
				diseasesVOs.add(diseasesVO);
			}
			//create a map for each date and no of cases.
			Map<String,Long> dateAndCaseCountMap = new HashMap<String,Long>();
			Long totalCount = 0L;
			for(Object[] param : diseasesList){
				totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				dateAndCaseCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
			}
			//push count and percentage into template
			if(diseasesVOs != null && diseasesVOs.size() > 0){
				for(DiseasesVO param : diseasesVOs){
					Long weekCount = 0L;
					for(String dateParam : monthAndDaysMap.get(param.getName())){
						if(dateAndCaseCountMap != null && dateAndCaseCountMap.get(dateParam) != null){
							weekCount = weekCount + dateAndCaseCountMap.get(dateParam);
						}
					}
					param.setCount(weekCount);
					param.setPercent(commonMethodsUtilService.calculatePercantage(param.getCount(),totalCount));
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,buildDayWiseCaseCount() ",e);
		}
	}
	public LinkedHashMap<String,List<String>> getMonthWeekAndDaysList(String startDate,String endDate,String type){
		LinkedHashMap<String,List<String>> returnDays = new LinkedHashMap<String, List<String>>();
    	try{
		
		List<String> wkDays = new ArrayList<String>();
		List<String> daysArr = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-yyyy");
		if(type != null && type.trim().equalsIgnoreCase("month")){
		 List<String> mntDays = departmentDiseasesInfoDAO.getMonthAndYear(sdf.parse(startDate),sdf.parse(endDate));
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
				
				daysArr = dateUtilService.getDaysBetweenDatesStringFormat(monthStart,monthEnd);
				returnDays.put(string,daysArr);
				i++;
			}
		}else if(type != null && type.trim().equalsIgnoreCase("week")){
			wkDays  = commonMethodsUtilService.getBetweenWeeks(sdf.parse(startDate),sdf.parse(endDate),"yyyy-MM-dd");
			int i=1;
				for (String string : wkDays) {
					String[] days = string.split("to");
					daysArr = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(days[0]),sdf.parse(days[1]));
					returnDays.put("week"+i,daysArr);
					i++;
			}
		}else if(type != null && type.trim().equalsIgnoreCase("today")){
			List<String> noOfDays = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(startDate),sdf.parse(endDate));
			int i=1;
				for (String string : noOfDays) {
					daysArr = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(string),sdf.parse(string));
					returnDays.put("day"+i, daysArr);
					i++;
				}
		   }
    	}catch(Exception e){
    		LOG.error("Error occured getMonthWeekAndDays() method of AlertManagementSystemService",e);
    	}
		
		return returnDays;
	}
	/*public void buildCaseCountLocationWise(List<DiseasesVO> diseasesVOs,List<Object[]> diseasesList){
		try{
			DiseasesVO diseasesVO = null;
			List<DiseasesVO> tempList = null;
			Map<Long,List<DiseasesVO>> diseasesWiseLocMap = new HashMap<Long,List<DiseasesVO>>();
			
			if(diseasesList != null && diseasesList.size() > 0){
				for(Object[] param : diseasesList)
				{
					diseasesVO = new DiseasesVO();
					diseasesVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					diseasesVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					diseasesVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[2]));
					diseasesVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[3]));
					diseasesVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					tempList = diseasesWiseLocMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(tempList == null){
						tempList = new ArrayList<DiseasesVO>();
						tempList.add(diseasesVO);
					}else{
						tempList.add(diseasesVO);
					}
					diseasesWiseLocMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), tempList);
				}
			}
			if(diseasesWiseLocMap != null && diseasesWiseLocMap.size() > 0){
				for(Entry<Long,List<DiseasesVO>> param : diseasesWiseLocMap.entrySet()){
					diseasesVO = new DiseasesVO();
					diseasesVO.getSubList1().addAll(param.getValue());
					diseasesVO.setId(param.getValue().get(0).getId());
					diseasesVO.setName(param.getValue().get(0).getName());
					diseasesVOs.add(diseasesVO);
				}
			}
		}catch(Exception e){
			LOG.error(" Exception occured in HealthMedicalAndFamilyWelfareService ,getDiseasesWiseThenLocationWiseCount() ",e);
		}
	}*/
}
