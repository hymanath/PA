package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IUserVoterService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CustomCategoryWiseVoterDetailsAction extends ActionSupport implements ServletRequestAware ,ServletContextAware{

	
	private static final long serialVersionUID = 8680206637030330902L;
	private static final Logger LOG = Logger.getLogger(CustomCategoryWiseVoterDetailsAction.class);
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private IUserVoterService userVoterService;
	private ServletContext context;
	private Long customCategoryId;
	private String locationType;
	private Long locationValue;
	private Long constituencyId;
	private List<VoterVO> categoryWiseVoterDet,votersData;
	private Long publicationDateId;
	private ConstituencyManagementVO constituencyManagementVO;
	private VoterVO voterVO;
	private String categoryName;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public IUserVoterService getUserVoterService() {
		return userVoterService;
	}

	public void setUserVoterService(IUserVoterService userVoterService) {
		this.userVoterService = userVoterService;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	
	public ServletContext getContext() {
		return context;
	}
	
	public Long getCustomCategoryId() {
		return customCategoryId;
	}

	public void setCustomCategoryId(Long customCategoryId) {
		this.customCategoryId = customCategoryId;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public List<VoterVO> getCategoryWiseVoterDet() {
		return categoryWiseVoterDet;
	}

	public void setCategoryWiseVoterDet(List<VoterVO> categoryWiseVoterDet) {
		this.categoryWiseVoterDet = categoryWiseVoterDet;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}
	public List<VoterVO> getVotersData() {
		return votersData;
	}

	public void setVotersData(List<VoterVO> votersData) {
		this.votersData = votersData;
	}
	public VoterVO getVoterVO() {
		return voterVO;
	}

	public void setVoterVO(VoterVO voterVO) {
		this.voterVO = voterVO;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		
        if(customCategoryId !=null && customCategoryId > 0)
        	categoryName = userVoterService.getCategoryNameByCategoryId(customCategoryId);
        	
		return Action.SUCCESS;
	}

	public String getVoterCategoryData()
	{
		
		try {
			
			LOG.debug("Entered into the getVoterCategoryData() method in VotersEditAction");
			session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
			 userId = user.getRegistrationID();
			
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			Integer maxRecords = Integer.parseInt(request.getParameter("results"));
			Long publicationId = request.getParameter("publicationId") != null ?Long.parseLong(request.getParameter("publicationId")):0L;
			Long ids = request.getParameter("id") != null ? Long.parseLong(request.getParameter("id")):0L;
			Long constituencyId = request.getParameter("constituencyId") != null? Long.parseLong(request.getParameter("constituencyId")) :0L;
			Long categoryId = request.getParameter("categoryId")!=null?Long.parseLong(request.getParameter("categoryId")):0L;
			
			String buildType = request.getParameter("buildType");
			String sort = request.getParameter("sort");
			String dir = request.getParameter("dir");
			
			VoterDataVO voterDataVO = new VoterDataVO();
			voterDataVO.setConstituencyId(constituencyId);
			voterDataVO.setId(ids);
			voterDataVO.setPublicationId(publicationId);
			voterDataVO.setStartIndex(Long.valueOf(startIndex));
			voterDataVO.setMaxIndex(Long.valueOf(maxRecords));
			voterDataVO.setDir(dir);
			voterDataVO.setBuildType(buildType);
			voterDataVO.setSort(sort);
			voterDataVO.setCategoryId(categoryId);
			
			List<Long> categories = new ArrayList<Long>();
			if(request.getParameter("reqfields").trim().length() >0){
				String[] idsArray = request.getParameter("reqfields").trim().split(",");
			    for(String id : idsArray){
                   if(id.equalsIgnoreCase("party")){
                	   voterDataVO.setPartyPresent(true);
			    	}else if(id.equalsIgnoreCase("cast")){
			    		voterDataVO.setCastePresent(true);
			    	}
			    	else{
			    		categories.add(new Long(id));
			    	}
			    }
			} 
			votersData = userVoterService.getCategoryWiseVoterData(voterDataVO , userId , categories);
			voterVO = new VoterVO();
			if(votersData != null & votersData.size() > 0)
			{
				voterVO.setVotersList(votersData);
				voterVO.setTotalVoters(votersData.get(0).getTotalVoters());
			}
			
		} catch (Exception e) {
			LOG.error("Error occured in the getvoterData() method in VotersEditAction", e);
		}
		return Action.SUCCESS;
	}
	
	
	
}
