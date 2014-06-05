package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CmsAdminAction extends ActionSupport implements ServletRequestAware{
	
	
	private static final Logger LOG = Logger.getLogger(CmsAdminAction.class);
	private HttpServletRequest request;
	private String task;
	JSONObject jObj;
	private HttpSession session;
	
	private VoterTagVO voterVO;
	private List<VoterTagVO> voterDetails;
	private ResultStatus resultStatus;
	
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<VoterTagVO> getVoterDetails() {
		return voterDetails;
	}

	public void setVoterDetails(List<VoterTagVO> voterDetails) {
		this.voterDetails = voterDetails;
	}


	@Autowired
	private IVoterReportService voterReportService;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	

	public VoterTagVO getVoterVO() {
		return voterVO;
	}

	public void setVoterVO(VoterTagVO voterVO) {
		this.voterVO = voterVO;
	}

	public String execute()
	{
		
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
		Long userId = regVo.getRegistrationID();
		if(userId == null)
			return Action.ERROR;
		
		return Action.SUCCESS;
		
	}
	
	public String getCmsAdminReport()
	{
		try{
			
			voterVO = voterReportService.getCmsAdminReportData();
		}
		catch (Exception e) {
		LOG.error("Exception Occured in getCmsAdminReport() method in CmsAdminAction - ",e);	
		}
		return Action.SUCCESS;
	}

	
	public String getCmsAdminReportDetails()
	{
		try{
			jObj = new JSONObject(getTask());
			voterDetails = voterReportService.getCmsAdminReportDrtails(jObj.getString("isType"),jObj.getString("typeOfData"));
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getCmsAdminReportDetails() method in CmsAdminAction - ",e);	
			}
			return Action.SUCCESS;
	}
	
	public String saveTaggedVoterDetails()
	{
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			List<VoterTagVO> inputList = new ArrayList<VoterTagVO>();
			
			JSONArray jArray = jObj.getJSONArray("dataArr");
			if(jArray.length() > 0)
			for(int i = 0; i< jArray.length(); i++)
			{
				VoterTagVO voterTagVO = new VoterTagVO();
				JSONObject jsonObj = jArray.getJSONObject(i);
				Long constituencyId = jsonObj.getLong("constituencyId");
				String name = jsonObj.getString("name");
				String voterId = jsonObj.getString("voterId");
				String gender = jsonObj.getString("gender");
				String age = jsonObj.getString("age");
				String mobileNo = jsonObj.getString("mobileNo");
				voterTagVO.setConstituencyId(constituencyId);
				voterTagVO.setName(name);
				voterTagVO.setVoterIdCardNo(voterId);
				voterTagVO.setAge(age);
				voterTagVO.setGender(gender);
				voterTagVO.setMobileNo(mobileNo);
				inputList.add(voterTagVO);
			}
			
			
			resultStatus = voterReportService.saveTaggedVoterDetails(jObj.getString("type"),inputList,userId);
		}
		catch (Exception e) {
			LOG.error("Exception Occured in saveTaggedVoterDetails() method in CmsAdminAction - ",e);	
		}
		return Action.SUCCESS;
	}
}
