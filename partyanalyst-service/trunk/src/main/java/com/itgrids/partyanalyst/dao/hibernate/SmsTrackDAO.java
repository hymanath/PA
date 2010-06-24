package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISmsTrackDAO;
import com.itgrids.partyanalyst.model.SmsTrack;

public class SmsTrackDAO extends GenericDaoHibernate<SmsTrack, Long> implements
		ISmsTrackDAO {

	public SmsTrackDAO() {
		super(SmsTrack.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<SmsTrack> findByDescription(String description) {
		return getHibernateTemplate().find("from SmsTrack model where model.description = ?",description);
	}

	@SuppressWarnings("unchecked")
	public List<SmsTrack> findByRenewalDate(String renewalDate) {
		return getHibernateTemplate().find("from SmsTrack model where model.renewalDate = ?",renewalDate);
	}

	@SuppressWarnings("unchecked")
	public List<SmsTrack> findByRenewalSmsCount(Long renewalSmsCount) {
		return getHibernateTemplate().find("from SmsTrack model where model.renewalSmsCount = ?",renewalSmsCount);
	}

	@SuppressWarnings("unchecked")
	public List<SmsTrack> findBySsmsTrackId(Long smsTrackId) {
		return getHibernateTemplate().find("from SmsTrack model where model.smsTrackId = ?",smsTrackId);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SmsTrack> findLatestRenewalDate(Long registrationId) {
		return getHibernateTemplate().find("from SmsTrack model where model.registration.registrationId = ? order by model.renewalDate desc ",registrationId);
	}
	
}
