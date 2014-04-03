package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.InformationManager;

public interface IInformationManagerDAO extends GenericDao<InformationManager, Long>{
	public List<Object[]> getInformationUsers(Long userId);
	public List<String> getMobileNosForReceiverIds(List<Long> receiverIds);
}
