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
	private Long userId;
	private Long groupId;
	private Long userMemberId;
	private static final Logger log = Logger.getLogger("UserGroupService.class");
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	//private SmsCountrySmsService smsCountrySmsService;
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
			PersonalUserGroup parentGroup = personalUserGroupDAO.get(parentGroupId); 
			
			personalUserGroup.setParentGroupId(parentGroup);
			personalUserGroup.setParentGroupName(parentGroup.getGroupName());
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
	
	public void addMemberToGroup(final Long groupId, UserGroupMembersVO userGroupMembersToSave) {
		this.userGroupMembersVo=userGroupMembersToSave;
		if(log.isDebugEnabled()){
			log.debug("Entered UserGroup members Details....");
		}
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				public void doInTransactionWithoutResult(TransactionStatus status)
				{
                UserGroupMembersVO userGroupMembersFromDb = new UserGroupMembersVO();
                PersonalUserGroup personalUserGroup=null;
                StaticUsers staticUsers = null;
                 
                try{
                personalUserGroup= personalUserGroupDAO.get(groupId);
                
                staticUsers = new StaticUsers();          
               // staticUsers.setPersonalUserGroup(personalUserGroup);
                staticUsers.setName(UserGroupService.this.userGroupMembersVo.getName());
                staticUsers.setAddress(UserGroupService.this.userGroupMembersVo.getAddress());
                staticUsers.setEmailId(UserGroupService.this.userGroupMembersVo.getEmailId());
                staticUsers.setMobileNumber(UserGroupService.this.userGroupMembersVo.getMobileNumber());
                staticUsers.setDesignation(UserGroupService.this.userGroupMembersVo.getDesignation());
                staticUsers.setLocation(UserGroupService.this.userGroupMembersVo.getLocation());
              //  staticUsersDAO.save(staticUsers);
               
                
                StaticUserGroup staticUserGroup = new StaticUserGroup();
                staticUserGroup.setStaticUser(staticUsers);
                staticUserGroup.setPersonalUserGroup(personalUserGroup);
                staticUserGroup = staticUserGroupDAO.save(staticUserGroup);
                
                           
                //userGroupMembersFromDb.setPersonalUserId(staticUsers.getPersonalUserGroupId().getPersonalUserGroupId());
                userGroupMembersFromDb.setGroupName(staticUsers.getName());
                userGroupMembersFromDb.setAddress(staticUsers.getAddress());
                userGroupMembersFromDb.setEmailId(staticUsers.getEmailId());
                userGroupMembersFromDb.setMobileNumber(staticUsers.getMobileNumber());
                userGroupMembersFromDb.setDesignation(staticUsers.getDesignation());
                userGroupMembersFromDb.setLocation(staticUsers.getLocation());
                
          	//	return this.userGroupMembersVo;        
				}
                catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Getting Groups::", e);
					}
			//		return null;
				}
				UserGroupService.this.userGroupMembersVo = userGroupMembersFromDb ;	

				}
			});				

		}
	
	//sai
	/*
	 * Method Returns Complete Group Details For a Particular Group and userId
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
	 * Returns Basic Details For A System Group
	 */
	@SuppressWarnings("unchecked")
	public GroupsBasicInfoVO getCompleteDetailsOfAStaticCategoryGroup(StaticGroup staticGrp,Long userId) throws Exception{
		
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
	
	@SuppressWarnings("unchecked")
	public List<GroupsBasicInfoVO> getSubGroupsOfAStaticCategoryGroup(StaticGroup staticGrp,Long userId) throws Exception{
		
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
	
	@SuppressWarnings("unchecked")
	public GroupsBasicInfoVO getCompleteDetailsOfAPersonalUserGroup(PersonalUserGroup personalUserGrp,Long userId) throws Exception{
		
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
	
	@SuppressWarnings("unchecked")
	public List<GroupsBasicInfoVO> getSubGroupsOfAPersonalUserGroup(PersonalUserGroup personalUserGrp,Long userId) throws Exception{
		
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
	
	@SuppressWarnings("unchecked")
	public Long getSubGroupsCount(Long groupId,Long userId) throws Exception{
		
		log.debug("Inside getSubGroupsCount Method ..");
		
		Long subGroupsCount = null;
		List persnlGrpsCount = personalUserGroupDAO.getSubGroupsCountForASystemGroupFromPersonalUserGroup(groupId,userId);
		if(persnlGrpsCount != null && persnlGrpsCount.size() > 0){
			Object params = (Object)persnlGrpsCount.get(0);
			subGroupsCount = (Long)params;
		}
		
		return subGroupsCount;
	}
	
	@SuppressWarnings("unchecked")
	public Long getGroupMembersCount(Long groupId,Long userId) throws Exception{
		
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
			log.debug("Exception Raised ---->" + ex);
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			userGroupBasicDetails.setResultStatus(resultStatus);
		 }
		 
		 return userGroupBasicDetails;
	 }
	 

	 /*
		 * This method is used for sending SMS to the members.
		 */
		public void sendSMStoGroup(String message,String[] groupMembersMobileNos){
			
			
			for(int i=0;i<groupMembersMobileNos.length;i++){
				smsCountrySmsService.sendSms(message, true, groupMembersMobileNos[i]);
			}
		}
		
		/*
		 * This method is used for retrieving all the group members in the group. 
		 */
		public List<UserGroupMembersVO> getAllMembersIntheGroup(Long registrationId, Long groupId){
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

	 @SuppressWarnings("unchecked")
	public GroupsBasicInfoVO getBasicInfoForAGroupForList(List basicList,Long userId){
		 
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
	 @SuppressWarnings("unchecked")
	public List<GroupsBasicInfoVO> getSubGroupsOfAPersonalUserGroupFromMyGroup(Long userGrpId,Long userId) throws Exception{
		 
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
	 
	@SuppressWarnings("unchecked")
	public Long getSubGrpsCountForMyGroups(Long grpId,Long userId) throws Exception{
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
	
	@SuppressWarnings("unchecked")
	public List<String> getUserGroupMobileNos(Long groupId,Long userId){
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

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getSubGroupsListInSystemGroups(Long groupId ,Long userId) {
		List<SelectOptionVO> subGroupsList = new ArrayList<SelectOptionVO>();
		List list = personalUserGroupDAO.getSubGroupsCompleteDetailsForASystemGroup(groupId, userId);
		System.out.println(list.size());
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

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getSubGroupsListInMyGroups(Long groupId ,Long userId) {
		List<SelectOptionVO> subGroupsList = new ArrayList<SelectOptionVO>();
		List list = personalUserGroupDAO.getSubGroupsCompleteDetailsForMyGroup(groupId, userId);
		System.out.println(list.size());
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

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getSubGroupsOfStaticGroupParents(Long groupId,
			Long userId) {
		List<SelectOptionVO> subGroupsList = new ArrayList<SelectOptionVO>();
		List list = personalUserGroupDAO.getSubGroupsCompleteDetailsForASystemGroupFromPersonalUserGroup(groupId, userId);
		System.out.println(list.size());
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
	
	
	 
	 

}