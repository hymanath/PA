package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAttributeWiseElectionResultComparisonService;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CasteAndElectionResultsComparisonAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6262958130736176453L;
	
	private static final Logger LOG = Logger.getLogger(CasteAndElectionResultsComparisonAction.class);
			
	private HttpServletRequest request;
	
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	private IVotersAnalysisService votersAnalysisService;
	
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	
	private IAttributeWiseElectionResultComparisonService attributeWiseElectionResultComparisonService;
	
	private String task;
	
	JSONObject jObj;
	
	private PartyResultsVO partyResultsVO;
	
	private EntitlementsHelper entitlementsHelper;
	
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
		
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}

	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}

	public IAttributeWiseElectionResultComparisonService getAttributeWiseElectionResultComparisonService() {
		return attributeWiseElectionResultComparisonService;
	}

	public void setAttributeWiseElectionResultComparisonService(
			IAttributeWiseElectionResultComparisonService attributeWiseElectionResultComparisonService) {
		this.attributeWiseElectionResultComparisonService = attributeWiseElectionResultComparisonService;
	}
	
	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public PartyResultsVO getPartyResultsVO() {
		return partyResultsVO;
	}

	public void setPartyResultsVO(PartyResultsVO partyResultsVO) {
		this.partyResultsVO = partyResultsVO;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute(){
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.VOTER_ANALYSIS))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.VOTER_ANALYSIS))
			return ERROR;
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(user.getRegistrationID(),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID));
		constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		return Action.SUCCESS;
	}
	
	public String getAttributesWiseElectionResults(){
		//(List<Long> electionIds,List<Long> partyIds,Long userId,Long constituencyId,String type,List<Long> ids,String attributeType,List<Long> attributeIds,Long attrPerc)
		try{
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return Action.ERROR;
			jObj = new JSONObject(getTask());
			List<Long> electionIds = new ArrayList<Long>();
			List<Long> partyIds = new ArrayList<Long>();
			List<Long> ids = new ArrayList<Long>();
			List<Long> attributeIds = new ArrayList<Long>();
			
			Long constituencyId = jObj.getLong("constituencyId");
			String type = jObj.getString("type");
			String attributeType = jObj.getString("attributeType");
			Long attrPerc = jObj.getLong("attrPerc");
			Long publicationId = jObj.getLong("publicationId");
			
			String[] elecIds = jObj.getString("electionIds").split(",");
			for(String elecId:elecIds){
				electionIds.add(new Long(elecId.trim()));
			}
			
			String[] partIds = jObj.getString("partyIds").split(",");
			for(String partId:partIds){
				partyIds.add(new Long(partId.trim()));
			}
			
			String[] locIds = jObj.getString("locationIds").split(",");
			for(String loc:locIds){
				ids.add(new Long(loc.trim()));
			}
			
			String[] attrIds = jObj.getString("attributeIds").split(",");
			for(String attrId:attrIds){
				attributeIds.add(new Long(attrId.trim()));
			}
			
			partyResultsVO = attributeWiseElectionResultComparisonService.getElectionResultsByAttributeWise(electionIds,partyIds,user.getRegistrationID(),constituencyId,type,ids,attributeType,attributeIds,attrPerc,publicationId);
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getAttributesWiseElectionResults()",e);
		}
		
		return Action.SUCCESS;
	}
}
