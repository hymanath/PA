package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class CadreDashBoardService implements ICadreDashBoardService {

	private static Logger LOG = Logger.getLogger(CadreDashBoardService.class);
	
	private ITdpCadreDAO tdpCadreDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothDAO boothDAO;
	private IDistrictDAO districtDAO;
	private IPanchayatDAO panchayatDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private DateUtilService dateService = new DateUtilService();

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public List<CadreRegisterInfo> getDashBoardBasicInfo(){
		
		List<CadreRegisterInfo> returnResult = new ArrayList<CadreRegisterInfo>();
		
		Date currentDate = dateService.getCurrentDateAndTime();
		CadreRegisterInfo todayInfo = getRegisterCount(currentDate,currentDate);
		
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(currentDate);
		fromCalendar.add(Calendar.DAY_OF_WEEK, fromCalendar.getFirstDayOfWeek() - fromCalendar.get(Calendar.DAY_OF_WEEK));
	   
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(fromCalendar.getTime());
		toCalendar.add(Calendar.DAY_OF_YEAR, 6);
		
		CadreRegisterInfo thisWeekInfo = getRegisterCount(fromCalendar.getTime(),toCalendar.getTime());
		
		fromCalendar.set(Calendar.DAY_OF_MONTH, 1);
	    toCalendar.set(Calendar.DAY_OF_MONTH,toCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

	     
	     CadreRegisterInfo thisMonthInfo = getRegisterCount(fromCalendar.getTime(),toCalendar.getTime());
	     
	     CadreRegisterInfo totalCadreInfo = getRegisterCount(null,null);
	     
	     CadreRegisterInfo newlyRegisterCadreInfo = getNewlyRegisterCount();
	     
	     returnResult.add(todayInfo);
	     returnResult.add(thisWeekInfo);
	     returnResult.add(thisMonthInfo);
	     returnResult.add(totalCadreInfo);
	     returnResult.add(newlyRegisterCadreInfo);
	     
	     return returnResult;
	}
	
	
	 public CadreRegisterInfo getRegisterCount(Date fromDate,Date toDate){
		CadreRegisterInfo info = new CadreRegisterInfo();
		Long apCount = 0l;
		Long tgCount = 0l;
		
		try{
          List<Object[]> districtWiseCount = tdpCadreDAO.getRegisterCadreInfoBetweenDates(fromDate, toDate);
		  for(Object[] districtCount:districtWiseCount){
			if(((Long)districtCount[1]).longValue() > 10l){
				apCount = apCount+(Long)districtCount[0];
			}else{
				tgCount = tgCount+(Long)districtCount[0];	
			}
		  }
		}catch(Exception e){
			LOG.error("Exception rised in getRegisterCount",e);
		}
		info.setApCount(apCount);
		info.setTgCount(tgCount);
		info.setTotalCount(apCount+tgCount);
		
		return info;
	}
	 
	 public CadreRegisterInfo getNewlyRegisterCount(){
			CadreRegisterInfo info = new CadreRegisterInfo();
			Long apCount = 0l;
			Long tgCount = 0l;
			
			try{
	          List<Object[]> districtWiseCount = tdpCadreDAO.getNewlyRegisterCadreInfo();
			  for(Object[] districtCount:districtWiseCount){
				if(((Long)districtCount[1]).longValue() > 10l){
					apCount = apCount+(Long)districtCount[0];
				}else{
					tgCount = tgCount+(Long)districtCount[0];	
				}
			  }
			}catch(Exception e){
				LOG.error("Exception rised in getNewlyRegisterCount",e);
			}
			info.setApCount(apCount);
			info.setTgCount(tgCount);
			info.setTotalCount(apCount+tgCount);
			
			return info;
		}
	 
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
		   CadreRegisterInfo info = null;
		 //0 first name ,1 lastname,2 constituency ,3 localArea, 4 image
		   List<Object[]> cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadres();
		   for(Object[] cadre:cadreDetails){
			   StringBuilder name = new StringBuilder("");
			   if(cadre[0] != null){
				   name.append(cadre[0].toString());
			   }
			   if(cadre[1] != null){
				   name.append(" "+cadre[1].toString());
			   }
				info = new CadreRegisterInfo();
				info.setName(name.toString());
				if(cadre[3] != null){
				    info.setArea(cadre[3].toString());
				}else{
					info.setArea("");
				}
				info.setLocation(cadre[2].toString());
				if(cadre[4] != null){
				info.setDate("images/cadre_images/"+cadre[4].toString());
				}
				returnList.add(info);
			}
		}catch(Exception e){
        	LOG.error("Exception rised in getRecentlyRegisteredCadresInfo",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getConstituencyWiseRegistrationInfo(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo info = null;
		try{
		    List<Object[]> constituencyWiseCount = tdpCadreDAO.getRegisterCadreInfoConstituencyWise();
			for(Object[] constituency:constituencyWiseCount){
				info = new CadreRegisterInfo();
				info.setName(constituency[1].toString());
				info.setTotalCount((Long)constituency[0]);
				returnList.add(info);
			}
        }catch(Exception e){
        	LOG.error("Exception rised in getConstituencyWiseRegistrationInfo",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getDistrictWiseRegistrationInfo(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo info = null;
			List<Object[]> districtWiseCount = tdpCadreDAO.getRegisterCadreInfoDistrictWise();
			
			for(Object[] district:districtWiseCount){
				info = new CadreRegisterInfo();
				info.setName(district[1].toString());
				info.setTotalCount((Long)district[0]);
				returnList.add(info);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getDistrictWiseRegistrationInfo",e);
		}
		return returnList;
	}

	public List<CadreRegisterInfo> getWorkStartedConstituencyCount(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			
			 Long tsCount = tdpCadreDAO.getWorkStartedConstituencyCount("TS");
			 Long apCount = tdpCadreDAO.getWorkStartedConstituencyCount("AP");
			 Long count_2012TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"TS");
			 Long count_2012AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"AP");
			 Long count_2014AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"AP");
			 Long count_2014TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"TS");
			 
			 CadreRegisterInfo apVo = new CadreRegisterInfo();
			 CadreRegisterInfo tsVo = new CadreRegisterInfo();
			 
			 apVo.setTotalCount(apCount);
			 tsVo.setTotalCount(tsCount);
			 
			 apVo.setApCount(count_2012AP);
			 tsVo.setApCount(count_2012TS);
			 
			 apVo.setTgCount(count_2014AP);
			 tsVo.setTgCount(count_2014TS);
			 
			 apVo.setPercentage(0l);
			 tsVo.setPercentage(0l);
			 
			 if(apVo.getApCount().longValue() > 0l && apVo.getTgCount().longValue() > 0l){
				 apVo.setPercentage((apVo.getTgCount().longValue()*100)/apVo.getApCount().longValue());
			 }
			 
             if(tsVo.getApCount().longValue() > 0l && tsVo.getTgCount().longValue() > 0l){
            	 tsVo.setPercentage((tsVo.getTgCount().longValue()*100)/tsVo.getApCount().longValue());
			 }
			 
			 returnList.add(apVo);
			 returnList.add(tsVo);
			
		}catch(Exception e){
			LOG.error("Exception rised in getWorkStartedConstituencyCount",e);
		}
		return returnList;
	}
			
	public List<CadreRegisterInfo> getAssemblyWiseCompletedPercentage(Long assemblyId,Long stateId){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			List<Long> assemblyIds = null;
			CadreRegisterInfo infoVo = null;
			if(assemblyId == null || assemblyId.longValue() == 0){
				assemblyIds = tdpCadreDAO.getCadreAvailableConstituencies(stateId);
			}else{
				assemblyIds = new ArrayList<Long>();
				assemblyIds.add(assemblyId);
			}
			Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
			Map<Long,String> locationNames = new HashMap<Long,String>();
			Map<Long,Long> yearMap = null;
			if(assemblyIds.size() > 0){
				//0 count,1 id,2 name ,3 year
				List<Object[]>  constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(assemblyIds);
				
				for(Object[] info:constituencyInfoList){
					 yearMap = locationMap.get((Long)info[1]);
					 if(yearMap == null){
						 yearMap = new HashMap<Long,Long>();
						 locationMap.put((Long)info[1],yearMap);
						 locationNames.put((Long)info[1],info[2].toString());
					 }
					 yearMap.put((Long)info[3], (Long)info[0]);
				}
			}
			for(Long locationId:locationMap.keySet()){
				yearMap = locationMap.get(locationId);
				if(yearMap.size() >= 2){
					Long previousCount = yearMap.get(2012l);
					Long currentCount = yearMap.get(2014l);
					if(previousCount != null && previousCount.longValue() > 0 && currentCount != null && currentCount.longValue() > 0){
						infoVo = new CadreRegisterInfo();
						infoVo.setLocation(locationNames.get(locationId));
						infoVo.setTotalCount(currentCount);
						infoVo.setApCount((currentCount*100)/previousCount);
						infoVo.setTgCount(locationId);
						returnList.add(infoVo);
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAssemblyWiseCompletedPercentage",e);
		}
		
		return returnList;
	}
	
	public List<CadreRegisterInfo> getDistrictWiseCompletedPercentage(Long districtId,Long stateId){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			List<Long> districtIds = null;
			CadreRegisterInfo infoVo = null;
			if(districtId == null || districtId.longValue() == 0){
				districtIds = tdpCadreDAO.getCadreAvailableDistricts(stateId);
			}else{
				districtIds = new ArrayList<Long>();
				districtIds.add(districtId);
			}
			Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
			Map<Long,String> locationNames = new HashMap<Long,String>();
			Map<Long,Long> yearMap = null;
			if(districtIds.size() > 0){
				//0 count,1 id,2 name ,3 year
				List<Object[]>  constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(districtIds);
				
				for(Object[] info:constituencyInfoList){
					 yearMap = locationMap.get((Long)info[1]);
					 if(yearMap == null){
						 yearMap = new HashMap<Long,Long>();
						 locationMap.put((Long)info[1],yearMap);
						 locationNames.put((Long)info[1],info[2].toString());
					 }
					 yearMap.put((Long)info[3], (Long)info[0]);
				}
			}
			for(Long locationId:locationMap.keySet()){
				yearMap = locationMap.get(locationId);
				if(yearMap.size() >= 2){
					Long previousCount = yearMap.get(2012l);
					Long currentCount = yearMap.get(2014l);
					if(previousCount != null && previousCount.longValue() > 0 && currentCount != null && currentCount.longValue() > 0){
						infoVo = new CadreRegisterInfo();
						infoVo.setLocation(locationNames.get(locationId));
						infoVo.setTotalCount(currentCount);
						infoVo.setApCount((currentCount*100)/previousCount);
						infoVo.setTgCount(locationId);
						returnList.add(infoVo);
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception rised in getDistrictWiseCompletedPercentage",e);
		}
		
		return returnList;
	}
	
	public CadreRegisterInfo getWorkingMembersInfo(){
		CadreRegisterInfo info = new CadreRegisterInfo();
		try{
			Date date = dateService.getCurrentDateAndTime();
			Long count = tdpCadreDAO.getWorkingMembersCount(date);
			info.setTotalCount(count);
		}catch(Exception e){
			LOG.error("Exception rised in getWorkingMembersInfo",e);
		}
		
		return info;
	}
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Date fromDate,Date toDate){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo vo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
		try{
			List<Date> datesList = new ArrayList<Date>();
			Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
			Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
			Map<Long,String> userNames = new HashMap<Long,String>();
			//0 count,1 name,2 min,3 max,4 date,5 id
			List<Object[]> dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo(fromDate, toDate);
			
			for(Object[] data:dataCollectedInfo){
				if(!datesList.contains((Date)data[4])){
					datesList.add((Date)data[4]);
				}
				userNames.put((Long)data[5], data[1].toString());
				dateMap = userMap.get((Long)data[5]);
				if(dateMap == null){
					dateMap = new HashMap<Date,CadreRegisterInfo>();
					userMap.put((Long)data[5],dateMap);
				}
				vo = new CadreRegisterInfo() ;
				vo.setArea(convertTimeTo12HrsFormat(timeFormate.format((Date)data[2])));
				vo.setLocation(convertTimeTo12HrsFormat(timeFormate.format((Date)data[3])));
				vo.setTotalCount((Long)data[0]);
				dateMap.put((Date)data[4], vo);
			}
			int count = 0;
			for(Long key:userMap.keySet()){
				dateMap = userMap.get(key);
				vo = new CadreRegisterInfo();
				vo.setName(userNames.get(key));
				List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
				for(Date date:datesList){
					if(dateMap.get(date) != null){
						CadreRegisterInfo day = dateMap.get(date);
					    if(count == 0){
						  day.setDate(sdf.format(date));
					    }
						daysList.add(day);
					}else{
						CadreRegisterInfo day = new CadreRegisterInfo();
						if(count == 0){
						   day.setDate(sdf.format(date));
						 }
						daysList.add(day);
					}
				}
				vo.setInfoList(daysList);
				returnList.add(vo);
				count++;
			}
		}catch(Exception e){
			LOG.error("Exception rised in getCandidateDataCollectionInfo",e);
		}
		return returnList;
	}
	
	private String convertTimeTo12HrsFormat(String time){
		String[] timeArray = time.split(":");
		int hours = Integer.parseInt(timeArray[0].trim());
		if(hours > 11){
			if(hours != 12){
			   hours = hours-12;
			}
			return hours+":"+timeArray[1]+" PM";
		}
		if(hours == 0){
			return "12:"+timeArray[1]+" AM";
		}
		
		return time+" AM";
	}
	
	public Map<Long,List<CadreRegisterInfo>> getConstituencyWiseAgeRangeCount(Long constituencyId) {
		Map<Long,List<CadreRegisterInfo>> returnMap=new HashMap<Long,List<CadreRegisterInfo>>();
		
		List<CadreRegisterInfo> cadreInfo2012 = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> cadreInfo2014 = new ArrayList<CadreRegisterInfo>();
		try {
			List<Object[]> cadre18to25info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(constituencyId,"18-25");
			List<Object[]> cadre26to35info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(constituencyId,("26-35"));
			List<Object[]> cadre36to45info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(constituencyId,("36-45"));
			List<Object[]> cadre46to60info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(constituencyId,("46-60"));
			List<Object[]> cadreabove60info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(constituencyId,("above 60"));
			Long totalConstituencyCount2012 = tdpCadreDAO.getConstituencyWiseYearCount(constituencyId, 2012L);
			Long totalConstituencyCount2014 = tdpCadreDAO.getConstituencyWiseYearCount(constituencyId, 2014L);

			setAgeWiseRangeCount(cadre18to25info, cadre26to35info,cadre36to45info, cadre46to60info, cadreabove60info,totalConstituencyCount2012, totalConstituencyCount2014,cadreInfo2012, cadreInfo2014);
            
			returnMap.put(2012L, cadreInfo2012);
            returnMap.put(2014L, cadreInfo2014);
            
		} catch (Exception e) {
			LOG.error("Exception rised in getConstituencyWiseAgeRangeCount", e);
		}
		return returnMap;
	}

	public Map<Long,List<CadreRegisterInfo>> getDistrictWiseAgeRangeCount(Long districtId) {
		
		Map<Long,List<CadreRegisterInfo>> returnMap=new HashMap<Long,List<CadreRegisterInfo>>();
		List<CadreRegisterInfo> cadreInfo2012 = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> cadreInfo2014 = new ArrayList<CadreRegisterInfo>();
		try {
			List<Object[]> cadre18to25info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(districtId, "18-25");
			List<Object[]> cadre26to35info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(districtId, ("26-35"));
			List<Object[]> cadre36to45info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(districtId, ("36-45"));
			List<Object[]> cadre46to60info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(districtId, ("46-60"));
			List<Object[]> cadreabove60info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(districtId, ("above 60"));
			Long totalDistrictCount2012 = tdpCadreDAO.getDistrictWiseYearCount(districtId, 2012L);
			Long totalDistrictCount2014 = tdpCadreDAO.getDistrictWiseYearCount(districtId, 2014L);

			setAgeWiseRangeCount(cadre18to25info, cadre26to35info,cadre36to45info, cadre46to60info, cadreabove60info,totalDistrictCount2012, totalDistrictCount2014,cadreInfo2012, cadreInfo2014);
            returnMap.put(2012L, cadreInfo2012);
            returnMap.put(2014L, cadreInfo2014);
			
		} catch (Exception e) {
			LOG.error("Exception rised in getDistrictWiseAgeRangeCount", e);
		}
		return returnMap;

	}

	public void setAgeWiseRangeCount(List<Object[]> cadre18to25info,
			List<Object[]> cadre26to35info, List<Object[]> cadre36to45info,
			List<Object[]> cadre46to60info, List<Object[]> cadreabove60info,
			Long totalCount2012, Long totalCount2014,
			List<CadreRegisterInfo> cadreInfo2012,
			List<CadreRegisterInfo> cadreInfo2014) {
		CadreRegisterInfo vo1 = null;

		if (cadre18to25info.size() > 0) {
			for (Object[] cadre18to25info1 : cadre18to25info) {
				if ((Long) cadre18to25info1[1] == 2012) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre18to25info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2012.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2012.add(vo1);
				}
				if ((Long) cadre18to25info1[1] == 2014) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre18to25info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2014.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2014.add(vo1);
				}
			}
		}

		if (cadre26to35info.size() > 0) {
			for (Object[] cadre26to35info1 : cadre26to35info) {
				if ((Long) cadre26to35info1[1] == 2012) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre26to35info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2012.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2012.add(vo1);
				}
				if ((Long) cadre26to35info1[1] == 2014) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre26to35info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2014.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2014.add(vo1);
				}
			}
		}

		if (cadre36to45info.size() > 0) {
			for (Object[] cadre36to45info1 : cadre36to45info) {
				if ((Long) cadre36to45info1[1] == 2012) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre36to45info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2012.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2012.add(vo1);
				}
				if ((Long) cadre36to45info1[1] == 2014) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre36to45info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2014.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2014.add(vo1);
				}
			}
		}

		if (cadre46to60info.size() > 0) {
			for (Object[] cadre46to60info1 : cadre46to60info) {
				if ((Long) cadre46to60info1[1] == 2012) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre46to60info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2012.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2012.add(vo1);
				}
				if ((Long) cadre46to60info1[1] == 2014) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadre46to60info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2014.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2014.add(vo1);
				}
			}
		}

		if (cadreabove60info.size() > 0) {
			for (Object[] cadreabove60info1 : cadreabove60info) {
				if ((Long) cadreabove60info1[1] == 2012) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadreabove60info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2012.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2012.add(vo1);
				}
				if ((Long) cadreabove60info1[1] == 2014) {
					vo1 = new CadreRegisterInfo();
					Long total = (Long) cadreabove60info1[0];
					vo1.setTotalCount(total);
					String b = new BigDecimal(
							(total.doubleValue() / totalCount2014.doubleValue()) * 100)
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					Double per = new Double(b);
					Long percentage = per.longValue();
					vo1.setPercentage(percentage);
					cadreInfo2014.add(vo1);
				}
			}
		}

	}
	
	public Map<Long,List<CadreRegisterInfo>> getConstituencyWiseGenderCadreCount(Long constituencyId){
		Map<Long,List<CadreRegisterInfo>> genderMap = new HashMap<Long,List<CadreRegisterInfo>>();
		try{
			List<Object[]> genderInfoList=tdpCadreDAO.getConstituencyWiseGenderCadreCount(constituencyId);
			Long totalConstituencyCount2012 = tdpCadreDAO.getDistrictWiseYearCount(constituencyId, 2012L);
			Long totalConstituencyCount2014 = tdpCadreDAO.getDistrictWiseYearCount(constituencyId, 2014L);
			setGenderWiseCount(genderInfoList,genderMap,totalConstituencyCount2012,totalConstituencyCount2014);
		
			
		} catch (Exception e) {
		LOG.error("Exception rised in getConstituencyWiseGenderCadreCount", e);
		}
		return genderMap;
	}
	
	public Map<Long,List<CadreRegisterInfo>> getDistrictWiseGenderCadreCount(Long districtId){
		Map<Long,List<CadreRegisterInfo>> genderMap = new HashMap<Long,List<CadreRegisterInfo>>();
		try{
			List<Object[]> genderInfoList=tdpCadreDAO.getDistrictWiseGenderCadreCount(districtId);
			Long totalDistrictCount2012 = tdpCadreDAO.getDistrictWiseYearCount(districtId, 2012L);
			Long totalDistrictCount2014 = tdpCadreDAO.getDistrictWiseYearCount(districtId, 2014L);
			setGenderWiseCount(genderInfoList,genderMap,totalDistrictCount2012,totalDistrictCount2014);
			} catch (Exception e) {
			LOG.error("Exception rised in getDistrictWiseGenderCadreCount", e);
			}
		return genderMap;
		
	}
	
	public void setGenderWiseCount(List<Object[]> genderInfoList,Map<Long,List<CadreRegisterInfo>> genderMap,Long totalCount2012,Long totalCount2014){
		List<CadreRegisterInfo> genderInfo2012 = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> genderInfo2014 = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo genderVO=null;
		//totalCount for count
		//apCount for year
		//name for Gender
		//percentage for percentage
		for(Object[] gender:genderInfoList){
			if((Long)gender[1]==2012){
				genderVO=new CadreRegisterInfo();
				Long total=(Long)gender[0];
				genderVO.setTotalCount(total);
				genderVO.setApCount((Long)gender[1]);
				genderVO.setName((String)gender[2]);
				String b = new BigDecimal(
						(total.doubleValue() / totalCount2012.doubleValue()) * 100)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per = new Double(b);
				Long percentage = per.longValue();
				genderVO.setPercentage(percentage);
				genderInfo2012.add(genderVO);
			}
			if((Long)gender[1]==2014){
				genderVO=new CadreRegisterInfo();
				Long total=(Long)gender[0];
				genderVO.setTotalCount(total);
				genderVO.setApCount((Long)gender[1]);
				genderVO.setName((String)gender[2]);
				String b = new BigDecimal(
						(total.doubleValue() / totalCount2014.doubleValue()) * 100)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per = new Double(b);
				Long percentage = per.longValue();
				genderVO.setPercentage(percentage);
				genderInfo2014.add(genderVO);				
			}
		}
		genderMap.put(2012L, genderInfo2012);	
		genderMap.put(2014L, genderInfo2014);
	}

	public Map<Long,List<CadreRegisterInfo>> getConstituencyWiseCastCadreCount(Long constituencyId){
		Map<Long,List<CadreRegisterInfo>> casteMap = new HashMap<Long,List<CadreRegisterInfo>>();
		try{
			List<Object[]> casteInfoList = tdpCadreDAO.getConstituencyWiseCastCadreCount(constituencyId);
			Long totalConstituencyCount2012 = tdpCadreDAO.getDistrictWiseYearCount(constituencyId, 2012L);
			Long totalConstituencyCount2014 = tdpCadreDAO.getDistrictWiseYearCount(constituencyId, 2014L);
			setCasteWiseCount(casteInfoList, casteMap,totalConstituencyCount2012,totalConstituencyCount2014);
		}catch (Exception e) {
			LOG.error("Exception rised in getConstituencyWiseCastCadreCount", e);
		}			
		return casteMap;
		
	}
	
	public Map<Long,List<CadreRegisterInfo>> getDistrictWiseCastCadreCount(Long districtId){
		Map<Long,List<CadreRegisterInfo>> casteMap = new HashMap<Long,List<CadreRegisterInfo>>();
		try{
			List<Object[]> casteInfoList = tdpCadreDAO.getDistrictWiseCastCadreCount(districtId);
			Long totalDistrictCount2012 = tdpCadreDAO.getDistrictWiseYearCount(districtId, 2012L);
			Long totalDistrictCount2014 = tdpCadreDAO.getDistrictWiseYearCount(districtId, 2014L);
			setCasteWiseCount(casteInfoList, casteMap,totalDistrictCount2012,totalDistrictCount2014);
		}catch (Exception e) {
			LOG.error("Exception rised in getDistrictWiseCastCadreCount", e);
		}		
		return casteMap;		
	}
	
	public static void setCasteWiseCount(List<Object[]> casteInfoList,Map<Long,List<CadreRegisterInfo>> casteMap,Long totalCount2012,Long totalCount2014){
		List<CadreRegisterInfo> genderInfo2012 = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> genderInfo2014 = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo casteVO=null;
		//totalCount for count
		//apCount for entrolmentyear
		//tgCount for casteStateId
		//name for casteName
		//percentage for percentage
		for(Object[] caste:casteInfoList){
			if((Long)caste[1]==2012){
				casteVO=new CadreRegisterInfo();
				Long total=(Long)caste[0];
				casteVO.setTotalCount(total);
				casteVO.setApCount((Long)caste[1]);
				casteVO.setTgCount((Long)caste[2]);
				casteVO.setName((String)caste[3]);
				String b = new BigDecimal(
						(total.doubleValue() / totalCount2012.doubleValue()) * 100)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per = new Double(b);
				Long percentage = per.longValue();
				casteVO.setPercentage(percentage);
				
				genderInfo2012.add(casteVO);
			}
			if((Long)caste[1]==2014){
				casteVO=new CadreRegisterInfo();
				Long total=(Long)caste[0];
				casteVO.setTotalCount(total);
				casteVO.setApCount((Long)caste[1]);
				casteVO.setTgCount((Long)caste[2]);
				casteVO.setName((String)caste[3]);
				String b = new BigDecimal(
						(total.doubleValue() / totalCount2014.doubleValue()) * 100)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per = new Double(b);
				Long percentage = per.longValue();
				casteVO.setPercentage(percentage);
				
				genderInfo2014.add(casteVO);				
			}
		}
		casteMap.put(2012L, genderInfo2012);	
		casteMap.put(2014L, genderInfo2014);
	}
	
	public List<CadreRegisterInfo> getAssemblyConstituencies(String type){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo vo = null;
			List<Object[]> constituenciesList = constituencyDAO.getAssemblyConstituenciesInAP(type);
			for(Object[] constituency:constituenciesList){
				vo = new CadreRegisterInfo();
				vo.setId((Long)constituency[0]);
				vo.setName(constituency[1].toString());
				returnList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAssemblyConstituencies",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getPanchayatsInConstituencies(Long constituencyId){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo vo = null;
			List<Object[]> constituenciesList = boothDAO.getPanchayatsByConstituencyAndPublication(constituencyId,11l);
			for(Object[] constituency:constituenciesList){
				vo = new CadreRegisterInfo();
				vo.setId((Long)constituency[0]);
				vo.setName(constituency[1].toString());
				returnList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getPanchayatsInConstituencies",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getBoothsInConstituencies(Long constituencyId){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo vo = null;
			List<Object[]> constituenciesList = boothDAO.getBoothsInAConstituency(constituencyId,11l);
			for(Object[] constituency:constituenciesList){
				vo = new CadreRegisterInfo();
				vo.setId((Long)constituency[0]);
				vo.setName(constituency[1].toString());
				returnList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getBoothsInConstituencies",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getStateWiseRegistrationInfo(List<Long> stateIds){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			if(stateIds.contains(1l)){
				 Long count_2012AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"AP");
				 Long count_2014AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"AP");
				 CadreRegisterInfo apVo = new CadreRegisterInfo();
				 apVo.setLocation("Andhra Pradesh");
				 apVo.setApCount(count_2014AP);
				 apVo.setTgCount(count_2012AP);
				 returnList.add(apVo);
			}
			if(stateIds.contains(36l)){
			    Long count_2012TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"TS");
			    Long count_2014TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"TS");
			    CadreRegisterInfo tgVo = new CadreRegisterInfo();
			    tgVo.setLocation("Telangana");
			    tgVo.setApCount(count_2014TS);
			    tgVo.setTgCount(count_2012TS);
				 returnList.add(tgVo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getStateWiseRegistrationInfo",e);
		}
		return returnList;
	}
	
	
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo(List<Long> ids,String type){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo infoVo = null;
			Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
			Map<Long,Long> yearMap = null;
			List<Object[]> namesList = new ArrayList<Object[]>();
			//0 count,1 id,2 name ,3 year
			List<Object[]> constituencyInfoList = new ArrayList<Object[]>();
			if(ids.size() > 0){
				if(type.equals("assembly")){
					constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(ids);
					 namesList = constituencyDAO.getConstituencyNameByConstituencyIdsList(ids);
				}else if(type.equals("district")){
				    constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(ids);
				    namesList = districtDAO.getDistrictDetailsByDistrictIds(ids);
				}else if(type.equals("panchayat")){
				    constituencyInfoList = tdpCadreDAO.getCadreInfoPanchayatWise(ids);
				    namesList = panchayatDAO.getPanchayatNamesByIds(ids);
				}else if(type.equals("booth")){
				    constituencyInfoList = tdpCadreDAO.getCadreInfoBoothWise(ids);
				    namesList = boothDAO.getBoothNamesByIds(ids);
				}else if(type.equals("mandal")){
					List<Long> mandalIds = new ArrayList<Long>();
					List<Long> localBodyIds = new ArrayList<Long>();
					for(Long id:ids){
						if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
							localBodyIds.add(new Long(id.toString().substring(1)));
						}else{
							mandalIds.add(new Long(id.toString().substring(1)));
						}
					}
					if(mandalIds.size() > 0){
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoMandalWise(mandalIds));
						namesList.addAll(tehsilDAO.getTehsilNameByTehsilIdsList(mandalIds));
					}
					if(localBodyIds.size() > 0){
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoLocalBodyWise(localBodyIds));
						namesList.addAll(localElectionBodyDAO.getLocalElectionBodyNames(localBodyIds));
					}
				     
				}
				for(Object[] info:constituencyInfoList){
					 yearMap = locationMap.get((Long)info[1]);
					 if(yearMap == null){
						 yearMap = new HashMap<Long,Long>();
						 locationMap.put((Long)info[1],yearMap);
					 }
					 yearMap.put((Long)info[3], (Long)info[0]);
				}
			}
			for(Object[] name:namesList){
			    yearMap = locationMap.get((Long)name[0]);
			    infoVo = new CadreRegisterInfo();
			    infoVo.setLocation(name[1].toString());
			    if(yearMap != null){
					infoVo.setApCount(yearMap.get(2014l));
					infoVo.setTgCount(yearMap.get(2012l));
					if((type.equals("assembly") || type.equals("district")) && infoVo.getApCount() != null && infoVo.getApCount().longValue() > 0 && infoVo.getTgCount() != null && infoVo.getTgCount().longValue() > 0 ){
						infoVo.setName(new BigDecimal(infoVo.getApCount()*(100.0)/infoVo.getTgCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
			    }
			    returnList.add(infoVo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getLocationWiseRegistrationInfo",e);
		}
		Collections.sort(returnList,locationSort);
		return returnList;
	}
	
	public  Comparator<CadreRegisterInfo> locationSort = new Comparator<CadreRegisterInfo>(){
				  
	  public int compare(CadreRegisterInfo vo1, CadreRegisterInfo vo2)
		{
		   return vo1.getLocation().compareTo(vo2.getLocation());
		}
	};
	
}
