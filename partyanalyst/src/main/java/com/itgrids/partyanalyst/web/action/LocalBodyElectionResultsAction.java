package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.mapping.Array;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.LocalElectionBodyDetailsVo;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.ILocalBodyElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LocalBodyElectionResultsAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware{
	

	/**
	 * @author Prasad Thiragabathina
	 * @date 02/03/2013
	 */
	private static final long serialVersionUID = 1L;
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	 private ServletContext context;
	 private HttpServletResponse response;
	 private HttpServletRequest request;
	 HttpSession session;
	 private static final Logger LOG = Logger.getLogger(LocalBodyElectionResultsAction.class);
	 JSONObject jObj = null;
	 private String mptcElectionType,zptcElectionType,muncipalityElectionType,corporationElectionType;
	 private List<SelectOptionVO> electionTypes;
	 private IStaticDataService staticDataService;
	 private List<Long> mptcElectionId,muncipalityElectionTypeId,corporationElectionTypeId,zptcElectionId;
	 private Long mptcElectionTypeId,zptcElectionTypeId,mptcLocalBodyElectionId,zptcLocalBodyElectionId;
	 private List<SelectOptionVO> zptcElectionYears;
	 private List<SelectOptionVO> mptcElectionYears,muncipalElectionYears,corpElectionYears,ghmcElectionYears; 
	 private IConstituencyPageService constituencyPageService;
	 private  LocalElectionBodyDetailsVo localBodeElectionDetails;
	 private ILocalBodyElectionService localBodyElectionService;
	 private ResultStatus resultStatus;
	 private List<SelectOptionVO> zptcElection,mptcElection,muncipalElection,corpElection,ghmcElection; 
	 private String ghmcDetails,muncipalOrCorpDetails;
	 private Long constituencyId,electionId;
	 private String constituencyName;
	 private LocalBodyElectionResultsVO localBodyElectionResultsVO;
	 private List<TeshilPartyInfoVO> muncipalityVO;
	 private String localBodyDetails,localBodyAreaType;
	 private List<TeshilPartyInfoVO> constituencyWiseAllPartyTrends ;
	 private String areaType;
	 private List<SelectOptionVO> greaterElections;
	 public String getMptcElectionType() {
		return mptcElectionType;
	}

	public void setMptcElectionType(String mptcElectionType) {
		this.mptcElectionType = mptcElectionType;
	}

	public String getZptcElectionType() {
		return zptcElectionType;
	}

	public void setZptcElectionType(String zptcElectionType) {
		this.zptcElectionType = zptcElectionType;
	}

	public String getMuncipalityElectionType() {
		return muncipalityElectionType;
	}

	public void setMuncipalityElectionType(String muncipalityElectionType) {
		this.muncipalityElectionType = muncipalityElectionType;
	}

	public String getCorporationElectionType() {
		return corporationElectionType;
	}

	public void setCorporationElectionType(String corporationElectionType) {
		this.corporationElectionType = corporationElectionType;
	}

	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}

	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	
	public Long getMptcElectionTypeId() {
		return mptcElectionTypeId;
	}

	public void setMptcElectionTypeId(Long mptcElectionTypeId) {
		this.mptcElectionTypeId = mptcElectionTypeId;
	}

	public Long getZptcElectionTypeId() {
		return zptcElectionTypeId;
	}

	public void setZptcElectionTypeId(Long zptcElectionTypeId) {
		this.zptcElectionTypeId = zptcElectionTypeId;
	}

	
	public List<Long> getMptcElectionId() {
		return mptcElectionId;
	}

	public void setMptcElectionId(List<Long> mptcElectionId) {
		this.mptcElectionId = mptcElectionId;
	}

	public List<Long> getMuncipalityElectionTypeId() {
		return muncipalityElectionTypeId;
	}

	public void setMuncipalityElectionTypeId(List<Long> muncipalityElectionTypeId) {
		this.muncipalityElectionTypeId = muncipalityElectionTypeId;
	}

	public List<Long> getCorporationElectionTypeId() {
		return corporationElectionTypeId;
	}

	public void setCorporationElectionTypeId(List<Long> corporationElectionTypeId) {
		this.corporationElectionTypeId = corporationElectionTypeId;
	}

	public List<Long> getZptcElectionId() {
		return zptcElectionId;
	}

	public void setZptcElectionId(List<Long> zptcElectionId) {
		this.zptcElectionId = zptcElectionId;
	}

	public List<SelectOptionVO> getZptcElectionYears() {
		return zptcElectionYears;
	}

	public void setZptcElectionYears(List<SelectOptionVO> zptcElectionYears) {
		this.zptcElectionYears = zptcElectionYears;
	}

	public List<SelectOptionVO> getMptcElectionYears() {
		return mptcElectionYears;
	}

	public void setMptcElectionYears(List<SelectOptionVO> mptcElectionYears) {
		this.mptcElectionYears = mptcElectionYears;
	}	
	public static Logger getLog() {
		return LOG;
	}
	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}
	
	
	public LocalElectionBodyDetailsVo getLocalBodeElectionDetails() {
		return localBodeElectionDetails;
	}

	public void setLocalBodeElectionDetails(
			LocalElectionBodyDetailsVo localBodeElectionDetails) {
		this.localBodeElectionDetails = localBodeElectionDetails;
	}
	
	
	public List<SelectOptionVO> getMuncipalElectionYears() {
		return muncipalElectionYears;
	}

	public void setMuncipalElectionYears(List<SelectOptionVO> muncipalElectionYears) {
		this.muncipalElectionYears = muncipalElectionYears;
	}

	public List<SelectOptionVO> getCorpElectionYears() {
		return corpElectionYears;
	}

	public void setCorpElectionYears(List<SelectOptionVO> corpElectionYears) {
		this.corpElectionYears = corpElectionYears;
	}

	public List<SelectOptionVO> getGhmcElectionYears() {
		return ghmcElectionYears;
	}

	public void setGhmcElectionYears(List<SelectOptionVO> ghmcElectionYears) {
		this.ghmcElectionYears = ghmcElectionYears;
	}
	
	
	public ILocalBodyElectionService getLocalBodyElectionService() {
		return localBodyElectionService;
	}

	public void setLocalBodyElectionService(
			ILocalBodyElectionService localBodyElectionService) {
		this.localBodyElectionService = localBodyElectionService;
	}
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public List<SelectOptionVO> getZptcElection() {
		return zptcElection;
	}

	public void setZptcElection(List<SelectOptionVO> zptcElection) {
		this.zptcElection = zptcElection;
	}

	public List<SelectOptionVO> getMptcElection() {
		return mptcElection;
	}

	public void setMptcElection(List<SelectOptionVO> mptcElection) {
		this.mptcElection = mptcElection;
	}

	public List<SelectOptionVO> getMuncipalElection() {
		return muncipalElection;
	}

	public void setMuncipalElection(List<SelectOptionVO> muncipalElection) {
		this.muncipalElection = muncipalElection;
	}

	public List<SelectOptionVO> getCorpElection() {
		return corpElection;
	}

	public void setCorpElection(List<SelectOptionVO> corpElection) {
		this.corpElection = corpElection;
	}
	
	
	public List<SelectOptionVO> getGhmcElection() {
		return ghmcElection;
	}

	public void setGhmcElection(List<SelectOptionVO> ghmcElection) {
		this.ghmcElection = ghmcElection;
	}
	
	public String getGhmcDetails() {
		return ghmcDetails;
	}

	public void setGhmcDetails(String ghmcDetails) {
		this.ghmcDetails = ghmcDetails;
	}
	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	
	public LocalBodyElectionResultsVO getLocalBodyElectionResultsVO() {
		return localBodyElectionResultsVO;
	}

	public void setLocalBodyElectionResultsVO(
			LocalBodyElectionResultsVO localBodyElectionResultsVO) {
		this.localBodyElectionResultsVO = localBodyElectionResultsVO;
	}
	
	public List<TeshilPartyInfoVO> getMuncipalityVO() {
		return muncipalityVO;
	}

	public void setMuncipalityVO(List<TeshilPartyInfoVO> muncipalityVO) {
		this.muncipalityVO = muncipalityVO;
	}
	
	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	
	public Long getMptcLocalBodyElectionId() {
		return mptcLocalBodyElectionId;
	}

	public void setMptcLocalBodyElectionId(Long mptcLocalBodyElectionId) {
		this.mptcLocalBodyElectionId = mptcLocalBodyElectionId;
	}

	public Long getZptcLocalBodyElectionId() {
		return zptcLocalBodyElectionId;
	}

	public void setZptcLocalBodyElectionId(Long zptcLocalBodyElectionId) {
		this.zptcLocalBodyElectionId = zptcLocalBodyElectionId;
	}
	
	public String getLocalBodyDetails() {
		return localBodyDetails;
	}

	public void setLocalBodyDetails(String localBodyDetails) {
		this.localBodyDetails = localBodyDetails;
	}

	
	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	
	public List<TeshilPartyInfoVO> getConstituencyWiseAllPartyTrends() {
		return constituencyWiseAllPartyTrends;
	}

	public void setConstituencyWiseAllPartyTrends(
			List<TeshilPartyInfoVO> constituencyWiseAllPartyTrends) {
		this.constituencyWiseAllPartyTrends = constituencyWiseAllPartyTrends;
	}

	
	public String getMuncipalOrCorpDetails() {
		return muncipalOrCorpDetails;
	}

	public void setMuncipalOrCorpDetails(String muncipalOrCorpDetails) {
		this.muncipalOrCorpDetails = muncipalOrCorpDetails;
	}

	

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	
	public List<SelectOptionVO> getGreaterElections() {
		return greaterElections;
	}

	public void setGreaterElections(List<SelectOptionVO> greaterElections) {
		this.greaterElections = greaterElections;
	}

	public String execute() {
		
		mptcElectionType = IConstants.MPTC_ELECTION_TYPE;
		zptcElectionType = IConstants.ZPTC_ELECTION_TYPE;
		muncipalityElectionType = IConstants.MUNCIPLE_ELECTION_TYPE;
		corporationElectionType = IConstants.CORPORATION_ELECTION_TYPE;
		mptcElectionId = new ArrayList<Long>();
		zptcElectionId= new ArrayList<Long>();
		muncipalityElectionTypeId= new ArrayList<Long>();
		corporationElectionTypeId= new ArrayList<Long>();
		electionTypes = staticDataService.getAllElectionTypes();
		List<Long> constityencyIds = new ArrayList<Long>();
		NavigationVO ghmcElectionDetail= null;
		constityencyIds.add(constituencyId);
		for(SelectOptionVO eleTypes : electionTypes){
			if(eleTypes.getName().equalsIgnoreCase(mptcElectionType)){
				mptcLocalBodyElectionId = eleTypes.getId();
				mptcElectionId.add(mptcLocalBodyElectionId);
			}else if(eleTypes.getName().equalsIgnoreCase(zptcElectionType)){
				zptcLocalBodyElectionId = eleTypes.getId();
				zptcElectionId.add(zptcLocalBodyElectionId);
			}else if(eleTypes.getName().equalsIgnoreCase(muncipalityElectionType)){
				electionId = eleTypes.getId();
				muncipalityElectionTypeId.add(electionId);
			}else if(eleTypes.getName().equalsIgnoreCase(corporationElectionType)){
				electionId = eleTypes.getId();
				corporationElectionTypeId.add(electionId);
			}
			else if(eleTypes.getName().equalsIgnoreCase(""));
		}
		 zptcElectionYears = staticDataService.getAllElectionYearsForATeshil(mptcLocalBodyElectionId);
		 zptcElection = new ArrayList<SelectOptionVO>();
		 	for (SelectOptionVO zptcElectionYear : zptcElectionYears)
		 	{
		 		if(zptcElectionYears != null)
		 		{
					resultStatus = staticDataService.checkForLocalElctionBody(constituencyId,zptcElectionId);
					if(resultStatus.getResultCode() == 0)
					{
						zptcElection.add(zptcElectionYear);
					}
		 		}
			}
		 try {
			mptcElectionYears = staticDataService.getAllElectionYearsForATeshil(zptcLocalBodyElectionId);
			mptcElection = new ArrayList<SelectOptionVO>();
			 for (SelectOptionVO mptcElectionYear : mptcElectionYears)
			    {
				 if(mptcElectionYears != null)
				 {
					resultStatus = staticDataService.checkForLocalElctionBody(constituencyId,mptcElectionId);
					if(resultStatus.getResultCode() == 0)
					{
						mptcElection.add(mptcElectionYear);	
					}
				 }
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
			muncipalElectionYears = localBodyElectionService.getLocalBodyElectionsList(IConstants.MUNCIPLE_ELECTION_TYPE, 1l,constityencyIds);
			muncipalElection = new ArrayList<SelectOptionVO>();
			for (SelectOptionVO muncipalElectionYear : muncipalElectionYears) 
			{
				if(muncipalElectionYear != null)
				{
					resultStatus = staticDataService.checkForLocalElctionBody(constituencyId,muncipalityElectionTypeId);
					if(resultStatus.getResultCode() == 0)
					{
						muncipalElection.add(muncipalElectionYear);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			try {
				corpElectionYears = localBodyElectionService.getLocalBodyElectionsList(IConstants.CORPORATION_ELECTION_TYPE, 1l,constityencyIds);
				corpElection = new ArrayList<SelectOptionVO>();
				for (SelectOptionVO corpElectionYear : corpElectionYears) 
				{
					if(corpElectionYear != null)
					{
						resultStatus = staticDataService.checkForLocalElctionBody(constituencyId,corporationElectionTypeId);
						if(resultStatus.getResultCode() == 0)
						{
							corpElection.add(corpElectionYear);
						}
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				greaterElections = localBodyElectionService.getLocalBodyElectionsList(IConstants.GREATER_ELECTION_TYPE, 1l,constityencyIds);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*ghmcElectionDetail=  localBodyElectionService.getLocalBodyElectionIdsForAConstituency(constituencyId, IConstants.GREATER_ELECTION_TYPE);
			if(ghmcElectionDetail != null){
			 ghmcElection = ghmcElectionDetail.getMessageTypes();
			}*/
		return Action.SUCCESS;
		
	}
	
	public String ajaxHandler()
	{
		
		String param=null;		
		param=request.getParameter("task");
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return ERROR;
		LOG.debug("param:"+param);
		try {
			jObj=new JSONObject(param);
			LOG.debug("Entered Into The ajaxHandler() method In LocalBodyElectionResultsAction");
			 TeshilPartyInfoVO teshilPartyInfoVO = null;
			localBodeElectionDetails = new LocalElectionBodyDetailsVo();
			
			if(jObj.getString("task").equalsIgnoreCase("getMptcElectionResults"))
			{
				teshilPartyInfoVO = new TeshilPartyInfoVO();
				constituencyWiseAllPartyTrends = constituencyPageService.getPartyWiseZptcOrMptcElectionDataForAConstituency(jObj.getLong("constituencyId"),jObj.getString("mptcElectionYear"),IConstants.MPTC_ELECTION_TYPE,jObj.getString("constituencyType"));
				//teshilPartyInfoVO.setMuncipalityVO(constituencyWiseAllPartyTrends);
				//localBodeElectionDetails.getMptcDetails().add(teshilPartyInfoVO);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getZptcElectionResults"))
			{
				teshilPartyInfoVO = new TeshilPartyInfoVO();
				constituencyWiseAllPartyTrends = constituencyPageService.getPartyWiseZptcOrMptcElectionDataForAConstituency(jObj.getLong("constituencyId"),jObj.getString("zptcElectionYear"),IConstants.ZPTC_ELECTION_TYPE,jObj.getString("constituencyType"));
				//teshilPartyInfoVO.setMuncipalityVO(constituencyWiseAllPartyTrends);
				//localBodeElectionDetails.getZptcDetails().add(teshilPartyInfoVO);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getMuncipalityElectionResults"))
			{
				teshilPartyInfoVO = localBodyElectionService.getMuncipalOrCorporationElectionsResultsForAnAssembly(jObj.getLong("localBodyElectionId"), jObj.getLong("constituencyId"));
				if(teshilPartyInfoVO != null && teshilPartyInfoVO.getMuncipalityVO() != null && teshilPartyInfoVO.getMuncipalityVO().size() > 0)
				{
					localBodeElectionDetails.getMuncipalityDetails().add(teshilPartyInfoVO.getMuncipalityVO().get(0));
				}
				return "muncipalOrCorpDetails";
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCorpElectionResults"))
			{
				teshilPartyInfoVO = localBodyElectionService.getMuncipalOrCorporationElectionsResultsForAnAssembly(jObj.getLong("localBodyElectionId"), jObj.getLong("constituencyId"));
				if(teshilPartyInfoVO != null && teshilPartyInfoVO.getMuncipalityVO() != null && teshilPartyInfoVO.getMuncipalityVO().size() > 0)
				{
					localBodeElectionDetails.getCorprationDetails().add(teshilPartyInfoVO.getMuncipalityVO().get(0));
				}
				return "muncipalOrCorpDetails";
			}
			else if(jObj.getString("task").equalsIgnoreCase("getghmcElectionResults"))
			{
				localBodyElectionResultsVO =  localBodyElectionService.getLocalBodyElectionResultsByLocalBodyTypeAndYear(jObj.getLong("localBodyElectionId"), jObj.getLong("stateId"));
				if(localBodyElectionResultsVO != null)
				{
					muncipalityVO = localBodyElectionResultsVO.getMuncipalityVO();
				}
				
				return "ghmcDetails";
			}
			else if(jObj.getString("task").equalsIgnoreCase("checkForLocalBodyElection"))
			{
				List<Long> localBodyElecionIds = new ArrayList<Long>();
				localBodyElecionIds.add(3l);
				localBodyElecionIds.add(4l);
				localBodyElecionIds.add(5l);
				localBodyElecionIds.add(6l);
				localBodyElecionIds.add(7l);
				resultStatus = staticDataService.checkForLocalElctionBody(constituencyId,localBodyElecionIds);
				return "localBodyDetails";
			}
			else if(jObj.getString("task").equalsIgnoreCase("getLocalBodyElectionType"))
			{
				areaType = constituencyPageService.getLocalBodyElectionTypeByConstituencyId(jObj.getLong("constituencyId")); 
				return "localBodyAreaType";
			}
				
		} catch (Exception e) {
		Log.error("Exception Raised in ajaxHandler() method In LocalBodyElectionResultsAction", e);
		}
		return SUCCESS;
		
	}
}
