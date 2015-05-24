package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitInfoDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitDetails;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitInfo;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class MahanaduDashBoardService implements IMahanaduDashBoardService {

	private IEventAttendeeDAO eventAttendeeDAO;
	private ICadreMahanaduVisitDetailsDAO cadreMahanaduVisitDetailsDAO;
	private ICadreMahanaduVisitInfoDAO cadreMahanaduVisitInfoDAO;
	private TransactionTemplate transactionTemplate;
	
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

	public void getTodayTotalVisitors(){
		//Long totalVisitors = 0l;
		//Long currentVisitors = 0l;
	 try{	
		Long entryEventId = 14l;
		Long exitEventId = 15l;
		
		Date today = dateUtilService.getCurrentDateAndTime();
		
		//0eventId, 1attendedTime, 2tdpCadreId
		List<Object[]> cadreEventList = eventAttendeeDAO.getCadreVisitInfo(today, entryEventId, exitEventId);
		
		final Date todayDate = dateUtilService.getCurrentDateAndTime();
		
		long hours = 0;
		long minutes = 0;
		long seconds = 0;
		
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
						currentCadreId = (Long)cadreEventList.get(i)[2];
					}
					//if current record not belongs to previous record user then updating time wise count.
					if(currentCadreId.longValue() != ((Long)cadreEventList.get(i)[2]).longValue()){
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
						 hours = 0;
						 minutes = 0;
						 seconds = 0;
						 //changing current user
						 currentCadreId = (Long)cadreEventList.get(i)[2];
					}
					
					int nextRecordIndex = i+1;
					//checking middle record or not
					if(nextRecordIndex <= (totalRecordsSize-1)){
						//if next record belongs to user
						if(currentCadreId.longValue() == ((Long)cadreEventList.get(nextRecordIndex)[2]).longValue()){
							if(((Long)cadreEventList.get(i)[0]).longValue() == entryEventId.longValue() && 
									  ((Long)cadreEventList.get(nextRecordIndex)[0]).longValue() == exitEventId.longValue()){
							  startTime = (Date)cadreEventList.get(i)[1];
							  endTime = (Date)cadreEventList.get(nextRecordIndex)[1];
							}
						}else{//next record belongs to other user
							//if record is exit
							if(((Long)cadreEventList.get(i)[0]).longValue() == exitEventId.longValue()){
								continue;
							}else{//considering current time as exit
								startTime = (Date)cadreEventList.get(i)[1];
								endTime = todayDate;
							}
						}
					}else{//if last record
						//if last record is exit
						if(((Long)cadreEventList.get(i)[0]).longValue() == exitEventId.longValue()){
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
	    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				CadreMahanaduVisitInfo info = new CadreMahanaduVisitInfo();
				info.setInsertedTime(todayDate);
				info = cadreMahanaduVisitInfoDAO.save(info);
				
				saveDetails(info,"above8hrs",above8hrs1);
				saveDetails(info,"seventoeight",seventoeight1);
				saveDetails(info,"sixtoseven",sixtoseven1);
				saveDetails(info,"fivetosix",fivetosix1);
				saveDetails(info,"fourtofive",fourtofive1);
				saveDetails(info,"threetofour",threetofour1);
				saveDetails(info,"twotothree",twotothree1);
				saveDetails(info,"onetotwo",onetotwo1);
				saveDetails(info,"halfanhour",halfanhour1);
				saveDetails(info,"belowhalfanhour",belowhalfanhour1);
			}
		});
	 }catch(Exception e){
		 LOG.error("Exception rised in getTodayTotalVisitors()",e);
	 }
	}
	
	private void saveDetails(CadreMahanaduVisitInfo info,String type,Long count){
		CadreMahanaduVisitDetails details = new CadreMahanaduVisitDetails();
		details.setCadreMahanaduVisitInfo(info);
		details.setType(type);
		details.setCount(count);
		cadreMahanaduVisitDetailsDAO.save(details);
	}
	public MahanaduVisitVO getTodayTotalAndCurrentUsersInfo(){
		MahanaduVisitVO returnVo = new MahanaduVisitVO();
		try{
		Long entryEventId = 14l;
		Long exitEventId = 15l;
		
		Date todayDate = dateUtilService.getCurrentDateAndTime();
		
		returnVo.setTotalVisitors(eventAttendeeDAO.getTodayTotalVisitors(todayDate));
		returnVo.setCurrentVisitors((eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId)).longValue());
		
		//0id,1date
		List<Object[]> latestRecord = cadreMahanaduVisitDetailsDAO.getLatestInfoRecord(todayDate);
		if(latestRecord.size() > 0 && latestRecord.get(0)[0] != null){
			//0type 1count
			List<Object[]> visitDetailsList = cadreMahanaduVisitDetailsDAO.getLatestRecords((Long)latestRecord.get(0)[0]);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
			returnVo.setLastUpdated(sdf.format((Date)latestRecord.get(0)[1]));
			for(Object[] visitDetail:visitDetailsList){
				if(visitDetail[0].toString().equalsIgnoreCase("above8hrs")){
					returnVo.setAbove8hrs((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("seventoeight")){
					returnVo.setSeventoeight((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("sixtoseven")){
					returnVo.setSixtoseven((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("fivetosix")){
					returnVo.setFivetosix((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("fourtofive")){
					returnVo.setFourtofive((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("threetofour")){
					returnVo.setThreetofour((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("twotothree")){
					returnVo.setTwotothree((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("onetotwo")){
					returnVo.setOnetotwo((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("halfanhour")){
					returnVo.setHalfanhour((Long)visitDetail[1]);
				}else if(visitDetail[0].toString().equalsIgnoreCase("belowhalfanhour")){
					returnVo.setBelowhalfanhour((Long)visitDetail[1]);
				}
			}
		}
		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfo()",e);
		}
		return returnVo;
	}
}
