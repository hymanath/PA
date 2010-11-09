package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IEntitlementDAO;
import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IGroupEntitlementRelationDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IUserGroupRelationDAO;
import com.itgrids.partyanalyst.dao.IUserGroupsDAO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.GroupEntitlement;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;
import com.itgrids.partyanalyst.model.UserGroupRelation;
import com.itgrids.partyanalyst.service.IUserEntitlementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserEntitlementService implements IUserEntitlementService {

	private IEntitlementDAO entitlementDAO;
	private IGroupEntitlementDAO groupEntitlementDAO;
	private IGroupEntitlementRelationDAO groupEntitlementRelationDAO;
	private IUserGroupEntitlementDAO userGroupEntitlementDAO;
	private IUserGroupsDAO userGroupsDAO;
	private IUserGroupRelationDAO userGroupRelationDAO;
	private IRegistrationDAO registrationDAO;
	private final static Logger log = Logger.getLogger(UserEntitlementService.class);
	

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	public IUserGroupRelationDAO getUserGroupRelationDAO() {
		return userGroupRelationDAO;
	}
	public void setUserGroupRelationDAO(IUserGroupRelationDAO userGroupRelationDAO) {
		this.userGroupRelationDAO = userGroupRelationDAO;
	}
	public IUserGroupsDAO getUserGroupsDAO() {
		return userGroupsDAO;
	}
	public void setUserGroupsDAO(IUserGroupsDAO userGroupsDAO) {
		this.userGroupsDAO = userGroupsDAO;
	}
	public IUserGroupEntitlementDAO getUserGroupEntitlementDAO() {
		return userGroupEntitlementDAO;
	}
	public void setUserGroupEntitlementDAO(
			IUserGroupEntitlementDAO userGroupEntitlementDAO) {
		this.userGroupEntitlementDAO = userGroupEntitlementDAO;
	}
	public IGroupEntitlementRelationDAO getGroupEntitlementRelationDAO() {
		return groupEntitlementRelationDAO;
	}
	public void setGroupEntitlementRelationDAO(
			IGroupEntitlementRelationDAO groupEntitlementRelationDAO) {
		this.groupEntitlementRelationDAO = groupEntitlementRelationDAO;
	}
	public IEntitlementDAO getEntitlementDAO() {
		return entitlementDAO;
	}
	public void setEntitlementDAO(IEntitlementDAO entitlementDAO) {
		this.entitlementDAO = entitlementDAO;
	}
	public IGroupEntitlementDAO getGroupEntitlementDAO() {
		return groupEntitlementDAO;
	}
	public void setGroupEntitlementDAO(IGroupEntitlementDAO groupEntitlementDAO) {
		this.groupEntitlementDAO = groupEntitlementDAO;
	}
	
	/**
	 * This method can be used to get all the groups.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 */
	public EntitlementVO getAllGroups(){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> setOfGroups;
		try{
			setOfGroups = new ArrayList<SelectOptionVO>(0);			
			List result = groupEntitlementDAO.getAllGroups();
			if(result!=null){
				if(result.size()!=0){
					for(int i=0;i<result.size();i++){
						Object[] parms = (Object[])result.get(i); 
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName((String)parms[1]);
						setOfGroups.add(selectOptionVO);
					}
				}
			}
			entitlementVO.setSetOfGroups(setOfGroups);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);			
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;
	}

	
	
	/**
	 * This method can be used to get all the groups where each group is a list of entitlements
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 */
	public EntitlementVO getAllEntitlements(){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> listOfEntitlements;
		try{
			listOfEntitlements= new ArrayList<SelectOptionVO>(0);
			List result = entitlementDAO.getAllEntitlements();
			if(result!=null){
				if(result.size()!=0){
					for(int i=0;i<result.size();i++){
						Object[] parms = (Object[])result.get(i); 
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName((String)parms[1]);
						listOfEntitlements.add(selectOptionVO);
					}
				}
			}			
			entitlementVO.setListOfEntitlements(listOfEntitlements);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);			
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;
	}
	
	
	/**
	 * This method can be used to create a group.
	 * This method internally checks whether the group is available or not if the group is available
	 * it creates the group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @param groupName
	 * @return EntitlementVO 
	 */
	public EntitlementVO creatingAGroup(String groupName,String assignedEntitlementIds){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			EntitlementVO resultOfAvailability = checkAvailabilityOfGroup(groupName);
			if(resultOfAvailability.getResultStatus()!=null){
				throw new Exception(resultOfAvailability.getResultStatus().getExceptionEncountered());
			}else{				
				if(resultOfAvailability.getMessage().equalsIgnoreCase(IConstants.NOT_AVAILABLE)){
					entitlementVO.setMessage(IConstants.NOT_AVAILABLE);
				}else{					
					GroupEntitlement groupEntitlement = new GroupEntitlement();
					groupEntitlement.setDescription(groupName);
					groupEntitlement = groupEntitlementDAO.save(groupEntitlement);
					entitlementVO.setMessage(IConstants.AVAILABLE);
					if(assignedEntitlementIds.length()!=0){
						List assignedIds = Arrays.asList(assignedEntitlementIds.split(","));
						for(int i=0;i<assignedIds.size();i++){
							GroupEntitlementRelation relation = new GroupEntitlementRelation();
							relation.setEntitlement(entitlementDAO.get(new Long(assignedIds.get(i).toString())));
							relation.setGroupEntitlement(groupEntitlement);
							groupEntitlementRelationDAO.save(relation);
						}
						
					}
				}
				entitlementVO.setName(groupName);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);				
			}			
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;
	}
	
	/**
	 * This method can be used to check the availability to create a new group.
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 */
	public EntitlementVO checkAvailabilityOfGroup(String name){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<GroupEntitlement> result = groupEntitlementDAO.checkForGroupNameAvailability(name);
			if(result!= null){				
				entitlementVO.setMessage(result.size()==0 ? IConstants.AVAILABLE : IConstants.NOT_AVAILABLE);
			}
			entitlementVO.setName(name);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;		
	}
	

	/**
	 * This method can be used to check the availability to create a new Entitlement.
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 */
	public EntitlementVO checkAvailabilityOfEntitlement(String name){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Entitlement> result = entitlementDAO.checkForEntitlementAvailability(name);
			if(result!= null){				
				entitlementVO.setMessage(result.size()==0 ? IConstants.AVAILABLE : IConstants.NOT_AVAILABLE);
			}
			entitlementVO.setName(name);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;		
	}
	
	/**
	 * This method can be used to create a group.
	 * This method internally checks whether the group is available or not if the group is available
	 * it creates the group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @param groupName
	 * @return EntitlementVO 
	 */
	public EntitlementVO creatingAnEntitlement(String name){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			EntitlementVO resultOfAvailability = checkAvailabilityOfEntitlement(name);
			if(resultOfAvailability.getResultStatus()!=null){
				throw new Exception(resultOfAvailability.getResultStatus().getExceptionEncountered());
			}else{				
				if(resultOfAvailability.getMessage().equalsIgnoreCase(IConstants.NOT_AVAILABLE)){
					entitlementVO.setMessage(IConstants.NOT_AVAILABLE);
				}else{					
					Entitlement entitlement = new Entitlement();
					entitlement.setEntitlementType(name);
					entitlementDAO.save(entitlement);
					entitlementVO.setMessage(IConstants.AVAILABLE);
				}
				entitlementVO.setName(name);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);				
			}			
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;
	}
	
	/**
	 * This method can be used to get all the groups where each group is a list of entitlements
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 */
	public EntitlementVO getAllDetailsOfAGroup(Long groupId,String name){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> listOfEntitlements;
		try{
			listOfEntitlements= new ArrayList<SelectOptionVO>(0);
			List result =  groupEntitlementRelationDAO.getAllEntitlementsForAGroupByGroupId(groupId);
			if(result.size()!=0){
				for(int i=0;i<result.size();i++){
					{
						Object[] entitlement = (Object[])result.get(i);
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)entitlement[0]);
						selectOptionVO.setName(entitlement[1].toString());
						listOfEntitlements.add(selectOptionVO);				
					}
				}
			}
			entitlementVO.setListOfEntitlements(listOfEntitlements);
			entitlementVO.setName(name);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);		
		
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}finally{
			System.gc();
		}
		return entitlementVO;
	}
	

	
	/**
	 * This method can be used to get all the groups.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 */
	public EntitlementVO getAllUserGroups(){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> setOfGroups;
		try{
			setOfGroups = new ArrayList<SelectOptionVO>(0);			
			List result = userGroupsDAO.getAllUserGroups();
			if(result!=null){
				if(result.size()!=0){
					for(int i=0;i<result.size();i++){
						Object[] parms = (Object[])result.get(i); 
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName((String)parms[1]);
						setOfGroups.add(selectOptionVO);
					}
				}
			}
			entitlementVO.setSetOfGroups(setOfGroups);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);			
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}finally{
			System.gc();
		}
		return entitlementVO;
	}

	/**
	 * This method can be used to save the relation between the user and the user group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @param userId
	 * @param groupId
	 * @return EntitlementVO 
	 */
	public EntitlementVO saveUserGroupsRelation(Long userId,String groupIds){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		int j=0;
		List<String> elements = null;
		Map<Long,Long> userIdsAndGroupIds = new HashMap<Long,Long>(0);
		try{
			if(groupIds.length()!=0){
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(groupIds).split(","))));	
				List result = userGroupRelationDAO.checkTheRelationBetweenUserAndGroup(userId);
			    if(result!=null && result.size()!=0){
			    	for(int i=0;i<result.size();i++){
			    		Object[] parms = (Object[])result.get(i); 
			    		userIdsAndGroupIds.put((Long)parms[0], (Long)parms[1]);	
			    	}
			    	
			    }
			    
				for(int i=0;i<elements.size();i++){
					Long id = new Long(elements.get(i));
					if(!userIdsAndGroupIds.containsKey(id)){
						j++;
						UserGroupRelation relation = new UserGroupRelation();
						relation.setUser(registrationDAO.get(userId));
						relation.setUserGroup(userGroupsDAO.get(id));
						relation = userGroupRelationDAO.save(relation);	
					}
				}				
				if(j!=0){
					entitlementVO.setMessage(IConstants.SUCCESSFULLY_SAVED);	
				}else{
					entitlementVO.setMessage("User already present in the same group");	
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);			
			entitlementVO.setResultStatus(resultStatus);
		}finally{
			userIdsAndGroupIds = null;
			userId = null;
			groupIds = null;
			elements = null;
			System.gc();
		}
		return entitlementVO;
	}

	/**
	 * This method can be used to get all the entitlements for a user group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @param userId
	 * @param groupId
	 * @return EntitlementVO 
	 */
	public EntitlementVO getAllEntitlementsForAUserGroup(Long userGroupId){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> seleList = new ArrayList<SelectOptionVO>(); 
		try{
			List result = groupEntitlementRelationDAO.getAllEntitlementsBasedOnUserGroupId(userGroupId);
			if(result.size()!=0){
				for(int i=0;i<result.size();i++){
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					Object[] parms = (Object[])result.get(i);
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					seleList.add(selectOptionVO);
				}				
				entitlementVO.setListOfEntitlements(seleList);
				entitlementVO.setMessage("Data Available");
			}else{
				entitlementVO.setMessage("Data not available");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);			
			entitlementVO.setResultStatus(resultStatus);
		}finally{
			System.gc();
		}
		return entitlementVO;
	}
	
	
	
}
