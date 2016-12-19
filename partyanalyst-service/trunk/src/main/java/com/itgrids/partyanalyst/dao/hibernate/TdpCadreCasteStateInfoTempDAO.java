package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreCasteStateInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfoTemp;

public class TdpCadreCasteStateInfoTempDAO extends GenericDaoHibernate<TdpCadreCasteStateInfoTemp,Long> implements ITdpCadreCasteStateInfoTempDAO {

	public TdpCadreCasteStateInfoTempDAO(){
		super(TdpCadreCasteStateInfoTemp.class);
	}
	

}
