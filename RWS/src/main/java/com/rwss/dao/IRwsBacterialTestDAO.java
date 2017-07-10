package com.rwss.dao;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsBacterialTest;

public interface IRwsBacterialTestDAO extends GenericDao<RwsBacterialTest,Long>{

	public Long getBacterialTestCountYearWise(InputVO inputVO);
}
