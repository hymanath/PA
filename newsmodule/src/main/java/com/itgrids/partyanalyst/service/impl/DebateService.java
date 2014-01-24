package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.itgrids.partyanalyst.dao.IDebateRolesDAO;
import com.itgrids.partyanalyst.dao.IDebateSmsQuestionDAO;
import com.itgrids.partyanalyst.dao.IDebateSmsQuestionOptionDAO;
import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.dao.IObserverDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.ITelecastTypeDAO;
import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ParticipantVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Channel;
import com.itgrids.partyanalyst.model.Debate;
import com.itgrids.partyanalyst.model.DebateObserver;
import com.itgrids.partyanalyst.model.DebateParticipant;
import com.itgrids.partyanalyst.model.DebateParticipantExpectedRole;
import com.itgrids.partyanalyst.model.DebateParticipantRole;
import com.itgrids.partyanalyst.model.DebateQuestionAnswer;
import com.itgrids.partyanalyst.model.DebateQuestions;
import com.itgrids.partyanalyst.model.DebateRoles;
import com.itgrids.partyanalyst.model.DebateSmsQuestion;
import com.itgrids.partyanalyst.model.DebateSmsQuestionOption;
import com.itgrids.partyanalyst.model.DebateSubject;
import com.itgrids.partyanalyst.model.Observer;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.TelecastType;
import com.itgrids.partyanalyst.service.IDebateService;

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
	private TransactionTemplate transactionTemplate = null;
	private IPartyDAO partyDAO;
	private ICandidateDAO candidateDAO;
	

	
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
					  debate.setSummary(debateDetailsVO.getDebateSummery());
					  debate.setStartTime(debateDetailsVO.getStartDate());
					  debate.setEndTime(debateDetailsVO.getEndDate());
					  
					  List<SelectOptionVO> subjectsList = debateDetailsVO.getSubjectList();
					  if(subjectsList != null && subjectsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : subjectsList) {
							DebateSubject debateSubject = new DebateSubject();
							debateSubject.setSubject(selectOptionVO.getName());
							debateSubject.setDebate(debate);
							debateSubjectDAO.save(debateSubject);
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
							debateObserverDAO.save(debateObserver);
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
							  debateParticipant.setSummary(participantVO.getSummery());
							  List<SelectOptionVO> rolesList = participantVO.getRoleList();
							  if(rolesList != null && rolesList.size() > 0)
							  {
								  for (SelectOptionVO selectOptionVO : rolesList) {
									DebateParticipantRole debateParticipantRole = new DebateParticipantRole();
									debateParticipantRole.setDebateParticipant(debateParticipant);
									DebateRoles debateRoles = debateRolesDAO.get(selectOptionVO.getId());
									debateParticipantRole.setDebateRoles(debateRoles);
									debateParticipantRoleDAO.save(debateParticipantRole);
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
									debateParticipantExceptedRoleDAO.save(debateParticipantExpectedRole);
								}
							  }
						}
						  
					 }
					 
					  List<SelectOptionVO> questionsList = debateDetailsVO.getQuestionsList();
					  if(questionsList != null && questionsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : questionsList) {
							DebateQuestionAnswer debateQuestionAnswer = new DebateQuestionAnswer();
							debateQuestionAnswer.setAnswer(selectOptionVO.getName());
							debateQuestionAnswer.setDebate(debate);
							DebateQuestions debateQuestions = debateQuestionsDAO.get(selectOptionVO.getId());
							if(debateQuestions != null)
							{
								debateQuestionAnswer.setDebateQuestions(debateQuestions);
							}
							debateQuestionAnswerDAO.save(debateQuestionAnswer);
						}
					  }
					  
					  List<SelectOptionVO> smsQuestionsList = debateDetailsVO.getSmsQuestionList();
					  if(smsQuestionsList != null && smsQuestionsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : smsQuestionsList) {
							DebateSmsQuestion debateSmsQuestion = new DebateSmsQuestion();
							debateSmsQuestion.setDebate(debate);
							debateSmsQuestion.setQuestion(selectOptionVO.getName());
							debateSmsQuestion.setIsDeleted("N");
							debateSmsQuestionDAO.save(debateSmsQuestion);
						}
					  }
					  List<SelectOptionVO> smsOptionsList = debateDetailsVO.getSmaOptionsList();
					  if(smsOptionsList != null && smsOptionsList.size() > 0)
					  {
						  for (SelectOptionVO selectOptionVO : smsOptionsList) {
							DebateSmsQuestionOption debateSmsQuestionOption = new DebateSmsQuestionOption();
							debateSmsQuestionOption.setOption(selectOptionVO.getName());
							DebateSmsQuestion debateSmsQuestion = debateSmsQuestionDAO.get(selectOptionVO.getId());
							if(debateSmsQuestion != null)
							{
								debateSmsQuestionOption.setDebateSmsQuestion(debateSmsQuestion);
							}
							debateSmsQuestionOption.setPercantage(selectOptionVO.getPerc());
							debateSmsQuestionOptionDAO.save(debateSmsQuestionOption);
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
			List<SelectOptionVO> scalesList = null;
			List<SelectOptionVO> rolesList = null;
			// here we are getting all details of debate
			Object[] debateDetails = debateDAO.getDebateDetailsForSelectedDebate(debateId).get(0);
			if(debateDetails != null )
			{
				debateVO.setDebateId(debateDetails[0] != null ? (Long)debateDetails[0]:0l);
				debateVO.setStartTime(debateDetails[1] != null ? debateDetails[1].toString() :"");
				debateVO.setEndTime(debateDetails[2] != null ? debateDetails[2].toString() :"");
				debateVO.setChannelId(debateDetails[3] != null ? (Long)debateDetails[3] :0l);
				debateVO.setChannelName(debateDetails[4] != null ? debateDetails[4].toString() :"");
				debateVO.setTelecastTypeId(debateDetails[5] != null ? (Long)debateDetails[5] :0l);
				debateVO.setTelecastTime(debateDetails[6] != null ? debateDetails[6].toString() :"");
				debateVO.setDebateSummery(debateDetails[7] != null ? debateDetails[7].toString() :"");
			}
			// here we are getting the all details of the sma question and answer with percentage details
			List<Object[]> debateSmsDetails = debateSmsQuestionOptionDAO.getDebateSmsQuestionsForSelectedDebate(debateId);
			if(debateSmsDetails != null && debateSmsDetails.size() > 0)
			{
				debateSmsDetailsList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : debateSmsDetails)
				{
					SelectOptionVO debateSms = new SelectOptionVO();
					debateSms.setName(parms[4] != null ? parms[4].toString() :"");//question
					debateSms.setId(parms[3] != null ? (Long)parms[3] :0l);//questionid
					debateSms.setType(parms[1] != null ? parms[1].toString() :"");//option
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
					paticepentSummery.setName(parms[2] != null ? parms[2].toString() :"");//summery
					debatePaticepentDetailsList.add(paticepentSummery);
				}
				debateVO.setCandidateSummery(debatePaticepentDetailsList);
			}
			//here we are getting the all details of the debate particepent candidate characers
			List<Object[]> dabateCharcsList = debateParticipantCharcsDAO.getDebateCharcsDetails(debateId);
			// here we are getting the all particepents role details
			List<Object[]> debateRolesList  = debateParticipantRoleDAO.getParticepentRoles(debateId);
			if(dabateCharcsList != null && dabateCharcsList.size() > 0 && debatePaticepentDetails != null && debatePaticepentDetails.size() > 0 && debateRolesList != null && debateRolesList.size() > 0)
			{
				particepentDetailsList = new ArrayList<ParticipantVO>();
				Map<Long, SelectOptionVO> particepentsMap = new HashMap<Long, SelectOptionVO>();//Map<candidate,partydetails>
				Map<Long,SelectOptionVO> charactesMap = new HashMap<Long, SelectOptionVO>();//Map<candidate,scaleDetails>
				Map<Long,SelectOptionVO> rolesMap = new HashMap<Long, SelectOptionVO>();//Map<candidate,roledetails>
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
					SelectOptionVO charactrsDetails = charactesMap.get((Long)parms[0]);
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					if(charactrsDetails == null)
					{
						scalesList = new ArrayList<SelectOptionVO>();
						charactrsDetails = new SelectOptionVO();
						charactesMap.put((Long)parms[0], charactrsDetails);
					}
					selectOptionVO.setName(parms[4] != null ?parms[4].toString() :"");//scale
					selectOptionVO.setPerc(parms[5] != null ?(Double)parms[5]: 0.0);//rating
					scalesList.add(selectOptionVO);
					charactrsDetails.setSelectOptionsList(scalesList);
				}
				// here we are processing the candidate role participated in debate
				for(Object[] parms : debateRolesList)
				{
					SelectOptionVO roleDetails = rolesMap.get((Long)parms[0]);
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					if(roleDetails == null)
					{
						rolesList = new ArrayList<SelectOptionVO>();
						roleDetails = new SelectOptionVO();
						rolesMap.put((Long)parms[0], roleDetails);
					}
					selectOptionVO.setName(parms[4] != null ?parms[4].toString() :"");//role
					rolesList.add(selectOptionVO);
					roleDetails.setSelectOptionsList(rolesList);
				}
				Set<Long> candidatesSet = particepentsMap.keySet();
				// here we are processing the each candidate wise debate scaling , charactes, party etc...
				for (Long candidateId : candidatesSet)
				{
					ParticipantVO participantVO = new ParticipantVO();
					SelectOptionVO paticiVO = particepentsMap.get(candidateId);
					participantVO.setName(paticiVO.getLocation());
					participantVO.setPartyName(paticiVO.getName());
					SelectOptionVO chatesVO = charactesMap.get(candidateId);
					List<SelectOptionVO> scopesList = chatesVO.getSelectOptionsList();
					List<SelectOptionVO> scopeList = new ArrayList<SelectOptionVO>();
					for (SelectOptionVO scopesVOVO : scopesList)
					{
						SelectOptionVO scopesVO = new SelectOptionVO();
						scopesVO.setName(scopesVOVO.getName());
						scopesVO.setPerc(scopesVOVO.getPerc());
						scopeList.add(scopesVO);
					}
					SelectOptionVO roleVO = rolesMap.get(candidateId);
					List<SelectOptionVO> roleList = roleVO.getSelectOptionsList();
					List<SelectOptionVO> roleDetailsList = new ArrayList<SelectOptionVO>();
					for (SelectOptionVO selectOptionVO : roleList) {
						SelectOptionVO debateRoleVO = new SelectOptionVO();
						debateRoleVO.setName(selectOptionVO.getName());
						roleDetailsList.add(debateRoleVO);
					}
					participantVO.setRoleList(roleDetailsList);
					participantVO.setScaleList(scopeList);
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
						questionAnswer.setName(parms[1] != null ? parms[1].toString() :"");//Answer
						questionAnswer.setLocation(parms[0] != null ? parms[0].toString() :"");//Question
						debateQuestionDetailsList.add(questionAnswer);
				}
				debateVO.setQuestionAnswersList(debateQuestionDetailsList);
				
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
					debateExpRole.setLocation(parms[0] != null ? parms[0].toString() :"");//role
					debateExpRolesList.add(debateExpRole);
				}
				debateVO.setDebateExpRolesList(debateExpRolesList);
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
						String subject = parms[0].toString();
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
}
