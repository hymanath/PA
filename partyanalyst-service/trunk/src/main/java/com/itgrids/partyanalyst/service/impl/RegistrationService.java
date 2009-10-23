package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.BaseDTO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.utils.IConstants;

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
		
		return reg;
	}
	
}
