package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IUserVoterService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AgeWiseVoterDetailsAction extends ActionSupport implements
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(AgeWiseVoterDetailsAction.class);
	transient private HttpServletRequest request;
	private HttpSession session;
	
	private Long constituencyId;
	private Long publicationDateId;
	private Long mandalId;
	private Long panchayatId;
	private String buildType;
	private String name;
	private String retrieveType;
	private String type;
	private Long startNumber;
	private Long publicationYear;
	private Long categoryId;
	private Long importanceId;
	private Long lastIndex;
	private Long locationId;
	private Long locationValue;
	private Long publicationId;
	private Long startIndex;
	private String categoryStr;
	private String displayStr;
	private Long count;
	private String status;
	private Long srcId;
	private String title;
	
	private String typename;
	/*For castWiseVoterDetails */
	
	private Long id;
	private String typeName;
	private String resultFor;
	private String queryType;
	
	private String btnName;
	private String typeValue;
	private String maintype;
	private Long mainreqid;
	private Long contentId;
	private String publicationDate;
	private String task;
	private Long lclBodyId;
	transient private JSONObject jObj;
	private IUserVoterService userVoterService;
	
	private List<SelectOptionVO> resultList;
	private String attributeIds;
	private String checkAreaType;
		
	public String getCheckAreaType() {
		return checkAreaType;
	}
	public void setCheckAreaType(final String checkAreaType) {
		this.checkAreaType = checkAreaType;
	}
	public Long getLclBodyId() {
		return lclBodyId;
	}
	public void setLclBodyId(final Long lclBodyId) {
		this.lclBodyId = lclBodyId;
	}
	public IUserVoterService getUserVoterService() {
		return userVoterService;
	}
	public void setUserVoterService(final IUserVoterService userVoterService) {
		this.userVoterService = userVoterService;
	}
	public List<SelectOptionVO> getResultList() {
		return resultList;
	}
	public void setResultList(final List<SelectOptionVO> resultList) {
		this.resultList = resultList;
	}
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(final HttpSession session) {
		this.session = session;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(final Long contentId) {
		this.contentId = contentId;
	}
	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(final String btnName) {
		this.btnName = btnName;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(final String typeValue) {
		this.typeValue = typeValue;
	}

	public String getMaintype() {
		return maintype;
	}

	public void setMaintype(final String maintype) {
		this.maintype = maintype;
	}

	public Long getMainreqid() {
		return mainreqid;
	}

	public void setMainreqid(final Long mainreqid) {
		this.mainreqid = mainreqid;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	public String getResultFor() {
		return resultFor;
	}

	public void setResultFor(final String resultFor) {
		this.resultFor = resultFor;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(final String queryType) {
		this.queryType = queryType;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}
	
	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(final Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(final Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(final Long mandalId) {
		this.mandalId = mandalId;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(final Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(final String buildType) {
		this.buildType = buildType;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getRetrieveType() {
		return retrieveType;
	}

	public void setRetrieveType(final String retrieveType) {
		this.retrieveType = retrieveType;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	
	public Long getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(final Long startNumber) {
		this.startNumber = startNumber;
	}

	
	public Long getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(final Long publicationYear) {
		this.publicationYear = publicationYear;
	}

	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getImportanceId() {
		return importanceId;
	}

	public void setImportanceId(final Long importanceId) {
		this.importanceId = importanceId;
	}

	public Long getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(final Long lastIndex) {
		this.lastIndex = lastIndex;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(final Long locationId) {
		this.locationId = locationId;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(final Long locationValue) {
		this.locationValue = locationValue;
	}

	public Long getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(final Long publicationId) {
		this.publicationId = publicationId;
	}

	public Long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(final Long startIndex) {
		this.startIndex = startIndex;
	}

	
	public String getCategoryStr() {
		return categoryStr;
	}

	public void setCategoryStr(final String categoryStr) {
		this.categoryStr = categoryStr;
	}

	public String getDisplayStr() {
		return displayStr;
	}

	public void setDisplayStr(final String displayStr) {
		this.displayStr = displayStr;
	}

	
	public Long getCount() {
		return count;
	}

	public void setCount(final Long count) {
		this.count = count;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Long getSrcId() {
		return srcId;
	}

	public void setSrcId(final Long srcId) {
		this.srcId = srcId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(final String typename) {
		this.typename = typename;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(final String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getAttributeIds() {
		return attributeIds;
	}
	public void setAttributeIds(final String attributeIds) {
		this.attributeIds = attributeIds;
	}
	public String execute()
	{
		final HttpSession session = request.getSession();
		final RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.ERROR;
		}
		else
		{
			return Action.SUCCESS;
		}
		
		
	}
	
	public String getWindowForCast()
	{
		final HttpSession session = request.getSession();
		final RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.ERROR;
		}
		else
		{
			return Action.SUCCESS;
		}
		
		
	}
	public String getTask() {
		return task;
	}
	public void setTask(final String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObjfinal (final JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getUserVoterCategoryList()
	{
		try{
			final List<Long> userIds = new ArrayList<Long>(0);
			jObj = new JSONObject(getTask());
			session = request.getSession();
			final RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null){
				return ERROR;
			}
			userIds.add(regVO.getRegistrationID());
			resultList = userVoterService.getUserVoterCategoryList(userIds);
		}catch (Exception e) {
			LOG.error("Exception rised in getUserVoterCategoryList",e);
			
		}
		return SUCCESS;
	}

}
