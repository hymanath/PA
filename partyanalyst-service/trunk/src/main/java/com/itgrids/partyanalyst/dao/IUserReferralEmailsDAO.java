package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserReferralEmails;

public interface IUserReferralEmailsDAO extends GenericDao<UserReferralEmails, Long>{
	
	public List<String>getUserReferencedEmails(Long userId);
}
