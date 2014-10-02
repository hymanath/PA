package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class CadreDashBoardService implements ICadreDashBoardService {

	private static Logger LOG = Logger.getLogger(CadreDashBoardService.class);
	
	private ITdpCadreDAO tdpCadreDAO;
	private DateUtilService dateService = new DateUtilService();

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
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
		 //0 first name ,1 lastname,2 constituency ,3 localArea
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
				if(yearMap.size() == 2){
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
				if(yearMap.size() == 2){
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
}
