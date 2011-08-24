package com.itgrids.partyanalyst.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnnouncementsDao;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserAnnouncementDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.dto.AnnouncementInfo;
import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Announcement;
import com.itgrids.partyanalyst.model.UserAnnouncement;
import com.itgrids.partyanalyst.model.UserConstituencyScope;
import com.itgrids.partyanalyst.service.IAnnocementsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnnouncementService implements IAnnocementsService {

	
	private Announcement announcements;
	private IAnnouncementsDao announcementsDao;
	private IUserAnnouncementDAO userAnnouncementDAO;
	private IUserConstituencyScopeDAO userConstituencyScopeDAO;
	private IRegistrationDAO registrationDAO;
	private IConstituencyDAO constituencyDAO;
	private TransactionTemplate transactionTemplate = null;
	public Date fromDate ;
	public Date toDate ;
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public IAnnouncementsDao getAnnouncementsDao() {
		return announcementsDao;
	}

	public void setAnnouncementsDao(IAnnouncementsDao announcementsDao) {
		this.announcementsDao = announcementsDao;
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

	@Override
	public void saveAnnouncement(final AnnouncementVO announcementVO,final RegistrationVO registrationVO) {
	try{
			
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				
			
		announcements = new Announcement();
		announcements.setTitle(announcementVO.getTitle());
		announcements.setDiscription(announcementVO.getMessage());
		SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				
		try {
			
			   if(announcementVO.getFromdate()!=null)
				   
			 fromDate = format.parse(announcementVO.getFromdate());
			  
			   if(announcementVO.getTodate()!=null)
			 toDate = format.parse(announcementVO.getTodate());
			   
		} catch (ParseException e) {
			e.printStackTrace();
		}
		announcements.setFromDate(fromDate);
		announcements.setToDate(toDate);
		announcements = announcementsDao.save(announcements);
		UserAnnouncement userAnnouncement = new UserAnnouncement();
		userAnnouncement.setAnnouncement(announcements);
		userAnnouncement.setUser(registrationDAO.get(registrationVO.getRegistrationID()));
		userAnnouncement = userAnnouncementDAO.save(userAnnouncement);

		UserConstituencyScope userConstituencyScope = new UserConstituencyScope();
		
		userConstituencyScope.setConstituency(constituencyDAO.get(announcementVO.getConstituencyId()));
		userConstituencyScope.setUsersAnnouncement(userAnnouncement);
		userConstituencyScope = userConstituencyScopeDAO.save(userConstituencyScope);
		}
		});
	}
	catch(Exception e){
		e.printStackTrace();
	}
		
	}

	@Override
	public List<AnnouncementResultsVO> getAllAnnouncementsInConstituency(Long constituencyId) {
		System.out.println(constituencyId);
		// TODO Auto-generated method stub	
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
				announcement = announcementsDao.get(announcementResultsVO.getAnnouncementsId());
				announcement.setTitle(announcementResultsVO.getTitle());
				announcement.setDiscription(announcementResultsVO.getDiscription());
				announcement.setFromDate(announcementResultsVO.getFromDate());
				announcement.setToDate(announcementResultsVO.getToDate());
				announcement = announcementsDao.save(announcement);
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
//	public void deleteAnnouncement(final long announcementId){
//		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//			public void doInTransactionWithoutResult(TransactionStatus status) {
//				Announcement announcement = announcementsDao.get(announcementId);
//				Set<UserAnnouncement> userAnnouncements =	announcement.getUserAnnouncements();
//				
//				Iterator i = userAnnouncements.iterator();
//				while(i.hasNext()){
//					UserAnnouncement annos =(UserAnnouncement) i.next();
//					Set<UserConstituencyScope> userConstituencyScope =	annos.getUserConstituencyScope();
//					Iterator j = userConstituencyScope.iterator();
//					while(j.hasNext()){
//						UserConstituencyScope cons =(UserConstituencyScope)j.next();
//					long userConstituencyScopeId =	cons.getUserConstituencyScopeId();
//					userConstituencyScopeDAO.remove(userConstituencyScopeId);
//						
//					}
//					long userAnnouncementsId = annos.getUserAnnouncementsId();
//					userAnnouncementDAO.remove(userAnnouncementsId);
//					
//				}
//				announcementsDao.remove(announcementId);
//				}
//			
//				
//			
//			
//		});
//		
//		
//		announcementsDao.deleteAnnouncement(announcementId);
//	}
	public AnnouncementInfo getAnnouncementDetailsByAnnouncementId(long announcementId){
		
		Announcement announcement   =  announcementsDao.get(announcementId);
		     AnnouncementInfo announcementInfo = new AnnouncementInfo();
		     announcementInfo.setAnnouncementId(announcement.getAnnouncementsId());
		     System.out.println(announcement.getAnnouncementsId());
		     announcementInfo.setTitle(announcement.getTitle());
		     System.out.println(announcement.getTitle());
		     announcementInfo.setMessage(announcement.getDiscription());
		     System.out.println(announcement.getDiscription());
		     SimpleDateFormat formatter=  new SimpleDateFormat(IConstants.DATE_PATTERN);
		     String fromdate = formatter.format(announcement.getFromDate());
		     String todate = formatter.format(announcement.getToDate());
		     announcementInfo.setFromdate(fromdate);
		     System.out.println(announcement.getFromDate());
		     announcementInfo.setTodate(todate);
		     System.out.println(announcement.getToDate());
		     return announcementInfo;
	}
	public List getAnnouncementDetails(long announcementId){
		return userConstituencyScopeDAO.getConstituencyId(announcementId);
	}
	
}
