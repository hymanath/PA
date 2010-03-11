package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GroupsBasicInfoVO;
import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupBasicDetails;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.dto.UserGroupsVO;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private UserGroupsVO userGroupsVO;
	private GroupsDetailsForUserVO subGroupsForStaticGroupForUser;
	private UserGroupDetailsVO userGroupsDescriptionVO;
	private String task = null;
	JSONObject jObj = null;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private IUserGroupService userGroupService;
	private List<SelectOptionVO> staticGroupsListboxOptions;
	private List<SelectOptionVO> myGroupsListboxOptions;
	private UserGroupBasicDetails userGroupBasicDetails; 
		
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}	

	public UserGroupsVO getUserGroupsVO() {
		return userGroupsVO;
	}

	public void setUserGroupsVO(UserGroupsVO userGroupsVO) {
		this.userGroupsVO = userGroupsVO;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	

	public GroupsDetailsForUserVO getSubGroupsForStaticGroupForUser() {
		return subGroupsForStaticGroupForUser;
	}

	public void setSubGroupsForStaticGroupForUser(
			GroupsDetailsForUserVO subGroupsForStaticGroupForUser) {
		this.subGroupsForStaticGroupForUser = subGroupsForStaticGroupForUser;
	}	
	
	public UserGroupDetailsVO getUserGroupsDescriptionVO() {
		return userGroupsDescriptionVO;
	}

	public void setUserGroupsDescriptionVO(
			UserGroupDetailsVO userGroupsDescriptionVO) {
		this.userGroupsDescriptionVO = userGroupsDescriptionVO;
	}	

	public IUserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
	
	public List<SelectOptionVO> getStaticGroupsListboxOptions() {
		return staticGroupsListboxOptions;
	}

	public void setStaticGroupsListboxOptions(
			List<SelectOptionVO> staticGroupsListboxOptions) {
		this.staticGroupsListboxOptions = staticGroupsListboxOptions;
	}	
	
	public List<SelectOptionVO> getMyGroupsListboxOptions() {
		return myGroupsListboxOptions;
	}

	public void setMyGroupsListboxOptions(
			List<SelectOptionVO> myGroupsListboxOptions) {
		this.myGroupsListboxOptions = myGroupsListboxOptions;
	}
	
	public UserGroupBasicDetails getUserGroupBasicDetails() {
		return userGroupBasicDetails;
	}

	public void setUserGroupBasicDetails(UserGroupBasicDetails userGroupBasicDetails) {
		this.userGroupBasicDetails = userGroupBasicDetails;
	}

	public String execute() throws Exception
	{	
		
		staticGroupsListboxOptions = userGroupService.getAllStaticGroupNames();
		staticGroupsListboxOptions.add(0, new SelectOptionVO(0l,"Select System Group"));
		
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		myGroupsListboxOptions = userGroupService.getAllMyGroupsCreatedByUser(user.getRegistrationID());
		myGroupsListboxOptions.add(0, new SelectOptionVO(0l,"Select A Group"));  
		
		return Action.SUCCESS;
		
	}
	
	public String ajaxCallHandler()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserGroupDetailsVO userGroupDetailsVO;
		if(jObj.getString("task").equalsIgnoreCase("subGrpsCountInSystemGrpsForUser"))
		{
			userGroupsVO = new UserGroupsVO();
			
			userGroupsVO.setGroupsDetailsForUser(userGroupService.subGrpsCountInSystemGrpsForUser(user.getRegistrationID()));	
		}
		if(jObj.getString("task").equalsIgnoreCase("subGrpsCountInMyGrpsForUser"))
		{
			userGroupsVO = new UserGroupsVO();
			
			userGroupsVO.setGroupsDetailsForUser(userGroupService.subGroupsCountInMyGroupsForUser(user.getRegistrationID()));	
		}
		if(jObj.getString("task").equalsIgnoreCase("createNewGroup"))
		{
			String staticGroupId = jObj.getString("staticGroupId");
			String parentGroupId = jObj.getString("parentGroupId");
			System.out.println("parentGroupId in action" + parentGroupId);
			String status = jObj.getString("statusVal");
			System.out.println("status in action" + status);
			String categoryType = jObj.getString("categoryType");
			System.out.println("categoryType in action" + categoryType);
			String myGroupId = jObj.getString("myGroupId");
			System.out.println("myGroupId in action" + myGroupId);
			userGroupDetailsVO = new UserGroupDetailsVO();
			userGroupDetailsVO.setCreatedUserId(user.getRegistrationID());
			userGroupDetailsVO.setGroupName(jObj.getString("groupName"));
			userGroupDetailsVO.setGroupDesc(jObj.getString("groupdDesc"));
			if(status.equals("1")){
				userGroupDetailsVO.setStatus(IConstants.MAIN_GROUP);
			}else if(status.equals("2")){
				userGroupDetailsVO.setStatus(IConstants.SUB_GROUP);
			}	
			if(categoryType.equals("1"))
			{
				System.out.println("If category 1");
				userGroupDetailsVO.setCategoryType(IConstants.STATIC_GROUP);
				System.out.println("If category 1 in VO:-----" + userGroupDetailsVO.getCategoryType());
			} else if(categoryType.equals("2"))
			{
				System.out.println("If category 2");
				userGroupDetailsVO.setCategoryType(IConstants.MY_GROUP);
			}
			if(staticGroupId.equals("null"))
			{
				userGroupDetailsVO.setStaticGroupId(null);
			} else if(!staticGroupId.equals("null"))
			{
				userGroupDetailsVO.setStaticGroupId(new Long(staticGroupId));	
			}
			if(parentGroupId.equals("null"))
			{
				userGroupDetailsVO.setParentGroupId(null);
			} else if(!parentGroupId.equals("null"))
			{
				userGroupDetailsVO.setParentGroupId(new Long(parentGroupId));	
			}
			if(myGroupId.equals("null"))
			{
				userGroupDetailsVO.setMyGroupId(null);
			} else if(!myGroupId.equals("null"))
			{
				userGroupDetailsVO.setMyGroupId(new Long(myGroupId));	
			}
			userGroupService.createGroupForUser(userGroupDetailsVO);
			System.out.println("From VO after Service call: Category:===="+userGroupDetailsVO.getCategoryType());
		}		
		return Action.SUCCESS;
	}
	
	public String getUserGroupsBasicInfo(){
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getSelectedStaticGroupCompleteDetails"))
		{
			userGroupBasicDetails = new UserGroupBasicDetails();
			String categoryType = jObj.getString("categoryType");
			String mainType = jObj.getString("mainType");
			Date date= new Date();
			Long id=new Long(jObj.getString("id")); 
			System.out.println("categoryType :" + categoryType + " id : " + id);
			userGroupBasicDetails = userGroupService.getUserGroupDetailsForAUserForSystemGroups(mainType, id, user.getRegistrationID());
			//System.out.println("userGroupBasicDetails :" + userGroupBasicDetails.getSubGroupDetails().size());
			//userGroupService.sampleService();
			
		}else if(jObj.getString("task").equalsIgnoreCase("getSelectedMyGroupCompleteDetails"))
		{
				
		}		
		return Action.SUCCESS;
	}	
}

