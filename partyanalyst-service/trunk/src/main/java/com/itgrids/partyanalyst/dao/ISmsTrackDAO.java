package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SmsTrack;

public interface ISmsTrackDAO extends GenericDao<SmsTrack, Long> {
	
	public List<SmsTrack> findBySsmsTrackId(Long smsTrackId);
	
	public List<SmsTrack> findByRenewalSmsCount(Long renewalSmsCount);
	
	public List<SmsTrack> findByRenewalDate(String renewalDate);
	
	public List<SmsTrack> findByDescription(String description);
	
	public List<SmsTrack> findLatestRenewalDate(Long registrationId);
	
	public Object getRemainingSMSCountForAUser(Long userId);
	
	public List<Object[]> getUserSmsDetails(Long userId);
	
	public Integer updateRemainingSmsLeftForUser(Long userId, Long count);
	
}
