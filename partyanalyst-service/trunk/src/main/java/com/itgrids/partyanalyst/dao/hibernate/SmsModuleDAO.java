package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISmsModuleDAO;
import com.itgrids.partyanalyst.model.SmsModule;

public class SmsModuleDAO extends GenericDaoHibernate<SmsModule, Long> implements
		ISmsModuleDAO {

	public SmsModuleDAO() {
		super(SmsModule.class);
	}

	@SuppressWarnings("unchecked")
	public List<SmsModule> findByModuleName(String moduleName) {
		return getHibernateTemplate().find("from SmsModule model where model.moduleName = ?",moduleName);
	}

	@SuppressWarnings("unchecked")
	public List<SmsModule> findBySmsModuleId(Long smsModuleId) {
		return getHibernateTemplate().find("from SmsModule model where model.smsModuleId = ?",smsModuleId);
	}

}
