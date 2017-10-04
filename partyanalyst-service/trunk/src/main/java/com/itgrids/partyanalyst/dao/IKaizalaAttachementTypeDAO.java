package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaAttachementType;

public interface IKaizalaAttachementTypeDAO extends GenericDao<KaizalaAttachementType, Long>{
	public Long checkAttachementTypeExistence(String type);
}
