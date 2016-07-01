package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICasteCategoryEventInfoDAO;
import com.itgrids.partyanalyst.model.CasteCategoryEventInfo;
import com.itgrids.partyanalyst.model.Debate;

public class CasteCategoryEventInfoDAO extends GenericDaoHibernate<CasteCategoryEventInfo, Long> implements ICasteCategoryEventInfoDAO {
 
	private CasteCategoryEventInfoDAO(){
		super(CasteCategoryEventInfo.class);
	}
	

}
