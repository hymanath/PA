package com.rwss.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinAssetView;

public interface IRwsMinAssetViewDAO extends GenericDao<RwsMinAssetView,String> {

	public List<Object[]> getAssetTypeCountDetails(InputVO inputVO);

	public List<Object[]> getAssestDetailsByAssetType(InputVO inputVO);

	public List<String> getAvliableAssets();
}
