package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnanymousUserService implements IAnanymousUserService {

	public IAnanymousUserDAO ananymousUserDAO;

	private TransactionTemplate transactionTemplate = null;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}
	
	
	/**
	 * This method can be used for saving of Anonymous User details.
	 * 
	 * @author Ravi Kiran.Y 
	 * @param RegistrationVO
	 * @return RegistrationVO
	 */
	public Boolean saveAnonymousUserDetails(final RegistrationVO userDetails){
		
		AnanymousUser ananymousUserReturn = (AnanymousUser)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {	
				AnanymousUser ananymousUser = new AnanymousUser();
				try{
				ananymousUser.setName(userDetails.getFirstName());
				ananymousUser.setLastName(userDetails.getLastName());
				ananymousUser.setUsername(userDetails.getUserName());
				ananymousUser.setPassword(userDetails.getPassword());
				ananymousUser.setGender(userDetails.getGender());
				SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				Date date =null;
				date= format.parse(userDetails.getDateOfBirth());
				
				ananymousUser.setDateofbirth(date);
				ananymousUser.setEmail(userDetails.getEmail());
				ananymousUser.setMobile(userDetails.getMobile());
				ananymousUser.setPhone(userDetails.getPhone());
				ananymousUser.setAddress(userDetails.getAddress());
				//userDetails.getCountry()
				ananymousUser.setState(stateDAO.get(new Long(userDetails.getState())));
				ananymousUser.setDistrict(districtDAO.get(new Long(userDetails.getDistrict())));
				ananymousUser.setConstituency(constituencyDAO.get(new Long(userDetails.getConstituency())));
				ananymousUser.setPincode(userDetails.getPincode());
				ananymousUser = ananymousUserDAO.save(ananymousUser);
				 
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
				}
			 return ananymousUser;
			}
		});
		
		if(ananymousUserReturn != null && ananymousUserReturn.getUserId() != null)
			return true;
		
	 return false;
	}
	
	
	/**
	 * This method is used to check whether the username and password entered by anonymous user
	 * is valid or not.
	 * if the entered information is valid this method returns the corresponding details to the
	 * calling method.
	 * 
	 * @author Ravi Kiran.Y
	 * @param anonymousUserId
	 * @param password
	 * @return RegistrationVO
	 */
	public RegistrationVO checkAnonymousUserLogin(String anonymousUserId,String password){	
		RegistrationVO userDetails = null;
		List<AnanymousUser> detailsList = null;	
		ResultStatus resultStatus = new ResultStatus();		
		try{
			userDetails = new RegistrationVO();
			detailsList = ananymousUserDAO.checkAnonymousUserLogin(anonymousUserId,password);
			if(detailsList.size()!=0){
				for(AnanymousUser resultIterator : detailsList){
					userDetails.setUserName(resultIterator.getUsername());
					userDetails.setPassword(resultIterator.getPassword());
					userDetails.setName(resultIterator.getName());
					userDetails.setGender(resultIterator.getGender());
					userDetails.setDateOfBirth(resultIterator.getDateofbirth().toString());
					userDetails.setEmail(resultIterator.getEmail());
					userDetails.setPhone(resultIterator.getPhone());
					userDetails.setMobile(resultIterator.getMobile());			
					userDetails.setAddress(resultIterator.getAddress());
					userDetails.setConstituency(resultIterator.getConstituency().getName());
					userDetails.setDistrict(resultIterator.getDistrict().getDistrictName());
					userDetails.setState(resultIterator.getState().getStateName());
					userDetails.setPincode(resultIterator.getPincode());
				}
			 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			userDetails.setResultStatus(resultStatus);			
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);			
		}
		return userDetails;		
	}
	
	/**
	 * This method can be used to check whether the user name is available or not.
	 * @author Ravi Kiran.Y
	 * @param userName
	 * @return ResultStatus
	 */
	public ResultStatus checkForUserNameAvalilability(String userName){
		ResultStatus resultStatus = new ResultStatus();
		List<AnanymousUser> detailsList = null;
		try{
			detailsList = ananymousUserDAO.checkForUserNameAvailabiity(userName);
			if(detailsList.size()==1){
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
	
	/**
	 * This method can be used to retrive ananymous/free users of party analyst based on location wise.
	 * @author Ravi Kiran.Y
	 * @version 1.0,08/10/2010
	 * @param locationIds
	 * @param locationType
	 * @return DataTransferVO
	 */
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType){
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();;
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();
		try{
			result = ananymousUserDAO.getAllUsersInSelectedLocations(locationIds, locationType);			
			if(result!=null && result.size()!=0){
				for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					CandidateVO candidateVO = new CandidateVO();
					String lastName="";
					if(parms[1]!=null){
						lastName = parms[1].toString();
					}
					candidateVO.setCandidateName(parms[0].toString().concat(" ").concat(lastName));
					candidateVO.setId(new Long(parms[2].toString()));
					candidateDetails.add(candidateVO);
				}
			}			
			dataTransferVO.setCandidateVO(candidateDetails);
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			dataTransferVO.setResultStatus(resultStatus);		
			System.out.println(dataTransferVO.getCandidateVO().size());
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
	} 
	
}
