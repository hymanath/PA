package com.itgrids.partyanalyst.dao.hibernate;



import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMomAtrSourceTypeDAO;
import com.itgrids.partyanalyst.model.MomAtrSourceType;


public class MomAtrSourceTypeDAO extends GenericDaoHibernate<MomAtrSourceType, Long> implements IMomAtrSourceTypeDAO {

	 public MomAtrSourceTypeDAO(){
		 super(MomAtrSourceType.class);
	    }
}
