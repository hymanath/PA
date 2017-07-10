package com.rwss.dao;

import java.math.BigDecimal;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinSchemeSourcesView;

public interface IRwsMinSchemeSourcesViewDAO extends GenericDao<RwsMinSchemeSourcesView, String> {
	
	public Long getWaterSourceDeatils(InputVO inputVo,String sourceType, String supplyTypeSafe);

	public List<Object[]> getWaterSourceDeatilsGroupByLocation(InputVO inputVo,String string);

	public BigDecimal gethabitationWatersupplyDetails(InputVO inputVO,String supplyTypeSafe, String string);

	public List<Object[]> getWaterSourceDeatils2(InputVO inputVo, String string,String supplyTypeSafe);

}
