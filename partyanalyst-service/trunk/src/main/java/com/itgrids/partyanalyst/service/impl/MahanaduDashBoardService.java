package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitInfoDAO;
import com.itgrids.partyanalyst.dao.IEntryExitInfoDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitDetails;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitInfo;
import com.itgrids.partyanalyst.model.EntryExitInfo;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class MahanaduDashBoardService implements IMahanaduDashBoardService {

	private IEventAttendeeDAO eventAttendeeDAO;
	private ICadreMahanaduVisitDetailsDAO cadreMahanaduVisitDetailsDAO;
	private ICadreMahanaduVisitInfoDAO cadreMahanaduVisitInfoDAO;
	private TransactionTemplate transactionTemplate;
	private IEntryExitInfoDAO entryExitInfoDAO;
	
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardService.class);
	private DateUtilService dateUtilService = new DateUtilService();
	
	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}

	public void setCadreMahanaduVisitDetailsDAO(
			ICadreMahanaduVisitDetailsDAO cadreMahanaduVisitDetailsDAO) {
		this.cadreMahanaduVisitDetailsDAO = cadreMahanaduVisitDetailsDAO;
	}

	public void setCadreMahanaduVisitInfoDAO(
			ICadreMahanaduVisitInfoDAO cadreMahanaduVisitInfoDAO) {
		this.cadreMahanaduVisitInfoDAO = cadreMahanaduVisitInfoDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setEntryExitInfoDAO(IEntryExitInfoDAO entryExitInfoDAO) {
		this.entryExitInfoDAO = entryExitInfoDAO;
	}

	public void getTodayTotalVisitorsForWeb(){
		 try{
		  EntryExitInfo entryExitInfo = entryExitInfoDAO.getAll().get(0);
    	  getTodayTotalVisitors(entryExitInfo.getEntryId(),entryExitInfo.getExitId(),entryExitInfo.getParentEventId());
		 }catch(Exception e){
			 LOG.error("Exception rised in getTodayTotalVisitorsForWeb()",e);
		 }
	}
	
    public void getTodayTotalVisitorsForSchedular(){
    	try{
    	EntryExitInfo entryExitInfo = entryExitInfoDAO.getAll().get(0);
    	if(entryExitInfo.getServiceRunReq().equalsIgnoreCase("true")){
    	  getTodayTotalVisitors(entryExitInfo.getEntryId(),entryExitInfo.getExitId(),entryExitInfo.getParentEventId());
    	}
      }catch(Exception e){
		 LOG.error("Exception rised in getTodayTotalVisitorsForSchedular()",e);
	 }
	}

	public void getTodayTotalVisitors(Long entryEventId,Long exitEventId,final Long parentEventId){
		//Long totalVisitors = 0l;
		//Long currentVisitors = 0l;
		
	try{	
		Date today = dateUtilService.getCurrentDateAndTime();
		
		//0eventId, 1attendedTime, 2tdpCadreId,3invitee tdpCadreId
		List<Object[]> cadreEventList = eventAttendeeDAO.getCadreVisitInfo(today, entryEventId, exitEventId);
		
		final Date todayDate = dateUtilService.getCurrentDateAndTime();
		
		long hours = 0;
		long minutes = 0;
		long seconds = 0;
		boolean invite = false;
		
		Long above8hrs =0l;
		Long seventoeight =0l;
		Long sixtoseven =0l;
		Long fivetosix =0l;
		Long fourtofive =0l;
		Long threetofour =0l;
		Long twotothree =0l;
		Long onetotwo =0l;
		Long halfanhour =0l;
		Long belowhalfanhour = 0l;
		
		
		Long above8hrsInv =0l;
		Long seventoeightInv =0l;
		Long sixtosevenInv =0l;
		Long fivetosixInv =0l;
		Long fourtofiveInv =0l;
		Long threetofourInv =0l;
		Long twotothreeInv =0l;
		Long onetotwoInv =0l;
		Long halfanhourInv =0l;
		Long belowhalfanhourInv = 0l;
		
		
		final int ONE_DAY = 1000 * 60 * 60 * 24;
	    final int ONE_HOUR = ONE_DAY / 24;
	    final int ONE_MINUTE = ONE_HOUR / 60;
	    final int ONE_SECOND = ONE_MINUTE / 60;
	    int totalRecordsSize = cadreEventList.size();
	       
	    if(cadreEventList.size() > 0){
	    	Long currentCadreId = null;
	    	boolean isCalc = false;
			for(int i=0;i<totalRecordsSize;i++){
				isCalc = false;
				Date startTime = null;
				Date endTime = null;
					//first time
					if(currentCadreId == null){
						currentCadreId = (Long.valueOf(cadreEventList.get(i)[2].toString())).longValue();
						if(cadreEventList.get(i)[3] != null){
						    invite = true;
						}
					}
					//if current record not belongs to previous record user then updating time wise count.
					if(currentCadreId.longValue() != (Long.valueOf(cadreEventList.get(i)[2].toString())).longValue()){
						if(hours >= 8){
							above8hrs=above8hrs+1;
							if(invite){
							  above8hrsInv=above8hrsInv+1;
							}
						}else if(hours >= 7 && hours < 8){
							seventoeight=seventoeight+1;
							if(invite){
							  seventoeightInv=seventoeightInv+1;
							}
						}else if(hours >= 6 && hours < 7){
							sixtoseven=sixtoseven+1;
							if(invite){
							  sixtosevenInv=sixtosevenInv+1;
							}
						}else if(hours >= 5 && hours < 6){
							fivetosix=fivetosix+1;
							if(invite){
							  fivetosixInv=fivetosixInv+1;
							}
						}else if(hours >= 4 && hours < 5){
							fourtofive=fourtofive+1;
							if(invite){
							  fourtofiveInv=fourtofiveInv+1;
							}
						}else if(hours >= 3 && hours < 4){
							threetofour=threetofour+1;
							if(invite){
							  threetofourInv=threetofourInv+1;
							}
						}else if(hours >= 2 && hours < 3){
							twotothree=twotothree+1;
							if(invite){
							  twotothreeInv=twotothreeInv+1;
							}
						}else if(hours >= 1 && hours < 2){
							onetotwo=onetotwo+1;
							if(invite){
							  onetotwoInv=onetotwoInv+1;
							}
						}else{
							if(minutes >= 30){
								halfanhour=halfanhour+1;
								if(invite){
								  halfanhourInv=halfanhourInv+1;
								}
							}else{
								belowhalfanhour=belowhalfanhour+1;
								if(invite){
								  belowhalfanhourInv=belowhalfanhourInv+1;
								}
							}
						}
						 hours = 0;
						 minutes = 0;
						 seconds = 0;
						 invite = false;
						 //changing current user
						 currentCadreId = (Long.valueOf(cadreEventList.get(i)[2].toString())).longValue();
						 if(cadreEventList.get(i)[3] != null){
							    invite = true;
							}
					}
					
					int nextRecordIndex = i+1;
					//checking middle record or not
					if(nextRecordIndex <= (totalRecordsSize-1)){
						//if next record belongs to user
						if(currentCadreId.longValue() == (Long.valueOf(cadreEventList.get(nextRecordIndex)[2].toString())).longValue()){
							if((Long.valueOf(cadreEventList.get(i)[0].toString())).longValue() == entryEventId.longValue() && 
									  (Long.valueOf(cadreEventList.get(nextRecordIndex)[0].toString())).longValue() == exitEventId.longValue()){
							  startTime = (Date)cadreEventList.get(i)[1];
							  endTime = (Date)cadreEventList.get(nextRecordIndex)[1];
							}
						}else{//next record belongs to other user
							//if record is exit
							if((Long.valueOf(cadreEventList.get(i)[0].toString())).longValue() == exitEventId.longValue()){
								continue;
							}else{//considering current time as exit
								startTime = (Date)cadreEventList.get(i)[1];
								endTime = todayDate;
							}
						}
					}else{//if last record
						//if last record is exit
						if((Long.valueOf(cadreEventList.get(i)[0].toString())).longValue() == exitEventId.longValue()){
							continue;
						}else{//considering current time as exit
							startTime = (Date)cadreEventList.get(i)[1];
							endTime = todayDate;
						}
					}
					
					if(startTime!=null && endTime!=null ){
						isCalc = true;
						Calendar cal = Calendar.getInstance();
				        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
				        cal.setTime(startTime);
				        long t1 = cal.getTimeInMillis();
				        cal.setTime(endTime);
					    
				        long diff = Math.abs(cal.getTimeInMillis() - t1);
				        
		
				        long d = diff / ONE_DAY;
				        diff %= ONE_DAY;
		
				        long h = diff / ONE_HOUR;
				        diff %= ONE_HOUR;
		
				        long m = diff / ONE_MINUTE;
				        diff %= ONE_MINUTE;
		
				        long s = diff / ONE_SECOND;
				        
				        hours=hours+h;
				        minutes=minutes+m;
				        seconds=seconds+s;
					}
					if(seconds > 59){
						minutes = minutes+seconds/60;
						seconds=seconds%60;
					}
					if(minutes > 59){
						hours = hours+minutes/60;
						minutes=minutes%60;
					}
			}
			if(isCalc){
				if(hours >= 8){
					above8hrs=above8hrs+1;
				}else if(hours >= 7 && hours < 8){
					seventoeight=seventoeight+1;
				}else if(hours >= 6 && hours < 7){
					sixtoseven=sixtoseven+1;
				}else if(hours >= 5 && hours < 6){
					fivetosix=fivetosix+1;
				}else if(hours >= 4 && hours < 5){
					fourtofive=fourtofive+1;
				}else if(hours >= 3 && hours < 4){
					threetofour=threetofour+1;
				}else if(hours >= 2 && hours < 3){
					twotothree=twotothree+1;
				}else if(hours >= 1 && hours < 2){
					onetotwo=onetotwo+1;
				}else{
					if(minutes >= 30){
						halfanhour=halfanhour+1;
					}else{
						belowhalfanhour=belowhalfanhour+1;
					}
				}
			}
	    }
	    final Long above8hrs1 =   above8hrs;
	    final Long seventoeight1 = seventoeight;
	    final Long sixtoseven1 =  sixtoseven;
	    final Long fivetosix1 =   fivetosix;
	    final Long fourtofive1 =  fourtofive;
	    final Long threetofour1 = threetofour;
	    final Long twotothree1 = twotothree;
	    final Long onetotwo1 =  onetotwo;
	    final Long halfanhour1 =  halfanhour;
	    final Long belowhalfanhour1 = belowhalfanhour;
	    
	    final Long  above8hrsInv1= above8hrsInv;
	    final Long  seventoeightInv1= seventoeightInv; 
	    final Long  sixtosevenInv1=sixtosevenInv;
	    final Long  fivetosixInv1= fivetosixInv; 
	    final Long  fourtofiveInv1=fourtofiveInv; 
	    final Long  threetofourInv1=  threetofourInv; 
	    final Long  twotothreeInv1=twotothreeInv;
	    final Long  onetotwoInv1=  onetotwoInv;
	    final Long  halfanhourInv1=halfanhourInv; 
	    final Long  belowhalfanhourInv1= belowhalfanhourInv;
		
		
	    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				CadreMahanaduVisitInfo info = new CadreMahanaduVisitInfo();
				info.setInsertedTime(todayDate);
				info.setParentEventId(parentEventId);
				info = cadreMahanaduVisitInfoDAO.save(info);
				
				saveDetails(info,"above8hrs",above8hrs1,above8hrsInv1);
				saveDetails(info,"seventoeight",seventoeight1,seventoeightInv1);
				saveDetails(info,"sixtoseven",sixtoseven1,sixtosevenInv1);
				saveDetails(info,"fivetosix",fivetosix1,fivetosixInv1);
				saveDetails(info,"fourtofive",fourtofive1,fourtofiveInv1);
				saveDetails(info,"threetofour",threetofour1,threetofourInv1);
				saveDetails(info,"twotothree",twotothree1,twotothreeInv1);
				saveDetails(info,"onetotwo",onetotwo1,onetotwoInv1);
				saveDetails(info,"halfanhour",halfanhour1,halfanhourInv1);
				saveDetails(info,"belowhalfanhour",belowhalfanhour1,belowhalfanhourInv1);
			}
		});
	    cadreMahanaduVisitInfoDAO.flushAndclearSession();
	 }catch(Exception e){
		 LOG.error("Exception rised in getTodayTotalVisitors()",e);
	 }
	}
	
	private void saveDetails(CadreMahanaduVisitInfo info,String type,Long count,Long inviteCount){
		CadreMahanaduVisitDetails details = new CadreMahanaduVisitDetails();
		details.setCadreMahanaduVisitInfo(info);
		details.setType(type);
		details.setCount(count);
		details.setInviteCount(inviteCount);
		cadreMahanaduVisitDetailsDAO.save(details);
	}
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfo(Date fromDate,Date toDate){
		List<MahanaduVisitVO> returnList = new ArrayList<MahanaduVisitVO>();
		Long totalVisitors = 0l;
		Long currentVisitors = 0l;
		Long currentInviteeVisitors = 0l;
		Long reqParentEventId = null;
		try{
			
		EntryExitInfo entryExitInfo = entryExitInfoDAO.getAll().get(0);
		Long entryEventId = entryExitInfo.getEntryId();
		Long exitEventId = entryExitInfo.getExitId();
		Long parentEventId = entryExitInfo.getParentEventId();
		reqParentEventId = parentEventId;
		
		Calendar fromDateCal = Calendar.getInstance();
		fromDateCal.setTime(fromDate);
		
		Calendar toDateCal = Calendar.getInstance();
		toDateCal.setTime(toDate);
		
		Date todayDate = dateUtilService.getCurrentDateAndTime();
		totalVisitors = eventAttendeeDAO.getTodayTotalVisitors(todayDate,parentEventId);
		currentVisitors = (eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId)).longValue();
		currentInviteeVisitors = (eventAttendeeDAO.getCurrentInviteeVisitors(todayDate, entryEventId, exitEventId)).longValue();
		
		
			if(!(toDate.before(fromDate))){
			    int from = fromDateCal.get(Calendar.DAY_OF_MONTH);
			    int to   = toDateCal.get(Calendar.DAY_OF_MONTH);
			    Map<Integer,Date> reqDates = new HashMap<Integer,Date>();
			    List<Integer> keys = new ArrayList<Integer>();
			    for(int i=from;i<=to;i++){
			    	Calendar cal = Calendar.getInstance();
			    	cal.set(2015, 4, i);
			    	reqDates.put(i, cal.getTime());
			    	if(!keys.contains(i)){
			    	    keys.add(i);
			    	}
			    }
			   for(Integer key:keys){ 
				//0id,1date
				List<Object[]> latestRecord = cadreMahanaduVisitDetailsDAO.getLatestInfoRecord(reqDates.get(key),parentEventId);
				if(latestRecord.size() > 0 && latestRecord.get(0)[0] != null){
					//0type 1count
					List<Object[]> visitDetailsList = cadreMahanaduVisitDetailsDAO.getLatestRecords((Long)latestRecord.get(0)[0]);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
					MahanaduVisitVO returnVo = new MahanaduVisitVO();
					returnVo.setLastUpdated(sdf.format((Date)latestRecord.get(0)[1]));
					for(Object[] visitDetail:visitDetailsList){
						if(visitDetail[0].toString().equalsIgnoreCase("above8hrs")){
							returnVo.setAbove8hrs((Long)visitDetail[1]);
							returnVo.setAbove8hrsInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("seventoeight")){
							returnVo.setSeventoeight((Long)visitDetail[1]);
							returnVo.setSeventoeightInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("sixtoseven")){
							returnVo.setSixtoseven((Long)visitDetail[1]);
							returnVo.setSixtosevenInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("fivetosix")){
							returnVo.setFivetosix((Long)visitDetail[1]);
							returnVo.setFivetosixInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("fourtofive")){
							returnVo.setFourtofive((Long)visitDetail[1]);
							returnVo.setFourtofiveInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("threetofour")){
							returnVo.setThreetofour((Long)visitDetail[1]);
							returnVo.setThreetofourInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("twotothree")){
							returnVo.setTwotothree((Long)visitDetail[1]);
							returnVo.setTwotothreeInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("onetotwo")){
							returnVo.setOnetotwo((Long)visitDetail[1]);
							returnVo.setOnetotwoInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("halfanhour")){
							returnVo.setHalfanhour((Long)visitDetail[1]);
							returnVo.setHalfanhourInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("belowhalfanhour")){
							returnVo.setBelowhalfanhour((Long)visitDetail[1]);
							returnVo.setBelowhalfanhourInv((Long)visitDetail[2]);
						}
						
					}
					returnList.add(returnVo);
				}
			 }
			}
			

		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfo()",e);
		}
		if(returnList.size() > 0){
			returnList.get(0).setTotalVisitors(totalVisitors);
			returnList.get(0).setCurrentVisitors(currentVisitors);
			returnList.get(0).setCurrentInviteeVisitors(currentInviteeVisitors);
			returnList.get(0).setParentEventId(reqParentEventId);
		}else{
			MahanaduVisitVO returnVo = new MahanaduVisitVO();
			returnList.add(returnVo);
			returnList.get(0).setTotalVisitors(totalVisitors);
			returnList.get(0).setCurrentVisitors(currentVisitors);
			returnList.get(0).setCurrentInviteeVisitors(currentInviteeVisitors);
			returnList.get(0).setParentEventId(reqParentEventId);
		}
		return returnList;
	}
}
