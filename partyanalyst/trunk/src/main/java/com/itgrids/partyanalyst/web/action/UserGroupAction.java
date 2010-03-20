package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
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
	private UserGroupMembersVO userGroupMembersVO = null;
	
	
		
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
	public UserGroupMembersVO getUserGroupMembersVO() {
		return userGroupMembersVO;
	}

	public void setUserGroupMembersVO(UserGroupMembersVO userGroupMembersVO) {
		this.userGroupMembersVO = userGroupMembersVO;
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
	
	@SuppressWarnings("unchecked")
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
		if(jObj.getString("task").equalsIgnoreCase("storeMembersDataIntoDB"))
		{
			Long groupId=new Long(jObj.getString("groupId"));
			userGroupMembersVO = new UserGroupMembersVO();
			userGroupMembersVO.setAddress(jObj.getString("address"));
			userGroupMembersVO.setDesignation(jObj.getString("designation"));
			userGroupMembersVO.setMobileNumber(jObj.getString("mobile"));
			userGroupMembersVO.setName(jObj.getString("name"));
			userGroupMembersVO.setLocation(jObj.getString("location"));
			userGroupMembersVO.setGroupName(jObj.getString("groupName"));
			
			userGroupService.addMemberToGroup(groupId,userGroupMembersVO);			
		}
		if(jObj.getString("task").equalsIgnoreCase("getSelectedGroupMembersDetails"))
		{
			userGroupsVO = new UserGroupsVO();
			Long groupId=new Long(jObj.getString("id"));
			userGroupsVO.setUserGroupMembersList(userGroupService.getAllMembersIntheGroup(user.getRegistrationID(), groupId));
		}if(jObj.getString("task").equalsIgnoreCase("sendSMS"))
		{
			
			String message = jObj.getString("message");
			JSONArray cellNumbers = jObj.getJSONArray("numbers");
			String smsMsgs[] = new String[cellNumbers.length()];
			for(int i=0; i<cellNumbers.length(); i++){
				smsMsgs[i] = (String)cellNumbers.get(i);
			/*	System.out.println("String " + no);
				smsMsgs[i] = no;*/
			}
			userGroupService.sendSMStoGroup(message,smsMsgs);			
		}if(jObj.getString("task").equalsIgnoreCase("getSubGroupsListInSystemGroups"))
		{
			userGroupsVO = new UserGroupsVO();
			//Map<Long, String> tm = new LinkedHashMap<Long, String>();
			Set<SelectOptionVO> breadCrumbList = new HashSet<SelectOptionVO>();
			SelectOptionVO breadCrumb = new SelectOptionVO();
			Long groupId=new Long(jObj.getString("categoryId"));
			String groupName = jObj.getString("name");
			breadCrumb.setId(0L);
			breadCrumb.setName(groupName);
			breadCrumbList.add(breadCrumb);
			session.removeAttribute("breadCrumb");
			session.setAttribute("breadCrumb",breadCrumbList);
			
			/*tm.put(0L, groupName);
			System.out.println("Before Session");
			session.removeAttribute("breadCrumb");
			session.setAttribute("breadCrumb", tm);*/
			
			/*Map<Long, String> map =(Map<Long, String>) session.getAttribute("breadCrumb");
			
			Set<Entry<Long, String>> set = map.entrySet();
			Iterator<Entry<Long, String>> it = set.iterator();
			while(it.hasNext()){
				Entry<Long, String> entry = it.next();
				String name= entry.getValue();
			//	System.out.println(" ID:"+" value:"+vo.getName());
			}*/
			System.out.println("after Session");
			
			userGroupsVO.setSystemGroupsList(userGroupService.getSubGroupsListInSystemGroups(groupId, user.getRegistrationID()));
			//userGroupsVO.setBreadCrumb(breadCrumbList);
			//userGroupsVO.setBMap(tm);
			userGroupsVO.setBreadCrumbList(breadCrumbList);
			
		}if(jObj.getString("task").equalsIgnoreCase("getSubGroupsListInMyGroups"))
		{
			userGroupsVO = new UserGroupsVO();
			Long groupId=new Long(jObj.getString("id"));
			userGroupsVO.setMyGroupsList(userGroupService.getSubGroupsListInMyGroups(groupId, user.getRegistrationID()));			
		} if(jObj.getString("task").equalsIgnoreCase("getSubGroupsOfStaticGroupParents"))
		{
			//List<SelectOptionVO> breadCrumbList = (List<SelectOptionVO>) session.getAttribute("breadCrumb");
			Set<SelectOptionVO> breadCrumbList = (Set<SelectOptionVO>) session.getAttribute("breadCrumb");
			//Map<Long, String> map =(Map) session.getAttribute("breadCrumb");
			userGroupsVO = new UserGroupsVO();
			Long groupId=new Long(jObj.getString("id"));
			String groupName = jObj.getString("subGrpname");
			String flag = jObj.getString("flag");
			SelectOptionVO breadcrumb = new SelectOptionVO();  
			breadcrumb.setId(groupId);
			breadcrumb.setName(groupName);
			if ("Add".equals(flag)) {
				//map.put(groupId, groupName);
				breadCrumbList.add(breadcrumb);
			} else if ("remove".equals(flag)) {
				boolean flag1 = false;
				//List<SelectOptionVO> requiredList  = new ArrayList<SelectOptionVO>();
				Set<SelectOptionVO> requiredList = new HashSet<SelectOptionVO>();
				for(SelectOptionVO vo : breadCrumbList){
					requiredList.add(vo);
					if(vo.getId().equals(groupId))
						break;
				}
				breadCrumbList = requiredList;
			}
			//userGroupsVO.setBreadCrumb(breadCrumbList);
			userGroupsVO.setBreadCrumbList(breadCrumbList);
			userGroupsVO.setSystemGroupsList(userGroupService.getSubGroupsOfStaticGroupParents(groupId, user.getRegistrationID()));
			
			
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
			userGroupBasicDetails = new UserGroupBasicDetails();
			String categoryType = jObj.getString("categoryType");
			Long id=new Long(jObj.getString("id"));
			userGroupBasicDetails = userGroupService.getUserGroupDetailsForAUserForMyGroups(categoryType, id, user.getRegistrationID());
		}		
		return Action.SUCCESS;
	}
	
	
}

