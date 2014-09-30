package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	     
	     CadreRegisterInfo allCadreInfo = getRegisterCount(null,null);
	     
	     returnResult.add(todayInfo);
	     returnResult.add(thisWeekInfo);
	     returnResult.add(thisMonthInfo);
	     returnResult.add(allCadreInfo);
		
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
	 
	public void getRecentlyRegisteredCadres(){
		
	}
	
	public List<CadreRegisterInfo> getConstituencyWiseRegistrationInfo(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo info = null;
		List<Object[]> constituencyWiseCount = tdpCadreDAO.getRegisterCadreInfoConstituencyWise();
		try{
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
	
}
