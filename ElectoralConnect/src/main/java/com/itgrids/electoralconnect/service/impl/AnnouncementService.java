package com.itgrids.electoralconnect.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.itgrids.electoralconnect.dto.ResultCodeMapper;

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
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
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
						File file = null;
						AnnouncementFiles announcementFiles = null;
						Announcements announcements = null;
						if(announcementVO.getFileId() != null && announcementVO.getFileId() > 0)
						{
							file = fileDAO.get(announcementVO.getFileId());
						}
						else
						{
							file = new File();
						}
						file.setTitle(removeSpecialCharsFromAString(announcementVO.getFileTitle()));
						file.setDescription(removeSpecialCharsFromAString(announcementVO.getFileDescription()));
						file.setFilePath(announcementVO.getFilePath());
						file.setFileName(announcementVO.getFileName());
						file.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						file.setUpdatedBy(userDAO.get(user.getRegistrationID()));
						
						file=fileDAO.save(file);
						if(announcementVO.getAnnouncementId() != null && announcementVO.getAnnouncementId() > 0)
						{
							announcements = announcementsDAO.get(announcementVO.getAnnouncementId());
						}
						else
						{
							announcements = new Announcements();
						}
						announcements.setTitle(removeSpecialCharsFromAString(announcementVO.getName()));
						announcements.setDescription(removeSpecialCharsFromAString(announcementVO.getDescription()));
						announcements.setDate(announcementVO.getUpdatedDate());
						announcements.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						announcements.setAnnouncementType(announcementTypeDAO.get(announcementVO.getAnnouncementType()));
						announcements.setUpdatedBy(userDAO.get(user.getRegistrationID()));
						announcements.setIsDeleted("NO");
						
						announcements=announcementsDAO.save(announcements);
						if(announcementVO.getAnnouncementFileId() != null && announcementVO.getAnnouncementFileId() > 0)
						{
							announcementFiles = announcementFilesDAO.get(announcementVO.getAnnouncementFileId());
						}
						else
						{
							announcementFiles = new AnnouncementFiles();
						}
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
		 * This Service is used to get All Announcemts
		 * @param int startRecord
		 * @param int maxRecord
		 * @param Long userId
		 * @return List<AnnouncementVO>
		 */
		public List<AnnouncementVO> getAllAnnouncementsForAdmin(int startRecord,int maxRecord,Long userId){
			Log.debug("Entered into getAllAnnouncementsForAdmin of Announcement Service");
			AnnouncementVO announcementVO=null;
			List<AnnouncementVO> announcementVOsList=new ArrayList<AnnouncementVO>();
			try{
				List<AnnouncementFiles> announcementFiles=announcementFilesDAO.getAllAnnouncements(startRecord, maxRecord);
				int allannouncementFilesCount=announcementFilesDAO.getAllAnnouncementsCountOfUser();
				announcementVOsList = fillAllAnnouncemntDataIntoVos(announcementFiles,allannouncementFilesCount);
			}catch (Exception e) {
				Log.debug("Exception Raised in into getAllAnnouncementsForAdmin of Announcement Service"+e);
			}
			
			return announcementVOsList;
		}
		/**
		 *  This Service is used to get All Announcements For Selected Dates
		 * @param String startDate
		 * @param String endDate
		 * @param int startIndex
		 * @param int maxIndex
		 * @return List<AnnouncementVO>
		 * @date 08-07-2013
		 */
		public List<AnnouncementVO> getAllAnnouncementsBtSelDates(String sdate,String eDate,int startIndex,int maxIndex)
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into getAllAnnouncementsBtSelDates() method in AnnouncementService Service");
				DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
				String startYear       = sdate.substring(0, 4);
				String startMonth      = sdate.substring(5, 7);
				String startDay        = sdate.substring(8, 10);
				String endYear         = eDate.substring(0, 4);
				String endMonth        = eDate.substring(5, 7);
				String endDay          = eDate.substring(8, 10);
				Date startDate         = dateFormate.parse(startYear +"-"+ startMonth +"-"+ startDay);
				Date endDate           = dateFormate.parse(endYear +"-"+ endMonth +"-"+ endDay);
				List<AnnouncementFiles> announcementFilsList = announcementFilesDAO.getAnnouncemetsBtSelDates(startDate, endDate, startIndex, maxIndex);
				int allannouncementFilesCount = announcementFilesDAO.getSelBtDatesAnnouncementsCountOfUser(startDate, endDate);
				returnList = fillAllAnnouncemntDataIntoVos(announcementFilsList,allannouncementFilesCount);
			} catch (Exception e) {
				LOG.error("Exception raised in getAllAnnouncementsBtSelDates() method in AnnouncementService Service",e);
			}
			return returnList;
		}
		/**
		 * This service is used to get All announcements For Selected type
		 * @param Long typeId
		 * @param int startIndex
		 * @param int maxIndex
		 * @return List<AnnouncementVO>
		 * @date 08-07-2013
		 */
		public List<AnnouncementVO> getAllAnnouncementsForSelType(Long typeId,int startIndex,int maxIndex)
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into getAllAnnouncementsForSelType() method in AnnouncementService Service");
				List<AnnouncementFiles> announcementFilsList = announcementFilesDAO.getAllAnnouncemetsForSelectedType(typeId, startIndex, maxIndex);
				Long allannouncementFilesCount = announcementFilesDAO.getCountForSelAnnouncemetType(typeId);
				returnList = fillAllAnnouncemntDataIntoVos(announcementFilsList,allannouncementFilesCount.intValue());
			} catch (Exception e) {
				LOG.error("Exception raised in getAllAnnouncementsForSelType() method in AnnouncementService Service",e);
			}
			return returnList;
		}
		/**
		 * This Service is used to fill the announcements data into single VO
		 * @param List<AnnouncementFiles> announcementsList
		 * @param int allannouncementFilesCount
		 * @return List<AnnouncementVO>
		 * @date 06-07-2013
		 */
		public List<AnnouncementVO> fillAllAnnouncemntDataIntoVos(List<AnnouncementFiles> announcementsList,int allannouncementFilesCount)
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			if(announcementsList != null && announcementsList.size() > 0)
			{
					for(AnnouncementFiles files:announcementsList){
						AnnouncementVO announcementVO=new AnnouncementVO();
						announcementVO.setAnnouncementFileId(files.getAnnouncementFilesId());
						announcementVO.setAnnouncementType(files.getAnnouncements().getAnnouncementType().getAnnouncementTypeId());
						announcementVO.setAnnouncementId(files.getAnnouncements().getAnnouncementId());
						announcementVO.setAnnouncementTypeName(files.getAnnouncements().getAnnouncementType().getName());
						announcementVO.setName(removeSpecialCharsFromAString(files.getAnnouncements().getTitle()));
						announcementVO.setDescription(removeSpecialCharsFromAString(files.getAnnouncements().getDescription()));
						//announcementVO.setUpdatedDate(files.getAnnouncements().getDate());
						String updatedBy=files.getAnnouncements().getUpdatedBy().getUserProfile().getFirstName()+""+
								files.getAnnouncements().getUpdatedBy().getUserProfile().getLastName();
						announcementVO.setUpdatedBy(updatedBy);
						
						DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
						String reportDate = df.format(files.getAnnouncements().getDate());
						announcementVO.setDateString(reportDate);
						announcementVO.setCommentsCount(commentDAO.getTotalCommentsCountByAnnouncementId(files.getAnnouncements().getAnnouncementId()));
						announcementVO.setAllAnnouncementsCount(allannouncementFilesCount);
						returnList.add(announcementVO);
					
				}
			}
			return returnList;
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
	 			List<Announcements> notificationsList = announcementsDAO.getTopAnnouncements(1l, 0, 5);
				if(notificationsList != null)
				{
					notificationList = new ArrayList<NotificationVO>();
					for (Announcements announcement : notificationsList) {
						NotificationVO notificationVO = new NotificationVO();
						notificationVO.setId(announcement.getAnnouncementId());
						notificationVO.setTitle(removeSpecialCharsFromAString(announcement.getTitle()));
						notificationVO.setDescription(removeSpecialCharsFromAString(announcement.getDescription()));
						notificationVO.setDate(dateFormate.format(announcement.getDate()));
						
						DateFormat df = new SimpleDateFormat("MMM dd yyyy");
						String reportDate = df.format(announcement.getDate());
						
						notificationVO.setDateString(reportDate);
						
						notificationVO.setCount(commentDAO.getTotalCommentsCountByAnnouncementId(announcement.getAnnouncementId()));
						notificationList.add(notificationVO);
					}
				}
				List<Announcements> pressReleasesList = announcementsDAO.getTopAnnouncements(2l, 0, 10);
				if(pressReleasesList != null)
				{
					pressReleaseList = new ArrayList<PressReleaseVO>();
					for (Announcements announcement : pressReleasesList) {
						PressReleaseVO pressReleaseVO = new PressReleaseVO();
						pressReleaseVO.setId(announcement.getAnnouncementId());
						pressReleaseVO.setTitle(removeSpecialCharsFromAString(announcement.getTitle()));
						pressReleaseVO.setDescription(removeSpecialCharsFromAString(announcement.getDescription()));
						pressReleaseVO.setDate(dateFormate.format(announcement.getDate()));
						DateFormat df = new SimpleDateFormat("MMM dd yyyy");
						String reportDate = df.format(announcement.getDate());
						
						pressReleaseVO.setDateString(reportDate);
						
						pressReleaseVO.setCount(commentDAO.getTotalCommentsCountByAnnouncementId(announcement.getAnnouncementId()));
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
		public List<AnnouncementVO> getAllAnnouncements(Long announcemetTypeId)
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into getAnnouncementById() method in AnnouncementService Service");
				List<Object[]> announcementsList = announcementFilesDAO.getAllAnnoncement(announcemetTypeId);
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
		 * This Service is used to get the Announcements by Announcement File Id
		 * @param Long announcemetFileId
		 * @return List<AnnouncementVO>
		 * @date 05-07-2013
		 */
		public List<AnnouncementVO> getAnnouncementByAnnouncementFileId(Long announcemetFileId)
		{
			List<AnnouncementVO> returnList = new ArrayList<AnnouncementVO>();
			try {
				LOG.debug("Enterd into getAnnouncementByAnnouncementFileId() method in AnnouncementService Service");
				List<Object[]> announcementsList = announcementFilesDAO.getAnnouncementsByAnnouncementFileId(announcemetFileId);
				if(announcementsList != null && announcementsList.size() >0)
				{
					returnList = fillAnnouncementVO(announcementsList);
				}
			} catch (Exception e) {
				LOG.error("Exception raised in getAnnouncementByAnnouncementFileId() method in AnnouncementService Service",e);
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
						Long announcementFileId = (Long)parms[0];
						announcements = (Announcements) parms[1];
						file          = (File) parms[2];
						announcementVO.setAnnouncementFileId(announcementFileId);
						announcementVO.setId(announcements.getAnnouncementId());
						announcementVO.setAnnouncementType(announcements.getAnnouncementType() != null ? announcements.getAnnouncementType().getAnnouncementTypeId():0l);
						announcementVO.setTitle(announcements.getTitle()!= null ? removeSpecialCharsFromAString(announcements.getTitle()) : "");
						announcementVO.setDescription(announcements.getDescription() != null ? removeSpecialCharsFromAString(announcements.getDescription()) : "");
						announcementVO.setDateString(dateFormate.format(announcements.getDate()));
						announcementVO.setFileDate(file.getUpdatedTime() != null ? dateFormate.format(file.getUpdatedTime()).toString():"");
						announcementVO.setFileTitle(file.getTitle() != null ? removeSpecialCharsFromAString(file.getTitle()) : "");
						announcementVO.setFileDescription(file.getDescription() != null ? removeSpecialCharsFromAString(file.getDescription()) : "");
						announcementVO.setFilePath(file.getFilePath() != null ? file.getFilePath() : "");
						announcementVO.setName(announcements.getUpdatedBy() != null ?announcements.getUpdatedBy().getUserProfile().getFirstName() :"");
						announcementVO.setAnnouncementName(announcements.getAnnouncementType() != null ?announcements.getAnnouncementType().getName() :"");
						announcementVO.setFileId(file.getFileId()!= null ?file.getFileId() :0l);
						announcementVO.setFileName(file.getFileName() != null ?file.getFileName() :"");
						returnList.add(announcementVO);
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised in fillAnnouncementVO() method in AnnouncementService Service",e);
			}
			
			return returnList;
		}
		
		/**
		 * This Service is used to delete the Selected Annoncement
		 * @param Long announcementid
		 * @return ResultStatus
		 * @dare 05-07-2013 
		 */
		public ResultStatus deleteSelctedAnnoncement(Long announcementid)
		{
			ResultStatus resultStatus = new ResultStatus();
			try {
				LOG.debug("Enterd into deleteSelctedAnnoncement() method in AnnouncementService Service");
				int status = announcementsDAO.deleteSelAnnouncement(announcementid);
				if(status == 1)
				{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
				else
				{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				}
			} catch (Exception e) {
				LOG.error("Exception raised in deleteSelctedAnnoncement() method in AnnouncementService Service",e);
			}
			
			return resultStatus;
		}
		/**
		 * This Service is used to remove the Special charactes in the Text
		 * @param String textValue
		 * @return  String
		 * @date 06-07-2013
		 */
		public static String removeSpecialCharsFromAString(String textValue){
			if(textValue != null){
				/*String[] j=IConstants.SPECIALCHARS;
				for(int i=0;i<j.length;i++){
					textValue=textValue.replace(j[i], "");
				}*/
				textValue = textValue.replaceAll("\uFFFD", "");
				textValue = textValue.replaceAll("&#65533;", "");
			}
			return textValue;
		}
}
