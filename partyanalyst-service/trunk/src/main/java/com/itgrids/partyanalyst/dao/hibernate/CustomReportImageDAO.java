
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICustomReportImageDAO;
import com.itgrids.partyanalyst.model.CustomReportFile;
import com.itgrids.partyanalyst.model.CustomReportImage;

public class CustomReportImageDAO extends GenericDaoHibernate<CustomReportImage, Long> implements ICustomReportImageDAO {

	public CustomReportImageDAO() {
		super(CustomReportImage.class);
	}
}