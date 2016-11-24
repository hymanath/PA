package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreGenderInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreGenderInfo;

public class TdpCadreGenderInfoDAO extends GenericDaoHibernate<TdpCadreGenderInfo,Long> implements ITdpCadreGenderInfoDAO {
	
	public TdpCadreGenderInfoDAO() {
		
		super(TdpCadreGenderInfo.class);
	}
	
	public int pushCadreCountsLocationWiseByGender(){
		Query query = getSession().createSQLQuery("CALL proceedureName();");
		return query.executeUpdate();  
	}
}
