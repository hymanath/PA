package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalToursMonthDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalToursMonth;


public class SelfAppraisalToursMonthDAO extends GenericDaoHibernate<SelfAppraisalToursMonth, Long> implements ISelfAppraisalToursMonthDAO {

	public SelfAppraisalToursMonthDAO() {
		super(SelfAppraisalToursMonth.class);
	}
}
