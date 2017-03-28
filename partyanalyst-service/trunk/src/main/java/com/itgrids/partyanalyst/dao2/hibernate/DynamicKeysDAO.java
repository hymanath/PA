package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDynamicKeysDAO;
import com.itgrids.partyanalyst.model.DynamicKeys;

public class DynamicKeysDAO extends GenericDaoHibernate<DynamicKeys, Long> implements IDynamicKeysDAO{

	public DynamicKeysDAO() {
		super(DynamicKeys.class);
	}

	public List<String> getDynamicKeyValue(String key)
	{
		Query query = getSession().createQuery("select model.value from DynamicKeys model where model.key = :key");
		query.setParameter("key", key);
		return query.list();
	}
	
	public String getValueByKey(String key)
	{
		Query query = getSession().createQuery("SELECT model.value from DynamicKeys model where model.key = :key");
		query.setParameter("key",key);
		return (String)query.uniqueResult();
	}
	
}
