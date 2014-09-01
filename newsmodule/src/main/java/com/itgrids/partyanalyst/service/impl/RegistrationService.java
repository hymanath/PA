package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IDepartmentDAO;
import com.itgrids.partyanalyst.dao.IEntitlementDAO;
import com.itgrids.partyanalyst.dao.IRoleDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserDepartmentDAO;
import com.itgrids.partyanalyst.dao.IUserEntitlementDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Role;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserDepartment;
import com.itgrids.partyanalyst.model.UserEntitlement;
import com.itgrids.partyanalyst.service.IRegistrationService;

public class RegistrationService implements IRegistrationService{
	//Other Templates
	private TransactionTemplate transactionTemplate = null;
	private IUserDAO userDAO;
	private IRoleDAO roleDAO;
	private IUserEntitlementDAO userEntitlementDAO;
	private IEntitlementDAO entitlementDAO;
	private IDepartmentDAO departmentDAO;
	private IUserDepartmentDAO userDepartmentDAO;
	
	private static final Logger LOG = Logger.getLogger(RegistrationService.class);
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public IUserEntitlementDAO getUserEntitlementDAO() {
		return userEntitlementDAO;
	}

	public void setUserEntitlementDAO(IUserEntitlementDAO userEntitlementDAO) {
		this.userEntitlementDAO = userEntitlementDAO;
	}

	public IEntitlementDAO getEntitlementDAO() {
		return entitlementDAO;
	}

	public void setEntitlementDAO(IEntitlementDAO entitlementDAO) {
		this.entitlementDAO = entitlementDAO;
	}

	public IDepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(IDepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public IUserDepartmentDAO getUserDepartmentDAO() {
		return userDepartmentDAO;
	}

	public void setUserDepartmentDAO(IUserDepartmentDAO userDepartmentDAO) {
		this.userDepartmentDAO = userDepartmentDAO;
	}

	/** This method is used save the registration Details **/
	public Boolean saveDataIntoUser(final RegistrationVO regVo,final String userType)
	{
		User result = null;
		try{
		 result = (User)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					User user = null;
					user = new User();
					Role role = roleDAO.get(Long.valueOf(regVo.getUserType()));
					user.setAccessType(regVo.getAccessType());
					user.setAccessValue(regVo.getAccessValue());
					
					user.setFirstName(regVo.getFirstName());
					user.setLastName(regVo.getLastName());
					user.setUserName(regVo.getEmail());
					user.setPassword(regVo.getPassword());
					user.setParentUser(userDAO.get(regVo.getRegistrationID()));
					user.setUserAccessType(role.getRoleType());
					user.setProjectType(role.getProjectType());
					user.setRole(role);
					if(userType.equalsIgnoreCase("Admin"))
					 user.setUserType("subuser");
					else 
					user.setUserType(userType);
					user = userDAO.save(user);
					regVo.getProfileOpts().add(role.getEntitlement().getEntitlementId());
					for(Long entitlementId:regVo.getProfileOpts()){
					 if(entitlementId != null){
						UserEntitlement userEntitlement = new UserEntitlement();
						userEntitlement.setEntitlement(entitlementDAO.get(entitlementId));
						userEntitlement.setUser(user);
						userEntitlementDAO.save(userEntitlement);
					 }
					}
					if(role.getRoleId().equals(6l)){
						UserDepartment userDepartment = new UserDepartment();
						userDepartment.setUser(user);
						userDepartment.setDepartment(departmentDAO.get(regVo.getUsrDeptId()));
						userDepartmentDAO.save(userDepartment);
					}
				return user;
				}	
			});
		}catch(Exception e){
			LOG.error(e);
		}
			if(result != null && result.getUserId() != null)
				return true;
			
		 return false;
				
  }
	/** this method is used to check user name exists or not **/
	public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName){
		ResultStatus resultStatus = new ResultStatus();
		List<User> detailsList = null;
		List<User> detailsListForEmail = null;
		try{
			detailsList = userDAO.checkForUserNameAvailabiity(userName);
			detailsListForEmail = userDAO.checkForUserNameAvailabiityForEmail(userName);
			if(detailsList.size()!=0 || detailsListForEmail.size()!=0){
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			}else{
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			}
			resultStatus.setResultPartial(false);
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
		}
		return resultStatus;
	}
	
	public List<SelectOptionVO> getAllRoles(){
		List<SelectOptionVO> allRoles = new ArrayList<SelectOptionVO>();
		List<Object[]> rolesList = roleDAO.getAllRoles();
		SelectOptionVO vo = null;
		for(Object[] role:rolesList){
			vo = new SelectOptionVO();
			vo.setId((Long)role[0]);
			vo.setName(role[1].toString());
			allRoles.add(vo);
		}
		return allRoles;
	}
	
	public List<SelectOptionVO> getAllDepartments(){
		List<SelectOptionVO> allDepartments = new ArrayList<SelectOptionVO>();
		allDepartments.add(0,new SelectOptionVO(0l,"Select Department"));
		List<Object[]> departmentsList = departmentDAO.getAllDepartments();
		SelectOptionVO vo = null;
		for(Object[] department:departmentsList){
			vo = new SelectOptionVO();
			vo.setId((Long)department[0]);
			vo.setName(department[1].toString());
			allDepartments.add(vo);
		}
		return allDepartments;
	}
}
