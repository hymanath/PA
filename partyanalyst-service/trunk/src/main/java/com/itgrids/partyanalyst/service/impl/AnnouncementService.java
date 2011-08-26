package com.itgrids.partyanalyst.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnnouncementDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserAnnouncementDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Announcement;
import com.itgrids.partyanalyst.model.UserAnnouncement;
import com.itgrids.partyanalyst.model.UserConstituencyScope;
import com.itgrids.partyanalyst.service.IAnnouncementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnnouncementService implements IAnnouncementService {

	
	private IAnnouncementDAO announcementDAO;
	private IUserAnnouncementDAO userAnnouncementDAO;
	private IUserConstituencyScopeDAO userConstituencyScopeDAO;
	private IRegistrationDAO registrationDAO;
	private IConstituencyDAO constituencyDAO;
	private TransactionTemplate transactionTemplate = null;
	private static final Logger log = Logger.getLogger(IAnnouncementService.class);
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public IAnnouncementDAO getAnnouncementDAO() {
		return announcementDAO;
	}

	public void setAnnouncementDAO(IAnnouncementDAO announcementDAO) {
		this.announcementDAO = announcementDAO;
	}

	public IUserAnnouncementDAO getUserAnnouncementDAO() {
		return userAnnouncementDAO;
	}

	public void setUserAnnouncementDAO(IUserAnnouncementDAO userAnnouncementDAO) {
		this.userAnnouncementDAO = userAnnouncementDAO;
	}

	public IUserConstituencyScopeDAO getUserConstituencyScopeDAO() {
		return userConstituencyScopeDAO;
	}

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public void setUserConstituencyScopeDAO(
			IUserConstituencyScopeDAO userConstituencyScopeDAO) {
		this.userConstituencyScopeDAO = userConstituencyScopeDAO;
	}
		
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ResultStatus saveAnnouncement(final AnnouncementVO announcementVO) 
	{
		final ResultStatus resultStatus = new ResultStatus();
	try{
			
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				
		Announcement announcement = null;
		UserAnnouncement userAnnouncement = null;
		UserConstituencyScope userConstituencyScope = null;
		SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
		
		if(announcementVO.getWindowTask().equalsIgnoreCase(IConstants.NEW))
		{
			announcement = new Announcement();
			userAnnouncement = new UserAnnouncement();
			userConstituencyScope = new UserConstituencyScope();
		}
		else if(announcementVO.getWindowTask().equalsIgnoreCase(IConstants.UPDATE_EXISTING))
		{
			announcement = announcementDAO.get(announcementVO.getAnnouncementId());
			userAnnouncement = userAnnouncementDAO.getUserAnnouncementByAnnouncementId(announcementVO.getAnnouncementId()).get(0);
			userConstituencyScope = userConstituencyScopeDAO.getUserConstituencyScopeByAnnouncementId(announcementVO.getAnnouncementId()).get(0);
		}
		announcement.setTitle(announcementVO.getTitle());
		announcement.setDiscription(announcementVO.getMessage());
		try
		{
			announcement.setFromDate(format.parse(announcementVO.getFromDate()));
			announcement.setToDate(format.parse(announcementVO.getToDate()));   
		}catch (Exception e){}		   
			
		announcement = announcementDAO.save(announcement);
				
		userAnnouncement.setAnnouncement(announcement);
		userAnnouncement.setUser(registrationDAO.get(announcementVO.getUserId()));
		userAnnouncement = userAnnouncementDAO.save(userAnnouncement);

		userConstituencyScope.setConstituency(constituencyDAO.get(announcementVO.getConstituency()));
		userConstituencyScope.setUserAnnouncement(userAnnouncement);
		userConstituencyScope = userConstituencyScopeDAO.save(userConstituencyScope);
		
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		
		}
		});
		return resultStatus;
	}
	catch(Exception e){
		log.error("Exception Occured in saving Announcement & Exception is -- "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	}
		
	}

	public List<AnnouncementResultsVO> getAllAnnouncementsInConstituency(Long constituencyId) 
	{
		Date date = null;
		Calendar currentDate = Calendar.getInstance();
		System.out.println(constituencyId);
		SimpleDateFormat formatter=  new SimpleDateFormat(IConstants.DATE_PATTERN);
		String dateNow = formatter.format(currentDate.getTime());
		System.out.println(dateNow);
		
		try {
			date = formatter.parse(dateNow);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List announcementsInConstituency = userConstituencyScopeDAO.findAnnouncementsByConstituencyId(constituencyId,date);
		
		List<AnnouncementResultsVO> announcementResult = new ArrayList<AnnouncementResultsVO>();
		if(announcementsInConstituency!= null && announcementsInConstituency.size()>0 ){
			Iterator i = announcementsInConstituency.iterator();
		while(i.hasNext()){
			AnnouncementResultsVO  announcementResultsVO  = new AnnouncementResultsVO();
			Object[] x = (Object[])i.next();
			announcementResultsVO.setTitle((String)x[0]);
			System.out.println((String)x[0]);
			announcementResultsVO.setDiscription((String)x[1]);
			announcementResultsVO.setFirstName((String)x[2]);
			announcementResultsVO.setMiddleName((String)x[3]);
			announcementResultsVO.setLastName((String)x[4]);
			
			announcementResult.add(announcementResultsVO);
		}
		}
		return announcementResult;
	}
	
	public List<AnnouncementResultsVO> getAllAnnouncementsByUserId(Long userId) {
		   
		List result = userAnnouncementDAO.findAnnouncementDetailsByUserId(userId);
		List<AnnouncementResultsVO> resultList = new ArrayList<AnnouncementResultsVO>();
		if(result != null && result.size()>0){
			Iterator i =result.iterator();
			while(i.hasNext()){
				AnnouncementResultsVO announcementResultsVO = new AnnouncementResultsVO();
				Object[] o =(Object[])i.next();
				announcementResultsVO.setAnnouncementsId((Long)(o[0]));
				announcementResultsVO.setTitle((String)o[1]);
				announcementResultsVO.setDiscription((String)o[2]);
				announcementResultsVO.setFromDate((Date)o[3]);
				announcementResultsVO.setToDate((Date)o[4]);
				resultList.add(announcementResultsVO);
			}
		}
			
		
		return resultList;
	}
	
	public void updateAnnouncementDetails(final AnnouncementResultsVO announcementResultsVO){
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try{
				Announcement announcement = null;
				System.out.println("start");
				announcement = announcementDAO.get(announcementResultsVO.getAnnouncementsId());
				announcement.setTitle(announcementResultsVO.getTitle());
				announcement.setDiscription(announcementResultsVO.getDiscription());
				announcement.setFromDate(announcementResultsVO.getFromDate());
				announcement.setToDate(announcementResultsVO.getToDate());
				announcement = announcementDAO.save(announcement);
				Set<UserAnnouncement> userAnnouncementset  = announcement.getUserAnnouncements();
				Iterator i = userAnnouncementset.iterator();
				UserAnnouncement userAnnouncement = null;
				while(i.hasNext()){
					 userAnnouncement = (UserAnnouncement)i.next();
					 userAnnouncement = userAnnouncementDAO.save(userAnnouncement);
				}
				if(userAnnouncement!= null){
				Set<UserConstituencyScope> userConstituencyScopeset = userAnnouncement.getUserConstituencyScope();
				Iterator j = userConstituencyScopeset.iterator();
				UserConstituencyScope userConstituencyScope = null;
				while(j.hasNext()){
					userConstituencyScope = (UserConstituencyScope)j.next();
				}
				if(userConstituencyScope!= null){
					
					userConstituencyScope.setConstituency(constituencyDAO.get(announcementResultsVO.getConstituencyId()));
					userConstituencyScopeDAO.save(userConstituencyScope);
				}
				
				}
				}catch(Exception e){
					
				 e.printStackTrace();
				}
			}
		});
		
	}

	public AnnouncementVO getAnnouncementDetailsByAnnouncementId(long announcementId)
	{
		try
		{
			log.debug("Enterred Into getAnnouncementDetailsByAnnouncementId() Method with announcementId -- "+ announcementId); 
			
			Announcement announcement   =  announcementDAO.get(announcementId);
		    AnnouncementVO announcementVO = new AnnouncementVO();
		    SimpleDateFormat formatter=  new SimpleDateFormat(IConstants.DATE_PATTERN);
		    
		    announcementVO.setAnnouncementId(announcement.getAnnouncementsId());
		    announcementVO.setTitle(announcement.getTitle());
		    announcementVO.setMessage(announcement.getDiscription());
		    announcementVO.setFromDate(formatter.format(announcement.getFromDate()));
		    announcementVO.setToDate(formatter.format(announcement.getToDate()));
		    
		    List<Object[]> constDetails = userConstituencyScopeDAO.getConstituencyId(announcementId);
		    if(constDetails != null && constDetails.size() > 0)
		    {
		    	announcementVO.setConstituency((Long)constDetails.get(0)[0]);
		    	if(constDetails.get(0)[2].toString().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
		    	{
		    		announcementVO.setState(constituencyDAO.getStateIdByConstituencyId((Long)constDetails.get(0)[0]).get(0));
		    	}
		    }
		    return announcementVO;
		}
		catch (Exception e) {
			log.error("Error Occured in getAnnouncementDetailsByAnnouncementId() Method with announcementId -- "+ announcementId);
			return null;
		}
	}
	
		
}
