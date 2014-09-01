package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class PartyManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,ServletContextAware {

	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PartyManagementAction.class);
	private List<SelectOptionVO> partyList = new ArrayList<SelectOptionVO>(0);
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private IPartyDetailsService partyDetailsService;
	private IStaticDataService staticDataService;
	private GallaryVO gallaryVO;
	private ResultStatus result;
	private List<GallaryVO> gallaryList;
	private List<FileVO> fileVO;
	private List<SelectOptionVO> selectOptionList,keywordsList;
	private ServletContext context;
	private String userImageContentType;
	private String fileTitle;
	private String fileDescription;
	private String visibility;
	private Long gallaryId;
	private Long source;
	private Long language;
	private String keywords;
	private Long locationScope;
	private Long locationValue;
	private String fileDate;
	private File userImage;
	private InputStream inputStream;
	private ICandidateDetailsService candidateDetailsService;
    private Long partyId;
    private Long electionId;
    private String task = null;
	JSONObject jObj = null;
	private List<SelectOptionVO> electionTypesList;
	private List<SelectOptionVO> electionYearsList;
	private String profileType;
	private String profileId;
	private String profileGalleryType;
	private List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> districtsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> parlConstiList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> assemConstiList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> statesList1 = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> districtsList1 = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> parlConstiList1 = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> assemConstiList1 = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> categoriesList;
	private List<AddressVO> userDetailsVO;
	private List<BasicVO> basicVOList;
	
	

	public List<BasicVO> getBasicVOList() {
		return basicVOList;
	}

	public void setBasicVOList(List<BasicVO> basicVOList) {
		this.basicVOList = basicVOList;
	}

	public List<AddressVO> getUserDetailsVO() {
		return userDetailsVO;
	}

	public void setUserDetailsVO(List<AddressVO> userDetailsVO) {
		this.userDetailsVO = userDetailsVO;
	}

	public List<SelectOptionVO> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<SelectOptionVO> categoriesList) {
		this.categoriesList = categoriesList;
	}

	public List<SelectOptionVO> getDistrictsList1() {
		return districtsList1;
	}

	public void setDistrictsList1(List<SelectOptionVO> districtsList1) {
		this.districtsList1 = districtsList1;
	}

	public List<SelectOptionVO> getParlConstiList1() {
		return parlConstiList1;
	}

	public void setParlConstiList1(List<SelectOptionVO> parlConstiList1) {
		this.parlConstiList1 = parlConstiList1;
	}

	public List<SelectOptionVO> getAssemConstiList1() {
		return assemConstiList1;
	}

	public void setAssemConstiList1(List<SelectOptionVO> assemConstiList1) {
		this.assemConstiList1 = assemConstiList1;
	}

	private IRegionServiceData regionServiceDataImp;
	
	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileGalleryType() {
		return profileGalleryType;
	}

	public void setProfileGalleryType(String profileGalleryType) {
		this.profileGalleryType = profileGalleryType;
	}

	public void setElectionYearsList(List<SelectOptionVO> electionYearsList) {
		this.electionYearsList = electionYearsList;
	}

	public List<SelectOptionVO> getElectionYearsList() {
		return electionYearsList;
	}

	public void setElectionTypesList(List<SelectOptionVO> electionTypesList) {
		this.electionTypesList = electionTypesList;
	}

	public List<SelectOptionVO> getElectionTypesList() {
		return electionTypesList;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Long getLocationScope() {
		return locationScope;
	}

	public void setLocationScope(Long locationScope) {
		this.locationScope = locationScope;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public Long getLanguage() {
		return language;
	}

	public void setLanguage(Long language) {
		this.language = language;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Long getGallaryId() {
		return gallaryId;
	}

	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public List<SelectOptionVO> getSelectOptionList() {
		return selectOptionList;
	}

	public void setSelectOptionList(List<SelectOptionVO> selectOptionList) {
		this.selectOptionList = selectOptionList;
	}

	public List<FileVO> getFileVO() {
		return fileVO;
	}

	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}

	public List<GallaryVO> getGallaryList() {
		return gallaryList;
	}

	public void setGallaryList(List<GallaryVO> gallaryList) {
		this.gallaryList = gallaryList;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public GallaryVO getGallaryVO() {
		return gallaryVO;
	}

	public void setGallaryVO(GallaryVO gallaryVO) {
		this.gallaryVO = gallaryVO;
	}

	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}

	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}

	

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	public void setServletResponse(HttpServletResponse response){
		this.response =  response;
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
	
    public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	
	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	
   public List<SelectOptionVO> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<SelectOptionVO> keywordsList) {
		this.keywordsList = keywordsList;
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

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

   public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

    public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public List<SelectOptionVO> getStatesList1() {
		return statesList1;
	}

	public void setStatesList1(List<SelectOptionVO> statesList1) {
		this.statesList1 = statesList1;
	}

public String execute()
 {
	session = request.getSession();
	RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
	//candidateDetailsService.testgetCandidatesListByPartyIdsList();
	if (registrationVO != null) 
	{
		
		/*departmentsList = staticDataService.getDepartments();
		newsTypesList = staticDataService.getNewsTypes();*/
		if(!registrationVO.getUserRoles().contains("NEWSUPLOAD")){
        	return "noAccess";
		}
		
	// if("Admin".equalsIgnoreCase(registrationVO.getUserType()) || "subuser".equalsIgnoreCase(registrationVO.getUserType())  )
		 if("Admin".equalsIgnoreCase(registrationVO.getUserAccessType()) || "subuser".equalsIgnoreCase(registrationVO.getUserAccessType())  )
		 {
			 
			 keywordsList = new ArrayList<SelectOptionVO>();//candidateDetailsService.getTotalKeyWords();
			 keywordsList.add(new SelectOptionVO(1l,"Cadre"));
			 keywordsList.add(new SelectOptionVO(2l,"MP/Incharge"));
			 keywordsList.add(new SelectOptionVO(3l,"MLA/Incharge"));
			 List<Object> assessLocs = null;
			 if("Admin".equalsIgnoreCase(registrationVO.getUserAccessType())){
				 List<Long> stateIds = new ArrayList<Long>();
				 stateIds.add(1l);
				 stateIds.add(36l);
				 assessLocs = regionServiceDataImp.getAllAccessLocByState(stateIds);
			 }else{
				
				 if(registrationVO.getAccessType().equalsIgnoreCase("STATE")){
					 List<Long> stateIds = new ArrayList<Long>();
					 stateIds.add(Long.valueOf(registrationVO.getAccessValue().trim()));
					 assessLocs = regionServiceDataImp.getAllAccessLocByState(stateIds);	 
				 }
				 else if(registrationVO.getAccessType().equalsIgnoreCase("DISTRICT")){
					 assessLocs = regionServiceDataImp.getAllAccessLocByDistrict(Long.valueOf(registrationVO.getAccessValue().trim()));	 
				 }else if(registrationVO.getAccessType().equalsIgnoreCase("MLA")){
					 assessLocs =  regionServiceDataImp.getAllAccessLocByAssConsti(Long.valueOf(registrationVO.getAccessValue().trim()));
				 }else if(registrationVO.getAccessType().equalsIgnoreCase("MP")){
					 assessLocs = regionServiceDataImp.getAllAccessLocByParlConsti(Long.valueOf(registrationVO.getAccessValue().trim()));
				 }
				 
			}
			 if(assessLocs != null && assessLocs.size() == 4){
				 statesList =     (List<SelectOptionVO>)assessLocs.get(0);
				 districtsList =  (List<SelectOptionVO>)assessLocs.get(1);
				 parlConstiList = (List<SelectOptionVO>)assessLocs.get(2);
				 assemConstiList =(List<SelectOptionVO>)assessLocs.get(3);
			 }
			
			 if("Admin".equalsIgnoreCase(registrationVO.getUserAccessType())){
				 statesList1 =     (List<SelectOptionVO>)assessLocs.get(0);
				 districtsList1 =  (List<SelectOptionVO>)assessLocs.get(1);
				 parlConstiList1 = (List<SelectOptionVO>)assessLocs.get(2);
				 assemConstiList1 =(List<SelectOptionVO>)assessLocs.get(3);
			 }else{
				 List<Long> stateIds = new ArrayList<Long>();
				 stateIds.add(1l);
				 stateIds.add(36l);
				 assessLocs = regionServiceDataImp.getAllAccessLocByState(stateIds);
				 statesList1 =     (List<SelectOptionVO>)assessLocs.get(0);
				 districtsList1 =  (List<SelectOptionVO>)assessLocs.get(1);
				 parlConstiList1 = (List<SelectOptionVO>)assessLocs.get(2);
				 assemConstiList1 =(List<SelectOptionVO>)assessLocs.get(3);
			 }
			
		     
		     userDetailsVO = new ArrayList<AddressVO>();
		     userDetailsVO =  staticDataService.getUserLocationScopeDetilsByUserid(registrationVO.getRegistrationID(),registrationVO.getAccessType(),registrationVO.getAccessValue());
			
				
	  return Action.SUCCESS;
		 }
	 else
		 return "error";
	}
	else
		return IConstants.NOT_LOGGED_IN;
	 
 }

public String getDeptsNewsTypes(){
	LOG.debug("In the getDeptsNewsTypes");
	try {
		jObj = new JSONObject(getTask());
		String task = jObj.getString("task");
		if(task.equalsIgnoreCase("getDepartments")){
			basicVOList = staticDataService.getDepartments();
		}else if(task.equalsIgnoreCase("getNewsTypes")){
			basicVOList = staticDataService.getNewsTypes();
		}
	} catch (ParseException e) {
		LOG.error("Exception Raised in getDeptsNewsTypes");
	}
	return Action.SUCCESS;
}

// @Override
 public void setServletContext(ServletContext context) {
 	// TODO Auto-generated method stub
 	this.context=context;
 }
 public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(jObj.getString("task").equalsIgnoreCase("saveDiscription"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("partyId"));
			gallaryVO.setDescription(jObj.getString("fileDesc"));
			//result = partyDetailsService.saveDescription(gallaryVO);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getPartyPhotoGallaryDetail"))
		{
		    fileVO = partyDetailsService.getPartyPhotoGallaryDetail(jObj.getLong("partyId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
		}
		else if(jObj.getString("task").equalsIgnoreCase("createNewGallary") || 
				jObj.getString("task").equalsIgnoreCase("createVideoNewGallary"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("partyId"));
			gallaryVO.setUserId(1l);
			gallaryVO.setGallaryName(jObj.getString("name"));
			gallaryVO.setDescription(jObj.getString("desc"));
			gallaryVO.setVisibility(jObj.getString("visibility"));
			if(jObj.getString("createOrUpdate").trim().equalsIgnoreCase("Create"))
			{
				gallaryVO.setContentType(jObj.getString("contentType"));
				gallaryVO.setCategoryId(jObj.getLong("categoryId"));
			}
			if(jObj.getString("createOrUpdate").trim().equalsIgnoreCase("Update"))
			{
				gallaryVO.setGallaryId(jObj.getLong("gallaryId"));
			}
			result = partyDetailsService.createNewGallaryOrUpdateGallary(gallaryVO,jObj.getString("createOrUpdate").trim());
		}
		else if(jObj.getString("task").trim().equalsIgnoreCase("partyGallariesForUplaod"))
		{
			selectOptionList = partyDetailsService.getPartyGallarySelectList(jObj.getLong("partyId"),jObj.getString("contentType"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("partyVideoGallariesForUplaod"))
		{
			FileVO fileVOObj = new FileVO();
			fileVOObj.setPath(jObj.getString("path"));
			fileVOObj.setContentType("video");
			fileVOObj.setTitle(jObj.getString("videoTitle"));
			fileVOObj.setDescription(jObj.getString("videoDescription"));
			fileVOObj.setKeywords(jObj.getString("keywords"));
			fileVOObj.setSourceId(jObj.getLong("sourceId"));
			fileVOObj.setLanguegeId(jObj.getLong("languageId"));
			fileVOObj.setFileDate(jObj.getString("fileDate"));
			fileVOObj.setGallaryId(jObj.getLong("gallaryId"));
			fileVOObj.setVisibility(jObj.getString("visibility"));
			
			result = candidateDetailsService.uploadAFile(fileVOObj);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getElectionType"))
		{
			fileVO = partyDetailsService.getElectionType();
		}
		else if(jObj.getString("task").equalsIgnoreCase("partyDescriptionUpdate"))
		{
			//gallaryList=partyDetailsService.getPartyProfileInfo(jObj.getLong("partyId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("deleteDiscription"))
		 {
			//result= partyDetailsService.deleteProfileDescById(jObj.getLong("profDescId"));
		 }
		else if(jObj.getString("task").equalsIgnoreCase("partyVideoVisibility"))
		{
			String value = partyDetailsService.getPartyVisibility(jObj.getLong("gallaryId"));
			GallaryVO gallaryvo = new GallaryVO();
			gallaryvo.setIsPrivate(value);
			gallaryVO = gallaryvo;
			
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("partyNewsVisibility"))
		{
			String value = partyDetailsService.getPartyVisibility(jObj.getLong("gallaryId"));
			GallaryVO gallaryvo = new GallaryVO();
			gallaryvo.setIsPrivate(value);
			gallaryVO = gallaryvo;
			
		}
		else if(jObj.getString("task").equalsIgnoreCase("partyPhotoVisibility"))
		{
			String value = partyDetailsService.getPartyVisibility(jObj.getLong("gallaryId"));
			GallaryVO gallaryvo = new GallaryVO();
			gallaryvo.setIsPrivate(value);
			gallaryVO = gallaryvo;
			
		}
		
		
		else if(jObj.getString("task").equalsIgnoreCase("updateProfileDiscription"))
		{
			List<Long> orderNo = new ArrayList<Long>();
			List<String> description = new ArrayList<String>();
			List<Long> condiProfDescId = new ArrayList<Long>();
			gallaryList =new ArrayList<GallaryVO>();
			JSONArray jOrderNo = jObj.getJSONArray("orderNoArr");
			JSONArray jDescription = jObj.getJSONArray("descriptionArr");
			JSONArray jprofDescId = jObj.getJSONArray("profDescIdArr");
			Long partyId = jObj.getLong("partyId");
			try{
			    
			    for (int i = 0; i < jOrderNo.length(); i++) {
				    orderNo.add(new Long(jOrderNo.get(i).toString()));
				    description.add(jDescription.get(i).toString());
				    condiProfDescId.add(new Long(jprofDescId.get(i).toString()));
			      }
			    for (int i = 0; i < jOrderNo.length(); i++) {
				   GallaryVO gallary = new GallaryVO();
				   gallary.setOrderNo(orderNo.get(i));
				   gallary.setDescription(description.get(i));
				   gallary.setPartyProfileDescriptionId(condiProfDescId.get(i));
				   gallaryList.add(gallary);
			      }
			      //result = partyDetailsService.updateProfileDescription(gallaryList,partyId);
			 }
			
			
			catch(Exception e)
			       {
				e.printStackTrace();
			    }
		}
		else if(jObj.getString("task").equalsIgnoreCase("getParties"))
		{
			//selectOptionList = partyDetailsService.getAllPartysNames();
		}
		
		return Action.SUCCESS;
} 
 public String getPartyGallaryDescription(){
	 
	 try {
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("UpdatePartyGallary")){
			Long galleryId = new Long(jObj.getString("gallaryId"));
			Long partyId = new Long(jObj.getString("partyId"));
			gallaryVO = partyDetailsService.getPartyGalleryDetails(galleryId,partyId); 
		}
		
		
		
		
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	 return Action.SUCCESS;
 }
  public String getElectionIdsAndYears()
  {
	  Long stateId = null;
	  String electionType = null;
	  try {
			jObj = new JSONObject(getTask());
			
				electionType = jObj.getString("electionType");
			 if(jObj.getString("stateId") != null && jObj.getString("stateId").trim().length()>0)
				stateId = jObj.getLong("stateId");
			
			//selectOptionList = staticDataService.electionYearsForstateAndElectionType(stateId,electionType);
	  } 
	  catch (ParseException e) {
			e.printStackTrace();
		}
	  return Action.SUCCESS;
  }
  
  public String uploadFiles()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
		String fileName = null;
		String filePath = null;
		FileVO fileVO = new FileVO();
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
		if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE))
			filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.UPLOADED_FILES + pathSeperator;
		else
			filePath = context.getRealPath("/")+IConstants.UPLOADED_FILES + pathSeperator;
		
		if(profileType != null && profileId != null && profileGalleryType != null)
		{
			filePath = filePath + profileType + pathSeperator + profileId + pathSeperator + profileGalleryType + pathSeperator;
		}
		
		try 
		{
			String fileType = null;
			Long systime = System.currentTimeMillis();
			Random random = new Random();
			if(userImageContentType.equalsIgnoreCase("text/plain"))
			{
				fileType = userImageContentType.substring(0,userImageContentType.indexOf("/"));
				fileName = systime.toString()+random.nextInt(10000000)+"."+fileType;
			}
			else
			{
				fileType = userImageContentType.substring(userImageContentType.indexOf("/")+1,userImageContentType.length());
				fileName = systime.toString()+random.nextInt(IWebConstants.FILE_RANDOM_NO)+"."+fileType;
			}
			if(fileType.equals("pjpeg"))
				fileType = "jpeg";
			fileVO.setName(fileName);
			fileVO.setDescription(getFileDescription());
			fileVO.setContentType(fileType);
			fileVO.setLanguegeId(getLanguage());
			fileVO.setIds(getPartyId());//setting party id
			fileVO.setElectionId(getElectionId());
			fileVO.setStateId(getLocationValue());
			
			if(profileType != null && profileId != null && profileGalleryType != null)
				fileVO.setPath(IWebConstants.UPLOADED_FILES+"/"+profileType+"/"+profileId+"/"+profileGalleryType+"/"+fileName);
			else
				fileVO.setPath(IWebConstants.UPLOADED_FILES+"/"+fileName);
			
			/* Here We are saving the to uploaded_files folder */
			File fileToCreate = new File(filePath, fileName);
			FileUtils.copyFile(userImage, fileToCreate);
			
			//result = partyDetailsService.uploadPartyManifesto(fileVO);
			
			if(result.getResultCode() == ResultCodeMapper.SUCCESS)
				inputStream = new StringBufferInputStream(SUCCESS);
			else
				inputStream = new StringBufferInputStream("fail");
						
		}
		catch (Exception e) {
			inputStream = new StringBufferInputStream("fail");
	}
		return Action.SUCCESS;
	}
  
  public String getElectionTypes(){
	  
	  try {
		jObj = new JSONObject(getTask());
		Long stateId = new Long(jObj.getString("stateId"));
		Long partyId = new Long(jObj.getString("partyId"));
		//electionTypesList = partyDetailsService.getElectionTypesBasedOnStateIdAndPartyId(partyId , stateId);
		
		
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  
	  return Action.SUCCESS;
  }
  
  public String getElectionYearsBasedOnElecTypeIdPartyIdAndStateId(){
	  
	  try {
		jObj = new JSONObject(getTask());
		Long electionTypeId = new Long(jObj.getString("electionTypeId"));
		Long stateId = new Long(jObj.getString("stateId"));
		Long partyId = new Long(jObj.getString("partyId"));
		//electionYearsList = partyDetailsService.getElectionYearsBasedOnElectionTypeIdAndPartyId(electionTypeId, partyId, stateId);
	} 
	  catch (ParseException e) {
		e.printStackTrace();
	}
	  
	  return Action.SUCCESS;
  }
  
 /* public String getPartyRelatedManifestoFileBasedOnElectionYear(){
	  try {
		jObj = new JSONObject(getTask());
		Long electionId = new Long(jObj.getString("electionId"));
		Long partyId = new Long(jObj.getString("partyId"));
		Long stateId = new Long(jObj.getString("stateId"));
		fileVO = partyDetailsService.getPartyRelatedManifestoBasedOnYear(electionId,partyId,stateId);
			
		
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  return Action.SUCCESS;
  }*/
  
  
  public String getAllCategoriesList(){
	  session = request.getSession();
	  RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	  categoriesList = new ArrayList<SelectOptionVO>();
	  try {
		  if(regVO != null){			  
			  categoriesList = partyDetailsService.getPartyGallarySelectList(null, null);
		  }

		} catch (Exception e) {
			categoriesList = null;
		}	  
	  return Action.SUCCESS;
	  }

   public String getKeyWordsBySearchCriteria(){
	      String searchString = request.getParameter("q");
	      if(searchString != null && searchString.trim().length() > 0){
	    	  keywordsList = candidateDetailsService.getKeyWordsBySearchCriteria(searchString);
	      }else{
	    	  keywordsList = new ArrayList<SelectOptionVO>(); 
	      }
	      return  Action.SUCCESS;
   }
}

