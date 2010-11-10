package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IUserGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IUserGroupsDAO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserGroupEntitlementDAOHibernateTest extends BaseDaoTestCase{

	private IUserGroupEntitlementDAO userGroupEntitlementDAO;
	private IUserGroupsDAO userGroupsDAO;
	private IGroupEntitlementDAO groupEntitlementDAO;
	
	
	public IGroupEntitlementDAO getGroupEntitlementDAO() {
		return groupEntitlementDAO;
	}

	public void setGroupEntitlementDAO(IGroupEntitlementDAO groupEntitlementDAO) {
		this.groupEntitlementDAO = groupEntitlementDAO;
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
	
/*	public void testGetAll(){		
		List list2 = userGroupEntitlementDAO.getAllGroupsForAUser(2l);
		System.out.println(list2.size());		
	}
	*/
	
	public void testGet(){
		getAllEntitlementsGroupsBasedOnUserGroupId(1l);
	}
public NavigationVO getAllEntitlementsGroupsBasedOnUserGroupId(Long userGroupId){
		
		NavigationVO navigationVO= new NavigationVO();		
		List<EntitlementVO> entitlementVO = new ArrayList<EntitlementVO>();		
		Map<Long,EntitlementVO> allGroups = new HashMap<Long,EntitlementVO>();		
		ResultStatus resultStatus = new ResultStatus();
	
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
							allGroups.put(id,eVo);								
						}						
					}
				}
			}
			
			for(Map.Entry<Long, EntitlementVO> res : allGroups.entrySet()){
				entitlementVO.add(res.getValue());
			}
			
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
}
