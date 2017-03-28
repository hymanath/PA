package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;
import com.itgrids.partyanalyst.model.SmsHistory;

public class SmsHistoryDAO extends GenericDaoHibernate<SmsHistory, Long> implements
		ISmsHistoryDAO {

	public SmsHistoryDAO() {
		super(SmsHistory.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<SmsHistory> findByHistoryId(Long historyId) {
		return getHibernateTemplate().find("from SmsHistory model where model.historyId = ?",historyId);
	}

	
	@SuppressWarnings("unchecked")
	public List<SmsHistory> findByLocationId(Long locationId) {
		return getHibernateTemplate().find("from SmsHistory model where model.locationId = ?",locationId);
	}

	
	@SuppressWarnings("unchecked")
	public List<SmsHistory> findByLocationType(String locationType) {
		return getHibernateTemplate().find("from SmsHistory model where model.locationType = ?",locationType);
	}

	
	@SuppressWarnings("unchecked")
	public List<SmsHistory> findByMobileNumber(String mobileNumber) {
		return getHibernateTemplate().find("from SmsHistory model where model.mobileNumber = ?",mobileNumber);
	}

	@SuppressWarnings("unchecked")
	public List<SmsHistory> findBySentDate(String sentDate) {
		return getHibernateTemplate().find("from SmsHistory model where model.sentDate = ?",sentDate);
	}

	@SuppressWarnings("unchecked")
	public List<Long> getTotalSmsSentByUserAfterRenewal(Long trackId) {
		return getHibernateTemplate().find("select count(*) from SmsHistory model where model.smsTrack.smsTrackId = ?",trackId);
	}
}
