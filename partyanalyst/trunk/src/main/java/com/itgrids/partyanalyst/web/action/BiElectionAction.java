package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IBiElectionPageService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BiElectionAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(BiElectionAction.class);
	private IBiElectionPageService biElectionPageService;
	private List<BiElectionDistrictVO> districtsAndConsts;
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String task;
	org.json.JSONObject jObj;
	List<MandalAllElectionResultsVO> mandalAllElectionResultsVO;
	
	
	public List<MandalAllElectionResultsVO> getMandalAllElectionResultsVO() {
		return mandalAllElectionResultsVO;
	}

	public void setMandalAllElectionResultsVO(
			List<MandalAllElectionResultsVO> mandalAllElectionResultsVO) {
		this.mandalAllElectionResultsVO = mandalAllElectionResultsVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<BiElectionDistrictVO> getDistrictsAndConsts() {
		return districtsAndConsts;
	}

	public void setDistrictsAndConsts(List<BiElectionDistrictVO> districtsAndConsts) {
		this.districtsAndConsts = districtsAndConsts;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
        this.response = response;
	}
	
	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}

	public String execute(){
		log.debug(" Inside Action ..");
		
		districtsAndConsts = biElectionPageService.getBiElectionConstituenciesDistrictWise();
		
		return Action.SUCCESS;
	}
	
	public String getMandalsVotingTrendz() throws Exception
	{
		String param=null;			    
		param = getTask();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		Long districtId = new Long(jObj.getString("districtId"));
		Long constiId =  new Long(jObj.getString("constituencyId"));
		
		mandalAllElectionResultsVO = biElectionPageService.getAllPartysElectionResultsInAConstituencyMandalWise(new Long(232));
		
		return Action.SUCCESS;
	}


}
