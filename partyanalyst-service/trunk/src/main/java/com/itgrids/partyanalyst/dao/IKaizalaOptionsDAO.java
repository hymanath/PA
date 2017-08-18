package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaActions;
import com.itgrids.partyanalyst.model.KaizalaOptions;

public interface IKaizalaOptionsDAO extends GenericDao<KaizalaOptions, Long> {
	public Long getOptionId(Long questionId,String answer);
}