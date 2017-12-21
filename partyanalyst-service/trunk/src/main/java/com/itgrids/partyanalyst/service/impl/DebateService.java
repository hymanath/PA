package com.itgrids.partyanalyst.service.impl;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IChannelDAO;
import com.itgrids.partyanalyst.dao.ICharacteristicsDAO;
import com.itgrids.partyanalyst.dao.IDebateDAO;
import com.itgrids.partyanalyst.dao.IDebateObserverDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantExceptedRoleDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantLocationDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantRoleDAO;
import com.itgrids.partyanalyst.dao.IDebateQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IDebateQuestionsDAO;
import com.itgrids.partyanalyst.dao.IDebateReportDAO;
import com.itgrids.partyanalyst.dao.IDebateRolesDAO;
import com.itgrids.partyanalyst.dao.IDebateSmsQuestionDAO;
import com.itgrids.partyanalyst.dao.IDebateSmsQuestionOptionDAO;
import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.dao.IObserverDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITelecastTypeDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebatePartyWiseCountVO;
import com.itgrids.partyanalyst.dto.DebateTopicVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ParticipantVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Channel;
import com.itgrids.partyanalyst.model.Characteristics;
import com.itgrids.partyanalyst.model.Debate;
import com.itgrids.partyanalyst.model.DebateObserver;
import com.itgrids.partyanalyst.model.DebateParticipant;
import com.itgrids.partyanalyst.model.DebateParticipantCharcs;
import com.itgrids.partyanalyst.model.DebateParticipantExpectedRole;
import com.itgrids.partyanalyst.model.DebateParticipantLocation;
import com.itgrids.partyanalyst.model.DebateParticipantRole;
import com.itgrids.partyanalyst.model.DebateQuestionAnswer;
import com.itgrids.partyanalyst.model.DebateQuestions;
import com.itgrids.partyanalyst.model.DebateReport;
import com.itgrids.partyanalyst.model.DebateRoles;
import com.itgrids.partyanalyst.model.DebateSmsQuestion;
import com.itgrids.partyanalyst.model.DebateSmsQuestionOption;
import com.itgrids.partyanalyst.model.DebateSubject;
import com.itgrids.partyanalyst.model.Observer;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.service.IDebateAnalysisService;
import com.itgrids.partyanalyst.service.IDebateService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DebateService implements IDebateService{
	
	private static final Logger 				LOG = Logger.getLogger(DebateService.class); 
	
	private IDebateDAO 							debateDAO;
	private IDebateSmsQuestionOptionDAO 		debateSmsQuestionOptionDAO;
	private IObserverDAO 						observerDAO;
	private IChannelDAO  						channelDAO;
	private ITelecastTypeDAO 					telecastTypeDAO;
	private IDebateRolesDAO  					debateRolesDAO;
	private IDebateSubjectDAO 					debateSubjectDAO;
	private IDebateObserverDAO  				debateObserverDAO;
	private ICharacteristicsDAO  				characteristicsDAO;
	private IDebateParticipantDAO 				debateParticipantDAO;
	private IDebateParticipantRoleDAO           debateParticipantRoleDAO;
	private IDebateParticipantExceptedRoleDAO   debateParticipantExceptedRoleDAO;
	private IDebateParticipantCharcsDAO         debateParticipantCharcsDAO;
	private IDebateSmsQuestionDAO 				debateSmsQuestionDAO;
	private IDebateQuestionsDAO					debateQuestionsDAO ;
	private IDebateQuestionAnswerDAO  			debateQuestionAnswerDAO;
	private TransactionTemplate 				transactionTemplate;
	private IPartyDAO 							partyDAO;
	private ICandidateDAO 						candidateDAO;
	private IUserDAO 							userDAO;
	private IDebateReportDAO 					debateReportDAO;

	private IDebateAnalysisService				debateAnalysisService;
	
	private ITdpCadreCandidateDAO				tdpCadreCandidateDAO;
	private IDebateParticipantLocationDAO       debateParticipantLocationDAO;
	public void setDebateReportDAO(IDebateReportDAO debateReportDAO) {
		this.debateReportDAO = debateReportDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setDebateDAO(IDebateDAO debateDAO) {
		this.debateDAO = debateDAO;
	}

	public void setDebateSmsQuestionOptionDAO(
			IDebateSmsQuestionOptionDAO debateSmsQuestionOptionDAO) {
		this.debateSmsQuestionOptionDAO = debateSmsQuestionOptionDAO;
	}

	public void setObserverDAO(IObserverDAO observerDAO) {
		this.observerDAO = observerDAO;
	}

	public IChannelDAO getChannelDAO() {
		return channelDAO;
	}

	public void setChannelDAO(IChannelDAO channelDAO) {
		this.channelDAO = channelDAO;
	}

	public void setTelecastTypeDAO(ITelecastTypeDAO telecastTypeDAO) {
		this.telecastTypeDAO = telecastTypeDAO;
	}

	public void setDebateRolesDAO(IDebateRolesDAO debateRolesDAO) {
		this.debateRolesDAO = debateRolesDAO;
	}

	public void setDebateSubjectDAO(IDebateSubjectDAO debateSubjectDAO) {
		this.debateSubjectDAO = debateSubjectDAO;
	}

	public void setDebateObserverDAO(IDebateObserverDAO debateObserverDAO) {
		this.debateObserverDAO = debateObserverDAO;
	}

	
	public void setCharacteristicsDAO(ICharacteristicsDAO characteristicsDAO) {
		this.characteristicsDAO = characteristicsDAO;
	}

	public void setDebateParticipantDAO(IDebateParticipantDAO debateParticipantDAO) {
		this.debateParticipantDAO = debateParticipantDAO;
	}

	public void setDebateParticipantRoleDAO(
			IDebateParticipantRoleDAO debateParticipantRoleDAO) {
		this.debateParticipantRoleDAO = debateParticipantRoleDAO;
	}

	public void setDebateParticipantExceptedRoleDAO(
			IDebateParticipantExceptedRoleDAO debateParticipantExceptedRoleDAO) {
		this.debateParticipantExceptedRoleDAO = debateParticipantExceptedRoleDAO;
	}

	public void setDebateParticipantCharcsDAO(
			IDebateParticipantCharcsDAO debateParticipantCharcsDAO) {
		this.debateParticipantCharcsDAO = debateParticipantCharcsDAO;
	}

	public void setDebateSmsQuestionDAO(IDebateSmsQuestionDAO debateSmsQuestionDAO) {
		this.debateSmsQuestionDAO = debateSmsQuestionDAO;
	}

	public void setDebateQuestionsDAO(IDebateQuestionsDAO debateQuestionsDAO) {
		this.debateQuestionsDAO = debateQuestionsDAO;
	}

	public void setDebateQuestionAnswerDAO(
			IDebateQuestionAnswerDAO debateQuestionAnswerDAO) {
		this.debateQuestionAnswerDAO = debateQuestionAnswerDAO;
	}

	public void setDebateAnalysisService(
			IDebateAnalysisService debateAnalysisService) {
		this.debateAnalysisService = debateAnalysisService;
	}
	
	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}
	public IDebateParticipantLocationDAO getDebateParticipantLocationDAO() {
		return debateParticipantLocationDAO;
	}

	public void setDebateParticipantLocationDAO(
			IDebateParticipantLocationDAO debateParticipantLocationDAO) {
		this.debateParticipantLocationDAO = debateParticipantLocationDAO;
	}

	/**
	 * This service is used for telugu font saving as well as telugu font retriving
	 * @param input
	 * @return String b
	 */
	public String escapeUnicode(String input)
	{
		StringBuilder b = new StringBuilder(input.length());
		Formatter f = new Formatter(b);
		for (char c : input.toCharArray()) {
		if (c < 128) {
		b.append(c);
		} else {
		f.format("\\u%04x", (int) c);
		}
		}
		return b.toString();
	}

	
	/**
	 * This Service is used for saving the debate details
	 * @param debateDetailsVO
	 * @return resultStatus
	 */
	public ResultStatus saveDebateDetails(final DebateDetailsVO debateDetailsVO)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try 
		{
			LOG.info("Enterd into saveDebateDetails method in DebateService class");
			 transactionTemplate.execute(new TransactionCallback() {
				  public Object doInTransaction(TransactionStatus status) {
					
				  if(debateDetailsVO != null)
				  {
					  List<Integer> debateParticipantIds = null;
					  //Integer value = 0;
					  if(debateDetailsVO.getType().equalsIgnoreCase("edit"))
					  {
						  debateParticipantIds = debateParticipantDAO.getDebateParticipantId(debateDetailsVO.getDebateId());
						  if(debateParticipantIds != null && debateParticipantIds.size()>0){
							    //value = debateParticipantIds.get(0);
							  for(Integer value : debateParticipantIds){
								  debateParticipantLocationDAO.removeDebateParticipantId(value);
							  }
						  }
						  debateDAO.removeDebate(debateDetailsVO.getDebateId());
					  }
					  Debate debate = new Debate();
					  Channel channel =  channelDAO.get(debateDetailsVO.getChannelId());
					  if(channel != null)
					  {
						 debate.setChannel(channel); 
					  }
					  //TelecastType telecastType = telecastTypeDAO.get(debateDetailsVO.getTelecasteTypeId());
					 /* if(telecastType != null)
					  {
						  debate.setTelecastType(telecastType);  
					  }*/
					//  debate.setSummary(escapeUnicode(StringEscapeUtils.escapeJava(debateDetailsVO.getDebateSummery())));
					  debate.setSummary(debateDetailsVO.getDebateSummery());
					  debate.setStartTime(debateDetailsVO.getStartDate());
					  debate.setEndTime(debateDetailsVO.getEndDate());
					  debate.setIsDeleted("N");
					  DateUtilService currentDate = new DateUtilService();
					  debate.setCreatedDate(currentDate.getCurrentDateAndTime());
					  
					  debate.setYoutubeUrl(debateDetailsVO.getYoutubeUrl() !=null && !debateDetailsVO.getYoutubeUrl().isEmpty() ? 
							  debateDetailsVO.getYoutubeUrl():null);
					  
					  debate.setInsertedTime(currentDate.getCurrentDateAndTime());
					  debate.setUpdatedTime(currentDate.getCurrentDateAndTime());
					  debate.setInsertedBy(debateDetailsVO.getUserId());
					  debate.setUpdatedBy(debateDetailsVO.getUserId());
					  if(debateDetailsVO.getStateId() == 1L){
						  debate.setAddressId(20441102l);
						  }else if(debateDetailsVO.getStateId() == 36L){
							  debate.setAddressId(20441103l);
						  }else if(debateDetailsVO.getStateId() == 2L){
							  debate.setAddressId(11236072l);
						  }
					  
					  debate=debateDAO.save(debate);
					  List<SelectOptionVO> subjectsList = debateDetailsVO.getSubjectList();
					  if(subjectsList != null && subjectsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : subjectsList) {
							DebateSubject debateSubject = new DebateSubject();
							//debateSubject.setSubject(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
							debateSubject.setSubject(selectOptionVO.getName());
							debateSubject.setDebate(debate);
							debateSubject = debateSubjectDAO.save(debateSubject);
						}
					  }
					  List<SelectOptionVO> obsersList = debateDetailsVO.getObserverList();
					  if(obsersList != null && obsersList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : obsersList) {
							DebateObserver debateObserver = new DebateObserver();
							Observer observer = observerDAO.get(selectOptionVO.getId());
							debateObserver.setObserver(observer);
							debateObserver.setDebate(debate);
							debateObserver = debateObserverDAO.save(debateObserver);
						}
					  }
					  
					  List<ParticipantVO> participentList = debateDetailsVO.getParticipentsList();
					  DebateParticipant debateParticipant =null;
					  if(participentList != null && participentList.size() > 0)
					  {
						  
						  for (ParticipantVO participantVO : participentList)
						  {
							   debateParticipant = new DebateParticipant();
							  Party party = partyDAO.get(participantVO.getPartyId());
							  if(party != null)
							  {
								  debateParticipant.setParty(party);
							  }
							  Candidate candidate =candidateDAO.get(participantVO.getId());
							  if(candidate != null)
							  {
								  debateParticipant.setCandidate(candidate);
							  }					  
							  debateParticipant.setDebate(debate);
							  //debateParticipant.setSummary(participantVO.getSummery() != null ? escapeUnicode(StringEscapeUtils.escapeJava(participantVO.getSummery())):null);
							  debateParticipant.setSummary(participantVO.getSummery() != null ? participantVO.getSummery():null);
							  debateParticipant = debateParticipantDAO.save(debateParticipant);
							  List<SelectOptionVO> rolesList = participantVO.getRoleList();
							  if(rolesList != null && rolesList.size() > 0)
							  {
								  for (SelectOptionVO selectOptionVO : rolesList) {
									DebateParticipantRole debateParticipantRole = new DebateParticipantRole();
									debateParticipantRole.setDebateParticipant(debateParticipant);
									DebateRoles debateRoles = debateRolesDAO.get(selectOptionVO.getId());
									debateParticipantRole.setDebateRoles(debateRoles);
									debateParticipantRole = debateParticipantRoleDAO.save(debateParticipantRole);
								}
							  }
							  List<SelectOptionVO> expRolesList = participantVO.getExpRoleList();
							  if(expRolesList != null && expRolesList.size() > 0)
							  {
								  for (SelectOptionVO selectOptionVO : expRolesList) {
									DebateParticipantExpectedRole debateParticipantExpectedRole = new DebateParticipantExpectedRole();
									debateParticipantExpectedRole.setDebateParticipant(debateParticipant);
									DebateRoles debateRoles = debateRolesDAO.get(selectOptionVO.getId());
									debateParticipantExpectedRole.setDebateRoles(debateRoles);
									debateParticipantExpectedRole = debateParticipantExceptedRoleDAO.save(debateParticipantExpectedRole);
								}
							  }
							  List<SelectOptionVO> scalesList = participantVO.getScaleList();
							  if(scalesList != null && scalesList.size() > 0)
							  {
								  for (SelectOptionVO selectOptionVO : scalesList) {
									DebateParticipantCharcs debateParticipantCharcs = new DebateParticipantCharcs();
									debateParticipantCharcs.setDebateParticipant(debateParticipant);
									debateParticipantCharcs.setScale(selectOptionVO.getPerc());
									Characteristics characteristics = characteristicsDAO.get(selectOptionVO.getId());
									if(characteristics !=null)
									{
										debateParticipantCharcs.setCharacteristics(characteristics);
									}
									debateParticipantCharcs = debateParticipantCharcsDAO.save(debateParticipantCharcs);
									
								}
							  }
							 
							 DebateParticipantLocation debateParticipantLocation  = new DebateParticipantLocation();
							   debateParticipantLocation.setDebateParticipantId(debateParticipant.getDebateParticipantId());
		                    if(participantVO.getLocationId() == 1L){
		                	   debateParticipantLocation.setAddressId(20441102l);
							  }else if(participantVO.getLocationId() == 36L){
								  debateParticipantLocation.setAddressId(20441103l);
							  }
							  debateParticipantLocation.setIsDeleted("N");
							  debateParticipantLocation = debateParticipantLocationDAO.save(debateParticipantLocation);
						}
						  
					 }
					 
					  List<SelectOptionVO> questionsList = debateDetailsVO.getQuestionsList();
					  if(questionsList != null && questionsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : questionsList) {
							DebateQuestionAnswer debateQuestionAnswer = new DebateQuestionAnswer();
							//debateQuestionAnswer.setAnswer(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
							debateQuestionAnswer.setAnswer(selectOptionVO.getName() !=null ? selectOptionVO.getName().toString():"");
							debateQuestionAnswer.setDebate(debate);
							DebateQuestions debateQuestions = debateQuestionsDAO.get(selectOptionVO.getId());
							if(debateQuestions != null)
							{
								debateQuestionAnswer.setDebateQuestions(debateQuestions);
							}
							debateQuestionAnswer = debateQuestionAnswerDAO.save(debateQuestionAnswer);
						}
					  }
					  
					  List<SelectOptionVO> smsQuestionsList = debateDetailsVO.getSmsQuestionList();
					  DebateSmsQuestion debateSmsQuestion = null;
					  if(smsQuestionsList != null && smsQuestionsList.size() > 0)
					  {
						  SelectOptionVO selectOptionVO = smsQuestionsList.get(0);
						    debateSmsQuestion = new DebateSmsQuestion();
							debateSmsQuestion.setDebate(debate);
							//debateSmsQuestion.setQuestion(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
							debateSmsQuestion.setQuestion(selectOptionVO.getName() !=null ? selectOptionVO.getName().toString():"");
							debateSmsQuestion.setIsDeleted("N");
							debateSmsQuestion = debateSmsQuestionDAO.save(debateSmsQuestion);
						
					  }
					  List<SelectOptionVO> smsOptionsList = debateDetailsVO.getSmaOptionsList();
					  if(smsOptionsList != null && smsOptionsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : smsOptionsList) {
							DebateSmsQuestionOption debateSmsQuestionOption = new DebateSmsQuestionOption();
							//debateSmsQuestionOption.setOption(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
							debateSmsQuestionOption.setOption(selectOptionVO.getName() !=null ? selectOptionVO.getName().toString():"");
							//DebateSmsQuestion debateSmsQuestion = debateSmsQuestionDAO.get(selectOptionVO.getId());
							//if(debateSmsQuestion != null)
							//{
								debateSmsQuestionOption.setDebateSmsQuestion(debateSmsQuestion);
							//}
							debateSmsQuestionOption.setPercantage(selectOptionVO.getPerc());
							debateSmsQuestionOption = debateSmsQuestionOptionDAO.save(debateSmsQuestionOption);
						}
					  }
					
				    }
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;  
				  }
			 });
		} 
		catch (Exception e)
		{
			LOG.error("Error occured in getDebateDetailsForSelected method in DebateService class",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
	}
	
	
	/**
	 * this service is used for getting the debate details for selected debate
	 * @param debateId
	 * @return DebateVO returnList
	 */
	public DebateVO getDebateDetailsForSelected(Long debateId,Long stateId)
	{
		DebateVO debateVO = new DebateVO();
		try 
		{
			LOG.info("Enterd into getDebateDetailsForSelected method in DebateService class");
			List<SelectOptionVO> debateSmsDetailsList = null;
			List<SelectOptionVO> debatePaticepentDetailsList = null;
			List<SelectOptionVO> debateQuestionDetailsList = null;
			List<String> debateSubjectsList = null;
			List<SelectOptionVO> debateExpRolesList = null;
			List<String> observerList = null;
			List<SelectOptionVO> observersList = null;
			List<ParticipantVO> particepentDetailsList = null;
			//List<SelectOptionVO> scalesList = null;
			//List<SelectOptionVO> rolesList = null;
			// here we are getting all details of debate
			SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat parseFormat1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			//SimpleDateFormat printFormat = new SimpleDateFormat("HH.mm");
			
			//System.out.println(printFormat.format(date)); // prints 09:30:51
			List<Object[]> debateDetailsList = debateDAO.getDebateDetailsForSelectedDebate(debateId,stateId);//debate Location
			if(debateDetailsList != null && debateDetailsList.size() > 0)
			{
				Object[] debateDetails = debateDetailsList.get(0);
				String startTime = "";
				debateVO.setDebateId(debateDetails[0] != null ? (Long)debateDetails[0]:0l);
				Calendar startCal = Calendar.getInstance();
				startCal.setTime((Date)debateDetails[1]);
				int startHour =  startCal.get(Calendar.HOUR) ;
				int startMinut = startCal.get(Calendar.MINUTE);
				int startType = startCal.get(Calendar.AM_PM);
				if(startType == 0)
				{
					if(startMinut >= 10)
					{
						startTime = startHour +":"+ startMinut +""+ "AM";
					}
					else
					{
						startTime = startHour +":"+ startMinut +"0"+ "AM";
					}
					
				}
				else
				{
					if(startMinut >= 10)
					{
						startTime = startHour +":"+ startMinut +""+ "PM";
					}
					else
					{
						startTime = startHour +":"+ startMinut +"0"+ "PM";
					}
					
				}
				String endTime = null;
				Calendar endCal = Calendar.getInstance();
				endCal.setTime((Date)debateDetails[2]);
				int endHour =  endCal.get(Calendar.HOUR);
				int endMinut = endCal.get(Calendar.MINUTE);
				int endType = endCal.get(Calendar.AM_PM);
				if(endType == 0)
				{
					if(endMinut >= 10)
					{
						endTime = endHour +":"+ endMinut +""+ "AM";
					}
					else
					{
						endTime = endHour +":"+ endMinut +"0"+ "AM";
					}
					
				}
				else
				{
					if(endMinut >= 10)
					{
						endTime = endHour +":"+ endMinut +""+ "PM";
					}
					else
					{
						endTime = endHour +":"+ endMinut +"0"+ "PM";
					}
					
				}
				
				
				debateVO.setStartTime(startTime);
				debateVO.setStDate(parseFormat1.format(parseFormat.parse(debateDetails[1].toString())));
				debateVO.setEdDate(parseFormat1.format(parseFormat.parse(debateDetails[2].toString())));
				//debateVO.setStartTime(debateDetails[1] != null ? printFormat.format(parseFormat.parse(debateDetails[1].toString())) :"");
				//debateVO.setEndTime(debateDetails[2] != null ? printFormat.format(parseFormat.parse(debateDetails[2].toString())) :"");
				debateVO.setEndTime(endTime);
				debateVO.setDate(debateDetails[1] != null ? sdf.format(parseFormat.parse(debateDetails[1].toString())) :"");
				debateVO.setChannelId(debateDetails[3] != null ? (Long)debateDetails[3] :0l);
				debateVO.setChannelName(debateDetails[4] != null ? debateDetails[4].toString() :"");
				//debateVO.setTelecastTypeId(debateDetails[5] != null ? (Long)debateDetails[5] :0l);
				//debateVO.setTelecastTime(debateDetails[6] != null ? debateDetails[6].toString() :"");
				debateVO.setDebateSummery(debateDetails[5] != null ? StringEscapeUtils.unescapeJava(debateDetails[5].toString()) :"");
				debateVO.setYoutubeUrl(debateDetails[6] != null ? debateDetails[6].toString():"");
				debateVO.setDebateLocation(debateDetails[7] != null ? debateDetails[7].toString() :null);
				debateVO.setDebateLocId(debateDetails[8] != null ? (Long)debateDetails[8] :0l);
				
			}
			// here we are getting the all details of the sma question and answer with percentage details
			List<Object[]> debateSmsDetails = debateSmsQuestionOptionDAO.getDebateSmsQuestionsForSelectedDebate(debateId);
			if(debateSmsDetails != null && debateSmsDetails.size() > 0)
			{
				debateSmsDetailsList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : debateSmsDetails)
				{
					SelectOptionVO debateSms = new SelectOptionVO();
					debateSms.setName(parms[4] != null ? StringEscapeUtils.unescapeJava(parms[4].toString()) :"");//question
					debateSms.setId(parms[3] != null ? (Long)parms[3] :0l);//questionid
					debateSms.setType(parms[1] != null ? StringEscapeUtils.unescapeJava(parms[1].toString()) :"");//option
					debateSms.setCount(parms[0] != null ? (Long)parms[0] :0l);//id
					debateSms.setPerc(parms[2] != null ? (Double)parms[2] :0.0);//percentage
					debateSmsDetailsList.add(debateSms);
				}
				debateVO.setSmsPoleList(debateSmsDetailsList);
			}
			// here we getting the all candidate details with part wise
			List<Object[]> debatePaticepentDetails = debateParticipantDAO.getDebatePaticepantsAndSummeryDetails(debateId,stateId);
			if(debatePaticepentDetails != null && debatePaticepentDetails.size() > 0)
			{
				debatePaticepentDetailsList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : debatePaticepentDetails)
				{
					if(parms[2] != null){
						SelectOptionVO paticepentSummery = new SelectOptionVO();
						paticepentSummery.setType(parms[1] != null ? parms[1].toString() :"");//party
						paticepentSummery.setLocation(parms[0] != null ? parms[0].toString() :"");//candidate
						paticepentSummery.setName(parms[2] != null ? StringEscapeUtils.unescapeJava(parms[2].toString()) :"");//summery
						paticepentSummery.setId(parms[3] != null ? (Long)parms[3] : 0l);//candidateId
						paticepentSummery.setCount(parms[4] != null ? (Long)parms[4] : 0l);//partyId
						paticepentSummery.setLocationId(parms[5] != null ? (Long)parms[5] :0l);//participant Location
						debatePaticepentDetailsList.add(paticepentSummery);
					}
				}
				debateVO.setCandidateSummery(debatePaticepentDetailsList);
			}
			// here we are getting the excepeted roles for participents of tdp party
			List<Object[]> debateExpRoles = debateParticipantExceptedRoleDAO.getPaticepentExpRoles(debateId);
			if(debateExpRoles != null && debateExpRoles.size() > 0)
			{
				debateExpRolesList = new ArrayList<SelectOptionVO>();
				
				for (Object[] parms : debateExpRoles)
				{
					SelectOptionVO debateExpRole = new SelectOptionVO();
					debateExpRole.setName(parms[1] != null ? parms[1].toString() :"");//candidate
					debateExpRole.setLocation(parms[0] != null ? StringEscapeUtils.unescapeJava(parms[0].toString()) :"");//role
					debateExpRolesList.add(debateExpRole);
				}
				debateVO.setDebateExpRolesList(debateExpRolesList);
			}
						
			//here we are getting the all details of the debate particepent candidate characers
			List<Object[]> dabateCharcsList = debateParticipantCharcsDAO.getDebateCharcsDetails(debateId);
			// here we are getting the all particepents role details
			List<Object[]> debateRolesList  = debateParticipantRoleDAO.getParticepentRoles(debateId);
			if(dabateCharcsList != null && dabateCharcsList.size() > 0 && debatePaticepentDetails != null && debatePaticepentDetails.size() > 0 && debateRolesList != null && debateRolesList.size() > 0)
			{
				particepentDetailsList = new ArrayList<ParticipantVO>();
				Map<Long, SelectOptionVO> particepentsMap = new HashMap<Long, SelectOptionVO>();//Map<candidate,partydetails>
				Map<Long,List<SelectOptionVO>> charactesMap = new HashMap<Long, List<SelectOptionVO>>();//Map<candidate,scaleDetails>
				Map<Long,List<SelectOptionVO>> rolesMap = new HashMap<Long, List<SelectOptionVO>>();//Map<candidate,roledetails>
				// here we are processing the particepent party and name wise details
				for (Object[] parms : debatePaticepentDetails)
				{
					SelectOptionVO particepentDetails = particepentsMap.get((Long)parms[3]);
					if(particepentDetails == null)
					{
						particepentDetails = new SelectOptionVO();
						particepentsMap.put((Long)parms[3], particepentDetails);
					}
					particepentDetails.setName(parms[1] != null ? parms[1].toString() :"");//party
					particepentDetails.setLocation(parms[0] != null ? parms[0].toString() :"");//candidate
					particepentDetails.setId(parms[3] != null ? (Long)parms[3] : 0l);//candidateId
					particepentDetails.setCount(parms[4] != null ? (Long)parms[4] : 0l);//partyId
					particepentDetails.setPartno(parms[2] != null ? StringEscapeUtils.unescapeJava(parms[2].toString()) :null);//summary
				}
				// here we are processing the candidate charactes and scaling details
				for (Object[] parms : dabateCharcsList)
				{
					List<SelectOptionVO> charactrsDetails = charactesMap.get((Long)parms[0]);
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					if(charactrsDetails == null)
					{
						//scalesList = new ArrayList<SelectOptionVO>();
						charactrsDetails = new ArrayList<SelectOptionVO>();
						charactesMap.put((Long)parms[0], charactrsDetails);
					}
					selectOptionVO.setName(parms[4] != null ?parms[4].toString() :"");//scale
					selectOptionVO.setPerc(parms[5] != null ?(Double)parms[5]: 0.0);//rating //srujana//overall
					if(selectOptionVO.getPerc() != null && selectOptionVO.getPerc() >0.0){
					Double scalePerc = getAvgDetailsOfDebate(selectOptionVO.getPerc(),(Long)parms[6]);
					   selectOptionVO.setScalePerc(scalePerc);
					}
					selectOptionVO.setId(parms[6] != null ? (Long)parms[6]:0l);//charectersticId
					//scalesList.add(selectOptionVO);
					charactrsDetails.add(selectOptionVO);;
				}
				// here we are processing the candidate role participated in debate
				for(Object[] parms : debateRolesList)
				{
					List<SelectOptionVO> roleDetails = rolesMap.get((Long)parms[0]);
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					if(roleDetails == null)
					{
						//rolesList = new ArrayList<SelectOptionVO>();
						roleDetails = new ArrayList<SelectOptionVO>();
						rolesMap.put((Long)parms[0], roleDetails);
					}
					selectOptionVO.setName(parms[4] != null ?StringEscapeUtils.unescapeJava(parms[4].toString()) :"");//role
					selectOptionVO.setId(parms[5] != null ? (Long)parms[5] : 0l);
					roleDetails.add(selectOptionVO);
				}
				Map<Long,List<SelectOptionVO>> expRolesMap = null;
				List<SelectOptionVO> expRoleList = null;
				if(debateExpRoles != null && debateExpRoles.size() > 0)
				{
					expRolesMap = new HashMap<Long, List<SelectOptionVO>>();
					for (Object[] parms : debateExpRoles) {
						expRoleList = expRolesMap.get((Long)parms[2]);
						if(expRoleList == null)
						{
							expRoleList = new ArrayList<SelectOptionVO>();
							expRolesMap.put((Long)parms[2], expRoleList);
						}
						SelectOptionVO debateExpRole = new SelectOptionVO();
						debateExpRole.setName(parms[1] != null ? parms[1].toString() :"");//candidate
						debateExpRole.setLocation(parms[0] != null ? StringEscapeUtils.unescapeJava(parms[0].toString()) :"");//role
						debateExpRole.setId(parms[3] != null ? (Long)parms[3] :0l);
						expRoleList.add(debateExpRole);
					}
				}
				if(expRolesMap != null && expRolesMap.size() > 0)
				{
					debateVO.setNoTdpLeaders(Long.valueOf(expRolesMap.size()));
				}
				else
				{
					debateVO.setNoTdpLeaders(0l);
				}
				Set<Long> candidatesSet = particepentsMap.keySet();
				// here we are processing the each candidate wise debate scaling , charactes, party etc...
				for (Long candidateId : candidatesSet)
				{
					ParticipantVO participantVO = new ParticipantVO();
					SelectOptionVO paticiVO = particepentsMap.get(candidateId);
					participantVO.setName(paticiVO.getLocation());
					participantVO.setPartyName(paticiVO.getName());
					participantVO.setPartyId(paticiVO.getCount());
					participantVO.setId(paticiVO.getId());
					participantVO.setSummery(paticiVO.getPartno());
					participantVO.setLocationId(paticiVO.getLocationId());
					List<SelectOptionVO> scopesList = charactesMap.get(candidateId);
					//List<SelectOptionVO> scopesList = chatesVO.getSelectOptionsList();
					List<SelectOptionVO> scopeList = new ArrayList<SelectOptionVO>();
					Double total = 0.00;
					for (SelectOptionVO scopesVOVO : scopesList)
					{
						SelectOptionVO scopesVO = new SelectOptionVO();
						scopesVO.setName(scopesVOVO.getName());
						scopesVO.setPerc(scopesVOVO.getPerc());
						scopesVO.setScalePerc(scopesVOVO.getScalePerc());
						total = total + (Double.valueOf(scopesVOVO.getPerc()));
						scopeList.add(scopesVO);
					}
					participantVO.setPerc(total);
					if(participantVO.getPerc() != null && participantVO.getPerc() >0.0){
						Double scalePerc = getAvgDetailsOfDebate(participantVO.getPerc(),null);
						   participantVO.setPerc(scalePerc);
						}
					List<SelectOptionVO> roleList = rolesMap.get(candidateId);
					if(roleList != null && roleList.size() > 0)
					{
						List<SelectOptionVO> roleDetailsList = new ArrayList<SelectOptionVO>();
						Long count = 0l;
						StringBuffer periRole = new StringBuffer();
						List<Long> roleIds = new ArrayList<Long>();
						for (SelectOptionVO selectOptionVO : roleList) {
							count ++;
							SelectOptionVO debateRoleVO = new SelectOptionVO();
							debateRoleVO.setName(selectOptionVO.getName());
							debateRoleVO.setId(selectOptionVO.getId());
							roleIds.add(selectOptionVO.getId());
							debateRoleVO.setTotalCountList(roleIds);
							roleDetailsList.add(debateRoleVO);
							if(count == 1)
							{
								periRole.append(selectOptionVO.getName());
							}
							else
							{
								periRole.append( "+" + selectOptionVO.getName()); 
							}
						}
						participantVO.setPrtiRoles(periRole.toString());
						participantVO.setRoleList(roleDetailsList);
					}
					List<SelectOptionVO> expList = null;
					if(expRolesMap != null && expRolesMap.size() > 0)
					{
						List<SelectOptionVO> exproles = expRolesMap.get(candidateId);
						
						StringBuffer expRole = null;
						if(exproles != null && exproles.size() > 0)
						{
							Long count = 0l;
							expRole = new StringBuffer();
							expList = new ArrayList<SelectOptionVO>();
							for (SelectOptionVO expRoleVO : exproles) {
								count ++ ;
								SelectOptionVO scopesVO = new SelectOptionVO();
								scopesVO.setName(expRoleVO.getLocation());
								scopesVO.setId(expRoleVO.getId());
								expList.add(scopesVO);
								if(count == 1)
								{
									expRole.append(expRoleVO.getLocation());
								}
								else
								{
									expRole.append( "+" + expRoleVO.getLocation()); 
								}
							}
						}
						if(expRole != null)
							participantVO.setExpRoles(expRole.toString());
					}
					
					List<Object[]> candiList = candidateDAO.getCandidatesForDebate(paticiVO.getCount());
					List<SelectOptionVO> candidatesList = null;
					if(candiList != null && candiList.size()>0){
						candidatesList = new ArrayList<SelectOptionVO>();
						for (Object[] candidate : candiList) {
							SelectOptionVO candiVO = new SelectOptionVO();
							candiVO.setId(Long.valueOf(candidate[0].toString()));
							candiVO.setName(candidate[1].toString());
							//candiVO.setLo
							candidatesList.add(candiVO);
						}
					}
					
					participantVO.setCandidatesList(candidatesList);
					participantVO.setScaleList(scopeList);
					participantVO.setExpRoleList(expList);
					particepentDetailsList.add(participantVO);
					
				}
				debateVO.setParticipantsList(particepentDetailsList);
			}
			// here we are getting the debate question and answer details
			List<Object[]> debateQuestionAnswer = debateQuestionAnswerDAO.getDebateQuestionAndAnswerDetails(debateId);
			if(debateQuestionAnswer != null && debateQuestionAnswer.size() > 0)
			{
				debateQuestionDetailsList = new ArrayList<SelectOptionVO>();
				// here we are processing the debate question with answer details
				for (Object[] parms : debateQuestionAnswer) 
				{
						SelectOptionVO questionAnswer = new SelectOptionVO();
						questionAnswer.setName(parms[1] != null ? StringEscapeUtils.unescapeJava(parms[1].toString()) :"");//Answer
						questionAnswer.setLocation(parms[0] != null ? StringEscapeUtils.unescapeJava(parms[0].toString()) :"");//Question
						questionAnswer.setId(parms[2] != null ? (Long)parms[2] : 0l);
						debateQuestionDetailsList.add(questionAnswer);
				}
				debateVO.setQuestionAnswersList(debateQuestionDetailsList);
				
			}
			
			// here we are getting the main subjet of the debeate
			List<Object[]> subjectsList = debateSubjectDAO.getDebateSubjectDetails(debateId);
			if(subjectsList != null && subjectsList.size() > 0)
			{
				debateSubjectsList = new ArrayList<String>();
				for (Object[] parms : subjectsList)
				{
					if(parms[0] != null)
					{
						String subject = StringEscapeUtils.unescapeJava(parms[0].toString());
						debateSubjectsList.add(subject);
					}
				}
				debateVO.setDebateNames(debateSubjectsList);
			}
			// here we are getting the observers details
			List<Object[]> debateObservers = debateObserverDAO.getObsersListForDebate(debateId);
			if(debateObservers != null && debateObservers.size() > 0)
			{
				observerList = new ArrayList<String>();
				observersList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : debateObservers) {
					if(parms[0] != null)
					{
						
						String observer = parms[0].toString();
						observerList.add(observer);
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[2]);
						selectOptionVO.setName(observer);
						observersList.add(selectOptionVO);
					}
				}
				debateVO.setObservorsList(observerList);
				debateVO.setObserverList(observersList);
			}
		}
		catch (Exception e)
		{
			LOG.error("Error occured in getDebateDetailsForSelected method in DebateService class",e);
		}
		return debateVO;
	}
	
	
	public String deleteSelectedDEbate(Long debateId)
	{
		String status = null;
		try {
			LOG.info("Enterd into deleteSelectedDEbate method in DebateService class");
			int result = debateDAO.deleteFlagDebate(debateId);
			
			if(result == 0)
			{
				status = "Fail";
			}
			else
			{
				status = "Success";
			}
		} catch (Exception e) {
			LOG.error("Error occured in deleteSelectedDEbate method in DebateService class",e);
		}
		return status;
	}
	/**
	 * This service is used for getting the channel details
	 * @return List<SelectOptionVO> channelDetails
	 */
	public List<SelectOptionVO> getChannelDetails()
	{
		List<SelectOptionVO> channelDetails = null;
		SelectOptionVO selectOptionVO;
		List<Object[]> channelDetail = channelDAO.getChannelDetailsNew();
		if(channelDetail != null && channelDetail.size() > 0)
		{
			channelDetails = new ArrayList<SelectOptionVO>();
			for(Object[] param:channelDetail){
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(param[0] !=null ? (Long)param[0]:0l);
				selectOptionVO.setName(param[1] !=null ? param[1].toString():"");
				channelDetails.add(selectOptionVO);
			}
		}
		
		return channelDetails;
	}
	
	
	
	/**
	 * This service is used for getting telecast details
	 * @return List<SelectOptionVO> telecastDetails
	 */
	public List<SelectOptionVO> getTelecastTimeDetails()
	{
		List<SelectOptionVO> telecastDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<Object[]> telecastTimeDetails = telecastTypeDAO.getTelecastTimeDetailsNew();
		for(Object[] param:telecastTimeDetails){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(param[0] !=null ? (Long)param[0]:0l);
			selectOptionVO.setName(param[1] !=null ? param[1].toString():"");
			telecastDetails.add(selectOptionVO);
		}
		return telecastDetails;
	}
	
	
	
	/**
	 * This service is used for getting the observers 
	 * @return List<SelectOptionVO> observerDetails
	 */
	public List<SelectOptionVO> getObserverDetails()
	{
		List<SelectOptionVO> observerDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<Object[]> observerDetailsVal = observerDAO.getObserverDetailsNew();
		for(Object[] param:observerDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(param[0] !=null ? (Long)param[0]:0l);
			selectOptionVO.setName(param[1] !=null ? param[1].toString():"");
			observerDetails.add(selectOptionVO);
		}
		return observerDetails;
	}
	
	
	
	/**
	 * This service is used for getting the debate question details
	 * @return List<SelectOptionVO> debateDetails
	 */
	public List<SelectOptionVO> getDebateQuestionDetails()
	{
		List<SelectOptionVO> debateDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<Object[]> debateDetailsVal = debateQuestionsDAO.getDebateQuestionDetailsNew();
		for(Object[] param:debateDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(param[0] !=null ? (Long)param[0]:0l);
			selectOptionVO.setName(param[1] !=null ? param[1].toString():"");
			debateDetails.add(selectOptionVO);
		}
		return debateDetails;
	}
	
	
	
	public List<SelectOptionVO> getDebateSmsQuestionDetails()
	{
		List<SelectOptionVO> debateSmsDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<Object[]> debateSmsDetailsVal = debateSmsQuestionDAO.getDebateSmsQuestionDetailsNew();
		for(Object[] param:debateSmsDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(param[0] !=null ? (Long)param[0]:0l);
			selectOptionVO.setName(param[1] !=null ? param[1].toString():"");
			debateSmsDetails.add(selectOptionVO);
		}
		return debateSmsDetails;
	}
	
	
	
	public List<SelectOptionVO> getDebateParticipantRoleDetails()
	{
		List<SelectOptionVO> debateParticipantRoleDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<Object[]> debateParticipantRoleDetailsVal = debateParticipantRoleDAO.getDebateParticipantRoleDetailsNew();
		for(Object[] param:debateParticipantRoleDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(param[0] !=null ? (Long)param[0]:0l);
			selectOptionVO.setName(param[1] !=null ? param[1].toString():"");
			debateParticipantRoleDetails.add(selectOptionVO);
		}
		return debateParticipantRoleDetails;
	}
	
	
	
	public ResultStatus saveNewRole(final Long userId,final String newRole)
	{
		LOG.info("Enterd into saveNewRole method in DebateService class");
		ResultStatus isSaved = new ResultStatus();
		try {
				Long count = debateRolesDAO.checkForExists(newRole.trim());
				if(count == 0)
				{
					if(userId != null && newRole != null && !newRole.isEmpty()){
						DebateRoles debateRoles = new DebateRoles();
						debateRoles.setName(newRole.trim());
						debateRoles.setIsDeleted("N");
						debateRoles = debateRolesDAO.save(debateRoles);
						if(debateRoles != null)
						{
							isSaved.setResultCode(ResultCodeMapper.SUCCESS);
						}
						else
						{
							isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
						}
					}
				}
				else
				{
					isSaved.setResultCode(ResultCodeMapper.FAILURE);
				}
	
		} catch (Exception e) {
			isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			LOG.error("Error occured in saveNewRole method in DebateService class",e);
		}
		
		return isSaved;
	}
	
	
	public ResultStatus saveNewCharacteristic(final Long userId, final String newCharacteristic)
	{
		LOG.info("Enterd into saveNewCharacteristic() in DebateService class");
		ResultStatus isSaved = new ResultStatus();
		try {
			Long count = characteristicsDAO.checkForExists(StringEscapeUtils.escapeJava(newCharacteristic));
			if(count == 0)
			{
				if(userId != null && newCharacteristic != null){
					Characteristics characteristics = new Characteristics();
					characteristics.setName(escapeUnicode(StringEscapeUtils.escapeJava(newCharacteristic)).trim());
					characteristics.setIsDeleted("N");
					characteristics = characteristicsDAO.save(characteristics);
					if(characteristics != null)
					{
						isSaved.setResultCode(ResultCodeMapper.SUCCESS);
					}
					else
					{
						isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					}
				}
			}
			else
			{
				isSaved.setResultCode(ResultCodeMapper.FAILURE);
			}
			
				
			 
			
		} catch (Exception e) {
			isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);		
			LOG.error("Error occured in saveNewCharacteristic() in DebateService class",e);
		}		
		return isSaved;
	}
	
	
	public ResultStatus saveNewDebateQuestion(final Long userId, final String newDebateQuestion)
	{
		LOG.info("Enterd into saveNewDebateQuestion() in DebateService class");
		ResultStatus isSaved = new ResultStatus();
		try {
			
			Long count = debateQuestionsDAO.checkForExists(StringEscapeUtils.escapeJava(newDebateQuestion));
			if(count == 0)
			{
				if(userId != null && newDebateQuestion != null)
				{
					DebateQuestions debateQuestion = new DebateQuestions();
					debateQuestion.setQuestion(escapeUnicode(StringEscapeUtils.escapeJava(newDebateQuestion)).trim());
					debateQuestion.setIsDeleted("N");
					debateQuestion = debateQuestionsDAO.save(debateQuestion);
					if(debateQuestion != null)
					{
						isSaved.setResultCode(ResultCodeMapper.SUCCESS);
					}
					else
					{
						isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					}
						
				 }
			}
			else
			{
				isSaved.setResultCode(ResultCodeMapper.FAILURE);	
			}
								
			
						
		} catch (Exception e) {
			isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			LOG.error("Error occured in saveNewDebateQuestion() in DebateService class",e);
		}
		
		return isSaved;
	}
	
	
	 public ResultStatus insertChannelDetails(final Long userId,final String channelName )
	 {
			LOG.info("Enterd into insertChannelDetails() in DebateService class");
			ResultStatus isSaved = new ResultStatus();
		 try
		 {			 
				Long count = channelDAO.checkForExists(StringEscapeUtils.escapeJava(channelName));
				if(count == 0)
				{
					if(userId != null && channelName != null)
					{
						Channel channel = new Channel();						 
						channel.setChannelName(StringEscapeUtils.escapeJava(channelName).trim());	
						channel.setIsDeleted("N");
						channel = channelDAO.save(channel);	
						if(channel != null)
						{
							isSaved.setResultCode(ResultCodeMapper.SUCCESS);
						}
						else
						{
							isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
						}
						
					}
				}
				else
				{
					isSaved.setResultCode(ResultCodeMapper.FAILURE);
				}
				
			 
			
		 }
		 catch(Exception e)
		 {
			isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			LOG.error("Error occured in insertChannelDetails() in DebateService class",e); 
		 }	
		  return isSaved;
	 }
	 

	 public ResultStatus insertObserverDetails(final Long userId,final String observerName )
	 {		 
			LOG.info("Enterd into insertObserverDetails() in DebateService class");
			ResultStatus isSaved = new ResultStatus();
		 try
		 {		 
			Long count = observerDAO.checkForExists(StringEscapeUtils.escapeJava(observerName));
			if(count == 0)
			{
				if(userId != null && observerName != null)
				{
					Observer observer = new Observer();					 
					observer.setObserverName(StringEscapeUtils.escapeJava(observerName).trim());	
					observer.setIsDeleted("N");
					observer= observerDAO.save(observer);
					if(observer != null)
					{
						isSaved.setResultCode(ResultCodeMapper.SUCCESS);
					}
					
					else
					{
						isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					}
					
				}
			}
			else
			{
				isSaved.setResultCode(ResultCodeMapper.FAILURE);
			}
					 
			 		 
		 }
		 catch(Exception e)
		 {
			isSaved.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			LOG.error("Error occured in insertObserverDetails() in DebateService class",e);	 
		 }	
		 return isSaved;
	 }	 
	 
	 
	 public List<SelectOptionVO> getCharacteristicsDetails()
	 {
	 	List<SelectOptionVO> CharacteristicsDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<Object[]> CharacteristicsDetailsVal = characteristicsDAO.getCharacteristicsDetailsNew();
		for(Object[] param:CharacteristicsDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(param[0] !=null ? (Long)param[0]:0l);
			selectOptionVO.setName(param[1] !=null ? param[1].toString():"");
			CharacteristicsDetails.add(selectOptionVO);
		}
		return CharacteristicsDetails;
	 }
	 
	 
	 public List<SelectOptionVO> getRolesList()
	 {
		 List<SelectOptionVO> returnList = null;
		 try {
			 LOG.info("Enterd into getRolesList() in DebateService class");
			 List<Object[]> rolesList = debateRolesDAO.getDebateRoles();
			 if(rolesList != null && rolesList.size() > 0)
			 {
				 returnList = new ArrayList<SelectOptionVO>();
				 for (Object[] param : rolesList) {
					 SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId(new Long((Long)param[0]));
						selectOptionVO.setName(param[1].toString());
						returnList.add(selectOptionVO);
				}
			 }
		} catch (Exception e) {
			LOG.error("Error occured in getRolesList() in DebateService class",e);	
		}
		 return returnList ;
	 }
	 
	 
	 public String saveDebateReportForPdf( Long userId, Long debateId, String description, String path,Long stateId)
	 {
		  String string = "invalid";
		 try {
			 LOG.info("Enterd into saveDebateReportForPdf() in DebateService class");
			 
			
						
						String key = debateReportDAO.getDebateDatils(userId, debateId,stateId);
						if(key == null)
						{
							DebateReport debateReport = new DebateReport();
							DateUtilService currentDate = new DateUtilService();
							debateReport.setDescription(escapeUnicode(StringEscapeUtils.unescapeJava(description)));
							debateReport.setCreatedDate(currentDate.getCurrentDateAndTime());
							Debate debate = debateDAO.get(debateId);
							if(debate != null)
							{
								debateReport.setDebate(debate);
							}
							User user = userDAO.get(userId);
							if(user != null)
							{
								debateReport.setUser(user);
							}
							String key1 = UUID.randomUUID().toString();
							debateReport.setKey(key1);
							debateReport = debateReportDAO.save(debateReport);
							if(debateReport == null)
							{
								
								string = "error";
							}
							else
							{
								string = path+"key1="+key1;
								
							}
						}
						else
						{
							string = path+"key="+key;
						}
						

		} catch (Exception e) {
			LOG.error("Error occured in saveDebateReportForPdf() in DebateService class",e);
			string = "error";
		}
		 return string;
	 }
	 
	 
	 public String genearetUrl(Long reportId,Long userId,String path)
	 {
		 String url = "invalid";
	    	try{
	    		Long count = debateReportDAO.checkValidUserForReport(userId, reportId);
		    	if(count > 0){
		    		String key = UUID.randomUUID().toString();
		    		DebateReport debatereport = debateReportDAO.get(reportId);
		    		debatereport.setKey(key);
		    		debateReportDAO.save(debatereport);
		    		url = path+"key="+key;
		    	}
	    	}catch(Exception e){
	    		LOG.error(" Exception Occured in genearetUrl method, Exception - ",e);
	    		url = "exception";
	    	}
	    	return url;
	 }
	 
	 public DebateVO getDebateDetailsForSelectedCriteria(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortBy,String sort ,int minIndex,int maxIndex,Long stateId)
	 {
		 List<SelectOptionVO> returnList = null;
		 DebateVO debateVO = new DebateVO();
		 try {
			 LOG.info("Enterd into getDebateDetailsForSelectedCriteria() in DebateService class");
			/* Long channelId = null;
			 Long partyId = null;
			 Long candidateId = null;
			 if(!channel.equalsIgnoreCase("null"))
			 {
				 channelId = Long.valueOf(channel) ;
			 }
			 if(!party.equalsIgnoreCase("null"))
			 {
				 partyId = Long.valueOf(party) ;
			 }
			 if(!candidate.equalsIgnoreCase("null"))
			 {
				 candidateId = Long.valueOf(candidate) ;
			 }*/
			 
						 
			 List<Object[]> debateDetails = debateSubjectDAO.getDebateDetalsForSelectedCriteria(fromDate,toDate,channelIds,partyIds,candidateIds,sortBy,sort ,minIndex,maxIndex,null,stateId);
			 Map<Long,SelectOptionVO> debateMap = new HashMap<Long, SelectOptionVO>();//Map<debateId,debateDetails>
			 if(debateDetails != null && debateDetails.size() > 0)
			 {
				 returnList = new ArrayList<SelectOptionVO>();
				 SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				 for (Object[] objects : debateDetails) {
					 SelectOptionVO selectOptionVO = debateMap.get((Long)objects[0]);
					 if(selectOptionVO == null)
					 {
						 selectOptionVO =  new SelectOptionVO();
						 selectOptionVO.setId((Long)objects[0]);//debateId
						 //selectOptionVO.setName(objects[1].toString());//debate subject
						 selectOptionVO.setName(StringEscapeUtils.unescapeJava(objects[1].toString()));
						 selectOptionVO.setType(sdf.format(parseFormat.parse(objects[2].toString())));//debate date
						 debateMap.put((Long)objects[0], selectOptionVO);
						 returnList.add(selectOptionVO);
					 }
					 else
					 {
						 selectOptionVO.setName(selectOptionVO.getName() + "<br/>" +StringEscapeUtils.unescapeJava(objects[1].toString()));
					 }
					 
				}
			 }
			 debateVO.setSmsPoleList(returnList);
			 List<Object[]> debatecountDetails = debateSubjectDAO.getDebateDetalsForSelectedCriteria(fromDate,toDate,channelIds,partyIds,candidateIds,sortBy,sort ,minIndex,maxIndex,"count",stateId);
			 if(debatecountDetails != null && debatecountDetails.size() > 0)
			 {
				 for (Object[] totalDebatecount : debatecountDetails) {	 
					 debateVO.setTotalCount(Long.valueOf(totalDebatecount[0].toString()));
				 }
			 }
			
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDebateDetailsForSelectedCriteria method, Exception - ",e);
		}
		 return debateVO;
	 }
	 
	 
	 public String deleteDebateReportUrl(String key)
	 {
		 String status = "";
		 try {
			 LOG.info("Enterd into deleteDebateReportUrl() in DebateService class");
			 int deleteStatus = debateReportDAO.deleteDebateReport(key);
			 if(deleteStatus == 1)
			 {
				 status = "deleted"; 
			 }
			 else
			 {
				 status = "error";
			 }
		} catch (Exception e) {
			LOG.error(" Exception Occured in deleteDebateReportUrl method, Exception - ",e);
		}
		 return status;
	 }
	 
	 
	 public List<SelectOptionVO> getCandidatesForDebate(List<Long> partyIds)
	 {
		 List<SelectOptionVO> returnList = null;
		 try {
			 LOG.info("Enterd into getCandidatesForDebate() in DebateService class");
			 if(partyIds != null && partyIds.size()>0)
			 {
				 List<Object[]> candidatesList = candidateDAO.getCandidatesForDebateParties(partyIds);
				 if(candidatesList != null && candidatesList.size() > 0)
				 {
					 returnList = new ArrayList<SelectOptionVO>();
					 for (Object[] parms : candidatesList) {
						 SelectOptionVO selectOptionVO = new SelectOptionVO();
						 selectOptionVO.setId((Long)parms[0]);
						 selectOptionVO.setName(parms[1].toString());
						 returnList.add(selectOptionVO);
					}
				 }
			 }
			
		} catch (Exception e) {
			LOG.error(" Exception Occured in getCandidatesForDebate method, Exception - ",e);
		}
		 return returnList;
	 }
	 
	 
	 public List<SelectOptionVO> debateAnalysisService(Long partyId,Long candidateId,String type)
	 {
		 List<SelectOptionVO> returnList = null;
		 try 
		 {
			 LOG.info("Enterd into debateAnalysisService() in DebateService class");
			 StringBuffer quryString = new StringBuffer();
			 if(type.equalsIgnoreCase("party"))
			 {
				 quryString.append("select model.party.partyId , model.party.shortName  ");
			 }
			 else if(type.equalsIgnoreCase("candidate"))
			 {
				 quryString.append("select model.candidate.candidateId , model.candidate.lastname  ");
			 }
			 quryString.append(", model2.debateRoles.debateRolesId ,model2.debateRoles.name ,sum(model1.scale)");
			 quryString.append(" from DebateParticipant model , DebateParticipantRole model2 ," +
			 		" DebateParticipantCharcs model1  where model.debateParticipantId = model1.debateParticipant.debateParticipantId and " +
				" model1.debateParticipant.debateParticipantId = model2.debateParticipant.debateParticipantId ");
			 if(type.equalsIgnoreCase("party"))
			 {
				 quryString.append(" and  model.party.partyId = :partyId group by model2.debateRoles.debateRolesId");
			 }
			 else if(type.equalsIgnoreCase("candidate"))
			 {
				 quryString.append(" model.party.partyId = :partyId and model.candidate.candidateId = :candidateId group by model2.debateRoles.debateRolesId "); 
			 }
			 
			 List<Object[]> annalysedData = debateParticipantDAO.getBasicDebateAnalysis(partyId,candidateId,type,quryString);
			 if(annalysedData != null && annalysedData.size() > 0)
			 {
				 returnList = new ArrayList<SelectOptionVO>();
				 for (Object[] parms : annalysedData) {
					 SelectOptionVO selectOptionVO = new SelectOptionVO();
					 selectOptionVO.setId((Long)parms[0]);//partyId or candidate name
					 selectOptionVO.setName(parms[1].toString());//party name or candidate name
					 selectOptionVO.setLocation(parms[3].toString());//role name
					 selectOptionVO.setPerc((Double)parms[4]);
					 returnList.add(selectOptionVO);
				}
			 }
		 } 
		 catch (Exception e)
		 {
			LOG.error(" Exception Occured in debateAnalysisService method, Exception - ",e);
		 }
		 return returnList;
	 }
	 
	 
	 
	 /**
	  * This service is used for detting the sms question with options and percentage
	  * @return List<SelectOptionVO> returnList
	  */
	 public List<SelectOptionVO> getDebateSMSQuestions(String fromDateStr,String toDateStr,Long stateId)
	 {
		 List<SelectOptionVO> returnList = null;
		 try 
		 {
			 LOG.info("Enterd into getDebateSMSQuestions() in DebateService class");
			 SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
			 SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			 SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 List<Object[]> smsQuestionObj = debateSmsQuestionOptionDAO.getSmsQuestionDetails(sdf1.parse(fromDateStr),sdf1.parse(toDateStr),stateId);
			 Map<Long,List<SelectOptionVO>> questionMap = null;
			 List<SelectOptionVO> questionsList = null;
			 if(smsQuestionObj != null && smsQuestionObj.size() > 0)
			 {
				 returnList = new ArrayList<SelectOptionVO>();
				 questionMap = new HashMap<Long, List<SelectOptionVO>>();
				 for (Object[] parms : smsQuestionObj)
				 {
					 questionsList = questionMap.get((Long)parms[0]);
					 SelectOptionVO selectOptionVO = new SelectOptionVO();
					 if(questionsList == null)
					 {
						 questionsList = new ArrayList<SelectOptionVO>();
						 questionMap.put((Long)parms[0], questionsList);
					 }
					 selectOptionVO.setId((Long)parms[0]);//questionId
					 selectOptionVO.setName(parms[1].toString());//question
					 selectOptionVO.setLocation(parms[2].toString());//option
					 selectOptionVO.setPerc((Double)parms[3]);//percentage
					 selectOptionVO.setPartno(parms[4].toString());//channel
					 selectOptionVO.setUrl(sdf.format(parseFormat.parse(parms[5].toString())));//date
					 questionsList.add(selectOptionVO);
				 }
				 Set<Long> smsQuestionIds = questionMap.keySet();
				 if(smsQuestionIds != null && smsQuestionIds.size() > 0)
				 {
					 for (Long smsQuestionId : smsQuestionIds)
					 {
						 SelectOptionVO OptionVO = new SelectOptionVO();
						 List<SelectOptionVO> OptionList = new ArrayList<SelectOptionVO>();
						 List<SelectOptionVO> selOptionList = questionMap.get(smsQuestionId);
						 int i = 0;
						 for (SelectOptionVO selectOptionVO : selOptionList)
						 {
							 if(selectOptionVO.getName() != null)
							 {
								 if(i == 0)
								 {
									 OptionVO.setId(selectOptionVO.getId());
									 OptionVO.setName(StringEscapeUtils.unescapeJava(selectOptionVO.getName()));
									 OptionVO.setPartno(selectOptionVO.getPartno());
									 OptionVO.setUrl(selectOptionVO.getUrl());
								 }
								 SelectOptionVO selOptionVO = new SelectOptionVO();
								 
								 selOptionVO.setLocation(selectOptionVO.getLocation());
								 selOptionVO.setPerc(selectOptionVO.getPerc());
								 
								 OptionList.add(selOptionVO);
								 i++;
							 }
							 
						}
						 OptionVO.setSelectOptionsList(OptionList);
						 returnList.add(OptionVO);
					}
				 }
				
				 
			 }
		 } catch (Exception e)
		 {
			LOG.error(" Exception Occured in getDebateSMSQuestions method, Exception - ",e);
		 }
		 if(returnList != null && returnList.size() > 0)
		 {
			 String url = buildExcelForSmsPole(returnList);
			 returnList.get(0).setType(url);
		 }
		 
		 return returnList;
	 }
	 
	 
	 
	 public String buildExcelForSmsPole(List<SelectOptionVO> returnList){
			LOG.info("entered into buildExcelForSmsPole()in meetingService class.");
			String filename =null;
			try {
				
				if(returnList != null && returnList.size() > 0)
				{
					
					Random randomNumber = new Random();
					int number = randomNumber.nextInt(100000);
					 filename= "Reports"+"/"+"smsPole"+number+".xls";
					//path = "Reports"+"/"+"StateAbstract"+number+".xls";
				    FileOutputStream fileOut = new FileOutputStream(IConstants.STATIC_CONTENT_FOLDER_URL+filename);
				    
					HSSFWorkbook workbook = new HSSFWorkbook();// creating excel sheet
					
					HSSFSheet worksheet = workbook.createSheet(" SMS POLL REPORT ");		// sheet 1
					
					generateXlReportForStateMeeting(returnList,worksheet,workbook);
		
					workbook.write(fileOut);
					
					fileOut.close();
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised while buildExcelForSmsPole()in meetingService class.",e);
			}	
			return filename;
			

		}
	 
	 public String generateXlReportForStateMeeting(List<SelectOptionVO> returnList,HSSFSheet worksheet,HSSFWorkbook workbook)
		{
			LOG.info("entere into  generateXlReportForStateMeeting()in meetingService class.");
			String path = null;
				try
				   {
					HSSFFont font = workbook.createFont();
					font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
					font.setItalic(false);
					font.setFontHeight((short)240);
					HSSFCellStyle style = workbook.createCellStyle();
					style.setFont(font);
					HSSFRow  rowHead = worksheet.createRow((short) 0);
				    Cell cell = rowHead.createCell(0);
				    cell.setCellValue("DATE");
				    cell.setCellStyle(style);
					cell = rowHead.createCell(1);
					cell.setCellStyle(style);
					cell.setCellValue("CHANNEL");
					cell = rowHead.createCell(2);
					cell.setCellStyle(style);
					cell.setCellValue("QUESTION");
					cell = rowHead.createCell(3);
					cell.setCellStyle(style);
					cell.setCellValue("YES");
					cell = rowHead.createCell(4);
					cell.setCellStyle(style);
					cell.setCellValue("NO");
				    int rownum = 1;
				    for (SelectOptionVO selectOptionVO : returnList) 
				    {
				    	if(selectOptionVO.getName().length() > 0)
				    	{
				    		HSSFRow  row= worksheet.createRow((short)rownum);
						      
					        row.createCell((short)0).setCellValue(selectOptionVO.getUrl());
					        row.createCell((short)1).setCellValue(selectOptionVO.getPartno());
					        row.createCell((short)2).setCellValue(selectOptionVO.getName());
					        int count = 3;
					        for(SelectOptionVO selectOptionVO1 :selectOptionVO.getSelectOptionsList())
					        {
					        	row.createCell((short)count).setCellValue(selectOptionVO1.getPerc());
					        	count++;
					        }
					        rownum++;
				    	}
				    	
				    }    

				} 
			    catch (Exception e) 
			    {
			    	LOG.error("Exception raised while generateXlReportForStateMeeting()in meetingService class.",e);
			    }
				return path;
			}	
	 
	 /**
	  * This Service is used for getting the avg scals for each party participated in the debate for selected dates
	  * @param fromDate
	  * @param toDate
	  * @return List<SelectOptionVO> returnList
	  */
	 public List<SelectOptionVO> getDebateAnalysisByPartyForScaling(Date fromDate,Date toDate,Long stateId)
	 {
		 List<SelectOptionVO> returnList = null;
		 try 
		 {
			 LOG.info("Enterd into getDebateAnalysisByPartyForScaling() in DebateService class");
			 List<Object[]> scalesList = debateParticipantDAO.getDebateTotalScaleForEachParty(fromDate, toDate,stateId);
			 List<Object[]> debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachParty(fromDate, toDate,stateId);
			 if(scalesList != null && scalesList.size() > 0 && debateCountsList!= null && debateCountsList.size() > 0 )
			 {
				 returnList = new ArrayList<SelectOptionVO>();
				 Map<Long,Long> countMap = new HashMap<Long, Long>();//Map<partyId,debatesCount>
				 Map<Long,Double> scalesMap = new HashMap<Long, Double>();//Map<partyId,scalesCount>
				 Map<Long,String> partyMap = new HashMap<Long, String>();//Map<partyId,partyName>
				 for (Object[] parms : debateCountsList)
				 {
					 Long debateCount = countMap.get((Long)parms[0]);
					 if(debateCount == null)
					 {
						 countMap.put((Long)parms[0], (Long)parms[1]);
						 partyMap.put((Long)parms[0], parms[2].toString());
					 }
				 }
				 for (Object[] parms : scalesList)
				 {
					 Double scaleCount = scalesMap.get((Long)parms[0]);
					 if(scaleCount == null)
					 {
						 scalesMap.put((Long)parms[0], (Double)parms[1]);
						 
					 }
				 }
				 
				 Set<Long> partyIds = scalesMap.keySet();
				 for (Long partyId : partyIds) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId(partyId);
					//Double avg = scalesMap.get(partyId)/countMap.get(partyId);
					selectOptionVO.setConstituencyId(countMap.get(partyId));
					selectOptionVO.setPerc(Double.parseDouble(new BigDecimal((scalesMap.get(partyId))/countMap.get(partyId)).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
					selectOptionVO.setName(partyMap.get(partyId) != null ? partyMap.get(partyId) :"");
					returnList.add(selectOptionVO);
				}
			 }
		}
		catch (Exception e)
		{
			LOG.error(" Exception Occured in getDebateAnalysisByPartyForScaling method, Exception - ",e);
		}
		 if(returnList != null && returnList.size() > 0)
		 {
			 String url = buildExcelForParty(returnList);
			 returnList.get(0).setUrl(url);
		 }
		
		 return returnList;
		 
	 }
	 
	 
	 public String buildExcelForParty(List<SelectOptionVO> returnList){
			LOG.info("entered into buildExcelForSmsPole()in meetingService class.");
			String filename =null;
			try {
				
				if(returnList != null && returnList.size() > 0)
				{
					
					Random randomNumber = new Random();
					int number = randomNumber.nextInt(100000);
					 filename= "Reports"+"/"+"partyWise"+number+".xls";
					//path = "Reports"+"/"+"StateAbstract"+number+".xls";
				    FileOutputStream fileOut = new FileOutputStream(IConstants.STATIC_CONTENT_FOLDER_URL+filename);
				    
					HSSFWorkbook workbook = new HSSFWorkbook();// creating excel sheet
					
					HSSFSheet worksheet = workbook.createSheet(" PARTY WISE REPORT");		// sheet 1
					
					buildExcelForPartyWise(returnList,worksheet,workbook);
		
					workbook.write(fileOut);
					
					fileOut.close();
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised while buildExcelForSmsPole()in meetingService class.",e);
			}	
			return filename;
			

		}
	 
	 public String buildExcelForPartyWise(List<SelectOptionVO> returnList,HSSFSheet worksheet,HSSFWorkbook workbook)
		{
			LOG.info("entere into  generateXlReportForStateMeeting()in meetingService class.");
			String path = null;
				try
				   {
		      
					HSSFFont font = workbook.createFont();
					font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
					font.setItalic(false);
					font.setFontHeight((short)240);
					HSSFCellStyle style = workbook.createCellStyle();
					style.setFont(font);
					HSSFRow  rowHead = worksheet.createRow((short) 0);
				    Cell cell = rowHead.createCell(0);
				    cell.setCellValue("S.NO");
				    cell.setCellStyle(style);
					cell = rowHead.createCell(1);
					cell.setCellValue("PARTY");
					cell.setCellStyle(style);
					cell = rowHead.createCell(2);
					cell.setCellValue("COUNT/SCALE");
					cell.setCellStyle(style);
				    int rownum = 1;
				    for (SelectOptionVO selectOptionVO : returnList) 
				    {
				    	HSSFRow  row= worksheet.createRow((short)rownum);
				      
				        row.createCell((short)0).setCellValue(rownum);
				        row.createCell((short)1).setCellValue(selectOptionVO.getName());
				        row.createCell((short)2).setCellValue(selectOptionVO.getConstituencyId()+"/"+selectOptionVO.getPerc());
				        rownum++;
				    }    

				} 
			    catch (Exception e) 
			    {
			    	LOG.error("Exception raised while generateXlReportForStateMeeting()in meetingService class.",e);
			    }
				return path;
			}	
	 
	 /**
	  * This Service is used for getting candidate wise debate report for selected dates
	  * @param fromDate
	  * @param toDate
	  * @return  returnList
	  */
	 public List<SelectOptionVO> getDebateAnalysisBycandidateForScaling(Date fromDate,Date toDate,Long stateId)
	 {
		 List<SelectOptionVO> returnList = null;
		 try
		 {
			 LOG.info("Enterd into getDebateAnalysisBycandidateForScaling() in DebateService class");
			 List<Object[]> scalesList = debateParticipantDAO.getDebateTotalScaleForEachCandidate(fromDate, toDate,stateId);
			 List<Object[]> debateCountsList = debateParticipantDAO.getTotalDabtesCountsForEachCandidate(fromDate, toDate,stateId);
			 if(scalesList != null && scalesList.size() > 0 && debateCountsList!= null && debateCountsList.size() > 0 )
			 {
				 Map<Long,List<SelectOptionVO>> scalesMap = new HashMap<Long, List<SelectOptionVO>>();//Map<partyId,candidate wise scale total>
				 Map<Long,List<SelectOptionVO>> debateCountMap = new HashMap<Long, List<SelectOptionVO>>();//Map<partyId,candidate wise debate count>
				 Map<Long,Double> scaleCandidateMap = new HashMap<Long, Double>();//Map<candidateId,scaleCount>
				 Map<Long,Long> debateCandidateCount = new HashMap<Long, Long>();//Map<candidateId,debateCount>
				 Map<Long,List<SelectOptionVO>> partyMap = new HashMap<Long, List<SelectOptionVO>>();//Map<partyId,candidatesList>
				 Map<Long,String> candidateMap = new HashMap<Long, String>();
				 returnList = new ArrayList<SelectOptionVO>();
				 for (Object[] parms : scalesList)
				 {
					 List<SelectOptionVO> scalesCountList = scalesMap.get((Long)parms[0]);
					 SelectOptionVO selectOptionVO = new SelectOptionVO();
					 if(scalesCountList == null)
					 {
						 scalesCountList = new ArrayList<SelectOptionVO>();
						 scalesMap.put((Long)parms[0], scalesCountList);
					 }
					 selectOptionVO.setId((Long)parms[0]);//partId
					 selectOptionVO.setName(parms[1].toString());//partyName
					 selectOptionVO.setCount((Long)parms[2]);//candidateId
					 selectOptionVO.setLocation(parms[3].toString());//candidateName
					 selectOptionVO.setPerc((Double)parms[4]);//scale total
					 scaleCandidateMap.put((Long)parms[2], (Double)parms[4]);
					 scalesCountList.add(selectOptionVO);
				 }
				 for (Object[] parms : debateCountsList)
				 {
					 List<SelectOptionVO> debateCountList =  debateCountMap.get((Long)parms[0]);
					 SelectOptionVO selectOptionVO = new SelectOptionVO();
					 if(debateCountList == null)
					 {
						 debateCountList = new ArrayList<SelectOptionVO>();
						 debateCountMap.put((Long)parms[0], debateCountList);
					 }
					 selectOptionVO.setId((Long)parms[0]);//partId
					 selectOptionVO.setName(parms[1].toString());//partyName
					 selectOptionVO.setCount((Long)parms[2]);//candidateId
					 selectOptionVO.setLocation(parms[3].toString());//candidateName
					 selectOptionVO.setOrderId((Long)parms[4]);//debate count candidate wise
					 debateCandidateCount.put((Long)parms[2], (Long)parms[4]);
					 debateCountList.add(selectOptionVO);
				 }
				 
				 List<Object[]> candidatesList = debateParticipantDAO.getCanidatesListForDebateForSelectedDates(fromDate,toDate,stateId);
				 
				 if(candidatesList != null && candidatesList.size() > 0)
				 {
					 
					 for (Object[] parms : candidatesList)
					 {
						 List<SelectOptionVO> partiesList = partyMap.get((Long)parms[0]);
						 SelectOptionVO selectOptionVO = new SelectOptionVO();
						 if(partiesList == null)
						 {
							 partiesList = new ArrayList<SelectOptionVO>(); 
							 partyMap.put((Long)parms[0], partiesList);
						 }
						 selectOptionVO.setId((Long)parms[0]);//partId
						 selectOptionVO.setName(parms[1].toString());//partyName
						 selectOptionVO.setCount((Long)parms[2]);//candidateId
						 selectOptionVO.setLocation(parms[3].toString());//candidateName
						 partiesList.add(selectOptionVO);
						 candidateMap.put((Long)parms[2], parms[3].toString());
					}
				 }
				 
				 Set<Long> partyIds = partyMap.keySet();
				 for (Long partyId : partyIds)
				 {
					 SelectOptionVO partyVO = new SelectOptionVO();
					 List<SelectOptionVO> candidateList = new ArrayList<SelectOptionVO>();
					 List<SelectOptionVO> partiesList = partyMap.get(partyId);
					 if(partiesList != null && partiesList.size() > 0)
					 {
						 
						 for (SelectOptionVO selectOptionVO : partiesList) {
							SelectOptionVO candidateVO = new SelectOptionVO();
							Double scaleCount = scaleCandidateMap.get(selectOptionVO.getCount());
							Long debateCount = debateCandidateCount.get(selectOptionVO.getCount());
							if(scaleCount == 0)
							{
								candidateVO.setPerc(null);
							}
							else
							{
								candidateVO.setPerc(Double.parseDouble(new BigDecimal((scaleCount)/debateCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
							}
							if(debateCount == null)
							{
								candidateVO.setCount(null);
							}
							else
							{
								candidateVO.setCount(debateCount);
							}
							candidateVO.setName(selectOptionVO.getName());//partyName
							candidateVO.setLocation(selectOptionVO.getLocation());//candidateName
							candidateVO.setId(selectOptionVO.getId());//partyId
							candidateVO.setOrderId(selectOptionVO.getCount());
							candidateList.add(candidateVO);
						}
					 }
					 partyVO.setSelectOptionsList(candidateList);
					 returnList.add(partyVO);
				 }
				 
			 }	 
				 
			 
		 }
		 catch (Exception e)
		 {
			LOG.error(" Exception Occured in getDebateAnalysisBycandidateForScaling method, Exception - ",e);
		 }
		 if(returnList != null && returnList.size() > 0)
		 {
			 String url = buildExcelForCandidate(returnList);
			 returnList.get(0).setUrl(url);
		 }
		 return returnList;
	 }
	 
	 
	 public String buildExcelForCandidate(List<SelectOptionVO> returnList){
			LOG.info("entered into buildExcelForSmsPole()in meetingService class.");
			String filename =null;
			try {
				
				if(returnList != null && returnList.size() > 0)
				{
					
					Random randomNumber = new Random();
					int number = randomNumber.nextInt(100000);
					 filename= "Reports"+"/"+"candidateWise"+number+".xls";
					//path = "Reports"+"/"+"StateAbstract"+number+".xls";
				    FileOutputStream fileOut = new FileOutputStream(IConstants.STATIC_CONTENT_FOLDER_URL+filename);
				    
					HSSFWorkbook workbook = new HSSFWorkbook();// creating excel sheet
					
					HSSFSheet worksheet = workbook.createSheet(" CANDIDATE WISE REPORT ");		// sheet 1
					
					buildExcelForCandidateWise(returnList,worksheet,workbook);
		
					workbook.write(fileOut);
					
					fileOut.close();
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised while buildExcelForSmsPole()in meetingService class.",e);
			}	
			return filename;
			

		}
	 
	 public String buildExcelForCandidateWise(List<SelectOptionVO> returnList,HSSFSheet worksheet,HSSFWorkbook workbook)
		{
			LOG.info("entere into  generateXlReportForStateMeeting()in meetingService class.");
			String path = null;
				try
				   {
					HSSFFont font = workbook.createFont();
					font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
					font.setItalic(false);
					font.setFontHeight((short)240);
					HSSFCellStyle style = workbook.createCellStyle();
					style.setFont(font);

					HSSFRow  rowHead = worksheet.createRow((short) 0);
					Cell cellHead = rowHead.createCell(0);
					cellHead.setCellValue("S.NO");
					cellHead.setCellStyle(style);
					cellHead = rowHead.createCell(1);
					cellHead.setCellValue("CANDIDATE");
					cellHead.setCellStyle(style);
					cellHead = rowHead.createCell(2);
					cellHead.setCellValue("COUNT/SCALE");
					cellHead.setCellStyle(style);
				    int rownum = 1;
				    for (SelectOptionVO selectOptionVO : returnList) 
				    {
				    	HSSFRow  row1= worksheet.createRow((short)rownum);
				    	Cell cell = row1.createCell(0);
				    	cell.setCellValue(selectOptionVO.getSelectOptionsList().get(0).getName());
				    	cell.setCellStyle(style);
				    	worksheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 2));
				    	int no = 1;
				    	for(SelectOptionVO selectOptionVO1 : selectOptionVO.getSelectOptionsList())
				    	{
				    		rownum++;
				    		HSSFRow  row = worksheet.createRow((short)rownum);
					        row.createCell((short)0).setCellValue(no);
					        row.createCell((short)1).setCellValue(selectOptionVO1.getLocation());
					        row.createCell((short)2).setCellValue(selectOptionVO1.getCount()+"/"+selectOptionVO1.getPerc());
					        no++;
				    	}
				    	rownum++;
				    }    

				} 
			    catch (Exception e) 
			    {
			    	LOG.error("Exception raised while generateXlReportForStateMeeting()in meetingService class.",e);
			    }
				return path;
			}	
	 
	 /**
	  * This Service is used for debate search critera
	  * @param searchString
	  * @return List<SelectOptionVO> returnList
	  */
	 public List<SelectOptionVO> getSearchriteriaForDebate(String searchString)
	 {
		 List<SelectOptionVO> returnList = null;
		 try {
			 LOG.info("Enterd into getSearchriteriaForDebate() in DebateService class");
			 List<Object[]> searchResults = debateSubjectDAO.searchCriteriaForDebateSearch(searchString);
			 Map<Long,SelectOptionVO> debateMap = new HashMap<Long, SelectOptionVO>();//Map<debateId,debateDetails>
			 if(searchResults != null && searchResults.size() > 0)
			 {
				 returnList = new ArrayList<SelectOptionVO>();
				 SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				 for (Object[] objects : searchResults) {
					 SelectOptionVO selectOptionVO = debateMap.get((Long)objects[0]);
					 if(selectOptionVO == null)
					 {
						 selectOptionVO =  new SelectOptionVO();
						 selectOptionVO.setId((Long)objects[0]);//debateId
						 selectOptionVO.setName(StringEscapeUtils.unescapeJava(objects[1].toString()));// ndebate subject
						 selectOptionVO.setType(sdf.format(parseFormat.parse(objects[2].toString())));//debate date
						 debateMap.put((Long)objects[0], selectOptionVO);
						 returnList.add(selectOptionVO);
					 }
					 else
					 {
						 selectOptionVO.setName(selectOptionVO.getName() + "<br/>" +StringEscapeUtils.unescapeJava(objects[1].toString()));
					 }
					 
				}
			 }
		} catch (Exception e) {
			LOG.error(" Exception Occured in getSearchriteriaForDebate method, Exception - ",e);
		}
		 return returnList;
	 }
		
	 
	 public ResultStatus createCandidate(Long partyId,String name,Long stateId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			 LOG.info("Enterd into createCandidate() in DebateService class");
			 List<Long> candodates = candidateDAO.getCandidateExistesOrNot(partyId, name);
			 if(candodates.size() == 0)
			 {
				 Candidate candidate = new Candidate();
				 Party party = partyDAO.get(partyId);
				 candidate.setParty(party);
				 candidate.setLastname(name);
				 candidate.setIsDebateCandidate("Y");
				 candidate.setStateId(stateId);
				 candidate = candidateDAO.save(candidate);
				 if(candidate != null)
				 {
					 resultStatus.setResultCode(0); 
				 }
			 }
			 else
			 {
				 resultStatus.setResultCode(2);
			 }
		} catch (Exception e) {
			resultStatus.setResultCode(1);
			LOG.error(" Exception Occured in createCandidate method, Exception - ",e);
		}
		 return resultStatus;
	 }
	 
	 /*public DebateVO getDebateDetailsForSelectedDebate(Long debateId)
	 {
		 DebateVO debateVO = null;
		try
		{
			LOG.info("Enterd into getDebateDetailsForSelectedDebate() in DebateService class");
			List<Object[]> debateDetails = debateDAO.getDebateDetailsForSelectedDebate(debateId);
			if(debateDetails != null && debateDetails.size() > 0)
			{
				debateVO = new DebateVO();
				SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				for (Object[] parms : debateDetails) {
					debateVO.setDebateId((Long)parms[0]);
					debateVO.setStartTime(sdf.format(parseFormat.parse(parms[1].toString())));
					debateVO.setEndTime(sdf.format(parseFormat.parse(parms[2].toString())));
					debateVO.setChannelId((Long)parms[3]);
					debateVO.setChannelName(parms[4].toString());
					debateVO.setDebateSummery(parms[5].toString());
				}
				List<Object[]> observersersList = debateObserverDAO.getObsersListForDebate(debateId);
				if(observersersList != null && observersersList.size() > 0)
				{
					List<SelectOptionVO> observersList = new ArrayList<SelectOptionVO>();
					for (Object[] objects : observersersList) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)objects[1]);
						selectOptionVO.
						observersList.add(selectOptionVO);
					}
				}
				List<Object[]> subjectDetails = debateSubjectDAO.getDebateSubjectDetails(debateId);
				if(subjectDetails != null && subjectDetails.size() > 0)
				{
					List<String> subjectList = new ArrayList<String>();
					for (Object[] parms : subjectDetails) {
						
						subjectList.add(parms[0].toString());
					}
					debateVO.setDebateNames(subjectList);
				}
			}
			
		}
		catch (Exception e)
		{
			LOG.error(" Exception Occured in getDebateDetailsForSelectedDebate method, Exception - ",e);
		}
		return debateVO;
	 }*/
	 
	 public List<DebateVO> getTotalAttendedDebatesOfCadre(Long tdpCadreId,Long stateId){
		 
		 List<DebateVO> finalList = new ArrayList<DebateVO>();
		 
		 try{		
			 //0.debateId,1.candidateId
			 List<Object[]> list = debateParticipantDAO.getTotalAttendedDebatesOfCadre(tdpCadreId,stateId);
			  			 
				if(list !=null && list.size()>0){
					for (Object[] long1 : list) {						
						DebateVO VO = getDebateDetailsForSelected(long1[0] !=null ? (Long)long1[0]:0l,long1[2] !=null ? (Long)long1[2]:0l);
						if(VO !=null){
							finalList.add(VO);
						}		
						
						Long candidateId = (Long) list.get(0)[1];
						finalList.get(0).setCandidateId(candidateId);
						
					}
					finalList.get(0).setTotalDebates(Long.parseLong(Integer.toString(list.size())));
										
				}			 
			 
		 }catch (Exception e) {
			 LOG.error(" Exception Occured in getTotalAttendedDebatesOfCadre method, Exception - ",e);
		}
		 return finalList;
		 
	 }
	 
	 public List<DebatePartyWiseCountVO> getPartyWiseOverAllPerformance(Long cadreId,Long stateId){
		 
		 List<DebatePartyWiseCountVO> finalList = new ArrayList<DebatePartyWiseCountVO>(0);
		 
		 try{
			 
			 List<Long>  partyIdsList = new ArrayList<Long>();
			 partyIdsList.add(IConstants.TDP_PARTY_ID);
			 
			List<Long> candidatesIds = tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
			finalList = debateAnalysisService.getPartyWiseOverAllPerformance(null,null,null,partyIdsList,candidatesIds,null);
			 
			 
		 }catch (Exception e) {
			 LOG.error(" Exception Occured in getPartyWiseOverAllPerformance method, Exception - ",e);
		}
		 return finalList;
	 }
	 
	 public List<DebateTopicVO> getPartyWiseStrongAndWeakTopicAndCandidates(Long cadreId){
		 List<DebateTopicVO> finalList = new ArrayList<DebateTopicVO>(0);
		 try{
			 
			 List<Long>  partyIdsList = new ArrayList<Long>();
			 partyIdsList.add(IConstants.TDP_PARTY_ID);
			 
			List<Long> candidatesIds = tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
			 
			finalList = debateAnalysisService.getPartyWiseStrongAndWeakTopicAndCandidates(null,null,null,partyIdsList,candidatesIds,null);
			 
		 }catch (Exception e) {
			 LOG.error(" Exception Occured in getPartyWiseStrongAndWeakTopicAndCandidates method, Exception - ",e);
		}
		 
		 return finalList;
	 }
	 
	 public Long getTotalAttendedDebatesOfCadreNew(Long tdpCadreId){
		 
		 try{			 
			 Long debateCount = debateParticipantDAO.getTotalAttendedDebatesOfCadreNew(tdpCadreId);			 
			 return debateCount; 
			 
		 }catch(Exception e){
			 LOG.error(" Exception Occured in getTotalAttendedDebatesOfCadreNew method, Exception - ",e);
		 }
		 return null;
	 }
	 
	 public void convertDebateContentToUnicode()
	 {
		 try{
			 List<Debate> debateList = debateDAO.getAll();
			 List<DebateSubject> subjectList = debateSubjectDAO.getAll();
			 
			 if(debateList != null && debateList.size() > 0)
			 {
				 for(Debate debate : debateList)
				 {
					 debate.setSummaryUnicode(StringEscapeUtils.unescapeJava(debate.getSummary()));
					 debateDAO.save(debate);
				 }
			 }
			 if(subjectList != null && subjectList.size() > 0)
			 {
				 for(DebateSubject debateSubject : subjectList)
				 {
					 debateSubject.setSubjectUnicode(StringEscapeUtils.unescapeJava(debateSubject.getSubject()));
					 debateSubjectDAO.save(debateSubject);
				 }
			 }
		 }catch(Exception e)
		 {
			 LOG.error("Exception occured in convertDebateContentToUnicode Method",e);
		 }
	 }
	 public List<DebateTopicVO> getEachCharacterWiseMaxScale(){
		 List<DebateTopicVO> finalList = new ArrayList<DebateTopicVO>(0);
		 try{
			 
			List<Object[]> characterOfScaleList = characteristicsDAO.getEachCharacterWiseMaxScale();
			if(characterOfScaleList != null && characterOfScaleList.size()>0){
				for(Object[] param : characterOfScaleList){
					DebateTopicVO VO = new DebateTopicVO();
					VO.setCharacterSticsId(param[0] !=null ? (Long)param[0]:0l);
					VO.setMaxScale(param[1] !=null ? (Long)param[1]:0l);
					finalList.add(VO);
				}
			}
			 
		 }catch (Exception e) {
			 LOG.error(" Exception Occured in getEachCharacterWiseMaxScale method, Exception - ",e);
		}
		 
		 return finalList;
	 }
	 public Double getAvgDetailsOfDebate(Double charactersSum,Long charactersticId){
			Double avg = 0.00;
			try {
				Long sum = characteristicsDAO.getSumOfCharacters(charactersticId);
				if(charactersSum !=null && charactersSum>0){
					avg = Double.parseDouble(new BigDecimal((charactersSum/sum)*(IConstants.DEBATE_AVG_VALUE)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
				}			
			} catch (Exception e) {
				LOG.error("Exception raised at getAvgDetailsOfDebate() method of CoreDashboardMainService",e);
			}
			return avg;
		}
	
}
