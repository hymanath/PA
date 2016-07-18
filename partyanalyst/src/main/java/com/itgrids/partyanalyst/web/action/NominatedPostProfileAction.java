package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.INominatedPostProfileService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NominatedPostProfileAction extends ActionSupport implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(NominatedPostProfileAction.class);
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
	private INominatedPostProfileService        nominatedPostProfileService;
	private String status;
	private List<IdNameVO> 						idNameVOList;
	private List<NominatedPostVO> 				nominatePostList;
	private NomintedPostMemberVO 				nomintedPostMemberVO;
	private NominatedPostVO                     nominatedPostVO;
	private ResultStatus 						resultStatus;
	private InputStream 						inputStream;
	
	
	public List<NominatedPostVO> getNominatePostList() {
		return nominatePostList;	}
	public void setNominatePostList(List<NominatedPostVO> nominatePostList) {
		this.nominatePostList = nominatePostList;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public NominatedPostVO getNominatedPostVO() {
		return nominatedPostVO;
	}
	public void setNominatedPostVO(NominatedPostVO nominatedPostVO) {
		this.nominatedPostVO = nominatedPostVO;
	}
	public NomintedPostMemberVO getNomintedPostMemberVO() {
		return nomintedPostMemberVO;
	}
	public void setNomintedPostMemberVO(NomintedPostMemberVO nomintedPostMemberVO) {
		this.nomintedPostMemberVO = nomintedPostMemberVO;
	}
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public INominatedPostProfileService getNominatedPostProfileService() {
		return nominatedPostProfileService;
	}
	public void setNominatedPostProfileService(
			INominatedPostProfileService nominatedPostProfileService) {
		this.nominatedPostProfileService = nominatedPostProfileService;
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
	public static Logger getLog() {
		return LOG;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public String nominatedPosts()
	{
		return Action.SUCCESS;
	}
	
	public String getBoardLevels(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getBoardLevels();
			
		}catch (Exception e) {
			LOG.error("Entered into getBoardLevels Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getDepartments(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartments();
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartments Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getDepartmentBoard(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartmentBoard(jObj.getLong("depmtId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentBoard Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getDepartmentBoardPositions(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartmentBoardPositions(jObj.getLong("depmtId"),jObj.getLong("boardId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentBoard Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getNominatedPostMemberDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			
			nomintedPostMemberVO = nominatedPostProfileService.getNominatedPostMemberDetails(jObj.getLong("levelId"),jObj.getLong("levelValue"),jObj.getLong("departmentId"),
															jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getString("type"));
			
		}catch (Exception e) {
			LOG.error("Entered into getNominatedPostMemberDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String boardWiseNominatedPosts(){
		return Action.SUCCESS;
	}
	
	public String updateApplicationStatusDetails(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			status = nominatedPostProfileService.updateApplicationStatusDetails(userId,jObj.getLong("nominatedPostId"),jObj.getLong("nominatedPostCandidateId"),jObj.getLong("statusId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getNominatedPostMemberDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String savingNominatedPostProfileApplication(){
		try {
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			resultStatus = nominatedPostProfileService.savingNominatedPostProfileApplication(nominatedPostVO,user.getRegistrationID());
			
			if(resultStatus!=null){
				if(resultStatus.getResultCode() == 0){
					inputStream = new StringBufferInputStream("SUCCESS");
				}else if(resultStatus.getResultCode() == 1){
					inputStream = new StringBufferInputStream("FAIL");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at savingNominatedPostProfileApplication", e);
		}
		
		return Action.SUCCESS;
	}
	public String savechangeAddressForNominatedPost()
	{
		try
		{
			jObj = new JSONObject(getTask());
		    NominatedPostVO Vo = new NominatedPostVO();
		    Vo.setId(jObj.getLong("tdpCadreId"));
		    Vo.setHno(jObj.getString("houseNo"));
		    Vo.setMobileNo(jObj.getString("mobileNo"));
		    Vo.setAddress1(jObj.getString("addressVal"));
		    Vo.setAddress2(jObj.getString("addressValue"));
		    Vo.setPincode(jObj.getString("pincode"));
		    Vo.setStateId(jObj.getLong("stateId"));
		    Vo.setDistrictId(jObj.getLong("districtId"));
		    Vo.setConstituencyId(jObj.getLong("constituencyId"));
		    Vo.setPanchayatId(jObj.getLong("panchayatId"));
		    Vo.setMandalId(jObj.getLong("mandalId"));
		    
			status = nominatedPostProfileService.savechangeAddressForNominatedPost(Vo);
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPopulateApplicantDetailsForMember()
	{
		try
		{
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("globalCadreId");
			nominatePostList = nominatedPostProfileService.getApplicantDetailsForMember(tdpCadreId);
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictsForStateForNominated()
	{
		try
		{
		jObj = new JSONObject(getTask());
		Long stateId = jObj.getLong("stateId");
		idNameVOList = nominatedPostProfileService.getDistrictsForState(stateId);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getVillagesForMandalId()
	{
		try
		{
		jObj = new JSONObject(getTask());
		Long mandalId = jObj.getLong("mandalId");
		idNameVOList = nominatedPostProfileService.getVillagesForMandalId(mandalId);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getCandidateAppliedPostsByCadre()
	{
		try
		{
			jObj = new JSONObject(getTask());
			nomintedPostMemberVO = nominatedPostProfileService.getCandidateAppliedPostsByCadre(jObj.getLong("globalCadreId"));
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getNominatedPostApplicationReviewDetails()
	{
	 return Action.SUCCESS;
	}
}
