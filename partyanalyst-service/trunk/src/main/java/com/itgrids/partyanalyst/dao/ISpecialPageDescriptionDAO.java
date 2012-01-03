package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.model.SpecialPageDescription;

public interface ISpecialPageDescriptionDAO extends GenericDao<SpecialPageDescription,Long>{
	
	public List<Object> getSpecialPageDescription(Long specialPageId);

	public List<Object[]> getEventDescription(Long specialPageId);

	public Integer deleteEventProfileDescriptionById(Long specialPageDescriptionId);
	
	public List<Object> getMaxOrderNo(Long specialPageId);
}
