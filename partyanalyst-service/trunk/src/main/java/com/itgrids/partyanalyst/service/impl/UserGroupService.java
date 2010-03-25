/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 17, 2010
 * Author Saikrishna.g
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IMyGroupDAO;
import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStaticGroupDAO;
import com.itgrids.partyanalyst.dao.IStaticUserGroupDAO;
import com.itgrids.partyanalyst.dao.IStaticUsersDAO;
import com.itgrids.partyanalyst.dao.IUserGroupPrivilegesDAO;
import com.itgrids.partyanalyst.dto.GroupsBasicInfoVO;
import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupBasicDetails;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.dto.UserGroupsVO;
import com.itgrids.partyanalyst.model.MyGroup;
import com.itgrids.partyanalyst.model.PersonalUserGroup;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.StaticGroup;
import com.itgrids.partyanalyst.model.StaticUserGroup;
import com.itgrids.partyanalyst.model.StaticUsers;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserGroupService implements IUserGroupService {

	private IMyGroupDAO myGroupDAO;
	private IPersonalUserGroupDAO personalUserGroupDAO;
	private IStaticUsersDAO staticUsersDAO;
	private IRegistrationDAO registrationDAO;	
	private IStaticUserGroupDAO staticUserGroupDAO;
	private IStaticGroupDAO staticGroupDAO;
	private TransactionTemplate transactionTemplate;
	private UserGroupDetailsVO userGroupDetailsVo;
	private UserGroupMembersVO userGroupMembersVo;
	private static final Logger log = Logger.getLogger(UserGroupService.class);
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private ISmsService smsCountrySmsService;	
	
	public IPersonalUserGroupDAO getPersonalUserGroupDAO() {
		return personalUserGroupDAO;
	}

	public void setPersonalUserGroupDAO(IPersonalUserGroupDAO personalUserGroupDAO) {
		this.personalUserGroupDAO = personalUserGroupDAO;
	}
	
	public IStaticUserGroupDAO getStaticUserGroupDAO() {
		return staticUserGroupDAO;
	}

	public void setStaticUserGroupDAO(IStaticUserGroupDAO staticUserGroupDAO) {
		this.staticUserGroupDAO = staticUserGroupDAO;
	}

	public IStaticGroupDAO getStaticGroupDAO() {
		return staticGroupDAO;
	}

	public void setStaticGroupDAO(IStaticGroupDAO staticGroupDAO) {
		this.staticGroupDAO = staticGroupDAO;
	}
	
	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
	public List<UserGroupMembersVO> addMembersToGroup() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IStaticUsersDAO getStaticUsersDAO() {
		return staticUsersDAO;
	}

	public void setStaticUsersDAO(IStaticUsersDAO staticUsersDAO) {
		this.staticUsersDAO = staticUsersDAO;
	}	

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public IMyGroupDAO getMyGroupDAO() {
		return myGroupDAO;
	}

	public void setMyGroupDAO(IMyGroupDAO myGroupDAO) {
		this.myGroupDAO = myGroupDAO;
	}

	/**
	 * To Get The List Of All Static groups From DB Into SelectOptionVO.These results are displayed in the drop down option in create a user group panel.
	 * @return staticGroups
	 */	
	public List<SelectOptionVO> getAllStaticGroupNames() {
		if(log.isDebugEnabled()){
			log.debug("Entered getAllStaticGroupNames() method....");
		}
		List<SelectOptionVO> staticGroups = new ArrayList<SelectOptionVO>();
		List<StaticGroup> list= staticGroupDAO.getAll();
		for(StaticGroup staticGroup:list)
			staticGroups.add(new SelectOptionVO(staticGroup.getStaticGroupId(), staticGroup.getGroupName()));
		return staticGroups;		
	}
	/**
	 * To Get The List Of All My groups From DB Into SelectOptionVO. These results are displayed in the drop down option in create a user group panel. 
	 * @return myGroupsList
	 */
	public List<SelectOptionVO> getAllMyGroupsCreatedByUser(Long userId) {
		if(log.isDebugEnabled()){
			log.debug("Entered  getAllMyGroupsCreatedByUser() method....");
		}
		List<SelectOptionVO> myGroupsList = new ArrayList<SelectOptionVO>();
		List<PersonalUserGroup> list= personalUserGroupDAO.getAllMyGroupsByUserId(userId);
		for(PersonalUserGroup myGroups:list)
			myGroupsList.add(new SelectOptionVO(myGroups.getPersonalUserGroupId(), myGroups.getGroupName()));
		return myGroupsList;		
	}	
	
	/*
	 * This method takes the UserGroupDetailsVO object which contains the details entered by the user while creating a group.
	 * These details are saved in to the data base.The same value object is returned.
	 */
	public UserGroupDetailsVO createGroupForUser( UserGroupDetailsVO userGroupDetailsToSave) {
		this.userGroupDetailsVo = userGroupDetailsToSave;
		if(log.isDebugEnabled()){
			log.debug("Entered createGroupForUser method....");
		}
		 final UserGroupDetailsVO userGroupDetailsFromDb =new UserGroupDetailsVO();
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			public void doInTransactionWithoutResult(TransactionStatus status)
			{
				Registration reg = null;
				PersonalUserGroup personalUserGroup=null;				
				
				MyGroup myGroupObj = null;				
				try{
					/* To get current Date and time */
					java.util.Date now = new java.util.Date();
					String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String strDateNew = sdf.format(now) ;
				    now = sdf.parse(strDateNew);
				String groupStatus = UserGroupService.this.userGroupDetailsVo.getStatus();
				String categoryType = UserGroupService.this.userGroupDetailsVo.getCategoryType();
				
				if(groupStatus.equals(IConstants.MAIN_GROUP))					
				{	
					if(log.isDebugEnabled()){
						log.debug("If Main Group");
					}
					myGroupObj = new MyGroup();
					reg = registrationDAO.get(UserGroupService.this.userGroupDetailsVo.getCreatedUserId());
					
					myGroupObj.setGroupName(UserGroupService.this.userGroupDetailsVo.getGroupName());
					myGroupObj.setGroupDescription(UserGroupService.this.userGroupDetailsVo.getGroupDesc());
					myGroupObj.setCreatedDate(now);
										
					personalUserGroup = new PersonalUserGroup();
					personalUserGroup.setGroupName(UserGroupService.this.userGroupDetailsVo.getGroupName());
					personalUserGroup.setDescription(UserGroupService.this.userGroupDetailsVo.getGroupDesc());
					personalUserGroup.setCreatedUserId(reg);
					personalUserGroup.setCreatedDate(now);
					personalUserGroup.setMyGroup(myGroupObj);
					
					personalUserGroup = personalUserGroupDAO.save(personalUserGroup);
										
				} else if(groupStatus.equals(IConstants.SUB_GROUP) && categoryType.equals(IConstants.STATIC_GROUP))
				{	
					if(log.isDebugEnabled()){
						log.debug("If sub group in static Group");
					}
					personalUserGroup = createSubGroupUnderStaticGroups(userGroupDetailsVo);
				} else if(groupStatus.equals(IConstants.SUB_GROUP) && categoryType.equals(IConstants.MY_GROUP))
				{
					if(log.isDebugEnabled()){
						log.debug("If sub group in My Group");
					}
					personalUserGroup = createSubGroupUnderMyGroups(userGroupDetailsVo);
				}
								
				userGroupDetailsFromDb.setGroupId(personalUserGroup.getPersonalUserGroupId());
				userGroupDetailsFromDb.setGroupName(personalUserGroup.getGroupName());	
				
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while saving a group::", e);					}
			}					
		}
		});	
		return userGroupDetailsFromDb;
	}
	/**
	 * This method is invoked by the createGroupForUser() from the if block , where the groupStatus is SUB_GROUP categoryType is STATIC_GROUP  
	 * @param userGroupDetailsToSave
	 * @throws Exception
	 */
	public PersonalUserGroup createSubGroupUnderStaticGroups(UserGroupDetailsVO userGroupDetailsToSave) throws Exception
	{
		if(log.isDebugEnabled()){
			log.debug("Entered createSubGroupUnderStaticGroups() method....");
		}
		StaticGroup staticGroupObj = null;
		PersonalUserGroup personalUserGroup= new PersonalUserGroup();
		Registration reg = null;
		java.util.Date now = new java.util.Date();
		String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String strDateNew = sdf.format(now) ;
	    now = sdf.parse(strDateNew);
	    Long parentGroupId = userGroupDetailsToSave.getParentGroupId();
	    reg = registrationDAO.get(userGroupDetailsToSave.getCreatedUserId());
		staticGroupObj = staticGroupDAO.get(new Long(userGroupDetailsToSave.getStaticGroupId()));
		personalUserGroup.setGroupName(userGroupDetailsToSave.getGroupName());
		personalUserGroup.setDescription(userGroupDetailsToSave.getGroupDesc());
		personalUserGroup.setStaticGroup(staticGroupObj);
		if(parentGroupId != null)
		{
			if(log.isDebugEnabled()){
				log.debug("Inside Parent Group Id is not null" + parentGroupId);
			}			
			PersonalUserGroup parentGroup = personalUserGroupDAO.get(parentGroupId); 
			
			personalUserGroup.setParentGroupId(parentGroup);
			personalUserGroup.setParentGroupName(parentGroup.getGroupName());
		}else
		{
			if(log.isDebugEnabled()){
				log.debug("Inside Parent Group Id is null");
			}
			personalUserGroup.setParentGroupId(null);
			personalUserGroup.setParentGroupName(null);
		}		
		personalUserGroup.setCreatedUserId(reg);
		personalUserGroup.setCreatedDate(now);
		
		personalUserGroup = personalUserGroupDAO.save(personalUserGroup);
		if(log.isDebugEnabled()){
			log.debug("After Execution of Save Method");
		}
		return personalUserGroup;
	}
	/**
	 * This method is invoked by the createGroupForUser() from the if block , where the groupStatus is SUB_GROUP  categoryType is MY_GROUP  
	 * @param userGroupDetailsToSave
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PersonalUserGroup createSubGroupUnderMyGroups(UserGroupDetailsVO userGroupDetailsToSave) throws Exception
	{
		if(log.isDebugEnabled()){
			log.debug("Inside createSubGroupUnderMyGroups()");
		}
		PersonalUserGroup personalUserGroup= new PersonalUserGroup();
		PersonalUserGroup parentGroupObj= new PersonalUserGroup();
		Registration reg = null;
		List<MyGroup> myGroupObj = null; 
		java.util.Date now = new java.util.Date();
		String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String strDateNew = sdf.format(now) ;
	    now = sdf.parse(strDateNew);
	    Long parentGroupId = userGroupDetailsToSave.getParentGroupId();
	    reg = registrationDAO.get(userGroupDetailsToSave.getCreatedUserId());
		myGroupObj = personalUserGroupDAO.getMyGroupObjFromPersonalUserGroup(userGroupDetailsToSave.getMyGroupId());
		personalUserGroup.setGroupName(userGroupDetailsToSave.getGroupName());
		personalUserGroup.setDescription(userGroupDetailsToSave.getGroupDesc());
		if(myGroupObj.size()>0 && myGroupObj != null )
		{	
			personalUserGroup.setMyGroup(myGroupObj.get(0));			
		} else {
			personalUserGroup.setMyGroup(null);
		}
		personalUserGroup.setCreatedDate(now);
		if(parentGroupId != null)
		{
			PersonalUserGroup parentGroup = personalUserGroupDAO.get(parentGroupId); 
			
			personalUserGroup.setParentGroupId(parentGroup);
			personalUserGroup.setParentGroupName(parentGroup.getGroupName());
		}else
		{
			parentGroupObj = personalUserGroupDAO.get(userGroupDetailsToSave.getMyGroupId());
			personalUserGroup.setParentGroupId(parentGroupObj);
			personalUserGroup.setParentGroupName(parentGroupObj.getGroupName());
		}		
		personalUserGroup.setCreatedUserId(reg);
		personalUserGroup.setCreatedDate(now);
		
		personalUserGroup = personalUserGroupDAO.save(personalUserGroup);
		if(log.isDebugEnabled()){
			log.debug("After Execution of Save Method");
		}
		return personalUserGroup;		
	}
	
	/*
	 * 
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#systemGroupsDetailsForUser(java.lang.Long)
	 * this method returns the sub groups count under static groups for a user
	 * The results retrieved from this method is displayed in left navigation side of user groups home page 
	 */
	@SuppressWarnings("unchecked")
	public List <GroupsDetailsForUserVO> subGrpsCountInSystemGrpsForUser(Long userId)
	{
		if(log.isDebugEnabled()){
			log.debug("Entered in to subGrpsCountInSystemGrpsForUser Method");
		}
		List subGroupsinSystemGroups = null;
		List <GroupsDetailsForUserVO> systemGroupsDetailsForUserList = new ArrayList<GroupsDetailsForUserVO>(0);
		subGroupsinSystemGroups = personalUserGroupDAO.findSubGroupsCountInSystemGroupsByUserId(userId);
		
		for(int i=0;i<subGroupsinSystemGroups.size();i++){
			GroupsDetailsForUserVO groupsDetailsForUser = new GroupsDetailsForUserVO();
			Object[] parms = (Object[])subGroupsinSystemGroups.get(i);	
			groupsDetailsForUser.setStaticGroupId(Long.parseLong(parms[0].toString()));
			groupsDetailsForUser.setStaticGroupName(parms[1].toString());
			groupsDetailsForUser.setNumberOfGroups(Long.parseLong(parms[2].toString()));
			systemGroupsDetailsForUserList.add(groupsDetailsForUser);	
		}		
		return systemGroupsDetailsForUserList;
	}
	/*
	 * this method returns the MyGroups(created by user other than system groups) and the number of sub groups(if any) in the top level group.
	 * The results retrieved from this method is displayed in left navigation side of user groups home page 
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#myGroupsDetailsForUser(java.lang.Long)
	 */
	
	@SuppressWarnings("unchecked")
	public List<GroupsDetailsForUserVO> subGroupsCountInMyGroupsForUser(Long userId)
	{
		if(log.isDebugEnabled()){
			log.debug("Inside subGroupsCountInMyGroupsForUser()");
		}
		List<GroupsDetailsForUserVO> groupsList = new ArrayList<GroupsDetailsForUserVO>();
		List<PersonalUserGroup> myGroupsCategoryList= personalUserGroupDAO.getAllMyGroupsByUserId(userId);
		for(PersonalUserGroup myGroups:myGroupsCategoryList)
		{
			GroupsDetailsForUserVO groupVO = new GroupsDetailsForUserVO();
			Long myGroupId =myGroups.getPersonalUserGroupId();
			System.out.println("myGroupId :" + myGroupId);
			groupVO.setStaticGroupId(myGroupId);
			groupVO.setStaticGroupName(myGroups.getGroupName());
			List subGrpCount = personalUserGroupDAO.getSubGroupsCountInMyGroupsByUserId(userId, myGroupId);
			System.out.println("SubGroups ..." + subGrpCount.size());
			if(subGrpCount != null && subGrpCount.size() > 0){
			Object[] params = (Object[])subGrpCount.get(0);
			Long grpId = (Long)params[0];
			String grpName = (String)params[1];
			Long count = (Long)params[2];
			groupVO.setNumberOfGroups(count);
			log.debug("SubGroups ... in VO" + groupVO.getNumberOfGroups());
			} else			
			groupVO.setNumberOfGroups(new Long(0));
			log.debug("SubGroups ... in VO" + groupVO.getNumberOfGroups());
			groupsList.add(groupVO);
		}		
		return groupsList;
	}
	
	/*
	 * This method takes the UserGroupMembersVO object which contains the details entered by the user while creating a group.
	 * These details are saved in to the data base.The same value object is returned.
	 */
	
	public UserGroupMembersVO addMemberToGroup(final Long groupId, UserGroupMembersVO userGroupMembersToSave) {
		this.userGroupMembersVo=userGroupMembersToSave;
		if(log.isDebugEnabled()){
			log.debug("Entered addMemberToGroup method....");
		}
		final UserGroupMembersVO userGroupMembersFromDb = new UserGroupMembersVO();
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				public void doInTransactionWithoutResult(TransactionStatus status)
				{
                
                PersonalUserGroup personalUserGroup=null;
                StaticUsers staticUsers = null;
                 
                try{
                personalUserGroup= personalUserGroupDAO.get(groupId);
                staticUsers = new StaticUsers();          
                staticUsers.setName(UserGroupService.this.userGroupMembersVo.getName());
                staticUsers.setAddress(UserGroupService.this.userGroupMembersVo.getAddress());
                staticUsers.setEmailId(UserGroupService.this.userGroupMembersVo.getEmailId());
                staticUsers.setMobileNumber(UserGroupService.this.userGroupMembersVo.getMobileNumber());
                staticUsers.setDesignation(UserGroupService.this.userGroupMembersVo.getDesignation());
                staticUsers.setLocation(UserGroupService.this.userGroupMembersVo.getLocation());
                             
                StaticUserGroup staticUserGroup = new StaticUserGroup();
                staticUserGroup.setStaticUser(staticUsers);
                staticUserGroup.setPersonalUserGroup(personalUserGroup);
                staticUserGroup = staticUserGroupDAO.save(staticUserGroup);                          
              
                userGroupMembersFromDb.setName(staticUserGroup.getStaticUser().getName());
                userGroupMembersFromDb.setGroupName(staticUserGroup.getPersonalUserGroup().getGroupName());
                System.out.println("Group Name::::::" + userGroupMembersFromDb.getGroupName());
                System.out.println("Member Name:::::::" + userGroupMembersFromDb.getName());
				}
                catch(Exception e){
                	e.printStackTrace();
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Adding a member in to the Group::", e);
					}			
				}
				}
			});	
			return userGroupMembersFromDb;

		}
	

	/*
	 * This Method Returns Complete Group Details For a System Group or my group based on userId.The complete details incudes the group name,description,
	 * Sub groups outer details like name,description, members count and sub groups count!
	 */
	@SuppressWarnings("unchecked")
	public UserGroupBasicDetails getUserGroupDetailsForAUserForSystemGroups(String categoryType,Long groupId,Long userId){
		
		UserGroupBasicDetails userGroupBasicDetails = new UserGroupBasicDetails();
		ResultStatus resultStatus = new ResultStatus();		
		if(log.isDebugEnabled())
			log.debug("Entered Into getUserGroupDetailsForAUserForSystemGroups method ....");
				
		if(categoryType != null && groupId != null && userId != null){
		try{
			List<Long> staticGrpIds = null;
			StaticGroup staticGrpObj = null;
			PersonalUserGroup persnlUserGrp = null;
			List staticGroups = null;
			staticGroups = staticGroupDAO.findAllStaticGroupDetails();
			if(staticGroups != null && staticGroups.size() > 0){
				staticGrpIds = new ArrayList<Long>();
				for(int i=0;i<staticGroups.size();i++){
				Object groupDetails = (Object)staticGroups.get(i);
				Long grpId = (Long)groupDetails;
				staticGrpIds.add(grpId);
				}
				if(staticGrpIds.contains(groupId) && categoryType.equals(IConstants.USER_GROUP_CATEGORY_PARENT)){
				staticGrpObj = staticGroupDAO.get(groupId);
				userGroupBasicDetails.setGroupBasicDetails(getCompleteDetailsOfAStaticCategoryGroup(staticGrpObj,userId));
				userGroupBasicDetails.setSubGroupDetails(getSubGroupsOfAStaticCategoryGroup(staticGrpObj,userId));
				}
			}
			if(staticGrpObj == null || categoryType.equals(IConstants.USER_GROUP_CATEGORY_CHILD)){
			persnlUserGrp = personalUserGroupDAO.get(groupId);
			userGroupBasicDetails.setGroupBasicDetails(getCompleteDetailsOfAPersonalUserGroup(persnlUserGrp,userId));
			userGroupBasicDetails.setSubGroupDetails(getSubGroupsOfAPersonalUserGroup(persnlUserGrp,userId));
			userGroupBasicDetails.setMembersMobileNos(getUserGroupMobileNos(persnlUserGrp.getPersonalUserGroupId(),userId));
			}
				
		}catch(Exception ex){
			if(log.isDebugEnabled())
				log.debug("Exception Raised --->" + ex);
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultPartial(true);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			userGroupBasicDetails.setResultStatus(resultStatus);
		}
		
		}
		
		return userGroupBasicDetails;
	}
	
	/*
	 * Returns Basic Details For A System Group.This method is invoked by the getUserGroupDetailsForAUserForSystemGroups() and the results retrieved from this method 
	 * is sent to the calling method 
	 */
	@SuppressWarnings("unchecked")
	public GroupsBasicInfoVO getCompleteDetailsOfAStaticCategoryGroup(StaticGroup staticGrp,Long userId) throws Exception{
		if(log.isDebugEnabled())
			log.debug("Inside  getCompleteDetailsOfAStaticCategoryGroup method....");
		
		GroupsBasicInfoVO groupsBasicInfo = null;
		if(staticGrp != null){
			Long staticGroupId = staticGrp.getStaticGroupId();
			Long subGroupsCount = null;
			Long membersCount = new Long(0);
			
			groupsBasicInfo = new GroupsBasicInfoVO();
			groupsBasicInfo.setGroupId(staticGroupId);
			groupsBasicInfo.setMembersCount(membersCount);
			groupsBasicInfo.setGroupName(staticGrp.getGroupName());
			groupsBasicInfo.setDesc(staticGrp.getGroupDescription());
			groupsBasicInfo.setCreatedDate(staticGrp.getCreatedDate().toString());
			
			List subGrpsCount = personalUserGroupDAO.getSubGroupsCountForASystemGroup(staticGroupId,userId);
			if(subGrpsCount != null && subGrpsCount.size() > 0){
				Object params = (Object)subGrpsCount.get(0);
				subGroupsCount = (Long)params;
				groupsBasicInfo.setSubGroupsCount(subGroupsCount);
			}
		}
		return groupsBasicInfo;
	}
	/*
	 * This method is invoked by the getUserGroupDetailsForAUserForSystemGroups() and the results retrieved from this method 
	 * is sent to the calling method 
	 */
	@SuppressWarnings("unchecked")
	public List<GroupsBasicInfoVO> getSubGroupsOfAStaticCategoryGroup(StaticGroup staticGrp,Long userId) throws Exception{
		if(log.isDebugEnabled())
			log.debug(" Inside getSubGroupsOfAStaticCategoryGroup Method ......");
				
		List<GroupsBasicInfoVO> subGroupsList = null;
		if(staticGrp != null){
			subGroupsList = new ArrayList<GroupsBasicInfoVO>();
			Long staticGroupId = staticGrp.getStaticGroupId();
			List subGrpsDetails  = personalUserGroupDAO.getSubGroupsCompleteDetailsForASystemGroup(staticGroupId,userId);
			
			if(subGrpsDetails != null && subGrpsDetails.size() > 0){
				for(int i=0;i<subGrpsDetails.size();i++){
					Object[] params = (Object[])subGrpsDetails.get(i);
					Long grpId = (Long)params[0];
					String gname = (String)params[1];
					String desc = (String)params[2];
					Date createdDate = (Date)params[3];
					
					GroupsBasicInfoVO groupBasicInfo = new GroupsBasicInfoVO();
					groupBasicInfo.setGroupId(grpId);
					groupBasicInfo.setGroupName(gname);
					groupBasicInfo.setDesc(desc);
					if(createdDate != null)
					groupBasicInfo.setCreatedDate(createdDate.toString());
					groupBasicInfo.setMembersCount(getGroupMembersCount(grpId,userId));
					groupBasicInfo.setSubGroupsCount(getSubGroupsCount(grpId,userId));
					
					subGroupsList.add(groupBasicInfo);
				}
			}
		}
		return subGroupsList;
	}
	/*
	 * This method is invoked by the getUserGroupDetailsForAUserForSystemGroups() and the results retrieved from this method 
	 * is sent to the calling method 
	 */
	@SuppressWarnings("unchecked")
	public GroupsBasicInfoVO getCompleteDetailsOfAPersonalUserGroup(PersonalUserGroup personalUserGrp,Long userId) throws Exception{
		if(log.isDebugEnabled())
			log.debug(" Inside getCompleteDetailsOfAPersonalUserGroup Method ......");
		
		GroupsBasicInfoVO groupsBasicInfo = null;
		if(personalUserGrp != null){
			Long persnlGroupId = personalUserGrp.getPersonalUserGroupId();
						
			groupsBasicInfo = new GroupsBasicInfoVO();
			groupsBasicInfo.setGroupId(persnlGroupId);
			groupsBasicInfo.setGroupName(personalUserGrp.getGroupName());
			groupsBasicInfo.setDesc(personalUserGrp.getDescription());
			if(personalUserGrp.getCreatedDate() != null)
			groupsBasicInfo.setCreatedDate(personalUserGrp.getCreatedDate().toString());
			groupsBasicInfo.setSubGroupsCount(getSubGroupsCount(persnlGroupId,userId));
			groupsBasicInfo.setMembersCount(getGroupMembersCount(persnlGroupId,userId));
		}
		return groupsBasicInfo;
	}
	
	/*
	 * This method is invoked by the getUserGroupDetailsForAUserForSystemGroups() and the results retrieved from this method 
	 * is sent to the calling method 
	 */
	@SuppressWarnings("unchecked")
	public List<GroupsBasicInfoVO> getSubGroupsOfAPersonalUserGroup(PersonalUserGroup personalUserGrp,Long userId) throws Exception{
		if(log.isDebugEnabled())
			log.debug(" Inside getSubGroupsOfAPersonalUserGroup Method ......");
		List<GroupsBasicInfoVO> subGroupsList = null;
		if(personalUserGrp != null){
			subGroupsList = new ArrayList<GroupsBasicInfoVO>();
			Long groupId = personalUserGrp.getPersonalUserGroupId();
			List subGrpsDetails  = personalUserGroupDAO.getSubGroupsCompleteDetailsForASystemGroupFromPersonalUserGroup(groupId,userId);
			if(subGrpsDetails != null && subGrpsDetails.size() > 0){
				for(int i=0;i<subGrpsDetails.size();i++){
					Object[] params = (Object[])subGrpsDetails.get(i);
					Long grpId = (Long)params[0];
					String gname = (String)params[1];
					String desc = (String)params[2];
					Date createdDate = (Date)params[3];
					
					GroupsBasicInfoVO groupBasicInfo = new GroupsBasicInfoVO();
					groupBasicInfo.setGroupId(grpId);
					groupBasicInfo.setGroupName(gname);
					groupBasicInfo.setDesc(desc);
					if(createdDate != null)
					groupBasicInfo.setCreatedDate(createdDate.toString());
					groupBasicInfo.setMembersCount(getGroupMembersCount(grpId,userId));
					groupBasicInfo.setSubGroupsCount(getSubGroupsCount(grpId,userId));
					
					subGroupsList.add(groupBasicInfo);
				}
			}
		}
			
		return subGroupsList;
	}
	/*
	 * This method is invoked by getCompleteDetailsOfAPersonalUserGroup() and getSubGroupsOfAPersonalUserGroup() and the results retrieved from this method 
	 * is sent to the calling method.This method will return the sub groups count in a group. 
	 */
	@SuppressWarnings("unchecked")
	public Long getSubGroupsCount(Long groupId,Long userId) throws Exception{
		if(log.isDebugEnabled())
			log.debug("Inside getSubGroupsCount Method ..");
		
		Long subGroupsCount = null;
		List persnlGrpsCount = personalUserGroupDAO.getSubGroupsCountForASystemGroupFromPersonalUserGroup(groupId,userId);
		if(persnlGrpsCount != null && persnlGrpsCount.size() > 0){
			Object params = (Object)persnlGrpsCount.get(0);
			subGroupsCount = (Long)params;
		}
		
		return subGroupsCount;
	}
	
	/*
	 * This method is invoked by getCompleteDetailsOfAPersonalUserGroup() and getSubGroupsOfAPersonalUserGroup() and the results retrieved from this method 
	 * is sent to the calling method.This method will return the members count in a group. 
	 */
	
	@SuppressWarnings("unchecked")
	public Long getGroupMembersCount(Long groupId,Long userId) throws Exception{
		if(log.isDebugEnabled())
			log.debug("Inside getGroupMembersCount Method ..");
		Long membersCount = null;
		
		List grpMembrsCount = staticUserGroupDAO.getGroupMembersCountForAGroup(groupId, userId);
		if(grpMembrsCount != null && grpMembrsCount.size() >0){
			Object memParams = (Object)grpMembrsCount.get(0);
			membersCount = (Long)memParams;
		}
		return membersCount;
	}
	
	
	 /*
	  * (non-Javadoc)
	  * @see com.itgrids.partyanalyst.service.IUserGroupService#getUserGroupDetailsForAUserForMyGroups(java.lang.String, java.lang.Long, java.lang.Long)
	  * Returns Complete UserGroup Details For MyGroups.
	  */
	@SuppressWarnings("unchecked")
	public UserGroupBasicDetails getUserGroupDetailsForAUserForMyGroups(String categoryType,Long userGroupId,Long userId){
		
		if(log.isDebugEnabled())
			log.debug("Inside getUserGroupDetailsForAUserForMyGroups Method ...");
		 
		 UserGroupBasicDetails userGroupBasicDetails = null;
		 GroupsBasicInfoVO groupsBasicInfo = null;
		 List<GroupsBasicInfoVO> subGroupDetails = null;
		 ResultStatus resultStatus = new ResultStatus();
		 
		 try{
		 if(userGroupId != null && userId != null){
			 userGroupBasicDetails = new UserGroupBasicDetails();
			 			 
			 List basicInfo = personalUserGroupDAO.getSubGroupsDetailsForMyGroupFromPersonalUserGroup(userGroupId, userId);
			 if(basicInfo != null && basicInfo.size() > 0)
			 groupsBasicInfo = getBasicInfoForAGroupForList(basicInfo,userId);
			 subGroupDetails = getSubGroupsOfAPersonalUserGroupFromMyGroup(userGroupId,userId);
			 
			 userGroupBasicDetails.setGroupBasicDetails(groupsBasicInfo);
			 userGroupBasicDetails.setSubGroupDetails(subGroupDetails);
			 userGroupBasicDetails.setMembersMobileNos(getUserGroupMobileNos(userGroupId,userId));
			 
		 }
		 }catch(Exception ex){
			if(log.isDebugEnabled())
				log.debug("Exception Raised ---->" + ex);
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			userGroupBasicDetails.setResultStatus(resultStatus);
		 }
		 
		 return userGroupBasicDetails;
	 }
	 

		/*
		 * This method is used for sending SMS to all members in a group.
		 */
		public void sendSMStoGroup(String message,String[] groupMembersMobileNos){
			
			if(log.isDebugEnabled())
				log.debug(" Inside sendSMStoGroup() Method ......");
			for(int i=0;i<groupMembersMobileNos.length;i++){
				smsCountrySmsService.sendSms(message, true, groupMembersMobileNos[i]);
			}
		}
		
		/*
		 * This method is used for retrieving all the group members in the group.This info is used when displaying the group members details list in a data table  
		 */
		public List<UserGroupMembersVO> getAllMembersIntheGroup(Long registrationId, Long groupId){
			if(log.isDebugEnabled())
				log.debug(" Inside getAllMembersIntheGroup() Method ......");
			List result = null;
			List<UserGroupMembersVO> userGroupMembersVO = null;
			try{
				userGroupMembersVO = new ArrayList<UserGroupMembersVO>(0);
				result =  staticUserGroupDAO.findMembersByUserId(registrationId,groupId);
				for(int i=0;i<result.size();i++){
					UserGroupMembersVO userGroupMembers= new  UserGroupMembersVO();
					Object[] parms = (Object[])result.get(i);
						userGroupMembers.setName(parms[0].toString());
						userGroupMembers.setMobileNumber(parms[1].toString());
						userGroupMembers.setAddress(parms[3].toString());
						if(parms[2] != null){	
						userGroupMembers.setEmailId(parms[2].toString());
						}else {}
						if(parms[4] != null){
						userGroupMembers.setLocation(parms[4].toString());
						}else{}				
						if(parms[5] != null){
							userGroupMembers.setDesignation(parms[5].toString());
						}else{}	
						if(parms[6] != null){
							userGroupMembers.setPhoneNumber(parms[6].toString());
						}else{}
					userGroupMembersVO.add(userGroupMembers);
				}
				return userGroupMembersVO;
			}catch(Exception e){
				e.printStackTrace();
				if(log.isDebugEnabled()){
					log.debug("Exception Raised while Retriving members from the group::", e);
				}
				return null;
			}
		}

	/*
	 * this method is invoked by the getUserGroupDetailsForAUserForMyGroups 
	 */
			
	 @SuppressWarnings("unchecked")
	public GroupsBasicInfoVO getBasicInfoForAGroupForList(List basicList,Long userId){
		 if(log.isDebugEnabled())
			 log.debug("Inside getBasicInfoForAGroupForList Method ...");
		 
		 GroupsBasicInfoVO groupsBasicInfo = null;
		 Long subGrpsCnt = new Long(0);
		 Long membersCount = new Long(0);
		 
		 if(basicList != null){
			 groupsBasicInfo = new GroupsBasicInfoVO();
			 Object[] params = (Object[])basicList.get(0);
			    Long grpId = (Long)params[0];
				String gname = (String)params[1];
				String desc = (String)params[2];
				Date createdDate = (Date)params[3];
				
				groupsBasicInfo.setGroupId(grpId);
				groupsBasicInfo.setGroupName(gname);
				groupsBasicInfo.setDesc(desc);
				if(createdDate != null)
				groupsBasicInfo.setCreatedDate(createdDate.toString());
				List subGrpsCount = personalUserGroupDAO.getSubGroupsCountForMyGroupFromPersonalUserGroup(grpId,userId);
				if(subGrpsCount != null && subGrpsCount.size() > 0){
				Object subCnt = (Object)subGrpsCount.get(0);
				subGrpsCnt = (Long)subCnt;
				groupsBasicInfo.setSubGroupsCount(subGrpsCnt);
				}
				List membrsCnt = staticUserGroupDAO.getGroupMembersCountForAGroup(grpId, userId);
				if(membrsCnt != null && membrsCnt.size() >0){
				Object memParams = (Object)membrsCnt.get(0);
				membersCount = (Long)memParams;
				groupsBasicInfo.setMembersCount(membersCount);	
				}
				
		 }
		 
		 return groupsBasicInfo;
	 }
	 /*
	 * this method is invoked by the getUserGroupDetailsForAUserForMyGroups 
	 */
	 @SuppressWarnings("unchecked")
	public List<GroupsBasicInfoVO> getSubGroupsOfAPersonalUserGroupFromMyGroup(Long userGrpId,Long userId) throws Exception{
		 if(log.isDebugEnabled())
				log.debug(" Inside getSubGroupsOfAPersonalUserGroupFromMyGroup() Method ......");
		 List<GroupsBasicInfoVO> subGroupsList = null;
		 if(userGrpId != null && userId != null){
			 subGroupsList = new ArrayList<GroupsBasicInfoVO>();
			 List subGrpsDetails = personalUserGroupDAO.getSubGroupsCompleteDetailsForMyGroup(userGrpId, userId);
			 if(subGrpsDetails != null && subGrpsDetails.size() > 0){
				 for(int i=0;i<subGrpsDetails.size();i++){
					 Object[] params = (Object[])subGrpsDetails.get(i);
						Long grpId = (Long)params[0];
						String gname = (String)params[1];
						String desc = (String)params[2];
						Date createdDate = (Date)params[3];
						
						GroupsBasicInfoVO groupBasicInfo = new GroupsBasicInfoVO();
						groupBasicInfo.setGroupId(grpId);
						groupBasicInfo.setGroupName(gname);
						groupBasicInfo.setDesc(desc);
						if(createdDate != null)
						groupBasicInfo.setCreatedDate(createdDate.toString());
						groupBasicInfo.setMembersCount(getGroupMembersCount(grpId,userId));
						groupBasicInfo.setSubGroupsCount(getSubGrpsCountForMyGroups(grpId,userId));
						
						subGroupsList.add(groupBasicInfo);
				 }
			 }
		 }
		 
		 return subGroupsList;
	 }
	 /*
	 * this method is invoked by the  getSubGroupsOfAPersonalUserGroupFromMyGroup
	 */
	@SuppressWarnings("unchecked")
	public Long getSubGrpsCountForMyGroups(Long grpId,Long userId) throws Exception{
		if(log.isDebugEnabled())
			log.debug(" Inside getSubGrpsCountForMyGroups() Method ......");
		 Long subGrpsCount = new Long(0);
		 if(grpId != null && userId != null){
			 List subGrpsCnt = personalUserGroupDAO.getSubGroupsCountForMyGroupFromPersonalUserGroup(grpId,userId);
			 if(subGrpsCnt != null && subGrpsCnt.size() > 0){
			 Object subCnt = (Object)subGrpsCnt.get(0);
			 subGrpsCount = (Long)subCnt;
			 }
		 }
		 return subGrpsCount;
	 }
	/*
	 * This method is invoked by the getUserGroupDetailsForAUserForSystemGroups() and  getUserGroupDetailsForAUserForMyGroups() methods to fetch tbe mobile numbers of all
	 * the members of a group whild showing the complete details
	 */
	@SuppressWarnings("unchecked")
	public List<String> getUserGroupMobileNos(Long groupId,Long userId){
		if(log.isDebugEnabled())
		{
			log.debug("Entered in to getUserGroupMobileNos() method");
		}
		List<String> mobileNos = null;
		if(groupId!= null && userId != null){
			List mobNos = staticUserGroupDAO.getGroupMembersMobileNoForAGroup(groupId, userId);
			if(mobNos != null){
				mobileNos = new ArrayList<String>();
				for(int i=0;i<mobNos.size();i++){
					Object params = (Object)mobNos.get(i);
					String mobile = (String)params;
					mobileNos.add(mobile);
				}
			}
		}
		return mobileNos;
	}
	/*
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#getSubGroupsListInSystemGroups(java.lang.Long, java.lang.Long)
	 * This method is will return all the subgroups list in a stem group based on user id.This method will return the parent groups only.
	 * These results are displayed in create new group panel when a user selects a system group from the drop down  
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getSubGroupsListInSystemGroups(Long groupId ,Long userId) {
		if(log.isDebugEnabled())
		{
			log.debug("Entered in to getSubGroupsListInSystemGroups() method");
		}
		List<SelectOptionVO> subGroupsList = new ArrayList<SelectOptionVO>();
		List list = personalUserGroupDAO.getSubGroupsCompleteDetailsForASystemGroup(groupId, userId);
		if(log.isDebugEnabled())
		{
			log.debug("Number of Sub groups in System Groups:" + list.size());
		}
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i< list.size(); i++)
			{
			SelectOptionVO  subGroupDetails = new SelectOptionVO();	
			Object[] params =(Object[])list.get(i);
			subGroupDetails.setId(Long.parseLong(params[0].toString()));
			subGroupDetails.setName(params[1].toString());
			
			subGroupsList.add(subGroupDetails);
			}
		}
		
		return subGroupsList;
	}
	/*
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#getSubGroupsListInSystemGroups(java.lang.Long, java.lang.Long)
	 * This method is will return all the subgroups list in my groups based on user id.
	 * These results are displayed in create new group panel when a user selects a system group from the drop down  
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getSubGroupsListInMyGroups(Long groupId ,Long userId) {
		if(log.isDebugEnabled())
		{
			log.debug("Entered in to getSubGroupsListInMyGroups() method");
		}
		List<SelectOptionVO> subGroupsList = new ArrayList<SelectOptionVO>();
		List list = personalUserGroupDAO.getSubGroupsCompleteDetailsForMyGroup(groupId, userId);
		if(log.isDebugEnabled())
		{
			log.debug("Number of Sub groups in My Groups:" + list.size());
		}		
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i< list.size(); i++)
			{
			SelectOptionVO  subGroupDetails = new SelectOptionVO();	
			Object[] params =(Object[])list.get(i);
			subGroupDetails.setId(Long.parseLong(params[0].toString()));
			subGroupDetails.setName(params[1].toString());
			
			subGroupsList.add(subGroupDetails);
			}
		}
		
		return subGroupsList;		
			}
	/*
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#getSubGroupsListInSystemGroups(java.lang.Long, java.lang.Long)
	 * This method is will return all the subgroups list in my groups based on static group id.
	 * These results are displayed in create new group panel when a user selects a system group from the drop down  
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getSubGroupsOfStaticGroupParents(Long groupId,
			Long userId) {
		if(log.isDebugEnabled())
		{
			log.debug("Entered in to getSubGroupsOfStaticGroupParents() method");
		}
		List<SelectOptionVO> subGroupsList = new ArrayList<SelectOptionVO>();
		List list = personalUserGroupDAO.getSubGroupsCompleteDetailsForASystemGroupFromPersonalUserGroup(groupId, userId);
		if(log.isDebugEnabled())
		{
			log.debug("Number of Sub groups of Static Group Parents:" + list.size());
		}	
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i< list.size(); i++)
			{
			SelectOptionVO  subGroupDetails = new SelectOptionVO();	
			Object[] params =(Object[])list.get(i);
			subGroupDetails.setId(Long.parseLong(params[0].toString()));
			subGroupDetails.setName(params[1].toString());
		
			subGroupsList.add(subGroupDetails);
			}
		}
		
		return subGroupsList;
	}
	/*
	 * this method is used by the user to check the availability of the group name while creation.
	 * if there is no group with the specified name this method returns false, else returns true
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#checkForAvailability(java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean checkForAvailability(Long userId, String groupName) {
		if(log.isDebugEnabled())
		{
			log.debug("Entered In to checkForAvailability!");
		}
		boolean flag;
		List result = personalUserGroupDAO.getGroupsByName(userId, groupName);
		if(Long.parseLong(result.get(0).toString())== 0L)
		{
			flag= false;
		} else {
			flag = true;	
		}		
		return flag;
	}	
}