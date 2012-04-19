package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BoothPanelVO;
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.HamletsAndBoothsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VillageBoothInfoVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.PartyBoothWiseResultsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class HamletBoothMapperAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> result;
	private List<MandalVO> mandals;
	private String task = null;
	JSONObject jObj = null;
	private HamletsAndBoothsVO hamletsAndBoothsVO; 
	private IConstituencyPageService constituencyPageService;
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	private List<VillageBoothInfoVO> villageBooths;
	private String requestStatus;
	private BoothPanelVO boothPanelVO;
	private static final Logger log = Logger.getLogger(PartyBoothWiseResultsService.class);
	
	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}

	public BoothPanelVO getBoothPanelVO() {
		return boothPanelVO;
	}

	public void setBoothPanelVO(BoothPanelVO boothPanelVO) {
		this.boothPanelVO = boothPanelVO;
	}

	public List<VillageBoothInfoVO> getVillageBooths() {
		return villageBooths;
	}

	public void setVillageBooths(List<VillageBoothInfoVO> villageBooths) {
		this.villageBooths = villageBooths;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public HamletsAndBoothsVO getHamletsAndBoothsVO() {
		return hamletsAndBoothsVO;
	}

	public void setHamletsAndBoothsVO(HamletsAndBoothsVO hamletsAndBoothsVO) {
		this.hamletsAndBoothsVO = hamletsAndBoothsVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<SelectOptionVO> getResult() {
		return result;
	}

	public List<MandalVO> getMandals() {
		return mandals;
	}

	public void setMandals(List<MandalVO> mandals) {
		this.mandals = mandals;
	}

	public void setResult(List<SelectOptionVO> result) {
		this.result = result;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() {
		
		RegistrationVO registrationVO = (RegistrationVO) request.getSession().getAttribute(IConstants.USER);
		if(registrationVO !=null)
		{
			if(registrationVO.getIsAdmin() !=null && registrationVO.getIsAdmin().equals("true"))
			{
				if(task != null){
					try{
						jObj = new JSONObject(getTask());
						System.out.println("Result From JSON:"+jObj);
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(jObj.getString("task").equals("getDistricts")){
						System.out.println("For Districts Of State");
						result = staticDataService.getDistricts(jObj.getLong("locationId"));
						result.add(0,new SelectOptionVO(0l,"Select District"));
					}else if(jObj.getString("task").equals("getMandals")){
						System.out.println("For Mandals Of District");
						mandals = staticDataService.getMandalsForDistrict(jObj.getLong("locationId"));
						mandals.add(0,new MandalVO(0l,"Select Mandal"));
					}else if(jObj.getString("task").equals("getRevenueVillagesAndBooths")){
						System.out.println("For REVENUE VILLAGES Of Mandal");
						hamletsAndBoothsVO = new HamletsAndBoothsVO();
						result = staticDataService.findTownshipsByTehsilID(jObj.getLong("locationId"));
						result.add(0,new SelectOptionVO(0l,"Select Revenue Village"));
						hamletsAndBoothsVO.setHamlets(result);
						hamletsAndBoothsVO.setConstituenciesAndBooths(staticDataService.getBoothPartNosForMandalAndElection(jObj.getLong("locationId"), "2009"));
					}else if(jObj.getString("task").equals("storeHamletBoothInfo")){
						Long hamletId = jObj.getLong("townshipId");
						JSONArray boothJsonIds = jObj.getJSONArray("boothIds");
						List<Long> boothElecIds = new ArrayList<Long>();
						
						for(int i=0; i < boothJsonIds.length(); i++){
							boothElecIds.add(new Long((String)boothJsonIds.get(i)));
						}
						ResultWithExceptionVO resultVO = constituencyPageService.saveAndUpdateHamletAndBoothInfo(new HamletAndBoothVO(hamletId, boothElecIds));
						if(resultVO.getExceptionEncountered() == null){
							villageBooths = (List<VillageBoothInfoVO>)resultVO.getFinalResult();
						}
					}else if(jObj.getString("task").equals("deleteVillageBoothInfo")){
						HamletAndBoothVO hamletAndBoothVO = new HamletAndBoothVO();
						Long villageBoothElectionId = jObj.getLong("villageBoothId");
						hamletAndBoothVO.setHamletId(villageBoothElectionId);
						ResultWithExceptionVO resultVO = constituencyPageService.deleteVillageBoothElectionRecord(hamletAndBoothVO);
						if(resultVO.getExceptionEncountered() == null){
							requestStatus = "Record Deleted";
						}
					}			
					
				}else{
					result = new ArrayList<SelectOptionVO>(2);
					result.add(0,new SelectOptionVO(0l,"Select State"));
					result.add(1,new SelectOptionVO(1l,"Andra Pradesh"));
				}
				
				return SUCCESS;
			}
			else
				return Action.ERROR;
		}
		else
			return IConstants.NOT_LOGGED_IN;
	}
	
}
