package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightInstallationTarget;

public interface ILightInstallationTargetDAO extends GenericDao<LightInstallationTarget,Long> {

	public List<Object[]> getLedTargetVendorWise(Date date);
}
