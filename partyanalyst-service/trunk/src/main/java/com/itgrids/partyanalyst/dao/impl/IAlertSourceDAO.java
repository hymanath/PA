package com.itgrids.partyanalyst.dao.impl;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityAttendance;
import com.itgrids.partyanalyst.model.AlertSource;

public interface IAlertSourceDAO  extends
GenericDao<AlertSource, Long>{
	public List<Long> getIdOfName(String source);
}
