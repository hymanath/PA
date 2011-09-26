package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SmsTrackVO;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ManageUserMessageCreditsAction extends ActionSupport implements
		ServletRequestAware {

	private HttpServletRequest request;
	HttpSession session;
	private IRegistrationService registrationService;
	private ISmsService smsCountrySmsService;
	private EntitlementVO allRegisteredUsersData;
	JSONObject jObj = null;
	private String task;
	SmsTrackVO smsTrackVO;

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public EntitlementVO getAllRegisteredUsersData() {
		return allRegisteredUsersData;
	}

	public void setAllRegisteredUsersData(EntitlementVO allRegisteredUsersData) {
		this.allRegisteredUsersData = allRegisteredUsersData;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public SmsTrackVO getSmsTrackVO() {
		return smsTrackVO;
	}

	public void setSmsTrackVO(SmsTrackVO smsTrackVO) {
		this.smsTrackVO = smsTrackVO;
	}

	public void setServletRequest(HttpServletRequest request) {

		this.request = request;
	}

	public String execute() {
		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session
				.getAttribute(IConstants.USER);
		if (registrationVO != null) {
			if (registrationVO.getIsAdmin().equals("true"))
				allRegisteredUsersData = registrationService
						.getAllRegisterdUsers();
		} else
			return ERROR;
		return Action.SUCCESS;
	}

	public String getUserMessageCreditDetails() {

		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session
				.getAttribute(IConstants.USER);
		if (registrationVO != null) {
			if (registrationVO.getIsAdmin().equals("true")) {
				try {
					jObj = new JSONObject(getTask());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					if (jObj.getString("task").equalsIgnoreCase("getUserMessageCreditDetails")) {
						smsTrackVO = smsCountrySmsService.getUserMessageCreditDetail(jObj.getLong("userId"));
						smsTrackVO.setSessionStatus(1L);
					}
					if (jObj.getString("task").equalsIgnoreCase("updateSmsDetails")) {
						SmsTrackVO smsTrVo = new SmsTrackVO();
						smsTrVo.setUserSmsTrackId(jObj.getLong("userSmsTrackId"));
						smsTrVo.setUserId(jObj.getLong("userId"));
						smsTrVo.setSmsUserName(jObj.getString("smsUserName"));
						smsTrVo.setSmsPassword(jObj.getString("smsPassword"));
						smsTrVo.setSenderId(jObj.getString("smsSenderId"));
						smsTrVo.setRenewalSmsCount(jObj.getLong("smsCredit"));
                        smsTrackVO = smsCountrySmsService.updateUserMessageCreditDetail(smsTrVo);
						smsTrackVO.setSessionStatus(1L);
					}
				} catch (Exception e) {
					e.printStackTrace();
					smsTrackVO = new SmsTrackVO();
					smsTrackVO.setUpdateStatus(0L);
					smsTrackVO.setSessionStatus(1L);
					return Action.SUCCESS;
				}

				return Action.SUCCESS;
			}
			return Action.SUCCESS;
		} else
			smsTrackVO = new SmsTrackVO();
		smsTrackVO.setSessionStatus(0L);
		return Action.SUCCESS;

	}

}
