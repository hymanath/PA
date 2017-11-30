package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LocalElectionBody;

public interface ILocalElectionBodyDAO extends GenericDao<LocalElectionBody, Long> {

	public List<Object[]> getLocalElectionBodyByTehsilId(List<Long> tehsilIds);
}
