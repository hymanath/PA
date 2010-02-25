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
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStaticGroupDAO;
import com.itgrids.partyanalyst.dao.IStaticUsersDAO;
import com.itgrids.partyanalyst.dao.IUserGroupPrivilegesDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.model.PersonalUserGroup;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.StaticGroup;
import com.itgrids.partyanalyst.model.StaticUsers;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserGroupService implements IUserGroupService {

	private IStaticGroupDAO staticGroupDAO;
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

	
	public List<UserGroupDetailsVO> getAllSubGroupNames() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public List<UserGroupMembersVO> getMembersNames() {
		// TODO Auto-generated method stub
		return null;
	}


	
	/*
	 * This method takes the UserGroupDetailsVO object which contains the details entered by the user while creating a group.
	 * These details are saved in to the data base.The same value object is returned.
	 */
	public UserGroupDetailsVO createGroupForUser(Long userId, UserGroupDetailsVO userGroupDetailsToSave) {
		this.userGroupDetailsVo = userGroupDetailsToSave;
		if(log.isDebugEnabled()){
			log.debug("Entered UserGroupDetails....");
		}
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			public void doInTransactionWithoutResult(TransactionStatus status)
			{
				UserGroupDetailsVO userGroupDetailsFromDb =new UserGroupDetailsVO();
				Registration reg = null;
				PersonalUserGroup personalUserGroup=null;
				StaticGroup staticGroupObj = null;
				try{	
				reg = registrationDAO.get(UserGroupService.this.userGroupDetailsVo.getCreatedUserId());
				personalUserGroup = new PersonalUserGroup();
				personalUserGroup.setCreatedUserId(reg);
				personalUserGroup.setGroupName(UserGroupService.this.userGroupDetailsVo.getGroupName());
				personalUserGroup.setDescription(UserGroupService.this.userGroupDetailsVo.getGroupDesc());
				staticGroupObj = staticGroupDAO.get(UserGroupService.this.userGroupDetailsVo.getStaticGroupId());
				personalUserGroup.setStaticGroup(staticGroupObj);
				Date createdDate = sdf.parse(UserGroupService.this.userGroupDetailsVo.getCreatedDate());
				personalUserGroup.setCreatedDate(createdDate);
				personalUserGroupDAO.save(personalUserGroup);
				
				userGroupDetailsFromDb.setGroupId(personalUserGroup.getPersonalUserGroupId());
				userGroupDetailsFromDb.setGroupName(personalUserGroup.getGroupName());
				userGroupDetailsFromDb.setGroupDesc(personalUserGroup.getDescription());
				userGroupDetailsFromDb.setStaticGroupId(personalUserGroup.getStaticGroup().getStaticGroupId());
				}catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
			}
				UserGroupService.this.userGroupDetailsVo = userGroupDetailsFromDb;	
		}
		});	
		return this.userGroupDetailsVo;
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
                staticUsers.setPersonalUserGroup(personalUserGroup);
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