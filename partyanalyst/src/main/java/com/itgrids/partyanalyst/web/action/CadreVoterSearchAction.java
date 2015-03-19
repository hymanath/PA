package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICadreVoterSearchService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreVoterSearchAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(TdpCadreReportAction.class);
	
	private HttpServletRequest request;	
	private String task;
	private JSONObject jobj;
	private List<SelectOptionVO> casteDetails;
	private List<CadreAddressVO> resultList;
	private IMahaNaduService mahaNaduService;
	private ICadreVoterSearchService cadreVoterSearchService;

	
	public ICadreVoterSearchService getCadreVoterSearchService() {
		return cadreVoterSearchService;
	}
	public void setCadreVoterSearchService(
			ICadreVoterSearchService cadreVoterSearchService) {
		this.cadreVoterSearchService = cadreVoterSearchService;
	}
	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}
	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}
	public List<CadreAddressVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<CadreAddressVO> resultList) {
		this.resultList = resultList;
	}
	public List<SelectOptionVO> getCasteDetails() {
		return casteDetails;
	}
	public void setCasteDetails(List<SelectOptionVO> casteDetails) {
		this.casteDetails = casteDetails;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJobj() {
		return jobj;
	}
	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

public String execute(){
		
		try {
			LOG.info("Entered into  cadreVoterSearch method");
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			casteDetails = mahaNaduService.getCasteCategories();
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in cadreVoterSearch method",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getDistrictsAndConstituencies(){
		
		LOG.info("Entered into getDistrictsAndConstituencies method");
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		try {		
			jobj = new JSONObject(request.getParameter("task"));
			String type=jobj.getString("type");
			resultList = cadreVoterSearchService.getAllDistrictsAndConstis(type,jobj.getLong("id"));
		}
		catch(Exception e){
			LOG.error("Exception raised in getDistrictsAndConstituencies method", e);
		}
		return Action.SUCCESS;
		
		
	}
	
}
