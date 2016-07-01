package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAgeWiseEventInfoDAO;
import com.itgrids.partyanalyst.model.AgeWiseEventInfo;
import com.itgrids.partyanalyst.model.SubCasteEventInfo;

public class AgeWiseEventInfoDAO extends GenericDaoHibernate<AgeWiseEventInfo, Long> implements IAgeWiseEventInfoDAO {

	public AgeWiseEventInfoDAO(){
		super(AgeWiseEventInfo.class);
	}

}
