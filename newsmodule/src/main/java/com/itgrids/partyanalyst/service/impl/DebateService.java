package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

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
import com.itgrids.partyanalyst.dao.ITelecastTypeDAO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ParticipantVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IDebateService;

public class DebateService implements IDebateService{
	
	private static final Logger LOG = Logger.getLogger(DebateService.class); 
	private IDebateDAO debateDAO;
	private IDebateSmsQuestionOptionDAO debateSmsQuestionOptionDAO;
	private IObserverDAO observerDAO;
	private IChannelDAO  ChannelDAO;
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
		ChannelDAO = channelDAO;
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
			List<Object[]> dabateCharcsList = debateParticipantCharcsDAO.getDebateCharcsDetails(debateId);
			List<Object[]> debateRolesList  = debateParticipantRoleDAO.getParticepentRoles(debateId);
			if(dabateCharcsList != null && dabateCharcsList.size() > 0 && debatePaticepentDetails != null && debatePaticepentDetails.size() > 0 && debateRolesList != null && debateRolesList.size() > 0)
			{
				particepentDetailsList = new ArrayList<ParticipantVO>();
				Map<Long, SelectOptionVO> particepentsMap = new HashMap<Long, SelectOptionVO>();//Map<candidate,partydetails>
				Map<Long,SelectOptionVO> charactesMap = new HashMap<Long, SelectOptionVO>();//Map<candidate,scaleDetails>
				Map<Long,SelectOptionVO> rolesMap = new HashMap<Long, SelectOptionVO>();//Map<candidate,roledetails>
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
			List<Object[]> debateQuestionAnswer = debateQuestionAnswerDAO.getDebateQuestionAndAnswerDetails(debateId);
			if(debateQuestionAnswer != null && debateQuestionAnswer.size() > 0)
			{
				debateQuestionDetailsList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : debateQuestionAnswer) 
				{
						SelectOptionVO questionAnswer = new SelectOptionVO();
						questionAnswer.setName(parms[1] != null ? parms[1].toString() :"");//Answer
						questionAnswer.setLocation(parms[0] != null ? parms[0].toString() :"");//Question
						debateQuestionDetailsList.add(questionAnswer);
				}
				debateVO.setQuestionAnswersList(debateQuestionDetailsList);
				
			}
			
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
