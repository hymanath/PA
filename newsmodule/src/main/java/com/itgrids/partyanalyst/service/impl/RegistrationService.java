package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.service.IRegistrationService;

public class RegistrationService implements IRegistrationService{
	//Other Templates
	private TransactionTemplate transactionTemplate = null;
	private IUserDAO userDAO;
	
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

	/*
	private IPartyDAO partyDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IStateDAO stateDAO;
	private IAnanymousUserService ananymousUserService;
	private IDateService dateService;
	private IRoleDAO roleDAO;
	private IUserRolesDAO userRolesDAO;
	private IUserDAO userDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	
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

	public String saveRegistration(RegistrationVO values,String userType)
	{
		User user = saveDataIntoUserModel(values);
		
		if(checkUserName(values.getUserName())!= true)
		{
			
			if(values.getStateId() !=null && values.getStateId()!=0)
				user.setStateId(values.getStateId());
			
			if(values.getConstituency()!= null && !values.getConstituency().equalsIgnoreCase("Select Constituency"))
			user.setConstituencyId(Long.parseLong(values.getConstituency().toString()));
			
			user = userDAO.save(user);
			saveDataInToUserRolesTable(user,values);
			requestStatus.setRequestStatus(BaseDTO.SUCCESS);
		}
		else
			requestStatus.setRequestStatus(BaseDTO.PARTIAL);

		return requestStatus.getRequestStatus();
	}
	
	public void saveDataInToAnonymousTable(Registration reg,String dob){
		//AnanymousUser userDetails = new AnanymousUser();
		Registration userDetails = new Registration();
		try{
				//userDetails.setName(reg.getFirstName());				
				userDetails.setGender(reg.getGender());
				//.setUsername(reg.getUserName());
				userDetails.setPassword(reg.getPassword());
				Date date =null;		
				SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				date= format.parse(dob);		
			//	userDetails.setDateofbirth(date);
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
			//ananymousUserDAO.save(userDetails);
			registrationDAO.save(userDetails);
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
		//regCheck = registrationDAO.findByUserName(userName);
		if(regCheck.size()>0){
			finalStatus = true;
			ResultStatus resultStatus = ananymousUserService.checkForUserNameAvalilability(userName);
			if(resultStatus.getResultCode()==ResultCodeMapper.FAILURE){
				finalStatus = false;
			}
		}
		return finalStatus;		
		
	}
	public User saveDataIntoUserModel(RegistrationVO values)
	{
		User user = new User();
		try
		{
			user.setFirstName(values.getFirstName());
			user.setMiddleName(values.getMiddleName());
			user.setLastName(values.getLastName());
			user.setGender(values.getGender());
			user.setUserName(values.getUserName());
			
			String secretKey = EncryptDecrypt.getSecretKey();
			
			EncryptDecrypt encryptDecrypt = new EncryptDecrypt(secretKey);
			
			user.setPasswdHashTxt(encryptDecrypt.encryptText(values.getPassword()));
			user.setHashKeyTxt(secretKey);

			user.setParty(partyDAO.get(values.getParty()));
			SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
			Date date = null;
			date = format.parse(values.getDateOfBirth());
			user.setDateOfBirth(date);
			user.setEmail(values.getEmail());
			user.setPhone(values.getPhone());
			user.setMobile(values.getMobile());
			user.setAddress(values.getAddress());
			user.setPincode(values.getPincode());
			user.setCountry(values.getCountry());
			user.setAccessType(values.getAccessType());
			user.setAccessValue(values.getAccessValue());
			user.setUserType(values.getUserType());
			user.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
			user.setRegisteredDate(dateUtilService.getCurrentDateAndTime());
			user.set_loginRestriction(IConstants.FALSE);
			
			if(values.getParentUserId() != null)
				user.setParentUser(userDAO.get(values.getParentUserId()));
			if(values.getMainAccountId() != null)
				user.setMainAccountUser(userDAO.get(values.getMainAccountId()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
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
	
	*//**
	 * This method can be used to get all registered users of party analyst.
	 * 
	 * @author Ravi Kiran.Y
	 * @serialData 04-11-10
	 * @return EntitlementVO 
	 *//*
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
			
			
			List<Object[]> result = userDAO.allRegisteredUsersData();
			if(result != null && result.size() > 0)
			{
				for(Object[] params : result)
				{
					String name = new String();
					if(params[1] != null)
						name = params[1].toString();
					name +=" ";
					if(params[2] != null)
						name +=params[2].toString();
					
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(name);
					listOfUser.add(selectOptionVO);
				}
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
		
		
		User user = userDAO.get(registrationId);
		registrationVO.setRegistrationID(user.getUserId());
		
		registrationVO.setUserName(user.getUserName());
		registrationVO.setPassword(user.getPassword());
		registrationVO.setGender(user.getGender());
		registrationVO.setEmail(user.getEmail());
		registrationVO.setPhone(user.getPhone());
		registrationVO.setMobile(user.getMobile());
		registrationVO.setAddress(user.getAddress());
		if(user.getDateOfBirth()!=null){
		registrationVO.setDateOfBirth(DateService.timeStampConversionToDDMMYY(user.getDateOfBirth().toString()));
		}
		registrationVO.setAccessType(user.getAccessType());
		registrationVO.setAccessValue(user.getAccessValue());
		registrationVO.setFirstName(user.getFirstName());
		registrationVO.setLastName(user.getLastName());
		registrationVO.setUserType(user.getUserType());
		
		if(user.getParty() != null){
			registrationVO.setParty(user.getParty().getPartyId());
			registrationVO.setPartyShortName(user.getParty().getShortName());
		}
		
		return registrationVO;
	}
	public ResultStatus checkForUserNameAvalilability(String userName){
		ResultStatus resultStatus = new ResultStatus();
		List<Registration> detailsList = null;
		List<Registration> detailsListForEmail = null;
		try{
			//detailsList = registrationDAO.checkForUserNameAvailabiity(userName);
			//detailsListForEmail=registrationDAO.checkForUserNameAvailabiityForEmail(userName);
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
			//detailsList = registrationDAO.checkForUserNameAvailabiity(userName);
			//detailsListForEmail=registrationDAO.checkForUserNameAvailabiityForEmail(userName);
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
			//detailsList = (Integer) registrationDAO.saveUserNameTOEmail(userId,userName);
		
			
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
*/
	/** This method is used save the registration Details **/
	public Boolean saveDataIntoUser(final RegistrationVO regVo,final String userType)
	{
		
		User result = (User)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					User user = null;
					user = new User();
					
					 user.setFirstName(regVo.getFirstName());
					 user.setLastName(regVo.getLastName());
					 user.setUserName(regVo.getEmail());
					user.setPassword(regVo.getPassword());
					 if(userType.equalsIgnoreCase("Admin"))
					 user.setUserType("subuser");
					 else 
					user.setUserType(userType);
					user = userDAO.save(user);
					
				return user;
				}	
			});
				
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
	
}
