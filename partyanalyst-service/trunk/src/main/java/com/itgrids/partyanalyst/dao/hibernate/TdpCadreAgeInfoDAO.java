package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreAgeInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreAgeInfo;

public class TdpCadreAgeInfoDAO extends GenericDaoHibernate<TdpCadreAgeInfo,Long> implements ITdpCadreAgeInfoDAO {
	
	public TdpCadreAgeInfoDAO() {
		super(TdpCadreAgeInfo.class);
	}
	
	public int pushCadreCountsLocationWiseByAge(){
		Query query = getSession().createSQLQuery("CALL proceedureName();");
		return query.executeUpdate();  
	}
}
