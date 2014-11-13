package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabLogInAuth;

public interface ITabLogInAuthDAO  extends GenericDao<TabLogInAuth,Long>{

	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei);
	
	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei1,String imei2);
	
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei);
	
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei1,String imei2);
	
	public void updateRecordBelongsToUserOrNot(Long userId,String imei);
	
	public void updateRecordBelongsToUserOrNot(Long userId,String imei1,String imei2);
	
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei);
	
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei1,String imei2);
}
