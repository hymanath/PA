package com.itgrids.electoralconnect.service.impl;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.electoralconnect.dao.IAnnouncementFilesDAO;
import com.itgrids.electoralconnect.dao.IAnnouncementTypeDAO;
import com.itgrids.electoralconnect.dao.IAnnouncementsDAO;
import com.itgrids.electoralconnect.dao.IFileDAO;
import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dto.AnnouncementVO;
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
						
						announcementFiles.setAnnouncement(announcements);
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
		
}
