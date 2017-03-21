package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICustomReportLocationDAO;
import com.itgrids.partyanalyst.model.CustomReportLocation;

public class CustomReportLocationDAO extends GenericDaoHibernate<CustomReportLocation, Long> implements ICustomReportLocationDAO{


		public CustomReportLocationDAO() {
			super(CustomReportLocation.class);
		}
	


}
