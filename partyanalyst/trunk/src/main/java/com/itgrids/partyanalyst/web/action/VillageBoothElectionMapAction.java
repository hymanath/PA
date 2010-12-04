package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VillageBoothElectionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVillageBoothDataPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class VillageBoothElectionMapAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IVillageBoothDataPopulationService villageBoothDataPopulationService;
	private List<SelectOptionVO> electionSelectVO;
	private File filePath;
	private Long electionId;
	private IStaticDataService staticDataService;
	private String task = null;
	private Boolean isValidate;
	JSONObject jObj = null;
	private VillageBoothElectionVO villageBoothElectionVO;
	private static final Logger log = Logger.getLogger(VillageBoothElectionMapAction.class);
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IVillageBoothDataPopulationService getVillageBoothDataPopulationService() {
		return villageBoothDataPopulationService;
	}

	public void setVillageBoothDataPopulationService(
			IVillageBoothDataPopulationService villageBoothDataPopulationService) {
		this.villageBoothDataPopulationService = villageBoothDataPopulationService;
	}

	public List<SelectOptionVO> getElectionSelectVO() {
		return electionSelectVO;
	}

	public void setElectionSelectVO(List<SelectOptionVO> electionSelectVO) {
		this.electionSelectVO = electionSelectVO;
	}

	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public Boolean getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(Boolean isValidate) {
		this.isValidate = isValidate;
	}

	public VillageBoothElectionVO getVillageBoothElectionVO() {
		return villageBoothElectionVO;
	}

	public void setVillageBoothElectionVO(
			VillageBoothElectionVO villageBoothElectionVO) {
		this.villageBoothElectionVO = villageBoothElectionVO;
	}

	public String execute(){
		villageBoothElectionVO = villageBoothDataPopulationService.readExcelAndInsertData(filePath, electionId, isValidate);
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
				log.debug("getElectionYears......");
				electionSelectVO = staticDataService.getElectionIdsAndYearsInfo(jObj.getLong("electionTypeId"), 1l);
			}
		}
		return SUCCESS;
	}

}
