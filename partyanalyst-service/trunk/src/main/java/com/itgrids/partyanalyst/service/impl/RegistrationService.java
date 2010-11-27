package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.BaseDTO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.utils.IConstants;

public class RegistrationService implements IRegistrationService{
	
	IRegistrationDAO registrationDAO;
	IPartyDAO partyDAO;
	
	BaseDTO requestStatus = new BaseDTO();
	private Long userID = null;

	
	private static final Logger log = Logger.getLogger(RegistrationService.class);
	
	
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
	
	public String saveRegistration(RegistrationVO values){
		Registration reg = new Registration();		
		
		reg = convertIntoModel(values);
				
		if(checkUserName(values.getUserName())!= true){
		reg = registrationDAO.save(reg);
		//requestStatus = BaseDTO.SUCCESS;
		setUserID(reg.getRegistrationId());
		requestStatus.setRequestStatus(BaseDTO.SUCCESS);
		}
		else{
			//requestStatus =  BaseDTO.PARTIAL;
			requestStatus.setRequestStatus(BaseDTO.PARTIAL);
		}
		return requestStatus.getRequestStatus();
	}
	
	public boolean checkUserName(String userName){
		boolean finalStatus = false;
		List<Registration> regCheck = new ArrayList<Registration>();
		regCheck = registrationDAO.findByUserName(userName);
		if(regCheck.size()>0){
			finalStatus = true;
		}
		return finalStatus;		
		
	}
	
	
	public Registration convertIntoModel(RegistrationVO values){
		Registration reg = new Registration();  	 
		reg.setFirstName(values.getFirstName());	
		reg.setMiddleName(values.getMiddleName());
		reg.setLastName(values.getLastName());
		reg.setGender(values.getGender());
		reg.setUserName(values.getUserName());
		reg.setPassword(values.getPassword());
		reg.setParty(partyDAO.get(values.getParty()));
		SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
		Date date =null;
		try{
			date= format.parse(values.getDateOfBirth());
		}catch(Exception e){
			e.printStackTrace();
		}
		reg.setDateOfBirth(date);
		reg.setEmail(values.getEmail());
		reg.setPhone(values.getPhone());
		reg.setMobile(values.getMobile());
		reg.setAddress(values.getAddress());
		reg.setCountry(values.getCountry());
		reg.setPincode(values.getPincode());
		reg.setAccessType(values.getAccessType());
		reg.setAccessValue(values.getAccessValue());
		reg.setUserType(values.getUserType());
		return reg;
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
			selectOption.setName("select a user");
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
	
	
}
