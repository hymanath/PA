package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationBlockDAO;
import com.itgrids.partyanalyst.model.DelimitationBlock;

public class DelimitationBlockDAO extends GenericDaoHibernate<DelimitationBlock, Long> implements IDelimitationBlockDAO{

	public DelimitationBlockDAO(){
		super(DelimitationBlock.class);
	}

	@SuppressWarnings("unchecked")
	public List<DelimitationBlock> findByDelimitationWardIdAndBlock(
			Long delimWardId, Long blockId) {
		Object[] params = {delimWardId,blockId};
		return getHibernateTemplate().find("from DelimitationBlock model where model.delimitationWard.delimitationWardId = ? and model.block.blockId = ?",params);
	}
}
