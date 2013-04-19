package com.itgrids.partyanalyst.service.impl;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserAcessIpAddressDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserAccessRegionVO;
import com.itgrids.partyanalyst.dto.UserDetailsInfoVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserAcessIpAddress;
import com.itgrids.partyanalyst.model.UserConstituencyAccessInfo;
import com.itgrids.partyanalyst.model.UserCountryAccessInfo;
import com.itgrids.partyanalyst.model.UserDistrictAccessInfo;
import com.itgrids.partyanalyst.model.UserStateAccessInfo;
import com.itgrids.partyanalyst.service.IUserAccessRegionService;
import com.itgrids.partyanalyst.utils.IConstants;


public class UserAccessRegionService implements IUserAccessRegionService{
private static final Logger Log = Logger.getLogger(VotersAnalysisService.class);	
private	IUserCountryAccessInfoDAO userCountryAccessInfoDAO;
private IUserStateAccessInfoDAO userStateAccessInfoDAO;
private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
private ICountryDAO countryDAO;
private IStateDAO stateDAO;
private IDistrictDAO districtDAO;
private IConstituencyDAO constituencyDAO;
private IUserDAO userDAO;

private IUserRolesDAO userRolesDAO;
private IUserAcessIpAddressDAO userAcessIpAddressDAO;




public IUserAcessIpAddressDAO getUserAcessIpAddressDAO() {
	return userAcessIpAddressDAO;
}


public void setUserAcessIpAddressDAO(
		IUserAcessIpAddressDAO userAcessIpAddressDAO) {
	this.userAcessIpAddressDAO = userAcessIpAddressDAO;
}


public IUserRolesDAO getUserRolesDAO() {
	return userRolesDAO;
}


public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
	this.userRolesDAO = userRolesDAO;
}


public IConstituencyDAO getConstituencyDAO() {
	return constituencyDAO;
}


public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
	this.constituencyDAO = constituencyDAO;
}


public ICountryDAO getCountryDAO() {
	return countryDAO;
}


public void setCountryDAO(ICountryDAO countryDAO) {
	this.countryDAO = countryDAO;
}


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


public IUserCountryAccessInfoDAO getUserCountryAccessInfoDAO() {
	return userCountryAccessInfoDAO;
}


public void setUserCountryAccessInfoDAO(
		IUserCountryAccessInfoDAO userCountryAccessInfoDAO) {
	this.userCountryAccessInfoDAO = userCountryAccessInfoDAO;
}


public IUserStateAccessInfoDAO getUserStateAccessInfoDAO() {
	return userStateAccessInfoDAO;
}


public void setUserStateAccessInfoDAO(
		IUserStateAccessInfoDAO userStateAccessInfoDAO) {
	this.userStateAccessInfoDAO = userStateAccessInfoDAO;
}


public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
	return userDistrictAccessInfoDAO;
}


public void setUserDistrictAccessInfoDAO(
		IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
	this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
}


public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
	return userConstituencyAccessInfoDAO;
}


public void setUserConstituencyAccessInfoDAO(
		IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
	this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
}


public IUserDAO getUserDAO() {
	return userDAO;
}


public void setUserDAO(IUserDAO userDAO) {
	this.userDAO = userDAO;
}


public UserAccessRegionVO getAccessDetailsByUserId(Long userId)
{
	UserAccessRegionVO userAccessRegionVO = null;
	List<UserAccessRegionVO> country = new ArrayList<UserAccessRegionVO>();
	List<UserAccessRegionVO> state = new ArrayList<UserAccessRegionVO>();
	List<UserAccessRegionVO> district = new ArrayList<UserAccessRegionVO>();
	List<UserAccessRegionVO> constituency = new ArrayList<UserAccessRegionVO>();
	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
	
	List<Object[]> countryAccessList = userCountryAccessInfoDAO.findByUser(userId);
	for(Object[] param : countryAccessList){
		userAccessRegionVO = new UserAccessRegionVO();
		userAccessRegionVO.setCountryId((Long)param[0]);
		userAccessRegionVO.setCountryName(param[1].toString());
		country.add(userAccessRegionVO);
	}
	
	List<Object[]> stateAccessList = userStateAccessInfoDAO.findByUser(userId);
	for(Object[] param : stateAccessList){
		userAccessRegionVO = new UserAccessRegionVO();
		userAccessRegionVO.setStateId((Long)param[0]);
		userAccessRegionVO.setStateName(param[1].toString());
		state.add(userAccessRegionVO);
	}
	
	
	List<Object[]> districtAccessList = userDistrictAccessInfoDAO.findByUser(userId);
	for(Object[] param : districtAccessList){
		userAccessRegionVO = new UserAccessRegionVO();
		userAccessRegionVO.setDistrictId((Long)param[0]);
		userAccessRegionVO.setDistrictName(param[1].toString());
		district.add(userAccessRegionVO);
	}
	
	
	List<Object[]> constituencyAccessList = userConstituencyAccessInfoDAO.findByUser(userId);
	for(Object[] param : constituencyAccessList){
		userAccessRegionVO = new UserAccessRegionVO();
		userAccessRegionVO.setConstituencyId((Long)param[0]);
		userAccessRegionVO.setConstituencyName(param[1].toString());
		userAccessRegionVO.setElectionType(param[2].toString());
		constituency.add(userAccessRegionVO);
	}
	
	userAccRegVO.setCountry(country);
	userAccRegVO.setState(state);
	userAccRegVO.setDistrict(district);
	userAccRegVO.setConstituency(constituency);
	
	return userAccRegVO;
}
    public UserAccessRegionVO getCountryDetails()
    {
    	UserAccessRegionVO userAccessRegionVO = null;
    	
    	List<UserAccessRegionVO> country = new ArrayList<UserAccessRegionVO>();
    	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
    	List<Object[]> countryList = countryDAO.getAllCountryDetails();
    	for(Object[] param : countryList){
    		userAccessRegionVO = new UserAccessRegionVO();
    		userAccessRegionVO.setCountryId((Long)param[0]);
    		userAccessRegionVO.setCountryName(param[1].toString());
    		country.add(userAccessRegionVO);
    	}
    	userAccRegVO.setCountry(country);
    	
    	return userAccRegVO;
    }
    
    public UserAccessRegionVO getStateDetailForDistrictAndAssConstituency(Long userId)
    {
    	UserAccessRegionVO userAccessRegionVO = null;
    	List<UserAccessRegionVO> state = new ArrayList<UserAccessRegionVO>();
    	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
    	
    	List<Object[]> stateList = stateDAO.findStatesByCountryIdAndCountryAccessAndStateAccess(1L,userId);
    	
    	for(Object[] param : stateList){
    		userAccessRegionVO = new UserAccessRegionVO();
    		userAccessRegionVO.setStateId((Long)param[0]);
    		userAccessRegionVO.setStateName(param[1].toString());
    		state.add(userAccessRegionVO);
    	}
    	userAccRegVO.setState(state);
    	
    	return userAccRegVO;
    	
    }
    public UserAccessRegionVO getDistrictDetailsByStateIdUserId(Long stateId,Long userId)
    {
    	 
    	UserAccessRegionVO userAccessRegionVO = null;
    	List<UserAccessRegionVO> district = new ArrayList<UserAccessRegionVO>();
    	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
    	Map<Long,UserAccessRegionVO> allDistrict = new HashMap<Long,UserAccessRegionVO>();
    	    	    	
    	try{
    		
    		List<Object[]> distList = districtDAO.getDistrictIdAndNameByState(stateId);    	 
        	for(Object[] param : distList){
        		userAccessRegionVO = new UserAccessRegionVO();
        		userAccessRegionVO.setDistrictId((Long)param[0]);
        		userAccessRegionVO.setDistrictName(param[1].toString());
        		userAccessRegionVO.setMessage(IConstants.NOT_AVAILABLE);
        		allDistrict.put((Long)param[0],userAccessRegionVO);
        	}
    		
    		List<Object[]> districtList = userDistrictAccessInfoDAO.findByUserAndState(stateId, userId);
    	
			if(districtList!=null){
				if(districtList.size()!=0){
					for(int i=0;i<districtList.size();i++){
						Object[] parms = (Object[])districtList.get(i); 
						userAccessRegionVO = new UserAccessRegionVO();
						userAccessRegionVO.setDistrictId((Long)parms[0]);
						userAccessRegionVO.setDistrictName((String)parms[1]);
						userAccessRegionVO.setMessage(IConstants.AVAILABLE);
						allDistrict.put((Long)parms[0],userAccessRegionVO);						
					}
				}
			}
			
		
    	
    	for(Map.Entry<Long, UserAccessRegionVO> res : allDistrict.entrySet()){
    		district.add(res.getValue());
		}
    	userAccRegVO.setDistrict(district);
    	
    	return userAccRegVO;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return userAccRegVO;
		}
    }
    
    public UserAccessRegionVO getCountryDetailsByUserId(Long userId)
    {
    	
    	UserAccessRegionVO userAccessRegionVO = null;
    	List<UserAccessRegionVO> country = new ArrayList<UserAccessRegionVO>();
    	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
    	Map<Long,UserAccessRegionVO> allCountry = new HashMap<Long,UserAccessRegionVO>();
    	    	    	
    	try{
    		
    		List<Object[]> countList = countryDAO.getAllCountryDetails();    	 
        	for(Object[] param : countList){
        		userAccessRegionVO = new UserAccessRegionVO();
        		userAccessRegionVO.setCountryId((Long)param[0]);
        		userAccessRegionVO.setCountryName(param[1].toString());
        		userAccessRegionVO.setMessage(IConstants.NOT_AVAILABLE);
        		allCountry.put((Long)param[0],userAccessRegionVO);
        	}
    		List<Object[]> countryList = userCountryAccessInfoDAO.findByUser(userId);
    	
			if(countryList!=null){
				if(countryList.size()!=0){
					for(int i=0;i<countryList.size();i++){
						Object[] parms = (Object[])countryList.get(i); 
						userAccessRegionVO = new UserAccessRegionVO();
						userAccessRegionVO.setCountryId((Long)parms[0]);
						userAccessRegionVO.setCountryName((String)parms[1]);
						userAccessRegionVO.setMessage(IConstants.AVAILABLE);
						allCountry.put((Long)parms[0],userAccessRegionVO);						
					}
				}
			}
			
		
    	
    	for(Map.Entry<Long, UserAccessRegionVO> res : allCountry.entrySet()){
    		country.add(res.getValue());
		}
    	userAccRegVO.setCountry(country);
    	
    	return userAccRegVO;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return userAccRegVO;
		}
    }
    
    public UserAccessRegionVO getStateDetailsByUserId(Long userId)
    {
       	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
    	UserAccessRegionVO userAccessRegionVO = null;
    	List<UserAccessRegionVO> state = new ArrayList<UserAccessRegionVO>();
    	Map<Long,UserAccessRegionVO> allState = new HashMap<Long,UserAccessRegionVO>();
    	    	    	
    	try{	
    		
    		List<Object[]> staList = stateDAO.findStatesByCountryIdAndCountryAccess(1L,userId);    	 
        	for(Object[] param : staList){
        		userAccessRegionVO = new UserAccessRegionVO();
        		userAccessRegionVO.setStateId((Long)param[0]);
        		userAccessRegionVO.setStateName(param[1].toString());
        		userAccessRegionVO.setMessage(IConstants.NOT_AVAILABLE);
        		allState.put((Long)param[0],userAccessRegionVO);
        	}
    		
    		
    		List<Object[]> stateList = userStateAccessInfoDAO.findByUser(userId);
    	
			if(stateList!=null){
				if(stateList.size()!=0){
					for(int i=0;i<stateList.size();i++){
						Object[] parms = (Object[])stateList.get(i); 
						userAccessRegionVO = new UserAccessRegionVO();
						userAccessRegionVO.setStateId((Long)parms[0]);
						userAccessRegionVO.setStateName((String)parms[1]);
						userAccessRegionVO.setMessage(IConstants.AVAILABLE);
						allState.put((Long)parms[0],userAccessRegionVO);						
					}
				}
			}
			
		
    	
    	for(Map.Entry<Long, UserAccessRegionVO> res : allState.entrySet()){
    		state.add(res.getValue());
		}
    	userAccRegVO.setState(state);
    	
    	return userAccRegVO;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return userAccRegVO;
		}
   }
    public UserAccessRegionVO getAssemblyConsDetailsByStateIdUserId(Long stateId,Long userId)
    {
    	
    	UserAccessRegionVO userAccessRegionVO = null;
    	List<UserAccessRegionVO> constituency = new ArrayList<UserAccessRegionVO>();
    	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
    	Map<Long,UserAccessRegionVO> allConstituency = new HashMap<Long,UserAccessRegionVO>();
    	    	    	
    	try{
    		
    		List<Object[]> constiList = constituencyDAO.getAssConstituenciesByElectionTypeAndStateIdAndDistrictAccess(2L,stateId,userId);    	 
        	for(Object[] param : constiList){
        		userAccessRegionVO = new UserAccessRegionVO();
        		userAccessRegionVO.setConstituencyId((Long)param[0]);
        		userAccessRegionVO.setConstituencyName(param[1].toString());
        		userAccessRegionVO.setMessage(IConstants.NOT_AVAILABLE);
        		allConstituency.put((Long)param[0],userAccessRegionVO);
        	}
        	
    		List<Object[]> constituencyList = userConstituencyAccessInfoDAO.findByElectionTypeUserState(2L,userId,stateId);
    	
			if(constituencyList!=null){
				if(constituencyList.size()!=0){
					for(int i=0;i<constituencyList.size();i++){
						Object[] parms = (Object[])constituencyList.get(i); 
						userAccessRegionVO = new UserAccessRegionVO();
						userAccessRegionVO.setConstituencyId((Long)parms[0]);
						userAccessRegionVO.setConstituencyName((String)parms[1]);
						userAccessRegionVO.setMessage(IConstants.AVAILABLE);
						allConstituency.put((Long)parms[0],userAccessRegionVO);						
					}
				}
			}
			
		
    	
    	for(Map.Entry<Long, UserAccessRegionVO> res : allConstituency.entrySet()){
    		constituency.add(res.getValue());
		}
    	userAccRegVO.setConstituency(constituency);
    	
    	return userAccRegVO;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return userAccRegVO;
		}
      }
    public UserAccessRegionVO getParliConsDetailsByUserId(Long userId)
    {
    	
    	UserAccessRegionVO userAccessRegionVO = null;
    	List<UserAccessRegionVO> constituency = new ArrayList<UserAccessRegionVO>();
    	UserAccessRegionVO userAccRegVO = new UserAccessRegionVO();
    	Map<Long,UserAccessRegionVO> allConstituency = new HashMap<Long,UserAccessRegionVO>();
    	    	    	
    	try{
    		List<Constituency> constiList = constituencyDAO.getAllParliamentConstituenciesInCountryByStateAccessAndCountryAccess(1L,1L,userId);    	 
        	for(Constituency param : constiList){
        		userAccessRegionVO = new UserAccessRegionVO();
        		userAccessRegionVO.setConstituencyId(param.getConstituencyId());
        		userAccessRegionVO.setConstituencyName(param.getName());
        		userAccessRegionVO.setMessage(IConstants.NOT_AVAILABLE);
        		allConstituency.put(param.getConstituencyId(),userAccessRegionVO);
        	}
    		
    		List<Object[]> constituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
    	
			if(constituencyList!=null){
				if(constituencyList.size()!=0){
					for(int i=0;i<constituencyList.size();i++){
						Object[] parms = (Object[])constituencyList.get(i); 
						userAccessRegionVO = new UserAccessRegionVO();
						userAccessRegionVO.setConstituencyId((Long)parms[0]);
						userAccessRegionVO.setConstituencyName((String)parms[1]);
						userAccessRegionVO.setMessage(IConstants.AVAILABLE);
						allConstituency.put((Long)parms[0],userAccessRegionVO);						
					}
				}
			}
			
		
    	
    	for(Map.Entry<Long, UserAccessRegionVO> res : allConstituency.entrySet()){
    		constituency.add(res.getValue());
		}
    	userAccRegVO.setConstituency(constituency);
    	
    	return userAccRegVO;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return userAccRegVO;
		}
     }
    
    public UserAccessRegionVO saveUserCountryAccessDetail(Long userId,String countryIds)
    {
    	UserAccessRegionVO userAccessRegionVO = new UserAccessRegionVO();
    	List<String> elements = null;		
		try{
			userCountryAccessInfoDAO.deleteAllCountryAccess(userId);
			
			if(countryIds.length()!=0){
				userStateAccessInfoDAO.deleteAllStateAccess(userId);
				userDistrictAccessInfoDAO.deleteAllDistrictAccess(userId);
				userConstituencyAccessInfoDAO.deleteAllConstituencyAccessByUserId(userId);
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(countryIds).split(","))));	
							    
				for(int i=0;i<elements.size();i++){
					Long id = new Long(elements.get(i));
					UserCountryAccessInfo userCountryAccessInfo = new UserCountryAccessInfo();
					userCountryAccessInfo.setUserId(userId);
					userCountryAccessInfo.setCountryId(id);
					userCountryAccessInfoDAO.save(userCountryAccessInfo);
					}
				}
			
			userAccessRegionVO.setMessage(IConstants.SUCCESSFULLY_SAVED);
			return userAccessRegionVO;
		}catch(Exception e){
			userAccessRegionVO.setMessage("failed");
			return userAccessRegionVO;
		}
    	
    }
    public UserAccessRegionVO saveUserStateAccessDetail(Long userId,String stateIds){
    	UserAccessRegionVO userAccessRegionVO = new UserAccessRegionVO();
    	List<String> elements = null;	
        try{
        	userStateAccessInfoDAO.deleteAllStateAccess(userId);
			if(stateIds.length()!=0){
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(stateIds).split(","))));	
							    
				for(int i=0;i<elements.size();i++){
					Long id = new Long(elements.get(i));
					userDistrictAccessInfoDAO.deleteDistrictAccessByUserIdStateId(userId,id);
					userConstituencyAccessInfoDAO.deleteAllConstituencyAccessByUserIdStateId(userId,id);
					UserStateAccessInfo userStateAccessInfo = new UserStateAccessInfo();
                    userStateAccessInfo.setUserId(userId);
					userStateAccessInfo.setStateId(id);
					userStateAccessInfoDAO.save(userStateAccessInfo);
					}
				}	
			userAccessRegionVO.setMessage(IConstants.SUCCESSFULLY_SAVED);
			return userAccessRegionVO;
		}catch(Exception e){
			userAccessRegionVO.setMessage("failed");
			return userAccessRegionVO;
		}
    	
    }
    public UserAccessRegionVO saveUserDistrictAccessDetail(Long userId,Long stateId,String districtIds){
    	UserAccessRegionVO userAccessRegionVO = new UserAccessRegionVO();
    	List<String> elements = null;	
    	try{
    	   userDistrictAccessInfoDAO.deleteDistrictAccessByUserIdStateId(userId, stateId);
    	
    		// userDistrictAccessInfoDAO.deleteAllDistrictAccessByStateIdUserId(userId,stateId);
			if(districtIds.length()!=0){
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(districtIds).split(","))));	
							    
				for(int i=0;i<elements.size();i++){
					Long id = new Long(elements.get(i));
					userConstituencyAccessInfoDAO.deleteAllAssemblyAccessByScopeStateIdUserIdDistrictId(2L,userId,stateId,id);
					UserDistrictAccessInfo userDistrictAccessInfo = new UserDistrictAccessInfo();
					userDistrictAccessInfo.setUserId(userId);
					userDistrictAccessInfo.setDistrictId(id);
					userDistrictAccessInfoDAO.save(userDistrictAccessInfo);
					}
				}	
			userAccessRegionVO.setMessage(IConstants.SUCCESSFULLY_SAVED);
			return userAccessRegionVO;
		}catch(Exception e){
			userAccessRegionVO.setMessage("failed");
			return userAccessRegionVO;
		}
    	
    }
   public UserAccessRegionVO saveUserAssemblyConstituencyAccessDetail(Long userId,Long stateId,String constituencyIds){
	   UserAccessRegionVO userAccessRegionVO = new UserAccessRegionVO();
	   List<String> elements = null;
	   try{
		   userConstituencyAccessInfoDAO.deleteAllAssemblyAccessByScopeStateIdUserId(2L,userId,stateId);
			if(constituencyIds.length()!=0){
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(constituencyIds).split(","))));	
				for(int i=0;i<elements.size();i++){
				Long id = new Long(elements.get(i));
				UserConstituencyAccessInfo  userConstituencyAccessInfo = new UserConstituencyAccessInfo();
				userConstituencyAccessInfo.setUserId(userId);
				userConstituencyAccessInfo.setConstituencyId(id);
				userConstituencyAccessInfoDAO.save(userConstituencyAccessInfo);
					}
				}	
			userAccessRegionVO.setMessage(IConstants.SUCCESSFULLY_SAVED);
			return userAccessRegionVO;
		}catch(Exception e){
			userAccessRegionVO.setMessage("failed");
			return userAccessRegionVO;
		}
   	
    }
   public UserAccessRegionVO saveUserParliamentConstituencyAccessDetail(Long userId,String constituencyIds){
	   UserAccessRegionVO userAccessRegionVO = new UserAccessRegionVO();
	   List<String> elements = null;
	   try{
		   userConstituencyAccessInfoDAO.deleteAllParliamentAccessByScopeUserId(1L,userId);
			if(constituencyIds.length()!=0){
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(constituencyIds).split(","))));	
							    
				for(int i=0;i<elements.size();i++){
					Long id = new Long(elements.get(i));
					UserConstituencyAccessInfo  userConstituencyAccessInfo = new UserConstituencyAccessInfo();
					userConstituencyAccessInfo.setUserId(userId);
					userConstituencyAccessInfo.setConstituencyId(id);
					userConstituencyAccessInfoDAO.save(userConstituencyAccessInfo);
				
				}
			}	
			userAccessRegionVO.setMessage(IConstants.SUCCESSFULLY_SAVED);
			return userAccessRegionVO;
		}catch(Exception e){
			userAccessRegionVO.setMessage("failed");
			return userAccessRegionVO;
		}
   	
    }
   
   public List<SelectOptionVO> getAllRestrictedUsers()
   {
	   List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
	   List<Object[]> users = new ArrayList<Object[]>(0);
	   try{
		  users = userRolesDAO.getAllRestrictedUsers();
		   if(users != null && users.size() > 0)
			   for(Object[] params : users)
			   {
				   if(params[1] == null)
					   params[1] = "";
				   if(params[2] == null)
					   params[2] = "";
			   resultList.add(new SelectOptionVO((Long)params[0],params[1].toString() + " "+params[2].toString()));
			   }
			   
	   }
	   catch(Exception e)
	   {
		   Log.error("Exception Occured - "+e);
	   }
	return resultList;
   }
   
   public ResultStatus saveRestrictedUser(Long userID)
   {
	   ResultStatus resultStatus = new ResultStatus();
	   try{
		   if(userID != null)
		   {
		   User user = userDAO.get(userID);
		   user.set_loginRestriction("true");
		   userDAO.save(user);
		   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		   }
		   else
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	   }
	   catch(Exception e)
	   {
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		   e.printStackTrace();
		   
	   }
	return resultStatus;
   }
   public List<SelectOptionVO> getAllUsers()
   {
	   List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
	   List<Object[]> users = new ArrayList<Object[]>(0);
	   try{
		  users = userRolesDAO.getAllUsers();
		   if(users != null && users.size() > 0)
			   for(Object[] params : users)
			   {
				   if(params[1] == null)
					   params[1] = "";
				   if(params[2] == null)
					   params[2] = "";
			   resultList.add(new SelectOptionVO((Long)params[0],params[1].toString() + " "+params[2].toString()));
			   }
			   
	   }
	   catch(Exception e)
	   {
		   Log.error("Exception Occured - "+e);
	   }
	return resultList;
   }
   public ResultStatus saveUserInUserAccessIpAddress(Long userID,String IpAddress)
   {
	   ResultStatus resultStatus = new ResultStatus();
	   try{
		   // check duplicate IpAddress
		   List<Long> value = userAcessIpAddressDAO.checkDuplicateIpForUser(userID,IpAddress);
		   if(value.size() > 0)
		   {
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE); 
			  return resultStatus;
		   }
		   else
		   {
		   if(userID != null)
		   {
			   UserAcessIpAddress userAcessIpAddress = new UserAcessIpAddress();
			   userAcessIpAddress.set_userId(userDAO.get(userID));
			   if(IpAddress != null)
			   userAcessIpAddress.setIpAddress(IpAddress);
			   userAcessIpAddressDAO.save(userAcessIpAddress);
			   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		   }
		   else
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	   }
	   }
	   catch(Exception e)
	   {
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		   e.printStackTrace();
		   
	   }
	return resultStatus;
   }
   
   public List<UserDetailsInfoVO> getAllIpAddressForUser(Long userId)
   {
	   List<Object[]> list = null;
	  UserDetailsInfoVO userDetailsInfoVO = null;
	   List<UserDetailsInfoVO> resultList = new ArrayList<UserDetailsInfoVO>();
	   try{
		 list = userAcessIpAddressDAO.getAllIpAddressByUser(userId);
		if(list!= null && list.size() > 0)
		{
			for(Object[] params :list)
			{
				userDetailsInfoVO = new UserDetailsInfoVO();
				userDetailsInfoVO.setUserAccessRegionId((Long)params[1]);
				userDetailsInfoVO.setIpAddress(params[0] != null ? params[0].toString(): "");
				resultList.add(userDetailsInfoVO);
			}
		}
	   }
	   catch(Exception e)
	   {
		   Log.error("Exception Occured in getAllIpAddressForUser() method -"+e);
	   }
	return resultList;
   }
   
   public ResultStatus deleteUserIpAddress(List<UserDetailsInfoVO> list)
   {
	   ResultStatus resultStatus = new ResultStatus();
	   try{
		   if(list!= null && list.size() > 0)
		   {
		   for(UserDetailsInfoVO IpAddr : list)
		   {
			   userAcessIpAddressDAO.deleteUserIpAddressById(IpAddr.getUserAccessRegionId());
			   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		   }
		   }
		   else
			   resultStatus.setResultCode(ResultCodeMapper.FAILURE);  
	   }
	   catch(Exception e)
	   {
		   Log.error("Exception Occured in deleteUserIpAddress() method -"+e);
	   }
	return resultStatus;
   }
   
}
