package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.dto.UserGroupBasicDetails;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.dto.UserGroupsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ISmsService;
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
	private static final Logger log = Logger.getLogger(UserGroupAction.class);
	private UserGroupDetailsVO userGroupDetailsVO;
	private Long remainingSms;
	private ISmsService smsCountrySmsService;
	private SmsResultVO resultVo ;
	private EntitlementsHelper entitlementsHelper;
	
	public SmsResultVO getResultVo() {
		return resultVo;
	}

	public void setResultVo(SmsResultVO resultVo) {
		this.resultVo = resultVo;
	}

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
	
	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public Long getRemainingSms() {
		return remainingSms;
	}

	public void setRemainingSms(Long remainingSms) {
		this.remainingSms = remainingSms;
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
	
	public UserGroupDetailsVO getUserGroupDetailsVO() {
		return userGroupDetailsVO;
	}

	public void setUserGroupDetailsVO(UserGroupDetailsVO userGroupDetailsVO) {
		this.userGroupDetailsVO = userGroupDetailsVO;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute() throws Exception
	{	
		staticGroupsListboxOptions = userGroupService.getAllStaticGroupNames();
		staticGroupsListboxOptions.add(0, new SelectOptionVO(0l,"Select System Group"));
		if(log.isDebugEnabled()){
			log.debug("Size: " + staticGroupsListboxOptions);
		}		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.USER_GROUPS_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.USER_GROUPS_ENTITLEMENT))
			return ERROR;
		
		Long userID = user.getRegistrationID();
		
		remainingSms = smsCountrySmsService.getRemainingSmsLeftForUser(userID);
				
		return SUCCESS;		
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
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getMyGroupsListForAUser"))
		{
			userGroupsVO = new UserGroupsVO();
			myGroupsListboxOptions = userGroupService.getAllMyGroupsCreatedByUser(user.getRegistrationID());
			myGroupsListboxOptions.add(0, new SelectOptionVO(0l,"Select A Group"));
			userGroupsVO.setMyGroupsListboxOptions(myGroupsListboxOptions);
			if(log.isDebugEnabled()){
				log.debug("Size: " + myGroupsListboxOptions);
			}
		}
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
			userGroupsVO = new UserGroupsVO();
			String staticGroupId = jObj.getString("staticGroupId");
			String parentGroupId = jObj.getString("parentGroupId");
			String status = jObj.getString("statusVal");
			String categoryType = jObj.getString("categoryType");
			String myGroupId = jObj.getString("myGroupId");
			
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
				userGroupDetailsVO.setCategoryType(IConstants.STATIC_GROUP);
				
			} else if(categoryType.equals("2"))
			{
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
			userGroupsVO.setUserGroupDetailsVO(userGroupService.createGroupForUser(userGroupDetailsVO)) ;
			
			
		} 
		if(jObj.getString("task").equalsIgnoreCase("addMemberToAGroup"))
		{
			userGroupsVO = new UserGroupsVO();
			Long groupId=new Long(jObj.getString("groupId"));
			userGroupMembersVO = new UserGroupMembersVO();
			userGroupMembersVO.setAddress(jObj.getString("address"));
			userGroupMembersVO.setDesignation(jObj.getString("designation"));
			userGroupMembersVO.setMobileNumber(jObj.getString("mobile"));
			userGroupMembersVO.setName(jObj.getString("name"));
			userGroupMembersVO.setLocation(jObj.getString("location"));			
			userGroupMembersVO.setEmailId(jObj.getString("eMailText"));
			userGroupMembersVO.setConfirmation(new Boolean(jObj.getString("confirmation")));
			userGroupsVO.setUserGroupMembersVO(userGroupService.addMemberToGroup(groupId,userGroupMembersVO));
						
		}
		if(jObj.getString("task").equalsIgnoreCase("getSelectedGroupMembersDetails"))
		{
			userGroupsVO = new UserGroupsVO();
			Long groupId=new Long(jObj.getString("id"));
			userGroupsVO.setUserGroupMembersList(userGroupService.getAllMembersIntheGroup(user.getRegistrationID(), groupId));
		}if(jObj.getString("task").equalsIgnoreCase("sendSMS"))
		{
			HttpSession session = request.getSession();
			RegistrationVO userVo = (RegistrationVO)session.getAttribute("USER");
			Long userID = userVo.getRegistrationID();
			
			String message = jObj.getString("message");
			JSONArray cellNumbers = jObj.getJSONArray("numbers");
			String smsMsgs[] = new String[cellNumbers.length()];
			
			for(int i=0; i<cellNumbers.length(); i++){
				smsMsgs[i] = (String)cellNumbers.get(i);
			}
			resultVo = userGroupService.sendSMStoGroup(message,smsMsgs,userID,jObj.getString("module"));			
		}if(jObj.getString("task").equalsIgnoreCase("getSubGroupsListInSystemGroups"))
		{
			userGroupsVO = new UserGroupsVO();
			Set<SelectOptionVO> breadCrumbList = new LinkedHashSet<SelectOptionVO>();
			SelectOptionVO breadCrumb = new SelectOptionVO();
			Long groupId=new Long(jObj.getString("categoryId"));
			String groupName = jObj.getString("name");
			breadCrumb.setId(0L);
			breadCrumb.setName(groupName);
			breadCrumbList.add(breadCrumb);
			session.removeAttribute("breadCrumb");
			session.setAttribute("breadCrumb",breadCrumbList);
			userGroupsVO.setSystemGroupsList(userGroupService.getSubGroupsListInSystemGroups(groupId, user.getRegistrationID()));
			
			userGroupsVO.setSystemGroupsBCList(breadCrumbList);
			
		}if(jObj.getString("task").equalsIgnoreCase("getSubGroupsListInMyGroups"))
		{
			userGroupsVO = new UserGroupsVO();
			Set<SelectOptionVO> myGroupsBCList = new LinkedHashSet<SelectOptionVO>();
			Set<SelectOptionVO> myGroupsBCListNew = null;			
			SelectOptionVO myGroupsBCObj = new SelectOptionVO();
			Long groupId=new Long(jObj.getString("id"));
			String groupName = jObj.getString("name");
			String flag = jObj.getString("flag");
			myGroupsBCObj.setId(groupId);
			myGroupsBCObj.setName(groupName);
			
			if("new".equals(flag)){
				if(log.isDebugEnabled())
					log.debug("flag is ::::" + flag);
				myGroupsBCList.add(myGroupsBCObj);
				session.removeAttribute("myGroupsBreadCrumb");
				session.setAttribute("myGroupsBreadCrumb",myGroupsBCList);				
				userGroupsVO.setMyGroupsBCList(myGroupsBCList);
			} else if("add".equals(flag))
			{
				if(log.isDebugEnabled())
					log.debug("flag is ::::" + flag);
				Set<SelectOptionVO> myGroupsBCList1  = (Set<SelectOptionVO>) session.getAttribute("myGroupsBreadCrumb");
				myGroupsBCList1.add(myGroupsBCObj);
				userGroupsVO.setMyGroupsBCList(myGroupsBCList1);
			} else if ("remove".equals(flag)) {
				Set<SelectOptionVO> myGroupsBCList2  = (Set<SelectOptionVO>) session.getAttribute("myGroupsBreadCrumb");
				if(log.isDebugEnabled())
					log.debug("flag is ::::" + flag);
				Set<SelectOptionVO> requiredList = new LinkedHashSet<SelectOptionVO>();
				for(SelectOptionVO vo : myGroupsBCList2){
					requiredList.add(vo);
					if(vo.getId().equals(groupId))
						break;					
				}
				myGroupsBCListNew = new LinkedHashSet<SelectOptionVO>();
				myGroupsBCListNew = requiredList;
				session.setAttribute("breadCrumb",myGroupsBCListNew);
				userGroupsVO.setMyGroupsBCList(myGroupsBCListNew);				
			}			
			userGroupsVO.setMyGroupsList(userGroupService.getSubGroupsListInMyGroups(groupId, user.getRegistrationID()));
			
		} if(jObj.getString("task").equalsIgnoreCase("getSubGroupsOfStaticGroupParents"))
		{
			Set<SelectOptionVO> breadCrumbListNew = null;
			Set<SelectOptionVO> breadCrumbList = (Set<SelectOptionVO>) session.getAttribute("breadCrumb");
			userGroupsVO = new UserGroupsVO();
			Long groupId=new Long(jObj.getString("id"));
			String groupName = jObj.getString("subGrpname");
			String flag = jObj.getString("flag");
			SelectOptionVO breadcrumb = new SelectOptionVO();  
			breadcrumb.setId(groupId);
			breadcrumb.setName(groupName);
			if ("Add".equals(flag)) {
				breadCrumbList.add(breadcrumb);
				userGroupsVO.setSystemGroupsBCList(breadCrumbList);
			} else if ("remove".equals(flag)) {				
				Set<SelectOptionVO> requiredList = new LinkedHashSet<SelectOptionVO>();
				for(SelectOptionVO vo : breadCrumbList){
					requiredList.add(vo);
					if(vo.getId().equals(groupId))
						break;					
				}
				breadCrumbListNew = new LinkedHashSet<SelectOptionVO>();
				breadCrumbListNew = requiredList;
				session.setAttribute("breadCrumb",breadCrumbListNew);
				userGroupsVO.setSystemGroupsBCList(breadCrumbListNew);
			}			
			userGroupsVO.setSystemGroupsList(userGroupService.getSubGroupsOfStaticGroupParents(groupId, user.getRegistrationID()));			
		} if(jObj.getString("task").equalsIgnoreCase("checkAvailability"))
		{
			userGroupsVO = new UserGroupsVO();
			String groupName =  jObj.getString("groupName");
			userGroupsVO.setGroupAlreadyExists(userGroupService.checkForAvailability(user.getRegistrationID(), groupName));
		} if(jObj.getString("task").equalsIgnoreCase("checkGroupMemberName"))
		{
			userGroupsVO = new UserGroupsVO();
			String memberName =  jObj.getString("name");
			Long groupId = new Long(jObj.getString("groupId"));
			userGroupsVO.setGroupMemberAlreadyExists(userGroupService.checkForExistingGroupMemeberByName( groupId, memberName));			
		}
		return Action.SUCCESS;
	}	
		
	@SuppressWarnings("unchecked")
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
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getSelectedStaticGroupCompleteDetails"))
		{
			userGroupBasicDetails = new UserGroupBasicDetails();
			Set<SelectOptionVO> sysGroupsBCList1 = new LinkedHashSet<SelectOptionVO>();
			Set<SelectOptionVO> sysGroupsBCListNew = null;			
			SelectOptionVO sysGroupsBCObj = new SelectOptionVO();
			String categoryType = jObj.getString("categoryType");
			String mainType = jObj.getString("mainType");			
			Long id=new Long(jObj.getString("id"));
			String groupName = jObj.getString("name");
			String flag = jObj.getString("flag");
			
			userGroupBasicDetails = userGroupService.getUserGroupDetailsForAUserForSystemGroups(mainType, id, user.getRegistrationID());
			if("new".equals(flag)){
				sysGroupsBCObj.setId(0L);
				sysGroupsBCObj.setName(groupName);
				sysGroupsBCList1.add(sysGroupsBCObj);
				//session.removeAttribute("sysGroupsBreadCrumb1");
				session.setAttribute("sysGroupsBreadCrumb1",sysGroupsBCList1);				
				userGroupBasicDetails.setSystemGroupsBCList(sysGroupsBCList1);
				
			}else if("add".equals(flag))
			{	
				Set<SelectOptionVO> sysGroupsBCList2  = (Set<SelectOptionVO>) session.getAttribute("sysGroupsBreadCrumb1");
				sysGroupsBCObj.setId(id);
				sysGroupsBCObj.setName(groupName);
				sysGroupsBCList2.add(sysGroupsBCObj);
				userGroupBasicDetails.setSystemGroupsBCList(sysGroupsBCList2);
				
			} else if ("remove".equals(flag)) {
				
				Set<SelectOptionVO> sysGroupsBCList3  = (Set<SelectOptionVO>) session.getAttribute("sysGroupsBreadCrumb1");
				Set<SelectOptionVO> requiredList = new LinkedHashSet<SelectOptionVO>();
				for(SelectOptionVO vo : sysGroupsBCList3){
					requiredList.add(vo);
					if(vo.getId().equals(id))
						break;					
				}
				sysGroupsBCListNew = new LinkedHashSet<SelectOptionVO>();
				sysGroupsBCListNew = requiredList;
				session.setAttribute("sysGroupsBreadCrumb1",sysGroupsBCListNew);
				userGroupBasicDetails.setSystemGroupsBCList(sysGroupsBCListNew);				
			}						
		}else if(jObj.getString("task").equalsIgnoreCase("getSelectedMyGroupCompleteDetails"))
		{
			userGroupBasicDetails = new UserGroupBasicDetails();
			Set<SelectOptionVO> myGroupsBCList1 = new LinkedHashSet<SelectOptionVO>();
			Set<SelectOptionVO> myGroupsBCListNew = null;			
			SelectOptionVO myGroupsBCObj = new SelectOptionVO();
			String categoryType = jObj.getString("categoryType");
			Long id=new Long(jObj.getString("id"));
			String groupName = jObj.getString("name");
			String flag = jObj.getString("flag");
			myGroupsBCObj.setId(id);
			myGroupsBCObj.setName(groupName);
			userGroupBasicDetails = userGroupService.getUserGroupDetailsForAUserForMyGroups(categoryType, id, user.getRegistrationID());
			if("new".equals(flag)){
				
				myGroupsBCList1.add(myGroupsBCObj);
				//session.removeAttribute("sysGroupsBreadCrumb1");
				session.setAttribute("myGroupsBreadCrumb1",myGroupsBCList1);				
				userGroupBasicDetails.setMyGroupsBCList(myGroupsBCList1);
				
			}else if("add".equals(flag))
			{	
				Set<SelectOptionVO> myGroupsBCList2  = (Set<SelectOptionVO>) session.getAttribute("myGroupsBreadCrumb1");
				
				myGroupsBCList2.add(myGroupsBCObj);
				userGroupBasicDetails.setMyGroupsBCList(myGroupsBCList2);
				
			} else if ("remove".equals(flag)) {
				
				Set<SelectOptionVO> myGroupsBCList3  = (Set<SelectOptionVO>) session.getAttribute("myGroupsBreadCrumb1");
				Set<SelectOptionVO> requiredList = new LinkedHashSet<SelectOptionVO>();
				for(SelectOptionVO vo : myGroupsBCList3){
					requiredList.add(vo);
					if(vo.getId().equals(id))
						break;					
				}
				myGroupsBCListNew = new LinkedHashSet<SelectOptionVO>();
				myGroupsBCListNew = requiredList;
				session.setAttribute("myGroupsBreadCrumb1",myGroupsBCListNew);
				userGroupBasicDetails.setMyGroupsBCList(myGroupsBCListNew);				
			}
		}		
		return Action.SUCCESS;
	}
	
	
}

