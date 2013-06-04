package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IUserService;

public class UserService implements IUserService{/*
	public static Logger log = Logger.getLogger(UserService.class); 
	
	private IUserDAO userDAO;
	private ResultStatus resultStatus;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	*//**
	 * @author srishailam
	 * @param Long
	 * @return Long
	 * 
	 *//*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Long getUserDistrictByUserId(Long userId){
		log.debug("entered into getUserDistrictByUserId() method ");
		List userIds = new ArrayList();
		long userDistrictId = 0L;
	//	List locationIds = null;
		Object[] values = null;
		try{
		userIds.add(userId);
		List<Object[]> userLocations = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
		if(userLocations !=null && userLocations.size()>0){
			values = (Object[])userLocations.get(0);
			locationIds = new ArrayList();
			locationIds.add((Long)values[0]);
			locationIds.add((Long)values[1]);
			locationIds.add((Long)values[2]);
			locationIds.add((Long)values[3]);
			locationIds.add((Long)values[4]);
			locationIds.add((Long)values[5]);
			userDistrictId = (Long)values[2];
			}
		}catch(Exception e){
			log.error("error occured in getUserDistrictByUserId() method");
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return userDistrictId;
	}
	
*/}
