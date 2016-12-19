package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreGenderInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreGenderInfoTemp;

public class TdpCadreGenderInfoTempDAO extends GenericDaoHibernate<TdpCadreGenderInfoTemp,Long> implements ITdpCadreGenderInfoTempDAO {

	public TdpCadreGenderInfoTempDAO(){
		super(TdpCadreGenderInfoTemp.class);
	}

}
