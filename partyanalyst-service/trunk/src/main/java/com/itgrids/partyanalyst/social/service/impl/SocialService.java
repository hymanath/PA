package com.itgrids.partyanalyst.social.service.impl;

import java.util.ArrayList;


import java.util.List;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SocialNetworkVO;
import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;
import com.itgrids.partyanalyst.social.dao.IPartySocialDAO;
import com.itgrids.partyanalyst.social.dao.ISocialNetworkSiteDAO;
import com.itgrids.partyanalyst.social.model.CandidateSocial;
import com.itgrids.partyanalyst.social.service.ISocialService;


public class SocialService implements ISocialService {

private IPartySocialDAO partySocialDAO;
private ICandidateSocialDAO candidateSocialDAO;
private ICandidateDAO candidateDAO;
private ISocialNetworkSiteDAO socialNetworkSiteDAO;

public ISocialNetworkSiteDAO getSocialNetworkSiteDAO() {
	return socialNetworkSiteDAO;
}
public void setSocialNetworkSiteDAO(ISocialNetworkSiteDAO socialNetworkSiteDAO) {
	this.socialNetworkSiteDAO = socialNetworkSiteDAO;
}
public ICandidateDAO getCandidateDAO() {
	return candidateDAO;
}
public void setCandidateDAO(ICandidateDAO candidateDAO) {
	this.candidateDAO = candidateDAO;
}
public ICandidateSocialDAO getCandidateSocialDAO() {
	return candidateSocialDAO;
}
public void setCandidateSocialDAO(ICandidateSocialDAO candidateSocialDAO) {
	this.candidateSocialDAO = candidateSocialDAO;
}

public IPartySocialDAO getPartySocialDAO() {
	return partySocialDAO;
}
public void setPartySocialDAO(IPartySocialDAO partySocialDAO) {
	this.partySocialDAO = partySocialDAO;
}	
	
public List<SocialNetworkVO> getPartyNames(){
		
	//List list=candidateSocialDAO.getCandidates();
	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;

	try
	{
		List<Object[]> result =partySocialDAO.getPartyNames();
		for(Object[] params:result)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setName(params[0].toString());
			socialNetworkVO.setUrl(params[1].toString());
			list.add(socialNetworkVO);
		}
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return list;
		}
public List<SocialNetworkVO> getCandidateNames(){
	
	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;

	try
	{
		List<Object[]> result=candidateSocialDAO.getCandidateNames();
		for(Object[] params:result)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setName(params[0].toString());
			socialNetworkVO.setUrl(params[1].toString());
			list.add(socialNetworkVO);
		}
		
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return list;
		
}

public List<SocialNetworkVO> getNames(){
	
	//List list=candidateSocialDAO.getCandidates();
	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;

	try
	{
		List<Object[]> result =partySocialDAO.getPartyNames();
		for(Object[] params:result)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setName(params[0].toString());
			socialNetworkVO.setUrl(params[1].toString());
			list.add(socialNetworkVO);
		}
		List<Object[]> result1=candidateSocialDAO.getCandidateNames();
		for(Object[] params:result1)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setCname(params[0].toString());
			socialNetworkVO.setCurl(params[1].toString());
			list.add(socialNetworkVO);
		}
		
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return list;
		}

public ResultStatus saveSocialInformation(SocialNetworkVO socialVO){
	ResultStatus resultStatus = new ResultStatus();
	try
	{
	CandidateSocial social = new CandidateSocial();
	Long candidateId = candidateDAO.getCandidateByLastName(socialVO.getCname());
	//Long socialNetworkId=candidateDAO.getSocialNetwork(socialVO.getCategory());
	Long socialNetworkId = socialNetworkSiteDAO.getSocialNetworkIdByName(socialVO.getCategory());
	
	social.setProfileId(socialVO.getProfileId());
	if(candidateId != null)
	social.setCandidate(candidateDAO.get(new Long(candidateId)));
	if(socialNetworkId != null)
	social.setSocialNetworkSite(socialNetworkSiteDAO.get(new Long(socialNetworkId)));	
	
	candidateSocialDAO.save(social);
	
	resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	}

catch (Exception e) {
	resultStatus.setExceptionEncountered(e);
	resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	e.printStackTrace();
}
	return resultStatus;
}

}
