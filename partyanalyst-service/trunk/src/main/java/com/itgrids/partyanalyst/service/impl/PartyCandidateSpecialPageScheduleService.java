package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.itgrids.partyanalyst.dao.ICandidateSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IJobDAO;
import com.itgrids.partyanalyst.dao.IJobRunDetailsDAO;
import com.itgrids.partyanalyst.dao.IPartySubscriptionsDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.dto.DailyUpdatesVO;
import com.itgrids.partyanalyst.dto.EmailNotificationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.JobRunDetails;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsTemplateService;
import com.itgrids.partyanalyst.service.IPartyCandidateSpecialPageScheduleService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.IJobConstants;

public class PartyCandidateSpecialPageScheduleService implements
		IPartyCandidateSpecialPageScheduleService {
	
	private static final Logger log = Logger.getLogger(PartyCandidateSpecialPageScheduleService.class);
	private ICandidateSubscriptionsDAO candidateSubscriptionsDAO;
	
	private IPartySubscriptionsDAO partySubscriptionsDAO;
	
	private ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO;
	
	private IFileGallaryDAO fileGallaryDAO;
	
	private IMailService mailService;
	
	private IMailsTemplateService mailsTemplateService;
	
	private VelocityEngine velocityEngine;
	
	private IJobRunDetailsDAO jobRunDetailsDAO;
	
	private IJobDAO jobDAO;
	
	public ICandidateSubscriptionsDAO getCandidateSubscriptionsDAO() {
		return candidateSubscriptionsDAO;
	}
	public void setCandidateSubscriptionsDAO(
			ICandidateSubscriptionsDAO candidateSubscriptionsDAO) {
		this.candidateSubscriptionsDAO = candidateSubscriptionsDAO;
	}
	public IPartySubscriptionsDAO getPartySubscriptionsDAO() {
		return partySubscriptionsDAO;
	}
	public void setPartySubscriptionsDAO(
			IPartySubscriptionsDAO partySubscriptionsDAO) {
		this.partySubscriptionsDAO = partySubscriptionsDAO;
	}
	public ISpecialPageSubscriptionsDAO getSpecialPageSubscriptionsDAO() {
		return specialPageSubscriptionsDAO;
	}
	public void setSpecialPageSubscriptionsDAO(
			ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO) {
		this.specialPageSubscriptionsDAO = specialPageSubscriptionsDAO;
	}
	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}
	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}
	
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	
	public IMailsTemplateService getMailsTemplateService() {
		return mailsTemplateService;
	}
	public void setMailsTemplateService(IMailsTemplateService mailsTemplateService) {
		this.mailsTemplateService = mailsTemplateService;
	}
	
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
	
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public IJobRunDetailsDAO getJobRunDetailsDAO() {
		return jobRunDetailsDAO;
	}
	public void setJobRunDetailsDAO(IJobRunDetailsDAO jobRunDetailsDAO) {
		this.jobRunDetailsDAO = jobRunDetailsDAO;
	}
	
	public IJobDAO getJobDAO() {
		return jobDAO;
	}
	public void setJobDAO(IJobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}
	
	public void sendUpdates(){
		log.info("Enter into sendUpdates() for schedule jobs run ");
	   if(IConstants.DEFAULT_SCHEDULER_UPDATES_SEVER.equalsIgnoreCase("server")){
		DailyUpdatesVO dailyUpdatesVO = new DailyUpdatesVO();
		DateUtilService dateUtilService = new DateUtilService();
		Date startTime = null;
		List<Date> dates = null;
		try{
		 dates = jobRunDetailsDAO.getStartTime();
		}catch(Exception e){
			log.error("Exception rised in  sendUpdates() while executing jobRunDetailsDAO.getStartTime() ",e);
		}
		if(dates != null && !dates.isEmpty())
			startTime = dates.get(0);
		Date endTime = dateUtilService.getCurrentDateAndTime();
		if(startTime == null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endTime);
			calendar.add(Calendar.DATE, -1);
			startTime = calendar.getTime();
		}
		
		log.info("start time "+startTime);
		log.info("end time "+endTime);
		
		dailyUpdatesVO.setAll(true);
		dailyUpdatesVO.setFrom(startTime);
		dailyUpdatesVO.setTo(endTime);
		sendMailsToAllSubscriders(dailyUpdatesVO);
		JobRunDetails jobRunDetails = new JobRunDetails();
		jobRunDetails.setStartTime(endTime);
		jobRunDetails.setEndTime(dateUtilService.getCurrentDateAndTime());
		jobRunDetails.setJob(jobDAO.get(IJobConstants.PARTY_CANDIDATE_SPECIALPAGE_UPDATES));
		jobRunDetailsDAO.save(jobRunDetails);
		log.info(" sendUpdates() method execution completed  ");
	  }else{
		  System.out.println("job will not run in local host");
	  }
		return;
	}
	public void sendMailsToAllSubscriders(DailyUpdatesVO dailyUpdatesVO)
	{
		log.info("sendMailsToAllSubscriders() Execution started");
		Map<Long,EmailNotificationVO> allSubscribersData = new HashMap<Long,EmailNotificationVO>();
		try
		{
		//List<EmailDetailsVO> emailDetailsVOList = new ArrayList<EmailDetailsVO>();
		if(dailyUpdatesVO.isCandidateSelected() || dailyUpdatesVO.isAll())	
		 getDaillyUpdatesForCandidatePageSubscribers(dailyUpdatesVO.getFrom(),dailyUpdatesVO.getTo(),allSubscribersData,dailyUpdatesVO.getCandidateIdsList());
		if(dailyUpdatesVO.isPartySelected() || dailyUpdatesVO.isAll()) 
		 getDaillyUpdatesForPartyPageSubscribers(dailyUpdatesVO.getFrom(),dailyUpdatesVO.getTo(),allSubscribersData,dailyUpdatesVO.getPartyIdsList());
		if(dailyUpdatesVO.isSpecialPageSelected() || dailyUpdatesVO.isAll()) 
		 getDaillyUpdatesForSpecialPageSubscribers(dailyUpdatesVO.getFrom(),dailyUpdatesVO.getTo(),allSubscribersData,dailyUpdatesVO.getSpecialPageIdsList());
		sendAllMails(allSubscribersData,dailyUpdatesVO);
		log.info("sendMailsToAllSubscriders() Execution without exception");
		}
		
		catch(Exception e){
			log.error("Exception Rised in sendMailsToAllSubscriders : ", e);
		}
		log.info("sendMailsToAllSubscriders() Execution completed");
	}
	public void sendAllMails(Map<Long,EmailNotificationVO> allSubscribersData,DailyUpdatesVO dailyUpdatesVO){
		log.info("sendAllMails() Execution started");
		EmailNotificationVO emailNotificationVO = null;
		EmailNotificationVO updatesFrom = new EmailNotificationVO();
		Map<String,String> userDetails = new HashMap<String,String>();
		Set<Long> keys = allSubscribersData.keySet();
		for(Long key : keys){
			try{
			   if(allSubscribersData.get(key).getSubscriberEmail() != null && allSubscribersData.get(key).getSubscriberEmail().length() > 0)
			   {
				String emailString = prepareMail(allSubscribersData.get(key),updatesFrom);
				if(emailString != null && emailString.length() > 0){
				  emailNotificationVO = new EmailNotificationVO();
				  emailNotificationVO.setDescription(emailString);
				  emailNotificationVO.setSubscriberEmail(allSubscribersData.get(key).getSubscriberEmail());
				  String status = sendMail(emailNotificationVO);
				  if(status.equalsIgnoreCase("success")){
					  userDetails.put(allSubscribersData.get(key).getSubscriberEmail(), allSubscribersData.get(key).getSubscriberName());
				  }
				}
			   }
				
			}catch(Exception e){
				log.error("Exception rised in sendAllMails ",e);
			}
		}
		sendMailToAdmin(updatesFrom,userDetails,dailyUpdatesVO);
		//sendMailToMeForTesting(updatesFrom,userDetails,dailyUpdatesVO);
		log.info("sendAllMails() Execution completed");
	}
	
	private void sendMailToAdmin(final EmailNotificationVO updatesFrom,final Map<String,String> userDetails,final DailyUpdatesVO dailyUpdatesVO){
		
       try{
    	   log.info("sendMailToAdmin() Execution started");
			JavaMailSenderImpl javamailsender = new JavaMailSenderImpl();
			javamailsender.setSession(mailService.getSessionObject(IConstants.DEFAULT_MAIL_SERVER));
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
		         public void prepare(MimeMessage mimeMessage) throws Exception {
		            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		            message.setTo(IJobConstants.ADMIN_GROUP_IDS);
		            Map model = new HashMap();
		   		    model.put("updatesFrom", updatesFrom);
		   		    model.put("userDetails", userDetails);
		   		    model.put("dailyUpdatesVO", dailyUpdatesVO);
		            String text = VelocityEngineUtils.mergeTemplateIntoString(
		 	               velocityEngine,"dailyUpdatesAdminMail.vm", model);
		            message.setText(text, true);
		            message.setSubject("Status for sending mails to subscribed users about profile update");
		            message.setFrom(IConstants.FROMEMAILID);
		         }
		      };
		      javamailsender.send(preparator);  
		      log.info("sendMailToAdmin() Execution completed successfully");
	     }catch(Exception e){
		log.error("Exception Rised in sendMailToAdmin : ", e);
	}
	}
	
	private void sendMailToMeForTesting(final EmailNotificationVO updatesFrom,final Map<String,String> userDetails,final DailyUpdatesVO dailyUpdatesVO){
		
	       try{
	    	   log.info("sendMailToMeForTesting() Execution started");
				JavaMailSenderImpl javamailsender = new JavaMailSenderImpl();
				javamailsender.setSession(mailService.getSessionObject(IConstants.DEFAULT_MAIL_SERVER));
				MimeMessagePreparator preparator = new MimeMessagePreparator() {
			         public void prepare(MimeMessage mimeMessage) throws Exception {
			            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			            message.setTo("k.mahesh@itgrids.com");
			            Map model = new HashMap();
			   		    model.put("updatesFrom", updatesFrom);
			   		    model.put("userDetails", userDetails);
			   		    model.put("dailyUpdatesVO", dailyUpdatesVO);
			            String text = VelocityEngineUtils.mergeTemplateIntoString(
			 	               velocityEngine,"dailyUpdatesAdminMail.vm", model);
			            message.setText(text, true);
			            message.setSubject("Status for sending mails to subscribed users about profile update");
			            message.setFrom(IConstants.FROMEMAILID);
			         }
			      };
			      javamailsender.send(preparator);  
			      log.info("sendMailToMeForTesting() Execution  completed successfully");
		     }catch(Exception e){
			log.error("Exception Rised in sendMailToAdmin : ", e);
		}
		}
	
	private void getDaillyUpdatesForCandidatePageSubscribers(Date startDate,Date endDate,Map<Long,EmailNotificationVO> allSubscribersData,List<Long> ids)
	{log.info("getDaillyUpdatesForCandidatePageSubscribers() Execution started");
	/*Set<Long> usersIds = new HashSet<Long>();
		usersIds.add(1234l);
		usersIds.add(411l);
		//usersIds.add(411l);
		usersIds.add(487l);
		usersIds.add(754l);
		usersIds.add(771l);
		usersIds.add(958l);
		usersIds.add(2032l);
		usersIds.add(1201l);
		usersIds.add(2032l);*/
		Map<Long,EmailNotificationVO> candidateIdMap = new HashMap<Long,EmailNotificationVO>();
		Set<Long> candidateIds = new HashSet<Long>();
		EmailNotificationVO emailNotificationVO = null;
		EmailNotificationVO data = null;

		boolean jdbcExceptionOccured = false;
		try
		{
			 
			List<Object[]> subscriberDetailsList = new ArrayList<Object[]>();
			for(int count = 0 ; count<10 ; count++){
				jdbcExceptionOccured = false;
			 try{
				 //getting all candidate subscribed details
			  log.info("getting  subscriberDetailsList for candidates started ");
		       subscriberDetailsList = candidateSubscriptionsDAO.getAllSubscriberDetails();
		      log.info("getting  subscriberDetailsList for candidates executed successfully ");
			 }
			 catch(DataAccessResourceFailureException jdbcExe){
				 jdbcExceptionOccured = true;
				 log.error("DataAccessResourceFailureException occured while getting  subscriberDetailsList for candidates for "+count+" time : ", jdbcExe);
				 Thread.sleep(1000);
			 }
			 catch(JDBCConnectionException jdbcExe){
				 jdbcExceptionOccured = true;
				 log.error("JDBCConnectionException occured while getting  subscriberDetailsList for candidates for "+count+" time : ", jdbcExe);
				 Thread.sleep(1000);
			 }catch(Exception e){
				 log.error("Exception occured while getting  subscriberDetailsList for candidates : ", e);
			 }
			 if(!jdbcExceptionOccured){
				 break;
			 }
				 
			}
			if(subscriberDetailsList != null && subscriberDetailsList.size() > 0){
				log.info("Total subscriptions count for candidates : "+subscriberDetailsList.size());
			}else{
				log.info("Total subscriptions count for candidates : 0 ");
			}
		      for(Object[] subscriberDetail : subscriberDetailsList)
		       {
		    	 if((ids == null || ids.isEmpty() || ids.contains((Long)subscriberDetail[0]))){
		    	 // adding all candidates to set
			     candidateIds.add((Long)subscriberDetail[0]);
			     //for each candidate creating one EmailNotificationVO to set all updates related to him to this VO for ex: news , photos,videos updates
			     if(candidateIdMap.get((Long)subscriberDetail[0]) == null)
			       candidateIdMap.put((Long)subscriberDetail[0], new EmailNotificationVO());
			     
			   //for each user creating one EmailNotificationVO to set all updates related to candidate or special or party pages etc to this VO if subscribed
			     if(allSubscribersData.get((Long)subscriberDetail[2]) == null)
			      {
			    	 emailNotificationVO = new EmailNotificationVO();
			    	 //setting subscriber email to VO
			    	 if(subscriberDetail[1] != null)
			    	  emailNotificationVO.setSubscriberEmail(subscriberDetail[1].toString());
			    	 //setting subscriber name to VO
			    	 emailNotificationVO.setSubscriberName(subscriberDetail[3].toString()+" "+subscriberDetail[4].toString());
			    	 //setting candidate VO to user
			    	 emailNotificationVO.getCandidatePage().add(candidateIdMap.get((Long)subscriberDetail[0]));
			    	 allSubscribersData.put((Long)subscriberDetail[2],emailNotificationVO);
			      }
			     else
			     {
			    	 emailNotificationVO = allSubscribersData.get((Long)subscriberDetail[2]);
			    	 //setting candidate VO to user
			    	 emailNotificationVO.getCandidatePage().add(candidateIdMap.get((Long)subscriberDetail[0]));
			     }
		    	}
		       }
		      if(!candidateIds.isEmpty()){
		       
		    	  List<FileGallary> photosList = new ArrayList<FileGallary>();
		    	  List<FileGallary> newsList = new ArrayList<FileGallary>();
		    	  List<FileGallary> videosList = new ArrayList<FileGallary>();
		      for(int count = 0 ; count<10 ; count++){
				   jdbcExceptionOccured = false;	
			       try{
			    	 //getting all photo gallery updates for all candidates 
			    	   log.info("getting  photosList for Candidates started ");
			           photosList = fileGallaryDAO.getCandidateGallaryDetailsForSubscribers(startDate,endDate,candidateIds,"photos");
			           log.info("getting  photosList for Candidates executed successfully ");
			       }
			       catch(DataAccessResourceFailureException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("DataAccessResourceFailureException occured while getting  photosList for candidates for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
					 }
			       catch(JDBCConnectionException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("JDBCConnectionException occured while getting photosList for candidates for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
				   }
			       catch(Exception e){
					 log.error("Exception occured while getting  photosList for Candidates: ", e);
				   }
			       if(!jdbcExceptionOccured){
						 break;
					 }
		      }
		      for(int count = 0 ; count<10 ; count++){
				   jdbcExceptionOccured = false;	  
			       try{
			    	 //getting all news gallery updates for all candidates
			    	   log.info("getting  newsList for Candidates started ");
			            newsList = fileGallaryDAO.getCandidateGallaryDetailsForSubscribers(startDate,endDate,candidateIds,"news");
			           log.info("getting  newsList for Candidates executed successfully ");
			       }
			       catch(DataAccessResourceFailureException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("DataAccessResourceFailureException occured while getting  newsList for candidates for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
				   }
			        catch(JDBCConnectionException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("JDBCConnectionException occured while getting newsList for candidates for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
				   }catch(Exception e){
						 log.error("Exception occured while getting  newsList for Candidates: ", e);
				   }
			       if(!jdbcExceptionOccured){
						 break;
					}
		      }
		      for(int count = 0 ; count<10 ; count++){
				   jdbcExceptionOccured = false; 
			       try{
			    	 //getting all video gallery updates for all candidates 
			    	log.info("getting  videosList for Candidates started ");
			          videosList = fileGallaryDAO.getCandidateGallaryDetailsForSubscribers(startDate,endDate,candidateIds,"videos");
			        log.info("getting  videosList for Candidates executed successfully ");
			        }
			        catch(DataAccessResourceFailureException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("DataAccessResourceFailureException occured while getting videosList for candidates for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
					 }
			        catch(JDBCConnectionException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("JDBCConnectionException occured while getting videosList for candidates for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
				    }catch(Exception e){
						 log.error("Exception occured while getting  videosList for Candidates: ", e);
				    }
			        if(!jdbcExceptionOccured){
						 break;
					}
		      }
		      
		      if(photosList != null && photosList.size() > 0){
		    	  log.info("Total photosList count for candidates : "+photosList.size());
		      }else{
		    	  log.info("Total photosList count for candidates : 0 ");
		      }
		      
		      if(newsList != null && newsList.size() > 0){
		    	  log.info("Total newsList count for candidates : "+newsList.size());
		      }else{
		    	  log.info("Total newsList count for candidates : 0 ");
		      }
		      
		      if(videosList != null && videosList.size() > 0){
		    	  log.info("Total videosList count for candidates : "+videosList.size());
		      }else{
		    	  log.info("Total videosList count for candidates : 0 ");
		      }
		      
		      
		        Map<Long,Long> photoGallaryIds = new HashMap<Long,Long>();
		       Map<Long,Long> newsGallaryIds = new HashMap<Long,Long>();
		       Map<Long,Long> videoGallaryIds = new HashMap<Long,Long>();
		       Long gallaryId = null;
		      for(FileGallary photos : photosList)
		       {
		    	 try{
		    		 gallaryId = photos.getGallary().getGallaryId();
		    	 if(photoGallaryIds.get(gallaryId) == null){
		    		 photoGallaryIds.put(gallaryId, gallaryId);
			      emailNotificationVO = candidateIdMap.get(photos.getGallary().getCandidate().getCandidateId());
			      emailNotificationVO.setId(photos.getGallary().getCandidate().getCandidateId());
			      emailNotificationVO.setName(photos.getGallary().getCandidate().getLastname());
			      emailNotificationVO.setPhotopresent(true);
			       data = new EmailNotificationVO();
			     
			      convertFileGalleryToVo(photos,data);
			      emailNotificationVO.getPhotos().add(data);
		    	 }
		    	}catch(Exception e){
		    		log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers while iterating photos: ", e);
		    	}
		       }
		     for(FileGallary news : newsList)
		      {
		    	 try{
		    		 gallaryId = news.getGallary().getGallaryId();
			      if(newsGallaryIds.get(gallaryId) == null){
			    	  newsGallaryIds.put(gallaryId, gallaryId);
			        emailNotificationVO = candidateIdMap.get(news.getGallary().getCandidate().getCandidateId());
			        emailNotificationVO.setId(news.getGallary().getCandidate().getCandidateId());
			        emailNotificationVO.setName(news.getGallary().getCandidate().getLastname());
			        emailNotificationVO.setNewspresent(true);
			        data = new EmailNotificationVO();
			        //convertFileGalleryToVo(news,data);
			        convertFileGalleryToVoForNews((FileGallary)news,data,"candidate",news.getGallary().getCandidate().getCandidateId().toString(),"");
			        emailNotificationVO.getNews().add(data);
			      }
		    	 }catch(Exception e){
		    		 log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers  while iterating news: ", e);
			     }
		      }
		    for(FileGallary videos : videosList)
		     {
		    	try{
		    		gallaryId = videos.getGallary().getGallaryId();
				 if(videoGallaryIds.get(gallaryId) == null){
					 videoGallaryIds.put(gallaryId, gallaryId);
		    	     emailNotificationVO = candidateIdMap.get(videos.getGallary().getCandidate().getCandidateId());
		    	     emailNotificationVO.setId(videos.getGallary().getCandidate().getCandidateId());
		    	     emailNotificationVO.setName(videos.getGallary().getCandidate().getLastname());
		    	     emailNotificationVO.setVideopresent(true);
		    	     data = new EmailNotificationVO();
		    	     convertFileGalleryToVo(videos,data);
		    	     emailNotificationVO.getVideos().add(data);
				 }
		        }catch(Exception e){
		        	log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers  while iterating videos: ", e);
		    	}
		     }
		      Set<Long> keys = allSubscribersData.keySet();
		      for(Long key:keys){
		    	  EmailNotificationVO userVo = allSubscribersData.get(key);
		    	  for(EmailNotificationVO candidateVo : userVo.getCandidatePage()){
		    		  if(candidateVo.isNewspresent() || candidateVo.isPhotopresent() || candidateVo.isVideopresent()){
		    			  userVo.setCandidatePagepresent(true);
		    			  break;
		    		  }
		    	  }
		      }
		  }
		}
		catch(Exception e){
			log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers : ", e);
		}
		log.info("getDaillyUpdatesForCandidatePageSubscribers() Execution completed");
	}
	
	
	private void getDaillyUpdatesForPartyPageSubscribers(Date startDate,Date endDate,Map<Long,EmailNotificationVO> allSubscribersData,List<Long> ids)
	{log.info("getDaillyUpdatesForPartyPageSubscribers() Execution started");
	/*Set<Long> usersIds = new HashSet<Long>();
		usersIds.add(1234l);
		usersIds.add(411l);
		//usersIds.add(411l);
		usersIds.add(487l);
		usersIds.add(754l);
		usersIds.add(771l);
		usersIds.add(958l);
		usersIds.add(2032l);*/
		Map<Long,EmailNotificationVO> partyIdMap = new HashMap<Long,EmailNotificationVO>();
		Set<Long> partyIds = new HashSet<Long>();
		EmailNotificationVO emailNotificationVO = null;
		EmailNotificationVO data = null;
		boolean jdbcExceptionOccured = false;
		
		try
		   {
			
			List<Object[]> subscriberDetailsList = new ArrayList<Object[]>();
			for(int count = 0 ; count<10 ; count++){
				jdbcExceptionOccured = false;
				try{
					//getting all party page subscribed details
					log.info("getting  subscriberDetailsList for party  started ");
				     subscriberDetailsList = partySubscriptionsDAO.getAllSubscriberDetails();
				    log.info("getting  subscriberDetailsList for party executed successfully ");
			   }catch(DataAccessResourceFailureException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("DataAccessResourceFailureException occured while getting subscriberDetailsList for party for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			   }catch(JDBCConnectionException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("JDBCConnectionException occured while getting  subscriberDetailsList for party for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			   }catch(Exception e){
					 log.error("Exception occured while getting  subscriberDetailsList for party : ", e);
			   }
			   if(!jdbcExceptionOccured){
					 break;
			   }
			}
			
			if(subscriberDetailsList != null && subscriberDetailsList.size() > 0){
				log.info("Total subscriptions count for parties : "+subscriberDetailsList.size());
			}else{
				log.info("Total subscriptions count for parties : 0 ");
			}
			
			 for(Object[] subscriberDetail : subscriberDetailsList)
		       {
				if((ids == null || ids.isEmpty() || ids.contains((Long)subscriberDetail[0])) ){
		    	 // adding all parties to set
				 partyIds.add((Long)subscriberDetail[0]);
			     //for each party creating one EmailNotificationVO to set all updates related to party to this VO for ex: news , photos,videos updates
				 if(partyIdMap.get((Long)subscriberDetail[0]) == null)
				   partyIdMap.put((Long)subscriberDetail[0], new EmailNotificationVO());
			     
			     //for each subscriber creating one EmailNotificationVO to set all updates related to candidate or special or party pages etc to this VO if subscribed
			     if(allSubscribersData.get((Long)subscriberDetail[2]) == null)
			      {
			    	 emailNotificationVO = new EmailNotificationVO();
			    	 //setting subscriber email to VO
			    	 if(subscriberDetail[1] != null)
			    	  emailNotificationVO.setSubscriberEmail(subscriberDetail[1].toString());
			    	 //setting subscriber name to VO
			    	 emailNotificationVO.setSubscriberName(subscriberDetail[3].toString()+" "+subscriberDetail[4].toString());
			    	 //setting party VO to user
			    	 emailNotificationVO.getPartyPage().add(partyIdMap.get((Long)subscriberDetail[0]));
			    	 allSubscribersData.put((Long)subscriberDetail[2],emailNotificationVO);
			      }
			     else
			     {
			    	 emailNotificationVO = allSubscribersData.get((Long)subscriberDetail[2]);
			    	//setting party VO to user
			    	 emailNotificationVO.getPartyPage().add(partyIdMap.get((Long)subscriberDetail[0]));
			     }
				}
		       }
			 if(!partyIds.isEmpty()){
				 
				 List<Object[]> photosList = new ArrayList<Object[]>();
				 List<Object[]> newsList = new ArrayList<Object[]>();
				 List<Object[]> videosList = new ArrayList<Object[]>();
				 
				 for(int count = 0 ; count<10 ; count++){
					 jdbcExceptionOccured = false;
					 try{
					 //getting all photo gallery updates for all parties 
						 log.info("getting  photosList for party started ");
					      photosList = fileGallaryDAO.getPartyGallaryDetailsForSubscribers(startDate,endDate,partyIds,"photos");
					    log.info("getting  photosList for party executed successfully ");
					 }catch(DataAccessResourceFailureException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("DataAccessResourceFailureException occured while getting photosList for party for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
					 }catch(JDBCConnectionException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("JDBCConnectionException occured while getting photosList for party for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
				     }catch(Exception e){
						 log.error("Exception occured while getting  photosList for party : ", e);
					 }
					 if(!jdbcExceptionOccured){
						 break;
					 }
				 }
				 
				 for(int count = 0 ; count<10 ; count++){
					 jdbcExceptionOccured = false;
					 try{
						//getting all news gallery updates for all parties 
						 log.info("getting  newsList for party started ");
						 newsList = fileGallaryDAO.getPartyGallaryDetailsForSubscribers(startDate,endDate,partyIds,"news");
						 log.info("getting  newsList for party executed successfully ");
				     }catch(DataAccessResourceFailureException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("DataAccessResourceFailureException occured while getting newsList for party for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
					 }catch(JDBCConnectionException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("JDBCConnectionException occured while getting newsList for party for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
				     }catch(Exception e){
						 log.error("Exception occured while getting  newsList for party : ", e);
					 }
					 if(!jdbcExceptionOccured){
						 break;
					 }
				 }
				 
				 for(int count = 0 ; count<10 ; count++){
					 jdbcExceptionOccured = false; 
					 try{
						//getting all video gallery updates for all parties
						 log.info("getting  videosList for party started ");
						 videosList = fileGallaryDAO.getPartyGallaryDetailsForSubscribers(startDate,endDate,partyIds,"videos");
						 log.info("getting  videosList for party executed successfully ");
					 }catch(DataAccessResourceFailureException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("DataAccessResourceFailureException occured while getting videosList for party for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
					 }catch(JDBCConnectionException jdbcExe){
						 jdbcExceptionOccured = true;
						 log.error("JDBCConnectionException occured while getting videosList for party for "+count+" time : ", jdbcExe);
						 Thread.sleep(1000);
				     }catch(Exception e){
						 log.error("Exception occured while getting  videosList for party : ", e);
					 }
					 if(!jdbcExceptionOccured){
						 break;
					 }
				 }
				 
				 if(photosList != null && photosList.size() > 0){
			    	  log.info("Total photosList count for party : "+photosList.size());
			      }else{
			    	  log.info("Total photosList count for party : 0 ");
			      }
			      
			      if(newsList != null && newsList.size() > 0){
			    	  log.info("Total newsList count for party : "+newsList.size());
			      }else{
			    	  log.info("Total newsList count for party : 0 ");
			      }
			      
			      if(videosList != null && videosList.size() > 0){
			    	  log.info("Total videosList count for party : "+videosList.size());
			      }else{
			    	  log.info("Total videosList count for party : 0 ");
			      }
				 
			   Map<Long,Long> photoGallaryIds = new HashMap<Long,Long>();
		       Map<Long,Long> newsGallaryIds = new HashMap<Long,Long>();
		       Map<Long,Long> videoGallaryIds = new HashMap<Long,Long>();
		       Long gallaryId = null;
		       
			 for(Object[] photos : photosList)
			 {
			  try{
				 gallaryId = ((FileGallary)photos[0]).getGallary().getGallaryId();
			     if(photoGallaryIds.get(gallaryId) == null){
			       photoGallaryIds.put(gallaryId, gallaryId);
				   emailNotificationVO = partyIdMap.get((Long)photos[1]);
				   emailNotificationVO.setId((Long)photos[1]);
				   emailNotificationVO.setName(photos[2] != null?photos[2].toString()+" Party":"");
				   emailNotificationVO.setPhotopresent(true);
				   data = new EmailNotificationVO();
				   convertFileGalleryToVo((FileGallary)photos[0],data);
				   emailNotificationVO.getPhotos().add(data);
			     }
			  }catch(Exception e){
				  log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers  while iterating photos : ", e);
		       }
			 }
			 for(Object[] news : newsList)
			 {
			  try{
				  gallaryId = ((FileGallary)news[0]).getGallary().getGallaryId();
			      if(newsGallaryIds.get(gallaryId) == null){
			    	  newsGallaryIds.put(gallaryId, gallaryId);
				     emailNotificationVO = partyIdMap.get((Long)news[1]);
					 emailNotificationVO.setId((Long)news[1]);
					 emailNotificationVO.setName(news[2] != null?news[2].toString()+" Party":"");
					 emailNotificationVO.setNewspresent(true);
					 data = new EmailNotificationVO();
					 //convertFileGalleryToVo((FileGallary)news[0],data);
					 convertFileGalleryToVoForNews((FileGallary)news[0],data,"party","",news[3] != null?news[3].toString():"");
				     emailNotificationVO.getNews().add(data);
			      }
			  }catch(Exception e){
				  log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers  while iterating news : ", e);
		       }
			 }
			 for(Object[] videos : videosList)
			 {
			  try{
				  gallaryId = ((FileGallary)videos[0]).getGallary().getGallaryId();
				  if(videoGallaryIds.get(gallaryId) == null){
					 videoGallaryIds.put(gallaryId, gallaryId);
				     emailNotificationVO = partyIdMap.get((Long)videos[1]);
					 emailNotificationVO.setId((Long)videos[1]);
					 emailNotificationVO.setName(videos[2] != null?videos[2].toString()+" Party":"");
					 emailNotificationVO.setVideopresent(true);
					 data = new EmailNotificationVO();
					 convertFileGalleryToVo((FileGallary)videos[0],data);
			
				     emailNotificationVO.getVideos().add(data);
				  }
			  }catch(Exception e){
				  log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers  while iterating videos : ", e);
		       }
			 }
			 Set<Long> keys = allSubscribersData.keySet();
		      for(Long key:keys){
		    	  EmailNotificationVO userVo = allSubscribersData.get(key);
		    	  for(EmailNotificationVO partyVo : userVo.getPartyPage()){
		    		  if(partyVo.isNewspresent() || partyVo.isPhotopresent() || partyVo.isVideopresent()){
		    			  userVo.setPartyPagepresent(true);
		    			  break;
		    		  }
		    	  }
		      }
			}
		 }
		catch(Exception e){
			log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers : ", e);
		}
		log.info("getDaillyUpdatesForPartyPageSubscribers() Execution completed");
	}
	
	private void getDaillyUpdatesForSpecialPageSubscribers(Date startDate,Date endDate,Map<Long,EmailNotificationVO> allSubscribersData,List<Long> ids)
	{log.info("getDaillyUpdatesForSpecialPageSubscribers() Execution started");
	/*Set<Long> usersIds = new HashSet<Long>();
		usersIds.add(1234l);
		usersIds.add(411l);
		//usersIds.add(411l);
		usersIds.add(487l);
		usersIds.add(754l);
		usersIds.add(771l);
		usersIds.add(958l);
		usersIds.add(2032l);
		usersIds.add(2032l);*/
		Map<Long,EmailNotificationVO> specialPageIdMap = new HashMap<Long,EmailNotificationVO>();		
		Set<Long> specialPageIds = new HashSet<Long>();
		EmailNotificationVO emailNotificationVO = null;
		EmailNotificationVO data = null;
		boolean jdbcExceptionOccured = false;
		
		try
		   {
			List<Object[]> subscriberDetailsList = new ArrayList<Object[]>();
			for(int count = 0 ; count<10 ; count++){
				jdbcExceptionOccured = false; 
				try{
				 //getting all special page subscribed details
					log.error("getting subscriberDetailsList for specialPage started ");
				     subscriberDetailsList = specialPageSubscriptionsDAO.getAllSubscriberDetails();
				    log.error("getting subscriberDetailsList for specialPage executed successfully ");
			    }catch(DataAccessResourceFailureException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("DataAccessResourceFailureException occured while getting subscriberDetailsList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			    }catch(JDBCConnectionException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("JDBCConnectionException occured while getting  subscriberDetailsList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
				}catch(Exception e){
					 log.error("Exception occured while getting  subscriberDetailsList for specialPage : ", e);
				}
				if(!jdbcExceptionOccured){
					 break;
				}
			}
			
			if(subscriberDetailsList != null && subscriberDetailsList.size() > 0){
				log.info("Total subscriptions count for specialPages : "+subscriberDetailsList.size());
			}else{
				log.info("Total subscriptions count for specialPages : 0 ");
			}
			
			for(Object[] subscriberDetail : subscriberDetailsList)
		       {
				if((ids == null || ids.isEmpty() || ids.contains((Long)subscriberDetail[0])) ){
		    	 // adding all special pages to set
				 specialPageIds.add((Long)subscriberDetail[0]);
			     //for each special page creating one EmailNotificationVO to set all updates related to special page to this VO for ex: news , photos,videos updates
				 if(specialPageIdMap.get((Long)subscriberDetail[0]) == null)
				   specialPageIdMap.put((Long)subscriberDetail[0], new EmailNotificationVO());
			     
			   //for each subscriber creating one EmailNotificationVO to set all updates related to candidate or special or party pages etc to this VO if subscribed
			     if(allSubscribersData.get((Long)subscriberDetail[2]) == null)
			      {
			    	 emailNotificationVO = new EmailNotificationVO();
			    	 //setting subscriber email to VO
			    	 if(subscriberDetail[1] != null)
			    	  emailNotificationVO.setSubscriberEmail(subscriberDetail[1].toString());
			    	 //setting subscriber name to VO
			    	 emailNotificationVO.setSubscriberName(subscriberDetail[3].toString()+" "+subscriberDetail[4].toString());
			    	 //setting special page VO to user
			    	 emailNotificationVO.getSpecialPage().add(specialPageIdMap.get((Long)subscriberDetail[0]));
			    	 allSubscribersData.put((Long)subscriberDetail[2],emailNotificationVO);
			      }
			     else
			     {
			    	 emailNotificationVO = allSubscribersData.get((Long)subscriberDetail[2]);
			    	//setting special page VO to user
			    	 emailNotificationVO.getSpecialPage().add(specialPageIdMap.get((Long)subscriberDetail[0]));
			     }
				}
		       }
			
		  if(!specialPageIds.isEmpty()){
			  List<Object[]> photosList = new ArrayList<Object[]>();
			  List<Object[]> newsList = new ArrayList<Object[]>();
			  List<Object[]> videosList = new ArrayList<Object[]>();
			  
			  for(int count = 0 ; count<10 ; count++){
				 jdbcExceptionOccured = false;	 
				 try{
					 log.error(" getting  photosList for specialPage started");
				       photosList = fileGallaryDAO.getSpecialPageGallaryDetailsForSubscribers(startDate,endDate,specialPageIds,"photos");
				      log.error(" getting  photosList for specialPage executed successfully");
			     }catch(DataAccessResourceFailureException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("DataAccessResourceFailureException occured while getting photosList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			     }catch(JDBCConnectionException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("JDBCConnectionException occured while getting photosList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			     }catch(Exception e){
					 log.error("Exception occured while getting  photosList for specialPage : ", e);
				 }
				 if(!jdbcExceptionOccured){
					 break;
				 }
			  }
			  
			  for(int count = 0 ; count<10 ; count++){
				 jdbcExceptionOccured = false;	  
				 try{
					 log.error(" getting  newsList for specialPage started");
				      newsList = fileGallaryDAO.getSpecialPageGallaryDetailsForSubscribers(startDate,endDate,specialPageIds,"news");
				     log.error(" getting  newsList for specialPage executed successfully");
			     }catch(DataAccessResourceFailureException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("DataAccessResourceFailureException occured while getting newsList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			     }catch(JDBCConnectionException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("JDBCConnectionException occured while getting newsList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			     }catch(Exception e){
					 log.error("Exception occured while getting  newsList for specialPage : ", e);
				 }
				 if(!jdbcExceptionOccured){
					 break;
				 }
			  }
			  
			  for(int count = 0 ; count<10 ; count++){
				 jdbcExceptionOccured = false;	
				 try{
					 log.error(" getting  videosList for specialPage started");
				      videosList = fileGallaryDAO.getSpecialPageGallaryDetailsForSubscribers(startDate,endDate,specialPageIds,"videos");
				     log.error(" getting  videosList for specialPage executed successfully");
				 }catch(DataAccessResourceFailureException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("DataAccessResourceFailureException occured while getting videosList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			     }catch(JDBCConnectionException jdbcExe){
					 jdbcExceptionOccured = true;
					 log.error("JDBCConnectionException occured while getting videosList for specialPage for "+count+" time : ", jdbcExe);
					 Thread.sleep(1000);
			     }catch(Exception e){
					 log.error("Exception occured while getting  videosList for specialPage : ", e);
				 }
				 if(!jdbcExceptionOccured){
					 break;
				 }
			  }
			  
			  if(photosList != null && photosList.size() > 0){
		    	  log.info("Total photosList count for specialPage : "+photosList.size());
		      }else{
		    	  log.info("Total photosList count for specialPage : 0 ");
		      }
		      
		      if(newsList != null && newsList.size() > 0){
		    	  log.info("Total newsList count for specialPage : "+newsList.size());
		      }else{
		    	  log.info("Total newsList count for specialPage : 0 ");
		      }
		      
		      if(videosList != null && videosList.size() > 0){
		    	  log.info("Total videosList count for specialPage : "+videosList.size());
		      }else{
		    	  log.info("Total videosList count for specialPage : 0 ");
		      }
			  
			  
			Map<Long,Long> photoGallaryIds = new HashMap<Long,Long>();
		       Map<Long,Long> newsGallaryIds = new HashMap<Long,Long>();
		       Map<Long,Long> videoGallaryIds = new HashMap<Long,Long>();
		       Long gallaryId = null;
			for(Object[] photos : photosList)
			{
			 try{
				 gallaryId = ((FileGallary)photos[0]).getGallary().getGallaryId();
			     if(photoGallaryIds.get(gallaryId) == null){
			       photoGallaryIds.put(gallaryId, gallaryId);
				   emailNotificationVO = specialPageIdMap.get((Long)photos[1]);
				   emailNotificationVO.setId((Long)photos[1]);
				   emailNotificationVO.setName(photos[2] != null?photos[2].toString():"");
				   emailNotificationVO.setPhotopresent(true);
				   data = new EmailNotificationVO();
				   convertFileGalleryToVo((FileGallary)photos[0],data);
			
				   emailNotificationVO.getPhotos().add(data);
			     }
			 }catch(Exception e){
				 log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers  while iterating photos : ", e);
		       }
			}
			for(Object[] news : newsList)
			{
			 try{
				 gallaryId = ((FileGallary)news[0]).getGallary().getGallaryId();
			     if(newsGallaryIds.get(gallaryId) == null){
			    	 newsGallaryIds.put(gallaryId, gallaryId);
				     emailNotificationVO = specialPageIdMap.get((Long)news[1]);
				     emailNotificationVO.setId((Long)news[1]);
				     emailNotificationVO.setName(news[2] != null?news[2].toString():"");
				     emailNotificationVO.setNewspresent(true);
				     data = new EmailNotificationVO();
				     //convertFileGalleryToVo((FileGallary)news[0],data);
				     convertFileGalleryToVoForNews((FileGallary)news[0],data,"specialpage",news[2] != null?news[2].toString():"",news[3] != null?news[3].toString():"");
				 
				     emailNotificationVO.getNews().add(data);
			     }
			 }catch(Exception e){
				 log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers  while iterating news : ", e);
		       }
			}
			for(Object[] videos : videosList)
			{
			 try{
			   gallaryId = ((FileGallary)videos[0]).getGallary().getGallaryId();
			   if(videoGallaryIds.get(gallaryId) == null){
				 videoGallaryIds.put(gallaryId, gallaryId);
				 emailNotificationVO = specialPageIdMap.get((Long)videos[1]);
				 emailNotificationVO.setId((Long)videos[1]);
				 emailNotificationVO.setName(videos[2] != null?videos[2].toString():"");
				 emailNotificationVO.setVideopresent(true);
				 data = new EmailNotificationVO();
				 convertFileGalleryToVo((FileGallary)videos[0],data);
			
				 emailNotificationVO.getVideos().add(data);
			   }
			 }catch(Exception e){
				 log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers  while iterating videos : ", e);		       }	
			}
			Set<Long> keys = allSubscribersData.keySet();
		      for(Long key:keys){
		    	  EmailNotificationVO userVo = allSubscribersData.get(key);
		    	  for(EmailNotificationVO specialPageVo : userVo.getSpecialPage()){
		    		  if(specialPageVo.isNewspresent() || specialPageVo.isPhotopresent() || specialPageVo.isVideopresent()){
		    			  userVo.setSpecialPagepresent(true);
		    			  break;
		    		  }
		    	  }
		      }
		  }
		   }
		catch(Exception e){
			log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers : ", e);   
		}
		log.info("getDaillyUpdatesForSpecialPageSubscribers() Execution completed");
	}
	/*private void getAllMailingDetails(List<EmailDetailsVO> emailDetailsVOList,Map<String,List<EmailNotificationVO>> emailMap,String type)
	{
	 try
	 {
		Set<String> mapKeys = emailMap.keySet();
		for(String key : mapKeys)
		{
			int count = 0;
			StringBuilder content = new StringBuilder();
			List<EmailNotificationVO> emailNotificationVOList = emailMap.get(key);
			if(emailNotificationVOList == null || emailNotificationVOList.size() == 0)
				continue;
			content.append("<div style='border:1px solid #CCCCCC;background:#EFFFFF;padding:10px;'>"+mailsTemplateService.getHeader()+" ");
			for(EmailNotificationVO emailNotificationVO : emailNotificationVOList)
			{
				if(emailNotificationVO.isPhotopresent()  || emailNotificationVO.isVideopresent()  || emailNotificationVO.isNewspresent())
				{
					count = count+1;
					content.append("<div style='background-color:#EEEEEE;border-radius:10px;padding:15px;margin-top:10px;'>");
					content.append("<div style='text-align:center;font-weight:bold;color:green' >Updates For "+emailNotificationVO.getName()+"</div>");
					
					if(emailNotificationVO.isNewspresent()){
						content.append("<div style='margin-top:10px;font-weight:bold;color:#FF4500'>News Updates</div>");
						for(EmailNotificationVO newsData : emailNotificationVO.getNews()){
							
							if(type.trim().equalsIgnoreCase("candidate"))
							{	
							  content.append("<div style='margin-top:5px;'><a href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+emailNotificationVO.getId()+"&contentId="+newsData.getFileGallaryId()+"'>"+newsData.getTitle()+"</a></div>" +
							  		"<div style='margin-top:3px;'>"+newsData.getDescription()+"</div>" +
							  		"<div style='margin-top:3px;'><span><b>Source : </b>"+newsData.getSource()+" </span>&nbsp;<span><b>Language : </b>"+newsData.getLanguage()+" </span></div>");
							}
							else if(type.trim().equalsIgnoreCase("party"))
							{	
								  content.append("<div style='margin-top:5px;'><span><a href='http://www.partyanalyst.com/partyPageAction.action?partyId="+emailNotificationVO.getId()+"&contentId="+newsData.getFileGallaryId()+"'>"+newsData.getTitle()+"</a></div>" +
										  "<div style='margin-top:3px;'>"+newsData.getDescription()+"</div>" +
									  	  "<div style='margin-top:3px;'><span><b>Source : </b>"+newsData.getSource()+" </span>&nbsp;<span><b>Language : </b>"+newsData.getLanguage()+" </span></div>");
							}
							
							else if(type.trim().equalsIgnoreCase("specialpage"))
							{	
								  content.append("<div style='margin-top:5px;'><span><a href='http://www.partyanalyst.com/specialPageAction.action?specialPageId="+emailNotificationVO.getId()+"&contentId="+newsData.getFileGallaryId()+"'>"+newsData.getTitle()+"</a></div>" +
										  "<div style='margin-top:3px;'>"+newsData.getDescription()+"</div>" +
									  	  "<div style='margin-top:3px;'><span><b>Source : </b>"+newsData.getSource()+" </span>&nbsp;<span><b>Language : </b>"+newsData.getLanguage()+" </span></div>");
							}
						}
					}
                    if(emailNotificationVO.isPhotopresent()){
                    	content.append("<div style='margin-top:10px;font-weight:bold;color:#FF4500'>Photos Updates</div>");
						for(EmailNotificationVO photoData : emailNotificationVO.getPhotos()){
							
							if(type.trim().equalsIgnoreCase("candidate"))
							{	
							  content.append("<div style='margin-top:5px;'><span><a href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+emailNotificationVO.getId()+"&contentId="+photoData.getFileGallaryId()+"'>"+photoData.getTitle()+"</a></span></div>" +
							  		"<div style='margin-top:3px;'>"+photoData.getDescription()+"</div>");
							}
							else if(type.trim().equalsIgnoreCase("party"))
							{	
								  content.append("<div style='margin-top:5px;'><span><a href='http://www.partyanalyst.com/partyPageAction.action?partyId="+emailNotificationVO.getId()+"&contentId="+photoData.getFileGallaryId()+"'>"+photoData.getTitle()+"</a></span></div>" +
								  		"<div style='margin-top:3px;'>"+photoData.getDescription()+"</div>");							}
							
							else if(type.trim().equalsIgnoreCase("specialpage"))
							{	
								  content.append("<div style='margin-top:5px;'><span><a href='http://www.partyanalyst.com/specialPageAction.action?specialPageId="+emailNotificationVO.getId()+"&contentId="+photoData.getFileGallaryId()+"'>"+photoData.getTitle()+"</a></span></div>" +
								  		"<div style='margin-top:3px;'>"+photoData.getDescription()+"</div>");
							}
						}
					}
                    if(emailNotificationVO.isVideopresent()){
                    	content.append("<div style='margin-top:10px;font-weight:bold;color:#FF4500'>Videos Updates</div>");
						for(EmailNotificationVO videoData : emailNotificationVO.getVideos()){
							
							if(type.trim().equalsIgnoreCase("candidate"))
							{	
							  content.append("<div style='margin-top:5px;'><a href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+emailNotificationVO.getId()+"&contentId="+videoData.getFileGallaryId()+"'>"+videoData.getTitle()+"</a></div>" +
							  		"<div style='margin-top:3px;'>"+videoData.getDescription()+"</div>" +
							  		"<div style='margin-top:3px;'><span><b>Source : </b>"+videoData.getSource()+" </span>&nbsp;<span><b>Language : </b>"+videoData.getLanguage()+" </span></div>");
							}
							else if(type.trim().equalsIgnoreCase("party"))
							{	
								  content.append("<div style='margin-top:5px;'><span><a href='http://www.partyanalyst.com/partyPageAction.action?partyId="+emailNotificationVO.getId()+"&contentId="+videoData.getFileGallaryId()+"'>"+videoData.getTitle()+"</a></div>" +
										  "<div style='margin-top:3px;'>"+videoData.getDescription()+"</div>" +
									  	  "<div style='margin-top:3px;'><span><b>Source : </b>"+videoData.getSource()+" </span>&nbsp;<span><b>Language : </b>"+videoData.getLanguage()+" </span></div>");
							}
							
							else if(type.trim().equalsIgnoreCase("specialpage"))
							{	
								  content.append("<div style='margin-top:5px;'><span><a href='http://www.partyanalyst.com/specialPageAction.action?specialPageId="+emailNotificationVO.getId()+"&contentId="+videoData.getFileGallaryId()+"'>"+videoData.getTitle()+"</a></div>" +
										  "<div style='margin-top:3px;'>"+videoData.getDescription()+"</div>" +
									  	  "<div style='margin-top:3px;'><span><b>Source : </b>"+videoData.getSource()+" </span>&nbsp;<span><b>Language : </b>"+videoData.getLanguage()+" </span></div>");
							}
						}
					}
                    content.append("</div>");
                    
                    
				}
			}
			content.append("</div>");
			if(count > 0){
			   EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
			   if(type.trim().equalsIgnoreCase("candidate"))
				 emailDetailsVO.setSubject("Politician Updates From PartyAnalyst");
			   else if(type.trim().equalsIgnoreCase("party"))
				 emailDetailsVO.setSubject("Political Party Updates From PartyAnalyst");		
			   else if(type.trim().equalsIgnoreCase("specialpage"))
                 emailDetailsVO.setSubject("Updates From PartyAnalyst");
              emailDetailsVO.setToAddress(key);
              emailDetailsVO.setContent(content.toString());
            
              emailDetailsVOList.add(emailDetailsVO);
			}
		}
	 }catch(Exception e)
	 {
		 log.error("Exception Rised in getAllMailingDetails : ", e);
	 }
	}*/
	
	public void  convertFileGalleryToVo(FileGallary result,EmailNotificationVO data){
		
		 data.setFileGallaryId(result.getFileGallaryId());
	     data.setGallaryName(result.getGallary().getName());
	     data.setTitle(result.getFile().getFileTitle());
	     data.setDescription(result.getFile().getFileDescription());
		 Set<FileSourceLanguage> fileSourceLanguageSet = result.getFile().getFileSourceLanguage();
		 String filePath = null;
		 StringBuilder source = new StringBuilder();
	     StringBuilder language = new StringBuilder();
		 List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
		 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
		 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList)
		 {
			 if(fileSourceLanguage.getSource()!= null)
				 source.append(fileSourceLanguage.getSource().getSource()+", ");
			 if(fileSourceLanguage.getLanguage()!= null)
				 language.append(fileSourceLanguage.getLanguage().getLanguage()+", ");
			 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
			 List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
			  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
			 if(filePath == null){
			     for(FilePaths singleFilePath : filePathsList)
			     {
			    	 if(filePath == null)
				      filePath = singleFilePath.getFilePath(); 
			    	 else
			    	  break;
			     }
			  }
		 }
		 if(source.length()>2)
		  data.setSource(source.toString().substring(0,source.toString().length() - 2));
		 if(language.length()>2)
	      data.setLanguage(language.toString().substring(0,language.toString().length() - 2));
	     data.setFilePath(filePath.trim());
		
	}
	public void  convertFileGalleryToVoForNews(FileGallary result,EmailNotificationVO data,String type,String name,String path){
		
		 data.setFileGallaryId(result.getFileGallaryId());
	     data.setGallaryName(result.getGallary().getName());
	     data.setTitle(result.getFile().getFileTitle());
	     data.setDescription(result.getFile().getFileDescription());
	     File file = result.getFile();
		 Set<FileSourceLanguage> fileSourceLanguageSet = result.getFile().getFileSourceLanguage();
		 StringBuilder source = new StringBuilder();
	     StringBuilder language = new StringBuilder();
		 List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
		 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
		 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList)
		 {
			 if(fileSourceLanguage.getSource()!= null)
				 source.append(fileSourceLanguage.getSource().getSource()+", ");
			 if(fileSourceLanguage.getLanguage()!= null)
				 language.append(fileSourceLanguage.getLanguage().getLanguage()+", ");
		 }
		 if(source.length()>2)
		  data.setSource(source.toString().substring(0,source.toString().length() - 2));
		 if(language.length()>2)
	      data.setLanguage(language.toString().substring(0,language.toString().length() - 2));
		 if(file.getFilePath() != null)
	       data.setFilePath(file.getFilePath().trim());
		 else{
			 if(type.equalsIgnoreCase("candidate")){
				 data.setFilePath("images/candidates/"+name.toUpperCase().trim()+".jpg");
			 }
			 else if(type.equalsIgnoreCase("party")){
				 data.setFilePath("images/party_flags/"+path.trim());
			 }
			 else if(type.equalsIgnoreCase("specialpage")){
				 data.setFilePath(""+path.trim());
			 }
		 }
		
	}
	public String sendMail(final EmailNotificationVO emailNotificationVO){
		
		try{
			
			JavaMailSenderImpl javamailsender = new JavaMailSenderImpl();
			javamailsender.setSession(mailService.getSessionObject(IConstants.DEFAULT_MAIL_SERVER));
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
		         public void prepare(MimeMessage mimeMessage) throws Exception {
		            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		            message.setTo(emailNotificationVO.getSubscriberEmail());
		            Map model = new HashMap();
		   		    model.put("user", emailNotificationVO);
		            String text = VelocityEngineUtils.mergeTemplateIntoString(
		 	               velocityEngine,"dailyUpdates.vm", model);
		            message.setText(text, true);
		            message.setSubject("Updates From PartyAnalyst");
		            message.setFrom(IConstants.FROMEMAILID);
		         }
		      };
		      javamailsender.send(preparator);  
		      
		      return "success";
	}catch(Exception e){
		log.error("Exception Rised in sendMail : ", e);
		return "failure";
	}
	}
	
	public String prepareMail(EmailNotificationVO emailNotificationVO,EmailNotificationVO updatesFrom){
		StringBuilder content = new StringBuilder();
		try
		 {	
				content.append("<div style='background:#FFFFFF;padding:10px;'>"+mailsTemplateService.getHeader()+" ");
				
					if(emailNotificationVO.isPartyPagepresent()  || emailNotificationVO.isCandidatePagepresent()  || emailNotificationVO.isSpecialPagepresent())
					{
						if(emailNotificationVO.isCandidatePagepresent()){
							content.append(getIndividualMailData("candidate",emailNotificationVO.getCandidatePage(),updatesFrom));
						}
	                    if(emailNotificationVO.isPartyPagepresent()){
	                    	content.append(getIndividualMailData("party",emailNotificationVO.getPartyPage(),updatesFrom));
						}
	                    if(emailNotificationVO.isSpecialPagepresent()){
	                    	content.append(getIndividualMailData("specialpage",emailNotificationVO.getSpecialPage(),updatesFrom));
						}
					}else{
						return "";
					}
				
				content.append("</div>");
				
		 }catch(Exception e)
		 {
			 log.error("Exception Rised in getAllMailingDetails : ", e);
			 return "";
		 }
		return content.toString();
	}
	
	public String getIndividualMailData(String type,List<EmailNotificationVO> dataList,EmailNotificationVO updatesFrom){
		StringBuilder mailString = new StringBuilder();
		for(EmailNotificationVO emailNotificationVO:dataList){
			
			if(emailNotificationVO.isNewspresent() || emailNotificationVO.isPhotopresent() || emailNotificationVO.isNewspresent()){
				if(type.equals("candidate")){
					updatesFrom.getCandidates().add(emailNotificationVO.getName());
				}else if(type.equals("party")){
					updatesFrom.getParties().add(emailNotificationVO.getName());
				}else if(type.equals("specialpage")){
					updatesFrom.getSpecialPgs().add(emailNotificationVO.getName());
				}
				mailString.append("<div>");
				mailString.append("<div style='text-align:center;padding:6px;margin-top:5px;margin-bottom:5px;font-weight:bold;background-color:#17315A;color:#FFFFFF;'>Updates for "+emailNotificationVO.getName()+"</div>");
				if(emailNotificationVO.isNewspresent()){
					mailString.append("<div style='font-weight:bold;color:#AB3A7E;margin-top:4px;margin-bottom:4px;'> News Updates for "+emailNotificationVO.getName()+"</div>");
					mailString.append(getCompleteData(emailNotificationVO.getNews(),type,emailNotificationVO.getId(),"news"));
			    }
                if(emailNotificationVO.isPhotopresent()){
                	mailString.append("<div style='font-weight:bold;color:#AB3A7E;margin-top:4px;margin-bottom:4px;'> Photo Updates for "+emailNotificationVO.getName()+"</div>");
                	mailString.append(getCompleteData(emailNotificationVO.getPhotos(),type,emailNotificationVO.getId(),"photo"));
			    }
                if(emailNotificationVO.isVideopresent()){
                	mailString.append("<div style='font-weight:bold;color:#AB3A7E;margin-top:4px;margin-bottom:4px;'> Video Updates for "+emailNotificationVO.getName()+"</div>");
                	mailString.append(getCompleteData(emailNotificationVO.getVideos(),type,emailNotificationVO.getId(),"video"));
			    }
                
                mailString.append("</div>");
		  }
		}
		return mailString.toString();
	}
	
	public String getCompleteData(List<EmailNotificationVO> dataList,String type,Long id,String fileType){
		StringBuilder individualString = new StringBuilder();
		for(EmailNotificationVO  emailNotificationVO : dataList){
			individualString.append("<div style='border-bottom:1px solid #ccc;padding:10px;background-color:#F3F3F3;margin-bottom:4px;'>");
			if(type.trim().equalsIgnoreCase("candidate")){
			   individualString.append("<div style='margin-top:5px;margin-bottom:3px;font-weight:normal;'><h4 style='margin:0px;'><a title='Click here for more details' href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email' style='text-decoration:none;'>"+emailNotificationVO.getTitle()+"</a></h4></div>");
			       if(fileType.equalsIgnoreCase("video"))
					  individualString.append("<div style='width:100%;display:inline-block;clear:both;padding:5px;'><span style='width:auto;display:inline-block;padding:2px;float:left;'><a title='Click here for more details' href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email'><img style='width:75px;height:75px;vertical-align:middle;' src='http://img.youtube.com/vi/"+emailNotificationVO.getFilePath()+"/1.jpg'></img></a></span>");
				   else
					  individualString.append("<div style='width:100%;display:inline-block;clear:both;padding:5px;'><span style='width:auto;display:inline-block;padding:2px;float:left;'><a title='Click here for more details' href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email'><img style='width:75px;height:75px;vertical-align:middle;' src='www.partyanalyst.com/"+emailNotificationVO.getFilePath()+"'></img></a></span>");
			}else if(type.trim().equalsIgnoreCase("party")){
			   individualString.append("<div style='margin-top:5px;margin-bottom:3px;font-weight:normal;'><h4 style='margin:0px;'><a title='Click here for more details' href='http://www.partyanalyst.com/partyPageAction.action?partyId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email' style='text-decoration:none;'>"+emailNotificationVO.getTitle()+"</a></h4></div>");
			       if(fileType.equalsIgnoreCase("video"))
					  individualString.append("<div style='width:100%;display:inline-block;clear:both;padding:5px;'><span style='width:auto;display:inline-block;padding:2px;float:left;'><a title='Click here for more details' href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email'><img style='width:75px;height:75px;vertical-align:middle;' src='http://img.youtube.com/vi/"+emailNotificationVO.getFilePath()+"/1.jpg'></img></a></span>");
				   else
					  individualString.append("<div style='width:100%;display:inline-block;clear:both;padding:5px;'><span style='width:auto;display:inline-block;padding:2px;float:left;'><a title='Click here for more details' href='http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email'><img style='width:75px;height:75px;vertical-align:middle;' src='www.partyanalyst.com/"+emailNotificationVO.getFilePath()+"'></img></a></span>");
			}else if(type.trim().equalsIgnoreCase("specialpage")){
			   individualString.append("<div style='margin-top:5px;margin-bottom:3px;font-weight:normal;'><h4 style='margin:0px;'><a title='Click here for more details' href='http://www.partyanalyst.com/specialPageAction.action?specialPageId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email' style='text-decoration:none;'>"+emailNotificationVO.getTitle()+"</a></h4></div>");
			     if(fileType.equalsIgnoreCase("video"))
			          individualString.append("<div style='width:100%;display:inline-block;clear:both;padding:5px;'><span style='width:auto;display:inline-block;padding:2px;float:left;'><a title='Click here for more details' href='http://www.partyanalyst.com/specialPageAction.action?specialPageId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email'><img style='width:75px;height:75px;vertical-align:middle;' src='http://img.youtube.com/vi/"+emailNotificationVO.getFilePath()+"/1.jpg'></img></a></span>");
			     else
			          individualString.append("<div style='width:100%;display:inline-block;clear:both;padding:5px;'><span style='width:auto;display:inline-block;padding:2px;float:left;'><a title='Click here for more details' href='http://www.partyanalyst.com/specialPageAction.action?specialPageId="+id+"&contentId="+emailNotificationVO.getFileGallaryId()+"&src=email'><img style='width:75px;height:75px;vertical-align:middle;' src='www.partyanalyst.com/"+emailNotificationVO.getFilePath()+"'></img></a></span>");
			}
			individualString.append("<span style='width:75%;font-weight:normal;float:left;display:inline-block;text-align:justify;padding-left:5px;'>"+emailNotificationVO.getDescription()+"</span></div>");
			individualString.append("</div>");
			
		}
		return individualString.toString();
	}
	
	public List<SelectOptionVO> getAllCandidates(){
		List<Object[]> candidates = candidateSubscriptionsDAO.getAllCandidates();
		List<SelectOptionVO> candidatesList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = null;
		 for(Object[] candidate:candidates){
			 selectOptionVO = new SelectOptionVO();
			 selectOptionVO.setId((Long)candidate[0]);
			 selectOptionVO.setName(candidate[1]!=null?candidate[1].toString():"");
			 candidatesList.add(selectOptionVO);
		 }
		return candidatesList;
	}
	
	public List<SelectOptionVO> getAllParties(){
		List<Object[]> parties = partySubscriptionsDAO.getAllParties();
		List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = null;
		 for(Object[] party:parties){
			 selectOptionVO = new SelectOptionVO();
			 selectOptionVO.setId((Long)party[0]);
			 selectOptionVO.setName(party[1]!=null?party[1].toString():"");
			 partiesList.add(selectOptionVO);
		 }
		return partiesList;
		
	}
	
	public List<SelectOptionVO> getAllSpecialPages(){
		List<Object[]> specialPages = specialPageSubscriptionsDAO.getAllSpecialPages();
		List<SelectOptionVO> specialPagesList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = null;
		 for(Object[] specialPage:specialPages){
			 selectOptionVO = new SelectOptionVO();
			 selectOptionVO.setId((Long)specialPage[0]);
			 selectOptionVO.setName(specialPage[1]!=null?specialPage[1].toString():"");
			 specialPagesList.add(selectOptionVO);
		 }
		return specialPagesList;
	}
}
