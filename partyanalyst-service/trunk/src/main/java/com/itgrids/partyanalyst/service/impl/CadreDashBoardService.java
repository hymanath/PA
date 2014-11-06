package com.itgrids.partyanalyst.service.impl;

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

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAppDbUpdateDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreDashBoardService implements ICadreDashBoardService {

	private static Logger LOG = Logger.getLogger(CadreDashBoardService.class);
	
	private ITdpCadreDAO tdpCadreDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothDAO boothDAO;
	private IDistrictDAO districtDAO;
	private IPanchayatDAO panchayatDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	private DateUtilService dateService = new DateUtilService();
	private IAppDbUpdateDAO appDbUpdateDAO;

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

	public ICadreSurveyUserDAO getCadreSurveyUserDAO() {
		return cadreSurveyUserDAO;
	}

	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}

	public IAppDbUpdateDAO getAppDbUpdateDAO() {
		return appDbUpdateDAO;
	}

	public void setAppDbUpdateDAO(IAppDbUpdateDAO appDbUpdateDAO) {
		this.appDbUpdateDAO = appDbUpdateDAO;
	}

	public List<CadreRegisterInfo> getDashBoardBasicInfo(){
		
		List<CadreRegisterInfo> returnResult = new ArrayList<CadreRegisterInfo>();
		
		Date currentDate = dateService.getCurrentDateAndTime();
		CadreRegisterInfo todayInfo = getRegisterCount(currentDate,currentDate);
		
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(currentDate);
		fromCalendar.set(Calendar.DAY_OF_MONTH,  fromCalendar.get(Calendar.DAY_OF_MONTH)-6);
		//fromCalendar.add(Calendar.DAY_OF_WEEK, fromCalendar.getFirstDayOfWeek() - fromCalendar.get(Calendar.DAY_OF_WEEK));
	   
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(currentDate);
		//toCalendar.setTime(fromCalendar.getTime());
		//toCalendar.add(Calendar.DAY_OF_YEAR, 6);
		
		CadreRegisterInfo thisWeekInfo = getRegisterCount(fromCalendar.getTime(),toCalendar.getTime());
		
		fromCalendar.setTime(currentDate);
		fromCalendar.set(Calendar.DAY_OF_MONTH,  fromCalendar.get(Calendar.DAY_OF_MONTH)-29);
		//fromCalendar.set(Calendar.DAY_OF_MONTH, 1);
	    //toCalendar.set(Calendar.DAY_OF_MONTH,toCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

	     
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
		Long apWebCount = 0l;
		Long tgWebCount = 0l;
		Long apTabCount = 0l;
		Long tgTabCount = 0l;
		try{
          List<Object[]> districtWiseCount = tdpCadreDAO.getRegisterCadreInfoBetweenDates(fromDate, toDate);
		  for(Object[] districtCount:districtWiseCount){
			if(((Long)districtCount[1]).longValue() > 10l){
				apCount = apCount+(Long)districtCount[0];
				if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("WEB"))
				apWebCount = apWebCount +  (Long)districtCount[0];
				else
				apTabCount = apTabCount + 	 (Long)districtCount[0];
				
			}else{
				tgCount = tgCount+(Long)districtCount[0];	
				if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("WEB"))
					tgWebCount = tgWebCount +  (Long)districtCount[0];
					else
					tgTabCount = tgTabCount + 	 (Long)districtCount[0];
			}
		  }
		}catch(Exception e){
			LOG.error("Exception rised in getRegisterCount",e);
		}
		info.setApCount(apCount);
		info.setTgCount(tgCount);
		info.setTotalCount(apCount+tgCount);
		info.setApWebCount(apWebCount);
		info.setApTabCount(apTabCount);
		info.setTgWebCount(tgWebCount);
		info.setTgTabCount(tgTabCount);
		
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
	
	
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo(Integer startIndex,Integer maxIndex){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
		   CadreRegisterInfo info = null;
		 //0 first name ,1 lastname,2 constituency ,3 localArea, 4 image
		   List<Object[]> cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadres(startIndex,maxIndex);
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
			 Long count_2012TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"TS",null,null);
			 Long count_2012AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"AP",null,null);
			 Long count_2014AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"AP",null,null);
			 Long count_2014TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"TS",null,null);
			 
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
				List<Object[]>  constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(assemblyIds,null,null,2014l);
				constituencyInfoList.addAll( tdpCadreDAO.getCadreInfoConstituencytWise(assemblyIds,null,null,2012l));
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
						infoVo.setDate(new BigDecimal(currentCount*(100.0)/previousCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
				List<Object[]>  constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(districtIds,null,null,2014l);
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictWise(districtIds,null,null,2012l));
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
						infoVo.setDate(new BigDecimal(currentCount*(100.0)/previousCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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

	/*public CadreRegisterInfo getWorkingMembersInfo(){
		CadreRegisterInfo info = new CadreRegisterInfo();
		try{
			Date date = dateService.getCurrentDateAndTime();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			cal.add(Calendar.HOUR, -1);
			Date oneHourBack = cal.getTime();
			
			cal.add(Calendar.HOUR, -2);
			Date towHourBack = cal.getTime();
			
			Long count = tdpCadreDAO.getWorkingMembersCount(date);
			info.setTotalCount(count);
			
			Long count1 = tdpCadreDAO.getLastHoursWorkingMemberCount(date, oneHourBack); // field members in the last 1 hr			
			info.setApCount(count1);
			
			Long count2 = tdpCadreDAO.getLastHoursWorkingMemberCount(date, towHourBack); // field members in the last 2 hrs
			info.setTgCount(count2);
			
		}catch(Exception e){
			LOG.error("Exception rised in getWorkingMembersInfo",e);
		}
		
		return info;
	}*/
	
	public CadreRegisterInfo getWorkingMembersInfo(String hours)
	{
		CadreRegisterInfo info = new CadreRegisterInfo();
		try{
			Date date = dateService.getCurrentDateAndTime();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			Date hourBack = null;
			Long count  = 0L;
			
			if(hours != null && hours.trim().length()>0 && !hours.equalsIgnoreCase("0"))
			{
				int hourCount = Integer.valueOf(hours);						
				cal.add(Calendar.HOUR, -hourCount);
				hourBack = cal.getTime();
				
				count = tdpCadreDAO.getLastHoursWorkingMemberCount(date,hourBack);
			}
			else
			{
				count = tdpCadreDAO.getWorkingMembersCount(date);
			}
			
			info.setTotalCount(count);

		}catch(Exception e){
			LOG.error("Exception rised in getWorkingMembersInfo",e);
		}
		
		return info;
	}
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo vo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
		try{
			List<Date> datesList = new ArrayList<Date>();
			Map<Long,String> unameMap = new HashMap<Long, String>();
			Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
			Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
			Map<Long,String> userNames = new HashMap<Long,String>();
			Map<Long,CadreRegisterInfo> mobileNos = new HashMap<Long,CadreRegisterInfo>();
			//0 count,1 name,2 min,3 max,4 date,5 id
			List<Object[]> dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo(locationType,locationIds,fromDate, toDate);
			
			for(Object[] data:dataCollectedInfo){
				if(!datesList.contains((Date)data[4])){
					datesList.add((Date)data[4]);
				}
				userNames.put((Long)data[5], data[1].toString());
				dateMap = userMap.get((Long)data[5]);
				if(dateMap == null){
					dateMap = new HashMap<Date,CadreRegisterInfo>();
					userMap.put((Long)data[5],dateMap);
					unameMap.put((Long)data[5], data[1].toString());
				}
				vo = new CadreRegisterInfo() ;
				vo.setArea(convertTimeTo12HrsFormat(timeFormate.format((Date)data[2])));
				vo.setLocation(convertTimeTo12HrsFormat(timeFormate.format((Date)data[3])));
				vo.setTotalCount((Long)data[0]);
				vo.setAmount(vo.getTotalCount() * 100);
				dateMap.put((Date)data[4], vo);
			}
			int count = 0;
			if(userNames.size() > 0){
				//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
				List<Object[]> mobileNosList = cadreSurveyUserDAO.getUserMobileNos(userNames.keySet());
				for(Object[] mobileNo:mobileNosList){
					CadreRegisterInfo userData = new CadreRegisterInfo();
				 if(mobileNo[1] != null){
					userData.setArea(mobileNo[1].toString());//mobileNo
				 }
				 if(((Long)mobileNo[4]).longValue() < 11){
				     userData.setLocation("Telangana");//state
				 }else{
					 userData.setLocation("AndhraPradesh");//state
				 }
				 userData.setNumber(mobileNo[5].toString());//district
				 userData.setMemberShipNo(mobileNo[3].toString());//constituency
				 mobileNos.put((Long)mobileNo[0], userData);
				}
			}
			for(Long key:userMap.keySet()){
				dateMap = userMap.get(key);
				vo = new CadreRegisterInfo();
				vo.setName(userNames.get(key));
				
				vo.setUname(unameMap.get(key) != null ? unameMap.get(key).toString() : "");
				CadreRegisterInfo userData = mobileNos.get(key);
				if(userData != null){
				    vo.setArea(userData.getArea());//mobileNo
				    vo.setLocation(userData.getLocation());//state
				    vo.setNumber(userData.getNumber());//district
				    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
				}
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
	
	public List<CadreRegisterInfo> getDetailsForConstituency(Long constituencyId)
	{
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try {
			//Long totalConstituencyCount2012 = tdpCadreDAO.getGenderTotalCount(constituencyId, 2012L,"constituency");
			//Long totalConstituencyCount2014 = tdpCadreDAO.getGenderTotalCount(constituencyId, 2014L,"constituency");
			List<CadreRegisterInfo> ageWiseInfo = getConstituencyWiseAgeRangeCount(constituencyId);
			
			if(ageWiseInfo != null && ageWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("agewise");
				mainVO.setAllDetailsList(ageWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> genderWiseInfo = getConstituencyWiseGenderCadreCount(constituencyId);
			if(genderWiseInfo != null && genderWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("genderwise");
				mainVO.setInfoList(genderWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> casteWiseInfo = getConstituencyWiseCastCadreCount(constituencyId);
			
			if(casteWiseInfo != null && casteWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("castewise");
				mainVO.setCadreRegisterInfoList(casteWiseInfo);
				returnList.add(mainVO);
			}
			
		} catch (Exception e) {
			LOG.error("Exception rised in getDetailsForState",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getDetailsForDistricts(Long districtId)
	{
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try {
			//Long totalDistrictCount2012 = tdpCadreDAO.getAgeRangeTotalCount(districtId, 2012L,"district");
			//Long totalDistrictCount2014 = tdpCadreDAO.getAgeRangeTotalCount(districtId, 2014L,"district");
			List<CadreRegisterInfo> ageWiseInfo = getDistrictWiseAgeRangeCount(districtId);
			
			if(ageWiseInfo != null && ageWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("agewise");
				mainVO.setAllDetailsList(ageWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> genderWiseInfo = getDistrictWiseGenderCadreCount(districtId);
			
			if(genderWiseInfo != null && genderWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("genderwise");
				mainVO.setInfoList(genderWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> casteWiseInfo = getDistrictWiseCastCadreCount(districtId);
			
			if(casteWiseInfo != null && casteWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("castewise");
				mainVO.setCadreRegisterInfoList(casteWiseInfo);
				returnList.add(mainVO);
			}
			
		} catch (Exception e) {
			LOG.error("Exception rised in getDetailsForDistricts",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getConstituencyWiseAgeRangeCount(Long constituencyId) {
		List<CadreRegisterInfo> ageWiseTotalList=new ArrayList<CadreRegisterInfo>();
		try {
			List<Object[]> countsList = tdpCadreDAO.getAgeTotalCount(constituencyId, "constituency");
			Long totalConstituencyCount2014 = null;
			Long totalConstituencyCount2012 = null;
			for(Object[] counts:countsList){
				if(counts[1] != null){
					if(((Long)counts[1]).longValue() == 2012l){
						totalConstituencyCount2012 = (Long)counts[0];
					}else if(((Long)counts[1]).longValue() == 2014l){
						totalConstituencyCount2014 = (Long)counts[0];
					}
				}
			}
			
			List<Object[]> cadreBelow18 = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"below 18","constituency");
			setAgeWiseRangeCount(cadreBelow18,"below 18", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre18to25info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"18-25","constituency");
			setAgeWiseRangeCount(cadre18to25info,"18-25", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre26to35info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"26-35","constituency");
			setAgeWiseRangeCount(cadre26to35info,"26-35", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre36to45info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"36-45","constituency");
			setAgeWiseRangeCount(cadre36to45info,"36-45", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre46to60info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"46-60","constituency");
			setAgeWiseRangeCount(cadre46to60info,"46-60", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadreabove60info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"above 60","constituency");
			setAgeWiseRangeCount(cadreabove60info,"above 60", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
            
		} catch (Exception e) {
			LOG.error("Exception rised in getConstituencyWiseAgeRangeCount", e);
		}
		return ageWiseTotalList;
	}

	public List<CadreRegisterInfo> getDistrictWiseAgeRangeCount(Long districtId) {
		List<CadreRegisterInfo> ageWiseTotalList=new ArrayList<CadreRegisterInfo>();
		
		try {	
			
			 List<Object[]> countsList = tdpCadreDAO.getAgeTotalCount(districtId, "district");
				Long totalDistrictCount2014 = null;
				Long totalDistrictCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalDistrictCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalDistrictCount2014 = (Long)counts[0];
						}
					}
				}
			
			List<Object[]> cadreBelow18 = tdpCadreDAO.getAgeRangeCadreCount(districtId, "below 18","district");			
			setAgeWiseRangeCount(cadreBelow18,"below 18", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
				
			List<Object[]> cadre18to25info = tdpCadreDAO.getAgeRangeCadreCount(districtId, "18-25","district");			
			setAgeWiseRangeCount(cadre18to25info,"18-25", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadre26to35info = tdpCadreDAO.getAgeRangeCadreCount(districtId, "26-35","district");
			setAgeWiseRangeCount(cadre26to35info,"26-35", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadre36to45info = tdpCadreDAO.getAgeRangeCadreCount(districtId,"36-45","district");
			setAgeWiseRangeCount(cadre36to45info,"36-45", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadre46to60info = tdpCadreDAO.getAgeRangeCadreCount(districtId,"46-60","district");
			setAgeWiseRangeCount(cadre46to60info,"46-60", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadreabove60info = tdpCadreDAO.getAgeRangeCadreCount(districtId,"above 60","district");
			setAgeWiseRangeCount(cadreabove60info,"above 60", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
            
		} catch (Exception e) {
			LOG.error("Exception rised in getDistrictWiseAgeRangeCount", e);
		}
		return ageWiseTotalList;
	}	
	
	public void setAgeWiseRangeCount(List<Object[]> cadreinfo,String ageRange,Long totalCount2012, Long totalCount2014,List<CadreRegisterInfo> ageWiseTotalList) {

		CadreRegisterInfo ageVo = new CadreRegisterInfo();
		ageVo.setName(ageRange);
		ageVo.setApCount(0l);//2014 total count
		ageVo.setTgCount(0l);//2012 total count
		ageVo.setPercentStr("0");//2014 perc
		ageVo.setArea("0");//2012 perc
		if (cadreinfo!=null && cadreinfo.size() > 0)
		{						
			for (Object[] cadreinfo1 : cadreinfo) 
			{
				if (((Long)cadreinfo1[1]).longValue() == 2014l){
					 ageVo.setApCount((Long) cadreinfo1[0]);
						if(totalCount2014 != null && totalCount2014.longValue() > 0 ){
							ageVo.setPercentStr(new BigDecimal((ageVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						} 
			   }				
			   if (((Long)cadreinfo1[1]).longValue() == 2012l){ 
				   
					ageVo.setTgCount((Long)cadreinfo1[0]);
					if(totalCount2012 != null && totalCount2012.longValue() > 0 ){
						ageVo.setArea(new BigDecimal((ageVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} 
			   }   
			}
		}  
		ageWiseTotalList.add(ageVo);
	} 
	
	public List<CadreRegisterInfo> getConstituencyWiseGenderCadreCount(Long constituencyId){
		List<CadreRegisterInfo> genderList = new ArrayList<CadreRegisterInfo>();
		try{
			 List<Object[]> countsList = tdpCadreDAO.getGenderTotalCount(constituencyId, "constituency");
				Long totalConstituencyCount2014 = null;
				Long totalConstituencyCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalConstituencyCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalConstituencyCount2014 = (Long)counts[0];
						}
					}
				}
			List<Object[]> genderInfoList=tdpCadreDAO.getGenderWiseCadreCount(constituencyId,"constituency");
			setGenderWiseCount(genderInfoList,genderList,totalConstituencyCount2012,totalConstituencyCount2014);							
						
		} catch (Exception e) {
			LOG.error("Exception rised in getConstituencyWiseGenderCadreCount", e);
		}
		return genderList;
	}
	
	public List<CadreRegisterInfo> getDistrictWiseGenderCadreCount(Long districtId){
		List<CadreRegisterInfo> genderList = new ArrayList<CadreRegisterInfo>();
		try{
			 List<Object[]> countsList = tdpCadreDAO.getGenderTotalCount(districtId, "district");
				Long totalCount2014 = null;
				Long totalCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalCount2014 = (Long)counts[0];
						}
					}
				}
			List<Object[]> genderInfoList=tdpCadreDAO.getGenderWiseCadreCount(districtId,"district");
			setGenderWiseCount(genderInfoList,genderList,totalCount2012,totalCount2014);
			
			} catch (Exception e) {
				LOG.error("Exception rised in getDistrictWiseGenderCadreCount", e);
			}
		return genderList;
		
	}
	
	public void setGenderWiseCount(List<Object[]> genderInfoList,List<CadreRegisterInfo> genderList,Long totalCount2012,Long totalCount2014){
		CadreRegisterInfo maleVo = new CadreRegisterInfo();
			maleVo.setName("Male");
			maleVo.setApCount(0l);//2014 total count
			maleVo.setTgCount(0l);//2012 total count
			maleVo.setPercentStr("0");//2014 perc
			maleVo.setArea("0");//2012 perc
			
		CadreRegisterInfo femaleVo = new CadreRegisterInfo();
		   femaleVo.setName("Female");
		   femaleVo.setApCount(0l);//2014 total count
		   femaleVo.setTgCount(0l);//2012 total count
		   femaleVo.setPercentStr("0");//2014 perc
		   femaleVo.setArea("0");//2012 perc
		   
		genderList.add(maleVo);
		genderList.add(femaleVo);
		
		for(Object[] gender:genderInfoList)
		{	
			if(((Long)gender[1]).longValue() == 2014l){
				if( gender[2] != null && (gender[2].toString().equals("Male") || gender[2].toString().equals("M"))){
					maleVo.setApCount(maleVo.getApCount()+(Long)gender[0]);
				}else if( gender[2] != null && (gender[2].toString().equals("Female") || gender[2].toString().equals("F"))){
					femaleVo.setApCount(femaleVo.getApCount()+(Long)gender[0]);
				}	
			}else if(((Long)gender[1]).longValue() == 2012l){
				if( gender[2] != null && (gender[2].toString().equals("Male") || gender[2].toString().equals("M"))){
					maleVo.setTgCount(maleVo.getTgCount()+(Long)gender[0]);
				}else if( gender[2] != null && (gender[2].toString().equals("Female") || gender[2].toString().equals("F"))){
					femaleVo.setTgCount(femaleVo.getTgCount()+(Long)gender[0]);
				}	
			}
			
		}
	
		if(totalCount2012 != null && totalCount2012.longValue() > 0l){
			maleVo.setArea(new BigDecimal((maleVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			femaleVo.setArea(new BigDecimal((femaleVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(totalCount2014 != null && totalCount2014.longValue() > 0l){
			maleVo.setPercentStr(new BigDecimal((maleVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			femaleVo.setPercentStr(new BigDecimal((femaleVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
			
	}

	public List<CadreRegisterInfo> getConstituencyWiseCastCadreCount(Long constituencyId){
		List<CadreRegisterInfo> casteList = new ArrayList<CadreRegisterInfo>();
		try{
			List<Object[]> countsList = tdpCadreDAO.getCasteGroupTotalCount(constituencyId, "constituency");
			Long totalCount2014 = null;
			Long totalCount2012 = null;
			for(Object[] counts:countsList){
				if(counts[1] != null){
					if(((Long)counts[1]).longValue() == 2012l){
						totalCount2012 = (Long)counts[0];
					}else if(((Long)counts[1]).longValue() == 2014l){
						totalCount2014 = (Long)counts[0];
					}
				}
			}
			//0 count,1 year,2 casteState ,3 casteName
			List<Object[]> casteInfoList = tdpCadreDAO.getCastWiseCadreCount(constituencyId,"constituency");
			setCasteWiseCount(casteInfoList, casteList,totalCount2012,totalCount2014);				
		}catch (Exception e) {
			//LOG.error("Exception rised in getConstituencyWiseCastCadreCount", e);
		}			
		return casteList;
		
	}
	
	public List<CadreRegisterInfo> getDistrictWiseCastCadreCount(Long districtId){
		List<CadreRegisterInfo> casteList = new ArrayList<CadreRegisterInfo>();
		try{
			List<Object[]> countsList = tdpCadreDAO.getCasteGroupTotalCount(districtId, "district");
			Long totalCount2014 = null;
			Long totalCount2012 = null;
			for(Object[] counts:countsList){
				if(counts[1] != null){
					if(((Long)counts[1]).longValue() == 2012l){
						totalCount2012 = (Long)counts[0];
					}else if(((Long)counts[1]).longValue() == 2014l){
						totalCount2014 = (Long)counts[0];
					}
				}
			}
			List<Object[]> casteInfoList = tdpCadreDAO.getCastWiseCadreCount(districtId,"district");
			setCasteWiseCount(casteInfoList, casteList,totalCount2012,totalCount2014);
		}catch (Exception e) {
			//LOG.error("Exception rised in getDistrictWiseCastCadreCount", e);
		}		
		return casteList;		
	}
	
	public void setCasteWiseCount(List<Object[]> casteInfoList,List<CadreRegisterInfo> casteList,Long totalCount2012,Long totalCount2014){
		
		LinkedHashMap<Long,CadreRegisterInfo> casteMap = new LinkedHashMap<Long,CadreRegisterInfo>();
		//0 count,1 year,2 casteStateId ,3 casteName,4casteGroup
		for(Object[] caste:casteInfoList){
			
			CadreRegisterInfo casteVo = casteMap.get((Long)caste[2]);
			if(casteVo == null){
				casteVo = new CadreRegisterInfo();
				casteVo.setName(caste[3].toString());
				casteVo.setApCount(0l);//2014 total count
				casteVo.setTgCount(0l);//2012 total count
				casteVo.setPercentStr("0");//2014 perc
				casteVo.setArea("0");//2012 perc
				casteVo.setDate(caste[4].toString());//casteGroup
				casteMap.put((Long)caste[2],casteVo);
			}
			if(((Long)caste[1]).longValue() == 2014l){
				casteVo.setApCount((Long)caste[0]);
				if(totalCount2014 != null && totalCount2014.longValue() > 0){
					casteVo.setPercentStr(new BigDecimal((casteVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}else if(((Long)caste[1]).longValue() == 2012l){
				casteVo.setTgCount((Long)caste[0]);
				if(totalCount2012 != null && totalCount2012.longValue() > 0){
					casteVo.setArea(new BigDecimal((casteVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			
		}
		casteList.addAll(new ArrayList<CadreRegisterInfo>(casteMap.values()));
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
	
	public List<CadreRegisterInfo> getStateWiseRegistrationInfo(List<Long> stateIds,String fromDateStr, String toDateStr){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			
			Date fromDate =  null;
			Date toDate = null;
			
			if(fromDateStr != null && fromDateStr.trim().length()>0)
			{
				fromDate = format.parse(fromDateStr);
			}
			if(toDateStr != null && toDateStr.trim().length()>0)
			{
				toDate = format.parse(toDateStr);
			}
			
			
			if(stateIds.contains(1l)){
				 Long count_2012AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"AP",null,null);
				 Long count_2014AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"AP",fromDate,toDate);
				 CadreRegisterInfo apVo = new CadreRegisterInfo();
				 apVo.setLocation("Andhra Pradesh");
				 apVo.setId(1l);
				 apVo.setApCount(count_2014AP);
				 apVo.setTgCount(count_2012AP);
				 returnList.add(apVo);
			}
			if(stateIds.contains(36l)){
			    Long count_2012TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"TS",null,null);
			    Long count_2014TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"TS",fromDate,toDate);
			    CadreRegisterInfo tgVo = new CadreRegisterInfo();
			    tgVo.setLocation("Telangana");
			    tgVo.setId(36l);
			    tgVo.setApCount(count_2014TS);
			    tgVo.setTgCount(count_2012TS);
				 returnList.add(tgVo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getStateWiseRegistrationInfo",e);
		}
		return returnList;
	}
	
	
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo(List<Long> ids,String type,String fromDateStr, String toDateStr,boolean reqOthers){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			CadreRegisterInfo infoVo = null;
			Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
			Map<Long,String> locationType = new HashMap<Long,String>();
			Map<Long,Long> yearMap = null;
			List<Object[]> namesList = new ArrayList<Object[]>();
			//0 count,1 id,2 name ,3 year
			List<Object[]> constituencyInfoList = new ArrayList<Object[]>();
			
			Date fromDate =  null;
			Date toDate = null;
			
			if(fromDateStr != null && fromDateStr.trim().length()>0)
			{
				fromDate = format.parse(fromDateStr);
			}
			if(toDateStr != null && toDateStr.trim().length()>0)
			{
				toDate = format.parse(toDateStr);
			}
			
			Long constituencyId = 0L;
			
			if(ids.size() > 0){
				if(type.equals("assembly")){
					constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(ids,fromDate,toDate,2014l);
					constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoConstituencytWise(ids,null,null,2012l));
					 namesList = constituencyDAO.getConstituencyNameByConstituencyIdsList(ids);
				}else if(type.equals("district")){
				    constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(ids,fromDate,toDate,2014l);
				    constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictWise(ids,null,null,2012l));
				    namesList = districtDAO.getDistrictDetailsByDistrictIds(ids);
				}else if(type.equals("panchayat")){
					constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(ids.get(0), type);
				    constituencyInfoList = tdpCadreDAO.getCadreInfoPanchayatWise(ids,fromDate,toDate,2014l);
				    constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoPanchayatWise(ids,null,null,2012l));
				    namesList = panchayatDAO.getPanchayatNamesByIds(ids);
				}else if(type.equals("booth")){
					constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(ids.get(0), type);
				    constituencyInfoList = tdpCadreDAO.getCadreInfoBoothWise(ids,fromDate,toDate,2014l);
				    constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoBoothWise(ids,null,null,2012l));
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
						constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(mandalIds.get(0), type);
					}else if(localBodyIds.size() > 0){
						constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(localBodyIds.get(0), "localBody");
					}
					if(mandalIds.size() > 0){
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoMandalWise(mandalIds,fromDate,toDate,2014l));
						 constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoMandalWise(ids,null,null,2012l));
						namesList.addAll(tehsilDAO.getTehsilNameByTehsilIdsList(mandalIds));
					}
					if(localBodyIds.size() > 0){
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoLocalBodyWise(localBodyIds,fromDate,toDate,2014l));
						 constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoLocalBodyWise(ids,null,null,2012l));
						namesList.addAll(localElectionBodyDAO.getLocalElectionBodyNames(localBodyIds));
					}
				     
				}
				for(Object[] info:constituencyInfoList){
					 yearMap = locationMap.get((Long)info[1]);
					 if(yearMap == null){
						 yearMap = new HashMap<Long,Long>();
						 locationMap.put((Long)info[1],yearMap);
						 if(type.equals("assembly") || type.equals("mandal")){
						   locationType.put((Long)info[1], info[4].toString());
						 }
					 }
					 yearMap.put((Long)info[3], (Long)info[0]);
				}
			}
			
			Map<Long,Long> yearWiseTotalCadreMap = new HashMap<Long, Long>();
			if(namesList != null && namesList.size()>0 )
			{
				for(Object[] name:namesList)
				{
					Long cadreCount2014 = 0L;
					Long cadreCount2012 = 0L;
					
					try{
							if(yearWiseTotalCadreMap.get(2014L) != null)
							{
								cadreCount2014 = yearWiseTotalCadreMap.get(2014L);
							}
							if(yearWiseTotalCadreMap.get(2012L) != null)
							{
								cadreCount2012 = yearWiseTotalCadreMap.get(2012L);
							}	
							
						    yearMap = locationMap.get((Long)name[0]);
						    infoVo = new CadreRegisterInfo();
						    infoVo.setId((Long)name[0]);
						    infoVo.setLocation(name[1].toString());
						    if(yearMap != null)
						    {
						    	cadreCount2012 = cadreCount2012 + Long.valueOf(yearMap.get(2012L) != null ? yearMap.get(2012L).toString().trim():"0");
								cadreCount2014 = cadreCount2014 + Long.valueOf(yearMap.get(2014L) != null ? yearMap.get(2014L).toString().trim():"0");
									
								yearWiseTotalCadreMap.put(2014L,cadreCount2014);
								yearWiseTotalCadreMap.put(2012L,cadreCount2012);
									
								infoVo.setApCount(yearMap.get(2014l));
								infoVo.setTgCount(yearMap.get(2012l));
								if(type.equals("assembly")){
									String reqType = locationType.get((Long)name[0]);
									 if(reqType != null && !reqType.equalsIgnoreCase("URBAN") ){
										 infoVo.setName("True");
										 infoVo.setArea("True");
									 }
								}else if(type.equals("mandal")){
									String reqType = locationType.get((Long)name[0]);
									 if(reqType != null && reqType.equalsIgnoreCase("mandal") ){
										 infoVo.setArea("True");
									 }
								}
								if((type.equals("assembly") || type.equals("district")) && infoVo.getApCount() != null && infoVo.getApCount().longValue() > 0 && infoVo.getTgCount() != null && infoVo.getTgCount().longValue() > 0 )
								{
									infoVo.setName(new BigDecimal(infoVo.getApCount()*(100.0)/infoVo.getTgCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								}
								
						    }
						    returnList.add(infoVo);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			if(reqOthers && constituencyId != null && constituencyId != 0L && ids.size() > 1)
			{
				
				List<Object[]> enrollmentYearWiseCadreCount  = tdpCadreDAO.getEnrollmentYearWiseDetails(constituencyId,fromDate,toDate,2014L);
				enrollmentYearWiseCadreCount.addAll( tdpCadreDAO.getEnrollmentYearWiseDetails(constituencyId,null,null,2012L));
				
				if(enrollmentYearWiseCadreCount != null && enrollmentYearWiseCadreCount.size()>0 && yearWiseTotalCadreMap != null && yearWiseTotalCadreMap.size()>0)
				{
					Long totalCount2012 = 0L;
					Long actualCount2012 =  0L;
					
					Long totalCount2014 = 0L;
					Long actualCount2014 =  0L;
					
					for (Object[] cadre : enrollmentYearWiseCadreCount) 
					{
						if(cadre[0] != null) 
						{
							Long enrollmentYear =  Long.valueOf(cadre[0].toString());
							
							if(enrollmentYear.longValue() == 2014L)
							{
								totalCount2014 = cadre[1] != null ? Long.valueOf(cadre[1].toString()):0L;
								actualCount2014 = yearWiseTotalCadreMap.get(2014L) != null ? yearWiseTotalCadreMap.get(2014L):0L;
							}
							
							if(enrollmentYear.longValue() == 2012L)
							{
								totalCount2012 = cadre[1] != null ? Long.valueOf(cadre[1].toString()):0L;
								actualCount2012 = yearWiseTotalCadreMap.get(2012L) != null ? yearWiseTotalCadreMap.get(2012L):0L;
							}
							
						}
					}
					
					 infoVo = new CadreRegisterInfo();
					 infoVo.setLocation(" Others ");
					 Long count2014 = totalCount2014 - actualCount2014;
					 Long count2012 = totalCount2012 - actualCount2012;
					 
					 infoVo.setApCount( count2014 );
					 infoVo.setTgCount( count2012 );
					 infoVo.setArea(" - ");
					 if((count2014 != null && count2014 != 0L) || (count2012 != null && count2012 != 0L))
					 {
						// infoVo.setName(new BigDecimal((double)count2014*(100.0)/(double)count2012).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						 returnList.add(0,infoVo);
					 }
				}
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
	
	public List<CadreRegisterInfo> getCastGroupWiseCadreCount(Long constituencyId,String type){
		LinkedHashMap<Long,CadreRegisterInfo> casteMap = new LinkedHashMap<Long,CadreRegisterInfo>();
	 try{
		List<Object[]> countsList = tdpCadreDAO.getCasteGroupTotalCount(constituencyId, type);
		Long totalCount2014 = null;
		Long totalCount2012 = null;
		for(Object[] counts:countsList){
			if(counts[1] != null){
				if(((Long)counts[1]).longValue() == 2012l){
					totalCount2012 = (Long)counts[0];
				}else if(((Long)counts[1]).longValue() == 2014l){
					totalCount2014 = (Long)counts[0];
				}
			}
		}
		List<Object[]> casteInfoList = tdpCadreDAO.getCastGroupWiseCadreCount(constituencyId,type);
		//0 count,1 year,2 4casteGroupId ,3 casteGroup
		for(Object[] caste:casteInfoList){
			
			CadreRegisterInfo casteVo = casteMap.get((Long)caste[2]);
			if(casteVo == null){
				casteVo = new CadreRegisterInfo();
				casteVo.setName(caste[3].toString());
				casteVo.setApCount(0l);//2014 total count
				casteVo.setTgCount(0l);//2012 total count
				casteVo.setPercentStr("0");//2014 perc
				casteVo.setArea("0");//2012 perc
				//casteVo.setDate(caste[4].toString());//casteGroup
				casteMap.put((Long)caste[2],casteVo);
			}
			if(((Long)caste[1]).longValue() == 2014l){
				casteVo.setApCount((Long)caste[0]);
				if(totalCount2014 != null && totalCount2014.longValue() > 0){
					casteVo.setPercentStr(new BigDecimal((casteVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}else if(((Long)caste[1]).longValue() == 2012l){
				casteVo.setTgCount((Long)caste[0]);
				if(totalCount2012 != null && totalCount2012.longValue() > 0){
					casteVo.setArea(new BigDecimal((casteVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			
		}
	 }catch(Exception e){
		 LOG.error("Exception rised in getCastGroupWiseCadreCount",e);
	 }
		return new ArrayList<CadreRegisterInfo>(casteMap.values());
	}
	public CadreRegisterInfo getRegisteredDetailsByLocation(String locationType,List<Long> locationIds,int startIndex,int maxIndex,String orderBy,String orderType){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo returnVO = new CadreRegisterInfo();
		
	  try{
		//0 id,1 image,2name,3relative,4mobile,5partNo,6panchayat
		List<Object[]> cadres = null;
		CadreRegisterInfo cadreVo = null;
		Long totalCount = null;
		if(locationType != null && locationType.equalsIgnoreCase("booth")){
			cadres = tdpCadreDAO.getBoothWiseCadreInfo(locationIds,startIndex,maxIndex,orderBy,orderType);
			totalCount = tdpCadreDAO.getBoothWiseCadreInfoCount(locationIds);
		}else if(locationType != null && locationType.equalsIgnoreCase("panchayat")){
			List<Long> boothIds = new ArrayList<Long>();
			List<Long> panchayatIds = new ArrayList<Long>();
			List<Long> localBodyIds = new ArrayList<Long>();
			List<Long> constiIds = new ArrayList<Long>();
			for(Long id:locationIds){
				if(id != null && id.longValue() > 0){
					if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						panchayatIds.add(Long.valueOf(id.toString().substring(1)));
					}else if(id.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						localBodyIds.add(Long.valueOf(id.toString().substring(1)));
					}else if(id.toString().substring(0,1).trim().equalsIgnoreCase("3")){
						constiIds.add(Long.valueOf(id.toString().substring(1)));
					}
				}
			}
			List<Long> boothsList = new ArrayList<Long>();
			if(panchayatIds.size() > 0){
				boothIds.addAll(boothDAO.getAllBoothIdsInPanchayat(panchayatIds, IConstants.VOTER_DATA_PUBLICATION_ID));
			}
			if(localBodyIds.size() > 0){
				boothIds.addAll(boothDAO.getAllBoothIdsInLocalBodies(localBodyIds,IConstants.VOTER_DATA_PUBLICATION_ID));
			}
            if(constiIds.size() > 0){
            	boothIds.addAll(boothDAO.getAllBoothIdsByConsti(constiIds.get(0),IConstants.VOTER_DATA_PUBLICATION_ID));
			}
			/*cadres = tdpCadreDAO.getPanchayatWiseCadreInfo(locationIds,startIndex,maxIndex,orderBy,orderType);
			totalCount = tdpCadreDAO.getPanchayatWiseCadreInfoCount(locationIds);*/
            cadres = tdpCadreDAO.getBoothWiseCadreInfo(boothsList,startIndex,maxIndex,orderBy,orderType);
			totalCount = tdpCadreDAO.getBoothWiseCadreInfoCount(boothsList);
		}
		if(cadres != null && cadres.size() > 0){
			for(Object[] cadre:cadres){
				cadreVo = new CadreRegisterInfo();
				cadreVo.setId((Long)cadre[0]);//id
				if(cadre[1] != null){
				   cadreVo.setDate(cadre[1].toString());//image
				}
				if(cadre[2] != null){
				  cadreVo.setName(cadre[2].toString());//name
				}else{
					cadreVo.setName("");
				}
				if(cadre[3] != null){
					   cadreVo.setPercentStr(cadre[3].toString());//relative
				}else{
				   cadreVo.setPercentStr("");
				}
				if(cadre[4] != null){
				   cadreVo.setNumber(cadre[4].toString());//mobile
				}else{
				   cadreVo.setNumber("");
				}
				if(cadre[5] != null){
				   cadreVo.setLocation(cadre[5].toString());//booth
				}else{
				   cadreVo.setLocation("");
				}
				if(cadre[6] != null){
				   cadreVo.setArea(cadre[6].toString());//panchayat
				}else{
				   cadreVo.setArea("");
				}
				
				if(cadre[7] != null){
					cadreVo.setMemberShipNo(cadre[7].toString());//Membership No
				}else{
					cadreVo.setMemberShipNo("");
				}
				
				returnList.add(cadreVo);
			}
		}
		
		returnVO.setCadreRegisterInfoList(returnList);
		returnVO.setTotalCount(totalCount);
	  }catch(Exception e){
		  LOG.error("Exception rised in getRegisteredDetailsByLocation",e);
	  }
		return returnVO;
	}
	
	public List<CadreRegisterInfo> getDataForSubLocations(String fromLocation,Long fromLocationId,String toLocation,String fromDateStr, String toDateStr,Long constituencyId){
		List<Long> ids = new ArrayList<Long>();
		String type = "";
		try{
			if(fromLocation.equalsIgnoreCase("state")){
				if(toLocation.equalsIgnoreCase("district")){
					type ="district";
					ids = districtDAO.getDistrictsInAState(fromLocationId);
				}else if(toLocation.equalsIgnoreCase("constituency")){
					type ="assembly";
					ids = constituencyDAO.getConstituenciesInAState(fromLocationId);
				}
			}else if(fromLocation.equalsIgnoreCase("district")){
	            if(toLocation.equalsIgnoreCase("constituency")){
	            	type ="assembly";
	            	ids = constituencyDAO.getConstituenciesInADistrict(fromLocationId);
				}
			}else if(fromLocation.equalsIgnoreCase("constituency")){
	            if(toLocation.equalsIgnoreCase("mandal")){
	            	type = "mandal";
	            	List<Long> mandalIds = boothDAO.getAllTehsilsInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
	            	List<Long> lblIds = boothDAO.getLBSInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
	            	for(Long id:lblIds){
	            		ids.add(Long.valueOf("1"+id));
	            	}
	            	for(Long id:mandalIds){
	            		ids.add(Long.valueOf("2"+id));
	            	}
				}else if(toLocation.equalsIgnoreCase("panchayat")){
					type = "panchayat";
					ids = boothDAO.getAllPanchayatsInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
				}else if(toLocation.equalsIgnoreCase("booth")){
					type = "booth";
					ids = boothDAO.getAllBoothsInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
				}
			}else if(fromLocation.equalsIgnoreCase("mandal")){
				if(toLocation.equalsIgnoreCase("panchayat")){
					type = "panchayat";
					ids = boothDAO.getAllPanchayatsInAMandal(fromLocationId,IConstants.VOTER_DATA_PUBLICATION_ID,constituencyId);
				}else if(toLocation.equalsIgnoreCase("booth")){
					type = "booth";
					ids = boothDAO.getAllBoothsInAMandal(fromLocationId,IConstants.VOTER_DATA_PUBLICATION_ID,constituencyId);
				}
			}else if(fromLocation.equalsIgnoreCase("panchayat")){
				if(toLocation.equalsIgnoreCase("booth")){
					type = "booth";
					List<Long> panchayatIds = new ArrayList<Long>();
					panchayatIds.add(fromLocationId);
					ids = boothDAO.getAllBoothIdsInPanchayat(panchayatIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				}
			}
			if(type.trim().length() == 0 || ids.size() == 0){
				return new ArrayList<CadreRegisterInfo>();
			}
		}catch(Exception e){
			LOG.error("Exception rised in getDataForSubLocations",e);
		}
		return getLocationWiseRegistrationInfo(ids,type,fromDateStr,toDateStr,false);
	}
	
	public AppDbDataVO getAllVersionsOfAnApp(String appName,Double currentVerson,boolean includeTest){
		AppDbDataVO returnVo = new AppDbDataVO();
		try{
			List<Double> versions = appDbUpdateDAO.getAllVersionsOfAnApp(appName, currentVerson, includeTest);
			if(versions.size() > 0){
				returnVo.setStatus("Updates Available");
				returnVo.setVersions(versions);
			}else{
				returnVo.setStatus("No Updates Available");
				returnVo.setVersions(new ArrayList<Double>());
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAllVersionsOfAnApp",e);
			returnVo.setStatus("Try Again Later");
		}
		return returnVo;
	}
	
	public AppDbDataVO getAllUpdatesByVersion(String appName,Double version){
		AppDbDataVO returnVo = new AppDbDataVO();
		try{
			List<Object[]> scriptList = appDbUpdateDAO.getAllUpdatesByVersion(appName, version);
			if(scriptList.size() > 0){
				returnVo.setStatus("Data Available");
				List<AppDbDataVO> dataList = new ArrayList<AppDbDataVO>();
				for(Object[] script:scriptList){
					 AppDbDataVO data = new AppDbDataVO();
					 data.setId((Long)script[0]);
					 data.setOrder((Long)script[1]);
					 data.setScript(script[2].toString());
					 dataList.add(data);
				}
				returnVo.setDataList(dataList);
			}else{
				returnVo.setStatus("Data Not Available");
				returnVo.setDataList(new ArrayList<AppDbDataVO>());
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAllVersionsOfAnApp",e);
			returnVo.setStatus("Try Again Later");
		}
		return returnVo;
	}
	
	/**
	 * Service Method will call DAO to fetch Survey Working Member details
	 * @param hours
	 * @return
	 */
	public CadreRegisterInfo getWorkingMembersDetails(String hours)
	{
		CadreRegisterInfo info = new CadreRegisterInfo();
		List<CadreRegisterInfo> infoLst = new ArrayList<CadreRegisterInfo>();
		List<Object[]> resultsLst = null;
		try{
			Date date = dateService.getCurrentDateAndTime();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			Date hourBack = null;
			Long count  = 0L;
			
			if(hours != null && hours.trim().length()>0 && !hours.equalsIgnoreCase("0"))
			{
				int hourCount = Integer.valueOf(hours);						
				cal.add(Calendar.HOUR, -hourCount);
				hourBack = cal.getTime();
				
				//count = tdpCadreDAO.getLastHoursWorkingMemberCount(date,hourBack);
				resultsLst = tdpCadreDAO.getLastHoursWorkingMembersDetails(date, hourBack);
			}
			else
			{
				//count = tdpCadreDAO.getWorkingMembersCount(date);
				resultsLst = tdpCadreDAO.getWorkingMembersDetails(date);
			}
			
			if(resultsLst != null && resultsLst.size() > 0){
				count = new Long(resultsLst.size());
				
				for(Object[] res:resultsLst){
					
					CadreRegisterInfo sInfo = new CadreRegisterInfo();
					sInfo.setId((Long)res[0]);
					sInfo.setName(res[2].toString());
					//latitude
					sInfo.setArea(res[3].toString());
					//longitude
					sInfo.setLocation(res[4].toString());					
					infoLst.add(sInfo);
				}
			}
			
			info.setTotalCount(count);
			info.setAllDetailsList(infoLst);
			

		}catch(Exception e){
			LOG.error("Exception rised in getWorkingMembersInfo",e);
		}
		
		return info;
	}

		
	public CadreRegisterInfo getRegisteredInfo(Long locationId,String locationType,int startIndex,int maxIndex){
		CadreRegisterInfo returnVo = new CadreRegisterInfo();
		List<CadreRegisterInfo> resultList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo vo = null;
		try{
			//0image,1name,2relative,3constituency,4age,5dataSourceType
		    List<Object[]> cadreList = tdpCadreDAO.getCadreInfoDetails(locationId,locationType,startIndex,maxIndex);
		    Long count = tdpCadreDAO.getCadreInfoDetailsCount(locationId,locationType);
		    returnVo.setTotalCount(count);
		    for(Object[] cadre:cadreList){
		    	vo = new CadreRegisterInfo(); 
		    	if(cadre[1] != null){
		    	   vo.setName(cadre[1].toString());
		    	}else{
		    		 vo.setName("-");
		    	}
		    	if(cadre[4] != null){
		    	  vo.setNumber(cadre[4].toString());//age
		    	}else{
		    		 vo.setNumber("-");//age
		    	}
		    	if(cadre[2] != null){
		    	  vo.setArea(cadre[2].toString());//2relative
		    	}else{
	    		  vo.setArea("-");//2relative
		    	}
		    	if(cadre[3] != null){
			      vo.setLocation(cadre[3].toString());//3constituency
			    }else{
	    		  vo.setLocation("-");//3constituency
		    	}
		    	if(cadre[0] != null){
		    	 vo.setMemberShipNo("images/cadre_images/"+cadre[0].toString());//image
		    	}
		    	if(cadre[5] != null){
			    	 vo.setDate(cadre[5].toString());//5datasource
			    }else{
			    	 vo.setDate("-");//5datasource
			    }
		    	resultList.add(vo);
		    }
		}catch(Exception e){
			LOG.error("Exception rised in getRegisteredInfo",e);
		}
		returnVo.setInfoList(resultList);
		return returnVo;
	}
}
