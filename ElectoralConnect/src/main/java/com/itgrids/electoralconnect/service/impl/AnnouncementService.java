package com.itgrids.electoralconnect.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.electoralconnect.dao.IAnnouncementFilesDAO;
import com.itgrids.electoralconnect.dao.IAnnouncementTypeDAO;
import com.itgrids.electoralconnect.dao.IAnnouncementsDAO;
import com.itgrids.electoralconnect.dao.ICommentDAO;
import com.itgrids.electoralconnect.dao.IFileDAO;
import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.dto.NotificationVO;
import com.itgrids.electoralconnect.dto.PressReleaseVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.model.AnnouncementFiles;
import com.itgrids.electoralconnect.model.Announcements;
import com.itgrids.electoralconnect.model.File;
import com.itgrids.electoralconnect.service.IAnnouncementService;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;

public class AnnouncementService implements IAnnouncementService{
		
		private IAnnouncementsDAO announcementsDAO;
		private IFileDAO fileDAO;
		private IAnnouncementFilesDAO announcementFilesDAO;
		private IUserDAO userDAO;
		private IAnnouncementTypeDAO announcementTypeDAO;
		private ICommentDAO commentDAO;
		
		private static final Logger LOG=Logger.getLogger(UserService.class);
		private TransactionTemplate transactionTemplate=null;
		private DateUtilService dateUtilService=new DateUtilService();
		
		public IFileDAO getFileDAO() {
			return fileDAO;
		}
		public void setFileDAO(IFileDAO fileDAO) {
			this.fileDAO = fileDAO;
		}
		
		public IAnnouncementFilesDAO getAnnouncementFilesDAO() {
			return announcementFilesDAO;
		}
		public void setAnnouncementFilesDAO(IAnnouncementFilesDAO announcementFilesDAO) {
			this.announcementFilesDAO = announcementFilesDAO;
		}
		public TransactionTemplate getTransactionTemplate() {
			return transactionTemplate;
		}
		public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
			this.transactionTemplate = transactionTemplate;
		}
		
		public IAnnouncementsDAO getAnnouncementsDAO() {
			return announcementsDAO;
		}
		public void setAnnouncementsDAO(IAnnouncementsDAO announcementsDAO) {
			this.announcementsDAO = announcementsDAO;
		}
		public IUserDAO getUserDAO() {
			return userDAO;
		}
		public void setUserDAO(IUserDAO userDAO) {
			this.userDAO = userDAO;
		}
		public IAnnouncementTypeDAO getAnnouncementTypeDAO() {
			return announcementTypeDAO;
		}
		public void setAnnouncementTypeDAO(IAnnouncementTypeDAO announcementTypeDAO) {
			this.announcementTypeDAO = announcementTypeDAO;
		}
		
		public ICommentDAO getCommentDAO() {
			return commentDAO;
		}
		public void setCommentDAO(ICommentDAO commentDAO) {
			this.commentDAO = commentDAO;
		}
		public ResultStatus uploadFile(final AnnouncementVO announcementVO,final RegistrationVO user){
			ResultStatus result=new ResultStatus();
			Log.debug("Entered into uploadFile of Announcement Service");
			
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						File file=new File();
						AnnouncementFiles announcementFiles=new AnnouncementFiles();
						Announcements announcements=new Announcements();
						
						file.setTitle(announcementVO.getFileTitle());
						file.setDescription(announcementVO.getFileDescription());
						file.setFilePath(announcementVO.getFilePath());
						file.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						file.setUpdatedBy(userDAO.get(user.getRegistrationID()));
						
						file=fileDAO.save(file);
						
						announcements.setTitle(announcementVO.getName());
						announcements.setDescription(announcementVO.getDescription());
						announcements.setDate(announcementVO.getUpdatedDate());
						announcements.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						announcements.setAnnouncementType(announcementTypeDAO.get(announcementVO.getAnnouncementType()));
						announcements.setUpdatedBy(userDAO.get(user.getRegistrationID()));
						
						announcements=announcementsDAO.save(announcements);
						
						announcementFiles.setAnnouncements(announcements);
						announcementFiles.setFile(file);
						announcementFiles=announcementFilesDAO.save(announcementFiles);
					}
				});
				result.setResultCode(ResultCodeMapper.SUCCESS);
				return result;
			} catch (Exception e) {
				Log.debug("Exception Raised in uploadFile of AnnouncementService"+e);
				result.setExceptionEncountered(e);
				result.setResultCode(ResultCodeMapper.FAILURE);
				return result;
			}
			
		}
		/**
		 * This Service is used to get Top 5 Notifications And Press Releases
		 * @return List<AnnouncementVO>
		 * @date 04-07-2013
		 */
		public List<AnnouncementVO> getTop5Announcements()
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into getTop5Announcements() method in AnnouncementService Service");
				List<NotificationVO> notificationList = null;
				List<PressReleaseVO> pressReleaseList = null;
				AnnouncementVO announcementVO = null;
	 			List<Object[]> notificationsList = commentDAO.getTop5Notifications(0, 5);
				if(notificationsList != null)
				{
					notificationList = new ArrayList<NotificationVO>();
					for (Object[] parms : notificationsList) {
						NotificationVO notificationVO = new NotificationVO();
						notificationVO.setCount((Long)parms[0]);
						Announcements announcements = (Announcements) parms[1];
						notificationVO.setId(announcements.getAnnouncementId());
						notificationVO.setTitle(announcements.getTitle());
						notificationVO.setDescription(announcements.getDescription());
						notificationVO.setDate(announcements.getDate());
						notificationList.add(notificationVO);
					}
				}
				List<Object[]> pressReleasesList = commentDAO.getTop5PressReleases(0, 10);
				if(pressReleasesList != null)
				{
					pressReleaseList = new ArrayList<PressReleaseVO>();
					for (Object[] parms : pressReleasesList) {
						PressReleaseVO pressReleaseVO = new PressReleaseVO();
						pressReleaseVO.setCount((Long)parms[0]);
						Announcements announcements = (Announcements) parms[1];
						pressReleaseVO.setId(announcements.getAnnouncementId());
						pressReleaseVO.setTitle(announcements.getTitle());
						pressReleaseVO.setDescription(announcements.getDescription());
						pressReleaseVO.setDate(announcements.getDate());
						pressReleaseList.add(pressReleaseVO);
					}
				}
				if(notificationList != null && notificationList.size() > 0 || pressReleaseList != null && pressReleaseList.size()> 0)
				{
					announcementVO = new AnnouncementVO();
					announcementVO.setNotificationsList(notificationList);
					announcementVO.setPressReleasesList(pressReleaseList);
					returnList.add(announcementVO);
				}
			} catch (Exception e) {
				LOG.error("Exception raised in getTop5Announcements() method in AnnouncementService Service",e);
			}
			
			
			return returnList;
		}
		
		/**
		 * This Service is used to get the announcement by announcement id
		 * @param Long announcementId
		 * @return List<AnnouncementVO>
		 * @date 04-07-2012
		 */
		public List<AnnouncementVO> getAnnouncementById(Long announcementId)
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into getAnnouncementById() method in AnnouncementService Service");
				List<Object[]> announcementsList = announcementFilesDAO.getAnnoncementById(announcementId);
				if(announcementsList != null && announcementsList.size() >0)
				{
					returnList = fillAnnouncementVO(announcementsList);
				}
			} catch (Exception e) {
				LOG.error("Exception raised in getAnnouncementById() method in AnnouncementService Service",e);
			}
			
			return returnList;
		}
		/**
		 * This Service is used to get all announcements 
		 * @return List<AnnouncementVO>
		 * @date 04-07-2013
		 */
		public List<AnnouncementVO> getAllAnnouncements()
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into getAnnouncementById() method in AnnouncementService Service");
				List<Object[]> announcementsList = announcementFilesDAO.getAllAnnoncement();
				if(announcementsList != null && announcementsList.size() >0)
				{
					returnList = fillAnnouncementVO(announcementsList);
				}
			} catch (Exception e) {
				LOG.error("Exception raised in getAllAnnouncements() method in AnnouncementService Service",e);
			}
			return returnList;
		}
		
		/**
		 * This Service is used to fill the AnnouncementVo
		 * @param List<Object[]> announcementsList
		 * @return List<AnnouncementVO>
		 * @date 04-07-2013
		 */
		public List<AnnouncementVO> fillAnnouncementVO(List<Object[]> announcementsList)
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into fillAnnouncementVO() method in AnnouncementService Service");
				if(announcementsList != null && announcementsList.size() >0)
				{
					Announcements announcements = new Announcements();
					File file = new File();
					for (Object[] parms : announcementsList) {
						AnnouncementVO announcementVO = new AnnouncementVO();
						announcements = (Announcements) parms[0];
						file          = (File) parms[1];
						announcementVO.setId(announcements.getAnnouncementId());
						announcementVO.setTitle(announcements.getTitle()!= null ? announcements.getTitle() : "");
						announcementVO.setDescription(announcements.getDescription() != null ? announcements.getDescription() : "");
						announcementVO.setDate(announcements.getDate());
						announcementVO.setFileDate(file.getUpdatedTime() != null ? file.getUpdatedTime().toString():"");
						announcementVO.setFileTitle(file.getTitle() != null ? file.getTitle() : "");
						announcementVO.setFileDescription(file.getDescription() != null ? file.getDescription() : "");
						announcementVO.setFilePath(file.getFilePath() != null ? file.getFilePath() : "");
						announcementVO.setName(announcements.getUpdatedBy().getUserProfile().getFirstName());
						announcementVO.setAnnouncementName(announcements.getAnnouncementType().getName());
						returnList.add(announcementVO);
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised in fillAnnouncementVO() method in AnnouncementService Service",e);
			}
			
			return returnList;
		}
}
