package com.itgrids.electoralconnect.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.electoralconnect.dto.CommentVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultCodeMapper;
import com.itgrids.electoralconnect.dto.UserVO;
import com.itgrids.electoralconnect.service.IMailService;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.electoralconnect.util.IConstants;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminPageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private static final Logger LOG = Logger.getLogger(AdminPageAction.class);
	private String task;
	private JSONObject jobj;
	private IUserService userService;
	private List<CommentVO> commentVO;
	private ResultStatus resultStatus;
	private UserVO userVO;
	private IMailService mailService;
	private Long repliesCount;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request  = request;
	}
	
	

	public Long getRepliesCount() {
		return repliesCount;
	}



	public void setRepliesCount(Long repliesCount) {
		this.repliesCount = repliesCount;
	}



	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public IUserService getUserService() {
		return userService;
	}


	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	public List<CommentVO> getCommentVO() {
		return commentVO;
	}


	public void setCommentVO(List<CommentVO> commentVO) {
		this.commentVO = commentVO;
	}

	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public UserVO getUserVO() {
		return userVO;
	}


	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}


	public IMailService getMailService() {
		return mailService;
	}


	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}


	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String getCommentsBtSelDates()
	{
		try {
			LOG.debug("Entered into getCommentsBtSelDates() method in AdminPageAction Action");
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return "notLogged";
			jobj = new JSONObject(getTask());
			if(jobj.getString("task").equalsIgnoreCase("commentsBetweenDates"))
			{
				String sdate           = jobj.getString("statrtDate");
				String eDate           = jobj.getString("endDate");
				int startIndex         = jobj.getInt("startIndex");
				int maxIndex           = jobj.getInt("maxIndex");
				//Long id                = jobj.getLong("id");
				DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
				String startYear       = sdate.substring(0, 4);
				String startMonth      = sdate.substring(5, 7);
				String startDay        = sdate.substring(8, 10);
				String endYear         = eDate.substring(0, 4);
				String endMonth        = eDate.substring(5, 7);
				String endDay          = eDate.substring(8, 10);
				Date startDate         = dateFormate.parse(startYear +"-"+ startMonth +"-"+ startDay);
				Date endDate           = dateFormate.parse(endYear +"-"+ endMonth +"-"+ endDay);
				commentVO = userService.getAllCommentsBetweenSelectedDates(startDate, endDate, startIndex, maxIndex);
			}
			
			else if(jobj.getString("task").equalsIgnoreCase("getTotalComments"))
			{
				int startIndex         = jobj.getInt("startIndex");
				int maxIndex           = jobj.getInt("maxIndex");
				commentVO = userService.getAllComments(startIndex, maxIndex);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in  getCommentsBtSelDates() method in AdminPageAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getRepliesForComment(){
		try {
			LOG.debug("Entered into abuseComment() method in AdminPageAction Action");
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return Action.ERROR;
			jobj = new JSONObject(getTask());
			Long commentId = jobj.getLong("id");
			if(jobj.getString("task").equalsIgnoreCase("getRepliesForComment")){
				commentVO=userService.getRepliesList(commentId);
			}
			else{
				repliesCount = userService.getRepliesCount(commentId);
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in  abuseComment() method in AdminPageAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String abuseComment()
	{
		try {
			LOG.debug("Entered into abuseComment() method in AdminPageAction Action");
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return Action.ERROR;
			jobj = new JSONObject(getTask());
			Long commentId = jobj.getLong("id");
			userVO = userService.abuseCommentService(commentId);
			if(userVO.getResultCode() == 0)
			{
				String requestURL= request.getRequestURL().toString();
				String requestFrom = "";
				if(requestURL.contains("www.partyanalyst.com"))
					requestFrom = IConstants.SERVER;
				else
					requestFrom = IConstants.LOCALHOST;
				resultStatus = mailService.sendMailToUserAboutAbuseComment(userVO,requestFrom);
			}
			else
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in  abuseComment() method in AdminPageAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	

}
