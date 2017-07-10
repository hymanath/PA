package com.rwss.dao;

import java.math.BigDecimal;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinHandpumpsView;

public interface IRwsMinHandpumpsViewDAO extends GenericDao<RwsMinHandpumpsView,String> {
	
	public Long getWaterSourceDeatils(InputVO inputVo, String supplyTypeSafe);

	public List<Object[]> getWaterSourceDeatilsGroupByLocation(InputVO inputVo);

	public BigDecimal gethabitationWatersupplyDetails(InputVO inputVO,String supplyTypeSafe);

	public List<Object[]> getWaterSourceDeatils2(InputVO inputVo, String type);

}
