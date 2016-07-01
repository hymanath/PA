package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGenderWiseEventInfoDAO;
import com.itgrids.partyanalyst.model.AgeWiseEventInfo;
import com.itgrids.partyanalyst.model.GenderWiseEventInfo;

public class GenderWiseEventInfoDAO extends GenericDaoHibernate<GenderWiseEventInfo, Long> implements IGenderWiseEventInfoDAO {

	public GenderWiseEventInfoDAO(){
		super(GenderWiseEventInfo.class);
	}
}
