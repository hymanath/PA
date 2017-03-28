package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreAchievedSmsDAO;
import com.itgrids.partyanalyst.model.TdpCadreAchievedSms;

public class TdpCadreAchievedSmsDAO extends GenericDaoHibernate<TdpCadreAchievedSms, Long> implements ITdpCadreAchievedSmsDAO{

	public TdpCadreAchievedSmsDAO() {
		super(TdpCadreAchievedSms.class);
	}

}
