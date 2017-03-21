
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IcustomReportFileDAO;
import com.itgrids.partyanalyst.model.CustomReportFile;

public class CustomReportFileDAO extends GenericDaoHibernate<CustomReportFile, Long> implements IcustomReportFileDAO {

	public CustomReportFileDAO() {
		super(CustomReportFile.class);
	}
}