package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IUserEntitlementService;

public class UserEntitlementService implements IUserEntitlementService {/*

	private IEntitlementDAO entitlementDAO;
	private IGroupEntitlementDAO groupEntitlementDAO;
	private IGroupEntitlementRelationDAO groupEntitlementRelationDAO;
	private IUserGroupEntitlementDAO userGroupEntitlementDAO;
	private IUserGroupsDAO userGroupsDAO;
	private IUserGroupRelationDAO userGroupRelationDAO;
	private IUserDAO userDAO;
	private final static Logger log = Logger.getLogger(UserEntitlementService.class);
	

	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
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
	
	*//**
	 * This method can be used to get all the entitlement groups.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 *//*
	public EntitlementVO getAllGroups(){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> setOfGroups;
		try{
			setOfGroups = new ArrayList<SelectOptionVO>(0);	
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(0l);
			selectOption.setName("select a entitlement group");
			setOfGroups.add(selectOption);
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
			entitlementVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}finally{		
			setOfGroups = null;			
		}
		return entitlementVO;
	}

	
	
	*//**
	 * This method can be used to get all the groups where each group is a list of entitlements
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 *//*
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
		}finally{		
			listOfEntitlements = null;			
		}
		return entitlementVO;
	}
	
	
	*//**
	 * This method can be used to create a group.
	 * This method internally checks whether the group is available or not if the group is available
	 * it creates the group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @param groupName
	 * @return EntitlementVO 
	 *//*
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
	
	*//**
	 * This method can be used to check the availability to create a new group.
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 *//*
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
	

	*//**
	 * This method can be used to check the availability to create a new Entitlement.
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 *//*
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
	
	
	*//**
	 * This method can be used to check the availability to create a new user group.
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 *//*
	public EntitlementVO checkForUserGroupNameAvailability(String name){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Entitlement> result = userGroupsDAO.checkForUserAvailability(name);
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
	
	*//**
	 * This method can be used to create a group.
	 * This method internally checks whether the group is available or not if the group is available
	 * it creates the group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 10-11-10
	 * @param groupName
	 * @return EntitlementVO 
	 *//*
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
	
	*//**
	 * This method can be used to create a group.
	 * This method internally checks whether the group is available or not if the group is available
	 * it creates the group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 10-11-10
	 * @param groupName
	 * @return EntitlementVO 
	 *//*
	public EntitlementVO creatingAUserGroup(String name){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			EntitlementVO resultOfAvailability = checkForUserGroupNameAvailability(name);
			if(resultOfAvailability.getResultStatus()!=null){
				throw new Exception(resultOfAvailability.getResultStatus().getExceptionEncountered());
			}else{				
				if(resultOfAvailability.getMessage().equalsIgnoreCase(IConstants.NOT_AVAILABLE)){
					entitlementVO.setMessage(IConstants.NOT_AVAILABLE);
				}else{					
					UserGroups userGroups = new UserGroups();
					userGroups.setNotes(name);
					userGroupsDAO.save(userGroups);
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
	
	*//**
	 * This method can be used to get all the groups where each group is a list of entitlements
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 *//*
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
		}
		return entitlementVO;
	}
	

	
	*//**
	 * This method can be used to get all the groups.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 *//*
	public EntitlementVO getAllUserGroups(){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> setOfGroups;
		try{
			setOfGroups = new ArrayList<SelectOptionVO>(0);	
			
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(0l);
			selectOption.setName("select a user group");
			setOfGroups.add(selectOption);
			
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
		}
		return entitlementVO;
	}

	*//**
	 * This method can be used to save the relation between the user and the user group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @param userId
	 * @param groupId
	 * @return EntitlementVO 
	 *//*
	public EntitlementVO saveUserGroupsRelation(Long userId,String groupIds){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		int j=0;
		List<String> elements = null;
		Map<Long,Long> userIdsAndGroupIds = new HashMap<Long,Long>(0);
		try{
			userGroupRelationDAO.deleteAllUser(userId);
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
						relation.setUser(userDAO.get(userId));
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
		}
		return entitlementVO;
	}

	*//**
	 * This method can be used to get all the entitlements for a user group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @param userId
	 * @param groupId
	 * @return EntitlementVO 
	 *//*
	public EntitlementVO getAllEntitlementsForAUserGroup(Long userGroupId,String name){
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
			entitlementVO.setName(name);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);			
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;
	}
	
	*//**
	 * This method can be used to get all the groups and checks which user
	 * is present in the user group.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @return NavigationVO 
	 *//*
	public NavigationVO getAllGroupsBasedOnUserId(Long userId){
		
		NavigationVO navigationVO= new NavigationVO();		
		List<EntitlementVO> entitlementVO = new ArrayList<EntitlementVO>();		
		Map<Long,EntitlementVO> allGroups = new HashMap<Long,EntitlementVO>();		
		ResultStatus resultStatus = new ResultStatus();
		Long availableCount=0l;
		try{			
			List result = userGroupsDAO.getAllUserGroups();
			if(result!=null){
				if(result.size()!=0){
					for(int i=0;i<result.size();i++){
						Object[] parms = (Object[])result.get(i); 
						EntitlementVO eVo = new EntitlementVO();
						eVo.setUserId((Long)parms[0]);
						eVo.setName((String)parms[1]);
						eVo.setMessage(IConstants.NOT_AVAILABLE);
						allGroups.put((Long)parms[0],eVo);						
					}
				}
			}
			
			List list2 = userGroupRelationDAO.checkTheRelationBetweenUserAndGroup(userId);
			if(list2!=null){
				if(list2.size()!=0){
					for(int i=0;i<list2.size();i++){
						Object[] parms = (Object[])list2.get(i);
						Long id = (Long)parms[0];
						EntitlementVO eVo = new EntitlementVO();
						if(allGroups.containsKey(id)){
							eVo = allGroups.get(id);
							allGroups.remove(id);						
							eVo.setUserId(id);
							eVo.setName(eVo.getName());
							eVo.setMessage(IConstants.AVAILABLE);
							availableCount++;
							allGroups.put(id,eVo);								
						}						
					}
				}
			}
			
			for(Map.Entry<Long, EntitlementVO> res : allGroups.entrySet()){
				entitlementVO.add(res.getValue());
			}
			navigationVO.setCount(availableCount);
			navigationVO.setEntitlementVO(entitlementVO);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			navigationVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			navigationVO.setResultStatus(resultStatus);
		}
		return navigationVO;
	}
	

	
	*//**
	 * This method can be used to get all the entitlements based on the entitlement group.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @return NavigationVO 
	 *//*
	public NavigationVO getAllEntitlementsBasedOnEntitlementGroup(Long groupId){
		
		NavigationVO navigationVO= new NavigationVO();		
		List<EntitlementVO> entitlementVO = new ArrayList<EntitlementVO>();		
		Map<Long,EntitlementVO> allGroups = new HashMap<Long,EntitlementVO>();		
		ResultStatus resultStatus = new ResultStatus();
		Long availableCount=0l;
		try{			
			List result = entitlementDAO.getAllEntitlements();
			if(result!=null){
				if(result.size()!=0){
					for(int i=0;i<result.size();i++){
						Object[] parms = (Object[])result.get(i); 
						EntitlementVO eVo = new EntitlementVO();
						eVo.setUserId((Long)parms[0]);
						eVo.setName((String)parms[1]);
						eVo.setMessage(IConstants.NOT_AVAILABLE);
						allGroups.put((Long)parms[0],eVo);						
					}
				}
			}
			
			List list2 = groupEntitlementRelationDAO.getAllEntitlementsForAGroupByGroupId(groupId);
			if(list2!=null){
				if(list2.size()!=0){
					for(int i=0;i<list2.size();i++){
						Object[] parms = (Object[])list2.get(i);
						Long id = (Long)parms[0];
						EntitlementVO eVo = new EntitlementVO();
						if(allGroups.containsKey(id)){
							eVo = allGroups.get(id);
							allGroups.remove(id);						
							eVo.setUserId(id);
							eVo.setName(eVo.getName());
							eVo.setMessage(IConstants.AVAILABLE);
							availableCount++;
							allGroups.put(id,eVo);								
						}						
					}
				}
			}
			
			for(Map.Entry<Long, EntitlementVO> res : allGroups.entrySet()){
				entitlementVO.add(res.getValue());
			}
			navigationVO.setCount(availableCount);
			navigationVO.setEntitlementVO(entitlementVO);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			navigationVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			navigationVO.setResultStatus(resultStatus);
		}
		return navigationVO;
	}
	
	*//**
	 * This method can be used to save the relation between the user and the user group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @param userId
	 * @param groupId
	 * @return EntitlementVO 
	 *//*
	public EntitlementVO saveRelationBetweenEntitlementGroupAndEntitlement(Long groupId,String entitlementIds){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<String> elements = null;		
		try{
			groupEntitlementRelationDAO.deleteAllRelations(groupId);
			if(entitlementIds.length()!=0){
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(entitlementIds).split(","))));	
							    
				for(int i=0;i<elements.size();i++){
					Long id = new Long(elements.get(i));
						GroupEntitlementRelation relation = new GroupEntitlementRelation();
						relation.setEntitlement(entitlementDAO.get(id));
						relation.setGroupEntitlement(groupEntitlementDAO.get(groupId));
						relation = groupEntitlementRelationDAO.save(relation);	
					}
				}	
		entitlementVO.setMessage(IConstants.SUCCESSFULLY_SAVED);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);			
			entitlementVO.setResultStatus(resultStatus);
		}finally{
			entitlementIds = null;
			groupId = null;
			elements = null;
		}
		return entitlementVO;
	}
	
	
	*//**
	 * This method can be used to get all the entitlements based on the entitlement group.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @return NavigationVO 
	 *//*
	public NavigationVO getAllEntitlementsGroupsBasedOnUserGroupId(Long userGroupId){
		
		NavigationVO navigationVO= new NavigationVO();		
		List<EntitlementVO> entitlementVO = new ArrayList<EntitlementVO>();		
		Map<Long,EntitlementVO> allGroups = new HashMap<Long,EntitlementVO>();		
		ResultStatus resultStatus = new ResultStatus();
		Long availableCount=0l;
		try{			
			List result = groupEntitlementDAO.getAllGroups();
			if(result!=null){
				if(result.size()!=0){
					for(int i=0;i<result.size();i++){
						Object[] parms = (Object[])result.get(i); 
						EntitlementVO eVo = new EntitlementVO();
						eVo.setUserId((Long)parms[0]);
						eVo.setName((String)parms[1]);
						eVo.setMessage(IConstants.NOT_AVAILABLE);
						allGroups.put((Long)parms[0],eVo);						
					}
				}
			}
			
			List list2 = userGroupEntitlementDAO.getAllEntitlementGroupsBasedOnUserGroupId(userGroupId);
			if(list2!=null){
				if(list2.size()!=0){
					for(int i=0;i<list2.size();i++){
						Object[] parms = (Object[])list2.get(i);
						Long id = (Long)parms[0];
						EntitlementVO eVo = new EntitlementVO();
						if(allGroups.containsKey(id)){
							eVo = allGroups.get(id);
							allGroups.remove(id);						
							eVo.setUserId(id);
							eVo.setName(eVo.getName());
							eVo.setMessage(IConstants.AVAILABLE);
							availableCount++;
							allGroups.put(id,eVo);								
						}						
					}
				}
			}
			
			for(Map.Entry<Long, EntitlementVO> res : allGroups.entrySet()){
				entitlementVO.add(res.getValue());
			}
			navigationVO.setCount(availableCount);
			navigationVO.setEntitlementVO(entitlementVO);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			navigationVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			navigationVO.setResultStatus(resultStatus);
		}
		return navigationVO;
	}
	
	

	*//**
	 * This method can be used to save the relation between the user and the user group.
	 * 	
	 * @author Ravi Kiran.Y
	 * @serialData 09-11-10
	 * @param userId
	 * @param groupId
	 * @return EntitlementVO 
	 *//*
	public EntitlementVO saveRelationBetweenEntitlementsGroupsAndUserGroupId(Long userGroupId,String entitlementGroupIds){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<String> elements = null;		
		try{
			userGroupEntitlementDAO.deleteAllRelations(userGroupId);
			if(entitlementGroupIds.length()!=0){
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(entitlementGroupIds).split(","))));	
							    
				for(int i=0;i<elements.size();i++){
					Long id = new Long(elements.get(i));
					    UserGroupEntitlement relation = new UserGroupEntitlement();
						relation.setGroupEntitlement(groupEntitlementDAO.get(id));
						relation.setUserGroup(userGroupsDAO.get(userGroupId));
						relation = userGroupEntitlementDAO.save(relation);	
					}
				}	
		entitlementVO.setMessage(IConstants.SUCCESSFULLY_SAVED);
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching EntitlementGroups data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);			
			entitlementVO.setResultStatus(resultStatus);
		}finally{
			userGroupId = null;
			entitlementGroupIds = null;
			elements = null;
		}
		return entitlementVO;
	}
	
	
*/}
