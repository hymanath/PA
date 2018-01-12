package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.EntitlementsGroupEntitlementUrl;

public interface IEntitlementsGroupEntitlementUrlDAO extends GenericDao<EntitlementsGroupEntitlementUrl, Long> {
	public List<Object[]> getUserIdsByEntitlementsLogin(Long userId);

}
