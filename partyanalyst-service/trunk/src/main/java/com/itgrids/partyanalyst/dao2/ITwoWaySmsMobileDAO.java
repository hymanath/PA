package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TwoWaySmsMobile;

public interface ITwoWaySmsMobileDAO  extends GenericDao<TwoWaySmsMobile, Long>{
	public List<TwoWaySmsMobile> getMobileInfo(String mobileNo);
}
