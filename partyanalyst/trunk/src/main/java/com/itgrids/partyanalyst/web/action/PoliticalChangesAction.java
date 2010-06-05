package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPoliticalChangesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PoliticalChangesAction extends ActionSupport implements
		ServletContextAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	
	private String task = null;
	JSONObject jObj = null;
	
	private IPoliticalChangesService politicalChangesService;
	private IStaticDataService staticDataService;
	
	private List<PoliticalChangesVO> politicalChangesData;   
	private List<SelectOptionVO> informationSources,staticParties;
	private PoliticalChangesVO externalPersonInfo;
	
	/*//jsp fields
	
	private String titleTextField;
	private String descriptionTextBox;
	private String identifiedFromText;
	private String reportedFromText;
	private String selectedPartyBox;
	private String userTypeSelectBox;
	private String ;
	private String ;
	private String ;
	private String ;
	//
	
*/	
	
	
	public PoliticalChangesVO getExternalPersonInfo() {
		return externalPersonInfo;
	}

	public void setExternalPersonInfo(PoliticalChangesVO externalPersonInfo) {
		this.externalPersonInfo = externalPersonInfo;
	}

	public List<PoliticalChangesVO> getPoliticalChangesData() {
		return politicalChangesData;
	}

	public void setPoliticalChangesData(
			List<PoliticalChangesVO> politicalChangesData) {
		this.politicalChangesData = politicalChangesData;
	}

	
	public List<SelectOptionVO> getStaticParties() {
		return staticParties;
	}

	public void setStaticParties(List<SelectOptionVO> staticParties) {
		this.staticParties = staticParties;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public void setServletContext(ServletContext context) {
		this.setContext(context);
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ServletContext getContext() {
		return context;
	}
	
	public IPoliticalChangesService getPoliticalChangesService() {
		return politicalChangesService;
	}

	public void setPoliticalChangesService(
			IPoliticalChangesService politicalChangesService) {
		this.politicalChangesService = politicalChangesService;
	}
	
	public List<SelectOptionVO> getInformationSources() {
		return informationSources;
	}

	public void setInformationSources(List<SelectOptionVO> informationSources) {
		this.informationSources = informationSources;
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
	
	public String execute() throws Exception {		
		return Action.SUCCESS;
	}
	
	public String getAllInformationSources(){
		String param = null;
		param = getTask();
		try {
				jObj = new JSONObject(param);			
				if(jObj.getString("task").equalsIgnoreCase("getPoliticalChangesInformationSources"))
				{				
					informationSources = politicalChangesService.getAllPoliticalInformationSources();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;	
	}
	
	public void saveDataForLocalPoliticalChanges(){
		String param = null; 
		param = getTask();
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		
		try {
				jObj = new JSONObject(param);			
				if(jObj.getString("task").equalsIgnoreCase("saveDataForLocalPoliticalChanges"))
				{				
					PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();
					politicalChangesVO.setSaveType(jObj.getString("saveType"));
					politicalChangesVO.setPoliticalChangeId(jObj.getLong("localPoliticalChangeId"));  
					politicalChangesVO.setTitle(jObj.getString("titleTextFieldData"));
					politicalChangesVO.setDescription(jObj.getString("descriptionTextBoxData"));
					politicalChangesVO.setDate(jObj.getString("identifiedFromTextData"));
					politicalChangesVO.setReportedDate(jObj.getString("reportedFromTextData"));
					politicalChangesVO.setSourceOfInformation(jObj.getString("userTypeSelectBoxData"));
					politicalChangesVO.setName(jObj.getString("externalPersonNameData"));
					politicalChangesVO.setMobile(jObj.getString("externalPersonMobileData"));
					politicalChangesVO.setTelephoneNo(jObj.getString("externalPersonTelephoneNoData"));
					politicalChangesVO.setEmail(jObj.getString("externalPersonEmailData"));
					politicalChangesVO.setAddress(jObj.getString("externalPersonAddressData"));
					politicalChangesVO.setPartyId(jObj.getLong("selectedPartyBoxData"));
					politicalChangesVO.setSelectedPersonId(jObj.getLong("userTypeSelectBoxData"));
					politicalChangesVO.setUserId(userID);
					politicalChangesService.savePoliticalChangeDataReceivedFromUser(politicalChangesVO);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getAllStaticParties(){
		String param = null;
		param = getTask();
		try {
				jObj = new JSONObject(param);			
				if(jObj.getString("task").equalsIgnoreCase("getAllStaticParties"))
				{				
					staticParties = staticDataService.getStaticParties();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;	
	}
	
	public String getAllPoliticalChangesForTheUser(){
		String param = null;
		param = getTask();
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			Long userID = user.getRegistrationID();
			jObj = new JSONObject(param);			
			if(jObj.getString("task").equalsIgnoreCase("getAllPoliticalChangesForTheUser"))
			{	
				politicalChangesData = politicalChangesService.getAllPoliticalChanges(userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;	
	}
	
	public void deltePoliticalChange(){
		String param = null;
		param = getTask();		
		try {
			jObj = new JSONObject(param);					
			if(jObj.getString("task").equalsIgnoreCase("deltePoliticalChange"))
			{
				politicalChangesService.deltePoliticalChangeBasedOnUser(jObj.getLong("politicalChangeId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getExternalPersonDetails(){
		String param = null;
		param = getTask();
		try {			
				jObj = new JSONObject(param);				
				if(jObj.getString("task").equalsIgnoreCase("getExternalPersonDetails") || jObj.getString("task").equalsIgnoreCase("getExternalPersonDetailsForEdit")) 
				{	
					externalPersonInfo = politicalChangesService.getExternalPersonDetails(jObj.getLong("politicalChangeId"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return Action.SUCCESS;	
	} 
}
