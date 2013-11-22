package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.INewsAnalysisService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NewsAnalysisAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5662347083673292646L;
	private static final Logger LOG = Logger.getLogger(NewsAnalysisAction.class);
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private INewsAnalysisService newsAnalysisService;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> districtsList,parlConstiList,assemConstiList;
	private HttpSession session ; 
	
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

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public List<SelectOptionVO> getParlConstiList() {
		return parlConstiList;
	}

	public void setParlConstiList(List<SelectOptionVO> parlConstiList) {
		this.parlConstiList = parlConstiList;
	}

	public List<SelectOptionVO> getAssemConstiList() {
		return assemConstiList;
	}

	public void setAssemConstiList(List<SelectOptionVO> assemConstiList) {
		this.assemConstiList = assemConstiList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public INewsAnalysisService getNewsAnalysisService() {
		return newsAnalysisService;
	}

	public void setNewsAnalysisService(INewsAnalysisService newsAnalysisService) {
		this.newsAnalysisService = newsAnalysisService;
	}
	
	
	public String execute()
	{
		ConstituencyInfoVO constituencyInfoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(2L,1L);
		 ConstituencyInfoVO parliamantConstis = staticDataService.getConstituenciesByElectionTypeAndStateId(1L,1L);
		 districtsList =  staticDataService.getDistricts(1l);
		 parlConstiList = parliamantConstis.getConstituencies();
		 assemConstiList = constituencyInfoVO.getConstituencies();
		 
		return Action.SUCCESS;
	}
	
	public String getAnalysedData()
	{
		try {
			LOG.debug("Entered into getAnalysedData method in NewsAnalysisAction Ation");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return Action.ERROR;
			jObj = new JSONObject(getTask());
			List<Long> keywordsList = new ArrayList<Long>();
			List<Long> newsSourceList = new ArrayList<Long>();
			List<Long> locationValuesList = new ArrayList<Long>();
			String fromdate = jObj.getString("fromdate");
			String todate = jObj.getString("todate");
			Long whoPartyId = jObj.getLong("whoPartyId");
			Long whomPartyId = jObj.getLong("whomPartyId");
			Long whoCandidateId = jObj.getLong("whoCandidateId");
			Long whomCandidateId = jObj.getLong("whomCandidateId");
			Long whoBenfitId = jObj.getLong("whoBenfitId");
			Long whomBenfitId = jObj.getLong("whomBenfitId");
			Long locationLevelId = jObj.getLong("locationLevelId");
			String locationLevelValue = jObj.getString("locationLevelValue");
			if(locationLevelValue.trim().length() > 0)
			{
				String[] options = locationLevelValue.split(",");
				if(options.length > 1)
				{
					for (String value : options) {
						locationValuesList.add(Long.valueOf(value.trim()));
					}
				}
			}
			
			
			
			String newsSourceId = jObj.getString("newsSourceId");
			if(newsSourceId.trim().length() > 0)
			{
				String[] newsSourceIdObj = newsSourceId.split(",");
				if(newsSourceIdObj.length > 1)
				{
					for (String value : newsSourceIdObj) {
						newsSourceList.add(Long.valueOf(value.trim()));
					}
				}
			}
			
			
			
			String KeyWordsList = jObj.getString("KeyWordsList");
			if(KeyWordsList.trim().length() >0)
			{
				String[] KeyWordsListObj = KeyWordsList.split(",");
				if(KeyWordsListObj.length > 1)
				{
					for (String value : KeyWordsListObj) {
						keywordsList.add(Long.valueOf(value.trim()));
					}
				}
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in getAnalysedData method in NewsAnalysisAction Ation");
		}
		return Action.SUCCESS;
	}
	

	
}
