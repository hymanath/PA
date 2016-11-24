package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCasteStateInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfo;

public class TdpCadreCasteStateInfoDAO extends GenericDaoHibernate<TdpCadreCasteStateInfo,Long> implements ITdpCadreCasteStateInfoDAO {
	
	public TdpCadreCasteStateInfoDAO() {
		super(TdpCadreCasteStateInfo.class);
	}
	
	public int pushCadreCountsLocationWiseByCasteState(){
		Query query = getSession().createSQLQuery("CALL proceedureName();");
		return query.executeUpdate();  
	}
}
