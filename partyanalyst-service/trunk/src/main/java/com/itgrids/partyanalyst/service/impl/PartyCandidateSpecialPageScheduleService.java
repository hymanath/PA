package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IPartyUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageUpdatesEmailDAO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.EmailNotificationVO;
import com.itgrids.partyanalyst.model.CandidateUpdatesEmail;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;
import com.itgrids.partyanalyst.model.SpecialPageUpdatesEmail;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsTemplateService;
import com.itgrids.partyanalyst.service.IPartyCandidateSpecialPageScheduleService;

public class PartyCandidateSpecialPageScheduleService implements
		IPartyCandidateSpecialPageScheduleService {
	
	private static final Logger log = Logger.getLogger(PartyCandidateSpecialPageScheduleService.class);
	private ICandidateUpdatesEmailDAO candidateUpdatesEmailDAO;
	
	private IPartyUpdatesEmailDAO partyUpdatesEmailDAO;
	
	private ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO;
	
	private IFileGallaryDAO fileGallaryDAO;
	
	private IMailService mailService;
	
	private IMailsTemplateService mailsTemplateService;
	
	public ICandidateUpdatesEmailDAO getCandidateUpdatesEmailDAO() {
		return candidateUpdatesEmailDAO;
	}
	public void setCandidateUpdatesEmailDAO(
			ICandidateUpdatesEmailDAO candidateUpdatesEmailDAO) {
		this.candidateUpdatesEmailDAO = candidateUpdatesEmailDAO;
	}
	public IPartyUpdatesEmailDAO getPartyUpdatesEmailDAO() {
		return partyUpdatesEmailDAO;
	}
	public void setPartyUpdatesEmailDAO(IPartyUpdatesEmailDAO partyUpdatesEmailDAO) {
		this.partyUpdatesEmailDAO = partyUpdatesEmailDAO;
	}
	public ISpecialPageUpdatesEmailDAO getSpecialPageUpdatesEmailDAO() {
		return specialPageUpdatesEmailDAO;
	}
	public void setSpecialPageUpdatesEmailDAO(
			ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO) {
		this.specialPageUpdatesEmailDAO = specialPageUpdatesEmailDAO;
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
	
	public void sendMailsToAllSubscriders(Date startDate,Date endDate)
	{
		try
		{
		List<EmailDetailsVO> emailDetailsVOList = new ArrayList<EmailDetailsVO>();
		Map<String,List<EmailNotificationVO>> candidatePage = getDaillyUpdatesForCandidatePageSubscribers(startDate,endDate);
		Map<String,List<EmailNotificationVO>> partyPage = getDaillyUpdatesForPartyPageSubscribers(startDate,endDate);
		Map<String,List<EmailNotificationVO>> specialPage = getDaillyUpdatesForSpecialPageSubscribers(startDate,endDate);
		
		getAllMailingDetails(emailDetailsVOList,candidatePage,"candidate");
		getAllMailingDetails(emailDetailsVOList,partyPage,"party");
		getAllMailingDetails(emailDetailsVOList,specialPage,"specialpage");
		
		mailService.sendEmails(emailDetailsVOList);
		}
		catch(Exception e){
			log.error("Exception Rised in sendMailsToAllSubscriders : ", e);
		}
	}
	private Map<String,List<EmailNotificationVO>> getDaillyUpdatesForCandidatePageSubscribers(Date startDate,Date endDate)
	{
		Map<Long,EmailNotificationVO> candidateIdMap = new HashMap<Long,EmailNotificationVO>();
		Map<String,List<EmailNotificationVO>> emailMap = new HashMap<String,List<EmailNotificationVO>>();
		List<Long> candidateIds = new ArrayList<Long>();
		try
		{
		      List<CandidateUpdatesEmail> subscriberDetailsList = candidateUpdatesEmailDAO.getAllSubscriberDetails();
		      for(CandidateUpdatesEmail candidateUpdatesEmail : subscriberDetailsList)
		       {
			     candidateIds.add(candidateUpdatesEmail.getCandidate().getCandidateId());
			     candidateIdMap.put(candidateUpdatesEmail.getCandidate().getCandidateId(), new EmailNotificationVO());
			     emailMap.put(candidateUpdatesEmail.getEmail(), new ArrayList<EmailNotificationVO>());
		       }
		      for(CandidateUpdatesEmail candidateUpdatesEmail : subscriberDetailsList)
		       {
			     emailMap.get(candidateUpdatesEmail.getEmail()).add(candidateIdMap.get(candidateUpdatesEmail.getCandidate().getCandidateId()));
		       }
		       List<Object[]> photosList = fileGallaryDAO.getCandidateGallaryDetailsForSubscribers(startDate,endDate,candidateIds,"photos");
		       List<Object[]> newsList = fileGallaryDAO.getCandidateGallaryDetailsForSubscribers(startDate,endDate,candidateIds,"news");
		       List<Object[]> videosList = fileGallaryDAO.getCandidateGallaryDetailsForSubscribers(startDate,endDate,candidateIds,"videos");
		
		      for(Object[] photos : photosList)
		       {
		    	 try{
			      EmailNotificationVO emailNotificationVO = candidateIdMap.get((Long)photos[3]);
			      emailNotificationVO.setId((Long)photos[3]);
			      emailNotificationVO.setName(photos[4] != null?photos[4].toString():"");
			      emailNotificationVO.setPhotopresent(1);
			      EmailNotificationVO data = new EmailNotificationVO();
			      data.setFileGallaryId((Long)photos[0]);
			      data.setGallaryName(photos[1] != null?photos[1].toString():"");
			      data.setTitle(photos[2] != null?photos[2].toString():"");
			      data.setDescription(photos[5] != null?photos[5].toString():"");
			
			      emailNotificationVO.getPhotos().add(data);
		    	}catch(Exception e){
		    		log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers : ", e);
		    	}
		       }
		     for(Object[] news : newsList)
		      {
		    	 try{
			       EmailNotificationVO emailNotificationVO = candidateIdMap.get((Long)news[3]);
			       emailNotificationVO.setId((Long)news[3]);
			       emailNotificationVO.setName(news[4] != null?news[4].toString():"");
			       emailNotificationVO.setNewspresent(1);
			       EmailNotificationVO data = new EmailNotificationVO();
			       data.setFileGallaryId((Long)news[0]);
			       data.setGallaryName(news[1] != null?news[1].toString():"");
			       data.setTitle(news[2] != null?news[2].toString():"");
			       data.setDescription(news[5] != null?news[5].toString():"");
			       data.setSource(news[6] != null?news[6].toString():"");
			       data.setLanguage(news[7] != null?news[7].toString():"");
			
			       emailNotificationVO.getNews().add(data);
		    	 }catch(Exception e){
		    		 log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers : ", e);
			     }
		      }
		    for(Object[] videos : videosList)
		     {
		    	try{
		    	  EmailNotificationVO emailNotificationVO = candidateIdMap.get((Long)videos[3]);
		    	  emailNotificationVO.setId((Long)videos[3]);
		    	  emailNotificationVO.setName(videos[4] != null?videos[4].toString():"");
		    	  emailNotificationVO.setVideopresent(1);
		    	  EmailNotificationVO data = new EmailNotificationVO();
		    	  data.setFileGallaryId((Long)videos[0]);
		    	  data.setGallaryName(videos[1] != null?videos[1].toString():"");
		    	  data.setTitle(videos[2] != null?videos[2].toString():"");
		    	  data.setDescription(videos[5] != null?videos[5].toString():"");
		    	  data.setSource(videos[6] != null?videos[6].toString():"");
		    	  data.setLanguage(videos[7] != null?videos[7].toString():"");
			
		    	  emailNotificationVO.getVideos().add(data);
		        }catch(Exception e){
		        	log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers : ", e);
		    	}
		     }
		}
		catch(Exception e){
			log.error("Exception Rised in getDaillyUpdatesForCandidatePageSubscribers : ", e);
		}
		return emailMap;
	}
	
	private Map<String,List<EmailNotificationVO>> getDaillyUpdatesForPartyPageSubscribers(Date startDate,Date endDate)
	{
		Map<Long,EmailNotificationVO> partyIdMap = new HashMap<Long,EmailNotificationVO>();
		Map<String,List<EmailNotificationVO>> emailMap = new HashMap<String,List<EmailNotificationVO>>();
		List<Long> partyIds = new ArrayList<Long>();
		try
		   {
			 List<PartyUpdatesEmail> subscriberDetailsList = partyUpdatesEmailDAO.getAllSubscriberDetails();
		
			 for(PartyUpdatesEmail partyUpdatesEmail : subscriberDetailsList)
			 {
				 partyIds.add(partyUpdatesEmail.getParty().getPartyId());
				 partyIdMap.put(partyUpdatesEmail.getParty().getPartyId(), new EmailNotificationVO());
				 emailMap.put(partyUpdatesEmail.getEmail(), new ArrayList<EmailNotificationVO>());
			 }
			 for(PartyUpdatesEmail partyUpdatesEmail : subscriberDetailsList)
			 {
				 emailMap.get(partyUpdatesEmail.getEmail()).add(partyIdMap.get(partyUpdatesEmail.getParty().getPartyId()));
			 }
			 List<Object[]> photosList = fileGallaryDAO.getPartyGallaryDetailsForSubscribers(startDate,endDate,partyIds,"photos");
			 List<Object[]> newsList = fileGallaryDAO.getPartyGallaryDetailsForSubscribers(startDate,endDate,partyIds,"news");
			 List<Object[]> videosList = fileGallaryDAO.getPartyGallaryDetailsForSubscribers(startDate,endDate,partyIds,"videos");
		
			 for(Object[] photos : photosList)
			 {
			  try{
				 EmailNotificationVO emailNotificationVO = partyIdMap.get((Long)photos[3]);
				 emailNotificationVO.setId((Long)photos[3]);
				 emailNotificationVO.setName(photos[4] != null?photos[4].toString()+" Party":"");
				 emailNotificationVO.setPhotopresent(1);
				 EmailNotificationVO data = new EmailNotificationVO();
				 data.setFileGallaryId((Long)photos[0]);
				 data.setGallaryName(photos[1] != null?photos[1].toString():"");
				 data.setTitle(photos[2] != null?photos[2].toString():"");
				 data.setDescription(photos[5] != null?photos[5].toString():"");
			
				 emailNotificationVO.getPhotos().add(data);
			  }catch(Exception e){
				  log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers : ", e);
		       }
			 }
			 for(Object[] news : newsList)
			 {
			  try{
				 EmailNotificationVO emailNotificationVO = partyIdMap.get((Long)news[3]);
				 emailNotificationVO.setId((Long)news[3]);
				 emailNotificationVO.setName(news[4] != null?news[4].toString()+" Party":"");
				 emailNotificationVO.setNewspresent(1);
				 EmailNotificationVO data = new EmailNotificationVO();
				 data.setFileGallaryId((Long)news[0]);
				 data.setGallaryName(news[1] != null?news[1].toString():"");
				 data.setTitle(news[2] != null?news[2].toString():"");
				 data.setDescription(news[5] != null?news[5].toString():"");
				 data.setSource(news[6] != null?news[6].toString():"");
				 data.setLanguage(news[7] != null?news[7].toString():"");
			
				 emailNotificationVO.getNews().add(data);
			  }catch(Exception e){
				  log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers : ", e);
		       }
			 }
			 for(Object[] videos : videosList)
			 {
			  try{
				 EmailNotificationVO emailNotificationVO = partyIdMap.get((Long)videos[3]);
				 emailNotificationVO.setId((Long)videos[3]);
				 emailNotificationVO.setName(videos[4] != null?videos[4].toString()+" Party":"");
				 emailNotificationVO.setVideopresent(1);
				 EmailNotificationVO data = new EmailNotificationVO();
				 data.setFileGallaryId((Long)videos[0]);
				 data.setGallaryName(videos[1] != null?videos[1].toString():"");
				 data.setTitle(videos[2] != null?videos[2].toString():"");
				 data.setDescription(videos[5] != null?videos[5].toString():"");
				 data.setSource(videos[6] != null?videos[6].toString():"");
				 data.setLanguage(videos[7] != null?videos[7].toString():"");
			
				 emailNotificationVO.getVideos().add(data);
			  }catch(Exception e){
				  log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers : ", e);
		       }
			 }
		 }
		catch(Exception e){
			log.error("Exception Rised in getDaillyUpdatesForPartyPageSubscribers : ", e);
		}
		return emailMap;
	}
	
	private Map<String,List<EmailNotificationVO>> getDaillyUpdatesForSpecialPageSubscribers(Date startDate,Date endDate)
	{
		Map<Long,EmailNotificationVO> specialPageIdMap = new HashMap<Long,EmailNotificationVO>();
		Map<String,List<EmailNotificationVO>> emailMap = new HashMap<String,List<EmailNotificationVO>>();
		List<Long> specialPageIds = new ArrayList<Long>();
		try
		   {
			List<SpecialPageUpdatesEmail> subscriberDetailsList = specialPageUpdatesEmailDAO.getAllSubscriberDetails();
		
			for(SpecialPageUpdatesEmail specialPageUpdatesEmail : subscriberDetailsList)
			{
				specialPageIds.add(specialPageUpdatesEmail.getSpecialPage().getSpecialPageId());
				specialPageIdMap.put(specialPageUpdatesEmail.getSpecialPage().getSpecialPageId(), new EmailNotificationVO());
				emailMap.put(specialPageUpdatesEmail.getEmail(), new ArrayList<EmailNotificationVO>());
			}
			for(SpecialPageUpdatesEmail specialPageUpdatesEmail : subscriberDetailsList)
			{
				emailMap.get(specialPageUpdatesEmail.getEmail()).add(specialPageIdMap.get(specialPageUpdatesEmail.getSpecialPage().getSpecialPageId()));
			}
			List<Object[]> photosList = fileGallaryDAO.getSpecialPageGallaryDetailsForSubscribers(startDate,endDate,specialPageIds,"photos");
			List<Object[]> newsList = fileGallaryDAO.getSpecialPageGallaryDetailsForSubscribers(startDate,endDate,specialPageIds,"news");
			List<Object[]> videosList = fileGallaryDAO.getSpecialPageGallaryDetailsForSubscribers(startDate,endDate,specialPageIds,"videos");
		
			for(Object[] photos : photosList)
			{
			 try{
				EmailNotificationVO emailNotificationVO = specialPageIdMap.get((Long)photos[3]);
				emailNotificationVO.setId((Long)photos[3]);
				emailNotificationVO.setName(photos[4] != null?photos[4].toString():"");
				emailNotificationVO.setPhotopresent(1);
				EmailNotificationVO data = new EmailNotificationVO();
				data.setFileGallaryId((Long)photos[0]);
				data.setGallaryName(photos[1] != null?photos[1].toString():"");
				data.setTitle(photos[2] != null?photos[2].toString():"");
				data.setDescription(photos[5] != null?photos[5].toString():"");
			
				emailNotificationVO.getPhotos().add(data);
			 }catch(Exception e){
				 log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers : ", e);
		       }
			}
			for(Object[] news : newsList)
			{
			 try{
				EmailNotificationVO emailNotificationVO = specialPageIdMap.get((Long)news[3]);
				emailNotificationVO.setId((Long)news[3]);
				emailNotificationVO.setName(news[4] != null?news[4].toString():"");
				emailNotificationVO.setNewspresent(1);
				EmailNotificationVO data = new EmailNotificationVO();
				data.setFileGallaryId((Long)news[0]);
				data.setGallaryName(news[1] != null?news[1].toString():"");
				data.setTitle(news[2] != null?news[2].toString():"");
				data.setDescription(news[5] != null?news[5].toString():"");
				data.setSource(news[6] != null?news[6].toString():"");
				data.setLanguage(news[7] != null?news[7].toString():"");
			
				emailNotificationVO.getNews().add(data);
			 }catch(Exception e){
				 log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers : ", e);
		       }
			}
			for(Object[] videos : videosList)
			{
			 try{
				EmailNotificationVO emailNotificationVO = specialPageIdMap.get((Long)videos[3]);
				emailNotificationVO.setId((Long)videos[3]);
				emailNotificationVO.setName(videos[4] != null?videos[4].toString():"");
				emailNotificationVO.setVideopresent(1);
				EmailNotificationVO data = new EmailNotificationVO();
				data.setFileGallaryId((Long)videos[0]);
				data.setGallaryName(videos[1] != null?videos[1].toString():"");
				data.setTitle(videos[2] != null?videos[2].toString():"");
				data.setDescription(videos[5] != null?videos[5].toString():"");
				data.setSource(videos[6] != null?videos[6].toString():"");
				data.setLanguage(videos[7] != null?videos[7].toString():"");
			
				emailNotificationVO.getVideos().add(data);
			 }catch(Exception e){
				 log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers : ", e);		       }	
			}
		   }
		catch(Exception e){
			log.error("Exception Rised in getDaillyUpdatesForSpecialPageSubscribers : ", e);   
		}
		return emailMap;
	}
	private void getAllMailingDetails(List<EmailDetailsVO> emailDetailsVOList,Map<String,List<EmailNotificationVO>> emailMap,String type)
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
				if(emailNotificationVO.getPhotopresent() == 1 || emailNotificationVO.getVideopresent() == 1 || emailNotificationVO.getNewspresent() == 1)
				{
					count = count+1;
					content.append("<div style='background-color:#EEEEEE;border-radius:10px;padding:15px;margin-top:10px;'>");
					content.append("<div style='text-align:center;font-weight:bold;color:green' >Updates For "+emailNotificationVO.getName()+"</div>");
					
					if(emailNotificationVO.getNewspresent() == 1){
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
                    if(emailNotificationVO.getPhotopresent() == 1){
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
                    if(emailNotificationVO.getVideopresent() == 1){
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
	}
}
