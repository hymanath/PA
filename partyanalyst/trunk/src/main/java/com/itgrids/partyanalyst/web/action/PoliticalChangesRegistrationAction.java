package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IPoliticalChangesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class PoliticalChangesRegistrationAction extends ActionSupport implements
		ServletRequestAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext context;
    private HttpSession session;
	private HttpServletRequest request;
	
	private String title,description,occuredDate,reportedDate,party,effectRange,politicalChangeId,saveType;
	private List<SelectOptionVO> informationSourcesList,staticPartiesList,effectedRangeList;
	private String type,externalPerson,name,mobile,telephoneNo,email,address,sourceInformation,range,rangeId,Save;
	private int resultStatus = 3;
	private IPoliticalChangesService politicalChangesService;
	private IInfluencingPeopleService influencingPeopleService;
	private IStaticDataService staticDataService;
	
	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public int getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getPoliticalChangeId() {
		return politicalChangeId;
	}

	public void setPoliticalChangeId(String politicalChangeId) {
		this.politicalChangeId = politicalChangeId;
	}

	public String getSave() {
		return Save;
	}

	public void setSave(String save) {
		Save = save;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getRangeId() {
		return rangeId;
	}

	public void setRangeId(String rangeId) {
		this.rangeId = rangeId;
	}

	public String getSourceInformation() {
		return sourceInformation;
	}

	public void setSourceInformation(String sourceInformation) {
		this.sourceInformation = sourceInformation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Address",shortCircuit=true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Name",shortCircuit=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile number is required",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD,message = "mobile number should be 10 digits", shortCircuit = true,  minLength = "10",  maxLength = "10")
	@RegexFieldValidator( type = ValidatorType.FIELD, expression = "^([9]{1})([02346789]{1})([0-9]{8})$", message="mobile number should contain digits",shortCircuit = true)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IPoliticalChangesService getPoliticalChangesService() {
		return politicalChangesService;
	}

	public void setPoliticalChangesService(
			IPoliticalChangesService politicalChangesService) {
		this.politicalChangesService = politicalChangesService;
	}

	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExternalPerson() {
		return externalPerson;
	}

	public void setExternalPerson(String externalPerson) {
		this.externalPerson = externalPerson;
	}
   
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select Occured Date",shortCircuit=true)
	public String getOccuredDate() {
		return occuredDate;
	}

	public void setOccuredDate(String occuredDate) {
		this.occuredDate = occuredDate;
	}
/*
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select Reported Date",shortCircuit=true)
	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
*/
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Party is required",shortCircuit=true)
	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Effected Range is required",shortCircuit=true)
	public String getEffectRange() {
		return effectRange;
	}

	public void setEffectRange(String effectRange) {
		this.effectRange = effectRange;
	}

	public List<SelectOptionVO> getStaticPartiesList() {
		return staticPartiesList;
	}
	public void setStaticPartiesList(List<SelectOptionVO> staticPartiesList) {
		this.staticPartiesList = staticPartiesList;
	}
	
	public List<SelectOptionVO> getEffectedRangeList() {
		return effectedRangeList;
	}

	public void setEffectedRangeList(List<SelectOptionVO> effectedRangeList) {
		this.effectedRangeList = effectedRangeList;
	}
	

	public List<SelectOptionVO> getInformationSourcesList() {
		return informationSourcesList;
	}

	public void setInformationSourcesList(
			List<SelectOptionVO> informationSourcesList) {
		this.informationSourcesList = informationSourcesList;
	}

	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Title",shortCircuit=true)
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	/*
	public String getDescription() {
		return description;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Description",shortCircuit=true)
	public void setDescription(String description) {
		this.description = description;
	}
*/
		
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public String execute() throws Exception
	{
		externalPerson = IConstants.EXTERNAL_PERSON;
		
		effectedRangeList = influencingPeopleService.getInfluenceRange();
		informationSourcesList = politicalChangesService.getAllPoliticalInformationSources();
		staticPartiesList = staticDataService.getStaticParties();
		session = request.getSession();
		session.setAttribute("effectedRangeList", effectedRangeList);
		session.setAttribute("informationSourcesList",informationSourcesList);
		session.setAttribute("staticPartiesList",staticPartiesList);
		
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		
		PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();
		politicalChangesVO.setRange(range); 
		politicalChangesVO.setRangeId(Long.parseLong(rangeId));
		politicalChangesVO.setSaveType(saveType);
		politicalChangesVO.setPoliticalChangeId(Long.parseLong(politicalChangeId));  
		politicalChangesVO.setTitle(title);
		politicalChangesVO.setDescription(description);
		politicalChangesVO.setDate(occuredDate);
		politicalChangesVO.setReportedDate(reportedDate);
		politicalChangesVO.setSourceOfInformation(sourceInformation);
		politicalChangesVO.setName(name);
		politicalChangesVO.setMobile(mobile);
		politicalChangesVO.setTelephoneNo(telephoneNo);
		politicalChangesVO.setEmail(email);
		politicalChangesVO.setAddress(address);
		politicalChangesVO.setPartyId(Long.parseLong(party));
		politicalChangesVO.setSelectedPersonId(Long.parseLong(sourceInformation));
		politicalChangesVO.setUserId(userID);
		ResultStatus result = politicalChangesService.savePoliticalChangeDataReceivedFromUser(politicalChangesVO);
		resultStatus = result.getResultCode();
		/*
		session.removeAttribute("effectedRangeList");
		session.removeAttribute("informationSourcesList");
		session.removeAttribute("staticPartiesList");
		*/
		
		return Action.SUCCESS;	
	}
}

