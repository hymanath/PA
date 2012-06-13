package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IRoleDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dto.BaseDTO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.Role;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserRoles;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.utils.IConstants;

public class RegistrationService implements IRegistrationService{
	
	private IRegistrationDAO registrationDAO;
	private IPartyDAO partyDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IStateDAO stateDAO;
	private IAnanymousUserDAO ananymousUserDAO;
	private IAnanymousUserService ananymousUserService;
	private IDateService dateService;
	private IRoleDAO roleDAO;
	private IUserRolesDAO userRolesDAO;
	private IUserDAO userDAO;
	
	public IUserRolesDAO getUserRolesDAO() {
		return userRolesDAO;
	}

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	BaseDTO requestStatus = new BaseDTO();
	private Long userID = null;

	
	private static final Logger log = Logger.getLogger(RegistrationService.class);
	
	
	public void setDateService(IDateService dateService) {
		this.dateService = dateService;
	}

	public IDateService getDateService() {
		return dateService;
	}
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}

	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO){
		this.registrationDAO = registrationDAO;
	}
	
	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	//private String requestStatus;
	public void setUserID(Long userID){
		this.userID = userID;
	}
	
	public Long getUserID(){
		return userID;
	}
	
	public String getRequestStatus() {
		return this.requestStatus.getRequestStatus();
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus.setRequestStatus(requestStatus);
	}
	
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public String saveRegistration(RegistrationVO values,String userType){
		//Registration reg = new Registration();
		User user = new User();
		String dob = values.getDateOfBirth();
		//reg = convertIntoModel(values);
		user = convertIntoModel(values);
		
		if(checkUserName(values.getUserName())!= true)
		{
			if(userType.equalsIgnoreCase(IConstants.PARTY_ANALYST_USER))
			//reg = registrationDAO.save(reg);
			user = userDAO.save(user);
			
			//setUserID(reg.getRegistrationId());
			setUserID(user.getUserId());
			//saveDataInToUserRolesTable(reg,values);
			saveDataInToUserRolesTable(user,values);
			requestStatus.setRequestStatus(BaseDTO.SUCCESS);
		}
		else
			requestStatus.setRequestStatus(BaseDTO.PARTIAL);
		
		return requestStatus.getRequestStatus();
	}
	
	public void saveDataInToAnonymousTable(Registration reg,String dob){
		AnanymousUser userDetails = new AnanymousUser();
		try{
				userDetails.setName(reg.getFirstName());				
				userDetails.setGender(reg.getGender());
				userDetails.setUsername(reg.getUserName());
				userDetails.setPassword(reg.getPassword());
				Date date =null;		
				SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				date= format.parse(dob);		
				userDetails.setDateofbirth(date);
				userDetails.setEmail(reg.getEmail());
				userDetails.setPhone(reg.getPhone());
				userDetails.setMobile(reg.getMobile());
				userDetails.setAddress(reg.getAddress());				
				userDetails.setPincode(reg.getPincode());
				userDetails.setLastName(reg.getLastName());
			if(reg.getAccessType().equalsIgnoreCase(IConstants.STATE)){
				userDetails.setState(stateDAO.get(new Long(reg.getAccessValue().toString())));
				userDetails.setDistrict(null);
				userDetails.setConstituency(null);
			}else if(reg.getAccessType().equalsIgnoreCase(IConstants.DISTRICT)){
				userDetails.setState(districtDAO.get(new Long(reg.getAccessValue().toString())).getState());
				userDetails.setDistrict(districtDAO.get(new Long(reg.getAccessValue())));
				userDetails.setConstituency(null);
			}else if(reg.getAccessType().equalsIgnoreCase(IConstants.MP)){
				userDetails.setState(constituencyDAO.get(new Long(reg.getAccessValue().toString())).getState());
				userDetails.setDistrict(null);
				userDetails.setConstituency(constituencyDAO.get(new Long(reg.getAccessValue())));
			}else if(reg.getAccessType().equalsIgnoreCase(IConstants.MLA)){
				userDetails.setState(constituencyDAO.get(new Long(reg.getAccessValue().toString())).getState());
				userDetails.setDistrict(constituencyDAO.get(new Long(reg.getAccessValue().toString())).getDistrict());
				userDetails.setConstituency(constituencyDAO.get(new Long(reg.getAccessValue())));
			}
			ananymousUserDAO.save(userDetails);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public ResultStatus saveDataInToUserRolesTable(User user, RegistrationVO values)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			UserRoles userRoles = null;
			Role role = null;
			
			role = roleDAO.getRoleByRoleType(IConstants.PARTY_ANALYST_USER);
			userRoles = new UserRoles();
			userRoles.setUser(user);
			userRoles.setRole(role);
			userRolesDAO.save(userRoles);
			
			if(values.getFreeuser().equalsIgnoreCase(IConstants.TRUE))
			{
				role = roleDAO.getRoleByRoleType(IConstants.FREE_USER);
				userRoles = new UserRoles();
				userRoles.setUser(user);
				userRoles.setRole(role);
				userRolesDAO.save(userRoles);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}
		catch (Exception e) 
		{
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	
	public boolean checkUserName(String userName){
		boolean finalStatus = false;
		List<Registration> regCheck = new ArrayList<Registration>();
		regCheck = registrationDAO.findByUserName(userName);
		if(regCheck.size()>0){
			finalStatus = true;
			/*ResultStatus resultStatus = ananymousUserService.checkForUserNameAvalilability(userName);
			if(resultStatus.getResultCode()==ResultCodeMapper.FAILURE){
				finalStatus = false;
			}*/
		}
		return finalStatus;		
		
	}
	
	
	
	
	public User convertIntoModel(RegistrationVO values){
		//Registration reg = new Registration();  
		User user = new User();
		try{ 
			user.setFirstName(values.getFirstName());	
			user.setMiddleName(values.getMiddleName());
			user.setLastName(values.getLastName());
			user.setGender(values.getGender());
			user.setUserName(values.getUserName());
			user.setPassword(values.getPassword());
			user.setParty(partyDAO.get(values.getParty()));
			SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
			Date date =null;		
			date= format.parse(values.getDateOfBirth());		
			user.setDateOfBirth(date);
			user.setEmail(values.getEmail());
			user.setPhone(values.getPhone());
			user.setMobile(values.getMobile());
			user.setAddress(values.getAddress());
			user.setCountry(values.getCountry());
			user.setPincode(values.getPincode());
			user.setAccessType(values.getAccessType());
			user.setAccessValue(values.getAccessValue());
			user.setUserType(values.getUserType());
			if(values.getParentUserId() != null)
				user.setParentUser(userDAO.get(values.getParentUserId()));
			if(values.getMainAccountId() != null)
				user.setMainAccountUser(userDAO.get(values.getMainAccountId()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * This method can be used to get all registered users of party analyst.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 */
	public EntitlementVO getAllRegisterdUsers(){
		EntitlementVO entitlementVO = new EntitlementVO();
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> listOfUser;
		try{
			listOfUser = new ArrayList<SelectOptionVO>(0);
			
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(0l);
			selectOption.setName(IConstants.SELECT_USER_MESSAGE);
			listOfUser.add(selectOption);
			
			List result =  registrationDAO.getAllRegisteredUsers();
			for(int i=0;i<result.size();i++){
				Object[] registration = (Object[])result.get(i); 
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)registration[0]);
				String name = new String();
				if(registration[1]!=null){
					name+=registration[1].toString();
				}
				name+=" ";
				if(registration[2]!=null){
					name+=registration[2].toString();
				}
				selectOptionVO.setName(name);
				listOfUser.add(selectOptionVO);				
			}
			entitlementVO.setListOfUsers(listOfUser);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);			
		}catch(Exception e){
			e.printStackTrace();
			log.warn("There was an error in fetching all registered users data");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			entitlementVO.setResultStatus(resultStatus);
		}
		return entitlementVO;
	}
	
	public RegistrationVO getDetailsOfUserByUserId(Long registrationId){
		RegistrationVO registrationVO = new RegistrationVO();
		List<Registration> users = registrationDAO.findByUserRegistrationId(registrationId);
		
		Registration registration = users.get(0);
		
		registrationVO.setRegistrationID(registrationId);
		registrationVO.setUserName(registration.getUserName());
		registrationVO.setPassword(registration.getPassword());
		registrationVO.setGender(registration.getGender());
		registrationVO.setEmail(registration.getEmail());
		registrationVO.setPhone(registration.getPhone());
		registrationVO.setMobile(registration.getMobile());
		registrationVO.setAddress(registration.getAddress());
		if(registration.getDateOfBirth()!=null){
		registrationVO.setDateOfBirth(DateService.timeStampConversionToDDMMYY(registration.getDateOfBirth().toString()));
		}
		registrationVO.setAccessType(registration.getAccessType());
		registrationVO.setAccessValue(registration.getAccessValue());
		registrationVO.setFirstName(registration.getFirstName());
		registrationVO.setLastName(registration.getLastName());
		registrationVO.setUserType(registration.getUserType());
		
		if(registration.getParty() != null){
			registrationVO.setParty(registration.getParty().getPartyId());
			registrationVO.setPartyShortName(registration.getParty().getShortName());
		}
		
		return registrationVO;
	}
	public ResultStatus checkForUserNameAvalilability(String userName){
		ResultStatus resultStatus = new ResultStatus();
		List<Registration> detailsList = null;
		List<Registration> detailsListForEmail = null;
		try{
			detailsList = registrationDAO.checkForUserNameAvailabiity(userName);
			detailsListForEmail=registrationDAO.checkForUserNameAvailabiityForEmail(userName);
			if(detailsList!=null && detailsList.size()!=0 && detailsListForEmail!=null && detailsListForEmail.size()!=0){
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
	
	public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName){
		ResultStatus resultStatus = new ResultStatus();
		List<Registration> detailsList = null;
		List<Registration> detailsListForEmail = null;
		try{
			detailsList = registrationDAO.checkForUserNameAvailabiity(userName);
			detailsListForEmail=registrationDAO.checkForUserNameAvailabiityForEmail(userName);
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
	public Integer updateRegisteredUserDetailsToUserNameToEmail(Long userId,String userName){
		
		Integer detailsList = null;
		RegistrationVO registrationVO=new RegistrationVO();
		try{
			detailsList = (Integer) registrationDAO.saveUserNameTOEmail(userId,userName);
		
			
		}catch(Exception e){
			e.printStackTrace();
			return detailsList;
		}
		return detailsList;
	}
	
	public List<String> getRoleTypeForUser()
	{
		 List<String> detailsList = new ArrayList<String>();
		 RegistrationVO registrationVO=new RegistrationVO();
		 
		try
		{
		List<Role> userTypes = roleDAO.getRoleType();
		if(userTypes != null && userTypes.size() > 0)
		{
			for(Role role : userTypes)
			{
				detailsList = new ArrayList<String>();
				detailsList.add(role.getRoleType());
				
				
			}
			
		}
		return detailsList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return detailsList;
		}
		
		
	}
}
