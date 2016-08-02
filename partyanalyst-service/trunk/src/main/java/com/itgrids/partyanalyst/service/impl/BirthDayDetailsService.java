package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ILeaderOccasionDAO;
import com.itgrids.partyanalyst.dao.ILeaderOccasionWishDetailsDAO;
import com.itgrids.partyanalyst.dao.hibernate.LeaderOccasionDAO;
import com.itgrids.partyanalyst.dao.hibernate.LeaderOccasionWishDetailsDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BirthDayDetailsVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;
import com.itgrids.partyanalyst.service.IBirthDayDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class BirthDayDetailsService implements IBirthDayDetailsService {
	private final static Logger LOG = Logger.getLogger(BirthDayDetailsService.class);
	private ILeaderOccasionDAO leaderOccasionDAO;
	private ILeaderOccasionWishDetailsDAO leaderOccasionWishDetailsDAO;
	private TransactionTemplate transactionTemplate = null;
	
	public ILeaderOccasionWishDetailsDAO getLeaderOccasionWishDetailsDAO() {
		return leaderOccasionWishDetailsDAO;
	}
	public void setLeaderOccasionWishDetailsDAO(ILeaderOccasionWishDetailsDAO leaderOccasionWishDetailsDAO) {
		this.leaderOccasionWishDetailsDAO = leaderOccasionWishDetailsDAO;
	}
	private DateUtilService dateUtilService = new DateUtilService();
	
	public ILeaderOccasionDAO getLeaderOccasionDAO() {
		return leaderOccasionDAO;
	}

	public void setLeaderOccasionDAO(ILeaderOccasionDAO leaderOccasionDAO) {
		this.leaderOccasionDAO = leaderOccasionDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	@SuppressWarnings("null")
	@Override
	public BirthDayDetailsVO getLeaderOccasionDetails(Long occastionTypeId) {
		Date fromDate = null;
		Date toDate = null;
		BirthDayDetailsVO birthDayDetailsVOs = new BirthDayDetailsVO();
		try {
			TdpCadreVO tdpCadreVO = null;
			IdNameVO idNameVO = null;
			List<TdpCadreVO> cadreVOs = new ArrayList<TdpCadreVO>(0);
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>(0);
			Long betweenDatesCount = 0L;
			String searchTypeStr = "Today";
			
			
			//30 days
			fromDate = getBirthdaySearchfromDateBasedOnValue(-30);
			toDate = getBirthdaySearchToDateBasedOnValue(-1);
			Long totalBirthdayDetailsCountForOneMonth = leaderOccasionDAO.getTotalBirthDays(fromDate,toDate);
			Long totalBirthdayWishedDetailsCountForOneMonth = leaderOccasionDAO.getTotalWishedBirthDays(fromDate,toDate);
			idNameVO = new IdNameVO();
			idNameVO.setName("Last 30 Days");
			idNameVO.setActualCount(totalBirthdayDetailsCountForOneMonth);
			idNameVO.setCount(totalBirthdayWishedDetailsCountForOneMonth);
			idNameVOs.add(idNameVO);

			
			//last 7 days
			fromDate = getBirthdaySearchfromDateBasedOnValue(-7);
			toDate = getBirthdaySearchToDateBasedOnValue(-1);
			Long totalBirthdayDetailsCountForOneWeek = leaderOccasionDAO.getTotalBirthDays(fromDate,toDate);
			Long totalBirthdayWishedDetailsCountForOneWeek = leaderOccasionDAO.getTotalWishedBirthDays(fromDate,toDate);
			idNameVO = new IdNameVO();
			idNameVO.setName("Last 7 Days");
			idNameVO.setActualCount(totalBirthdayDetailsCountForOneWeek);
			idNameVO.setCount(totalBirthdayWishedDetailsCountForOneWeek);
			idNameVOs.add(idNameVO);

			
			//yesterday
			fromDate = getBirthdaySearchfromDateBasedOnValue(-1);
			toDate = getBirthdaySearchToDateBasedOnValue(-1);
			Long totalBirthdayDetailsCountForYesterday = leaderOccasionDAO.getTotalBirthDays(fromDate,toDate);
			Long totalBirthdayWishedDetailsCountForYesterday = leaderOccasionDAO.getTotalWishedBirthDays(fromDate,toDate);
			idNameVO = new IdNameVO();
			idNameVO.setName("Yesterday");
			idNameVO.setActualCount(totalBirthdayDetailsCountForYesterday);
			idNameVO.setCount(totalBirthdayWishedDetailsCountForYesterday);
			idNameVOs.add(idNameVO);
			
			
			fromDate = getBirthdaySearchfromDateBasedOnValue(1);
			toDate = getBirthdaySearchToDateBasedOnValue(1);
			//today
			fromDate = new Date();
			toDate = new Date();
			Long totalBdayDetailsCntToday = leaderOccasionDAO.getTotalBirthDays(fromDate,toDate);
			Long totalBdayWishedDetailsCntToday = leaderOccasionDAO.getTotalWishedBirthDays(fromDate,toDate);
			idNameVO = new IdNameVO();
			idNameVO.setName("Today");
			idNameVO.setActualCount(totalBdayDetailsCntToday);
			idNameVO.setCount(totalBdayWishedDetailsCntToday);
			idNameVOs.add(idNameVO);
			
			//List<Object[]> leaderOccasionDetailsForTaday = leaderOccasionDAO.getLeaderOccasionDetailsForTaday(fromDate, toDate,occastionTypeId);

			List<Object[]> leaderOccasionDetailsForTaday = leaderOccasionDAO.getLeaderOccasionDetailsForTaday(searchTypeStr,betweenDatesCount,occastionTypeId);
			
			if (leaderOccasionDetailsForTaday != null && leaderOccasionDetailsForTaday.size() > 0) {
				for (Object[] result : leaderOccasionDetailsForTaday) {

					tdpCadreVO = new TdpCadreVO();
					tdpCadreVO.setImageURL(result[0] != null ? result[0].toString() : "");
					tdpCadreVO.setName(result[1] != null ? result[1].toString() : "");

					Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(result[2].toString().substring(0, 19));
					String convertDate = new SimpleDateFormat("dd MMM").format(date);

					tdpCadreVO.setDateOfBirth(convertDate);
					tdpCadreVO.setMobileNo(result[3] != null ? result[3].toString() : "");
					// tdpCadreVO.setWished(result[4] != null ?
					// result[4].toString(): "");
					tdpCadreVO.setId((Long) result[4]);
					cadreVOs.add(tdpCadreVO);
				}
			}
			
			fromDate = getBirthdaySearchfromDateBasedOnValue(1);
			toDate = getBirthdaySearchToDateBasedOnValue(1);
			
			
			
			Long totalBdayDetailsCntTommorow = leaderOccasionDAO.getTotalBirthDays(fromDate,toDate);
			Long totalBdayWishedDetailsCntTommorow = leaderOccasionDAO.getTotalWishedBirthDays(fromDate,toDate);
			idNameVO = new IdNameVO();
			idNameVO.setName("Tomorrow");
			idNameVO.setActualCount(totalBdayDetailsCntTommorow);
			idNameVO.setCount(totalBdayWishedDetailsCntTommorow);
			idNameVOs.add(idNameVO);
			
			fromDate = getBirthdaySearchfromDateBasedOnValue(8);
			toDate = getBirthdaySearchToDateBasedOnValue(8);
			
			
			Long totalBdayDetailsCntNxtweek = leaderOccasionDAO.getTotalBirthDays(fromDate,toDate);
			Long totalBdayWishedDetailsCntNxtweek = leaderOccasionDAO.getTotalWishedBirthDays(fromDate,toDate);
			idNameVO = new IdNameVO();
			idNameVO.setName("Next 7 Days");
			idNameVO.setActualCount(totalBdayDetailsCntNxtweek);
			idNameVO.setCount(totalBdayWishedDetailsCntNxtweek);
			idNameVOs.add(idNameVO);

			fromDate = getBirthdaySearchfromDateBasedOnValue(31);
			toDate = getBirthdaySearchToDateBasedOnValue(31);
			
			
			
			Long totalBdayDetailsCntNxtMonth = leaderOccasionDAO.getTotalBirthDays(fromDate,toDate);
			Long totalBdayWishedDetailsCntNxtMonth = leaderOccasionDAO.getTotalWishedBirthDays(fromDate,toDate);
			idNameVO = new IdNameVO();
			idNameVO.setName("Next 30 days");
			idNameVO.setActualCount(totalBdayDetailsCntNxtMonth);
			idNameVO.setCount(totalBdayWishedDetailsCntNxtMonth);
			idNameVOs.add(idNameVO);
			birthDayDetailsVOs.setTdpCadreVOList(cadreVOs);
			birthDayDetailsVOs.setIdNameVOList(idNameVOs);
		} catch (Exception e) {
			LOG.error("Exception raised in getLeaderOccasionDetails in BirthDayService service:-", e);
		}
		return birthDayDetailsVOs;
	}
	
	

	
	/* (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IBirthDayDetailsService#getAllLstOfDaysForBdayDtails(java.lang.String)
	 */
	public BirthDayDetailsVO getAllLstOfDaysForBdayDtails(String searchType,Long occastionTypeId) {
		BirthDayDetailsVO allBDayDtlsVOs = new BirthDayDetailsVO();
		try {
			TdpCadreVO tdpCadreVO = null;
			IdNameVO idNameVO = new IdNameVO();
			List<TdpCadreVO> cadreVOs = new ArrayList<TdpCadreVO>();
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
	
			Date fromDate = new DateUtilService().getCurrentDateAndTime();
			Date toDate = new DateUtilService().getCurrentDateAndTime();
			
			/*
			//get from and to date
			if (searchType != null && searchType.equals("Last 30 Days")) {

				fromDate = getBirthdaySearchfromDateBasedOnValue(-30);
				toDate = getBirthdaySearchToDateBasedOnValue(-1);
			}
			else if (searchType != null && searchType.equals("Last 7 Days")) {
				fromDate = getBirthdaySearchfromDateBasedOnValue(-7);
				toDate = getBirthdaySearchToDateBasedOnValue(-1);
			}
			else if (searchType != null && searchType.equals("Yesterday")) {
				fromDate = getBirthdaySearchfromDateBasedOnValue(-1);
				toDate = getBirthdaySearchToDateBasedOnValue(-1);
			}
			else if (searchType != null && searchType.equals("Today")) {
				fromDate = new Date();
				toDate = new Date();
			}
			
			else if (searchType != null && searchType.equals("Tomorrow")) {
				fromDate = getBirthdaySearchfromDateBasedOnValue(1);
				toDate = getBirthdaySearchToDateBasedOnValue(1);
			}
			else if (searchType != null && searchType.equals("Next 7 days")) {
				fromDate = getBirthdaySearchfromDateBasedOnValue(1);
				toDate = getBirthdaySearchToDateBasedOnValue(7);
			}
			else if (searchType != null && searchType.equals("Next 30 days")) {
				fromDate = getBirthdaySearchfromDateBasedOnValue(1);
				toDate = getBirthdaySearchToDateBasedOnValue(30);
			}
			
			List<Integer> months = new ArrayList<Integer>(0);
			List<Integer> days = new ArrayList<Integer>(0);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			months.add(Integer.parseInt(sdf.format(fromDate).split("-")[1]));
			days.add(Integer.parseInt(sdf.format(fromDate).split("-")[2]));
			
			months.add(Integer.parseInt(sdf.format(toDate).split("-")[1]));
			days.add(Integer.parseInt(sdf.format(toDate).split("-")[2]));
			
			
			
			List<Object[]> leaderOccasionDetailsForTaday = leaderOccasionDAO.getLeaderOccasionDetailsForTaday1(months, days,occastionTypeId);
			
			if (leaderOccasionDetailsForTaday != null && leaderOccasionDetailsForTaday.size() > 0) {
				for (Object[] result : leaderOccasionDetailsForTaday) {
					tdpCadreVO = new TdpCadreVO();
					tdpCadreVO.setImageURL(result[0] != null ? result[0].toString() : "");
					tdpCadreVO.setName(result[1] != null ? result[1].toString() : "");
					date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(result[2].toString().substring(0, 19));
					String convertDate = new SimpleDateFormat("dd MMMM").format(date);
					tdpCadreVO.setDateOfBirth(convertDate);
					tdpCadreVO.setMobileNo(result[3] != null ? result[3].toString() : "");
					// tdpCadreVO.setWished(result[4] != null ?
					// result[4].toString(): "");
					tdpCadreVO.setId((Long) result[4]);
					cadreVOs.add(tdpCadreVO);
					allBDayDtlsVOs.setTdpCadreVOList(cadreVOs);
					// allBDayDtlsVOs.setIdNameVOList(idNameVOs);

				}

			}
			
			*/
			
			List<Object[]> leaderOccasionDetailsForTaday  = null;
			Long betweenDatesCount=1L;
			String searchTypeStr = "Next";
			if (searchType != null && searchType.equals("Last 30 Days")) {
				searchTypeStr = "Previous";
				betweenDatesCount = 30L;
			}
			else if (searchType != null && searchType.equals("Last 7 Days")) {
				searchTypeStr = "Previous";
				betweenDatesCount = 7L;	
			} else if (searchType != null && searchType.equals("Yesterday")) {
				searchTypeStr = "Previous";
				betweenDatesCount = 1L;
			}
			else if (searchType != null && searchType.equals("Today")) {
				betweenDatesCount = 0L;
			}
			else if (searchType != null && searchType.equals("Tomorrow")) {
				betweenDatesCount = 1L;
			}
			else if (searchType != null && searchType.equals("Next 7 Days")) {
				betweenDatesCount = 7L;
			}
			else if (searchType != null && searchType.equals("Next 30 days")) {
				betweenDatesCount = 30L;
			}
			
			leaderOccasionDetailsForTaday = leaderOccasionDAO.getLeaderOccasionDetailsForTaday(searchTypeStr,betweenDatesCount,occastionTypeId);
			if (leaderOccasionDetailsForTaday != null && leaderOccasionDetailsForTaday.size() > 0) {
				for (Object[] result : leaderOccasionDetailsForTaday) {
					tdpCadreVO = new TdpCadreVO();
					tdpCadreVO.setImageURL(result[0] != null ? result[0].toString() : "");
					tdpCadreVO.setName(result[1] != null ? result[1].toString() : "");
					String convertDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(result[2].toString().substring(0, 19)).toString();
					tdpCadreVO.setDateOfBirth(convertDate);
					tdpCadreVO.setMobileNo(result[3] != null ? result[3].toString() : "");
					// tdpCadreVO.setWished(result[4] != null ?
					// result[4].toString(): "");
					tdpCadreVO.setId((Long) result[4]);
					cadreVOs.add(tdpCadreVO);
					allBDayDtlsVOs.setTdpCadreVOList(cadreVOs);
					// allBDayDtlsVOs.setIdNameVOList(idNameVOs);

				}

			}

		} catch (Exception e) {
			LOG.error("Exception raised in getAllLstOfDaysForBdayDtails in BirthDayService service:-", e);
		}
		return allBDayDtlsVOs;

	}
	
	public String getWishingDetails(Long searchId) {
		String status = null;
		List<TdpCadreVO> cadreVOs = new ArrayList<TdpCadreVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		List<LeaderOccasionWishDetails> id=null;
		
         try{
        	 //transactionTemplate.execute(new TransactionCallbackWithoutResult() {
     	        //protected void doInTransactionWithoutResult(TransactionStatus arg0){       	 
        	 TdpCadreVO tdpCadreVO = new TdpCadreVO();
    		 //id=leaderOccasionDAO.getWishingDetails(searchId,year);
    	 if(id == null || id.size() <= 0){
    		 LeaderOccasionWishDetails  leaderOccasionWishDetails=new LeaderOccasionWishDetails();
    		 //leaderOccasionWishDetails.setLeaderOccasionWishDetailsId(tdpCadreVO.getId());
    		 leaderOccasionWishDetails.setLeaderOccasionId(searchId);
    		 leaderOccasionWishDetails.setWishTime(dateUtilService.getCurrentDateAndTime());
    		 //leaderOccasionWishDetails.setDescription(description);
    		 leaderOccasionWishDetails.setIsdeleted("false");
    		 leaderOccasionWishDetails.setYear(year);
    		 
    		  leaderOccasionWishDetailsDAO.save(leaderOccasionWishDetails);
    	 
    		 
    	 }
    	 else
    	 {
    		 LeaderOccasionWishDetails model = id.get(0);
    		if(model.getIsdeleted()=="false") 
    		{
    			model.setIsdeleted("true");
    			leaderOccasionWishDetailsDAO.save(model);
    		}
    		else
    		{
    			model.setIsdeleted("false");
    			leaderOccasionWishDetailsDAO.save(model);	
    		}
    	 }		
    	 status = "success";
     	        }
    	 catch (Exception e) {
    		 e.printStackTrace();
    		 status = "failure";
			LOG.error("Exception ocured in gettingUserDetails()"+e);
		}
        
		return status;
   
		
	}
	/**
	 * @param value
	 * @return
	 */
	private Date getBirthdaySearchfromDateBasedOnValue(Integer value) {
		Date fromDate = null;
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, value);
		fromDate = calender.getTime();
		return fromDate;
	}
	
	/**
	 * @param value
	 * @return
	 */
	private Date getBirthdaySearchToDateBasedOnValue(Integer value){
		Date toDate = null;
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, value);
		toDate = calender.getTime();
		return toDate;
	}
	
	}


