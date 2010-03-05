package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.MandalTownshipWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.ActionSupport;

public class MandalPageElectionInfoAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO;
	private VillageDetailsVO villageDetailsVO;
	private MandalInfoVO mandalInfoVO;
	private List<SelectOptionVO> electionSelectVO;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private IStaticDataService staticDataService;
	private IConstituencyPageService constituencyPageService;
	private String task = null;
	JSONObject jObj = null;
	private String mandalId;
	private MandalAndRevenueVillagesInfoVO mandalAndRevenueVillagesInfoVO;
	private static final Logger log = Logger.getLogger(MandalPageAction.class);
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IDelimitationConstituencyMandalService getDelimitationConstituencyMandalService() {
		return delimitationConstituencyMandalService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public ElectionWiseMandalPartyResultListVO getElectionWiseMandalPartyResultListVO() {
		return electionWiseMandalPartyResultListVO;
	}

	public void setElectionWiseMandalPartyResultListVO(
			ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO) {
		this.electionWiseMandalPartyResultListVO = electionWiseMandalPartyResultListVO;
	}
	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public MandalInfoVO getMandalInfoVO() {
		return mandalInfoVO;
	}

	public void setMandalInfoVO(MandalInfoVO mandalInfoVO) {
		this.mandalInfoVO = mandalInfoVO;
	}

	public VillageDetailsVO getVillageDetailsVO() {
		return villageDetailsVO;
	}

	public void setVillageDetailsVO(VillageDetailsVO villageDetailsVO) {
		this.villageDetailsVO = villageDetailsVO;
	}

	public List<SelectOptionVO> getElectionSelectVO() {
		return electionSelectVO;
	}

	public void setElectionSelectVO(List<SelectOptionVO> electionSelectVO) {
		this.electionSelectVO = electionSelectVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public MandalAndRevenueVillagesInfoVO getMandalAndRevenueVillagesInfoVO() {
		return mandalAndRevenueVillagesInfoVO;
	}

	public void setMandalAndRevenueVillagesInfoVO(
			MandalAndRevenueVillagesInfoVO mandalAndRevenueVillagesInfoVO) {
		this.mandalAndRevenueVillagesInfoVO = mandalAndRevenueVillagesInfoVO;
	}

	public String execute(){
		
		mandalId = request.getParameter("MANDAL_ID");
		String mandalID = request.getParameter("MANDAL_ID");
		String mandalName = request.getParameter("MANDAL_NAME");
		List<MandalInfoVO> mandalInfo = delimitationConstituencyMandalService.getCensusInfoForMandals(mandalID);
		for(MandalInfoVO mandalInfoVO : mandalInfo){
			mandalInfoVO.setMandalName(mandalName);
			Throwable ex = mandalInfoVO.getExceptionEncountered();
			if(ex!=null){
				log.error("exception raised while retrieving mandal details ", ex);
				return ERROR;
			}
			setMandalInfoVO(mandalInfoVO);
			break;
		}		
		
		villageDetailsVO = delimitationConstituencyMandalService.getVillagesFormMandal(new Long(mandalID));
		villageDetailsVO.setMandalName(mandalName);
		Throwable ex = villageDetailsVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		electionWiseMandalPartyResultListVO = partyBoothWiseResultsService.getPartyGenderWiseBoothVotesForMandal(new Long(mandalID));
				
		return SUCCESS;
	}
	
	public String getElectionIdsAndYears(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(jObj.getString("task").equals("getElectionYears")){
				System.out.println("For Districts Of State");
				electionSelectVO = staticDataService.getElectionIdsAndYears(jObj.getLong("electionTypeId"));
				electionSelectVO.add(0,new SelectOptionVO(0l,"Select Year"));
			}
		}
		return SUCCESS;
	}
	
	public String getTownshipDetails(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			Long partyId;
			session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				partyId = null;
			else
				partyId = user.getParty();
			
			if(jObj.getString("task").equals("getRevenueVillagesInfo")){
				mandalAndRevenueVillagesInfoVO = constituencyPageService.getTownshipWiseBoothDetailsForTehsil(844l, jObj.getLong("electionId"));
			}
		}
		return SUCCESS;
	}

}
