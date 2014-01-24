package com.itgrids.partyanalyst.service.impl;

import java.util.Formatter;
import java.util.List;

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
	 * @return List<DebateVO> returnList
	 */
	public DebateVO getDebateDetailsForSelected(Long debateId)
	{
		
		DebateVO debateVO = new DebateVO();
		try 
		{
			LOG.info("Enterd into getDebateDetailsForSelected method in DebateService class");
			
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
				
			}
		}
		catch (Exception e)
		{
			LOG.error("Error occured in getDebateDetailsForSelected method in DebateService class",e);
		}
		return debateVO;
	}
}
