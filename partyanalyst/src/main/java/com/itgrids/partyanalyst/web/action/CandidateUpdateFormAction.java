package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SocialNetworkVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.social.service.ISocialService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class CandidateUpdateFormAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware, ServletContextAware , Preparable,ModelDriven<CandidateDetailsVO>{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CandidateUpdateFormAction.class);
	JSONObject jObj = null;
	private String task = null;
	private List<SocialNetworkVO> casteCategory;
	private List<SocialNetworkVO> casteCategoryGroupNames;
	private List<SocialNetworkVO> casteNames;
	private List<SocialNetworkVO> mandalNames;
	//private List<CandidateDetailsVO> candidateDetailsVO;
	private String candidateName;
	private String image;
	private Long candidateId;
	private ResultStatus result;
	private ResultStatus result1;
	private ResultStatus result2;
	private ResultStatus result3;
	
	private CandidateDetailsVO candidateDetailsVO;
	private HttpServletRequest request;
	private HttpSession session;
	private String candidateName1;
	private List<AddressVO> address;
	
	/*private String emailId;
	private String websiteAddress;
	private String facebookUrl;
	private String twitterUrl;
	*/
	
	
	//private List<CandidateDetailsVO> casteCategory1;
	//private List<CandidateDetailsVO>  casteGroup;
	//private List<CandidateDetailsVO>  casteName;
	//private List<CandidateDetailsVO> addresses;
	//private List<CandidateDetailsVO> phoneType;
	

	

	public List<AddressVO> getAddress() {
		return address;
	}

	public void setAddress(List<AddressVO> address) {
		this.address = address;
	}


/*
	private Long casteCategory1;
	private Long casteGroupId;
	private Long casteId;
	private String newCaste;
	private Long phoneType;
	private Long addressTypeId;
	private Long phoneNumber;
	private Long phoneCategory;
	
	private Long countryId;
	private String countryName;
	private Long stateId;
	private String stateName;
	private Long districtId;
	private String districtName;
	private Long mandalId;
	private String mandalName;
	private String city;
	private String address1;
	private String address2;
	private Long pincode;
	
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> problemSourcesList;
*/
	private ISocialService socialService;
	List<SelectOptionVO> casteCategorysList;
	List<SelectOptionVO> casteGroupsList;
	List<SelectOptionVO> statesList;
	List<SelectOptionVO> districtsList;
	List<SelectOptionVO> tehsilsList;
	
	private File userMoreImages;
	private String userMoreImagesContentType;
	private String userMoreImagesFileName;
	private InputStream inputStream;
	
	private String fileTitle;
	private String fileDescription;
    private String visibility;
	private Long gallaryId;
	private String keywords;
	private Long locationScope;
	private Long locationValue;
	private String fileDate;
	private Long source;
	private Long language;
	private Long category;
	private Long newsimportance;
	
	private List<SelectOptionVO> resultNames;	
		
	private List<SelectOptionVO> stateList;
	//private List<SelectOptionVO> districtList;
	//private List<SelectOptionVO> tehsilList;
	private List<SelectOptionVO> casteList;
	private List<SelectOptionVO> addressTypesList;
	private List<SelectOptionVO> phoneTypesList;
	private List<SelectOptionVO> phoneCategorysList;
	
	
	
	
	public List<SelectOptionVO> getCasteList() {
		return casteList;
	}

	public void setCasteList(List<SelectOptionVO> casteList) {
		this.casteList = casteList;
	}

	public List<SelectOptionVO> getPhoneTypesList() {
		return phoneTypesList;
	}

	public void setPhoneTypesList(List<SelectOptionVO> phoneTypesList) {
		this.phoneTypesList = phoneTypesList;
	}

	public List<SelectOptionVO> getPhoneCategorysList() {
		return phoneCategorysList;
	}

	public void setPhoneCategorysList(List<SelectOptionVO> phoneCategorysList) {
		this.phoneCategorysList = phoneCategorysList;
	}

	public List<SelectOptionVO> getAddressTypesList() {
		return addressTypesList;
	}

	public void setAddressTypesList(List<SelectOptionVO> addressTypesList) {
		this.addressTypesList = addressTypesList;
	}

	public List<SelectOptionVO> getCasteCategorysList() {
		return casteCategorysList;
	}

	public void setCasteCategorysList(List<SelectOptionVO> casteCategorysList) {
		this.casteCategorysList = casteCategorysList;
	}

	public List<SelectOptionVO> getCasteGroupsList() {
		return casteGroupsList;
	}

	public void setCasteGroupsList(List<SelectOptionVO> casteGroupsList) {
		this.casteGroupsList = casteGroupsList;
	}

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public List<SelectOptionVO> getTehsilsList() {
		return tehsilsList;
	}

	public void setTehsilsList(List<SelectOptionVO> tehsilsList) {
		this.tehsilsList = tehsilsList;
	}

	

/*	public List<SelectOptionVO> getTehsilList() {
		return tehsilList;
	}

	public void setTehsilList(List<SelectOptionVO> tehsilList) {
		this.tehsilList = tehsilList;
	}
*/
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

	/*public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}*/

	public List<SelectOptionVO> getResultNames() {
		return resultNames;
	}

	public CandidateDetailsVO getCandidateDetailsVO() {
		return candidateDetailsVO;
	}

	public void setCandidateDetailsVO(CandidateDetailsVO candidateDetailsVO) {
		this.candidateDetailsVO = candidateDetailsVO;
	}

	public void setResultNames(List<SelectOptionVO> resultNames) {
		this.resultNames = resultNames;
	}

	public Long getNewsimportance() {
		return newsimportance;
	}

	public void setNewsimportance(Long newsimportance) {
		this.newsimportance = newsimportance;
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

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
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

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getUserMoreImages() {
		return userMoreImages;
	}

	public void setUserMoreImages(File userMoreImages) {
		this.userMoreImages = userMoreImages;
	}

	public String getUserMoreImagesContentType() {
		return userMoreImagesContentType;
	}

	public void setUserMoreImagesContentType(String userMoreImagesContentType) {
		this.userMoreImagesContentType = userMoreImagesContentType;
	}

	public String getUserMoreImagesFileName() {
		return userMoreImagesFileName;
	}

	public void setUserMoreImagesFileName(String userMoreImagesFileName) {
		this.userMoreImagesFileName = userMoreImagesFileName;
	}

	public ResultStatus getResult3() {
		return result3;
	}

	public void setResult3(ResultStatus result3) {
		this.result3 = result3;
	}



	public ResultStatus getResult1() {
		return result1;
	}

	public void setResult1(ResultStatus result1) {
		this.result1 = result1;
	}

	public ResultStatus getResult2() {
		return result2;
	}

	public void setResult2(ResultStatus result2) {
		this.result2 = result2;
	}

	

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}
	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}




	public List<SocialNetworkVO> getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(List<SocialNetworkVO> casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getCandidateName1() {
		return candidateName1;
	}

	public void setCandidateName1(String candidateName1) {
		this.candidateName1 = candidateName1;
	}

	

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<SocialNetworkVO> getMandalNames() {
		return mandalNames;
	}

	public void setMandalNames(List<SocialNetworkVO> mandalNames) {
		this.mandalNames = mandalNames;
	}
	
	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public List<SocialNetworkVO> getCasteNames() {
		return casteNames;
	}

	public void setCasteNames(List<SocialNetworkVO> casteNames) {
		this.casteNames = casteNames;
	}

	public ISocialService getSocialService() {
		return socialService;
	}

	public void setSocialService(ISocialService socialService) {
		this.socialService = socialService;
	}


	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
		
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}


	
	
	public List<SocialNetworkVO> getCasteCategoryGroupNames() {
		return casteCategoryGroupNames;
	}

	public void setCasteCategoryGroupNames(
			List<SocialNetworkVO> casteCategoryGroupNames) {
		this.casteCategoryGroupNames = casteCategoryGroupNames;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	

	
public String execute() {
	//casteList = socialService.getAllCasteDetails();
	//casteCategorysList = socialService.getAllCasteCategoryDetails();
	//casteGroupsList = socialService.getAllCasteCategoryGroupDetails();
	//statesList = socialService.getAllStateDetails();
	//districtsList = socialService.getAllDistrictDetails();
	//tehsilsList = socialService.getAllTehsilDetails();


	
	
		return Action.SUCCESS;
	}

/*public String usingList(){
	casteList = socialService.getAllCasteInfoDetails();
	casteCategorysList = socialService.getAllCasteCategoryDetails();
	statesList = socialService.getAllStateDetails();
	//tehsilsList = socialService.getAllTehsilInfoDetails();
	addressTypesList=socialService.getAllAddressTypes();
	phoneTypesList=socialService.getAllPhoneTypes();
	phoneCategorysList=socialService.getAllPhoneCategorys();
	//districtsList =  socialService.getAllDistrictInfoDetails();

return SUCCESS;
}*/


public String uploadImage(){
	
try{

			File fileToCreate = new File("C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PartyAnalyst/photos", userMoreImagesFileName);
			FileUtils.copyFile(userMoreImages.getAbsoluteFile(), fileToCreate);

}
catch (Exception e) 
{
	//inputStream = new StringBufferInputStream("fail");
	log.error("Exception Occured in uploadFile() method, Exception is - "+e); 
}
			return Action.SUCCESS;
}

public String candidateUpdate(){
	

	
//candidateDetailsVO =  getModel();
	
return Action.SUCCESS;
}


public String getCandidateDetails(){
	
	
	result=socialService.insertAddressDetails(candidateDetailsVO);
	
	result2=socialService.insertCasteDetails(candidateDetailsVO);
	
	result3=socialService.insertOtherDetails(candidateDetailsVO);


	return Action.SUCCESS;
	
	
	
}
public void prepare() throws Exception {
	
	//boolean isSaved=false;
	String candidateId = request.getParameter("candidateId");
			

	List<SelectOptionVO> casteGroupList=null;
	List<SelectOptionVO> districtList=new ArrayList<SelectOptionVO>();
	List<SelectOptionVO> tehsilList=new ArrayList<SelectOptionVO>();
	List<SelectOptionVO> castesList=null;
	AddressVO addressVO = new AddressVO();
	List<SocialNetworkVO> phoneList=new ArrayList<SocialNetworkVO>();
	List<AddressVO> addressList = new ArrayList<AddressVO>();
	SocialNetworkVO socialNetworkVO=new SocialNetworkVO();
	
	if(candidateId!=null){
		
		candidateDetailsVO=new CandidateDetailsVO();
		
		candidateDetailsVO=socialService.getcandidateFullInformation(new Long(candidateId));
		statesList = socialService.getAllStateDetails();
		statesList.add(0, new SelectOptionVO(0l,"select state"));
		//fresh application
		//if(candidateDetailsVO == null)
		
		//casteGroupsList = socialService.getAllCasteCategoryGroupInfoDetails();
		if(candidateDetailsVO.getCasteGroupList()==null){
			casteGroupList=new ArrayList<SelectOptionVO>();
			//casteGroupsList = new ArrayList<SelectOptionVO>();
			casteGroupList.add(0,new SelectOptionVO(0l,"Select Group"));
			candidateDetailsVO.setCasteGroupList(casteGroupList);
		}
		
		
		if(candidateDetailsVO.getCasteGroupNameList()==null){
			castesList = new ArrayList<SelectOptionVO>();
			casteList = socialService.getAllCasteInfoDetails();
			casteList.add(0, new SelectOptionVO(0l,"others"));
			candidateDetailsVO.setCasteGroupNameList(casteList);
			candidateDetailsVO.setCasteId(0l);
		}
		
		if(candidateDetailsVO.getAddressList()==null){
		districtsList=new ArrayList<SelectOptionVO>();
		districtList.add(0, new SelectOptionVO(0l,"select district"));
		
		tehsilsList=new ArrayList<SelectOptionVO>();
		tehsilList.add(0,new SelectOptionVO(0l,"select mandal"));
		
		casteCategorysList = socialService.getAllCasteCategoryDetails();
		
		//tehsilsList = socialService.getAllTehsilInfoDetails();
		addressTypesList=socialService.getAllAddressTypes();
		phoneTypesList=socialService.getAllPhoneTypes();
		phoneCategorysList=socialService.getAllPhoneCategorys();
		//districtsList =  socialService.getAllDistrictInfoDetails();
		addressVO.setDistrictList(districtsList);
		addressVO.setTehsilList(tehsilsList);
		addressVO.setAddressTypeList(addressTypesList);
		addressVO.setAddressTypeId(0l);
		socialNetworkVO.setPhoneType(0l);
		socialNetworkVO.setPhoneCategory(0l);
		socialNetworkVO.setPhoneTypeList(phoneTypesList);
		socialNetworkVO.setPhoneCategoryList(phoneCategorysList);
		
		phoneList.add(socialNetworkVO);
		addressVO.setPhoneList(phoneList);
		addressList.add(addressVO);
		candidateDetailsVO.setAddressList(addressList);
		
		}
	/*	if(addressList==null){
		addressVO.setDistrictList(districtsList);
		addressVO.setTehsilList(tehsilsList);
		addressVO.setAddressTypeList(addressTypesList);
		addressVO.setAddressTypeId(0l);
		socialNetworkVO.setPhoneType(0l);
		socialNetworkVO.setPhoneCategory(0l);
		socialNetworkVO.setPhoneTypeList(phoneTypesList);
		socialNetworkVO.setPhoneCategoryList(phoneCategorysList);
		
		phoneList.add(socialNetworkVO);
		addressVO.setPhoneList(phoneList);
		addressList.add(addressVO);
		candidateDetailsVO.setAddressList(addressList);
		}*/
	
	}else{
		candidateDetailsVO=new CandidateDetailsVO();
	}
		
}    
	
public CandidateDetailsVO getModel() {
		return candidateDetailsVO;
}

public String callAjaxHandler(){
	
	String param = null;
	param = getTask();
	try {
		jObj = new JSONObject(param);
		System.out.println(jObj);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	if(jObj.getString("task").equalsIgnoreCase("getCasteCategories")){
		
		casteCategory = socialService.getCasteCategoriesNames();
		
		//CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		
		//candidateDetailsVO.setCandidateName("");
		
		
	}
		return Action.SUCCESS;
}

public String callAjaxHandlerCasteGroupNames(){
	
	String param = null;
	param = getTask();
	
	try {
		jObj = new JSONObject(param);
		System.out.println(jObj);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	if(jObj.getString("task").equalsIgnoreCase("getCasteCategoryGroupNames")){
		System.out.println("with in the action");
		Long casteCategoryId=new Long(jObj.getString("categoryId"));
		casteCategoryGroupNames = socialService.getCasteCategoryGroupNames(casteCategoryId);
		System.out.println("casteCategoryGroupNames value is:"+casteCategoryGroupNames);
	}
	return Action.SUCCESS;
}

@SuppressWarnings("unchecked")
public String callAjaxHandlerCasteNames(){
	
	String param=null;
	param=getTask();
	try{
		jObj = new JSONObject(param);
		System.out.println(jObj);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	if(jObj.getString("task").equalsIgnoreCase("getCasteNames")){
		Long casteGroupId=new Long(jObj.getString("casteGroupId"));
		
		casteNames=socialService.getCasteName(casteGroupId);
	}
	return Action.SUCCESS;
}
public String callAjaxHandlerMandalNames(){
	
	String param=null;
	param=getTask();
	try{
		jObj = new JSONObject(param);
		System.out.println(jObj);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	if(jObj.getString("task").equalsIgnoreCase("getMandalNames")){
		//String name, Long districtId
		Long districtId=new Long(jObj.getString("districtId"));
		
		mandalNames=socialService.findByTehsilNameAndDistrict(districtId);
	}
	return Action.SUCCESS;
		
}

public String callAjaxHandlerCasteNamesByAutoPopulate(){
	
	String param=null;
	param=getTask();
	try{
		jObj = new JSONObject(param);
		System.out.println(jObj);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	if(jObj.getString("task").equalsIgnoreCase("getCasteNamesByUsingAutoPopuate")){
		
		Long stateId=new Long(jObj.getString("stateId"));
		String letters=jObj.getString("letters");
	resultNames=socialService.getCasteNamesByAutoPopulate(new Long(stateId),letters);
	
	}
	return Action.SUCCESS;
}

}
