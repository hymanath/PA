package com.itgrids.partyanalyst.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.itgrids.partyanalyst.dao.IAnnouncementDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserAnnouncementDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
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

	public AnnouncementVO saveAnnouncement(final AnnouncementVO announcementVO) 
	{
		final AnnouncementVO resultVO = new AnnouncementVO();
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
		resultVO.setResultStatus(resultStatus);
		resultVO.setType(announcementVO.getWindowTask());
		
		}
		});
		return resultVO;
	}
	catch(Exception e){
		log.error("Exception Occured in saving Announcement & Exception is -- "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		resultVO.setResultStatus(resultStatus);
		return resultVO;
	}
		
	}

	public List<AnnouncementVO> getAllAnnouncementsInConstituency(Long constituencyId) 
	{
		try
		{
			List<Object[]> result = userConstituencyScopeDAO.findAnnouncementsByConstituencyId(constituencyId,new Date());
			
			if(result != null && result.size() > 0)
			{
				List<AnnouncementVO> announcements = new ArrayList<AnnouncementVO>(0);
				AnnouncementVO announcementVO = null;
				for(Object[] param : result)
				{
					announcementVO = new AnnouncementVO();
					announcementVO.setTitle(param[0] != null ? param[0].toString() : "");
					announcementVO.setMessage(param[1] != null ? param[1].toString() : "");
					announcementVO.setFromDate(param[2] != null ? param[2].toString() : "");
					announcementVO.setUserId((Long)param[3]);
					announcementVO.setUserName(WordUtils.capitalize((param[4] != null ? param[4].toString().toLowerCase() : "") 
						+" "+(param[5] != null ? param[5].toString().toLowerCase() : "")));
					
					announcements.add(announcementVO);
				}
				
				return announcements;
			}
			return null;
		}catch (Exception e) {
			log.error("Error Ocuured and Error is - "+e);
			return null;
		}
	}
	
	public List<AnnouncementVO> getAllAnnouncementsByUserId(Long userId) {
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			List<AnnouncementVO> announcementsList = null; 
			
			List<Object[]> result = userConstituencyScopeDAO.getAnnouncementDetailsByUserId(userId);
			
			if(result != null && result.size() > 0)
			{
				announcementsList = new ArrayList<AnnouncementVO>(0);
				AnnouncementVO announcementVO = null;
				for(Object[] param : result)
				{
					announcementVO = new AnnouncementVO();
					
					announcementVO.setAnnouncementId((Long)param[0]);
					announcementVO.setTitle(param[1].toString());
					announcementVO.setMessage(param[2].toString());
					announcementVO.setFromDate(sdf.format((Date)param[3]));
					announcementVO.setToDate(sdf.format((Date)param[4]));
					announcementVO.setConstituency((Long)param[5]);
					announcementVO.setConstituencyName(param[6].toString());
					announcementVO.setType(param[7].toString());
					
					announcementsList.add(announcementVO);
				}
				return announcementsList;
			}
			else
				return null;
			
		}catch (Exception e) {
			return null;
		}
	}
	

	public AnnouncementVO getAnnouncementDetailsByAnnouncementId(long announcementId)
	{
		try
		{
			log.debug("Enterred Into getAnnouncementDetailsByAnnouncementId() Method with announcementId -- "+ announcementId); 
			
			Announcement announcement   =  announcementDAO.get(announcementId);
		    AnnouncementVO announcementVO = new AnnouncementVO();
		    SimpleDateFormat formatter=  new SimpleDateFormat(IConstants.DATE_PATTERN);
		    
		    announcementVO.setAnnouncementId(announcement.getAnnouncementId());
		    announcementVO.setTitle(announcement.getTitle());
		    announcementVO.setMessage(announcement.getDiscription());
		    announcementVO.setFromDate(formatter.format(announcement.getFromDate()));
		    announcementVO.setToDate(formatter.format(announcement.getToDate()));
		    
		    List<Object[]> constDetails = userConstituencyScopeDAO.getConstituencyId(announcementId);
		    if(constDetails != null && constDetails.size() > 0)
		    {
		    	announcementVO.setConstituency((Long)constDetails.get(0)[0]);
		    	announcementVO.setType(constDetails.get(0)[2].toString());
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
	
	
	public List<AnnouncementVO> searchAnnouncementDetailsByUserIdDateConstId(AnnouncementVO annVO){
	    List<AnnouncementVO> announcementList = null;
	    SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	    Date fromDate = null;
	    Date toDate = null;
	try{
		
		log.debug("Entered Into searchAnnouncementDetailsByDate() Method with UserId ,FromDate,Todate,ConstituencyId -- "+ annVO.getUserId()+" , "+annVO.getFromDate()+" , "+annVO.getToDate()+" , "+annVO.getConstituency());
		try
		{   if(annVO.getFromDate() != null && annVO.getFromDate().length()>0)
			    fromDate = sdf.parse(annVO.getFromDate());
		if(annVO.getToDate() != null && annVO.getToDate().length()>0)
			    toDate = sdf.parse(annVO.getToDate());   
		}catch (Exception e){log.error(" Exception is ",e); }	
		List<Object[]> result = userConstituencyScopeDAO.findAnnouncementDetailsByUserIdDateConstId(annVO.getUserId(),fromDate,toDate,annVO.getConstituency());
		if(result != null && result.size() > 0)
		{
			announcementList = new ArrayList<AnnouncementVO>(0);
			AnnouncementVO announcementVO = null;
			for(Object[] param : result)
			{
				announcementVO = new AnnouncementVO();
				
				announcementVO.setAnnouncementId((Long)param[0]);
				announcementVO.setTitle(param[1].toString());
				announcementVO.setMessage(param[2].toString());
				announcementVO.setFromDate(sdf.format((Date)param[3]));
				announcementVO.setToDate(sdf.format((Date)param[4]));
				announcementVO.setConstituency((Long)param[5]);
				announcementVO.setConstituencyName(param[6].toString());
				announcementVO.setType(param[7].toString());
				
				announcementList.add(announcementVO);
			}
			return announcementList;
		}
		else
			return null;
	}catch(Exception e){
		log.error("Error Occured in searchAnnouncementDetailsByDate() Method with UserId ,FromDate,Todate,ConstituencyId -- "+ annVO.getUserId()+" , "+annVO.getFromDate()+" , "+annVO.getToDate()+" , "+annVO.getConstituency());
		log.error("And Exception is ",e);
		return null;
	}
	
}
		
}
