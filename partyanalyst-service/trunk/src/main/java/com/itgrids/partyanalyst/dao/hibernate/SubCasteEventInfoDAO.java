package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISubCasteEventInfoDAO;
import com.itgrids.partyanalyst.model.CasteCategoryEventInfo;
import com.itgrids.partyanalyst.model.SubCasteEventInfo;

public class SubCasteEventInfoDAO extends GenericDaoHibernate<SubCasteEventInfo, Long> implements ISubCasteEventInfoDAO {
	
	public SubCasteEventInfoDAO(){
		super(SubCasteEventInfo.class);
	}

	

}
