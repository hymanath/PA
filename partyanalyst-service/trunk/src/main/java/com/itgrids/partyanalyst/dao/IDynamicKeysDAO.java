package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DynamicKeys;

public interface IDynamicKeysDAO extends GenericDao<DynamicKeys, Long>{

	public List<String> getDynamicKeyValue(String key);
	public String getValueByKey(String key);
}
