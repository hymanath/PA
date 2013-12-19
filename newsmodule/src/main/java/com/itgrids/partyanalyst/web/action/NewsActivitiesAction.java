package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.INewsAnalysisService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IWebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NewsActivitiesAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(NewsActivitiesAction.class);
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private HttpSession session ; 
	private IStaticDataService staticDataService;
	private INewsAnalysisService newsAnalysisService;
	private List<SelectOptionVO> districtsList,assemConstiList;
	private List<SelectOptionVO> categoeriesList;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
	
	/**
	 * @return the categoeriesList
	 */
	public List<SelectOptionVO> getCategoeriesList() {
		return categoeriesList;
	}




	/**
	 * @param categoeriesList the categoeriesList to set
	 */
	public void setCategoeriesList(List<SelectOptionVO> categoeriesList) {
		this.categoeriesList = categoeriesList;
	}




	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	/**
	 * @return the jObj
	 */
	public JSONObject getjObj() {
		return jObj;
	}
	/**
	 * @param jObj the jObj to set
	 */
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	
	/**
	 * @return the districtsList
	 */
	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}
	/**
	 * @param districtsList the districtsList to set
	 */
	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}
	/**
	 * @return the assemConstiList
	 */
	public List<SelectOptionVO> getAssemConstiList() {
		return assemConstiList;
	}
	/**
	 * @param assemConstiList the assemConstiList to set
	 */
	public void setAssemConstiList(List<SelectOptionVO> assemConstiList) {
		this.assemConstiList = assemConstiList;
	}
	
	
	/**
	 * @return the staticDataService
	 */
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	/**
	 * @param staticDataService the staticDataService to set
	 */
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	
	/**
	 * @return the newsAnalysisService
	 */
	public INewsAnalysisService getNewsAnalysisService() {
		return newsAnalysisService;
	}
	/**
	 * @param newsAnalysisService the newsAnalysisService to set
	 */
	public void setNewsAnalysisService(INewsAnalysisService newsAnalysisService) {
		this.newsAnalysisService = newsAnalysisService;
	}
	public String execute()
	{
		try {
			LOG.info("Entered into execute() method in NewsActivitiesAction Ation");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null){
				return Action.ERROR;
			}
			districtsList =  staticDataService.getDistricts(1l);
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.info("Exception occured in execute() method in NewsActivitiesAction Ation",e);
			return Action.ERROR;
			
		}
		
	}
	
	public String getConstituencyes()
	{
		try {
			LOG.info("Entered into getConstituencyes() method in NewsActivitiesAction Ation");
			jObj                     = new JSONObject(getTask());
			String districtIds       = jObj.getString("districtIds");
			List<Long> districtsList = new ArrayList<Long>();
			String[] districts       = districtIds.split(",");
			for (String string : districts) 
			{
				districtsList.add(Long.valueOf(string));
			}
			assemConstiList          = newsAnalysisService.getConstituencyesList(districtsList);
		} catch (ParseException e) {
			LOG.info("Exception occured in getConstituencyes() method in NewsActivitiesAction Ation",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCategoeryWiseNews()
	{
		try {
			LOG.info("Entered into getCategoeryWiseNews() method in NewsActivitiesAction Ation");
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(jObj.getString("task").equalsIgnoreCase("categoeryWiseNews"))
			{
				String categorieyIds        = jObj.getString("categorieyIds");
				String constituencyIds      = jObj.getString("constituencyIds");
				String fromDateStr          = jObj.getString("fromDate");
				String toDateStr            = jObj.getString("toDate");
				String requestType          = jObj.getString("requestType");
				Long startIndex             = jObj.getLong("startIndex");
				Long maxIndex               = jObj.getLong("maxIndex");
				Long partyId                = jObj.getLong("partyId");
				String[] categoeryStr       = categorieyIds.split(",");
				String[] constituencyStr    = constituencyIds.split(",");
				List<Long> categoeryList    = new ArrayList<Long>();
				List<Long> constituencyList = new ArrayList<Long>();
				for (String catg : categoeryStr) {
					categoeryList.add(Long.valueOf(catg));
				}
				for (String constituency : constituencyStr) {
					constituencyList.add(Long.valueOf(constituency));
				}
				 String[] urls = request.getRequestURL().toString().split("getCategoeryWiseNewsAction.action");
				 String url = urls[0].concat("createActivitiesReportAction.action?");
				categoeriesList = newsAnalysisService.getProgramsWiseNews(categoeryList, constituencyList, fromDateStr, toDateStr,startIndex,maxIndex,partyId,regVO.getRegistrationID(),url,requestType);
			}
			else if(jObj.getString("task").equalsIgnoreCase("genereatePDFOrExcel"))
			{
				String categorieyIds        = jObj.getString("categorieyIds");
				String constituencyIds      = jObj.getString("constituencyIds");
				String fromDateStr          = jObj.getString("fromDate");
				String toDateStr            = jObj.getString("toDate");
				String districtIds          = jObj.getString("districtIds");
				String typeFor              = jObj.getString("typeFor");
				Long partyId                = jObj.getLong("partyId");
				String[] categoeryStr       = categorieyIds.split(",");
				String[] constituencyStr    = constituencyIds.split(",");
				String[] districtStr        = districtIds.split(",");
				List<Long> categoeryList    = new ArrayList<Long>();
				List<Long> constituencyList = new ArrayList<Long>();
				List<Long> districtList      = new ArrayList<Long>();
				for (String catg : categoeryStr) {
					categoeryList.add(Long.valueOf(catg));
				}
				for (String constituency : constituencyStr) {
					constituencyList.add(Long.valueOf(constituency));
				}
				for (String district : districtStr) {
					districtList.add(Long.valueOf(district));
				}
				String path = IWebConstants.STATIC_CONTENT_FOLDER_URL;
				categoeriesList = newsAnalysisService.generatePdfOrExcel(categoeryList,constituencyList,districtList,fromDateStr,toDateStr,typeFor,path,partyId);
			}
			else
			{
				String categorieyIds        = jObj.getString("categorieyIds");
				String constituencyIds      = jObj.getString("constituencyIds");
				String fromDateStr          = jObj.getString("fromDate");
				String toDateStr            = jObj.getString("toDate");
				String type                 = jObj.getString("type");
				String districtIds          = jObj.getString("districtIds");
				Long partyId                = jObj.getLong("partyId");
				String[] categoeryStr       = categorieyIds.split(",");
				String[] constituencyStr    = constituencyIds.split(",");
				String[] districtStr        = districtIds.split(",");
				List<Long> categoeryList    = new ArrayList<Long>();
				List<Long> constituencyList = new ArrayList<Long>();
				List<Long> districtList      = new ArrayList<Long>();
				for (String catg : categoeryStr) {
					categoeryList.add(Long.valueOf(catg));
				}
				for (String constituency : constituencyStr) {
					constituencyList.add(Long.valueOf(constituency));
				}
				for (String district : districtStr) {
					districtList.add(Long.valueOf(district));
				}
				categoeriesList = newsAnalysisService.getCategoeryWiseCountDetails(categoeryList,constituencyList,fromDateStr,toDateStr,type,districtList,partyId);
			}
			
			
		} catch (Exception e) {
			LOG.info("Entered into getCategoeryWiseNews() method in NewsActivitiesAction Ation");
		}
		return Action.SUCCESS;
	}
}
