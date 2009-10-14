package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.BaseDTO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Registration;

public class RegistrationService implements IRegistrationService{
	
	IRegistrationDAO registrationDAO;
	
	public void setRegistrationDAO(IRegistrationDAO registrationDAO){
		this.registrationDAO = registrationDAO;
	}
	
	//private String requestStatus;
	BaseDTO requestStatus = new BaseDTO();
	private Long userID = null;
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
		java.util.Date dt =java.sql.Date.valueOf(values.getDateOfBirth());
		reg.setDateOfBirth(dt);
		reg.setEmail(values.getEmail());
		reg.setPhone(values.getPhone());
		reg.setMobile(values.getMobile());
		reg.setAddress(values.getAddress());
		reg.setCountry(values.getCountry());
		reg.setPincode(values.getPincode());
		reg.setAccessType(values.getAccessType());
		reg.setAccessValue(values.getAccessValue());
		
		return reg;
	}
	
}
