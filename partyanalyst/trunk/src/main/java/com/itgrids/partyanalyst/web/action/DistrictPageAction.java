package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class DistrictPageAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String districtId;
	private String districtName;
	private HttpServletRequest request;
	private IStaticDataService staticDataService;	
	private IRegionServiceData regionServiceDataImp;
	private ConstituenciesStatusVO constituenciesStatusVO;
	private List<SelectOptionVO> constituencies ;
	private List<MandalVO> mandals ;
	private MandalAllElectionDetailsVO mptcElectionDetails;
	private MandalAllElectionDetailsVO zptcElectionDetails;
	private String constituencyId;
	private final Logger log = Logger.getLogger(DistrictPageAction.class);
	private List<CandidateDetailsVO> candidateDetailsVO;
	private List newConstituencies;
	private List<SelectOptionVO> electionYears,mptcElectionYears;
	private String task = null;
	JSONObject jObj = null;
	private Long zptcCount,mptcCount;
	private CandidateDetailsVO  parliamentCandidateDetailsVo;
	private List<TeshilPartyInfoVO> partyDetails,getMptcPartyDetails;
	
	public List<TeshilPartyInfoVO> getGetMptcPartyDetails() {
		return getMptcPartyDetails;
	}
	public void setGetMptcPartyDetails(List<TeshilPartyInfoVO> getMptcPartyDetails) {
		this.getMptcPartyDetails = getMptcPartyDetails;
	}
	public List<SelectOptionVO> getMptcElectionYears() {
		return mptcElectionYears;
	}
	public void setMptcElectionYears(List<SelectOptionVO> mptcElectionYears) {
		this.mptcElectionYears = mptcElectionYears;
	}
	public List<TeshilPartyInfoVO> getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(List<TeshilPartyInfoVO> partyDetails) {
		this.partyDetails = partyDetails;
	}
	
	public CandidateDetailsVO getParliamentCandidateDetailsVo() {
		return parliamentCandidateDetailsVo;
	}
	public void setParliamentCandidateDetailsVo(
			CandidateDetailsVO parliamentCandidateDetailsVo) {
		this.parliamentCandidateDetailsVo = parliamentCandidateDetailsVo;
	}

	public Long getZptcCount() {
		return zptcCount;
	}

	public void setZptcCount(Long zptcCount) {
		this.zptcCount = zptcCount;
	}

	public Long getMptcCount() {
		return mptcCount;
	}

	public void setMptcCount(Long mptcCount) {
		this.mptcCount = mptcCount;
	}

	public MandalAllElectionDetailsVO getMptcElectionDetails() {
		return mptcElectionDetails;
	}

	public void setMptcElectionDetails(
			MandalAllElectionDetailsVO mptcElectionDetails) {
		this.mptcElectionDetails = mptcElectionDetails;
	}

	public MandalAllElectionDetailsVO getZptcElectionDetails() {
		return zptcElectionDetails;
	}

	public void setZptcElectionDetails(
			MandalAllElectionDetailsVO zptcElectionDetails) {
		this.zptcElectionDetails = zptcElectionDetails;
	}
	public List getNewConstituencies() {
		return newConstituencies;
	}

	public void setNewConstituencies(List newConstituencies) {
		this.newConstituencies = newConstituencies;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public List<CandidateDetailsVO> getCandidateDetailsVO() {
		return candidateDetailsVO;
	}

	public void setCandidateDetailsVO(List<CandidateDetailsVO> candidateDetailsVO) {
		this.candidateDetailsVO = candidateDetailsVO;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public ConstituenciesStatusVO getConstituenciesStatusVO() {
		return constituenciesStatusVO;
	}

	public void setConstituenciesStatusVO(
			ConstituenciesStatusVO constituenciesStatusVO) {
		this.constituenciesStatusVO = constituenciesStatusVO;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<MandalVO> getMandals() {
		return mandals;
	}

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public void setMandals(List<MandalVO> mandals) {
		this.mandals = mandals;
	}

	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public String execute() throws Exception
	{
		districtId = request.getParameter("districtId");
		districtName = request.getParameter("districtName");
			
		constituenciesStatusVO = staticDataService.getConstituenciesWinnerInfo(Long.parseLong(districtId));	
		zptcCount = Long.parseLong(constituenciesStatusVO.getZptcCount().toString());
		mptcCount = Long.parseLong(constituenciesStatusVO.getMptcCount().toString());
		mandals = staticDataService.getMandalsForDistrict(Long.parseLong(districtId));	
		parliamentCandidateDetailsVo = staticDataService.getAllParliamentWinningCandidatesForADistrict(Long.parseLong(districtId));
		if(mandals == null){
				if(log.isDebugEnabled())
					log.error("Failed to get Mandal Data");
				return Action.ERROR;
		}

		log.debug("District Id = "+districtId+" & District Name = "+districtName);
		
		return Action.SUCCESS;
	
	}
		
	public String getMptcAndZptcInfoDistrictAction(){
		if(task != null){
			try {
				jObj = new JSONObject(getTask());
				System.out.println(jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			log.debug("Task::"+jObj.getString("task"));
			if(jObj.getString("task").equalsIgnoreCase("getZptcData"))
			{
				String districtID = jObj.getString("id"); 
				zptcElectionDetails = staticDataService.getAllZptcsForADistrictForLatestYear(Long.parseLong(districtID));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getMptcData"))
			{
				String districtID = jObj.getString("id"); 
				mptcElectionDetails = staticDataService.getAllMptcsForADistrictForLatestYear(Long.parseLong(districtID));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllElectionYears"))
			{
				electionYears = staticDataService.getAllElectionYearsForATeshil(new Long(jObj.getString("eleType")));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllMptcElectionYears"))
			{
				mptcElectionYears = staticDataService.getAllElectionYearsForATeshil(new Long(jObj.getString("eleType")));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPartyDetails"))
			{
				partyDetails = staticDataService.getMandalWisePartyReport(jObj.getString("electionType"),jObj.getString("electionYear"),new Long(jObj.getString("districtId")));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getMptcPartyDetails"))
			{
				getMptcPartyDetails = staticDataService.getMandalWisePartyReport(jObj.getString("electionType"),jObj.getString("electionYear"),new Long(jObj.getString("districtId")));
			}
		}		
		return SUCCESS;  
	}	
	
}
//public List<TeshilPartyInfoVO> partyDetails getMandalWisePartyReport(String electionType,String electionYear)