package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TirupathiByeleMobileBooth;

public interface ITirupathiByeleMobileBoothDAO extends GenericDao<TirupathiByeleMobileBooth, Long>{
	public List<String> getMobileNosOfBooth(Long boothId);
}
