package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.UserTrackingReportVO;
import com.itgrids.partyanalyst.model.UserLoginDetails;
import com.itgrids.partyanalyst.service.IUserTrackingReportService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserTrackingReportService implements IUserTrackingReportService{
	private static final Logger log = Logger.getLogger(UserTrackingReportService.class);
	
	private IUserLoginDetailsDAO userLoginDetailsDAO;
	private IUserTrackingDAO userTrackingDAO;
	private TransactionTemplate transactionTemplate;

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IUserLoginDetailsDAO getUserLoginDetailsDAO() {
		return userLoginDetailsDAO;
	}

	public void setUserLoginDetailsDAO(IUserLoginDetailsDAO userLoginDetailsDAO) {
		this.userLoginDetailsDAO = userLoginDetailsDAO;
	}

	public IUserTrackingDAO getUserTrackingDAO() {
		return userTrackingDAO;
	}

	public void setUserTrackingDAO(IUserTrackingDAO userTrackingDAO) {
		this.userTrackingDAO = userTrackingDAO;
	}

	public String saveUserLogOutDetails(Date fromDate, Date toDate, Boolean isToday){
		try{
			List<Object[]> list = null;
			if(isToday)
				list = userLoginDetailsDAO.getSessionIdsAndLogoutTimeOfTodaysUsers(fromDate);
			else
				list = userLoginDetailsDAO.getSessionIdsAndLogoutTimeInWithinDates(fromDate,toDate);
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					final UserLoginDetails userLoginDetails = userLoginDetailsDAO.getBySessionId(params[0].toString());
					final Date logoutTime = (Date)params[1];
					if(userLoginDetails != null)
					{
						transactionTemplate.execute(new TransactionCallbackWithoutResult() {
							public void doInTransactionWithoutResult(TransactionStatus status) {
								userLoginDetails.setLogoutTime(logoutTime);
								userLoginDetailsDAO.save(userLoginDetails);
							}});
					}
				}
			}
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.debug("Entered into saveUserLogOutDetails() exception is"+e);
			return null;
		}
	}
	public List<UserTrackingReportVO> getTotalUniqueVisitorDetails(Date fromDate, Date toDate){
		List<UserTrackingReportVO> userTrackingReportVOList = new ArrayList<UserTrackingReportVO>(0);
		UserTrackingReportVO userTrackingReportVO=new UserTrackingReportVO();
		String usrTyp=null;
		
		Long allUsersSessionCount=0l;
		Long allUsersTimeDiff=0l;
		Long allUsersAvgTimeDiff=0l;
		Long allUsersPagesAccessed=0l;
		float allUsersAvgPagesAccessed=0;
		try{
			for(int i=0;i<3;i++){			
				userTrackingReportVO=new UserTrackingReportVO();			
				if(i==0)
					usrTyp=IConstants.FREE_USER;
				if(i==1)
					usrTyp=IConstants.PARTY_ANALYST_USER;
				if(i==2)
					usrTyp=null;		
				
				Long sessionCount = (Long)userTrackingDAO.getUniqueVisitorsBetweenDates(fromDate, toDate, usrTyp);
				userTrackingReportVO.setUniqueVisitors(sessionCount);			
				allUsersSessionCount+=sessionCount;
				Long pagesAccessed=0l;
				float avgPagesAccessed=0;
				String timeSpent="";
				String avgTimeSpent="";
				
				if(sessionCount==0){
					pagesAccessed=0l;
					avgPagesAccessed=0;
					timeSpent="0";
					avgTimeSpent="0";				
				}
				else{			
					List loginTime = userTrackingDAO.getLoginTimeBetweenDates(fromDate, toDate, usrTyp);
					List initialLogoutTime = userTrackingDAO.getLogoutTimeBetweenDates(fromDate, toDate, usrTyp);
					
					Iterator inItr= loginTime.iterator();			
					Long timeDiff=0l;
				
					Iterator logoutItr=initialLogoutTime.iterator();
					int nullCounter=0;
					while(logoutItr.hasNext()){
						if(logoutItr.next()==null){
							nullCounter++;
							break;
						}
					}
					if(nullCounter!=0){
						if(fromDate==toDate)
							saveUserLogOutDetails(fromDate, toDate, true);
						else
							saveUserLogOutDetails(fromDate, toDate, false);	
					}
				
					List logoutTime = userTrackingDAO.getLogoutTimeBetweenDates(fromDate, toDate, usrTyp);
					Iterator outItr=logoutTime.iterator();
					
					Map logoutMap=new HashMap();						
					Map loginMap=new HashMap();				
					Set sessionIdSet=new HashSet();

					if(logoutTime != null && logoutTime.size()>0){					
						Iterator mapOutItr=logoutTime.iterator();					
						while(mapOutItr.hasNext()){						
							Object objOut[]=(Object[])mapOutItr.next();						
							
							if(objOut[1]!=null){
								sessionIdSet.add(objOut[0].toString());
								logoutMap.put(objOut[0].toString(), ((Date)objOut[1]).getTime());
							}
						}					
					}	
					
					if(loginTime != null && loginTime.size()>0){
						Iterator mapInItr=loginTime.iterator();
						while(mapInItr.hasNext()){
							Object objIn[]=(Object[])mapInItr.next();
							loginMap.put(objIn[0].toString(), ((Date)objIn[1]).getTime());						
						}
					}
					
					Iterator sessionSetItr=sessionIdSet.iterator();				
					while(sessionSetItr.hasNext()){
						String sessionId=sessionSetItr.next().toString();
						Long userTimeDiff=(Long)logoutMap.get(sessionId)-(Long)loginMap.get(sessionId);
						timeDiff=timeDiff+userTimeDiff;
					}				
								
					allUsersTimeDiff+=timeDiff;
				
					timeSpent=getTimeSpentBetweenDatesInString(timeDiff);
				
					Long avgTimeDiff=(timeDiff/sessionCount);
					allUsersAvgTimeDiff+=avgTimeDiff;
				
					avgTimeSpent=getTimeSpentBetweenDatesInString(avgTimeDiff);
				
					pagesAccessed = (Long)userTrackingDAO.getNoOfPagesAccessedBetweenDates(fromDate, toDate, usrTyp);
					allUsersPagesAccessed+=pagesAccessed;
						
					avgPagesAccessed=pagesAccessed/sessionCount.floatValue();
					avgPagesAccessed=Math.round(avgPagesAccessed*100.0f)/100.0f;
					
					allUsersAvgPagesAccessed=allUsersPagesAccessed/allUsersSessionCount.floatValue();
					allUsersAvgPagesAccessed=Math.round(allUsersAvgPagesAccessed*100.0f)/100.0f;
				}
				
				userTrackingReportVO.setTotalTimeSpent(timeSpent);

				userTrackingReportVO.setAvgTimeSpent(avgTimeSpent);

				userTrackingReportVO.setTotalNoOfPagesAccessed(pagesAccessed.longValue());

				userTrackingReportVO.setAvgNoOfPagesAccessed(avgPagesAccessed);
				
				userTrackingReportVOList.add(userTrackingReportVO);				
			}
			userTrackingReportVO=new UserTrackingReportVO();
			userTrackingReportVO.setUniqueVisitors(allUsersSessionCount);
			String allUsersTimeSpent=getTimeSpentBetweenDatesInString(allUsersTimeDiff);
			userTrackingReportVO.setTotalTimeSpent(allUsersTimeSpent);
			String allUsersAvgTimeSpent=getTimeSpentBetweenDatesInString(allUsersAvgTimeDiff);
			userTrackingReportVO.setAvgTimeSpent(allUsersAvgTimeSpent);
			userTrackingReportVO.setTotalNoOfPagesAccessed(allUsersPagesAccessed);
			userTrackingReportVO.setAvgNoOfPagesAccessed(allUsersAvgPagesAccessed);
			userTrackingReportVOList.add(userTrackingReportVO);
		}
		catch(Exception e){
			log.error("Exception raised in getTotalUniqueVisitorDetails method of UserTrackingReportService",e);
		}		
		return userTrackingReportVOList;
	}
	private String getTimeSpentBetweenDatesInString(Long timeDiff){
		String duration;
		Long seconds;
		Long minutes;
		Long hours;
		Long days;		
		
		if(timeDiff>=86400000){
			seconds=(timeDiff/1000)%60;
			String secs=seconds.toString();
			if(seconds<10)
				secs="0"+secs;
			
			minutes=(timeDiff/(1000*60))%60;
			String mins=minutes.toString();
			if(minutes<10)
				mins="0"+mins;
			
			hours=(timeDiff/(1000*60*60))%24;
			String hrs=hours.toString();
			if(hours<10)
				hrs="0"+hrs;
			
			days=timeDiff/1000*60*60*24;
			String dys=days.toString();
			duration=dys+" Days "+hrs+":"+mins+":"+secs+" hours";
		}
		else if(timeDiff>=3600000){
			seconds=(timeDiff/1000)%60;
			String secs=seconds.toString();
			if(seconds<10)
				secs="0"+secs;
			
			minutes=(timeDiff/(1000*60))%60;
			String mins=minutes.toString();
			if(minutes<10)
				mins="0"+mins;
			
			hours=(timeDiff/(1000*60*60))%24;
			String hrs=hours.toString();
			if(hours<10)
				hrs="0"+hrs;
			
			duration=hrs+":"+mins+":"+secs+" hours";
		}
		else if(timeDiff>=60000){
			seconds=(timeDiff/1000)%60;
			String secs=seconds.toString();
			if(seconds<10)
				secs="0"+secs;
			
			minutes=(timeDiff/(1000*60))%60;
			String mins=minutes.toString();
			if(minutes<10)
				mins="0"+mins;
			
			duration=mins+":"+secs+" minutes";
		}
		else{
			seconds=(timeDiff/1000)%60;
			String secs=seconds.toString();
			if(seconds<10)
				secs="0"+secs;
			
			duration=secs+" seconds";
		}
		return duration;
	}
}
