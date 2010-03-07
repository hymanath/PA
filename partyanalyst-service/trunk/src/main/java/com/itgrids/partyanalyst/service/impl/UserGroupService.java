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
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IMyGroupDAO;
import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStaticGroupDAO;
import com.itgrids.partyanalyst.dao.IStaticUsersDAO;
import com.itgrids.partyanalyst.dao.IUserGroupPrivilegesDAO;
import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.model.MyGroup;
import com.itgrids.partyanalyst.model.PersonalUserGroup;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.StaticGroup;
import com.itgrids.partyanalyst.model.StaticUsers;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserGroupService implements IUserGroupService {

	private IStaticGroupDAO staticGroupDAO;
	private IMyGroupDAO myGroupDAO;
	private IPersonalUserGroupDAO personalUserGroupDAO;
	private IStaticUsersDAO staticUsersDAO;
	private IRegistrationDAO registrationDAO;	
	private TransactionTemplate transactionTemplate;
	private UserGroupDetailsVO userGroupDetailsVo;
	private UserGroupMembersVO userGroupMembersVo;
	private Long userId;
	private Long groupId;
	private Long userMemberId;
	private static final Logger log = Logger.getLogger("UserGroupService.class");
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	
	
	public IStaticGroupDAO getStaticGroupDAO() {
		return staticGroupDAO;
	}

	public void setStaticGroupDAO(IStaticGroupDAO staticGroupDAO) {
		this.staticGroupDAO = staticGroupDAO;
	}

	public IPersonalUserGroupDAO getPersonalUserGroupDAO() {
		return personalUserGroupDAO;
	}

	public void setPersonalUserGroupDAO(IPersonalUserGroupDAO personalUserGroupDAO) {
		this.personalUserGroupDAO = personalUserGroupDAO;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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

	public Long getUserMemberId() {
		return userMemberId;
	}

	public void setUserMemberId(Long userMemberId) {
		this.userMemberId = userMemberId;
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

	public IMyGroupDAO getMyGroupDAO() {
		return myGroupDAO;
	}

	public void setMyGroupDAO(IMyGroupDAO myGroupDAO) {
		this.myGroupDAO = myGroupDAO;
	}

	/**
	 * To Get The List Of All Static groups From DB Into SelectOptionVO
	 * @return
	 */	
	public List<SelectOptionVO> getAllStaticGroupNames() {
		List<SelectOptionVO> staticGroups = new ArrayList<SelectOptionVO>();
		List<StaticGroup> list= staticGroupDAO.getAll();
		for(StaticGroup staticGroup:list)
			staticGroups.add(new SelectOptionVO(staticGroup.getStaticGroupId(), staticGroup.getGroupName()));
		return staticGroups;		
	}
	/**
	 * To Get The List Of All My groups From DB Into SelectOptionVO
	 * @return
	 */
	public List<SelectOptionVO> getAllMyGroupsCreatedByUser(Long userId) {
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
			log.debug("Entered in service method....");
		}
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			public void doInTransactionWithoutResult(TransactionStatus status)
			{
				UserGroupDetailsVO userGroupDetailsFromDb =new UserGroupDetailsVO();
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
				System.out.println("groupStatus:======" + groupStatus);
				String categoryType = UserGroupService.this.userGroupDetailsVo.getCategoryType();
				System.out.println("categoryType:=====" + categoryType);
				if(groupStatus.equals(IConstants.MAIN_GROUP))					
				{	
					System.out.println("If Main Group");
					myGroupObj = new MyGroup();
					reg = registrationDAO.get(UserGroupService.this.userGroupDetailsVo.getCreatedUserId());
					//createMainGroupInMyGroup(userGroupDetailsToSave);
					myGroupObj.setGroupName(UserGroupService.this.userGroupDetailsVo.getGroupName());
					myGroupObj.setGroupDescription(UserGroupService.this.userGroupDetailsVo.getGroupDesc());
					myGroupObj.setCreatedDate(now);
					//myGroupObj = myGroupDAO.save(myGroupObj);
					
					personalUserGroup = new PersonalUserGroup();
					personalUserGroup.setGroupName(UserGroupService.this.userGroupDetailsVo.getGroupName());
					personalUserGroup.setDescription(UserGroupService.this.userGroupDetailsVo.getGroupDesc());
					personalUserGroup.setCreatedUserId(reg);
					personalUserGroup.setCreatedDate(now);
					personalUserGroup.setMyGroup(myGroupObj);
					
					personalUserGroupDAO.save(personalUserGroup);
					
					
				} else if(groupStatus.equals(IConstants.SUB_GROUP) && categoryType.equals(IConstants.STATIC_GROUP))
				{	
					System.out.println("If sub group in static Group");
					System.out.println("Inside if condition");
					createSubGroupUnderStaticGroups(userGroupDetailsVo);
				} else if(groupStatus.equals(IConstants.SUB_GROUP) && categoryType.equals(IConstants.MY_GROUP))
				{
					System.out.println("If sub group in My Group");
					createSubGroupUnderMyGroups(userGroupDetailsVo);
				}
				/*				
				userGroupDetailsFromDb.setGroupId(personalUserGroup.getPersonalUserGroupId());
				userGroupDetailsFromDb.setGroupName(personalUserGroup.getGroupName());
				userGroupDetailsFromDb.setGroupDesc(personalUserGroup.getDescription());
				System.out.println(personalUserGroup.getStaticGroup().getStaticGroupId());
				if(personalUserGroup.getStaticGroup().getStaticGroupId()!= null)
				{
					userGroupDetailsFromDb.setStaticGroupId(personalUserGroup.getStaticGroup().getStaticGroupId());
				}
				else
				{
					userGroupDetailsFromDb.setStaticGroupId(null);
				}*/
				
				}catch(Exception e){
					
					e.printStackTrace();
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
			}
				UserGroupService.this.userGroupDetailsVo = userGroupDetailsFromDb;	
		}
		});	
		return null;
	}
	
	public UserGroupDetailsVO createSubGroupUnderStaticGroups(UserGroupDetailsVO userGroupDetailsToSave) throws Exception
	{
		StaticGroup staticGroupObj = null;
		PersonalUserGroup personalUserGroup= new PersonalUserGroup();
		Registration reg = null;
		java.util.Date now = new java.util.Date();
		String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String strDateNew = sdf.format(now) ;
	    now = sdf.parse(strDateNew);
	    Long parentGroupId = userGroupDetailsToSave.getParentGroupId();
	    System.out.println("parentGroupId:======" + parentGroupId);
		reg = registrationDAO.get(userGroupDetailsToSave.getCreatedUserId());
		staticGroupObj = staticGroupDAO.get(new Long(userGroupDetailsToSave.getStaticGroupId()));
		personalUserGroup.setGroupName(userGroupDetailsToSave.getGroupName());
		personalUserGroup.setDescription(userGroupDetailsToSave.getGroupDesc());
		personalUserGroup.setStaticGroup(staticGroupObj);
		if(parentGroupId != null)
		{
			System.out.println("Inside sub group method in if");
			personalUserGroup = personalUserGroupDAO.get(parentGroupId);
			personalUserGroup.setParentGroupId(personalUserGroup);
			personalUserGroup.setParentGroupName(personalUserGroup.getGroupName());
		}else
		{
			personalUserGroup.setParentGroupId(null);
			personalUserGroup.setParentGroupName(null);
		}		
		personalUserGroup.setCreatedUserId(reg);
		personalUserGroup.setCreatedDate(now);
		
		personalUserGroupDAO.save(personalUserGroup);
		System.out.println("After Save");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public UserGroupDetailsVO createSubGroupUnderMyGroups(UserGroupDetailsVO userGroupDetailsToSave) throws Exception
	{
		
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
	    System.out.println("parentGroupId:======" + parentGroupId);
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
			System.out.println("Inside sub group method in if");
			personalUserGroup = personalUserGroupDAO.get(parentGroupId);
			personalUserGroup.setParentGroupId(personalUserGroup);
			personalUserGroup.setParentGroupName(personalUserGroup.getGroupName());
		}else
		{
			parentGroupObj = personalUserGroupDAO.get(userGroupDetailsToSave.getMyGroupId());
			personalUserGroup.setParentGroupId(parentGroupObj);
			personalUserGroup.setParentGroupName(parentGroupObj.getGroupName());
		}		
		personalUserGroup.setCreatedUserId(reg);
		personalUserGroup.setCreatedDate(now);
		
		personalUserGroupDAO.save(personalUserGroup);
		System.out.println("After Save");
		return null;
		
	}
	
	/*
	 * 
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#systemGroupsDetailsForUser(java.lang.Long)
	 * this method returns the sub groups count under static groups for a user
	 */
	public List <GroupsDetailsForUserVO> subGrpsCountInSystemGrpsForUser(Long userId)
	{
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
	//		systemGroupsDetailsForUserList.add(new GroupsDetailsForUserVO(Long.parseLong(parms[0].toString()),parms[1].toString(),Long.parseLong(parms[2].toString())));
		}		
		return systemGroupsDetailsForUserList;
	}
	/*
	 * this method returns the MyGroups(created by user other than system groups) and the number of sub groups(if any) in the top level group. 
	 * @see com.itgrids.partyanalyst.service.IUserGroupService#myGroupsDetailsForUser(java.lang.Long)
	 */
	
	@SuppressWarnings("unchecked")
	public List<GroupsDetailsForUserVO> subGroupsCountInMyGroupsForUser(Long userId)
	{
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
			System.out.println("SubGroups ... in VO" + groupVO.getNumberOfGroups());
			} else			
			groupVO.setNumberOfGroups(new Long(0));
			System.out.println("SubGroups ... in VO" + groupVO.getNumberOfGroups());
			groupsList.add(groupVO);
		}		
		return groupsList;
	}
	
	/*
	 * This method takes the UserGroupMembersVO object which contains the details entered by the user while creating a group.
	 * These details are saved in to the data base.The same value object is returned.
	 */
	
	public UserGroupMembersVO addMemberToGroup(Long groupId,
			UserGroupMembersVO userGroupMembersToSave) {
		this.userGroupMembersVo=userGroupMembersToSave;
		if(log.isDebugEnabled()){
			log.debug("Entered UserGroup members Details....");
		}
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				public void doInTransactionWithoutResult(TransactionStatus status)
				{
                UserGroupMembersVO userGroupMembersFromDb = new UserGroupMembersVO();
                PersonalUserGroup personalUserGroup=null;
                StaticUsers staticUsers=null;
                try{
                personalUserGroup= personalUserGroupDAO.get(UserGroupService.this.userGroupMembersVo.getGroupMemberId());
                staticUsers=new StaticUsers();
               // staticUsers.setPersonalUserGroup(personalUserGroup);
                staticUsers.setName(UserGroupService.this.userGroupMembersVo.getGroupName());
                staticUsers.setAddress(UserGroupService.this.userGroupMembersVo.getAddress());
                staticUsers.setEmailId(UserGroupService.this.userGroupMembersVo.getEmailId());
                staticUsers.setMobileNumber(UserGroupService.this.userGroupMembersVo.getMobileNumber());
                staticUsers.setDesignation(UserGroupService.this.userGroupMembersVo.getDesignation());
                staticUsers.setLocation(UserGroupService.this.userGroupMembersVo.getLocation());
                staticUsersDAO.save(staticUsers);
                //userGroupMembersFromDb.setPersonalUserId(staticUsers.getPersonalUserGroupId().getPersonalUserGroupId());
                userGroupMembersFromDb.setGroupName(staticUsers.getName());
                userGroupMembersFromDb.setAddress(staticUsers.getAddress());
                userGroupMembersFromDb.setEmailId(staticUsers.getEmailId());
                userGroupMembersFromDb.setMobileNumber(staticUsers.getMobileNumber());
                userGroupMembersFromDb.setDesignation(staticUsers.getDesignation());
                userGroupMembersFromDb.setLocation(staticUsers.getLocation());
                
				}
                catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
				}
				UserGroupService.this.userGroupMembersVo = userGroupMembersFromDb ;	

				}
			});				
		return this.userGroupMembersVo;
		}	
	
	

}