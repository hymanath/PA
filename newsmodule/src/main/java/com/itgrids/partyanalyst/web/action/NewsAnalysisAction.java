package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AnalysisBasicInfoVO;
import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsAnalysisVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
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
	private NewsAnalysisVO result;
	private Long sourceCandId;
	private Long destiCandId;
	private Long sourcePartyId;
	private Long destiPartyId;
	private Long locationLvl;
	private Long locationId;
	private Long sourceId;
	private Long categoryId;
	private Long keywordId;
	private String  benifitsFor;
	private String startDate;
	private String endDate;
	private Integer startValue;
	private Integer endValue;
	private List<FileVO> analysedNewsDetails;
	private ResultStatus resultStatus;
	private Long sourceBenifitId;
	private Long destiBenifitId;
	private Long type;
	private String sourceType;
	private String destiType;
	private String considerParty,locationIds;
	private SelectOptionVO graphData;
	private AnalysisBasicInfoVO countInfo;
	private FileVO analysedDetails;
	private List<NewsAnalysisVO>  attributeCount;
	
	/**
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * @return the resultStatus
	 */
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	/**
	 * @param resultStatus the resultStatus to set
	 */
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

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

	public NewsAnalysisVO getResult() {
		return result;
	}

	public void setResult(NewsAnalysisVO result) {
		this.result = result;
	}

	public Long getDestiCandId() {
		return destiCandId;
	}

	public void setDestiCandId(Long destiCandId) {
		this.destiCandId = destiCandId;
	}

	public Long getSourcePartyId() {
		return sourcePartyId;
	}

	public void setSourcePartyId(Long sourcePartyId) {
		this.sourcePartyId = sourcePartyId;
	}

	public Long getDestiPartyId() {
		return destiPartyId;
	}

	public void setDestiPartyId(Long destiPartyId) {
		this.destiPartyId = destiPartyId;
	}

	public Long getLocationLvl() {
		return locationLvl;
	}

	public void setLocationLvl(Long locationLvl) {
		this.locationLvl = locationLvl;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}

	public String getBenifitsFor() {
		return benifitsFor;
	}

	public void setBenifitsFor(String benifitsFor) {
		this.benifitsFor = benifitsFor;
	}

	public Long getSourceCandId() {
		return sourceCandId;
	}

	public void setSourceCandId(Long sourceCandId) {
		this.sourceCandId = sourceCandId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getStartValue() {
		return startValue;
	}

	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}

	public Integer getEndValue() {
		return endValue;
	}

	public void setEndValue(Integer endValue) {
		this.endValue = endValue;
	}

	public List<FileVO> getAnalysedNewsDetails() {
		return analysedNewsDetails;
	}

	public void setAnalysedNewsDetails(List<FileVO> analysedNewsDetails) {
		this.analysedNewsDetails = analysedNewsDetails;
	}

	public Long getSourceBenifitId() {
		return sourceBenifitId;
	}

	public void setSourceBenifitId(Long sourceBenifitId) {
		this.sourceBenifitId = sourceBenifitId;
	}

	public Long getDestiBenifitId() {
		return destiBenifitId;
	}

	public void setDestiBenifitId(Long destiBenifitId) {
		this.destiBenifitId = destiBenifitId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getDestiType() {
		return destiType;
	}

	public void setDestiType(String destiType) {
		this.destiType = destiType;
	}

	public String getConsiderParty() {
		return considerParty;
	}

	public void setConsiderParty(String considerParty) {
		this.considerParty = considerParty;
	}

	public SelectOptionVO getGraphData() {
		return graphData;
	}

	public void setGraphData(SelectOptionVO graphData) {
		this.graphData = graphData;
	}

	public AnalysisBasicInfoVO getCountInfo() {
		return countInfo;
	}

	public void setCountInfo(AnalysisBasicInfoVO countInfo) {
		this.countInfo = countInfo;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}
	public FileVO getAnalysedDetails() {
		return analysedDetails;
	}

	public void setAnalysedDetails(FileVO analysedDetails) {
		this.analysedDetails = analysedDetails;
	}
	
	public List<NewsAnalysisVO> getAttributeCount() {
		return attributeCount;
	}

	public void setAttributeCount(List<NewsAnalysisVO> attributeCount) {
		this.attributeCount = attributeCount;
	}

	public String execute()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null){
			return Action.ERROR;
		}else if(regVO.getUserAccessType() == null){
		  return "fail";
		}else if(!regVO.getUserAccessType().equalsIgnoreCase("Admin")){
        	return "input";
		}
		
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
			AnalysisVO vo =  populateDataToVo();
			result = newsAnalysisService.analyseNewsWithSelectedParameters(vo);
			//newsAnalysisService.getResults(vo);
		} catch (Exception e) {
			LOG.error("Exception raised in getAnalysedData method in NewsAnalysisAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public AnalysisVO populateDataToVo() throws Exception{
		
		jObj = new JSONObject(getTask());
		
		String fromdate           = jObj.getString("fromdate");
		String todate             = jObj.getString("todate");
		Long whoPartyId           = jObj.getLong("whoPartyId");
		Long whomPartyId          = jObj.getLong("whomPartyId");
		Long whoCandidateId       = jObj.getLong("whoCandidateId");
		Long whomCandidateId      = jObj.getLong("whomCandidateId");
		Long whoBenfitId          = jObj.getLong("whoBenfitId");
		Long whomBenfitId         = jObj.getLong("whomBenfitId");
		Long locationLevelId      = jObj.getLong("locationLevelId");
		String checkedType        = jObj.getString("checkedType");
		String analyseCandidate   = jObj.getString("analyseCandidate");
		String locationLevelValue = jObj.getString("locationLevelValue");
		AnalysisVO vo = new AnalysisVO();
		
		
		vo.setSourceBenifitId(whoBenfitId);
		vo.setDestiBenifitId(whomBenfitId);
		String newsSourceId = jObj.getString("newsSourceId");
		if(newsSourceId.trim().length() > 0)
		{
			vo.setSourceIds(newsSourceId.trim());
			vo.setSourcePresent(true);
		}
		if(whoCandidateId.longValue() > 0){
			vo.setSourceCand(true);
			vo.setSourceCandidateId(whoCandidateId);
		}else if(whoPartyId.longValue() > 0){
			vo.setSourceParty(true);
			vo.setSourcePartyId(whoPartyId);
		}
		if(whomCandidateId.longValue() > 0){
			vo.setDestiCand(true);
			vo.setDestiCandidateId(whomCandidateId);
		}else if(whomPartyId.longValue() > 0){
			vo.setDestiParty(true);
			vo.setDestiPartyId(whomPartyId);
		}
		
		String keyWordsList = jObj.getString("KeyWordsList");
		if(keyWordsList.trim().length() >0)
		{
			if(checkedType.equalsIgnoreCase("keyword")){
				vo.setByKeyword(true);
				vo.setGallaryKeyWordIds(keyWordsList.trim());
			}else if(checkedType.equalsIgnoreCase("category")){
				vo.setByCategory(true);
				vo.setGallaryKeyWordIds(keyWordsList.trim());
			}
		}
		if(analyseCandidate.trim().length() > 0){
			
			if(analyseCandidate.trim().equalsIgnoreCase("sourceDesti")){
				vo.setBySourceCand(true);
				vo.setByDestiCand(true);
			}
			else if(analyseCandidate.trim().equalsIgnoreCase("source")){
				vo.setBySourceCand(true);
			}else if(analyseCandidate.trim().equalsIgnoreCase("destination")){
				vo.setByDestiCand(true);
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(fromdate != null && fromdate.trim().length() >0){
			Date fromDate = format.parse(fromdate.trim());
			vo.setFromDate(fromDate);
		}
		if(todate != null && todate.trim().length() >0){
			Date toDate = format.parse(todate.trim());
			vo.setToDate(toDate);
		}
		if(locationLevelId > 0 && locationLevelValue.trim().length() > 0){
			vo.setLocationPresent(true);
			vo.setLocationLvl(locationLevelId);
			vo.setLocationValues(locationLevelValue.trim());
		}
		return vo;
	}
	
	public String saveDesignation()
	{
		try {
			LOG.debug("Entered into saveDesignation method in NewsAnalysisAction Ation");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return Action.ERROR;
			
			jObj = new JSONObject(getTask());
			String designationString = jObj.getString("designation");
			resultStatus = newsAnalysisService.saveDesignationDetails(designationString);
		} catch (Exception e) {
			LOG.error("Exception raised in saveDesignation method in NewsAnalysisAction Ation");
		}
		return Action.SUCCESS;
	}
	
	public String savePartyDetails()
	{
		try {
			LOG.debug("Entered into savePartyDetails method in NewsAnalysisAction Ation");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return Action.ERROR;
			
			jObj = new JSONObject(getTask());
			String partyLongName = jObj.getString("partyLongName");
			String partyShortName = jObj.getString("partyShortName");
			resultStatus = newsAnalysisService.savePartyDetails(partyShortName,partyLongName);
		} catch (Exception e) {
			LOG.error("Exception raised in savePartyDetails method in NewsAnalysisAction Ation");
		}
		return Action.SUCCESS;
	}
	public String viewSelectedAnalysedNews(){
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
        if(user == null){
        	return ERROR;
        }
        
        return Action.SUCCESS;
	}
	
    public String getAnalysedNews(){
    	try {
    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    		NewsAnalysisVO vo = new NewsAnalysisVO();
    		 vo.setSourceCandId(getSourceCandId());
    		 vo.setDestiCandId(getDestiCandId());
    		 vo.setSourcePartyId(getSourcePartyId());
    		 vo.setDestiPartyId(getDestiPartyId());
    		 vo.setLocationLvl(getLocationLvl());
    		 vo.setLocationId(getLocationId());
    		 vo.setSourceId(getSourceId());
    		 vo.setCategoryId(getCategoryId());
    		 vo.setKeywordId(getKeywordId());
    		 vo.setBenifitsFor(getBenifitsFor());
    		 vo.setDestiBenifitId(getDestiBenifitId());
    		 vo.setDestiBenifitId(getDestiBenifitId());
    		 vo.setSourceType(getSourceType());
    		 vo.setDestiType(getDestiType());
    		 vo.setConsiderParty(getConsiderParty());
    		 if(getType() != null){
    		   vo.setName(getType().toString());
    		 }else{
    			 vo.setName("other");
    		 }
    		 Date fromDate = (getStartDate()!=null && getStartDate().trim().length() > 0)?format.parse(getStartDate()):null;
    		 Date toDate = (getEndDate()!=null && getEndDate().trim().length() >0)?format.parse(getEndDate()):null;
    		 Integer startIndex = null;
    		 Integer maxIndex = null;
    		 if(startValue != null && startValue > 0){
    			 startIndex = startValue;
    		 }
    		 if(endValue != null && endValue > 0){
    			 maxIndex = endValue;
    		 }
    		 analysedNewsDetails = newsAnalysisService.getSelectedNews(vo, fromDate, toDate, startIndex, maxIndex);
    	}catch (Exception e) {
			LOG.error("Exception raised in getAnalysedNews method",e);
		}
    	return Action.SUCCESS;
    }
    public String getPartyAnalysedDataForGraph()
	{
		try {
			LOG.debug("Entered into getAnalysedData method in NewsAnalysisAction Ation");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return Action.ERROR;
			AnalysisVO vo =  populateDataToVo();
			graphData = newsAnalysisService.getPartyWiseNewsCountForGraph(vo);
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyAnalysedDataForGraph method in NewsAnalysisAction Action",e);
		}
		return Action.SUCCESS;
	}
    
    public String getAnalysedNewsCount(){
    	try {
			LOG.debug("Entered into getAnalysedNewsCount method in NewsAnalysisAction Ation");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return Action.ERROR;
			jObj = new JSONObject(getTask());
			String fromdate           = jObj.getString("fromdate");
			String todate             = jObj.getString("todate");
			Long partyId           = jObj.getLong("partyId");
			Long candidateId          = jObj.getLong("candidateId");
			Long locationLevelId      = jObj.getLong("locationLevelId");
			String locationLevelValue = jObj.getString("locationLevelValue");
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(fromdate != null && fromdate.trim().length() >0){
				startDate = format.parse(fromdate.trim());
			}
			if(todate != null && todate.trim().length() >0){
				endDate = format.parse(todate.trim());
			}
			countInfo = newsAnalysisService.getAnalysedNewsCount(startDate,endDate,partyId,candidateId,locationLevelId,locationLevelValue);
		} catch (Exception e) {
			LOG.error("Exception raised in getAnalysedNewsCount method in NewsAnalysisAction Action",e);
		}
		return Action.SUCCESS;
    }
    
    public String viewAnalysedNews(){
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
        if(user == null){
        	return ERROR;
        }
        
        return Action.SUCCESS;
	}
    
    public String getAllAnalysedNews(){
    	try {
    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    		 
    		 Date fromDate = (getStartDate()!=null && getStartDate().trim().length() > 0)?format.parse(getStartDate()):null;
    		 Date toDate = (getEndDate()!=null && getEndDate().trim().length() >0)?format.parse(getEndDate()):null;
    		 Integer startIndex = null;
    		 Integer maxIndex = null;
    		 if(startValue != null && startValue > 0){
    			 startIndex = startValue;
    		 }
    		 if(endValue != null && endValue > 0){
    			 maxIndex = endValue;
    		 }
    		 analysedNewsDetails = newsAnalysisService.getAnalysedNews(fromDate,toDate,sourcePartyId,sourceCandId,locationLvl,locationIds,destiPartyId,sourceType,benifitsFor,startIndex,maxIndex,categoryId,sourceId,destiPartyId,keywordId);
    	}catch (Exception e) {
			LOG.error("Exception raised in getAllAnalysedNews method",e);
		}
    	return Action.SUCCESS;
    }
    
    public String getAttributeWiseNewsCount(){
    	try {
    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    		 
    		 Date fromDate = (getStartDate()!=null && getStartDate().trim().length() > 0)?format.parse(getStartDate()):null;
    		 Date toDate = (getEndDate()!=null && getEndDate().trim().length() >0)?format.parse(getEndDate()):null;
    		 Integer startIndex = null;
    		 Integer maxIndex = null;
    		 if(startValue != null && startValue > 0){
    			 startIndex = startValue;
    		 }
    		 if(endValue != null && endValue > 0){
    			 maxIndex = endValue;
    		 }
    		 attributeCount = newsAnalysisService.getSourceCategoryCount(fromDate,toDate,sourcePartyId,sourceCandId,locationLvl,locationIds,sourceType,benifitsFor,startIndex,maxIndex,destiPartyId);
    	}catch (Exception e) {
			LOG.error("Exception raised in getAllAnalysedNews method",e);
		}
    	return Action.SUCCESS;
    }
  
    public String viewAttributeNews(){
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
        if(user == null){
        	return ERROR;
        }
        
        return Action.SUCCESS;
	}
    
}
