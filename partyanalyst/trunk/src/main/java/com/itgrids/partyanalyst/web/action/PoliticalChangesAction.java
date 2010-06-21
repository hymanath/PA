package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
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
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IPoliticalChangesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

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
	
	private String type,externalPerson;
	private Long localPoliticalChangeId;
	private IPoliticalChangesService politicalChangesService;
	private IInfluencingPeopleService influencingPeopleService;
	private IStaticDataService staticDataService;
	
	private List<PoliticalChangesVO> politicalChangesData;   
	private List<SelectOptionVO> informationSources,staticParties,effectedRange;
	private List<SelectOptionVO> informationSourcesList,staticPartiesList,effectedRangeList;
	private PoliticalChangesVO externalPersonInfo,editPoliticalChangeData;
	private String name,description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PoliticalChangesVO getEditPoliticalChangeData() {
		return editPoliticalChangeData;
	}

	public void setEditPoliticalChangeData(
			PoliticalChangesVO editPoliticalChangeData) {
		this.editPoliticalChangeData = editPoliticalChangeData;
	}

	public Long getLocalPoliticalChangeId() {
		return localPoliticalChangeId;
	}

	public void setLocalPoliticalChangeId(Long localPoliticalChangeId) {
		this.localPoliticalChangeId = localPoliticalChangeId;
	}

	public String getExternalPerson() {
		return externalPerson;
	}

	public void setExternalPerson(String externalPerson) {
		this.externalPerson = externalPerson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}

	public List<SelectOptionVO> getEffectedRange() {
		return effectedRange;
	}

	public void setEffectedRange(List<SelectOptionVO> effectedRange) {
		this.effectedRange = effectedRange;
	}

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

	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
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
		
		effectedRangeList = new ArrayList<SelectOptionVO>();
		informationSourcesList = new ArrayList<SelectOptionVO>();
		staticPartiesList = new ArrayList<SelectOptionVO>();
		HttpSession session = request.getSession();
		
		externalPerson = IConstants.EXTERNAL_PERSON;
		effectedRangeList = influencingPeopleService.getInfluenceRange();
		informationSourcesList = politicalChangesService.getAllPoliticalInformationSources();
		staticPartiesList = staticDataService.getStaticParties();
		
		session = request.getSession();
		session.setAttribute("effectedRangeList", effectedRangeList);
		session.setAttribute("informationSourcesList",informationSourcesList);
		session.setAttribute("staticPartiesList",staticPartiesList);
		
		return Action.SUCCESS;
	}
	
	public void removeSessionVariablesForPoliticalChanges(){
		try {
			HttpSession session = request.getSession();
			session = request.getSession();
			session.removeAttribute("effectedRangeList");
			session.removeAttribute("informationSourcesList");
			session.removeAttribute("staticPartiesList");		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getEffectedRangeForAUser(){
		String param = null;
		param = getTask();
		try {
				jObj = new JSONObject(param);			
				if(jObj.getString("task").equalsIgnoreCase("effectedRange"))
				{				
					effectedRange = influencingPeopleService.getInfluenceRange();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
					name = jObj.getString("titleTextFieldData");
					
					PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();
					politicalChangesVO.setRange(jObj.getString("range"));
					politicalChangesVO.setRangeId(jObj.getLong("rangeId"));
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
	
	public String getDetailsByPoliticalChangeId(){
		String param = null;
		param = getTask();
		try {			
				jObj = new JSONObject(param);				
				if(jObj.getString("task").equalsIgnoreCase("getDetailsBylocalPoliticalChangeId")) 
				{					
					editPoliticalChangeData = politicalChangesService.getDetailsBylocalPoliticalChangeId(jObj.getLong("localPoliticalChangeId"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return Action.SUCCESS;	
	}
}
