package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.model.AppointmentLableStatus;

public interface IAppointmentLableStatusDAO extends GenericDao<AppointmentLableStatus, Long> {
	public List<Object[]> getAppmntLblStatusList();

}
