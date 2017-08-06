package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertInputsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.KaizalaResponderInfo;

public interface IKaizalaResponderInfoDAO extends GenericDao<KaizalaResponderInfo, Long> {
	public List<Long> getRespondentId(String modileNum);
}