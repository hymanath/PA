package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
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
import com.itgrids.partyanalyst.dao.ITelecastTypeDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.DebateDetailsVO;
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
import com.itgrids.partyanalyst.model.TelecastType;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.service.IDebateService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class DebateService implements IDebateService{
	
	private static final Logger LOG = Logger.getLogger(DebateService.class); 
	private IDebateDAO debateDAO;
	private IDebateSmsQuestionOptionDAO debateSmsQuestionOptionDAO;
	private IObserverDAO observerDAO;
	private IChannelDAO  channelDAO;
	private ITelecastTypeDAO telecastTypeDAO;
	private IDebateRolesDAO  debateRolesDAO;
	private IDebateSubjectDAO debateSubjectDAO;
	private IDebateObserverDAO  debateObserverDAO;
	private ICharacteristicsDAO  characteristicsDAO;
	private IDebateParticipantDAO debateParticipantDAO;
	private IDebateParticipantRoleDAO debateParticipantRoleDAO;
	private IDebateParticipantExceptedRoleDAO  debateParticipantExceptedRoleDAO;
	private IDebateParticipantCharcsDAO  debateParticipantCharcsDAO;
	private IDebateSmsQuestionDAO debateSmsQuestionDAO;
	private IDebateQuestionsDAO debateQuestionsDAO ;
	private IDebateQuestionAnswerDAO  debateQuestionAnswerDAO;
	private TransactionTemplate transactionTemplate;
	private IPartyDAO partyDAO;
	private ICandidateDAO candidateDAO;
	private IUserDAO userDAO;
	private IDebateReportDAO debateReportDAO;

	
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
					  
					  Debate debate = new Debate();
					  Channel channel =  channelDAO.get(debateDetailsVO.getChannelId());
					  if(channel != null)
					  {
						 debate.setChannel(channel); 
					  }
					  TelecastType telecastType = telecastTypeDAO.get(debateDetailsVO.getTelecasteTypeId());
					  if(telecastType != null)
					  {
						  debate.setTelecastType(telecastType);  
					  }
					  debate.setSummary(escapeUnicode(StringEscapeUtils.escapeJava(debateDetailsVO.getDebateSummery())));
					  debate.setStartTime(debateDetailsVO.getStartDate());
					  debate.setEndTime(debateDetailsVO.getEndDate());
					  debate.setIsDeleted("N");
					  DateUtilService currentDate = new DateUtilService();
					  debate.setCreatedDate(currentDate.getCurrentDateAndTime());
					  debate=debateDAO.save(debate);
					  List<SelectOptionVO> subjectsList = debateDetailsVO.getSubjectList();
					  if(subjectsList != null && subjectsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : subjectsList) {
							DebateSubject debateSubject = new DebateSubject();
							debateSubject.setSubject(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
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
					  if(participentList != null && participentList.size() > 0)
					  {
						  
						  for (ParticipantVO participantVO : participentList)
						  {
							  DebateParticipant debateParticipant = new DebateParticipant();
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
							  debateParticipant.setSummary(escapeUnicode(StringEscapeUtils.escapeJava(participantVO.getSummery())));
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
						}
						  
					 }
					 
					  List<SelectOptionVO> questionsList = debateDetailsVO.getQuestionsList();
					  if(questionsList != null && questionsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : questionsList) {
							DebateQuestionAnswer debateQuestionAnswer = new DebateQuestionAnswer();
							debateQuestionAnswer.setAnswer(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
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
							debateSmsQuestion.setQuestion(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
							debateSmsQuestion.setIsDeleted("N");
							debateSmsQuestion = debateSmsQuestionDAO.save(debateSmsQuestion);
						
					  }
					  List<SelectOptionVO> smsOptionsList = debateDetailsVO.getSmaOptionsList();
					  if(smsOptionsList != null && smsOptionsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : smsOptionsList) {
							DebateSmsQuestionOption debateSmsQuestionOption = new DebateSmsQuestionOption();
							debateSmsQuestionOption.setOption(escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName())));
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
	public DebateVO getDebateDetailsForSelected(Long debateId)
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
			List<ParticipantVO> particepentDetailsList = null;
			//List<SelectOptionVO> scalesList = null;
			//List<SelectOptionVO> rolesList = null;
			// here we are getting all details of debate
			SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat printFormat = new SimpleDateFormat("HH.mm");
			
			//System.out.println(printFormat.format(date)); // prints 09:30:51
			List<Object[]> debateDetailsList = debateDAO.getDebateDetailsForSelectedDebate(debateId);
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
				//debateVO.setStartTime(debateDetails[1] != null ? printFormat.format(parseFormat.parse(debateDetails[1].toString())) :"");
				//debateVO.setEndTime(debateDetails[2] != null ? printFormat.format(parseFormat.parse(debateDetails[2].toString())) :"");
				debateVO.setEndTime(endTime);
				debateVO.setDate(debateDetails[1] != null ? sdf.format(parseFormat.parse(debateDetails[1].toString())) :"");
				debateVO.setChannelId(debateDetails[3] != null ? (Long)debateDetails[3] :0l);
				debateVO.setChannelName(debateDetails[4] != null ? debateDetails[4].toString() :"");
				debateVO.setTelecastTypeId(debateDetails[5] != null ? (Long)debateDetails[5] :0l);
				debateVO.setTelecastTime(debateDetails[6] != null ? debateDetails[6].toString() :"");
				debateVO.setDebateSummery(debateDetails[7] != null ? StringEscapeUtils.unescapeJava(debateDetails[7].toString()) :"");
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
			List<Object[]> debatePaticepentDetails = debateParticipantDAO.getDebatePaticepantsAndSummeryDetails(debateId);
			if(debatePaticepentDetails != null && debatePaticepentDetails.size() > 0)
			{
				debatePaticepentDetailsList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : debatePaticepentDetails)
				{
					SelectOptionVO paticepentSummery = new SelectOptionVO();
					paticepentSummery.setType(parms[1] != null ? parms[1].toString() :"");//party
					paticepentSummery.setLocation(parms[0] != null ? parms[0].toString() :"");//candidate
					paticepentSummery.setName(parms[2] != null ? StringEscapeUtils.unescapeJava(parms[2].toString()) :"");//summery
					debatePaticepentDetailsList.add(paticepentSummery);
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
					selectOptionVO.setPerc(parms[5] != null ?(Double)parms[5]: 0.0);//rating
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
					//olesList.add(selectOptionVO);
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
						expRoleList.add(debateExpRole);
					}
				}
				debateVO.setNoTdpLeaders(Long.valueOf(expRolesMap.size()));
				Set<Long> candidatesSet = particepentsMap.keySet();
				// here we are processing the each candidate wise debate scaling , charactes, party etc...
				for (Long candidateId : candidatesSet)
				{
					ParticipantVO participantVO = new ParticipantVO();
					SelectOptionVO paticiVO = particepentsMap.get(candidateId);
					participantVO.setName(paticiVO.getLocation());
					participantVO.setPartyName(paticiVO.getName());
					List<SelectOptionVO> scopesList = charactesMap.get(candidateId);
					//List<SelectOptionVO> scopesList = chatesVO.getSelectOptionsList();
					List<SelectOptionVO> scopeList = new ArrayList<SelectOptionVO>();
					for (SelectOptionVO scopesVOVO : scopesList)
					{
						SelectOptionVO scopesVO = new SelectOptionVO();
						scopesVO.setName(scopesVOVO.getName());
						scopesVO.setPerc(scopesVOVO.getPerc());
						scopeList.add(scopesVO);
					}
					List<SelectOptionVO> roleList = rolesMap.get(candidateId);
					if(roleList != null && roleList.size() > 0)
					{
						List<SelectOptionVO> roleDetailsList = new ArrayList<SelectOptionVO>();
						Long count = 0l;
						StringBuffer periRole = new StringBuffer();
						for (SelectOptionVO selectOptionVO : roleList) {
							count ++;
							SelectOptionVO debateRoleVO = new SelectOptionVO();
							debateRoleVO.setName(selectOptionVO.getName());
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
					List<SelectOptionVO> exproles = expRolesMap.get(candidateId);
					List<SelectOptionVO> expList = null;
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
				for (Object[] parms : debateObservers) {
					if(parms[0] != null)
					{
						String observer = parms[0].toString();
						observerList.add(observer);
					}
				}
				debateVO.setObservorsList(observerList);
			}
		}
		catch (Exception e)
		{
			LOG.error("Error occured in getDebateDetailsForSelected method in DebateService class",e);
		}
		return debateVO;
	}
	
	/**
	 * This service is used for getting the channel details
	 * @return List<SelectOptionVO> channelDetails
	 */
	public List<SelectOptionVO> getChannelDetails()
	{
		List<SelectOptionVO> channelDetails = null;
		SelectOptionVO selectOptionVO;
		List<Channel> channelDetail = channelDAO.getChannelDetails();
		if(channelDetail != null && channelDetail.size() > 0)
		{
			channelDetails = new ArrayList<SelectOptionVO>();
			for(Channel param:channelDetail){
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(new Long(param.getChannelId()));
				selectOptionVO.setName(param.getChannelName());
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
		List<TelecastType> telecastTimeDetails = telecastTypeDAO.getTelecastTimeDetails();
		for(TelecastType param:telecastTimeDetails){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(param.getTelecastTypeId()));
			selectOptionVO.setName(param.getName());
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
		List<Observer> observerDetailsVal = observerDAO.getObserverDetails();
		for(Observer param:observerDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(param.getObserverId()));
			selectOptionVO.setName(param.getObserverName());
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
		List<DebateQuestions> debateDetailsVal = debateQuestionsDAO.getDebateQuestionDetails();
		for(DebateQuestions param:debateDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(param.getDebateQuestionsId()));
			selectOptionVO.setName(param.getQuestion());
			debateDetails.add(selectOptionVO);
		}
		return debateDetails;
	}
	
	public List<SelectOptionVO> getDebateSmsQuestionDetails()
	{
		List<SelectOptionVO> debateSmsDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<DebateSmsQuestion> debateSmsDetailsVal = debateSmsQuestionDAO.getDebateSmsQuestionDetails();
		for(DebateSmsQuestion param:debateSmsDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(param.getDebateSmsQuestionId()));
			selectOptionVO.setName(param.getQuestion());
			debateSmsDetails.add(selectOptionVO);
		}
		return debateSmsDetails;
	}
	
	public List<SelectOptionVO> getDebateParticipantRoleDetails()
	{
		List<SelectOptionVO> debateParticipantRoleDetails = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO;
		List<DebateParticipantRole> debateParticipantRoleDetailsVal = debateParticipantRoleDAO.getDebateParticipantRoleDetails();
		for(DebateParticipantRole param:debateParticipantRoleDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(param.getDebateParticipantRoleId()));
			selectOptionVO.setName(param.getDebateRoles().getName());
			debateParticipantRoleDetails.add(selectOptionVO);
		}
		return debateParticipantRoleDetails;
	}
	
	public ResultStatus saveNewRole(final Long userId,final String newRole)
	{
		LOG.info("Enterd into saveNewRole method in DebateService class");
		ResultStatus isSaved = new ResultStatus();
		try {
				Long count = debateRolesDAO.checkForExists(StringEscapeUtils.escapeJava(newRole));
				if(count == 0)
				{
					if(userId != null && newRole != null){
						DebateRoles debateRoles = new DebateRoles();
						debateRoles.setName(escapeUnicode(StringEscapeUtils.escapeJava(newRole)).trim());
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
		List<Characteristics> CharacteristicsDetailsVal = characteristicsDAO.getCharacteristicsDetails();
		for(Characteristics param:CharacteristicsDetailsVal){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(param.getCharacteristicsId()));
			selectOptionVO.setName(param.getName());
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
	 
	 public String saveDebateReportForPdf( Long userId, Long debateId, String description, String path)
	 {
		  String string = "invalid";
		 try {
			 LOG.info("Enterd into saveDebateReportForPdf() in DebateService class");
			 
			
						
						String key = debateReportDAO.getDebateDatils(userId, debateId);
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
	 
	 public List<SelectOptionVO> getDebateDetailsForSelectedDates(Date fromDate,Date toDate)
	 {
		 List<SelectOptionVO> returnList = null;
		 try {
			 LOG.info("Enterd into getDebateDetailsForSelectedDates() in DebateService class");
			 List<Object[]> debateDetails = debateSubjectDAO.getDebateDetalsForSelectedDates(fromDate,toDate);
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
			 
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDebateDetailsForSelectedDates method, Exception - ",e);
		}
		 return returnList;
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
	 
	 public List<SelectOptionVO> getCandidatesForDebate(Long partyId)
	 {
		 List<SelectOptionVO> returnList = null;
		 try {
			 LOG.info("Enterd into getCandidatesForDebate() in DebateService class");
			 List<Object[]> candidatesList = candidateDAO.getCandidatesForDebate(partyId);
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
		} catch (Exception e) {
			LOG.error(" Exception Occured in getCandidatesForDebate method, Exception - ",e);
		}
		 return returnList;
	 }
}
