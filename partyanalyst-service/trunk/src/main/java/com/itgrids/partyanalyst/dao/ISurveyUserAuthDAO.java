package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserAuth;

public interface ISurveyUserAuthDAO extends GenericDao<SurveyUserAuth, Long>{
	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei);
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei);
	public void updateRecordBelongsToUserOrNot(Long userId,String imei);
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei);
	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei1,String imei2);
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei1,String imei2);
	public void updateRecordBelongsToUserOrNot(Long userId,String imei1,String imei2);
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei1,String imei2);
	public List<Object[]> getAuthDetailsByUserId(String userName);
	public List<Object[]> getAuthDetailsByImei(String imei);
	public void updateStatus(Long authId,String cause,Long userId);
}
