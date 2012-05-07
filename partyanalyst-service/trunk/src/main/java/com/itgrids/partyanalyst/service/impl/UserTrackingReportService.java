package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.AccessedPageLoginTimeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
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

	public String saveUserLogOutDetails(Date fromDate, Date toDate, Boolean isToday)
	{
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
	
	public List<UserTrackingReportVO> getHostNameAndNoOfPagesForAVisitor(Date fromDate , Date toDate)
	{
		List<UserTrackingReportVO> resultList = null;
		Date maxTime;
		Date minTime;
		Long spentTimeOnPage;
		
		try
		{
			List<Object[]> list = userTrackingDAO.getHostNameAndNoOfPagesForAVisitor(fromDate,toDate);
			if(list != null && list.size() > 0)
			{
				resultList = new ArrayList<UserTrackingReportVO>(0);
				int count=0;
				for(Object[] params : list)
				{	
					if(params[0]!=null){
					UserTrackingReportVO userTrackingReportVO = new UserTrackingReportVO();
					maxTime = (Date)params[2];
					minTime = (Date)params[3];
					spentTimeOnPage = maxTime.getTime()- minTime.getTime();
					userTrackingReportVO.setSessionId(params[0].toString());
					userTrackingReportVO.setId(++count);
					userTrackingReportVO.setRemoteAddress(params[1].toString());
					userTrackingReportVO.setNoOfPages(((Long)params[4]).intValue());
					userTrackingReportVO.setSpentTime(getTimeSpentBetweenDatesInString(spentTimeOnPage));
					userTrackingReportVO.setSpentTimeMS(spentTimeOnPage.toString());
					
					List<Object> pages = userLoginDetailsDAO.getLandingPageAndExitPageForAUser(params[0].toString());
					if(pages != null && pages.size() >= 2)
					{
						userTrackingReportVO.setExitPage(pages.get(0).toString());
						userTrackingReportVO.setLandingPage(pages.get(1).toString());
					}
					else if(pages !=null && pages.size() <= 1){
						userTrackingReportVO.setLandingPage(pages.get(0).toString());
						userTrackingReportVO.setExitPage(pages.get(0).toString());
					}
					resultList.add(userTrackingReportVO);
					}
				}
			}
			return resultList;
		
		}catch (Exception e) {
			log.error("Exception occured in getHostNameAndNoOfPagesForAVisitor() Method, Exception is - "+e);
			return resultList;
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
		Float allUsersAvgPagesAccessed=0f;
		
		for(int i=0;i<3;i++){
			try{
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
				Float avgPagesAccessed=0f;
				String timeSpent="";
				String avgTimeSpent="";
				
				if(sessionCount==0){
					pagesAccessed=0l;
					avgPagesAccessed=0f;
					timeSpent="0";
					avgTimeSpent="0";				
				}
				else{			
					List<Object[]> initialLogInOutTime = userTrackingDAO.getLoginLogoutTimeBetweenDates(fromDate, toDate, usrTyp);
				
					Long timeDiff=0l;
					int nullCounter=0;

					if(initialLogInOutTime != null && initialLogInOutTime.size() > 0){
						for(Object[] params : initialLogInOutTime){	
							if(params[1]==null){
								nullCounter++;
								break;
							}							
						}
					}
					
					if(nullCounter!=0){
						if(fromDate==toDate)
							saveUserLogOutDetails(fromDate, toDate, true);
						else
							saveUserLogOutDetails(fromDate, toDate, false);	
					}
				
					List<Object[]> logInOutTime = userTrackingDAO.getLoginLogoutTimeBetweenDates(fromDate, toDate, usrTyp);
				
					if(logInOutTime != null && logInOutTime.size() > 0){
						for(Object[] params : logInOutTime){	
							if(params[0]!=null){
								Long userTimeDiff=((Date)params[1]).getTime()-((Date)params[2]).getTime();
								timeDiff=timeDiff+userTimeDiff;
							}
						}
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
			catch(Exception e){
				log.error("Exception raised in getTotalUniqueVisitorDetails method of UserTrackingReportService",e);
			}		
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
		
		//userTrackingReportVOList=getTotalVisitorLandingExitBounceRates(fromDate, toDate, userTrackingReportVOList);
		
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
			
			days=timeDiff/(1000*60*60*24);
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
public List<UserTrackingReportVO> getHostNameAndNoOfPagesForAUser(Date fromDate , Date toDate , String userType){
	List<UserTrackingReportVO> resultList = new ArrayList<UserTrackingReportVO>(0);
	Date maxTime;
	Date minTime;
	Long spentTimeOnPage;
	UserTrackingReportVO userTrackingReportVO = null;
	try{
		List<Object[]> list = userTrackingDAO.getHostNameAndNoOfPagesForAUser(fromDate, toDate, userType);
			
		if(list != null && list.size() > 0)
		{
			int guestCount=0;
			int customerCount=0;
			int userCount=0;
			for(Object[] params : list)
			{
					
				userTrackingReportVO = new UserTrackingReportVO();
				
				if(userType == null)
				{
					maxTime = (Date)params[2];
					minTime = (Date)params[3];
					
					String loginTime=DateFormat.getTimeInstance().format(minTime);
					userTrackingReportVO.setLoginTime(loginTime);
					spentTimeOnPage = maxTime.getTime()-minTime.getTime();
					userTrackingReportVO.setSessionId(params[0].toString());
					userTrackingReportVO.setUserType(userType);
					userTrackingReportVO.setId(++guestCount);
					userTrackingReportVO.setRemoteAddress(params[1].toString());
					userTrackingReportVO.setNoOfPages(((Long)params[4]).intValue());
					userTrackingReportVO.setSpentTime(getTimeSpentBetweenDatesInString(spentTimeOnPage));
					userTrackingReportVO.setSpentTimeMS(spentTimeOnPage.toString());
					List<Object> pages = userLoginDetailsDAO.getLandingPageAndExitPageForAUser(params[0].toString());
					
					 if(pages != null && pages.size() >= 2)
					 {
						 userTrackingReportVO.setExitPage(pages.get(0).toString());
						 userTrackingReportVO.setLandingPage(pages.get(1).toString());
					 }
					 else if(pages != null && pages.size() <= 1){
						 userTrackingReportVO.setLandingPage(pages.get(0).toString());
						 userTrackingReportVO.setExitPage(pages.get(0).toString());
					 }
				}
				else if(userType != null && userType.equalsIgnoreCase(IConstants.FREE_USER))
				{
					maxTime = (Date)params[3];
					minTime = (Date)params[4];
					String loginTime=DateFormat.getTimeInstance().format(minTime);
					userTrackingReportVO.setLoginTime(loginTime);
					spentTimeOnPage = maxTime.getTime()-minTime.getTime();
					userTrackingReportVO.setSessionId(params[0].toString());
					userTrackingReportVO.setUserType(userType);
					userTrackingReportVO.setId(++userCount);
					userTrackingReportVO.setNoOfPages(((Long)params[6]).intValue());
					userTrackingReportVO.setUserName(params[1].toString()+" "+params[2].toString());
					userTrackingReportVO.setSpentTime(getTimeSpentBetweenDatesInString(spentTimeOnPage));
					userTrackingReportVO.setSpentTimeMS(spentTimeOnPage.toString());
					List<Object> FUPages = userLoginDetailsDAO.getLandingPageAndExitPageForAUser(params[0].toString());
					if(FUPages != null && FUPages.size() >= 2){
						userTrackingReportVO.setExitPage(FUPages.get(0).toString());
						userTrackingReportVO.setLandingPage(FUPages.get(1).toString());
					}
					else if(FUPages !=null && FUPages.size() <= 1)
					{
						userTrackingReportVO.setExitPage(FUPages.get(0).toString());
						userTrackingReportVO.setExitPage(FUPages.get(0).toString());
					}					
					//userTrackingReportVO.setUrlTimeVOList(getUrlTimeVOList(fromDate, toDate, userType, params[0].toString()));
				}
				else if(userType != null && userType.equalsIgnoreCase(IConstants.PARTY_ANALYST_USER))
				{
					maxTime = (Date)params[3];
					minTime = (Date)params[4];
					String loginTime=DateFormat.getTimeInstance().format(minTime);
					userTrackingReportVO.setLoginTime(loginTime);
					spentTimeOnPage = maxTime.getTime()-minTime.getTime();
					userTrackingReportVO.setSessionId(params[0].toString());
					userTrackingReportVO.setUserType(userType);
					userTrackingReportVO.setId(++customerCount);
					userTrackingReportVO.setNoOfPages(((Long)params[6]).intValue());
					userTrackingReportVO.setSpentTime(getTimeSpentBetweenDatesInString(spentTimeOnPage));
					userTrackingReportVO.setSpentTimeMS(spentTimeOnPage.toString());
					userTrackingReportVO.setUserName(params[1].toString() +" "+params[2].toString());
					List<Object> PAPages = userLoginDetailsDAO.getLandingPageAndExitPageForAUser(params[0].toString());
					if(PAPages != null && PAPages.size() >= 2){
						userTrackingReportVO.setExitPage(PAPages.get(0).toString());
						userTrackingReportVO.setLandingPage(PAPages.get(1).toString());
					}
					else if(PAPages != null && PAPages.size() <=1){
						userTrackingReportVO.setExitPage(PAPages.get(0).toString());
						userTrackingReportVO.setLandingPage(PAPages.get(0).toString());
					}
		
					//userTrackingReportVO.setUrlTimeVOList((getUrlTimeVOList(fromDate, toDate, userType, params[0].toString())));					
				}
				resultList.add(userTrackingReportVO);
				}
			}
		return resultList;
	}catch (Exception e) {
		log.debug("Exception Occured in getHostNameAndNoOfPagesForAUser() Method ,Exception is - "+e);
		return null;
		}
	}

	public List<AccessedPageLoginTimeVO> getUrlTimeVOList(Date fromDate, Date toDate, String userType, String sessionId){
		List<Object> pageFlow=userTrackingDAO.getPageFlowOfUserBetweenDates(fromDate, toDate, userType, sessionId);
		Iterator<Object> pageFlowItr=pageFlow.iterator();
		List<AccessedPageLoginTimeVO> urlTimeVOList=new ArrayList<AccessedPageLoginTimeVO>();
		AccessedPageLoginTimeVO urlTimeVO=new AccessedPageLoginTimeVO();
		
		if(pageFlow!=null && pageFlow.size()>0){
			while(pageFlowItr.hasNext()){							
				Object[] obj=(Object[])pageFlowItr.next();
				String page=(String)obj[0];
				Long time=((Date)obj[1]).getTime();
				urlTimeVO=new AccessedPageLoginTimeVO();
				urlTimeVO.setPageUrl(page);
				urlTimeVO.setAccessTime(time);
				urlTimeVOList.add(urlTimeVO);
			}						
		}

		AccessedPageLoginTimeVO updatedUrlTimeVO=new AccessedPageLoginTimeVO();
		List<AccessedPageLoginTimeVO> updateUrlTimeVOList=new ArrayList<AccessedPageLoginTimeVO>();
				
		Iterator<AccessedPageLoginTimeVO> urlTimeItr=urlTimeVOList.iterator();
		if(urlTimeVOList!=null && urlTimeVOList.size()>0){
			String previousUrl="";
			Long previousTime=0l;
			int sameUrlCount=1;
			String fromTime="";
			Long lastAccessTime=0l;
			Long fromTimeLong=0l;
			int firstObjCount=1;
			String loginTime="";
			while(urlTimeItr.hasNext()){
				try{
				AccessedPageLoginTimeVO pageTimeVO=(AccessedPageLoginTimeVO)urlTimeItr.next();
				String presentUrl=pageTimeVO.getPageUrl();
				Long presentTime=pageTimeVO.getAccessTime();
				if(firstObjCount==1){
					loginTime=DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date(presentTime));
					
				}					
				if(!(presentUrl.equalsIgnoreCase(previousUrl))){
					if(previousUrl!=""){
						if(sameUrlCount>1){
							updatedUrlTimeVO=new AccessedPageLoginTimeVO();
							updatedUrlTimeVO.setPageUrl(previousUrl);
							updatedUrlTimeVO.setAccessTime(previousTime);
							updatedUrlTimeVO.setAccessCount(sameUrlCount);
							updatedUrlTimeVO.setFromTime(fromTime);
							updatedUrlTimeVO.setToTime(DateFormat.getInstance().format(new Date(lastAccessTime)));
							updatedUrlTimeVO.setTimeSpent(getTimeSpentBetweenDatesInString(lastAccessTime-fromTimeLong));							
							updatedUrlTimeVO.setLoginDate(loginTime);
							updateUrlTimeVOList.add(updatedUrlTimeVO);
							fromTime=null;
							sameUrlCount=0;
						}
						else{
							updatedUrlTimeVO=new AccessedPageLoginTimeVO();
							updatedUrlTimeVO.setPageUrl(previousUrl);
							updatedUrlTimeVO.setAccessTime(previousTime);
							updatedUrlTimeVO.setLoginDate(loginTime);
							updateUrlTimeVOList.add(updatedUrlTimeVO);
							if(!(urlTimeItr.hasNext())){
								updatedUrlTimeVO=new AccessedPageLoginTimeVO();
								updatedUrlTimeVO.setPageUrl(presentUrl);
								updatedUrlTimeVO.setAccessTime(presentTime);
								updatedUrlTimeVO.setLoginDate(loginTime);
								updateUrlTimeVOList.add(updatedUrlTimeVO);
							}
						}
					}
					previousUrl=presentUrl;
					previousTime=presentTime;											
				}				
				else{
					if(sameUrlCount==1){
						fromTime=DateFormat.getInstance().format(new Date(previousTime));
						fromTimeLong=previousTime;
					}
					sameUrlCount++;
					lastAccessTime=presentTime;
					presentTime=previousTime;
					
					if(!(urlTimeItr.hasNext())){
						updatedUrlTimeVO=new AccessedPageLoginTimeVO();
						updatedUrlTimeVO.setPageUrl(previousUrl);
						updatedUrlTimeVO.setAccessTime(previousTime);
						updatedUrlTimeVO.setAccessCount(sameUrlCount);
						updatedUrlTimeVO.setFromTime(fromTime);
						updatedUrlTimeVO.setToTime(DateFormat.getInstance().format(new Date(lastAccessTime)));
						updatedUrlTimeVO.setTimeSpent(getTimeSpentBetweenDatesInString(lastAccessTime-fromTimeLong));
						updatedUrlTimeVO.setLoginDate(loginTime);
						updateUrlTimeVOList.add(updatedUrlTimeVO);
					}
				}			
			}			
			catch(Exception e){
				log.error("Exception raised in getUrlTimeVOList method of UserTrackingReportService",e);
			}
			}
		}		
		
		if(updateUrlTimeVOList!=null && updateUrlTimeVOList.size()>0){
			if(updateUrlTimeVOList.size()>1){
				for(int i=0;i<updateUrlTimeVOList.size();i++){
					if(i==updateUrlTimeVOList.size()-1){
						if(updateUrlTimeVOList.get(i).getTimeSpent()=="" || updateUrlTimeVOList.get(i).getTimeSpent()==null)
							updateUrlTimeVOList.get(i).setTimeSpent("-");
					}
					else{
						Long timeDiff=updateUrlTimeVOList.get(i+1).getAccessTime()-updateUrlTimeVOList.get(i).getAccessTime();
						updateUrlTimeVOList.get(i).setTimeSpent(getTimeSpentBetweenDatesInString(timeDiff));				
					}
				}
			}
			else{				
				updateUrlTimeVOList.get(0).setTimeSpent("-");
			}			
		}	
		
		return updateUrlTimeVOList;
	}
	
	private List<UserTrackingReportVO> getTotalVisitorLandingExitBounceRates(Date fromDate, Date toDate, List<UserTrackingReportVO> userTrackingReportVOList){
		UserTrackingReportVO userTrackingReportVO=new UserTrackingReportVO();
		HashMap<String, Integer> leadRateMap=new HashMap<String, Integer>();
		HashMap<String, Integer> exitRateMap=new HashMap<String, Integer>();
		HashMap<String, Integer> bounceRateMap=new HashMap<String, Integer>();
		Integer bounceCtr=0;
		Integer leadCtr=0;
		Integer exitCtr=0;
		
		try{
		List<Object> landingObj=userTrackingDAO.getLandingPageBetweenDatesBySessionId(fromDate, toDate);
		List<Object> exitObj=userTrackingDAO.getExitPageBetweenDatesBySessionId(fromDate, toDate);
		
		Iterator<Object> landingItr=landingObj.iterator();
		Iterator<Object> exitItr=exitObj.iterator();		
		
		HashSet<String> sessionSet=new HashSet<String>();
		
		HashMap<String, String> landingPageMap=new HashMap<String, String>();
		HashMap<String, String> exitPageMap=new HashMap<String, String>();
		HashMap<String, Long> landingTimeMap=new HashMap<String, Long>();
		HashMap<String, Long> exitTimeMap=new HashMap<String, Long>();
				
		if(landingObj!=null && landingObj.size()>0){
			while(landingItr.hasNext()){
				Object[] objLanding=(Object[])landingItr.next();
				sessionSet.add((String)objLanding[0]);
				landingPageMap.put((String)objLanding[0], (String)objLanding[2]);
				landingTimeMap.put((String)objLanding[0], ((Date)objLanding[1]).getTime());
			}
		}
		
		if(exitObj!=null && exitObj.size()>0){
			while(exitItr.hasNext()){
				Object[] objExit=(Object[])exitItr.next();
				exitPageMap.put((String)objExit[0], (String)objExit[2]);
				landingTimeMap.put((String)objExit[0], ((Date)objExit[1]).getTime());
			}	
		}
		
		Iterator<String> sessionItr=sessionSet.iterator();
		while(sessionItr.hasNext()){
			String sessionId=sessionItr.next().toString();
			Long minTime=landingTimeMap.get(sessionId);
			Long maxTime=exitTimeMap.get(sessionId);
			if(maxTime==minTime){
				bounceRateMap.put(landingPageMap.get(sessionId), bounceCtr++);
			}
			else{
				leadRateMap.put(landingPageMap.get(sessionId), leadCtr++);
				exitRateMap.put(exitPageMap.get(sessionId), exitCtr++);
			}
		}
		
		Long sessionCount=(Long)userTrackingDAO.getTotalSessionCountBetweenDates(fromDate, toDate);
		
		Collection<String> landingCollection=leadRateMap.keySet();
		Collection<String> exitCollection=exitRateMap.keySet();
		Collection<String> bounceCollection=bounceRateMap.keySet();		
		Map<String, String> landingMap=new HashMap<String, String>();
		Map<String, String> exitMap=new HashMap<String, String>();
		Map<String, String> bounceMap=new HashMap<String, String>();

		userTrackingReportVO= new UserTrackingReportVO();
		if(landingCollection!=null && landingCollection.size()>0){
			Iterator<String> landColItr=landingCollection.iterator();
			while(landColItr.hasNext()){
				String landingUrl=landColItr.next();
				Integer leadHit=leadRateMap.get(landingUrl);
				Float leadRate=leadHit/sessionCount.floatValue();
				leadRate=Math.round(leadRate*100.0f)/100.0f;
				String leadRateStr=(new Integer((int)(leadRate*100))).toString()+"%";
				landingMap.put(landingUrl, leadRateStr);
			}
		}
		if(exitCollection!=null && exitCollection.size()>0){
			Iterator<String> exitColItr=exitCollection.iterator();
			while(exitColItr.hasNext()){
				String exitUrl=exitColItr.next();
				Integer exitHit=exitRateMap.get(exitUrl);
				Float exitRate=exitHit/sessionCount.floatValue();
				exitRate=Math.round(exitRate*100.0f)/100.0f;
				String exitRateStr=(new Integer((int)(exitRate*100))).toString()+"%";
				exitMap.put(exitUrl, exitRateStr);
			}
		}
		if(bounceCollection!=null && bounceCollection.size()>0){
			Iterator<String> bounceColItr=bounceCollection.iterator();
			while(bounceColItr.hasNext()){
				String bounceUrl=bounceColItr.next();
				Integer bounceHit=bounceRateMap.get(bounceUrl);
				Float bounceRate=bounceHit/sessionCount.floatValue();
				bounceRate=Math.round(bounceRate*100.0f)/100.0f;
				String bounceRateStr=(new Integer((int)(bounceRate*100))).toString()+"%";
				bounceMap.put(bounceUrl, bounceRateStr);
			}
		}
		
		userTrackingReportVO.setLandingPageMap(landingMap);
		userTrackingReportVO.setExitPageMap(exitMap);
		userTrackingReportVO.setBouncePageMap(bounceMap);
		userTrackingReportVOList.add(userTrackingReportVO);
		
		}
		catch(Exception e){
			log.error("Exception raised in getTotalVisitorLandingExitBounceRates method of UserTrackingReportService",e);
		}
		return userTrackingReportVOList;
	}
	 public Date getDate(String dateStr){
		  String[] dateArray =  dateStr.split("-");
		  Calendar cal = Calendar.getInstance(); 
		  cal.set(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
		  return cal.getTime();
	  }	
}
