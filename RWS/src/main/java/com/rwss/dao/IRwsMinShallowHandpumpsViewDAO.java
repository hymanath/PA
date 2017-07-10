package com.rwss.dao;

import java.math.BigDecimal;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinShallowHandpumpsView;

public interface IRwsMinShallowHandpumpsViewDAO extends GenericDao<RwsMinShallowHandpumpsView, String> {
	
	public Long getWaterSourceDeatils(InputVO inputVO, String supplyTypeSafe);

	public List<Object[]> getWaterSourceDeatilsGroupByLocation(InputVO inputVo);

	public BigDecimal gethabitationWatersupplyDetails(InputVO inputVO, String supplyTypeSafe);

	public List<Object[]> getWaterSourceDeatils2(InputVO inputVo, String supplyTypeSafe);

}
