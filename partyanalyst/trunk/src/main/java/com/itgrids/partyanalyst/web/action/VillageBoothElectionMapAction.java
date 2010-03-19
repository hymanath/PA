package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IVillageBoothDataPopulationService;
import com.opensymphony.xwork2.ActionSupport;

public class VillageBoothElectionMapAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IVillageBoothDataPopulationService villageBoothDataPopulationService;
	private List<SelectOptionVO> electionSelectVO;
	private File filePath;
	private Long electionId;
	
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

	public String execute(){
		villageBoothDataPopulationService.readExcelAndInsertData(filePath, electionId);
		return SUCCESS;
	}

}
